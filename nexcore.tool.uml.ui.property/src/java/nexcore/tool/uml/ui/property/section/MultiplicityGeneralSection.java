/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.property.type.MultiplicityType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : MultiplicityGeneralSection</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class MultiplicityGeneralSection extends AbstractPropertyCommonSection implements SelectionListener,
KeyListener, FocusListener {

    /** multiplicityLabel */
    private CLabel multiplicityLabel;

    /** multiplicityCombo */
    private CCombo multiplicityCombo;

    /**
     * Property 모델을 리턴.
     * 
     * @return Property
     */
    private Property getData() {
        return (Property) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(3, false);
        sectionComposite.setLayout(sectionLayout);

        multiplicityLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_MULTIPLICITY) + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        multiplicityLabel.setLayoutData(gridData);
        this.multiplicityCombo = getWidgetFactory().createCCombo(sectionComposite, SWT.NONE);
        this.multiplicityCombo.setItems(UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES);
        this.multiplicityCombo.setVisibleItemCount(5);
        this.multiplicityCombo.setEditable(true);
        this.multiplicityCombo.setText(MultiplicityType.UNIQUE.toString());
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (!multiplicityCombo.isDisposed()) {
            this.multiplicityCombo.setText(getMultiplicity(this.getData()));
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (!multiplicityCombo.isDisposed()) {
            this.multiplicityCombo.addSelectionListener(this);
            this.multiplicityCombo.addKeyListener(this);
            this.multiplicityCombo.addFocusListener(this);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (!multiplicityCombo.isDisposed()) {
            this.multiplicityCombo.removeSelectionListener(this);
            this.multiplicityCombo.removeKeyListener(this);
            this.multiplicityCombo.removeFocusListener(this);
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
    public void widgetSelected(SelectionEvent e) {
        final CCombo combo = (CCombo) e.widget;
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                setMultiplicityOfPropertyToModel(combo);
            }
        });
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        if (e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER
            || e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER_SECOND) {
            String text = multiplicityCombo.getText();

            try {
                final int value = new Integer(text).intValue();
                final Property property = this.getData();
                if (value > 0) {

                    DomainUtil.run(new TransactionalAction() {
                        @Override
                        public void doExecute() {
                            property.setLower(value);
                            property.setUpper(value);
                        }
                    });
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
        String text = multiplicityCombo.getText();

        try {
            final int value = new Integer(text).intValue();
            final Property property = this.getData();
            if (value > 0) {

                DomainUtil.run(new TransactionalAction() {
                    @Override
                    public void doExecute() {
                        property.setLower(value);
                        property.setUpper(value);
                    }
                });
            }
        } catch (Exception e2) {
            // TODO: handle exception
        }

    }

    /**
     * 속성 모델에 다중성 값을 세팅
     * 
     * @param combo
     *            void
     */
    private void setMultiplicityOfPropertyToModel(CCombo combo) {
        if ((combo.getText()).equals(MultiplicityType.SINGLE_STAR.toString())) {
            // *
            this.getData().setLower(0);
            this.getData().setUpper(LiteralUnlimitedNatural.UNLIMITED);
        } else if ((combo.getText()).equals(MultiplicityType.ZERO_TO_UNIQUE.toString())) {
            // 0.. 1
            this.getData().setLower(0);
            this.getData().setUpper(1);
        } else if ((combo.getText()).equals(MultiplicityType.UNIQUE.toString())) {
            // 1
            this.getData().setLower(1);
            this.getData().setUpper(1);
        } else if ((combo.getText()).equals(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString())) {
            // 1.. *
            this.getData().setLower(1);
            this.getData().setUpper(LiteralUnlimitedNatural.UNLIMITED);
        } else if ((combo.getText()).equals(MultiplicityType.NONE.toString())) {
            // 1
            this.getData().setLower(1);
            this.getData().setUpper(1);
        } else {
            return;
        }
    }

    /**
     * 다중성 유형을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getMultiplicity(Property property) {
        if (property.getUpperValue() != null && property.getLowerValue() != null) {
            if (isSingleStar(property)) {
                return MultiplicityType.SINGLE_STAR.toString();
            } else if (isZeroToUnique(property)) {
                return MultiplicityType.ZERO_TO_UNIQUE.toString();
            } else if (isUnique(property)) {
                return MultiplicityType.UNIQUE.toString();
            } else if (isUniqueToSingleStar(property)) {
                return MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString();
            } else {
                // 숫자를 직접 입력한 경우
                return property.getUpperValue().stringValue();
            }
        } else {
            return MultiplicityType.UNIQUE.toString();
        }
    }

    /**
     * 다중성이 "1.. *" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isUniqueToSingleStar(org.eclipse.uml2.uml.Property property) {
        if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(property.getLowerValue().stringValue())) {
            if (MultiplicityType.SINGLE_STAR.toString().equals(property.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "1" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isUnique(org.eclipse.uml2.uml.Property property) {
        if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(property.getLowerValue().stringValue())) {
            if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(property.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "0.. 1" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isZeroToUnique(org.eclipse.uml2.uml.Property property) {
        if (UICoreConstant.UMLSECTION_CONSTANTS___ZERO.equals(property.getLowerValue().stringValue())) {
            if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(property.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "*" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isSingleStar(org.eclipse.uml2.uml.Property property) {
        if (UICoreConstant.UMLSECTION_CONSTANTS___ZERO.equals(property.getLowerValue().stringValue())) {
            if (MultiplicityType.SINGLE_STAR.toString().equals(property.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }
}
