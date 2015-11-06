/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
// [참고] 요소가 복사 되지 않을때
// ElementCollector.collectExtraElement 에서 수집되지 않는 요소를 추가한다.
// ElementCopier 해당 요소에 대한 조건식 점검 후 추가한다.
// addExtraElements(리팩토링대상) 도 해당 요소건에 대해 수정할 것이 있는지 체크한다.
package nexcore.tool.uml.ui.core.util.paste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util.paste</li>
 * <li>설  명 : ElementPaster</li>
 * <li>작성일 : 2012. 1. 3.</li>
 * <li>작성자 : 김기선</li>
 * </ul>
 */
public class ElementPaster {
    /** 성공여부. 단지 코드 가독성을 높이기 위한 상수임. */
    private static final boolean SUCCESSFUL = true;

    /** 복사시 사용되는 어노테이션 명 */
    private static final String EANNO_PASTE = "ELEMENT_PASTER_MARKED"; //$NON-NLS-1$

    /** 복사할 요소들을 수집해 놓은 Map */
    private Map<EObject, EObject> elements = null;

    /** 에러 메시지 */
    String exceptedElementsStr = UICoreConstant.EMPTY_STRING;

    /**
     * 클립보드에 담겨져 있는 요소들을 선택한 모델의 위치에 붙여 넣는다. > 기존 ProjectUtil 수정 및 개선
     * 
     * @param parent
     *            요소들을 넣을 상위 부모 요소
     * @param elementsToPaste
     *            붙여넣을 요소들
     */
    public void paste(EObject parent, Collection<Object> elementsToPaste) {
        boolean copyElementsInDiagram = false;
        EObject copied = null;
        EObject original = null;

        Set<EObject> collectedEObjs = null;
        Collection<EObject> copiedEObjs = null;
        Object[] sortedElms = elementsToPaste.toArray();

        // 복사가능 여부 체크
        if (!isPastable(parent, sortedElms)) {
            showErrorMessage();
            return;
        }

        // 복사할 것인지 참조할 것인지 묻는다.
        try {
            copyElementsInDiagram = checkContainingDiagram(sortedElms);
        } catch (Exception e) {
            return;
        }
        
        if (copyElementsInDiagram) {
            Arrays.sort(sortedElms, new ElementComparator());
            collectedEObjs = new ElementCollector(sortedElms).collect();
            mark(collectedEObjs);
            copiedEObjs = EcoreUtil.copyAll(collectedEObjs);
        }

        elements = new HashMap<EObject, EObject>();
        for (int i = 0; i < sortedElms.length; i++) {
            if (!(sortedElms[i] instanceof EObject))
                continue;
            if (this.elements.get(sortedElms[i]) != null)
                continue;

            original = (EObject) sortedElms[i];

            // 요소 복사
            if (copyElementsInDiagram) {
                copied = getCopiedElement(original, copiedEObjs);
            }
            else
                copied = copyElement(original);
            if (copied == null) {
                setErrorMessage(parent, copied);
                continue;
            }

            checkElementHasBeenPasted(original, copied);

            // 똑같은 이름을 가진 같은 요소가 존재하면 이름을 변경_기존 코드
            renameIfExist(parent, copied);

            // 요소가 Diagram일 경우 복사
            if (copyDiagram(parent, copied, original, copiedEObjs, copyElementsInDiagram) == SUCCESSFUL)
                continue;

            // 복사하려는 요소가 해당 부모로 복사 가능한지 체크한 후 복사
            if (ElementCopier.copy(parent, copied, original))
                continue;

            // 복사가 되지 않았으면 메세지를 보여준다.
            setErrorMessage(parent, copied);
        }

        // 복사안된 요소들 복사 한후 복사하려고 마킹 해놓았던 요소들을 언마킹 한다
        if (copyElementsInDiagram) {
            addExtraElements(parent, collectedEObjs, copiedEObjs);
            unmark(collectedEObjs);
        }

        // 붙이려고 하는 요소 중 다이어그램이 있을 경우에 메세지를 보여준다.
        showErrorMessage();

        elements = null;
    }

