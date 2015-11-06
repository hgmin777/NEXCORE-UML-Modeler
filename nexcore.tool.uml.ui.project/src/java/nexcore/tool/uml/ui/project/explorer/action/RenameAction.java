/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.NavigatorActionService;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : RenameAction</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class RenameAction extends CommonActionDelegate {

    public static boolean IS_RENAME_MODE = false;

    /** project explorer tree */
    private Tree navigatorTree;

    /** text editor to edit name. */
    private Text textEditor;

    /** text editor parent */
    private Composite textEditorParent;

    /** tree editor */
    private TreeEditor treeEditor;

    /** text action handler */
    private TextActionHandler textActionHandler;

    /** status of the resource */
    private boolean saving = false;
    

    /**
     * RenameAction
     */
    public RenameAction() {
        // RND:refresh()
        navigatorTree = ViewerRegistry.getViewer().getTree();
        treeEditor = new TreeEditor(navigatorTree);
    }

    /**
     * to handle rename action on creation
     * 
     * @param selectedEObject
     */
    public RenameAction(EObject eobj) {
        navigatorTree = ViewerRegistry.getViewer().getTree();
        treeEditor = new TreeEditor(navigatorTree);
        selectedEObject = eobj;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        selectionChanged(null, ViewerRegistry.getViewer().getSelection());

        if (selectedEObject instanceof NamedElement) {
            if (((NamedElement) selectedEObject).getName() == null) {
                return;
            }
        }

        run(null);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        IS_RENAME_MODE = true;
        
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        NavigatorActionService actionService = commonViewer.getCommonNavigator().getNavigatorActionService();
        
        actionService.fillActionBars(commonViewer.getCommonNavigator().getViewSite()
            .getActionBars());
        
        // Make sure text editor is created only once. Simply reset text
        // editor when action is executed more than once. Fixes bug 22269.
        if (textEditorParent == null) {
            createTextEditor();

            if (textEditorParent == null)
                return;
        }
        if (selectedEObject instanceof NamedElement) {
            textEditor.setText(((NamedElement) selectedEObject).getName());
        } else if (selectedEObject instanceof Diagram) {
            textEditor.setText(((Diagram) selectedEObject).getName());
        } else {
            return;
        }
        // ITreeNode targetNode = null;
        // targetNode = UMLTreeNodeRegistry.getTreeNode(eobject);
        // Open text editor with initial size.
        textEditorParent.setVisible(true);
        Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        textSize.x += textSize.y; // Add extra space for new characters.
        Point parentSize = textEditorParent.getSize();
        int inset = 1;
        textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
        textEditorParent.redraw();
        textEditor.selectAll();
        textEditor.setFocus();
    }

    /**
     * create text editor parent.
     * 
     * @return Composite
     */
    Composite createParent() {
        Tree tree = navigatorTree;
        Composite result = new Composite(tree, SWT.NONE);
        TreeItem[] selectedItems = tree.getSelection();

        if (selectedItems == null || selectedItems.length < 1) {
            return null;
        }

        treeEditor.horizontalAlignment = SWT.LEFT;
        treeEditor.grabHorizontal = true;
        treeEditor.setEditor(result, selectedItems[0]);
        return result;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        super.selectionChanged(action, selection);

        disposeTextWidget();
    }

    /**
     * create text editor.
     */
    private void createTextEditor() {
        // Create text editor parent. This draws a nice bounding rect.
        textEditorParent = createParent();
        if (textEditorParent == null) {
            return;
        }

        textEditorParent.setVisible(false);
        final int inset = 1;
        if (inset > 0) {
            textEditorParent.addListener(SWT.Paint, new Listener() {
                /**
                 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                 */
                public void handleEvent(Event e) {
                    Point textSize = textEditor.getSize();
                    Point parentSize = textEditorParent.getSize();
                    e.gc.drawRectangle(0, 0, Math.min(textSize.x + 4, parentSize.x - 1), parentSize.y - 1);
                }
            });
        }
        // Create inner text editor.
        textEditor = new Text(textEditorParent, SWT.NONE);
        textEditor.setFont(navigatorTree.getFont());
        textEditorParent.setBackground(textEditor.getBackground());

        textEditor.addListener(SWT.Modify, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event e) {
                Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                textSize.x += textSize.y; // Add extra space for new characters.
                Point parentSize = textEditorParent.getSize();
                textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
                textEditorParent.redraw();
            }
        });

        textEditor.addListener(SWT.Traverse, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                // Workaround for Bug 20214 due to extra traverse events
                switch (event.detail) {
                    case SWT.TRAVERSE_ESCAPE:
                        // Do nothing in this case
                        disposeTextWidget();
                        event.doit = true;
                        event.detail = SWT.TRAVERSE_NONE;
                        break;
                    case SWT.TRAVERSE_RETURN:
                        saveChangesAndDispose();
                        event.doit = true;
                        event.detail = SWT.TRAVERSE_NONE;
                        break;
                    default:
                        break;
                }
            }
        });

        textEditor.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             */
            public void focusLost(FocusEvent fe) {
                saveChangesAndDispose();
            }
        });

        if (textActionHandler != null) {
            textActionHandler.addText(textEditor);
        }
    }

    /**
     * Close the text widget and reset the editorText field.
     */
    private void disposeTextWidget() {
        IS_RENAME_MODE = false;
        
        if (textActionHandler != null) {
            textActionHandler.removeText(textEditor);
        }

        if (textEditorParent != null) {
            textEditorParent.dispose();
            textEditorParent = null;
            textEditor = null;
            treeEditor.setEditor(null, null);
        }
    }

    /**
     * Save the changes and dispose of the text widget.
     * 
     * @param resource
     *            - the resource to move.
     */
    private void saveChangesAndDispose() {
        if (saving == true) {
            return;
        }

        saving = true;
        // Cache the resource to avoid selection loss since a selection of
        // another item can trigger this method

        final String newName = textEditor.getText();
        // Run this in an async to make sure that the operation that triggered
        // this action is completed. Otherwise this leads to problems when the
        // icon of the item being renamed is clicked (i.e., which causes the
        // rename text widget to lose focus and trigger this method).
        // Runnable query = new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        // public void run() {
        try {
            String objName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            if (selectedEObject instanceof NamedElement) {
                objName = ((NamedElement) selectedEObject).getName();
            } else if (selectedEObject instanceof Diagram) {
                objName = ((Diagram) selectedEObject).getName();
            }
            
            if (UICoreConstant.EMPTY_STRING.equals(newName.trim())) {
            	MessageDialog.openWarning(ProjectExplorerPlugin.getShell(), 
            			UMLMessage.LABEL_NEXCORE_UML_MODELER, UMLMessage.MESSAGE_CANNOT_BE_EMPTY_NAME);
            	
            } else if (!newName.equals(objName)) {
                // DomainRegistry.getUMLDomain().getCommandStack().execute(new
                // Command() {
                DomainRegistry.getEditingDomain()
                    .getCommandStack()
                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                        // private String oldName =
                        // UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                        /**
                         * @see org.eclipse.gef.commands.Command#execute()
                         */
                        // public void execute() {
                        /**
                         * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (selectedEObject instanceof NamedElement) {
                                // oldName = ((NamedElement)eobject).getName();
                                ((NamedElement) selectedEObject).setName(newName);
                            } else if (selectedEObject instanceof Diagram) {
                                // oldName = ((Diagram)eobject).getName();
                                ((Diagram) selectedEObject).setName(newName);
                                // UMLProjectUtil.refreshExplorer(eobject.eResource().getContents().get(0),
                                // eobject);
                                // ProjectUtil.refreshExplorer(((Diagram)
                                // eobject).getParent(), eobject);
                            }
                            // UMLProjectUtil.refreshAllEditorPath();
                        }

                        /**
                         * @see org.eclipse.gef.commands.Command#undo()
                         */
                        // @Override
                        // public void undo() {
                        // if( eobject instanceof NamedElement ) {
                        // ((NamedElement)eobject).setName(oldName);
                        // } else if( eobject instanceof Diagram ) {
                        // ((Diagram)eobject).setName(oldName);
                        // ProjectUtil.refreshExplorer(((Diagram)
                        // eobject).getParent(), eobject);
                        // }
                        // }
                    });
            }

            // Dispose the text widget regardless
            disposeTextWidget();

            // Ensure the Navigator tree has focus,
            // which it may not if the text widget previously had focus.
            if (navigatorTree != null && !navigatorTree.isDisposed()) {
                navigatorTree.setFocus();
            }
        } finally {
            saving = false;
            // ProjectUtil.refreshExplorer(eobject, eobject);
        }
        // }
        // };
        // navigatorTree.getShell().getDisplay().asyncExec(query);
    }

}
