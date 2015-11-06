/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설 명 : PrintPreferencePage</li>
 * <li>작성일 : 2011. 9. 28.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage, VerifyListener {

    private Button fButtonHeader;

    private Button fButtonFooter;

    private Button fButtonAll;

    private Button fButtonNone;

    private Button fButtonOnlyDiagramName;

    private Label headerTextLabel;
    
    private Text fButtonHeaderText;

    private Label headerMarginLabel;

    private Text fHeaderMarginText;

    private Label footerMarginLabel;

    private Text fFooterMarginText;

    private Combo pageSizeCombo;

    private Button fButtonPortrait;

    private Button fButtonLandscape;

    private Button fButtonMm;

    private Button fButtonInch;

    private Label marginTopLabel;

    private Label marginLeftLabel;

    private Label marginRightLabel;

    private Label marginBottomLabel;
    
    private Text marginTopText;

    private Text marginLeftText;

    private Text marginRightText;

    private Text marginBottomText;

    private Label marginErrorLabel;

    //    private static String DIAGRAM_NAME = "Diagram Name"; //$NON-NLS-1$
    //
    //    private static String PROJECT_TITLE = "Project Title"; //$NON-NLS-1$
    //
    //    private static String PAGE_NUMBER = "Page Number"; //$NON-NLS-1$
    //
    //    private static String DIAGRAM_KIND = "Diagram Kind"; //$NON-NLS-1$
    //
    //    private static String DATE_TIME = "Date Time"; //$NON-NLS-1$

    //    private static String MARGIN_TOP = "Margin Top"; //$NON-NLS-1$
    //
    //    private static String MARGIN_BOTTOM = "Margin Bottom"; //$NON-NLS-1$
    //
    //    private static String MARGIN_LEFT = "Margin Left"; //$NON-NLS-1$
    //
    //    private static String MARGIN_RIGHT = "Margin Right"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore()
     */
    @Override
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER,
            UMLMessage.LABEL_PRINT_NONE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE,
            UMLMessage.LABEL_PRINT_A4);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION,
            UMLMessage.LABEL_PRINT_PORTRAIT);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM,
            false);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT,
            UICoreConstant.EMPTY_STRING);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP,
            "0.0");
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT,
            "0.0");
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT,
            "0.0");
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM,
            "0.0");
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER,
            ManagerConstant.PRINT_PREVIEW_HEADERFOOTER_MARGIN_DEFAULT_VALUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER,
            ManagerConstant.PRINT_PREVIEW_HEADERFOOTER_MARGIN_DEFAULT_VALUE);

        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        // 1. HEADER, FOOTER
        String value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER);

        // fButtonHeader.setSelection(false);
        // fButtonFooter.setSelection(false);
        // fButtonNone.setSelection(false);
        fButtonOnlyDiagramName.setEnabled(false);
        fButtonHeaderText.setEnabled(false);
        if (value.equals(UMLMessage.LABEL_PRINT_HEADER)) {
            fButtonHeader.setSelection(true);
            fButtonOnlyDiagramName.setEnabled(true);
            fButtonHeaderText.setEnabled(true);
        } else if (value.equals(UMLMessage.LABEL_PRINT_FOOTER)) {
            fButtonFooter.setSelection(true);
        } else if (value.equals(UMLMessage.LABEL_PRINT_ALL)) {
            fButtonAll.setSelection(true);
            fButtonOnlyDiagramName.setEnabled(true);
            fButtonHeaderText.setEnabled(true);
        } else {
            fButtonNone.setSelection(true);
        }

        boolean b = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM);
        fButtonOnlyDiagramName.setSelection(b);
        
        value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT);        
        fButtonHeaderText.setText(value);

        // 2. Page Size - A3, A4, B4, B5
