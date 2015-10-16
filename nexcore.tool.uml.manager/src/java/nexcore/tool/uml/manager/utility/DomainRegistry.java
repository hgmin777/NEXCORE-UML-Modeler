/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.utility;

import java.util.HashMap;
import java.util.Map;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설 명 : DomainRegistry</li>
 * <li>작성일 : 2010. 11. 26.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainRegistry {

    /** UMLDomain 인스턴스 */
//    private static IDomainModelHandler umlDomain;

    /**
     * UMLDomain 설정
     * 
     * @param domain
     *            void
     */
    public static void setUMLDomain(IDomainModelHandler domain) {
        DomainModelHandlerUtil.setUMLDomain(domain);
    }

    /**
     * UMLDomain 반환
     * 
     * @return UMLDomain
     */
    public static IDomainModelHandler getUMLDomain() {
        return DomainModelHandlerUtil.getUMLDomain();
    }

    /**
     * TransactionalEditingDomain 반환
     * 
     * @return TransactionalEditingDomain
     */
    public static TransactionalEditingDomain getEditingDomain() {
        return DomainModelHandlerUtil.getEditingDomain();
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.manager_v.1.0</li>
     * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
     * <li>설 명 : 리소스 변경을 체크하기 위한 Registry</li>
     * <li>작성일 : 2010. 6. 10.</li>
     * <li>작성자 : 황선림</li>
     * </ul>
     */
    public static class ResourceStampRegistry {

        /**
         * map
         */
        private static Map<String, Long> map = new HashMap<String, Long>();

        public static long getResourceStamp(String id) {
            if (map.containsKey(id)) {
                return map.get(id);
            } else {
                return 0;
            }
        }

        public static void removeAllResourceStamp() {
            map.clear();
        }

        public static void removeResourceStamp(String id) {
            map.remove(id);
        }

        public static void setResourceStamp(String id, long stamp) {
            map.put(id, stamp);
        }

    }
}
