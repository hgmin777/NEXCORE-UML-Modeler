/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : RequiredInterfacesEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class RequiredInterfacesEditPart extends ScrollableEditPart {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        IFigure figure = super.createFigure();
        return figure;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        // super.refreshVisuals();
        AbstractNode node = (AbstractNode) getModel();
        // if(getParent() == null){
        // return;
        // }
        LayoutManager layoutParent = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;
        if (layoutParent instanceof GridLayout) {
            gridLayout = (GridLayout) layoutParent;
            gridData = (GridData) ((AbstractGraphicalEditPart) this.getParent()).getFigure()
                .getLayoutManager()
                .getConstraint(getFigure());
            gridData.grabExcessHorizontalSpace = true;
            gridData.horizontalAlignment = GridData.FILL;
            // gridData.grabExcessVerticalSpace = true;
            gridData.verticalAlignment = GridData.FILL;

            gridData.heightHint = node.getHeight();
            gridLayout.setConstraint(((AbstractGraphicalEditPart) this.getParent()).getFigure(), gridData);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {
        NotationNode requiredInterfacesModel = (NotationNode) this.getModel();
        List<NotationNode> list = new ArrayList<NotationNode>();
        Component component = (Component) requiredInterfacesModel.getUmlModel();
        if (component == null) {
            component = (Component) ((AbstractNode) requiredInterfacesModel.getParent()).getUmlModel();
        }
        if (null == component) {
            return list;
        }

        if (component instanceof NamedElement) {
            if (component.getName() != null && !component.getName().equals(UICoreConstant.EMPTY_STRING)) {
                NotationNode nameofUML = UMLDiagramFactory.eINSTANCE.createNotationNode();
                nameofUML.setUmlModel(component);
                nameofUML.setNodeType(NodeType.NAME);
                nameofUML.setParent(requiredInterfacesModel);

                list.add(nameofUML);
            }
        }

        EList<Interface> interfaceList = component.getRequireds();
        for (Interface umlInterface : interfaceList) {

            NotationNode operationModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
            operationModel.setNodeType(NodeType.REQUIRED_INTERFACE);
            operationModel.setParent(requiredInterfacesModel);
            operationModel.setUmlModel(umlInterface);

            list.add(operationModel);
        }
        return list;
    }
}
