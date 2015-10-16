/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.editor.MultilineCellEditor;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : TextEditPart</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class TextEditPart extends AbstractNotationNodeEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();

        TextFigure textFigure = new TextFigure();
        textFigure.setSize(notationNode.getWidth(), notationNode.getHeight());
        textFigure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
        textFigure.setName(notationNode.getName());

        return textFigure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            NotationNode notationNode = (NotationNode) getModel();

            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                notationNode.getWidth(),
                notationNode.getHeight());

            ((TextFigure) getFigure()).setName(notationNode.getName());
            getFigure().setSize(notationNode.getWidth(), notationNode.getHeight());
            getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));

            setLayoutConstraint(this, getFigure(), bounds);

            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String fillColorValue = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
            if (fillColorValue.equals("")) {
                ((TextFigure) getFigure()).setColor(new Color(null, new RGB(255, 255, 255)));
            } else {
                RGB fillColor = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FILL);
                ((TextFigure) getFigure()).setColor(new Color(null, fillColor));
            }
            @SuppressWarnings("unused")
            String fontColorValue = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
            if (fillColorValue.equals("")) {
                ((TextFigure) getFigure()).setFontColor(new Color(null, PreferenceUtil.INSTANCE.getTextFontColor()));
            } else {
                RGB fillColor = PreferenceUtil.INSTANCE.getValueOfColorFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COLORSANDFONTS_COMMENT_FONT);
                ((TextFigure) getFigure()).setFontColor(new Color(null, fillColor));
            }

        } catch (Exception e) {
            Log.error("TextEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            setDirectManager(new DirectEditorManager(this,
                MultilineCellEditor.class,
                new DirectEditCellEditorLocator(figure)));
        }
        super.performRequest(req);
    }

}
