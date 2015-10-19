/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.command;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.command</li>
 * <li>설  명 : HandleStereotypeCommand</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleStereotypeCommand extends RecordingCommand {

    /** 모델 요소 */
    private Element element;

    /** 스테레오타입 목록 */
    private List<Stereotype> selectedStereotypeList;

    /** 스테레오타입 열거형 */
    private Iterator<Stereotype> selectedStereotypeIterator;

    /** 스테레오타입 적용 여부 */
    private boolean applyStereotype = false;

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param selectedStereotypeList
     * @param applyStereotype
     */
    public HandleStereotypeCommand(TransactionalEditingDomain domain, Element element,
    List<Stereotype> selectedStereotypeList, boolean applyStereotype) {
        super(domain);

        this.element = element;
        this.selectedStereotypeList = selectedStereotypeList;

        this.applyStereotype = applyStereotype;
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param selectedStereotypeIterator
     * @param applyStereotype
     */
    public HandleStereotypeCommand(TransactionalEditingDomain domain, Element element,
    Iterator<Stereotype> selectedStereotypeIterator, boolean applyStereotype) {
        super(domain);

        this.element = element;
        this.selectedStereotypeIterator = selectedStereotypeIterator;

        this.applyStereotype = applyStereotype;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (applyStereotype) {
            for (Stereotype stereotype : element.getAppliedStereotypes()) {
                element.unapplyStereotype(stereotype);

            }

            for (Stereotype stereotype : selectedStereotypeList) {
                element.applyStereotype(stereotype);
                ((NamedElement) element).setName(((NamedElement) element).getName());
            }
        } else {
            for (Iterator<Stereotype> iterator = selectedStereotypeIterator; iterator.hasNext();) {
                element.unapplyStereotype(iterator.next());
                ((NamedElement) element).setName(((NamedElement) element).getName());
            }
        }
    }

}
