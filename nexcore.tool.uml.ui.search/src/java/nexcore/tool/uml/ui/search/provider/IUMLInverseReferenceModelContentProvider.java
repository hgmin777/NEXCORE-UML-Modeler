/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.provider;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.provider</li>
 * <li>설 명 : IUMLInverseReferenceModelContentProvider</li>
 * <li>작성일 : 2012. 8. 23.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public interface IUMLInverseReferenceModelContentProvider {
    /**
     * elementsChanged
     * 
     * @param updatedElements
     *            void
     */
    public abstract void elementsChanged(Object[] updatedElements);

    /**
     * clear void
     */
    public abstract void clear();
}
