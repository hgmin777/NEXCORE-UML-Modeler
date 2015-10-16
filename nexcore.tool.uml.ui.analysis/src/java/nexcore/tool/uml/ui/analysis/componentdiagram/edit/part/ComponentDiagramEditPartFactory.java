/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.componentdiagram.edit.part;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.edit.part.ArtifactEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AssociationEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ClassEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ComponentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DependencyEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.edit.part.InterfaceEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.PackageEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.RealizationEditPart;

import org.eclipse.gef.EditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.componentdiagram.edit.part</li>
 * <li>설 명 : ComponentDiagramEditPartFactory</li>
 * <li>작성일 : 2011. 3. 4.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ComponentDiagramEditPartFactory extends DiagramEditPartFactory {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory#createOwnEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    @Override
    protected EditPart createOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;

        if (viewModel instanceof Diagram) {
            part = new ComponentDiagramEditPart();
            part.setModel(viewModel);
            return part;
        } else if (viewModel instanceof NotationNode) {
            NodeType type = ((NotationNode) viewModel).getNodeType();
            if (NodeType.COMPONENT.equals(type)) {
                part = new ComponentEditPart();
                part.setModel(viewModel);
                return part;

            } else if (NodeType.PACKAGE.equals(type)) {
                part = new PackageEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.INTERFACE.equals(type)) {
                part = new InterfaceEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.ARTIFACT.equals(type)) {
                part = new ArtifactEditPart();
                part.setModel(viewModel);
                return part;
            } else if (NodeType.CLASS.equals(type)) {
                part = new ClassEditPart();
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
            if (RelationType.USAGE.equals(type) || RelationType.DEPENDENCY.equals(type)
                || RelationType.ABSTRACTION.equals(type)) {
                part = new DependencyEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.INTERFACE_REALIZATION.equals(type)) {
                part = new RealizationEditPart();
                part.setModel(viewModel);
                return part;
            } else if (RelationType.ASSOCIATION.equals(type) || RelationType.DIRECTED_ASSOCIATION.equals(type)
                    || RelationType.DIRECTED_AGGREGATION.equals(type) || RelationType.AGGREGATION.equals(type)
                    || RelationType.DIRECTED_COMPOSITION.equals(type) || RelationType.COMPOSITION.equals(type)) {
                part = new AssociationEditPart();
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
