/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import java.util.List;

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

import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityCreateNotationNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.core.diagram.command.ChangeNodeConstraintCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.core.util.UiUtil;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설 명 : ActivityPartitionLayoutPolicy</li>
 * <li>작성일 : 2011. 2. 14.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityPartitionLayoutPolicy extends DiagramLayoutEditPolicy {
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
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

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    /**
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseSourceFeedback(Request request) {
        if (null != getFeedbackLayer().getChildren() && !getFeedbackLayer().getChildren().isEmpty()) {
            if( AnalysisConstant.MOVE.equals( request.getType() ) ) {
                return;
            }
//            super.eraseSourceFeedback(request);
        }
    }

    /* (non-Javadoc)
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseTargetFeedback(Request request) {
        if (null != getFeedbackLayer().getChildren() && !getFeedbackLayer().getChildren().isEmpty()) {
            if( AnalysisConstant.MOVE.equals( request.getType() ) ) {
                return;
            }
//            super.eraseTargetFeedback(request);
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        diagramEditDomain = (DiagramEditDomain) getHost().getViewer().getEditDomain();
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);

        // 파티션안에 파티션 생성. 계층 구조 생성.
        // if (request.getNewObject() instanceof ContainerNode) {
        // return new ActivityCreateContainerNodeCommand(diagramEditDomain,
        // getHost().getModel(), request, location);
        // }
        if (request.getNewObject() instanceof NotationNode) {
            return new ActivityCreateNotationNodeCommand(diagramEditDomain, getHost(), request, location);
        } else {
            return null;
        }

    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getAddCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getAddCommand(Request generic) {
        // return super.getAddCommand(generic);]
        return new AddChildCommand((ChangeBoundsRequest) generic);
    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getOrphanChildrenCommand(Request request) {
        // return super.getOrphanChildrenCommand(request);
        return new OrphanChildrenCommand((GroupRequest) request);
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
     * <li>설 명 : OrphanChildren</li>
     * <li>작성일 : 2011. 7. 13.</li>
     * <li>작성자 : Kang</li>
     * </ul>
     */
    public class OrphanChildrenCommand extends Command {

        /**
         * generic
         */
        private GroupRequest generic;

        public OrphanChildrenCommand(GroupRequest request) {
            this.generic = request;
        }

        @Override
        public void execute() {
            UiUtil.eraseFeedback(getHost());
            ActivityPartitionEditPart partitionEditPart = null;
            ActivityPartition partition = null;
            
            if( getHost() instanceof ActivityPartitionEditPart ) {
                partitionEditPart = (ActivityPartitionEditPart) getHost();
            }
            ContainerNode containerNode = null;
            if( null != partitionEditPart ) {
                containerNode = (ContainerNode) partitionEditPart.getModel();
                partition = (ActivityPartition) containerNode.getUmlModel();
            }
            
            if( null != containerNode ) {
                
                List<EditPart> children = generic.getEditParts();
                for( int i = 0; i < children.size(); i++ ) {
//                for( EditPart child : children ) {
                    AbstractView view = (AbstractView) children.get(i).getModel();
                    
                    if( view instanceof LabelNode ) {
                        continue;
                    }
                    
                    if( view instanceof NotationNode ) {
                        NotationNode node = (NotationNode) view;
                        containerNode.getNodeList().remove(node);
                        if(!node.getLabels().isEmpty()) {
                            containerNode.getNodeList().remove(node.getLabels().get(0));
                            partition.getNodes().remove(node.getUmlModel());
                        }
                        
                    } else if ( view instanceof AbstractConnection ) {
                        AbstractConnection connection = (AbstractConnection) view;
                    }
                }
                
                partitionEditPart.refresh();
                
            }
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
     * <li>설 명 : addChildCommand</li>
     * <li>작성일 : 2011. 7. 13.</li>
     * <li>작성자 : Kang</li>
     * </ul>
     */
    public class AddChildCommand extends Command {

        /**
         * generic
         */
        private ChangeBoundsRequest generic;

        public AddChildCommand(ChangeBoundsRequest request) {
            this.generic = request;
        }

        @Override
        public void execute() {
            UiUtil.eraseFeedback(getHost());
            EditPart sourceEditPart = getHost();
            AbstractNode sourceNode = (AbstractNode) sourceEditPart.getModel();
            final Element element = sourceNode.getUmlModel();

            EditPart editPart = getHost().getRoot().getContents();
            Diagram diagram = (Diagram) editPart.getModel();
            List<AbstractNode> nodeList = diagram.getNodeList();

            org.eclipse.draw2d.geometry.Point cusorPoint = ((ChangeBoundsRequest) generic).getMouseLocation();

            ContainerNode currentPartition = null;
            for( AbstractNode node : nodeList ) {
                if( node.getUmlModel() instanceof ActivityPartition ) {
                    final ActivityPartition partitionNode = (ActivityPartition) node.getUmlModel();
                    Rectangle bounds = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
                    if ( bounds.contains(cusorPoint) ) {

                        currentPartition = (ContainerNode) node;
                        break;
                    }
                }
            }
            
            if( null == currentPartition ) {
                return;
            }
            List<EditPart> orphanChildrenEditPart = generic.getEditParts();
            for( EditPart orphanChild : orphanChildrenEditPart ) {

                // 파티션 이동의 경우 아직 처리하지 않는다 추후 처리예정
                if( orphanChild instanceof ActivityPartitionEditPart ) {
                    continue;
                }
                
                if( orphanChild.getModel() instanceof LabelNode ) {
                    continue;
                }

                NotationNode notationNode = (NotationNode) orphanChild.getModel();
                Point location = new Point( notationNode.getX(), notationNode.getY() );
                notationNode.setX( location.x + generic.getMoveDelta().x );
                notationNode.setY( location.y + generic.getMoveDelta().y );
                
                currentPartition.getNodeList().add( notationNode );
                if(!notationNode.getLabels().isEmpty()) {
                    currentPartition.getNodeList().add( notationNode.getLabels().get(0) );
                }
                
                ActivityPartition partition = (ActivityPartition) currentPartition.getUmlModel();
                partition.getNodes().add( (ActivityNode) notationNode.getUmlModel() );
                ( (ActivityNode) notationNode.getUmlModel() ).getInPartitions().add(partition);
            }
            
            sourceEditPart.refresh();
            editPart.refresh();
        }
    }
}
