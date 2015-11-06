/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.editor.palette;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor.palette</li>
 * <li>설 명 : ConnectionCreationToolWithAdditionalInformation</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ConnectionCreationToolWithAdditionalInformation extends ConnectionCreationTool {

    // protected boolean isShiftKeyPressed = false;
    //
    // /**
    // * @see
    // org.eclipse.gef.tools.AbstractTool#keyDown(org.eclipse.swt.events.KeyEvent,
    // * org.eclipse.gef.EditPartViewer)
    // */
    // @Override
    // public void keyDown(KeyEvent evt, EditPartViewer viewer) {
    // System.out.println("xxxx1x");
    // if(evt.keyCode == SWT.SHIFT){
    // DiagramEditDomain.setShiftKeyDown(true);
    // System.out.println("xxxxx");
    // }
    // // if ((evt.stateMask & SWT.SHIFT) == SWT.SHIFT) {
    // // isShiftKeyPressed = true;System.out.println("xxxxx");
    // // } else {
    // // isShiftKeyPressed = false;
    // // }
    // super.keyDown(evt, viewer);
    // }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#keyUp(org.eclipse.swt.events.KeyEvent,
     *      org.eclipse.gef.EditPartViewer)
     */
    // @Override
    // public void keyUp(KeyEvent evt, EditPartViewer viewer) {
    // System.out.println("yyyy1");
    // if(evt.keyCode == SWT.SHIFT){
    // DiagramEditDomain.setShiftKeyDown(true);
    // System.out.println("yyyy");
    // }
    // // if ((evt.stateMask & SWT.SHIFT) == SWT.SHIFT) {
    // // isShiftKeyPressed = false;System.out.println("yyyy");
    // // }
    // super.keyUp(evt, viewer);
    // }
    /**
     * @see org.eclipse.gef.tools.AbstractConnectionCreationTool#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        Command endCommand = getCommand();
        if (handleAdditionalInformation(endCommand)) {
            executeCurrentCommand();
        }
        super.eraseSourceFeedback();
        super.eraseTargetFeedback();
        this.getDomain().loadDefaultTool();
        // if(DiagramEditDomain.isShiftKeyDown() || isShiftKeyPressed){
        // this.getDomain().setActiveTool(this);
        // }else{
        // this.getDomain().loadDefaultTool();
        // }

        return true;
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#executeCommand(org.eclipse.gef.commands.Command)
     */
    @Override
    protected void executeCommand(Command command) {
        // if(this.handleAdditionalInformation(command)){
        // super.executeCommand(command);
        // }
        super.executeCommand(command);

    }

    /**
     * @see org.eclipse.gef.tools.ConnectionCreationTool#handleFocusLost()
     */
    @Override
    protected boolean handleFocusLost() {
        return false;
    }

    // /**
    // * @see
    // org.eclipse.gef.tools.AbstractConnectionCreationTool#handleCreateConnection()
    // */
    // @Override
    // protected boolean handleCreateConnection() {
    //
    // Command endCommand = getCommand();
    // setCurrentCommand(endCommand);
    // if (handleAdditionalInformation(endCommand)) {
    // executeCurrentCommand();
    // }
    // return true;
    // }

    /**
     * 
     * 커맨드 실행전에 데이터 선택이 필요한경우 override되는 함수
     * 
     * @param command
     * @return boolean
     */
    protected boolean handleAdditionalInformation(Command command) {
        return true;
    }

    /**
     * 
     */
    public ConnectionCreationToolWithAdditionalInformation() {

    }

    /**
     * @param factory
     */
    public ConnectionCreationToolWithAdditionalInformation(CreationFactory factory) {
        super(factory);
    }

}
