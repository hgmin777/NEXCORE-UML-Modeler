/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation Igor Fedorenko
 * <igorfie@yahoo.com> - Fix for Bug 136921 [IDE] New File dialog locks for 20
 * seconds
 *******************************************************************************/
package nexcore.tool.uml.ui.project.explorer.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

/**
 * Workbench-level composite for choosing a container.
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.dialog</li>
 * <li>설  명 : ContainerSelectionGroup</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ContainerSelectionGroup extends Composite {
    // The listener to notify of events
    /**
     * listener
     */
    private Listener listener;

    // Enable user to type in new container name
    /**
     * allowNewContainerName
     */
    private boolean allowNewContainerName = true;

    // show all projects by default
    /**
     * showClosedProjects
     */
    private boolean showClosedProjects = true;

    // Last selection made by user
    /**
     * selectedContainer
     */
    private IContainer selectedContainer;

    // handle on parts
    /**
     * containerNameField
     */
    private Text containerNameField;

    /**
     * treeViewer
     */
    TreeViewer treeViewer;

    // the message to display at the top of this dialog
    /**
     * DEFAULT_MSG_NEW_ALLOWED
     */
    private static final String DEFAULT_MSG_NEW_ALLOWED = IDEWorkbenchMessages.ContainerGroup_message;

    /**
     * DEFAULT_MSG_SELECT_ONLY
     */
    private static final String DEFAULT_MSG_SELECT_ONLY = IDEWorkbenchMessages.ContainerGroup_selectFolder;

    // sizing constants
    /**
     * SIZING_SELECTION_PANE_WIDTH
     */
    private static final int SIZING_SELECTION_PANE_WIDTH = 320;

    /**
     * SIZING_SELECTION_PANE_HEIGHT
     */
    private static final int SIZING_SELECTION_PANE_HEIGHT = 300;

    /**
     * dialogType
     */
    private int dialogType;

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent
     *            The parent widget of the group.
     * @param listener
     *            A listener to forward events to. Can be null if no listener is
     *            required.
     * @param allowNewContainerName
     *            Enable the user to type in a new container name instead of
     *            just selecting from the existing ones.
     */
    // public ContainerSelectionGroup(Composite parent, Listener listener,
    // boolean allowNewContainerName) {
    // this(parent, listener, allowNewContainerName, null);
    // }
    /**
     * Creates a new instance of the widget.
     * 
     * @param parent
     *            The parent widget of the group.
     * @param listener
     *            A listener to forward events to. Can be null if no listener is
     *            required.
     * @param allowNewContainerName
     *            Enable the user to type in a new container name instead of
     *            just selecting from the existing ones.
     * @param message
     *            The text to present to the user.
     */
    // public ContainerSelectionGroup(Composite parent, Listener listener,
    // boolean allowNewContainerName, String message) {
    // this(parent, listener, allowNewContainerName, message, true);
    // }
    /**
     * Creates a new instance of the widget.
     * 
     * @param parent
     *            The parent widget of the group.
     * @param listener
     *            A listener to forward events to. Can be null if no listener is
     *            required.
     * @param allowNewContainerName
     *            Enable the user to type in a new container name instead of
     *            just selecting from the existing ones.
     * @param message
     *            The text to present to the user.
     * @param showClosedProjects
     *            Whether or not to show closed projects.
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName, String message,
    boolean showClosedProjects, int type) {
        this(parent,
             listener,
             allowNewContainerName,
             message,
             showClosedProjects,
             SIZING_SELECTION_PANE_HEIGHT,
             SIZING_SELECTION_PANE_WIDTH,
             type);
    }

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent
     *            The parent widget of the group.
     * @param listener
     *            A listener to forward events to. Can be null if no listener is
     *            required.
     * @param allowNewContainerName
     *            Enable the user to type in a new container name instead of
     *            just selecting from the existing ones.
     * @param message
     *            The text to present to the user.
     * @param showClosedProjects
     *            Whether or not to show closed projects.
     * @param heightHint
     *            height hint for the drill down composite
     * @param widthHint
     *            width hint for the drill down composite
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName, String message,
    boolean showClosedProjects, int heightHint, int widthHint, int type) {
        super(parent, SWT.NONE);
        this.listener = listener;
        this.allowNewContainerName = allowNewContainerName;
        this.showClosedProjects = showClosedProjects;
        this.dialogType = type;
        if (message != null) {
            createContents(message, heightHint, widthHint);
        } else if (allowNewContainerName) {
            createContents(DEFAULT_MSG_NEW_ALLOWED, heightHint, widthHint);
        } else {
            createContents(DEFAULT_MSG_SELECT_ONLY, heightHint, widthHint);
        }
    }

    /**
     * The container selection has changed in the tree view. Update the
     * container name field value and notify all listeners.
     * 
     * @param container
     *            The container that changed
     */
    public void containerSelectionChanged(IContainer container) {
        selectedContainer = container;

        if (allowNewContainerName) {
            if (container == null) {
                containerNameField.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
            } else {
                String text = container.getFullPath().makeRelative().toString();
                containerNameField.setText(text);
                containerNameField.setToolTipText(text);
            }
        }

        // fire an event so the parent can update its controls
        if (listener != null) {
            Event changeEvent = new Event();
            changeEvent.type = SWT.Selection;
            changeEvent.widget = this;
            listener.handleEvent(changeEvent);
        }
    }

    /**
     * Creates the contents of the composite.
     * 
     * @param message
     */
    public void createContents(String message) {
        createContents(message, SIZING_SELECTION_PANE_HEIGHT, SIZING_SELECTION_PANE_WIDTH);
    }

    /**
     * Creates the contents of the composite.
     * 
     * @param message
     * @param heightHint
     * @param widthHint
     */
    public void createContents(String message, int heightHint, int widthHint) {
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        setLayout(layout);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Label label = new Label(this, SWT.WRAP);
        label.setText(message);
        label.setFont(this.getFont());

        if (allowNewContainerName) {
            containerNameField = new Text(this, SWT.SINGLE | SWT.BORDER);
            GridData gd = new GridData(GridData.FILL_HORIZONTAL);
            gd.widthHint = widthHint;
            containerNameField.setLayoutData(gd);
            containerNameField.addListener(SWT.Modify, listener);
            containerNameField.setFont(this.getFont());
        } else {
            // filler...
            new Label(this, SWT.NONE);
        }

        createTreeViewer(heightHint);
        Dialog.applyDialogFont(this);
    }

    /**
     * Returns a new drill down viewer for this dialog.
     * 
     * @param heightHint
     *            height hint for the drill down composite
     */
    protected void createTreeViewer(int heightHint) {
        // Create drill down.
        DrillDownComposite drillDown = new DrillDownComposite(this, SWT.BORDER);
        GridData spec = new GridData(SWT.FILL, SWT.FILL, true, true);
        spec.widthHint = SIZING_SELECTION_PANE_WIDTH;
        spec.heightHint = heightHint;
        drillDown.setLayoutData(spec);

        // Create tree viewer inside drill down.
        treeViewer = new TreeViewer(drillDown, SWT.NONE);
        drillDown.setChildTree(treeViewer);

        ContainerContentProvider cp = new ContainerContentProvider(dialogType);
        cp.showClosedProjects(showClosedProjects);
        treeViewer.setContentProvider(cp);
        treeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        treeViewer.setComparator(new ViewerComparator());
        treeViewer.setUseHashlookup(true);
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                Object obj = selection.getFirstElement();
                if (obj instanceof File) {
                    File file = (File) obj;
                    obj = file.getParent();
                }
                containerSelectionChanged((IContainer) obj); // allow null
            }
        });
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    Object item = ((IStructuredSelection) selection).getFirstElement();
                    if (item == null) {
                        return;
                    }
                    if (treeViewer.getExpandedState(item)) {
                        treeViewer.collapseToLevel(item, 1);
                    } else {
                        treeViewer.expandToLevel(item, 1);
                    }
                }
            }
        });

        // This has to be done after the viewer has been laid out
        treeViewer.setInput(ResourcesPlugin.getWorkspace());
    }

    /**
     * Returns the currently entered container name. Null if the field is empty.
     * Note that the container may not exist yet if the user entered a new
     * container name in the field.
     * 
     * @return IPath
     */
    public IPath getContainerFullPath() {
        if (allowNewContainerName) {
            String pathName = containerNameField.getText();
            if (pathName == null || pathName.length() < 1) {
                return null;
            }
            // The user may not have made this absolute so do it for them
            return (new Path(pathName)).makeAbsolute();

        }
        if (selectedContainer == null)
            return null;
        return selectedContainer.getFullPath();

    }

    /**
     * Gives focus to one of the widgets in the group, as determined by the
     * group.
     */
    public void setInitialFocus() {
        if (allowNewContainerName) {
            containerNameField.setFocus();
        } else {
            treeViewer.getTree().setFocus();
        }
    }

    /**
     * Sets the selected existing container.
     * 
     * @param container
     */
    public void setSelectedContainer(IContainer container) {
        selectedContainer = container;

        // expand to and select the specified container
        List itemsToExpand = new ArrayList();
        IContainer parent = container.getParent();
        while (parent != null) {
            itemsToExpand.add(0, parent);
            parent = parent.getParent();
        }
        treeViewer.setExpandedElements(itemsToExpand.toArray());
        treeViewer.setSelection(new StructuredSelection(container), true);
    }
}
