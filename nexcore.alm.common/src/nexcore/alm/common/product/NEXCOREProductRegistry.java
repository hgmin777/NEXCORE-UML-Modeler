/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.alm.common.product;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.product</li>
 * <li>설 명 : NEXCOREProductRegistry</li>
 * <li>작성일 : 2010. 10. 5.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public class NEXCOREProductRegistry {
    /**
     * OSGi 환경에 있는 모든 NEXCORE 제품을 담고 있다.
     */
    private static List<INEXCOREProduct> PRODUCT_LIST = new ArrayList<INEXCOREProduct>();

    /**
     * 제품을 담고 있는 래지스트를 다시 읽어서 초기화 한다.
     * 
     */
    private static void refreshRegistry() {
        PRODUCT_LIST.clear();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor("nexcore.tool.core.product");
        if (config != null) {
            for (IConfigurationElement element : config) {
                if (element != null) {
                    NEXCOREProduct product = new NEXCOREProduct();
                    // 프로덕트 아이디
                    product.setProductId(element.getAttribute("productId"));
                    // 프로덕트 이름
                    product.setName(element.getAttribute("name"));
                    // 프로덕트 버전
                    product.setVersion(element.getAttribute("version"));

                    PRODUCT_LIST.add(product);
                }
            }
        }
    }

    /**
     * 제품 목록을 가져 온다. 이 리스트에는 제품 아이디, 이름, 버전 정보가 담겨 있다.
     * 
     * @return List<INEXCOREProduct> 제품 정보가 담겨 있는 리스트
     */
    public static List<INEXCOREProduct> getList() {
        if (PRODUCT_LIST.size() < 1) {// 래지스트리에 내용이 없다면 다시 읽는다
            refreshRegistry();
        }

        return PRODUCT_LIST;
    }

    /**
     * 특정 아이디의 제품 이름을 가져 온다.
     * 
     * @param id
     *            제품 아이디
     * @return String 제품 이름
     */
    public static String getProductName(String id) {
        if (id == null) {
            return null;
        }
        List<INEXCOREProduct> list = getList();
        for (INEXCOREProduct product : list) {
            if (id.equals(product.getProductId())) {
                return product.getName();
            }
        }
        return null;
    }
}
