/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.utility;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.mdd.core.registry.NotationModelHandlerRegistry;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설 명 : UMLModelerNotationModelHandlerUtil</li>
 * <li>작성일 : 2010. 11. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLModelerNotationModelHandlerUtil {

    /** UML 모델러 노테이션 모델 핸들러 유틸 객체 */
    private static UMLModelerNotationModelHandlerUtil umlModelerNotationModelHandlerUtil;

    /** 노테이션 모델 핸들러 */
    private static INotationModelHandler notationModelHandler;

    /**
     * 생성자
     */
    private UMLModelerNotationModelHandlerUtil() {
    }

    /**
     * UML 모델러 노테이션 모델 핸들러 유틸 객체 반환
     * 
     * @return UMLModelerNotationModelHandlerUtil
     */
    public static UMLModelerNotationModelHandlerUtil getInstance() {
        if (notationModelHandler == null) {
            if (NotationModelHandlerRegistry.getInstance() != null) {
                if (NotationModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER) != null) {
                    notationModelHandler = NotationModelHandlerRegistry.getInstance()
                        .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER);
                }
            }

            return umlModelerNotationModelHandlerUtil;
        }

        return null;
    }

    /**
     * UML 모델러 노테이션 모델 핸들러 반환
     * 
     * @return INotationModelHandler
     */
    public static INotationModelHandler getHandlerInstance() {
        if (NotationModelHandlerRegistry.getInstance() != null) {
            if (NotationModelHandlerRegistry.getInstance()
                .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER) != null) {
                return NotationModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_NOTATION_MODEL_HANDLER);
            }
        }

        return notationModelHandler;
    }

    /**
     * 특정 엘리먼트 하위의 다이어그램 목록 반환하는 메소드
     * 
     * @param element
     * @param diagramType
     * @return List<Diagram>
     */
    public static List<Diagram> getDiagramList(Element element, DiagramType diagramType) {
        List<Diagram> list = new ArrayList<Diagram>();

        EAnnotation annotation = element.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);

        if (annotation == null) {
            return null;
        }

        EList<EObject> contents = annotation.getContents();
        EObject object;
        for (int i = 0; i < contents.size(); i++) {
            object = contents.get(i);
            if (object instanceof Diagram) {
                if (diagramType.equals(((Diagram) object).getType())) {
                    list.add((Diagram) object);
                }
            }
        }

        return list;
    }

    /**
     * 부모가 가진 다이어그램의 이름을 반환하는 메소드 - 없을 경우엔, 부모의 이름을 반환
     * 
     * @param parent
     * @return String
     */
    public static String getDiagramName(Element parent) {
        String diagramName = null;
        EAnnotation diagramAnnotation = ((Element) parent).getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);
        Diagram diagram = null;

        if (diagramAnnotation != null) {
            diagram = (Diagram) diagramAnnotation.getContents().get(0);
        }

        if (diagram != null) {
            diagramName = diagram.getName();
        } else {
            diagramName = ((NamedElement) parent).getName();
        }

        return diagramName;
    }

}
