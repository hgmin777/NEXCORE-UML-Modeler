/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.registry;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : URIRegistry</li>
 * <li>작성일 : 2011. 4. 7.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
@Deprecated
public class URIRegistry {
    /**
     * uriMap
     */
    @Deprecated
    public static Map<String, URI> uriMap = new HashMap<String, URI>();

    /**
     * 
     * 
     * 
     * @param pathName
     *            file.getFullPath().toString()
     * @return URI
     */
    @Deprecated
    public static URI getURI(String pathName) {
        if (!uriMap.containsKey(pathName)) {
            URI uri = URI.createURI(pathName);
            uriMap.put(pathName, uri);
        }

        return uriMap.get(pathName);
    }

    /**
     * removeResourceURI
     *  
     * @param pathName void
     */
    @Deprecated
    public static void removeResourceURI(String pathName) {
        if (uriMap.containsKey(pathName)) {
            uriMap.remove(pathName);
        }
    }

    /**
     * removeAll
     *   void
     */
    @Deprecated
    public static void removeAll() {
        uriMap.clear();
    }
}
