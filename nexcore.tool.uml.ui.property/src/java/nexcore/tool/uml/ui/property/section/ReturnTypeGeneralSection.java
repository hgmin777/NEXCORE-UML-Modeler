/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : ReturnTypeGeneralSection</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 송주경</li>
 * </ul>
 */
public class ReturnTypeGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** 리턴 유형 제목 라벨 */
    private CLabel returnTypeTitleLabel;

    /** 리턴 유형 내용 라벨 */
    private CLabel returnTypeContentLabel;

    /** 리턴 유형 설정 버튼 */
    private Button searchTypeButton;

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
        sectionLayout = new GridLayout(4, false);
//        GridData data = new GridData(GridData.FILL_HORIZONTAL, GridData.FILL_HORIZONTAL, true, false);
//        sectionComposite.setLayoutData(data);
        sectionComposite.setLayout(sectionLayout);

        returnTypeTitleLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.LABEL_RETURNTYPE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
//        returnTypeTitleLabel.setLayoutData(gridData);

        returnTypeContentLabel = getWidgetFactory().createCLabel(sectionComposite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);

        searchTypeButton = getWidgetFactory().createButton(sectionComposite, UMLMessage.LABEL_SETRETURNTYPE, SWT.PUSH);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (this.getData().getReturnResult() != null) {
            settingTypeToReturnTypeContentLabel();
        } else {

            if (!returnTypeContentLabel.isDisposed()) {
                returnTypeContentLabel.setImage(null);
                returnTypeContentLabel.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);

                returnTypeContentLabel.getParent().pack();
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (searchTypeButton != null && !searchTypeButton.isDisposed()) {
            searchTypeButton.addSelectionListener(this);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (searchTypeButton != null && !searchTypeButton.isDisposed()) {
            searchTypeButton.removeSelectionListener(this);
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {

    }

    /**
     * getElements
     *  
     * @param inputElement
     * @return Object[]
     */
    public Object[] getElements(Object inputElement) {
        List<Parameter> parameterList;
        if (inputElement instanceof BehavioralFeature) {
            parameterList = ((Operation) inputElement).getOwnedParameters();
            Object[] objects = new Object[parameterList.size()];
            for (int i = 0; i < parameterList.size(); i++) {
                objects[i] = parameterList.get(i);
            }
            return objects;
        }
        return null;
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        TypeSelectDialog typeSelectDialog = new TypeSelectDialog(searchTypeButton.getShell(),
            TypeSelectDialogType.RETURN_TYPE, getData());

        int selected = typeSelectDialog.open();
        if (selected == TypeSelectDialog.OK) {
            if (typeSelectDialog.getFirstResult() != null) {
                final Type typeOfResult = (Type) typeSelectDialog.getFirstResult();
                final NamedElement namedElementOfResult = (NamedElement) typeSelectDialog.getFirstResult();
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        for (Parameter para : getData().returnResult()) {
                            para.destroy();
                        }

                        String returnName = "ret" + StringUtil.toUpperCaseAtFirstChar(namedElementOfResult.getName());

                        boolean isDuplicate = false;
                        List<Parameter> parameters = getData().getOwnedParameters();
                        for (Parameter p : parameters) {
                            if (returnName.equals(p.getName())) {
                                isDuplicate = true;
                                break;
                            }
                        }

                        String uniqueName = returnName;
                        if (isDuplicate) {
                            uniqueName = createUniqueName(returnName);
                        }

                        // 2011-02-17
                        // 리턴타입의 이름 자동 설정 수정
                        getData().createReturnResult(uniqueName, typeOfResult);
                    }
                });

                settingTypeToReturnTypeContentLabel();
            }
        } else if (selected == 16) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    for (Parameter para : getData().returnResult()) {
                        para.destroy();
                    }
                }
            });
            settingTypeToReturnTypeContentLabel();
        }
    }

    /**
     * createUniqueName
     *  
     * @param orgName
     * @return String
     */
    private String createUniqueName(String orgName) {
        return UMLManager.getPackagedUniqueName(this.getData(), orgName);
    }

    /**
     * 리턴 타입 레이블에 리턴타입을 세팅함. void
     */
    private void settingTypeToReturnTypeContentLabel() {
        if (this.getData().getReturnResult() == null) {
            if (!returnTypeContentLabel.isDisposed()) {
                returnTypeContentLabel.setImage(null);
                returnTypeContentLabel.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                returnTypeContentLabel.getParent().pack();
            }
        } else if (this.getData().getReturnResult().getType() != null) {
            // modified by nspark
            // 2011-02-17 redmine 일감번호 #12233 리턴타입 매개변수 이름 변경
            // String(UMLPrimitiveTypes::String) 형태로 수정.
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.getData().getType().getName());
            stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT);
            stringBuffer.append(this.getData().getType().getQualifiedName());
            stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT);
            if (returnTypeContentLabel !=null && !returnTypeContentLabel.isDisposed()) {
                returnTypeContentLabel.setImage(UICoreConstant.UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER.getImage(this.getData()
                    .getReturnResult()
                    .getType()));
                returnTypeContentLabel.setText(stringBuffer.toString());

                returnTypeContentLabel.getParent().pack();
            }
        }
    }
}
