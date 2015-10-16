/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.modeldetail.ModelDetail;
import nexcore.tool.uml.model.modeldetail.ModelType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설  명 : OverviewModelDetailSection</li>
 * <li>작성일 : 2010. 6. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class OverviewModelDetailSection extends AbstractSection {

    /** 모델 */
    private Model model;

    /** 모델상세 */
    private ModelDetail modelDetail;

    /** 국제화 처리된 모델 종류와 실제로 모델에 저장되는 모델 타입을 가지는 맵 */
    private static Map<String, ModelType> modelTypeMap;

    /** label */
    @SuppressWarnings("unused")
    private Label label;

    /** GridData */
    private GridData gridData;

    /** 모델 종류 콤보 */
    private Combo modelTypeCombo;

    /** 선택된 모델 종류 인덱스값 */
    private int indexOfModelTypeComboItem = 0;

    /** 모델 종류 이름 배열 */
    private String[] modelTypeNames = null;

    /**
     * 생성자
     * 
     * @param page
     * @param parent
     */
    public OverviewModelDetailSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);

    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        initializeSection();

        section.setText(UMLMessage.LABEL_MODEL_DETAIL_INFORMATION);
        section.setDescription(UMLMessage.MESSAGE_MODEL_DETAIL_INFORMATION_OVERVIEW);

        Composite composite = toolkit.createComposite(section);
        composite.setLayout(new GridLayout(2, true));

        gridData = new GridData(GridData.FILL_HORIZONTAL);
        composite.setLayoutData(gridData);

        createModelTypeComboComposite(toolkit, composite);

        setValueToWidget();

        section.setClient(composite);
    }

    /**
     * 모델 종류 선택하는 컴포짓 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createModelTypeComboComposite(FormToolkit toolkit, Composite composite) {
        label = toolkit.createLabel(composite, UMLMessage.LABEL_MODEL_TYPE);
        modelTypeCombo = new Combo(composite, SWT.READ_ONLY);

        modelTypeCombo.setItems(modelTypeNames);
        
        modelTypeCombo.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                markDirty();
                
            }
        });
    }

    /**
     * 위젯에 값 설정
     * 
     * void
     */
    public void setValueToWidget() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();

        modelDetail = (ModelDetail) model.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__MODEL_DETAIL_EANNOTATION_SOURCE_NAME);

        if (modelDetail != null) {
            if (modelDetail.getModelType().getName() == null) {
                modelTypeCombo.setText(EMPTY_TEXT);
            } else {
                ModelType modelType = modelDetail.getModelType();
                String modelKey = null;
                ModelType mapModelType = null;

                for (Iterator<String> keyIterator = modelTypeMap.keySet().iterator(); keyIterator.hasNext();) {
                    modelKey = keyIterator.next();
                    mapModelType = modelTypeMap.get(modelKey);
                    if (mapModelType != null && modelType.equals(mapModelType)) {
                        for (int i = 0; i < modelTypeNames.length; i++) {
                            if (modelTypeNames[i].equals(modelKey)) {
                                indexOfModelTypeComboItem = i;

                                modelTypeCombo.select(indexOfModelTypeComboItem);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            modelTypeCombo.setText(EMPTY_TEXT);
        }

    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    public void commit(boolean onSave) {
        //ModelDetail modelDetail = ModelDetailFactory.eINSTANCE.createModelDetail();
        //modelDetail.setSource(UICoreConstant.PROJECT_CONSTANTS__MODEL_DETAIL_EANNOTATION_SOURCE_NAME);

        //modelDetail.setModelType(modelTypeMap.get(modelTypeCombo.getText()));

        //RecordingCommand command = new HandleModelDetailCommand(DomainRegistry.getEditingDomain(),
        //    model,
        //    modelDetail,
        //    true);
        //DomainUtil.executeCommand(command);

        // DomainRegistry.getUMLDomain().save(model);
//        DomainModelHandlerUtil.save(model);

        super.commit(onSave);
    }

    /**
     * 국제화 처리된 모델 종류와 실제로 모델에 저장되는 모델 타입을 가지는 맵 초기화 void
     */
    private void initializeSection() {
        if (modelTypeMap == null) {
            modelTypeMap = new HashMap<String, ModelType>();
        }

        if (modelTypeMap.isEmpty()) {
            modelTypeMap.put(UMLMessage.LABEL_MODEL_TYPE_GENERAL, ModelType.USE_CASE);
            modelTypeMap.put(UMLMessage.LABEL_MODEL_TYPE_USECASE, ModelType.DESIGN);
            modelTypeMap.put(UMLMessage.LABEL_MODEL_TYPE_ANALYSIS, ModelType.ANALYSIS);
            modelTypeMap.put(UMLMessage.LABEL_MODEL_TYPE_DESIGN, ModelType.GENERAL);
        }

        modelTypeNames = new String[modelTypeMap.keySet().size()];
        int modelTypeNameIndex = 0;

        for (Iterator<String> iterator = modelTypeMap.keySet().iterator(); iterator.hasNext();) {
            modelTypeNames[modelTypeNameIndex] = iterator.next();
            modelTypeNameIndex++;
        }
    }

}
