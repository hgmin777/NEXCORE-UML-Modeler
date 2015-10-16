/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : ProvidedInterfaceEditPart</li>
 * <li>작성일 : 2011. 3. 4.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ProvidedInterfaceEditPart extends AbstractChildCompartmentEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        IFigure figure = new RectangleFigure();// super.createFigure();
        figure.setSize(0, getModelChildren().size() * 15);
        figure.setLayoutManager(new GridLayout());
        figure.setBorder(new LineBorder(ColorConstants.white));

        LayoutManager layout = ((ScrollableEditPart) this.getParent()).getContentPane().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;

        if (layout instanceof GridLayout) {

            gridLayout = (GridLayout) layout;
            gridData = new GridData();
            gridData.grabExcessHorizontalSpace = true;
            gridData.horizontalAlignment = GridData.FILL;
            gridLayout.setConstraint(figure, gridData);
        }
        // figure.setBorder(new LineBorder(ColorConstants.white));

        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        // super.refreshVisuals();
        IFigure figure = getFigure();
        figure.setSize(0, getModelChildren().size() * 15);
        figure.setLayoutManager(new GridLayout());

        LayoutManager layout = ((ScrollableEditPart) this.getParent()).getContentPane().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;

        if (layout instanceof GridLayout) {

            gridLayout = (GridLayout) layout;
            gridData = new GridData();
            gridData.grabExcessHorizontalSpace = true;
            gridData.horizontalAlignment = GridData.FILL;
            gridLayout.setConstraint(figure, gridData);
        }
        // figure.setBorder(new LineBorder(ColorConstants.white));

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {

        boolean showOperation = false;
        if (!PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_OPERATION_IN_COMPONENT)
            .equals("false")) {
            showOperation = true;
        }

        NotationNode providedInterfacsModel = (NotationNode) this.getModel();
        List<NotationNode> list = new ArrayList<NotationNode>();
        Interface interfaceElement = (Interface) providedInterfacsModel.getUmlModel();
        if (null == interfaceElement) {
            return list;
        }

        if (interfaceElement instanceof NamedElement) {
            if (interfaceElement.getName() != null && !interfaceElement.getName().equals(UICoreConstant.EMPTY_STRING)) {
                NotationNode nameofUML = UMLDiagramFactory.eINSTANCE.createNotationNode();
                nameofUML.setUmlModel(interfaceElement);
                nameofUML.setNodeType(NodeType.NAME);
                nameofUML.setParent(providedInterfacsModel);

                list.add(nameofUML);
            }
        }

        if (showOperation) {
            EList<Operation> operationList = interfaceElement.getOperations();
            for (Operation umlOperation : operationList) {

                NotationNode operationModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                operationModel.setNodeType(NodeType.PROVIDED_INTERFACE_OPERATION);
                operationModel.setParent(providedInterfacsModel);
                operationModel.setUmlModel(umlOperation);

                list.add(operationModel);
            }
        }
        return list;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DelegatingDragEditPartsTracker(this, getParent());
    }
}
