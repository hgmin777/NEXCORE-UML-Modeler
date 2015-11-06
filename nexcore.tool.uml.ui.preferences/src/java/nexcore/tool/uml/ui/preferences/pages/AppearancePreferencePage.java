/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.preferences.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.preferences.PreferenceConstant;
import nexcore.tool.uml.ui.preferences.part.FontStyleType;

import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : AppearancePreferencePage</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class AppearancePreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage,
IPropertyChangeListener {

    /** SIZE_ONE */
    private static final int SIZE_ONE = 1;

    /** TRIPLE_DOT */
    private static final String TRIPLE_DOT = "..."; //$NON-NLS-1$

    /** defaultFontPreviewLabel */
    private Label defaultFontPreviewLabel;

    /** styleCombo */
    private Combo styleCombo;

    /** defaultFontButton */
    private Button defaultFontButton;

    /** fontColorSelector */
    private ColorSelector fontColorSelector;

    /** fillColorSelector */
    private ColorSelector fillColorSelector;

    /** lineColorSelector */
    private ColorSelector lineColorSelector;

    /** noteFontColorSelector */
    private ColorSelector noteFontColorSelector;

    /** noteFillColorSelector */
    private ColorSelector noteFillColorSelector;

    // /** noteLineColorSelector */
    // private ColorSelector noteLineColorSelector;

    /** commentFontColorSelector */
    private ColorSelector commentFontColorSelector;

    /** commentFillColorSelector */
    private ColorSelector commentFillColorSelector;

    // /** commentLineColorSelector */
    // private ColorSelector commentLineColorSelector;

    // /** constraintFillColorSelector */
    // private ColorSelector constraintFillColorSelector;
    // /** constraintLineColorSelector */
    // private ColorSelector constraintLineColorSelector;
    // /** constraintFontColorSelector */
    // private ColorSelector constraintFontColorSelector;

    /**
     * labelGridData
     */
    private GridData labelGridData;

    /** changedColorMap */
    private Map<String, RGB> changedColorMap = new HashMap<String, RGB>();

    /** changedColorNameList */
    private List<String> changedColorNameList = new ArrayList<String>();

    /** fontList */
    private FontData[] fontList;

    /** fontListOfDialog */
    private FontData[] fontListOfDialog = new FontData[SIZE_ONE];

    /** defaultFontList */
    private FontData[] defaultFontList = new FontData[SIZE_ONE];

    /** defaultFontData */
    private FontData defaultFontData;

    /** defaultNodeColor */
    private RGB defaultNodeColor;

    /** defaultLineColor */
    private RGB defaultLineColor;

    /** defaultLineColor */
    private RGB defaultFontColor;

    /** defaultNoteColor */
    private RGB defaultNoteColor;

    /** defaultNoteFontColor */
    private RGB defaultNoteFontColor;

    /** defaultTextColor */
    private RGB defaultTextColor;

    /** defaultTextFontColor */
    private RGB defaultTextFontColor;

    /** defaultIndexOfStyle */
    private String defaultIndexOfStyle;

    /** VALUE_OF_FIRST_STYLE_ITEM */
    private static final String VALUE_OF_FIRST_STYLE_ITEM = "0"; //$NON-NLS-1$

    /** VALUE_OF_SECOND_STYLE_ITEM */
    private static final String VALUE_OF_SECOND_STYLE_ITEM = "1"; //$NON-NLS-1$

    /** ENTRY_NAMES_AND_VALUES */
    private static final String[][] ENTRY_NAMES_AND_VALUES = {
        { UMLMessage.getMessage(UMLMessage.LABEL_DEFAULT_VALUE), VALUE_OF_FIRST_STYLE_ITEM },
        { UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_ROSE_CLASSIC), VALUE_OF_SECOND_STYLE_ITEM } };

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer()
     */
    @Override
    protected void initializer() {
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        // Group appearanceStyleGroup = createGroupOfAppearanceStyle(parent);
        // createStyle(appearanceStyleGroup);
        labelGridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        labelGridData.widthHint = 130;
        Group notationGroup = createGroupOfNotation(parent);
        Group commentGroup = createGroupOfComment(parent);
        Group noteGroup = createGroupOfNote(parent);
        // Group constraintGroup = createGroupOfConstraint(parent);
        // createDefaultFont(notationGroup);
        createFontColor(notationGroup);
        createFillColor(notationGroup);
        createLineColor(notationGroup);
        createNoteFillColor(noteGroup);
        // createNoteLineColor(noteGroup);
        createNoteFontColor(noteGroup);
        createCommentFillColor(commentGroup);
        // createCommentLineColor(commentGroup);
        createCommentFontColor(commentGroup);
        // createConstraintFillColor(constraintGroup);
        // createConstraintLineColor(constraintGroup);
        // createConstraintFontColor(constraintGroup);
        return parent;
    }

    // /**
    // *
    // *
    // * @param colorsAndFontsGroup
    // * void
    // */
    // private void createConstraintLineColor(Group parent) {
    // Label fontColorLabel = new Label(parent, SWT.NONE);
    // fontColorLabel.setLayoutData(labelGridData);
    // fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_CONSTRAINT_LINE_COLOR)
    // + COLON_TEXT);
    // constraintLineColorSelector = new ColorSelector(parent);
    // gridData = new GridData();
    // gridData.horizontalSpan = 2;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // constraintLineColorSelector.getButton().setLayoutData(gridData);
    // constraintLineColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE);
    // changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE,
    // (RGB) event.getNewValue());
    // fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE,
    // event.getOldValue(),
    // event.getNewValue());
    // }
    // });
    // }

    // /**
    // *
    // *
    // * @param colorsAndFontsGroup
    // * void
    // */
    // private void createConstraintFillColor(Group parent) {
    // Label fontColorLabel = new Label(parent, SWT.NONE);
    // fontColorLabel.setLayoutData(labelGridData);
    // fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_CONSTRAINT_FILL_COLOR)
    // + COLON_TEXT);
    // constraintFillColorSelector = new ColorSelector(parent);
    // gridData = new GridData();
    // gridData.horizontalSpan = 2;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // constraintFillColorSelector.getButton().setLayoutData(gridData);
    // constraintFillColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL);
    // changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL,
    // (RGB) event.getNewValue());
    // fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL,
    // event.getOldValue(),
    // event.getNewValue());
    // }
    // });
    // }

    // /**
    // * @param parent
    // */
    // private void createConstraintFontColor(Group parent) {
    // Label fontColorLabel = new Label(parent, SWT.NONE);
    // fontColorLabel.setLayoutData(labelGridData);
    // fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FONT_COLOR)
    // + COLON_TEXT);
    // constraintFontColorSelector = new ColorSelector(parent);
    // gridData = new GridData();
    // gridData.horizontalSpan = 2;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // constraintFontColorSelector.getButton().setLayoutData(gridData);
    // constraintFontColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT);
    // changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT,
    // (RGB) event.getNewValue());
    // fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT,
    // event.getOldValue(),
    // event.getNewValue());
    // }
    // });
    // }

    // /**
    // *
    // *
    // * @param colorsAndFontsGroup
    // * void
    // */
    // private void createCommentLineColor(Group parent) {
    // Label fontColorLabel = new Label(parent, SWT.NONE);
    // fontColorLabel.setLayoutData(labelGridData);
    // fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_COMMENT_LINE_COLOR)
    // + COLON_TEXT);
    // commentLineColorSelector = new ColorSelector(parent);
    // gridData = new GridData();
    // gridData.horizontalSpan = 2;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // commentLineColorSelector.getButton().setLayoutData(gridData);
    // commentLineColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE);
    // changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE,
    // (RGB) event.getNewValue());
    // fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE,
    // event.getOldValue(),
    // event.getNewValue());
    // }
    // });
    // }

    /**
     * 
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    private void createCommentFillColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_COMMENT_FILL_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        commentFillColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        commentFillColorSelector.getButton().setLayoutData(gridData);
        commentFillColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * @param parent
     */
    private void createFontColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FONT_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        fontColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        fontColorSelector.getButton().setLayoutData(gridData);
        fontColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    // /**
    // *
    // *
    // * @param colorsAndFontsGroup
    // * void
    // */
    // private void createNoteLineColor(Group parent) {
    // Label fontColorLabel = new Label(parent, SWT.NONE);
    // fontColorLabel.setLayoutData(labelGridData);
    // fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_NOTE_LINE_COLOR)
    // + COLON_TEXT);
    // noteLineColorSelector = new ColorSelector(parent);
    // gridData = new GridData();
    // gridData.horizontalSpan = 1;
    // gridData.horizontalAlignment = GridData.BEGINNING;
    // noteLineColorSelector.getButton().setLayoutData(gridData);
    // noteLineColorSelector.addListener(new IPropertyChangeListener() {
    // // forward the property change of the color selector
    // /**
    // * @see
    // org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
    // */
    // public void propertyChange(PropertyChangeEvent event) {
    // changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE);
    // changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE,
    // (RGB) event.getNewValue());
    // fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE,
    // event.getOldValue(),
    // event.getNewValue());
    // }
    // });
    // }

    /**
     * 
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    private void createNoteFillColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_NOTE_FILL_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        noteFillColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 1;
        gridData.horizontalAlignment = GridData.BEGINNING;
        noteFillColorSelector.getButton().setLayoutData(gridData);
        noteFillColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * @param parent
     */
    private void createNoteFontColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FONT_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        noteFontColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 1;
        gridData.horizontalAlignment = GridData.BEGINNING;
        noteFontColorSelector.getButton().setLayoutData(gridData);
        noteFontColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * 
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    private void createLineColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_LINE_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        lineColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        lineColorSelector.getButton().setLayoutData(gridData);
        lineColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * 
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    private void createFillColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FILL_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        fillColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        fillColorSelector.getButton().setLayoutData(gridData);
        fillColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * @param parent
     */
    private void createCommentFontColor(Group parent) {
        Label fontColorLabel = new Label(parent, SWT.NONE);
        fontColorLabel.setLayoutData(labelGridData);
        fontColorLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_FONT_COLOR)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        commentFontColorSelector = new ColorSelector(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.BEGINNING;
        commentFontColorSelector.getButton().setLayoutData(gridData);
        commentFontColorSelector.addListener(new IPropertyChangeListener() {
            // forward the property change of the color selector
            /**
             * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent event) {
                changedColorNameList.add(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
                changedColorMap.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT,
                    (RGB) event.getNewValue());
                fireValueChanged(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT,
                    event.getOldValue(),
                    event.getNewValue());
            }
        });
    }

    /**
     * 
     * 
     * @param colorsAndFontsGroup
     *            void
     */
    @SuppressWarnings("unused")
    private void createDefaultFont(Group parent) {
        Label defaultFontLabel = new Label(parent, SWT.NONE);
        defaultFontLabel.setLayoutData(labelGridData);
        defaultFontLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_DEFAULT_FONT)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        defaultFontPreviewLabel = new Label(parent, SWT.NONE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        defaultFontPreviewLabel.setLayoutData(gridData);
        defaultFontButton = new Button(parent, SWT.PUSH);
        defaultFontButton.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANC_CHANGE) + TRIPLE_DOT);
        defaultFontButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                openFontDialog();
            }
        });
    }

    /**
     * 
     * void
     */
    private void openFontDialog() {
        FontDialog dialog = new FontDialog(UiCorePlugin.getShell(), SWT.NONE);
        if (fontListOfDialog[0] != null) {
            if (fontListOfDialog != fontList) {
                dialog.setFontList(fontListOfDialog);
            } else {
                dialog.setFontList(fontList);
            }
        } else {
            dialog.setFontList(fontList);
        }
        fontListOfDialog[0] = dialog.open();
        defaultFontPreviewLabel.setText(getFormatedFontString(fontListOfDialog));
    }

    /**
     * 
     * 
     * @param property
     * @param oldValue
     * @param newValue
     *            void
     */
    private void fireValueChanged(String changedColorSelectorName, Object oldValue, Object newValue) {
        this.propertyChange(new PropertyChangeEvent(this, changedColorSelectorName, oldValue, newValue));
    }

    /**
     * 
     * 
     * @param appearanceStyleGroup
     *            void
     */
    @SuppressWarnings("unused")
    private void createStyle(Group parent) {
        Label styleLabel = new Label(parent, SWT.NONE);
        styleLabel.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_STYLE)
            + UICoreConstant.PROJECT_CONSTANTS__COLON);
        styleCombo = new Combo(parent, SWT.READ_ONLY);
        for (int i = 0; i < ENTRY_NAMES_AND_VALUES.length; i++) {
            styleCombo.add(ENTRY_NAMES_AND_VALUES[i][0], i);
        }
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        styleCombo.setLayoutData(gridData);
    }

    /**
     * 색상 및 글꼴 그룹을 리턴
     * 
     * @return Group
     */
    private Group createGroupOfNotation(Composite parent) {
        Group notationGroup = new Group(parent, SWT.NONE);
        layout = new GridLayout(3, false);
        notationGroup.setLayout(layout);
        gridData = new GridData(GridData.FILL_BOTH);
        notationGroup.setLayoutData(gridData);
        notationGroup.setText(UMLMessage.LABEL_UML_NOTATION);
        return notationGroup;
    }

    /**
     * @param parent
     * @return
     */
    private Group createGroupOfComment(Composite parent) {
        Group commentGroup = new Group(parent, SWT.NONE);
        layout = new GridLayout(3, false);
        commentGroup.setLayout(layout);
        gridData = new GridData(GridData.FILL_BOTH);
        commentGroup.setLayoutData(gridData);
        commentGroup.setText(UMLMessage.getMessage(UMLMessage.UML_ANNOTATION));
        return commentGroup;
    }

    /**
     * @param parent
     * @return
     */
    private Group createGroupOfNote(Composite parent) {
        Group noteGroup = new Group(parent, SWT.NONE);
        layout = new GridLayout(2, false);
        noteGroup.setLayout(layout);
        gridData = new GridData(GridData.FILL_BOTH);
        noteGroup.setLayoutData(gridData);
        noteGroup.setText(UMLMessage.getMessage(UMLMessage.UML_NOTE));
        return noteGroup;
    }

    /**
     * @param parent
     * @return
     */
    @SuppressWarnings("unused")
    private Group createGroupOfConstraint(Composite parent) {
        Group constraintGroup = new Group(parent, SWT.NONE);
        layout = new GridLayout(3, false);
        constraintGroup.setLayout(layout);
        gridData = new GridData(GridData.FILL_BOTH);
        constraintGroup.setLayoutData(gridData);
        constraintGroup.setText(UMLMessage.getMessage(UMLMessage.UML_CONSTRAINT));
        return constraintGroup;
    }

    /**
     * 모양 스타일 그룹을 리턴
     * 
     * @return Group
     */
    @SuppressWarnings("unused")
    private Group createGroupOfAppearanceStyle(Composite parent) {
        Group styleGroup = new Group(parent, SWT.NONE);
        layout = new GridLayout(2, false);
        styleGroup.setLayout(layout);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        styleGroup.setLayoutData(gridData);
        styleGroup.setText(UMLMessage.getMessage(UMLMessage.LABEL_PREFERENCE_APPEARANCE_APPEARANCE_STYLE));
        return styleGroup;
    }

    /**
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {

    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        @SuppressWarnings("unused")
        String styleValue = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(PreferenceConstant.NAME_OF_PREFERENCE_CONSTANT__MODELING_APPEARANCE_STYLE);
        // if (VALUE_OF_FIRST_STYLE_ITEM.equals(styleValue)) {
        // styleCombo.setText(ENTRY_NAMES_AND_VALUES[0][0]);
        // } else if (VALUE_OF_SECOND_STYLE_ITEM.equals(styleValue)) {
        // styleCombo.setText(ENTRY_NAMES_AND_VALUES[1][0]);
        // } else {
        // styleCombo.setText(ENTRY_NAMES_AND_VALUES[0][0]);
        // }

        fontList = PreferenceUtil.INSTANCE.getValueOfFontListFieldEditor(PreferenceConstant.NAME_OF_PREFERENCE_CONSTANT__MODELING_APPEARANCE_DEFAULT_FONT);
        // defaultFontPreviewLabel.setText(getFormatedFontString(fontList));

        RGB color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT);
        fontColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL);
        fillColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE);
        lineColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL);
        noteFillColorSelector.setColorValue(color);
        // color =
        // PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE);
        // noteLineColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT);
        noteFontColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
        commentFillColorSelector.setColorValue(color);
        // color =
        // PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE);
        // commentLineColorSelector.setColorValue(color);
        color = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
        commentFontColorSelector.setColorValue(color);
        // color =
        // PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL);
        // constraintFillColorSelector.setColorValue(color);
        // color =
        // PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE);
        // constraintLineColorSelector.setColorValue(color);
        // color =
        // PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT);
        // constraintFontColorSelector.setColorValue(color);
    }

    /**
     * 
     * 
     * @param fontList
     * @return String
     */
    private String getFormatedFontString(FontData[] fontList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fontList[0].getName());
        stringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__HYPHEN);
        switch (fontList[0].getStyle()) {
            case SWT.NORMAL:
                stringBuilder.append(FontStyleType.REGULAR);
                break;
            case SWT.BOLD:
                stringBuilder.append(FontStyleType.BOLD);
                break;
            case SWT.ITALIC:
                stringBuilder.append(FontStyleType.ITALIC);
                break;
            case 3:
                stringBuilder.append(FontStyleType.BOLD_ITALIC);
                break;
            default:
                break;
        }
        stringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__HYPHEN);
        stringBuilder.append(fontList[0].getHeight());
        return stringBuilder.toString();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        // if (styleCombo.getText().equals(ENTRY_NAMES_AND_VALUES[0][0])) {
        // PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__MODELING_APPEARANCE_STYLE,
        // ENTRY_NAMES_AND_VALUES[0][1]);
        // } else if (styleCombo.getText().equals(ENTRY_NAMES_AND_VALUES[1][0]))
        // {
        // PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__MODELING_APPEARANCE_STYLE,
        // ENTRY_NAMES_AND_VALUES[1][1]);
        // }
