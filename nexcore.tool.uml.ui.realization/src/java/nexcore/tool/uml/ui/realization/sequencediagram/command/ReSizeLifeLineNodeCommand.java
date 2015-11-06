/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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
 * <li>설 명 : ReSizeLifeLineNodeCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ReSizeLifeLineNodeCommand extends Command {

    /**
     * 현재의 constraint
     */
    private Rectangle newConstraint;

    /**
     * 이전의 constraint
     */
    private Rectangle oldConstraint;

    /** diagram */
    private Diagram diagram;

    /** viewModel */
    private AbstractNode node;

    /**
     * ReSizeLifeLineNodeCommand
     * @param editPart
     * @param constraint
     */
    public ReSizeLifeLineNodeCommand(EditPart editPart, Object constraint) {
        this.node = (AbstractNode) editPart.getModel();
        oldConstraint = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
        if (constraint instanceof Rectangle) {
            this.newConstraint = (Rectangle) constraint;
        }
        diagram = (Diagram) editPart.getParent().getModel();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        node.setWidth(newConstraint.width);
        node.setHeight(newConstraint.height);
        setLifeLineLayout();
        
        // 라이프라인에 걸쳐있는 프래그먼트가 있다면 라이프라인의 사이즈 변경에 따라 사이즈를 같이 바꿔준다.
        setFragmentsSize();
    }

    /**
     * setFragmentsSize
     *   void
     */
    private void setFragmentsSize() {
        List<NotationNode> fragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(fragmentNodeList.size() <= 0) {
            return;
        }        
        NotationNode fragmentNode;
        CombinedFragment combindFragment;
        for (int i = 0; i < fragmentNodeList.size(); i++) {
            fragmentNode =  fragmentNodeList.get(i);
            combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
            if(combindFragment.getCovereds().contains(node.getUmlModel()) && newConstraint.x != oldConstraint.x) {
                fragmentNode.setX( node.getX() );
                fragmentNode.setWidth( fragmentNode.getWidth() + newConstraint.width - oldConstraint.width );
            }            
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void undo() {
        node.setWidth(oldConstraint.width);
        node.setHeight(oldConstraint.height);
        reSetLifeLineLayout();
    }

    /**
     * reSize되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setLifeLineLayout() {
        List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList()));

        LifeLineNode lifeLineNode;
        LifeLineNode othersLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (lifeLineNode.getX() > node.getX()) {
                for (int k = i; k < lifeLineNodeList.size(); k++) {
                    othersLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    othersLifeLineNode.setX(othersLifeLineNode.getX() + (newConstraint.width - oldConstraint.width));
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
                CombinedFragment combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
                if(newConstraint.x == oldConstraint.x && combindFragment.getCovereds().contains(node.getUmlModel())) {
                    if( newConstraint.width != oldConstraint.width ) {
                        fragmentNode.setWidth(fragmentNode.getWidth() + (newConstraint.width - oldConstraint.width));
                        if(fragmentNode.getCompartmentList().size() > 0) {
                            List<NotationNode> list = new ArrayList<NotationNode>();
                            for(AbstractNode abstractNode : fragmentNode.getCompartmentList()) {
                                ContainerNode operandNode =  (ContainerNode) abstractNode;
                                List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
                                list.addAll(combindFragmentNodeList);
                            }
                            layoutCombinedFragment(list);
                        }
                    } else {
                        fragmentNode.setX(fragmentNode.getX() + (newConstraint.width - oldConstraint.width));
                    }
                } else if( newConstraint.width != oldConstraint.width && !combindFragment.getCovereds().contains(node.getUmlModel()) ) {
                    fragmentNode.setX(fragmentNode.getX() + (newConstraint.width - oldConstraint.width));
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
            } else if(isResizeFragment(fragmentNode) || newConstraint.x == oldConstraint.x ) {
                CombinedFragment combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
                if( combindFragment.getCovereds().contains(node.getUmlModel()) ) {
                    fragmentNode.setWidth(fragmentNode.getWidth() + (newConstraint.width - oldConstraint.width));
                }
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
     * Undo된 reSize되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void reSetLifeLineLayout() {
        List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList()));

        LifeLineNode lifeLineNode;
        LifeLineNode othersLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (lifeLineNode.getX() > node.getX()) {
                for (int k = i; k < lifeLineNodeList.size(); k++) {
                    othersLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    othersLifeLineNode.setX(othersLifeLineNode.getX() - (newConstraint.width - oldConstraint.width));
                }

                break;
            }
        }
    }

}
