/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.editor.command;

import nexcore.tool.uml.model.projectinformation.ProjectElement;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.command</li>
 * <li>설  명 : HandleProjectRecordingCommand</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleProjectRecordingCommand extends RecordingCommand {
    /** 모델 요소 */
    private org.eclipse.uml2.uml.Package element;

    /** 프로젝트 요소 */
    private ProjectElement projectElement;

    /** 프로젝트 정보 적용여부 */
    private boolean applyProjectInformation;

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param projectElement
     * @param applyProjectInformation
     */
    public HandleProjectRecordingCommand(TransactionalEditingDomain domain, org.eclipse.uml2.uml.Package element,
    ProjectElement projectElement, boolean applyProjectInformation) {
        super(domain);

        this.element = element;
        this.projectElement = projectElement;
        this.applyProjectInformation = applyProjectInformation;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (applyProjectInformation) {
            if (projectElement != null) {
                element.getEAnnotations().remove(element.getEAnnotation("Project"));
                element.getEAnnotations().add(0, projectElement);
            }
        } else {
            element.getEAnnotations().remove(projectElement);
        }
    }

}
