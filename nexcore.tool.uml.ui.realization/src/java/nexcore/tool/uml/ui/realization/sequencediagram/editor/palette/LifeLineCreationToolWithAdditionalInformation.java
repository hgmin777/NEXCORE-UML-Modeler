/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.editor.palette;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.command.CreateLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor.palette</li>
 * <li>설 명 : LifeLineCreationToolWithAdditionalInformation</li>
 * <li>작성일 : 2010. 2. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineCreationToolWithAdditionalInformation extends CreationTool implements FocusListener, KeyListener,
MouseListener {

    /** isShiftKeyPressed */
    protected boolean isShiftKeyPressed = false;

    /** dialog */
    private Shell dialog;

    /** selectedIndex */
    private int selectedIndex;

    /**
     * 
     */
    public LifeLineCreationToolWithAdditionalInformation() {

    }

    /**
     * @param factory
     */
    public LifeLineCreationToolWithAdditionalInformation(CreationFactory factory) {
        super(factory);
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#keyDown(org.eclipse.swt.events.KeyEvent,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void keyDown(KeyEvent evt, EditPartViewer viewer) {
        if ((evt.stateMask & SWT.SHIFT) == SWT.SHIFT) {
            isShiftKeyPressed = true;
        } else {
            isShiftKeyPressed = false;
        }
        super.keyDown(evt, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#keyUp(org.eclipse.swt.events.KeyEvent,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void keyUp(KeyEvent evt, EditPartViewer viewer) {
        if ((evt.stateMask & SWT.SHIFT) == SWT.SHIFT) {
            isShiftKeyPressed = false;
        }
        super.keyUp(evt, viewer);
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
     * @see org.eclipse.gef.tools.ConnectionCreationTool#handleFocusLost()
     */
    @Override
    protected boolean handleFocusLost() {
        return false;
    }

    /**
     * handleAdditionalInformation
     * 
     * @param command
     * @return boolean
     */
    protected boolean handleAdditionalInformation(Command command) {
        if (command instanceof CreateLifeLineNodeCommand) {
            return handleExtendRelationship((CreateLifeLineNodeCommand) command);
        }
        return true;
    }

    /**
     * 
     * 
     * @param command
     * @return boolean
     */
    private boolean handleExtendRelationship(CreateLifeLineNodeCommand command) {

        createList();

        AbstractNode parentNodeModel = command.getParentNodeModel();

        if (-1 == selectedIndex || 0 == selectedIndex) {
            // 지정안함.
            command.setType(null);
        } else if (1 == selectedIndex) {
            // 새 클래스를 작성하여 LifeLine에 유형으로 지정
            Type type = SequenceUtil.createClass(parentNodeModel);
            command.setType(type);
            command.setCreateType(true);
        } else {
            // 유형 선택 다이얼로그를 띄어서 선택한 유형을 LifeLine에 설정.
            TypeSelectDialog typeSelectDialog = new TypeSelectDialog(this.getCurrentViewer().getControl().getShell(),
                TypeSelectDialogType.LIFELINE, parentNodeModel.getUmlModel());
            if (typeSelectDialog.open() == TypeSelectDialog.OK) {
                if (typeSelectDialog.getResult() != null) {
                    command.setType((Type) typeSelectDialog.getFirstResult());
                }
            }
        }
        return true;
    }

    /**
     * List UI 생성
     * 
     * @param operations
     *            void
     */
    private void createList() {

        Display display = this.getCurrentViewer().getControl().getShell().getDisplay();
        Shell shell = new Shell(display);
        org.eclipse.swt.graphics.Point currentPoint = this.getCurrentViewer()
            .getControl()
            .getDisplay()
            .getCursorLocation();
        shell.setLocation(currentPoint.x, currentPoint.y);

        dialog = new Shell(shell, SWT.TOOL | SWT.APPLICATION_MODAL);
        dialog.setSize(120, 50);
        dialog.setLocation(currentPoint.x, currentPoint.y);
        dialog.setLayout(new FillLayout());
        final List list = new List(dialog, SWT.SINGLE | SWT.V_SCROLL);

        list.add(UMLMessage.getMessage(UMLMessage.LABEL_NO_TYPE));
        list.add(UMLMessage.getMessage(UMLMessage.LABEL_CREATE_CLASS));
        list.add(UMLMessage.getMessage(UMLMessage.LABEL_SELECT_TYPE));

        list.select(0);
        list.addFocusListener(this);
        list.addKeyListener(this);
        list.addMouseListener(this);

        dialog.open();
        while (!dialog.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        shell.dispose();
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
        this.dialog.close();
        this.dialog.dispose();
        this.selectedIndex = -1;
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        this.selectedIndex = ((List) e.getSource()).getSelectionIndex();
        if (13 == e.keyCode && -1 != selectedIndex) {
            this.dialog.close();
            this.dialog.dispose();
        }
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseDoubleClick(MouseEvent e) {
        this.selectedIndex = ((List) e.getSource()).getSelectionIndex();
        if (-1 != this.selectedIndex) {
            dialog.close();
            dialog.dispose();
        }
    }

    /**
     * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseDown(MouseEvent e) {
    }

    /**
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseUp(MouseEvent e) {
    }

}
