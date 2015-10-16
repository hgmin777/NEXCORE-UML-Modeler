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
 * <li>설 명 : NEXCOREProduct</li>
 * <li>작성일 : 2010. 10. 5.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public class NEXCOREProduct implements INEXCOREProduct {
    private String productId;

    private String name;

    private String version;

    /**
     * @see nexcore.alm.common.product.INEXCOREProduct#getProductId()
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @see nexcore.alm.common.product.INEXCOREProduct#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see nexcore.alm.common.product.INEXCOREProduct#getVersion()
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("Product Id:");
        result.append(this.getProductId());

        result.append(" Product Name:");
        result.append(this.getName());

        result.append(" Product Version:");
        result.append(this.getVersion());
        return result.toString();
    }
}
