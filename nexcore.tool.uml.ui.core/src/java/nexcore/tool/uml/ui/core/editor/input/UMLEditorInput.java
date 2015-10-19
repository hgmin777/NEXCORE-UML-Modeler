/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.editor.input;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.input</li>
 * <li>설  명 : UMLEditorInput</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLEditorInput implements IEditorInput {

    /** UML 요소 */
    private Element element;

    /**
     * 생성자
     * 
     * @param element
     */
    public UMLEditorInput(Element element) {
        super();

        this.setElement(element);
    }

    /**
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists() {
        return false;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName() {
        if (element != null) {
            return element.getModel().getName();
        }

        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText() {
        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public Object getAdapter(Class adapter) {
        return null;
    }

    /**
     * 인자로 넘어온 EditorInput을 다른 EditorInput과 비교
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof UMLEditorInput) {
            if (this.element != null && this.element.equals(((UMLEditorInput) object).getElement())) {
                return true;
            }
        }

        return result;
    }

    /**
     * @param element
     *            the element to set
     */
    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * @return the element
     */
    public Element getElement() {
        return element;
    }

}
