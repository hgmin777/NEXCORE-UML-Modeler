/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.model;

import java.util.UUID;

import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreatorOfUseCaseDiagram</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfUseCaseDiagram implements CreationFactory {

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** View Model의 타입 */
    private Object viewModelType = null;

    /** 객체 세부 타입 */
    @SuppressWarnings("unused")
    private UMLHelper.DirectedType objectType = UMLHelper.DirectedType.NONE;

    /**
     * 생성하고자 하는 클래스타입만으로 생성할 수 있는 경우
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfUseCaseDiagram(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(org.eclipse.uml2.uml.Actor.class)) {
            this.viewModelType = NodeType.ACTOR;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            this.viewModelType = NodeType.PACKAGE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.UseCase.class)) {
            this.viewModelType = NodeType.USE_CASE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            this.viewModelType = RelationType.DEPENDENCY;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Extend.class)) {
            this.viewModelType = RelationType.EXTEND;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Generalization.class)) {
            this.viewModelType = RelationType.GENERALIZATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Include.class)) {
            this.viewModelType = RelationType.INCLUDE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Realization.class)) {
            this.viewModelType = RelationType.REALIZATION;
        }
    }

    /**
     * 생성대상의 타입과 세부 속성이 필요한경우
     * 
     * @param targetClass
     * @param objectType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfUseCaseDiagram(Class targetClass, UMLHelper.DirectedType objectType) {
        this(targetClass);
        this.objectType = objectType;

        if (this.targetClass.equals(org.eclipse.uml2.uml.Association.class)) {
            if (objectType.equals(UMLHelper.DirectedType.BINARY)) {
                this.viewModelType = RelationType.ASSOCIATION;
            } else {
                this.viewModelType = RelationType.DIRECTED_ASSOCIATION;
            }
        }
    }

    /**
     * UseCase UML2Model 생성
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        Element newObject = null;
        NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        notationNode.setId(UUID.randomUUID().toString());
        Relation relation = UMLDiagramFactory.eINSTANCE.createRelation();
        relation.setId(UUID.randomUUID().toString());

        if (this.targetClass.equals(org.eclipse.uml2.uml.Actor.class)) {
            newObject = UMLHelper.createActor();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            newObject = UMLHelper.createPackage();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.UseCase.class)) {
            newObject = UMLHelper.createUseCase();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Association.class)) {
            newObject = UMLHelper.createAssociation();
            relation.setUmlModel(newObject);
            // if(objectType.equals(UMLHelper.DirectedType.DIRECTED)){
            // Association association = (Association)newObject;
            // //Property targetProperty =
            // association.getOwnedEnds().get(UMLHelper.CLIENT_ASSOCIATION_END_INDEX);
            // //association.getNavigableOwnedEnds().add(targetProperty);
            //                
            // }
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            newObject = UMLHelper.createDependency();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Extend.class)) {
            newObject = UMLHelper.createExtend();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Generalization.class)) {
            newObject = UMLHelper.createGeneralization();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Include.class)) {
            newObject = UMLHelper.createInclude();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Realization.class)) {
            newObject = UMLHelper.createRealization();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        }
        return null;
    }

    /**
     * UseCase UML2Model Type 구분
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return this.viewModelType;
    }

}
