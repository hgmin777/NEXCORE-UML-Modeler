/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.IEditorPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager</li>
 * <li>설  명 : DiagramEditDomain</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : 최윤석 </li>
 * </ul>
 */
public class DiagramEditDomain extends DefaultEditDomain {

    /** 다이어그램 */
    private Diagram diagram;

    /**
     * 생성자
     * 
     * @param editorPart
     */
    public DiagramEditDomain(IEditorPart editorPart) {
        super(editorPart);
    }

    /**
     * 생성자
     * 
     * @param editorPart
     * @param diagram
     */
    public DiagramEditDomain(IEditorPart editorPart, Diagram diagram) {
        this(editorPart);

        this.diagram = diagram;
    }

    /**
     * @see org.eclipse.gef.EditDomain#getCommandStack()
     */
    @Override
    public CommandStack getCommandStack() {
        // return DomainRegistry.getUMLDomain().getCommandStack();
        return (CommandStack) DomainRegistry.getUMLDomain().getCommandStackListener();
    }

    // /**
    // * @see
    // org.eclipse.gef.EditDomain#setCommandStack(org.eclipse.gef.commands.CommandStack)
    // */
    // @Override
    // public void setCommandStack(CommandStack stack) {
    // DomainRegistry.getUMLDomain().setCommandStack(stack);
    // }

    /**
     * 다이어그램 가져오는 메소드
     * 
     * @return Diagram
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * 다이어그램 설정하는 메소드
     * 
     * @param setDiagram
     *            void
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 다이어그램에딧 도메인 이름 반환
     * 
     * @return String
     */
    public String getDomainName() {
        if (diagram != null) {
            return diagram.getName();
        }

        return null;
    }

//    @Override
//    public void setActiveTool(Tool tool) {
//        // TODO Auto-generated method stub
//        super.setActiveTool(tool);
//        if (tool instanceof CreationTool) {
//            if (isShiftKeyDown) {
//                setDefaultTool(tool);
//            } else {
//                setDefaultTool(new SelectionTool());
//            }
//        }
//    }

    /**
     * @see org.eclipse.gef.EditDomain#loadDefaultTool()
     */
    @Override
    public void loadDefaultTool() {
        super.loadDefaultTool();
        setActiveTool(getDefaultTool());
    }

    /**
     * isShiftKeyDown
     */
    private static boolean isShiftKeyDown = false;

    /**
     * isShiftKeyDown
     *  
     * @return boolean
     */
    public static boolean isShiftKeyDown() {
        return isShiftKeyDown;
    }

    /**
     * setShiftKeyDown
     *  
     * @param flag void
     */
    public static void setShiftKeyDown(boolean flag) {
        isShiftKeyDown = flag;
    }

//    @Override
//    public void keyDown(KeyEvent keyEvent, EditPartViewer viewer) {
//        if (keyEvent.keyCode == SWT.SHIFT) {
//            setShiftKeyDown(true);
//        } else if (keyEvent.keyCode == SWT.ESC) {
//            setActiveTool(new SelectionTool());
//        } else {
//            super.keyDown(keyEvent, viewer);
//        }
//    }
//
//    @Override
//    public void keyUp(KeyEvent keyEvent, EditPartViewer viewer) {
//        if (keyEvent.keyCode == SWT.SHIFT) {
//            setShiftKeyDown(false);
//        } else {
//            super.keyUp(keyEvent, viewer);
//        }
//    }

}