    /**
     * 붙여넣기 가능여부를 체크한다.
     * 
     * @param parent
     *            요소를 붙여넣을 부모
     * @param sortedElms
     *            붙여넣을 요소들
     * @return boolean 붙여넣기 가능 여부
     */
    private boolean isPastable(EObject parent, Object[] sortedElms) {
        elements = new HashMap<EObject, EObject>();
        for (int i = 0; i < sortedElms.length; i++) {
            if (!(sortedElms[i] instanceof EObject))
                continue;
            if (this.elements.get(sortedElms[i]) != null)
                continue;

            if (sortedElms[i] instanceof Diagram) {
                Diagram diagram = (Diagram) sortedElms[i];
                // 시퀀스다이어그램 자체를 붙여넣기 할 수 없다.
                // Collaboration 단위로 복사해야함.
                if (diagram.getType() == DiagramType.SEQUENCE_DIAGRAM) {
                    setErrorMessage(parent, diagram);
                    return false;
                }
            }
            
            EObject eObject = (EObject) sortedElms[i];
            if (!ElementCopier.isCopyable(parent, eObject, eObject))
                setErrorMessage(parent, eObject);
            checkElementHasBeenPasted(eObject, eObject);
        }
        elements = null;

        if (exceptedElementsStr.length() > 0)
            return false;
        return true;
    }

