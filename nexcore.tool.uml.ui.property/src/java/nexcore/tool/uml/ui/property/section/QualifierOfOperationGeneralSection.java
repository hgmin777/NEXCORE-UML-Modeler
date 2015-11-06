/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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
import org.eclipse.uml2.uml.Operation;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : QualifierOfOperationGeneralSection</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 송주경</li>
 * </ul>
 */
public class QualifierOfOperationGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** 규정자 라벨 */
    private CLabel checkButtonLabel;

    /** GridData */
    private GridData gridData;

    /** 리프 체크버튼 */
    private Button leafCheckBtn;

    /** 정렬됨 체크 버튼 */
    private Button orderedCheckBtn;

    /** 정적 체크 버튼 */
    private Button staticCheckBtn;

    /** 고유 체크 버튼 */
    private Button uniqueCheckBtn;

    /** 추상 체크 버튼 */
    private Button abstractCheckBtn;

    /** 조회 체크 버튼 */
    private Button queryCheckBtn;

    /** Composite */
    private Composite qualiferComposite;

    /**
     * 
     * 
     * @return Operation
     */
    private Operation getData() {
        return (Operation) this.getSelectedModel();
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
            UMLMessage.LABEL_QUALIFIER + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$        
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        checkButtonLabel.setLayoutData(gridData);
        qualiferComposite = getWidgetFactory().createComposite(sectionComposite);
        qualiferComposite.setLayout(new GridLayout(6, false));
        leafCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_LEAF, SWT.CHECK);
        orderedCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_ORDERED, SWT.CHECK);
        staticCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_STATIC, SWT.CHECK);
        uniqueCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_UNIQUE, SWT.CHECK);
        abstractCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_ABSTRACT, SWT.CHECK);
        queryCheckBtn = getWidgetFactory().createButton(qualiferComposite, UMLMessage.LABEL_QUERY, SWT.CHECK);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        settingQualifierOfOperationToWidget();

    }

    /**
     * 오퍼레이션의 규정자 값 초기 세팅 void
     */
    private void settingQualifierOfOperationToWidget() {

        if (!leafCheckBtn.isDisposed()) {
            if (this.getData().isLeaf()) {
                leafCheckBtn.setSelection(true);
            } else {
                leafCheckBtn.setSelection(false);
            }
        }

        if (!orderedCheckBtn.isDisposed()) {
            if (this.getData().isOrdered()) {
                orderedCheckBtn.setSelection(true);
            } else {
                orderedCheckBtn.setSelection(false);
            }
        }

        if (!staticCheckBtn.isDisposed()) {
            if (this.getData().isStatic()) {
                staticCheckBtn.setSelection(true);
            } else {
                staticCheckBtn.setSelection(false);
            }
        }

        if (!uniqueCheckBtn.isDisposed()) {
            if (this.getData().isUnique()) {
                uniqueCheckBtn.setSelection(true);
            } else {
                uniqueCheckBtn.setSelection(false);
            }
        }

        if (!abstractCheckBtn.isDisposed()) {
            if (this.getData().isAbstract()) {
                abstractCheckBtn.setSelection(true);
            } else {
                abstractCheckBtn.setSelection(false);
            }
        }

        if (!queryCheckBtn.isDisposed()) {
            if (this.getData().isQuery()) {
                queryCheckBtn.setSelection(true);
            } else {
                queryCheckBtn.setSelection(false);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#setListener()
     */
    @Override
    protected void setListener() {
        if (!leafCheckBtn.isDisposed()) {
            this.leafCheckBtn.addSelectionListener(this);
        }
        if (!orderedCheckBtn.isDisposed()) {
            this.orderedCheckBtn.addSelectionListener(this);
        }
        if (!staticCheckBtn.isDisposed()) {
            this.staticCheckBtn.addSelectionListener(this);
        }
        if (!uniqueCheckBtn.isDisposed()) {
            this.uniqueCheckBtn.addSelectionListener(this);
        }
        if (!abstractCheckBtn.isDisposed()) {
            this.abstractCheckBtn.addSelectionListener(this);
        }
        if (!queryCheckBtn.isDisposed()) {
            this.queryCheckBtn.addSelectionListener(this);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (!leafCheckBtn.isDisposed()) {
            this.leafCheckBtn.removeSelectionListener(this);
        }
        if (!leafCheckBtn.isDisposed()) {
            this.orderedCheckBtn.removeSelectionListener(this);
        }
        if (!leafCheckBtn.isDisposed()) {
            this.staticCheckBtn.removeSelectionListener(this);
        }
        if (!leafCheckBtn.isDisposed()) {
            this.uniqueCheckBtn.removeSelectionListener(this);
        }
        if (!leafCheckBtn.isDisposed()) {
            this.abstractCheckBtn.removeSelectionListener(this);
        }
        if (!leafCheckBtn.isDisposed()) {
            this.queryCheckBtn.removeSelectionListener(this);
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
        final Object source = e.getSource();
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                if (source.equals(leafCheckBtn)) {
                    if (leafCheckBtn.getSelection()) {
                        getData().setIsLeaf(true);
                    } else {
                        getData().setIsLeaf(false);
                    }
                }

                else if (source.equals(orderedCheckBtn)) {
                    if (orderedCheckBtn.getSelection()) {
                        getData().setIsOrdered(true);
                    } else {
                        getData().setIsOrdered(false);
                    }
                }

                else if (source.equals(staticCheckBtn)) {
                    if (staticCheckBtn.getSelection()) {
                        getData().setIsStatic(true);
                    } else {
                        getData().setIsStatic(false);
                    }
                }

                else if (source.equals(uniqueCheckBtn)) {
                    if (uniqueCheckBtn.getSelection()) {
                        getData().setIsUnique(true);
                    } else {
                        getData().setIsUnique(false);
                    }
                }

                else if (source.equals(abstractCheckBtn)) {
                    if (abstractCheckBtn.getSelection()) {
                        getData().setIsAbstract(true);
                    } else {
                        getData().setIsAbstract(false);
                    }
                }

                else if (source.equals(queryCheckBtn)) {
                    if (queryCheckBtn.getSelection()) {
                        getData().setIsQuery(true);
                    } else {
                        getData().setIsQuery(false);
                    }
                }
            }
        });
    }
}
