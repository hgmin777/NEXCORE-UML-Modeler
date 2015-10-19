/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.Relationship;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : LabelFeedbackEditPolicy</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class LabelFeedbackEditPolicy extends NonResizableEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#createDragSourceFeedbackFigure()
     */
    @Override
    protected IFigure createDragSourceFeedbackFigure() {

        LabelNode connectionLabel = (LabelNode) ((LabelNodeEditPart) getHost()).getModel();
        Relationship association = null;
        // = (Association) ((Relation)
        // connectionLabel.getOwner()).getUmlModel();

        if (((Relation) connectionLabel.getOwner()).getUmlModel() instanceof Association) {
            association = (Association) ((Relation) connectionLabel.getOwner()).getUmlModel();
        } else if (((Relation) connectionLabel.getOwner()).getUmlModel() instanceof Dependency) {
            association = (Dependency) ((Relation) connectionLabel.getOwner()).getUmlModel();
        } else if (((Relation) connectionLabel.getOwner()).getUmlModel() instanceof Include) {
            association = (Include) ((Relation) connectionLabel.getOwner()).getUmlModel();
        } else if (((Relation) connectionLabel.getOwner()).getUmlModel() instanceof Extend) {
            association = (Extend) ((Relation) connectionLabel.getOwner()).getUmlModel();
        } else if (((Relation) connectionLabel.getOwner()).getUmlModel() instanceof Realization) {
            association = (Realization) ((Relation) connectionLabel.getOwner()).getUmlModel();
        }

        //
        // ChopBox Anchor
        AbstractConnection relation = (AbstractConnection) connectionLabel.getOwner();

        RectangleFigure sFigure = new RectangleFigure();
        RectangleFigure tFigure = new RectangleFigure();
        if (relation.getSource() instanceof NotationNode || relation.getTarget() instanceof NotationNode) {
            NotationNode source = (NotationNode) relation.getSource();
            NotationNode target = (NotationNode) relation.getTarget();

            sFigure.setLocation(new Point(source.getX(), source.getY()));
            sFigure.setSize(source.getWidth(), source.getHeight());
            tFigure.setLocation(new Point(target.getX(), target.getY()));
            tFigure.setSize(target.getWidth(), target.getHeight());
        } else if (relation.getSource() instanceof ContainerNode || relation.getTarget() instanceof ContainerNode) {
            ContainerNode source = (ContainerNode) relation.getSource();
            ContainerNode target = (ContainerNode) relation.getTarget();

            sFigure.setLocation(new Point(source.getX(), source.getY()));
            sFigure.setSize(source.getWidth(), source.getHeight());
            tFigure.setLocation(new Point(target.getX(), target.getY()));
            tFigure.setSize(target.getWidth(), target.getHeight());
        }

        ChopboxAnchor sourceAnchor = new ChopboxAnchor(sFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(tFigure);
        Point sourceAnchorLocation = new Point();
        Point targetAnchorLocation = new Point();
        sourceAnchorLocation = sourceAnchor.getLocation(targetAnchor.getReferencePoint());
        targetAnchorLocation = targetAnchor.getLocation(sourceAnchor.getReferencePoint());
        // ChopBox Anchor
        //

        IFigure sourceFigure = null;

        PolylineConnection connection = new PolylineConnection();
        connection.setLineStyle(SWT.LINE_DOT);

        XYAnchor anchor = null;

        IFigure figure = super.createDragSourceFeedbackFigure();
        figure.setOpaque(false);
        figure.setBorder(new LineBorder(ColorConstants.black));

        if (figure != null) {
            sourceFigure = figure;
        } else {
            sourceFigure = new RoundedRectangle();
            sourceFigure.setSize(connectionLabel.getX(), connectionLabel.getY());
        }

        if (association instanceof Association) {

            Property sourceProperty = null;
            Property targetProperty = null;
            if (((NotationNode) relation.getSource()).getUmlModel().equals(((Association) association).getMemberEnds()
                .get(0)
                .getType())) {
                sourceProperty = ((Association) association).getMemberEnds().get(1);
                targetProperty = ((Association) association).getMemberEnds().get(0);
            } else if (((NotationNode) relation.getSource()).getUmlModel()
                .equals(((Association) association).getMemberEnds().get(1).getType())) {
                sourceProperty = ((Association) association).getMemberEnds().get(0);
                targetProperty = ((Association) association).getMemberEnds().get(1);
            }

            if (targetProperty.equals(connectionLabel.getUmlModel())) {
                anchor = new XYAnchor(sourceAnchorLocation);
            } else if (sourceProperty.equals(connectionLabel.getUmlModel())) {
                anchor = new XYAnchor(targetAnchorLocation);
            }
        }

        if (connectionLabel.getType().equals(LabelType.LABEL)) {

            Point centerPoint = new Point();
            if (sourceAnchorLocation.x < targetAnchorLocation.x) {
                centerPoint.x = sourceAnchorLocation.x + (int) ((targetAnchorLocation.x - sourceAnchorLocation.x) / 2);
            } else {
                centerPoint.x = targetAnchorLocation.x + (int) ((sourceAnchorLocation.x - targetAnchorLocation.x) / 2);
            }

            if (sourceAnchorLocation.x < targetAnchorLocation.y) {
                centerPoint.y = sourceAnchorLocation.y + (int) ((targetAnchorLocation.y - sourceAnchorLocation.y) / 2);
            } else {
                centerPoint.y = targetAnchorLocation.y + (int) ((sourceAnchorLocation.y - targetAnchorLocation.y) / 2);
            }

            anchor = new XYAnchor(centerPoint);
        }

        connection.setSourceAnchor(new ChopboxAnchor(sourceFigure));
        connection.setTargetAnchor(anchor);

        addFeedback(sourceFigure);
        addFeedback(connection);

        return sourceFigure;
    }

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        return null;
    }

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#showChangeBoundsFeedback(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if( null == getDragSourceFeedbackFigure()) {
            return;
        }
        super.showChangeBoundsFeedback(request);
    }
}
