/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramGraphicalNodeEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.IUMLFigure;
import nexcore.tool.uml.ui.core.diagram.figure.NoteFigure;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.edit.part</li>
 * <li>설 명 : AbstractNotationNodeEditPart</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public abstract class AbstractNotationNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart, Adapter {
    /**
     * AbstractNotationNodeEditPart
     */
    public AbstractNotationNodeEditPart() {

    }

    /**
     * isValid
     *  
     * @param umlModel
     * @return boolean
     */
    protected boolean isValid(EObject umlModel) {
        URI uri = EcoreUtil.getURI(umlModel);

        boolean resourceExists = true;
        
        try {
            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
            resourceExists = resourceSet.getURIConverter().exists(uri, null);
        } catch (Exception e) {
            resourceExists = false;
        }
        
        if (null == umlModel) {
        	return false;
        }
        
        if (DomainUtil.isProxy(umlModel)) {
        	return false;
        }
        
        return resourceExists;
        
        
//        return ((null != umlModel && !umlModel.eIsProxy()) && resourceExists);
    }
    
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
    @SuppressWarnings("unchecked")
    private void performDirectEdit() {

        IFigure nodeFigure = getFigure();
        if( nodeFigure instanceof org.eclipse.draw2d.Label ) { 
            figure = getFigure();
            if (directManager == null) {
                setDirectManager(new DirectEditorManager(this,
                    TextCellEditor.class,
                    new DirectEditCellEditorLocator(figure)));
            }
            directManager.show();
        } else if ( nodeFigure instanceof NoteFigure || nodeFigure instanceof TextFigure) { 
          
            if (directManager == null) {
                setDirectManager(new DirectEditorManager((GraphicalEditPart) this,
                    TextCellEditor.class,
                    new DirectEditCellEditorLocator(figure)));
            }
            directManager.show();
            
        } else {
            getChildFigure(this);
        }

    }

    /**
     * 
     *   void
     */
    private void getChildFigure(EditPart currentEditPart) {
        List<EditPart> childrenEditPart = currentEditPart.getChildren();
        for( EditPart child : childrenEditPart ) {
            if( child instanceof NotationNameEditPart ) {
                if( ((NotationNameEditPart)child).getFigure() instanceof org.eclipse.draw2d.Label ) {
                    
                    GraphicalEditPart gEdit = (GraphicalEditPart) child;
                    
                    setDirectManager(new DirectEditorManager((GraphicalEditPart) child,
                        TextCellEditor.class,
                        new DirectEditCellEditorLocator(gEdit.getFigure())));
                    directManager.show();
                    
                    break;
                } else {
                    getChildFigure(child);
                }
            }
        }
    }

    /**
     * 
     * 
     * @param manager
     *            void
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
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
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
                if (!NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                    && !NodeType.TEXT.equals(((AbstractNode) model).getNodeType())
                    && !NodeType.LIFE_LINE_BEHAVIOR.equals(((AbstractNode) model).getNodeType())) {
                    model = ((AbstractNode) getModel()).getUmlModel();
                    if( model == null ) {
                        return;
                    }
                    model.eAdapters().add(this);

                    model = ((AbstractNode) getModel()).getUmlModel();
                    for (Stereotype stereotype : ((Element) model).getAppliedStereotypes()) {
                        stereotype.eAdapters().add(this);
                    }

                    model = ((AbstractNode) getModel()).getUmlModel();
                    for (EAnnotation annotation : ((EModelElement) model).getEAnnotations()) {
                        annotation.eAdapters().add(this);
                    }

                }
                model = (EObject) getModel();
                if (NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                    || NodeType.TEXT.equals(((AbstractNode) model).getNodeType())) {
                    model.eAdapters().add(this);
                }
            }

            if (getModel() instanceof NotationNode) {
                NotationNode node = (NotationNode) getModel();
                List<AbstractNode> children = node.getCompartmentList();
                for (AbstractNode child : children) {
                    child.eAdapters().add(this);
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
            if( getModel() != null) {
                EObject model = (EObject) getModel();
                model.eAdapters().remove(this);
                if (model instanceof AbstractNode) {
                    if (!NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                    && !NodeType.TEXT.equals(((AbstractNode) model).getNodeType())
                    && !NodeType.LIFE_LINE_BEHAVIOR.equals(((AbstractNode) model).getNodeType())) {
                        model = ((AbstractNode) getModel()).getUmlModel();
                        model.eAdapters().remove(this);
                        
                        model = ((AbstractNode) getModel()).getUmlModel();
                        for (Stereotype stereotype : ((Element) model).getAppliedStereotypes()) {
                            stereotype.eAdapters().remove(this);
                        }
                        
                        model = ((AbstractNode) getModel()).getUmlModel();
                        for (EAnnotation annotation : ((EModelElement) model).getEAnnotations()) {
                            annotation.eAdapters().remove(this);
                        }
                    }
                    
                    model = (EObject) getModel();
                    if (NodeType.NOTE.equals(((AbstractNode) model).getNodeType())
                    || NodeType.TEXT.equals(((AbstractNode) model).getNodeType())) {
                        model.eAdapters().remove(this);
                    }
                }
                
                if (getModel() instanceof NotationNode) {
                    NotationNode node = (NotationNode) getModel();
                    List<AbstractNode> children = node.getCompartmentList();
                    for (AbstractNode child : children) {
                        child.eAdapters().remove(this);
                    }
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
        if (!(getModel() instanceof NotationNode)) {
            return;
        }
        NotationNode notationNode = (NotationNode) getModel();
        if (notationNode.getNodeType().equals(NodeType.INITIAL_NODE)
            || notationNode.getNodeType().equals(NodeType.ACTIVITY_FINAL_NODE)
            || notationNode.getNodeType().equals(NodeType.MERGE_NODE)
            || notationNode.getNodeType().equals(NodeType.DECISION_NODE)
            || notationNode.getNodeType().equals(NodeType.FORK_NODE)
            || notationNode.getNodeType().equals(NodeType.JOIN_NODE)) {
            return;
        }
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
                    if (notification.getNewValue() instanceof EAnnotation) {
                        if (!((EAnnotation) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                        }
                    }
                    if (notification.getNewValue() instanceof EStringToStringMapEntryImpl) {
                        if (!((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().add(this);
                        }
                        if (notification.getNotifier() instanceof Element) {
                            ViewModelUtil.setModelInfo((NotationNode) getModel());
                        }
                    }
                    refreshChildren();
                    refreshVisuals();
                }
                if (getModel() instanceof NotationNode) {

                }
                break;
            case Notification.REMOVING_ADAPTER:
            case Notification.REMOVE:
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
                if (notification.getNotifier() instanceof Element) {
                    if (!(notification.getNotifier() instanceof AbstractNode)) {
                        ViewModelUtil.setModelInfo((NotationNode) getModel());
                    }
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
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            if (getFigure() instanceof IUMLFigure) {
                IUMLFigure figure = (IUMLFigure) getFigure();
                NotationNode notationNode = (NotationNode) getModel();

                EObject umlModel = notationNode.getUmlModel();
                if (umlModel != null && isValid(umlModel)) {
                    figure.setDangling(false);
                } else {
                    figure.setDangling(true);
                }

                figure.refresh();

            }
        } catch (Exception e) {}

        super.refreshVisuals();
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
        if (!(this.getModel() instanceof NotationNode)) {
            return new ArrayList<Object>();
        }
        final NotationNode viewModel = (NotationNode) this.getModel();

        Object object = viewModel.getUmlModel();
        if (!(object instanceof NamedElement)) {
            return Collections.EMPTY_LIST;
        }
        return ((NotationNode) getModel()).getCompartmentList();
    }

    /**
     * getFillColor
     * 
     * @return RGB
     */
    protected RGB getFillColor() {
        RGB nodeColor = PreferenceUtil.INSTANCE.getNodeFillColor();
        NotationNode viewModel = (NotationNode) getModel();
        if (viewModel != null) {
            if (viewModel.getFillColor() != null) {
                nodeColor = StringConverter.asRGB(viewModel.getFillColor());
            } else {
                if (NodeType.INITIAL_NODE.equals(viewModel.getNodeType())
                    || NodeType.ACTIVITY_FINAL_NODE.equals(viewModel.getNodeType())) {
                    RGB blackColor = UiCorePlugin.getDefault().getColor("Black").getRGB();
                    nodeColor = blackColor;
                }
                if (viewModel.getNodeType().equals(NodeType.ACTOR)) {
                    nodeColor = ColorConstants.lightGray.getRGB();
                }
                if (viewModel.getNodeType().equals(NodeType.JOIN_NODE)) {
                    nodeColor = new RGB(240, 240, 240);
                }
                if (viewModel.getNodeType().equals(NodeType.FORK_NODE)) {
                    nodeColor = new RGB(240, 240, 240);
                }
            }
        }

        return nodeColor;
    }

    /**
     * getFontColor
     * 
     * @return RGB
     */
    protected RGB getFontColor() {
        RGB fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();
        NotationNode viewModel = (NotationNode) getModel();
        if (viewModel != null) {
            if (viewModel.getFontColor() != null) {
                fontColor = StringConverter.asRGB(viewModel.getFontColor());
            }
        }
        return fontColor;
    }

    /** isSelected */
    private boolean isSelected = false;

    /**
     * 
     * 
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * 
     * 
     * @param isSelected
     *            void
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        return super.isSelectable();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        return super.getSelected();
    }

    /** editpartLevel */
    protected static int editpartLevel = 0;
}
