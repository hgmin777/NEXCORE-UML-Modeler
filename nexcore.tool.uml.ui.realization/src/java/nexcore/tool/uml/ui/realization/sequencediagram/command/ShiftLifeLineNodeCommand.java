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

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : ShiftLifeLineNodeCommand</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ShiftLifeLineNodeCommand extends Command {

    /**
     * 현재의 x 좌표
     */
    private int newX;

    /**
     * 이전의 X 좌표
     */
    private int oldX;

    /** viewModel */
    private AbstractNode node;

    /** X좌표로 오름순 정렬된 lifeLineNode 리스트 */
    private List<LifeLineNode> lifeLineNodeList;

    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * ShiftLifeLineNodeCommand
     * @param editPart
     * @param constraint
     * @param lifeLineNodeList
     * @param diagram
     */
    public ShiftLifeLineNodeCommand(EditPart editPart, Rectangle constraint, List<LifeLineNode> lifeLineNodeList,
    Diagram diagram) {
        this.node = (AbstractNode) editPart.getModel();
        this.lifeLineNodeList = lifeLineNodeList;
        this.oldX = node.getX();
        this.newX = constraint.x;
        this.diagram = diagram;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        node.setX(newX);
        setLifeLineLayout();

        if (diagram != null) {
            SequenceUtil.refreshLifeLineOrder(diagram);
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void undo() {
        node.setX(oldX);
        unSetLifeLineLayout();
    }

    /**
     * Move 되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setLifeLineLayout() {

        LifeLineNode lifeLineNode;
        LifeLineNode otherLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (node.equals(lifeLineNode)) {
                for (int k = i + 1; k < lifeLineNodeList.size(); k++) {
                    otherLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    otherLifeLineNode.setX(otherLifeLineNode.getX() + (newX - oldX));
                }
                break;
            }
        }
        
        layoutCombinedFragment(SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList()));
    }

    /**
     * layoutCombinedFragment
     *  
     * @param fragmentNodeList void
     */
    private void layoutCombinedFragment(List<NotationNode> fragmentNodeList) {
        if(fragmentNodeList.size() <= 0) {
            return;
        }
        
        NotationNode fragmentNode;
        
        for (int i = 0; i < fragmentNodeList.size(); i++) {
            fragmentNode =  fragmentNodeList.get(i);
            if(isMoveFragment(fragmentNode)) {
                fragmentNode.setX(fragmentNode.getX() + (newX - oldX));
            } else if(isResizeFragment(fragmentNode)) {
                fragmentNode.setWidth(fragmentNode.getWidth() + (newX - oldX));
                if(fragmentNode.getCompartmentList().size() > 0) {
                    List<NotationNode> list = new ArrayList<NotationNode>();
                    for(AbstractNode abstractNode : fragmentNode.getCompartmentList()) {
                        ContainerNode operandNode =  (ContainerNode) abstractNode;
                        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
                        list.addAll(combindFragmentNodeList);
                    }
                    layoutCombinedFragment(list);
                }
            }
        }
        
    }

    /**
     * isMoveFragment
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isMoveFragment(NotationNode fragmentNode) {
        CombinedFragment combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
        if(combindFragment.getCovereds().contains(node.getUmlModel())) {
            if(isFirtLifelineNodeFromFragment(fragmentNode)) {
                return true;
            }
        } else {
            if(fragmentNode.getX() > node.getX()) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * isResizeFragment
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isResizeFragment(NotationNode fragmentNode) {
        CombinedFragment combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
        if(combindFragment.getCovereds().contains(node.getUmlModel())) {
            if(!isFirtLifelineNodeFromFragment(fragmentNode)) {
                return true;
            }
        }      
        return false;
    }

    /**
     * isFirtLifelineNodeFromFragment
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isFirtLifelineNodeFromFragment(NotationNode fragmentNode) {
        List<LifeLineNode> list = new ArrayList<LifeLineNode>();        
        List<AbstractNode> nodeList = diagram.getNodeList();
        
        CombinedFragment combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
        EList<Lifeline> coveredsLifeline = combindFragment.getCovereds();
        for(Lifeline lifeline : coveredsLifeline) {
            for(AbstractNode abstractNode : nodeList) {
                if(abstractNode instanceof LifeLineNode && lifeline.equals(abstractNode.getUmlModel())) {
                    if(list.size() == 0) {
                        list.add((LifeLineNode) abstractNode);
                    } else {
                        if(list.get(0).getX() > abstractNode.getX()) {
                            list.add(0, (LifeLineNode) abstractNode);
                        } else {
                            list.add((LifeLineNode) abstractNode);
                        }
                    }
                }
            }
        }
        
        if(node.equals(list.get(0))){
            return true;
        }
        
        return false;
    }

    /**
     * Undo된 Move의 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void unSetLifeLineLayout() {

        LifeLineNode lifeLineNode;
        LifeLineNode otherLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (node.equals(lifeLineNode)) {
                for (int k = i + 1; k < lifeLineNodeList.size(); k++) {
                    otherLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    otherLifeLineNode.setX(otherLifeLineNode.getX() - (newX - oldX));
                }

                break;
            }
        }
    }

}
