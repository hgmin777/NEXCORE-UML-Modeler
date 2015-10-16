/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.provider;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.paste.ElementPaster;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : ExplorerDropAdapterAssistant</li>
 * <li>작성일 : 2009. 12. 17.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ExplorerDropAdapterAssistant extends CommonDropAdapterAssistant {

    /**
     * @see org.eclipse.ui.navigator.CommonDropAdapterAssistant#handleDrop(org.eclipse.ui.navigator.CommonDropAdapter,
     *      org.eclipse.swt.dnd.DropTargetEvent, java.lang.Object)
     */
    @Override
    public IStatus handleDrop(CommonDropAdapter dropAdapter, DropTargetEvent event, Object target) {

        switch (event.detail) {
            case DND.DROP_DEFAULT:
            case DND.DROP_MOVE:
                return handleDropMove(target, event);
            case DND.DROP_COPY:
                return handleDropCopy(target, event);
            default:
                break;
        }

        return Status.CANCEL_STATUS;
    }

    /**
     * drop copy action from explorer to explorer.
     * 
     * @param target
     * @param event
     * @return IStatus
     */
    private IStatus handleDropCopy(Object target, DropTargetEvent event) {

        final EObject eobject = ((ITreeNode) target).getEObject();

        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        final List<EObject> sel = ProjectUtil.getSelectedEObjects(selection);

        if (sel.contains(eobject)) {
            return Status.CANCEL_STATUS;
        }
        for (EObject eobj : sel) {
            if (eobj instanceof Model) {
                return Status.CANCEL_STATUS;
            }
        }
        // DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
        // {
        //
        // /**
        // * @see org.eclipse.gef.commands.Command#execute()
        // */
        // @Override
        // public void execute() {
        // ProjectUtil.pasteElements(eobject, new ArrayList<Object>(sel));
        // }
        //
        // });
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                public void doExecute() {
                    // 2012-01-09 다이어그램 복사 기능 추가때문에 변경함.
//                    ProjectUtil.pasteElements(eobject, new ArrayList<Object>(sel));
                    new ElementPaster().paste(eobject, new ArrayList<Object>(sel)); 
                }
            });

        return Status.OK_STATUS;
    }

    /**
     * drop move action from explorer to explorer.
     * 
     * @param target
     * @param event
     * @return IStatus
     */
    private IStatus handleDropMove(Object target, DropTargetEvent event) {

        final EObject eobject = ((ITreeNode) target).getEObject();

        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        final List<EObject> sel = ProjectUtil.getSelectedEObjects(selection);

        if (sel.contains(eobject)) {
            return Status.CANCEL_STATUS;
        }

        Property property = null;
        boolean flag = false;
        String strMessage = UICoreConstant.EMPTY_STRING;
        EObject parentEobj = null;
        for (EObject object : sel) {
            // 단편화된 요소
            if (AdapterFactoryEditingDomain.isControlled(object)) {
                flag = true;
                if (object instanceof NamedElement) {
                    strMessage = ((NamedElement) object).getName() + UICoreConstant.PROJECT_CONSTANTS__BLANK
                        + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_FRAGMENTED_ELEMENT
                        + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                }
                break;
            }
            parentEobj = eobject.eContainer();
            if (object instanceof Property) {
                property = (Property) object;
                if (null != property.getAssociation()) {
                    flag = true;
                    strMessage = property.getName();
                    break;
                }
            } else if (object instanceof Model) {
                // 선택한 요소가 Model인 경우
                flag = true;
                strMessage = ((Model) object).getName() + UICoreConstant.PROJECT_CONSTANTS__BLANK
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_MODEL
                    + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                break;
            }

            // 부모-자식 관계
            while (parentEobj != null) {
                if (object.equals(parentEobj)) {
                    flag = true;
                    if (object instanceof NamedElement) {
                        strMessage = ((NamedElement) object).getName() + UICoreConstant.PROJECT_CONSTANTS__BLANK
                            + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT + UMLMessage.LABEL_CHILD_PARENT
                            + UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT;
                    }
                    break;
                }
                parentEobj = parentEobj.eContainer();
            }

        }
        if (flag) {
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.UML_MODELER,
                UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_MOVE, strMessage));
            return Status.CANCEL_STATUS;
        }

        // DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
        // {
        //
        // /**
        // * @see org.eclipse.gef.commands.Command#execute()
        // */
        // @Override
        // public void execute() {
        // ProjectUtil.moveElements(eobject, new ArrayList<Object>(sel));
        // }
        //
        // });
//        ProjectResourceSetListenerController.getInstance().fireSkipChangeEvent(true);
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                public void doExecute() {
                    ProjectUtil.moveElements(eobject, new ArrayList<Object>(sel));
                }
            });
//        BusyIndicator.showWhile(event.display, new Runnable() {
//        
//            public void run() {
//                List<Resource> resources = ResourceManager.getInstance().referenceResource(eobject);
//                resources.add(eobject.eResource());
//                
//                for (EObject sourceEObject : sel) {
//                    resources.addAll(ResourceManager.getInstance().referenceResource(sourceEObject));
//                    resources.add(sourceEObject.eResource());
//                }
//                
//                for (Iterator<Resource> ir = resources.iterator(); ir.hasNext();) {
//                    Resource rs = ir.next();
//
//                    if (rs != null && ProjectUtil.isModelFile(rs)) {
//                        try {
//                            rs.save(DomainUtil.getSaveOptions());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
        
        return Status.OK_STATUS;
    }

    /**
     * @see org.eclipse.ui.navigator.CommonDropAdapterAssistant#validateDrop(java.lang.Object,
     *      int, org.eclipse.swt.dnd.TransferData)
     */
    @Override
    public IStatus validateDrop(Object object, int operation, TransferData transferType) {
        if (object == null || transferType == null)
            return Status.CANCEL_STATUS;

        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        
        // validate target
        if (object instanceof ITreeNode) {
            EObject target = ((ITreeNode) object).getEObject();
            List<EObject> selections = ProjectUtil.getSelectedEObjects(selection);
            
            for (EObject source : selections) {
                if (source instanceof Diagram || source instanceof Activity || source instanceof org.eclipse.uml2.uml.Package) {
                    if ( target instanceof Diagram) {
                        return Status.CANCEL_STATUS;
                    }
                }
                
                if (source instanceof Activity || source instanceof org.eclipse.uml2.uml.Package) {
                    if (target instanceof Activity) {
                        return Status.CANCEL_STATUS;
                    }
                }
                
                // Interaction 에 시퀀스다이어그램은 1개만 허용. 
                if (source instanceof Diagram) {
                    if (target instanceof Interaction) {
                        EAnnotation annotation = ((Interaction) target).getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                        if (annotation != null) {
                            EList<EObject> contents = annotation.getContents();

                            for (EObject content : contents) {
                                if (content instanceof Diagram) {
                                    if (((Diagram) content).getType() == DiagramType.SEQUENCE_DIAGRAM) {
                                        return Status.CANCEL_STATUS;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            if (target instanceof UseCase) {
                for (EObject source : selections) {
                    // TODO PNS NCP Def. TYPE 설정 재검토해야함. component, interface
                    if (source instanceof org.eclipse.uml2.uml.Class || source instanceof Collaboration
                        || source instanceof Interaction || source instanceof Diagram) {
                        if (isSupportedType(transferType)) {
                            return Status.OK_STATUS;
                        }
                    } else {
                        return Status.CANCEL_STATUS;
                    }
                }
            }

            // validate transfer
            if (isSupportedType(transferType))
                return Status.OK_STATUS;
        }

        return Status.CANCEL_STATUS;

    }

}