//        if (fontListOfDialog[0] != null) {
//            PreferenceUtil.INSTANCE.setValueOfFontListFieldEditor(PreferenceConstant.NAME_OF_PREFERENCE_CONSTANT__MODELING_APPEARANCE_DEFAULT_FONT,
//                fontListOfDialog);
//        }
//        if (changedColorNameList.size() > 0) {
//            for (String changedColorName : changedColorNameList) {
//                PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(changedColorName,
//                    changedColorMap.get(changedColorName));
//            }
//        }
    	
    	IPreferenceStore preferenceStore = PreferenceUtil.INSTANCE.getPreferenceStore();
    	PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL,
            fillColorSelector.getColorValue());
        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE,
            lineColorSelector.getColorValue());
        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT,
            fontColorSelector.getColorValue());

        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL,
            noteFillColorSelector.getColorValue());
        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT,
            noteFontColorSelector.getColorValue());

        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL,
            commentFillColorSelector.getColorValue());
        PreferenceConverter.setValue(preferenceStore,
            ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT,
            commentFontColorSelector.getColorValue());
            
//        UiCorePlugin.getDefault().savePluginPreferences();

        // IPreferenceStore store =
        // UiCorePlugin.getDefault().getPreferenceStore();
        // store.putValue("isDefault", "false");

        PreferenceUtil.INSTANCE.setUsePreference(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore()
     */
    @Override
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(PreferenceConstant.NAME_OF_PREFERENCE_CONSTANT__MODELING_APPEARANCE_STYLE,
            ENTRY_NAMES_AND_VALUES[Integer.valueOf(defaultIndexOfStyle)][1]);
        PreferenceUtil.INSTANCE.setValueOfFontListFieldEditor(PreferenceConstant.NAME_OF_PREFERENCE_CONSTANT__MODELING_APPEARANCE_DEFAULT_FONT,
            defaultFontList);

        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT,
            defaultFontColor);
        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL,
            defaultNodeColor);
        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE,
            defaultLineColor);

        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL,
            defaultNoteColor);
        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT,
            defaultNoteFontColor);
        // PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE,
        // defaultLineColor);

        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL,
            defaultTextColor);
        PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT,
            defaultTextFontColor);
        // PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE,
        // defaultLineColor);

        // PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL,
        // defaultNodeColor);
        // PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE,
        // defaultLineColor);
        // PreferenceUtil.INSTANCE.setValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT,
        // defaultLineColor);

        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(false);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        changedColorNameList.clear();
        changedColorMap.clear();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        defaultIndexOfStyle = "0";
        // styleCombo.setText(ENTRY_NAMES_AND_VALUES[Integer.valueOf(defaultIndexOfStyle)][0]);

        defaultFontData = new FontData("Arial", 9, SWT.NORMAL);
        defaultFontList[0] = defaultFontData;
        // defaultFontPreviewLabel.setText(getFormatedFontString(defaultFontList));

        RGB rgbWhite = new RGB(255, 255, 255);
        RGB rgbBlack = new RGB(0, 0, 0);
        
        defaultNodeColor = rgbWhite;
        defaultLineColor = rgbBlack;
        defaultFontColor = rgbBlack;

        defaultNoteColor = new RGB(255, 255, 128);
        defaultNoteFontColor = rgbBlack;

        defaultTextColor = rgbWhite;
        defaultTextFontColor = rgbBlack;

        fillColorSelector.setColorValue(defaultNodeColor);
        lineColorSelector.setColorValue(defaultLineColor);
        fontColorSelector.setColorValue(defaultLineColor);

        noteFillColorSelector.setColorValue(defaultNoteColor);
        noteFontColorSelector.setColorValue(defaultNoteFontColor);
        // noteLineColorSelector.setColorValue(defaultLineColor);

        commentFillColorSelector.setColorValue(defaultTextColor);
        commentFontColorSelector.setColorValue(defaultTextFontColor);
        // commentLineColorSelector.setColorValue(defaultLineColor);

        // constraintFillColorSelector.setColorValue(defaultNodeColor);
        // constraintLineColorSelector.setColorValue(defaultLineColor);
        // constraintFontColorSelector.setColorValue(defaultLineColor);
        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }
}
