/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.registry;

import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : ConstantImageRegistryImpl</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ConstantImageRegistryImpl implements IConstantImageRegistry {

    /**
     * eInstance
     */
    private static IConstantImageRegistry eInstance = null;

    /**
     * Imageregistry Singleton처리
     */
    protected ConstantImageRegistryImpl() {
    }

    /**
     * getInstance
     *  
     * @return IConstantImageRegistry
     */
    public static IConstantImageRegistry getInstance() {
        if (null == eInstance) {
            imageRegistry = UiCorePlugin.getDefault().getImageRegistry();
            eInstance = new ConstantImageRegistryImpl();
        }
        return eInstance;
    }

    /**
     * imageRegistry
     */
    protected static ImageRegistry imageRegistry;// =

    // UiCorePlugin.getDefault().getImageRegistry();

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getLargeIcon(java.lang.String)
     */
    public Image getLargeIcon(String imageKey) {
        return imageRegistry.get(IConstantImageRegistry.eInstance.getLargeIconKey(imageKey));
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getNomralIcon(java.lang.String)
     */
    public Image getNomralIcon(String imageKey) {
        return imageRegistry.get(IConstantImageRegistry.eInstance.getNormalIconKey(imageKey));
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getSmallIcon(java.lang.String)
     */
    public Image getSmallIcon(String imageKey) {
        return imageRegistry.get(IConstantImageRegistry.eInstance.getSmallIconKey(imageKey));
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getLocationForNormal(java.lang.String)
     */
    public String getLocationForNormal(String id) {
        return LOCATION_SAMLLICON_PATH + id + ICON_EXTENTION_NAME;
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getLocationForSmall(java.lang.String)
     */
    public String getLocationForSmall(String id) {
        return LOCATION_NORMALICON_PATH + id + ICON_EXTENTION_NAME;
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getLocationForLarge(java.lang.String)
     */
    public String getLocationForLarge(String id) {
        return LOCATION_LARGEICON_PATH + id + ICON_EXTENTION_NAME;
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getLargeIconKey(java.lang.String)
     */
    public String getLargeIconKey(String id) {
        return id + ICON_LARGE;
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getNormalIconKey(java.lang.String)
     */
    public String getNormalIconKey(String id) {
        return id + ICON_NORMAL;
    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IConstantImageRegistry#getSmallIconKey(java.lang.String)
     */
    public String getSmallIconKey(String id) {
        return id + ICON_SMALL;
    }
}
