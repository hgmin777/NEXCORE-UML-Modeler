/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.List;

import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TemplateTextSection</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public abstract class TemplateTextSection extends AbstractPropertyCommonSection implements KeyListener, FocusListener {

    /** text */
    protected Text text;

    /** gridData */
    protected GridData gridData;

    /** label */
    protected CLabel label;

    /** section */
    protected Class<? extends ISection> activatedSection;
    
    /**
     * excutedData
     */
    private String excutedData = null;

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);

        label = getWidgetFactory().createCLabel(sectionComposite,
            this.getLabelText() + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        label.setLayoutData(gridData);
        this.createTextControl();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (null == this.text || this.text.isDisposed()) {
            return;
        }
        Display.getDefault().syncExec(new Runnable() {
            
            @Override
            public void run() {
                if (null == text || text.isDisposed()) {
                    return;
                }
                text.removeKeyListener(TemplateTextSection.this);
                text.removeFocusListener(TemplateTextSection.this);
            }
        });
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        Display.getDefault().asyncExec(new Runnable() {
            
            @Override
            public void run() {
                if (null == text || text.isDisposed()) {
                    return;
                }
                
                String str = get();
                if (null != str && !str.equals(text.getText())) {
                    text.setText(str);
                } else if (str == null) {
                    text.setText("");
                    
                }
            }
        });
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        Display.getDefault().asyncExec(new Runnable() {
            
            @Override
            public void run() {
                if (null == text || text.isDisposed()) {
                    return;
                }
                
                text.addKeyListener(TemplateTextSection.this);
                text.addFocusListener(TemplateTextSection.this);
            }
        });


    }

    /**
     * 해당 레이블의 이름을 리턴함.
     * 
     * @return String
     */
    protected abstract String getLabelText();

    /**
     * 선택된 모델에 해당 스트링 속성 값을 리턴함.
     * 
     * @return String
     */
    protected abstract String get();

    /**
     * 선택된 모델에 해당 스트링 속성 값을 입력함.
     * 
     * @param value
     *            void
     */
    protected abstract void set(String value);

    /**
     * 각 요소에 맞는 텍스트 위젯을 생성함. void
     */
    protected abstract void createTextControl();

    /**
     * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
     */
    public void modifyText(ModifyEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        if (e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER
            || e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER_SECOND) {
            if (activatedSection != null) {

                if (activatedSection.getSimpleName().equals(text.getMessage())) {
                    return;
                }
            } else {
                final String textValue = text.getText().trim();
                if (this.get() != null && this.get().equals(textValue)) {
                    return;
                }
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        try {
                            setExcutedData(textValue);
                            set(textValue);
                            refreshChildren();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            Object target = getExistingInput();
            if (target instanceof EditPart) {
                EditPart editpart = (EditPart) getExistingInput();
                RootEditPart root = editpart.getRoot();
                List<EditPart> editparts = root.getChildren();
                for (EditPart diagramEditparts : editparts) {
                    List<EditPart> diagrameditpart = diagramEditparts.getChildren();
                    for (EditPart modelEditPart : diagrameditpart) {
                        if (modelEditPart.equals(editpart)) {
                            editpart.refresh();
                            for (int i = 0; i < editpart.getChildren().size(); i++) {
                                ((EditPart) editpart.getChildren().get(i)).refresh();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    @SuppressWarnings("unchecked")
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(FocusEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(FocusEvent e) {

        String modelValue = this.get();
        final String textValue = text.getText().trim();

        if (null == textValue)
            return;
        if (null == modelValue && 0 == textValue.length()) {
            return;
        }
        if (null != modelValue && modelValue.equals(textValue)) {

            return;
        }
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                try {
                    if( null == get() || !get().equals(textValue) ) {
                        set(textValue);
                        refreshChildren();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Object target = getExistingInput();
        if (target instanceof EditPart) {
            EditPart editpart = (EditPart) getExistingInput();
            RootEditPart root = editpart.getRoot();
            List<EditPart> editparts = root.getChildren();
            for (EditPart diagramEditparts : editparts) {
                List<EditPart> diagrameditpart = diagramEditparts.getChildren();
                for (EditPart modelEditPart : diagrameditpart) {
                    if (modelEditPart.equals(editpart)) {
                        editpart.refresh();
                        for (int i = 0; i < editpart.getChildren().size(); i++) {
                            ((EditPart) editpart.getChildren().get(i)).refresh();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * getExcutedData
     *  
     * @return String
     */
    public String getExcutedData() {
        return excutedData;
    }

    /**
     * setExcutedData
     *  
     * @param excutedData void
     */
    public void setExcutedData(String excutedData) {
        this.excutedData = excutedData;
    }
}
