/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.LifeLineComponentEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.LifeLineFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : LifeLineEditPart</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineEditPart extends AbstractGraphicalEditPart implements Adapter {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();

        LifeLineFigure figure = new LifeLineFigure();

        figure.setName(notationNode.getName());
        figure.setSize(notationNode.getWidth(), notationNode.getHeight());
        figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));

        return figure;

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LifeLineComponentEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            super.activate();
            EObject model = (EObject) getModel();
            model.eAdapters().add(this);
            model = ((AbstractNode) getModel()).getUmlModel();
            if (null == model) {
                return;
            }
            model.eAdapters().add(this);
            if (((Lifeline) model).getRepresents() != null) {
                ((Lifeline) model).getRepresents().eAdapters().add(this);
            }

            if (this.getModel() instanceof NotationNode) {
                AbstractView viewModel = (AbstractView) this.getModel();
                viewModel.getUmlModel().eAdapters().add(this);
                if (viewModel.getUmlModel() instanceof Lifeline
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                    Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                    if (!type.eAdapters().contains(this)) {
                        type.eAdapters().add(this);
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
            model.eAdapters().remove(this);
            model = ((AbstractNode) getModel()).getUmlModel();
            model.eAdapters().remove(this);
            if (((Lifeline) model).getRepresents() != null) {
                ((Lifeline) model).getRepresents().eAdapters().remove(this);
            }

        }

        if (this.getModel() instanceof NotationNode) {
            AbstractView viewModel = (AbstractView) this.getModel();
            viewModel.getUmlModel().eAdapters().remove(this);
            if (viewModel.getUmlModel() instanceof Lifeline
                && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                if (type.eAdapters().contains(this)) {
                    type.eAdapters().remove(this);
                }
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createChild(java.lang.Object)
     */
    @Override
    protected EditPart createChild(Object model) {
        if (getParent() != null && getParent().getParent() != null) {
            return getViewer().getEditPartFactory().createEditPart(this, model);
        } else {
            if (model instanceof Line) {
                LineEditPart editpart = new LineEditPart();
                editpart.setParent(this);
                editpart.setModel(model);
                return editpart;
            } else {
                return ((AbstractDiagramEditor) PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage()
                    .getActiveEditor()).getDiagramGraphicalViewer().getEditPartFactory().createEditPart(this, model);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getRoot()
     */
    @Override
    public RootEditPart getRoot() {
        if (getParent() != null && getParent().getParent() != null) {
            return super.getRoot();
        } else {
            return ((AbstractDiagramEditor) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getRootEditPart();
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        Tool activeTool = getViewer().getEditDomain().getActiveTool();
        return super.isSelectable();
    }

    /** isSelected */
    private boolean isSelected = false;

    /**
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param isSelected
     *            void
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                refreshChildren();
                refreshVisuals();
                break;
            case Notification.REMOVE:
                refreshChildren();
                refreshVisuals();

                if (this.getModel() instanceof NotationNode) {
                    AbstractView viewModel = (AbstractView) this.getModel();
                    viewModel.getUmlModel().eAdapters().remove(this);
                    if (viewModel.getUmlModel() instanceof Lifeline
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                        Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                        if (type.eAdapters().contains(this)) {
                            type.eAdapters().remove(this);
                        }
                    }
                }
            case Notification.REMOVE_MANY:
                refreshVisuals();

                if (this.getModel() instanceof NotationNode) {
                    AbstractView viewModel = (AbstractView) this.getModel();
                    viewModel.getUmlModel().eAdapters().remove(this);
                    if (viewModel.getUmlModel() instanceof Lifeline
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                        Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                        if (type.eAdapters().contains(this)) {
                            type.eAdapters().remove(this);
                        }
                    }
                }
                break;
            case Notification.MOVE:
            case Notification.UNSET:
            case Notification.SET:
                refreshChildren();
                refreshVisuals();
                for (int i = 0; i < getChildren().size(); i++) {
                    ((EditPart) getChildren().get(i)).refresh();
                }

                if (this.getModel() instanceof NotationNode) {
                    AbstractView viewModel = (AbstractView) this.getModel();
                    viewModel.getUmlModel().eAdapters().add(this);
                    if (viewModel.getUmlModel() instanceof Lifeline
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                        && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                        Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                        if (!type.eAdapters().contains(this)) {
                            type.eAdapters().add(this);
                        }
                    }
                }
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
            NotationNode notationNode = (NotationNode) getModel();
            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                notationNode.getWidth(),
                notationNode.getHeight());
            ((LifeLineFigure) getFigure()).setName(notationNode.getName());
            getFigure().setSize(notationNode.getWidth(), notationNode.getHeight());
            getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));
            if (getFigure().getParent() != null) {
                setLayoutConstraint(this, getFigure(), bounds);
            }
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_LIFELINE_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

    /** nameOfUML */
    private NotationNode nameOfUML;

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    protected List getModelChildren() {
        LifeLineNode node = (LifeLineNode) this.getModel();
        Element element = node.getUmlModel();
        if (!(element instanceof NamedElement)) {
            return Collections.EMPTY_LIST;
        }

        if (!(element instanceof Lifeline)) {
            return Collections.EMPTY_LIST;
        }
        NamedElement namedElement = (NamedElement) element;

        List<Object> list = new ArrayList<Object>();

        if (nameOfUML == null) {
            nameOfUML = UMLDiagramFactory.eINSTANCE.createNotationNode();
            nameOfUML.setUmlModel(namedElement);
            nameOfUML.setNodeType(NodeType.NAME);
            list.add(nameOfUML); // Element Name
        } else {
            list.add(nameOfUML); // Element Name
        }

        if (node.getLine() != null) {
            list.add(node.getLine());
        }

        if (node.getBehaviorList() != null && node.getBehaviorList().size() > 0) {
            list.addAll(node.getBehaviorList());
        }
        return list;
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
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    public void eraseSourceFeedback(Request request) {
        if (isActive()) {
            EditPolicyIterator iter = getEditPolicyIterator();
            while (iter.hasNext()) {
                EditPolicy nextEditPolicy = iter.next();
                if(nextEditPolicy instanceof ResizableEditPolicy) {
                    List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                    if(null == feedbackList || feedbackList.isEmpty()){
                        continue;
                    }
                }
                nextEditPolicy.eraseSourceFeedback(request);
            }
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#deactivateEditPolicies()
     */
    protected void deactivateEditPolicies() {
        EditPolicyIterator iter = getEditPolicyIterator();
        while (iter.hasNext()) {
            EditPolicy nextEditPolicy = iter.next();
            if(nextEditPolicy instanceof ResizableEditPolicy) {
                List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                if(null == feedbackList || feedbackList.isEmpty()){
                    continue;
                }
            }
            nextEditPolicy.deactivate();
        }
    }


}
