/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.editor.palette;

import java.util.Collections;
import java.util.Comparator;

import nexcore.tool.uml.manager.utility.SequenceManagerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.editor.palette.ConnectionCreationToolWithAdditionalInformation;
import nexcore.tool.uml.ui.realization.sequencediagram.command.CreateMessageCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

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
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor.palette</li>
 * <li>설 명 : MessageCreationToolForSequenceDiagramRelationship</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MessageCreationToolForSequenceDiagramRelationship extends ConnectionCreationToolWithAdditionalInformation
implements FocusListener, KeyListener, MouseListener {

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
        if (command instanceof CreateMessageCommand) {
            return handleExtendRelationship((CreateMessageCommand) command);
        }
        return true;
    }

    public java.util.List<Operation> sortOperationList(java.util.List<Operation> operations) {

        Comparator<Operation> comparator = new Comparator<Operation>() {
            public int compare(Operation o1, Operation o2) {
                String o1s = o1.getName();
                String o2s = o2.getName();

                return o1s.compareTo(o2s);
            }
        };

        Collections.sort(operations, comparator);
        return operations;

    }
    
    /**
     * 
     * 
     * @param command
     * @return boolean
     */
    private boolean handleExtendRelationship(CreateMessageCommand command) {

        AbstractNode node = command.getTarget();
        Lifeline lifeline = null;
        Element element = null;
        if (node.getNodeType().equals(NodeType.LIFE_LINE_BEHAVIOR)) {
            if (node.getParent() instanceof AbstractNode) {
                element = ((AbstractNode) node.getParent()).getUmlModel();
            }
        } else if (node.getNodeType().equals(NodeType.LINE)) {
            element = node.getUmlModel();
        }
        if (null == element) {
            return false;
        }

        if (element instanceof Lifeline) {
            lifeline = (Lifeline) element;
        }

        // target lifeline에 type이 있나 없나 먼저 체크. 없으면 안 보여줌.
        if (lifeline == null) {
            return false;
        }

        Type type = lifeline.getRepresents().getType();
        if (type == null || type instanceof Actor) {
            return true;
        }

        if (command.getConnection() instanceof Relation) {
            if (((Relation) command.getConnection()).getRelationType().equals(RelationType.REPLY_MESSAGE)) {
                return true;
            }
        }
        java.util.List<Operation> operations = SequenceManagerUtil.getTypeOperations(type);
        
        sortOperationList(operations);
        
        createList(operations);

        if (-1 == selectedIndex) {
            // 지정안함.
            command.setOperationAndType(null, null);
            return false;

        } else if (0 == selectedIndex) {
            // Lifeline의 Type에 새 Operation 생성해서 메시지에 설정.
            Operation operation = SequenceUtil.createOperation(type);
            if (null == operation) {
                return false;
            }
            command.setOperationAndType(operation, type);
        } else {
            // 선택한 Operation을 메시지에 설정.
            command.setOperationAndType(operations.get(selectedIndex - 1), type);
        }
        return true;
    }

    /**
     * List UI 생성
     * 
     * @param operations
     *            void
     */
    private void createList(java.util.List<Operation> operations) {

        Display display = this.getCurrentViewer().getControl().getShell().getDisplay();
        Shell shell = new Shell(display);
        org.eclipse.swt.graphics.Point currentPoint = this.getCurrentViewer()
            .getControl()
            .getDisplay()
            .getCursorLocation();
        shell.setLocation(currentPoint.x, currentPoint.y);

        dialog = new Shell(shell, SWT.TOOL | SWT.APPLICATION_MODAL);

        dialog.setLocation(currentPoint.x, currentPoint.y);
        dialog.setLayout(new FillLayout());
        final List list = new List(dialog, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        list.add("<New>");

        // operation을 list에 보여준다.
        if (operations != null) {
            for (Operation operation : operations) {
                list.add(SequenceUtil.getOperationVisibility(operation) + UICoreConstant.PROJECT_CONSTANTS__BLANK
                    + ((NamedElement) operation.eContainer()).getName()
                    + UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON + operation.getName() + " ()");
            }
        }

        list.select(0);
        list.addFocusListener(this);
        list.addKeyListener(this);
        list.addMouseListener(this);
        int x, y;
        x = 300;
        y = list.getItems().length * 13;
        y = y < 100 ? 100 : y;
        y = y > 400 ? 400 : y;
        dialog.setSize(x, y);

        selectedIndex = -1;
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

    // /**
    // * @see org.eclipse.gef.tools.TargetingTool#doAutoexpose()
    // */
    // @Override
    // protected void doAutoexpose() {
    // super.doAutoexpose();
    // }
    //
    // public void doAutoScroll() {
    // updateAutoexposeHelper();
    // // autoUpdate();
    // }
    //    
    // @Override
    // protected void updateAutoexposeHelper() {
    // // super.updateAutoexposeHelper();
    // autoUpdate();
    // }
    //
    // private void autoUpdate() {
    // exposeHelper = getAutoexposeHelper();
    // if(exposeHelper == null){
    // return;
    // }
    // Search search;
    // search = new Search(getLocation());
    // getCurrentViewer().findObjectAtExcluding(getLocation(),
    // Collections.EMPTY_LIST, search);
    // setAutoexposeHelper(search.result);
    // // updateAutoexposeHelper();
    // }
    // private AutoexposeHelper exposeHelper = getAutoexposeHelper();
    // /**
    // * @see
    // org.eclipse.gef.tools.TargetingTool#setAutoexposeHelper(org.eclipse.gef.AutoexposeHelper)
    // */
    // @Override
    // protected void setAutoexposeHelper(AutoexposeHelper helper) {
    // exposeHelper = helper;
    // if (exposeHelper == null)
    // return;
    //
    // Display.getCurrent().asyncExec(new QueuedAutoexpose(helper));
    //        
    // // super.setAutoexposeHelper(helper);
    // }
    // class QueuedAutoexpose implements Runnable {
    // private AutoexposeHelper helper;
    // public QueuedAutoexpose(AutoexposeHelper helper) {
    // // TODO Auto-generated constructor stub
    // this.helper = helper;
    // }
    //
    // public void run() {
    // if (helper != null)
    // doAutoexpose();
    // }
    // }
    // class Search implements EditPartViewer.Conditional {
    // /**
    // * Constructs a new Search at a point on the viewer.
    // * @param pt the mouse location
    // */
    // public Search(Point pt) {
    // where = pt;
    // }
    //        
    // /**
    // * the result of the search.
    // */
    // private Point where;
    // public AutoexposeHelper result;
    // public boolean evaluate(EditPart editpart) {
    // EditPart diagramEditPart = editpart.getRoot();
    // result =
    // (AutoexposeHelper)diagramEditPart.getAdapter(AutoexposeHelper.class);
    // // if (result != null && result.detect(where))
    // // return true;
    // // result = null;
    // // return false;
    // return true;
    // }
    // }
}
