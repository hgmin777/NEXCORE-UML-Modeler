/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.connector;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.connector</li>
 * <li>서브 업무명 : nexcore.tool.uml.connector</li>
 * <li>설  명 : UMLConnectorActivator</li>
 * <li>작성일 : 2011. 7. 21.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLConnectorActivator implements BundleActivator {
    
    /**
     * PLUGIN_ID
     */
    public static final String PLUGIN_ID = "nexcore.tool.uml.connector";

    /**
     * context
     */
    private static BundleContext context;

    /**
     * plugin
     */
    private static UMLConnectorActivator plugin;

    /**
     * getContext
     *  
     * @return BundleContext
     */
    static BundleContext getContext() {
        return context;
    }

    /**
     * getDefault
     *  
     * @return UMLConnectorActivator
     */
    public static UMLConnectorActivator getDefault() {
        return plugin;
    }

//    /**
//     * serviceManager
//     */
//    private ServiceRegister serviceManager = null;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        plugin = this;
//        if (ALMPreference.useCollaboration()) {
//            NCPSession.getInstance();
//            UMLConnectorActivator.context = bundleContext;
//            startNCPBundle();
//            serviceManager = new ServiceRegister();
//
//            ProductShortcutRegister.hereIam("NEXCORE UML Modeler", "2.0.0");
//        }

    }

    /**
     * startNCPBundle
     *   void
     */
    private void startNCPBundle() {
        Bundle[] bundles = getContext().getBundles();
        
        List<String> bundleList = new ArrayList<String>();
        bundleList.add("cxf-dosgi-ri-singlebundle-distribution");
        bundleList.add("nexcore.platform.foundation.configuration.provider");
        bundleList.add("nexcore.platform.foundation.configuration.ui");
        bundleList.add("nexcore.platform.foundation.core.osgi");
        bundleList.add("nexcore.platform.foundation.core.system");
        bundleList.add("nexcore.platform.foundation.core.ui");
        bundleList.add("nexcore.platform.foundation.integration.ui");
        for (Bundle bundle : bundles) {
            String symbolicName = bundle.getSymbolicName();
            if (symbolicName.startsWith("nexcore.platf") || symbolicName.startsWith("cxf-dosgi-ri")) {
                if (bundleList.contains(symbolicName)) {
                    if (bundle.getState() != Bundle.ACTIVE) {
                        try {
                            bundle.start();
                        } catch (BundleException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        UMLConnectorActivator.context = null;
    }

//    /**
//     * getServiceManager
//     *  
//     * @return ServiceRegister
//     */
//    public ServiceRegister getServiceManager() {
//        return serviceManager;
//    }
}
