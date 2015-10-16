/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.connector;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.connector</li>
 * <li>서브 업무명 : nexcore.tool.uml.connector</li>
 * <li>설 명 : UMLCacheManager</li>
 * <li>작성일 : 2011. 10. 31.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLCacheManager extends CacheManager {
    /**
     * instance
     */
    private static UMLCacheManager instance;
    
    /**
     * RESOURCE_URI_CACHE
     */
    public static final String RESOURCE_URI_CACHE = "nexcore.tool.uml.uri";
    
    /**
     * UMLCacheManager
     * @param resource
     */
    private UMLCacheManager(URL resource) {
        super(resource);
    }

    /**
     * getInstance
     *  
     * @return
     * @throws CacheException UMLCacheManager
     */
    public static UMLCacheManager getInstance() throws CacheException {
        if( instance == null ) {
            Bundle bundle = Platform.getBundle(UMLConnectorActivator.PLUGIN_ID);
            URL resource = bundle.getResource("config/ehcache.xml");
            instance = new UMLCacheManager(resource);
        }
        return instance;
    }

    /**
     * @see net.sf.ehcache.CacheManager#shutdown()
     */
    public void shutdown() {
        String[] cacheNames = getCacheNames();
        for (String cacheName : cacheNames) {
            Cache cache = getCache(cacheName);
            cache.removeAll();
        }
    }

    /**
     * get
     *  
     * @param cacheName
     * @param key
     * @return Object
     */
    public Object get(String cacheName, Object key) {
        if(!useCache()) {
            return null;
        }
        Cache cache = getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Element element = cache.get(key);
        if (element == null) {
            return null;
        }
        return element.getObjectValue();
    }
    
    /**
     * 
     *   void
     */
    public void refresh() {
        String[] cacheNames = getCacheNames();
        for (String cacheName : cacheNames) {
            Cache cache = getCache(cacheName);
            cache.removeAll();
        }
    }

    /**
     * put
     *  
     * @param cacheName
     * @param key
     * @param value void
     */
    public void put(String cacheName, Object key, Object value) {
        if(!useCache()) {
            return;
        }
        Cache cache = getCache(cacheName);
        if (cache == null) {
            return;
        }
        cache.put(new Element(key, value));
    }

    /**
     * remove
     *  
     * @param cacheName
     * @param key void
     */
    public void remove(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        cache.remove(key);
    }
    
    /**
     * removeAll
     *  
     * @param cacheName void
     */
    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        cache.removeAll();
    }
    
    /**
     * 
     * 
     *  
     * @return boolean
     */
    public static boolean useCache() {
        // VM arg
        // default = true
        // use.cache=true/false
        String useCache = System.getProperty("use.cache");

        if (useCache == null) {
            return true;
        } else {
            if ("true".equalsIgnoreCase(useCache) || "false".equalsIgnoreCase(useCache)) {
                return Boolean.parseBoolean(useCache);
            } else {
                return true;
            }
        }
    }
}
