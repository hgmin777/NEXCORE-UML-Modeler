/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.editor.command;

import nexcore.tool.uml.model.modeldetail.ModelDetail;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.command</li>
 * <li>설  명 : HandleModelDetailCommand</li>
 * <li>작성일 : 2010. 6. 22.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class HandleModelDetailCommand extends RecordingCommand {

    /** 모델 요소 */
    private Model model;

    /** 모델 상세 */
    private ModelDetail modelDetail;

    /** 프로젝트 정보 적용 여부 */
    private boolean applyProjectInformation;

    /**
     * @param domain
     * @param label
     * @param description
     */
    public HandleModelDetailCommand(TransactionalEditingDomain domain, Model model, ModelDetail modelDetail,
    boolean applyProjectInformation) {
        super(domain);

        this.model = model;
        this.modelDetail = modelDetail;
        this.applyProjectInformation = applyProjectInformation;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (applyProjectInformation) {
            if (modelDetail != null) {
                model.getEAnnotations()
                    .remove(model.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__MODEL_DETAIL_EANNOTATION_SOURCE_NAME));

                model.getEAnnotations().add(modelDetail);
            }
        } else {
            model.getEAnnotations().remove(modelDetail);
        }

    }

}
