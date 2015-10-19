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
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreatorOfComponentDiagram</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfComponentDiagram implements CreationFactory {

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** View Model의 타입 */
    private Object viewModelType = null;

    /**
     * 생성하고자 하는 클래스타입만으로 생성할 수 있는 경우
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfComponentDiagram(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(org.eclipse.uml2.uml.Artifact.class)) {
            this.viewModelType = NodeType.ARTIFACT;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Component.class)) {
            this.viewModelType = NodeType.COMPONENT;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Interface.class)) {
            this.viewModelType = NodeType.INTERFACE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Abstraction.class)) {
            this.viewModelType = RelationType.ABSTRACTION;
        } /*
           * else if
           * (this.targetClass.equals(org.eclipse.uml2.uml.ComponentRealization
           * .class)) { newObject = UMLHelper.createComponentRealization(); }
           */else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            this.viewModelType = RelationType.DEPENDENCY;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InterfaceRealization.class)) {
            this.viewModelType = RelationType.INTERFACE_REALIZATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Usage.class)) {
            this.viewModelType = RelationType.USAGE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            this.viewModelType = NodeType.PACKAGE;
        }
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        Element newObject = null;
        AbstractNode notationNode = null;

        Relation relation = UMLDiagramFactory.eINSTANCE.createRelation();
        relation.setId(UUID.randomUUID().toString());

        if (this.targetClass.equals(org.eclipse.uml2.uml.Artifact.class)) {
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setId(UUID.randomUUID().toString());
            newObject = UMLHelper.createArtifact();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Component.class)) {
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setId(UUID.randomUUID().toString());
            newObject = UMLHelper.createComponent();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Interface.class)) {
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setId(UUID.randomUUID().toString());
            newObject = UMLHelper.createInterface();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Abstraction.class)) {
            newObject = UMLHelper.createAbstraction();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } /*
           * else if
           * (this.targetClass.equals(org.eclipse.uml2.uml.ComponentRealization
           * .class)) { newObject = UMLHelper.createComponentRealization(); }
           */else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            newObject = UMLHelper.createDependency();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InterfaceRealization.class)) {
            newObject = UMLHelper.createInterfaceRealization();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Usage.class)) {
            newObject = UMLHelper.createUsage();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setId(UUID.randomUUID().toString());
            newObject = UMLHelper.createPackage();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        }

        return newObject;
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return this.viewModelType;
    }

}
