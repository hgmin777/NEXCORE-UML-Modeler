/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.common;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mda.core</li>
 * <li>서브 업무명 : nexcore.tool.mda.core.infrastructure.model.common</li>
 * <li>설 명 : IRuleSet</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface IRuleSet {

    /**
     * 검증 여부 설정
     * 
     * @param validated
     *            void
     */
    public void setValidated(boolean validated);

    /**
     * 검증 여부 반환
     * 
     * @return boolean
     */
    public boolean isValidated();

}
