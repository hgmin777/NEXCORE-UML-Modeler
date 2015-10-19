/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.diagram.command.DropNotationCommand;
import nexcore.tool.uml.ui.core.diagram.command.RelationAutoGenerationCommand;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.project.ITreeNode;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor</li>
 * <li>설 명 : DiagramDropTargetListener</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramDropTargetListener implements TransferDropTargetListener {

    /** diagramEditor */
    protected AbstractDiagramEditor diagramEditor;

    /** event & data transfer */
    protected LocalSelectionTransfer transfer;

    /** view model list in diagram */
    private List<AbstractNode> viewModelListInDiagram;

    /**
     * DiagramDropTargetListener
     * @param diagramEditor
     */
    public DiagramDropTargetListener(AbstractDiagramEditor diagramEditor) {
        this.diagramEditor = diagramEditor;
        this.viewModelListInDiagram = new ArrayList<AbstractNode>();
    }

    /**
     * @see org.eclipse.jface.util.TransferDropTargetListener#getTransfer()
     */
    public Transfer getTransfer() {
        transfer = LocalSelectionTransfer.getTransfer();
        return transfer;
    }

    /**
     * @see org.eclipse.jface.util.TransferDropTargetListener#isEnabled(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public boolean isEnabled(DropTargetEvent event) {
        ISelection transferObject = (TreeSelection) this.transfer.getSelection();
        if (!(transferObject instanceof TreeSelection)) {
            return false;
        }
        return checkTreeSectionSource((TreeSelection) transferObject);
    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public void dragEnter(DropTargetEvent event) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#dragLeave(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public void dragLeave(DropTargetEvent event) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#dragOperationChanged(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public void dragOperationChanged(DropTargetEvent event) {
    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#dragOver(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public void dragOver(DropTargetEvent event) {
    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#dropAccept(org.eclipse.swt.dnd.DropTargetEvent)
     */
    public void dropAccept(DropTargetEvent event) {
    }

    /**
     * @see org.eclipse.swt.dnd.DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent)
     */
    @SuppressWarnings("unchecked")
    public void drop(DropTargetEvent event) {

        if (event.data instanceof TreeSelection) {
            TreeSelection selection = (TreeSelection) event.data;

            if (!checkTreeSectionSource(selection)) {
                return;
            }
            viewModelListInDiagram.clear();
            viewModelListInDiagram.addAll(this.diagramEditor.getDiagram().getNodeList());
            CompoundCommand commpoundCommand = new CompoundCommand();

            int count = 0;
            Object object = null;
            for (Iterator<Object> i = selection.iterator(); i.hasNext();) {
                object = i.next();
                if (object instanceof ITreeNode) { // UML 요소일 경우
                    dropCommandForNode(event, commpoundCommand, (ITreeNode) object, count);
                    count++;
                }
            }

            diagramEditor.getDiagramCommandStack().execute(commpoundCommand);
        }
    }

    /**
     * UmlElement drop 처리
     * 
     * @param next
     * @param count
     *            void
     */
    protected void dropCommandForNode(DropTargetEvent event, CompoundCommand commpoundCommand, ITreeNode next, int count) {

        Element treeElement = (Element) next.getEObject();
        int horizontalScrollPoint = 0;
        int verticalScrollPoint = 0;
        Point eventPoint = null;
        Command command = null;
        org.eclipse.draw2d.geometry.Point where = null;
        NotationNode node = null;

        horizontalScrollPoint = ((FigureCanvas) diagramEditor.getDiagramGraphicalViewer().getControl()).getHorizontalBar()
            .getSelection();
        verticalScrollPoint = ((FigureCanvas) diagramEditor.getDiagramGraphicalViewer().getControl()).getVerticalBar()
            .getSelection();
        eventPoint = diagramEditor.getDiagramGraphicalViewer().getControl().toControl(event.x + horizontalScrollPoint,
            event.y + verticalScrollPoint);

        // y좌표에 +100은 여러개를 Dorp했을때 대각선 아래로 위치하도록 한다.
        where = new org.eclipse.draw2d.geometry.Point(eventPoint.x + (count * 100), eventPoint.y + (count * 100));
        node = createNotationNode(treeElement);
        node.setX(where.x);
        node.setY(where.y);
        command = new DropNotationCommand("Add drop");
        ((DropNotationCommand) command).setDiagram(this.diagramEditor.getDiagram());
        ((DropNotationCommand) command).setChild(node);
        commpoundCommand.add(command);
        if (!viewModelListInDiagram.contains(node)) {
            viewModelListInDiagram.add(node);
        }
        getNotationNodeWithRelation(commpoundCommand, node);
        // this.getNotationNodeAsAssociation(commpoundCommand, node);
        // this.getNotationNodeAsSource(commpoundCommand, node);
        // this.getNotationNodeAsTarget(commpoundCommand, node);

    }

    /**
     * 
     * 
     * @param commpoundCommand
     * @param nodeTree
     *            void
     */
    protected void getNotationNodeWithRelation(CompoundCommand commpoundCommand, AbstractNode nodeTree) {
        Element element = nodeTree.getUmlModel();
        if (null == element) {
            return;
        }

        List<Relationship> relationshipList = element.getRelationships();
        // cacheAdapter에만 남아있는 이미 삭제된 relation이 있는지 확인한다.
        // cacheAdpater에 남아있는 잔재 정보는 eContainer가 null이다.
        List<Relationship> relationList = removeDeletedRelation(relationshipList);

        List<Relationship> umlRelationshipList = new ArrayList<Relationship>();
        umlRelationshipList.addAll(relationList);
        if (element instanceof UseCase) {
            for (ExtensionPoint extensionPoint : ((UseCase) element).getExtensionPoints()) {
                umlRelationshipList.addAll(extensionPoint.getRelationships());
            }
        }
        Association association = null;
        DirectedRelationship directedRelationship = null;
        AbstractNode otherNode = null;
        Extend extend = null;

        for (Relationship relationship : umlRelationshipList) {

            if (relationship instanceof Association) {
                association = (Association) relationship;
                EList<Property> propList = association.getMemberEnds();
                if (null != propList && 1 < propList.size()) {
                    if (element.equals(propList.get(0).getType()) && element.equals(propList.get(1).getType())) {
                        createRelationViewModel(commpoundCommand, DiagramUtil.createConnection(relationship), nodeTree, nodeTree);
                    } else {
                        if (element.equals(propList.get(0).getType())) {

                            otherNode = findNotationNode(propList.get(1).getType());
                        } else {
                            otherNode = findNotationNode(propList.get(0).getType());
                        }
                        if (null != otherNode && !(element.equals(otherNode.getUmlModel()))) {
                            createRelationViewModel(commpoundCommand,
                                DiagramUtil.createConnection(relationship),
                                nodeTree,
                                otherNode);
                        }
                    }
                }

            } else if (relationship instanceof DirectedRelationship) {
                directedRelationship = (DirectedRelationship) relationship;
                if (relationship instanceof Extend) {
                    extend = (Extend) relationship;
                    if (element.equals(extend.getExtendedCase())) {
                        EList<ExtensionPoint> epList = extend.getExtensionLocations();
                        if (null != epList && 0 != epList.size()) {
                            otherNode = findNotationNode(epList.get(0));
                            createRelationViewModel(commpoundCommand,
                                DiagramUtil.createConnection(relationship),
                                nodeTree,
                                otherNode);
                        }
                    } else {
                        otherNode = findNotationNode(extend.getExtendedCase());
                        createRelationViewModel(commpoundCommand, DiagramUtil.createConnection(relationship), otherNode, nodeTree);
                    }

                } else {
                    if (directedRelationship.getTargets().contains(element)) {
                        EList<Element> elementList = directedRelationship.getSources();
                        if (null != elementList && 0 != elementList.size()) {
                            otherNode = findNotationNode(elementList.get(0));
                            createRelationViewModel(commpoundCommand,
                                DiagramUtil.createConnection(relationship),
                                otherNode,
                                nodeTree);
                        }
                    } else {
                        EList<Element> elementList = directedRelationship.getTargets();
                        if (null != elementList && 0 != elementList.size()) {
                            otherNode = findNotationNode(elementList.get(0));
                            createRelationViewModel(commpoundCommand,
                                DiagramUtil.createConnection(relationship),
                                nodeTree,
                                otherNode);
                        }
                    }
                }
            }
            otherNode = null;

        }
        association = null;
    }

    /**
     * @param relationshipList
     */
    private List<Relationship> removeDeletedRelation(List<Relationship> relationshipList) {

        List<Relationship> relationList = new ArrayList<Relationship>();
        for (Relationship relation : relationshipList) {
            if (null != relation.eContainer()) {
                relationList.add(relation);
            }
        }

        return relationList;
    }

    /**
     * 
     * 
     * @param element
     * @return AbstractNode
     */
    protected AbstractNode findNotationNode(Element element) {
        if (null == element) {
            return null;
        }
        Element target = null;
        if (element instanceof ExtensionPoint) {
            target = ((ExtensionPoint) element).getOwner();
        } else {
            target = element;
        }
        for (AbstractNode node : this.viewModelListInDiagram) {
            if (target.equals(node.getUmlModel())) {
                return node;
            }
        }
        return null;
    }

    /**
     * 
     * 
     * @param commpoundCommand
     * @param relation
     * @param sourceNode
     * @param targetNode
     *            void
     */
    protected void createRelationViewModel(CompoundCommand commpoundCommand, Relation relation,
                                           AbstractNode sourceNode, AbstractNode targetNode) {
        if (null == targetNode || null == sourceNode) {
            return;
        }
        Command command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
            sourceNode,
            targetNode,
            relation);
        commpoundCommand.add(command);
    }

    /**
     * createNotationNode
     * 
     * @param treeElement
     * @return NotationNode
     */
    protected NotationNode createNotationNode(Element treeElement) {
        NotationNode node = UMLDiagramFactory.eINSTANCE.createNotationNode();
        node.setUmlModel(treeElement);
        if (treeElement instanceof org.eclipse.uml2.uml.Component) {
            node.setNodeType(NodeType.COMPONENT);
            node.setWidth(FigureConstant.FIGURE_COMPONENT_WIDTH);
            node.setHeight(FigureConstant.FIGURE_COMPONENT_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Class) {
            node.setNodeType(NodeType.CLASS);
            node.setWidth(FigureConstant.FIGURE_CLASS_WIDTH);
            node.setHeight(FigureConstant.FIGURE_CLASS_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Package) {
            node.setNodeType(NodeType.PACKAGE);
            node.setWidth(FigureConstant.FIGURE_PACKAGE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_PACKAGE_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Artifact) {
            node.setNodeType(NodeType.ARTIFACT);
            node.setWidth(FigureConstant.FIGURE_ARTIFACT_WIDTH);
            node.setHeight(FigureConstant.FIGURE_ARTIFACT_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Collaboration) {
            node.setNodeType(NodeType.COLLABORATION);
            node.setWidth(FigureConstant.FIGURE_COLLABORATION_WIDTH);
            node.setHeight(FigureConstant.FIGURE_COLLABORATION_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Enumeration) {
            node.setNodeType(NodeType.ENUMERATION);
            node.setWidth(FigureConstant.FIGURE_ENUMERATION_WIDTH);
            node.setHeight(FigureConstant.FIGURE_ENUMERATION_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.DataType) {
            node.setNodeType(NodeType.DATA_TYPE);
            node.setWidth(FigureConstant.FIGURE_DATATYPE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_DATATYPE_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Interface) {
            node.setNodeType(NodeType.INTERFACE);
            node.setWidth(FigureConstant.FIGURE_INTERFACE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_INTERFACE_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.UseCase) {
            node.setNodeType(NodeType.USE_CASE);
            node.setWidth(FigureConstant.FIGURE_USECASE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_USECASE_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Actor) {
            node.setNodeType(NodeType.ACTOR);
            node.setWidth(FigureConstant.FIGURE_ACTOR_WIDTH);
            node.setHeight(FigureConstant.FIGURE_ACTOR_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.Collaboration) {
            node.setNodeType(NodeType.COLLABORATION_USE);
            node.setWidth(FigureConstant.FIGURE_COLLABORATIONUSE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_COLLABORATIONUSE_HEIGHT);
        } else if (treeElement instanceof org.eclipse.uml2.uml.DataType) {
            node.setNodeType(NodeType.DATA_TYPE);
            node.setWidth(FigureConstant.FIGURE_DATATYPE_WIDTH);
            node.setHeight(FigureConstant.FIGURE_DATATYPE_HEIGHT);
        }
        if (treeElement instanceof NamedElement) {
            node.setName(((NamedElement) treeElement).getName());
        }

        return node;
    }

    /**
     * 트리에서 선택된 요소가 다이어그램에 놓을 수 있는 요소인지 검사한다.
     * 
     * 
     * @param selection
     * @return boolean
     */
    protected boolean checkTreeSectionSource(TreeSelection selection) {
        DiagramType diagramType = diagramEditor.getDiagram().getType();
        if (DiagramType.CLASS_DIAGRAM.equals(diagramType)) {
            if (checkClassDiagram(selection)) {
                return true;
            }
        } else if (DiagramType.USE_CASE_DIAGRAM.equals(diagramType)) {
            if (checkUseCaseDiagram(selection)) {
                return true;
            }
        } else if (DiagramType.SEQUENCE_DIAGRAM.equals(diagramType)) {
            if (checkSequenceDiagram(selection)) {
                return true;
            }
        } else if (DiagramType.COMPONENT_DIAGRAM.equals(diagramType)) {
            if (checkComponentDiagram(selection)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * 
     * @param selection
     * @return boolean
     */
    @SuppressWarnings( { "restriction", "unchecked" })
    protected boolean checkSequenceDiagram(TreeSelection selection) {
        Object object;
        Element treeElement;
        for (Iterator<Object> i = selection.iterator(); i.hasNext();) {
            object = i.next();
            if (object instanceof ITreeNode) {// UML 요소일 경우
                treeElement = (Element) ((ITreeNode) object).getEObject();

                if (!(treeElement.getClass() == org.eclipse.uml2.uml.internal.impl.ActorImpl.class
                    || treeElement.getClass() == org.eclipse.uml2.uml.internal.impl.ClassImpl.class
                    || treeElement.getClass() == org.eclipse.uml2.uml.internal.impl.InterfaceImpl.class || treeElement.getClass() == org.eclipse.uml2.uml.internal.impl.ComponentImpl.class)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 
     * 
     * @param selection
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    protected boolean checkUseCaseDiagram(TreeSelection selection) {
        Object object;
        Element treeElement;
        for (Iterator<Object> i = selection.iterator(); i.hasNext();) {
            object = i.next();
            if (object instanceof ITreeNode) {// UML 요소일 경우
                treeElement = (Element) ((ITreeNode) object).getEObject();

                if (!(treeElement instanceof org.eclipse.uml2.uml.Actor
                    || treeElement instanceof org.eclipse.uml2.uml.Package
                    || treeElement instanceof org.eclipse.uml2.uml.UseCase || treeElement instanceof org.eclipse.uml2.uml.Collaboration)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 
     * 
     * @param selection
     *            void
     */
    @SuppressWarnings("unchecked")
    protected boolean checkClassDiagram(TreeSelection selection) {
        Object object;
        Element treeElement;
        for (Iterator<Object> i = selection.iterator(); i.hasNext();) {
            object = i.next();
            if (object instanceof ITreeNode) {// UML 요소일 경우
                treeElement = (Element) ((ITreeNode) object).getEObject();

                if (!(treeElement instanceof org.eclipse.uml2.uml.Class
                    || treeElement instanceof org.eclipse.uml2.uml.Package
                    || treeElement instanceof org.eclipse.uml2.uml.Actor
                    || treeElement instanceof org.eclipse.uml2.uml.Artifact
                    || treeElement instanceof org.eclipse.uml2.uml.Collaboration
                    || treeElement instanceof org.eclipse.uml2.uml.DataType
                    || treeElement instanceof org.eclipse.uml2.uml.Enumeration || treeElement instanceof org.eclipse.uml2.uml.Interface)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 
     * 
     * @param selection
     *            void
     */
    @SuppressWarnings("unchecked")
    protected boolean checkComponentDiagram(TreeSelection selection) {
        Object object;
        Element treeElement;
        for (Iterator<Object> i = selection.iterator(); i.hasNext();) {
            object = i.next();
            if (object instanceof ITreeNode) {// UML 요소일 경우
                treeElement = (Element) ((ITreeNode) object).getEObject();

                if (!(treeElement instanceof org.eclipse.uml2.uml.Class
                    || treeElement instanceof org.eclipse.uml2.uml.Package
                    || treeElement instanceof org.eclipse.uml2.uml.Artifact
                    || treeElement instanceof org.eclipse.uml2.uml.Interface || treeElement instanceof org.eclipse.uml2.uml.Component)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 
     * 
     * @param treeElement
     * @param element
     * @return AbstractNode
     */
    protected void getNotationNodeAsAssociation(CompoundCommand commpoundCommand, NotationNode nodeTree) {
        Relation connection = null;
        Element element = nodeTree.getUmlModel();
        AbstractNode abstractNode;
        AbstractNode targetAbstractNode = null;
        Relationship relationship;
        List<Relationship> relationshipList;
        Command command = null;
        // element.getRelationships();
        // element.getTargetDirectedRelationships();

        for (Iterator<AbstractNode> iter = viewModelListInDiagram.iterator(); iter.hasNext();) {
            abstractNode = iter.next();
            if (null == abstractNode.getUmlModel()) {
                continue;
            }
            // 자기 자신에 대한 연결은 제외.
            if (abstractNode.getUmlModel().equals(element)) {
                continue;
            }

            relationshipList = UMLManager.getRelatedRelationshipModel(abstractNode.getUmlModel());
            for (Iterator<Relationship> i = relationshipList.iterator(); i.hasNext();) {
                relationship = i.next();
                if (relationship instanceof Association) {
                    if (((Association) relationship).getMemberEnds().get(0).getType().equals(element)) {
                        connection = UMLDiagramFactory.eINSTANCE.createRelation();
                        connection.setUmlModel(relationship);
                        targetAbstractNode = abstractNode;
                        connection.setRelationType(RelationType.ASSOCIATION);
                        command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                            nodeTree,
                            targetAbstractNode,
                            connection);
                        commpoundCommand.add(command);
                    } else if (((Association) relationship).getMemberEnds().get(1).getType().equals(element)) {
                        connection = UMLDiagramFactory.eINSTANCE.createRelation();
                        connection.setUmlModel(relationship);
                        targetAbstractNode = abstractNode;
                        connection.setRelationType(RelationType.ASSOCIATION);
                        command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                            nodeTree,
                            targetAbstractNode,
                            connection);
                        commpoundCommand.add(command);
                    }
                }
            }
        }
    }

    /**
     * 
     * 
     * @param element
     * @return AbstractNode
     */
    protected void getNotationNodeAsSource(CompoundCommand commpoundCommand, NotationNode nodeTree) {
        Relation connection = null;
        Element treeElement = nodeTree.getUmlModel();
        AbstractNode abstractNode;
        DirectedRelationship directedRelationship;
        EList<DirectedRelationship> directedRelationshipList = treeElement.getSourceDirectedRelationships();
        Command command = null;
        Element umlModel;
        Extend extend;
        for (Iterator<AbstractNode> iter = viewModelListInDiagram.iterator(); iter.hasNext();) {
            abstractNode = iter.next();
            umlModel = abstractNode.getUmlModel();
            if (null == umlModel) {
                continue;
            }
            if (umlModel.equals(treeElement)) {
                continue;
            }
            for (Iterator<DirectedRelationship> i = directedRelationshipList.iterator(); i.hasNext();) {
                directedRelationship = i.next();
                if (directedRelationship instanceof Extend && umlModel instanceof UseCase) {
                    extend = (Extend) directedRelationship;
                    for (Iterator<org.eclipse.uml2.uml.ExtensionPoint> ext = extend.getExtensionLocations().iterator(); ext.hasNext();) {
                        if (ext.next().eContainer().equals(umlModel)) {
                            connection = UMLDiagramFactory.eINSTANCE.createRelation();
                            connection.setUmlModel(directedRelationship);
                            setConnectionType(connection, directedRelationship);
                            command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                                nodeTree,
                                abstractNode,
                                connection);
                            commpoundCommand.add(command);
                        }
                    }

                } else if (directedRelationship.getTargets().contains(umlModel)) {
                    connection = UMLDiagramFactory.eINSTANCE.createRelation();
                    connection.setUmlModel(directedRelationship);
                    setConnectionType(connection, directedRelationship);
                    command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                        nodeTree,
                        abstractNode,
                        connection);
                    commpoundCommand.add(command);
                }
            }

        }
    }

    /**
     * 
     * 
     * @param connection
     * @param relationship
     *            void
     */
    protected void setConnectionType(Relation connection, Relationship relationship) {
        if (relationship.eClass() == UMLPackage.Literals.ASSOCIATION) {
            connection.setRelationType(RelationType.ASSOCIATION);
        } else if (relationship.eClass() == UMLPackage.Literals.INTERFACE_REALIZATION) {
            connection.setRelationType(RelationType.INTERFACE_REALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.COMPONENT_REALIZATION) {
            connection.setRelationType(RelationType.COMPONENT_REALIZATION);
        }
        // } else if (directedRelationship.eClass() ==
        // UMLPackage.Literals.SUBSTITUTION) {
        // connection.setRelationType(RelationType.SU);
        // }
        else if (relationship.eClass() == UMLPackage.Literals.REALIZATION) {
            connection.setRelationType(RelationType.REALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.ABSTRACTION) {
            connection.setRelationType(RelationType.ABSTRACTION);
        } else if (relationship.eClass() == UMLPackage.Literals.USAGE) {
            connection.setRelationType(RelationType.USAGE);
        } else if (relationship.eClass() == UMLPackage.Literals.DEPENDENCY) {
            connection.setRelationType(RelationType.DEPENDENCY);
        } else if (relationship.eClass() == UMLPackage.Literals.EXTEND) {
            connection.setRelationType(RelationType.EXTEND);
        } else if (relationship.eClass() == UMLPackage.Literals.INCLUDE) {
            connection.setRelationType(RelationType.INCLUDE);
        } else if (relationship.eClass() == UMLPackage.Literals.GENERALIZATION) {
            connection.setRelationType(RelationType.GENERALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.INTERFACE_REALIZATION) {
            connection.setRelationType(RelationType.INTERFACE_REALIZATION);
        }
    }

    /**
     * 
     * 
     * @param element
     * @return AbstractNode
     */
    protected void getNotationNodeAsTarget(CompoundCommand commpoundCommand, NotationNode nodeTree) {
        Relation connection = null;
        Element treeElement = nodeTree.getUmlModel();
        AbstractNode abstractNode;
        DirectedRelationship directedRelationship;
        EList<DirectedRelationship> directedRelationshipList = treeElement.getTargetDirectedRelationships();
        Command command = null;
        Element umlModel;
        Extend extend;
        for (Iterator<AbstractNode> iter = viewModelListInDiagram.iterator(); iter.hasNext();) {
            abstractNode = iter.next();
            umlModel = abstractNode.getUmlModel();
            if (null == umlModel) {
                continue;
            }
            if (umlModel.equals(treeElement)) {
                continue;
            }
            for (Iterator<DirectedRelationship> i = directedRelationshipList.iterator(); i.hasNext();) {
                directedRelationship = i.next();
                if (directedRelationship.getSources().contains(umlModel)) {
                    connection = UMLDiagramFactory.eINSTANCE.createRelation();
                    connection.setUmlModel(directedRelationship);
                    setConnectionType(connection, directedRelationship);
                    command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                        abstractNode,
                        nodeTree,
                        connection);
                    commpoundCommand.add(command);
                }
            }

        }
        ExtensionPoint extensionPoint;
        if (treeElement instanceof UseCase) {
            for (Iterator<ExtensionPoint> extensionIter = ((UseCase) treeElement).getExtensionPoints().iterator(); extensionIter.hasNext();) {
                extensionPoint = extensionIter.next();
                for (Iterator<AbstractNode> iter = viewModelListInDiagram.iterator(); iter.hasNext();) {
                    abstractNode = iter.next();
                    umlModel = abstractNode.getUmlModel();
                    directedRelationshipList = umlModel.getSourceDirectedRelationships();
                    for (Iterator<DirectedRelationship> i = directedRelationshipList.iterator(); i.hasNext();) {
                        directedRelationship = i.next();
                        if (directedRelationship instanceof Extend) {
                            extend = (Extend) directedRelationship;
                            if (extend.getExtensionLocations().contains(extensionPoint)) {
                                connection = UMLDiagramFactory.eINSTANCE.createRelation();
                                connection.setUmlModel(directedRelationship);
                                setConnectionType(connection, directedRelationship);
                                command = new RelationAutoGenerationCommand(this.diagramEditor.getDiagram(),
                                    abstractNode,
                                    nodeTree,
                                    connection);
                                commpoundCommand.add(command);
                            }
                        }
                    }
                }
            }
        }
    }
}
