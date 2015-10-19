/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.List;

import nexcore.tool.uml.ui.core.diagram.edit.policy.CompartmentResizableEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.DirectEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.edit.part</li>
 * <li>설 명 : AbstractParentCompartmentEditPart</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public abstract class AbstractParentCompartmentEditPart extends AbstractGraphicalEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, createSizeableEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
    }

    /**
     * 다이렉트 에디트 폴리시(직접편집)를 생성한다. EditPolicy.DIRECT_EDIT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected DirectEditPolicy createDirectEditorPolicy() {
        return new DirectEditorPolicy();
    }

    /**
     * createSizeableEditPolicy s
     * 
     * @return EditPolicy
     */
    private EditPolicy createSizeableEditPolicy() {
        return new CompartmentResizableEditPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DelegatingDragEditPartsTracker(this, getParent());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * isSelected
     */
    private boolean isSelected = false;

    /**
     * isSelected
     *  
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * setSelected
     *  
     * @param isSelected void
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        if (isSelected) {
            return true;
        }
        if (getParent().getSelected() == 2 || getChildrenSelected() || getSameLevelSelected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSameLevelSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getSameLevelSelected() {
        List<EditPart> editParts = getParent().getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * getChildrenSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getChildrenSelected() {
        List<EditPart> editParts = getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        if (super.getSelected() == 2) {
            isSelected = true;
        } else if (super.getSelected() == 0) {
            isSelected = false;
        }
        return super.getSelected();
    }

    /**
     * editpartLevel
     */
    protected static int editpartLevel = 1;

}
