/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.command.execution.unit;

import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.SemanticModelHandlerRegistry;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.extension.UMLCommandExecutionUnit;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command.execution.unit</li>
 * <li>설  명 : GlossaryTranslation</li>
 * <li>작성일 : 2011. 6. 10.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class GlossaryTranslation extends UMLCommandExecutionUnit {

    /** 시맨틱 모델 핸들러 */
    private ISemanticModelHandler semanticModelHandler;

    /**
     * 생성자
     */
    public GlossaryTranslation() {
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.UMLCommandExecutionUnit#initialize()
     */
    @Override
    public void initialize() {
        if (SemanticModelHandlerRegistry.getInstance() != null) {
            semanticModelHandler = SemanticModelHandlerRegistry.getInstance()
                .getHandlerInstance(UICoreConstant.NEXCORE_TOOL_UML_UI_PROJECT_SEMANTIC_MODEL_HANDLER);

            if(semanticModelHandler != null && selectedElementList != null) {
                semanticModelHandler.setElementList(selectedElementList);
                semanticModelHandler.setParentShell(parentShell);
                semanticModelHandler.setProgressMonitor(monitor);

                // 전달된 공유 객체가 있을 경우 비지니스 클래스로 전달
                if(deliveredObject != null) {
                    semanticModelHandler.setSharingObject(deliveredObject);
                }
            }
        }
        
        // 실행결과 표시시점을 '마지막'으로 설정
        setExecutionResultDisplayTime();
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.UMLCommandExecutionUnit#execute()
     */
    @Override
    public void execute() {
        boolean result = false;

        semanticModelHandler.initializeGlossaryManagement();

        result = semanticModelHandler.translateGlossaryIntoEnglish();

        if(result) {
            // 결과 boolean 값에 따라 메시지를 바로 보이지 않고 Status 설정으로 변경
            /*
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.MESSAGE_TRANSLATION_OF_SELECTED_MODEL_HAS_BEEN_COMPLETED_SUCCESSFULLY); // Messages.getString("TransformToAnalysisModelInEnglishCommand_DIALOG_MSG_COMPLETE"));
             */

//            String text = UMLMessage.MESSAGE_TRANSLATION_OF_SELECTED_MODEL_HAS_BEEN_COMPLETED_SUCCESSFULLY;
//            if (semanticModelHandler instanceof SemanticModelHandler) {
//                int tn = ((SemanticModelHandler) semanticModelHandler).getTranslationNumber();
//                text = text + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + String.valueOf(tn) + "개의 용어가 변환됨";
//            }

//            executionResult = new Status(IStatus.OK, ProjectExplorerPlugin.PLUGIN_ID, text);
        } else {
            // 결과 boolean 값에 따라 메시지를 바로 보이지 않고 Status 설정으로 변경
            /*
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.MESSAGE_ERROR_TRANSLATION_OF_SELECTED_MODEL_FAILED); // Messages.getString("TransformToAnalysisModelInEnglishCommand_DIALOG_MSG_FAILURE"));
             */
            executionResult = new Status(IStatus.ERROR,
                ProjectExplorerPlugin.PLUGIN_ID,
                UMLMessage.MESSAGE_ERROR_TRANSLATION_OF_SELECTED_MODEL_FAILED);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.UMLCommandExecutionUnit#getSharingObject()
     */
    @Override
    public Object getSharingObject() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.UMLCommandExecutionUnit#setExecutionResultDisplayTime()
     */
    @Override
    public void setExecutionResultDisplayTime() {
        // 실행결과 표시시점 '마지막' 으로 설정
        this.executionResultDisplayTime = UICoreConstant.EXECUTION_RESULT_DISPLAY_TIME_TYPE_LAST;
    }
}
