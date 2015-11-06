/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */


package nexcore.tool.uml.manager.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.extension.registry.PrecedingInitializerRegistry;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.transaction.UMLResourceSetListener;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectInformationPackage;
import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.model.umlfragment.UMLFragmentPackage;
import nexcore.tool.uml.model.umlglossary.Glossary;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryFactory;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.test</li>
 * <li>설 명 : UMLDomain New</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
@Deprecated
public class UMLDomainTest {

    /** 트랜잭셔널 에디팅 도메인 */
    // TransactionalEditingDomain transactionEditingDomain =
    // TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("nexcore.tool.uml.manager.editingDomain");
    TransactionalEditingDomain transactionEditingDomain = null;

    /** 리소스 셋 리스너 */
    ResourceSetListener resourceSetListener = null;

    /** 리소스 셋 */
    private ResourceSet resourceSet = new ResourceSetImpl();

    /** 커맨드 스택 */
    private UMLDiagramCommandStack gefCommandStack = new UMLDiagramCommandStack();

    /** UMLDomain의 관리 하에 있는 Clipboard */
    private Collection<Object> clipboard = new ArrayList<Object>();

    /** 교차 참조 어뎁터 */
    private ECrossReferenceAdapter crossReferenceAdapter;

    /** RMData형 리소스를 생성하기 위한 리소스 셋 */
    private ResourceSet rmDataResourceSet = new ResourceSetImpl();

    /**
     * 생성자
     */
    public UMLDomainTest() {
        // 도메인 초기화 메소드 실행
        initializeDomain();
        // 도메인 레지스트리에 등록
        setDomainRegistry();

        // 선행 초기화 작업 실행
        PrecedingInitializerRegistry.getInstance().executeInitializer();
    }

    /**
     * @see nexcore.tool.uml.manager.IUMLDomain#initializeDomain()
     */
    public void initializeDomain() {
        transactionEditingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);

        this.gefCommandStack.setTransactionEditingDomain(transactionEditingDomain);

        resourceSetListener = new UMLResourceSetListener();

        transactionEditingDomain.addResourceSetListener(resourceSetListener);

        this.resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        this.resourceSet.getPackageRegistry().put(UMLDiagramPackage.eNS_URI, UMLDiagramPackage.eINSTANCE);
        this.resourceSet.getPackageRegistry().put(UMLFragmentPackage.eNS_URI, UMLFragmentPackage.eINSTANCE);
        this.resourceSet.getPackageRegistry().put(ProjectInformationPackage.eNS_URI,
            ProjectInformationPackage.eINSTANCE);
        this.resourceSet.getPackageRegistry().put(UseCaseDetailPackage.eNS_URI, UseCaseDetailPackage.eINSTANCE);

