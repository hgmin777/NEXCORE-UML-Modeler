/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : NotationNameEditPart</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class NotationNameEditPart extends ScrollableEditPart {

    /** image */
    private Image image;

    /** defaultFont */
    private Font defaultFont = UiCorePlugin.getDefault().getFont("default");

    /** italicFont */
    private Font italicFont = UiCorePlugin.getDefault().getFont("italic");

    /** topMarginborder */
    private MarginBorders topMarginBorder = new MarginBorders(5, 0, 0, 0);

    /** defaultMarginBorder */
    private MarginBorders defaultMarginBorder = new MarginBorders(0, 0, 0, 0);

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {

        Label label = new Label();

        AbstractNode node = (AbstractNode) getParent().getModel();
        NamedElement element = null;
        if (node.getUmlModel() instanceof NamedElement) {
            element = (NamedElement) node.getUmlModel();
        }
        if (element == null || DomainUtil.isProxy(element)) {
            if(node.getName() != null){
                label.setText(node.getName());
            }
        } else {
            label.setText(element.getName());
        }

        NodeType nodeType = node.getNodeType();

        if (nodeType.equals(NodeType.COMPONENT)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_COMPONENT);
        } else if (nodeType.equals(NodeType.CLASS)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CLASS);
        } else if (nodeType.equals(NodeType.PACKAGE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE);
        } else if (nodeType.equals(NodeType.ARTIFACT)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ARTIFACT);
        } else if (nodeType.equals(NodeType.COLLABORATION)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_COLLABORATION);
        } else if (nodeType.equals(NodeType.DATA_TYPE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_DATATYPE);
        } else if (nodeType.equals(NodeType.ENUMERATION)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ENUMERATION);
        } else if (nodeType.equals(NodeType.INTERFACE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_INTERFACE);
        } else if (nodeType.equals(NodeType.USE_CASE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_USECASE);
        } else if (nodeType.equals(NodeType.LIFELINE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_LIFELINE);
        } else if (nodeType.equals(NodeType.OPAQUE_ACTION)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_OPAQUEACTION);
        } else if (nodeType.equals(NodeType.DATA_STORE_NODE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_DATASTORENODE);
        } else if (nodeType.equals(NodeType.CENTRAL_BUFFER_NODE)) {
            image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE);
        }

        if (node.getUmlModel() instanceof Classifier) {
            Classifier classifierNode = (Classifier) node.getUmlModel();
            if (classifierNode.isAbstract()) {
                label.setFont(italicFont);
            } else {
                label.setFont(defaultFont);
            }
        } else {
            label.setFont(defaultFont);
        }

        label.setIcon(image);
        label.setBorder(topMarginBorder);

        setFigureLayout(label);

        label.setToolTip(new Label(label.getText()));
        label.setTextAlignment(PositionConstants.CENTER);

        if (((AbstractNode) node).getNodeType().equals(NodeType.PROVIDED_INTERFACES)) {
            label.setText(UMLMessage.LABEL_PROVIDED_INTERFACE);// "provided Interfaces");
            label.setToolTip(new Label(label.getText()));
            setFigureLayout(label, GridData.BEGINNING);
        } else if (((AbstractNode) node).getNodeType().equals(NodeType.REQUIRED_INTERFACES)) {
            label.setText(UMLMessage.LABEL_REQUIRED_INTERFACE);// "Required Interfaces");
            label.setToolTip(new Label(label.getText()));
            setFigureLayout(label, GridData.BEGINNING);
        } else if (((AbstractNode) node).getNodeType().equals(NodeType.ATTRIBUTES)) {
            label.setText(UMLMessage.LABEL_ATTRIBUTES);// "Required Interfaces");
            label.setToolTip(new Label(label.getText()));
            setFigureLayout(label, GridData.BEGINNING);
        } else if (((AbstractNode) node).getNodeType().equals(NodeType.OPERATIONS)) {
            label.setText(UMLMessage.LABEL_OPERATIONS);// "Required Interfaces");
            label.setToolTip(new Label(label.getText()));
            setFigureLayout(label, GridData.BEGINNING);
        }

        return label;
    }

    /**
     * 
     * 
     * @param label
     *            void
     */
    private void setFigureLayout(Label label) {
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
    }

    /**
     * 
     * 
     * @param label
     * @param alignment
     *            void
     */
    private void setFigureLayout(Label label, int alignment) {
        LayoutManager layout = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;
        if (layout instanceof GridLayout) {
            gridLayout = (GridLayout) layout;
            gridData = new GridData();
            gridData.horizontalAlignment = alignment;
            gridData.grabExcessHorizontalSpace = true;
            gridLayout.setConstraint(label, gridData);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            if (getParent() != null) {
                AbstractNode node = (AbstractNode) getParent().getModel();

                Label label = (Label) getFigure();
                NamedElement element = null;
                if (node.getUmlModel() instanceof NamedElement) {
                    element = (NamedElement) node.getUmlModel();
                }
                if (element == null || DomainUtil.isProxy(element)) {
                    if(node.getName() != null) {
                        label.setText(node.getName());
                    }
                } else {
                    label.setText(element.getName());
                }

                if (node.getUmlModel() instanceof Classifier) {
                    Classifier classifierNode = (Classifier) node.getUmlModel();
                    if (classifierNode.isAbstract()) {
                        label.setFont(italicFont);
                    } else {
                        label.setFont(defaultFont);
                    }
                } else {
                    label.setFont(defaultFont);
                }
                label.setBorder(topMarginBorder);
                label.setToolTip(new Label(label.getText()));
                label.setTextAlignment(PositionConstants.CENTER);
            }
            getFigure().setForegroundColor(new Color(null, getFontColor()));

            boolean isDirectEdit = true;
            if (getParent() != null && getParent() instanceof ScrollableEditPart) {
                AbstractNode node = (AbstractNode) getParent().getModel();
                Label label = (Label) getFigure();
                if (((AbstractNode) node).getNodeType().equals(NodeType.PROVIDED_INTERFACES)) {
                    label.setText(UMLMessage.LABEL_PROVIDED_INTERFACE);
                    label.setForegroundColor(ColorConstants.lightGray);
                    label.setBorder(defaultMarginBorder);
                    label.setToolTip(new Label(label.getText()));
                    isDirectEdit = false;
                } else if (((AbstractNode) node).getNodeType().equals(NodeType.REQUIRED_INTERFACES)) {
                    label.setText(UMLMessage.LABEL_REQUIRED_INTERFACE);
                    label.setForegroundColor(ColorConstants.lightGray);
                    label.setBorder(defaultMarginBorder);
                    label.setToolTip(new Label(label.getText()));
                    isDirectEdit = false;
                }
                setFigureLayout(label, GridData.BEGINNING);
            } else if (getParent() != null && getParent() instanceof AbstractChildCompartmentEditPart) {
                Label label = (Label) getFigure();
                label.setBorder(defaultMarginBorder);
                setFigureLayout(label, GridData.BEGINNING);
                isDirectEdit = false;
            }

            if (isDirectEdit) {
                if (((GraphicalViewer) getViewer()).getEditDomain() != null) {
                    if (((GraphicalViewer) getViewer()).getEditDomain().getActiveTool() instanceof CreationTool) {
                        if (directManager == null) {
                            directManager = new DirectEditorManager(this,
                            TextCellEditor.class,
                            new DirectEditCellEditorLocator(getFigure()));
                            directManager.show();
                        }
                    }
                }
            }

        } catch (Exception e) {
            Log.error("NotationNameEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * getFontColor
     * 
     * @return RGB
     */
    protected RGB getFontColor() {
        RGB fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();
        AbstractView viewModel = (AbstractView) ((AbstractView) getModel()).getParent();
        if (viewModel != null) {
            if (viewModel.getFontColor() != null) {
                fontColor = StringConverter.asRGB(viewModel.getFontColor());
            }
        }
        return fontColor;
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

        if (getParent() != null && getParent() instanceof ScrollableEditPart) {
            AbstractNode node = (AbstractNode) getParent().getModel();
            Label label = (Label) getFigure();
            if (((AbstractNode) node).getNodeType().equals(NodeType.PROVIDED_INTERFACES)) {
                return;
            } else if (((AbstractNode) node).getNodeType().equals(NodeType.REQUIRED_INTERFACES)) {
                return;
            }
            setFigureLayout(label, GridData.BEGINNING);
        } else if (getParent() != null && getParent() instanceof AbstractChildCompartmentEditPart) {
            return;
        }

        if (directManager == null) {
            directManager = new DirectEditorManager(this,
                TextCellEditor.class,
                new DirectEditCellEditorLocator(getFigure()));
        }
        directManager.show();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        EObject object = (EObject) getParent().getModel();
        object.eAdapters().add(this);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        EObject object = (EObject) getParent().getModel();
        object.eAdapters().remove(this);
    }
}
