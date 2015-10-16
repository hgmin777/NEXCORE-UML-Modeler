/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.AddCoveredLifelineByMessageDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.AddCoveredLifelineDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : CreateCombinedFragmentNodeCommand</li>
 * <li>작성일 : 2011. 4. 7.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateCombinedFragmentNodeCommand extends Command {

    /** HEIGHT_MINIMUM_SIZE */
    public static final int HEIGHT_MINIMUM_SIZE = 100;
    
    /** if(...) */
    private static final String CONSTRAINT_IF = "if(...)"; //$NON-NLS-1$
    
    /** else */
    private static final String CONSTRAINT_ELSE = "else"; //$NON-NLS-1$

    /** case */
    private static final String CONSTRAINT_CASE = "case :"; //$NON-NLS-1$

    /** default */
    private static final String CONSTRAINT_DEFAULT = "default :"; //$NON-NLS-1$

    /** while */
    private static final String CONSTRAINT_WHILE = "while(...)"; //$NON-NLS-1$

    /** for */
    private static final String CONSTRAINT_FOR = "for(...)"; //$NON-NLS-1$

    /** request */
    private CreateRequest request;
    
    /**
     * diagram
     */
    private Diagram diagram;

    /** parentNodeModel */
    private AbstractNode parentNodeModel;

    /** notationNode */
    private NotationNode notationNode;
    
    /**
     * addedLifelineList
     */
    private List<Lifeline> addedLifelineList;    

    /**
     * addedConnectionList
     */
    private List<AbstractConnection> addedConnectionList;    

    /**
     * addedEntryList
     */
    private List<Entry<Lifeline, AbstractConnection>> addedEntryList;
    
    /**
     * rangedLifeLineNodeList
     */
    private List<LifeLineNode> rangedLifeLineNodeList;    
    
    /**
     * rangedLifelineNodeMap
     */
    private HashMap<Lifeline, LifeLineNode> rangedLifelineNodeMap = new HashMap<Lifeline, LifeLineNode>();    

    /**
     * rangedConnectionList
     */
    private List<AbstractConnection> rangedConnectionList;
    
    /**
     * unRangedConnectionList
     */
    private List<AbstractConnection> unRangedConnectionList;
    
    /**
     * fragmentX
     */
    private int fragmentX = 0;
    
    /**
     * fragmentWidth
     */
    private int fragmentWidth = 0;

    /**
     * firstOperandHeight
     */
    private int firstOperandHeight = 0;
        
    /** location */
    private Rectangle createBounds;
    
    /** size */
    private Dimension size;
    
    /**
     * editPart
     */
    private EditPart editPart;
    
    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     */
    public CreateCombinedFragmentNodeCommand(EditPart parentNodeEditPart, CreateRequest request, Rectangle createBounds) {
        this.editPart = parentNodeEditPart;
        this.parentNodeModel = (AbstractNode) editPart.getModel();
        this.request = request;
        this.createBounds = createBounds;
        this.size = request.getSize(); 
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        UiUtil.eraseFeedback(editPart);
        try {
            
            if (parentNodeModel instanceof Diagram) {
                diagram = (Diagram) parentNodeModel;;
            } else if (parentNodeModel instanceof ContainerNode) {
                diagram = SequenceUtil.getDiagram(parentNodeModel);
            }

            addCoveredLifeline();
            if(addedLifelineList == null) {
                return;
            }
            addCoveredMessage();
            //CombinedFragment에 추가 될 라이프라인이 없다면 생성 할 수 없다.
            if(addedLifelineList != null && addedLifelineList.size() > 0) {            
                notationNode = (NotationNode) request.getNewObject();
               
                CombinedFragment combinedFragment = (CombinedFragment) notationNode.getUmlModel();
                PackageableElement parentElement = null;

                initFragmentXandWidth();
                notationNode.setX(fragmentX);
                notationNode.setY(createBounds.y);
                notationNode.setWidth(fragmentWidth);
                notationNode.setHeight(createBounds.height);
                setOperands();
    
                if (combinedFragment instanceof NamedElement) {
                    notationNode.setName(((NamedElement) combinedFragment).getName());
                }
    
                if (parentNodeModel instanceof Diagram) {
                    parentElement = UMLManager.getParent(this.parentNodeModel);
                    Interaction interaction = (Interaction) parentElement;
                    interaction.getFragments().add(combinedFragment);
                    
                    for( AbstractNode child : ((Diagram) parentNodeModel).getNodeList() ) {
                        if( child instanceof LifeLineNode ) {
                            child.setHeight( child.getHeight() + notationNode.getHeight() );
                        }
                    }
                } else if (parentNodeModel instanceof ContainerNode) {
                    InteractionOperand operand = (InteractionOperand) this.parentNodeModel.getUmlModel();
                    operand.getFragments().add((InteractionFragment) combinedFragment);
                    NotationNode parentNode = (NotationNode) parentNodeModel.getParent();
                    //x,y위치 좌표를 부모노드의 상대값으로 변경한다.
                    translateToRelativeLocationByParentFragmentNode(notationNode, (ContainerNode) parentNodeModel, parentNode);

                    setParentNodeSize(parentNode);
                    
                    for( AbstractNode child : ((ContainerNode) parentNodeModel).getNodeList() ) {
                        if( child instanceof LifeLineNode ) {
                            child.setHeight( child.getHeight() + notationNode.getHeight() );
                        }
                    }
                }

                for(Lifeline lifeline : addedLifelineList) {
                    lifeline.getCoveredBys().add(combinedFragment);      
                    combinedFragment.getCovereds().add(lifeline);           
                }
                
                notationNode.setParent(parentNodeModel);
                // 20120608 by kang
                //TODO CombinedFragment 내부에 CombinedFragment 생성 시, 하위 요소들을 모두 아래로 이동하도록 처리 필요.
                if( parentNodeModel instanceof ContainerNode ) {
                    // 여기에 시프트하는 로직이 필요해...
                	// 아래 if-else를 지우고 여기에 내용 채우기 필요.
                }
                
                if (parentNodeModel instanceof Diagram) {
                    ((Diagram) parentNodeModel).getNodeList().add(notationNode);
//                    SequenceUtil.shiftByCreatedCombinedFragment(diagram, notationNode, addedConnectionList);
                } else if (parentNodeModel instanceof ContainerNode) {
                    ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
//                    SequenceUtil.shiftByCreatedInnerCombinedFragment(diagram, notationNode, addedConnectionList);
                }
                
                SequenceUtil.shiftByNewInputBounds(notationNode);
                SequenceUtil.setAllLifeLineHeight(diagram);
                
                List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
                
                if( !(notationNode.getParent() instanceof Diagram) ) {
                    int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) notationNode.getParent(), notationNode);
                    for (NotationNode abstractNode : allFragments) {
                        // 프래그먼트 하위요소가 아니면 넘어감.
                        if( !(abstractNode.getParent() instanceof ContainerNode) ) {
                            continue;
                        } 
                        // 같은 부모를 가지고 있는지 확인. 
                        if( !notationNode.equals(SequenceUtil.getAncestor(abstractNode))) {
//                        continue;
                        }
                        if( notationNode.getCompartmentList().contains(abstractNode.getParent()) ) {
                            continue;
                        }
                        int absoluteNodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) abstractNode.getParent(), abstractNode);
                        if (absoluteY < absoluteNodeY && absoluteY + notationNode.getHeight() < absoluteNodeY ) {
                            abstractNode.setY(abstractNode.getY() + notationNode.getHeight());
                        } else if (absoluteY + notationNode.getHeight() < absoluteNodeY) {
                            abstractNode.setY(abstractNode.getY() + notationNode.getHeight());
                        }
                    }
                }
                
