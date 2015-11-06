/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.LabeledRoundedRectangle;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : ConnectionLabelEditPart</li>
 * <li>작성일 : 2011. 2. 10.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ConnectionLabelEditPart extends AbstractLabelCompartmentEditPart {

    /** currentLocation */
    private Point currentLocation = new Point();

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        LabelNode labelNode = (LabelNode) getModel();
        AbstractConnection abstractConnection = (AbstractConnection) labelNode.getOwner();
        String connectionName = ((NamedElement) labelNode.getUmlModel()).getName();
        String connectionStereotype = ProjectUtil.getStereotypeLabel((NamedElement) labelNode.getUmlModel());

        if (labelNode.getUmlModel() instanceof Property) {
            if (((Property) labelNode.getUmlModel()).isNavigable()) {
                labelVisibility = true;
            } else {
                labelVisibility = false;
            }
        } else if (labelNode.getUmlModel() instanceof ControlFlow) {
            labelVisibility = true;
        } else if (labelNode.getUmlModel() instanceof ObjectFlow) {
            labelVisibility = true;
        } else {
            labelVisibility = false;
        }

        if (labelNode.getType().equals(LabelType.LABEL)) {
            LabeledRoundedRectangle label = new LabeledRoundedRectangle();
            label.setText(connectionName, connectionStereotype);
            label.setBackgroundColor(ColorConstants.yellow);
            label.setBorder(new MarginBorders(0, 0, 0, 0));

            Point point;
            if (sourceAnchorPoint != null) {
                point = new Point(sourceAnchorPoint);
            } else {
                point = new Point(abstractConnection.getSourceAnchor().getWidth(), abstractConnection.getSourceAnchor()
                    .getHeight());
            }
            label.setLocation(new Point(point.x + labelNode.getX(), point.y + labelNode.getY()));
            currentLocation = new Point(point.x + labelNode.getX(), point.y + labelNode.getY());

            return label;
        } else if (labelNode.getType().equals(LabelType.SOURCE_ROLE)
            || labelNode.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
            Label label = new Label();
            label.setSize(labelNode.getWidth(), labelNode.getHeight());
            label.setText(labelNode.getName());

            Point point;
            if (sourceAnchorPoint != null) {
                point = new Point(sourceAnchorPoint);
            } else {
                point = new Point(abstractConnection.getSourceAnchor().getWidth(), abstractConnection.getSourceAnchor()
                    .getHeight());
            }
            label.setLocation(new Point(point.x + labelNode.getX(), point.y + labelNode.getY()));
            currentLocation = new Point(point.x + labelNode.getX(), point.y + labelNode.getY());

            label.setVisible(labelVisibility);
            return label;
        } else if (labelNode.getType().equals(LabelType.TARGET_ROLE)
            || labelNode.getType().equals(LabelType.TARGET_MULTIPLEX)) {
            Label label = new Label();
            label.setSize(labelNode.getWidth(), labelNode.getHeight());
            label.setText(labelNode.getName());
            Point point;
            if (sourceAnchorPoint != null) {
                point = new Point(sourceAnchorPoint);
            } else {
                point = new Point(abstractConnection.getTargetAnchor().getWidth(), abstractConnection.getTargetAnchor()
                    .getHeight());
            }
            label.setLocation(new Point(point.x + labelNode.getX(), point.y + labelNode.getY()));
            currentLocation = new Point(point.x + labelNode.getX(), point.y + labelNode.getY());

            label.setVisible(labelVisibility);
            return label;
        }

        return null;
    }

    /** sourceAnchorPoint */
    private Point sourceAnchorPoint;

    /** targetAnchorPoint */
    private Point targetAnchorPoint;

    /**
     * setConnectionAnchorPoints
     *  
     * @param sourceAnchorPoint
     * @param targetAnchorPoint void
     */
    public void setConnectionAnchorPoints(Point sourceAnchorPoint, Point targetAnchorPoint) {
        this.sourceAnchorPoint = sourceAnchorPoint;
        this.targetAnchorPoint = targetAnchorPoint;

        refreshVisuals();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractLabelCompartmentEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            LabelNode LabelNode = (LabelNode) getModel();
            AbstractConnection abstractConnection = (AbstractConnection) LabelNode.getOwner();
            String connectionName = ((NamedElement) LabelNode.getUmlModel()).getName();
            String connectionStereotype = ProjectUtil.getStereotypeLabel((NamedElement) LabelNode.getUmlModel());

            if (LabelNode.getType().equals(LabelType.LABEL)) {
                LabeledRoundedRectangle label = (LabeledRoundedRectangle) getFigure();

                if (connectionName == null)
                    connectionName = "";
                int nameWidth = DiagramUtil.getWidthSize(connectionName, UiCorePlugin.getDefault().getFont("default"));
                int stereotypelWidth = DiagramUtil.getWidthSize(connectionStereotype, UiCorePlugin.getDefault()
                    .getFont("default"));
                // if ((abstractConnection.getUmlModel() instanceof Extend
                // || abstractConnection.getUmlModel() instanceof Include
                // || abstractConnection.getUmlModel() instanceof Usage
                // || abstractConnection.getUmlModel() instanceof Dependency)
                // && !(abstractConnection.getUmlModel() instanceof
                // Realization)) {
                //            
                // }
                label.setSize(nameWidth > stereotypelWidth ? nameWidth : stereotypelWidth, LabelNode.getHeight());
                label.setText(connectionName, connectionStereotype);

                // IPreferenceStore store =
                // PreferenceUtil.INSTANCE.getPreferenceStore();
                // String showLabel =
                // store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_NAMEINDICATIONTYPE);
                // if(showLabel.equals(UICoreConstant.NAMEINDICATIONTYPE_BOOLEAN[1])
                // ||
                // showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__EMPTY_TEXT)){
                // label.setText(connectionName, connectionStereotype);
                // }
                // else{
                // label.setText("", "");
                // }

                Point point;
                if (sourceAnchorPoint != null) {
                    point = new Point();
                    if (sourceAnchorPoint.x < targetAnchorPoint.x) {
                        point.x = sourceAnchorPoint.x + (int) ((targetAnchorPoint.x - sourceAnchorPoint.x) / 2);
                    } else {
                        point.x = targetAnchorPoint.x + (int) ((sourceAnchorPoint.x - targetAnchorPoint.x) / 2);
                    }

                    if (sourceAnchorPoint.x < targetAnchorPoint.y) {
                        point.y = sourceAnchorPoint.y + (int) ((targetAnchorPoint.y - sourceAnchorPoint.y) / 2);
                    } else {
                        point.y = targetAnchorPoint.y + (int) ((sourceAnchorPoint.y - targetAnchorPoint.y) / 2);
                    }

                } else {
                    Point sourcePoint = new Point(abstractConnection.getSourceAnchor().getWidth(),
                        abstractConnection.getSourceAnchor().getHeight());
                    Point targetPoint = new Point(abstractConnection.getTargetAnchor().getWidth(),
                        abstractConnection.getTargetAnchor().getHeight());

                    point = new Point();
                    if (sourcePoint.x < targetPoint.x) {
                        point.x = sourcePoint.x + (int) ((targetPoint.x - sourcePoint.x) / 2);
                    } else {
                        point.x = targetPoint.x + (int) ((sourcePoint.x - targetPoint.x) / 2);
                    }

                    if (sourcePoint.x < targetPoint.y) {
                        point.y = sourcePoint.y + (int) ((targetPoint.y - sourcePoint.y) / 2);
                    } else {
                        point.y = targetPoint.y + (int) ((sourcePoint.y - targetPoint.y) / 2);
                    }
                }

                label.setLocation(new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY()));
                currentLocation = new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY());
                label.reSize();
                setPreference(LabelNode);

            } else if (LabelNode.getType().equals(LabelType.SOURCE_ROLE)
                || LabelNode.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
                Label label = (Label) getFigure();
                if (LabelNode.getType().equals(LabelType.SOURCE_ROLE)) {
                    VisibilityKind visibilityKind = ((Property) LabelNode.getUmlModel()).getVisibility();
                    if (visibilityKind == VisibilityKind.PUBLIC_LITERAL) {
                        label.setText("+" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PRIVATE_LITERAL) {
                        label.setText("-" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PROTECTED_LITERAL) {
                        label.setText("#" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PACKAGE_LITERAL) {
                        label.setText("~" + ((Property) LabelNode.getUmlModel()).getName());
                    }
                } else if (LabelNode.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
                    int upper, lower;
                    upper = ((Property) LabelNode.getUmlModel()).getUpper();
                    lower = ((Property) LabelNode.getUmlModel()).getLower();
                    label.setText(getMultiplex(upper, lower));
                }
                int connectionWidthSize = DiagramUtil.getWidthSize(label.getText(), UiCorePlugin.getDefault()
                    .getFont("default"));
                label.setSize(connectionWidthSize, LabelNode.getHeight());
                Point point;
                if (sourceAnchorPoint != null) {
                    point = new Point(sourceAnchorPoint);
                } else {
                    point = new Point(abstractConnection.getSourceAnchor().getWidth(),
                        abstractConnection.getSourceAnchor().getHeight());
                }
                label.setLocation(new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY()));
                currentLocation = new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY());

                label.setVisible(labelVisibility);
                setPreference(LabelNode);
            } else if (LabelNode.getType().equals(LabelType.TARGET_ROLE)
                || LabelNode.getType().equals(LabelType.TARGET_MULTIPLEX)) {
                Label label = (Label) getFigure();

                if (LabelNode.getType().equals(LabelType.TARGET_ROLE)) {
                    VisibilityKind visibilityKind = ((Property) LabelNode.getUmlModel()).getVisibility();
                    if (visibilityKind == VisibilityKind.PUBLIC_LITERAL) {
                        label.setText("+" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PRIVATE_LITERAL) {
                        label.setText("-" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PROTECTED_LITERAL) {
                        label.setText("#" + ((Property) LabelNode.getUmlModel()).getName());
                    } else if (visibilityKind == VisibilityKind.PACKAGE_LITERAL) {
                        label.setText("~" + ((Property) LabelNode.getUmlModel()).getName());
                    }
                } else if (LabelNode.getType().equals(LabelType.TARGET_MULTIPLEX)) {
                    int upper, lower;
                    upper = ((Property) LabelNode.getUmlModel()).getUpper();
                    lower = ((Property) LabelNode.getUmlModel()).getLower();
                    label.setText(getMultiplex(upper, lower));
                }

                int connectionWidthSize = DiagramUtil.getWidthSize(label.getText(), UiCorePlugin.getDefault()
                    .getFont("default"));
                label.setSize(connectionWidthSize, LabelNode.getHeight());
                Point point;
                if (targetAnchorPoint != null) {
                    point = new Point(targetAnchorPoint);
                } else {
                    point = new Point(abstractConnection.getTargetAnchor().getWidth(),
                        abstractConnection.getTargetAnchor().getHeight());
                }
                label.setLocation(new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY()));
                currentLocation = new Point(point.x + LabelNode.getX(), point.y + LabelNode.getY());

                label.setVisible(labelVisibility);
                setPreference(LabelNode);
            }

        } catch (Exception e) {
            Log.error("LabelNodeEditPart refreshVisuals() Error " + e);
        }

    }

    /**
     * setPreference
     *  
     * @param LabelNode void
     */
    private void setPreference(LabelNode LabelNode) {
        if (LabelNode.getType().equals(LabelType.LABEL)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                getFigure().setVisible(true);
            } else {
                getFigure().setVisible(false);
            }
        } else if (LabelNode.getType().equals(LabelType.SOURCE_ROLE)
            || LabelNode.getType().equals(LabelType.TARGET_ROLE)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                if (labelVisibility) {
                    getFigure().setVisible(true);
                }
            } else {
                getFigure().setVisible(false);
            }
        } else if (LabelNode.getType().equals(LabelType.SOURCE_MULTIPLEX)
            || LabelNode.getType().equals(LabelType.TARGET_MULTIPLEX)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                if (labelVisibility) {
                    getFigure().setVisible(true);
                }
            } else {
                getFigure().setVisible(false);
            }
        }
    }

    /**
     * getCurrentLocation
     *  
     * @return Point
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /** labelVisibility */
    private boolean labelVisibility = true;

    /**
     * isLabelVisibility
     *  
     * @return boolean
     */
    public boolean isLabelVisibility() {
        return labelVisibility;
    }

    /**
     * setLabelVisibility
     *  
     * @param labelVisibility void
     */
    public void setLabelVisibility(boolean labelVisibility) {
        this.labelVisibility = labelVisibility;
    }

    /**
     * 
     * 
     * @param upper
     * @param lower
     * @return String
     */
    private String getMultiplex(int upper, int lower) {
        String string;
        if (upper == 1) {
            if (lower == 1) {
                string = " 1 ";
            } else {
                string = "0 ... 1";
            }
        } else {
            if (lower == 1) {
                string = "1 ... *";
            } else {
                string = " * ";
            }
        }
        return string;
    }
}
