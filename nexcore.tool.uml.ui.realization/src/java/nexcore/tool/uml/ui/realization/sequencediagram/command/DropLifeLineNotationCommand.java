/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;
import java.util.UUID;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.ModelUtility;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : DropLifeLineNotationCommand</li>
 * <li>작성일 : 2010. 2. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DropLifeLineNotationCommand extends Command {

    /** diagramEditor */
    protected AbstractDiagramEditor diagramEditor;

    /** treeElement */
    protected Element treeElement;

    /** where */
    protected org.eclipse.draw2d.geometry.Point where;

    /** child */
    protected AbstractNode notationNode;

    /** parent */
    protected Diagram diagram;

    /** index */
    protected int index = -1;

    /**
     * @param label
     */
    public DropLifeLineNotationCommand(String label, AbstractDiagramEditor diagramEditor, Element treeElement,
    org.eclipse.draw2d.geometry.Point where) {
        super(label);
        this.diagramEditor = diagramEditor;
        this.treeElement = treeElement;
        this.where = where;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        
        if ( treeElement instanceof Type && diagram.getNodeList().size() > 0) {
            List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList()));
            if (lifeLineNodeList.size() > 0) {
                
                for( LifeLineNode lifeLine : lifeLineNodeList ) {
                    
                    Rectangle bounds = new Rectangle();
                    bounds.x = lifeLine.getX();
                    bounds.y = lifeLine.getY();
                    bounds.width = lifeLine.getWidth();
                    bounds.height = FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT;//lifeLine.getHeight();
                    
                    if( bounds.contains(where) ) {
                        Lifeline lifeLineElement = (Lifeline) lifeLine.getUmlModel();
                        Type newType = (Type) treeElement;
                        Type oldType = lifeLineElement.getRepresents().getType();
                        if (null != oldType) {
                        	// 기존에 지정된 라이프라인의 타입이 있으면 메시지 검사.
                        	
	                        Message message = null;
	                        Operation operation = null;
	                        boolean hasSameOperation = false;
	                        EList<InteractionFragment> coverdBys = lifeLineElement.getCoveredBys();
	                        for (InteractionFragment interactionFragment : coverdBys) {
								if (interactionFragment instanceof MessageOccurrenceSpecification) {
									// 해당 라이프라인에 작성된 메시지를 찾는다.
									message = ((MessageOccurrenceSpecification) interactionFragment).getMessage();
									operation = ModelUtility.getMessageOperation(message);
		                        	if (operation != null) {
		                        		// 메시지에 할당된 오퍼레이션명이 새로 Drop한 클래스에 있으면 유지, 없으면 오퍼레이션없는 표시.
		                        		EList<Element> newOperations = newType.getOwnedElements();
		                        		for (Element element : newOperations) {
											if (element instanceof Operation) {
												Operation newOperation = (Operation) element;
												if (newOperation.getName().equals(operation.getName())) {
													// newOperation을 메시지에 할당해준다.
													SequenceUtil.setOperationToMessage(newOperation, message);
													hasSameOperation = true;
													break; 
												}
											}
										}
		                        		
		                        		if (!hasSameOperation) {
		                        			// 같은 오퍼레이션이 없을 때, 메시지에 할당된 오퍼레이션을 해제.
		                        			SequenceUtil.setOperationToMessage(null, message);
		                        		}
		                        	}
								}
							}
	                        
                        }
                        lifeLineElement.getRepresents().setType(newType);
                        
                        return;
                    }
                }
            }
        }

        notationNode = createNotationNode(treeElement);
        notationNode.setX(where.x);
        notationNode.setY(20);

        if (diagram.getNodeList().size() > 0) {
            List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList()));
            if (lifeLineNodeList.size() > 0) {
                setLifeLineLayout(lifeLineNodeList);
            } else {
                if (index < 0) {
                    diagram.getNodeList().add(notationNode);
                } else {
                    diagram.getNodeList().add(index, notationNode);
                }
            }
        } else {
            if (index < 0) {
                diagram.getNodeList().add(notationNode);
            } else {
                diagram.getNodeList().add(index, notationNode);
            }
        }
        
        if (diagram != null) {
            SequenceUtil.refreshLifeLineOrder(diagram);
        }

    }

    /**
     * createNotationNode
     * 
     * @param treeElement
     * @return NotationNode
     */
    protected NotationNode createNotationNode(Element treeElement) {

        LifeLineNode notationNode = UMLDiagramFactory.eINSTANCE.createLifeLineNode();
        Element umlModel = UMLHelper.createLifeline();

        notationNode = createLifeLineNode(notationNode, umlModel);

        if (treeElement instanceof org.eclipse.uml2.uml.Component) {
            ((Lifeline) notationNode.getUmlModel()).getRepresents().setType((Component) treeElement);
            ((Lifeline) notationNode.getUmlModel()).setName(((org.eclipse.uml2.uml.Component) treeElement).getName());
            notationNode.setName(((org.eclipse.uml2.uml.Component) treeElement).getName());
        } else if (treeElement instanceof org.eclipse.uml2.uml.Class) {
            ((Lifeline) notationNode.getUmlModel()).getRepresents().setType((org.eclipse.uml2.uml.Class) treeElement);
            ((Lifeline) notationNode.getUmlModel()).setName(((org.eclipse.uml2.uml.Class) treeElement).getName());
            notationNode.setName(((org.eclipse.uml2.uml.Class) treeElement).getName());
        } else if (treeElement instanceof org.eclipse.uml2.uml.Interface) {
            ((Lifeline) notationNode.getUmlModel()).getRepresents().setType((Interface) treeElement);
            ((Lifeline) notationNode.getUmlModel()).setName(((org.eclipse.uml2.uml.Interface) treeElement).getName());
            notationNode.setName(((org.eclipse.uml2.uml.Interface) treeElement).getName());
        } else if (treeElement instanceof org.eclipse.uml2.uml.Actor) {
            ((Lifeline) notationNode.getUmlModel()).getRepresents().setType((Actor) treeElement);
            ((Lifeline) notationNode.getUmlModel()).setName(((org.eclipse.uml2.uml.Actor) treeElement).getName());
            notationNode.setName(((org.eclipse.uml2.uml.Actor) treeElement).getName());
        }

        return notationNode;

    }

    /**
     * createLifeLineNode
     * 
     * @param notationNode
     * @param umlModel
     * @return NotationNode
     */
    private LifeLineNode createLifeLineNode(LifeLineNode notationNode, Element umlModel) {
        PackageableElement parentElement = null;
        StructuredClassifier structuredClassifier;
        Property property;
        parentElement = UMLManager.getParent(this.diagramEditor.getDiagram());
        Interaction interaction = (Interaction) parentElement;
        interaction.getLifelines().add((Lifeline) umlModel);
        structuredClassifier = (StructuredClassifier) interaction.getOwner();
        property = structuredClassifier.createOwnedAttribute(UMLManager.getPackagedUniqueName(structuredClassifier,
            UMLMessage.getMessage(UMLMessage.LABEL_ATTRIBUTE)), null);
        ((Lifeline) umlModel).setRepresents(property);

        notationNode.setId(UUID.randomUUID().toString());
        notationNode = UMLDiagramFactory.eINSTANCE.createLifeLineNode();
        notationNode.setId(UUID.randomUUID().toString());
        notationNode.setUmlModel(umlModel);
        notationNode.setNodeType(NodeType.LIFELINE);
        Dimension dimension = FigureConstant.getFigureDimension(notationNode.getNodeType());
        notationNode.setWidth(dimension.width);
        notationNode.setHeight(dimension.height);

        Line line = UMLDiagramFactory.eINSTANCE.createLine();
        line.setId(UUID.randomUUID().toString());
        line.setUmlModel(umlModel);
        line.setNodeType(NodeType.LINE);
        line.setParent(notationNode);
        notationNode.setLine(line);
        return notationNode;
    }

    /**
     * 생성되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setLifeLineLayout(List<LifeLineNode> lifeLineNodeList) {

        LifeLineNode lifeLineNode;
        LifeLineNode othersLifeLineNode;

        AbstractNode lastNode = lifeLineNodeList.get(lifeLineNodeList.size() - 1);
        notationNode.setX(lastNode.getX() + lastNode.getWidth() + 20);

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (((lifeLineNode.getX() + lifeLineNode.getWidth() / 2)) > where.x) {

                notationNode.setX(lifeLineNode.getX());

                for (int k = i; k < lifeLineNodeList.size(); k++) {
                    othersLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    othersLifeLineNode.setX(othersLifeLineNode.getX() + notationNode.getWidth() + 20);
                }

                break;
            }
        }

        // 다이어그램 내 다른 LifeLine과 높이를 맞춘다.
        notationNode.setHeight(lifeLineNodeList.get(0).getHeight());

        if (index < 0) {
            diagram.getNodeList().add(notationNode);
        } else {
            diagram.getNodeList().add(index, notationNode);
        }

    }

    /**
     * @return Returns the child.
     */
    public AbstractNode getChild() {
        return notationNode;
    }

    /**
     * @param child
     *            The child to set.
     */
    public void setChild(AbstractNode notationNode) {
        this.notationNode = notationNode;
    }

    /**
     * @return Returns the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            The index to set.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return Returns the parent.
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * @param parent
     *            The parent to set.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
}
