/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : DeleteLifeLineCommand</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DeleteLifeLineCommand extends Command {

    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * 삭제할 노드
     */
    private LifeLineNode lifeLineNode;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        try {
            deleteBehaviorList();
            deleteLine();
            deleteCoveredBysCombinedFragment();

            Lifeline lifeline = (Lifeline) lifeLineNode.getUmlModel();
            Interaction interaction = (Interaction) lifeline.eContainer();
            ConnectableElement connectableElement = lifeline.getRepresents();
            Collaboration collaboration = (Collaboration) interaction.eContainer();
            collaboration.getOwnedAttributes().remove(connectableElement);

            diagram.getNodeList().remove(lifeLineNode);
            UMLManager.deleteElement(lifeLineNode);
            interaction.getLifelines().remove(lifeline);
            UMLManager.deleteElement(lifeline);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * deleteCoveredBysCombinedFragment
     * 
     * void
     */
    private void deleteCoveredBysCombinedFragment() {

        Lifeline lifeline = (Lifeline) lifeLineNode.getUmlModel();
        EList<InteractionFragment> coveredBys = lifeline.getCoveredBys();
        if(!SequenceUtil.coveredBysHasCombinedFragment(coveredBys)) {
            return;
        }
        Object[] coveredByArray = coveredBys.toArray();
        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(Object fragment : coveredByArray) {
            if(fragment instanceof CombinedFragment) {
                CombinedFragment combinedFragment = (CombinedFragment) fragment;
                NotationNode combinedFragmentNode = SequenceUtil.getCombinedFragmentNode(combindFragmentNodeList, combinedFragment);
                if(combinedFragmentNode == null) {
                    continue;
                }
                SequenceUtil.removeCoveredLifeline(diagram, combinedFragmentNode, lifeline); 
                SequenceUtil.calculateCoverdsLocationAndSize(diagram, combinedFragmentNode, combinedFragment);
            }
        }
        
    }

    /**
     * deleteLine
     * 
     * void
     */
    private void deleteLine() {
        AbstractConnection connection;
        Line line = lifeLineNode.getLine();
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        if (line.getIncomingConnectionList().size() > 0) {
            list.addAll(line.getIncomingConnectionList());
        }

        if (line.getOutgoingConnectionList().size() > 0) {
            list.addAll(line.getOutgoingConnectionList());
        }

        for (int i = 0; i < list.size(); i++) {
            connection = list.get(i);
            if (connection instanceof Attachement) {
                DiagramUtil.detachSourceOfConnection(connection);
                DiagramUtil.detachTargetOfConnection(connection);
                diagram.getConnectionList().remove(connection);
            } else {
                SequenceUtil.deleteUmlMessage(connection);
                SequenceUtil.deleteMessage(connection, diagram);
            }
        }
    }

    /**
     * deleteBehaviorList
     * 
     * void
     */
    private void deleteBehaviorList() {
        AbstractConnection connection;
        List<NotationNode> behaviorList = lifeLineNode.getBehaviorList();
        List<NotationNode> deleteBehaviorList = new ArrayList<NotationNode>();
        deleteBehaviorList.addAll(behaviorList);
        NotationNode behaviorNode;
        List<AbstractConnection> list;
        for (int k = 0; k < deleteBehaviorList.size(); k++) {
            behaviorNode = deleteBehaviorList.get(k);
            list = new ArrayList<AbstractConnection>();

            if (behaviorNode.getIncomingConnectionList().size() > 0) {
                list.addAll(behaviorNode.getIncomingConnectionList());
            }

            if (behaviorNode.getOutgoingConnectionList().size() > 0) {
                list.addAll(behaviorNode.getOutgoingConnectionList());
            }

            for (int i = 0; i < list.size(); i++) {
                connection = list.get(i);
                if (connection instanceof Attachement) {
                    DiagramUtil.detachSourceOfConnection(connection);
                    DiagramUtil.detachTargetOfConnection(connection);
                    diagram.getConnectionList().remove(connection);
                } else {
                    SequenceUtil.deleteUmlMessage(connection);
                    SequenceUtil.deleteMessage(connection, diagram);
                }
            }
        }
    }

    /**
     * @return the diagram
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * 삭제 액션 Diagram 설정
     * 
     * @param model
     *            void
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 삭제할 node 반환
     * 
     * @return the node
     */
    public LifeLineNode getLifeLineNode() {
        return lifeLineNode;
    }

    /**
     * 삭제될 node 저장.
     * 
     * @param model
     *            void
     */
    public void setLifeLineNode(LifeLineNode lifeLineNode) {
        this.lifeLineNode = lifeLineNode;
    }
}
