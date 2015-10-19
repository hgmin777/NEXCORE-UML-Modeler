/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.command.DeleteUMLPropertyCommand;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.ClearGarbageMessageCommand;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairBehaviorExecutionCommand;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairDanglingObjectCommand;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairMessageConnection;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairMessageEventCommand;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairPropertyCommand;
import nexcore.tool.uml.ui.core.diagram.command.umlmodel.RepairSequenceDiagramCommand;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : RepairAction</li>
 * <li>작성일 :  2010. 3. 26.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class RepairAction extends CommonActionDelegate {

    /**
     * objList
     */
    private List<EObject> objList;

    /**
     * 생성자
     */
    public RepairAction() {
        objList = new ArrayList<EObject>();
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        objList.clear();
        objList = ProjectUtil.getSelectedEObjects(selection);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run(IAction action) {

        // DomainUtil.run(new TransactionalAction() {
        /**
         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
         */
        // @Override
        // public void doExecute() {
        // if(objList == null || objList.size() < 1) {
        // return;
        // }
        // if(objList.get(0) instanceof Model){
        // diagrams = new ArrayList<Diagram>();
        // diagrams = getDiagrams((Element) objList.get(0));
        // }
        // List<AbstractNode> aNodes;
        // if(!diagrams.isEmpty()){
        // for(Diagram diagram : diagrams){
        // if(diagram.getType().equals(DiagramType.SEQUENCE_DIAGRAM)){
        // fixWrongModel(diagram);
        // }
        // }
        // }
        // }
        // });
        this.selectedEObject = objList.get(0);

        if (!(this.selectedEObject instanceof org.eclipse.uml2.uml.Package)) {
            return;
        }
        String strFileName = openFileDialog();
        if (null == strFileName || 0 == strFileName.length()) {
            return;
        }

        // Workbook workbook = new Workbook();
        // try {
        // workbook.open(strFileName);
        // Worksheets worksheets = workbook.getWorksheets();
        // Worksheet sheetRMID = worksheets.getSheet(0);
        // Worksheet sheetUCID = worksheets.getSheet(1);
        //     
        // ImportRQInformationCommand command = new
        // ImportRQInformationCommand();
        // command.setModel((Model)this.eobject);
        // command.setRmidSheet(sheetRMID);
        // command.setUcidSheet(sheetUCID);
        //     
        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // org.eclipse.uml2.uml.Package _package =
        // (org.eclipse.uml2.uml.Package) this.eobject;
        // clearGarbageMessage(_package);
        // // clearMessageEvent(_package);
        // clearProperty(_package);
        // clearLifeline(_package);

    }

    /**
     * 
     * 
     * @param diagram
     *            void
     */
    private void fixWrongModel(Object obj) {
        List<AbstractNode> aNodes = null;
        List<NotationNode> behaviors = null;

        if (obj instanceof Diagram) {
            aNodes = ((Diagram) obj).getNodeList();
        }

        if (aNodes != null && !aNodes.isEmpty()) {
            for (AbstractNode node : aNodes) {
                if (node instanceof LifeLineNode) {
                    behaviors = ((LifeLineNode) node).getBehaviorList();
                    for (NotationNode behaviorNode : behaviors) {
                        behaviorNode.setParent(((LifeLineNode) node).getLine());
                    }
                }
            }
        }
    }

    /**
     * diagrams
     */
    private List<Diagram> diagrams;

    /**
     * 
     * 
     * @param model
     * @return List<Diagram>
     */
    private List<Diagram> getDiagrams(Element element) {
        EList<EAnnotation> eAnnotations = element.getEAnnotations();
        EList<EObject> eObjects;
        for (EAnnotation eAnnotation : eAnnotations) {
            eObjects = eAnnotation.getContents();
            for (EObject eObj : eObjects) {
                if (eObj instanceof Diagram) {
                    if (!diagrams.contains(eObj)) {
                        diagrams.add((Diagram) eObj);
                    }

                }
            }
        }

        if (element instanceof Model) {
            for (Element childElement : ((Model) element).getPackagedElements()) {
                getDiagrams(childElement);
            }
        } else if (element instanceof PackageableElement) {
            for (EObject childElement : ((PackageableElement) element).eContents()) {
                if (childElement instanceof Element) {
                    getDiagrams((Element) childElement);
                } else if (childElement instanceof EAnnotation) {
                    eObjects = ((EAnnotation) childElement).getContents();
                    for (EObject eObj : eObjects) {
                        if (eObj instanceof Diagram) {
                            if (!diagrams.contains(eObj)) {
                                diagrams.add((Diagram) eObj);
                            }
                        }
                    }
                }

            }
        }

        return diagrams;
    }

    /**
     * clearGarbageMessage
     *  
     * @param _package void
     */
    private void clearGarbageMessage(org.eclipse.uml2.uml.Package _package) {
        EObject obj;
        List<AbstractView> viewModel;
        Message message;
        CompoundCommand command = new CompoundCommand();
        for (TreeIterator<EObject> iter = _package.eAllContents(); iter.hasNext();) {
            obj = iter.next();
            if (obj instanceof Message) {
                message = (Message) obj;
                viewModel = UMLManager.getRelatedViewModel(message);
                if (0 == viewModel.size()) {
                    ClearGarbageMessageCommand clearCommand = new ClearGarbageMessageCommand(message);
                    command.add(clearCommand);
                    Log.info(message.getQualifiedName());
                    // Log.error(EcoreUtil.getIdentification(message) + ":" +
                    // message.getSendEvent() + ":" + message.getReceiveEvent()
                    // + ":" + ((MessageOccurrenceSpecification)
                    // message.getSendEvent()).getEvent() + ":" +
                    // ((MessageOccurrenceSpecification)
                    // message.getReceiveEvent()).getEvent());
                }
            }
        }
        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(command);
    }

    /**
     * clearMessageEvent
     *  
     * @param _package void
     */
    private void clearMessageEvent(org.eclipse.uml2.uml.Package _package) {
        EObject obj;
        List<Element> elementList;
        MessageEvent messageEvent;
        for (TreeIterator<EObject> iter = _package.eAllContents(); iter.hasNext();) {
            obj = iter.next();
            if (obj instanceof MessageEvent) {
                messageEvent = (MessageEvent) obj;
                elementList = UMLManager.getRelatedUMLModel(messageEvent);
                if (2 != elementList.size()) {
                    Log.error(obj);
                }
            }
        }
    }

    /**
     * clearLifeline
     *  
     * @param _package void
     */
    private void clearLifeline(org.eclipse.uml2.uml.Package _package) {
        EObject obj;
        List<AbstractView> viewModel;
        Property property;
        int i = 0;
        CompoundCommand command = new CompoundCommand();
        Command deleteCommand;
        for (TreeIterator<EObject> iter = _package.eAllContents(); iter.hasNext();) {
            obj = iter.next();
            if (obj instanceof Lifeline) {
                viewModel = UMLManager.getRelatedViewModel((Element) obj);
                if (0 == viewModel.size()) {
                    Log.error(((Lifeline) obj).getInteraction().getQualifiedName()
                        + UICoreConstant.PROJECT_CONSTANTS__TRIPLE_COLON + UMLManager.getRelatedElement((Element) obj));
                }
            }
        }

    }

    /**
     * clearProperty
     *  
     * @param _package void
     */
    private void clearProperty(org.eclipse.uml2.uml.Package _package) {
        EObject obj;
        List<Element> elementList;
        List<AbstractView> viewModel;
        Property property;
        boolean flag = false;
        int i = 0;
        CompoundCommand command = new CompoundCommand();
        Command deleteCommand;
        for (TreeIterator<EObject> iter = _package.eAllContents(); iter.hasNext();) {
            obj = iter.next();
            if (obj instanceof Property) {
                property = (Property) obj;
                if (property.eContainer() instanceof Collaboration) {
                    elementList = UMLManager.getRelatedElement(property);
                    flag = false;
                    for (Element element : elementList) {
                        if (element instanceof Lifeline) {
                            viewModel = UMLManager.getRelatedViewModel(element);
                            if (0 == viewModel.size()) {
                                continue;
                            }
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        deleteCommand = new DeleteUMLPropertyCommand(property);
                        command.add(deleteCommand);
                        // if ("영업대표".equals(property.getName())) {
                        // i = 0;
                        // }
                        // if ("추가기간단가입력".equals(property.getName())) {
                        // i = 0;
                        // }
                        // if ("제안자원기간연장".equals(property.getName())) {
                        // i = 0;
                        // }
                        // if ("자원계획".equals(property.getName())) {
                        // i = 0;
                        // }
                        if (1 != elementList.size()) {
                            Log.error(EcoreUtil.getIdentification(property)
                                + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT + property);
                            Log.error(UMLMessage.LABEL_RELATED + elementList.size());
                        }
                    }
                }
            }
        }
        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(command);
    }

    /**
     * first
     *  
     * @param _package void
     */
    @SuppressWarnings("unused")
    private void first(org.eclipse.uml2.uml.Package _package) {
        EObject obj;
        Diagram diagram;
        Relation relation;

        CompoundCommand command = new CompoundCommand();
        RepairSequenceDiagramCommand repairSequenceDiagramCommand;
        RepairMessageConnection repairMessageConnectionCommand;
        BehaviorExecutionSpecification behaviorExecutionSpecification;
        RepairBehaviorExecutionCommand repairBehaviorExecutionCommand;
        RepairPropertyCommand repairPropertyCommand;
        RepairMessageEventCommand repairMessageEventCommand;

        for (TreeIterator<EObject> iter = _package.eAllContents(); iter.hasNext();) {
            obj = iter.next();
            if (obj instanceof Diagram) {
                diagram = (Diagram) obj;
                if (diagram.getType().equals(DiagramType.SEQUENCE_DIAGRAM)) {
                    repairSequenceDiagramCommand = new RepairSequenceDiagramCommand();
                    repairSequenceDiagramCommand.setDiagram(diagram);
                    command.add(repairSequenceDiagramCommand);
                }
            }
            if (obj instanceof Property) {
                repairPropertyCommand = new RepairPropertyCommand();
                repairPropertyCommand.setProperty((Property) obj);
                command.add(repairPropertyCommand);
            }
            if (obj instanceof MessageEvent) {
                repairMessageEventCommand = new RepairMessageEventCommand();
                repairMessageEventCommand.setMessageEvent((MessageEvent) obj);
                command.add(repairMessageEventCommand);
            }
            if (obj instanceof Relation) {
                relation = (Relation) obj;
                if (relation.getRelationType().equals(RelationType.ASYNCHRONOUS_MESSAGE)
                    || relation.getRelationType().equals(RelationType.SYNCHRONOUS_MESSAGE)
                    || relation.getRelationType().equals(RelationType.REPLY_MESSAGE)
                    || relation.getRelationType().equals(RelationType.MESSAGE)) {
                    if (null == relation.getUmlModel()) {
                        repairMessageConnectionCommand = new RepairMessageConnection();
                        repairMessageConnectionCommand.setRelation(relation);
                        command.add(repairMessageConnectionCommand);
                    }
                }
            }
            if (obj instanceof BehaviorExecutionSpecification) {
                behaviorExecutionSpecification = (BehaviorExecutionSpecification) obj;
                repairBehaviorExecutionCommand = new RepairBehaviorExecutionCommand();
                repairBehaviorExecutionCommand.setBehaviorExecutionSpecification(behaviorExecutionSpecification);
                command.add(repairBehaviorExecutionCommand);

            }

        }
        if (command.isEmpty()) {
            return;
        }

        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(command);
    }

    /**
     * diagnostic
     *   void
     */
    private void diagnostic() {
        Diagnostic diagnostic = Diagnostician.INSTANCE.validate(this.selectedEObject);
        BasicDiagnostic basicDiagnostic;
        RepairDanglingObjectCommand repairLifeLineCommand;
        switch (diagnostic.getSeverity()) {
            case Diagnostic.ERROR:
                Log.error(diagnostic.getMessage());

                Diagnostic childDiagnostic = null;
                for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext();) {
                    childDiagnostic = (Diagnostic) i.next();

                    switch (childDiagnostic.getSeverity()) {
                        case Diagnostic.ERROR:
                            Log.error("\t" + childDiagnostic.getMessage()); //$NON-NLS-1$
                            if (childDiagnostic instanceof BasicDiagnostic) {
                                basicDiagnostic = (BasicDiagnostic) childDiagnostic;
                                if (3 == basicDiagnostic.getCode()) {
                                    for (Object object : basicDiagnostic.getData()) {
                                        if (object instanceof EObject) {
                                            Log.error(EcoreUtil.getIdentification((EObject) object) + object);
                                        }
                                    }
                                }
                            }
                        case Diagnostic.WARNING:
                            Log.warn("\t" + childDiagnostic.getMessage()); //$NON-NLS-1$

                        default:
                            break;
                    }

                }

            case Diagnostic.WARNING:
                Log.warn(diagnostic.getMessage());

                for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext();) {
                    childDiagnostic = (Diagnostic) i.next();

                    switch (childDiagnostic.getSeverity()) {
                        case Diagnostic.ERROR:
                            Log.error("\t" + childDiagnostic.getMessage()); //$NON-NLS-1$
                        case Diagnostic.WARNING:
                            Log.warn("\t" + childDiagnostic.getMessage()); //$NON-NLS-1$

                        default:
                            break;
                    }

                }

            default:
                break;
        }
    }

    // SWT.OPEN
    /**
     * openFileDialog
     *  
     * @param type
     * @return String
     */
    public String openFileDialog(int type) {

        FileDialog dlg = new FileDialog(Display.getCurrent().getActiveShell(), type);
        dlg.setFilterNames(new String[] { UMLMessage.LABEL_XLS_EXTENSION });
        dlg.setFilterExtensions(new String[] { UMLMessage.LABEL_XLS_EXTENSION });
        String fileName = dlg.open();

        return fileName;
    }

    /**
     * openFileDialog
     *  
     * @return String
     */
    public String openFileDialog() {
        return openFileDialog(SWT.OPEN);
    }
}
