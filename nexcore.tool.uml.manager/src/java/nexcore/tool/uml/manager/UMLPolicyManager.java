/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.RelationType;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager</li>
 * <li>설  명 : UMLPolicyManager</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class UMLPolicyManager {
    /**
     * UMLPolicyManager
     */
    private UMLPolicyManager() {
        this.init();
    }

    /**
     * 전달된 관계가 소스노드로서 가능한지 판단.
     * 
     * @param relationType
     * @param notationType
     * @return boolean
     */
    public static boolean isConnectableFromSource(RelationType relationType, NodeType sourceType) {

        if (RelationType.ATTACHEMENT.equals(relationType)) {
            if (NodeType.TEXT.equals(sourceType)) {
                return false;
            } else {
                return true;
            }
        }

        if (RelationType.EXTEND.equals(relationType) || RelationType.INCLUDE.equals(relationType)) {
            if (NodeType.PACKAGE.equals(sourceType)) {
                return false;
            } else {
                return true;
            }
        }

        if (!eInstance.ruleDictionary.containsKey(relationType)) {
            return false;
        }
        HashMap<NodeType, List<NodeType>> relationRule = eInstance.ruleDictionary.get(relationType);
        if (null == relationRule) {
            return false;
        }
        List<NodeType> sourceTypes = relationRule.get(sourceType);
        if (null == sourceTypes) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 
     * 
     * @param relationType
     * @param sourceType
     * @param targetType
     * @return boolean
     */
    public static boolean isConnectableToTarget(RelationType relationType, NodeType sourceType, NodeType targetType) {
        if (RelationType.ATTACHEMENT.equals(relationType)) {
        	if (NodeType.NOTE.equals(sourceType) || NodeType.NOTE.equals(targetType)) {
        		return true;
        	}
        	return false;
        }
        if (!eInstance.ruleDictionary.containsKey(relationType)) {
            return false;
        }
        HashMap<NodeType, List<NodeType>> relationRule = eInstance.ruleDictionary.get(relationType);
        if (null == relationRule) {
            return false;
        }
        List<NodeType> sourceTypes = relationRule.get(sourceType);
        if (null == sourceTypes) {
            return false;
        }
        for (NodeType type : sourceTypes) {
            if (type.equals(targetType)) {
                return true;
            }
        }
        return false;
    }

    /** eInstance */
    private static UMLPolicyManager eInstance = new UMLPolicyManager();

    /** ruleDictionary */
    private HashMap<RelationType, HashMap<NodeType, List<NodeType>>> ruleDictionary = new HashMap<RelationType, HashMap<NodeType, List<NodeType>>>();

    /** Relation정책:Dependency */
    private HashMap<NodeType, List<NodeType>> ruleDependency = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Include */
    private HashMap<NodeType, List<NodeType>> ruleInclude = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Extend */
    private HashMap<NodeType, List<NodeType>> ruleExtend = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Association */
    private HashMap<NodeType, List<NodeType>> ruleAssociation = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:ControlFlow */
    private HashMap<NodeType, List<NodeType>> ruleControlFlow = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Generalization */
    private HashMap<NodeType, List<NodeType>> ruleGeneralization = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Message */
    private HashMap<NodeType, List<NodeType>> ruleMessage = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:ReplyMessage */
    private HashMap<NodeType, List<NodeType>> ruleReplyMessage = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:ObjectFlow */
    private HashMap<NodeType, List<NodeType>> ruleObjectFlow = new HashMap<NodeType, List<NodeType>>();

    /** Relation정책:Realization */
    private HashMap<NodeType, List<NodeType>> ruleRealization = new HashMap<NodeType, List<NodeType>>();

    /**
     * 
     * void
     */
    protected void init() {
        initAssociation();
        initControlFlow();
        initGeneralization();
        initIncludeExclude();
        initMessage();
        initReplyMessage();
        initObjectFlow();
        initRealization();
        initDependencyAbstractionUsage();
    }

    /**
     * Relation정책초기화:Dependency void
     */
    private void initDependencyAbstractionUsage() {
        List<NodeType> artifact = new ArrayList<NodeType>();
        List<NodeType> classes = new ArrayList<NodeType>();
        List<NodeType> datatype = new ArrayList<NodeType>();
        List<NodeType> interfaces = new ArrayList<NodeType>();
        List<NodeType> packages = new ArrayList<NodeType>();
        List<NodeType> usecase = new ArrayList<NodeType>();
        List<NodeType> component = new ArrayList<NodeType>();
        List<NodeType> actor = new ArrayList<NodeType>();

        actor.add(NodeType.CLASS);
        actor.add(NodeType.INTERFACE);
        actor.add(NodeType.COMPONENT);

        usecase.add(NodeType.USE_CASE);

        classes.add(NodeType.CLASS);
        classes.add(NodeType.ARTIFACT);
        classes.add(NodeType.DATA_TYPE);
        classes.add(NodeType.ENUMERATION);
        classes.add(NodeType.INTERFACE);

        component.add(NodeType.INTERFACE);
        component.add(NodeType.COMPONENT);

        artifact.add(NodeType.CLASS);
        artifact.add(NodeType.ARTIFACT);
        artifact.add(NodeType.DATA_TYPE);
        artifact.add(NodeType.ENUMERATION);
        artifact.add(NodeType.INTERFACE);
        artifact.add(NodeType.COMPONENT);

        datatype.add(NodeType.CLASS);
        datatype.add(NodeType.ARTIFACT);
        datatype.add(NodeType.DATA_TYPE);
        datatype.add(NodeType.ENUMERATION);
        datatype.add(NodeType.INTERFACE);

        interfaces.add(NodeType.CLASS);
        interfaces.add(NodeType.ARTIFACT);
        interfaces.add(NodeType.DATA_TYPE);
        interfaces.add(NodeType.ENUMERATION);
        interfaces.add(NodeType.INTERFACE);

        packages.add(NodeType.PACKAGE);

        this.ruleDependency.put(NodeType.ARTIFACT, artifact);
        this.ruleDependency.put(NodeType.CLASS, classes);
        this.ruleDependency.put(NodeType.DATA_TYPE, datatype);
        this.ruleDependency.put(NodeType.INTERFACE, interfaces);
        this.ruleDependency.put(NodeType.PACKAGE, packages);
        this.ruleDependency.put(NodeType.USE_CASE, usecase);
        this.ruleDependency.put(NodeType.COMPONENT, component);

        this.ruleDependency.put(NodeType.ACTOR, actor);

        this.ruleDictionary.put(RelationType.DEPENDENCY, this.ruleDependency);
        this.ruleDictionary.put(RelationType.ABSTRACTION, this.ruleDependency);
        this.ruleDictionary.put(RelationType.USAGE, this.ruleDependency);
        // this.ruleDictionary.put(RelationType.EXTEND, this.ruleDependency);
        // this.ruleDictionary.put(RelationType.INCLUDE, this.ruleDependency);

    }

    /**
     * Relation정책초기화:Include void
     */
    private void initIncludeExclude() {
        List<NodeType> usecase = new ArrayList<NodeType>();

        usecase.add(NodeType.USE_CASE);
        this.ruleInclude.put(NodeType.USE_CASE, usecase);
        this.ruleExtend.put(NodeType.USE_CASE, usecase);
        this.ruleDictionary.put(RelationType.INCLUDE, this.ruleInclude);
        this.ruleDictionary.put(RelationType.EXTEND, this.ruleExtend);
    }

    /**
     * Relation정책초기화:Association void
     */
    private void initAssociation() {
        List<NodeType> actor = new ArrayList<NodeType>();
        List<NodeType> artifact = new ArrayList<NodeType>();
        List<NodeType> classes = new ArrayList<NodeType>();
        List<NodeType> datatype = new ArrayList<NodeType>();
        List<NodeType> interfaces = new ArrayList<NodeType>();
        List<NodeType> usecase = new ArrayList<NodeType>();
        List<NodeType> component = new ArrayList<NodeType>();

        actor.add(NodeType.USE_CASE);
        actor.add(NodeType.CLASS);
        actor.add(NodeType.INTERFACE);
        actor.add(NodeType.COMPONENT);

        usecase.add(NodeType.ACTOR);

        classes.add(NodeType.CLASS);
        classes.add(NodeType.ARTIFACT);
        classes.add(NodeType.DATA_TYPE);
        classes.add(NodeType.ENUMERATION);
        classes.add(NodeType.INTERFACE);
        classes.add(NodeType.ACTOR);
        classes.add(NodeType.COMPONENT);

        artifact.add(NodeType.CLASS);
        artifact.add(NodeType.ARTIFACT);
        artifact.add(NodeType.DATA_TYPE);
        artifact.add(NodeType.ENUMERATION);
        artifact.add(NodeType.INTERFACE);

        datatype.add(NodeType.CLASS);
        datatype.add(NodeType.ARTIFACT);
        datatype.add(NodeType.DATA_TYPE);
        datatype.add(NodeType.ENUMERATION);
        datatype.add(NodeType.INTERFACE);

        interfaces.add(NodeType.CLASS);
        interfaces.add(NodeType.ARTIFACT);
        interfaces.add(NodeType.DATA_TYPE);
        interfaces.add(NodeType.ENUMERATION);
        interfaces.add(NodeType.INTERFACE);
        interfaces.add(NodeType.ACTOR);
        interfaces.add(NodeType.COMPONENT);

        component.add(NodeType.CLASS);
        component.add(NodeType.ARTIFACT);
        component.add(NodeType.DATA_TYPE);
        component.add(NodeType.ENUMERATION);
        component.add(NodeType.INTERFACE);
        component.add(NodeType.ACTOR);
        component.add(NodeType.COMPONENT);

        this.ruleAssociation.put(NodeType.ACTOR, actor);
        this.ruleAssociation.put(NodeType.ARTIFACT, artifact);
        this.ruleAssociation.put(NodeType.CLASS, classes);
        this.ruleAssociation.put(NodeType.DATA_TYPE, datatype);
        this.ruleAssociation.put(NodeType.INTERFACE, interfaces);
        this.ruleAssociation.put(NodeType.USE_CASE, usecase);
        this.ruleAssociation.put(NodeType.COMPONENT, component);

        this.ruleDictionary.put(RelationType.ASSOCIATION, this.ruleAssociation);
        this.ruleDictionary.put(RelationType.AGGREGATION, this.ruleAssociation);
        this.ruleDictionary.put(RelationType.COMPOSITION, this.ruleAssociation);
        this.ruleDictionary.put(RelationType.DIRECTED_AGGREGATION, this.ruleAssociation);
        this.ruleDictionary.put(RelationType.DIRECTED_ASSOCIATION, this.ruleAssociation);
        this.ruleDictionary.put(RelationType.DIRECTED_COMPOSITION, this.ruleAssociation);
    }

    /**
     * Relation정책초기화:Generalization void
     */
    private void initGeneralization() {
        List<NodeType> classes = new ArrayList<NodeType>();
        List<NodeType> datatype = new ArrayList<NodeType>();
        List<NodeType> enumeration = new ArrayList<NodeType>();
        List<NodeType> interfaces = new ArrayList<NodeType>();
        List<NodeType> usecase = new ArrayList<NodeType>();
        List<NodeType> actor = new ArrayList<NodeType>();

        classes.add(NodeType.CLASS);
        datatype.add(NodeType.DATA_TYPE);
        enumeration.add(NodeType.DATA_TYPE);
        enumeration.add(NodeType.ENUMERATION);
        interfaces.add(NodeType.INTERFACE);
        usecase.add(NodeType.USE_CASE);
        actor.add(NodeType.ACTOR);

        this.ruleGeneralization.put(NodeType.CLASS, classes);
        this.ruleGeneralization.put(NodeType.DATA_TYPE, datatype);
        this.ruleGeneralization.put(NodeType.ENUMERATION, enumeration);
        this.ruleGeneralization.put(NodeType.INTERFACE, interfaces);
        this.ruleGeneralization.put(NodeType.USE_CASE, usecase);
        this.ruleGeneralization.put(NodeType.ACTOR, actor);

        this.ruleDictionary.put(RelationType.GENERALIZATION, this.ruleGeneralization);

    }

    /**
     * Relation정책초기화:Realization void
     */
    private void initRealization() {
        List<NodeType> collaborationuse = new ArrayList<NodeType>();
        List<NodeType> component = new ArrayList<NodeType>();
        // List<NodeType> packages = new ArrayList<NodeType>();
        List<NodeType> usecase = new ArrayList<NodeType>();
        List<NodeType> classes = new ArrayList<NodeType>();

        usecase.add(NodeType.USE_CASE);
        classes.add(NodeType.INTERFACE);
        collaborationuse.add(NodeType.USE_CASE);
        // packages.add(NodeType.CLASS);
        // packages.add(NodeType.INTERFACE);
        component.add(NodeType.INTERFACE);
        component.add(NodeType.COMPONENT);
        component.add(NodeType.ARTIFACT);

        this.ruleRealization.put(NodeType.USE_CASE, usecase);
        this.ruleRealization.put(NodeType.COLLABORATION_USE, collaborationuse);
        this.ruleRealization.put(NodeType.COLLABORATION, collaborationuse);
        // this.ruleRealization.put(NodeType.PACKAGE, packages);
        this.ruleRealization.put(NodeType.COMPONENT, component);
        this.ruleRealization.put(NodeType.CLASS, classes);

        this.ruleDictionary.put(RelationType.REALIZATION, this.ruleRealization);
        this.ruleDictionary.put(RelationType.INTERFACE_REALIZATION, this.ruleRealization);
        this.ruleDictionary.put(RelationType.COMPONENT_REALIZATION, this.ruleRealization);
    }

    /**
     * Relation정책초기화:Message void
     */
    private void initMessage() {
        List<NodeType> line = new ArrayList<NodeType>();
        List<NodeType> lifeLineBehavior = new ArrayList<NodeType>();

        line.add(NodeType.LINE);
        line.add(NodeType.LIFE_LINE_BEHAVIOR);

        lifeLineBehavior.add(NodeType.LINE);
        lifeLineBehavior.add(NodeType.LIFE_LINE_BEHAVIOR);

        this.ruleMessage.put(NodeType.LINE, line);
        this.ruleMessage.put(NodeType.LIFE_LINE_BEHAVIOR, line);

        this.ruleDictionary.put(RelationType.MESSAGE, this.ruleMessage);
        this.ruleDictionary.put(RelationType.SYNCHRONOUS_MESSAGE, this.ruleMessage);
        this.ruleDictionary.put(RelationType.ASYNCHRONOUS_MESSAGE, this.ruleMessage);
        this.ruleDictionary.put(RelationType.DESTROY_MESSAGE, this.ruleMessage);
    }

    /**
     * 
     * Relation정책초기화:Message void
     */
    private void initReplyMessage() {
        List<NodeType> line = new ArrayList<NodeType>();

        line.add(NodeType.LINE);
        line.add(NodeType.LIFE_LINE_BEHAVIOR);

        this.ruleReplyMessage.put(NodeType.LIFE_LINE_BEHAVIOR, line);

        this.ruleDictionary.put(RelationType.REPLY_MESSAGE, this.ruleReplyMessage);

    }

    /**
     * Relation정책초기화:ObjectFlow void
     */
    private void initObjectFlow() {
        List<NodeType> decisionnode = new ArrayList<NodeType>();
        List<NodeType> forknode = new ArrayList<NodeType>();
        List<NodeType> initialnode = new ArrayList<NodeType>();
        List<NodeType> joinnode = new ArrayList<NodeType>();
        List<NodeType> mergenode = new ArrayList<NodeType>();
        List<NodeType> opaqueaction = new ArrayList<NodeType>();
        List<NodeType> dataNode = new ArrayList<NodeType>();

        initialnode.add(NodeType.CENTRAL_BUFFER_NODE);
        initialnode.add(NodeType.DATA_STORE_NODE);

        opaqueaction.add(NodeType.CENTRAL_BUFFER_NODE);
        opaqueaction.add(NodeType.DATA_STORE_NODE);

        decisionnode.add(NodeType.CENTRAL_BUFFER_NODE);
        decisionnode.add(NodeType.DATA_STORE_NODE);

        forknode.add(NodeType.CENTRAL_BUFFER_NODE);
        forknode.add(NodeType.DATA_STORE_NODE);

        joinnode.add(NodeType.CENTRAL_BUFFER_NODE);
        joinnode.add(NodeType.DATA_STORE_NODE);

        mergenode.add(NodeType.CENTRAL_BUFFER_NODE);
        mergenode.add(NodeType.DATA_STORE_NODE);

        dataNode.add(NodeType.OPAQUE_ACTION);
        dataNode.add(NodeType.JOIN_NODE);
        dataNode.add(NodeType.CENTRAL_BUFFER_NODE);
        dataNode.add(NodeType.DATA_STORE_NODE);

        this.ruleObjectFlow.put(NodeType.DECISION_NODE, decisionnode);
        this.ruleObjectFlow.put(NodeType.FORK_NODE, forknode);
        this.ruleObjectFlow.put(NodeType.INITIAL_NODE, initialnode);
        this.ruleObjectFlow.put(NodeType.JOIN_NODE, joinnode);
        this.ruleObjectFlow.put(NodeType.MERGE_NODE, mergenode);
        this.ruleObjectFlow.put(NodeType.OPAQUE_ACTION, opaqueaction);
        this.ruleObjectFlow.put(NodeType.CENTRAL_BUFFER_NODE, dataNode);
        this.ruleObjectFlow.put(NodeType.DATA_STORE_NODE, dataNode);

        this.ruleDictionary.put(RelationType.OBJECT_FLOW, this.ruleObjectFlow);

    }

    /**
     * Relation정책초기화:ControlFlow void
     */
    private void initControlFlow() {

        List<NodeType> decisionnode = new ArrayList<NodeType>();
        List<NodeType> forknode = new ArrayList<NodeType>();
        List<NodeType> initialnode = new ArrayList<NodeType>();
        List<NodeType> joinnode = new ArrayList<NodeType>();
        List<NodeType> mergenode = new ArrayList<NodeType>();
        List<NodeType> opaqueaction = new ArrayList<NodeType>();

        initialnode.add(NodeType.ACTIVITY_FINAL_NODE);
        initialnode.add(NodeType.DECISION_NODE);
        initialnode.add(NodeType.FORK_NODE);
        initialnode.add(NodeType.OPAQUE_ACTION);
        opaqueaction.add(NodeType.ACTIVITY_FINAL_NODE);
        opaqueaction.add(NodeType.DECISION_NODE);
        opaqueaction.add(NodeType.FORK_NODE);
        opaqueaction.add(NodeType.JOIN_NODE);
        opaqueaction.add(NodeType.MERGE_NODE);
        opaqueaction.add(NodeType.OPAQUE_ACTION);
        decisionnode.add(NodeType.ACTIVITY_FINAL_NODE);
        decisionnode.add(NodeType.DECISION_NODE);
        decisionnode.add(NodeType.FORK_NODE);
        decisionnode.add(NodeType.JOIN_NODE);
        decisionnode.add(NodeType.MERGE_NODE);
        decisionnode.add(NodeType.OPAQUE_ACTION);
        forknode.add(NodeType.ACTIVITY_FINAL_NODE);
        forknode.add(NodeType.DECISION_NODE);
        forknode.add(NodeType.FORK_NODE);
        forknode.add(NodeType.JOIN_NODE);
        forknode.add(NodeType.MERGE_NODE);
        forknode.add(NodeType.OPAQUE_ACTION);
        joinnode.add(NodeType.ACTIVITY_FINAL_NODE);
        joinnode.add(NodeType.DECISION_NODE);
        joinnode.add(NodeType.FORK_NODE);
        joinnode.add(NodeType.JOIN_NODE);
        joinnode.add(NodeType.MERGE_NODE);
        joinnode.add(NodeType.OPAQUE_ACTION);
        mergenode.add(NodeType.ACTIVITY_FINAL_NODE);
        mergenode.add(NodeType.DECISION_NODE);
        mergenode.add(NodeType.FORK_NODE);
        mergenode.add(NodeType.JOIN_NODE);
        mergenode.add(NodeType.MERGE_NODE);
        mergenode.add(NodeType.OPAQUE_ACTION);

        this.ruleControlFlow.put(NodeType.DECISION_NODE, decisionnode);
        this.ruleControlFlow.put(NodeType.FORK_NODE, forknode);
        this.ruleControlFlow.put(NodeType.INITIAL_NODE, initialnode);
        this.ruleControlFlow.put(NodeType.JOIN_NODE, joinnode);
        this.ruleControlFlow.put(NodeType.MERGE_NODE, mergenode);
        this.ruleControlFlow.put(NodeType.OPAQUE_ACTION, opaqueaction);

        this.ruleDictionary.put(RelationType.CONTROL_FLOW, this.ruleControlFlow);

    }

}
