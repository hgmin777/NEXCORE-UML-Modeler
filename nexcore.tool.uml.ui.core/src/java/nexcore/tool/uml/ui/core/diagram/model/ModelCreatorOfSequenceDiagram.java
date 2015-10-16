/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.model;

import java.util.UUID;

import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.MessageSort;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreatorOfSequenceDiagram</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfSequenceDiagram implements CreationFactory {

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** View Model의 타입 */
    private Object viewModelType = null;

    /** Message 세부 타입 */
    private MessageSort messageType = MessageSort.SYNCH_CALL_LITERAL;

    /** CombinedFragment */
    @SuppressWarnings("unused")
    private InteractionOperatorKind interactionOperatorKind = InteractionOperatorKind.ALT_LITERAL;
    
    /**
     * operandType
     */
    private String operandType = null;

    /**
     * 생성하고자 하는 클래스타입만으로 생성할 수 있는 경우
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfSequenceDiagram(Class targetClass) {
        this.targetClass = targetClass;
        if (this.targetClass.equals(org.eclipse.uml2.uml.DestructionEvent.class)) {
            this.viewModelType = NodeType.DESTRUCTION_EVENT;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InteractionUse.class)) {
            this.viewModelType = NodeType.INTERACTION_USE;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Lifeline.class)) {
            this.viewModelType = NodeType.LIFELINE;
        } 
    }

    /**
     * MessageType 설정을 위한 생성자
     * 
     * @param targetClass
     * @param objectType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfSequenceDiagram(Class targetClass, MessageSort messageType) {
        this(targetClass);
        this.messageType = messageType;

        if (this.targetClass.equals(org.eclipse.uml2.uml.Message.class)) {

            if (messageType.equals(MessageSort.ASYNCH_CALL_LITERAL)) {
                this.viewModelType = RelationType.ASYNCHRONOUS_MESSAGE;
            } else if (messageType.equals(MessageSort.SYNCH_CALL_LITERAL)) {
                this.viewModelType = RelationType.SYNCHRONOUS_MESSAGE;
            } else if (messageType.equals(MessageSort.CREATE_MESSAGE_LITERAL)) {
                this.viewModelType = RelationType.CREATE_MESSAGE;
            } else if (messageType.equals(MessageSort.DELETE_MESSAGE_LITERAL)) {
                this.viewModelType = RelationType.DESTROY_MESSAGE;
            } else if (messageType.equals(MessageSort.REPLY_LITERAL)) {
                this.viewModelType = RelationType.REPLY_MESSAGE;
            }

        }
    }

    /**
     * ModelCreatorOfSequenceDiagram
     * @param targetClass
     * @param interactionOperatorKind
     * @param operandType
     */
    @SuppressWarnings( { "unchecked", "static-access" })
    public ModelCreatorOfSequenceDiagram(Class targetClass, InteractionOperatorKind interactionOperatorKind, String operandType) {
        this(targetClass);
        this.messageType = messageType.SYNCH_CALL_LITERAL;
        this.interactionOperatorKind = interactionOperatorKind;
        this.viewModelType = NodeType.COMBINED_FRAGMENT;
        this.operandType = operandType;
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

        if (this.targetClass.equals(org.eclipse.uml2.uml.DestructionEvent.class)) {
            newObject = UMLHelper.createDestructionEvent();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.CombinedFragment.class)) {
            newObject = UMLHelper.createCombinedFragment();
            ((CombinedFragment) newObject).setInteractionOperator(this.interactionOperatorKind);
            notationNode.setUmlModel(newObject);
            if(this.interactionOperatorKind.equals(InteractionOperatorKind.OPT_LITERAL)) {
                notationNode.setNodeType(NodeType.OPTION_IF);               
            } else if(this.interactionOperatorKind.equals(InteractionOperatorKind.ALT_LITERAL)) {
                if(UICoreConstant.ALTERNATIVE_IFELSE.equals(operandType)) {                    
                    notationNode.setNodeType(NodeType.ALTERNATIVE_IF_ELSE);  
                } else if (UICoreConstant.ALTERNATIVE_SWITCH.equals(operandType)) {   
                    notationNode.setNodeType(NodeType.ALTERNATIVE_SWITCH);  
                }
            } else if(this.interactionOperatorKind.equals(InteractionOperatorKind.LOOP_LITERAL)) {
                if(UICoreConstant.LOOP_WHILE.equals(operandType)) {                    
                    notationNode.setNodeType(NodeType.LOOP_WHILE);  
                } else if (UICoreConstant.LOOP_FOR.equals(operandType)) {   
                    notationNode.setNodeType(NodeType.LOOP_FOR);  
                }
            }
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.InteractionUse.class)) {
            newObject = UMLHelper.createInteractionUse();
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Lifeline.class)) {
            newObject = UMLHelper.createLifeline();
            notationNode = UMLDiagramFactory.eINSTANCE.createLifeLineNode();
            notationNode.setId(UUID.randomUUID().toString());
            notationNode.setUmlModel(newObject);
            notationNode.setNodeType((NodeType) this.viewModelType);
            Line line = UMLDiagramFactory.eINSTANCE.createLine();
            line.setId(UUID.randomUUID().toString());
            line.setUmlModel(newObject);
            line.setNodeType(NodeType.LINE);
            line.setParent(notationNode);
            ((LifeLineNode) notationNode).setLine(line);
            return notationNode;
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Message.class)) {
            newObject = UMLHelper.createMessage();
            relation.setUmlModel(newObject);
            relation.setRelationType((RelationType) this.viewModelType);
            relation.setName("message");
            return relation;
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
