/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : RefactorPreferencePage</li>
 * <li>작성일 : 2011. 9. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class RefactorPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** 그리드 레이아웃에서 위젯간 가로 간격 크기 */
    private final static int HORISONTAL_SPACING = 15;

    /** Prefix 텍스트 */
    private Text prefixText;

    /** 타겟 엘리먼트의 Prefix */
    private String targetElementPrefix;

    /** IO_Prefix 텍스트 */
    private Text ioPrefixText;

    /** IO_Prefix 텍스트 */
    private String ioPrefix;

    /** PreferenceStore */
    IPreferenceStore store;

    /**
     * 생성자
     */
    public RefactorPreferencePage() {
        super();
        setPreferenceStore(UiCorePlugin.getDefault().getPreferenceStore());
        setDescription(UMLMessage.MESSAGE_COPY_BIZUNIT_OPERATION_TO_COMPONENT);//bizUnit의 오퍼레이션을 컴포넌트로 복사합니다.");

        store = getPreferenceStore();
    }

    /**
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        if (store.getString(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_TARGET_ELEMENT_PREFIX) != null) {
            targetElementPrefix = store.getString(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_TARGET_ELEMENT_PREFIX);
        }

        if (store.getString(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_IO_IMPORT) != null) {
            ioPrefix = store.getString(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_IO_IMPORT);
        }
    }

    /**
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.verticalSpacing = 30;
        gridLayout.horizontalSpacing = 20;
        composite.setLayout(gridLayout);

        createPackageNameGroup(composite);
        createIoImportGroup(composite);

        // 기본값 세팅
        setDefaultValues();

        return composite;
    }

    /**
     * 패키지 명 설정 영역 생성
     * 
     * 
     * @param composite
     *            void
     */
    private void createPackageNameGroup(Composite parent) {

        Group group = new Group(parent, SWT.SHADOW_OUT);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = HORISONTAL_SPACING;
        group.setLayout(gridLayout);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText(UMLMessage.LABEL_OPERATION_AUTO_GENERATE);//"컴포넌트 리팩터"); 

        createUsecasePackageName(group);
    }

    /**
     * 
     * 유스케이스모델 전개 패키지명
     * 
     * @param group
     *            void
     */
    private void createUsecasePackageName(Group group) {
        Label label = new Label(group, SWT.NONE);
        label.setText(UMLMessage.LABEL_TARGET_PREFIX_FOR_COPING_OPERATION);//"복사할 오퍼레이션이 포함된 클래스의 Prefix : "); 
        label.setLayoutData(new GridData(GridData.BEGINNING));

        prefixText = new Text(group, SWT.BORDER);
        setTextAttributes(prefixText, 150);

    }

    /**
     * IO가져오기 설정 패키지명 Prefix 설정
     * 
     * 
     * @param composite
     *            void
     */
    private void createIoImportGroup(Composite parent) {

        Group group = new Group(parent, SWT.SHADOW_OUT);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = HORISONTAL_SPACING;
        group.setLayout(gridLayout);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText(UMLMessage.LABEL_IO_IMPORT);// "IO 가져오기

        Label label = new Label(group, SWT.NONE);
        label.setText(UMLMessage.LABEL_IO_IMPORT_PACKAGE_PREFIX);// "IO를 가져올 패키지명의 Prefix")
        label.setLayoutData(new GridData(GridData.BEGINNING));

        ioPrefixText = new Text(group, SWT.BORDER);
        setTextAttributes(ioPrefixText, 150);
    }

    /**
     * 텍스트 초기값 세팅
     * 
     * void
     */
    private void setDefaultValues() {
        if (!isEmpty(targetElementPrefix)) {
            prefixText.setText(targetElementPrefix);
        } else {
            prefixText.setText(UICoreConstant.EMPTY_STRING);
        }
        
        if (!isEmpty(ioPrefix)) {
            ioPrefixText.setText(ioPrefix);
        } else {
            ioPrefixText.setText(UICoreConstant.EMPTY_STRING);
        }
    }

    /**
     * 
     * isEmpty
     * 
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (string == null || UICoreConstant.EMPTY_STRING.equals(string)) {
            return true;
        }

        return false;
    }
    /**
     * 입력값의 공백을 제거한 후, 빈 문자열(&quot;&quot;)인지를 검사한다. 빈 문자열인 경우 true, 아니면 false를
     * 리턴한다.
     * 
     * <pre>
     * boolean empty = isEmptyAfterTrim(&quot; abc &quot;);
     * // empty =&gt; false
     * </pre>
     * 
     * @param string
     *            입력값
     * @return boolean 빈 문자열 여부 (true."" false.!"")
     */
    public static boolean isEmptyAfterTrim(String string) {
        return string == null || string.trim().equals(UICoreConstant.EMPTY_STRING) ? true : false;
    }
    /**
     * 
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    public boolean performOk() {
        store.setValue(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_TARGET_ELEMENT_PREFIX, prefixText.getText());
        store.setValue(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_IO_IMPORT, ioPrefixText.getText());
        return super.performOk();
    }

    /**
     * 
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        prefixText.setText(UICoreConstant.EMPTY_STRING);
        ioPrefixText.setText(UICoreConstant.EMPTY_STRING);
        super.performDefaults();
    }

    /**
     * 텍스트 생성
     * 
     * @param text
     *            void
     */
    private void setTextAttributes(Text text, int width) {

        GridData gridData = new GridData();
        gridData.widthHint = width;
        gridData.horizontalAlignment = SWT.LEFT;
        text.setLayoutData(gridData);
    }
}
