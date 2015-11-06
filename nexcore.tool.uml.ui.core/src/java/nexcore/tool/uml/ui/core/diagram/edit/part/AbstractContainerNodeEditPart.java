/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramContainerEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramGraphicalNodeEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : AbstractContainerNodeEditPart</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public abstract class AbstractContainerNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart, Adapter {

    /** directManager */
    private DirectEditorManager directManager = null;

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            performDirectEdit();
            return;
        }
        super.performRequest(req);
    }

    /**
     * performDirectEdit void
     */
    private void performDirectEdit() {

        IFigure figure = null;
        figure = getFigure();
        
        if (directManager == null) {
            setDirectManager(new DirectEditorManager(this,
                TextCellEditor.class,
                new DirectEditCellEditorLocator(figure)));
        }
        directManager.show();
    }

    /**
     * setDirectManager
     *  
     * @param manager void
     */
    protected void setDirectManager(DirectEditorManager manager) {
        directManager = manager;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, createGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
        installEditPolicy(EditPolicy.CONTAINER_ROLE, createContainerEditPolicy());

    }

    /**
     * 콤포넌트 에디트 폴리시(삭제정책)를 생성한다. EditPolicy.COMPONENT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ComponentEditPolicy createComponentEditPolicy() {
        return new DiagramComponentEditPolicy();
    }

    /**
     * 그래픽칼 에디트 폴리시(컨넥션정책)를 생성한다. EditPolicy.GRAPHICAL_NODE_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        return new DiagramGraphicalNodeEditPolicy();
    }

    /**
     * 
     * 
     * @return LayoutEditPolicy
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new DiagramLayoutEditPolicy();
    }

    /**
     * TODO 메소드 설명
     * 
     * @return
     */
    protected ContainerEditPolicy createContainerEditPolicy() {
        return new DiagramContainerEditPolicy();
    }

    /**
     * 다이렉트 에디트 폴리시(직접편집)를 생성한다. EditPolicy.DIRECT_EDIT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected DirectEditorPolicy createDirectEditorPolicy() {
        return new DirectEditorPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            super.activate();
            EObject model = (EObject) getModel();
            model.eAdapters().add(this);
            if (model instanceof AbstractNode) {
                AbstractNode node = (AbstractNode) model;
                if (!NodeType.NOTE.equals(node.getNodeType())
                    && !NodeType.TEXT.equals(node.getNodeType())
                    && !NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType())) {
                    Element element = node.getUmlModel();
                    element.eAdapters().add(this);                    

                    for (Stereotype stereotype : (element).getAppliedStereotypes()) {
                        stereotype.eAdapters().add(this);
                    }

                    for (EAnnotation annotation : (element).getEAnnotations()) {
                        annotation.eAdapters().add(this);
                    }

                }
                if (NodeType.NOTE.equals(node.getNodeType())
                    || NodeType.TEXT.equals(node.getNodeType())) {
                    model.eAdapters().add(this);
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
            model.eAdapters().remove(this);
            if (model instanceof AbstractNode) {
                AbstractNode node = (AbstractNode) model;
                if (!NodeType.NOTE.equals(node.getNodeType())
                    && !NodeType.TEXT.equals(node.getNodeType())
                    && !NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType())) {
                    Element element = node.getUmlModel();
                    element.eAdapters().remove(this);                    

                    for (Stereotype stereotype : (element).getAppliedStereotypes()) {
                        stereotype.eAdapters().remove(this);
                    }

                    for (EAnnotation annotation : (element).getEAnnotations()) {
                        annotation.eAdapters().remove(this);
                    }

                }
                if (NodeType.NOTE.equals(node.getNodeType())
                    || NodeType.TEXT.equals(node.getNodeType())) {
                    model.eAdapters().remove(this);
                }
            }
        }
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
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        EObject model = ((AbstractNode) getModel()).getUmlModel();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNewValue() instanceof AbstractConnection) {
                    switch (featureID) {
                        case UMLDiagramPackage.NOTATION_NODE__INCOMING_CONNECTION_LIST:
                            refreshTargetConnections();
                            break;
                        case UMLDiagramPackage.NOTATION_NODE__OUTGOING_CONNECTION_LIST:
                            refreshSourceConnections();
                            break;
                        default:
                            break;
                    }
                } else if (notification.getNewValue() instanceof StructuralFeature
                    || notification.getNewValue() instanceof BehavioralFeature) {
                    refreshChildren();
                    refreshVisuals();
                } else if (notification.getNewValue() instanceof NotationNode) {
                    refreshChildren();
                } else {

                    if (model instanceof AbstractNode) {
                        if (!NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                            && !NodeType.TEXT.equals(((AbstractNode) model).getNodeType())
                            && !NodeType.LIFE_LINE_BEHAVIOR.equals(((AbstractNode) model).getNodeType())) {}
                    }
                    if (notification.getNewValue() instanceof EAnnotation) {
                        if (!((EAnnotation) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                        }
                    }
                    if (notification.getNewValue() instanceof EStringToStringMapEntryImpl) {
                        if (!((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().add(this);
                        }
                    }
                    refreshChildren();
                    refreshVisuals();
                }
                break;
            case Notification.REMOVE:
                refreshChildren();
                refreshVisuals();
            case Notification.REMOVE_MANY:
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshVisuals();
                break;
            case Notification.SET:
                if (model instanceof AbstractNode) {
                    if (!NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                        && !NodeType.TEXT.equals(((AbstractNode) model).getNodeType())
                        && !NodeType.LIFE_LINE_BEHAVIOR.equals(((AbstractNode) model).getNodeType())) {}
                }
                if (notification.getNewValue() instanceof EAnnotation) {
                    ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                }
                refreshChildren();
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelSourceConnections() {
        return ((AbstractNode) getModel()).getOutgoingConnectionList();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelTargetConnections() {
        return ((AbstractNode) getModel()).getIncomingConnectionList();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connectionEditPart) {
        int horizontalScrollPoint = 0;
        int verticalScrollPoint = 0;
        if (connectionEditPart.getTarget() != null && connectionEditPart.getSource() != null) {
            AbstractNode sourceNode = (AbstractNode) connectionEditPart.getSource().getModel();
            AbstractNode targetNode = (AbstractNode) connectionEditPart.getTarget().getModel();

            if (sourceNode.equals(targetNode)) {
                try {
                    horizontalScrollPoint = ((FigureCanvas) getViewer().getControl()).getHorizontalBar().getSelection();
                    verticalScrollPoint = ((FigureCanvas) getViewer().getControl()).getVerticalBar().getSelection();
                } catch (Exception error) {
                    Log.error(error);
                }
                Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
                sourceAnchorDimension.setWidth(sourceNode.getX() + sourceNode.getWidth() - horizontalScrollPoint);
                sourceAnchorDimension.setHeight(sourceNode.getY() + (int) (sourceNode.getHeight() / 2)
                    - verticalScrollPoint);

                XYAnchor anchor = new XYAnchor(new Point(sourceAnchorDimension.getWidth(),
                    sourceAnchorDimension.getHeight()));
                return anchor;
            } else {
                ChopboxAnchor anchor = new ChopboxAnchor(getFigure());
                return anchor;
            }
        } else {
            ChopboxAnchor anchor = new ChopboxAnchor(getFigure());
            return anchor;
        }
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connectionEditPart) {
        int horizontalScrollPoint = 0;
        int verticalScrollPoint = 0;

        if (connectionEditPart.getTarget() != null && connectionEditPart.getSource() != null) {
            AbstractNode sourceNode = (AbstractNode) connectionEditPart.getSource().getModel();
            AbstractNode targetNode = (AbstractNode) connectionEditPart.getTarget().getModel();

            if (sourceNode.equals(targetNode)) {
                try {
                    horizontalScrollPoint = ((FigureCanvas) getViewer().getControl()).getHorizontalBar().getSelection();
                    verticalScrollPoint = ((FigureCanvas) getViewer().getControl()).getVerticalBar().getSelection();
                } catch (Exception error) {
                    Log.error(error);
                }
                Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
                targetAnchorDimension.setWidth(targetNode.getX() + (int) (targetNode.getWidth() / 2)
                    - horizontalScrollPoint);
                targetAnchorDimension.setHeight(targetNode.getY() + targetNode.getHeight() - verticalScrollPoint);

                XYAnchor anchor = new XYAnchor(new Point(targetAnchorDimension.getWidth(),
                    targetAnchorDimension.getHeight()));
                return anchor;

            } else {
                ChopboxAnchor anchor = new ChopboxAnchor(getFigure());
                return anchor;
            }
        } else {
            ChopboxAnchor anchor = new ChopboxAnchor(getFigure());
            return anchor;
        }
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
    }

    /**
     * return provided/required Interface, stereotype,name,
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {

        ContainerNode node = (ContainerNode) this.getModel();

        List list = new ArrayList();
           
        if(node.getNodeList().size() > 0) {
            list.addAll(node.getNodeList());
        }
        
        return list;
        
    }
    
    
//    /**
//     * @see org.eclipse.gef.editparts.AbstractEditPart#eraseSourceFeedback(org.eclipse.gef.Request)
//     */
//    @Override
//    public void eraseSourceFeedback(Request request) {
//        try {
//            if (isActive()) {
//                EditPolicyIterator iter = getEditPolicyIterator();
//                while (iter.hasNext()) {
//                    EditPolicy next = iter.next();            
//                    if(next instanceof NonResizableEditPolicy) {
//                        continue;
//                    }
//                    next.eraseSourceFeedback(request);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
}
