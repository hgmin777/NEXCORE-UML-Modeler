/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.project;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.registry.ModelUpdater;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : UMLFileTreeNode</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@SuppressWarnings( { "restriction", "unchecked" })
public class UMLFileTreeNode extends UMLAbstractTreeNode implements ITreeNode, IUMLTreeNode, ITabbedPropertySheetPageContributor {

    /** CONTRIBUTOR_ID */
    private static final String CONTRIBUTOR_ID = UICoreConstant.PROJECT_CONSTANTS__PROPERTY_CONTRIBUTOR_ID;

    /** parent of this node. */
    private Object parent;

    /** EObject of this node. */
    private EObject eobject;

    /** IPropertySourceProvider */
    private IPropertySourceProvider propertySourceProvider;

    /** a flag for a fragmented node. */
    boolean fragment;

    /** File Path */
    private IPath path;

    static {
        final Class[] supportedTypes = new Class[] { EObject.class, IPropertySource.class, IResource.class };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof UMLFileTreeNode) {
                    UMLFileTreeNode node = (UMLFileTreeNode) adaptableObject;
                    EObject eObject = node.getEObject();
                    if (adapterType == EObject.class) {
                        return eObject;
                    } else if (adapterType == IPropertySource.class) {
                        return node.getPropertySourceProvider().getPropertySource(eObject);
                    } else if (adapterType == IResource.class) {
                        return node.getResource();
                    }
                }

                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, UMLFileTreeNode.class);
    }

    /**
     * constructor.
     * 
     * @param workspace
     * @param path
     * @param parentElement
     * @param fragment
     * @param adapterFactoryContentProvider
     */
    public UMLFileTreeNode(IPath pPath, Workspace workspace, EObject obj, Object parentElement, boolean isFragmented,
    AdapterFactoryContentProvider propSourceProvider) {
        // super(path, workspace);
        super(parentElement);
        path = pPath;
        eobject = obj;
        parent = parentElement;
        fragment = isFragmented;
        propertySourceProvider = propSourceProvider;
        if (eobject != null) {
            if(!eobject.eResource().isTrackingModification()){
                eobject.eResource().setTrackingModification(true);
            }
        }
        
//        String fragment = EcoreUtil.getURI(eobject).fragment();
//        Object object = UMLCacheManager.getInstance().get(UMLCacheManager.RESOURCE_URI_CACHE, fragment);
//        
//        if (object == null){ 
//            String uriStr = EcoreUtil.getURI(eobject).toString();
//            UMLCacheManager.getInstance().put(UMLCacheManager.RESOURCE_URI_CACHE, fragment, uriStr);
//        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // StringBuffer sb = new StringBuffer();
        // sb.append("path : ").append(path).append("\r\n");
        // sb.append("eobject : ").append(eobject).append("\r\n");
        // if (parent instanceof ITreeNode) {
        // sb.append("parent : ").append(((ITreeNode)
        // parent).getEObject()).append("\r\n");
        // } else if (parent instanceof Folder) {
        // sb.append("parent : ").append(((Folder)parent).getFullPath()).append("\r\n");
        // }
        // sb.append("fragment : ").append(fragment).append("\r\n");
        // sb.append("propertySourceProvider : ").append(propertySourceProvider).append("\r\n");
        // System.out.println(sb.toString());

        return super.toString();
    }

    /**
     * getResource
     *  
     * @return IResource
     */
    public IResource getResource() {
        return ResourcesPlugin.getWorkspace().getRoot().findMember(path);
    }

    /**
     * Fragment로 만들어진 umf 파일인지, 모델 파일인 umx인지 구분.
     * 
     * @return boolean if true, fragmented file.
     */
    public boolean isFragmented() {
        return fragment;
    }

    /**
     * setIsFragment
     *  
     * @param isFragmented void
     */
    public void setIsFragment(boolean isFragmented) {
        fragment = isFragmented;
    }

    /**
     * @return
     */
    public IPropertySourceProvider getPropertySourceProvider() {
        return propertySourceProvider;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#getEObject()
     */
    public EObject getEObject() {
        return eobject;
    }

    /**
     * refreshNode
     *   void
     */
    public void refreshNode() {
        if (eobject != null && DomainUtil.isProxy(eobject)) {
            URI uri = EcoreUtil.getURI(eobject);
            try {
                eobject = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            if (eobject == null) {
                uri = URI.createURI(path.toString());
                try {
                    Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);

                    if (resource != null && resource.getContents().size() > 0) {
                        eobject = resource.getContents().get(0);
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(EObject eoj) {
        eobject = eoj;
    }

    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class) {
            return new UMLTreeActionFilter();
        }
        return super.getAdapter(adapter);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
     */
    public String getContributorId() {
        return CONTRIBUTOR_ID;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#getParentNode()
     */
    public Object getParentNode() {
        return parent;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#refresh()
     */
    public void refresh() {
        URI uri = EcoreUtil.getURI(eobject);
        eobject = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
        if (eobject == null) {
            uri = URI.createURI(path.toString());
            Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
            if(!resource.isTrackingModification()){
                resource.setTrackingModification(true);
            }
            if (resource.getContents().size() > 0) {
                eobject = resource.getContents().get(0);
            }
        }
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#dispose()
     */
    public void dispose() {
//        ResourceManager.getInstance().removeResource(eobject.eResource());
        ResourceUnloader.getInstance().put(eobject.eResource());
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isValid()
     */
    public boolean isValid() {
        IFile file = WorkspaceSynchronizer.getFile(eobject.eResource());
        try {
            file.deleteMarkers(IMarker.PROBLEM, true, 0);
            IStatus valid = ModelUpdater.isValid(file);
            if (valid.getSeverity() != IStatus.OK) {
                if (valid.isMultiStatus()) {
                    MultiStatus status = (MultiStatus) valid;
                    IStatus[] children = status.getChildren();
                    for (IStatus s : children) {
                        IMarker createMarker = file.createMarker(IMarker.PROBLEM);
                        createMarker.setAttribute(IMarker.MESSAGE, s.getMessage());
                        createMarker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                    }
                }
            }
            return valid.getSeverity() == IStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isReferenceURIChanged()
     */
    @Override
    public boolean isReferenceURIChanged() {
        String fragment = EcoreUtil.getURI(eobject).fragment();
        String uriStr = EcoreUtil.getURI(eobject).toString();

//        Object object = UMLCacheManager.getInstance().get(UMLCacheManager.RESOURCE_URI_CACHE, fragment);
//
//        if (!uriStr.equals(object)) {
//            UMLCacheManager.getInstance().put(UMLCacheManager.RESOURCE_URI_CACHE, fragment, uriStr);
//            return true;
//        }
        return false;
    }

    /**
     * 모델이 열려있는지 닫혀 있는지 상태를 반환한다.
     * 
     * @see nexcore.tool.uml.ui.core.project.ITreeNode#isActive()
     */
    @Override
    public boolean isActive() {
        if (getResource() != null && getResource().exists()) {
            if (eobject == null || eobject.eResource() == null) {
                return true;
            }
            return ResourceManager.getInstance().isActive(eobject.eResource().getURI());
        }

        return true;
    }
}

