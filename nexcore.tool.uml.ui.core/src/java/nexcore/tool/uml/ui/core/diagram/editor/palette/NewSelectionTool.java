/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.editor.palette;

import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Event;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor.palette</li>
 * <li>설 명 : NewSelectionTool</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class NewSelectionTool extends SelectionTool {

    /** STACK_ROLE */
    public static final int STACK_ROLE = 0;

    /** QUEUE_ROLE */
    public static final int QUEUE_ROLE = 1;

    /** role */
    private int role = 1;

    /** currentEditPart */
    @SuppressWarnings("unused")
    private EditPart currentEditPart;

    /** savedEditPart */
    @SuppressWarnings("unused")
    private EditPart savedEditPart = null;

    /** feedbackEditPart */
    @SuppressWarnings("unused")
    private EditPart feedbackEditPart = null;

    /** selectEditPart */
    @SuppressWarnings("unused")
    private EditPart selectEditPart;

    /**
     * getRole
     *  
     * @return int
     */
    public int getRole() {
        return role;
    }

    /**
     * setRole
     *  
     * @param role void
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#executeCommand(org.eclipse.gef.commands.Command)
     */
    @Override
    protected void executeCommand(Command command) {
        if (this.handleAdditionalInformation(command)) {
            super.executeCommand(command);
        }
    }

    /**
     * handleAdditionalInformation
     *  
     * @param command
     * @return boolean
     */
    protected boolean handleAdditionalInformation(Command command) {
        return true;
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleFocusLost()
     */
    @Override
    protected boolean handleFocusLost() {
        return super.handleFocusLost();
    }

    /**
     * @see org.eclipse.gef.tools.TargetingTool#updateTargetUnderMouse()
     */
    @Override
    protected boolean updateTargetUnderMouse() {
        return super.updateTargetUnderMouse();
        // currentEditPart = getCurrentViewer().findObjectAt(getLocation());
        // selectEditPart = currentEditPart;
        //
        // if (getRole() == STACK_ROLE) {
        // return super.updateTargetUnderMouse();
        // } else if (getRole() == QUEUE_ROLE) {
        // // 저장 값이 없을 경우 다이어그램 바로 밑의 부모를 리턴
        // if (savedEditPart == null) {
        // while (!(selectEditPart.getParent() instanceof
        // AbstractDiagramEditPart)) {
        // selectEditPart = selectEditPart.getParent();
        // if(null == selectEditPart){
        // return false;
        // }
        // }
        // // setTargetEditPart(selectEditPart);
        // feedbackEditPart = selectEditPart;
        // return true;
        // }
        //
        // // 저장 값이 있을 경우
        // else if (savedEditPart != null) {
        // // 저장 값과 선택된 값을 비교
        // // 같을 경우
        // if (savedEditPart == currentEditPart) {
        // // setTargetEditPart(savedEditPart);
        // feedbackEditPart = savedEditPart;
        // return true;
        // }
        // // 다를 경우
        // else if (savedEditPart != selectEditPart) {
        // // 저장된 값과 선택된 값의 부모가 같은지 확인
        // EditPart tempSavedEditPart = savedEditPart;
        // EditPart tempCurrentEditPart = selectEditPart;
        // while (!(tempSavedEditPart.getParent() instanceof
        // AbstractDiagramEditPart)) {
        // tempSavedEditPart = tempSavedEditPart.getParent();
        // }
        //                    
        // while (!(tempCurrentEditPart.getParent() instanceof
        // AbstractDiagramEditPart)) {
        // tempCurrentEditPart = tempCurrentEditPart.getParent();
        // }
        //
        // // 같으면
        // if (tempSavedEditPart == tempCurrentEditPart) {
        // while(selectEditPart.getParent() != savedEditPart){
        // selectEditPart = selectEditPart.getParent();
        // }
        // feedbackEditPart = selectEditPart;
        // return true;
        // } else if (tempSavedEditPart != tempCurrentEditPart) {
        // // 다르면
        // // 다이어그램 바로 밑의 부모를 리턴
        // while (!(selectEditPart.getParent() instanceof
        // AbstractDiagramEditPart)) {
        // selectEditPart = selectEditPart.getParent();
        // }
        // // setTargetEditPart(selectEditPart);
        // feedbackEditPart = selectEditPart;
        // return true;
        // }
        // }
        // }
        // }
        // return false;
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseDown(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseDown(MouseEvent e, EditPartViewer viewer) {
        // savedEditPart = feedbackEditPart;
        // setTargetEditPart(savedEditPart);
        super.mouseDown(e, viewer);
    }

    // @Override
    // protected void setTargetEditPart(EditPart editpart) {
    // super.setTargetEditPart(editpart);
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gef.tools.SelectionTool#mouseDrag(org.eclipse.swt.events.
     * MouseEvent, org.eclipse.gef.EditPartViewer)
     */

    /**
     * isDragging
     */
    private static boolean isDragging = false;

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseDrag(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseDrag(MouseEvent e, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        if (getTargetEditPart() instanceof AbstractDiagramConnectionEditPart) {
            isDragging = true;
            ((AbstractDiagramConnectionEditPart) getTargetEditPart()).showFeedback(getStartLocation(), new Point(e.x,
                e.y));// getLocation());
        } else {
            super.mouseDrag(e, viewer);
        }
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseUp(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseUp(MouseEvent e, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        if (isDragging) {
            // Relation connection = (Relation)
            // ((AbstractDiagramConnectionEditPart)getTargetEditPart()).getModel();
            if (getTargetEditPart() instanceof AbstractDiagramConnectionEditPart) {
                ((AbstractDiagramConnectionEditPart) getTargetEditPart()).createBendPoint(getStartLocation(),
                    new Point(e.x, e.y));// getLocation());
            }
        }
        isDragging = false;
        super.mouseUp(e, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseDoubleClick(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseDoubleClick(MouseEvent e, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        isDragging = false;
        super.mouseDoubleClick(e, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseHover(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseHover(MouseEvent me, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        // isDragging = false;
        // System.out.println("isDragging False3");
        super.mouseHover(me, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseMove(org.eclipse.swt.events.MouseEvent, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseMove(MouseEvent me, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        // isDragging = false;
        // System.out.println("isDragging False4");
        super.mouseMove(me, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseWheelScrolled(org.eclipse.swt.widgets.Event, org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseWheelScrolled(Event event, EditPartViewer viewer) {
        // TODO Auto-generated method stub
        isDragging = false;
        super.mouseWheelScrolled(event, viewer);
    }
}
