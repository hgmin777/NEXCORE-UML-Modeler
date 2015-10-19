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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : QualifierOfAttributeGeneralSection</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class QualifierOfAttributeGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** qualifiersLabel */
    private CLabel qualifiersLabel;

    /** leafButton */
    private Button leafButton;

    /** orderdButton */
    private Button orderdButton;

    /** staticButton */
    private Button staticButton;

    /** uniqueButton */
    private Button uniqueButton;

    /** derivedButton */
    private Button derivedButton;

    /** derivedUnionButton */
    private Button derivedUnionButton;

    /** readOnlyButton */
    private Button readOnlyButton;

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
        sectionLayout = new GridLayout(8, false);
        sectionComposite.setLayout(sectionLayout);

        qualifiersLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_QUALIFIER) + UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHECK;
        qualifiersLabel.setLayoutData(gridData);

        leafButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_LEAF),
            SWT.CHECK);
        orderdButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_ORDERED),
            SWT.CHECK);
        staticButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_STATIC),
            SWT.CHECK);
        uniqueButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_UNIQUE),
            SWT.CHECK);
        derivedButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DERIVED),
            SWT.CHECK);
        derivedUnionButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DERIVED_UNION),
            SWT.CHECK);
        readOnlyButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_READ_ONLY),
            SWT.CHECK);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        settingQualifiersOfPropertyToWidget();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {

        if (!leafButton.isDisposed()) {
            this.leafButton.addSelectionListener(this);
        }
        if (!orderdButton.isDisposed()) {
            this.orderdButton.addSelectionListener(this);
        }
        if (!staticButton.isDisposed()) {
            this.staticButton.addSelectionListener(this);
        }
        if (!uniqueButton.isDisposed()) {
            this.uniqueButton.addSelectionListener(this);
        }
        if (!derivedButton.isDisposed()) {
            this.derivedButton.addSelectionListener(this);
        }
        if (!derivedUnionButton.isDisposed()) {
            this.derivedUnionButton.addSelectionListener(this);
        }
        if (!readOnlyButton.isDisposed()) {
            this.readOnlyButton.addSelectionListener(this);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {

        if (!leafButton.isDisposed()) {
            this.leafButton.removeSelectionListener(this);
        }
        if (!orderdButton.isDisposed()) {
            this.orderdButton.removeSelectionListener(this);
        }
        if (!staticButton.isDisposed()) {
            this.staticButton.removeSelectionListener(this);
        }
        if (!uniqueButton.isDisposed()) {
            this.uniqueButton.removeSelectionListener(this);
        }
        if (!derivedButton.isDisposed()) {
            this.derivedButton.removeSelectionListener(this);
        }
        if (!derivedUnionButton.isDisposed()) {
            this.derivedUnionButton.removeSelectionListener(this);
        }
        if (!readOnlyButton.isDisposed()) {
            this.readOnlyButton.removeSelectionListener(this);
        }

    }

    /**
     * 속성의 Qualifiers 위젯에 값을 세팅함. void
     */
    private void settingQualifiersOfPropertyToWidget() {
        if (!leafButton.isDisposed()) {
            if (this.getData().isLeaf()) {
                leafButton.setSelection(true);
            } else {
                leafButton.setSelection(false);
            }
        }

        if (!orderdButton.isDisposed()) {
            if (this.getData().isOrdered()) {
                orderdButton.setSelection(true);
            } else {
                orderdButton.setSelection(false);
            }
        }

        if (!staticButton.isDisposed()) {
            if (this.getData().isStatic()) {
                staticButton.setSelection(true);
            } else {
                staticButton.setSelection(false);
            }
        }

        if (!uniqueButton.isDisposed()) {
            if (this.getData().isUnique()) {
                uniqueButton.setSelection(true);
            } else {
                uniqueButton.setSelection(false);
            }
        }

        if (!derivedButton.isDisposed()) {
            if (this.getData().isDerived()) {
                derivedButton.setSelection(true);
            } else {
                derivedButton.setSelection(false);
            }
        }

        if (!derivedUnionButton.isDisposed()) {
            if (this.getData().isDerivedUnion()) {
                derivedUnionButton.setSelection(true);
            } else {
                derivedUnionButton.setSelection(false);
            }
        }

        if (!readOnlyButton.isDisposed()) {
            if (this.getData().isReadOnly()) {
                readOnlyButton.setSelection(true);
            } else {
                readOnlyButton.setSelection(false);
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
    public void widgetSelected(SelectionEvent e) {
        final Object source = e.getSource();
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                if (source.equals(leafButton)) {
                    if (leafButton.getSelection()) {
                        getData().setIsLeaf(true);
                    } else {
                        getData().setIsLeaf(false);
                    }
                } else if (source.equals(orderdButton)) {
                    if (orderdButton.getSelection()) {
                        getData().setIsOrdered(true);
                    } else {
                        getData().setIsOrdered(false);
                    }
                } else if (source.equals(staticButton)) {
                    if (staticButton.getSelection()) {
                        getData().setIsStatic(true);
                    } else {
                        getData().setIsStatic(false);
                    }
                } else if (source.equals(uniqueButton)) {
                    if (uniqueButton.getSelection()) {
                        getData().setIsUnique(true);
                    } else {
                        getData().setIsUnique(false);
                    }
                } else if (source.equals(derivedButton)) {
                    if (derivedButton.getSelection()) {
                        getData().setIsDerived(true);
                    } else {
                        getData().setIsDerived(false);
                    }
                } else if (source.equals(derivedUnionButton)) {
                    if (derivedUnionButton.getSelection()) {
                        getData().setIsDerivedUnion(true);
                    } else {
                        getData().setIsDerivedUnion(false);
                    }
                } else if (source.equals(readOnlyButton)) {
                    if (readOnlyButton.getSelection()) {
                        getData().setIsReadOnly(true);
                    } else {
                        getData().setIsReadOnly(false);
                    }
                }
            }
        });
    }

}
