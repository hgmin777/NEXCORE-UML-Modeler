/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;

import org.eclipse.gef.EditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : ActivityDiagramEditPartFactory</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityDiagramEditPartFactory extends DiagramEditPartFactory {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory#createOwnEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    protected EditPart createOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;

        if (viewModel instanceof Diagram) {
            part = new ActivityDiagramEditPart();
            part.setModel(viewModel);
            return part;
        } else if (viewModel instanceof NotationNode) {
            NodeType type = ((NotationNode) viewModel).getNodeType();
            if (NodeType.INITIAL_NODE.equals(type)) {
                part = new InitialNodeEditPart();
                part.setModel(viewModel);
                return part;

            } else if (NodeType.ACTIVITY_FINAL_NODE.equals(type)) {
                part = new ActivityFinalNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.MERGE_NODE.equals(type)) {
                part = new MergeNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.FORK_NODE.equals(type)) {
                part = new ForkNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.JOIN_NODE.equals(type)) {
                part = new JoinNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.OPAQUE_ACTION.equals(type)) {
                part = new OpaqueActionEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.DECISION_NODE.equals(type)) {
                part = new DecisionNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.CENTRAL_BUFFER_NODE.equals(type)) {
                part = new CentralBufferNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.DATA_STORE_NODE.equals(type)) {
                part = new DataStoreNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                return part;
            }
        } else if (viewModel instanceof ContainerNode) {
            NodeType type = ((ContainerNode) viewModel).getNodeType();

            if (NodeType.ACTIVITY_PARTITION.equals(type)) {
                part = new ActivityPartitionEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                return part;
            }
        } else if (viewModel instanceof LabelNode) {

            LabelNode labelNode = (LabelNode) viewModel;
            if (labelNode.getType().equals(LabelType.COMPARTMENT)) {
                part = new LabelNodeEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                part = new CompartmentLabelNodeEditPart();
                part.setModel(viewModel);
                return part;

            }
        } else if (viewModel instanceof Relation) {
            RelationType type = ((Relation) viewModel).getRelationType();
            if (RelationType.CONTROL_FLOW.equals(type)) {
                part = new ControlFlowEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.OBJECT_FLOW.equals(type)) {
                part = new ObjectFlowEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                return part;
            }
        } else {
            return part;
        }
    }
}
