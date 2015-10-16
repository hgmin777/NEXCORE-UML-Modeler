/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.project;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설 명 : ClosedTreeNode</li>
 * <li>작성일 : 2012. 7. 4.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ClosedTreeNode implements IUMLTreeNode {

    /**
     * file
     */
    private IFile file;

    /**
     * ClosedTreeNode
     * 
     * @param file
     */
    public ClosedTreeNode(IFile file) {
        this.file = file;
    }

    /**
     * getResource
     *  
     * @return IResource
     */
    public IResource getResource() {
        return file;
    }

    /**
     * getURI
     *  
     * @return URI
     */
    public URI getURI() {
        if(file == null || !file.exists()) {
            return null;
        }
        return URI.createURI(file.getFullPath().toString());
    }
}
