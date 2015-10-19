/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.uml2.uml.Interface;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : DiagramEditPartFactory</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public abstract class DiagramEditPartFactory implements EditPartFactory {

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        if (viewModel instanceof LabelNode) {
            if (((LabelNode) viewModel).getType().equals(LabelType.LABEL)) {
                part = new CompartmentLabelNodeEditPart();
            } else if (((LabelNode) viewModel).getType().equals(LabelType.STEREOTYPE)) {
                part = new CompartmentLabelNodeEditPart();
            } else {
                part = new LabelNodeEditPart();
            }
        } else if (viewModel instanceof NotationNode) {
            NodeType type = ((NotationNode) viewModel).getNodeType();
            if (NodeType.NOTE.equals(type)) {
                part = new NoteEditPart();
            } else if (NodeType.TEXT.equals(type)) {
                part = new TextEditPart();
            } else {
                part = this.createUMLModelOwnEditPart(context, viewModel);
            }
        } else if (viewModel instanceof Attachement) {
            part = new AttachementEditPart();
        }

        if (null == part) {
            if (viewModel instanceof AbstractView) {
                part = this.createOwnEditPart(context, viewModel);
            }
        }
        if (null == part) {
            Log.error("No editpart assigned for " + viewModel.toString());
        } else {
            if (part.getModel() == null) {
                part.setModel(viewModel);
            }
            part.setParent(context);
        }
        return part;

    }

    /**
     * Factory for ViewModel
     * 
     * @param context
     * @param viewModel
     * @return EditPart
     */
    protected abstract EditPart createOwnEditPart(EditPart context, Object viewModel);

    /**
     * Factory for UMLModel
     * 
     * @param context
     * @param viewModel
     * @return EditPart
     */
    protected EditPart createUMLModelOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        NotationNode node = (NotationNode) viewModel;
        if (node.getNodeType().equals(NodeType.ATTRIBUTES)) {
            part = new AttributesEditPart();
        } else if (node.getNodeType().equals(NodeType.ATTRIBUTE)) {
            part = new AttributeEditPart();
        } else if (node.getNodeType().equals(NodeType.OPERATIONS)) {
            part = new OperationsEditPart();
        } else if (node.getNodeType().equals(NodeType.OPERATION)) {
            part = new OperationEditPart();
        } else if (node.getNodeType().equals(NodeType.NAME)) {
            part = new NotationNameEditPart();
        } else if (node.getNodeType().equals(NodeType.STEREOTYPE)) {
            part = new StereotypeEditPart();
        } else if (node.getNodeType().equals(NodeType.PROVIDED_INTERFACES)) {
            part = new ProvidedInterfacesEditPart();
        } else if (node.getNodeType().equals(NodeType.REQUIRED_INTERFACES)) {
            part = new RequiredInterfacesEditPart();
        } else if (node.getNodeType().equals(NodeType.PROVIDED_INTERFACE)) {
            part = new ProvidedInterfaceEditPart();
        } else if (node.getNodeType().equals(NodeType.REQUIRED_INTERFACE)) {
            part = new RequiredInterfaceEditPart();
        } else if (node.getNodeType().equals(NodeType.PROVIDED_INTERFACE_OPERATION)) {
            part = new ProvidedInterfaceOperationEditPart();
        } else if (node.getNodeType().equals(NodeType.REQUIRED_INTERFACE_OPERATION)) {
            part = new RequiredInterfaceOperationEditPart();
        } else if (viewModel instanceof Interface) {
            part = new CompartmentInterfaceEditPart();
        } else if (node.getNodeType().equals(NodeType.ENUMERATION_LITERAL)) {
            part = new EnumerationLiteralEditPart();
        } else if (node.getNodeType().equals(NodeType.ENUMERATION_LITERALS)) {
            part = new EnumerationLiteralsEditPart();
        }

        return part;
    }
}
