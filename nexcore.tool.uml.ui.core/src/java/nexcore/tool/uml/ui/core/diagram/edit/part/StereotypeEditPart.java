/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : StereotypeEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class StereotypeEditPart extends ScrollableEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {

        NamedElement element = (NamedElement) (((NotationNode) this.getModel()).getUmlModel());

        Label label = new Label();
        label.setText(ProjectUtil.getStereotypeLabel(element));
        label.setBorder(new MarginBorders(0, 0, 0, 0));

        label.setFont(UiCorePlugin.getDefault().getFont("default8"));

        LayoutManager layout = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;
        if (layout instanceof GridLayout) {
            gridLayout = (GridLayout) layout;
            gridData = new GridData();
            gridData.horizontalAlignment = GridData.CENTER;
            gridData.grabExcessHorizontalSpace = true;
            gridLayout.setConstraint(label, gridData);
        }
        label.setToolTip(new Label(label.getText()));
        label.setTextAlignment(PositionConstants.CENTER);
        return label;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            Label label = (Label) getFigure();
            NamedElement element = (NamedElement) (((NotationNode) this.getModel()).getUmlModel());
            label.setText(ProjectUtil.getStereotypeLabel(element));
            label.setToolTip(new Label(label.getText()));

            // setFigureLayout(label);

            label.setForegroundColor(new Color(null, (getFontColor())));

        } catch (Exception e) {
            Log.error("StereoTypeEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * getFontColor
     * 
     * @return RGB
     */
    protected RGB getFontColor() {
        RGB fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();
        AbstractView abstractView = (AbstractView) ((AbstractView) getModel()).getParent();
        if (abstractView != null) {
            if (abstractView.getFontColor() != null) {
                fontColor = StringConverter.asRGB(abstractView.getFontColor());
            }
        }
        return fontColor;
    }

    /**
     * resourceSetListener
     */
    ResourceSetListenerImpl resourceSetListener = null;
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#activate()
     */
    @Override
    public void activate() {
        // 2011-08-11 nspark
        // 클래스다이어그램에디터가 오픈된 상태이고 Project Explorer 에서 스테레오타입이 적용된 클래스를 삭제후 undo 하면
        // 클래스다이어그램에 스테레오타입이 표시되지 않는 문제 해결.
        if (resourceSetListener == null) {
            resourceSetListener = new ResourceSetListenerImpl() {
                /**
                 * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
                 */
                @Override
                public void resourceSetChanged(ResourceSetChangeEvent event) {
                    List<Notification> list = event.getNotifications();
                    Notification notification = null;
                    int type = -1;
                    
                    for (Iterator<Notification> it = list.iterator(); it.hasNext();) {
                        try {
                            notification = it.next();
                            if (notification.isTouch()) {
                                continue;
                            }
                            if (notification instanceof NotifyingList) {
                                continue;
                            }
                            type = notification.getEventType();
                            if (notification.getNotifier() instanceof EObject) {
                                Object newValue = notification.getNewValue();
                                
                                switch (type) {
                                    case Notification.SET:
                                    case Notification.ADD:
                                    case Notification.ADD_MANY:
                                        if (newValue instanceof Element) {
                                            if (((Element) notification.getNewValue()).getAppliedStereotypes().size() > 0) {
                                                refresh();
                                            }
                                        } 
                                        break;
                                }
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            DomainRegistry.getUMLDomain().getTransactionalEditingDomain().addResourceSetListener(resourceSetListener);
        }
        
        super.activate();
        AbstractView node = (AbstractView) getModel();
        if(node.getParent() != null) {
            ((AbstractView) node.getParent()).eAdapters().add(this);            
        }
    }
    
            

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (resourceSetListener != null) {
            DomainRegistry.getUMLDomain()
                .getTransactionalEditingDomain()
                .removeResourceSetListener(resourceSetListener);
        }
        super.deactivate();
        AbstractView node = (AbstractView) getModel();
        if (node.getParent() != null) {
            ((AbstractView) node.getParent()).eAdapters().remove(this);
        }
    }

    /**
     * setFigureLayout
     *  
     * @param label void
     */
    private void setFigureLayout(Label label) {
        if (getParent() != null) {
            LayoutManager layout = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
            GridData gridData;
            GridLayout gridLayout;
            if (layout instanceof GridLayout) {
                gridLayout = (GridLayout) layout;
                gridData = new GridData();
                gridData.horizontalAlignment = GridData.CENTER;
                gridData.grabExcessHorizontalSpace = true;
                gridLayout.setConstraint(label, gridData);
            }
            label.setTextAlignment(PositionConstants.CENTER);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DelegatingDragEditPartsTracker(this, getParent());
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

}
