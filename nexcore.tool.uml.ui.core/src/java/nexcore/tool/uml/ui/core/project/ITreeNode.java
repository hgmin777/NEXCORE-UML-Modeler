/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.project;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : ITreeNode</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public interface ITreeNode extends IAdaptable {

    /**
     * getEObject
     *  
     * @return EObject
     */
    public EObject getEObject();

    /**
     * getResource
     *  
     * @return IResource
     */
    public IResource getResource();
    
    /**
     * setEObject
     *  
     * @param eoj void
     */
    public void setEObject(EObject eoj);

    /**
     * getParentNode
     *  
     * @return Object
     */
    public Object getParentNode();
    
    /**
     * refresh
     *   void
     */
    public void refresh();
    
    /**
     * isValid
     *  
     * @return boolean
     */
    public boolean isValid();
    
    /**
     * isActive
     *  
     * @return boolean
     */
    public boolean isActive();
    
    /**
     * dispose
     *   void
     */
    public void dispose();
    
    /**
     * isReferenceURIChanged
     *  
     * @return boolean
     */
    public boolean isReferenceURIChanged();
}
