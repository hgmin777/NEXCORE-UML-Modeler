/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.handler;

import java.util.HashMap;
import java.util.Map;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectInformationPackage;
import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.model.umlfragment.UMLFragmentPackage;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.handler</li>
 * <li>설 명 : UMLResourceSetManager</li>
 * <li>작성일 : 2011. 7. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLResourceSetManager {
    /**
     * resourceSetMap
     */
    private Map<String, ResourceSet> resourceSetMap = new HashMap<String, ResourceSet>();

    /**
     * editingDomainMap
     */
    private Map<String, TransactionalEditingDomain> editingDomainMap = new HashMap<String, TransactionalEditingDomain>();

    /**
     * manager
     */
    public static UMLResourceSetManager manager;

    /**
     * UMLResourceSetManager
     */
    private UMLResourceSetManager() {
    }

    /**
     * getInstance
     *  
     * @return UMLResourceSetManager
     */
    public static UMLResourceSetManager getInstance() {
        if (manager == null) {
            manager = new UMLResourceSetManager();
        }

        return manager;
    }

    /**
     * createResourceSet
     *  
     * @param key
     * @return ResourceSet
     */
    private ResourceSet createResourceSet(String key) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getLoadOptions().putAll(DomainUtil.getLoadOptions());

        TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        editingDomainMap.put(key, editingDomain);

//        if (resourceSet != null) {
//            resourceSet.eAdapters().add(new CacheAdapter());
//        }

        registerUsablePackage(resourceSet);
        // 도메인 모델에서 사용하는 확장자 등록
        registerUsableExtension(resourceSet);

        ResourceSet mainResourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        Map<URI, URI> orguriMap = mainResourceSet.getURIConverter().getURIMap();
        Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();

        uriMap.putAll(orguriMap);

        for (Resource resource : mainResourceSet.getResources()) {
            if (!isModelFile(resource)) {
                URI uri = resource.getURI();
                resourceSet.getResource(uri, true);
            }
        }

        return resourceSet;
    }

    /**
     * isModelFile
     *  
     * @param resource
     * @return boolean
     */
    public static boolean isModelFile(Resource resource) {
        return resource.getURI().toString().contains(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION) 
        || resource.getURI().toString().contains(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION);
    }

    /**
     * registerUsablePackage
     *  
     * @param resourceSet void
     */
    public void registerUsablePackage(ResourceSet resourceSet) {
        // UML Modeler 리소스셋 사용 패키지 등록
        resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(UMLDiagramPackage.eNS_URI, UMLDiagramPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(UMLFragmentPackage.eNS_URI, UMLFragmentPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(ProjectInformationPackage.eNS_URI, ProjectInformationPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(UseCaseDetailPackage.eNS_URI, UseCaseDetailPackage.eINSTANCE);

        // RM Data 리소스셋 사용 패키지 등록
        resourceSet.getPackageRegistry().put(RMDataPackage.eNS_URI, RMDataPackage.eINSTANCE);
    }

    /**
     * registerUsableExtension
     *  
     * @param resourceSet void
     */
    public void registerUsableExtension(ResourceSet resourceSet) {
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
            UMLResource.Factory.INSTANCE);
        resourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
        resourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
    }

    /**
     * @return the resourceSet
     */
    public ResourceSet getResourceSet(String key) {
        ResourceSet resourceSet = resourceSetMap.get(key);
        if (resourceSet == null) {
            resourceSet = createResourceSet(key);
        }

        return resourceSet;
    }

    /**
     * dispose
     *  
     * @param key void
     */
    public void dispose(String key) {
        if (resourceSetMap.containsKey(key)) {
            ResourceSet resourceSet = resourceSetMap.get(key);
            resourceSet.getAllContents().remove();
            resourceSetMap.remove(key);
        }
    }
}
