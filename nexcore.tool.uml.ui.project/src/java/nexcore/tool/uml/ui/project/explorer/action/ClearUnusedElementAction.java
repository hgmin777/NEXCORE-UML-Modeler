/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.command.DeleteUMLElementCommandFactory;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

/**
 * 미사용 요소들을 삭제한다. 시퀀스 다이어그램에서 사용되지 않는 라이프라인, 메세지 및 이와 관계된 요소들.
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : ClearUnusedElementAction</li>
 * <li>작성일 : 2011. 11. 25.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ClearUnusedElementAction extends CommonActionDelegate {

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {

        if (!(selectedEObject instanceof Namespace)) {
            return;
        }

        Namespace selectedElement = (Namespace) selectedEObject;
        if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
            UMLMessage.getMessage(UMLMessage.LABEL_DELETE_UNUSED_ELEMENT),
            UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE_UNUSED_ELEMENT, selectedElement.getQualifiedName()))) {
            return;
        }

        ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());
        dialog.create();
        dialog.getProgressMonitor().beginTask(UMLMessage.LABEL_DELETE_UNUSED_ELEMENT, IProgressMonitor.UNKNOWN);
        dialog.open();

        /********* 미사용 요소들 추출. *********/
        EList<Lifeline> lifelineList = null; // 라이프라인의 리스트.
        EList<Message> messageList = null; // 메시지의 리스트.
        EList<AbstractConnection> connectionList = null; // 다이어그램에 그려진 커넥션 리스트.
        EList<AbstractNode> nodeList = null; // 다이어그램에 그려진 노드 리스트.
        
        // 다이어그램에 그려진 노드의 라이프라인 리스트.
        List<Lifeline> lifelineNodeList = new ArrayList<Lifeline>();
        // 다이어그램에 그려진 커넥션의 메시지 리스트.
        List<Message> messageConnectionList = new ArrayList<Message>();

        // EObject로 만들어야 되는 것 같다..
        List<Element> deleteList = new ArrayList<Element>(); // 삭제할 요소들을 모은다.

//        List<Diagram> sequencediagramList = ModelManager.getAllDiagramList(selectedElement,
//            DiagramType.SEQUENCE_DIAGRAM);
//        for (Diagram diagram : sequencediagramList) {
//            if (diagram.getParent() instanceof Interaction) {
//                Interaction interaction = (Interaction) diagram.getParent();
//
//                /********* 미사용 MessageOccurenceSpecification, BehaviorExecutionSpecification 체크. *********/
//                // 라이프라인과 연결되지 않은 MessageOccurenceSpecification과 BehaviorExecutionSpecification, Event.
//                EList<InteractionFragment> fragments = interaction.getFragments();
//                for (InteractionFragment interactionFragment : fragments) {
//                    if (interactionFragment instanceof MessageOccurrenceSpecification) {
//                        MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) interactionFragment;
//                        if (null == mos.getMessage()) {
//                            deleteList.add(interactionFragment);
//                            if (null != mos.getEvent()) {
//                                deleteList.add(mos.getEvent());
//                            }
//                        }
//                    } else if (interactionFragment instanceof BehaviorExecutionSpecification) {
//                        BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) interactionFragment;
//                        if (null == bes.getStart() || null == bes.getFinish()) {
//                            deleteList.add(interactionFragment);
//                        }
//                    }
//                }
//                
//                /********* 미사용 라이프라인 체크. *********/
//                // 다이어그램에 그려진 라이프라인 리스트 생성.
//                nodeList = diagram.getNodeList();
//                lifelineNodeList = new ArrayList<Lifeline>();
//                for (AbstractNode abstractNode : nodeList) {
//                    Element element = abstractNode.getUmlModel();
//                    if (element instanceof Lifeline) {
//                        lifelineNodeList.add((Lifeline) element);
//                    }
//                }
//
//                // 라이프라인이 다이어그램에 그려져 있는 지 체크.
//                lifelineList = interaction.getLifelines();
//                for (Lifeline lifeline : lifelineList) {
//                    if (!lifelineNodeList.contains(lifeline)) {
//                        deleteList.add(lifeline);
//                    }
//                }
//
//                /********* 미사용 메시지 체크. *********/
//                // 다이어그램에 그려진 메시지 리스트 생성
//                connectionList = diagram.getConnectionList();
//                messageConnectionList = new ArrayList<Message>();
//                for (AbstractConnection abstractConnection : connectionList) {
//                    Element element = abstractConnection.getUmlModel();
//                    if (element instanceof Message) {
//                        messageConnectionList.add((Message) element);
//                    }
//                }
//
//                // 메시지가 다이어그램에 그려져 있는 지 체크.
//                messageList = interaction.getMessages();
//                for (Message message : messageList) {
//                    if (!messageConnectionList.contains(message)) {
//                        deleteList.add(message);
//                    }
//                }
//            }
//        }

        /********* 미사용 요소들 삭제. *********/
        // TODO deleteList를 삭제할 수 있는 커맨드 호출해서 실행.
        Command deleteCommand;
        CompoundCommand deleteCommandList = new CompoundCommand();
        List<IMarker> deleteMarkerList = new ArrayList<IMarker>();
        // deleteCommandList.add(diagramDelete);
        for (EObject eobject : deleteList) {
            // UMLManager.clearStereotype((Element) eobject);
            // EcoreUtil.remove(eobject);
            NamedElement obj = (NamedElement) eobject;

            deleteCommand = DeleteUMLElementCommandFactory.getCommand(obj);
            if (null == deleteCommand) {
                continue;
            } else {
                deleteCommandList.add(deleteCommand);
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(obj);

                // 마커 제거
                IPath path = new Path(obj.eResource().getURI().toFileString());
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
                try {
                    IMarker[] markers = file.findMarkers(UICoreConstant.VALIDATION_MARKER_TYPE,
                        true,
                        IResource.DEPTH_INFINITE);
                    for (IMarker marker : markers) {
                        String location = (String) marker.getAttribute(IMarker.LOCATION);
                        if (obj.getQualifiedName().equals(location) && !deleteMarkerList.contains(marker)) {
                            deleteMarkerList.add(marker);
                        }
                    }

                } catch (CoreException e) {}

            }
        }
		if (0 != deleteCommandList.getCommands().size()) {
			for (int i = 0; i < deleteMarkerList.size(); i++) {
				try {
					if (deleteMarkerList.get(i).exists()) {
						deleteMarkerList.get(i).delete();
					}
				} catch (CoreException e) {
					Log.error(e);
				}
			}
			((UMLDiagramCommandStack) DomainRegistry.getUMLDomain()
					.getCommandStackListener()).execute(deleteCommandList);
			// deleteResource();
		}
        dialog.getProgressMonitor().done();
        dialog.close();

        MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
            UMLMessage.LABEL_DELETE_UNUSED_ELEMENT_COMPLETED,
            UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE_UNUSED_ELEMENT_COMPLETED, String.valueOf(deleteCommandList.size())));
    }
}
