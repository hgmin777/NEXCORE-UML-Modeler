/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project</li>
 * <li>설 명 : UMLPerspectiveFactory</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLPerspectiveFactory implements IPerspectiveFactory {

    /** page layout */
    private IPageLayout fLayout;

    /**
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(IPageLayout layout) {
        this.fLayout = layout;

        addViews();
        addActionSets();
        addNewWizardShortcuts();
        addPerspectiveShortcuts();
        addViewShortcuts();
    }

    /**
     * add Views
     */
    private void addViews() {
        // left
        IFolderLayout left = fLayout.createFolder(UICoreConstant.PROJECT_CONSTANTS__LEFT,
            IPageLayout.LEFT,
            0.20f,
            fLayout.getEditorArea());
        left.addView(UICoreConstant.PROJECT_CONSTANTS__PROJECT_EXPLORER_ID);
        left.addView(JavaUI.ID_PACKAGES);

        // bottom
        IFolderLayout bottom = fLayout.createFolder(UICoreConstant.PROJECT_CONSTANTS__BOTTOM,
            IPageLayout.BOTTOM,
            0.72f,
            fLayout.getEditorArea());
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);

        // right
        IFolderLayout right = fLayout.createFolder(UICoreConstant.PROJECT_CONSTANTS__RIGHT,
            IPageLayout.RIGHT,
            0.80f,
            fLayout.getEditorArea());
        right.addView(IPageLayout.ID_OUTLINE);
    }

    /**
     * add ActionSets
     */
    private void addActionSets() {
        // fLayout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        // fLayout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
        fLayout.addActionSet(JavaUI.ID_ACTION_SET);
        fLayout.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
    }

    /**
     * add Wizard Shortcuts
     */
    private void addNewWizardShortcuts() {

        fLayout.addNewWizardShortcut(UICoreConstant.PROJECT_CONSTANTS__WizardFolderID);
        fLayout.addNewWizardShortcut(UICoreConstant.PROJECT_CONSTANTS__WizardFileID);

        fLayout.addNewWizardShortcut(UICoreConstant.PROJECT_CONSTANTS__NewUmlProjectID);
        fLayout.addNewWizardShortcut(UICoreConstant.PROJECT_CONSTANTS__NewUmlID);

    }

    /**
     * add Perspective Shortcuts
     */
    private void addPerspectiveShortcuts() {
        fLayout.addPerspectiveShortcut(UICoreConstant.PROJECT_CONSTANTS__UmlPerspectiveID);
        fLayout.addPerspectiveShortcut(UICoreConstant.PROJECT_CONSTANTS__ResourcePerspectiveID);
        fLayout.addPerspectiveShortcut(JavaUI.ID_PERSPECTIVE);
    }

    /**
     * add View shortcuts
     */
    private void addViewShortcuts() {

        fLayout.addShowViewShortcut(UICoreConstant.PROJECT_CONSTANTS__PROJECT_EXPLORER_ID);
        fLayout.addShowViewShortcut(JavaUI.ID_PACKAGES);
        fLayout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
        fLayout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        fLayout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
    }

}
