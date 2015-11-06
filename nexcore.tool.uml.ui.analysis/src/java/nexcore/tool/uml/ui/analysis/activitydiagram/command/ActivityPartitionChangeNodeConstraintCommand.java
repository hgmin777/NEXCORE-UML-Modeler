/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;
import nexcore.tool.uml.ui.analysis.activitydiagram.util.ActivityDiagramUtil;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.util.UiUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : ActivityPartitionChangeNodeConstraintCommand</li>
 * <li>작성일 : 2011. 7. 8.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ActivityPartitionChangeNodeConstraintCommand extends Command {

    /** newConstraint */
    private Rectangle newConstraint;

    /** viewModel */
    private ContainerNode containerNode;

    /** editPart */
    private EditPart editPart;
    
    /** parentEditPart */
    private EditPart parentEditPart;
    
    /** oldConstraint */
    private Rectangle oldConstraint;

    /** isHorizontal */
    private boolean isHorizontal;

    /**
     * ActivityPartitionChangeNodeConstraintCommand
     * @param editPart
     * @param constraint
     */
    public ActivityPartitionChangeNodeConstraintCommand(EditPart editPart, Object constraint) {
        this.containerNode = (ContainerNode) editPart.getModel();
        this.editPart = editPart;
        if (constraint instanceof Rectangle) {
            this.newConstraint = (Rectangle) constraint;
        }
        this.oldConstraint = new Rectangle(containerNode.getX(), containerNode.getY(), containerNode.getWidth(), containerNode.getHeight() );
        this.parentEditPart = editPart.getParent();
        
        checkPartitionDirection();
    }

    /**
     * ActivityPartitionChangeNodeConstraintCommand
     * @param editPart
     * @param direction
     * @param constraint
     */
    public ActivityPartitionChangeNodeConstraintCommand(EditPart editPart, int direction, Object constraint) {
        this.containerNode = (ContainerNode) editPart.getModel();
        this.editPart = editPart;
        if (constraint instanceof Rectangle) {
            this.newConstraint = (Rectangle) constraint;
        }
        this.oldConstraint = new Rectangle(containerNode.getX(), containerNode.getY(), containerNode.getWidth(), containerNode.getHeight() );
        this.parentEditPart = editPart.getParent();
        
        checkPartitionDirection();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        UiUtil.eraseFeedback(editPart);
        this.containerNode.setWidth( newConstraint.width );
        this.containerNode.setHeight( newConstraint.height );
        
        setLocationAndSize();
        
        List<ActivityPartitionEditPart> partitionEditPartList = new ArrayList<ActivityPartitionEditPart>();
        List<ContainerNode> partitionList = new ArrayList<ContainerNode>();
        
        List<EditPart> children = this.parentEditPart.getChildren();
        
        for( EditPart child : children ) { 
            if( child instanceof ActivityPartitionEditPart ) {
                ActivityPartitionEditPart activityPartitionEditPart = (ActivityPartitionEditPart) child;
                partitionEditPartList.add( activityPartitionEditPart );
                partitionList.add( (ContainerNode) activityPartitionEditPart.getModel() );
            }
        }
        
        IDialogSettings dialogSetting = ActivityDiagramUtil.getDialogSetting();
        String partitionDirection = AnalysisConstant.EMPTY_STRING;
        boolean isHorizontal = true;
        partitionDirection = dialogSetting.get(AnalysisConstant.DIRECTION);
        partitionDirection = dialogSetting.get(AnalysisConstant.DIRECTION);
        if( null == partitionDirection ) {
            isHorizontal = false;
            dialogSetting.put(AnalysisConstant.DIRECTION, AnalysisConstant.VERTICAL);
        }
        if( AnalysisConstant.HORIZONTAL.equals(partitionDirection) ) {
            isHorizontal = true;
        } else if( AnalysisConstant.VERTICAL.equals(partitionDirection) ) {
            isHorizontal = false;
        }
        
        // 파티션들의 높이를 동일하게 맞춰준다.
        for( ActivityPartitionEditPart partition : partitionEditPartList ) {
            ContainerNode containerNode = (ContainerNode) partition.getModel();
            ActivityPartitionFigure partitionFigure = (ActivityPartitionFigure) partition.getFigure();
            
            if( isHorizontal ) {
                containerNode.setWidth(newConstraint.width);
            } else { 
                containerNode.setHeight(newConstraint.height);
            }
        }
        
        
        // 파티션의 크기 조절 시 범위 내에 일반 액티비티 노드가 있다면 위치를 시프트 해준다.
        int sumWidth = 0;
        int sumHeight = 0;
        Point defaultPoint = new Point(20, 20);
        
        for ( ActivityPartitionEditPart activityPartitionEditPart : partitionEditPartList ) { 
            ContainerNode partition = (ContainerNode) activityPartitionEditPart.getModel();
            sumHeight = partition.getHeight();
            sumWidth += partition.getWidth();
        }
        Rectangle bounds = new Rectangle();
        bounds.setLocation(defaultPoint);
        bounds.setSize(sumWidth, sumHeight);
        
        List<NotationNode> nodeList = new ArrayList<NotationNode>();
        
        for ( EditPart child : children ) {
            if ( child.getModel() instanceof NotationNode ) {
                NotationNode notationNode = (NotationNode) child.getModel();
                Point nodeLocation = new Point(notationNode.getX(), notationNode.getY());
                if( bounds.contains(nodeLocation) ) {
                   
                    nodeList.add(notationNode);
                }
            }
        }
        
        for ( NotationNode node : nodeList ) { 
            Diagram diagram = (Diagram) parentEditPart.getModel();
            diagram.getNodeList().remove(node);
            if( !node.getLabels().isEmpty() ) {
                diagram.getNodeList().remove(node.getLabels().get(0));
            }
            containerNode.getNodeList().add(node);
            if( !node.getLabels().isEmpty() ) {
                containerNode.getNodeList().add(node.getLabels().get(0));
            }
            ((ActivityPartition)containerNode.getUmlModel()).getNodes().add((ActivityNode) node.getUmlModel());
            
        }
    }
    
    /**
     * checkPartitionDirection
     *   void
     */
    private void checkPartitionDirection() {
        IDialogSettings dialogSetting = ActivityDiagramUtil.getDialogSetting();
        String partitionDirection = AnalysisConstant.EMPTY_STRING;
        
        partitionDirection = dialogSetting.get(AnalysisConstant.DIRECTION);
        if( null == partitionDirection ) {
            isHorizontal = false;
            dialogSetting.put(AnalysisConstant.DIRECTION, AnalysisConstant.VERTICAL);
        }
        if( AnalysisConstant.HORIZONTAL.equals(partitionDirection) ) {
            isHorizontal = true;
        } else if( AnalysisConstant.VERTICAL.equals(partitionDirection) ) {
            isHorizontal = false;
        }
    }
    
    /**
     * 노드의 위치와 사이즈 설정 void
     */
    private void setLocationAndSize() {

        Diagram diagram = null;
        if( parentEditPart.getModel() instanceof Diagram ) {
            diagram = (Diagram) parentEditPart.getModel();
        }
        
        if (NodeType.ACTIVITY_PARTITION.equals(containerNode.getNodeType())) {
            if ( null != diagram ) {
                
                List<AbstractNode> nodeList = diagram.getNodeList();
                List<ContainerNode> partitionList = new ArrayList<ContainerNode>();
                for ( AbstractNode node : nodeList ) {
                    if( NodeType.ACTIVITY_PARTITION.equals( node.getNodeType() ) ) {
                        partitionList.add((ContainerNode) node);
                    }
                }
                
                int widthSum = 0;
                int heightSum = 0;
                for (AbstractNode node : partitionList) {

                    if( partitionList.indexOf(node) == 0 ) {
                        node.setX(20);
                        node.setY(20);
                        widthSum += node.getWidth();
                        heightSum += node.getHeight();
                        continue;
                    }
                    
                    int oldX = node.getX();
                    int oldY = node.getY();
                    int newX = 0;
                    int newY = 0;
                    
                    if( isHorizontal ) { 
                        node.setX(20);
                        node.setY(20 + heightSum);
                    } else {
                        node.setX(20 + widthSum);
                        node.setY(20);
                    }
                    newX = node.getX();
                    newY = node.getY();
                    
                    widthSum += node.getWidth();
                    heightSum += node.getHeight();
                    
                    // partition x,y 좌표 이동 시 partition 내의 노드들의 위치도 옮겨준다.
                    if( node instanceof ContainerNode ) {
                        ContainerNode containerNode = (ContainerNode) node;
                        List<AbstractNode> activityNodeList = containerNode.getNodeList();
                        
                        for( AbstractNode child : activityNodeList ) {
                            
                            if( !(child instanceof NotationNode) ) {
                                continue;
                            }
                            NotationNode notationNode = (NotationNode) child;
                            notationNode.setX( notationNode.getX() + newX - oldX );
                            notationNode.setY( notationNode.getY() + newY - oldY );
                        }
                    }
                }
            }
        }
    }
}
