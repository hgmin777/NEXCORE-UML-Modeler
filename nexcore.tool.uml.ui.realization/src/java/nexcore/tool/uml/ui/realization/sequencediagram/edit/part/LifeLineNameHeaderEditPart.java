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
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.LifeLineNameHeaderFigure;
import nexcore.tool.uml.ui.realization.sequencediagram.model.LifeLineNameHeader;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : LifeLineNameHeaderEditPart</li>
 * <li>작성일 : 2010. 3. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineNameHeaderEditPart extends AbstractGraphicalEditPart implements NodeEditPart, Adapter {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        // super.createEditPolicies();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            EObject model = ((LifeLineNameHeader) getModel()).getLifeLine();
            model.eAdapters().add(this);
            model = ((AbstractNode) model).getUmlModel();
            if (null == model) {
                return;
            }
            model.eAdapters().add(this);
            if (model instanceof Lifeline && ((Lifeline) model).getRepresents() != null
                && ((Lifeline) model).getRepresents().getType() != null) {
                Type type = ((Lifeline) model).getRepresents().getType();
                type.eAdapters().add(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            EObject model = ((LifeLineNameHeader) getModel()).getLifeLine();
            model.eAdapters().remove(this);
            model = ((AbstractNode) model).getUmlModel();
            model.eAdapters().remove(this);
            if (model instanceof Lifeline && ((Lifeline) model).getRepresents() != null
                && ((Lifeline) model).getRepresents().getType() != null) {
                Type type = ((Lifeline) model).getRepresents().getType();
                type.eAdapters().remove(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {

        LifeLineNameHeader header = (LifeLineNameHeader) getModel();

        LifeLineNode lifeLineNode = header.getLifeLine();
        Lifeline lifeline = (Lifeline) lifeLineNode.getUmlModel();
        String strName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        if (null == lifeline) {
            strName = lifeLineNode.getName();
        } else {
            strName = lifeline.getName();
        }
        LifeLineNameHeaderFigure figure = new LifeLineNameHeaderFigure(strName);
        figure.setLocation(new Point(lifeLineNode.getX(), lifeLineNode.getY()));
        if (header.isVisible()) {
            figure.getLabel().setSize(lifeLineNode.getWidth(), FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
            figure.setSize(lifeLineNode.getWidth(), FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
        } else {
            figure.setSize(0, 0);
        }
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    public void refreshVisuals() {
        try {
            String strLifelineName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            LifeLineNameHeader header = (LifeLineNameHeader) getModel();
            LifeLineNode lifeLineNode = header.getLifeLine();
            Lifeline lifeline = (Lifeline) lifeLineNode.getUmlModel();
            LifeLineNameHeaderFigure figure = (LifeLineNameHeaderFigure) getFigure();
            figure.setName(lifeline.getName());
            if (lifeline.getRepresents() != null) {
                Type type = lifeline.getRepresents().getType();
                if (type != null) {
                    String stereoTypeName = ProjectUtil.getStereotypeLabel(type);
                    String typeName = type.getName();
                    //                if(stereoTypeName != null && !"".equals(stereoTypeName)) { //$NON-NLS-1$                 
                    //                    figure.setName(stereoTypeName + figure.getName() + ":" + typeName); //$NON-NLS-1$                    
                    // } else {
                    //                    figure.setName(figure.getName() + ":" + typeName); //$NON-NLS-1$                    
                    // }
                    if (SequenceUtil.checkLifelineVisibility()) {
                        strLifelineName = figure.getName();
                    } else {
                        strLifelineName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
                    if (stereoTypeName != null && !UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(stereoTypeName)) {                 
                        figure.setName(strLifelineName + UICoreConstant.PROJECT_CONSTANTS__COLON + typeName);
                        figure.setStereotype(stereoTypeName);
                    } else {
                        figure.setName(strLifelineName + UICoreConstant.PROJECT_CONSTANTS__COLON + typeName);     
                        figure.setStereotype(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                    }
                }
            }

            int verticalScrollPoint = 0;

            if (getParent() != null && getViewer() != null && getViewer().getControl() != null
                && ((FigureCanvas) getViewer().getControl()).getVerticalBar() != null) {
                verticalScrollPoint = ((FigureCanvas) getViewer().getControl()).getVerticalBar().getSelection();
            }

            if (header.isVisible()) {
                figure.setSize(lifeLineNode.getWidth(), FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
            } else {
                figure.setSize(0, 0);
            }

            figure.setLocation(new Point(lifeLineNode.getX(), verticalScrollPoint));

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_LIFELINE_NAME_HEADER_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @SuppressWarnings("unused")
    public void notifyChanged(Notification notification) {
        int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
            case Notification.MOVE:
            case Notification.UNSET:
            case Notification.SET:
                refreshVisuals();
                break;
            default:
                break;
        }
    }

    /**
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        // TODO Auto-generated method stub

    }
}
