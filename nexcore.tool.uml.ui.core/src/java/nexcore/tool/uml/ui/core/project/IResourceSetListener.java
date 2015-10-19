/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.project;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : IResourceSetListener</li>
 * <li>작성일 : 2010. 6. 4.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public interface IResourceSetListener {

    /**
     * startResourceListening
     *   void
     */
    public void startResourceListening();

    /**
     * stopResourceListening
     *   void
     */
    public void stopResourceListening();

}
