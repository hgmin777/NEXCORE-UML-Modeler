/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreatorOfClassDiagram</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfClassDiagram implements CreationFactory {

    /** View Model의 타입 */
    private Object viewModelType = null;

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** 객체 세부 타입 */
    @SuppressWarnings("unused")
    private UMLHelper.DirectedType directedType = UMLHelper.DirectedType.NONE;

    /** aggregationKindType */
    @SuppressWarnings("unused")
    private AggregationKind aggregationKindType = AggregationKind.NONE_LITERAL;

    /**
     * 생성하고자 하는 클래스타입만으로 생성할 수 있는 경우
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfClassDiagram(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(org.eclipse.uml2.uml.Artifact.class)) {
            this.viewModelType = NodeType.ARTIFACT;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Class.class)) {
            this.viewModelType = NodeType.CLASS;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Collaboration.class)) {
            this.viewModelType = NodeType.COLLABORATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DataType.class)) {
            this.viewModelType = NodeType.DATA_TYPE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Enumeration.class)) {
            this.viewModelType = NodeType.ENUMERATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Interface.class)) {
            this.viewModelType = NodeType.INTERFACE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            this.viewModelType = NodeType.PACKAGE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Abstraction.class)) {
            this.viewModelType = RelationType.ABSTRACTION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Association.class)) {
            this.viewModelType = RelationType.ASSOCIATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            this.viewModelType = RelationType.DEPENDENCY;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Generalization.class)) {
            this.viewModelType = RelationType.GENERALIZATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Realization.class)) {
            this.viewModelType = RelationType.INTERFACE_REALIZATION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Usage.class)) {
            this.viewModelType = RelationType.USAGE;
        }
    }

    /**
     * 생성대상의 타입과 세부 속성이 필요한경우
     * 
     * @param targetClass
     * @param objectType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfClassDiagram(Class targetClass, UMLHelper.DirectedType directedType) {
        this(targetClass, directedType, AggregationKind.NONE_LITERAL);
    }

    /**
     * ModelCreatorOfClassDiagram
     * @param targetClass
     * @param aggregationKindType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfClassDiagram(Class targetClass, AggregationKind aggregationKindType) {
        this(targetClass, UMLHelper.DirectedType.DIRECTED, aggregationKindType);
    }

    /**
     * ModelCreatorOfClassDiagram
     * @param targetClass
     * @param objectType
     * @param aggregationKindType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfClassDiagram(Class targetClass, UMLHelper.DirectedType objectType,
    AggregationKind aggregationKindType) {
        this(targetClass);
        this.directedType = objectType;
        this.aggregationKindType = aggregationKindType;
        if (this.targetClass.equals(org.eclipse.uml2.uml.Association.class)) {
            if (UMLHelper.DirectedType.BINARY.equals(objectType)) {
                if (AggregationKind.NONE_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.ASSOCIATION;
                } else if (AggregationKind.COMPOSITE_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.COMPOSITION;
                } else if (AggregationKind.SHARED_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.AGGREGATION;
                }
            } else {
                if (AggregationKind.NONE_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.DIRECTED_ASSOCIATION;
                } else if (AggregationKind.COMPOSITE_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.DIRECTED_COMPOSITION;
                } else if (AggregationKind.SHARED_LITERAL.equals(aggregationKindType)) {
                    this.viewModelType = RelationType.DIRECTED_AGGREGATION;
                }
            }
        }
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        Element newObject = null;
        NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        notationNode.setId(UUID.randomUUID().toString());
        Relation relation = UMLDiagramFactory.eINSTANCE.createRelation();
        relation.setId(UUID.randomUUID().toString());

        if (this.targetClass.equals(org.eclipse.uml2.uml.Artifact.class)) {
            newObject = UMLHelper.createArtifact();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Class.class)) {
            newObject = UMLHelper.createClass();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Collaboration.class)) {
            newObject = UMLHelper.createCollaboration();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DataType.class)) {
            newObject = UMLHelper.createDataType();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Enumeration.class)) {
            newObject = UMLHelper.createEnumeration();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Interface.class)) {
            newObject = UMLHelper.createInterface();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Package.class)) {
            newObject = UMLHelper.createPackage();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Abstraction.class)) {
            newObject = UMLHelper.createAbstraction();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Association.class)) {
            newObject = UMLHelper.createAssociation();
            // @SuppressWarnings("unused")
            // Association association = (Association)newObject;
            relation.setUmlModel(newObject);

            // Property sourceProperty = null;
            // if(directedType.equals(UMLHelper.DirectedType.DIRECTED)){
            // Property targetProperty =
            // association.getOwnedEnds().get(UMLHelper.CLIENT_ASSOCIATION_END_INDEX);
            // association.getNavigableOwnedEnds().add(targetProperty);
            // }
            // if(directedType.equals(AggregationKind.SHARED_LITERAL)){
            // sourceProperty =
            // association.getOwnedEnds().get(UMLHelper.SUPPLIER_ASSOCIATION_END_INDEX);
            // sourceProperty.setAggregation(AggregationKind.SHARED_LITERAL);
            // }else if(directedType.equals(AggregationKind.COMPOSITE_LITERAL)){
            // sourceProperty =
            // association.getOwnedEnds().get(UMLHelper.SUPPLIER_ASSOCIATION_END_INDEX);
            // sourceProperty.setAggregation(AggregationKind.COMPOSITE_LITERAL);
            // }
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;

        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Dependency.class)) {
            newObject = UMLHelper.createDependency();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            relation.setName("dependency");
            // ConnectionLabel nameLabel =
            // UMLDiagramFactory.eINSTANCE.createConnectionLabel();
            // nameLabel.setName("Dependency");
            // relation.getLabels().add(nameLabel);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Generalization.class)) {
            newObject = UMLHelper.createGeneralization();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Realization.class)) {
            newObject = UMLHelper.createRealization();
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
        }
        return null;

        // else if (this.targetClass.equals(org.eclipse.uml2.uml.Signal.class))
        // {
        // newObject = UMLHelper.createSignal();
        // } else if
        // (this.targetClass.equals(org.eclipse.uml2.uml.InterfaceRealization.class))
        // {
        // newObject = UMLHelper.createInterfaceRealization();
        // } else if
        // (this.targetClass.equals(org.eclipse.uml2.uml.PackageImport.class)) {
        // newObject = UMLHelper.createPackageImport();
        // } else if
        // (this.targetClass.equals(org.eclipse.uml2.uml.PackageMerge.class)) {
        // newObject = UMLHelper.createPackageMerge();
        // }

    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return this.viewModelType;
    }

}