//        value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE);
//        pageSizeCombo.setText(value);

        // 3. Page Orientation - Portrait, Landscape
        // fButtonPortrait.setSelection(false);
        // fButtonLandscape.setSelection(false);
        value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION);
        if (value.equals(UMLMessage.LABEL_PRINT_PORTRAIT)) {
            fButtonPortrait.setSelection(true);
        } else {
            fButtonLandscape.setSelection(true);
        }
        // 4. Printer Margin - Top, Left, Right, Bottom

        b = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM);
        if (b) {
            fButtonMm.setSelection(true);
            fButtonInch.setSelection(false);

        } else {
            fButtonMm.setSelection(false);
            fButtonInch.setSelection(true);

            String unitText = UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT
                + UMLMessage.LABEL_PRINT_MARGIN_IN_INCHES + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            marginLeftLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_LEFT + unitText);
            marginRightLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_RIGHT + unitText);
            marginTopLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_TOP + unitText);
            marginBottomLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_BOTTOM + unitText);
            headerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_HEADER + unitText);
            footerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_FOOTER + unitText);
        }

        marginTopText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP));
        marginLeftText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT));
        marginRightText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT));
        marginBottomText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM));
        fHeaderMarginText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER));
        fFooterMarginText.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER));

    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        // 1. HEADER, FOOTER
        String value;
        if (fButtonHeader.getSelection()) {
            value = UMLMessage.LABEL_PRINT_HEADER;
        } else if (fButtonFooter.getSelection()) {
            value = UMLMessage.LABEL_PRINT_FOOTER;
        } else if (fButtonAll.getSelection()) {
            value = UMLMessage.LABEL_PRINT_ALL;
        } else {
            value = UMLMessage.LABEL_PRINT_NONE;
        }
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER,
            value);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM,
            fButtonOnlyDiagramName.getSelection());

        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT,
            fButtonHeaderText.getText());
        // 2. Page Size - A3, A4, B4, B5
