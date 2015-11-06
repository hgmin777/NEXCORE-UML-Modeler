/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttachementEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.model.LifeLineNameHeader;

import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.CombinedFragment;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : SequenceDiagramEditPartFactory</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramEditPartFactory extends DiagramEditPartFactory {

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    public EditPart createEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        if (viewModel instanceof NotationNode) {
            NodeType type = ((NotationNode) viewModel).getNodeType();
            if (NodeType.NOTE.equals(type)) {
                part = new NoteEditPart();
            } else if (NodeType.TEXT.equals(type)) {
                part = new TextEditPart();
            }
        } else if (viewModel instanceof Attachement) {
            part = new AttachementEditPart();
        } else if (viewModel instanceof LabelNode) {
            part = new LabelNodeEditPart();
        }
        if (null == part) {
            if (viewModel instanceof AbstractView) {
                part = this.createOwnEditPart(context, viewModel);
            } else if (viewModel instanceof LifeLineNameHeader) {
                part = new LifeLineNameHeaderEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                part = this.createUMLModelOwnEditPart(context, viewModel);
            }

        }
        if (null == part) {
            Log.error(UMLMessage.LABEL_NO_EDITPART_ASSIGNED_FOR + viewModel.toString());
        } else {
            part.setModel(viewModel);
        }
        return part;

    }

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    protected EditPart createOwnEditPart(EditPart context, Object viewModel) {

        EditPart part = null;

        if (viewModel instanceof Diagram) {
            part = new SequenceDiagramEditPart();
            part.setModel(viewModel);
            return part;
        } else if (viewModel instanceof AbstractNode) {
            NodeType type = ((AbstractNode) viewModel).getNodeType();
            if (NodeType.LIFELINE.equals(type)) {
                part = new LifeLineEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.LINE.equals(type)) {
                part = new LineEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.LIFE_LINE_BEHAVIOR.equals(type)) {
                part = new LifeLineBehaviorEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.INTERACTION_USE.equals(type)) {
                part = new InteractionUseEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.DESTRUCTION_EVENT.equals(type)) {
                part = new DestructionEventEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.NAME.equals(type)) {
                part = new LifeLineNameEditPart();
                part.setModel(viewModel);
                return part;             
            } else if(((AbstractNode) viewModel).getUmlModel() instanceof CombinedFragment) {
                //CombinedFragment 경우에는 NodeType이 다르지만 하나의 Editpart를 
                //사용하기 때문에 위와 같은 비교문을 사용한다.
                part = new CombinedFragmentEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.INTERACTION_OPERAND.equals(type)) {
                part = new InteractionOperandEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.GUARD.equals(type)) {
                part = new GuardEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                return part;
            }
        } else if (viewModel instanceof Attachement) {
            part = new AttachementEditPart();
            part.setModel(viewModel);
            return part;
        } else if (viewModel instanceof Relation) {
            RelationType type = ((Relation) viewModel).getRelationType();
            if (RelationType.ASYNCHRONOUS_MESSAGE.equals(type)) {
                part = new MessageEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.SYNCHRONOUS_MESSAGE.equals(type)) {
                part = new MessageEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.CREATE_MESSAGE.equals(type)) {
                part = new MessageEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.DESTROY_MESSAGE.equals(type)) {
                part = new MessageEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.REPLY_MESSAGE.equals(type)) {
                part = new MessageEditPart();
                part.setModel(viewModel);
                return part;
            } else {
                return part;
            }
        } else {
            return part;
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory#createUMLModelOwnEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    protected EditPart createUMLModelOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        if (viewModel instanceof NotationNode) {
            part = new LifeLineNameEditPart();
        }
        return part;
    }

}
