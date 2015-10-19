/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import java.util.HashMap;

import nexcore.tool.uml.model.umldiagram.NodeType;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.figure</li>
 * <li>설 명 : FigureConstant</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class FigureConstant {

    /** Singleton 처리 인스턴스 */
    private static FigureConstant eInstance = new FigureConstant();

    /** 타입별 상수값 사전 */
    private HashMap<NodeType, Dimension> figureDicationary;

    /**
     * 타입별로 사이즈 사전에 저장
     */
    private FigureConstant() {
        this.figureDicationary = new HashMap<NodeType, Dimension>();
        this.figureDicationary.put(NodeType.ACTIVITY_FINAL_NODE, new Dimension(FIGURE_ACTIVITYFINALNODE_WIDTH,
            FIGURE_ACTIVITYFINALNODE_HEIGHT));
        this.figureDicationary.put(NodeType.ACTOR, new Dimension(FIGURE_ACTOR_WIDTH, FIGURE_ACTOR_HEIGHT));
        this.figureDicationary.put(NodeType.ARTIFACT, new Dimension(FIGURE_ARTIFACT_WIDTH, FIGURE_ARTIFACT_HEIGHT));
        this.figureDicationary.put(NodeType.CENTRAL_BUFFER_NODE, new Dimension(FIGURE_CENTRALBUFFERNODE_WIDTH,
            FIGURE_CENTRALBUFFERNODE_HEIGHT));
        this.figureDicationary.put(NodeType.CLASS, new Dimension(FIGURE_CLASS_WIDTH, FIGURE_CLASS_HEIGHT));
        this.figureDicationary.put(NodeType.COLLABORATION, new Dimension(FIGURE_COLLABORATION_WIDTH,
            FIGURE_COLLABORATION_HEIGHT));
        this.figureDicationary.put(NodeType.COLLABORATION_USE, new Dimension(FIGURE_COLLABORATIONUSE_WIDTH,
            FIGURE_COLLABORATIONUSE_HEIGHT));
        this.figureDicationary.put(NodeType.COMPONENT, new Dimension(FIGURE_COMPONENT_WIDTH, FIGURE_COMPONENT_HEIGHT));
        this.figureDicationary.put(NodeType.DATA_STORE_NODE, new Dimension(FIGURE_DATASTORENODE_WIDTH,
            FIGURE_DATASTORENODE_HEIGHT));
        this.figureDicationary.put(NodeType.DATA_TYPE, new Dimension(FIGURE_DATATYPE_WIDTH, FIGURE_DATATYPE_HEIGHT));
        this.figureDicationary.put(NodeType.DECISION_NODE, new Dimension(FIGURE_DECISIONNODE_WIDTH,
            FIGURE_DECISIONNODE_HEIGHT));
        this.figureDicationary.put(NodeType.DESTRUCTION_EVENT, new Dimension(FIGURE_DESTRUCTIONEVENT_WIDTH,
            FIGURE_DESTRUCTIONEVENT_HEIGHT));
        this.figureDicationary.put(NodeType.ENUMERATION, new Dimension(FIGURE_ENUMERATION_WIDTH,
            FIGURE_ENUMERATION_HEIGHT));
        this.figureDicationary.put(NodeType.FINAL_STATE, new Dimension(FIGURE_FINALSTATE_WIDTH,
            FIGURE_FINALSTATE_HEIGHT));
        this.figureDicationary.put(NodeType.FORK_NODE, new Dimension(FIGURE_FORKNODE_WIDTH, FIGURE_FORKNODE_HEIGHT));
        this.figureDicationary.put(NodeType.INITIAL_NODE, new Dimension(FIGURE_INITIALNODE_WIDTH,
            FIGURE_INITIALNODE_HEIGHT));
        this.figureDicationary.put(NodeType.INTERACTION_USE, new Dimension(FIGURE_INTERACTIONUSE_WIDTH,
            FIGURE_INTERACTIONUSE_HEIGHT));
        this.figureDicationary.put(NodeType.INTERFACE, new Dimension(FIGURE_INTERFACE_WIDTH, FIGURE_INTERFACE_HEIGHT));
        this.figureDicationary.put(NodeType.JOIN_NODE, new Dimension(FIGURE_JOINNODE_WIDTH, FIGURE_JOINNODE_HEIGHT));
        this.figureDicationary.put(NodeType.LIFELINE, new Dimension(FIGURE_LIFELINE_WIDTH, FIGURE_LIFELINE_HEIGHT));
        this.figureDicationary.put(NodeType.LIFE_LINE_BEHAVIOR, new Dimension(FIGURE_BEHAVIOR_WIDTH,
            FIGURE_BEHAVIOR_HEIGHT));
        this.figureDicationary.put(NodeType.MERGE_NODE, new Dimension(FIGURE_MERGENODE_WIDTH, FIGURE_MERGENODE_HEIGHT));
        this.figureDicationary.put(NodeType.OPAQUE_ACTION, new Dimension(FIGURE_OPAQUEACTION_WIDTH,
            FIGURE_OPAQUEACTION_HEIGHT));
        this.figureDicationary.put(NodeType.PACKAGE, new Dimension(FIGURE_PACKAGE_WIDTH, FIGURE_PACKAGE_HEIGHT));
        this.figureDicationary.put(NodeType.PORT, new Dimension(FIGURE_PORT_WIDTH, FIGURE_PORT_HEIGHT));
        this.figureDicationary.put(NodeType.PROPERTY, new Dimension(FIGURE_PROPERTY_WIDTH, FIGURE_PROPERTY_HEIGHT));
        this.figureDicationary.put(NodeType.STATE_INVARIANT, new Dimension(FIGURE_STATEINVARIANT_WIDTH,
            FIGURE_STATEINVARIANT_HEIGHT));
        this.figureDicationary.put(NodeType.TRANSITION,
            new Dimension(FIGURE_TRANSITION_WIDTH, FIGURE_TRANSITION_HEIGHT));
        this.figureDicationary.put(NodeType.USE_CASE, new Dimension(FIGURE_USECASE_WIDTH, FIGURE_USECASE_HEIGHT));

    }

    /**
     * 
     * 타입으로 사이즈 정보 리턴
     * 
     * @param NodeType
     * @return Dimension
     */
    public static Dimension getFigureDimension(NodeType NodeType) {
        Dimension dimension = eInstance.figureDicationary.get(NodeType);
        if (null == dimension) {
            return new Dimension(FIGURE_WIDTH, FIGURE_HEIGHT);
        } else {
            return dimension;
        }
    }

    /** FIGURE_WIDTH */
    public static final int FIGURE_WIDTH = 160;

    /** FIGURE_HEIGHT */
    public static final int FIGURE_HEIGHT = 70;

    /** Figure ActivityPartition width */
    public static final int FIGURE_VERTICAL_ACTIVITYPARTITION_WIDTH = 200;

//    /** Figure ActivityPartition height */
//    public static final int FIGURE_VERTICAL_ACTIVITYPARTITION_HEIGHT = 500;
//    
//    /** Figure ActivityPartition width */
//    public static final int FIGURE_HORIZONTAL_ACTIVITYPARTITION_WIDTH = 700;

    /** Figure ActivityPartition height */
    public static final int FIGURE_HORIZONTAL_ACTIVITYPARTITION_HEIGHT = 200;

    /** Figure ActivityFinalNode width */
    public static final int FIGURE_ACTIVITYFINALNODE_WIDTH = 30;

    /** Figure ActivityFinalNode height */
    public static final int FIGURE_ACTIVITYFINALNODE_HEIGHT = 30;

    /** Figure Actor width */
    public static final int FIGURE_ACTOR_WIDTH = 60;

    /** Figure Actor height */
    public static final int FIGURE_ACTOR_HEIGHT = 80;

    /** Figure Artifact width */
    public static final int FIGURE_ARTIFACT_WIDTH = 130;

    /** Figure Artifact height */
    public static final int FIGURE_ARTIFACT_HEIGHT = 50;

    /** Figure CentralBufferNode width */
    public static final int FIGURE_CENTRALBUFFERNODE_WIDTH = 150;

    /** Figure CentralBufferNode height */
    public static final int FIGURE_CENTRALBUFFERNODE_HEIGHT = 40;

    /** Figure Class width */
    public static final int FIGURE_CLASS_WIDTH = 150;

    /** Figure Class height */
    public static final int FIGURE_CLASS_HEIGHT = 100;

    /** Figure Collaboration width */
    public static final int FIGURE_COLLABORATION_WIDTH = 100;

    /** Figure Collaboration height */
    public static final int FIGURE_COLLABORATION_HEIGHT = 60;

    /** Figure CollaborationUse width */
    public static final int FIGURE_COLLABORATIONUSE_WIDTH = 200;

    /** Figure CollaborationUse height */
    public static final int FIGURE_COLLABORATIONUSE_HEIGHT = 100;

    /** Figure Component width */
    public static final int FIGURE_COMPONENT_WIDTH = 200;

    /** Figure Component height */
    public static final int FIGURE_COMPONENT_HEIGHT = 200;

    /** Figure DataStoreNode width */
    public static final int FIGURE_DATASTORENODE_WIDTH = 140;

    /** Figure DataStoreNode height */
    public static final int FIGURE_DATASTORENODE_HEIGHT = 40;

    /** Figure DataType width */
    public static final int FIGURE_DATATYPE_WIDTH = 130;

    /** Figure DataType height */
    public static final int FIGURE_DATATYPE_HEIGHT = 50;

    /** Figure DecisionNode width */
    public static final int FIGURE_DECISIONNODE_WIDTH = 60;

    /** Figure DecisionNode height */
    public static final int FIGURE_DECISIONNODE_HEIGHT = 50;

    /** Figure DestructionEvent width */
    public static final int FIGURE_DESTRUCTIONEVENT_WIDTH = 30;

    /** Figure DestructionEvent height */
    public static final int FIGURE_DESTRUCTIONEVENT_HEIGHT = 30;

    /** Figure Enumeration width */
    public static final int FIGURE_ENUMERATION_WIDTH = 150;

    /** Figure Enumeration height */
    public static final int FIGURE_ENUMERATION_HEIGHT = 60;

    /** Figure FinalState width */
    public static final int FIGURE_FINALSTATE_WIDTH = 50;

    /** Figure FinalState height */
    public static final int FIGURE_FINALSTATE_HEIGHT = 50;

    /** Figure ForkNode width */
    public static final int FIGURE_FORKNODE_WIDTH = 100;

    /** Figure ForkNode height */
    public static final int FIGURE_FORKNODE_HEIGHT = 10;

    /** Figure InitialNode width */
    public static final int FIGURE_INITIALNODE_WIDTH = 24;

    /** Figure InitialNode height */
    public static final int FIGURE_INITIALNODE_HEIGHT = 24;

    /** Figure InteractionUse width */
    public static final int FIGURE_INTERACTIONUSE_WIDTH = 200;

    /** Figure InteractionUse height */
    public static final int FIGURE_INTERACTIONUSE_HEIGHT = 100;

    /** Figure Interface width */
    public static final int FIGURE_INTERFACE_WIDTH = 160;

    /** Figure Interface height */
    public static final int FIGURE_INTERFACE_HEIGHT = 62;

    /** Figure JoinNode width */
    public static final int FIGURE_JOINNODE_WIDTH = 100;

    /** Figure JoinNode height */
    public static final int FIGURE_JOINNODE_HEIGHT = 10;

    /** Figure Lifeline width */
    public static final int FIGURE_LIFELINE_WIDTH = 150;

    /** Figure Lifeline_head Height */
    public static final int FIGURE_LIFELINE_HEAD_HEIGHT = 65;

    /** MESSAGE_MAGIN */
    public static final int MESSAGE_MAGIN = 10;

    /** FIGURE_DESTRUCTION_EVENT_SIZE */
    public static final int FIGURE_DESTRUCTION_EVENT_SIZE = 30;

    /** FIGURE_LIFELINE_TOP_MARGIN */
    public static final int FIGURE_LIFELINE_TOP_MARGIN = 20;

    /** Figure Lifeline height */
    public static final int FIGURE_LIFELINE_HEIGHT = 300;

    /** Figure Lifeline width */
    public static final int FIGURE_BEHAVIOR_WIDTH = 10;

    /** Figure Lifeline height */
    public static final int FIGURE_BEHAVIOR_HEIGHT = 60;

    /** Figure MergeNode width */
    public static final int FIGURE_MERGENODE_WIDTH = 60;

    /** Figure MergeNode height */
    public static final int FIGURE_MERGENODE_HEIGHT = 50;

    /** Figure OpaqueAction width */
    public static final int FIGURE_OPAQUEACTION_WIDTH = 140;

    /** Figure OpaqueAction height */
    public static final int FIGURE_OPAQUEACTION_HEIGHT = 40;

    /** Figure Package width */
    public static final int FIGURE_PACKAGE_WIDTH = 120;

    /** Figure Package height */
    public static final int FIGURE_PACKAGE_HEIGHT = 50;

    /** Figure Port width */
    public static final int FIGURE_PORT_WIDTH = 40;

    /** Figure Port height */
    public static final int FIGURE_PORT_HEIGHT = 40;

    /** Figure Property width */
    public static final int FIGURE_PROPERTY_WIDTH = 100;

    /** Figure Property height */
    public static final int FIGURE_PROPERTY_HEIGHT = 40;

    /** Figure StateInvariant width */
    public static final int FIGURE_STATEINVARIANT_WIDTH = 200;

    /** Figure StateInvariant height */
    public static final int FIGURE_STATEINVARIANT_HEIGHT = 100;

    /** Figure Transition width */
    public static final int FIGURE_TRANSITION_WIDTH = 200;

    /** Figure Transition height */
    public static final int FIGURE_TRANSITION_HEIGHT = 100;

    /** Figure UseCase width */
    public static final int FIGURE_USECASE_WIDTH = 100;

    /** Figure UseCase height */
    public static final int FIGURE_USECASE_HEIGHT = 60;

}
