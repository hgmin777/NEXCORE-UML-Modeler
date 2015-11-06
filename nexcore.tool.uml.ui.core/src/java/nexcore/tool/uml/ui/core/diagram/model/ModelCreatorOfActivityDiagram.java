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
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
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
 * <li>설 명 : ModelCreatorOfActivityDiagram</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfActivityDiagram implements CreationFactory {

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
    public ModelCreatorOfActivityDiagram(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(org.eclipse.uml2.uml.ActivityFinalNode.class)) {
            this.viewModelType = NodeType.ACTIVITY_FINAL_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.CentralBufferNode.class)) {
            this.viewModelType = NodeType.CENTRAL_BUFFER_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DataStoreNode.class)) {
            this.viewModelType = NodeType.DATA_STORE_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DecisionNode.class)) {
            this.viewModelType = NodeType.DECISION_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ForkNode.class)) {
            this.viewModelType = NodeType.FORK_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InitialNode.class)) {
            this.viewModelType = NodeType.INITIAL_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.JoinNode.class)) {
            this.viewModelType = NodeType.JOIN_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.MergeNode.class)) {
            this.viewModelType = NodeType.MERGE_NODE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.OpaqueAction.class)) {
            this.viewModelType = NodeType.OPAQUE_ACTION;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ControlFlow.class)) {
            this.viewModelType = RelationType.CONTROL_FLOW;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ObjectFlow.class)) {
            this.viewModelType = RelationType.OBJECT_FLOW;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ActivityPartition.class)) {
            this.viewModelType = NodeType.ACTIVITY_PARTITION;
        }
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        Element newObject = null;
        NotationNode notationNode = null;
        ContainerNode containerNode = null;
        Relation relation = null;

        LabelNode nodeLabel = null;
        LabelNode stereotypeLabel = null;
        LabelNode nodeNameLabel = null;

        if (this.targetClass.equals(org.eclipse.uml2.uml.ActivityFinalNode.class)) {
            newObject = UMLHelper.createActivityFinalNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.COMPARTMENT);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.CentralBufferNode.class)) {
            newObject = UMLHelper.createCentralBufferNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DataStoreNode.class)) {
            newObject = UMLHelper.createDataStoreNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.DecisionNode.class)) {
            newObject = UMLHelper.createDecisionNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.LABEL);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ForkNode.class)) {
            newObject = UMLHelper.createForkNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.LABEL);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InitialNode.class)) {
            newObject = UMLHelper.createInitialNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.LABEL);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.JoinNode.class)) {
            newObject = UMLHelper.createJoinNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.LABEL);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.MergeNode.class)) {
            newObject = UMLHelper.createMergeNode();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
            nodeLabel.setUmlModel(newObject);
            nodeLabel.setType(LabelType.LABEL);
            nodeLabel.setId(UUID.randomUUID().toString());
            nodeLabel.setParent(notationNode);
            notationNode.getLabels().add(nodeLabel);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.OpaqueAction.class)) {
            newObject = UMLHelper.createOpaqueAction();
            notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            notationNode.setId(UUID.randomUUID().toString());
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ControlFlow.class)) {
            newObject = UMLHelper.createControlFlow();
            relation = UMLDiagramFactory.eINSTANCE.createRelation();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            relation.setId(UUID.randomUUID().toString());
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ObjectFlow.class)) {
            newObject = UMLHelper.createObjectFlow();
            relation = UMLDiagramFactory.eINSTANCE.createRelation();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            relation.setId(UUID.randomUUID().toString());
            return relation;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.ActivityPartition.class)) {
            newObject = UMLHelper.createActivityPartition();
            containerNode = UMLDiagramFactory.eINSTANCE.createContainerNode();
            containerNode.setUmlModel(newObject);
            containerNode.setNodeType((NodeType) this.viewModelType);
            containerNode.setId(UUID.randomUUID().toString());
            return containerNode;
        }
        return null;

    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return this.viewModelType;
    }
}
