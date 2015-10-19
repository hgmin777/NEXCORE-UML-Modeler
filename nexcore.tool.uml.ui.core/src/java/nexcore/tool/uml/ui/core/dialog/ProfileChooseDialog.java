/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.extension.registry.PrecedingInitializerRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.editor.util.EditorUtil;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.uml2.uml.Profile;

/**
 * 도구내에서 공통적으로 사용할 프로파일 선택 다이얼로그
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설 명 : ProfileChooseDialog</li>
 * <li>작성일 : 2011. 3. 3.</li>
 * <li>작성자 : hyun</li>
 * </ul>
 * 
 */
public class ProfileChooseDialog extends TitleAreaDialog {
    /** 타이틀 */
    private String title;

    /** 테이블 뷰어 */
    private TableViewer tableViewer;

    /** 선택된 Object */
    private Object selectedObject;

    /** 프로젝트 정보를 찾기 위해 필요한 URI 정보 */
    private URI uri;

    /** applyingProfileList */
    private List<Profile> applyingProfileList;

    /**
     * 생성자
     * 
     * @param parentShell
     * @param title
     * @param model
     */
    public ProfileChooseDialog(Shell parentShell, String title, URI uri) {
        super(parentShell);
        this.title = title;
        this.uri = uri;
    }

    /**
     * ProfileChooseDialog
     * @param parentShell
     * @param title
     * @param uri
     * @param applyingProfileList
     */
    public ProfileChooseDialog(Shell parentShell, String title, URI uri, List<Profile> applyingProfileList) {
        super(parentShell);
        this.title = title;
        this.uri = uri;
        this.applyingProfileList = applyingProfileList;
    }

    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);

        newShell.setText(title);
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Control content = super.createContents(parent);
        setTitle(title);
        setMessage(UMLMessage.MESSAGE_SELECT_PROFILE_DESCRIPTION); // "적용할 프로파일을 선택해 주세요.");
        return content;
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);

        tableViewer = new TableViewer(container, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new LabelProvider() {
            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                if (element instanceof Profile) {
                    return ((Profile) element).getName();
                }

                return UICoreConstant.EMPTY_STRING;
            }
        });

        // 모델에 이미 적용되어 있는 프로파일은 리스트에 추가하지 않는다. 2011/04/26
        List<Object> applicableProfileList = new ArrayList<Object>();
        List<Object> allProfileList = getApplicableProfileList();
        if (applyingProfileList == null) {
            applicableProfileList.addAll(allProfileList);
        } else {
            for (Object obj : allProfileList) {
                if (!applyingProfileList.contains(obj)) {
                    applicableProfileList.add(obj);
                }
            }
        }

        if (!applicableProfileList.isEmpty()) {
            tableViewer.setInput(applicableProfileList.toArray());
        }
        
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                okPressed();
            }
        });

        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = convertHeightInCharsToPixels(15);
        gd.widthHint = convertWidthInCharsToPixels(55);
        Table table = tableViewer.getTable();
        table.setLayoutData(gd);
        table.setFont(container.getFont());

        return container;
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        selectedObject = selection.getFirstElement();
        super.okPressed();
    }

    /**
     * 선택된 프로파일을 반환한다.
     * 
     * 
     * @return Object
     */
    public Profile getSelectedObject() {
        return (Profile) selectedObject;
    }

    /**
     * 프로파일 리스트를 반환한다. -- 프로파일 조회 절차(2011.03.04현재) 1. UML / MDA 프로파일용 프로파일이 있으면
     * 추가한다. 2. 프로젝트의 프로파일 폴더 내에 프로파일이 있으면 추가한다. 3. 프로젝트 root위치에 프로파일이 있으면 추가한다.
     * 
     */
    private List<Object> getApplicableProfileList() {
        List<Object> applicableProfileList = new ArrayList<Object>();
        // UML Core 프로파일 존재 여부 확인
        boolean isExistUMLCoreProfile = PrecedingInitializerRegistry.getInstance()
            .isProjectExist(ManagerConstant.NEXCORE_TOOL_UML_UI_CORE_PRECEDING_INITIALIZER_ID);
        // MDA 프로파일 존재 여부 확인
        boolean isExistMDAProfile = PrecedingInitializerRegistry.getInstance()
            .isProjectExist(ManagerConstant.NEXCORE_TOOL_MDA_CORE_PRECEDING_INITIALIZER_ID);

        if (applicableProfileList == null) {
            applicableProfileList = new ArrayList<Object>();
        } else if (!applicableProfileList.isEmpty()) {
            applicableProfileList.clear();
        }

        // UML Core 프로파일이 존재하면 적용할수 있는 프로파일 목록에 추가
        if (isExistUMLCoreProfile) {
            applicableProfileList.addAll(PrecedingInitializerRegistry.getInstance()
                .getProperInitializer(ManagerConstant.NEXCORE_TOOL_UML_UI_CORE_PRECEDING_INITIALIZER_ID)
                .getFeedbacks());
        }
        // MDA 프로파일이 존재하면 적용할수 있는 프로파일 목록에 추가
        if (isExistMDAProfile) {
            applicableProfileList.addAll(PrecedingInitializerRegistry.getInstance()
                .getProperInitializer(ManagerConstant.NEXCORE_TOOL_MDA_CORE_PRECEDING_INITIALIZER_ID)
                .getFeedbacks());
        }

        /**
         * 프로젝트용 프로파일이 존재하면 프로파일 목록에 추가
         */

        List<Object> projectProfileList = EditorUtil.getProjectProfileList(this.uri);
        // 목록이 있을 경우 적용할 수 있는 프로파일 목록에 추가
        if (!projectProfileList.isEmpty()) {
            applicableProfileList.addAll(projectProfileList);
        }

        /**
         * 프로젝트 루트에 프로파일이 존재하면 프로파일 목록에 추가
         */
        projectProfileList.clear();
        projectProfileList = EditorUtil.getProjectRootProfileList(this.uri);
        if (!projectProfileList.isEmpty()) {
            applicableProfileList.addAll(projectProfileList);
        }

        return applicableProfileList;
    }

    /**
     * 적용할 프로파일이 존재 하는가?
     * 
     * 
     * @return Boolean
     */
    public Boolean isExistApplicableProfiles() {
        if (getApplicableProfileList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
