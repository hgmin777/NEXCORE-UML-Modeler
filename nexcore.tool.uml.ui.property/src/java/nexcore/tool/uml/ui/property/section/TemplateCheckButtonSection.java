/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TemplateCheckButtonSection</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public abstract class TemplateCheckButtonSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** abstractButton */
    protected Button checkButton;

    /** abstractLabel */
    protected CLabel checkButtonLabel;

    /** gridData */
    protected GridData gridData;

    /**
     * 모델의 isxxx() 값, 모델에 해당 값 세팅 여부를 반환함.
     * 
     * @return boolean
     */
    protected abstract boolean get();

    /**
     * 모델에서 사용되는 레이블 위젯 이름을 리턴함.
     * 
     * @return String
     */
    protected abstract String getLabelText();

    /**
     * 모델에 setIsxxx() 로 여부 값을 세팅함.
     * 
     * @param value
     *            void
     */
    protected abstract void set(boolean value);

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (this.checkButton != null) {
            if (!this.checkButton.isDisposed()) {
                this.checkButton.addSelectionListener(this);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (this.checkButton != null) {
            if (!this.checkButton.isDisposed()) {
                this.checkButton.removeSelectionListener(this);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);

        checkButtonLabel = getWidgetFactory().createCLabel(sectionComposite,
            this.getLabelText() + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$        
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        checkButtonLabel.setLayoutData(gridData);
        checkButton = getWidgetFactory().createButton(sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.CHECK);

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (this.checkButton != null) {
            if (!this.checkButton.isDisposed()) {
                this.checkButton.setSelection(this.get());
            }
        }

    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(final SelectionEvent e) {

        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {

                Button button = (Button) e.getSource();
                set(button.getSelection());

            }
        });
    }
}
