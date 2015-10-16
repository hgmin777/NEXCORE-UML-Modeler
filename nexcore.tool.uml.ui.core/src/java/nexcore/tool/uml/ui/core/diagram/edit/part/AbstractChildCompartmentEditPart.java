/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : AbstractChildCompartmentEditPart</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public abstract class AbstractChildCompartmentEditPart extends AbstractGraphicalEditPart implements Adapter {

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshVisuals();
                refreshChildren();
            case Notification.SET:
                refreshVisuals();
                refreshChildren();
                break;
            default:
                break;
        }

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    @Override
    public void activate() {
        if (!isActive()) {
            super.activate();
            Element model = (Element) getModel();
            model.eAdapters().add(this);

            AbstractNode node;
            if (model instanceof AbstractNode) {
                node = (AbstractNode) model;
                node.getUmlModel().eAdapters().add(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
            Element model = (Element) getModel();
            model.eAdapters().remove(this);

            AbstractNode node;
            if (model instanceof AbstractNode) {
                node = (AbstractNode) model;
                node.getUmlModel().eAdapters().remove(this);
            }
        }
    }

    /** directManager */
    protected DirectEditManager directManager = null;

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            performDirectEdit(req);
            return;
        }
        super.performRequest(req);
    }

    /**
     * performDirectEdit void
     */
    protected void performDirectEdit(Request req) {
        if (directManager == null) {
            directManager = new DirectEditorManager(this,
                TextCellEditor.class,
                new DirectEditCellEditorLocator(getFigure()));
        }
        directManager.show();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, createSizeableEditPolicy());
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
     * 다이렉트 에디트 폴리시(직접편집)를 생성한다. EditPolicy.DIRECT_EDIT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected DirectEditPolicy createDirectEditorPolicy() {
        return new DirectEditorPolicy();
    }

    /**
     * createSizeableEditPolicy
     * 
     * @return EditPolicy
     */
    private EditPolicy createSizeableEditPolicy() {
        return new NonResizableEditPolicy() {
            @Override
            protected void setSelectedState(int type) {
                if (getHost().getParent().getParent().getSelected() == EditPart.SELECTED
                    || getHost().getParent().getParent().getSelected() == EditPart.SELECTED_PRIMARY) {
                    super.setSelectedState(EditPart.SELECTED_NONE);
                } else {
                    super.setSelectedState(type);
                }
            }
        };
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected abstract IFigure createFigure();

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
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
    }

    /**
     * isSelected
     */
    private boolean isSelected = false;

    /**
     * isSelected
     *  
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * setSelected
     *  
     * @param isSelected void
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        if (isSelected) {
            return true;
        }
        if (getParent().getSelected() == 2 || getChildrenSelected() || getSameLevelSelected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSameLevelSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getSameLevelSelected() {
        List<EditPart> editParts = getParent().getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * getChildrenSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getChildrenSelected() {
        List<EditPart> editParts = getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        if (super.getSelected() == 2) {
            isSelected = true;
        } else if (super.getSelected() == 0) {
            isSelected = false;
        }
        return super.getSelected();
    }

    /**
     * editpartLevel
     */
    protected static int editpartLevel = 2;
}
