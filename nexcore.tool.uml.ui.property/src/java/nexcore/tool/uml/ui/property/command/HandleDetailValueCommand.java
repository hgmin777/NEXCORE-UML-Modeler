/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.command;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.command</li>
 * <li>설  명 : HandleDetailValueCommand</li>
 * <li>작성일 : 2010. 1. 12.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleDetailValueCommand extends RecordingCommand {

    /** 프로퍼티 */
    private DynamicEObjectImpl parentObject;

    /** 선택된 세부 프로퍼티 */
    private EStructuralFeature selectedObject;

    /** 입력된 값 */
    private Object inputValue;

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedObject
     * @param inputValue
     */
    public HandleDetailValueCommand(TransactionalEditingDomain domain, EStructuralFeature selectedObject,
    Object inputValue) {
        super(domain);

        this.selectedObject = selectedObject;
        this.inputValue = inputValue;
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param parentObject
     * @param selectedObject
     * @param inputValue
     */
    public HandleDetailValueCommand(TransactionalEditingDomain domain, DynamicEObjectImpl parentObject,
    EStructuralFeature selectedObject, Object inputValue) {
        super(domain);

        this.parentObject = parentObject;
        this.selectedObject = selectedObject;
        this.inputValue = inputValue;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        parentObject.eSet(selectedObject, inputValue);
    }

}
