/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Operation;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : RequiredInterfaceOperationEditPart</li>
 * <li>작성일 : 2011. 3. 4.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class RequiredInterfaceOperationEditPart extends AbstractChildCompartmentEditPart {

    /** label */
    private Label label;

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NotationNode node = (NotationNode) getModel();
        Operation operation = (Operation) node.getUmlModel();
        Image image = UiCorePlugin.getDefault().getImageForUMLElement(operation);
        label = new Label(operation.getName(), image);
        label.setForegroundColor(ColorConstants.black);
        label.setBorder(new MarginBorders(0, 5, 0, 0));
        label.setToolTip(new Label(label.getText()));

        GridLayout gridLayout;
        GridData gridData;
        LayoutManager layout = ((AbstractChildCompartmentEditPart) this.getParent()).getFigure().getLayoutManager();
        if (layout instanceof GridLayout) {
            gridLayout = (GridLayout) layout;
            gridData = new GridData();
            gridData.horizontalIndent = 5;
            gridLayout.setConstraint(figure, gridData);
        }
        label.setBorder(new MarginBorder(0, 10, 0, 0));

        return label;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        NotationNode requiredInterfaceOperation = (NotationNode) getModel();
        Operation operation = (Operation) requiredInterfaceOperation.getUmlModel();
        Image image = UiCorePlugin.getDefault().getImageForUMLElement(operation);
        Label label = (Label) getFigure();
        label.setIcon(image);
        label.setText(operation.getLabel());
        label.setToolTip(new Label(label.getText()));
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DelegatingDragEditPartsTracker(this, getParent());
    }
}
