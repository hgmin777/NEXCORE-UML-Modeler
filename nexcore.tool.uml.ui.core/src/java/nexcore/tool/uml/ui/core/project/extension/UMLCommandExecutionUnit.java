/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.project.extension;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.extension</li>
 * <li>설  명 : UMLCommandExecutionUnit</li>
 * <li>작성일 : 2011. 6. 10.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class UMLCommandExecutionUnit {

    /** 트랜잭셔널 에디팅 도메인 */
    // private TransactionalEditingDomain domain;

    /** 선택한 요소 목록 */
    protected List<Element> selectedElementList;

    /** 부모 쉘 */
    protected Shell parentShell;

    /** 진행 모니터 */
    protected IProgressMonitor monitor;
    
    /** 실행결과 표시시점 */
    protected String executionResultDisplayTime;
    /** 실행결과 상태 */
    protected IStatus executionResult;
    
    /** 전달된 공유 객체 */
    protected Object deliveredObject;

    /**
     * 트랜잭셔널 에디팅 도메인 설정
     *  
     * @param domain void
     */
    /*
    public void setDomain(TransactionalEditingDomain domain) {
        this.domain = domain;
    }
     */

    /**
     * 선택한 요소 목록 설정
     *  
     * @param selectedElementList void
     */
    public void setSelectedElementList(List<Element> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    /**
     * 부모 쉘 설정
     *  
     * @param parentShell void
     */
    public void setShell(Shell parentShell) {
        this.parentShell = parentShell;
    }

    /**
     * 진행 모니터 설정
     *  
     * @param monitor void
     */
    public void setMonitor(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * 초기화
     * 
     *   void
     */
    public abstract void initialize();

    /**
     * 실행단위 실행
     * 
     *   void
     */
    public abstract void execute();

    /**
     * 공유객체 반환(추상 메소드)
     *  
     * @return Object
     */
    public abstract Object getSharingObject();

    /**
     * 실행결과 반환
     *  
     * @return IStatus
     */
    public IStatus getExecutionResult() {
        return executionResult;
    }

    /**
     * 실행결과 표시시점(immediately, last) 반환
     * 
     * @return the executionResultDisplayTime
     */
    public String getExecutionResultDisplayTime() {
        return executionResultDisplayTime;
    }

    /**
     * 실행결과 표시시점(immediately, last) 설정(추상메소드)
     * 
     *   void
     */
    public abstract void setExecutionResultDisplayTime();

    /**
     * 공유 객체를 전달 객체로 설정
     *  
     * @param sharingObject void
     */
    public void deliverSharingObject(Object sharingObject) {
        this.deliveredObject = sharingObject;
    }
}
