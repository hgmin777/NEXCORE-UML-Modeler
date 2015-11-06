/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mdd.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설 명 : DomainUtil New</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainUtil {

    /** 룩업 테이블 */
    @SuppressWarnings("unchecked")
    private static List lookupTable = new ArrayList();

    /** 파서 풀 객체 */
    private static XMLParserPool parserPool = new XMLParserPoolImpl();

    /** 이름 대 피쳐 맵 */
    private static Map<String, Object> nameToFeatureMap = new HashMap<String, Object>();

    /**
     * 적재시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getLoadOptions() {
        Map<Object, Object> loadOptions = new HashMap<Object, Object>();

        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);

        return loadOptions;
    }

    /**
     * 저장시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOptions = new HashMap<Object, Object>();

        saveOptions.put(XMIResource.OPTION_ENCODING, UMLResource.DEFAULT_ENCODING);
        saveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, true);
        saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
        saveOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, new ArrayList());
        saveOptions.put(XMLResource.OPTION_SAVE_ONLY_IF_CHANGED, true);

        return saveOptions;
    }

}
