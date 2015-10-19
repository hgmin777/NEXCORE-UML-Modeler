/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.NotationModelHandlerRegistry;
import nexcore.tool.mdd.core.registry.SemanticModelHandlerRegistry;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.transaction.UMLResourceSetListener;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectInformationPackage;
import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.model.umlfragment.UMLFragmentPackage;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UML212UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.handler</li>
 * <li>설 명 : UMLModelerDomainModelHandler</li>
 * <li>작성일 : 2010. 11. 26.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLModelerDomainModelHandler implements IDomainModelHandler {

    /** UML Modeler 시맨틱 모델 핸들러 */
    private static ISemanticModelHandler semanticModelHandler;

    /** UML Modeler 노테이션 모델 핸들러 */
    private static INotationModelHandler notationModelHandler;

    /** UML Modeler 리소스셋 */
    private ResourceSet umlResourceSet;

    /** RM Data 리소스셋 */
    private ResourceSet rmResourceSet;

    /** 교차참조 어뎁터 */
    private ECrossReferenceAdapter crossReferenceAdapter;

    /** UML Modeler 리소스셋 리스너 */
    private ResourceSetListener umlResourceSetListener;

    /** UML Modeler 트랜잭셔널 에디팅 도메인 */
    private TransactionalEditingDomain umlTransactionalEditingDomain;

    /** UML Modeler 커맨드 스택 리스너 */
    private CommandStackListener umlCommandStackListener;

    /** UML Modeler 클립보드 */
    private Collection<Object> clipboard;

    /**
     * 생성자
     */
    public UMLModelerDomainModelHandler() {
        init();
    }

    /**
     * 도메인 모델 핸들러 인스턴스 반환
     * 
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getInstance()
     */
    public IDomainModelHandler getInstance() {
        // if(instance == null) {
        // instance = new UMLModelerDomainModelHandler();
        //     
        // instance.init();
        // }

        return this;
    }

    /**
     * 초기화
     * 
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#init()
     */
    public void init() {
        // 도메인 모델에서 사용하는 시맨틱 모델 핸들러 등록
        registerSemanticModelHandler();
        // 도메인 모델에서 사용하는 노테이션 모델 핸들러 등록
        registerNotationModelHandler();

        // 도메인 모델에서 사용하는 리소스셋 초기화
        initializeResourceSet();
        // 도메인 모델에서 사용하는 교차참조 어뎁터 초기화
        initializeCrossReferenceAdapter(umlResourceSet);

        // 도메인 모델에서 사용하는 리소스셋 리스너 초기화
        initializeResourceSetListener();

        // 도메인 모델에서 사용하는 커맨드 스택 리스너 초기화
        initializeCommandStackListener();

        // 도메인 모델에서 사용하는 트랜잭셔널 에디팅 도메인 초기화
        initializeTransactionalEditingDomain(umlResourceSet);
        // 도메인 모델에서 사용하는 리소스셋 리스너 등록
        registerResourceSetListener(umlTransactionalEditingDomain, umlResourceSetListener);
        // 도메인 모델에서 사용하는 커맨드 스택 리스너 등록
        registerCommandStackListener(umlTransactionalEditingDomain, umlCommandStackListener);

        // 도메인 모델에서 사용하는 패키지 등록
        registerUsablePackage();
        // 도메인 모델에서 사용하는 확장자 등록
        registerUsableExtension();

        // 선행 초기화 작업 실행
        // PrecedingInitializerRegistry.getInstance().executeInitializer();

        // 도메인 모델에서 사용하는 클립보드 초기화
        initializeClipboard();
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerSemanticModelHandler()
     */
    public void registerSemanticModelHandler() {
        if (SemanticModelHandlerRegistry.getInstance() != null) {
            if (SemanticModelHandlerRegistry.getInstance()
                .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER) != null) {
                semanticModelHandler = SemanticModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER);
            }
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerNotationModelHandler()
     */
    public void registerNotationModelHandler() {
        if (NotationModelHandlerRegistry.getInstance() != null) {
            if (NotationModelHandlerRegistry.getInstance()
                .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER) != null) {
                notationModelHandler = NotationModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER);
            }
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeResourceSet()
     */
    public void initializeResourceSet() {
        // UML Modeler 리소스셋 생성
        if (umlResourceSet == null) {
            umlResourceSet = new ResourceSetImpl();
            ((ResourceSetImpl) umlResourceSet).setURIResourceMap(new HashMap<URI, Resource>() {
                @Override
                public Resource put(URI uri, Resource resource) {
                    URI resourceURI = resource.getURI();
                    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                    IFile file = root.getFile(new Path(resourceURI.toString()));
                    
                    if("uml".equals(file.getFileExtension())){
                        return super.put(uri, resource);
                    }
                    
                    if(!file.exists()) {
                        return null;
                    }
                    if (uri != resourceURI) {
                        if (containsKey(resourceURI)) {
                            return super.put(resourceURI, resource);
                        } else {
                            String uristr = uri.toString();
                            if (uristr.startsWith("platform:/resource")) {
                                return super.put(resourceURI, resource);
                            } else {
                                return super.put(uri, resource);
                            }
                        }
                    } else {
                    }
                    return super.put(uri, resource);
                }
            });
            umlResourceSet.getLoadOptions().putAll(DomainUtil.getLoadOptions());
        }

        // RM Data 리소스셋 생성
        if (rmResourceSet == null) {
            rmResourceSet = new ResourceSetImpl();
            rmResourceSet.getLoadOptions().putAll(DomainUtil.getLoadOptions());
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeCrossReferenceAdapter(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public void initializeCrossReferenceAdapter(ResourceSet resourceSet) {
        if (resourceSet != null) {
            crossReferenceAdapter = new CacheAdapter() {
                /**
                 *  Syncronizing UML Model Job
                 */
                Job job = new Job("Syncronizing UML Model"){

                    @Override
                    protected IStatus run(IProgressMonitor monitor) {
                        return Status.OK_STATUS;
                    }
                
                };
                @Override
                public void notifyChanged(Notification msg) {
                    job.schedule();
                    super.notifyChanged(msg);
                }

                @Override
                protected void handleContainment(Notification notification) {
                    job.schedule();
                    super.handleContainment(notification);
                }

                @Override
                protected void handleCrossReference(EReference reference, Notification notification) {
                    job.schedule();
                    super.handleCrossReference(reference, notification);
                }

            };

            resourceSet.eAdapters().add(crossReferenceAdapter);
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeResourceSetListener()
     */
    public void initializeResourceSetListener() {
        // UML Modeler 리소스셋 리스너 생성
        umlResourceSetListener = new UMLResourceSetListener();
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeCommandStackListener()
     */
    public void initializeCommandStackListener() {
        // UML Modeler 커맨드 스택 리스너 생성
        umlCommandStackListener = new UMLDiagramCommandStack();
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeTransactionalEditingDomain(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public void initializeTransactionalEditingDomain(ResourceSet resourceSet) {
        if (resourceSet != null) {
            // UML Modeler 트랜잭셔널 에디팅 도메인 생성
            umlTransactionalEditingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerResourceSetListener(org.eclipse.emf.transaction.TransactionalEditingDomain,
     *      org.eclipse.emf.transaction.ResourceSetListener)
     */
    public void registerResourceSetListener(TransactionalEditingDomain transactionalEditingDomain,
                                            ResourceSetListener resourceSetListener) {
        if (transactionalEditingDomain != null && resourceSetListener != null) {
            // UML Modeler 트랜잭셔널 에디팅 도메인에 리소스셋 리스너 추가
            transactionalEditingDomain.addResourceSetListener(resourceSetListener);
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerCommandStackListener(org.eclipse.emf.transaction.TransactionalEditingDomain,
     *      org.eclipse.emf.common.command.CommandStackListener)
     */
    public void registerCommandStackListener(TransactionalEditingDomain transactionalEditingDomain,
                                             CommandStackListener commandStackListener) {
        if (transactionalEditingDomain != null && commandStackListener != null) {
            // UML Modeler 트랜잭셔널 에디팅 도메인에 커맨드 스택 리스너 추가
            transactionalEditingDomain.getCommandStack().addCommandStackListener(commandStackListener);

            // 커맨드 스택 리스너가 사용하는 트랜잭셔널 에디팅 도메인도 설정
            if (commandStackListener instanceof UMLDiagramCommandStack) {
                ((UMLDiagramCommandStack) commandStackListener).setTransactionEditingDomain(transactionalEditingDomain);
            }
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerUsablePackage()
     */
    public void registerUsablePackage() {
        // UML Modeler 리소스셋 사용 패키지 등록
        umlResourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        umlResourceSet.getPackageRegistry().put(UMLDiagramPackage.eNS_URI, UMLDiagramPackage.eINSTANCE);
        umlResourceSet.getPackageRegistry().put(UMLFragmentPackage.eNS_URI, UMLFragmentPackage.eINSTANCE);
        umlResourceSet.getPackageRegistry().put(ProjectInformationPackage.eNS_URI, ProjectInformationPackage.eINSTANCE);
        umlResourceSet.getPackageRegistry().put(UseCaseDetailPackage.eNS_URI, UseCaseDetailPackage.eINSTANCE);

        // Stereotype의 참조가 자동으로 되지 않아서 아래의 코드 추가
        umlResourceSet.getPackageRegistry().put(UML212UMLResource.UML_METAMODEL_NS_URI, UMLPackage.eINSTANCE);

        // RM Data 리소스셋 사용 패키지 등록
        rmResourceSet.getPackageRegistry().put(RMDataPackage.eNS_URI, RMDataPackage.eINSTANCE);
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#registerUsableExtension()
     */
    public void registerUsableExtension() {
        umlResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
            UMLResource.Factory.INSTANCE);
        umlResourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
        umlResourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#initializeClipboard()
     */
    public void initializeClipboard() {
        clipboard = new ArrayList<Object>();
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getResourceSet()
     */
    public ResourceSet getResourceSet() {
        if( umlResourceSet == null) {
            initializeResourceSet();
        }
        return umlResourceSet;
    }
    
    /**
     * resource 반환
     * 
     *  
     * @param uri
     * @param loadOnDemand
     * @return Resource
     */
    public Resource getResource(URI uri, boolean loadOnDemand) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        if (uri == null || uri.segmentCount() == 0) {
            return null;
        }

        if (uri == URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI)
            || uri == URI.createURI(UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI)
            || uri == URI.createURI(UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI)) {

        } else {
            IFile file = root.getFile(new Path(uri.toString()));

            if (file == null || !file.exists()) {
                return null;
            }
        }

        Resource resource = umlResourceSet.getResource(uri, loadOnDemand);
        if (resource != null && (!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty())) {
//            resource.unload();
//            resource = umlResourceSet.getResource(uri, loadOnDemand);
//            System.out.println("UMLModelerDomainModelHandler : 405");
            
        }

        if (resource != null && ((ResourceImpl) resource).getIntrinsicIDToEObjectMap() == null) {
            ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
        }

        return resource;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getRMDataResourceSet()
     */
    public ResourceSet getRMDataResourceSet() {
        return rmResourceSet;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getECrossReferenceAdapter()
     */
    public ECrossReferenceAdapter getECrossReferenceAdapter() {
        return crossReferenceAdapter;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getResourceSetListener()
     */
    public ResourceSetListener getResourceSetListener() {
        return umlResourceSetListener;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getCommandStackListener()
     */
    public CommandStackListener getCommandStackListener() {
        return umlCommandStackListener;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getTransactionalEditingDomain()
     */
    public TransactionalEditingDomain getTransactionalEditingDomain() {
        return umlTransactionalEditingDomain;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getSemanticModelHandler()
     */
    public ISemanticModelHandler getSemanticModelHandler() {
        return semanticModelHandler;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getNotationModelHandler()
     */
    public INotationModelHandler getNotationModelHandler() {
        return notationModelHandler;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#getClipboard()
     */
    public Collection<Object> getClipboard() {
        return clipboard;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.IDomainModelHandler#setClipboard(java.util.Collection)
     */
    public void setClipboard(Collection<Object> clipBoard) {
        this.clipboard = clipBoard;
    }

}
