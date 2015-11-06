/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
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
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : ApplyStereotypeAction</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ApplyStereotypeAction extends SelectionAction {

    /**
     * selectedList
     */
    private List<Element> selectedList = new ArrayList<Element>();

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        
        selectedList = new ArrayList<Element>();
        
        for(Object obj : getSelectedObjects()){
            Object element = ((EditPart) obj).getModel();
            
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
                    if( null == ((AbstractView)model).getUmlModel() || DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                        return false;
                    }
                }
            }
            
            NotationNode selectedView = null;
            if (element instanceof NotationNode) {
                selectedView = (NotationNode) element;
                if( null != selectedView.getUmlModel() ){
                    if(!selectedList.contains(selectedView.getUmlModel())){
                        selectedList.add(selectedView.getUmlModel());
                    }
                }
            }
            if (selectedView == null) {
                return false;
            }
        }
        return true;
    }

    /** Add Attribute Action ID */
    public static final String APPLY_STEREOTYPE_ACTIONID = "ApplyStereotypeAction";

    /**
     * @param part
     */
    public ApplyStereotypeAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_APPLY_STEREOTYPE_ACTION));
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
        setText(UMLMessage.LABEL_APPLY_STEREOTYPE);
        setId(APPLY_STEREOTYPE_ACTIONID);
        
    }
    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
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
        
        for(Object obj : selectedList){

            if(obj instanceof NamedElement){
                NamedElement namedElement = (NamedElement) obj;
                for(Stereotype stereotype : namedElement.getApplicableStereotypes()){
                    
                    EClass eClass = stereotype.getDefinition();
                    EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
                    List<Object> typeList = new ArrayList<Object>();
                    for(EStructuralFeature eStructurealFeature : eList){
                        if(eStructurealFeature instanceof EReference){
                            EClassifier eClassifier = eStructurealFeature.getEType();
                            typeList.add(eClassifier);
                        }
                    }
                    
                    if(!applicableStereotype.contains(stereotype)){
                        applicableStereotype.add(stereotype);
                    }
                    if(!applicableStereotypeMap.containsKey(stereotype)){
                        applicableStereotypeMap.put(stereotype, typeList);
                    }
                }
                for(Stereotype stereotype : namedElement.getAppliedStereotypes()){
                    
                    EClass eClass = stereotype.getDefinition();
                    EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
                    List<Object> typeList = new ArrayList<Object>();
                    for(EStructuralFeature eStructurealFeature : eList){
                        if(eStructurealFeature instanceof EReference){
                            EClassifier eClassifier = eStructurealFeature.getEType();
                            typeList.add(eClassifier);
                        }
                    }
                    
                    if(!appliedStereotype.contains(stereotype)){
                        appliedStereotype.add(stereotype);
                    }
                    if(!appliedStereotypeMap.containsKey(stereotype)){
                        appliedStereotypeMap.put(stereotype, typeList);
                    }
                }
            }
        }

        SelectStereotypeDialog selectStereotypeDialog = new SelectStereotypeDialog(null, applicableStereotype, appliedStereotype, applicableStereotypeMap, appliedStereotypeMap, selectedList);
//        List<Stereotype> selectedStereotypeList = selectStereotypeDialog.getSelectedStereotypeList();
        CompoundCommand commands = new CompoundCommand();
        if (selectStereotypeDialog.open() == Window.OK) {
            
            for( Object object : selectedList ) {
                if( object instanceof Element ) { 
                    Element element = (Element) object ;
                    
                    for( Stereotype selected : element.getAppliedStereotypes() ) { 
                        
                        if( !selectStereotypeDialog.getSelectedStereotypeList().contains(selected) ) {
                            UnApplyStereotypeCommand command = new UnApplyStereotypeCommand(DomainRegistry.getEditingDomain());
                            command.setElement(element);
                            command.setStereotype(selected);
                            commands.append(command);
                        }
                    }
                }
            }
            commands.execute();
            
            for( Stereotype selected : selectStereotypeDialog.getSelectedStereotypeList() ) { 
                
                if(appliedStereotype.contains(selected)){
                   continue; 
                }
                for( Object object : selectedList ) { 
                    if( object instanceof Element ) { 
                        Element element = (Element) object ;

                        List<Object> objList = (List<Object>) applicableStereotypeMap.get( selected );
                 
                        for( Object child : objList ) {
                            EClass eClass = null;
                            if( child instanceof EClass ) {
                                eClass = (EClass) child;
                            }
                            
                            if( eClass.isSuperTypeOf(element.eClass()) ) {
                                ApplyStereotypeCommand command = new ApplyStereotypeCommand(DomainRegistry.getEditingDomain());
                                command.setElement(element);
                                command.setStereotype(selected);
                                commands.append(command);
                            }
                        }
                        
//                        if(objList.contains(element.eClass())){
//                            ApplyStereotypeCommand command = new ApplyStereotypeCommand(DomainRegistry.getEditingDomain());
//                            command.setElement(element);
//                            command.setStereotype(selected);
//                            commands.append(command);
//                        }
                    }
                }
            }
            commands.execute();
        }
        
        EditPart editPart = ProjectUtil.getDiagramEditPart();
        ProjectUtil.refreshChildEditPart( editPart );
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
         * @param element void
         */
        public void setElement(Element element) {
            this.element = element;
        }
        /**
         * 
         *  
         * @param stereotype void
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
            if(element.getAppliedStereotypes().contains(stereotype)){
                element.unapplyStereotype(stereotype);
            }
        }
        /**
         * 
         *  
         * @param element void
         */
        public void setElement(Element element) {
            this.element = element;
        }
        /**
         * 
         *  
         * @param stereotype void
         */
        public void setStereotype(Stereotype stereotype) {
            this.stereotype = stereotype;
        }
    }
}
