/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityDiagramEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.util.ActivityDiagramUtil;
import nexcore.tool.uml.ui.core.diagram.command.CreateContainerNodeCommand;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.util.UiUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : ActivityCreateContainerNodeCommand</li>
 * <li>작성일 : 2011. 2. 1.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityCreateContainerNodeCommand extends CreateContainerNodeCommand {

    /** PARTITION_DEFAULT_Y_LOCATION */
    private static final int PARTITION_DEFAULT_Y_LOCATION = 20;

    /** NAME_INDEX */
    private static int NAME_INDEX = 1;

    /** isHorizontal */
    private boolean isHorizontal = true;

    /** parentEditPart */
    private EditPart parentEditPart;
    
    /** diagram */
    private Diagram diagram;

    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     * @param location
     */
    public ActivityCreateContainerNodeCommand(EditDomain editDomain, EditPart parentEditPart, CreateRequest request,
    Point location) {
        super(editDomain, parentEditPart.getModel(), request, location);
        this.parentEditPart = parentEditPart;
        this.parentNodeModel = (AbstractNode) parentEditPart.getModel();
        if( this.parentNodeModel instanceof Diagram ) {
            this.diagram = (Diagram) this.parentNodeModel;
        }

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
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        UiUtil.eraseFeedback(parentEditPart);
        
        try {

            if( request.getNewObject() instanceof ContainerNode ) {
                containerNode = (ContainerNode) request.getNewObject();
            }
            if( null == containerNode ) {
                return;
            }
            
            setLocationAndSize();

            Element umlModel = containerNode.getUmlModel();
            PackageableElement parentElement = null;

            if (umlModel instanceof NamedElement) {
                if (umlModel instanceof org.eclipse.uml2.uml.ActivityPartition
                    || umlModel instanceof org.eclipse.uml2.uml.ActivityNode
                    || umlModel instanceof org.eclipse.uml2.uml.Action) {
                    if (this.parentNodeModel instanceof ContainerNode) {
                        parentElement = (PackageableElement) this.parentNodeModel.getParent();
                    } else {
                        parentElement = UMLManager.getParent(this.parentNodeModel);
                    }
                    setAllPartitionHeight();
                    shiftActivityNode();

                } else if (umlModel instanceof Lifeline) {
                    parentElement = UMLManager.getParent(this.parentNodeModel);

                } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
                    && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
                    parentElement = UMLManager.getParentPackage(this.parentNodeModel);
                    ((NamedElement) umlModel).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
                        ((NamedElement) umlModel).getName()));
                }

                if (isNamedActivity(umlModel)) {
                    if (null == parentElement) {
                        parentElement = UMLManager.getParent(this.parentNodeModel);
                    }
                    if (parentElement instanceof Activity) {
                        ((NamedElement) umlModel).setName(getPackagedUniqueName((Activity) parentElement,
                            ((NamedElement) umlModel).getName()));
                    }
                }
                containerNode.setName(((NamedElement) umlModel).getName());
            }
            redo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 액티비티 파티션이 생성될 자리에 다른 액티비티 노드가 있다면 각 노드들을 파티션이 없는 위치로 Shift 한다. void
     */
    private void shiftActivityNode() {

        // 생성될 파티션 이전의 가장 오른쪽 파티션의 Bounds를 가져온다.
        Rectangle bounds = new Rectangle(containerNode.getX(), containerNode.getY(), containerNode.getWidth(), containerNode.getHeight() );

        List<NotationNode> activityNodeList = new ArrayList<NotationNode>();
        if (null != this.diagram) {
            
            List<AbstractNode> nodeList = this.diagram.getNodeList();
            for (AbstractNode abstractNode : nodeList) {
                if ((abstractNode instanceof NotationNode)) {
                    NotationNode node = (NotationNode) abstractNode;
                    Point nodeLocation = new Point(node.getX(), node.getY());
                    if (bounds.contains(nodeLocation)) {
                        activityNodeList.add(node);
                    }
                }
            }
        }
        for (NotationNode node : activityNodeList) {
            this.diagram.getNodeList().remove(node);
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
     * 생성하는 파티션의 크기를 다른 파티션들의 크기와 동일하게 맞춘다. void
     */
    private void setAllPartitionHeight() {
        int maxWidth = 0;
        int maxHeight = 0;
        if ( null != this.diagram ) {
            
            List<AbstractNode> nodeList = this.diagram.getNodeList();
            for (AbstractNode node : nodeList) {
                if (node instanceof ContainerNode) {
                    ContainerNode partition = (ContainerNode) node;
                    if (maxHeight < partition.getHeight()) {
                        maxHeight = partition.getHeight();
                    }
                    if (maxWidth < partition.getWidth()) {
                        maxWidth = partition.getWidth();
                    }
                }
            }
            for (AbstractNode node : nodeList) {
                if (node instanceof ContainerNode) {
                    
                    if ( isHorizontal ) {
                        node.setWidth(maxWidth);
                    } else {
                        node.setHeight(maxHeight);
                    }
                }
            }
        }
        
        if ( isHorizontal ) {
            if (maxWidth != 0) {
                containerNode.setWidth(maxWidth);
            }
            
        } else {
            if (maxHeight != 0) {
                containerNode.setHeight(maxHeight);
            }
        }
    }

    /**
     * 
     * 
     * @param umlModel
     * @return boolean
     */
    private boolean isNamedActivity(Element umlModel) {
        if (umlModel instanceof OpaqueAction || umlModel instanceof CentralBufferNode
            || umlModel instanceof DataStoreNode || umlModel instanceof ActivityPartition) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
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
            if (null == parentPackage.getPartition(newName)) {
                return newName;
            }
            if (10000 <= index) {
                return name + Integer.toString(NAME_INDEX++);
            }
        }

    }

    /**
     * 노드의 위치와 사이즈 설정 void
     */
    private void setLocationAndSize() {

        containerNode.setX(location.x);
        containerNode.setY(location.y);

        if (NodeType.ACTIVITY_PARTITION.equals(containerNode.getNodeType())) {

            containerNode.setX(20);
            containerNode.setY(20);
            
            if ( null != this.diagram ) {
                
                List<AbstractNode> nodeList = this.diagram.getNodeList();
                
                int widthSum = 0;
                int heightSum = 0;
                for (AbstractNode node : nodeList) {
                    if (NodeType.ACTIVITY_PARTITION.equals(node.getNodeType())) {

                        widthSum += node.getWidth();
                        heightSum += node.getHeight();
                        
                        if( isHorizontal ) { 
                            containerNode.setX(20);
                            containerNode.setY(20 + heightSum);
                        } else {
                            containerNode.setX(20 + widthSum);
                            containerNode.setY(20);
                        }
                    }
                }
            }
        }
        
        if ( null == this.parentEditPart) {
            return;
        }
        if ( !(this.parentEditPart instanceof ActivityDiagramEditPart) ) {
            return;
        }
        ActivityDiagramEditPart activityDiagramEditPart = (ActivityDiagramEditPart) this.parentEditPart;
        IFigure figure = activityDiagramEditPart.getFigure();
        
        if ( null == figure ) {
            return;
        }
        Dimension bounds = new Dimension( figure.getBounds().width - 40, figure.getBounds().height - 40 );
        

        if( isHorizontal ) { 
            Dimension dimension = new Dimension(bounds.width, FigureConstant.FIGURE_HORIZONTAL_ACTIVITYPARTITION_HEIGHT);
            containerNode.setWidth(dimension.width);
            containerNode.setHeight(dimension.height);
        } else {
            Dimension dimension = new Dimension(FigureConstant.FIGURE_VERTICAL_ACTIVITYPARTITION_WIDTH, bounds.height);
            containerNode.setWidth(dimension.width);
            containerNode.setHeight(dimension.height);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        // Element diagramModel = this.parentNodeModel.getUmlModel();
        Element umlModel = containerNode.getUmlModel();
        PackageableElement parentElement = null;
        if (umlModel instanceof org.eclipse.uml2.uml.ActivityPartition) {

            if (this.parentNodeModel instanceof ContainerNode) {
                parentElement = (PackageableElement) this.parentNodeModel.getParent();
            } else {
                parentElement = UMLManager.getParent(this.parentNodeModel);
            }
            Activity activity = (Activity) parentElement;
            activity.getPartitions().add((ActivityPartition) umlModel);

        } else if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode
            || umlModel instanceof org.eclipse.uml2.uml.Action) {

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
        containerNode.setParent(parentElement);
        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(containerNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(containerNode);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Element umlModel = containerNode.getUmlModel();

        if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode || umlModel instanceof org.eclipse.uml2.uml.Action) {
            Activity activity = (Activity) containerNode.getParent();
            activity.getNodes().remove(umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            org.eclipse.uml2.uml.Package parentElement = (org.eclipse.uml2.uml.Package) containerNode.getParent();
            parentElement.getPackagedElements().remove(umlModel);
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().remove(containerNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().remove(containerNode);
        }
    }

}