//        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE,
//            pageSizeCombo.getText());

        // 3. Page Orientation - Portrait, Landscape
        if (fButtonPortrait.getSelection()) {
            value = UMLMessage.LABEL_PRINT_PORTRAIT;
        } else {
            value = UMLMessage.LABEL_PRINT_LANDSCAPE;
        }
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION,
            value);

        // 4. Printer Margin - Top, Left, Right, Bottom
        // // Margin Validation
        // PrintPage page = PrintPage.getPrintPage(pageSizeCombo.getText());
        // Point size = page.getSize();
        //        
        //
        // // try {
        // // final int value = new Integer(text).intValue();
        // // }

        boolean useMm = fButtonMm.getSelection();
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM,
            useMm);

        // 용지크기 설정 추가시 변경필요
        double pageWidth = A4_CONTENTS_SIZE.x;
        double pageHeight = A4_CONTENTS_SIZE.y;
        String orientation = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION);
        if (orientation.equals(UMLMessage.LABEL_PRINT_LANDSCAPE)) {
            pageWidth = A4_CONTENTS_SIZE.y;
            pageHeight = A4_CONTENTS_SIZE.x;
        }
        String errMsg = UICoreConstant.EMPTY_STRING;

        // 여백값이 용지 크기를 벗어나는 경우, 에러메시지 출력후 저장하지 않는다.
        if (StringUtil.isDigit(marginTopText.getText()) || StringUtil.isDoubleNumber(marginTopText.getText())) {
            double top = getMarginInMm(marginTopText.getText());
            if ((top >= pageHeight) || (top < 0)) {
                // err msg 추가
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_TOP
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            } 
        }
        if (StringUtil.isDigit(marginLeftText.getText()) || StringUtil.isDoubleNumber(marginLeftText.getText())) {
            double left = getMarginInMm(marginLeftText.getText());
            if ((left >= pageWidth) || (left < 0)) {
                // err msg 추가
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_LEFT
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            } 
        }
        if (StringUtil.isDigit(marginRightText.getText()) || StringUtil.isDoubleNumber(marginRightText.getText())) {
            double right = getMarginInMm(marginRightText.getText());
            if ((right >= pageWidth) || (right < 0)) {
                // err msg 추가
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_RIGHT
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            } 
        }
        if (StringUtil.isDigit(marginBottomText.getText()) || StringUtil.isDoubleNumber(marginBottomText.getText())) {
            double bottom = getMarginInMm(marginBottomText.getText());
            if ((bottom >= pageHeight) || (bottom < 0)) {
                // err msg 추가
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_BOTTOM
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            } 
        }

        // 머리글/꼬리글 여백의 경우, 용지 상/하 여백과 합쳐서 용지 길이보다 큰지 계산한다.
        if (StringUtil.isDigit(fHeaderMarginText.getText()) || StringUtil.isDoubleNumber(fHeaderMarginText.getText())) {

            double top = getMarginInMm(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP))
                + getMarginInMm(fHeaderMarginText.getText());
            if ((top >= pageHeight) || (top < 0)) {
                // err msg 추가
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_HEADER
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
            }
        }

        if (StringUtil.isDigit(fFooterMarginText.getText()) || StringUtil.isDoubleNumber(fHeaderMarginText.getText())) {

            double bottom = getMarginInMm(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM))
                + getMarginInMm(fFooterMarginText.getText());
            if ((bottom >= pageHeight) || (bottom < 0)) {
                errMsg = UMLMessage.MESSAGE_PRINT_PREVIEW_ERROR_MARGIN
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_FOOTER
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                // err msg 추가
            }
        }

        marginErrorLabel.setText(errMsg);

        if (errMsg.equals(UICoreConstant.EMPTY_STRING)) {
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP,
                marginTopText.getText());
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT,
                marginLeftText.getText());
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT,
                marginRightText.getText());
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM,
                marginBottomText.getText());
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER,
                fHeaderMarginText.getText());
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER,
                fFooterMarginText.getText());
        }
        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        fButtonHeader.setSelection(false);
        fButtonFooter.setSelection(false);
        fButtonAll.setSelection(false);
        fButtonNone.setSelection(true);
        fButtonOnlyDiagramName.setEnabled(false);
        fButtonOnlyDiagramName.setSelection(false);
        fButtonHeaderText.setText(UICoreConstant.EMPTY_STRING);
        fButtonHeaderText.setEnabled(false);
        // pageSizeCombo.setText(UMLMessage.LABEL_PRINT_A4);
        // fButtonPortrait.setSelection(true);
        // fButtonLandscape.setSelection(false);
        fButtonMm.setSelection(true);
        fButtonInch.setSelection(false);

        String unitText = UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_IN_MM
            + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
        marginLeftLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_LEFT + unitText);
        marginRightLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_RIGHT + unitText);
        marginTopLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_TOP + unitText);
        marginBottomLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_BOTTOM + unitText);

        marginTopText.setText(UICoreConstant.PROJECT_CONSTANTS__ZERO);
        marginLeftText.setText(UICoreConstant.PROJECT_CONSTANTS__ZERO);
        marginRightText.setText(UICoreConstant.PROJECT_CONSTANTS__ZERO);
        marginBottomText.setText(UICoreConstant.PROJECT_CONSTANTS__ZERO);
        fHeaderMarginText.setText(ManagerConstant.PRINT_PREVIEW_HEADERFOOTER_MARGIN_DEFAULT_VALUE);
        fFooterMarginText.setText(ManagerConstant.PRINT_PREVIEW_HEADERFOOTER_MARGIN_DEFAULT_VALUE);
    }

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
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, false));

        createCommonGroup(container);

        return container;
    }

    private void createCommonGroup(Composite container) {

        // 1. HEADER, FOOTER
        Group group = new Group(container, SWT.NULL);
        group.setText(UMLMessage.LABEL_PRINT_HEADERFOOTER);
        GridLayout layout = new GridLayout(4, true);
        group.setLayout(layout);

        GridData groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 4;
        // groupGridData.verticalSpan = 2;
        group.setLayoutData(groupGridData);

        fButtonAll = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonAll.setLayoutData(groupGridData);
        fButtonAll.setText(UMLMessage.LABEL_PRINT_ALL);
        fButtonAll.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                fButtonOnlyDiagramName.setEnabled(true);
                fButtonHeaderText.setEnabled(true);
            }
        });

        fButtonNone = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonNone.setLayoutData(groupGridData);
        fButtonNone.setText(UMLMessage.LABEL_PRINT_NONE);
        fButtonNone.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                fButtonOnlyDiagramName.setEnabled(false);
                fButtonHeaderText.setEnabled(false);
            }
        });

        fButtonHeader = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonHeader.setLayoutData(groupGridData);
        fButtonHeader.setText(UMLMessage.LABEL_PRINT_HEADER);
        fButtonHeader.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                fButtonOnlyDiagramName.setEnabled(true);
                fButtonHeaderText.setEnabled(true);
            }
        });

        fButtonFooter = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonFooter.setLayoutData(groupGridData);
        fButtonFooter.setText(UMLMessage.LABEL_PRINT_FOOTER);
        fButtonFooter.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                fButtonOnlyDiagramName.setEnabled(false);
                fButtonHeaderText.setEnabled(false);
            }
        });

        fButtonOnlyDiagramName = new Button(group, SWT.CHECK);
        groupGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        groupGridData.horizontalSpan = 4;
        fButtonOnlyDiagramName.setLayoutData(groupGridData);
        fButtonOnlyDiagramName.setText(UMLMessage.LABEL_PRINT_HEADER_DIAGRAMNAME);

        headerTextLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        headerTextLabel.setLayoutData(groupGridData);
        headerTextLabel.setText(UMLMessage.LABEL_PRINT_HEADER_TEXT + UICoreConstant.PROJECT_CONSTANTS__BLANK
            + UICoreConstant.PROJECT_CONSTANTS__COLON);

        fButtonHeaderText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 3;
        fButtonHeaderText.setLayoutData(groupGridData);
        
        
        // 2. Page Size - A3, A4, B4, B5
