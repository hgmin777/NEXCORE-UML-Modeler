/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.usecasediagram.edit.part;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.edit.part.DependencyEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.edit.part.PackageEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.RealizationEditPart;
import nexcore.tool.uml.ui.realization.classdiagram.edit.part.CollaborationEditPart;
import nexcore.tool.uml.ui.realization.classdiagram.edit.part.GeneralizationEditPart;

import org.eclipse.gef.EditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.edit.part</li>
 * <li>설  명 : UseCaseDiagramEditPartFactory</li>
 * <li>작성일 : 2009. 11. 13.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class UseCaseDiagramEditPartFactory extends DiagramEditPartFactory {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.DiagramEditPartFactory#createOwnEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @Override
    protected EditPart createOwnEditPart(EditPart context, Object viewModel) {
        EditPart part = null;
        if (viewModel instanceof Diagram) {
            part = new UseCaseDiagramEditPart();
            part.setModel(viewModel);
            return part;
        }

        else if (viewModel instanceof NotationNode) {
            NodeType type = ((NotationNode) viewModel).getNodeType();
            if (NodeType.ACTOR.equals(type)) {
                part = new ActorEditPart();
                return part;
            } else if (NodeType.PACKAGE.equals(type)) {
                part = new PackageEditPart();
                return part;
            } else if (NodeType.USE_CASE.equals(type)) {
                part = new UseCaseEditPart();
                return part;
            } else if (NodeType.COLLABORATION.equals(type)) {
                part = new CollaborationEditPart();
                return part;
            } else {
                return part;
            }
        }

        else if (viewModel instanceof Relation) {

            RelationType type = ((Relation) viewModel).getRelationType();
            if (RelationType.ASSOCIATION.equals(type) || RelationType.DIRECTED_AGGREGATION.equals(type)
                || RelationType.AGGREGATION.equals(type) || RelationType.DIRECTED_COMPOSITION.equals(type)) {
                part = new UsecaseAssociationEditPart();

            } else if (RelationType.DIRECTED_ASSOCIATION.equals(type)) {
                part = new UsecaseAssociationEditPart();
            } else if (RelationType.DEPENDENCY.equals(type)) {
                part = new DependencyEditPart();
            } else if (RelationType.EXTEND.equals(type)) {
                part = new ExtendEditPart();
            } else if (RelationType.INCLUDE.equals(type)) {
                part = new IncludeEditPart();
            } else if (RelationType.GENERALIZATION.equals(type)) {
                part = new GeneralizationEditPart();
            } else if (RelationType.REALIZATION.equals(type)) {
                part = new RealizationEditPart();
            }

        }

        return part;
    }
}
