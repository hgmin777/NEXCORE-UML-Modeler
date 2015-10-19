/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.search.match.UMLModelElement;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search</li>
 * <li>설  명 : Activator</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    /**
     * PLUGIN_ID
     */
    public static final String PLUGIN_ID = "nexcore.tool.uml.ui.search";

    // The shared instance
    /**
     * plugin
     */
    private static Activator plugin;

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
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
     * )
     */
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
     * openEditor
     *  
     * @param dataModelElement void
     */
    public void openEditor(Object dataModelElement) {
        Object object = null;
        if (dataModelElement instanceof UMLModelElement) {
            object = ((UMLModelElement) dataModelElement).getOrgObject();
        }
        if (object == null) {
            return;
        }
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        openDiagramEditor((UMLModelElement) dataModelElement, page);
    }

    /**
     * openDiagramEditor
     *  
     * @param dataModelElement
     * @param page void
     */
    private void openDiagramEditor(UMLModelElement dataModelElement, IWorkbenchPage page) {

    }

    /**
     * getImage
     *  
     * @param elementType
     * @return Image
     */
    public Image getImage(String elementType) {
        Image image = null;
        String imgPath = UICoreConstant.PROJECT_CONSTANTS__ICONS + elementType + UICoreConstant.PROJECT_CONSTANTS__GIF;

        if (getImageRegistry().get(imgPath) == null) {
            ImageDescriptor imageDescriptor = imageDescriptorFromPlugin(Activator.PLUGIN_ID, imgPath);
            if (imageDescriptor == null) {
                return null;
            }
            image = imageDescriptor.createImage();
            getImageRegistry().put(imgPath, image);
        } else {
            image = getImageRegistry().get(imgPath);
        }

        return image;
    }

}
