/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.diagram.edit.policy.ConnectionBendpointEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramConnectionEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramEndpointEditPolicy;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : AbstractDiagramConnectionEditPart</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class AbstractDiagramConnectionEditPart extends AbstractConnectionEditPart {

    /**
     * @param startLocation
     * @param point
     */
    public void showFeedback(Point startPoint, Point point) {
        List<Double> checkDistanceList = getBendpointInfo(startPoint);
        final BendpointRequest request = createBendpointRequest(point, checkDistanceList);
        request.setIndex(getIndex(checkDistanceList));

        ConnectionBendpointEditPolicy editPolicy = (ConnectionBendpointEditPolicy) getEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
        if( editPolicy == null ) {
            return;
        }
        editPolicy.showSourceFeedback(request);
    }

    /**
     * @param start
     * @param end
     * @param selectPoint
     * @return
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
     * @param startPoint
     * @param point
     */
    public void createBendPoint(Point startPoint, final Point point) {

        List<Double> checkDistanceList = getBendpointInfo(startPoint);
        final BendpointRequest request = createBendpointRequest(point, checkDistanceList);
        final ConnectionBendpointEditPolicy editPolicy = (ConnectionBendpointEditPolicy) getEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);

        if( editPolicy == null ) { 
            return;
        }
        
        editPolicy.callEraseConnectionFeedback(request);
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */

            @Override
            public void doExecute() {
                // getCreateBendpointCommand(request).execute();
                Command cmd = editPolicy.getCreateBendpoint(request);
                if (cmd != null) {
                    cmd.execute();
                }
            }
        });
    }

    /**
     * createBendpointRequest
     *  
     * @param point
     * @param checkDistanceList
     * @return BendpointRequest
     */
    private BendpointRequest createBendpointRequest(final Point point, List<Double> checkDistanceList) {
        final BendpointRequest request = new BendpointRequest();
        request.setType("create bendpoint");
        request.setIndex(getIndex(checkDistanceList));
        request.setSource(this);
        request.setLocation(point);
        return request;
    }

    /**
     * getBendpointInfo
     *  
     * @param startPoint
     * @return List<Double>
     */
    private List<Double> getBendpointInfo(Point startPoint) {
        
        if( !(this.getModel() instanceof Relation) ) {
            return new ArrayList<Double>();
        }
        
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
        return checkDistanceList;
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

    // protected Command getCreateBendpointCommand(BendpointRequest request) {
    //	       
    // CreateBendpointCommand cmd = new CreateBendpointCommand();
    // Point p = request.getLocation();
    // Connection conn = getConnection();
    //
    // conn.translateToRelative(p);
    // cmd.setLocation(p);
    //	        
    // AbstractNode sourceNode = (AbstractNode)
    // ((Relation)this.getModel()).getSource();
    // AbstractNode targetNode = (AbstractNode)
    // ((Relation)this.getModel()).getTarget();
    // if(sourceNode.equals(targetNode)){
    // XYAnchor sourceAnchor = new XYAnchor(new Point(sourceNode.getX() +
    // sourceNode.getWidth(), sourceNode.getY() + (int) (sourceNode.getHeight()
    // / 2)));
    // XYAnchor targetAnchor = new XYAnchor(new Point(targetNode.getX() + (int)
    // (targetNode.getWidth() / 2), targetNode.getY() +
    // targetNode.getHeight()));
    // Point sourcePoint = sourceAnchor.getReferencePoint();
    // Point targetPoint = targetAnchor.getReferencePoint();
    // conn.translateToRelative(sourcePoint);
    // conn.translateToRelative(targetPoint);
    //	
    // int horizontalPoint =
    // ((FigureCanvas)((AbstractDiagramEditor)UiCorePlugin.getActivePage().getActiveEditor()).getDiagramGraphicalViewer().getControl()).getHorizontalBar().getSelection();
    // int verticalPoint =
    // ((FigureCanvas)((AbstractDiagramEditor)UiCorePlugin.getActivePage().getActiveEditor()).getDiagramGraphicalViewer().getControl()).getVerticalBar().getSelection();
    // Point scrollBarPoint = new Point(p.x + horizontalPoint, p.y +
    // verticalPoint);
    // cmd.setRelativeDimensions(scrollBarPoint.getDifference(sourcePoint),
    // scrollBarPoint.getDifference(targetPoint));
    // cmd.setModel(request.getSource().getModel());
    // cmd.setFigure(request.getSource().getFigure());
    // cmd.setIndex(request.getIndex());
    //			 
    // return cmd;
    // } else{
    //			 
    // Point sourcePoint =
    // getConnection().getSourceAnchor().getReferencePoint();
    // Point targetPoint =
    // getConnection().getTargetAnchor().getReferencePoint();
    //        
    // conn.translateToRelative(sourcePoint);
    // conn.translateToRelative(targetPoint);
    //
    // cmd.setRelativeDimensions(p.getDifference(sourcePoint),
    // p.getDifference(targetPoint));
    //        
    // cmd.setModel(request.getSource().getModel());
    // cmd.setFigure(request.getSource().getFigure());
    // cmd.setIndex(request.getIndex());
    //        
    // return cmd;
    // }
    // }
    /**
     * @return
     */
    private Connection getConnection() {
        return (Connection) this.getConnectionFigure();
    }

    /**
     * AbstractDiagramConnectionEditPart
     */
    public AbstractDiagramConnectionEditPart() {
        adapter = new InternalAdapter(this);
        // classAdapter = new ClassInternalAdapter(this);
    }

    /** adapter */
    InternalAdapter adapter;

    // /** classAdapter */
    // ClassInternalAdapter classAdapter;

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            super.activate();
            EObject model = (EObject) getModel();
            model.eAdapters().add(adapter);
            Element umlModel;

            // AbstractConnection connection = (AbstractConnection) getModel();
            // connection.getSource().eAdapters().add(classAdapter);
            // connection.getTarget().eAdapters().add(classAdapter);

            if (model instanceof Relation) {
                model = ((AbstractConnection) getModel()).getUmlModel();
                if (null != model) {
                    model.eAdapters().add(adapter);
                    Property property;
                    if (model instanceof Association) {
                        for (Iterator<Property> iter = ((Association) model).getMemberEnds().iterator(); iter.hasNext();) {
                            property = iter.next();
                            property.eAdapters().add(adapter);
                            if (property.getUpperValue() != null) {
                                property.getUpperValue().eAdapters().add(adapter);
                            }
                            if (property.getLowerValue() != null) {
                                property.getLowerValue().eAdapters().add(adapter);
                            }

                        }
                    }

                    model = ((AbstractConnection) getModel()).getSource();
                    model.eAdapters().add(adapter);
                    model = ((AbstractConnection) getModel()).getTarget();
                    model.eAdapters().add(adapter);
                    umlModel = ((Relation) getModel()).getUmlModel();
                    for (EAnnotation eAnnotation : ((Element) umlModel).getEAnnotations()) {
                        eAnnotation.eAdapters().add(adapter);
                    }
                }
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
            EObject model = (EObject) getModel();
            model.eAdapters().remove(adapter);
            Element umlModel;

            // AbstractConnection connection = (AbstractConnection) getModel();
            // connection.getSource().eAdapters().remove(classAdapter);
            // connection.getTarget().eAdapters().remove(classAdapter);

            Property property;
            if (model instanceof Relation) {
                model = ((AbstractConnection) getModel()).getUmlModel();
                if (null != model) {
                    model.eAdapters().remove(adapter);
                    if (model instanceof Association) {
                        for (Iterator<Property> iter = ((Association) model).getMemberEnds().iterator(); iter.hasNext();) {
                            property = iter.next();
                            property.eAdapters().remove(adapter);
                            if (property.getUpperValue() != null) {
                                property.getUpperValue().eAdapters().remove(adapter);
                            }
                            if (property.getLowerValue() != null) {
                                property.getLowerValue().eAdapters().remove(adapter);
                            }
                        }
                    }

                    model = ((AbstractConnection) getModel()).getSource();
                    model.eAdapters().remove(adapter);
                    model = ((AbstractConnection) getModel()).getTarget();
                    model.eAdapters().remove(adapter);
                    umlModel = ((Relation) getModel()).getUmlModel();
                    for (EAnnotation eAnnotation : ((Element) umlModel).getEAnnotations()) {
                        eAnnotation.eAdapters().remove(adapter);
                    }
                }
            }

        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
     * <li>설 명 : InternalAdapter</li>
     * <li>작성일 : 2009. 12. 22.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    class InternalAdapter implements Adapter {

        /** myEditPart */
        AbstractDiagramConnectionEditPart myEditPart;

        public InternalAdapter(AbstractDiagramConnectionEditPart editPart) {
            myEditPart = editPart;
        }

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
            // int featureID = notification.getFeatureID(Relation.class);
            int eventType = notification.getEventType();
            switch (eventType) {
                case Notification.ADD:
                    refreshBendpoints();
                    refreshChildren();
                    if (notification.getNewValue() instanceof EAnnotation) {
                        ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                    }
                case Notification.SET:
                    Display.getDefault().syncExec(new Runnable() {
                        
                        @Override
                        public void run() {
                            myEditPart.refreshChildren();
                            myEditPart.refreshVisuals();
                            myEditPart.refresh();
                            refreshBendpoints();
                        }
                    });
                    if (notification.getNewValue() instanceof EAnnotation) {
                        ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                    }
                    break;
                case Notification.REMOVE:
                case Notification.REMOVE_MANY:

                    Display.getDefault().syncExec(new Runnable() {
                        
                        @Override
                        public void run() {
                            refreshBendpoints();
                            refreshChildren();
                        }
                    });
                default:
                    break;
            }
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
         */
        public void setTarget(Notifier newTarget) {
        }

    }

    // /**
    // * <ul>
    // * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
    // * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
    // * <li>설 명 : InternalAdapter</li>
    // * <li>작성일 : 2009. 12. 22.</li>
    // * <li>작성자 : 강경구</li>
    // * </ul>
    // */
    // class ClassInternalAdapter implements Adapter {
    //
    // /** myEditPart */
    // AbstractDiagramConnectionEditPart myEditPart;
    //
    // public ClassInternalAdapter(AbstractDiagramConnectionEditPart editPart) {
    // myEditPart = editPart;
    // }
    //
    // /**
    // * @see org.eclipse.emf.common.notify.Adapter#getTarget()
    // */
    // public Notifier getTarget() {
    // return null;
    // }
    //
    // /**
    // * @see
    // org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
    // */
    // public boolean isAdapterForType(Object type) {
    // return false;
    // }
    //
    // /**
    // * @see
    // org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
    // */
    // public void notifyChanged(Notification notification) {
    // int eventType = notification.getEventType();
    // switch (eventType) {
    // case Notification.SET:
    //
    // AbstractConnection connection = (AbstractConnection) getModel();
    //                    
    // AbstractConnectionAnchor sourceAnchor = (AbstractConnectionAnchor)
    // ((AbstractNotationNodeEditPart)myEditPart.getSource()).getSourceConnectionAnchor(myEditPart);
    // AbstractConnectionAnchor targetAnchor = (AbstractConnectionAnchor)
    // ((AbstractNotationNodeEditPart)myEditPart.getTarget()).getTargetConnectionAnchor(myEditPart);
    //                    
    // //
    // ((AbstractGraphicalEditPart)myEditPart.getSource()).getFigure().getBounds()
    //                    
    // Point sourceAnchorLocation;
    // Point targetAnchorLocation;
    //                    
    // sourceAnchorLocation =
    // sourceAnchor.getLocation(targetAnchor.getReferencePoint());
    // targetAnchorLocation =
    // targetAnchor.getLocation(sourceAnchor.getReferencePoint());
    //                    
    // System.out.println("sourceAnchor Ref" +
    // sourceAnchor.getReferencePoint());
    // System.out.println("targetAnchor Ref" +
    // targetAnchor.getReferencePoint());
    // System.out.println("source anchor" + sourceAnchorLocation);
    // System.out.println("target anchor" + targetAnchorLocation);
    //                    
    // nexcore.tool.uml.model.umldiagram.Dimension sourceDimension =
    // UMLDiagramFactory.eINSTANCE.createDimension();
    // sourceDimension.setWidth(sourceAnchorLocation.x);
    // sourceDimension.setHeight(sourceAnchorLocation.y);
    // connection.setSourceAnchor(sourceDimension);
    //
    // nexcore.tool.uml.model.umldiagram.Dimension targetDimension =
    // UMLDiagramFactory.eINSTANCE.createDimension();
    // targetDimension.setWidth(targetAnchorLocation.x);
    // targetDimension.setHeight(targetAnchorLocation.y);
    // connection.setTargetAnchor(targetDimension);
    // break;
    //
    // default:
    // break;
    // }
    // }
    //
    // /**
    // * @see
    // org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
    // */
    // public void setTarget(Notifier newTarget) {
    // }
    //
    // }

    /**
     * refresh bend point void
     */
    @SuppressWarnings("unchecked")
    protected void refreshBendpoints() {

        try {
            List figureConstraint = new ArrayList();
            List modelConstraint = new ArrayList();
            if (getModel() instanceof Relation) {
                modelConstraint = ((Relation) getModel()).getBendPointList();
            }

            if (getModel() instanceof Attachement) {
                modelConstraint = ((Attachement) getModel()).getBendPointList();
            }

            BendPoint bendpoint;
            RelativeBendpoint relativeBendpoint;
            Dimension firstPositionDimension, secondPositionDimension;

            for (int i = 0; i < modelConstraint.size(); i++) {

                bendpoint = (BendPoint) modelConstraint.get(i);
                relativeBendpoint = new RelativeBendpoint((Connection) getFigure());

                firstPositionDimension = new Dimension();
                firstPositionDimension.height = bendpoint.getFirstPosition().getHeight();
                firstPositionDimension.width = bendpoint.getFirstPosition().getWidth();
                secondPositionDimension = new Dimension();
                secondPositionDimension.height = bendpoint.getSecondPosition().getHeight();
                secondPositionDimension.width = bendpoint.getSecondPosition().getWidth();

                relativeBendpoint.setRelativeDimensions(firstPositionDimension, secondPositionDimension);
                relativeBendpoint.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
                figureConstraint.add(relativeBendpoint);
            }
            ((Connection) getFigure()).setRoutingConstraint(figureConstraint);
        } catch (Exception e) {
            Log.error("AbstractDiagramConnectionEditPart RefreshBendPoint() Error " + e);
        }

    }

    /** nameLabel */
    protected Label nameLabel;

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ROLE, createConnectionEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, createConnectionEndpointEditPolicy());

        if (!(getModel() instanceof Attachement)) {
            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, createBendpointEditPolicy());
        }
    }

    /**
     * 컨넥션 에디트 폴리시(삭제정책)를 생성한다. EditPolicy.CONNECTION_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ConnectionEditPolicy createConnectionEditPolicy() {
        return new DiagramConnectionEditPolicy();
    }

    /**
     * 컨넥션엔드포인트 폴리시(MyConnectionEndpointEditPolicy, connection 선택 여부를 반영하는 정책)를
     * 생성한다. EditPolicy.CONNECTION_ENDPOINTS_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ConnectionEndpointEditPolicy createConnectionEndpointEditPolicy() {
        return new DiagramEndpointEditPolicy();
    }

    /**
     * 컨넥션벤드포인트 폴리시(벤드포인트 정책)를 생성한다. EditPolicy.CONNECTION_BENDPOINTS_ROLE 인스톨
     * 된다.
     * 
     * @return
     */
    protected SelectionHandlesEditPolicy createBendpointEditPolicy() {
        return new ConnectionBendpointEditPolicy();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = new PolylineConnection();
        connection.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
        PolygonDecoration polygonDecoreation = new PolygonDecoration();
        polygonDecoreation.setScale(10, 5);
        connection.setTargetDecoration(polygonDecoreation);
        return connection;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    // public void notifyChanged(Notification notification) {
    // int eventType = notification.getEventType();
    // switch (eventType) {
    // case Notification.SET:
    // //refreshVisuals();
    // refresh();
    // break;
    // default:
    // break;
    //
    // }
    // }
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            refreshBendpoints();

            getFigure().setForegroundColor(new Color(null, getFillColor()));

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_ABSTRACT_DIAGRAM_CONNECTION_EDIT_PART_ERROR + e);
        }
    }

    /**
     * getFillColor
     * 
     * @return RGB
     */
    protected RGB getFillColor() {
        RGB nodeColor = PreferenceUtil.INSTANCE.getNodeLineColor();
        AbstractConnection viewModel = (AbstractConnection) getModel();
        if (viewModel != null) {
            if (viewModel.getFillColor() != null) {
                nodeColor = StringConverter.asRGB(viewModel.getFillColor());
            }
        }
        return nodeColor;
    }

    /**
     * refreshLabels void
     */
    protected void refreshLabels() {
        Relation relation = (Relation) getModel();
        NamedElement element = (NamedElement) relation.getUmlModel();
        if (relation.getName() != null) {
            setNameLabel(relation, element.getName());
        }
    }

    /**
     * setNameLabel
     * 
     * @param relation
     * @param name
     *            void
     */
    protected IFigure setNameLabel(Relation relation, String name) {
        PolylineConnection connection = (PolylineConnection) figure;

        if (nameLabel != null) {
            getFigure().remove(nameLabel);
        }

        nameLabel = new Label(name);
        ConnectionLabelLocator nameLabelLocator = new ConnectionLabelLocator(connection, LabelType.LABEL, true);

        figure.add(nameLabel, nameLabelLocator);

        return figure;
    }
}
