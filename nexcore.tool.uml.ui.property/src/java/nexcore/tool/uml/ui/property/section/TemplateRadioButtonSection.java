/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TemplateRadioButtonSection</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public abstract class TemplateRadioButtonSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** gridData */
    protected GridData gridData;

    /** publicButton */
    protected Button publicButton;

    /** privateButton */
    protected Button privateButton;

    /** protectedButton */
    protected Button protectedButton;

    /** packageButton */
    protected Button packageButton;

    /**
     * 모델의 isxxx() 값, 모델에 해당 값 세팅 여부를 반환함.
     * 
     * @return boolean
     */
    protected abstract VisibilityKind get();

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
    protected abstract void set(VisibilityKind value);

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);

        CLabel visibilityLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.LABEL_VISIBILITY + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$
        gridData = new GridData();

        Composite visibilityComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout(4, false);
        visibilityComposite.setLayout(sectionLayout);
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RADIO;
        visibilityLabel.setLayoutData(gridData);
        publicButton = getWidgetFactory().createButton(visibilityComposite, UMLMessage.LABEL_PUBLIC, SWT.RADIO);
        privateButton = getWidgetFactory().createButton(visibilityComposite, UMLMessage.LABEL_PRIVATE, SWT.RADIO);
        protectedButton = getWidgetFactory().createButton(visibilityComposite, UMLMessage.LABEL_PROTECTED, SWT.RADIO);
        packageButton = getWidgetFactory().createButton(visibilityComposite, UMLMessage.LABEL_PACKAGE, SWT.RADIO);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (null == this.publicButton || this.publicButton.isDisposed()) {
            return;
        }
        setVisibility();
    }

    /**
     * 
     * void
     */
    private void setVisibility() {
        if (this.get().equals(VisibilityKind.PUBLIC_LITERAL)) {
            this.publicButton.setSelection(true);
            this.privateButton.setSelection(false);
            this.protectedButton.setSelection(false);
            this.packageButton.setSelection(false);
        } else if (this.get().equals(VisibilityKind.PRIVATE_LITERAL)) {
            this.publicButton.setSelection(false);
            this.privateButton.setSelection(true);
            this.protectedButton.setSelection(false);
            this.packageButton.setSelection(false);
        } else if (this.get().equals(VisibilityKind.PROTECTED_LITERAL)) {
            this.publicButton.setSelection(false);
            this.privateButton.setSelection(false);
            this.protectedButton.setSelection(true);
            this.packageButton.setSelection(false);
        } else if (this.get().equals(VisibilityKind.PACKAGE_LITERAL)) {
            this.publicButton.setSelection(false);
            this.privateButton.setSelection(false);
            this.protectedButton.setSelection(false);
            this.packageButton.setSelection(true);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (this.publicButton == null || this.publicButton.isDisposed()) {
            return;
        }
        Display.getDefault().syncExec(new Runnable() {
            
            @Override
            public void run() {
                TemplateRadioButtonSection.this.publicButton.removeSelectionListener(TemplateRadioButtonSection.this);
                TemplateRadioButtonSection.this.privateButton.removeSelectionListener(TemplateRadioButtonSection.this);
                TemplateRadioButtonSection.this.protectedButton.removeSelectionListener(TemplateRadioButtonSection.this);
                TemplateRadioButtonSection.this.packageButton.removeSelectionListener(TemplateRadioButtonSection.this);
                
            }
        });
        
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (this.publicButton == null || this.publicButton.isDisposed()) {
            return;
        }
        this.publicButton.addSelectionListener(this);
        this.privateButton.addSelectionListener(this);
        this.protectedButton.addSelectionListener(this);
        this.packageButton.addSelectionListener(this);
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                set(getSelectedVisibilityKind());
            }
        });
    }

    /**
     * 
     * 
     * @return VisibilityKind
     */
    private VisibilityKind getSelectedVisibilityKind() {
        if (this.publicButton.getSelection()) {
            return VisibilityKind.PUBLIC_LITERAL;
        } else if (this.privateButton.getSelection()) {
            return VisibilityKind.PRIVATE_LITERAL;
        } else if (this.protectedButton.getSelection()) {
            return VisibilityKind.PROTECTED_LITERAL;
        } else if (this.packageButton.getSelection()) {
            return VisibilityKind.PACKAGE_LITERAL;
        }
        return null;
    }
}
