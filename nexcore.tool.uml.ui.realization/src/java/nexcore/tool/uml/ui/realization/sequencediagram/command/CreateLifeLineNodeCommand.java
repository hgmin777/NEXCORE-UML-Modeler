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

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : CreateLifeLineNodeCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateLifeLineNodeCommand extends Command {

    /** WIDTH_MINIMUM_SIZE */
    private static final int WIDTH_MINIMUM_SIZE = 100;

    /** HEIGHT_MINIMUM_SIZE */
    private static final int HEIGHT_MINIMUM_SIZE = 300;
    
    /** MARGIN */
    private static final int MARGIN = 20;

    /** request */
    private CreateRequest request;

    /** parentDiagram */
    private Diagram parentDiagram;

    /** LifeLineNode */
    private LifeLineNode lifelineNode;

    /** type */
    private Type type;
    
    /** isCreateType */
    private boolean isCreateType = false;

    /** location */
    private Point location;
    
    /**
     * editPart
     */
    private EditPart editPart;
    
    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     */
    public CreateLifeLineNodeCommand(EditPart parentDiagramEditPart, CreateRequest request, Point location) {
        this.editPart = parentDiagramEditPart;
        this.parentDiagram = (Diagram) editPart.getModel();
        this.request = request;
        this.location = location;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        UiUtil.eraseFeedback(editPart);
        lifelineNode = (LifeLineNode) request.getNewObject();
        
        setLocationAndSize();
        setLifelineUmlProperty();

        //라이프라인이 하나라도 있으면 레이아웃을 해줘야 한다.
        if (parentDiagram.getNodeList().size() > 0) {
            List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(parentDiagram.getNodeList()));
            if (lifeLineNodeList.size() > 0) {
                setLifeLineLayout(lifeLineNodeList);
            } else {
                parentDiagram.getNodeList().add(lifelineNode);
            }
        } else {
            parentDiagram.getNodeList().add(lifelineNode);
        }
        
        //라이프라인 정렬
        SequenceUtil.refreshLifeLineOrder(parentDiagram);
    }
    
    /**
     * 위치와 사이즈를 설정한다.
     *   void
     */
    private void setLocationAndSize() {
        lifelineNode.setX(MARGIN);
        lifelineNode.setY(MARGIN);
        Dimension size = request.getSize();

        //기본 사이즈를 셋팅한다.
        if (size != null) {
            if (size.width > WIDTH_MINIMUM_SIZE) {
                lifelineNode.setWidth(size.width);
            } else {
                lifelineNode.setWidth(WIDTH_MINIMUM_SIZE);
            }
            if (size.height > HEIGHT_MINIMUM_SIZE) {
                lifelineNode.setHeight(size.height);
            } else {
                lifelineNode.setHeight(HEIGHT_MINIMUM_SIZE);
            }
        } else {
            Dimension dimension = FigureConstant.getFigureDimension(lifelineNode.getNodeType());
            lifelineNode.setWidth(dimension.width);
            lifelineNode.setHeight(dimension.height);
        }
    }


    /**
     * 라이프라인의 UML속성을 셋팅한다.
     *   void
     */
    private void setLifelineUmlProperty() {
        Lifeline lifeline = (Lifeline) lifelineNode.getUmlModel();
        PackageableElement parentElement = null;
        parentElement = UMLManager.getParent(this.parentDiagram);
        ((NamedElement) lifeline).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
            ((NamedElement) lifeline).getName()));
        lifelineNode.setName(((NamedElement) lifeline).getName());
      
        StructuredClassifier node;
        Property property;
        Interaction interaction = (Interaction) parentElement;
        interaction.getLifelines().add(lifeline);
        node = (StructuredClassifier) interaction.getOwner();
        property = node.createOwnedAttribute(UMLManager.getPackagedUniqueName(node,
            UMLMessage.getMessage(UMLMessage.LABEL_ATTRIBUTE)), null);
        lifeline.setRepresents(property);
        if (type != null) {
            if (isCreateType) {
                interaction.getNearestPackage().getPackagedElements().add((PackageableElement) type);
            }
            lifeline.getRepresents().setType(type);
        }
       
        lifelineNode.setParent(parentElement);
    }

    

    /**
     * 생성되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setLifeLineLayout(List<LifeLineNode> lifeLineNodeList) {

        LifeLineNode lifeLineNode;
        LifeLineNode othersLifeLineNode;

        AbstractNode lastNode = lifeLineNodeList.get(lifeLineNodeList.size() - 1);
        lifelineNode.setX(lastNode.getX() + lastNode.getWidth() + MARGIN);

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (((lifeLineNode.getX() + lifeLineNode.getWidth() / 2)) > location.x) {

                lifelineNode.setX(lifeLineNode.getX());

                for (int k = i; k < lifeLineNodeList.size(); k++) {
                    othersLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    othersLifeLineNode.setX(othersLifeLineNode.getX() + lifelineNode.getWidth() + MARGIN);
                }

                break;
            }
        }

        parentDiagram.getNodeList().add(lifelineNode);
        layoutCombinedFragment(SequenceUtil.getOnlyCombindFragmentNodeList(parentDiagram.getNodeList()));
        
    }
    
    /**
     * 결합단편 레이아웃을 한다. 
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
            if(isMoveFragment(fragmentNode)) { //결합단편을 이동시킬건지 검사.
                fragmentNode.setX(fragmentNode.getX() + (lifelineNode.getWidth() + MARGIN));
            } else if(isResizeFragment(fragmentNode)) { //결합단편 사이즈를 조정해야하는지 검사.
                fragmentNode.setWidth(fragmentNode.getWidth() + (lifelineNode.getWidth() + MARGIN));
                if(fragmentNode.getCompartmentList().size() > 0) {
                    List<NotationNode> list = new ArrayList<NotationNode>();
                    for(AbstractNode abstractNode : fragmentNode.getCompartmentList()) {
                        ContainerNode operandNode =  (ContainerNode) abstractNode;
                        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
                        list.addAll(combindFragmentNodeList);
                    }
                    //operand에 자식 결합단편이 있으면 같이 레이아웃한다.
                    layoutCombinedFragment(list);
                }
            }
        }
        
    }

    /**
     * 새로 생성된 라이프라인이 fragment보다 왼편에 있으면 fragment를 이동시킨다. 
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isMoveFragment(NotationNode fragmentNode) {
        int fragmentX = fragmentNode.getX();
        if(fragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode parentContainerNode = (ContainerNode) fragmentNode.getParent();
            fragmentX = SequenceUtil.translateToAbsoluteXByParentNode(fragmentX, (NotationNode) parentContainerNode.getParent());
        }
        if(fragmentX >= lifelineNode.getX()) {
            return true;
        }
        
        return false;
    }

    /**
     * 결합단편 사이즈를 늘려야 하는지 검사
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isResizeFragment(NotationNode fragmentNode) {
        int fragmentX = fragmentNode.getX();
        if(fragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode parentContainerNode = (ContainerNode) fragmentNode.getParent();
            fragmentX = SequenceUtil.translateToAbsoluteXByParentNode(fragmentX, (NotationNode) parentContainerNode.getParent());
        }
        if(lifelineNode.getX() >= fragmentX
            && lifelineNode.getX() <=  fragmentX +  fragmentNode.getWidth()) {
            return true;
        }
        return false;
    }

    
    /**
     * 결합단편 내에서(Coverds)에서 가장 왼편의 라이프라인지 검사한다.  
     *  
     * @param fragmentNode
     * @return boolean
     */
    private boolean isFirtLifelineNodeFromFragment(NotationNode fragmentNode) {
        List<LifeLineNode> list = new ArrayList<LifeLineNode>();        
        List<AbstractNode> nodeList = parentDiagram.getNodeList();
        
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
        
        if(lifelineNode.equals(list.get(0))){
            return true;
        }
        
        return false;
    }


    /**
     * getLifeLifeNode
     *  
     * @return LifeLineNode
     */
    public LifeLineNode getLifeLifeNode() {
        return (LifeLineNode) lifelineNode;
    }

    /**
     * setType
     *  
     * @param type void
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * getParentNodeModel
     *  
     * @return AbstractNode
     */
    public AbstractNode getParentNodeModel() {
        return parentDiagram;
    }

    /**
     * setCreateType
     *  
     * @param isCreateType void
     */
    public void setCreateType(boolean isCreateType) {
        this.isCreateType = isCreateType;
    }

}
