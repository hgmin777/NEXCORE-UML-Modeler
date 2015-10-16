/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.command;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command</li>
 * <li>설 명 : UMLExplorerExtendedCommand</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class UMLExplorerExtendedCommand extends RecordingCommand {

    /** 선택한 요소 목록 */
    protected List<Element> selectedElementList;

    /** 부모 쉘 */
    protected Shell parentShell;

    /** 진행 모니터 */
    protected IProgressMonitor monitor;

    /**
     * 생성자
     * 
     * @param domain
     */
    public UMLExplorerExtendedCommand(TransactionalEditingDomain domain) {
        super(domain);
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param monitor
     */
    public UMLExplorerExtendedCommand(TransactionalEditingDomain domain, List<Element> selectedElementList,
    Shell parentShell, IProgressMonitor monitor) {
        this(domain);

        this.selectedElementList = selectedElementList;

        this.parentShell = parentShell;
        this.monitor = monitor;

        initialize();
    }

    /**
     * 커맨드 생성 시점에 초기화 작업을 수행하는 추상 메소드
     * 
     * void
     */
    public abstract void initialize();

}
