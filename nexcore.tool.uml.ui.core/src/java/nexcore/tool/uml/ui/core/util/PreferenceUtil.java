/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.util;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설  명 : PreferenceUtil</li>
 * <li>작성일 : 2010. 1. 13.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public final class PreferenceUtil {

    /** PreferenceUtil 클래스의 싱글턴 객체 */
    public static final PreferenceUtil INSTANCE = new PreferenceUtil();

    /** preferenceStore */
    private IPreferenceStore preferenceStore;

    /**
     * getPreferenceStore
     *  
     * @return IPreferenceStore
     */
    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    // /** isDefaultPresented */
    // private Boolean isDefaultPresented = false;

    /**
     * PreferenceUtil
     */
    public PreferenceUtil() {
        if (preferenceStore == null) {
            this.preferenceStore = UiCorePlugin.getDefault().getPreferenceStore();
            init();
        }
    }

    /**
     * Preference 기본값 셋팅.
     */
    private void init() {
    	
        RGB notationColor = getNodeFillColor();
        RGB notationLine = getNodeLineColor();
        @SuppressWarnings("unused")
        RGB notationFontColor = getNodeFontColor();

        RGB noteColor = getNoteFillColor();
        RGB noteFontColor = getNoteFontColor();

        RGB textColor = getTextFillColor();
        RGB textFontColor = getTextFontColor();

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL)) {
            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL,
                notationColor);
            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE,
                notationLine);
            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT,
                noteFontColor);

            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL,
                noteColor);
            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT,
                noteFontColor);

            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL,
                textColor);
            PreferenceConverter.setValue(preferenceStore,
                ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT,
                textFontColor);
            // PreferenceConverter.setValue(store,
            // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_LINE,
            // notationLine);

            // PreferenceConverter.setValue(store,
            // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_LINE,
            // notationLine);

            // PreferenceConverter.setValue(store,
            // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FILL,
            // notationColor);
            // PreferenceConverter.setValue(store,
            // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_FONT,
            // notationLine);
            // PreferenceConverter.setValue(store,
            // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_CONSTRAINT_LINE,
            // notationLine);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL,
                true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL,
                true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL,
                true);

        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO, true);
            preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_DEFAULT_VALUE,
                true);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE, true);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL, true);
        }
        
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER, true);
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN, false);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP,
                UICoreConstant.PROJECT_CONSTANTS__ZERO);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT,
                UICoreConstant.PROJECT_CONSTANTS__ZERO);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT,
                UICoreConstant.PROJECT_CONSTANTS__ZERO);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM,
                UICoreConstant.PROJECT_CONSTANTS__ZERO);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER,
                "20");
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER,
                "20");
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER,
                UMLMessage.LABEL_PRINT_NONE);
        }

        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM,
                false);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE,
                UMLMessage.LABEL_PRINT_A4);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION,
                UMLMessage.LABEL_PRINT_PORTRAIT);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM, true);
        }
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT,
                UICoreConstant.EMPTY_STRING);
        }
        
        if (!preferenceStore.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE)) {
        	preferenceStore.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE, true);
        }
        
        if (!preferenceStore.contains(ManagerConstant.IS_CREATE_ATTRIBUTE)) {
            preferenceStore.setValue(ManagerConstant.IS_CREATE_ATTRIBUTE, true);
        }
        
        if (!preferenceStore.contains(ManagerConstant.USE_COLLABORATION)) {
            preferenceStore.setValue(ManagerConstant.USE_COLLABORATION, false);
        }

        if (!preferenceStore.contains(ManagerConstant.USE_MODEL_CLOSE)) {
            preferenceStore.setValue(ManagerConstant.USE_MODEL_CLOSE, false);
        }
        
        if (!preferenceStore.contains(ManagerConstant.PROJECT_BACKUP)) {
            preferenceStore.setValue(ManagerConstant.PROJECT_BACKUP, true);
        }
        
        doStore();
    }
    
    /**
     * @see org.eclipse.jface.preference.PreferencePage#setPreferenceStore(org.eclipse.jface.preference.IPreferenceStore)
     */
    public void setPreferenceStore(IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    /**
     * String 으로 세팅된 환경설정 값을 리턴
     * 
     * @param name
     * @return String
     */
    public String getValueOfStringFieldEditor(String name) {
        return preferenceStore.getString(name);
    }

    /**
     * int로 세팅된 환경설정 값을 리턴
     * 
     * @param name
     * @return String
     */
    public int getValueOfIntFieldEditor(String name) {
        return Integer.parseInt(preferenceStore.getString(name));
    }

    /**
     * boolean 으로 세팅된 환경설정 값을 리턴
     * 
     * @param name
     * @return String
     */
    public Boolean getValueOfBooleanFieldEditor(String name) {
        String value = preferenceStore.getString(name);
        if (value.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])) {
            return true;
        }
        if (value.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[1])) {
            return false;
        }
        return false;
    }

    /**
     * FontFieldEditor 의 환경설정 값을 리턴
     * 
     * @param name
     * @return FontData[]
     */
    public FontData[] getValueOfFontListFieldEditor(String name) {
        return PreferenceConverter.getFontDataArray(preferenceStore, name);
    }

    /**
     * ColorFieldEditor 의 환경설정 값을 리턴
     * 
     * @param name
     * @return RGB
     */
    public RGB getValueOfColorFieldEditor(String name) {
        return PreferenceConverter.getColor(preferenceStore, name);
    }

    /**
     * boolean 값을 환경설정 저장소에 저장
     * 
     * @param name
     * @param value
     *            void
     */
    public void setValueOfBooleanFieldEditor(String name, boolean value) {
        if (value) {
            preferenceStore.setValue(name, UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0]);
        }
        else {
            preferenceStore.setValue(name, UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[1]);
        }
    }

    /**
     * String 값을 환경설정 저장소에 저장
     * 
     * @param name
     * @param value
     *            void
     */
    public void setValueOfStringFieldEditor(String name, String value) {
        preferenceStore.setValue(name, value);
    }

    /**
     * int 값을 환경설정 저장소에 저장
     * 
     * @param name
     * @param value
     *            void
     */
    public void setValueOfIntFieldEditor(String name, int value) {
        preferenceStore.setValue(name, String.valueOf(value));
    }

    /**
     * FontFieldEditor 값을 환경설정 저장소에 저장
     * 
     * @param name
     * @param value
     *            void
     */
    public void setValueOfFontListFieldEditor(String name, FontData[] value) {
        PreferenceConverter.setValue(preferenceStore, name, value);
    }

    /**
     * ColorFieldEditor 값을 환경설정 저장소에 저장
     * 
     * @param name
     * @param value
     *            void
     */
    public void setValueOfColorFieldEditor(String name, RGB value) {
        PreferenceConverter.setValue(preferenceStore, name, value);
    }

    // /**
    // * Default Store 의 활성화 여부를 세팅
    // *
    // * @param isDefaultPresented
    // * void
    // */
    // public void setIsDefaultPresented(boolean isDefaultPresented) {
    // this.isDefaultPresented = isDefaultPresented;
    // }
    //
    // /**
    // * Default Store 의 활성화 여부를 리턴
    // *
    // * @return boolean
    // */
    // public boolean getIsDefaultPresented() {
    // return this.isDefaultPresented;
    // }

    /**
     * doStore
     *   void
     */
    public void doStore() {
        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * usePreference
     */
    private boolean usePreference = false;

    /**
     * getUsePreference
     *  
     * @return boolean
     */
    public boolean getUsePreference() {
        return usePreference;
    }

    /**
     * setUsePreference
     *  
     * @param usePreference void
     */
    public void setUsePreference(boolean usePreference) {
        this.usePreference = usePreference;
    }

    /**
     * rgbWhite
     */
    RGB rgbWhite = new RGB(255, 255, 255);
    /**
     * rgbBlack
     */
    RGB rgbBlack = new RGB(0, 0, 0);
    
    /**
     * nodeFillColor
     */
    private RGB nodeFillColor = rgbWhite;
    /**
     * lineColor
     */
    private RGB lineColor = rgbBlack;
    /**
     * nodeFontColor
     */
    private RGB nodeFontColor = rgbBlack;
    /**
     * noteFillColor
     */
    private RGB noteFillColor = new RGB(255, 255, 128);
    /**
     * noteFontColor
     */
    private RGB noteFontColor = rgbBlack;
    /**
     * textFillColor
     */
    private RGB textFillColor = rgbWhite;
    /**
     * textFontColor
     */
    private RGB textFontColor = rgbBlack;

    /**
     * getNodeFillColor
     *  
     * @return RGB
     */
    public RGB getNodeFillColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            nodeFillColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FILL);
        }
        return nodeFillColor;
    }

    /**
     * getNodeLineColor
     *  
     * @return RGB
     */
    public RGB getNodeLineColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            lineColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_LINE);
        }
        return lineColor;
    }

    /**
     * getNodeFontColor
     *  
     * @return RGB
     */
    public RGB getNodeFontColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            nodeFontColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_UMLNOTATION_FONT);
        }
        return nodeFontColor;
    }

    /**
     * getNoteFillColor
     *  
     * @return RGB
     */
    public RGB getNoteFillColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            noteFillColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FILL);
        }
        return noteFillColor;
    }

    /**
     * getNoteFontColor
     *  
     * @return RGB
     */
    public RGB getNoteFontColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            noteFontColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_NOTE_FONT);
        }
        return noteFontColor;
    }

    /**
     * getTextFillColor
     *  
     * @return RGB
     */
    public RGB getTextFillColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            textFillColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
        }
        return textFillColor;
    }

    /**
     * getTextFontColor
     *  
     * @return RGB
     */
    public RGB getTextFontColor() {
        String colorValue = getPreferenceStore().getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
        if (!colorValue.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
            textFontColor = getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
        }
        return textFontColor;
    }

}
