/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.project.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설  명 : UMLCommonCommand</li>
 * <li>작성일 : 2011. 6. 7.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class UMLCommonCommand extends RecordingCommand {

    /** 선택한 요소 목록 */
    protected List<Element> selectedElementList;

    /** 부모 쉘 */
    protected Shell parentShell;

    /** 진행 모니터 */
    protected IProgressMonitor monitor;

    /** UML Command 실행단위 레지스트리 */
    private UMLCommandExecutionUnitRegistry registry;
    /** Command 실행단위 스택 */
    protected Stack<UMLCommandExecutionUnit> executionUnitStack;
    /** 실행단위 Id */
    protected String executionUnitId;

    /** 공유객체 */
    protected Object sharingObject;
    /** 실행결과 목록 */
    protected List<IStatus> executionResultList;
    /** 업무명 */
    protected String taskName;

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param taskName 
     * @param monitor
     */
    public UMLCommonCommand(TransactionalEditingDomain domain, List<Element> selectedElementList, Shell parentShell, String taskName, IProgressMonitor monitor) {
        super(domain);

        this.selectedElementList = selectedElementList;

        this.parentShell = parentShell;
        this.taskName = taskName;
        this.monitor = monitor;

        initialize();
    }

    /**
     * Command 실행환경 초기화
     * 
     *   void
     */
    private void initialize() {
        // 실행단위 Id 설정
        setExecutionUnitId();

        // Command 실행단위 레지스트리 설정
        registry = UMLCommandExecutionUnitRegistry.getInstance();

        // 실행단위 스택 얻기
        if(registry != null && executionUnitId != null) {
            executionUnitStack = registry.getExecutionUnitStack(executionUnitId);
        }
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        UMLCommandExecutionUnit executionUnit = null;

        while(executionUnitStack.empty() == false) {
            executionUnit = executionUnitStack.pop();

            if (executionUnit != null && executionUnit instanceof UMLCommandExecutionUnit){
                // 이전 실행 단위에서 보관한 공유 객체를 현재 실행 단위에 전달
                if(sharingObject != null) {
                    executionUnit.deliverSharingObject(sharingObject);
                    
                    // 전달한 후 보관 공유 객체 
                    sharingObject = null;
                }

                if(selectedElementList != null && parentShell != null && monitor != null) {
                    executionUnit.setSelectedElementList(selectedElementList);

                    executionUnit.setShell(parentShell);
                    executionUnit.setMonitor(monitor);

                    executionUnit.initialize();
                }
            }

            executionUnit.execute();

            // 공유객체가 있을 경우 가져와서 보관
            if(executionUnit.getSharingObject() != null) {
                this.sharingObject = executionUnit.getSharingObject();
            }

            // 실행결과 표시시점이 '즉시(immediately)' 인 경우
            // 메시지창을 띄워 바로 표시
            if(executionUnit.getExecutionResultDisplayTime().equals(UICoreConstant.EXECUTION_RESULT_DISPLAY_TIME_TYPE_IMMEDIATELY)) {
                if(taskName != null && executionUnit.getExecutionResult() != null) {
                    MessageDialog.openInformation(parentShell, taskName, executionUnit.getExecutionResult().getMessage());
                }
            // 실행결과 표시시점이 '마지막(last)' 인 경우
            // 실행결과목록(executionResultList)에 추가
            } else if(executionUnit.getExecutionResultDisplayTime().equals(UICoreConstant.EXECUTION_RESULT_DISPLAY_TIME_TYPE_LAST)) {
                if(executionResultList == null) {
                    executionResultList = new ArrayList<IStatus>();
                }

                if(executionUnit.getExecutionResult() != null) {
                    executionResultList.add(executionUnit.getExecutionResult());
                }
            }
        }

        // 실행결과 표시시점이 '마지막(last)' 인 경우
        // 실행결과목록(executionResultList)에 추가된 결과를 병합하여 
        // 실행 단위가 모두 실행된 후에 메시지창을 띄워 표시
        if(executionResultList != null && executionResultList.size() > 0) {
            MessageDialog.openInformation(parentShell, taskName, mergeResultMessage(executionResultList));
        }
    }

    /**
     * 실행결과목록(executionResultList)을 병합하여 하나의 메시지로 생성 
     *  
     * @param executionResultList
     * @return String
     */
    private String mergeResultMessage(List<IStatus> executionResultList) {
        StringBuffer resultMessage = new StringBuffer();

        for(IStatus status : executionResultList) {
            resultMessage.append(status.getMessage()).append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
        }

        return resultMessage.toString();
    }

    /**
     * 실행단위 Id 설정(추상메소드)
     * 
     *   void
     */
    public abstract void setExecutionUnitId();
}
