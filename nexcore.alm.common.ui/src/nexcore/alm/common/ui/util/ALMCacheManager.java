/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.ui.util;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import nexcore.alm.common.ui.Activator;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.util</li>
 * <li>설  명 : ALMCacheManager</li>
 * <li>작성일 : 2012. 3. 16.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ALMCacheManager extends CacheManager {
    private static ALMCacheManager instance;
    
    private ALMCacheManager(URL resource) {
        super(resource);
    }

    public static ALMCacheManager getInstance() throws CacheException {
        if( instance == null ) {
            Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
            URL resource = bundle.getResource("config/ehcache.xml");
            instance = new ALMCacheManager(resource);
        }
        return instance;
    }
    
    public static ALMCacheManager getInstance(URL resource) throws CacheException {
        if( instance == null ) {
            instance = new ALMCacheManager(resource);
        }
        return instance;
    }

    public void shutdown() {
        String[] cacheNames = getCacheNames();
        for (String cacheName : cacheNames) {
            Cache cache = getCache(cacheName);
            cache.removeAll();
        }
    }

    public Object get(String cacheName, Object key) {
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

    public void put(String cacheName, Object key, Object value) {
        Cache cache = getCache(cacheName);
        if (cache == null) {
            return;
        }
        if (useCache()) {
            cache.put(new Element(key, value));
        }
    }

    public void remove(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        cache.remove(key);
    }
    
    public static String genKey(String cacheName, String resourceLocation, String condition,
                                String from, String to) {
        String format = String.format("%s_%s_%s_%s_%s", cacheName, resourceLocation, condition, from, to);
        return format;
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
