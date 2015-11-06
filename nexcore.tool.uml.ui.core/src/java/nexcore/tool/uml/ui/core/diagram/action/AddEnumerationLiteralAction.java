/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : AddEnumerationLiteralAction</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class AddEnumerationLiteralAction extends SelectionAction {

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        Object obj = getSelectedObjects().get(0);
        Object element = ((EditPart) obj).getModel();
        Element uml = null;
        
        obj = getSelectedObjects().get(0);
        element = ((EditPart) obj).getModel();
        if( element instanceof AbstractView ) {
            uml = ((AbstractView)element).getUmlModel();
        }
        
        if( null == obj || null == element || null == uml ) {
            return false;
        }
        
        if( null == uml || DomainUtil.isProxy(uml) ){
            return false;
        }

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
            }
        }
        
        NotationNode selectedView = null;
        if (element instanceof NotationNode) {
            selectedView = (NotationNode) element;
        }

        if (selectedView == null) {
            return false;
        }
        if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERAL)) {
            return true;
        } else if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERALS)) {
            return true;
        }

        if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERALS)) {
            element = ((EditPart) obj).getParent().getModel();
        } else if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERAL)) {
            element = ((EditPart) obj).getParent().getParent().getModel();
        }

        if (element instanceof NotationNode) {
            NotationNode node = (NotationNode) element;
            int count = this.getSelectedObjects().size();
            if (0 == count) {
                return false;
            } else if (1 == count) {
                if (obj instanceof AbstractDiagramEditPart) {
                    return false;
                }
                if (node.getUmlModel() instanceof org.eclipse.uml2.uml.Enumeration) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /** Add Attribute Action ID */
    public static final String ADD_ENUMERATION_LITERAL_ACTIONID = "AddEnumerationLiteral";

    /**
     * @param part
     */
    public AddEnumerationLiteralAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ENUMERATIONLITERAL)));
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
        setId(ADD_ENUMERATION_LITERAL_ACTIONID);
        setText(UMLMessage.LABEL_ADD_ENUMERATION_LITERAL);
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
                    createEnumerationLiteral();

                }
            });
    }

    /**
     * createEnumerationLiteral
     *   void
     */
    private void createEnumerationLiteral() {
        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;
        Object element = ((EditPart) obj).getModel();

        NotationNode selectedView = null;
        if (element instanceof NotationNode) {
            selectedView = (NotationNode) element;
        }
        if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERALS)) {
            element = ((EditPart) obj).getParent().getModel();
        } else if (selectedView.getNodeType().equals(NodeType.ENUMERATION_LITERAL)) {
            element = ((EditPart) obj).getParent().getParent().getModel();
            element = ((EditPart) obj).getParent().getParent().getModel();
        }

        Enumeration enumeration;
        if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Enumeration) {
            enumeration = (Enumeration) ((NotationNode) element).getUmlModel();
            EnumerationLiteral enumerationLiteral = UMLHelper.createEnumerationLiteral();
            enumerationLiteral.setName((UMLManager.getPackagedUniqueName(enumeration,
                UMLMessage.getMessage(UMLMessage.UML_ENUMERATIONLITERAL))));
            enumeration.getOwnedLiterals().add(enumerationLiteral);
        }
    }

}
