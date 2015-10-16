
/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "nexcore.alm.common.ui"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    private BundleContext context;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
     * )
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        this.context = context;
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
     * )
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * 주어진 경로의 이미지를 반환 (이미지를 레지스트리로 관리하기 위함)
     * 
     * 
     * @param imgPath
     *            이미지 경로 (ImageConstant 사용)
     * @return Image
     */
    public Image getImage(String imgPath) {
        Image image = getImageRegistry().get(imgPath);
        if (image == null) {
            ImageDescriptor imageDescriptor = getImageDescriptor(imgPath);
            image = imageDescriptor.createImage();
            getImageRegistry().put(imgPath, image);
        }
        return image;
    }

    /**
     * 주어진 경로의 ImageDescriptor를 반환
     * 
     * 
     * @param imgPath
     *            이미지 경로
     * @return ImageDescriptor
     */
    public ImageDescriptor getImageDescriptor(String imgPath) {
        return imageDescriptorFromPlugin(PLUGIN_ID, imgPath);
    }

    private ServiceTracker projectTracker;

    private ServiceTracker contentTracker;

    private ServiceTracker metaRelationDefTracker;

    private ServiceTracker relationTracker;

    private ServiceTracker productDefinitionTracker;

    public synchronized ServiceTracker createServiceTracker(final Class<?> c) {
        ServiceTracker serviceTracker = new ServiceTracker(context, c.getName(), null);
        serviceTracker.open();
        long s = System.currentTimeMillis();
        while (serviceTracker.getService() == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}

            if (System.currentTimeMillis() - s > 5000) {
                if (serviceTracker.getService() == null) {
                    System.err.println(c.getName() + " 서비스를 사용 할 수 없습니다.");
                }
                break;
            }
        }

        return serviceTracker;
    }
}
