/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.project;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.uml2.uml.PackageImport;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : UMLTreeNode</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@SuppressWarnings("unchecked")
public class UMLTreeNode extends UMLAbstractTreeNode implements ITreeNode, IUMLTreeNode, ITabbedPropertySheetPageContributor {

    /** CONTRIBUTOR_ID */
    private static final String CONTRIBUTOR_ID = UICoreConstant.PROJECT_CONSTANTS__PROPERTY_CONTRIBUTOR_ID;

    /** parent object of this node. */
    private Object parent;

    /** EObject of this node. */
    private EObject eobj;

    /** IPropertySourceProvider */
    private IPropertySourceProvider propertySourceProvider;

    static {
        final Class[] supportedTypes = new Class[] { EObject.class, IPropertySource.class };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof UMLTreeNode) {
                    UMLTreeNode node = (UMLTreeNode) adaptableObject;
                    EObject eObject = node.getEObject();
                    if (adapterType == EObject.class)
                        return eObject;

                    if (adapterType == IPropertySource.class)
                        return node.getPropertySourceProvider().getPropertySource(eObject);
                }

                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, UMLTreeNode.class);
    }

    /**
     * @param eobject
     * @param parentObj
     * @param propSourceProvider
     */
    public UMLTreeNode(EObject eobject, Object parentObj, IPropertySourceProvider propSourceProvider) {
        super(parentObj);
        eobj = eobject;
        parent = parentObj;
        propertySourceProvider = propSourceProvider;
        
        String fragment = EcoreUtil.getURI(eobj).fragment();
//        Object object = UMLCacheManager.getInstance().get(UMLCacheManager.RESOURCE_URI_CACHE, fragment);
//        
//        if (object == null){ 
//            String uriStr = EcoreUtil.getURI(eobj).toString();
//            UMLCacheManager.getInstance().put(UMLCacheManager.RESOURCE_URI_CACHE, fragment, uriStr);
//        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // StringBuffer sb = new StringBuffer();
        // sb.append("eobj : ").append(eobj).append("\r\n");
        // sb.append("parent : ").append(((ITreeNode)parent).getEObject()).append("\r\n");
        // sb.append("propertySourceProvider : ").append(propertySourceProvider).append("\r\n");
        // System.out.println(sb.toString());

        return super.toString();
    }

    /**
     * 
     * 
     * @return Object
     */
    public Object getParent() {
        return parent;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#getParentNode()
     */
    public Object getParentNode() {
        return parent;
    }

    /**
     * @return
     */
    public IPropertySourceProvider getPropertySourceProvider() {
        return propertySourceProvider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see nexcore.tool.uml.ui.project.explorer.node.ITreeNode#getEObject()
     */
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#getEObject()
     */
    public EObject getEObject() {
        return eobj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.project.explorer.node.ITreeNode#setEObject(org.eclipse
     * .emf.ecore.EObject)
     */
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(EObject eoj) {
        eobj = eoj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor
     * #getContributorId()
     */
    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
     */
    public String getContributorId() {
        if (eobj instanceof PackageImport) {
            return UICoreConstant.PROJECT_CONSTANTS__CONTRIBUTOR_ID;
        } else {
            return CONTRIBUTOR_ID;
        }
    }

    /**
     * @see org.eclipse.core.runtime.PlatformObject#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class) {
            return new UMLTreeActionFilter();
        }
        return super.getAdapter(adapter);
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#refresh()
     */
    public void refresh() {
        URI uri = EcoreUtil.getURI(eobj);
        if (!(uri.toString().contains(UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION) || uri.toString()
            .contains(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION))) {
            return;
        }
        try {
            eobj = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
            if (eobj == null) {
                Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);

                if (resource != null && resource.getContents().size() > 0) {
                    eobj = resource.getContents().get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            dispose();
        }
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#dispose()
     */
    public void dispose() {
        UMLTreeNodeRegistry.removeTreeNode(eobj);
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isValid()
     */
    public boolean isValid() {
        return true;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isReferenceURIChanged()
     */
    @Override
    public boolean isReferenceURIChanged() {
        String fragment = EcoreUtil.getURI(eobj).fragment();
        String uriStr = EcoreUtil.getURI(eobj).toString();

//        Object object = UMLCacheManager.getInstance().get(UMLCacheManager.RESOURCE_URI_CACHE, fragment);
//
//        if (!uriStr.equals(object)) {
//            UMLCacheManager.getInstance().put(UMLCacheManager.RESOURCE_URI_CACHE, fragment, uriStr);
//            return true;
//        }
        return false;
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isActive()
     */
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public IResource getResource() {
        // TODO Auto-generated method stub
        return null;
    }
}
