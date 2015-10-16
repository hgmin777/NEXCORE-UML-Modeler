/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.OCLExpressionUtil;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractLabelCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.StereotypeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.figure.NoteFigure;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;

import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DirectEditorManager</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DirectEditorManager extends DirectEditManager {

    /** RIGHT_BRACKET */
    private static final String RIGHT_BRACKET = ">>";//$NON-NLS-1$

    /** LEFT_BRACKET */
    private static final String LEFT_BRACKET = "<<";//$NON-NLS-1$

    /** notationNode */
    @SuppressWarnings("unused")
    private Object model;

    /** stringValue */
    @SuppressWarnings("unused")
    private String stringValue;

    /** EMPTY_TEXT */
    private static String EMPTY_TEXT = "";//$NON-NLS-1$

    /** string */
    private String string = null;

    /** editPart */
    private GraphicalEditPart editPart;

    /**
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public DirectEditorManager(GraphicalEditPart source, java.lang.Class editorType, CellEditorLocator locator,
    String stringValue) {
        super(source, editorType, locator);

        if (source.getModel() instanceof NotationNode) {
            model = (NotationNode) source.getModel();
        } else if (source.getModel() instanceof LabelNode) {
            model = (LabelNode) source.getModel();
        }

        this.stringValue = stringValue;
        this.editPart = source;
    }

    /**
     * DirectEditorManager
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public DirectEditorManager(GraphicalEditPart source, java.lang.Class editorType, CellEditorLocator locator) {
        this(source, editorType, locator, EMPTY_TEXT);
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {

        string = null;

        if (getEditPart() instanceof AbstractLabelCompartmentEditPart) {

            if (((AbstractLabelCompartmentEditPart) getEditPart()).isName()) {
                string = ((Label) getEditPart().getFigure().getChildren().get(1)).getText();
            } else if (((AbstractLabelCompartmentEditPart) getEditPart()).isStereotype()) {
                string = getKeyword(((LabelNode) getEditPart().getModel()).getUmlModel());
            } else if ((getEditPart().getFigure()) instanceof Label) {
                string = ((Label) (getEditPart().getFigure())).getText();
            }
        } else if (getEditPart() instanceof StereotypeEditPart) {
            string = getKeyword(((AbstractView) ((AbstractNotationNodeEditPart) getEditPart().getParent()).getModel()).getUmlModel());
        } else {
            AbstractNode node = (AbstractNode) editPart.getModel();
            if (NodeType.GUARD.equals(node.getNodeType())) { //결합단편의 Guard 체크
                InteractionConstraint guard = (InteractionConstraint) node.getUmlModel();
                OpaqueExpression expression = (OpaqueExpression) guard.getSpecification();
                string = expression.getBodies().get(0);
            } else if ((getEditPart().getFigure()) instanceof Label) {
                string = ((Label) (getEditPart().getFigure())).getText();
            } else if (((getEditPart().getFigure()) instanceof NoteFigure)) {
                string = ((NoteFigure) (getEditPart().getFigure())).getName();
            } else if (((getEditPart().getFigure()) instanceof TextFigure)) {
                string = ((TextFigure) (getEditPart().getFigure())).getName();
            } else if (getEditPart() instanceof AbstractNotationNodeEditPart) {
                Element element = ((AbstractView) ((AbstractNotationNodeEditPart) getEditPart()).getModel()).getUmlModel();
                string = ((NamedElement) element).getName();
            }
                
        }

        if (string != null) {

            if (!(getEditPart() instanceof NoteEditPart) & !(getEditPart() instanceof TextEditPart)) {
                if (getEditPart() instanceof StereotypeEditPart) {
                    string = string.replaceAll(LEFT_BRACKET, EMPTY_TEXT);
                    string = string.replaceAll(RIGHT_BRACKET, EMPTY_TEXT);
                    string = string.replaceAll("-", EMPTY_TEXT);//$NON-NLS-1$
                    string = string.replaceAll("#", EMPTY_TEXT);//$NON-NLS-1$
                    string = string.replaceAll("~", EMPTY_TEXT);//$NON-NLS-1$
                    string = string.replaceAll("\\+", EMPTY_TEXT);//$NON-NLS-1$
                }
            }
            getCellEditor().setValue(string);

            Text text = (Text) getCellEditor().getControl();
            text.selectAll();
        }

        if (editPart.getModel() instanceof AbstractNode) {
            final AbstractNode abstractNode = (AbstractNode) editPart.getModel();
            final NamedElement childElement = (NamedElement) abstractNode.getUmlModel();
            if (abstractNode.getNodeType().equals(NodeType.ATTRIBUTE)
                || abstractNode.getNodeType().equals(NodeType.OPERATION)) {
                getCellEditor().getControl().addKeyListener(new KeyListener() {
                    /*
                     * (non-Javadoc)
                     * 
                     * @see
                     * org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse
                     * .swt.events.KeyEvent)
                     */
                    public void keyReleased(KeyEvent e) {
                        // ignore
                    }

                    /*
                     * (non-Javadoc)
                     * 
                     * @see
                     * org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse
                     * .swt.events.KeyEvent)
                     */
                    public void keyPressed(KeyEvent e) {
                        if (e.keyCode == SWT.F3) {
                            Element element = null;
                            if (abstractNode.getUmlModel() != null) {
                                element = abstractNode.getUmlModel();
                            }
                            if (element != null) {
                                DomainRegistry.getEditingDomain()
                                    .getCommandStack()
                                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                                        @Override
                                        protected void doExecute() {
                                            AbstractNode parentNode = (AbstractNode) abstractNode.getParent();
                                            parentNode.setHeight(parentNode.getHeight() + 21);
                                            AbstractNode pParentNode = (AbstractNode) parentNode.getParent();
                                            pParentNode.setHeight(pParentNode.getHeight() + 21);
                                            createAttribute();
                                        }
                                    });
                            }
                        }
                        if (e.keyCode == SWT.F4) {
                            Element element = null;
                            if (abstractNode.getUmlModel() != null) {
                                element = abstractNode.getUmlModel();
                            }
                            if (element != null) {
                                DomainRegistry.getEditingDomain()
                                    .getCommandStack()
                                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                                        @Override
                                        protected void doExecute() {
                                            AbstractNode parentNode = (AbstractNode) abstractNode.getParent();
                                            parentNode.setHeight(parentNode.getHeight() + 21);
                                            AbstractNode pParentNode = (AbstractNode) parentNode.getParent();
                                            pParentNode.setHeight(pParentNode.getHeight() + 21);
                                            createOperation();
                                        }
                                    });
                            }
                        }

                        // ESC 키 입력 시.
                        // 잠정적인 기능 삭제. -> 모든 다이렉트 에디팅모드에서 적용되고 있으므로 불편한면도 있다.