//        group = new Group(container, SWT.NULL);
//        group.setText(UMLMessage.LABEL_PRINT_PAGESIZE);
//        layout = new GridLayout(2, true);
//        group.setLayout(layout);
//
//        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
//        groupGridData.horizontalSpan = 2;
//        group.setLayoutData(groupGridData);
//
//        pageSizeCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
//        GridData scGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
//        scGridData.horizontalSpan = 4;
//        scGridData.grabExcessHorizontalSpace = true;
//        pageSizeCombo.setLayoutData(scGridData);
//
//        String[] names = { UMLMessage.LABEL_PRINT_A3, UMLMessage.LABEL_PRINT_A4, UMLMessage.LABEL_PRINT_B4,
//            UMLMessage.LABEL_PRINT_B5 };
//        pageSizeCombo.setItems(names);

        // 3. Page Orientation - Portrait, Landscape
        group = new Group(container, SWT.NULL);
        group.setText(UMLMessage.LABEL_PRINT_PAGEORIENTATION);
        layout = new GridLayout(2, true);
        group.setLayout(layout);

        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        group.setLayoutData(groupGridData);

        fButtonPortrait = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        fButtonPortrait.setLayoutData(groupGridData);
        fButtonPortrait.setText(UMLMessage.LABEL_PRINT_PORTRAIT);

        fButtonLandscape = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        fButtonLandscape.setLayoutData(groupGridData);
        fButtonLandscape.setText(UMLMessage.LABEL_PRINT_LANDSCAPE);

        // 4. Printer Margin - Top, Left, Right, Bottom
        group = new Group(container, SWT.NULL);
        group.setText(UMLMessage.LABEL_PRINT_MARGIN_PAGE);
        layout = new GridLayout(4, true);
        group.setLayout(layout);

        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 4;
        group.setLayoutData(groupGridData);

        fButtonMm = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonMm.setLayoutData(groupGridData);
        fButtonMm.setText(UMLMessage.LABEL_PRINT_MARGIN_USE_MM);
        fButtonMm.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {

                if (fButtonMm.getSelection()) {
                    // Milimeter, Inch 단위 변경시 label text도 변경해줌
                    String unitText = UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT
                        + UMLMessage.LABEL_PRINT_MARGIN_IN_MM + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                    marginLeftLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_LEFT + unitText);
                    marginRightLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_RIGHT + unitText);
                    marginTopLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_TOP + unitText);
                    marginBottomLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_BOTTOM + unitText);
                    headerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_HEADER + unitText);
                    footerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_FOOTER + unitText);

                    // Milimeter, Inch 단위 변경시 textbox안의 수치도 변경해줌
                    marginLeftText.setText(InchesToMm(marginLeftText.getText()));
                    marginRightText.setText(InchesToMm(marginRightText.getText()));
                    marginTopText.setText(InchesToMm(marginTopText.getText()));
                    marginBottomText.setText(InchesToMm(marginBottomText.getText()));
                    fHeaderMarginText.setText(InchesToMm(fHeaderMarginText.getText()));
                    fFooterMarginText.setText(InchesToMm(fFooterMarginText.getText()));
                }
            }
        });

        fButtonInch = new Button(group, SWT.RADIO);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 2;
        fButtonInch.setLayoutData(groupGridData);
        fButtonInch.setText(UMLMessage.LABEL_PRINT_MARGIN_USE_INCHES);
        fButtonInch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                if (fButtonInch.getSelection()) {
                    String unitText = UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT
                        + UMLMessage.LABEL_PRINT_MARGIN_IN_INCHES
                        + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                    marginLeftLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_LEFT + unitText);
                    marginRightLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_RIGHT + unitText);
                    marginTopLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_TOP + unitText);
                    marginBottomLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_BOTTOM + unitText);
                    headerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_HEADER + unitText);
                    footerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_FOOTER + unitText);

                    marginLeftText.setText(mmToInches(marginLeftText.getText()));
                    marginRightText.setText(mmToInches(marginRightText.getText()));
                    marginTopText.setText(mmToInches(marginTopText.getText()));
                    marginBottomText.setText(mmToInches(marginBottomText.getText()));
                    fHeaderMarginText.setText(mmToInches(fHeaderMarginText.getText()));
                    fFooterMarginText.setText(mmToInches(fFooterMarginText.getText()));
                }
            }
        });

        String unitText = UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_PRINT_MARGIN_IN_MM
            + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
        marginLeftLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginLeftLabel.setLayoutData(groupGridData);
        marginLeftLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_LEFT + unitText);

        marginLeftText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginLeftText.setLayoutData(groupGridData);
        marginLeftText.addVerifyListener(this);

        marginRightLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginRightLabel.setLayoutData(groupGridData);
        marginRightLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_RIGHT + unitText);

        marginRightText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginRightText.setLayoutData(groupGridData);
        marginRightText.addVerifyListener(this);

        marginTopLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginTopLabel.setLayoutData(groupGridData);
        marginTopLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_TOP + unitText);

        marginTopText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginTopText.setLayoutData(groupGridData);
        marginTopText.addVerifyListener(this);

        marginBottomLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginBottomLabel.setLayoutData(groupGridData);
        marginBottomLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_BOTTOM + unitText);

        marginBottomText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        marginBottomText.setLayoutData(groupGridData);
        marginBottomText.addVerifyListener(this);

        headerMarginLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        headerMarginLabel.setLayoutData(groupGridData);
        headerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_HEADER + unitText);

        fHeaderMarginText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        fHeaderMarginText.setLayoutData(groupGridData);
        fHeaderMarginText.addVerifyListener(this);

        footerMarginLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        footerMarginLabel.setLayoutData(groupGridData);
        footerMarginLabel.setText(UMLMessage.LABEL_PRINT_MARGIN_FOOTER + unitText);

        fFooterMarginText = new Text(group, SWT.BORDER);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        fFooterMarginText.setLayoutData(groupGridData);
        fFooterMarginText.addVerifyListener(this);

        marginErrorLabel = new Label(group, SWT.NONE);
        groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
        groupGridData.horizontalSpan = 4;
        marginErrorLabel.setLayoutData(groupGridData);
        marginErrorLabel.setText(UICoreConstant.EMPTY_STRING);
        marginErrorLabel.setForeground(ColorConstants.red);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub
    }

    public void verifyText(VerifyEvent e) {
        // e.doit = e.text.matches("[0-9]*");
    }

    /**
     * mm단위를 Inch단위로 변환한다.
     * 
     * @param mm
     * @return String
     */
    private String mmToInches(String mm) {
        if (!(StringUtil.isDigit(mm) || StringUtil.isDoubleNumber(mm))) {
            return UICoreConstant.PROJECT_CONSTANTS__ZERO;
        }

        double tmp = Double.parseDouble(mm);
        String value = String.valueOf(tmp / 25.4);

        return value;
    }

    /**
     * Inch단위를 mm단위로 변환한다.
     * 
     * @param mm
     * @return String
     */
    private String InchesToMm(String inches) {
        if (!(StringUtil.isDigit(inches) || StringUtil.isDoubleNumber(inches))) {
            return UICoreConstant.PROJECT_CONSTANTS__ZERO;
        }

        double tmp = Double.parseDouble(inches);
        // 1인치 = 25.4mm
        String value = String.valueOf(tmp * 25.4);

        return value;
    }

    /**
     * 저장된 여백 정보의 단위가 Inch인 경우, mm단위로 변환한다.
     * 
     * @param mm
     * @return String
     */
    private double getMarginInMm(String margin) {
        boolean useMm = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM);
        double value;
        if (!useMm) {
            value = Double.valueOf(mmToInches(margin));
        } else {
            value = Double.valueOf(margin);
        }

        return value;
    }

    // A4 용지의 기본 크기 (용지 크기에서 어느정도 여백 크기 제한을 둠)
    private Point A4_CONTENTS_SIZE = new Point(210 - 10, 297 - 15);
}
