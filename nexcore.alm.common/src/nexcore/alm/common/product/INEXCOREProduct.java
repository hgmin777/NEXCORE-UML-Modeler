/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.alm.common.product;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.product</li>
 * <li>설 명 : INEXCOREProduct</li>
 * <li>작성일 : 2010. 10. 5.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public interface INEXCOREProduct {

    /**
     * @return the productId
     */
    public String getProductId();

    /**
     * @return the name
     */
    public String getName();

    /**
     * @return the version
     */
    public String getVersion();

}
