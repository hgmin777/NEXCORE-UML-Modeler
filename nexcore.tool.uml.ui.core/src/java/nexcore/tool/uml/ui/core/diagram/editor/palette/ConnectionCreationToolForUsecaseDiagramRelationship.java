/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.editor.palette;

import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.diagram.command.CreateConnectionCommand;

import org.eclipse.gef.commands.Command;
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
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor.palette</li>
 * <li>설  명 : ConnectionCreationToolForUsecaseDiagramRelationship</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ConnectionCreationToolForUsecaseDiagramRelationship extends
ConnectionCreationToolWithAdditionalInformation implements FocusListener, KeyListener, MouseListener {

    /**
     * dialog
     */
    private Shell dialog;

    /**
     * selectedIndex
     */
    private int selectedIndex;

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.palette.ConnectionCreationToolWithAdditionalInformation#handleAdditionalInformation(org.eclipse.gef.commands.Command)
     */
    @Override
    protected boolean handleAdditionalInformation(Command command) {
        if (command instanceof CreateConnectionCommand) {
            return handleExtendRelationship((CreateConnectionCommand) command);
        }
        return true;
    }

    /**
     * handleExtendRelationship
     *  
     * @param command
     * @return boolean
     */
    private boolean handleExtendRelationship(CreateConnectionCommand command) {
        if (!command.isExpensible()) {
            return true;
        }
        if (!command.getConnection().getRelationType().equals(RelationType.EXTEND)) {
            return true;
        }

        UseCase useCase = (UseCase) command.getTarget().getUmlModel();

        Display display = this.getCurrentViewer().getControl().getShell().getDisplay();
        Shell shell = new Shell(display);
        org.eclipse.swt.graphics.Point currentPoint = this.getCurrentViewer()
            .getControl()
            .getDisplay()
            .getCursorLocation();
        shell.setLocation(currentPoint.x, currentPoint.y);

        dialog = new Shell(shell, SWT.TOOL | SWT.APPLICATION_MODAL);
        dialog.setSize(180, 80);
        dialog.setLocation(currentPoint.x, currentPoint.y);
        dialog.setLayout(new FillLayout());
        final List list = new List(dialog, SWT.SINGLE | SWT.V_SCROLL);

        list.add("<New>");
        for (ExtensionPoint extensionPoint : useCase.getExtensionPoints()) {
            list.add(extensionPoint.getName());
        }
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

        if (-1 == selectedIndex) {
            return false;
        } else if (0 == selectedIndex) {
            command.setExtensionPoint(null);
            return true;
        } else {
            command.setExtensionPoint(useCase.getExtensionPoints().get(selectedIndex - 1));
            return true;
        }
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
