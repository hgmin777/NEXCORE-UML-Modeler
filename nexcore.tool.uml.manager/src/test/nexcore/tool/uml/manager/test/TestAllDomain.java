/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */


package nexcore.tool.uml.manager.test;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.model.umlfragment.DiagramGrabber;
import nexcore.tool.uml.model.umlfragment.UMLFragmentFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.test</li>
 * <li>설 명 : DomainTotalTest</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class TestAllDomain extends TestCase {

    /**
     * Diagram Edit Domain 생성 테스트
     * 
     * void
     */
    public void testCreateDiagramEditDomain() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/original.umx")); //$NON-NLS-1$

        Diagram diagram = umlDomain.createDiagram((Element) resource.getContents().get(0),
            DiagramType.CLASS_DIAGRAM,
            "Class Diagram"); //$NON-NLS-1$

        @SuppressWarnings("unused")
        DiagramEditDomain diagramEditDomain = umlDomain.createDiagramEditDomain(null, diagram);

        umlDomain.save(diagram);
    }

    /**
     * Diagram 단편화 생성 테스트
     * 
     * void
     */
    public void testCreateDiagramFragment() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/diagram_fragment.umx")); //$NON-NLS-1$

        Diagram diagram = umlDomain.createDiagram((Element) resource.getContents().get(0),
            DiagramType.CLASS_DIAGRAM,
            "Class Diagram"); //$NON-NLS-1$

        DiagramEditDomain diagramEditDomain = umlDomain.createDiagramEditDomain(null, diagram);
        // assertNotNull(diagramEditDomain.getDomain());

        // 01. 다이어그램 정보 단편화.
        Resource diagramResource = umlDomain.getResourceSet().createResource(URI.createURI("file:/c:/diagram.umf")); //$NON-NLS-1$
        diagramResource.getContents().add(diagramEditDomain.getDiagram());

        // 02. 원본 정보에 넣을 단편화에 관련된 Annotation 생성
        EAnnotation annotation = ((Element) resource.getContents().get(0)).createEAnnotation("Fragment"); //$NON-NLS-1$
        annotation.getReferences().add(diagramResource.getContents().get(0));

        // 03. 원본 정보에 단편화로 대체된 다이어그램 위치 단편화 정보 넣어 주는 Annotation 생성
        DiagramGrabber diagramGrabber = UMLFragmentFactory.eINSTANCE.createDiagramGrabber();
        diagramGrabber.getGrabbedDiagrams().add(diagramEditDomain.getDiagram());

        // 04. 원본 정보 모델 안에 03.에서 생성한 다이어그램 위치 단편화 정보 Annotation 추가
        // modelDomain.getUmlModelRoot().getEAnnotations().add(diagramGrabber);
        DomainUtil.getUMLModelRoot(resource).getEAnnotations().add(diagramGrabber);

        // 05. 단편화 파일에 원본 정보를 참조할 수 있도록 Annotation에 추가
        EAnnotation fragmentContainter = ((Element) diagramResource.getContents().get(0)).createEAnnotation("FragmentContainer"); //$NON-NLS-1$
        fragmentContainter.getReferences().add(diagramGrabber);

        // 06. 원본 리소스 저장
        try {
            // diagramEditDomain.save(diagram);
            resource.save(DomainUtil.getSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 07. 다이어그램 단편화 리소스 파일 저장
        try {
            diagramResource.save(DomainUtil.getSaveOptions());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 생성된 Diagram 단편화 파일 로드 테스트
     * 
     * void
     */
    public void testLoadDiagramFragment() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/diagram_fragment.umx")); //$NON-NLS-1$

        Diagram diagram = umlDomain.createDiagram((Element) resource.getContents().get(0),
            DiagramType.CLASS_DIAGRAM,
            "Class Diagram"); //$NON-NLS-1$

        DiagramEditDomain diagramEditDomain = umlDomain.createDiagramEditDomain(null, diagram);
        // assertNotNull(diagramEditDomain.getDomain());

        // 01. 다이어그램 정보 단편화.
        Resource diagramResource = umlDomain.getResourceSet().createResource(URI.createURI("file:/c:/diagram.umf")); //$NON-NLS-1$
        diagramResource.getContents().add(diagramEditDomain.getDiagram());

        // 02. 원본 정보에 넣을 단편화에 관련된 Annotation 생성
        EAnnotation annotation = ((Element) resource.getContents().get(0)).createEAnnotation("Fragment"); //$NON-NLS-1$
        annotation.getReferences().add(diagramResource.getContents().get(0));

        // 03. 원본 정보에 단편화로 대체된 다이어그램 위치 단편화 정보 넣어 주는 Annotation 생성
        DiagramGrabber diagramGrabber = UMLFragmentFactory.eINSTANCE.createDiagramGrabber();
        diagramGrabber.getGrabbedDiagrams().add(diagramEditDomain.getDiagram());

        // 04. 원본 정보 모델 안에 03.에서 생성한 다이어그램 위치 단편화 정보 Annotation 추가
        // modelDomain.getUmlModelRoot().getEAnnotations().add(diagramGrabber);
        DomainUtil.getUMLModelRoot(resource).getEAnnotations().add(diagramGrabber);

        // 05. 단편화 파일에 원본 정보를 참조할 수 있도록 Annotation에 추가
        EAnnotation fragmentContainter = ((Element) diagramResource.getContents().get(0)).createEAnnotation("FragmentContainer"); //$NON-NLS-1$
        fragmentContainter.getReferences().add(diagramGrabber);

        // 06. 원본 리소스 저장
        try {
            // diagramEditDomain.save(diagram);
            resource.save(DomainUtil.getSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 07. 다이어그램 단편화 리소스 파일 저장
        try {
            diagramResource.save(DomainUtil.getSaveOptions());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // 08. 리소스 셋 클리어.
        umlDomain.getResourceSet().getResources().clear();

        // 09. 원본 파일만 로딩
        // modelDomain.load(URI.createURI("file:/c:/diagram_fragment.umx")); //$NON-NLS-1$
        umlDomain.load(URI.createURI("file:/c:/diagram_fragment.umx")); //$NON-NLS-1$
        // assertNotNull(modelDomain.getResource());

        // 10. 관리되는 모든 리소스 파일 리졸브(해 줄 필요 있을까?)
        // EcoreUtil.resolveAll(modelDomain.getResource());

        // 11. 단편화된 다이어그램 정보 가져오기
        // Diagram foundDiagram = (Diagram) ((Element)
        // modelDomain.getResource().getContents().get(0)).getEAnnotation("Fragment").getReferences().get(0);
        Diagram foundDiagram = (Diagram) ((Element) resource.getContents().get(0)).getEAnnotation("Fragment") //$NON-NLS-1$
            .getReferences()
            .get(0);
        assertNotNull(foundDiagram);

        // 12. 11.의 결과가 null이라면 다이어그램 단편화 리소스 파일만 리졸브(해 줄 필요 있을까?)
        // EcoreUtil.resolveAll(diagram);

        //        System.out.println("Fragmented diagram name is " + foundDiagram.getName()); //$NON-NLS-1$
    }

    /**
     * UML Element 단편화 생성 테스트.
     * 
     * void
     */
    public void testCreateUMLElementFragment() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/uml_element_fragment.umx")); //$NON-NLS-1$

        Diagram diagram = umlDomain.createDiagram((Element) resource.getContents().get(0),
            DiagramType.CLASS_DIAGRAM,
            "Class Diagram"); //$NON-NLS-1$

        DiagramEditDomain diagramEditDomain = umlDomain.createDiagramEditDomain(null, diagram);
        // assertNotNull(diagramEditDomain.getDomain());

        // 01. UML클래스 추가
        // modelDomain.getUmlModelRoot().createOwnedClass("TestClass", false); //$NON-NLS-1$
        // assertNotNull(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        DomainUtil.getUMLModelRoot(resource).createOwnedClass("TestClass", false); //$NON-NLS-1$
        assertNotNull(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 02. 다이어그램에서도 01.에서 UML클래스를 참조하도록 설정
        NotationNode classNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        classNode.setNodeType(NodeType.ACTIVITY);
        classNode.setName("TestClass"); //$NON-NLS-1$
        // classNode.setUmlModel(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        classNode.setUmlModel(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        ((Diagram) diagramEditDomain.getDiagram()).getNodeList().add(classNode);

        // 03. 클래스 정보 단편화.
        Resource umlElementResource = umlDomain.getResourceSet()
            .createResource(URI.createURI("file:/c:/uml_element.umf")); //$NON-NLS-1$
        // umlElementResource.getContents().add(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        umlElementResource.getContents().add(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 04. 원본 정보에 넣을 단편화에 관련된 Annotation 생성
        EAnnotation annotation = ((Element) resource.getContents().get(0)).createEAnnotation("Fragment"); //$NON-NLS-1$
        // annotation.getReferences().add(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        annotation.getReferences().add(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 05. 단편화 파일에 원본 정보를 참조할 수 있도록 Annotation에 추가
        EAnnotation fragmentContainter = ((Element) umlElementResource.getContents().get(0)).createEAnnotation("FragmentContainer"); //$NON-NLS-1$
        // fragmentContainter.getReferences().add(modelDomain.getUmlModelRoot());
        fragmentContainter.getReferences().add(DomainUtil.getUMLModelRoot(resource));

        // 06. 원본 리소스 저장
        try {
            // diagramEditDomain.save();
            resource.save(DomainUtil.getSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 07. UML클래스 단편화 리소스 저장
        try {
            umlElementResource.save(DomainUtil.getSaveOptions());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 생성된 UML Element 단편화 파일 로드 테스트
     * 
     * void
     */
    public void testLoadUMLElementFragment() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/uml_element_fragment.umx")); //$NON-NLS-1$

        Diagram diagram = umlDomain.createDiagram((Element) resource.getContents().get(0),
            DiagramType.CLASS_DIAGRAM,
            "Class Diagram"); //$NON-NLS-1$

        DiagramEditDomain diagramEditDomain = umlDomain.createDiagramEditDomain(null, diagram);
        // assertNotNull(diagramEditDomain.getDomain());

        // 01. UML클래스 추가
        // modelDomain.getUmlModelRoot().createOwnedClass("TestClass", false); //$NON-NLS-1$
        // assertNotNull(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        DomainUtil.getUMLModelRoot(resource).createOwnedClass("TestClass", false); //$NON-NLS-1$
        assertNotNull(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 02. 다이어그램에서도 01.에서 UML클래스를 참조하도록 설정
        NotationNode classNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        classNode.setNodeType(NodeType.CLASS);
        classNode.setName("TestClass"); //$NON-NLS-1$
        // classNode.setUmlModel(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        classNode.setUmlModel(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        ((Diagram) diagramEditDomain.getDiagram()).getNodeList().add(classNode);

        // 03. 클래스 정보 단편화.
        Resource umlElementResource = umlDomain.getResourceSet()
            .createResource(URI.createURI("file:/c:/uml_element.umf")); //$NON-NLS-1$
        // umlElementResource.getContents().add(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        umlElementResource.getContents().add(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 04. 원본 정보에 넣을 단편화에 관련된 Annotation 생성
        EAnnotation annotation = ((Element) resource.getContents().get(0)).createEAnnotation("Fragment"); //$NON-NLS-1$
        // annotation.getReferences().add(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        annotation.getReferences().add(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 05. 단편화 파일에 원본 정보를 참조할 수 있도록 Annotation에 추가
        EAnnotation fragmentContainter = ((Element) umlElementResource.getContents().get(0)).createEAnnotation("FragmentContainer"); //$NON-NLS-1$
        // fragmentContainter.getReferences().add(modelDomain.getUmlModelRoot());
        fragmentContainter.getReferences().add(DomainUtil.getUMLModelRoot(resource));

        // 06. 원본 리소스 저장
        try {
            // diagramEditDomain.save();
            resource.save(DomainUtil.getSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 07. UML클래스 단편화 리소스 저장
        try {
            umlElementResource.save(DomainUtil.getSaveOptions());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // 08. 리소스 셋 클리어.
        umlDomain.getResourceSet().getResources().clear();

        // 09. 원본 파일만 로딩
        // modelDomain.load(URI.createURI("file:/c:/uml_element_fragment.umx")); //$NON-NLS-1$
        umlDomain.load(URI.createURI("file:/c:/uml_element_fragment.umx")); //$NON-NLS-1$
        // assertNotNull(modelDomain.getResource());

        // 10. 관리되는 모든 리소스 파일 리졸브(해 줄 필요 있을까?)
        // EcoreUtil.resolveAll(modelDomain.getResource());

        // 11. 단편화된 UML클래스 단편화 정보 가져오기
        // assertNotNull(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$
        assertNotNull(DomainUtil.getUMLModelRoot(resource).getMember("TestClass")); //$NON-NLS-1$

        // 12. 11.의 결과가 null이라면 UML클래스 단편화 리소스 파일만 리졸브(해 줄 필요 있을까?)
        // EcoreUtil.resolveAll(modelDomain.getUmlModelRoot().getMember("TestClass")); //$NON-NLS-1$

        // System.out.println("Class name is " + modelDomain.getUmlModelRoot().getMember("TestClass").getName()); //$NON-NLS-1$
        System.out.println("Class name is " + DomainUtil.getUMLModelRoot(resource).getMember("TestClass").getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * 프로파일 적재 테스트
     * 
     * void
     */
    public static void testLoadingProfileObject() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.load(URI.createURI("file:/D:/DEVUML/runtime_environment/UML_Modeler/Test88/umlmodel.umx")); //$NON-NLS-1$
        //        Resource resource = umlDomain.load(URI.createURI("file:/D:/Workspaces/rationalsdp/MDA_Test/a.umx")); //$NON-NLS-1$
        DomainUtil.registerDefaultPathmaps();
        Model model = (Model) resource.getContents().get(0);
        assertNotNull(model);

        List<ProfileApplication> profileApplicationList = model.getAllProfileApplications();
        // EcoreUtil.resolveAll(resource);
        System.out.println("profileApplicationList size is " + profileApplicationList.size());
        assertNotNull(profileApplicationList);

        Profile profile = null;
        List<Stereotype> stereotypeList = null;

        EPackage package_ = null;
        for (ProfileApplication profileApplication : profileApplicationList) {
            profile = profileApplication.getAppliedProfile();
            assertNotNull(profile);
            System.out.println(profile.eIsProxy());
            System.out.println(profile.getName());
            package_ = profile.getDefinition(); // ("http://www.eclipse.org/uml2/2.0.0/UML");
            assertNotNull(package_);
            if (package_ != null)
                break;
        }

        org.eclipse.uml2.uml.Package loadedPackage = (org.eclipse.uml2.uml.Package) model.getOwnedMember("패키지");
        assertNotNull(loadedPackage);
        stereotypeList = loadedPackage.getApplicableStereotypes();

        // org.eclipse.uml2.uml.Class class_ = (org.eclipse.uml2.uml.Class)
        // model.getOwnedMember("클래스1");
        // assertNotNull(class_);
        // stereotypeList = class_.getApplicableStereotypes();

        System.out.println("applicableStereotypeList size is " + stereotypeList.size());

    }
}
