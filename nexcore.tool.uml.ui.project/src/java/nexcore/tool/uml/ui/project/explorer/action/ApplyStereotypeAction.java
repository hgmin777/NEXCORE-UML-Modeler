/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.diagram.action.SelectStereotypeDialog;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : ApplyStereotypeAction</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ApplyStereotypeAction implements IObjectActionDelegate {

    /** site */
    private IWorkbenchPartSite site;

    /** parentShell */
    private Shell parentShell;

    /** firstSelectedEObject */
    private List<Element> firstSelectedEObject;

    /**
     * ApplyStereotypeAction
     */
    public ApplyStereotypeAction() {
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // 워크벤치파트 사이트 가져오기
        this.site = targetPart.getSite();
        // 부모 쉘 가져오기
        if (site != null) {
            this.parentShell = site.getShell();
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    applyStereotype();
                }
            });
    }

    /**
     * applyStereotype
     */
    private void applyStereotype() {

        List<Stereotype> applicableStereotype = new ArrayList<Stereotype>();
        HashMap<Stereotype, Object> applicableStereotypeMap = new HashMap<Stereotype, Object>();

        List<Stereotype> appliedStereotype = new ArrayList<Stereotype>();
        HashMap<Stereotype, Object> appliedStereotypeMap = new HashMap<Stereotype, Object>();

        for (Object obj : firstSelectedEObject) {

            if (obj instanceof NamedElement) {
                NamedElement namedElement = (NamedElement) obj;
                for (Stereotype stereotype : namedElement.getApplicableStereotypes()) {

                    EClass eClass = stereotype.getDefinition();
                    EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
                    List<Object> typeList = new ArrayList<Object>();
                    for (EStructuralFeature eStructurealFeature : eList) {
                        if (eStructurealFeature instanceof EReference) {
                            EClassifier eClassifier = eStructurealFeature.getEType();
                            typeList.add(eClassifier);
                        }
                    }

                    if (!applicableStereotype.contains(stereotype)) {
                        applicableStereotype.add(stereotype);
                    }
                    if (!applicableStereotypeMap.containsKey(stereotype)) {
                        applicableStereotypeMap.put(stereotype, typeList);
                    }
                }
                for (Stereotype stereotype : namedElement.getAppliedStereotypes()) {

                    EClass eClass = stereotype.getDefinition();
                    EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
                    List<Object> typeList = new ArrayList<Object>();
                    for (EStructuralFeature eStructurealFeature : eList) {
                        if (eStructurealFeature instanceof EReference) {
                            EClassifier eClassifier = eStructurealFeature.getEType();
                            typeList.add(eClassifier);
                        }
                    }

                    if (!appliedStereotype.contains(stereotype)) {
                        appliedStereotype.add(stereotype);
                    }
                    if (!appliedStereotypeMap.containsKey(stereotype)) {
                        appliedStereotypeMap.put(stereotype, typeList);
                    }
                }
            }
        }

        SelectStereotypeDialog selectStereotypeDialog = new SelectStereotypeDialog(parentShell,
            applicableStereotype,
            appliedStereotype,
            applicableStereotypeMap,
            appliedStereotypeMap,
            firstSelectedEObject);
        // List<Stereotype> selectedStereotypeList =
        // selectStereotypeDialog.getSelectedStereotypeList();

        if (applicableStereotype.isEmpty()) {
            MessageDialog.openInformation(parentShell, UMLMessage.LABEL_MANAGEMENT_STEREOTYPE,// UMLMessage.LABEL_TO_BE_DETERMINED,
                UMLMessage.MESSAGE_APPLICABLE_STEREOTYPE_IS_EMPTY);// UMLMessage.MESSAGE_TO_BE_DETERMINED);
        } else {
            CompoundCommand commands = new CompoundCommand();
            if (selectStereotypeDialog.open() == Window.OK) {

                for (Object object : firstSelectedEObject) {
                    if (object instanceof Element) {
                        Element element = (Element) object;

                        for (Stereotype selected : element.getAppliedStereotypes()) {

                            if (!selectStereotypeDialog.getSelectedStereotypeList().contains(selected)) {
                                UnApplyStereotypeCommand command = new UnApplyStereotypeCommand(DomainRegistry.getEditingDomain());
                                command.setElement(element);
                                command.setStereotype(selected);
                                commands.append(command);
                            }
                        }
                    }
                }
                commands.execute();

                for (Stereotype selected : selectStereotypeDialog.getSelectedStereotypeList()) {

                    if (appliedStereotype.contains(selected)) {
                        continue;
                    }
                    for (Object object : firstSelectedEObject) {
                        if (object instanceof Element) {
                            Element element = (Element) object;

                            List<Object> objList = (List<Object>) applicableStereotypeMap.get(selected);

                            for (Object child : objList) {
                                EClass eClass = null;
                                if (child instanceof EClass) {
                                    eClass = (EClass) child;
                                }

                                if (eClass.isSuperTypeOf(element.eClass())) {
                                    ApplyStereotypeCommand command = new ApplyStereotypeCommand(DomainRegistry.getEditingDomain());
                                    command.setElement(element);
                                    command.setStereotype(selected);
                                    commands.append(command);
                                }
                            }

//                            if (objList.contains(element.eClass())) {
//                                ApplyStereotypeCommand command = new ApplyStereotypeCommand(DomainRegistry.getEditingDomain());
//                                command.setElement(element);
//                                command.setStereotype(selected);
//                                commands.append(command);
//                            }
                        }
                    }
                }
                commands.execute();
            }
        }

        EditPart editPart = ProjectUtil.getDiagramEditPart();
        ProjectUtil.refreshChildEditPart(editPart);
    }

    class ApplyStereotypeCommand extends RecordingCommand {

        /** element */
        private Element element;

        /** stereotype */
        private Stereotype stereotype;

        /**
         * @param domain
         */
        public ApplyStereotypeCommand(TransactionalEditingDomain domain) {
            super(domain);
        }

        /**
         * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
         */
        @Override
        protected void doExecute() {
            element.applyStereotype(stereotype);
        }

        /**
         * 
         * 
         * @param element
         *            void
         */
        public void setElement(Element element) {
            this.element = element;
        }

        /**
         * 
         * 
         * @param stereotype
         *            void
         */
        public void setStereotype(Stereotype stereotype) {
            this.stereotype = stereotype;
        }
    }

    class UnApplyStereotypeCommand extends RecordingCommand {

        /** element */
        private Element element;

        /** stereotype */
        private Stereotype stereotype;

        /**
         * @param domain
         */
        public UnApplyStereotypeCommand(TransactionalEditingDomain domain) {
            super(domain);
        }

        /**
         * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
         */
        @Override
        protected void doExecute() {
            if (element.getAppliedStereotypes().contains(stereotype)) {
                element.unapplyStereotype(stereotype);
            }
        }

        /**
         * 
         * 
         * @param element
         *            void
         */
        public void setElement(Element element) {
            this.element = element;
        }

        /**
         * 
         * 
         * @param stereotype
         *            void
         */
        public void setStereotype(Stereotype stereotype) {
            this.stereotype = stereotype;
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {

        firstSelectedEObject = new ArrayList<Element>();

        if (!(selection instanceof IStructuredSelection)) {
            return;
        }

        // 액션의 선택 객체 설정
        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        Object pickedUpObject;
        ITreeNode node;

        for (Iterator<Object> structureSelectionIterator = structureSelection.iterator(); structureSelectionIterator.hasNext();) {
            pickedUpObject = structureSelectionIterator.next();

            if (pickedUpObject instanceof ITreeNode) {
                node = (ITreeNode) pickedUpObject;

                firstSelectedEObject.add((Element) node.getEObject());
            }
        }
    }
}
