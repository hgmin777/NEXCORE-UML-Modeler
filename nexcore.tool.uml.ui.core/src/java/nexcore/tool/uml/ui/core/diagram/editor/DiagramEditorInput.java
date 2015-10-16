/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.editor;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor</li>
 * <li>설 명 : DiagramEditorInput</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramEditorInput extends URIEditorInput {

    /**
     * DiagramEditorInput
     * @param uri
     */
    public DiagramEditorInput(URI uri) {
        super(uri);
    }

    /** diagram */
    Diagram diagram;

    /**
     * setDiagram
     *  
     * @param diagram void
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.common.ui.URIEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.common.ui.URIEditorInput#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName() {
        return diagram.getName();
        //return "[" + diagram.getName() + "]" + diagram.getType().getLiteral(); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText() {
        return "[" + diagram.getName() + "]" + diagram.getType().getLiteral(); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * 입력된 EditorInput을 다른 EditorInput과 비교하기 위한 equals함수. True일경우 기존에 열린다이어그램을
     * 활성화한다.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    // @Override
    // public boolean equals(Object object) {
    // boolean result = false;
    // if (object instanceof DiagramEditorInput) {
    // if (this.diagram.getId().equals(((DiagramEditorInput)
    // object).getDiagram().getId())) {
    // return true;
    // }
    // }
    //
    // return result;
    // }
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * @see org.eclipse.emf.common.ui.URIEditorInput#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        Diagram selectedDiagram = ((DiagramEditorInput) o).getDiagram();
        PackageableElement selectedParent = UMLManager.getParent(selectedDiagram);
        PackageableElement parent = UMLManager.getParent(this.diagram);

        if (selectedParent != null && parent != null) {
            if (this.diagram.getId().equals(selectedDiagram.getId()) && parent.equals(selectedParent)) {
                return true;
            }
        }
        return false;
    }

}
