/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.builder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.tool.uml.connector.UMLCacheManager;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.relation.Relation;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.conditions.eobjects.structuralfeatures.EObjectReferencerCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설 명 : CloseResourceAction</li>
 * <li>작성일 : 2011. 6. 15.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class CloseResourceAction implements IObjectActionDelegate {
    /**
     * i
     */
    int i = 0;
    /**
     * referenceResources
     */
    List<EObject> referenceResources = new ArrayList<EObject>();
    /** 선택 객체 */
    private ISelection selection;
    
    /**
     * allElementMap
     */
    static Map<String, Element> allElementMap = Collections.synchronizedMap(new HashMap<String, Element>());
    /**
     * allElementSet
     */
    static Set<Element> allElementSet = Collections.synchronizedSet(new HashSet<Element>());
    
    /**
     * addElementMap
     *  
     * @param object void
     */
    private void addElementMap(final Object object) {
        TransactionalEditingDomain editingDomain = DomainRegistry.getUMLDomain().getTransactionalEditingDomain();
        TransactionalCommandStack commandStack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
            commandStack.execute(new RecordingCommand(editingDomain) {
                @Override
                protected void doExecute() {
                    if (object instanceof NamedElement) {
                        if (object instanceof NamedElement) {
                            NamedElement element = (NamedElement) object;
                            String key = EcoreUtil.getURI(element).fragment();
                                allElementMap.put(key, element);
                        }
                        for (Element child : ((Element) object).allOwnedElements()) {
                            if (child instanceof NamedElement) {
                                NamedElement element = (NamedElement) child;
                                String key = EcoreUtil.getURI(element).fragment();
                                    allElementMap.put(key, element);
                            }
                        }
                        
                    } else if (object instanceof Resource) {
                        Model model = (Model) EcoreUtil.getObjectByType(((Resource) object).getContents(),
                            UMLPackage.Literals.MODEL);
                        if (model != null) {
                            for (Element child : model.allOwnedElements()) {
                                if (child instanceof NamedElement) {
                                    NamedElement element = (NamedElement) child;
                                    // System.out.println(element.getQualifiedName());
                                    String key = EcoreUtil.getURI(element).fragment();
                                    if (key != null && key.length() > 0) {
                                        allElementMap.put(key, element);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * addElementSet
     *  
     * @param object void
     */
    private void addElementSet(final Object object) {
        TransactionalEditingDomain editingDomain = DomainRegistry.getUMLDomain().getTransactionalEditingDomain();
        TransactionalCommandStack commandStack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
            commandStack.execute(new RecordingCommand(editingDomain) {
                @Override
                protected void doExecute() {
                    if (object instanceof NamedElement) {
                        if (object instanceof NamedElement) {
                            NamedElement element = (NamedElement) object;
                            allElementSet.add(element);
                        }
                        for (Element child : ((Element) object).allOwnedElements()) {
                            if (child instanceof NamedElement) {
                                NamedElement element = (NamedElement) child;
                                allElementSet.add(element);
                            }
                        }

                    } else if (object instanceof Resource) {
                        Model model = (Model) EcoreUtil.getObjectByType(((Resource) object).getContents(),
                            UMLPackage.Literals.MODEL);
                        if (model != null) {
                            for (Element child : model.allOwnedElements()) {
                                if (child instanceof NamedElement) {
                                    NamedElement element = (NamedElement) child;
//                                    System.out.println(element.getQualifiedName());
                                    allElementSet.add(element);
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * referenceElement2
     *  
     * @param eObj
     * @param isContainOwnedElement
     * @return List<EObject>
     */
    public List<EObject> referenceElement2(final EObject eObj, boolean isContainOwnedElement) {
        long s = System.currentTimeMillis();
        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        referenceResources = new ArrayList<EObject>();
        
        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(eObj).fragment();
        
        Object object = cacheManager.get(ELEMENT_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            //return (List<EObject>) object;
        }
        
        for (Iterator<?> iterator = resources.iterator(); iterator.hasNext();) {
            final Resource rs = (Resource) iterator.next();
            if (rs != null && ProjectUtil.isModelFile(rs)) {
                try {
                    ProjectExplorerPlugin.getDefault().getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {
                        
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            SELECT statement = new SELECT(10000, false, new FROM(rs.getContents()),
                                new WHERE(new EObjectReferencerCondition(eObj)), monitor);
                            IQueryResult classList = statement.execute();
                            
                            for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
                                EObject eObject = (EObject) ir.next();
                                
                                if (!referenceResources.contains(eObject)) {
                                    referenceResources.add(eObject);
                                }
                            }
                            if(true) {
                                if( eObj instanceof Element ) {
                                    EList<Element> ownedElements = ((Element) eObj).getOwnedElements();
                                    for(Element ownedElement : ownedElements) {
                                        List<EObject> referenceElementOfOwnedElements = referenceElementOfOwnedElement(rs.getContents(), ownedElement);
                                        for (EObject eObject : referenceElementOfOwnedElements) {
                                            if (!referenceResources.contains(eObject)) {
                                                referenceResources.add(eObject);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        
        cacheManager.put(ELEMENT_CACHE_KEY, objectKey, referenceResources);
        System.err.println("referenceElement : "+ (System.currentTimeMillis() - s));
        return referenceResources;
    }

    /**
     * referenceResource
     *  
     * @param eObj
     * @param isContainOwnedElement
     * @return List<Resource>
     */
    public List<Resource> referenceResource(EObject eObj, boolean isContainOwnedElement) {
        long s = System.currentTimeMillis();
        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        List<Resource> referenceResources = new ArrayList<Resource>();
        
        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(eObj).fragment();
        
        Object object = cacheManager.get(RESOURCE_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            //return (List<Resource>) object;
        }
        
        List<EObject> allContents = new ArrayList<EObject>();
        
        for (Iterator<?> iterator = resources.iterator(); iterator.hasNext();) {
            Resource rs = (Resource) iterator.next();
            if (rs != null && ProjectUtil.isModelFile(rs)) {
                allContents.addAll(rs.getContents());
            }
        }
        
        SELECT statement = new SELECT(new FROM(allContents),
            new WHERE(new EObjectReferencerCondition(eObj)));
        IQueryResult classList = statement.execute();

        for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
            EObject eObject = (EObject) ir.next();
            Resource r = eObject.eResource();

            if (!referenceResources.contains(r)) {
                referenceResources.add(r);
            }
        }
        
        if(isContainOwnedElement) {
            if( eObj instanceof Element ) {
                EList<Element> ownedElements = ((Element) eObj).getOwnedElements();
                for(Element ownedElement : ownedElements) {
                    List<Resource> referenceResourceOfOwnedElement = referenceResourceOfOwnedElement(allContents, ownedElement);
                    for (Resource r : referenceResourceOfOwnedElement) {
                        if (!referenceResources.contains(r)) {
                            referenceResources.add(r);
                        }
                    }
                }
            }
        }
        
        cacheManager.put(RESOURCE_CACHE_KEY, objectKey, referenceResources);
        System.err.println("referenceResource : "+ (System.currentTimeMillis() - s));
        return referenceResources;
    }
    /**
     * referenceElement3
     *  
     * @param eObj
     * @param isContainOwnedElement
     * @return List<EObject>
     */
    public List<EObject> referenceElement3(EObject eObj, boolean isContainOwnedElement) {
        long s = System.currentTimeMillis();
        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        List<EObject> referenceResources = new ArrayList<EObject>();
        
        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(eObj).fragment();
        
        Object object = cacheManager.get(ELEMENT_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            //return (List<EObject>) object;
        }
        
        List<EObject> allContents = new ArrayList<EObject>();
        
        for (Iterator<?> iterator = resources.iterator(); iterator.hasNext();) {
            Resource rs = (Resource) iterator.next();
            if (rs != null && ProjectUtil.isModelFile(rs)) {
                allContents.addAll(rs.getContents());
            }
        }
        
        SELECT statement = new SELECT(new FROM(allContents),
            new WHERE(new EObjectReferencerCondition(eObj)));
        IQueryResult classList = statement.execute();
        
        for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
            EObject eObject = (EObject) ir.next();
            
            if (!referenceResources.contains(eObject)) {
                referenceResources.add(eObject);
            }
        }
        
        if(isContainOwnedElement) {
            if( eObj instanceof Element ) {
                EList<Element> ownedElements = ((Element) eObj).getOwnedElements();
                for(Element ownedElement : ownedElements) {
                    List<EObject> referenceElementOfOwnedElements = referenceElementOfOwnedElement(allContents, ownedElement);
                    for (EObject eObject : referenceElementOfOwnedElements) {
                        if (!referenceResources.contains(eObject)) {
                            referenceResources.add(eObject);
                        }
                    }
                }
            }
        }
        
        cacheManager.put(ELEMENT_CACHE_KEY, objectKey, referenceResources);
        System.err.println("referenceElement : "+ (System.currentTimeMillis() - s));
        return referenceResources;
    }
    
    /**
     * 
     * 
     *  
     * @param rs
     * @param ownedElement
     * @return List<Resource>
     */
    public List<Resource> referenceResourceOfOwnedElement(List<EObject> allContents, EObject ownedElement) {
        List<Resource> referenceResources = new ArrayList<Resource>();
        
        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(ownedElement).fragment();
        
        Object object = cacheManager.get(OWNED_RESOURCE_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            //return (List<Resource>) object;
        }
        
        SELECT statement = new SELECT(new FROM(allContents),
            new WHERE(new EObjectReferencerCondition(ownedElement)));
        IQueryResult classList = statement.execute();

        for (Iterator<?> iterator = classList.iterator(); iterator.hasNext();) {
            EObject eObject = (EObject) iterator.next();
            Resource r = eObject.eResource();

            if (!referenceResources.contains(r)) {
                referenceResources.add(r);
            }
        }
        
        cacheManager.put(OWNED_RESOURCE_CACHE_KEY, objectKey, referenceResources);

        return referenceResources;
    }
    
    /**
     * OWNED_RESOURCE_CACHE_KEY
     */
    static final String OWNED_RESOURCE_CACHE_KEY = "nexcore.tool.uml.model.OwnedResource";
    /**
     * OWNED_ELEMENT_CACHE_KEY
     */
    static final String OWNED_ELEMENT_CACHE_KEY = "nexcore.tool.uml.model.OwnedElement";
    /**
     * RESOURCE_CACHE_KEY
     */
    static final String RESOURCE_CACHE_KEY = "nexcore.tool.uml.model.Resource";
    /**
     * ELEMENT_CACHE_KEY
     */
    static final String ELEMENT_CACHE_KEY = "nexcore.tool.uml.model.Element";
    
    /**
     * referenceElementOfOwnedElement
     *  
     * @param allContents
     * @param ownedElement
     * @return List<EObject>
     */
    public List<EObject> referenceElementOfOwnedElement(List<EObject> allContents, EObject ownedElement) {
        List<EObject> referenceElements = new ArrayList<EObject>();

        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(ownedElement).fragment();
        
        Object object = cacheManager.get(OWNED_ELEMENT_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            //return (List<EObject>) object;
        }
        
        SELECT statement = new SELECT(new FROM(allContents),
            new WHERE(new EObjectReferencerCondition(ownedElement)));
        IQueryResult classList = statement.execute();
        
        for (Iterator<?> iterator = classList.iterator(); iterator.hasNext();) {
            EObject eObject = (EObject) iterator.next();
            
            if (!referenceElements.contains(eObject)) {
                referenceElements.add(eObject);
            }
        }
        
        cacheManager.put(OWNED_ELEMENT_CACHE_KEY, objectKey, referenceElements);
        
        return referenceElements;
    }
    

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("unchecked")
    public void run2(IAction action) {
        if ((null == selection) || (!(selection instanceof IStructuredSelection))) {
            return;
        }

        List<IResource> resources = new ArrayList<IResource>();

        for (Iterator iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            if (obj instanceof IProject) {
                resources.add((IResource) obj);
            }

        }

        ResourceManager.cleanResource(null,resources.toArray(new IResource[] {}), true);
    }
    
    /**
     * referenceElement
     *  
     * @param eObj
     * @param isContainOwnedElement
     * @return List<EObject>
     */
    public List<EObject> referenceElement(EObject eObj, boolean isContainOwnedElement) {
        long s = System.currentTimeMillis();
        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        List<EObject> referenceResources = new ArrayList<EObject>();
        
        UMLCacheManager cacheManager = UMLCacheManager.getInstance();
        
        String objectKey = EcoreUtil.getURI(eObj).fragment();
        
        Object object = cacheManager.get(ELEMENT_CACHE_KEY, objectKey);
        if (object != null && object instanceof List) {
            return (List<EObject>) object;
        }
        
        EObjectCondition condition = new EObjectReferencerCondition(eObj);
        if(isContainOwnedElement) {
            if( eObj instanceof Element ) {
                EList<Element> ownedElements = ((Element) eObj).allOwnedElements();
                for(Element ownedElement : ownedElements) {
                    condition = condition.OR(new EObjectReferencerCondition(ownedElement));
                }
            }
        }

        for (Iterator<?> iterator = resources.iterator(); iterator.hasNext();) {
            Resource rs = (Resource) iterator.next();
            if (rs != null && ProjectUtil.isModelFile(rs)) {
                
                SELECT statement = new SELECT(new FROM(rs.getContents()),
                    new WHERE(condition));
                IQueryResult classList = statement.execute();
                
                for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
                    EObject eObject = (EObject) ir.next();
                    
                    if (!referenceResources.contains(eObject)) {
                        referenceResources.add(eObject);
                    }
                }
            }
        }
        
        cacheManager.put(ELEMENT_CACHE_KEY, objectKey, referenceResources);
        System.err.println("referenceElement : "+ (System.currentTimeMillis() - s));
        return referenceResources;
    }
    
    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
//        {
//            ResourceSet resourceSet = new ResourceSetImpl();
//
//            resourceSet.getResourceFactoryRegistry()
//                .getExtensionToFactoryMap()
//                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
//
//            resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//
//            final Resource resource = resourceSet.createResource(URI.createURI("http:///xxxx.xxxxx"));
//
//            EObject xxx = UMLFactory.eINSTANCE.createActor();
//
//            resource.getContents().add(xxx);
//
//            try {
//                resource.save(System.out, null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        List<Element> elements = new ArrayList<Element>();
        {
            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
            Iterator<Resource> ir = resourceSet.getResources().iterator();
            EObjectCondition condition = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getClass_()).OR(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getUseCase()));
            
            EObjectCondition condition2 = null;
            for (int i = 0; i < 2; i++) {
                if (condition2 == null) {
                    condition2 = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getClass_());
                } else {
                    condition2 = condition2.OR(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getUseCase()));
                }
            }
            
            while (ir.hasNext()) {
                Resource resource = ir.next();
    
                SELECT statement = new SELECT(new FROM(resource.getContents()),
                    new WHERE(condition));
                IQueryResult classList = statement.execute();
    
                for (Iterator<?> ir2 = classList.iterator(); ir2.hasNext();) {
                    Element eObject = (Element) ir2.next();
    
                    if (!elements.contains(eObject)) {
                        elements.add(eObject);
                    }
                }
            }
            
            System.out.println(elements.size());
        }
        
        {
            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
            EObject eObj = resourceSet.getEObject(URI.createURI("/bbbb/모델/UML 모델.umx#_c9iE8GKeEeGrt8RmZ77baw"), true);
            
            EObject eObj2 = resourceSet.getEObject(URI.createURI("/aaaa/.fragment/UML_모델/Package1.umf#_I1DQcGaTEeGAUcv1m-cDog"), true);
            
            TreeIterator<EObject> eAllContents = eObj2.eAllContents();
            while(eAllContents.hasNext()){
                EObject next = eAllContents.next();
                System.out.println(EcoreUtil.getURI(next).toString());
                
                
                if( next.eIsProxy()) {
                    System.err.println(next);
                }
            }
            
//            Collection<Setting> nonNavigableInverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(eObj);
//            for (Iterator iterator = nonNavigableInverseReferences.iterator(); iterator.hasNext();) {
//                Setting setting = (Setting) iterator.next();
//                if( setting.getEObject() instanceof NamedElement) {
//                    System.err.println(((NamedElement)setting.getEObject()).getQualifiedName());
//                }
//                System.out.println(EcoreUtil.getURI(setting.getEObject()).toString());
//            }
        }
        
//        /bbbb/모델/UML 모델.umx#_c9iE8GKeEeGrt8RmZ77baw
        
//        try {
//            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//            
//            IProject project = root.getProject("BBB");//assume this exists
//            IFolder link = project.getFolder("/");
//            IPath location = new Path("d:\\aaa.txt");
//            link.createLink(location, IResource.NONE, null);
//            if (ResourcesPlugin.getWorkspace().validateLinkLocation(link, location).isOK()) {
//            } else {
//               //invalid location, throw an exception or warn user
//            }
//
//
//            
////            ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path("")).createLink(new Path(""), true, new NullProgressMonitor());
//        } catch (Exception e) {
//            // TODO: handle exception
//            
//            e.printStackTrace();
//        }
        
//        long s = System.currentTimeMillis();
//        allElementSet.clear();
//        allElementMap.clear();
//        
//        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
//System.out.println("resourceSet.getResources(). " + resourceSet.getResources().size());
//        Iterator<Resource> ir = resourceSet.getResources().iterator();
//        while (ir.hasNext()) {
//            Resource resource = ir.next();
//            if(ProjectUtil.isModelFile(resource))
//                addElementMap(resource);
//        }
//        
//        System.out.println("allElementMap"+ " / " + (System.currentTimeMillis() - s) + " / " + allElementMap.size());
//        s = System.currentTimeMillis();
//        Iterator<Resource> ir2 = resourceSet.getResources().iterator();
//        while (ir2.hasNext()) {
//            Resource resource = ir2.next();
//            if(ProjectUtil.isModelFile(resource))
//                addElementSet(resource);
//        }
//        
//        System.out.println("allElementSet"+ " / " + (System.currentTimeMillis() - s) + " / " + allElementSet.size());
        
//        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
//        {
//            Resource resource = resourceSet.getResource(URI.createURI("/02.BBB/.fragment/UML_Model/Package1.umf"), false);
//            
//            System.out.println(resource);
//        }
        
        
//        {
//            Resource resource = resourceSet.getResource(URI.createURI("/02.BBB/.fragment/UML_Model3/Package1.umf"), true);
//            
//            Collection<Setting> nonNavigableInverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(resource.getContents().get(0));
//            for (Iterator iterator = nonNavigableInverseReferences.iterator(); iterator.hasNext();) {
//                Setting setting = (Setting) iterator.next();
//                System.out.println(setting);
//            }
//        }
//        {
//            Resource resource = resourceSet.getResource(URI.createURI("/02.BBB/.fragment/UML_Model3/Package9.umf"), true);
//            
//            Collection<Setting> nonNavigableInverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(resource.getContents().get(0));
//            for (Iterator iterator = nonNavigableInverseReferences.iterator(); iterator.hasNext();) {
//                Setting setting = (Setting) iterator.next();
//                System.out.println(setting);
//            }
//        }
    }
    
    /**
     * run23
     *  
     * @param action void
     */
    public void run23(IAction action) {
        
        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        System.out.println("remove"+ " / " + resourceSet.getResources().size());
//        Resource r2 = DomainRegistry.getUMLDomain().getResourceSet().getResource(URI.createURI("/01.AAA/Model/UML Model.umx"), true);
        
        long s = System.currentTimeMillis();
//        System.out.println("remove"+ " / " + resourceSet.getResources().size());
//        Iterator<Resource> ir = resourceSet.getResources().iterator(); 
//        while (ir.hasNext()) {
//            Resource r3 = ir.next();
//            if (ProjectUtil.isModelFile(r3)) {
//                ir.remove();
//            }
//        }
//        
//        System.out.println("remove : " + (System.currentTimeMillis() - s)+ " / " + resourceSet.getResources().size());
//        s = System.currentTimeMillis();
//        System.out.println("unload" + resourceSet.getResources().size());
//        for (Iterator<?> iterator = resourceSet.getResources().iterator(); iterator.hasNext();) {
//            Resource type = (Resource) iterator.next();
//            if (ProjectUtil.isModelFile(type)) {
//                type.unload();
//            }
//        }
//        
//        System.out.println("unload : " + (System.currentTimeMillis() - s) + " / " + resourceSet.getResources().size());
        
//        EObject eobject = DomainRegistry.getUMLDomain()
//            .getResourceSet()
//            .getEObject(URI.createURI("/01.AAA/Model/UML Model.umx#_IGUP0FX4EeGfPL-JQzqd0g"), true);
//        EObject eobject2 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/01.AAA/.fragment/UML_Model/Package1.umf#_0xoz4FbYEeGRT54xDCRh5g"), true);
//        EObject eobject3 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/01.AAA/.fragment/UML_Model/Package2.umf#_4KtBcFbYEeGRT54xDCRh5g"), true);
//        EObject actor1 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/02.BBB/Model/UML Model3.umx#_tSjMIFbYEeGRT54xDCRh5g"), true);
//        EObject actor2 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/02.BBB/Model/UML Model3.umx#_tsjAgFbYEeGRT54xDCRh5g"), true);
        EObject actor3 = DomainRegistry.getUMLDomain()
        .getResourceSet()
        .getEObject(URI.createURI("/02.BBB/.fragment/UML_Model/Package1/Package2.umf#_6SGLwFbYEeGRT54xDCRh5g"), true);
//        EObject actor4 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/02.BBB/Model/UML Model3.umx#_7QEP4FbYEeGRT54xDCRh5g"), true);
//        EObject pk1 = DomainRegistry.getUMLDomain()
//        .getResourceSet()
//        .getEObject(URI.createURI("/02.BBB/Model/UML Model3.umx#_sxXxsFbYEeGRT54xDCRh5g"), true);

        System.out.println("2 : " +((Element) actor3).allOwnedElements().size()+" / "+(System.currentTimeMillis() - s));
        EList<Element> els = ((Element) actor3).allOwnedElements();
        for (Element el : els) {
            Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(el);
            for (Setting r : inverseReferences) {
                System.out.println(r.getEObject().eResource().getURI().toString());
            }
        }
        
        Collection<Setting> inverseReferences2 = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(actor3);
        for (Setting r : inverseReferences2) {
            System.out.println(r.getEObject().eResource().getURI().toString());
        }
        
//        Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(eobject3);
//        for (Setting r : inverseReferences) {
//            //System.out.println(r.getEObject().eResource().getURI().toString());
//        }
    }
    /**
     * run4
     *  
     * @param action void
     */
    public void run4(IAction action) {
        
        Resource r = DomainRegistry.getUMLDomain().getResourceSet().getResource(URI.createURI("/DEFAULT/.relation"), true);
        System.out.println(r.isModified());
        
        EList<EObject> contents = r.getContents();
        
        EObject eObject = contents.get(0);
        if( eObject instanceof Relation) {
            EMap<Element, EList<Element>> relationMap = ((Relation)eObject).getRelationMap();
            
            Object[] array = relationMap.keySet().toArray();


            for (Object object : array) {
                Element element = (Element) object;
                String containKey = EcoreUtil.getURI(element).toString();

                EList<Element> eList = relationMap.get(element);
                for(Element l : eList) {
                    System.out.println(l);
                    if (l.eIsProxy()) {
                        
                        URI uri = EcoreUtil.getURI(l);
                        
                        EObject eObject2 = DomainRegistry.getUMLDomain()
                            .getResourceSet()
                            .getEObject(uri, true);
                        
                        System.out.println(eObject2);
                    }
                }
            }
        }
        if(true) return;
        ///DEMO-PROJECT/Model/UML Model2.umx#_eMKIUEyyEeGabdhdcJ-pvA
        EObject eobject = DomainRegistry.getUMLDomain().getResourceSet().getEObject(URI.createURI("/02.BBB/Model/UML Model.umx#_ALB-0FX5EeGfPL-JQzqd0g"), true);
        
        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        for (Resource rs : resources) {
            try {
                SELECT statement = new SELECT(new FROM(contents), new WHERE(new EObjectReferencerCondition(eobject)));
                IQueryResult classList = statement.execute();

                for (Iterator<?> iterator = classList.iterator(); iterator.hasNext();) {
                    Object oo = (Object) iterator.next();
                    System.out.println(EcoreUtil.getURI((EObject) oo).toString());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
            }
        }
        
//        Relation relation = RelationManager.getInstance().getRelation("DEMO-PROJECT");
//        
//        EMap<Element, EList<Element>> relationMap = relation.getRelationMap();
//        
//        Object[] array = relationMap.keySet().toArray();
//
//
//        for (Object object : array) {
//            String containKey = EcoreUtil.getURI((Element) object).toString();
//
//            System.out.println(containKey);
//        }
        
    }
    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }
}
