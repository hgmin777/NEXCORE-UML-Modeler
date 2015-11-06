/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.utility;

import java.util.List;
import java.util.Map;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설 명 : DomainUtil</li>
 * <li>작성일 : 2010. 11. 26.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainUtil {
    /**
     * 적재시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getLoadOptions() {
        return DomainModelHandlerUtil.getLoadOptions();
    }

    /**
     * 저장시 사용하는 옵션 반환
     * 
     * @return Map<Object,Object>
     */
    public static Map<Object, Object> getSaveOptions() {
        return DomainModelHandlerUtil.getSaveOptions();
    }

    /**
     * 기본 Pathmaps 지정
     * 
     * void
     * 
     * @TODO yschoi URI 정보를 항상 같은 위치에서 찾아 올 수 있도록 변경이 필요함.
     */
    public static void registerDefaultPathmaps() {
        DomainModelHandlerUtil.registerDefaultPathmaps();
    }

    /**
     * URI에 Pathmaps 지정
     * 
     * @param uri
     *            void
     */
    public static void registerPathmaps(URI uri) {
        DomainModelHandlerUtil.registerPathmaps(uri);
    }

    /**
     * RM 프로파일 반환
     * 
     * @return Profile
     */
    public static Profile getRMProfile() {
        return DomainModelHandlerUtil.getRMProfile();
    }

    /**
     * 인자로 넘어온 리소스의 UML 모델 최상위 객체 반환
     * 
     * @param resource
     *            void
     */
    public static Model getUMLModelRoot(Resource resource) {
        return DomainModelHandlerUtil.getUMLModelRoot(resource);
    }

    /**
     * isUMXFile
     *  
     * @param file
     * @return boolean
     */
    public static boolean isUMXFile(IFile file) {
        return ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(file.getFileExtension());
    }

    /**
     * isUMXFile
     *  
     * @param resource
     * @return boolean
     */
    public static boolean isUMXFile(Resource resource) {
        return resource.getURI().toString().contains(ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION);
    }
    
    /**
     * 인자로 넘어온 리소스의 UML 프로파일 최상위 객체 반환
     * 
     * @param resource
     * @return Profile
     */
    public static Profile getUMLProfileRoot(Resource resource) {
        return DomainModelHandlerUtil.getUMLProfileRoot(resource);
    }

    /**
     * 다이어그램이 포함된 리소스 가져오는 메소드
     * 
     * @param diagram
     * @return Resource
     */
    @SuppressWarnings("unchecked")
    public static Resource findResource(Diagram diagram) {
        return DomainModelHandlerUtil.findResource(diagram);
    }

    /**
     * 해당 Element에서 특정 Annotation 반환
     * 
     * @param element
     * @param annotationName
     * @return EAnnotation
     */
    public static EAnnotation findSpecificAnnotation(Element element, String annotationName) {
        return DomainModelHandlerUtil.findSpecificAnnotation(element, annotationName);
    }

    /**
     * 해당 다이어그램을 가지는 부모 UML 모델을 반환
     * 
     * @param diagram
     * @return Element
     */
    public static Element findParentUMLElement(Diagram diagram) {
        return DomainModelHandlerUtil.findParentUMLElement(diagram);
    }

    /**
     * Diagram Annotation 에 담긴 Diagram 이 더 이상 없을 경우 Diagram Annotation 삭제
     * 
     * @param parent
     *            void
     */
    public static void processDiagramAnnotation(Element parent) {
        DomainModelHandlerUtil.processDiagramAnnotation(parent);
    }

    /**
     * 트랜잭션 커맨드 실행
     * 
     * @param command
     *            void
     */
    public static void executeCommand(RecordingCommand command) {
        DomainModelHandlerUtil.executeCommand(command);
    }

    /**
     * 패키지형 객체 반환
     * 
     * @param uri
     * @return org.eclipse.uml2.uml.Package
     */
    public static org.eclipse.uml2.uml.Package load(URI uri) {
        return DomainModelHandlerUtil.load(uri);
    }

    /**
     * 작업공간의 모델 반환
     * 
     * @return List<Model>
     */
    public static List<Model> getModelFromWorkspace() {
        return DomainModelHandlerUtil.getModelFromWorkspace();
    }

    /**
     * 
     * 
     * @param transactionalAction
     *            void
     */
    public static void run(final TransactionalAction transactionalAction) {
        DomainModelHandlerUtil.run(transactionalAction);
    }

    /**
     * 저장시 모델의 유효성을 검증하기 위한 메소드
     * 
     * @param diagnostic
     * @param indent
     *            void
     */
    public static void printDiagnostic(Diagnostic diagnostic, String indent) {
        DomainModelHandlerUtil.printDiagnostic(diagnostic, indent);
    }

    /**
     * SVN update 시 변경된 리소스를 구분하기 위한 Time stamp 기록.
     * 
     * @param resource
     *            void
     */
    public static void setResourceTimeStamp(Resource resource) {
        DomainModelHandlerUtil.setResourceTimeStamp(resource);
    }

    /**
     * Unload and reload resources, to synchronize with file.
     * 
     * @param resourceSet
     *            void
     */
    public static void refreshResourceSet(ResourceSet resourceSet) {
        DomainModelHandlerUtil.refreshResourceSet(resourceSet);
    }

    /**
     * 
     * 
     * @param list
     * @param finalList
     * @param resource
     *            void
     */
    private static void sortResourcesForFragment(EList<Resource> list, List<Resource> finalList, Resource resource) {
        DomainModelHandlerUtil.sortResourcesForFragment(list, finalList, resource);
    }
    
    /**
     * Proxy인지 여부를 리소스셋에서 판단함.
     * {@link org.eclipse.emf.ecore.EObject#eIsProxy()} 대신 이걸 사용하길 권함.
     *  
     * @param eobject
     * @return boolean
     */
    public static boolean isProxy(EObject eobject) {
    	
    	if (eobject == null) {
    		return true;
    	}
    	
    	if (eobject.eIsProxy()) {
    		return true;
    	}
    	
    	URI uri = EcoreUtil.getURI(eobject).trimFragment();
    	Resource resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(uri, false);
    	if (null == resource) {
    		return true;
    	}
    	
    	return false;
    }
}
