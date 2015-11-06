/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.ui.dialog;

import org.eclipse.osgi.util.NLS;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.dialog</li>
 * <li>설  명 : Messages</li>
 * <li>작성일 : 2011. 10. 14.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "nexcore.alm.common.ui.dialog.messages"; //$NON-NLS-1$

    public static String ALMProjectSelectionDialog_PROJECT_CODE;

    public static String ALMProjectSelectionDialog_PROJECT_NAME;

    public static String ALMProjectSelectionDialog_SELECT_PROJECT_MESSAGE;

    public static String ALMProjectSelectionDialog_SELECT_PROJECT_TITLE;

    public static String ContentsManagementDialog_CALLAPSE_ALL;

    public static String ContentsManagementDialog_CHECK_ALL;

    public static String ContentsManagementDialog_DELETE;

    public static String ContentsManagementDialog_LOCAL_CONTENTS;

    public static String ContentsManagementDialog_REPOSITORY_CONTENTS;

    public static String ContentsManagementDialog_UNCHECK_ALL;

    public static String ContentsManagementDialog_VIEW_ONLY_DIFFRENT;

    public static String ContentsManagementDialog_MANAGEMENT_REPOSITORY_CONTENTS;

	public static String DetailInformationDialog_CONTENTS_DESCRIPTION;

    public static String DetailInformationDialog_CONTENTS_DETAIL_INFORMATION;

    public static String DetailInformationDialog_CONTENTS_ID;

    public static String DetailInformationDialog_CONTENTS_NAME;

    public static String DetailInformationDialog_CONTENTS_PROPERTY;

    public static String SearchMetaContentDialog_MACHED_CONTENTS_MESSAGE;
    public static String SearchMetaContentDialog_RESOURCE_NAME;
    public static String SearchMetaContentDialog_SEARCH_CONTENTS_MESSAGE;
    public static String SearchMetaContentDialog_SEARCH;
    public static String SearchMetaContentDialog_SEARCH_KEYWORD_MESSAGE;
    public static String SearchMetaContentDialog_SEARCH_PROPERTY;
    public static String SearchMetaContentDialog_SELETED_ITEMS;
    
    /** 속성이 같은 항목 */
    public static String ContentsManagementDialog_COMPARE_SAME;
    /** 속성이 다른 항목 */
	public static String ContentsManagementDialog_COMPARE_DIFF;
	/** 한쪽에만 있는 항목 */
	public static String ContentsManagementDialog_COMPARE_ONESIDE;
    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
