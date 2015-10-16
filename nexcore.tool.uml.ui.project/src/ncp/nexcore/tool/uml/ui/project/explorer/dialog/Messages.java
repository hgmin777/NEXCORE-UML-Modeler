/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.dialog;

import org.eclipse.osgi.util.NLS;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.dialog</li>
 * <li>설  명 : Messages</li>
 * <li>작성일 : 2011. 12. 8.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class Messages extends NLS {
    /**
     * BUNDLE_NAME
     */
    private static final String BUNDLE_NAME = "nexcore.tool.uml.ui.project.explorer.dialog.messages"; //$NON-NLS-1$

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    /**
     * Messages
     */
    private Messages() {
    }

    /**
     * DMElementContentSelectionDialog_CREATE_CLASS
     */
    public static String DMElementContentSelectionDialog_CREATE_CLASS;
    /**
     * DMElementContentSelectionDialog_CREATE_CLASS_FROM_DATAMODEL
     */
    public static String DMElementContentSelectionDialog_CREATE_CLASS_FROM_DATAMODEL;
    /**
     * DMElementContentSelectionDialog_DATA_MODEL
     */
    public static String DMElementContentSelectionDialog_DATA_MODEL;
    /**
     * ElementSelectionDialog_CHOOSE_ELEMENT
     */
    public static String ElementSelectionDialog_CHOOSE_ELEMENT;
    /**
     * ElementSelectionDialog_CHOOSE_ELEMENT_MESSAGE
     */
    public static String ElementSelectionDialog_CHOOSE_ELEMENT_MESSAGE;
    /**
     * ElementSelectionDialog_ELEMENT_LABEL
     */
    public static String ElementSelectionDialog_ELEMENT_LABEL;
    /**
     * ResourceCodeSelectionDialog_LABEL_CREATE_ATTRIBUTE
     */
    public static String ResourceCodeSelectionDialog_LABEL_CREATE_ATTRIBUTE;
    /**
     * ResourceCodeSelectionDialog_LABEL_RESOURCE_NAME
     */
    public static String ResourceCodeSelectionDialog_LABEL_RESOURCE_NAME;
    /**
     * RMUsecaseContentSelectionDialog_CREATE_USECASE
     */
    public static String RMUsecaseContentSelectionDialog_CREATE_USECASE;
    /**
     * RMUsecaseContentSelectionDialog_CREATE_USECASE_MESSAGE
     */
    public static String RMUsecaseContentSelectionDialog_CREATE_USECASE_MESSAGE;
    /**
     * RMUsecaseContentSelectionDialog_REQUIREMENT_DETAIL
     */
    public static String RMUsecaseContentSelectionDialog_REQUIREMENT_DETAIL;
    /**
     * UMLContentsManagementComposite_NEXCORE_COLLABORATION_PLATFORM
     */
    public static String UMLContentsManagementComposite_NEXCORE_COLLABORATION_PLATFORM;
    /**
     * UMLContentsManagementComposite_UPDATE_ERROR_MESSAGE
     */
    public static String UMLContentsManagementComposite_UPDATE_ERROR_MESSAGE;
}
