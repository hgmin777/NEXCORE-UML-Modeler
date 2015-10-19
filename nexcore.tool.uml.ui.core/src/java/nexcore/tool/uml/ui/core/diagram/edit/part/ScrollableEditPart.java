/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : ScrollableEditPart</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ScrollableEditPart extends AbstractParentCompartmentEditPart implements Adapter {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getContentPane()
     */
    @Override
    public IFigure getContentPane() {
        return ((ScrollPane) this.getFigure()).getContents();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createChild(java.lang.Object)
     */
    @Override
    protected EditPart createChild(Object model) {
        if (getParent() != null && getParent().getParent() != null) {
            return getViewer().getEditPartFactory().createEditPart(this, model);
        } else {
            return ((AbstractDiagramEditor) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getEditPartFactory().createEditPart(this, model);
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
        // return super.getRoot();
    }

    /**
     * isCreated
     */
    private boolean isCreated = false;

    /**
     * isCreated
     *  
     * @return boolean
     */
    public boolean isCreated() {
        return isCreated;
    }

    /**
     * setCreated
     *  
     * @param bool void
     */
    public void setCreated(boolean bool) {
        isCreated = bool;
    }

    /**
     * addedElement
     */
    private Element addedElement;

    /**
     * getAdded
     *  
     * @return Element
     */
    public Element getAdded() {
        return addedElement;
    }

    /**
     * setAdded
     *  
     * @param element void
     */
    public void setAdded(Element element) {
        this.addedElement = element;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {

        // ViewModelUtil util = new ViewModelUtil((NotationNode) getModel());
        // util.setModelInfo();
        if(!isActive()){
            return;
        }
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNotifier() instanceof org.eclipse.uml2.uml.Class) {
                    if (notification.getNewValue() instanceof Element) {
                        setCreated(false);
                        setAdded((Element) notification.getNewValue());
                    }

                }
                Display.getDefault().syncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        refreshVisuals();
                        refreshChildren();
                    }
                });
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Display.getDefault().syncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        refreshVisuals();
                        refreshChildren();
                    }
                });
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                Display.getDefault().syncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        refreshVisuals();
                        refreshChildren();
                    }
                });
            case Notification.SET:
                Display.getDefault().syncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        refreshVisuals();
                        refreshChildren();
                    }
                });
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
            AbstractNode model = (AbstractNode) getModel();
            model.eAdapters().add(this);
            if (model.getUmlModel() instanceof NamedElement) {
                ((NamedElement) (model.getUmlModel())).eAdapters().add(this);
            }

            EObject object = (EObject) getParent().getModel();
            object.eAdapters().add(this);
            if (((AbstractView) object).getUmlModel() != null) {
                ((AbstractView) object).getUmlModel().eAdapters().add(this);
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
            AbstractNode model = (AbstractNode) getModel();
            model.eAdapters().remove(this);
            if (model.getUmlModel() instanceof Classifier) {
                ((Classifier) (model.getUmlModel())).eAdapters().remove(this);
            }

            EObject object = (EObject) getParent().getModel();
            object.eAdapters().add(this);
            if (((AbstractView) object).getUmlModel() != null) {
                ((AbstractView) object).getUmlModel().eAdapters().remove(this);
            }

        }

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        ScrollPane scrollPane = null;
        final IFigure rec = new RectangleFigure();
        scrollPane = new ScrollPane();

        GridLayout layout = new GridLayout(1, false);

        rec.setForegroundColor(ColorConstants.lightGray);
        rec.setLayoutManager(layout);

        scrollPane.setScrollBarVisibility(ScrollPane.AUTOMATIC);
        scrollPane.setContents(rec);
        scrollPane.setOpaque(true);
//        scrollPane.setHorizontalScrollBarVisibility(ScrollPane.NEVER);
//        scrollPane.setVerticalScrollBarVisibility(ScrollPane.NEVER);

        LayoutManager layoutParent = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;
        if (layoutParent instanceof GridLayout) {
            gridLayout = (GridLayout) layoutParent;
            gridData = new GridData();
            gridData.grabExcessHorizontalSpace = true;
            gridData.horizontalAlignment = GridData.FILL;
             gridData.grabExcessVerticalSpace = true;
             gridData.verticalAlignment = GridData.FILL;
            gridLayout.setConstraint(scrollPane, gridData);
            gridLayout.marginHeight = gridLayout.marginWidth = 0;
        }

        return scrollPane;
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
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
    }
}
