/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.mdd.core.registry.DomainModelHandlerRegistry;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry.ResourceStampRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umlglossary.Glossary;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설 명 : DomainModelHandlerUtil</li>
 * <li>작성일 : 2010. 11. 26.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainModelHandlerUtil {

    /** 도메인 모델 핸들러 */
    public static IDomainModelHandler domainModelHandler;

    /** 룩업 테이블 */
    @SuppressWarnings("unchecked")
    private static List lookupTable = new ArrayList();

    /** 파서 풀 객체 */
    private static XMLParserPool parserPool = new XMLParserPoolImpl();

    /** 이름 대 피쳐 맵 */
    private static Map<String, Object> nameToFeatureMap = new HashMap<String, Object>();

    /**
     * UML 모델러 도메인 모델 핸들러 반환
     * 
     * @return IDomainModelHandler
     */
    public static IDomainModelHandler getHandlerInstance() {
        if (DomainModelHandlerRegistry.getInstance() != null) {
            if (DomainModelHandlerRegistry.getInstance()
                .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_DOMAIN_MODEL_HANDLER) != null) {
                domainModelHandler = DomainModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_DOMAIN_MODEL_HANDLER);
            }

            return domainModelHandler;
        }

        return null;
    }

    /**
     * 적재시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getLoadOptions() {
        Map<Object, Object> loadOptions = new HashMap<Object, Object>();

        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);

        return loadOptions;
    }

    /**
     * 저장시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOptions = new HashMap<Object, Object>();

        saveOptions.put(XMIResource.OPTION_ENCODING, UMLResource.DEFAULT_ENCODING);
        saveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, true);
        saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
        saveOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, lookupTable);
        saveOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        saveOptions.put(XMLResource.OPTION_SAVE_ONLY_IF_CHANGED, true);
        saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
        saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
//        saveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD, true);

        return saveOptions;
    }

    /**
     * 기본 경로 맵 등록
     * 
     * void
     */
    public static void registerDefaultPathmaps() {
        Map<URI, URI> uriMap;
        
        // org.eclipse.uml2.uml.resources 버전 변경으로 해당 버전의 리소스에서 찾도록 변경.
//        URI uri = URI.createURI("jar:platform:/base/plugins/org.eclipse.uml2.uml.resources_2.2.0.v200805131030.jar!/"); //$NON-NLS-1$
        URI uri = URI.createURI(Platform.getBundle("org.eclipse.uml2.uml.resources").getLocation()); //$NON-NLS-1$

        if (domainModelHandler != null) {
            uriMap = domainModelHandler.getResourceSet().getURIConverter().getURIMap();
        } else {
            uriMap = getHandlerInstance().getResourceSet().getURIConverter().getURIMap();
        }

        uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
            uri.appendSegment("libraries").appendSegment(ManagerConstant.EMPTY_STRING)); //$NON-NLS-1$
        uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
            uri.appendSegment("metamodels").appendSegment(ManagerConstant.EMPTY_STRING)); //$NON-NLS-1$
        uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
            uri.appendSegment("profiles").appendSegment(ManagerConstant.EMPTY_STRING)); //$NON-NLS-1$
    }

    /**
     * URI를 경로 맵에 등록
     * 
     * @param uri
     *            void
     */
    public static void registerPathmaps(URI uri) {
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
            uri.appendSegment("libraries").appendSegment("")); //$NON-NLS-1$ //$NON-NLS-2$
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
            uri.appendSegment("metamodels").appendSegment("")); //$NON-NLS-1$ //$NON-NLS-2$
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
            uri.appendSegment("profiles").appendSegment("")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * RM 프로파일 반환
     * 
     * @return Profile
     */
    public static Profile getRMProfile() {
        Resource resource = null;
        URI profileURI = null;
        Profile profile = null;

        // RM 프로파일 로딩
        profileURI = URI.createURI(ManagerConstant.NEXCORE_UML_PROFILES_PATHMAP + ManagerConstant.RM_PROFILE_NAME
            + ManagerConstant.DOT + UMLResource.PROFILE_FILE_EXTENSION);
        if (domainModelHandler != null) {
            resource = domainModelHandler.getResourceSet().getResource(profileURI, true);
        } else {
            resource = getHandlerInstance().getResourceSet().getResource(profileURI, true);
        }

        if (!resource.isLoaded()) {
            try {
                resource.load(getLoadOptions());
            } catch (IOException e) {}
        }

        profile = getUMLProfileRoot(resource);

        return profile;
    }

    /**
     * 인자로 넘어온 리소스의 UML 모델 최상위 객체 반환
     * 
     * @param resource
     * @return Model
     */
    public static Model getUMLModelRoot(Resource resource) {
        Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        return model;
    }

    /**
     * 인자로 넘어온 리소스의 UML 프로파일 최상위 객체 반환
     * 
     * @param resource
     * @return Profile
     */
    public static Profile getUMLProfileRoot(Resource resource) {
        Profile profile = (org.eclipse.uml2.uml.Profile) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.PROFILE);

        return profile;
    }

    /**
     * 다이어그램이 포함된 리소스 가져오는 메소드
     * 
     * @param diagram
     * @return Resource
     */
    public static Resource findResource(Diagram diagram) {
        Element parent = (Element) diagram.getParent();
        EList<Resource> resourceList = null;
        Object obj = null;

        if (domainModelHandler != null) {
            resourceList = domainModelHandler.getResourceSet().getResources();
        } else {
            resourceList = getHandlerInstance().getResourceSet().getResources();
        }

        for (Resource resource : resourceList) {
            for (Iterator iter = EcoreUtil.getAllContents(resource, true); iter.hasNext();) {
                obj = iter.next();

                if (parent.equals(obj)) {
                    return resource;
                }
            }
        }

        return null;
    }

    /**
     * 해당 Element에서 특정 Annotation 반환
     * 
     * @param element
     * @param annotationName
     * @return EAnnotation
     */
    public static EAnnotation findSpecificAnnotation(Element element, String annotationName) {
        if (annotationName != null) {
            if (element.getEAnnotation(annotationName) != null) {
                return element.getEAnnotation(annotationName);
            } else {
                return element.createEAnnotation(annotationName);
            }
        }

        return null;
    }

    /**
     * 해당 다이어그램을 가지는 부모 UML 모델을 반환
     * 
     * @param diagram
     * @return Element
     */
    public static Element findParentUMLElement(Diagram diagram) {
        return (Element) diagram.getParent();
    }

    /**
     * Diagram Annotation 에 담긴 Diagram 이 더 이상 없을 경우 Diagram Annotation 삭제
     * 
     * @param parent
     *            void
     */
    public static void processDiagramAnnotation(Element parent) {
        if (parent.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME).getContents().size() == 0) {
            parent.getEAnnotations()
                .remove(parent.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME));
        }
    }

    /**
     * 커맨드 실행
     * 
     * @param command
     *            void
     */
    public static void executeCommand(RecordingCommand command) {
        TransactionalEditingDomain transactionalEditingDomain = null;

        if (domainModelHandler != null) {
            transactionalEditingDomain = domainModelHandler.getTransactionalEditingDomain();
        } else {
            transactionalEditingDomain = getHandlerInstance().getTransactionalEditingDomain();
        }

        transactionalEditingDomain.getCommandStack().execute(command);
    }

    /**
     * 패키지형 객체 반환
     * 
     * @param uri
     * @return Package
     */
    public static Package load(URI uri) {
        org.eclipse.uml2.uml.Package newPackage = null;
        Resource resource = null;

        try {
            if (domainModelHandler != null) {
                resource = domainModelHandler.getResourceSet().getResource(uri, true);
            } else {
                resource = getHandlerInstance().getResourceSet().getResource(uri, true);
            }
            
            newPackage = (org.eclipse.uml2.uml.Package) EcoreUtil.getObjectByType(resource.getContents(),
                UMLPackage.Literals.PACKAGE);
        } catch (WrappedException we) {
            Log.error(we);
        }

        return newPackage;
    }

    /**
     * 작업공간의 모델 반환
     * 
     * @return List<Model>
     */
    public static List<Model> getModelFromWorkspace() {
        EList<Resource> resourceList = null;

        if (domainModelHandler != null) {
            resourceList = domainModelHandler.getResourceSet().getResources();
        } else {
            resourceList = getHandlerInstance().getResourceSet().getResources();
        }

        List<Model> modelList = new ArrayList<Model>();
        Resource resource;
        EObject eObject;
        for (Iterator<Resource> iterator = resourceList.iterator(); iterator.hasNext();) {
            resource = iterator.next();

            if (!resource.getContents().isEmpty()) {
                if (resource.getURI().lastSegment().endsWith(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION)
                    || resource.getURI().lastSegment().endsWith(UMLResource.LIBRARY_FILE_EXTENSION)) {
                    eObject = resource.getContents().get(0);

                    if (eObject instanceof Model) {
                        modelList.add((Model) eObject);
                    }
                }
            }
        }

        return modelList;
    }

    /**
     * 트랜잭셔널 액션 실행
     * 
     * @param transactionalAction
     *            void
     */
    public static void run(final TransactionalAction transactionalAction) {
        TransactionalEditingDomain transactionalEditingDomain = null;

        if (domainModelHandler != null) {
            transactionalEditingDomain = domainModelHandler.getTransactionalEditingDomain();
        } else {
            transactionalEditingDomain = getHandlerInstance().getTransactionalEditingDomain();
        }

        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            /**
             * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
             */
            @Override
            protected void doExecute() {
                transactionalAction.doExecute();
            }
        });
    }

    /**
     * 진단 출력
     * 
     * @param diagnostic
     * @param indent
     *            void
     */
    public static void printDiagnostic(Diagnostic diagnostic, String indent) {
        Log.warn(indent);
        Log.warn(diagnostic.getMessage());

        for (Iterator<Diagnostic> i = diagnostic.getChildren().iterator(); i.hasNext();) {
            printDiagnostic(i.next(), indent + ManagerConstant.BLANK_STRING);
        }
    }

    /**
     * SVN update 시 변경된 리소스를 구분하기 위한 Time stamp 기록.
     * 
     * @param resource
     *            void
     */
    public static void setResourceTimeStamp(Resource resource) {
        IFile file = WorkspaceSynchronizer.getFile(resource);
        if (file == null) {
            return;
        }
        long stamp = file.getModificationStamp();
        ResourceStampRegistry.setResourceStamp(resource.getURI().toString(), stamp + 1);
    }

    /**
     * Unload and reload resources, to synchronize with file.
     * 
     * @param resourceSet
     *            void
     */
    public static void refreshResourceSet(ResourceSet resourceSet) {
        EList<Resource> list = resourceSet.getResources();
        List<Resource> finalList = new ArrayList<Resource>();
        List<Resource> hostList = new ArrayList<Resource>();

        Resource nextResource;
        String extension = ManagerConstant.EMPTY_STRING;
        for (int i = 0; i < list.size(); i++) {
            nextResource = list.get(i);
            extension = nextResource.getURI().fileExtension();
            if (ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION.equals(extension)
                || ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(extension)) {
                hostList.add(nextResource);
            }
        }

        for (Resource resource : hostList) {
            if (!finalList.contains(resource))
                sortResourcesForFragment(list, finalList, resource);
        }

        for (Iterator<Resource> it = finalList.iterator(); it.hasNext();) {
            nextResource = it.next();
            extension = nextResource.getURI().fileExtension();
            if (ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION.equals(extension)
                || ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(extension)) {
                nextResource.unload();
            }
        }

        for (Iterator<Resource> it = finalList.iterator(); it.hasNext();) {
            nextResource = it.next();
            try {
                extension = nextResource.getURI().fileExtension();
                if (ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION.equals(extension)
                    || ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(extension)) {
                    nextResource.load(getLoadOptions());
                }

            } catch (IOException e) {
                it.remove();
                nextResource.unload();
                Log.error(e);
            }

        }
    }

    /**
     * 
     * 
     * @param list
     * @param finalList
     * @param resource
     *            void
     */
    public static void sortResourcesForFragment(EList<Resource> list, List<Resource> finalList, Resource resource) {
        if (resource == null || resource.getContents() == null || resource.getContents().size() <= 0
            || !(resource.getContents().get(0) instanceof Package)) {
            return;
        }

        Package pack = (Package) resource.getContents().get(0);
        EObjectCondition condition = new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEAnnotation());
        SELECT statement = new SELECT(new FROM(pack), new WHERE(condition));
        IQueryResult result = statement.execute();

        if (result.getException() != null) {
            Log.error(result.getException());
        } else {
            Object obj;
            EAnnotation annotation;
            Resource parentResource;

            for (Iterator<EObject> it = result.iterator(); it.hasNext();) {
                obj = it.next();

                if (obj instanceof EAnnotation) {
                    annotation = (EAnnotation) obj;

                    if (annotation.getSource() == null) {
                        continue;
                    }
                    if (!(annotation.getSource().equals(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_CONTAINER_ANNOTATION_NAME))
                        || annotation.eResource() != resource) {
                        continue;
                    }

                    if (annotation != null) {
                        for (EObject eobject : annotation.getReferences()) {
                            parentResource = eobject.eResource();

                            if (parentResource != null) {
                                if (parentResource == resource) {
                                    continue;
                                }

                                sortResourcesForFragment(list, finalList, parentResource);
                            }
                        }
                    }
                }
            }
        }

        if (list.contains(resource) && !(finalList.contains(resource))) {
            finalList.add(resource);
        }
    }

    /**
     * UMLDomain 설정 (DomainModelHandlerRegistry에서 가져오는 방식으로 변경됨으로 구현 필요 없음)
     * 
     * @param domain
     *            void
     */
    public static void setUMLDomain(IDomainModelHandler domain) {

    }

    /**
     * IDomainModelHandler형 UMLDomain 반환
     * 
     * @return IDomainModelHandler
     */
    public static IDomainModelHandler getUMLDomain() {
        if (domainModelHandler != null) {
            return domainModelHandler;
        } else {
            return getHandlerInstance();
        }
    }

    /**
     * 트랜잭셔널 에디팅 도메인 반환
     * 
     * @return TransactionalEditingDomain
     */
    public static TransactionalEditingDomain getEditingDomain() {
        TransactionalEditingDomain transactionalEditingDomain = null;

        if (domainModelHandler != null) {
            transactionalEditingDomain = domainModelHandler.getTransactionalEditingDomain();
        } else {
            transactionalEditingDomain = getHandlerInstance().getTransactionalEditingDomain();
        }

        return transactionalEditingDomain;
    }

    /**
     * URI에 해당하는 모델을 가져오는 메소드
     * 
     * @param uri
     * @return
     */
    public static Model openModel(URI uri) {
        Model model = null;
        Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);

        try {
            Object objectByType = EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);
            if (objectByType != null) {
                model = (Model) objectByType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    /**
     * 
     * 
     * @param editorPart
     * @param diagram
     * @return DiagramEditDomain
     */
    public static DiagramEditDomain createDiagramEditDomain(IEditorPart editorPart, Diagram diagram) {
        DiagramEditDomain diagramEditDomain = new DiagramEditDomain(editorPart, diagram);

        return diagramEditDomain;
    }

    /**
     * ResourceSet 의 resource 를 맵으로 리턴.
     */
    public static Map<String, Resource> getURIResourceMap() {
        Map<String, Resource> uriResourceMap = new HashMap<String, Resource>();
        ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
        
        EList<Resource> resources = resourceSet.getResources();
        for (Resource r : resources) {
            uriResourceMap.put(r.getURI().toString(), r);
        }
        
        return uriResourceMap;
    }
    
    /**
     * 
     */
    public static Resource createResource(URI uri) throws Exception {
        ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
        Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)resourceSet).getURIResourceMap();
        Resource resource = null;

        if(uriResourceMap != null && uriResourceMap.containsKey(uri) ) {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            
            IFile file = root.getFile(new Path(uri.toString()));
            
            if(!file.exists()) {
                uriResourceMap.remove(uri);
            }
            
//            String message = String.format("%s is exist.", uri.toString());
//            throw new Exception(message);
        }
        
        resource = resourceSet.createResource(uri);
        return resource;
    }
    
    /**
     * 
     * 
     * @param uri
     * @param modelName
     * @return Resource
     */
    public static Resource createUMLModelRoot(URI uri, final String modelName) throws Exception {
        final Resource resource;

        resource = createResource(uri);
        
        domainModelHandler.getTransactionalEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(domainModelHandler.getTransactionalEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                protected void doExecute() {
                    Model umlModelRoot = UMLFactory.eINSTANCE.createModel();
                    umlModelRoot.setName(modelName);

                    Model library = (Model) DomainModelHandlerUtil.load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
                    umlModelRoot.createPackageImport(library);

                    library = (Model) DomainModelHandlerUtil.load(URI.createURI(UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI));
                    umlModelRoot.createPackageImport(library);

                    library = (Model) DomainModelHandlerUtil.load(URI.createURI(UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI));
                    umlModelRoot.createPackageImport(library);

                    resource.getContents().add(umlModelRoot);
                }
            });

        return resource;
    }

    /**
     * 
     * 
     * @param uri
     * @return Resource
     */
    public static Resource createUMLGlossary(URI uri) {
        final Resource resource;

        if (domainModelHandler != null) {
            resource = domainModelHandler.getTransactionalEditingDomain().getResourceSet().createResource(uri);
        } else {
            resource = getHandlerInstance().getTransactionalEditingDomain().getResourceSet().createResource(uri);
        }

        domainModelHandler.getTransactionalEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(domainModelHandler.getTransactionalEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                protected void doExecute() {
                    Glossary glossaryModel = UMLGlossaryFactory.eINSTANCE.createGlossary();
                    resource.getContents().add(glossaryModel);
                }
            });

        return resource;
    }

    /**
     * 
     * void
     */
    public static void save() {
        TransactionalEditingDomain transactionalEditingDomain = null;

        if (domainModelHandler != null) {
            transactionalEditingDomain = domainModelHandler.getTransactionalEditingDomain();
        } else {
            transactionalEditingDomain = getHandlerInstance().getTransactionalEditingDomain();
        }

        for (Resource resource : transactionalEditingDomain.getResourceSet().getResources()) {
            if (resource.isModified()) {
                save(resource, false);
            }
        }
    }

    /**
     * 
     * 
     * @param eObject
     *            void
     */
    public static void save(EObject eObject) {
        if (eObject == null) {
            return;
        }

        final Resource resource = eObject.eResource();
        save(resource, false);
    }

    /**
     * 
     * @param eObject
     * @param forceSave
     *            void
     */
    public static void save(EObject eObject, boolean forceSave) {
        if (eObject == null) {
            return;
        }

        final Resource resource = eObject.eResource();
        save(resource, forceSave);
    }

    /**
     * 트랜잭션 편집 도메인을 이용한 저장
     * 
     * @param resource
     *            void
     */
    public static synchronized void save(final Resource resource, final boolean forceSave) {
        try {
            if (domainModelHandler != null) {
                domainModelHandler.getTransactionalEditingDomain().runExclusive(new Runnable() {
                    /**
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        if (resource == null) {
                            return;
                        }

                        if (resource.isModified() || forceSave) {
                            try {
                                setResourceTimeStamp(resource);
                                resource.save(getSaveOptions());
                                // getCommandStack().markSaveLocation();
                                ((UMLDiagramCommandStack) domainModelHandler.getCommandStackListener()).markSaveLocation();
                            } catch (IOException e) {
                                e.printStackTrace();
                                EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnosticList = resource.getErrors();
                                for (org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic : diagnosticList) {
                                    Log.error(diagnostic.getMessage());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        domainModelHandler.getTransactionalEditingDomain().yield();
                    }
                });

            } else {
                getHandlerInstance().getTransactionalEditingDomain().runExclusive(new Runnable() {
                    /**
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        if (resource == null) {
                            return;
                        }

                        if (resource.isModified() || forceSave) {
                            try {
                                setResourceTimeStamp(resource);
                                resource.save(getSaveOptions());
                                // getCommandStack().markSaveLocation();
                                ((UMLDiagramCommandStack) domainModelHandler.getCommandStackListener()).markSaveLocation();
                            } catch (IOException e) {
                                e.printStackTrace();
                                EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnosticList = resource.getErrors();
                                for (org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic : diagnosticList) {
                                    Log.error(diagnostic.getMessage());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        getHandlerInstance().getTransactionalEditingDomain().yield();
                    }
                });
            }
        } catch (InterruptedException e) {
            Log.error(e);
        }
    }

    /**
     * 
     * 
     * @param rmDataURI
     * @return Resource
     */
    public static Resource loadRMResource(URI rmDataURI) {
        Resource resource = null;

        if (domainModelHandler != null) {
            resource = domainModelHandler.getRMDataResourceSet().getResource(rmDataURI, true);
        } else {
            resource = getHandlerInstance().getRMDataResourceSet().getResource(rmDataURI, true);
        }

        return resource;
    }

}
