/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.property;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.property.section.AbstractUMLPropertySection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.property</li>
 * <li>설 명 : UMLPropertyCreator</li>
 * <li>작성일 : 2010. 2. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class UMLPropertyCreator {

    /** 프로퍼티 생성 클래스 인스턴스 */
    UMLPropertyCreator propertyCreatorInstance = null;

    /** 프로퍼티 섹션 목록 */
    protected List<AbstractUMLPropertySection> propertySectionList = new ArrayList<AbstractUMLPropertySection>();

    /**
     * 생성자
     */
    public UMLPropertyCreator(ISelection selection) {
        setCreatorInstance(selection);

        createProperty();
    }

    /**
     * 프로퍼티 생성 void
     */
    abstract void createProperty();

    /**
     * 입력된 정보를 기준으로 필요한 프로퍼티 섹션 생성되어야 하는지를 void
     */
    abstract void setNecessaryPropertySection();

    /**
     * 선택된 요소에 따른 프로퍼티 생성 클래스 인스턴스 설정
     * 
     * @param selection
     *            void
     */
    private void setCreatorInstance(ISelection selection) {
        if (selection instanceof Diagram) {
            propertyCreatorInstance = new DiagramPropertyCreator(selection);
        } else if (selection instanceof Element) {
            propertyCreatorInstance = new ElementPropertyCreator(selection);
        } else if (selection instanceof Relation) {
            propertyCreatorInstance = new RelationPropertyCreator(selection);
        } else if (selection instanceof Model) {
            propertyCreatorInstance = new ModelPropertyCreator(selection);
        }
    }

}
