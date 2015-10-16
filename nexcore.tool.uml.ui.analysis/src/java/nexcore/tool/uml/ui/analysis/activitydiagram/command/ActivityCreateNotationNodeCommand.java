/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.command.CreateNotationNodeCommand;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : ActivityCreateNotationNodeCommand</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityCreateNotationNodeCommand extends CreateNotationNodeCommand {

    /**
     * ActivityCreateNotationNodeCommand
     * @param editDomain
     * @param editPart
     * @param request
     * @param location
     */
    public ActivityCreateNotationNodeCommand(EditDomain editDomain, EditPart editPart, CreateRequest request,
    Point location) {
        super(editDomain, editPart, request, location);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.command.CreateNotationNodeCommand#execute()
     */
    @Override
    public void execute() {
        UiUtil.eraseFeedback(editPart);
        
        notationNode = (NotationNode) request.getNewObject();

        setLocationAndSize();

        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;

        if (umlModel instanceof NamedElement) {
            if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode
                || umlModel instanceof org.eclipse.uml2.uml.Action) {
                if (this.parentNodeModel instanceof ContainerNode) {
                    parentElement = (PackageableElement) this.parentNodeModel.getParent();
                } else {
                    parentElement = UMLManager.getParent(this.parentNodeModel);
                }
            } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
                && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
                parentElement = UMLManager.getParentPackage(this.parentNodeModel);
                ((NamedElement) umlModel).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
                    ((NamedElement) umlModel).getName()));
            }
            
            if( isNamedActivity(umlModel) ) {
                if (null == parentElement) {
                    parentElement = UMLManager.getParent(this.parentNodeModel);
                }
                if(parentElement instanceof Activity){
                    ((NamedElement) umlModel).setName(getPackagedUniqueName( (Activity) parentElement,
                        ((NamedElement) umlModel).getName()));
                }
                notationNode.setName(((NamedElement) umlModel).getName());
            }
        }
        redo();

        ((AbstractDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
            .getEditDomain()
            .setDefaultTool(new NewSelectionTool());

        if (notationNode.getNodeType().equals(NodeType.INITIAL_NODE)
            || notationNode.getNodeType().equals(NodeType.ACTIVITY_FINAL_NODE)
            || notationNode.getNodeType().equals(NodeType.MERGE_NODE)
            || notationNode.getNodeType().equals(NodeType.DECISION_NODE)
            || notationNode.getNodeType().equals(NodeType.FORK_NODE)
            || notationNode.getNodeType().equals(NodeType.JOIN_NODE)) {
            return;
        }
        ViewModelUtil.setModelInfo(notationNode);
    }

    /**
     * isNamedActivity
     *  
     * @param umlModel
     * @return boolean
     */
    private boolean isNamedActivity(Element umlModel) {
        if( umlModel instanceof OpaqueAction || umlModel instanceof CentralBufferNode || umlModel instanceof DataStoreNode ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 노드의 위치와 사이즈 설정 void
     */
    private void setLocationAndSize() {
        notationNode.setX(location.x);
        notationNode.setY(location.y);
        Dimension size = null;
        
        if(NodeType.OPAQUE_ACTION.equals(notationNode.getNodeType())
            || NodeType.DATA_STORE_NODE.equals(notationNode.getNodeType())
            || NodeType.CENTRAL_BUFFER_NODE.equals(notationNode.getNodeType())) {
            size = request.getSize();           
        }

        if (size != null) {
            if (size.width > WIDTH_MINIMUM_SIZE) {
                notationNode.setWidth(size.width);
            } else {
                notationNode.setWidth(WIDTH_MINIMUM_SIZE);
            }
            if (size.height > HEIGHT_MINIMUM_SIZE) {
                notationNode.setHeight(size.height);
            } else {
                notationNode.setHeight(HEIGHT_MINIMUM_SIZE);
            }
        } else {
            Dimension dimension = FigureConstant.getFigureDimension(notationNode.getNodeType());
            notationNode.setWidth(dimension.width);
            notationNode.setHeight(dimension.height);
        }

        Element umlModel = notationNode.getUmlModel();
        if (umlModel instanceof org.eclipse.uml2.uml.ControlNode) {
            if(!notationNode.getLabels().isEmpty()){
                
                LabelNode nodeLabel = notationNode.getLabels().get(0);
                nodeLabel.setParent(notationNode);
                nodeLabel.setX(10);
                nodeLabel.setY(-20);
                nodeLabel.setType(LabelType.COMPARTMENT);
            }
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;
        if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode || umlModel instanceof org.eclipse.uml2.uml.Action) {
            if (this.parentNodeModel instanceof ContainerNode) {
                parentElement = (PackageableElement) this.parentNodeModel.getParent();
            } else {
                parentElement = UMLManager.getParent(this.parentNodeModel);
            }
            Activity activity = (Activity) parentElement;
            activity.getNodes().add((ActivityNode) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            parentElement = UMLManager.getParentPackage(this.parentNodeModel);
            ((org.eclipse.uml2.uml.Package) parentElement).getPackagedElements().add((PackageableElement) umlModel);
        }
        notationNode.setParent(parentElement);
        if (parentNodeModel instanceof Diagram) {
            
            ((Diagram) parentNodeModel).getNodeList().add(notationNode);
            if (umlModel instanceof org.eclipse.uml2.uml.ControlNode) {
                
                if( !notationNode.getLabels().isEmpty() )
                ((Diagram) parentNodeModel).getNodeList().add(notationNode.getLabels().get(0));
            }
            
        } else if (parentNodeModel instanceof ContainerNode) {
            
            ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
            if( umlModel instanceof ActivityNode ) {
                ActivityNode activityNode = (ActivityNode) umlModel;
                ActivityPartition activityPartition = (ActivityPartition) parentNodeModel.getUmlModel();
                
                activityNode.getInPartitions().add( activityPartition );
                activityPartition.setRepresents(activityNode);
            }
            if (umlModel instanceof org.eclipse.uml2.uml.ControlNode) {
                if( !notationNode.getLabels().isEmpty() )
                ((ContainerNode) parentNodeModel).getNodeList().add(notationNode.getLabels().get(0));
            }
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Element umlModel = notationNode.getUmlModel();

        if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode || umlModel instanceof org.eclipse.uml2.uml.Action) {
            Activity activity = (Activity) notationNode.getParent();
            activity.getNodes().remove(umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            org.eclipse.uml2.uml.Package parentElement = (org.eclipse.uml2.uml.Package) notationNode.getParent();
            parentElement.getPackagedElements().remove(umlModel);
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().remove(notationNode);
            if (umlModel instanceof org.eclipse.uml2.uml.ControlNode) {
                if( !notationNode.getLabels().isEmpty() )
                ((Diagram) parentNodeModel).getNodeList().remove(notationNode.getLabels().get(0));
            }
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().remove(notationNode);
            if (umlModel instanceof org.eclipse.uml2.uml.ControlNode) {
                if( !notationNode.getLabels().isEmpty() )
                ((ContainerNode) parentNodeModel).getNodeList().remove(notationNode.getLabels().get(0));
            }
        }
    }
    /** NAME_INDEX */
    private static int NAME_INDEX = 1;
    
    /**
     * getPackagedUniqueName
     *  
     * @param parentPackage
     * @param name
     * @return String
     */
    private static String getPackagedUniqueName(Activity parentPackage, String name) {
        int index = 1;
        String newName;
        while (true) {
            newName = name + Integer.toString(index++);
            if (null == parentPackage.getNode(newName)) {
                return newName;
            }
            if (10000 <= index) {
                return name + Integer.toString(NAME_INDEX++);
            }
        }

    }
}
