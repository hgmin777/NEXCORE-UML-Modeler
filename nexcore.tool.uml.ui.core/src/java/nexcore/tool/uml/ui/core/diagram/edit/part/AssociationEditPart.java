/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.diagram.figure.ArrowDecoration;
import nexcore.tool.uml.ui.core.diagram.figure.RhombeArrowDecoration;
import nexcore.tool.uml.ui.core.diagram.figure.RhombeDecoration;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.edit.part</li>
 * <li>설 명 : AssociationEditPart</li>
 * <li>작성일 : 2010. 1. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : AssociationEditPart</li>
 * <li>작성일 : 2010. 1. 7.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class AssociationEditPart extends AbstractDiagramConnectionEditPart {

    /** relation */
    private Relation relation;

    /** relationType */
    // private RelationType relationType;
    /** sourceProperty */
    private Property sourceProperty;

    /** targetProperty */
    private Property targetProperty;

    /** source */
    // private Element source;
    // private Element target;
    /** sourceHasArrow */
    private boolean sourceHasArrow;

    /** targetHasArrow */
    private boolean targetHasArrow;

    /** sourceAggregationKind */
    private AggregationKind sourceAggregationKind;

    /** targetAggregationKind */
    private AggregationKind targetAggregationKind;

    /** association */
    private org.eclipse.uml2.uml.Association association;

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        settingLineCondition();

        PolylineConnection polylineConnection = new PolylineConnection() {
            @Override
            public void paintFigure(Graphics graphics) {
                graphics.setAntialias(SWT.ON);
                super.paintFigure(graphics);
            }
        };
        polylineConnection.addFigureListener(new FigureListener() {

            /**
             * @see org.eclipse.draw2d.FigureListener#figureMoved(org.eclipse.draw2d.IFigure)
             */
            @SuppressWarnings("unchecked")
            public void figureMoved(IFigure source) {
                Point sourcePoint = ((PolylineConnection) source).getStart();
                Point targetPoint = ((PolylineConnection) source).getEnd();

                RootEditPart rootEditPart = (RootEditPart) getParent();
                List<EditPart> diagramEditparts = new ArrayList<EditPart>();
                diagramEditparts = rootEditPart.getChildren();
                List<EditPart> editParts = new ArrayList<EditPart>();

                for (EditPart diagramEditPart : diagramEditparts) {
                    editParts = diagramEditPart.getChildren();
                    for (EditPart editpart : editParts) {
                        if (editpart.getModel() instanceof LabelNode) {
                            if (((LabelNode) editpart.getModel()).getOwner() == getModel()) {
                                if (editpart instanceof LabelNodeEditPart) {
                                    ((LabelNodeEditPart) editpart).setConnectionAnchorPoints(sourcePoint, targetPoint);
                                }
                            }

                        }
                    }
                }
            }
        });

        return drawDecoration(polylineConnection);

    }

    /**
     * 
     * void
     */
    private void settingLineCondition() {

        relation = ((Relation) getModel());
        association = (org.eclipse.uml2.uml.Association) relation.getUmlModel();

        if (association.getMemberEnds().size() <= 1 || null == ((NotationNode) relation.getSource()).getUmlModel()) {
            return;
        }
        if (((NotationNode) relation.getSource()).getUmlModel().equals(association.getMemberEnds().get(0).getType())) {
            sourceProperty = association.getMemberEnds().get(1);
            targetProperty = association.getMemberEnds().get(0);
            // }
        } else if (((NotationNode) relation.getSource()).getUmlModel().equals(association.getMemberEnds()
            .get(1)
            .getType())) {
            sourceProperty = association.getMemberEnds().get(0);
            targetProperty = association.getMemberEnds().get(1);
            // }
        } else {
            sourceProperty = association.getMemberEnds().get(0);
            targetProperty = association.getMemberEnds().get(1);
        }

        sourceAggregationKind = AggregationKind.NONE_LITERAL;
        targetAggregationKind = AggregationKind.NONE_LITERAL;

//        if (sourceProperty == null) {}

        if (sourceProperty.isNavigable()) {
            targetHasArrow = true;
        } else {
            targetHasArrow = false;
        }
        if (targetProperty.isNavigable()) {
            sourceHasArrow = true;
        } else {
            sourceHasArrow = false;
        }

        sourceAggregationKind = (sourceProperty).getAggregation();
        targetAggregationKind = (targetProperty).getAggregation();
    }

    /**
     * 
     * 
     * @param connection
     * @return IFigure
     */
    private IFigure drawDecoration(PolylineConnection connection) {
        RhombeDecoration rhombe = new RhombeDecoration();
        ArrowDecoration arrow = new ArrowDecoration();
        RhombeArrowDecoration rhombeArrow = new RhombeArrowDecoration();

        if (association.getMemberEnds().size() <= 1) {
            return connection;
        }

        for (int i = 0; i < connection.getChildren().size(); i++) {
            if (!(connection.getChildren().get(i) instanceof Label)) {
                connection.getChildren().remove(i);
            }
        }

        if (sourceHasArrow && !targetHasArrow) {
            if (!sourceAggregationKind.equals(AggregationKind.NONE_LITERAL)) {
                if (sourceAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombeArrow.setBackgroundColor(ColorConstants.white);
                } else if (sourceAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombeArrow.setBackgroundColor(ColorConstants.black);
                }
                connection.setSourceDecoration(rhombeArrow);

            } else if (!targetAggregationKind.equals(AggregationKind.NONE_LITERAL)) {

                if (targetAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.white);
                } else if (targetAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.black);
                }
                connection.setTargetDecoration(rhombe);
                connection.setSourceDecoration(arrow);
            } else {
                connection.setSourceDecoration(arrow);
            }
        } else if (targetHasArrow && !sourceHasArrow) {

            if (!sourceAggregationKind.equals(AggregationKind.NONE_LITERAL)) {
                if (sourceAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.white);
                } else if (sourceAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.black);
                }
                connection.setSourceDecoration(rhombe);
                connection.setTargetDecoration(arrow);

            } else if (!targetAggregationKind.equals(AggregationKind.NONE_LITERAL)) {
                if (targetAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombeArrow.setBackgroundColor(ColorConstants.white);
                } else if (targetAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombeArrow.setBackgroundColor(ColorConstants.black);
                }
                connection.setTargetDecoration(rhombeArrow);
            }

            else {
                connection.setTargetDecoration(arrow);
            }
        }

        else {
            if (!sourceAggregationKind.equals(AggregationKind.NONE_LITERAL)) {
                if (sourceAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.white);
                } else if (sourceAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.black);
                }
                connection.setSourceDecoration(rhombe);

            } else if (!targetAggregationKind.equals(AggregationKind.NONE_LITERAL)) {
                if (targetAggregationKind.equals(AggregationKind.SHARED_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.white);
                } else if (targetAggregationKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                    rhombe.setBackgroundColor(ColorConstants.black);
                }
                connection.setTargetDecoration(rhombe);
            }
        }

        connection.addRoutingListener(RoutingAnimator.getDefault());
        connection.setConnectionRouter(new BendpointConnectionRouter());

        return connection;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#refreshVisuals()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            PolylineConnection connection = (PolylineConnection) getFigure();
            settingLineCondition();
            IFigure figure = drawDecoration(connection);
            figure.repaint();

            RootEditPart rootEditPart = (RootEditPart) getParent();
            List<EditPart> diagramEditparts = new ArrayList<EditPart>();
            diagramEditparts = rootEditPart.getChildren();

            Property sourceLabelProperty = null;
            Property targetLabelProperty = null;
            Property property = null;
            Element element;
            List<EditPart> editParts = new ArrayList<EditPart>();

            for (EditPart diagramEditPart : diagramEditparts) {

                editParts = diagramEditPart.getChildren();

                for (EditPart editpart : editParts) {
                    if (editpart instanceof LabelNodeEditPart) {

                        element = ((LabelNode) ((LabelNodeEditPart) editpart).getModel()).getUmlModel();

                        if (element instanceof Association) {
                            association = (Association) element;
                        } else if (element instanceof Property) {
                            property = (Property) element;

                            if (sourceProperty.equals(property)) {
                                sourceLabelProperty = property;
                            } else if (targetProperty.equals(property)) {
                                targetLabelProperty = property;
                            }
                        }
                    }

                }

                for (EditPart editpart : editParts) {
                    if (editpart instanceof LabelNodeEditPart) {
                        element = ((LabelNode) ((LabelNodeEditPart) editpart).getModel()).getUmlModel();

                        if (element instanceof Association) {
                            association = (Association) element;
                        } else if (element instanceof Property) {
                            property = (Property) element;

                            if (sourceProperty.equals(property)) {
                                if (sourceLabelProperty.isNavigable()) {
                                    ((LabelNodeEditPart) editpart).setLabelVisibility(true);
                                } else {
                                    ((LabelNodeEditPart) editpart).setLabelVisibility(false);
                                }
                            } else if (targetProperty.equals(property)) {
                                if (targetLabelProperty.isNavigable()) {
                                    ((LabelNodeEditPart) editpart).setLabelVisibility(true);
                                } else {
                                    ((LabelNodeEditPart) editpart).setLabelVisibility(false);
                                }
                            }
                            // ((LabelNodeEditPart) editpart).refresh();
                        }
                    }
                }
            }
            super.refreshBendpoints();
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_ASSOCIATION_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }
}
