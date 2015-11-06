/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : AbstractUMLExplorerExtendedAction</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class AbstractUMLExplorerExtendedAction extends Action {

    /** 트랜잭셔널 에디팅 도메인 */
    private TransactionalEditingDomain transactionalEditingDomain = DomainRegistry.getEditingDomain();

    /** 액션 라벨 */
    private String label;

    /** 선택한 요소 목록 */
    private List<Element> selectedElementList;

    /** 부모 쉘 */
    private Shell parentShell;

    /** 작업공간 변경 처리 */
    private UMLWorkspaceModifyOperation workspaceModifyOperation;

    /** 컴파운드 커맨드 */
    private CompoundCommand command;

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        // 진행 모니터 다이얼로그 생성
        ProgressMonitorDialog dialog = new ProgressMonitorDialog(parentShell);

        // 작업공간 변경 처리 생성
        workspaceModifyOperation = new UMLWorkspaceModifyOperation();

        try {
            // 진행 모니터 다이얼로그 실행
            dialog.run(false, true, workspaceModifyOperation);
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
     * <li>설 명 : UMLWorkspaceModifyOperation</li>
     * <li>작성일 : 2010. 11. 16.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class UMLWorkspaceModifyOperation extends WorkspaceModifyOperation {
        /**
         * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
         */
        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
        InterruptedException {
            monitor.beginTask(getTaskName() + UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON,
                getSelectedElementList().size());

            // 컴파운드 커맨드 구성
            command = configureCommand(monitor);
            // 컴파운드 커맨드 실행
            getDomain().getCommandStack().execute(command);

            monitor.done();
        }
    }

    /**
     * 태스크명을 가져오는 추상 메소드
     * 
     * @return String
     */
    public abstract String getTaskName();

    /**
     * 커맨드 구성하는 추상 메소드
     * 
     * @return CompoundCommand
     */
    public abstract CompoundCommand configureCommand(IProgressMonitor monitor);

    /**
     * 트랜잭셔널 에디팅 도메인 반환
     * 
     * @return the transactionalEditingDomain
     */
    protected TransactionalEditingDomain getDomain() {
        return transactionalEditingDomain;
    }

    /**
     * 액션 라벨 설정
     * 
     * @param actionLabel
     *            the actionLabel to set
     */
    protected void setLabel(String label) {
        this.label = label;
    }

    /**
     * 액션 라벨 반환
     * 
     * @return the actionLabel
     */
    protected String getLabel() {
        return label;
    }

    /**
     * 선택된 요소 항목 설정
     * 
     * @param selectedElementList
     *            the selectedElementList to set
     */
    public void setSelectedElementList(List<Element> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    /**
     * 선택된 요소 항목 반환
     * 
     * @return the selectedElementList
     */
    public List<Element> getSelectedElementList() {
        return selectedElementList;
    }

    /**
     * 부모 쉘 설정
     * 
     * @param parentShell
     *            void
     */
    public void setShell(Shell parentShell) {
        this.parentShell = parentShell;
    }

    /**
     * 부모 쉡 반환
     * 
     * @return Shell
     */
    public Shell getShell() {
        return parentShell;
    }

    /**
     * 컴파운드 커맨드 설정
     * 
     * @param command
     *            void
     */
    public void setCommand(CompoundCommand command) {
        this.command = command;
    }

    /**
     * 컴파운드 커맨드 반환
     * 
     * @return CompoundCommand
     */
    public CompoundCommand getCommand() {
        return command;
    }

}
