/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.model;

import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.gef.requests.CreationFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreationFactory</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ModelCreationFactory implements CreationFactory {

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** 객체 세부 타입 */
    @SuppressWarnings("unused")
    private int objectType = -1;

    /** View Model의 타입 */
    private Object viewModelType = null;

    /**
     * 생성자
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreationFactory(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(INote.class)) {
            viewModelType = NodeType.NOTE;
        } else if (this.targetClass.equals(IText.class)) {
            viewModelType = NodeType.TEXT;
        } else if (this.targetClass.equals(Attachement.class)) {
            viewModelType = null;
        }
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        // Object object = null;
        if (this.targetClass.equals(INote.class)) {
            NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setNodeType(NodeType.NOTE);
            return notationNode;
        } else if (this.targetClass.equals(IText.class)) {
            NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setNodeType(NodeType.TEXT);
            return notationNode;
        } else if (this.targetClass.equals(Attachement.class)) {
            Attachement attachement = UMLDiagramFactory.eINSTANCE.createAttachement();
            return attachement;
        }
        return null;
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return viewModelType;
    }

}
