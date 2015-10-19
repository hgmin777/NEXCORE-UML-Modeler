/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.handler.UMLModelerNotationModelHandler;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.manager.utility.UMLModelerNotationModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : ClassDiagramUtil</li>
 * <li>작성일 : 2010. 3. 24.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public class ClassDiagramUtil {

    /**
     * 클래스와 클래스의 관계 생성
     * 
     * @param connections
     * @param seqElmtMap
     *            void
     */
    public static void connectClassToClass(EList connections, HashMap seqElmtMap) {

        AbstractConnection connection = null;
        Lifeline srcElmt = null;
        Lifeline tgtElmt = null;
        Dependency depend = null;
        String firstElementName = null;
        String secondElementName = null;
        NotationNode srcNode = null;
        NotationNode tgtNode = null;

        for (Iterator iterator = connections.iterator(); iterator.hasNext();) {
            connection = (AbstractConnection) iterator.next();

            if (connection.getSource().getUmlModel() instanceof Lifeline) {
                srcElmt = (Lifeline) connection.getSource().getUmlModel();
                // 액터와의 연결은 제거
                if (srcElmt.getRepresents() == null || srcElmt.getRepresents().getType() instanceof Actor) {
                    srcElmt = null;
                }
            } else {
                srcElmt = null;
            }

            if (connection.getTarget().getUmlModel() instanceof Lifeline) {
                tgtElmt = (Lifeline) connection.getTarget().getUmlModel();
            } else {
                tgtElmt = null;
            }

            if (srcElmt != null && tgtElmt != null && srcElmt.getRepresents() != null
                && tgtElmt.getRepresents() != null && srcElmt.getRepresents().getType() != null
                && tgtElmt.getRepresents().getType() != null) {

                firstElementName = srcElmt.getRepresents().getType().getName() + ManagerConstant.UNDER_BAR// "_"
                    + tgtElmt.getRepresents().getType().getName();
                secondElementName = tgtElmt.getRepresents().getType().getName() + ManagerConstant.UNDER_BAR
                    + srcElmt.getRepresents().getType().getName(); // 시퀀스
                                                                   // 다이어그램에서
                                                                   // 역방향 라인

                // Edge 중복생성 방지
                if (!seqElmtMap.containsKey(firstElementName.toLowerCase())
                    && !seqElmtMap.containsKey(secondElementName.toLowerCase())) {

                    try {

                        depend = ClassDiagramUtil.getDependency(srcElmt.getRepresents().getType(),
                            tgtElmt.getRepresents().getType());
                        srcNode = (NotationNode) seqElmtMap.get(srcElmt.getRepresents()
                            .getType()
                            .getName()
                            .toLowerCase());
                        tgtNode = (NotationNode) seqElmtMap.get(tgtElmt.getRepresents()
                            .getType()
                            .getName()
                            .toLowerCase());

                        if (srcNode != null && tgtNode != null && srcNode != tgtNode) {
                            // TODO:
                            // UMLModeler.getUMLDiagramHelper().createEdge() 개발
                            // UMLModeler.getUMLDiagramHelper().createEdge(srcNode,
                            // tgtNode, depend);
                            seqElmtMap.put(firstElementName.toLowerCase(), null);
                        }

                    } catch (Exception e) {
                        // Log.error("# " + seqDgm.getName() +
                        // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC1")
                        // + srcElmt.getName() +
                        // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC2")
                        // + tgtElmt.getName() +
                        // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC3"));
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 
     * 
     * @param seqDgm
     * @param controlClassMap
     * @param voClassMap
     * @param seqElmtMap
     *            void
     */
    public static void connectControlToVO(Diagram seqDgm, HashMap controlClassMap, HashMap voClassMap,
                                          HashMap seqElmtMap) {

        org.eclipse.uml2.uml.Class voClass = null;
        Dependency depend = null;
        NotationNode srcNode = null;
        NotationNode tgtNode = null;

        Set controlKeyset = controlClassMap.keySet();
        Set voKeyset = voClassMap.keySet();
        String controlKey = null;
        String voKey = null;
        org.eclipse.uml2.uml.Class controlClass = null;

        for (Iterator itr = controlKeyset.iterator(); itr.hasNext();) {
            controlKey = (String) itr.next();
            controlClass = (org.eclipse.uml2.uml.Class) controlClassMap.get(controlKey);

            for (Iterator itr2 = voKeyset.iterator(); itr2.hasNext();) {
                voKey = (String) itr2.next();
                voClass = (org.eclipse.uml2.uml.Class) voClassMap.get(voKey);

                try {
                    depend = ClassDiagramUtil.getDependency(controlClass, voClass);
                    srcNode = (NotationNode) seqElmtMap.get(controlClass.getName().toLowerCase());
                    tgtNode = (NotationNode) seqElmtMap.get(voClass.getName().toLowerCase());

                    if (srcNode != null && tgtNode != null && srcNode != tgtNode) {
                        // TODO: UMLModeler.getUMLDiagramHelper().createEdge()
                        // 개발
                        // UMLModeler.getUMLDiagramHelper().createEdge(srcNode,
                        // tgtNode, depend);
                    }

                } catch (Exception e) {
                    // Log.error("# " + seqDgm.getName() +
                    // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC1")
                    // + srcElmt.getName()+
                    // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC2")
                    // + tgtElmt.getName() +
                    // Messages.getString("CreateClassDiagram_LOG_MSG_ERROR_CREATEASSOC3"));
                    continue;
                }
            }
        }
    }

    /**
     * 현재 클래스나 인터페이스에 존재하는 Dependency 를 검색 후 존재하면 바로 반환하고 없을 경우 새로 생성해서 반환
     * 
     * @param srcType
     * @param tgtType
     * @return Dependency
     */
    public static Dependency getDependency(Type srcType, Type tgtType) {

        EList dependList = srcType.getClientDependencies();
        EList tgtList = null;
        Dependency tgtDepend = null;
        NamedElement nmElmt = null;

        for (Iterator itr = dependList.iterator(); itr.hasNext();) {
            tgtDepend = (Dependency) itr.next();
            tgtList = tgtDepend.getTargets();

            if (tgtList != null) {
                for (Iterator itr2 = tgtList.iterator(); itr2.hasNext();) {
                    nmElmt = (NamedElement) itr2.next();
                    if (nmElmt != null && nmElmt.getQualifiedName() != null
                        && nmElmt.getQualifiedName().equals(tgtType.getQualifiedName())) {
                        return tgtDepend;
                    }
                }
            }
        }

        return srcType.createDependency(tgtType);
    }

    /**
     * 클래스간 관계 생성
     * 
     * @param type1
     * @param end1IsNavigable
     * @param end1Aggregation
     * @param end1Name
     * @param end1LowerBound
     * @param end1UpperBound
     * @param type2
     * @param end2IsNavigable
     * @param end2Aggregation
     * @param end2Name
     * @param end2LowerBound
     * @param end2UpperBound
     * @return Association
     */
    public static Association createAssociation(Type type1, boolean end1IsNavigable, AggregationKind end1Aggregation,
                                                String end1Name, int end1LowerBound, int end1UpperBound, Type type2,
                                                boolean end2IsNavigable, AggregationKind end2Aggregation,
                                                String end2Name, int end2LowerBound, int end2UpperBound) {

        Association association = type1.createAssociation(end1IsNavigable,
            end1Aggregation,
            end1Name,
            end1LowerBound,
            end1UpperBound,
            type2,
            end2IsNavigable,
            end2Aggregation,
            end2Name,
            end2LowerBound,
            end2UpperBound);
        return association;
    }

    /**
     * 클래스 다이어그램 존재여부 확인
     * 
     * @param collabo
     * @return boolean
     */
    public static boolean isAlreadyExistClassDiagram(Collaboration collabo) {
        boolean result = false;
        org.eclipse.uml2.uml.Package chkComponentPackage = collabo.getNearestPackage();

        List diagramList = UMLModelerNotationModelHandlerUtil.getDiagramList(collabo, DiagramType.CLASS_DIAGRAM);

        if (diagramList != null) {
            Diagram dgm = null;
            Object obj = null;
            for (Iterator iter = diagramList.iterator(); iter.hasNext();) {
                obj = iter.next();
                if (obj instanceof Diagram) {
                    dgm = (Diagram) obj;

                    // if
                    // (dgm.getName().equals(ModelPropertyManager.getInstance().getClassDiagramName()))
                    // {
                    if (dgm.getName().equals(UMLMessage.LABEL_VOPC_DIAGRAM_NAME)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 클래스 다이어그램 생성
     * 
     * @param collabo
     * @return Diagram
     */
    public static Diagram addClassDiagram(Collaboration collabo) {
        INotationModelHandler notationModelHandler = UMLModelerNotationModelHandlerUtil.getHandlerInstance();

        // Diagram diagram = UMLMDAUtil.createDiagram(collabo,
        // ModelPropertyManager.getInstance().getClassDiagramName(),
        // DiagramType.CLASS_DIAGRAM);
        Diagram diagram = ((UMLModelerNotationModelHandler) notationModelHandler).createDiagram(collabo,
            UMLMessage.LABEL_VOPC_DIAGRAM_NAME,
            DiagramType.CLASS_DIAGRAM);

        return diagram;
    }

    /**
     * 클래스 다이어그램 삭제
     * 
     * @param collabo
     *            void
     */
    public static void deleteExistingClassDiagram(Collaboration collabo) {
        org.eclipse.uml2.uml.Package chkComponentPackage = collabo.getNearestPackage();

        List diagramList = UMLModelerNotationModelHandlerUtil.getInstance().getDiagramList(collabo,
            DiagramType.CLASS_DIAGRAM);

        Diagram classDiagram = null;
        if (diagramList != null) {
            Diagram dgm = null;
            Object obj = null;
            for (Iterator iter = diagramList.iterator(); iter.hasNext();) {
                obj = iter.next();
                if (obj instanceof Diagram) {
                    dgm = (Diagram) obj;

                    // if
                    // (dgm.getName().equals(ModelPropertyManager.getInstance().getClassDiagramName()))
                    // {
                    if (dgm.getName().equals(UMLMessage.LABEL_VOPC_DIAGRAM_NAME)) {
                        classDiagram = dgm;
                        break;
                    }
                }
            }
        }

        if (classDiagram != null) {
            // TODO : UMLModeler.getUMLDiagramHelper().destroyView() 개발 필요
            // UMLModeler.getUMLDiagramHelper().destroyView(classDgm);
        }
    }
    
    public static void setModelInfo(NotationNode notationNode) {
        final NotationNode viewModel = notationNode;

        Object object = viewModel.getUmlModel();
        if (!(object instanceof NamedElement)) {
            return;
        }
        NamedElement namedElement = (NamedElement) object;

        List<Object> listStereotypes = new ArrayList<Object>();
        EList<String> keywords = namedElement.getKeywords();
        if (0 < keywords.size()) {
            listStereotypes.add(keywords); // Element Keywords
        }
        EList<Stereotype> stereotypes = namedElement.getAppliedStereotypes();
        if (0 < stereotypes.size()) {
            listStereotypes.add(stereotypes); // element Stereotypes
        }

        if (namedElement instanceof Actor) {
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            } else {
                removeLabelViewAndEditPart(viewModel, NodeType.STEREOTYPE);
            }
        } else {
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            } else {
                removeLabelViewAndEditPart(viewModel, NodeType.STEREOTYPE);
            }
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
        }
        boolean showOperation = false;
//        if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
//            .equals("true")) {
//            showOperation = true;
//        }
        if (showOperation) {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }
        } else {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                removeLabelViewAndEditPart(viewModel, NodeType.OPERATIONS);
                removeLabelViewAndEditPart(viewModel, NodeType.ATTRIBUTES);
            }
        }
        if (!(namedElement instanceof org.eclipse.uml2.uml.Component)) {
            if (namedElement instanceof org.eclipse.uml2.uml.Class) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
            }
            if (namedElement instanceof org.eclipse.uml2.uml.Class
                || namedElement instanceof org.eclipse.uml2.uml.Interface) {
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }

        }

        if (namedElement instanceof org.eclipse.uml2.uml.Component) {
            if (hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.PROVIDED_INTERFACES, namedElement);
            if (hasNodeType(NodeType.REQUIRED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.REQUIRED_INTERFACES, namedElement);
        }

        if (namedElement instanceof org.eclipse.uml2.uml.Enumeration) {
            if (hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                addCompartmentList(viewModel, NodeType.ENUMERATION_LITERALS, namedElement);
            }
        }

        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                int childrenHeight = 0;
                NotationNode childNode = null;
                for (AbstractNode child : viewModel.getCompartmentList()) {
                    if (child.getNodeType().equals(NodeType.STEREOTYPE)) {
                        childrenHeight -= 8;
                    }
                    if (child.getNodeType().equals(NodeType.NAME)) {
                        childrenHeight += 8;
                    }
                    childrenHeight += child.getHeight();
                    childNode = (NotationNode) child;
                }
                int parentHeight = 0;
                if (viewModel.getHeight() > childrenHeight) {
                    parentHeight = viewModel.getHeight();
                    if (childNode != null) {
                        if (childNode.getHeight() != childNode.getHeight() + parentHeight - childrenHeight) {
                            childNode.setHeight(childNode.getHeight() + parentHeight - childrenHeight);
                        }
                    }
                } else {
                    parentHeight = childrenHeight;
                }
                if (viewModel.getHeight() != parentHeight) {
                    viewModel.setHeight(parentHeight);
                }
            }
        });
    }

    /**
     * addCompartmentList
     *  
     * @param viewModel
     * @param nodeType
     * @param namedElement void
     */
    private static void addCompartmentList(final NotationNode viewModel, final NodeType nodeType,
                                           final NamedElement namedElement) {
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                boolean showOperation = false;
//                if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
//                    .equals("true")) {
//                    showOperation = true;
//                }

                NotationNode childViewModel = null;
                if (nodeType.equals(NodeType.NAME)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.NAME);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(15);

                } else if (nodeType.equals(NodeType.STEREOTYPE)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.STEREOTYPE);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(21);

                } else if (nodeType.equals(NodeType.ATTRIBUTES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.ATTRIBUTES);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);

                } else if (nodeType.equals(NodeType.OPERATIONS)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.OPERATIONS);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);

                } else if (nodeType.equals(NodeType.PROVIDED_INTERFACES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.PROVIDED_INTERFACES);
                    childViewModel.setParent(viewModel);
                    if (showOperation) {
                        childViewModel.setHeight(50);
                    } else {
                        childViewModel.setHeight(90);
                    }

                } else if (nodeType.equals(NodeType.REQUIRED_INTERFACES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.REQUIRED_INTERFACES);
                    childViewModel.setParent(viewModel);
                    if (showOperation) {
                        childViewModel.setHeight(50);
                    } else {
                        childViewModel.setHeight(90);
                    }

                } else if (nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.ENUMERATION_LITERALS);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);
                }

                childViewModel.setUmlModel(namedElement);

                if (childViewModel.getNodeType().equals(NodeType.STEREOTYPE)) {
                    viewModel.getCompartmentList().add(0, childViewModel);
                    if (viewModel.getNodeType().equals(NodeType.ACTOR)) {}
                    viewModel.setHeight(viewModel.getHeight() + 15);

                } else if (childViewModel.getNodeType().equals(NodeType.NAME)) {
                    if (hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        viewModel.getCompartmentList().add(0, childViewModel);
                    } else {
                        viewModel.getCompartmentList().add(1, childViewModel);
                    }
                } else if (nodeType.equals(NodeType.ATTRIBUTES) || nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
                    int cnt = 0;
                    if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.NAME, viewModel)) {
                        cnt++;
                    }
                    if (hasNodeType(NodeType.ATTRIBUTES, viewModel)
                        || hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                        viewModel.getCompartmentList().add(cnt, childViewModel);
                    }
                    if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                        if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                            viewModel.setHeight(viewModel.getHeight() + 40);
                        }
                    }
                } else if (nodeType.equals(NodeType.OPERATIONS)) {
                    int cnt = 0;
                    if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.NAME, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.ATTRIBUTES, viewModel)
                        || !hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                        cnt++;
                    }
                    if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                        viewModel.getCompartmentList().add(cnt, childViewModel);
                    }
                    if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                        if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                            viewModel.setHeight(viewModel.getHeight() + 40);
                        }
                    }
                } else {
                    viewModel.getCompartmentList().add(childViewModel);
                }
            }
        });
    }

    /**
     * 
     * 라벨의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    private static boolean hasNodeType(NodeType nodeType, NotationNode notationNode) {
        NotationNode node = notationNode;
        for (AbstractNode child : node.getCompartmentList()) {
            if (child.getNodeType().equals(nodeType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * 값이 없는 경우 뷰모델과 에디트파트를 지워준다
     * 
     * @param node
     * @param labelType
     *            void
     */
    public static void removeLabelViewAndEditPart(final NotationNode node, NodeType nodeType) {

        NotationNode removeTarget = null;
        for (AbstractNode label : node.getCompartmentList()) {
            if (label.getNodeType().equals(nodeType)) {
                removeTarget = (NotationNode) label;
                break;
            }
        }
        final NotationNode removeElement;
        if (removeTarget != null) {
            removeElement = removeTarget;
            if (node.getCompartmentList().contains(removeElement)) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {

//                        IEditorReference[] editors = UiCorePlugin.getActiveWorkbench()
//                            .getActiveWorkbenchWindow()
//                            .getActivePage()
//                            .getEditorReferences();
//                        for (IEditorReference editorPart : editors) {
//                            if (editorPart instanceof AbstractDiagramEditor
//                                && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram()
//                                    .getType())) {
//                                AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
//                                GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
//                                ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
//                                List<EditPart> diagramEditParts = root.getChildren();
//
//                                EditPart targetEditPart = findTargetEditPart(diagramEditParts);
//                            }
//                        }
                        node.setHeight(node.getHeight() - removeElement.getHeight());
                        node.getCompartmentList().remove(removeElement);
                    }

                    private boolean removeEditPart(EditPart targetEditPart, List<EditPart> editParts) {
                        EditPart removeChild = null;
                        for (EditPart child : editParts) {
                            if (child.equals(targetEditPart)) {
                                removeChild = child;
                            }
                            removeEditPart(targetEditPart, child.getChildren());
                        }
                        if (removeChild != null) {
                            if (editParts.contains(removeChild)) {
                                editParts.remove(removeChild);
                                return true;
                            }
                        }
                        return false;
                    }

                    private EditPart findTargetEditPart(List<EditPart> editParts) {

                        EditPart removeChild = null;

                        for (EditPart child : editParts) {
                            if (child.getModel().equals(removeElement)) {
                                removeChild = child;
                                return child;
                            } else {
                                removeChild = findTargetEditPart(child.getChildren());
                                if (removeChild != null)
                                    return removeChild;
                            }
                        }
                        return removeChild;
                    }
                });
            }
        }
    }
    
    /**
     * 다이어그램의 레이아웃을 구성하는 메소드
     * 
     * @param classDiagram
     *            void
     */
    public static void composeDiagramLayout(Diagram classDiagram) {
        int x = 10;
        int y = 10;
        AbstractNode classNode = null;

        for (int i = 0; i < classDiagram.getNodeList().size(); i++) {
            classNode = classDiagram.getNodeList().get(i);
            classNode.setX(x);
            classNode.setY(y);

            x += 300;
            if ((i + 1) % 3 == 0) {
                y += 200;// 150;
                x = 10;
            }
        }
    }
    
    /**
     * 클래스 다이어그램 요소 목록으로부터 클래스 다이어그램에 사용할 노드 목록을 생성하는 메소드
     * 
     * @param classDiagramElementList
     * @param classDiagram
     *            void
     */
    public static void createNodeListOfClassDiagram(List<Element> classDiagramElementList, Diagram classDiagram) {
        NamedElement namedElement = null;
        Class classElement = null;
        Interface interfaceElement = null;
        int ownedAttributeCnt, ownedOperationCnt, ownedElementCnt = 0;
        EList<Property> childProperty = null;
        EList<Operation> childOperation = null;
        String elementName = ManagerConstant.BLANK_STRING;
        int maxNameLength = 0;
        NotationNode node = null;

        // 클래스의 경우 소유한 어트리뷰트와 오퍼레이션의 갯수를 확인하여 클래스 노드를 그릴 시 크기에 반영한다.
        for (int i = 0; i < classDiagramElementList.size(); i++) {
            if (classDiagramElementList.get(i) instanceof NamedElement) {
                namedElement = (NamedElement) classDiagramElementList.get(i);
            }

            // 요소가 클래스인 경우, 클래스 요소로 설정
            if (classDiagramElementList.get(i) instanceof org.eclipse.uml2.uml.Class) {
                classElement = (Class) classDiagramElementList.get(i);
            
            // 요소가 인터페이스인 경우, 인터페이스 요소로 설정
            } else if (classDiagramElementList.get(i) instanceof Interface) {
                interfaceElement = (Interface) classDiagramElementList.get(i);
            }

            // 요소가 Class인 경우, Attribute갯수와 Operation갯수의 합계만큼 노드의 높이를 설정
            if (classElement != null) {
                childProperty = classElement.getOwnedAttributes();
                childOperation = classElement.getOwnedOperations();

                ownedAttributeCnt = childProperty.size();
                ownedOperationCnt = childOperation.size();

                if (ownedAttributeCnt == 0 && ownedOperationCnt != 0) {
                    ownedElementCnt = ownedOperationCnt;
                } else if (ownedOperationCnt == 0 && ownedAttributeCnt != 0) {
                    ownedElementCnt = ownedAttributeCnt;
                } else {
                    ownedElementCnt = ownedAttributeCnt + ownedOperationCnt;

                    if (ownedElementCnt != 0) {
                        ownedElementCnt -= 3;
                    }
                }
                
            // 요소가 Interface인 경우, Attribute갯수와 Operation갯수의 합계만큼 노드의 높이를 설정
            } else if (interfaceElement != null) {
                childProperty = interfaceElement.getOwnedAttributes();
                childOperation = interfaceElement.getOwnedOperations();

                ownedAttributeCnt = childProperty.size();
                ownedOperationCnt = childOperation.size();

                if (ownedAttributeCnt == 0 && ownedOperationCnt != 0) {
                    ownedElementCnt = ownedOperationCnt;
                } else if (ownedOperationCnt == 0 && ownedAttributeCnt != 0) {
                    ownedElementCnt = ownedAttributeCnt;
                } else {
                    ownedElementCnt = ownedAttributeCnt + ownedOperationCnt;

                    if (ownedElementCnt != 0) {
                        ownedElementCnt -= 3;
                    }
                }
            }

            // 요소가 소유한 Attribute명을 조사하여,
            // 클래스 노드의 기본 가로길이 160보다 길다면 가로 사이즈를 추가해 준다.
            for (Property property : childProperty) {
                elementName = property.getName();

                if (elementName.length() > maxNameLength) {
                    maxNameLength = elementName.length();
                }
            }
            // 요소가 소유한 Operation명을 조사하여,
            // 클래스 노드의 기본 가로길이 160보다 길다면 가로 사이즈를 추가해 준다.
            for (Operation operation : childOperation) {
                elementName = operation.getName();

                if (elementName.length() > maxNameLength) {
                    maxNameLength = elementName.length();
                }
            }
            if (maxNameLength != 0) {
                maxNameLength = maxNameLength - 10;
            }

            // 클래스 노드를 생성
            node = UMLDiagramFactory.eINSTANCE.createNotationNode();
            node.setUmlModel((Element) classDiagramElementList.get(i));
            node.setName(namedElement.getName());
            
            if (classDiagramElementList.get(i) instanceof Class) {
                node.setNodeType(NodeType.CLASS);
            } else if (classDiagramElementList.get(i) instanceof Interface) {
                node.setNodeType(NodeType.INTERFACE);
            }

            // 노드의 가로 길이 설정
            if (namedElement != null) {
                if (getWidthSize(namedElement.getName(), "sampleFont", 10, SWT.ITALIC) + 50 > 160 + (maxNameLength * 5)) { //$NON-NLS-1$
                    node.setWidth(getWidthSize(namedElement.getName(), "sampleFont", 10, SWT.ITALIC) + 50); //$NON-NLS-1$
                } else {
                    node.setWidth(160 + (maxNameLength * 5));
                }
            }
            // 노드의 세로 길이 설정
            node.setHeight(80 + (ownedElementCnt * 20));

            //2012-08-17
            //VOPC 다이어그램 생성시 compartment 정보 생성
            ClassDiagramUtil.setModelInfo((NotationNode) node);

            // 클래스 다이어그램에 노드로 추가
            classDiagram.getNodeList().add(node);
        }
    }
    
    /**
     * 가로 크기 가져오는 메소드
     * 
     * @param label
     * @param fontName
     * @param fontSize
     * @param fontStyle
     * @return int
     */
    private static int getWidthSize(String label, String fontName, int fontSize, int fontStyle) {
        int width = 0;
        Shell shell = new Shell();
        GC gc = new GC(shell);
        Font font = new Font(null, new FontData(fontName, fontSize, fontStyle));
        gc.setFont(font);
        width = gc.textExtent(label).x;
        font.dispose();
        gc.dispose();
        shell.dispose();
        return width;
    }
    
}
