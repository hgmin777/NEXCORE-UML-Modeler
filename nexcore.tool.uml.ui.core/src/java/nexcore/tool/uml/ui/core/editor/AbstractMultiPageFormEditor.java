/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.editor;

import java.util.ArrayList;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor</li>
 * <li>설  명 : AbstractMultiPageFormEditor</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class AbstractMultiPageFormEditor extends SharedHeaderFormEditor {

    /** 메시지 매니저 */
    private IMessageManager messageManager;

    /** 입력값 검증의 성공/실패 체크 멤버 변수 */
    protected boolean isValidate = true;

    /** 입력값 검증의 성공/실패 메시지 멤버 변수 */
    protected String validateFailedMessage = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

    /**
     * @see org.eclipse.ui.forms.editor.FormEditor#init(org.eclipse.ui.IEditorSite,
     *      org.eclipse.ui.IEditorInput)
     */
    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);

        createModel(input);

        setEditorTitleAndToolTip();
    }

    /**
     * 모델을 생성한다. 하위 클래스는 이 클래스를 반드시 재구현 해야 한다.
     * 
     * @param input
     */
    protected abstract void createModel(IEditorInput input) throws PartInitException;

    /**
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    @Override
    public void doSaveAs() {
        // TODO Auto-generated method stub
    }

    /**
     * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * @return the messageManager
     */
    public IMessageManager getMessageManager() {
        return messageManager;
    }

    /**
     * @param messageManager
     *            the messageManager to set
     */
    public void setMessageManager(IMessageManager messageManager) {
        this.messageManager = messageManager;
    }

    /**
     * @return the isValidate
     */
    public boolean isValidate() {
        return isValidate;
    }

    /**
     * @param validateFailedMessage
     *            the validateFailedMessage to set
     */
    public void addValidateFailedMessage(String message) {
        if (validateFailedMessage.indexOf(message) < 0) {
            this.validateFailedMessage += message;
        }
    }

    // 편의 메소드들. 아래는 이 클래스를 상속받는 하위 클래스들의 편의를 제공하는 편의 메소드들이다.

    /**
     * 에디터의 제목과 제목의 툴팁을 설정 - 제목은 getEditorTitle() 제목의 툴팁은
     * getEditorTitleToolTip() 을 호출하여 얻음.
     * 
     * void
     */
    protected void setEditorTitleAndToolTip() {
        setPartName(getEditorTitle());
        setTitleToolTip(getEditorTitleToolTip());
    }

    /**
     * 에디터의 제목을 반환
     * 
     * @return String
     */
    protected String getEditorTitle() {
        return getEditorInput().getName();
    }

    /**
     * 에디터 제목의 툴팁을 반환
     * 
     * @return String
     */
    protected String getEditorTitleToolTip() {
        return getEditorInput().getToolTipText();
    }

    /**
     * 에디터 페이지를 반환
     * 
     * @param index
     * @return IEditorPart
     */
    protected IEditorPart getEditorPage(int index) {
        if (index > -1 && index < getPageCount()) {
            return getEditor(index);
        }

        return null;
    }

    /**
     * 에디터에 추가된 폼 페이지 배열을 반환
     * 
     * @return IFormPage[]
     */
    public IFormPage[] getFormPages() {
        ArrayList<IFormPage> formPages = new ArrayList<IFormPage>();
        IFormPage page = null;

        if (pages != null) {
            for (int i = 0; i < pages.size(); i++) {
                page = (IFormPage) pages.get(i);

                if (page instanceof IFormPage) {
                    formPages.add(page);
                }
            }
        }

        return (IFormPage[]) formPages.toArray(new IFormPage[formPages.size()]);
    }

    /**
     * 모든 폼 페이지의 변경사항을 모델에 반영
     * 
     * void
     */
    protected void commitFormPages() {
        IFormPage[] pages = getFormPages();
        IFormPage page = null;
        IManagedForm managedForm = null;

        for (int i = 0; i < pages.length; i++) {
            page = pages[i];
            managedForm = page.getManagedForm();

            if (managedForm != null && managedForm.isDirty()) {
                managedForm.commit(true);

                // managedForm.refresh();
            }
        }
    }

    /**
     * @see org.eclipse.ui.part.MultiPageEditorPart#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getAdapter(Class adapter) {
        Object result = null;

        // 현재 활성화된 에디터 페이지의 getAdater()를 호출
        IEditorPart editor = getActiveEditor();

        if (editor != null) {
            result = editor.getAdapter(adapter);
        }

        if (result != null) {
            return result;
        }

        return super.getAdapter(adapter);
    }

}