    /**
     * 복사할 수 없는 요소들을 에러 메시지로 표시한다.
     */
    private void showErrorMessage() {
        if (exceptedElementsStr.length() > 0)
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.LABEL_NEXCORE_UML_MODELER,
                exceptedElementsStr);
    }

    /**
     * 복사가 안된 요소들은 메시지로 보여지도록 한다.
     * 
     * @param parent
     *            요소를 복사시킬 부모
     * @param eObject
     *            복사가 안되는 요소
     */
    private void setErrorMessage(EObject parent, EObject eObject) {
        // String parentName = null;
        // if (parent instanceof NamedElement) parentName =
        // ((NamedElement)parent).getName();
        // else if (parent instanceof Diagram) parentName =
        // ((Diagram)parent).getName();

        String elementName = null;
        if (eObject instanceof NamedElement)
            elementName = ((NamedElement) eObject).getName();
        else if (eObject instanceof Diagram)
            elementName = ((Diagram) eObject).getName();

        if (elementName != null) {
            if (UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr)) {
                exceptedElementsStr = UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_PASTE, elementName);
            } else {
                exceptedElementsStr += UICoreConstant.PROJECT_CONSTANTS__COMMA + elementName;
            }
        }
    }

    /**
     * UML 요소를 복사한다. (ProjectUtil의 기존 코드)
     * 
     * @param original
     *            복사하려는 UML 요소
     * @return EObject 복사된 UML 요소
     */
    private EObject copyElement(EObject original) {
        EObject copied = null;

        // //////////////////////////////////////////////////////
        // Association 연결에 따라 생성된 Property는 복사하지 않는다.
        // 추후에 연결선 복사 기능은 완성 시 삭제하도록 한다.
        List<Property> tempProperty = new ArrayList<Property>();
        if (original instanceof Class) {
            Class clazz = (Class) original;
            for (Property childProperty : clazz.getOwnedAttributes()) {
                if (childProperty.getAssociation() != null) {
                    tempProperty.add(childProperty);
                }
            }
            clazz.getOwnedAttributes().removeAll(tempProperty);
        }
        // ///////////////////////////////////////////////////////
        copied = EcoreUtil.copy(original);
        // //////////////////////////////////////////////////////
        // 노드 복사가 끝난 후엔 다시 Property를 추가해 준다.
        if (original instanceof Class) {
            ((Class) original).getOwnedAttributes().addAll(tempProperty);
        }

        return copied;
    }

    /**
     * 복사안된 요소들을 복사한다.
     * 
     * @param parent
     *            요소들을 복사시킬 위치
     * @param collectedEObjs
     *            복사할 요소
     * @param copiedEObjs
     */
    private void addExtraElements(EObject parent, Collection<EObject> collectedEObjs, Collection<EObject> copiedEObjs) {
        // 일반 요소들이 담길 상위 패키지 선택
        while (!(parent instanceof Package)) {
            parent = parent.eContainer();
        }

        if (parent != null && parent instanceof Package) {
            Iterator<EObject> iter = collectedEObjs.iterator();
            while (iter.hasNext()) {
                EObject original = iter.next();
                if (original instanceof Element) {
                    EObject copied = getCopiedElement(original, copiedEObjs);
                    if (copied == null)
                        continue;

                    EObject parentToPaste = null;
                    if ((original.eContainer() instanceof Activity) || (original.eContainer() instanceof Interaction)) {
                        parentToPaste = elements.get(original.eContainer());

                    } else if (original.eContainer() instanceof Collaboration) {
                        Collaboration collaborationOrg = (Collaboration) original.eContainer();
                        Collaboration collaboration = (Collaboration) elements.get(collaborationOrg);
                        if (collaboration == null) {
                            collaboration = createCollaboration((Package) parent, collaborationOrg.getName());
                            elements.put(collaborationOrg, collaboration);
                        }
                        parentToPaste = collaboration;

                    } else if (copied instanceof PackageableElement) {
                        Package parentPackage = getParentPackage((Package) parent, original);
                        parentToPaste = parentPackage;
                    }

                    if (parentToPaste == null)
                        continue;

                    renameIfExist(parentToPaste, copied);
                    ElementCopier.copy(parentToPaste, copied, original);
                }
            }
        }
    }

    /**
     * 기존 UML 요소가 속한 패키지 구조 상의 부모를 반환한다.
     * 
     * @param root
     *            UML요소가 속할 수 있는 최상위 부모
     * @param original
     *            원 UML 요소
     * @return Package 복사된 UML 요소가 속해야 할 패키지
     */
    private Package getParentPackage(Package root, EObject original) {
        // Dependency는 원래 추가하려는 패키지(여기선 root)에 그냥 추가시킨다.
        if (original instanceof Dependency)
            return root;

        Package upper = root;
        Package prevPackage = null;
        Package parent = (Package) original.eContainer();
        while ((parent instanceof Package) && !(parent instanceof Model)) {
            Package copied = (Package) elements.get(parent);
            if (copied == null) {
                copied = UMLFactory.eINSTANCE.createPackage();
                copied.setName(parent.getName());
                elements.put(parent, copied);
                if (upper == root)
                    upper = copied;
                if (prevPackage != null)
                    copied.getPackagedElements().add(prevPackage);
                prevPackage = copied;

            } else {
                if (upper == root)
                    return copied;
                else {
                    if (prevPackage != null)
                        copied.getPackagedElements().add(prevPackage);
                    return upper;
                }
            }

            if (!(parent.eContainer() instanceof Package))
                break;
            parent = (Package) parent.eContainer();
        }

        if (prevPackage != null)
            root.getPackagedElements().add(prevPackage);
        renameIfExist(root, prevPackage);
        return upper;
    }

    /**
     * Annotation을 이용하여 마킹 한 것을 제거한다.
     * 
     * @param elements
     *            언마킹하려는 요소
     */
    private void unmark(Set<EObject> elements) {
        Iterator<EObject> iter = elements.iterator();
        while (iter.hasNext()) {
            EObject eObj = (EObject) iter.next();
            if (eObj instanceof Element) {
                Element elm = (Element) eObj;
                EAnnotation eAnno = elm.getEAnnotation(EANNO_PASTE);
                if (eAnno != null)
                    elm.getEAnnotations().remove(eAnno);
            }
        }
    }

    /**
     * 복사된 요소를 반환한다.
     * 
     * @param eObject
     *            원 요소
     * @param copiedEObjs
     *            복사된 요소가 담겨진 콜렉션
     * @return EObject 복사된 요소
     */
    private EObject getCopiedElement(EObject eObject, Collection<EObject> copiedEObjs) {
        if (!(eObject instanceof Element))
            return null;

        EAnnotation eAnnoOrg = ((Element) eObject).getEAnnotation(EANNO_PASTE);
        if (eAnnoOrg == null)
            return null;

        String id = eAnnoOrg.getDetails().get(EANNO_PASTE);
        Iterator<EObject> iter = copiedEObjs.iterator();
        while (iter.hasNext()) {
            EObject eObj = (EObject) iter.next();
            if (eObj instanceof Element) {
                Element elm = (Element) eObj;
                EAnnotation eAnno = elm.getEAnnotation(EANNO_PASTE);
                if (eAnno != null && id.equals(eAnno.getDetails().get(EANNO_PASTE))) {
                    elm.getEAnnotations().remove(eAnno);
                    ((Element) eObject).getEAnnotations().remove(eAnnoOrg);
                    copiedEObjs.remove(eObj);
                    return eObj;
                }
            }
        }
        return null;
    }

    /**
     * 복사하려는 요소에 Annotation을 이용하여 마킹을 해 둔다.
     * 
     * @param collectedEObjs
     *            복사하려는 요소
     */
    private void mark(Collection<EObject> elements) {
        int uniqId = 0;
        Iterator<EObject> iter = elements.iterator();
        while (iter.hasNext()) {
            EObject eObj = (EObject) iter.next();
            if (eObj instanceof Element) {
                Element elm = (Element) eObj;
                EAnnotation eAnno = elm.getEAnnotation(EANNO_PASTE);
                if (eAnno == null)
                    eAnno = elm.createEAnnotation(EANNO_PASTE);
                eAnno.getDetails().put(EANNO_PASTE, String.valueOf(uniqId++));
            }
        }
    }

    /**
     * 복사하려는 요소 내 다이어그램이 존재하는지 점검하고, 있다면 해당 다이어그램 내 속해있는 UML 요소들을 참조할 것인지 복사할
     * 것인지를 결정한다.
     * 
     * @param sortedElms
     *            복사할 UML 요소들
     * @return boolean true면 다이어그램 내 존재하는 UML 요소들을 복사, false는 참조
     * @exception Exception
     *                사용자가 취소를 하였으면 발생한다.
     */
    private boolean checkContainingDiagram(Object[] sortedElms) throws Exception {
        // 복사하려는 요소 내 다이어그램 검사
        for (int i = 0; i < sortedElms.length; i++) {
            if (!(sortedElms[i] instanceof EObject))
                continue;

            EObject eObj = (EObject) sortedElms[i];
            boolean found = false;
            if (eObj instanceof Diagram)
                found = true;
            if (!found) {
                Iterator<EObject> iter = eObj.eAllContents();
                while (iter.hasNext()) {
                    if (iter.next() instanceof Diagram) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                int result = new PasteDialog(null).open();
                if (result == IDialogConstants.CANCEL_ID)
                    throw new Exception();
                return result == PasteDialog.COPY;
            }
        }
        return false;
    }

    /**
     * UML요소 중에서 다이어그램을 가장 먼저 나에도록 정렬하는 비교자
     */
    private class ElementComparator implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            if (o1 instanceof Diagram && o2 instanceof Diagram) {
                Diagram d1 = (Diagram) o1;
                if (d1.getType() == DiagramType.CLASS_DIAGRAM)
                    return -1;

                Diagram d2 = (Diagram) o2;
                if (d2.getType() == DiagramType.CLASS_DIAGRAM)
                    return 1;

            } else if (o1 instanceof Diagram) {
                return -1;

            } else if (o2 instanceof Diagram) {
                return 1;
            }

            return 0;
        }
    }

    /**
     * 해당 UML 요소가 이미 붙여졌음을 체크한다.
     * 
     * @param original
     *            붙여 넣을 UML 요소의 원본
     * @param copied
     *            복사된 UML 요소 (붙여 넣을 대상)
     */
    private void checkElementHasBeenPasted(EObject original, EObject copied) {
        elements.put(original, copied);
        Iterator<EObject> iter = original.eAllContents();
        while (iter.hasNext()) {
            EObject eObjChld = iter.next();
            elements.put(eObjChld, eObjChld); // 자식요소들도 추가되었음을 알린다.
        }
    }
    
    /**
     * 
     * applyReferencedEvent
     *  
     * @param copied
     * @param orgEvent
     * @param copiedEvent void
     */
    private void applyReferencedEvent(EObject copied, Event orgEvent, Event copiedEvent) {
        if(copied instanceof Interaction || copied instanceof Collaboration) {
            EList<Element> allOwnedElements = ((Classifier) copied).allOwnedElements();
            for (Element element : allOwnedElements) {
                if(element instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) element;
                    Event event = mos.getEvent();
                    
                    if( orgEvent == event) {
                        mos.setEvent(copiedEvent);
                    }
                }
            }
        }
    }
    
    /**
     * 
     * applyCopiedEvent
     *  
     * @param copied
     * @param orgEvent
     * @param copiedEvent void
     */
    private void applyCopiedEvent(Event copiedEvent, MessageOccurrenceSpecification copiedMsg, Collection<EObject> copiedEObjs) {
        Operation operation = null;
        if (copiedEvent instanceof SendOperationEvent) {
            operation = ((SendOperationEvent) copiedEvent).getOperation();
        } else if (copiedEvent instanceof ReceiveOperationEvent) {
            operation = ((ReceiveOperationEvent) copiedEvent).getOperation();
        }
        
        if (operation != null) {
            for (EObject eObject : copiedEObjs) {
                if (eObject instanceof Class) {
                    EList<Operation> operations = ((Class) eObject).getOperations();
                    for (Operation copiedOperation : operations) {
                        if (copiedOperation.getName().equals(operation.getName())) {
                            if (copiedEvent instanceof SendOperationEvent) {
                                ((SendOperationEvent) copiedEvent).setOperation(copiedOperation);
                                break;
                            } else if (copiedEvent instanceof ReceiveOperationEvent) {
                                ((ReceiveOperationEvent) copiedEvent).setOperation(copiedOperation);
                                break;
                            }
                        }
                    }
                }
            }

            copiedMsg.setEvent(copiedEvent);
        }
    }

    /**
     * 요소가 다이어그램이면 요소들도 같이 복사를 한다.
     * 
     * @param parent
     *            다이어그램을 붙여 넣을 부모
     * @param copied
     *            붙여넣을 다이어그램
     * @param original
     *            원본 다이어그램
     * @return boolean 다이어그램 붙여 넣기 성공 여부
     */
    private boolean copyDiagram(EObject parent, EObject copied, EObject original, Collection<EObject> copiedEObjs, boolean isCopy) {
        // 존재할 경우 SendOperationEvent, ReceiveOperationEvent copy 하고 fragment 의 event 를 copy 한 것으로 교체
        if(original instanceof Interaction || original instanceof Collaboration) {
            EList<Element> org_allOwnedElements = ((Classifier) original).allOwnedElements();
            EList<Element> copiedElements = ((Classifier) copied).allOwnedElements();
            
            for (int i = 0; i < org_allOwnedElements.size(); i++) {
                Element orgElement = org_allOwnedElements.get(i);
                Element copiedElement = copiedElements.get(i);
                if (orgElement instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification orgMsg = (MessageOccurrenceSpecification) orgElement;
                    MessageOccurrenceSpecification copiedMsg = (MessageOccurrenceSpecification) copiedElement;
                    Event orgEvent = orgMsg.getEvent();
                    Event copiedEvent = EcoreUtil.copy(orgEvent);
                    Package parentPackage = getParentPackage((Package) parent, copied);
                    parentPackage.getPackagedElements().add(copiedEvent);

                    if (!isCopy) { // 참조인 경우에 이벤트 적용
                        applyReferencedEvent(copied, orgEvent, copiedEvent);
                    } else { // 복사인 경우 해당 OP를 찾아서 적용
                        applyCopiedEvent(copiedEvent, copiedMsg, copiedEObjs);
                    }
                }

            }
        }
        
        if (!(original instanceof Diagram))
            return false;
        if (!(parent instanceof Package) && !(parent instanceof Interaction) && !(parent instanceof Collaboration) && !(parent instanceof Activity)) {
            EObject pkg = parent.eContainer();
            while (!(pkg instanceof Package))
                pkg = pkg.eContainer();
            if (!(pkg instanceof Package))
                return false;
            parent = pkg;
        }

        Diagram diagram = (Diagram) copied;
        if (diagram.getType() == DiagramType.ACTIVITY_DIAGRAM) {
            parent = getActivity(parent, (Diagram) original);
        } else if (diagram.getType() == DiagramType.SEQUENCE_DIAGRAM) {
            return false;
//            parent = getInteraction(parent, (Diagram) original);
        }
        if (parent == null)
            return false;

        EAnnotation eAnno = ((Element) parent).getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
        if (eAnno == null)
            eAnno = ((Element) parent).createEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);

        // 똑같은 이름의 다이어그램 명 존재하면 변경
        renameIfExist(eAnno, diagram);

        // 새 ID 부여
        diagram.setId(UUID.randomUUID().toString());

        eAnno.getContents().add(copied);
        diagram.setParent(parent);

        return true;
    }

    /**
     * 시퀀스 다이어그램인 경우 상위에 존재하는 인터랙션을 가져온다.
     * 
     * @param parent
     *            인터랙션을 가지고 있는 상위 요소
     * @param diagram
     *            시퀀스 다이어그램
     * @return 인터랙션 반환
     */
    private EObject getInteraction(EObject parent, Diagram diagram) {
        EObject eObjTemp = diagram.eContainer().eContainer();
        if (!(eObjTemp instanceof Interaction)
            || (!(parent instanceof Package) && !(parent instanceof Collaboration) && !(parent instanceof Interaction)))
            return null;

        Interaction interactionOrg = (Interaction) eObjTemp;
        Interaction interaction = (Interaction) elements.get(interactionOrg);
        if (interaction == null) {
            Collaboration collaborationOrg = (Collaboration) interactionOrg.eContainer();

            Collaboration collaboParent = null;
            if (parent instanceof Collaboration) {
                collaboParent = (Collaboration) parent;
            } else if (parent.eContainer() instanceof Collaboration) {
                collaboParent = (Collaboration) parent.eContainer();
            } else
                collaboParent = (Collaboration) elements.get(collaborationOrg);

            if (collaboParent == null) {
                collaboParent = createCollaboration((Package) parent, collaborationOrg.getName());
            }
            elements.put(collaborationOrg, collaboParent);

            if (parent instanceof Interaction) {
                interaction = (Interaction) parent;
            } else {
                interaction = UMLHelper.createInteraction();
                interaction.setName(interactionOrg.getName());
                renameIfExist(collaboParent, interaction);
            }
            collaboParent.getOwnedBehaviors().add(interaction);
            elements.put(interactionOrg, interaction);
        }

        return interaction;
    }

    /**
     * 새로운 Collaboration을 만들어 parent에 붙인다.
     * 
     * @param parent
     *            새로운 Collaboration이 붙여질 부모 요소
     * @param name
     *            새로운 Collaboration의 이름
     * @return Collaboration 새로 생성된 Collaboration
     */
    private Collaboration createCollaboration(Package parent, String name) {
        Collaboration collaboration = UMLHelper.createCollaboration();
        collaboration.setName(name);
        renameIfExist(parent, collaboration);
        parent.getPackagedElements().add(collaboration);

        return collaboration;
    }

    /**
     * 액티비티 다이어그램인 경우 상위에 존재하는 액티비티를 가져온다.
     * 
     * @param parent
     *            액티비티를 가질 상위 요소
     * @param diagram
     *            액티비티 다이어그램
     * @return 액티비티 반환
     */
    private EObject getActivity(EObject parent, Diagram diagram) {
        if (!(parent instanceof Package) && !(parent instanceof Activity))
            return null;

        EObject eObjTemp = diagram.eContainer().eContainer();
        if (!(eObjTemp instanceof Activity))
            return null;

        Activity activityOrg = (Activity) eObjTemp;
        Activity activity = null;
        eObjTemp = elements.get(activityOrg);
        if (eObjTemp == null) {
            if (parent instanceof Activity) {
                activity = (Activity) parent;
            } else {
                activity = UMLHelper.createActivity();
                activity.setName(activityOrg.getName());
                renameIfExist(parent, activity);
                ((Package) parent).getPackagedElements().add(activity);
            }
            elements.put(activityOrg, activity);

        } else
            activity = (Activity) eObjTemp;

        return activity;
    }

    /**
     * 붙여넣을 요소와 이름이 같은 요소가 같은 부모 요소에 이미 존재한다면 붙여넣을 요소의 이름을 변경한다.
     * 
     * @param parent
     *            부모 요소
     * @param childObj
     *            부모 요소내 존재하는 자식 요소
     * @param eObject
     *            붙여넣는 요소
     */
    private void renameIfExist(EObject parent, EObject eObject) {
        if (eObject instanceof Message
            || ((eObject instanceof InteractionFragment) && !(eObject instanceof Interaction)))
            return;

        for (EObject childObj : parent.eContents()) {
            if (eObject == childObj)
                continue;
            if ((eObject instanceof NamedElement) && (childObj instanceof NamedElement)) {
                NamedElement neEObj = (NamedElement) eObject;
                NamedElement neChldObj = (NamedElement) childObj;
                if ((neEObj.getName() == null) || (neEObj.getName().length() == 0))
                    return;
                if (neEObj.getName().equals(neChldObj.getName()) && eObject.getClass().equals(childObj.getClass())) {
                    neEObj.setName(ProjectUtil.getCopiedUniqueName(neEObj, (Namespace) parent, neEObj.getName()));
                }

            } else if ((eObject instanceof Diagram) && (childObj instanceof Diagram)) {
                // 임시 이름 변경자
                Diagram dEObj = (Diagram) eObject;
                Diagram dChldObj = (Diagram) childObj;

                if (dEObj.getName().equals(dChldObj.getName())) {
                    // 아래 코드는 ProjectUtil.getCopiedUniqueName과 비슷함
                    int index = 1;
                    String newName;
                    EObject[] diagrams = ((EAnnotation) parent).getContents().toArray(new EObject[0]);
                    while (true) {
                        newName = UMLMessage.getMessage(UMLMessage.LABEL_COPYOF) + Integer.toString(index++)
                            + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR + dEObj.getName();
                        int idx = 0;
                        for (; idx < diagrams.length; idx++) {
                            if ((diagrams[idx] instanceof Diagram)
                                && (newName.equals(((Diagram) diagrams[idx]).getName()))) {
                                break;
                            }
                        }
                        if (idx == diagrams.length) {
                            dEObj.setName(newName);
                            break;
                        }
                        if (10000 <= index) {
                            dEObj.setName(dEObj.getName() + Integer.toString(UMLManager.NAME_INDEX++));
                            break;
                        }
                    }
                }
            }
        }
    }
}
