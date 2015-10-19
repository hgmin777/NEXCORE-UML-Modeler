/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.util;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.util</li>
 * <li>설 명 : ElementUtil</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ElementUtil {

    /**
     * 파라미터로 넘긴 클래스의 속성 리스트 반환하는 메소드
     * 
     * @param sourceClass
     * @return List<Property>
     */
    public static List<Property> getAttributeList(org.eclipse.uml2.uml.Class sourceClass) {
        List<Property> attributeList = null;

        if (sourceClass.getAttributes().size() > 0) {
            attributeList = sourceClass.getAttributes();
        }

        return attributeList;
    }

    /**
     * 파라미터로 넘긴 속성을 해당 클래스의 속성으로 설정하는 메소드
     * 
     * @param sourceClass
     * @param attribute
     *            void
     */
    public static void setAttribute(org.eclipse.uml2.uml.Package sourcePackage, org.eclipse.uml2.uml.Class sourceClass,
                                    String attributeName, String typeName, int lowerBound, int upperBound) {
        Type type = (PrimitiveType) sourcePackage.createOwnedPrimitiveType(typeName);

        sourceClass.createOwnedAttribute(attributeName, type, lowerBound, upperBound);
    }

    /**
     * 파라미터로 넘긴 UML 모델을 가지는 Diagram을 반환하는 메소드
     * 
     * @param umlModel
     * @return AbstractView
     */
    public static AbstractView findDiagram(Element umlModel) {
        Model model = umlModel.getModel();
        if (model == null) {
            return null;
        }
        EAnnotation diagramAnnotation = model.getEAnnotation("Diagram"); //$NON-NLS-1$
        Diagram diagram = null;

        if (diagramAnnotation == null) {
            return null;
        }

        for (int diagramIdx = 0; diagramIdx < diagramAnnotation.getContents().size(); diagramIdx++) {
            diagram = (Diagram) diagramAnnotation.getContents().get(diagramIdx);

            for (AbstractNode diagramNode : diagram.getNodeList()) {
                if (diagramNode.getUmlModel().equals(umlModel)) {
                    return diagram;
                }
            }
        }

        return null;
    }

    /**
     * 파라미터로 넘긴 UML 모델과 1:1 매칭되는 Node를 반환하는 메소드
     * 
     * @param umlModel
     * @return AbstractView
     */
    public static AbstractView findNode(Element umlModel) {
        Model model = umlModel.getModel();
        if (model == null) {
            return null;
        }
        EAnnotation diagramAnnotation = model.getEAnnotation("Diagram"); //$NON-NLS-1$
        Diagram diagram = null;
        if (diagramAnnotation == null) {
            return null;
        }

        for (int diagramIdx = 0; diagramIdx < diagramAnnotation.getContents().size(); diagramIdx++) {
            diagram = (Diagram) diagramAnnotation.getContents().get(diagramIdx);

            for (AbstractNode diagramNode : diagram.getNodeList()) {
                if (diagramNode.getUmlModel().equals(umlModel)) {
                    return diagramNode;
                }
            }
        }

        return null;
    }
}