        this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
            UMLResource.Factory.INSTANCE);
        this.resourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
        this.resourceSet.getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION, UMLResource.Factory.INSTANCE);

        crossReferenceAdapter = new ECrossReferenceAdapter();
        this.resourceSet.eAdapters().add(crossReferenceAdapter);

        this.resourceSet.getLoadOptions().putAll(DomainUtil.getLoadOptions());

        // RMData용 리소스 셋 초기화
        initializeRMDataResourceSet();
    }

    /**
     * RMData용 리소스 셋 초기화
     * 
     * void
     */
    private void initializeRMDataResourceSet() {
        this.rmDataResourceSet.getPackageRegistry().put(RMDataPackage.eNS_URI, RMDataPackage.eINSTANCE);

        this.rmDataResourceSet.getLoadOptions().putAll(DomainUtil.getLoadOptions());
    }

    /**
     * 도메인 레지스트리에 등록
     * 
     * void
     */
    private void setDomainRegistry() {
//        DomainRegistry.setUMLDomain(this);
    }

    /**
     * UML 모델 생성
     * 
     * @param uri
     * @return Resource
     */
    public Resource createUMLModelRoot(URI uri) {
        return createUMLModelRoot(uri, UMLMessage.LABEL_MODEL);
    }

    /**
     * 최상위 UML 모델 생성
     * 
     * @param uri
     * @return Resource
     */
    public Resource createUMLModelRoot(URI uri, final String modelName) {
        final Resource resource = transactionEditingDomain.getResourceSet().createResource(uri);
        DomainUtil.registerDefaultPathmaps();

        transactionEditingDomain.getCommandStack().execute(new RecordingCommand(transactionEditingDomain) {
            /**
             * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
             */
            @Override
            protected void doExecute() {
                Model umlModelRoot = UMLFactory.eINSTANCE.createModel();
                umlModelRoot.setName(modelName);

                Model umlLibrary = (Model) DomainUtil.load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
                umlModelRoot.createPackageImport(umlLibrary);

                resource.getContents().add(umlModelRoot);
            }
        });

        return resource;
    }

    /**
     * 다이어그램에딧 도메인 생성
     * 
     * @param editorPart
     * @param diagram
     * @return DiagramEditDomain
     */
    public DiagramEditDomain createDiagramEditDomain(IEditorPart editorPart, Diagram diagram) {
        DiagramEditDomain diagramEditDomain = new DiagramEditDomain(editorPart, diagram);

        return diagramEditDomain;
    }

    /**
     * 용어집 모델 생성
     * 
     * @param uri
     * @return Resource
     */
    public Resource createUMLGlossary(URI uri) {
        final Resource resource = transactionEditingDomain.getResourceSet().createResource(uri);

        transactionEditingDomain.getCommandStack().execute(new RecordingCommand(transactionEditingDomain) {
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
     * 선택된 UML모델 요소의 다이어그램 모델 생성
     * 
     * @param parentUMLModel
     * @param diagramType
     * @param diagramName
     * @return Diagram
     */
    public Diagram createDiagram(Element parentUMLModel, DiagramType diagramType, String diagramName) {
        EAnnotation diagramAnnotation = parentUMLModel.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME); //$NON-NLS-1$

        if (diagramAnnotation == null) {
            diagramAnnotation = parentUMLModel.createEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME); //$NON-NLS-1$
        }
        parentUMLModel.getEAnnotations().add(diagramAnnotation);

        Diagram diagram = UMLDiagramFactory.eINSTANCE.createDiagram();
        diagram.setType(diagramType);
        diagram.setName(diagramName);
        diagram.setParent(parentUMLModel);

        return diagram;
    }

    /**
     * 리소스 로드
     * 
     * @param uri
     *            void
     */
    public Resource load(URI uri) {
        Resource resource = null;
        // transactionEditingDomain.getResourceSet().getLoadOptions().putAll(DomainUtil.getLoadOptions());

        DomainUtil.registerDefaultPathmaps();

        try {
            resource = transactionEditingDomain.getResourceSet().getResource(uri, true);
        } catch (Exception exception) {}

        return resource;
    }

    /**
     * RMData 리소스 반환
     * 
     * @param rmDataURI
     * @return Resource
     */
    public Resource loadRMResource(URI rmDataURI) {
        Resource resource = null;

        try {
            resource = rmDataResourceSet.getResource(rmDataURI, true);
        } catch (Exception exception) {}

        return resource;
    }

    /**
     * 전체 리소스 저장
     * 
     * void
     */
    public void save() {
        for (Resource resource : this.transactionEditingDomain.getResourceSet().getResources()) {
            if (resource.isModified()) {
                this.save(resource);
            }
        }
    }

    /**
     * EObject를 포함하는 리소스 저장.
     * 
     * @param eObject
     *            void
     */
    public void save(EObject eObject) {
        if (eObject == null) {
            return;
        }

        final Resource resource = eObject.eResource();
        this.save(resource);
    }

    /**
     * 다이어그램을 포함하는 리소스 저장
     * 
     * @param diagram
     *            void
     */
    public void save(Diagram diagram) {
        final Resource resource = diagram.eResource();
        this.save(resource);
    }

    /**
     * 트랜잭션 편집 도메인을 이용한 저장
     * 
     * @param resource
     *            void
     */
    public void save(final Resource resource) {
        try {
            transactionEditingDomain.runExclusive(new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    if (resource == null) {
                        return;
                    }

                    if (resource.isModified()) {
                        try {
                            DomainUtil.setResourceTimeStamp(resource);
                            resource.save(DomainUtil.getSaveOptions());
                            getCommandStack().markSaveLocation();
                        } catch (IOException e) {
                            EList<Diagnostic> diagnosticList = resource.getErrors();
                            for (Diagnostic diagnostic : diagnosticList) {
                                Log.error(diagnostic.getMessage());
                            }
                        }
                    }

                    transactionEditingDomain.yield();
                }
            });
        } catch (InterruptedException e) {
            Log.error(e);
        }
    }

    /**
     * 트랜잭셔널 에디팅 도메인을 이용한 강제 저장
     * 
     * @param resource
     * @param isForceSave
     *            void
     */
    public void save(final Resource resource, final boolean isForceSave) {
        try {
            transactionEditingDomain.runExclusive(new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    if (resource == null) {
                        return;
                    }

                    if (resource.getURI()
                        .lastSegment()
                        .endsWith(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION)
                        || resource.getURI()
                            .lastSegment()
                            .endsWith(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION)) {

                        try {
                            DomainUtil.setResourceTimeStamp(resource);
                            resource.save(DomainUtil.getSaveOptions());
                            getCommandStack().markSaveLocation();
                        } catch (IOException e) {
                            EList<Diagnostic> diagnosticList = resource.getErrors();

                            for (Diagnostic diagnostic : diagnosticList) {
                                Log.error(diagnostic.getMessage());
                            }
                        }
                    }

                    transactionEditingDomain.yield();
                }
            });
        } catch (InterruptedException e) {
            Log.error(e);
        }
    }

    /**
     * 전체 리소스 boolean 값에 따라 저장
     * 
     * void
     */
    public void save(boolean isForceSave) {
        for (Resource resource : this.transactionEditingDomain.getResourceSet().getResources()) {
            this.save(resource, isForceSave);
        }
    }

    /**
     * 트랜잭셔널 에디팅 도메인 반환
     * 
     * @return ResourceSet
     */
    public TransactionalEditingDomain getTransactionalEditingDomain() {
        return transactionEditingDomain;
    }

    /**
     * 리소스 셋 반환
     * 
     * @return ResourceSet
     */
    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    /**
     * RMData용 리소스 셋 반환
     * 
     * @return ResourceSet
     */
    public ResourceSet getRMDataResourceSet() {
        return rmDataResourceSet;
    }

    /**
     * 커맨드 스택 반환
     * 
     * @return CommandStack
     */
    public CommandStack getTransactionalCommandStack() {
        return transactionEditingDomain.getCommandStack();
    }

    /**
     * 커맨드 스택 반환
     * 
     * @return CommandStack
     */
    public org.eclipse.gef.commands.CommandStack getCommandStack() {
        return this.gefCommandStack;
    }

    /**
     * 클립보드 반환
     * 
     * @return Collection<Object>
     */
    public Collection<Object> getClipboard() {
        return clipboard;
    }

    /**
     * 클립보드 설정
     * 
     * @param collection
     *            void
     */
    public void setClipboard(Collection<Object> collection) {
        clipboard = collection;
    }

    /**
     * 교차 참조 어뎁터 반환
     * 
     * @return ECrossReferenceAdapter
     */
    public ECrossReferenceAdapter getECrossReferenceAdapter() {
        return this.crossReferenceAdapter;
    }
}
