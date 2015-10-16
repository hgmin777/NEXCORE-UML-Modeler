/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설 명 : ShapeSection</li>
 * <li>작성일 : 2011. 4. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ShapeSection extends AbstractPropertyCommonSection {

    /** COLON_TEXT */
    protected static final String COLON_TEXT = ":"; //$NON-NLS-1$

    /** fontColorSelector */
    private ColorSelector fontColorSelector;

    /** fillColorSelector */
    private ColorSelector fillColorSelector;

    /** lineColorSelector */
    // private ColorSelector lineColorSelector;
    /** nodeColor */
    private RGB nodeColor;

    /** lineColor */
    // private RGB lineColor;
    /** fontColor */
    private RGB fontColor;

    /** notationGroup */
    private Group notationGroup;

    /** labelGridData */
    private GridData labelGridData;

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);

        labelGridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        labelGridData.widthHint = 130;
        notationGroup = new Group(sectionComposite, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        notationGroup.setLayout(layout);
        GridData notationGroupGridData = new GridData(GridData.FILL_BOTH);
        notationGroup.setLayoutData(notationGroupGridData);
        notationGroup.setText(UMLMessage.LABEL_PREFERENCE_APPEARANCE_APPEARANCE_STYLE);
        notationGroup.setBackground(UiCorePlugin.getDefault().getColor("White"));
        createFillColor(notationGroup);
        // createLineColor(notationGroup);
        createFontColor(notationGroup);
    }

    /**
     * createFillColor
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    private void createFillColor(Group parent) {
        Label fillColorLabel = new Label(parent, SWT.NONE);
        fillColorLabel.setLayoutData(labelGridData);
        fillColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FILL_COLOR) + COLON_TEXT);
        fillColorLabel.setBackground(UiCorePlugin.getDefault().getColor("White"));
        fillColorSelector = new ColorSelector(parent);
        GridData gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        fillColorSelector.getButton().setLayoutData(gridData);
        fillColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                nodeColor = (RGB) event.getNewValue();
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        AbstractView viewModel = getViewModel();
                        if (viewModel != null) {
                            viewModel.setFillColor(StringConverter.asString(nodeColor));
                        }
                    }
                });
            }
        });
    }

    // /**
    // * createLineColor
    // *
    // * @param colorsAndFontsGroup
    // * void
    // */
    // private void createLineColor(Group parent) {
    // Label lineColorLabel = new Label(parent, SWT.NONE);
    // lineColorLabel.setLayoutData(labelGridData);
    // lineColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_LINE_COLOR)
    // + COLON_TEXT);
    // lineColorLabel.setBackground(UiCorePlugin.getDefault().getColor("White"));
    // lineColorSelector = new ColorSelector(parent);
    // GridData gridData = new GridData();
    // gridData.horizontalSpan = 2;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // lineColorSelector.getButton().setLayoutData(gridData);
    // lineColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // lineColor = (RGB) event.getNewValue();
    // DomainUtil.run(new TransactionalAction() {
    // /**
    // * @see
    // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
    // */
    // @Override
    // public void doExecute() {
    // AbstractView viewModel = getViewModel();
    // if(viewModel != null) {
    // viewModel.setLineColor(StringConverter.asString(lineColor));
    // }
    // }
    // });
    // }
    // });
    // }

    /**
     * @param parent
     */
    private void createFontColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FONT_COLOR) + COLON_TEXT);
        fontColorLabel.setBackground(UiCorePlugin.getDefault().getColor("White"));
        fontColorSelector = new ColorSelector(parent);
        GridData gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        fontColorSelector.getButton().setLayoutData(gridData);
        fontColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                fontColor = (RGB) event.getNewValue();
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        AbstractView viewModel = getViewModel();
                        if (viewModel != null) {
                            viewModel.setFontColor(StringConverter.asString(fontColor));
                        }
                    }
                });

            }
        });

    }

    /**
     * initDefault
     * 
     * void
     */
    protected void initDefault() {

        AbstractView viewModel = getViewModel();
        if (viewModel != null) {
            nodeColor = PreferenceUtil.INSTANCE.getNodeFillColor();
            fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();

            if (viewModel.getFillColor() != null) {
                nodeColor = StringConverter.asRGB(viewModel.getFillColor());
            } else if (viewModel instanceof NotationNode) {
                if (NodeType.INITIAL_NODE.equals(((NotationNode) viewModel).getNodeType())
                    || NodeType.ACTIVITY_FINAL_NODE.equals(((NotationNode) viewModel).getNodeType())) {
                    RGB blackColor = UiCorePlugin.getDefault().getColor("Black").getRGB();
                    nodeColor = blackColor;
                }

                if (NodeType.ACTOR.equals(((NotationNode) viewModel).getNodeType())) {
                    nodeColor = ColorConstants.lightGray.getRGB();
                }

                if (NodeType.JOIN_NODE.equals(((NotationNode) viewModel).getNodeType())) {
                    nodeColor = new RGB(240, 240, 240);
                }
                if (NodeType.FORK_NODE.equals(((NotationNode) viewModel).getNodeType())) {
                    nodeColor = new RGB(240, 240, 240);
                }

            } else if (viewModel instanceof AbstractConnection) {
                nodeColor = PreferenceUtil.INSTANCE.getNodeLineColor();
            } else if (viewModel instanceof ContainerNode
                && NodeType.ACTIVITY_PARTITION.equals(((ContainerNode) viewModel).getNodeType())) {
                nodeColor = UiCorePlugin.getDefault().getColor("SkyBlue").getRGB();
            }

            if (viewModel.getFontColor() != null) {
                fontColor = StringConverter.asRGB(viewModel.getFontColor());
            } else if (viewModel instanceof ContainerNode
                && NodeType.ACTIVITY_PARTITION.equals(((ContainerNode) viewModel).getNodeType())) {
                fontColor = UiCorePlugin.getDefault().getColor("White").getRGB();
            }

            if (!fillColorSelector.getButton().isDisposed()) {
                fillColorSelector.setColorValue(nodeColor);
            }
            if (!fontColorSelector.getButton().isDisposed()) {
                fontColorSelector.setColorValue(fontColor);
            }
        }
    }

    /**
     * getViewModel
     * 
     * @return AbstractView
     */
    private AbstractView getViewModel() {
        if (getSelection() instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) getSelection();
            Object firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof EditPart || ((EditPart) firstElement).getModel() instanceof AbstractView) {
                return (AbstractView) ((EditPart) firstElement).getModel();
            }
        }
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        initDefault();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
    }

}
