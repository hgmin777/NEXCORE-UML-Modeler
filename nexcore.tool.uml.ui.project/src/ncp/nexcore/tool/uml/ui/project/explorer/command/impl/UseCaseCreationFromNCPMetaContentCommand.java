/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.command.impl;

import java.util.List;

import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.SemanticModelHandlerRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command.impl</li>
 * <li>설  명 : UseCaseCreationFromNCPMetaContentCommand</li>
 * <li>작성일 : 2011. 7. 27.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UseCaseCreationFromNCPMetaContentCommand extends UMLExplorerExtendedCommand {

    /** 시맨틱 모델 핸들러 */
    private ISemanticModelHandler semanticModelHandler;

    /** 프로젝트 코드 */
    private String projectCode;
    /** 선택한 Meta Content 목록 */
    private List<?> selectedContents; 
    
    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param projectCode
     * @param selectedContents
     * @param monitor
     */
    public UseCaseCreationFromNCPMetaContentCommand(TransactionalEditingDomain domain, List<Element> selectedElementList, Shell parentShell, String projectCode, List<?> selectedContents, IProgressMonitor monitor) {
        super(domain, selectedElementList, parentShell, monitor);

        this.projectCode = projectCode;
        this.selectedContents = selectedContents;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand#initialize()
     */
    @Override
    public void initialize() {
        // 시맨틱 모델 핸들러 획득
        if(SemanticModelHandlerRegistry.getInstance() != null) {
            semanticModelHandler = SemanticModelHandlerRegistry.getInstance().getHandlerInstance(UICoreConstant.NEXCORE_TOOL_UML_UI_PROJECT_SEMANTIC_MODEL_HANDLER);

            if (semanticModelHandler != null && selectedElementList != null) {
                semanticModelHandler.setElementList(selectedElementList);
                semanticModelHandler.setProgressMonitor(monitor);
            }
        }
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
//        try {
//            // 유스케이스 생성(From NCP Meta Content)을 위한 초기화
//            if(selectedContents != null && selectedContents.size() > 0) {
//                semanticModelHandler.initializeUseCaseCreationFromNCPMetaContent(projectCode, selectedContents);
//            }
//
//            // 유스케이스 생성(From NCP Meta Content) 실행
//            semanticModelHandler.createUseCaseFromNCPMetaContent();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
