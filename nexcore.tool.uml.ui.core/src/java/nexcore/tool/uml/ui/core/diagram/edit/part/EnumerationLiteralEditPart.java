/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.EnumerationLiteral;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : EnumerationLiteralEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class EnumerationLiteralEditPart extends AbstractChildCompartmentEditPart {

    /** label */
    private Label label;

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NotationNode literalModel = (NotationNode) getModel();
        EnumerationLiteral enumerationLiteral = (EnumerationLiteral) literalModel.getUmlModel();
        Image image = UiCorePlugin.getDefault().getImageForUMLElement(enumerationLiteral);
        label = new Label(enumerationLiteral.getName(), image);
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
            NotationNode literalModel = (NotationNode) getModel();
            EnumerationLiteral enumerationLiteral = (EnumerationLiteral) literalModel.getUmlModel();
            Image image = UiCorePlugin.getDefault().getImageForUMLElement(enumerationLiteral);
            label.setIcon(image);
            label.setText(enumerationLiteral.getLabel());
            label.setToolTip(new Label(label.getText()));

        } catch (Exception e) {
            Log.error("AttributeEditPart refreshVisuals() Error " + e);
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

    public class UMLDragTracker extends DragEditPartsTracker {

        private EditPart sourceEditPart;

        public UMLDragTracker(EditPart sourceEditPart) {
            super(sourceEditPart);
            setDisabledCursor(SharedCursors.ARROW);
            this.sourceEditPart = sourceEditPart;
        }
    }
}
