/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import java.net.Authenticator.RequestorType;
import java.util.List;

import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityCreateContainerNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityCreateNotationNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityPartitionChangeNodeConstraintCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityDiagramEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityPartitionLayoutPolicy.AddChildCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityPartitionLayoutPolicy.OrphanChildrenCommand;
import nexcore.tool.uml.ui.core.diagram.command.ChangeNodeConstraintCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.core.util.UiUtil;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설 명 : ActivityDiagramLayoutEditPolicy</li>
 * <li>작성일 : 2011. 2. 10.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ActivityDiagramLayoutEditPolicy extends DiagramLayoutEditPolicy {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if( child instanceof ActivityPartitionEditPart ) { 
            ActivityPartitionChangeNodeConstraintCommand command = new ActivityPartitionChangeNodeConstraintCommand(child, constraint);
            return command;

        } else {
            ChangeNodeConstraintCommand command = new ChangeNodeConstraintCommand(child, constraint);

            List<EditPartViewer> editPartList = child.getViewer().getSelectedEditParts();
            EditPart editPart;
            boolean isLabel = false;
            boolean isNotLabel = false;
            for (int i = 0; i < editPartList.size(); i++) {
                editPart = (EditPart) editPartList.get(i);
                if (editPart instanceof LabelNodeEditPart || editPart instanceof CompartmentLabelNodeEditPart) {
                    isLabel = true;
                } else if (!(editPart instanceof LabelNodeEditPart)) {
                    isNotLabel = true;
                }

                if (isLabel && isNotLabel) {
                    command.setCommandIsFalse(false);
                }
            }
            return command;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    /**
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseSourceFeedback(Request request) {
//        if (null != getFeedbackLayer().getChildren() && !getFeedbackLayer().getChildren().isEmpty()) {
//            if( AnalysisConstant.MOVE.equals( request.getType() ) ) {
//                if( request instanceof ChangeBoundsRequest ) {
//                    ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
//                    List<EditPart> editParts = changeBoundsRequest.getEditParts();
//                    for( EditPart editpart : editParts ) {
//                        if( editpart instanceof LabelNodeEditPart ) {
//                            super.eraseSourceFeedback(request);
//                        }
//                    }
//                }
//                return;
//            }
//            getFeedbackLayer().getChildren().clear();
//        }
//        super.eraseTargetFeedback(request);
    }

    /* (non-Javadoc)
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseTargetFeedback(Request request) {
//        if (null != getFeedbackLayer().getChildren() && !getFeedbackLayer().getChildren().isEmpty()) {
//            if( AnalysisConstant.MOVE.equals(request.getType()) || AnalysisConstant.ADD_CHILD.equals(request.getType()) ) {
//                if( request instanceof ChangeBoundsRequest ) {
//                    ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
//                    List<EditPart> editParts = changeBoundsRequest.getEditParts();
//                    for( EditPart editpart : editParts ) {
//                        if( editpart instanceof LabelNodeEditPart ) {
//                            super.eraseTargetFeedback(request);
//                        }
//                    }
//                }
//                return; 
//            }
//            getFeedbackLayer().getChildren().clear();
////            super.eraseTargetFeedback(request);
//        super.eraseTargetFeedback(request);
    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        diagramEditDomain = (DiagramEditDomain) getHost().getViewer().getEditDomain();
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);

        if (request.getNewObject() instanceof ContainerNode) {
            return new ActivityCreateContainerNodeCommand(diagramEditDomain, getHost(), request, location);
        } else if (request.getNewObject() instanceof NotationNode) {
            return new ActivityCreateNotationNodeCommand(diagramEditDomain, getHost(), request, location);
        } else {
            return null;
        }

    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.Request)
     */
    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getOrphanChildrenCommand(Request request) {
        
        return new OrphanChildrenCommand((GroupRequest) request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getAddCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getAddCommand(Request generic) {

        return new AddChildCommand((ChangeBoundsRequest) generic);
    }
    
    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
     * <li>설  명 : OrphanChildrenCommand</li>
     * <li>작성일 : 2011. 7. 14.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public class OrphanChildrenCommand extends Command {

        /** generic */
        private GroupRequest generic;

        public OrphanChildrenCommand(GroupRequest request) {
            this.generic = request;
        }

        @Override
        public void execute() {
            UiUtil.eraseFeedback(getHost());
            ActivityDiagramEditPart activityDiagramEditPart = null;
            if( getHost() instanceof ActivityDiagramEditPart ) {
                activityDiagramEditPart = (ActivityDiagramEditPart) getHost();
            }
            Diagram diagram = null;
            if( null != activityDiagramEditPart ) {
                diagram = (Diagram) activityDiagramEditPart.getModel();
            }
            
            if( null != diagram ) {
                
                List<EditPart> children = generic.getEditParts();
                for( int i = 0; i < children.size(); i++ ) {
                    
                    // 파티션 이동의 경우 아직 처리하지 않는다 추후 처리예정
                    EditPart child = children.get(i);
                    if( child instanceof ActivityPartitionEditPart ) {
                        continue;
                    }
                    
                    AbstractView view = (AbstractView) child.getModel();
                    if( view instanceof LabelNode ) { 
                        continue;
                    }
                    
                    if( view instanceof NotationNode ) {
                        NotationNode node = (NotationNode) view;
                        diagram.getNodeList().remove(node);
                        if(!node.getLabels().isEmpty()) {
                            diagram.getNodeList().remove(node.getLabels().get(0));
                        }
                        
                    } else if ( view instanceof AbstractConnection ) {
                        AbstractConnection connection = (AbstractConnection) view;
                    }
                }
                
                activityDiagramEditPart.refresh();
              getFeedbackLayer().getChildren().clear();
            }
        }
    }
    
    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
     * <li>설  명 : AddChildCommand</li>
     * <li>작성일 : 2011. 7. 14.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public class AddChildCommand extends Command {

        /** generic */
        private ChangeBoundsRequest generic;

        public AddChildCommand(ChangeBoundsRequest request) {
            this.generic = request;
        }

        @Override
        public void execute() {
            UiUtil.eraseFeedback(getHost());
            EditPart editPart = getHost().getRoot().getContents();
            Diagram diagram = (Diagram) editPart.getModel();

            org.eclipse.draw2d.geometry.Point cusorPoint = ((ChangeBoundsRequest) generic).getMouseLocation();
            if( null == diagram ) {
                return;
            }
            
            List<EditPart> orphanChildrenEditPart = generic.getEditParts();
            for( EditPart orphanChild : orphanChildrenEditPart ) {
                
                if( orphanChild.getModel() instanceof LabelNode ) { 
                    continue;
                }
                
                NotationNode notationNode = (NotationNode) orphanChild.getModel();
                Point location = new Point( notationNode.getX(), notationNode.getY() );
                notationNode.setX( location.x + generic.getMoveDelta().x );
                notationNode.setY( location.y + generic.getMoveDelta().y );
                
                diagram.getNodeList().add( notationNode );
                if(!notationNode.getLabels().isEmpty()) {
                    diagram.getNodeList().add( notationNode.getLabels().get(0) );
                }
            }
            editPart.refresh();
            getFeedbackLayer().getChildren().clear();
        }
    }
}
