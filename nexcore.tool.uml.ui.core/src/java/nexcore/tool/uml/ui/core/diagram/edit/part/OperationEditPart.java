/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.edit.part</li>
 * <li>설 명 : AttributeCompartmentEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : OperationEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class OperationEditPart extends AbstractChildCompartmentEditPart {

    /**
     * STRING_RIGHT_BRACKET
     */
    private static final String STRING_RIGHT_BRACKET = ")";//$NON-NLS-1$

    /**
     * STRING__COMMA
     */
    private static final String STRING__COMMA = ", ";//$NON-NLS-1$

    /**
     * STRING_LEFT_BRACKET
     */
    private static final String STRING_LEFT_BRACKET = "(";//$NON-NLS-1$
    
    /**
     * STRING_COLON
     */
    private static final String STRING_COLON = ":";//$NON-NLS-1$

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NotationNode operationModel = (NotationNode) getModel();
        Operation operation = (Operation) operationModel.getUmlModel();
        Image image = UiCorePlugin.getDefault().getImageForUMLElement(operation);
        Label label = new Label(operation.getName(), image);
        label.setForegroundColor(ColorConstants.black);
        label.setBorder(new MarginBorders(0, 5, 0, 0));
        label.setToolTip(new Label(label.getText()));

        return label;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            NotationNode operationModel = (NotationNode) getModel();
            Operation operation = (Operation) operationModel.getUmlModel();
            Image image = UiCorePlugin.getDefault().getImageForUMLElement(operation);
            
            String operationName = operation.getName();

            String operationParameters = UICoreConstant.EMPTY_STRING;
            if( PreferenceUtil.INSTANCE.getPreferenceStore().getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_PARAMETERS) ){
                operationParameters = STRING_LEFT_BRACKET + operationParameters;
                int hasReturnParameter = 0;
                for(Parameter para : operation.getOwnedParameters()){
                    if(ParameterDirectionKind.RETURN == para.getDirection().getValue()){
                        hasReturnParameter = 1;
                        continue;
                    }
                }
                for(Parameter para : operation.getOwnedParameters()){
                    if(ParameterDirectionKind.RETURN == para.getDirection().getValue()){
                        continue;
                    }
                    String parameter = UICoreConstant.EMPTY_STRING;
                    if(null != para.getName()){
                        parameter = para.getName();
                    }
                    if( PreferenceUtil.INSTANCE.getPreferenceStore().getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_TYPE) ){
                        if( null != para.getType() && null != para.getType().getName() ){
                            parameter += STRING_COLON + para.getType().getName();
                        }
                    }
                    if( operation.getOwnedParameters().indexOf(para) != operation.getOwnedParameters().size() - hasReturnParameter - 1){
                        parameter += STRING__COMMA;
                    }
                    operationParameters += parameter; 
                    
                }
                operationName += operationParameters + STRING_RIGHT_BRACKET;
            }
            String operationType = UICoreConstant.EMPTY_STRING;
            if( PreferenceUtil.INSTANCE.getPreferenceStore().getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_TYPE) ){
                if(null != operation.getReturnResult()){
                    operationType = STRING_COLON + operation.getReturnResult().getType().getName();
                    operationName = operationName + operationType;
                }
            }
            

            Label label = (Label) getFigure();
            label.setIcon(image);
            label.setText(operationName);
            label.setToolTip(new Label(operationName));

        } catch (Exception e) {
            Log.error("OperationEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        // return super.getDragTracker(request);
        return new UMLDragTracker(this);
    }

    /**
     * @author 강경구
     * UMLDragTracker
     */
    public class UMLDragTracker extends DragEditPartsTracker {

        private EditPart sourceEditPart;

        public UMLDragTracker(EditPart sourceEditPart) {
            super(sourceEditPart);
            setDisabledCursor(SharedCursors.ARROW);
            this.sourceEditPart = sourceEditPart;
        }
        @Override
        protected void performDrag() {
            super.performDrag();
            
            EditPart sourceEditPart = getSourceEditPart();
            AbstractNode sourceNode = (AbstractNode) sourceEditPart.getModel();
            final Element element = sourceNode.getUmlModel();
            
            EditPart editPart = getRoot().getContents();
            Diagram diagram = (Diagram) editPart.getModel();
            List<AbstractNode> nodeList = diagram.getNodeList();

            ChangeBoundsRequest req = (ChangeBoundsRequest) getTargetRequest();
            org.eclipse.draw2d.geometry.Point cusorPoint = req.getMouseLocation();
            
            for(AbstractNode node : nodeList){
                if( node.getUmlModel() instanceof org.eclipse.uml2.uml.Class ) {
                    final org.eclipse.uml2.uml.Class classNode = (Class) node.getUmlModel();
                    Rectangle bounds = new Rectangle( node.getX(), node.getY(), node.getWidth(), node.getHeight() );
                    if( bounds.contains(cusorPoint) ){
                        
                        DomainUtil.run(new TransactionalAction() {
                            /**
                             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                             */
                            @Override
                            public void doExecute() {
                                classNode.getOwnedOperations().add( (Operation) element);
                            }
                        });
                    }
                } else if( node.getUmlModel() instanceof Interface) {
                    final Interface interfaceNode = (Interface) node.getUmlModel();
                    Rectangle bounds = new Rectangle( node.getX(), node.getY(), node.getWidth(), node.getHeight() );
                    if( bounds.contains(cusorPoint) ){
                        
                        DomainUtil.run(new TransactionalAction() {
                            /**
                             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                             */
                            @Override
                            public void doExecute() {
                                interfaceNode.getOwnedOperations().add( (Operation) element);
                            }
                        });
                    }
                }
            }
            editPart.refresh();
        }
    }
}
