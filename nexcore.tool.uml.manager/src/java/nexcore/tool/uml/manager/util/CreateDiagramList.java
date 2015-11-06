/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.manager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.UMLModelerNotationModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.DiagramType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : CreateDiagramList</li>
 * <li>작성일 : 2008. 06. 12</li>
 * <li>작성자 : 81228 </li>
 * </ul>
 */
public class CreateDiagramList {

    /** 컬레보레이션 목록 */
    private static List<Element> collaborationList = new ArrayList<Element>();

    /** 진행 모니터 */
    private IProgressMonitor monitor;

    /**
     * 생성자
     * 
     * @param monitor
     */
    public CreateDiagramList(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * 선택된 모델(분석 및 설계 모델)의 시퀀스 다이어그램으로부터 클래스 다이어그램 생성
     * 
     * @param elements
     * @return List
     */
    public List createDesignClassDiagrams(List elements) {
        List result = new ArrayList();

        if (elements == null || elements.size() < 1) {
            return result;
        }

        if (elements.get(0) instanceof Model) {
            Model model = (Model) elements.get(0);

            List classPkgList = model.getOwnedMembers();

            // CreatePackage cp = new CreatePackage();
            Package pkg = null;
            Collaboration collabo = null;
            Object obj = null;

            for (Iterator itr = ModelUtility.snapshotIterator(classPkgList); itr.hasNext();) {
                obj = itr.next();

                if (obj != null && obj instanceof Package) {
                    pkg = (Package) obj;
                    result = recursiveCreate(pkg, result);
                } else if (obj != null && obj instanceof Collaboration) {
                    collabo = (Collaboration) obj;
                    result = CreateClassDiagram.createClassDiagram(collabo, result, collaborationList);
                }
            }
        }

        return result;
    }

    /**
     * 컬레보레이션을 포함하는 패키지까지 재귀적으로 호출해서 클래스 다이어그램 생성
     * 
     * @param pkg
     * @param result
     * @return List
     */
    private List recursiveCreate(Package pkg, List result) {
        if (pkg == null) {
            return result;
        }

        EList ownElmtList = pkg.getOwnedMembers();

        Object object = null;
        Package subPkg = null;
        Collaboration collabo = null;

        for (Iterator iter = ModelUtility.snapshotIterator(ownElmtList); iter.hasNext();) {
            object = iter.next();

            if (object != null && object instanceof Collaboration) {
                collabo = (Collaboration) object;
                result = CreateClassDiagram.createClassDiagram(collabo, result, collaborationList);
            } else if (object != null && object instanceof Package) {
                subPkg = (Package) object;
                result = recursiveCreate(subPkg, result);
            }
        }

        return result;
    }

    /**
     * 선택된 모델(분석 및 설계 모델)의 시퀀스 다이어그램으로부터 클래스 다이어그램 생성
     * 
     * @param elements
     * @return List<String>
     */
    public List<String> createVOPCs(List<EObject> elements) {
        List<String> result = new ArrayList<String>();

        Element element = (Element) elements.get(0);

        if (element != null
            && (element instanceof Package || element instanceof Model || element instanceof Component || element instanceof Collaboration)) {
            /**
             * Object object = null; Collaboration collabo = null; Package pkg =
             * null; Component comp = null;
             * 
             * List<Element> ownElmtList = nmElmt.getOwnedElements();
             */
            try {
                createVOPC(element, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /**
             * EList ownElmtList = nmElmt.allOwnedElements();
             * 
             * for(Iterator iter = ModelUtility.snapshotIterator(ownElmtList);
             * iter.hasNext();) { try { object = iter.next();
             * 
             * // Collaboration이 발견되면 Collaboration을 하위를 탐색해서, // 시퀀스를 검색한 후.
             * 클래스를 추출하고, // 클래스다이어그램을 생성한다. if(object != null && object
             * instanceof Collaboration) { collabo = (Collaboration) object;
             * 
             * result = CreateClassDiagram.createClassDiagram(collabo, result,
             * collaborationList); } else if(object != null && object instanceof
             * Package) { pkg = (Package) object;
             * 
             * result = recursiveVOPC(pkg, result); } else if(object != null &&
             * object instanceof Component) { comp = (Component) object;
             * 
             * result = recursiveVOPC(pkg, result); } }
             * catch(ConcurrentModificationException e) { continue; } } } else
             * if(elements != null && (elements.get(0) instanceof
             * Collaboration)) { Collaboration collabo =
             * (Collaboration)elements.get(0);
             * 
             * result = CreateClassDiagram.createClassDiagram(collabo, result,
             * collaborationList);
             */
        }

        collaborationList.clear();

        return result;
    }

    /**
     * VOPC 생성
     * 
     * @param vopcElement
     * @param result
     * @return List<String>
     */
    private List<String> createVOPC(Element vopcElement, List<String> result) {
        if (vopcElement != null && vopcElement instanceof Collaboration) {
            monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_VOPC_DIAGRAM_CREATION)
                + ((NamedElement) vopcElement).getName());

            Collaboration collabo = (Collaboration) vopcElement;

            result = CreateClassDiagram.createClassDiagram(collabo, result, collaborationList);

            monitor.worked(1);
        } else if (vopcElement != null && (vopcElement instanceof Package || vopcElement instanceof Component)) {
            EList<Element> elements = vopcElement.getOwnedElements();
            Collaboration collabo = null;

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i) instanceof Collaboration) {
                    collabo = (Collaboration) elements.get(i);

                    monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_VOPC_DIAGRAM_CREATION)
                        + ((NamedElement) collabo).getName());

