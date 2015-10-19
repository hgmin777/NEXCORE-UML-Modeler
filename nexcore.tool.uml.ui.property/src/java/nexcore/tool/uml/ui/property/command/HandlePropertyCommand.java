/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.command;

import nexcore.tool.uml.ui.core.util.ProfileUtil;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.command</li>
 * <li>설  명 : HandlePropertyCommand</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandlePropertyCommand extends RecordingCommand {

    /** 모델 요소 */
    private NamedElement element;

    /** 프로퍼티 */
    private Property property;

    /** 프로퍼티 값 인덱스 */
    private int currentIndex = -1;

    /** 프로퍼티 값 */
    private Object propertyValue;

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param property
     * @param propertyValue
     */
    public HandlePropertyCommand(TransactionalEditingDomain domain, NamedElement element, Property property,
    Object propertyValue) {
        super(domain);

        this.element = element;
        this.property = property;
        this.propertyValue = propertyValue;
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param property
     * @param propertyValue
     */
    public HandlePropertyCommand(TransactionalEditingDomain domain, NamedElement element, Property property,
    int currentIndex, Object propertyValue) {
        super(domain);

        this.element = element;
        this.property = property;
        this.currentIndex = currentIndex;
        this.propertyValue = propertyValue;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (currentIndex != -1) {
            ProfileUtil.setValueOfProperty(element, property, currentIndex, propertyValue);
        } else {
            ProfileUtil.setValueOfProperty(element, property, propertyValue);
        }
    }

}
