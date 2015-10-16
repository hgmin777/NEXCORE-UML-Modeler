/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.Request;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : CompartmentInterfaceEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class CompartmentInterfaceEditPart extends AbstractChildCompartmentEditPart {

    /** image */
    private Image imageProvidedInterface = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PROVIDEDINTERFACE);

    /** image */
    private Image imageRequiredInterface = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_REQUIREDINTERFACE);

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        org.eclipse.uml2.uml.Interface umlInterface = (org.eclipse.uml2.uml.Interface) getModel();
        Image image;

        if (this.getParent() instanceof ProvidedInterfacesEditPart) {
            image = imageProvidedInterface;
        } else {
            image = imageRequiredInterface;
        }
        Label label = new Label(umlInterface.getName(), image);

        return label;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            Label label = (Label) getFigure();
            org.eclipse.uml2.uml.Interface umlInterface = (org.eclipse.uml2.uml.Interface) getModel();
            label.setText(umlInterface.getName());

        } catch (Exception e) {
            Log.error("CompartmentInterfaceEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart#performDirectEdit(org.eclipse.gef.Request)
     */
    @Override
    protected void performDirectEdit(Request req) {
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart#performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request req) {
    }

}