                    result = CreateClassDiagram.createClassDiagram(collabo, result, collaborationList);

                    monitor.worked(1);
                } else {
                    createVOPC(elements.get(i), result);
                }
            }
        }

        return result;
    }

    /**
     * 컬레보레이션을 포함하는 패키지까지 재귀적으로 호출해서 VOPC 생성
     * 
     * @param obj
     * @param result
     * @return List
     */
    @SuppressWarnings("unused")
    private List recursiveVOPC(Object obj, List result) {
        if (obj == null) {
            return result;
        }

        EList ownElmtList = null;

        if (obj instanceof Package) {
            Package pkg = (Package) obj;

            ownElmtList = pkg.getOwnedMembers();
        } else if (obj instanceof Component) {
            Component comp = (Component) obj;

            ownElmtList = comp.getOwnedMembers();
        }

        Object object = null;
        Package subPkg = null;
        Collaboration collabo = null;

        for (Iterator iter = ModelUtility.snapshotIterator(ownElmtList); iter.hasNext();) {
            object = iter.next();

            if (object != null && object instanceof Collaboration) {
                collabo = (Collaboration) object;

                result = CreateClassDiagram.createClassDiagram(collabo, result, collaborationList);
            } else if (object != null && object instanceof Package) {
                subPkg = (Package) object;

                result = recursiveCreate(subPkg, result);
            }
        }

        return result;
    }

    /**
     * 분석 시퀀스 다이어그램을 읽어서 설계 시퀀스 다이어그램 생성
     * 
     * @param elements
     * @param designModel
     * @return List
     */
    /*
     * public List createSequenceDiagramList(List elements, Model designModel) {
     * HashMap designTypeMap = new HashMap<String, Collaboration>(); HashMap
     * designInteractionMap = new HashMap();
     * 
     * List result = new ArrayList();
     * 
     * // 에러 처리 if(elements == null || elements.size() < 1) { //
     * Messages.getString
     * ("ReadFromSequenceDiagram_DIALOG_MSG_SELECTANALYSISMODEL")); //
     * MDAMessage.ReadFromSequenceDiagram_DIALOG_MSG_SELECTANALYSISMODEL);
     * MessageDialog.openInformation(null, UMLMessage.LABEL_NOTIFICATION,
     * UMLMessage.MESSAGE_SELECT_ANALYSISMODEL); } else { Object object =
     * elements.get(0); CreateSequenceDiagram crtSeqDgm = null; Interaction
     * intact = null; Object obj = null;
     * 
     * // 1. 분석모델과 설계모델을 동기화 처리한다. DuplicateAnalysisStructure duplAmStructure =
     * new DuplicateAnalysisStructure(); // TODO 동기화 처리 검사 by zerotae //
     * duplAmStructure.duplicateStructure(designModel, elements);
     * 
     * // 2. 설계모델의 Type과 Interaction 을 리스트업 한다.
     * (designInteractionMap.put('Interaction명','Collaboration객체')) // 나중에는 다시
     * Collaboration 에서 Interaction 을 추출해서 사용한다. extractModelInfo(designModel,
     * designTypeMap, designInteractionMap);
     * 
     * // 3. 분석 모델의 Interaction 들을 읽어서 설계 모델의 시퀀스 다이어그램을 생성한다.
     * if(designInteractionMap.size() > 0) { EList anlElmt =
     * ((NamedElement)object).allOwnedElements();
     * 
     * if(anlElmt != null) { crtSeqDgm = new
     * CreateSequenceDiagram(designInteractionMap, designTypeMap);
     * 
     * for(Iterator itr = anlElmt.iterator(); itr.hasNext();) { obj =
     * itr.next();
     * 
     * if(obj instanceof Interaction) { intact = (Interaction) obj;
     * 
     * // crtSeqDgm = new CreateSequenceDiagram(designInteractionMap,
     * designTypeMap); crtSeqDgm.createSequenceDiagram(intact); } } } } }
     * 
     * return result; }
     */

    /**
     * 설계모델의 타입과 인터랙션 정보를 추출
     * 
     * @param designModel
     * @param designTypeMap
     * @param designInteractionMap
     *            void
     */
    private void extractModelInfo(Model designModel, HashMap designTypeMap, HashMap designInteractionMap) {
        Collaboration collabo;
        Interaction intact;
        Class tempClass;
        Interface tempInterface;
        Object obj;
        Object obj2;
        EList dgnElmt = designModel.allOwnedElements();
        EList collobElmt = null;
        String keyName = null;

        if (dgnElmt != null) {
            for (Iterator itr = dgnElmt.iterator(); itr.hasNext();) {
                obj = itr.next();

                if (obj instanceof Collaboration) {
                    collabo = (Collaboration) obj;
                    collobElmt = collabo.getOwnedElements();

                    for (Iterator itr2 = collobElmt.iterator(); itr2.hasNext();) {
                        obj2 = itr2.next();

                        if (obj2 instanceof Interaction) {
                            intact = (Interaction) obj2;

                            keyName = ModelUtility.getHierarchyName(intact);
                            if (!designInteractionMap.containsKey(keyName)) {
                                designInteractionMap.put(keyName, collabo);
                            }
                        }
                    }
                } else if (obj instanceof Class) {
                    tempClass = (Class) obj;

                    if (!designTypeMap.containsKey(tempClass.getName())) {
                        designTypeMap.put(tempClass.getName(), tempClass);
                    }
                } else if (obj instanceof Interface) {
                    tempInterface = (Interface) obj;

                    if (!designTypeMap.containsKey(tempInterface.getName())) {
                        designTypeMap.put(tempInterface.getName(), tempInterface);
                    }
                }
            }
        }
    }

    /**
     * 컬레보레이션으로부터 시퀀스 다이어그램 목록 가져오기
     * 
     * @param sequenceList
     * @param collabo
     * @return List
     */
    public List getSequenceDiagramsFromCollaboration(List sequenceList, Collaboration collabo) {
        if (collabo != null && sequenceList != null) {
            List interactList = collabo.getOwnedElements();
            Interaction interaction = null;

            for (Iterator interactionItr = interactList.iterator(); interactionItr.hasNext();) {
                Object interactionObj = (Object) interactionItr.next();

                // Interaction Instance이면 수행
                if (interactionObj != null && interactionObj instanceof Interaction) {
                    interaction = (Interaction) interactionObj;

                    // Interaction Instance 내의 Sequence Diagram을 가져온다.
                    List diagramList = UMLModelerNotationModelHandlerUtil.getInstance().getDiagramList(interaction,
                        DiagramType.SEQUENCE_DIAGRAM);
                    // List에 추가한다.
                    sequenceList.addAll(diagramList);
                }
            }
        }

        return sequenceList;
    }

}
