/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.classdiagram.edit.part;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ArtifactEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AssociationEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ClassEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ComponentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DependencyEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.edit.part.InterfaceEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.PackageEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.RealizationEditPart;
import nexcore.tool.uml.ui.realization.usecasediagram.edit.part.ActorEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Component;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.edit.part</li>
 * <li>설 명 : ClassDiagramEditPartFactory</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ClassDiagramEditPartFactory extends DiagramEditPartFactory {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory#createOwnEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    protected EditPart createOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        AbstractDiagramEditPart diagramEditPart = null;

        if (viewModel instanceof Diagram) {
            part = new ClassDiagramEditPart();

        } else if (viewModel instanceof NotationNode) {

            diagramEditPart = (AbstractDiagramEditPart) context;
            NodeType type = ((NotationNode) viewModel).getNodeType();

            if (NodeType.PACKAGE.equals(type)) {
                part = new PackageEditPart();
            } else if (NodeType.ACTOR.equals(type)) {
                part = new ActorEditPart();
            } else if (NodeType.CLASS.equals(type)) {
                part = new ClassEditPart();
            } else if (NodeType.ARTIFACT.equals(type)) {
                part = new ArtifactEditPart();
            } else if (NodeType.COLLABORATION.equals(type)) {
                part = new CollaborationEditPart();
            } else if (NodeType.INTERFACE.equals(type)) {
                part = new InterfaceEditPart();
            } else if (NodeType.DATA_TYPE.equals(type)) {
                part = new DataTypeEditPart();
            } else if (NodeType.ENUMERATION.equals(type)) {
                part = new EnumerationEditPart();
            } else if (NodeType.COMPONENT.equals(type)) {
                part = new ComponentEditPart();
            }
            diagramEditPart.setEditPart((AbstractView) viewModel, part);
        }

        else if (viewModel instanceof Relation) {

            diagramEditPart = (AbstractDiagramEditPart) context.getParent();
            RelationType type = ((Relation) viewModel).getRelationType();

            if (RelationType.ASSOCIATION.equals(type) || RelationType.DIRECTED_ASSOCIATION.equals(type)
                || RelationType.DIRECTED_AGGREGATION.equals(type) || RelationType.AGGREGATION.equals(type)
                || RelationType.DIRECTED_COMPOSITION.equals(type) || RelationType.COMPOSITION.equals(type)) {
                part = new AssociationEditPart();

            } else if (RelationType.DEPENDENCY.equals(type) || RelationType.ABSTRACTION.equals(type)
                || RelationType.USAGE.equals(type)) {
                part = new DependencyEditPart();

            } else if (RelationType.GENERALIZATION.equals(type)) {
                part = new GeneralizationEditPart();

            } else if (RelationType.REALIZATION.equals(type) || RelationType.INTERFACE_REALIZATION.equals(type)) {
                part = new RealizationEditPart();
            }
            diagramEditPart.setEditPart((AbstractView) viewModel, part);
        }

        return part;
    }

}