//                setLocationAndSize();
                notationNode.setHeight(notationNode.getHeight() > createBounds.height ? notationNode.getHeight() : createBounds.height);
                int childHeight = notationNode.getHeight() - UICoreConstant.FRAGMENT_LABEL_HEIGHT;
                int cHeight = childHeight/notationNode.getCompartmentList().size();
                for( AbstractNode child : notationNode.getCompartmentList() ) {
                    child.setHeight(cHeight);
                }
                
                SequenceUtil.refreshUmlLifelineCoveredBysOrder(addedLifelineList, notationNode, combinedFragment);
                                
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    
    //미완성 by 경구(206라인 관련)
    /**
     * shiftCombinedFragment
     *  
     * @param node
     * @param delta void
     */
    private void shiftCombinedFragment(AbstractNode node, int delta) {
        ContainerNode operandNode = (ContainerNode) node;
        int oldHeight = operandNode.getHeight();
        int heightDelta = delta;
        AbstractNode parentNode = (AbstractNode) node.getParent();
        
        int operandY = SequenceUtil.getOperandY((NotationNode) parentNode, operandNode) + operandNode.getHeight();
        Diagram diagram = SequenceUtil.getDiagram(parentNode);
        if(operandNode.getHeight() + heightDelta >= UICoreConstant.OPERAND_MIN_HEIGHT) {
            operandNode.setHeight(operandNode.getHeight() + heightDelta);
            parentNode.setHeight(parentNode.getHeight() + heightDelta);           
            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode,  (NotationNode) parentNode, operandY, heightDelta);
        } else {
            operandNode.setHeight(UICoreConstant.OPERAND_MIN_HEIGHT);
            parentNode.setHeight(parentNode.getHeight() + heightDelta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + heightDelta)));              
            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode, (NotationNode) parentNode, operandY, heightDelta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + heightDelta)));
        }
        
        if(heightDelta > 0) { //결합단편 높이가 늘어나면 라이프라인의 높이도 늘린다.
            SequenceUtil.shiftLifeLinesHeight(diagram, heightDelta);
        }
    }
    
    /**
     * setParentNodeSize
     *  
     * @param parentNode void
     */
    private void setParentNodeSize(AbstractNode parentNode) {
        List<AbstractNode> parentnodeList = new ArrayList<AbstractNode>();

        parentnodeList = SequenceUtil.getParentNode(parentnodeList, parentNode);// getParentNode(parentnodeList, parentNode);
        
        for( int i = parentnodeList.size() - 1; i >= 0; i--) {
            
            AbstractNode nNode = (AbstractNode) parentnodeList.get(i);
            nNode.setHeight(nNode.getHeight() + notationNode.getHeight());
            
//            for( AbstractNode child : nNode.getCompartmentList() ) {
//                child.setHeight( child.getHeight() + notationNode.getHeight() ) ;
//            }
        }
        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
            parentNodeModel.setHeight(parentNodeModel.getHeight() + notationNode.getHeight());
        }
    }

    
    private List<AbstractNode> getParentNode(List<AbstractNode> parentnodeList, AbstractNode parentNode) {

        if (parentNode instanceof NotationNode) {
            NotationNode nNode = ((NotationNode) parentNode);

            if( !parentnodeList.contains(nNode) ) {
                parentnodeList.add(nNode);
            }

            if ((nNode.getParent() instanceof Diagram)) {
                return parentnodeList;
            }
            if (null != nNode.getParent() && nNode.getParent() instanceof ContainerNode) {
                getParentNode(parentnodeList, (ContainerNode) parentNode.getParent());
            }

        } else if (parentNode instanceof ContainerNode) {
            ContainerNode cNode = (ContainerNode) parentNode;
            EObject parent = cNode.getParent();
            if (parent instanceof NotationNode && ((NotationNode) parent).getUmlModel() instanceof CombinedFragment) {
                getParentNode(parentnodeList, (AbstractNode) parent);
            }
        }

        if ((parentNode.getParent() instanceof Diagram)) {
            return parentnodeList;
        }
        if (null != parentNode.getParent() && parentNode.getParent() instanceof NotationNode) {
            getParentNode(parentnodeList, (NotationNode) parentNode.getParent());
        }

        return parentnodeList;
    }
    
    
    
    /**
     * 위치 값을 를 부모 CmbinedFragment 노드의 상대값으로 변환한다.
     *  
     * @param x
     * @param parentCombinedFragmentNode
     * @return void
     */
    private void translateToRelativeLocationByParentFragmentNode(NotationNode fragmentNode, ContainerNode parentNode, NotationNode parentCombinedFragmentNode) {
        int relativeX = fragmentNode.getX() - parentCombinedFragmentNode.getX() + UICoreConstant.INNER_MARGIN; //위치 계층을 주기위해 마진을 준다.
        fragmentNode.setX(relativeX);
        
        int relativeY = fragmentNode.getY() - parentCombinedFragmentNode.getY() - UICoreConstant.FRAGMENT_LABEL_HEIGHT; 
        for(AbstractNode node : parentCombinedFragmentNode.getCompartmentList()) {
            if(node.equals(parentNode)) {
                break;
            }
            relativeY -= node.getHeight();
        }
        fragmentNode.setY(relativeY);
        
        int width = fragmentNode.getWidth() - UICoreConstant.INNER_MARGIN *2; 
        fragmentNode.setWidth(width);
        
        if(parentCombinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentCombinedFragmentNode.getParent();
            translateToRelativeLocationByParentFragmentNode(fragmentNode, containerNode, (NotationNode) containerNode.getParent());
        }
        
    }
    
    /**
     * 생성될 결합단편이 포함할 lifeline을 선택한다.
     * 
     *   void
     */
    private void addCoveredLifeline() {
        
        rangedLifeLineNodeList = getRangedLifeLineNodeList(); 
        
        List<Lifeline> lifelineList = new ArrayList<Lifeline>();
        for(LifeLineNode node : rangedLifeLineNodeList) {
            lifelineList.add((Lifeline) node.getUmlModel());
        }
        
        if(lifelineList.size() <= 0) {
            return;
        }
        
        AddCoveredLifelineDialog dialog = new AddCoveredLifelineDialog(UiCorePlugin.getShell(), lifelineList, true);
        if (dialog.open() == Window.OK) {
            addedLifelineList = dialog.getAddedLifelineList();
        }
    }

    /**
     * ombinedFragment가 생성될 영역 안에 있는 라이프라인 노드 리스트를 찾는다.
     *  
     * @return List<LifeLineNode>
     */
    private List<LifeLineNode> getRangedLifeLineNodeList() {
        List<LifeLineNode> rangedLifeLineNodeList = new ArrayList<LifeLineNode>();
        List<LifeLineNode> allLifeLineNodeList =SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());  
        
        if(size == null) {
            return rangedLifeLineNodeList;
        } else {
            //라이프라인 범위 계산할때는 절대좌표를 사용하기 때문에 request값이 아닌
            //별도의 location과 size를 사용한다.
            int startRangeX = createBounds.x;
            int endRangeX = startRangeX + size.width;
            
            for(LifeLineNode node : allLifeLineNodeList) {
                if(isRanged(startRangeX, endRangeX, node)) {
                    rangedLifeLineNodeList.add(node);
                    rangedLifelineNodeMap.put((Lifeline) node.getUmlModel(), node);
                }
            }     
        }
        return rangedLifeLineNodeList;
    }
        
    /**
     * isRanged
     *  
     * @param startRangeX
     * @param endRangeX
     * @param node
     * @return boolean
     */
    private boolean isRanged(int startRangeX, int endRangeX, LifeLineNode node) {
        if(node.getX() < startRangeX && node.getX() + node.getWidth() > startRangeX) {
            return true;
        }
        if(node.getX() > startRangeX && node.getX() + node.getWidth() < endRangeX) {
            return true;
        }
        if(node.getX() < endRangeX && node.getX() + node.getWidth() > endRangeX) {
            return true;
        }
        
        return false;
    }    
    
    /**
     * CombinedFragment가 생성될 영역에 포함하는 메시지를 찾는다.
     * 
     *   void
     */
    private void addCoveredMessage() {
        rangedConnectionList = getRangedConnectionList();
        unRangedConnectionList = new ArrayList<AbstractConnection>();
        addedConnectionList = new ArrayList<AbstractConnection>();
        // rangedConnectionList안의 메시지 중에서 소스 및 타겟 라이프라인이 combinedFragment의
        // covered가 아닌경우에 누락된 라이프라인으로 선정하고 추가하기 위해 lifelineMap을 구성한다.
        HashMap<Lifeline, AbstractConnection> lifelineMap = new HashMap<Lifeline, AbstractConnection>();
        Lifeline lifeline;
        for(AbstractConnection connection : rangedConnectionList) {
            lifeline = (Lifeline) SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource()).getUmlModel();
            boolean isAdd = true;
            if(!addedLifelineList.contains(lifeline)) {
                if(!lifelineMap.containsKey((lifeline))) {
                    lifelineMap.put(lifeline, connection);
                }
                if(!unRangedConnectionList.contains(connection)) {
                    unRangedConnectionList.add(connection);
                }
                isAdd = false;
            }
            lifeline = (Lifeline) SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget()).getUmlModel();
            if(!addedLifelineList.contains(lifeline)) {
                if(!lifelineMap.containsKey((lifeline))) {
                    lifelineMap.put(lifeline, connection);
                }
                if(!unRangedConnectionList.contains(connection)) {
                    unRangedConnectionList.add(connection);
                }
                isAdd = false;
            }
            if(isAdd && !addedConnectionList.contains(connection)) {
                addedConnectionList.add(connection);                    
            }
        }
        
        Diagram diagram;
        if(parentNodeModel instanceof Diagram) {
            diagram = (Diagram) parentNodeModel;                
        } else {
            diagram = SequenceUtil.getDiagram(parentNodeModel);                          
        }
        
        //lifelineMap empty가 아니면 누락된 라이프라인이 있다.
        if(lifelineMap.size() > 0) {
            AddCoveredLifelineByMessageDialog dialog = new AddCoveredLifelineByMessageDialog(UiCorePlugin.getShell(), lifelineMap, diagram);
            if (dialog.open() == Window.OK) {
                addedEntryList = dialog.getAddedEntryList();
            }
        }
        
        if(addedEntryList != null && addedEntryList.size() > 0) {
            for(Entry<Lifeline, AbstractConnection> entry : addedEntryList) {
                if(!addedLifelineList.contains(entry.getKey())) {
                    addedLifelineList.add(entry.getKey());                    
                }
                if(!addedConnectionList.contains(entry.getValue())) {
                    addedConnectionList.add(entry.getValue());                    
                }
                unRangedConnectionList.remove(entry.getValue());
            }
        }        
    }


    /**
     * combiindeFragment 영역안에 있는 메시지를 찾는다.
     *  
     * @return List<AbstractConnection>
     */
    private List<AbstractConnection> getRangedConnectionList() {
        List<AbstractConnection> rangedConnectionList = new ArrayList<AbstractConnection>();
        
        LifeLineNode lifeLineNode;
        for(int i = 0; i <rangedLifeLineNodeList.size(); i++){
            lifeLineNode = rangedLifeLineNodeList.get(i);
            addRangedConnectionList(rangedConnectionList, lifeLineNode.getLine().getIncomingConnectionList());
            addRangedConnectionList(rangedConnectionList, lifeLineNode.getLine().getOutgoingConnectionList());
        }
        
        return rangedConnectionList;
    }

    /**
     * combiindeFragment 영역안에 메시지를 rangedConnectionList에 추가한다.
     *  
     * @param rangedConnectionList
     * @param connectionList void
     */
    private void addRangedConnectionList(List<AbstractConnection> rangedConnectionList, EList<AbstractConnection> connectionList) {
        for(AbstractConnection connection : connectionList) {
            if(rangedLifeLineNodeList.contains(SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource())) 
                && rangedLifeLineNodeList.contains(SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget()))
                && connection.getY() > createBounds.y
                && connection.getY() <  createBounds.y + size.height) {
                if(!isSameLevelConnection(connection)) {
                    continue;
                }
                rangedConnectionList.add(connection);
                if(!RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                    NotationNode behviorNode = (NotationNode) connection.getTarget();
                    List<AbstractConnection> replyMessageList = getReplayMessages(behviorNode);
                    for(AbstractConnection childReplyMessage : replyMessageList) {
                        rangedConnectionList.add(childReplyMessage);
                    }
                }
            }
        }
        
    }
    
    
    /**
     * CombinedFragment가 포하려는 영역에 포함된 메시지가 같은 같은 레벨에 있는지 검사한다.
     * 
     * 예를 들면 
     * CombinedFragment안에 서브 CombinedFragment생성 시 포함되는 메시지는 부모 CombinedFragment의 메시지여만 한다.
     * 최상위 레벨에서 생성하는 CombinedFragment가 다른 CombinedFragment 영역에 있는 메시지를 포함한 경우는 안된다.
     * 
     *  
     * @param connection
     * @return boolean
     */
    private boolean isSameLevelConnection(AbstractConnection connection) {
        
        if(parentNodeModel instanceof ContainerNode) {
            if(SequenceUtil.isIncludedOperand(connection)) {
                Message message = (Message) connection.getUmlModel();
                MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
                ContainerNode interactionOperandNode = SequenceUtil.getInteractionOperandNode(diagram, (InteractionOperand) sendEvent.eContainer());
                if(!parentNodeModel.equals(interactionOperandNode)) {
                    return false;
                }                
            } else {
                return false;
            }
        } else {
            if(SequenceUtil.isIncludedOperand(connection)) {
                return false;
            }
        }
        
        
        return true;
    }

    /**
     * behviorNode 의 reply메시지를 찾아서 리턴한다.
     *  
     * @param behviorNode
     * @return List<AbstractConnection>
     */
    private List<AbstractConnection> getReplayMessages(NotationNode behviorNode) {
        List<AbstractConnection> replyMessageList = new ArrayList<AbstractConnection>();
        
        for(AbstractConnection connection : behviorNode.getOutgoingConnectionList()) {
            if(RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                replyMessageList.add(connection);
            }
        }
        return replyMessageList;
    }

    /**
     * Uml Operand 정보를 셋팅한다.
     * 
     *   void
     */
    private void setOperands() {
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {                    
                if(NodeType.OPTION_IF.equals(notationNode.getNodeType())) {
                    addInteractionOperandNode(notationNode, CONSTRAINT_IF);
                } else  if(NodeType.ALTERNATIVE_IF_ELSE.equals(notationNode.getNodeType())) {
                    addInteractionOperandNode(notationNode, CONSTRAINT_IF, firstOperandHeight);
                    notationNode.setHeight(notationNode.getHeight() + UICoreConstant.OPERAND_MIN_HEIGHT);
                    addInteractionOperandNode(notationNode, CONSTRAINT_ELSE, UICoreConstant.OPERAND_MIN_HEIGHT);
                } else  if(NodeType.ALTERNATIVE_SWITCH.equals(notationNode.getNodeType())) {
                    addInteractionOperandNode(notationNode, CONSTRAINT_CASE, firstOperandHeight);
                    notationNode.setHeight(notationNode.getHeight() + UICoreConstant. OPERAND_MIN_HEIGHT);
                    addInteractionOperandNode(notationNode, CONSTRAINT_DEFAULT, UICoreConstant.OPERAND_MIN_HEIGHT);
                } else  if(NodeType.LOOP_WHILE.equals(notationNode.getNodeType())) {
                    addInteractionOperandNode(notationNode, CONSTRAINT_WHILE);
                } else  if(NodeType.LOOP_FOR.equals(notationNode.getNodeType())) {
                    addInteractionOperandNode(notationNode, CONSTRAINT_FOR);
                }                
                
                setMessageFragments(notationNode);
                
            }
        });
    }

    /**
     * addInteractionOperandNode
     *  
     * @param combinedFragementNode void
     */
    private void addInteractionOperandNode(NotationNode combinedFragementNode, String constraintString){
        
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragementNode.getUmlModel();
        
        InteractionOperand operand = combinedFragment.createOperand(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);                    
        ContainerNode operandNode = UMLDiagramFactory.eINSTANCE.createContainerNode();
        operandNode.setNodeType(NodeType.INTERACTION_OPERAND);
        operandNode.setParent(combinedFragementNode);
        operandNode.setUmlModel(operand);
        operandNode.setHeight(firstOperandHeight);  
                
        NotationNode guardNode = addGarud(constraintString, operand, operandNode);
        
        operandNode.getNodeList().add(guardNode);
        
        combinedFragementNode.getCompartmentList().add(operandNode);
    }

        
    /**
     * addInteractionOperandNode
     *  
     * @param combinedFragementNode void
     */
    private void addInteractionOperandNode(NotationNode combinedFragementNode, String constraintString, int operandHeight){
        
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragementNode.getUmlModel();
        
        InteractionOperand operand = combinedFragment.createOperand(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);                    
        ContainerNode operandNode = UMLDiagramFactory.eINSTANCE.createContainerNode();
        operandNode.setNodeType(NodeType.INTERACTION_OPERAND);
        operandNode.setParent(combinedFragementNode);
        operandNode.setUmlModel(operand);
        operandNode.setHeight(operandHeight);
                
        NotationNode guardNode = addGarud(constraintString, operand, operandNode);
        
        operandNode.getNodeList().add(guardNode);
        
        combinedFragementNode.getCompartmentList().add(operandNode);
    }   
    
    /**
     * 
     *  
     * @param constraintString
     * @param operand
     * @param operandNode
     * @return NotationNode
     */
    private NotationNode addGarud(String constraintString, InteractionOperand operand, ContainerNode operandNode) {
        InteractionConstraint guard = operand.createGuard(null);
        guard.createSpecification(null, null, UMLPackage.Literals.OPAQUE_EXPRESSION);
        ((OpaqueExpression) guard.getSpecification()).getBodies().add(constraintString);
        NotationNode guardNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        guardNode.setNodeType(NodeType.GUARD);
        guardNode.setParent(operandNode);
        guardNode.setUmlModel(guard);   
        guardNode.setX(UICoreConstant.GUARD_LOCATION);
        guardNode.setY(UICoreConstant.GUARD_LOCATION);
        guardNode.setHeight(UICoreConstant.GUARD_HEIGHT);
        return guardNode;
    }
    

    /**
     * setMessageFragments
     *   void
     * @param combinedFragementNode 
     */
    private void setMessageFragments(NotationNode combinedFragementNode) {
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragementNode.getUmlModel();
        EList<InteractionOperand> operands = combinedFragment.getOperands();
        InteractionOperand interactionOperand = operands.get(0);
        Message message;
        List<InteractionFragment> fragmentList;
        for(AbstractConnection connection : addedConnectionList) {
            message = (Message) connection.getUmlModel();
            fragmentList = SequenceUtil.getFragmentListByMessage(message);
            for(InteractionFragment fragment : fragmentList) {
                interactionOperand.getFragments().add(fragment);             
            }
        }        
    }
    

    /**
     * setLocationAndSize
     * 
     *   void
     */
    private void setLocationAndSize() {
        
        initFragmentXandWidth();
        initFirstOperandHeight();
        
        notationNode.setX(fragmentX);
        notationNode.setY(createBounds.y);

        notationNode.setWidth(fragmentWidth);
        if (firstOperandHeight + UICoreConstant.FRAGMENT_LABEL_HEIGHT < HEIGHT_MINIMUM_SIZE) {
            notationNode.setHeight(HEIGHT_MINIMUM_SIZE);
            firstOperandHeight = HEIGHT_MINIMUM_SIZE - UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        } else {
            notationNode.setHeight(firstOperandHeight + UICoreConstant.FRAGMENT_LABEL_HEIGHT);            
        }
    }
    
    /**
     * initFragmentXandWidth
     * 
     *   void
     */
    private void initFragmentXandWidth() {
        
        List<LifeLineNode> orderedNodeListByXvalue = new ArrayList<LifeLineNode>();
        for(Lifeline lifeline : addedLifelineList) {
            LifeLineNode lifeLineNode = rangedLifelineNodeMap.get(lifeline);
            if(lifeLineNode != null) {
                orderedNodeListByXvalue.add(lifeLineNode);                
            }
        }
        
        orderedNodeListByXvalue = SequenceUtil.ascSortedLifeLineNodeListByXValue(orderedNodeListByXvalue);
        
        fragmentX = orderedNodeListByXvalue.get(0).getX();
        LifeLineNode lastNode =  orderedNodeListByXvalue.get(orderedNodeListByXvalue.size()-1);
        fragmentWidth = lastNode.getX() - fragmentX + lastNode.getWidth();
        
    }
    
    /**
     * initFirstOperandHeight
     * 
     *   void
     */
    private void initFirstOperandHeight() {
        AbstractConnection bottomConnection = null;
        int bottomY = 0;
        for(AbstractConnection connection : addedConnectionList) {
            if(bottomY < connection.getY()) {
                bottomY = connection.getY();
                bottomConnection = connection;
            }
        }
        
        if(bottomConnection == null) {
            return;
        }
        
        if(RelationType.REPLY_MESSAGE.equals(bottomConnection)) {
            firstOperandHeight = bottomConnection.getY() - createBounds.y  + 10;
        } else {
            firstOperandHeight = bottomConnection.getY() - createBounds.y + ((AbstractNode) bottomConnection.getTarget()).getHeight() +10;            
        }
        
        
    }
   
    /**
     * getParentNodeModel
     *  
     * @return AbstractNode
     */
    public AbstractNode getParentNodeModel() {
        return parentNodeModel;
    }


}