//                        if (e.keyCode == 27) {
//                            DomainRegistry.getEditingDomain()
//                                .getCommandStack()
//                                .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
//                                    @Override
//                                    protected void doExecute() {
//                                        DeleteUMLElementCommandFactory.getCommand(childElement).execute();
//                                    }
//                                });
//                        }
                        // Enter 키 입력 시.
                        if (e.keyCode == 13) {
                            if (string != null) {
                                DomainRegistry.getEditingDomain()
                                    .getCommandStack()
                                    .execute(new UMLTextParser(DomainRegistry.getEditingDomain(),
                                        childElement,
                                        getCellEditor(),
                                        getEditPart().getViewer()));
                            }

                            Element element = null;
                            if (abstractNode.getUmlModel() != null) {
                                element = abstractNode.getUmlModel();
                            }
                            if (element != null) {
                                // Enter 입력 후 다음 Attribute 생성
                                if (abstractNode.getNodeType().equals(NodeType.ATTRIBUTE)) {
                                    DomainRegistry.getEditingDomain()
                                        .getCommandStack()
                                        .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                                            @Override
                                            protected void doExecute() {
                                                AbstractNode parentNode = (AbstractNode) abstractNode.getParent();
                                                parentNode.setHeight(parentNode.getHeight() + 21);
                                                AbstractNode pParentNode = (AbstractNode) parentNode.getParent();
                                                pParentNode.setHeight(pParentNode.getHeight() + 21);
                                                createAttribute();
                                            }
                                        });
                                    // Enter 입력 후 다음 Operation 생성
                                } else if (abstractNode.getNodeType().equals(NodeType.OPERATION)) {
                                    DomainRegistry.getEditingDomain()
                                        .getCommandStack()
                                        .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                                            /* (non-Javadoc)
                                             * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                                             */
                                            @Override
                                            protected void doExecute() {
                                                AbstractNode parentNode = (AbstractNode) abstractNode.getParent();
                                                parentNode.setHeight(parentNode.getHeight() + 21);
                                                AbstractNode pParentNode = (AbstractNode) parentNode.getParent();
                                                pParentNode.setHeight(pParentNode.getHeight() + 21);
                                                createOperation();
                                            }
                                        });
                                }
                            }
                        }
                    }
                });
            }
        }
        
        getCellEditor().getControl().addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent arg0) {
            }
            @Override
            public void focusGained(FocusEvent arg0) {
                
            }
        });
    }

    /**
     * @param element
     * @return
     */
    protected String getKeyword(Element element) {

        String keywordString;
        if (element.getKeywords().isEmpty()) {
            return keywordString = EMPTY_TEXT;
        }
        EList<String> list = element.getKeywords();
        if (0 == list.size()) {
            return keywordString = EMPTY_TEXT;
        }
        List<String> keywordList = new ArrayList<String>();
        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            keywordList.add(iter.next());
        }
        keywordString = OCLExpressionUtil.parseKeywordListToString(keywordList);
        return keywordString;
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#eraseFeedback()
     */
    @Override
    protected void eraseFeedback() {
//        if (getEditPart().getParent() != null) {
//            LayerManager.Helper.find(getEditPart()).getLayer(LayerConstants.FEEDBACK_LAYER).getChildren().clear();
//        }
        
        if( null != getEditPart() && null != getEditPart().getParent() ) {
            List<Object> feedbackList = LayerManager.Helper.find(getEditPart()).getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
            if(null != feedbackList && !feedbackList.isEmpty()){
                super.eraseFeedback();
            }
        }
        
        IWorkbench workbench;
        IWorkbenchWindow workbenchWindow = null;
        IWorkbenchPage workbenchPage = null;
        IEditorPart editorPart = null;
        workbench = PlatformUI.getWorkbench();
        if(null != workbench){
            workbenchWindow = workbench.getActiveWorkbenchWindow();
        }
        if(null != workbenchWindow){
            workbenchPage = workbenchWindow.getActivePage();
        }
        if(null != workbenchPage){
            editorPart = workbenchPage.getActiveEditor();
        }
        if(null != editorPart){
            if(editorPart instanceof AbstractDiagramEditor){
                Diagram diagram = ((AbstractDiagramEditor)editorPart).getDiagram();
                if(DiagramType.SEQUENCE_DIAGRAM.equals( diagram.getType() ) ){
                    List<Object> removeChildren = LayerManager.Helper.find(getEditPart()).getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                    if(null != removeChildren && !removeChildren.isEmpty()){
                        super.eraseFeedback();
                    }
                }
            }
        }
    }

    /**
     * createAttribute Enter Key 입력 후 Attribute 생성.
     */
    private void createAttribute() {

        Object element = ((AbstractNode) editPart.getModel()).getParent();

        Class classNode;
        DataType dataType;
        Enumeration enumeration;

        Property property = UMLHelper.createProperty();
        if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Class) {
            classNode = (Class) ((NotationNode) element).getUmlModel();
            property.setName((UMLManager.getPackagedUniqueName(classNode,
                UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
            classNode.getOwnedAttributes().add(property);
        }

        else if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.DataType) {
            dataType = (DataType) ((NotationNode) element).getUmlModel();
            property.setName((UMLManager.getPackagedUniqueName(dataType, UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
            dataType.getOwnedAttributes().add(property);
        }

        else if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Enumeration) {
            enumeration = (Enumeration) ((NotationNode) element).getUmlModel();
            property.setName((UMLManager.getPackagedUniqueName(enumeration,
                UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
            enumeration.getOwnedAttributes().add(property);
        }
    }

    /**
     * createOperation Enter Key 입력 후 Operation 생성.
     */
    private void createOperation() {

        Object element = ((AbstractNode) editPart.getModel()).getParent();

        Class classNode;
        Interface interfaceNode;

        if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Class) {
            classNode = (Class) ((NotationNode) element).getUmlModel();
            Operation operation = UMLHelper.createOperation();
            operation.setName((UMLManager.getPackagedUniqueName(classNode,
                UMLMessage.getMessage(UMLMessage.UML_OPERATION))));
            classNode.getOwnedOperations().add(operation);
        }

        else if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Interface) {
            interfaceNode = (Interface) ((NotationNode) element).getUmlModel();
            Operation operation = UMLHelper.createOperation();
            operation.setName((UMLManager.getPackagedUniqueName(interfaceNode,
                UMLMessage.getMessage(UMLMessage.UML_OPERATION))));
            interfaceNode.getOwnedOperations().add(operation);
        }
    }

}
