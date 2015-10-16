/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.command.DeleteUMLElementCommandFactory;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.command.DeleteNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractParentCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : DeleteUMLElementAction</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class DeleteUMLElementAction extends SelectionAction {

    /**
     * showMessage
     */
    protected boolean showMessage = true;

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
            if ((object instanceof AbstractDiagramEditPart) || (object instanceof AbstractParentCompartmentEditPart)) {
                return false;
            }
        }
        
        Object obj = this.getSelectedObjects().get(0);
        if(obj instanceof EditPart){
            Object model = ((EditPart)obj).getModel();
            if (model instanceof AbstractView){
            	Element element = ((AbstractView) model).getUmlModel(); 
            	if (element instanceof Operation || element instanceof Property || element instanceof ActivityNode
            			|| element instanceof ActivityPartition) {
            		return false;
            	}
                if (model instanceof AbstractNode ) {
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

    /** Delete From Model Action ID */
    public static final String DELETE_UMLELEMENT_ACTIONID = "DeleteFromModel";

    /**
     * @param part
     */
    public DeleteUMLElementAction(IWorkbenchPart part) {
        super(part);
        this.showMessage = true;
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_DELETE_FROM_MODEL_ACTION));
    }

    /**
     * @param part
     * @param messageFlag
     *            확인 메세지박스를 보여줄 것인지 결정하는 플래그.
     */
    public DeleteUMLElementAction(IWorkbenchPart part, boolean messageFlag) {
        this(part);
        this.showMessage = messageFlag;
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
        setId(DELETE_UMLELEMENT_ACTIONID);
        setText(UMLMessage.LABEL_DELETE_FROM_MODEL);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        Object element;
        Element uml;
        Command deleteCommand;
        CompoundCommand deleteCommandList = new CompoundCommand();
        String elementID;
        Object model;
        
        List<Object> uuidList = new ArrayList<Object>();
        
        if (0 == getSelectedObjects().size()) {
            return;
        }
        if (showMessage) {
            if (1 == getSelectedObjects().size()) {
                element = getSelectedObjects().get(0);
                elementID = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                if (element instanceof NamedElement) {
                    elementID = ((NamedElement) element).getName();
                    if (null == elementID) {
                        elementID = ((NamedElement) element).eClass().getName();
                    }
                } else if (element instanceof Diagram) {
                    elementID = ((Diagram) element).getName();

                } else if (element instanceof EditPart) {
                    model = ((EditPart) element).getModel();
                    if (model instanceof AbstractView) {
                        uml = ((AbstractView) ((EditPart) element).getModel()).getUmlModel();
                    } else {
                        uml = (Element) model;
                    }
                    if (uml instanceof Relationship) {
                        elementID = "Relationship";
                    } else if (uml instanceof NamedElement) {
                        elementID = ((NamedElement) uml).eClass().getName();
                    } else if ((element instanceof NoteEditPart) || (element instanceof TextEditPart)) {
                        elementID = getSelectedObjects().size() + " elements";
                    }
                }

            } else {
                elementID = getSelectedObjects().size() + " elements";
            }

            if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
                UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
                UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE, elementID))) {
                return;
            }
        }

        for (Object obj : getSelectedObjects()) {
            if (!(obj instanceof EditPart)) {
                continue;
            }
            element = ((EditPart) obj).getModel();
            if (!(element instanceof Element)) {
                continue;
            }

            if (!(element instanceof AbstractView)) {
                deleteCommand = DeleteUMLElementCommandFactory.getCommand((Element) element);
                
                String uuid = EcoreUtil.getURI( ((AbstractView)element).getUmlModel() ).fragment();
                uuidList.add(uuid);
                
            } else if ((obj instanceof NoteEditPart) || (obj instanceof TextEditPart)) {
                deleteCommand = new DeleteNodeCommand();
                ((DeleteNodeCommand) deleteCommand).setParent(((EditPart) obj).getParent().getModel());
                ((DeleteNodeCommand) deleteCommand).setNode(((EditPart) obj).getModel());
            } else {
                uml = ((AbstractView) element).getUmlModel();
                deleteCommand = DeleteUMLElementCommandFactory.getCommand(uml);
                
                String uuid = EcoreUtil.getURI((EObject) uml).fragment();
                uuidList.add(uuid);
            }

            if (null != deleteCommand) {
                deleteCommandList.add(deleteCommand);
            }
            
            // Attachement에 연결된 UML 요소 [모델에서 삭제] 실행 시 Attachement를 지워준다.
            // 기존의 [모델에서 삭제] 실행 시에는 Attachement가 삭제 되지 않았다.
            if ( element instanceof AbstractNode ) {
                EList<AbstractConnection> incomingList = ((AbstractNode)element).getIncomingConnectionList();
                EList<AbstractConnection> outgoingList = ((AbstractNode)element).getOutgoingConnectionList();
                
                boolean hasAttachement = false;
                for( AbstractConnection connection : incomingList ) {
                    if( connection instanceof Attachement ) {
                        hasAttachement = true;
                    }
                }
                for( AbstractConnection connection : outgoingList ) {
                    if( connection instanceof Attachement ) {
                        hasAttachement = true;
                    }
                }
                if(hasAttachement){
                    deleteCommand = new DeleteNodeCommand();
                    ((DeleteNodeCommand) deleteCommand).setParent(((EditPart) obj).getParent().getModel());
                    ((DeleteNodeCommand) deleteCommand).setNode(((EditPart) obj).getModel());
                    deleteCommandList.add(deleteCommand);
                }
            }
            //
            //
        }
        if (0 != deleteCommandList.getCommands().size()) {
            // DomainRegistry.getUMLDomain().getCommandStack().execute(deleteCommandList);
            ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(deleteCommandList);
        }

        
//        try {
//            NCPMetaContentServiceUtil.deleteResources(uuidList);
//        } catch (ConnectException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
