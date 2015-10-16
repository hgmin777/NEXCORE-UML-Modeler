/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : PasteDiagramNodeAction</li>
 * <li>작성일 : 2011. 1. 27.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class PasteDiagramNodeAction extends SelectionAction {

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        int count = this.getSelectedObjects().size();
        if (0 == count) {
            return false;
        } else if (1 == count) {
            Object object = this.getSelectedObjects().get(0);
            if (!(object instanceof AbstractDiagramEditPart)) {
                return false;
            }
        }

        if (DomainRegistry.getUMLDomain().getClipboard().size() == 0) {
            return false;
        }
        
        Object obj = this.getSelectedObjects().get(0);
        if(obj instanceof EditPart){
            Object model = ((EditPart)obj).getModel();
            if(model instanceof AbstractView && !(model instanceof Diagram)){
                if( model instanceof AbstractNode ) {
                    if( NodeType.NOTE.equals( ((AbstractNode)model).getNodeType()) ||  NodeType.TEXT.equals( ((AbstractNode)model).getNodeType()) ||  nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals( ((AbstractNode)model).getNodeType())) {
                        return false;
                    }
                } else if( model instanceof AbstractConnection ) {
                    if(nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals( ((AbstractConnection)model).getRelationType())) {
                        return false;
                    }
                }
                if( DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return false;
                }
            }
        }
        
        return true;
    }

    /** Paste Diagram Node Action ID */
    public static final String PASTE_DIAGRAMNODE_ACTIONID = "Paste";

    /**
     * @param part
     */
    public PasteDiagramNodeAction(IWorkbenchPart part) {
        super(part);
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_PASTE_ACTION));
    }

    /**
     * @param part
     * @param messageFlag
     *            확인 메세지박스를 보여줄 것인지 결정하는 플래그.
     */
    public PasteDiagramNodeAction(IWorkbenchPart part, boolean messageFlag) {
        this(part);
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
        setId(PASTE_DIAGRAMNODE_ACTIONID);
        setText(UMLMessage.LABEL_PASTE);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        DiagramPasteCommand pasteCommand;
        AbstractDiagramEditPart diagramEditPart = null;

        if (1 == getSelectedObjects().size()) {
            if (getSelectedObjects().get(0) instanceof AbstractDiagramEditPart) {
                diagramEditPart = (AbstractDiagramEditPart) getSelectedObjects().get(0);
            }
        }

        if (diagramEditPart != null) {
            pasteCommand = new DiagramPasteCommand((Diagram) diagramEditPart.getModel());
            ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(pasteCommand);
        }
    }

    public class DiagramPasteCommand extends Command {

        /**
         * umlDomain
         */
        private IDomainModelHandler umlDomain;

        /**
         * clipboard
         */
        private Collection<Object> clipboard;

        /**
         * abstractNode
         */
        private AbstractNode abstractNode = null;

        /**
         * umlElement
         */
        private Element umlElement = null;

        /**
         * editPart
         */
        private EditPart editPart = null;

        /**
         * diagram
         */
        private Diagram diagram = null;

        /**
         * actionType
         */
        private String actionType = null;

        /**
         * @param objList
         */
        public DiagramPasteCommand(Diagram diagram) {
            umlDomain = DomainRegistry.getUMLDomain();
            this.diagram = diagram;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            clipboard = umlDomain.getClipboard();
            for (Object obj : clipboard) {
                if (obj instanceof EditPart) {
                    editPart = (EditPart) obj;
                    abstractNode = (AbstractNode) editPart.getModel();
                    umlElement = abstractNode.getUmlModel();
                    pasteNode();
                } else if (obj instanceof String) {
                    actionType = (String) obj;
                }
            }
        }

        private void pasteNode() {
            if (abstractNode != null && umlElement != null && diagram != null) {
                AbstractNode node = UMLDiagramFactory.eINSTANCE.createNotationNode();// (AbstractNode)
                // EcoreUtil.copy(element);

                if (actionType != null) {
                    if (actionType.equals(CopyDiagramNodeAction.COPY_DIAGRAMNODE_ACTIONID)) {

                        // //////////////////////////////////////////////////////
                        // Association 연결에 따라 생성된 Property는 복사하지 않는다.
                        // 추후에 연결선 복사 기능은 완성 시 삭제하도록 한다.
                        List<Property> tempProperty = new ArrayList<Property>();
                        if (umlElement instanceof Class) {
                            for (Property childProperty : ((Class) umlElement).getOwnedAttributes()) {
                                if (childProperty.getAssociation() != null) {
                                    tempProperty.add(childProperty);
                                }
                            }
                            ((Class) umlElement).getOwnedAttributes().removeAll(tempProperty);
                        }
                        // ///////////////////////////////////////////////////////
                        Element newElement = (Element) copyElement(diagram.getParent(), umlElement);//umlElement.getOwner(), umlElement);
                        // //////////////////////////////////////////////////////
                        // 노드 복사가 끝난 후엔 다시 Property를 추가해 준다.
                        if (umlElement instanceof Class) {
                            ((Class) umlElement).getOwnedAttributes().addAll(tempProperty);
                        }
                        // //////////////////////////////////////////////////////
                        node.setUmlModel(newElement);
                        node.setName( ((NamedElement)newElement).getName());
                    } else if (actionType.equals(CutDiagramNodeAction.CUT_DIAGRAMNODE_ACTIONID)) {
                        node.setUmlModel(umlElement);
                        clipboard = new ArrayList<Object>();
                        umlDomain.setClipboard(clipboard);
                    }
                }

                int x = abstractNode.getX();
                int y = abstractNode.getY();
                int variable = 0;

                for (AbstractNode cNode : diagram.getNodeList()) {
                    if (cNode.getX() == x && cNode.getY() == y) {
                        variable += 30;
                        x += 30;
                        y += 30;
                    }
                }

                node.setX(abstractNode.getX() + variable);
                node.setY(abstractNode.getY() + variable);
                node.setWidth(abstractNode.getWidth());
                node.setHeight(abstractNode.getHeight());
                node.setNodeType(abstractNode.getNodeType());

                diagram.getNodeList().add(node);

                if (umlElement instanceof ControlNode) {
                    LabelNode nodeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                    nodeLabel.setParent(node);
                    nodeLabel.setX(10);
                    nodeLabel.setY(-20);
                    nodeLabel.setType(LabelType.COMPARTMENT);
                    nodeLabel.setUmlModel(node.getUmlModel());
                    ((NotationNode) node).getLabels().add(nodeLabel);
                    diagram.getNodeList().add(nodeLabel);
                }

                if (node instanceof NotationNode) {
                    ViewModelUtil.setModelInfo((NotationNode) node);
                }

                // DropNotationCommand command = new
                // DropNotationCommand("Add drop");
                // ((DropNotationCommand) command).setDiagram(diagram);
                // ((DropNotationCommand) command).setChild(node);
                // command.execute();

                // AbstractDiagramEditor editor = (AbstractDiagramEditor)
                // PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                // DiagramRegerate diagramRegerate = new
                // DiagramRegerate(editor);
                // diagramRegerate.dropNotationNode(node);

                // AbstractNode target = null;
                // AbstractNode source = null;
                // AbstractConnection connection = null;
                // for(Relationship relationship :
                // umlElement.getRelationships()){
                // for(AbstractConnection abstractConnection :
                // diagram.getConnectionList()){
                // if( abstractConnection.getUmlModel().equals(relationship) ){
                // if( abstractConnection.getSource().equals(node) ){
                // target = (AbstractNode) abstractConnection.getTarget();
                // } else if ( abstractConnection.getTarget().equals(node) ){
                // source = (AbstractNode) abstractConnection.getSource();
                // }
                // connection = abstractConnection;
                // }
                // }
                // }
                //                
                //
                // if(target != null){
                // RelationAutoGenerationCommand relationAutoGenerationCommand =
                // new RelationAutoGenerationCommand(diagram,
                // node,
                // target,
                // connection);
                // relationAutoGenerationCommand.execute();
                // } else if(source != null){
                // RelationAutoGenerationCommand relationAutoGenerationCommand =
                // new RelationAutoGenerationCommand(diagram,
                // source,
                // node,
                // connection);
                // relationAutoGenerationCommand.execute();
                // }
            }
        }
    }

    /**
     * copyElement
     *  
     * @param parent
     * @param element
     * @return EObject
     */
    public EObject copyElement(EObject parent, EObject element) {
        
        if( !isWrongParent(parent, element) ) {
            if( null != ((Element) parent).getOwner() && ((Element) parent).getOwner() instanceof Element ) {
                return copyElement(((Element) parent).getOwner(), element);
            } else {
                return null;
            }
        }
        
        
        EObject eobject = null;
        EAnnotation eAnnotation;
        Object object;

        EObject tempEObject;
        object = element;
        if (object instanceof EObject) {
            eobject = EcoreUtil.copy((EObject) object);
            if (eobject instanceof Package) {
                for (TreeIterator<EObject> iter = eobject.eAllContents(); iter.hasNext();) {
                    tempEObject = iter.next();
                    if (tempEObject instanceof Diagram) {
                        EcoreUtil.remove(tempEObject);
                    }
                }
            }
            if (!((NamedElement) eobject).getName().equals("")) {
                ((NamedElement) eobject).setName(getCopiedUniqueName((NamedElement) eobject,
                    (Namespace) parent,
                    ((NamedElement) eobject).getName()));
            }

            // parent에 따른 분기
            if (parent instanceof Package) {
                Package _package = (Package) parent;
                if (eobject instanceof PackageableElement) {
                    _package.getPackagedElements().add((PackageableElement) eobject);

                } else if (eobject instanceof EAnnotation) {
                    eAnnotation = _package.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                    if (eAnnotation == null)
                        _package.getEAnnotations().add((EAnnotation) eobject);
                }
            } else if (parent instanceof Component) {
                Component component = (Component) parent;
                if (eobject instanceof Package) {
                    component.getPackagedElements().add((Package) eobject);
                } else if (eobject instanceof Property) { // Property, Port
                    component.getOwnedAttributes().add((Property) eobject);
                } else if (eobject instanceof Operation) {
                    component.getOwnedOperations().add((Operation) eobject);
                } else if (eobject instanceof Class) { // Class, Component
                    component.getPackagedElements().add((Class) eobject);
                } else if (eobject instanceof Enumeration) {
                    component.getPackagedElements().add((Enumeration) eobject);
                } else if (eobject instanceof Interface) {
                    component.getPackagedElements().add((Interface) eobject);
                } else if (eobject instanceof DataType) {// DataType,
                    component.getPackagedElements().add((DataType) eobject);
                } else if (eobject instanceof Activity) {
                    component.getOwnedBehaviors().add((Activity) eobject);
                } else if (eobject instanceof Collaboration) {
                    component.getPackagedElements().add((Collaboration) eobject);
                } else if (eobject instanceof CollaborationUse) {
                    component.getCollaborationUses().add((CollaborationUse) eobject);
                } else if (eobject instanceof Interaction) {
                    component.getOwnedBehaviors().add((Interaction) eobject);
                }
            } else if (parent instanceof Activity) {
                Activity activity = (Activity) parent;
                if (eobject instanceof ActivityPartition) {
                    activity.getGroups().add((ActivityPartition) eobject);
                } else if (eobject instanceof Action) {
                    activity.getNodes().add((Action) eobject);
                } else if (eobject instanceof ActivityNode) {
                    activity.getNodes().add((ActivityNode) eobject);
                }
            } else if (parent instanceof Class) {
                Class clazz = (Class) parent;
                if (eobject instanceof Property) { // Property, Port
                    clazz.getOwnedAttributes().add((Property) eobject);
                } else if (eobject instanceof Operation) {
                    clazz.getOwnedOperations().add((Operation) eobject);
                } else if (eobject instanceof Reception) {
                    clazz.getOwnedReceptions().add((Reception) eobject);
                } else if (eobject instanceof Class) {
                    clazz.getNestedClassifiers().add((Class) eobject);
                } else if (eobject instanceof Enumeration) {
                    clazz.getNestedClassifiers().add((Enumeration) eobject);
                } else if (eobject instanceof Interface) {
                    clazz.getNestedClassifiers().add((Interface) eobject);
                } else if (eobject instanceof Activity) {
                    clazz.getOwnedBehaviors().add((Activity) eobject);
                } else if (eobject instanceof CollaborationUse) {
                    clazz.getCollaborationUses().add((CollaborationUse) eobject);
                } else if (eobject instanceof Interaction) {
                    clazz.getNestedClassifiers().add((Interaction) eobject);
                }
            } else if (parent instanceof Collaboration) {
                Collaboration collaboration = (Collaboration) parent;
                if (eobject instanceof Property) {
                    collaboration.getOwnedAttributes().add((Property) eobject);
                } else if (eobject instanceof CollaborationUse) {
                    collaboration.getCollaborationUses().add((CollaborationUse) eobject);
                }
            } else if (parent instanceof Artifact) {
                Artifact artifact = (Artifact) parent;
                if (eobject instanceof Property) {

                } else if (eobject instanceof Operation) {
                    artifact.getOwnedOperations().add((Operation) eobject);
                } else if (eobject instanceof Artifact) {
                    artifact.getNestedArtifacts().add((Artifact) eobject);
                }
            } else if (parent instanceof Enumeration) {
                Enumeration enumeration = (Enumeration) parent;
                if (eobject instanceof EnumerationLiteral) {
                    enumeration.getOwnedLiterals().add((EnumerationLiteral) eobject);
                } else if (eobject instanceof Property) {
                    enumeration.getOwnedAttributes().add((Property) eobject);
                } else if (eobject instanceof Operation) {
                    enumeration.getOwnedOperations().add((Operation) eobject);
                }

            } else if (parent instanceof Interface) {
                Interface interfaze = (Interface) parent;
                if (eobject instanceof Property) {
                    interfaze.getOwnedAttributes().add((Property) eobject);
                } else if (eobject instanceof Operation) {
                    interfaze.getOwnedOperations().add((Operation) eobject);
                } else if (eobject instanceof Reception) {
                    interfaze.getOwnedReceptions().add((Reception) eobject);
                } else if (eobject instanceof Class) {
                    interfaze.getNestedClassifiers().add((Class) eobject);
                } else if (eobject instanceof Enumeration) {
                    interfaze.getNestedClassifiers().add((Enumeration) eobject);
                } else if (eobject instanceof Collaboration) {
                    interfaze.getNestedClassifiers().add((Collaboration) eobject);
                } else if (eobject instanceof CollaborationUse) {
                    interfaze.getCollaborationUses().add((CollaborationUse) eobject);
                }

            } else if (parent instanceof Signal) {
                Signal signal = (Signal) parent;
                if (eobject instanceof Property) {
                    signal.getOwnedAttributes().add((Property) eobject);
                }
            }
        }
        return eobject;
    }
    
    /**
     * isWrongParent
     *  
     * @param parent
     * @param child
     * @return boolean
     */
    private boolean isWrongParent(EObject parent, EObject child) {
        if (parent instanceof Package) {
//            Package _package = (Package) parent;
            if (child instanceof PackageableElement) {
                return true;
            } else if (child instanceof EAnnotation) {
                return true;
            }
        } else if (parent instanceof Component) {
            if (child instanceof Package) {
                return true;
            } else if (child instanceof Property) {
                return true;
            } else if (child instanceof Operation) {
                return true;
            } else if (child instanceof Class) {
                return true;
            } else if (child instanceof Enumeration) {
                return true;
            } else if (child instanceof Interface) {
                return true;
            } else if (child instanceof DataType) {
                return true;
            } else if (child instanceof Activity) {
                return true;
            } else if (child instanceof Collaboration) {
                return true;
            } else if (child instanceof CollaborationUse) {
                return true;
            } else if (child instanceof Interaction) {
                return true;
            }
        } else if (parent instanceof Activity) {
            if (child instanceof ActivityPartition) {
                return true;
            } else if (child instanceof Action) {
                return true;
            } else if (child instanceof ActivityNode) {
                return true;
            }
        } else if (parent instanceof Class) {
            if (child instanceof Property) {
                return true;
            } else if (child instanceof Operation) {
                return true;
            } else if (child instanceof Reception) {
                return true;
            } else if (child instanceof Class) {
                return true;
            } else if (child instanceof Enumeration) {
                return true;
            } else if (child instanceof Interface) {
                return true;
            } else if (child instanceof Activity) {
                return true;
            } else if (child instanceof CollaborationUse) {
                return true;
            } else if (child instanceof Interaction) {
                return true;
            }
        } else if (parent instanceof Collaboration) {
            if (child instanceof Property) {
                return true;
            } else if (child instanceof CollaborationUse) {
                return true;
            }
        } else if (parent instanceof Artifact) {
            if (child instanceof Property) {
                return true;
            } else if (child instanceof Operation) {
                return true;
            } else if (child instanceof Artifact) {
                return true;
            }
        } else if (parent instanceof Enumeration) {
            if (child instanceof EnumerationLiteral) {
                return true;
            } else if (child instanceof Property) {
                return true;
            } else if (child instanceof Operation) {
                return true;
            }
        } else if (parent instanceof Interface) {
            if (child instanceof Property) {
                return true;
            } else if (child instanceof Operation) {
                return true;
            } else if (child instanceof Reception) {
                return true;
            } else if (child instanceof Class) {
                return true;
            } else if (child instanceof Enumeration) {
                return true;
            } else if (child instanceof Collaboration) {
                return true;
            } else if (child instanceof CollaborationUse) {
                return true;
            }
        } else if (parent instanceof Signal) {
            if (child instanceof Property) {
                return true;
            }
        }
        return false;
    }
    

    /**
     * getCopiedUniqueName
     *  
     * @param element
     * @param parent
     * @param name
     * @return String
     */
    public static String getCopiedUniqueName(NamedElement element, Namespace parent, String name) {

        int index = 1;
        String newName;
        while (true) {
            newName = UMLMessage.getMessage(UMLMessage.LABEL_COPYOF) + Integer.toString(index++)
                + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR + name;
            boolean isUnique = true;
            for (Element child : parent.getOwnedElements()) {
                if (child instanceof NamedElement) {
                    if (((NamedElement) child).getName() != null) {
                        if (((NamedElement) child).getName().equals(newName)) {
                            isUnique = false;
                        }
                    }
                }
            }
            if (isUnique) {
                return newName;
            }
            // if (null == parent.getOwnedMember(newName)) {
            // return newName;
            // }

            if (10000 <= index) {
                return name + Integer.toString(UMLManager.NAME_INDEX++);
            }
        }
    }
}
