/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.editor.section;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.section</li>
 * <li>설 명 : AbstractSection</li>
 * <li>작성일 : 2009. 12. 14.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class AbstractSection extends SectionPart {

    /** FormPage 인스턴스 */
    private FormPage page;

    /** 설명, 에러 메시지를 위한 레이블 */
    private Text messageText;

    /** 제목 영역과 클라이언트 컴포짓 컨트롤러 간의 수직 간격 */
    public static final int CLIENT_VSPACING = 4;

    /** "" */
    protected static final String EMPTY_TEXT = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

    /**
     * 생성자
     * 
     * @param parent
     * @param toolkit
     * @param style
     */
    public AbstractSection(FormPage page, Composite parent, int style) {
        this(page, parent, style, true);
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param toolkit
     * @param style
     * @param titleBarYn
     */
    public AbstractSection(FormPage page, Composite parent, int style, boolean titleBarYn) {
        super(parent, page.getManagedForm().getToolkit(), titleBarYn ? (Section.TITLE_BAR | style) : style);
        this.page = page;
        // 설명, 에러 메시지를 출력할 컨트롤 설정
        messageText = (Text) getSection().getDescriptionControl();
        initialize(page.getManagedForm());

        getSection().clientVerticalSpacing = CLIENT_VSPACING;
        getSection().setData("part", this); //$NON-NLS-1$

        createClient(getSection(), page.getManagedForm().getToolkit());
    }

    /**
     * 클라이언트 영역 컴포짓 생성
     * 
     * @param section
     * @param toolkit
     *            void
     */
    protected abstract void createClient(Section section, FormToolkit toolkit);

    /**
     * FormPage 반환
     * 
     * @return FormPage
     */
    public FormPage getPage() {
        return page;
    }

    /**
     * 설명 메시지 설정
     * 
     * @param message
     *            void
     */
    public void setMessage(String message) {
        if (messageText != null) {
            messageText.setText(message);
            messageText.setBackground(new Color(null, 255, 255, 255));
            messageText.setForeground(new Color(null, 0, 0, 0));
        }
    }

    /**
     * 에러 메시지 설정
     * 
     * @param message
     *            void
     */
    public void setErrorMessage(String message) {
        if (messageText != null) {
            messageText.setText(message);
            messageText.setBackground(new Color(null, 255, 255, 255));
            messageText.setForeground(new Color(null, 255, 0, 0));
        }
    }

    /**
     * 메시지 반환
     * 
     * @return String
     */
    public String getMessage() {
        if (messageText != null) {
            return messageText.getText();
        }

        return EMPTY_TEXT;
    }

}
