/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.project.extension;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.extension</li>
 * <li>설  명 : IUMLCommandExecutionUnitWrapper</li>
 * <li>작성일 : 2011. 6. 3.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface IUMLCommandExecutionUnitWrapper {

    /**
     * Id 반환
     *  
     * @return String
     */
    public String getId();

    /**
     * Label 반환
     *  
     * @return String
     */
    public String getLabel();

    /**
     * 우선순위 반환
     *  
     * @return String
     */
    public String getPriority();

    /**
     * Command 실행단위 반환
     *  
     * @return UMLCommandExecutionUnit
     */
    public UMLCommandExecutionUnit getExecutionUnit();

}
