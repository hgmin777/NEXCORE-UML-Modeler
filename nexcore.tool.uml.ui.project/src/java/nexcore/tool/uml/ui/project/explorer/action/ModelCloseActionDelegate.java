/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.property.util.ModelHandler;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : ModelCloseActionDelegate</li>
 * <li>작성일 : 2012. 6. 26.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ModelCloseActionDelegate extends Action implements IObjectActionDelegate {
    
    private static final String NEW_LINE = "\r\n";
    
    /**
     * selection
     */
    ISelection selection;

    /**
     * targetPart
     */
    IWorkbenchPart targetPart;

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        Boolean useModelClose = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE);
        if(!useModelClose) {
//            UMLMessage.USE_MODEL_CLOSE
            // Window > Preferences > NEXCORE > UML Modeler > UMLMessage.USE_MODEL_CLOSE
            StringBuffer sb = new StringBuffer();
            sb.append("\"Window > Preferences > NEXCORE > UML Modeler > ").append( UMLMessage.MESSAGE_USE_MODEL_CLOSE).append("\"");
            sb.append(NEW_LINE).append(NEW_LINE);
            sb.append(UMLMessage.MESSAGE_CHECH_PREFERENCE);
            
            MessageDialog.openInformation(targetPart.getSite().getShell(), UMLMessage.LABEL_NOTIFICATION, sb.toString());
            return;
        }
        
        Set<Element> dependentDiagramList = new HashSet<Element>();
        Set<Resource> resourceHashSet = new HashSet<Resource>();
        
        for (Iterator<?> iterator = ((StructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            if (obj instanceof UMLFileTreeNode) {
                UMLFileTreeNode treeNode = (UMLFileTreeNode) obj;

                EObject eObject = treeNode.getEObject();
                resourceHashSet.add(eObject.eResource());
            }
        }
        
        for (Iterator<?> iterator = ((StructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            if (obj instanceof UMLFileTreeNode) {
                UMLFileTreeNode treeNode = (UMLFileTreeNode) obj;

                EObject eObject = treeNode.getEObject();
                Set<Element> inverseReferenceList = ResourceManager.getInstance().inverseReferenceElementList(eObject);

                if (!inverseReferenceList.isEmpty()) {
                    for (Iterator<?> ir = inverseReferenceList.iterator(); ir.hasNext();) {
                        Element element = (Element) ir.next();
                        
                        if( !resourceHashSet.contains(EcoreUtil.getRootContainer(element).eResource())) {
                            dependentDiagramList.add(element);
                        }
                    }
                }
            }
        }
        boolean question = false;
        if (!dependentDiagramList.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            // The following diagrams contain views or elements that depend on information from the resource(s) being closed.
            sb.append(UMLMessage.MESSAGE_DEPENDENT_INFORMATION).append(NEW_LINE).append(NEW_LINE);
            int i = 0;
            for (Iterator<?> ir = dependentDiagramList.iterator(); ir.hasNext();) {
                Element element = (Element) ir.next();
                if( element instanceof Relation) {
                    continue;
                }
                String name = ModelHandler.getInstance().getProperTitle(element);
                if( name == null ) {
//                    System.out.println("");
                }
                sb.append(name).append(NEW_LINE);
                if (i >= 2 && dependentDiagramList.size() - 3 > 0) {
                    sb.append("...............................................................................................................................")
                        .append(String.format(UMLMessage.LABEL_ETC_CNT, dependentDiagramList.size() - 3))
                        .append(NEW_LINE);
                    break;
                }
                i++;
            }
            // The dependent resources and/or diagrams will be closed. Do you wish to proceed?
            sb.append(NEW_LINE).append(UMLMessage.MESSAGE_DEPENDENT_INFORMATION2);

            question = MessageDialog.openQuestion(targetPart.getSite().getShell(), UMLMessage.LABEL_NOTIFICATION, sb.toString());

            if (question) {
                for (Iterator<?> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
                    Resource resource = (Resource) iterator.next();
                    // ResourceUnloader 에서 닫힘 처리 전에 active 상태를 알아야하기 때문에 여기서 먼저 닫힘 상태로 변경 한다
                    ResourceManager.getInstance().setActivation(resource.getURI(), false);
                }
                
                for (Iterator<?> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
                    Resource resource = (Resource) iterator.next();
                    ResourceUnloader.getInstance().put(resource);
                }
            }
        } else {
            for (Iterator<?> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
                Resource resource = (Resource) iterator.next();
                // ResourceUnloader 에서 닫힘 처리 전에 active 상태를 알아야하기 때문에 여기서 먼저 닫힘 상태로 변경 한다
                ResourceManager.getInstance().setActivation(resource.getURI(), false);
            }
            for (Iterator<?> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
                Resource resource = (Resource) iterator.next();
                ResourceUnloader.getInstance().put(resource);
            }
        }

        
//        Display.getDefault().syncExec(new Runnable() {
//            
//            @Override
//            public void run() {
//                nexcore.tool.uml.ui.core.util.ProjectUtil.refreshWholeExplorer();
//            }
//        });
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

}
