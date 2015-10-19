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
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;

import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : AttributesEditPart</li>
 * <li>작성일 : 2011. 2. 16.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class AttributesEditPart extends ScrollableEditPart {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        IFigure figure = super.createFigure();
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {

        NotationNode propertiesModel = (NotationNode) this.getModel();
        Element element = propertiesModel.getUmlModel();
        if (element == null) {
            element = ((AbstractNode) propertiesModel.getParent()).getUmlModel();
        }
        EList<Property> propertyList;
        if (element instanceof StructuredClassifier) {
            propertyList = ((StructuredClassifier) element).getOwnedAttributes();
        } else if (element instanceof Interface) {
            propertyList = ((Interface) element).getOwnedAttributes();
        } else if (element instanceof DataType) {
            propertyList = ((DataType) element).getOwnedAttributes();
        } else if (element instanceof Signal) {
            propertyList = ((Signal) element).getOwnedAttributes();
        } else {
            return null;
        }

        List<NotationNode> list = new ArrayList<NotationNode>();
        for (Property property : propertyList) {
            if (null == property.getAssociation()) {

                NotationNode propertyModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                propertyModel.setNodeType(NodeType.ATTRIBUTE);
                propertyModel.setParent(propertiesModel);
                propertyModel.setUmlModel(property);

                list.add(propertyModel);
            }
        }
        return list;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#addChildVisual(org.eclipse.gef.EditPart, int)
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {

        super.addChildVisual(childEditPart, index);

         if (!isCreated()) {
            if (((AbstractNode) childEditPart.getModel()).getUmlModel().equals(getAdded())) {
                DirectEditorManager dem = new DirectEditorManager((GraphicalEditPart) childEditPart,
                    TextCellEditor.class,
                    new DirectEditCellEditorLocator(((GraphicalEditPart) childEditPart).getFigure()));
                dem.show();
                setCreated(true);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        // super.refreshVisuals();
        AbstractNode node = (AbstractNode) getModel();

        if (getParent() == null) {
            return;
        }
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
}
