/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.AddCoveredLifelineDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.action</li>
 * <li>설  명 : AddCoveredLifelineAction</li>
 * <li>작성일 : 2011. 5. 23.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class AddCoveredLifelineAction extends SelectionAction {

    /** Add CoveredLifeline Action ID */
    public static final String ADD_COVERED_LIFELINE_ACTIONID = "AddCoveredLifeline";

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        Object obj = getSelectedObjects().get(0);
        Object element = ((EditPart) obj).getModel();

        AbstractNode selectedView = null;
        
        if (element instanceof NotationNode) {
            selectedView = (NotationNode) element;
            if (selectedView.getUmlModel() instanceof CombinedFragment) {
                return true;
            }
        }

        if (element instanceof ContainerNode) {
            selectedView = (ContainerNode) element;
            if (selectedView.getUmlModel() instanceof InteractionOperand) {
                return true;                
            }
        }
        return false;
    }
  
    /**
     * @param part
     */
    public AddCoveredLifelineAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_ADD_COVERED_LIFELINE));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(ADD_COVERED_LIFELINE_ACTIONID);
        setText(UMLMessage.LABEL_ADD_COVERED_LIFELINE);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        /*
         * DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
         * {
         * 
         * @Override public void execute() { createAttribute(); } });
         */
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    addCoveredLifeline();
                }
            });
    }
   
    /**
     * addCoveredLifeline
     *   void
     */
    private void addCoveredLifeline() {
        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;
        AbstractNode node = (AbstractNode) ((EditPart) obj).getModel();

        NotationNode selectedCombinedFragmentNode = null;
        if (node instanceof NotationNode) {
            selectedCombinedFragmentNode = (NotationNode) node;
        } else if(node instanceof ContainerNode) {
            selectedCombinedFragmentNode = (NotationNode) ((ContainerNode) node).getParent();
        }
        
        CombinedFragment combinedFragment = (CombinedFragment) selectedCombinedFragmentNode.getUmlModel();
        EList<Lifeline> covereds = combinedFragment.getCovereds();
        
        List<Lifeline> unCoveredLifelineList = new ArrayList<Lifeline>();
        HashMap<Lifeline, LifeLineNode> lifeLineNodeMap = new HashMap<Lifeline, LifeLineNode>();
        List<LifeLineNode> lifeLineNodeList = SequenceUtil.getOnlyLifeLineNodeList(SequenceUtil.getDiagram(selectedCombinedFragmentNode).getNodeList());
        if(selectedCombinedFragmentNode.getParent() instanceof ContainerNode) {
            //부모가 ContainerNode이면 부모 CombinedFragment의 Covereds 에서 추가할 라이프라인을 찾는다.
            ContainerNode containerNod = (ContainerNode)selectedCombinedFragmentNode.getParent();
            CombinedFragment parentCombinedFragment = (CombinedFragment) ((NotationNode)containerNod.getParent()).getUmlModel();
            EList<Lifeline> parentCovereds = parentCombinedFragment.getCovereds();
            for(Lifeline lifeline : parentCovereds) {
                if(!covereds.contains(lifeline)) {
                    unCoveredLifelineList.add(lifeline);
                    lifeLineNodeMap.put(lifeline, getLifeLineNode(lifeline, lifeLineNodeList));
                }
            }
        } else {
            //다이어그램에서 추가할 라이프라인을 찾는다.
            for(LifeLineNode lifelineNode : lifeLineNodeList) {
                if(!covereds.contains(lifelineNode.getUmlModel())) {
                    unCoveredLifelineList.add((Lifeline) lifelineNode.getUmlModel());
                    lifeLineNodeMap.put((Lifeline) lifelineNode.getUmlModel(), lifelineNode);
                }
            }
        }
        
        if(unCoveredLifelineList.size() <= 0) {
            //추가할 라이프라인이 없다.
            MessageDialog.openInformation(UiCorePlugin.getShell(), 
                UMLMessage.LABEL_ADD_COVERED_LIFELINE, UMLMessage.LABEL_NO_ADD_COVERED_LIFELINE);
            return;
        }        
        
        AddCoveredLifelineDialog dialog = new AddCoveredLifelineDialog(UiCorePlugin.getShell(), unCoveredLifelineList, false);
        List<Lifeline> addLifelineList = null;
        if (dialog.open() == Window.OK) {
            addLifelineList = dialog.getAddedLifelineList();
        }

        int minX = selectedCombinedFragmentNode.getX();
        int maxX = selectedCombinedFragmentNode.getX() + selectedCombinedFragmentNode.getWidth(); 
        
        if(selectedCombinedFragmentNode.getParent() instanceof ContainerNode) {
            //x 위치 좌표를 다이어그램의 절대값으로 변경한다.
            ContainerNode containerNod = (ContainerNode)selectedCombinedFragmentNode.getParent();
            minX = SequenceUtil.translateToAbsoluteXByParentNode(minX, (NotationNode) containerNod.getParent());
            maxX = SequenceUtil.translateToAbsoluteXByParentNode(maxX, (NotationNode) containerNod.getParent());
        }
        
        if(addLifelineList != null && addLifelineList.size() > 0) {
            //추가할 라이프라인의 영역을 계산해서 CombinedFragmentNode의 x좌표를 계산한다.
            for(Lifeline lifeline : addLifelineList) {
                lifeline.getCoveredBys().add(combinedFragment);
                combinedFragment.getCovereds().add(lifeline);        
                LifeLineNode lifeLineNode = lifeLineNodeMap.get(lifeline);
                if(lifeLineNode != null && minX > lifeLineNode.getX()) {
                    minX = lifeLineNode.getX();
                }
                if(lifeLineNode != null && maxX < lifeLineNode.getX() + lifeLineNode.getWidth()) {
                    maxX = lifeLineNode.getX() + lifeLineNode.getWidth();
                }
            }
            
            if(selectedCombinedFragmentNode.getWidth() < maxX - minX) {
                if(selectedCombinedFragmentNode.getParent() instanceof ContainerNode) {
                    //x 위치 좌표를 부모노드의 상대값으로 변경한다.
                    ContainerNode containerNode = (ContainerNode)selectedCombinedFragmentNode.getParent();
                    maxX = calculateInnerMargin(maxX, (NotationNode) containerNode.getParent());
                }                
                selectedCombinedFragmentNode.setWidth(maxX - minX);
            }
            int x = selectedCombinedFragmentNode.getX();
            if(selectedCombinedFragmentNode.getParent() instanceof ContainerNode) {
                //x 위치 좌표를 부모노드의 상대값으로 변경한다.
                ContainerNode containerNode = (ContainerNode)selectedCombinedFragmentNode.getParent();
                x = SequenceUtil.translateToAbsoluteXByParentNode(x, (NotationNode) containerNode.getParent());
                minX = SequenceUtil.translateToRelativeXByParentNode(minX, containerNode, (NotationNode) containerNode.getParent());
            }
            if(selectedCombinedFragmentNode.getX() > minX) {
                //하위 CombinedFragment가 있는지 검사하고 x값을 보정해준다.
                checkChildOperandX(selectedCombinedFragmentNode.getX() - minX, selectedCombinedFragmentNode);
                selectedCombinedFragmentNode.setX(minX);
            }
        }
    }


   

    /**
     * 부모 노드의 x좌표가 변경되었을 때 자식의 x좌표가 변경되면 안된다. 
     *  
     * @param selectedCombinedFragmentNode void
     */
    private void checkChildOperandX(int xValue, NotationNode combinedFragmentNode) {
        EList<AbstractNode> compartmentList = combinedFragmentNode.getCompartmentList();
        for(AbstractNode childOperandNode : compartmentList) {
            if(childOperandNode instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) childOperandNode;
                List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(containerNode.getNodeList());
                //하위 combinedFragment가 있으면 하위 X좌표를 보정해준다.
                for(NotationNode childCombinedNode : combindFragmentNodeList) {
                    childCombinedNode.setX(childCombinedNode.getX() + xValue);
                }
            }
        }
        
    }
    
    /**
     * UML모델 lifeline으로 Lifeline 모델을 찾아서리턴한다.
     *  
     * @param lifeline
     * @param lifeLineNodeList
     * @return LifeLineNode
     */
    private LifeLineNode getLifeLineNode(Lifeline lifeline, List<LifeLineNode> lifeLineNodeList) {
        for(LifeLineNode node : lifeLineNodeList) {
            if(lifeline.equals(node.getUmlModel())) {
                return node;
            }
        }
        return null;
    }
    
    /**
     * 결합단편은 안쪽에 생기는 결합단편의 마진을 계산한다.
     *  
     * @param minX
     * @param containerNod
     * @param parent
     * @return int
     */
    private int calculateInnerMargin(int x, NotationNode parentCombinedFragmentNode) {
        x = x - UICoreConstant.INNER_MARGIN;
        if(parentCombinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNod = (ContainerNode)parentCombinedFragmentNode.getParent();
            x = calculateInnerMargin(x, (NotationNode) containerNod.getParent());
        }
        return x;
    }
    
}
