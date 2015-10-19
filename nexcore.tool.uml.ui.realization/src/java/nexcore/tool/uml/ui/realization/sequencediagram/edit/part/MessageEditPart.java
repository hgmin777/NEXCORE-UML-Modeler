/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.MessageBendpointEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.MessageConnectionEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.SendOperationEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : MessageEditPart</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MessageEditPart extends AbstractDiagramConnectionEditPart {

    /** messageAdapter */
    MessageInternalAdapter messageAdapter;

    /**
     * MessageEditPart
     */
    public MessageEditPart() {
        messageAdapter = new MessageInternalAdapter();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        Relation relation = (Relation) getModel();
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        connection.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));

        connection.addRoutingListener(RoutingAnimator.getDefault());
        connection.setConnectionRouter(new BendpointConnectionRouter());

        if (RelationType.SYNCHRONOUS_MESSAGE.equals(relation.getRelationType())) {
            return connection;
        } else if (RelationType.ASYNCHRONOUS_MESSAGE.equals(relation.getRelationType())) {
            PolylineDecoration poly = new PolylineDecoration();
            PointList p = new PointList();
            p.addPoint(0, 0);
            p.addPoint(-1, -1);
            p.addPoint(-1, 1);
            poly.setPoints(p);
            poly.setScale(10, 5);
            connection.setTargetDecoration(poly);
            connection.setLineStyle(Graphics.LINE_SOLID);
            return connection;
        } else if (RelationType.REPLY_MESSAGE.equals(relation.getRelationType())) {
            connection.setLineStyle(Graphics.LINE_DOT);
            return connection;
        } else if (RelationType.CREATE_MESSAGE.equals(relation.getRelationType())) {
            return connection;
        } else if (RelationType.DESTROY_MESSAGE.equals(relation.getRelationType())) {
            return connection;
        } else {
            return connection;
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createBendpointEditPolicy()
     */
    @Override
    protected SelectionHandlesEditPolicy createBendpointEditPolicy() {
        return new MessageBendpointEditPolicy();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createConnectionEditPolicy()
     */
    @Override
    protected ConnectionEditPolicy createConnectionEditPolicy() {
        return new MessageConnectionEditPolicy();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            if (getModel() instanceof Relation) {
                refreshLabels();
            }
            super.refreshVisuals();
            refreshBendpoints();
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_MESSAGE_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }

    }

    /**
     * @param
     * @return
     */
    private void refreshMessage() {
        // Viewport viewport = ((FigureCanvas) getViewer().getControl())
        // .getViewport();
        // int verticalValue = viewport.getVerticalRangeModel().getValue();
        // if (verticalValue > -1) {
        // getFigure().setVisible(isMessageOnLifeLineName());
        // } else {
        // getFigure().setVisible(true);
        // }
    }

    /**
     * @param lnEditparts
     * @return
     */
    private boolean isMessageOnLifeLineName() {

        Viewport viewport = ((FigureCanvas) getViewer().getControl()).getViewport();
        int verticalValue = viewport.getVerticalRangeModel().getValue();

        // System.out.println(verticalValue);

        if (verticalValue < getFigure().getBounds().y && verticalValue + 75 > getFigure().getBounds().y) {
            // System.out.println("Figure Location false : " +
            // getFigure().getBounds().y);
            return false;
        } else if (getFigure().getBounds().y < verticalValue) {
            return false;
        }
        // System.out.println("Figure Location true : " +
        // getFigure().getBounds().y);
        return true;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#refreshLabels()
     * 
     *      메시지의 넘버링을 여기서 수행함.
     * 
     */
    @Override
    protected void refreshLabels() {
        Relation relation = (Relation) getModel();
        NamedElement element = (NamedElement) relation.getUmlModel();
        ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) this.getParent();
        Diagram diagram = null;
        if (rootEditPart == null) {
            try {
                diagram = ProjectUtil.getDiagrams((Element) element.eContainer(), DiagramType.SEQUENCE_DIAGRAM).get(0);
            } catch (Exception error) {
                return;
            }

        } else {
            diagram = (Diagram) rootEditPart.getContents().getModel();
        }
        if (null == diagram) {
            return;
        }
        List<AbstractConnection> messageList = SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());

        int order = 0;
        AbstractConnection message;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            if (message.equals(this.getModel())) {
                order = i + 1;
            }
        }
        Operation operation;
        Event sendEvent;
        String name = relation.getName();
        if (RelationType.REPLY_MESSAGE.equals(relation.getRelationType())) {
            if(SequenceUtil.checkReplyMessageVisibility()) {
                setNameLabel(relation, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                return;
            }
        }
        try {
            sendEvent = this.getSendMessageEvent(relation);
            if (null == sendEvent) {
                return;
            }
            operation = ((SendOperationEvent) sendEvent).getOperation();
            if (null == operation) {
                if (null == element) {
                    if (0 == name.length()) {
                        setNameLabel(relation, order + ":\\undefined\\");
                    } else {
                        setNameLabel(relation, order + ":\\" + name + "\\");
                    }
                } else {
                    setNameLabel(relation, order + ":\\" + element.getName() + "\\");
                }
            } else {
                Parameter returnResult = operation.getReturnResult();
                List<Parameter> parameterList = new ArrayList<Parameter>();
                for (Parameter para : operation.getOwnedParameters()) {
                    if (!para.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) {
                        parameterList.add(para);
                        if (!para.eAdapters().contains(this.messageAdapter)) {
                            para.eAdapters().add(this.messageAdapter);
                        }
                    }
                }

                String messageReturnType = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                String messageParameter = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

                if (SequenceUtil.showMessageType() && SequenceUtil.showMessageParameter()) {
                    if (returnResult != null) {
                        if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_BOOLEAN)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_BOOLEAN;
                        } else if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_INTEGER)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_INTEGER;
                        } else if (returnResult.getLabel().equals(UICoreConstant.STRING_LITERAL)) {
                            messageReturnType = UICoreConstant.STRING_LITERAL;
                        } else if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_UNLIMITEDNATURAL)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_UNLIMITEDNATURAL;
                        } else {
                            messageReturnType = returnResult.getType().getName();
                        }
                    }
                    if (parameterList != null && parameterList.size() != 0) {
                        for (int i = 0; i < parameterList.size(); i++) {
                            Parameter parameter = parameterList.get(i);
//                            if (parameter.getType() == null) {
//                                continue;
//                            }
                            if (i != 0) {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__COMMA
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + parameter.getLabel().toString()
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__COLON 
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getType().getName().toString();
                                } else {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__COMMA
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + parameter.getLabel().toString();
                                }
                            } else {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + parameter.getLabel().toString()
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__COLON 
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getType().getName().toString();
                                } else {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getLabel().toString();
                                }
                            }
                        }
                    }

                } else if (SequenceUtil.showMessageType()) {
                    if (returnResult != null) {
                        if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_BOOLEAN)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_BOOLEAN;
                        } else if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_INTEGER)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_INTEGER;
                        } else if (returnResult.getLabel().equals(UICoreConstant.STRING_LITERAL)) {
                            messageReturnType = UICoreConstant.STRING_LITERAL;
                        } else if (returnResult.getLabel().equals(UMLMessage.LABEL_UPPER_UNLIMITEDNATURAL)) {
                            messageReturnType = UMLMessage.LABEL_UPPER_UNLIMITEDNATURAL;
                        } else {
                            messageReturnType = returnResult.getLabel();
                        }
                    }
                    if (parameterList != null && parameterList.size() != 0) {
                        for (int i = 0; i < parameterList.size(); i++) {
                            Parameter parameter = parameterList.get(i);
//                            if (parameter.getLabel() == null) {
//                                continue;
//                            }
                            if (i != 0) {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__COMMA
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + parameter.getType().getName().toString();
                                } else {
                                	messageParameter += UICoreConstant.PROJECT_CONSTANTS__COMMA + UICoreConstant.PROJECT_CONSTANTS__BLANK;
                                }
                            } else {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + parameter.getType().getName().toString();
                                }
                            }
                        }
                    }
                } else if (SequenceUtil.showMessageParameter()) {
                    if (parameterList != null && parameterList.size() != 0) {
                        for (int i = 0; i < parameterList.size(); i++) {
                            Parameter parameter = parameterList.get(i);
                            if (parameter.getLabel() == null) {
                                continue;
                            }
                            if (i != 0) {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__COMMA
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + parameter.getLabel().toString()
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__COLON 
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getType().getName().toString();
                                } else {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__COMMA
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + parameter.getLabel().toString();
                                }
                            } else {
                                if (parameter.getType() != null) {
                                    messageParameter = messageParameter + parameter.getLabel().toString()
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__COLON 
                                        + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getType().getName().toString();
                                } else {
                                    messageParameter = messageParameter + UICoreConstant.PROJECT_CONSTANTS__BLANK
                                        + parameter.getLabel().toString();
                                }
                            }
                        }
                    }
                } else {
                    messageParameter = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    // messageParameter = "..";
                }
                setNameLabel(relation, order + UICoreConstant.PROJECT_CONSTANTS__COLON 
                		+ UICoreConstant.PROJECT_CONSTANTS__BLANK + operation.getName() 
                		+ UICoreConstant.PROJECT_CONSTANTS__BLANK 
                		+ UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + messageParameter
                		+ UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT + UICoreConstant.PROJECT_CONSTANTS__BLANK 
                		+ UICoreConstant.PROJECT_CONSTANTS__COLON
                		+ UICoreConstant.PROJECT_CONSTANTS__BLANK + messageReturnType);
            }
        } catch (Exception err) {
            Log.error(relation.toString());
        }
    }

    /**
     * @param relation
     * @return
     */
    private Event getSendMessageEvent(Relation relation) {
        Object model = relation;
        if (!(model instanceof Relation)) {
            return null;
        }
        Element umlModel = ((Relation) model).getUmlModel();
        if (!(umlModel instanceof Message)) {
            return null;
        }
        Message message = (Message) umlModel;
        MessageEnd messageEnd = message.getSendEvent();
        if (!(messageEnd instanceof MessageOccurrenceSpecification)) {
            return null;
        }
        Event event = ((MessageOccurrenceSpecification) messageEnd).getEvent();
        if (!(event instanceof SendOperationEvent)) {
            return null;
        } else {
            return event;
        }

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#setNameLabel(nexcore.tool.uml.model.umldiagram.Relation,
     *      java.lang.String)
     */
    @Override
    protected IFigure setNameLabel(Relation relation, String name) {

        PolylineConnection connection = (PolylineConnection) figure;

        if (nameLabel != null) {
            getFigure().remove(nameLabel);
        }

        nameLabel = new Label(name);
        MessageLabelLocator nameLabelLocator = new MessageLabelLocator(connection, LabelType.LABEL, true);

        figure.add(nameLabel, nameLabelLocator);

        return figure;
    }

    /** selectionAdapter */
    private PropertyChangeListener listener = new PropertyChangeListener() {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
            if ("viewLocation".equals(evt.getPropertyName())) { //$NON-NLS-1$                
                refreshMessage();
            }
        }
    };

    /**
     * mouseWheelListener
     */
    private MouseWheelListener mouseWheelListener = new MouseWheelListener() {
        public void mouseScrolled(MouseEvent e) {
            refreshMessage();
        }
    };

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        if(getViewer().getControl() != null) {
            if(((FigureCanvas) getViewer().getControl()).getViewport() != null) {
                ((FigureCanvas) getViewer().getControl()).getViewport().addPropertyChangeListener(listener);
            }
            ((FigureCanvas) getViewer().getControl()).addMouseWheelListener(mouseWheelListener);            
        }
        Object model = this.getModel();
        if (!(model instanceof Relation)) {
            return;
        }

        Event event = this.getSendMessageEvent((Relation) model);
        if (!(event instanceof SendOperationEvent)) {
            return;
        }
        if (!event.eAdapters().contains(this.messageAdapter)) {
            event.eAdapters().add(this.messageAdapter);
        }

        Operation operation = ((SendOperationEvent) event).getOperation();
        if (null == operation) {
            return;
        }
        if (!operation.eAdapters().contains(this.messageAdapter)) {
            operation.eAdapters().add(this.messageAdapter);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        if(getViewer().getControl() != null) {
            if(((FigureCanvas) getViewer().getControl()).getViewport() != null) {
                ((FigureCanvas) getViewer().getControl()).getViewport().removePropertyChangeListener(listener);
            }
            ((FigureCanvas) getViewer().getControl()).removeMouseWheelListener(mouseWheelListener);
        }
        Object model = this.getModel();
        if (!(model instanceof Relation)) {
            return;
        }
        Event event = this.getSendMessageEvent((Relation) model);
        if (!(event instanceof SendOperationEvent)) {
            return;
        }
        if (event.eAdapters().contains(this.messageAdapter)) {
            event.eAdapters().remove(this.messageAdapter);
        }
        Operation operation = ((SendOperationEvent) event).getOperation();
        if (null == operation) {
            return;
        }
        if (operation.eAdapters().contains(this.messageAdapter)) {
            operation.eAdapters().remove(this.messageAdapter);
        }
        List<Parameter> paramList = operation.getOwnedParameters();
        for (Parameter para : paramList) {
            if (!para.eAdapters().contains(this.messageAdapter)) {
                para.eAdapters().remove(this.messageAdapter);
            }
        }
    }

    class MessageInternalAdapter implements Adapter {
        /**
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        public Notifier getTarget() {
            return null;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
         */
        public boolean isAdapterForType(Object type) {
            return false;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
         */
        public void notifyChanged(Notification notification) {
            if (notification.getNewValue() instanceof Parameter && notification.getEventType() == Notification.ADD) {
                Parameter param = (Parameter) notification.getNewValue();
                if (!param.eAdapters().contains(messageAdapter)) {
                    param.eAdapters().add(messageAdapter);
                }
            }
            refreshLabels();
            refreshVisuals();
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
         */
        public void setTarget(Notifier newTarget) {
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        return super.getSelected();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createBendPoint(org.eclipse.draw2d.geometry.Point, org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public void createBendPoint(Point startPoint, Point point) {
        Relation relation = (Relation) this.getModel();
        Connection connection = (PolylineConnection) this.getFigure();
        List<BendPoint> bendPointList = relation.getBendPointList();
        List<Double> checkDistanceList = new ArrayList<Double>();
        for (int i = 0; i < bendPointList.size(); i++) {
            if (i == 0) {
                Point firstPosition = new Point(bendPointList.get(i).getFirstPosition().getWidth(),
                    bendPointList.get(i).getFirstPosition().getHeight());
                Point bendPoint = new Point(firstPosition.x + connection.getSourceAnchor().getReferencePoint().x,
                    firstPosition.y + connection.getSourceAnchor().getReferencePoint().y);
                checkDistanceList.add(checkPoint(connection.getSourceAnchor().getReferencePoint(),
                    bendPoint,
                    startPoint));
                if (bendPointList.size() == 1) {
                    checkDistanceList.add(checkPoint(connection.getTargetAnchor().getReferencePoint(),
                        bendPoint,
                        startPoint));
                }
            } else {
                Point beforeFirstPosition = new Point(bendPointList.get(i - 1).getFirstPosition().getWidth(),
                    bendPointList.get(i - 1).getFirstPosition().getHeight());
                Point firstPosition = new Point(bendPointList.get(i).getFirstPosition().getWidth(),
                    bendPointList.get(i).getFirstPosition().getHeight());
                Point beforeBendPoint = new Point(beforeFirstPosition.x
                    + connection.getSourceAnchor().getReferencePoint().x, beforeFirstPosition.y
                    + connection.getSourceAnchor().getReferencePoint().y);
                Point bendPoint = new Point(firstPosition.x + connection.getSourceAnchor().getReferencePoint().x,
                    firstPosition.y + connection.getSourceAnchor().getReferencePoint().y);
                checkDistanceList.add(checkPoint(beforeBendPoint, bendPoint, startPoint));

                if (bendPointList.size() == i + 1) {
                    checkDistanceList.add(checkPoint(connection.getTargetAnchor().getReferencePoint(),
                        bendPoint,
                        startPoint));
                }
            }
        }
        final BendpointRequest request = new BendpointRequest();
        request.setType(UMLMessage.LABEL_CREATE_BENDPOINT);
        request.setIndex(getIndex(checkDistanceList));
        request.setSource(this);
        request.setLocation(point);

        final MessageBendpointEditPolicy editPolicy = (MessageBendpointEditPolicy) getEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                Command cmd = editPolicy.getCreateBendpoint(request);
                if (cmd != null) {
                    cmd.execute();
                }
            }
        });
        eraseSourceFeedback(request);
    }

    /**
     * 
     * 
     * @param start
     * @param end
     * @param selectPoint
     * @return double
     */
    private static double checkPoint(Point start, Point end, Point selectPoint) {
        double x1, y1, a, b;
        x1 = start.x;
        y1 = start.y;
        double x2, y2;
        x2 = end.x;
        y2 = end.y;

        a = (y1 - y2) / (x1 - x2);
        b = y1 - (a * x1);

        double tempY = (a * selectPoint.x) + b;
        return Math.abs((Math.abs(selectPoint.y) - Math.abs(tempY)));
    }

    /**
     * @param checkDistanceList
     * @return
     */
    private int getIndex(List<Double> checkDistanceList) {
        Map<Double, Integer> minimumDistanceMap = new HashMap<Double, Integer>();
        double minimumDistance = 0;
        if (checkDistanceList.size() == 0) {
            return 0;
        }
        for (int i = 0; i < checkDistanceList.size(); i++) {
            if (i == 0) {
                minimumDistanceMap.put(checkDistanceList.get(i), i);
                minimumDistance = checkDistanceList.get(i);
            } else {
                minimumDistanceMap.put(checkDistanceList.get(i), i);
                if (minimumDistance > checkDistanceList.get(i)) {
                    minimumDistance = checkDistanceList.get(i);
                }
            }
        }
        return minimumDistanceMap.get(minimumDistance);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#showFeedback(org.eclipse.draw2d.geometry.Point, org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public void showFeedback(Point startPoint, Point point) {
        final BendpointRequest request = new BendpointRequest();
        request.setType(UMLMessage.LABEL_CREATE_BENDPOINT);
        request.setIndex(0);
        request.setSource(this);
        request.setLocation(point);

        MessageBendpointEditPolicy editPolicy = (MessageBendpointEditPolicy) getEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
        editPolicy.showSourceFeedback(request);
    }
    
//    @Override
//    public DragTracker getDragTracker(Request request) {
//        return new MessageDragTracker(this);
//    }
//
//    public class MessageDragTracker extends SelectEditPartTracker {
//        public MessageDragTracker(EditPart sourceEditPart) {
//            super(sourceEditPart);
//            setDefaultCursor(SharedCursors.SIZENS);
//            setDisabledCursor(SharedCursors.NO);
//        }
//    }
}
