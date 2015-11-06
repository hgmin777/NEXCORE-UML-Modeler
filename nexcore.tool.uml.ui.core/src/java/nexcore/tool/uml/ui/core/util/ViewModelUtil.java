/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : ViewModelUtil</li>
 * <li>작성일 : 2011. 3. 15.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ViewModelUtil {

    /**
     * setModelInfo
     *  
     * @param notationNode void
     */
    public static void setModelInfo(NotationNode notationNode) {
        final NotationNode viewModel = notationNode;

        Object object = viewModel.getUmlModel();
        if (!(object instanceof NamedElement)) {
            return;
        }
        NamedElement namedElement = (NamedElement) object;

        List<Object> listStereotypes = new ArrayList<Object>();
        EList<String> keywords = namedElement.getKeywords();
        if (0 < keywords.size()) {
            listStereotypes.add(keywords); // Element Keywords
        }
        EList<Stereotype> stereotypes = namedElement.getAppliedStereotypes();
        if (0 < stereotypes.size()) {
            listStereotypes.add(stereotypes); // element Stereotypes
        }

        if (namedElement instanceof Actor) {
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            } else {
                removeLabelViewAndEditPart(viewModel, NodeType.STEREOTYPE);
            }
        } else {
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            } else {
                removeLabelViewAndEditPart(viewModel, NodeType.STEREOTYPE);
            }
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
        }
        boolean showOperation = false;
        if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
            .equals("true")) {
            showOperation = true;
        }
        if (showOperation) {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }
        } else {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                removeLabelViewAndEditPart(viewModel, NodeType.OPERATIONS);
                removeLabelViewAndEditPart(viewModel, NodeType.ATTRIBUTES);
            }
        }
        if (!(namedElement instanceof org.eclipse.uml2.uml.Component)) {
            if (namedElement instanceof org.eclipse.uml2.uml.Class) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
            }
            if (namedElement instanceof org.eclipse.uml2.uml.Class
                || namedElement instanceof org.eclipse.uml2.uml.Interface) {
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }

        }

        if (namedElement instanceof org.eclipse.uml2.uml.Component) {
            if (hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.PROVIDED_INTERFACES, namedElement);
            if (hasNodeType(NodeType.REQUIRED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.REQUIRED_INTERFACES, namedElement);
        }

        if (namedElement instanceof org.eclipse.uml2.uml.Enumeration) {
            if (hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                addCompartmentList(viewModel, NodeType.ENUMERATION_LITERALS, namedElement);
            }
        }

        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                int childrenHeight = 0;
                NotationNode childNode = null;
                for (AbstractNode child : viewModel.getCompartmentList()) {
                    if (child.getNodeType().equals(NodeType.STEREOTYPE)) {
                        childrenHeight -= 8;
                    }
                    if (child.getNodeType().equals(NodeType.NAME)) {
                        childrenHeight += 8;
                    }
                    childrenHeight += child.getHeight();
                    childNode = (NotationNode) child;
                }
                int parentHeight = 0;
                if (viewModel.getHeight() > childrenHeight) {
                    parentHeight = viewModel.getHeight();
                    if (childNode != null) {
                        if (childNode.getHeight() != childNode.getHeight() + parentHeight - childrenHeight) {
                            childNode.setHeight(childNode.getHeight() + parentHeight - childrenHeight);
                        }
                    }
                } else {
                    parentHeight = childrenHeight;
                }
                if (viewModel.getHeight() != parentHeight) {
                    viewModel.setHeight(parentHeight);
                }
            }
        });
    }

    /**
     * addCompartmentList
     *  
     * @param viewModel
     * @param nodeType
     * @param namedElement void
     */
    private static void addCompartmentList(final NotationNode viewModel, final NodeType nodeType,
                                           final NamedElement namedElement) {
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                boolean showOperation = false;
                if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
                    .equals("true")) {
                    showOperation = true;
                }

                NotationNode childViewModel = null;
                if (nodeType.equals(NodeType.NAME)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.NAME);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(15);

                } else if (nodeType.equals(NodeType.STEREOTYPE)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.STEREOTYPE);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(21);

                } else if (nodeType.equals(NodeType.ATTRIBUTES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.ATTRIBUTES);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);

                } else if (nodeType.equals(NodeType.OPERATIONS)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.OPERATIONS);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);

                } else if (nodeType.equals(NodeType.PROVIDED_INTERFACES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.PROVIDED_INTERFACES);
                    childViewModel.setParent(viewModel);
                    if (showOperation) {
                        childViewModel.setHeight(50);
                    } else {
                        childViewModel.setHeight(90);
                    }

                } else if (nodeType.equals(NodeType.REQUIRED_INTERFACES)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.REQUIRED_INTERFACES);
                    childViewModel.setParent(viewModel);
                    if (showOperation) {
                        childViewModel.setHeight(50);
                    } else {
                        childViewModel.setHeight(90);
                    }

                } else if (nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
                    childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                    childViewModel.setNodeType(NodeType.ENUMERATION_LITERALS);
                    childViewModel.setParent(viewModel);
                    childViewModel.setHeight(40);
                }

                childViewModel.setUmlModel(namedElement);

                if (childViewModel.getNodeType().equals(NodeType.STEREOTYPE)) {
                    viewModel.getCompartmentList().add(0, childViewModel);
                    if (viewModel.getNodeType().equals(NodeType.ACTOR)) {}
                    viewModel.setHeight(viewModel.getHeight() + 15);

                } else if (childViewModel.getNodeType().equals(NodeType.NAME)) {
                    if (hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        viewModel.getCompartmentList().add(0, childViewModel);
                    } else {
                        viewModel.getCompartmentList().add(1, childViewModel);
                    }
                } else if (nodeType.equals(NodeType.ATTRIBUTES) || nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
                    int cnt = 0;
                    if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.NAME, viewModel)) {
                        cnt++;
                    }
                    if (hasNodeType(NodeType.ATTRIBUTES, viewModel)
                        || hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                        viewModel.getCompartmentList().add(cnt, childViewModel);
                    }
                    if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                        if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                            viewModel.setHeight(viewModel.getHeight() + 40);
                        }
                    }
                } else if (nodeType.equals(NodeType.OPERATIONS)) {
                    int cnt = 0;
                    if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.NAME, viewModel)) {
                        cnt++;
                    }
                    if (!hasNodeType(NodeType.ATTRIBUTES, viewModel)
                        || !hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                        cnt++;
                    }
                    if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                        viewModel.getCompartmentList().add(cnt, childViewModel);
                    }
                    if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                        if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                            viewModel.setHeight(viewModel.getHeight() + 40);
                        }
                    }
                } else {
                    viewModel.getCompartmentList().add(childViewModel);
                }
            }
        });
    }

    /**
     * 
     * 라벨의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    private static boolean hasNodeType(NodeType nodeType, NotationNode notationNode) {
        NotationNode node = notationNode;
        for (AbstractNode child : node.getCompartmentList()) {
            if (child.getNodeType().equals(nodeType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * 값이 없는 경우 뷰모델과 에디트파트를 지워준다
     * 
     * @param node
     * @param labelType
     *            void
     */
    public static void removeLabelViewAndEditPart(final NotationNode node, NodeType nodeType) {

        NotationNode removeTarget = null;
        for (AbstractNode label : node.getCompartmentList()) {
            if (label.getNodeType().equals(nodeType)) {
                removeTarget = (NotationNode) label;
                break;
            }
        }
        final NotationNode removeElement;
        if (removeTarget != null) {
            removeElement = removeTarget;
            if (node.getCompartmentList().contains(removeElement)) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {

//                        IEditorPart[] editors = 
                        IEditorReference[] editors = UiCorePlugin.getActiveWorkbench()
                            .getActiveWorkbenchWindow()
                            .getActivePage()
                            .getEditorReferences();
//                            .getEditors();
                        for (IEditorReference editorPart : editors) {
                            if (editorPart instanceof AbstractDiagramEditor
                                && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram()
                                    .getType())) {
                                AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                                GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                                ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                                List<EditPart> diagramEditParts = root.getChildren();

                                EditPart targetEditPart = findTargetEditPart(diagramEditParts);
                            }
                        }
                        // 에디트파트를 찾아서 지운다.
                        // List<EditPart> children = getChildren();
                        // EditPart removeChild = null;
                        // for (EditPart child : children) {
                        // if (child.getModel().equals(removeElement)) {
                        // removeChild = (child);
                        // }
                        // }
                        // if(getChildren().contains(removeChild)) {
                        // getFigure().remove(
                        // ((GraphicalEditPart)removeChild).getFigure() );
                        // getChildren().remove(removeChild);
                        //                            
                        node.setHeight(node.getHeight() - removeElement.getHeight());
                        // }
                        node.getCompartmentList().remove(removeElement);
                    }

                    private boolean removeEditPart(EditPart targetEditPart, List<EditPart> editParts) {
                        EditPart removeChild = null;
                        for (EditPart child : editParts) {
                            if (child.equals(targetEditPart)) {
                                removeChild = child;
                            }
                            removeEditPart(targetEditPart, child.getChildren());
                        }
                        if (removeChild != null) {
                            if (editParts.contains(removeChild)) {
                                editParts.remove(removeChild);
                                return true;
                            }
                        }
                        return false;
                    }

                    private EditPart findTargetEditPart(List<EditPart> editParts) {

                        EditPart removeChild = null;

                        for (EditPart child : editParts) {
                            if (child.getModel().equals(removeElement)) {
                                removeChild = child;
                                return child;
                            } else {
                                removeChild = findTargetEditPart(child.getChildren());
                                if (removeChild != null)
                                    return removeChild;
                            }
                        }
                        return removeChild;
                    }
                });
            }
        }
    }
    
	/**
	 * 
	 * 
	 * @param node
	 *            void
	 */
	public static void clearGarbageNode(final AbstractView view) {

		NotationNode node = null;
		LabelNode labelNode = null;
		AbstractConnection connection = null;

		final List<AbstractNode> compartmentList = new ArrayList<AbstractNode>();
		final List<AbstractNode> garbageList = new ArrayList<AbstractNode>();

		if (view instanceof AbstractNode) {

			if (view instanceof NotationNode) {
				node = (NotationNode) view;
				compartmentList.addAll(node.getCompartmentList());
				for (AbstractNode child : compartmentList) {
					if (child.getNodeType() == NodeType.NOTE) {
						garbageList.add(child);
					}
				}
				removeChildren(node.getCompartmentList(), garbageList);
			} else if (view instanceof LabelNode) {
				labelNode = (LabelNode) view;
				compartmentList.addAll(labelNode.getCompartmentList());
				for (AbstractNode child : compartmentList) {
				    if (child instanceof LabelNode) {
				        LabelNode childNode = (LabelNode) child;
				        if (LabelType.LABEL.equals(childNode.getType()) ||
				                LabelType.STEREOTYPE.equals(childNode.getType())) {
				            continue;
				        }
				    }
					if (child.getNodeType() == NodeType.NOTE) {
						garbageList.add(child);
					}
				}
				removeChildren(labelNode.getCompartmentList(), garbageList);
			}
			
		} else if (view instanceof AbstractConnection) {
			connection = (AbstractConnection) view;
			compartmentList.addAll(connection.getLabels());
			for (AbstractNode child : compartmentList) {
				if (child instanceof LabelNode) {
					LabelNode label = (LabelNode) child;
					if (LabelType.LABEL == label.getType()) {
						garbageList.add(child);
					}
				} else {
					if (NodeType.NOTE == child.getNodeType()) {
						garbageList.add(child);
					}
				}
				clearGarbageNode(child);
			}
			DomainUtil.run(new TransactionalAction() {
				/**
				 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
				 */
				@Override
				public void doExecute() {
					for (AbstractNode child : garbageList) {
						((LabelNode) child).setType(LabelType.COMPARTMENT);
						((LabelNode) child).setParent(view);
					}
				}
			});
			// removeChildren(connection.getLabels(), garbageList);
		}

	}

    /**
     * removeChildren
     *  
     * @param compartmentList
     * @param garbageList void
     */
    private static void removeChildren(final EList<?> compartmentList, final List<AbstractNode> garbageList) {
        
        if (garbageList.size() == 0) {
            return;
        }
        
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                compartmentList.removeAll(garbageList);
            }
        });
    }

}
