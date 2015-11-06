/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.preferences.pages;

import java.util.Iterator;
import java.util.TreeSet;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.project.explorer.ExplorerElementFilter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설 명 : ProjectExplorerPreferencePage</li>
 * <li>작성일 : 2010. 2. 11.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ProjectExplorerPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** allList */
    private Table allTable;

    /** filteredList */
    private Table filteredTable;

    /** Filter 기능 구현부 */
    private ExplorerElementFilter filter;

    /** CommonViewer */
    private CommonViewer viewer;

    /** Table Width */
    private int TABLE_WIDTH = 200;

    /** Table Height */
    private int TABLE_HEIGHT = 320;

    /**
     * Default constructor.
     */
    public ProjectExplorerPreferencePage() {
        filter = getExplorerFilter();
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {

        if (filter != null) {
            filter.init();
        }
    }

    /**
     * 
     * 
     * @return ExplorerElementFilter
     */
    private ExplorerElementFilter getExplorerFilter() {
        viewer = ViewerRegistry.getViewer();

        if (viewer != null) {
            ViewerFilter[] filter = viewer.getFilters();
            ExplorerElementFilter eFilter = null;
            for (int i = 0; i < filter.length; i++) {
                if (filter[i] instanceof ExplorerElementFilter) {
                    eFilter = (ExplorerElementFilter) filter[i];
                    return eFilter;
                }
            }
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {

        if (filter == null) {
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.MESSAGE_DIALOG_TITLE,
                UMLMessage.MESSAGE_PROJECT_APPLY_FILTER);
            return null;
        }

        Group filterGrp = createFilterGroup(parent);

        GridLayout layout = new GridLayout(3, false);
        filterGrp.setLayout(layout);

        /*
         * Label
         */
        GridData gd = new GridData(SWT.LEAD, SWT.CENTER, false, false);
        gd.horizontalSpan = 2;
        Label availLabel = new Label(filterGrp, SWT.NULL);
        availLabel.setText(UMLMessage.LABEL_PREFERENCE_PROJECT_ALLELEMENT);
        availLabel.setLayoutData(gd);

        gd = new GridData(SWT.LEAD, SWT.CENTER, false, false);
        Label filteredLabel = new Label(filterGrp, SWT.NULL);
        filteredLabel.setText(UMLMessage.LABEL_PREFERENCE_PROJECT_FILTERED_ELEMENT);
        filteredLabel.setLayoutData(gd);

        /*
         * Available UML Elements Table
         */
        gd = new GridData(SWT.LEAD, SWT.CENTER, false, false);
        gd.widthHint = TABLE_WIDTH;
        gd.heightHint = TABLE_HEIGHT;
        gd.verticalSpan = 2;
        allTable = new Table(filterGrp, SWT.BORDER | SWT.MULTI);
        allTable.setLayoutData(gd);
        setAllTableData();

        /*
         * Button : Available UML Elements -> Filtered UML Elements
         */
        gd = new GridData(SWT.CENTER, SWT.BOTTOM, false, false);
        gd.heightHint = TABLE_HEIGHT / 2;
        Button toFilteredBtn = new Button(filterGrp, SWT.NULL);
        toFilteredBtn.setText(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
        toFilteredBtn.addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                int[] idx = allTable.getSelectionIndices();
                if (idx.length < 1)
                    return;

                TableItem item = null;
                for (int i = idx.length - 1; i > -1; i--) {
                    item = allTable.getItem(idx[i]);
                    filter.setFilter(item.getText());
                }
                setAllTableData();
                setFilteredTableData();
            }

        });
        toFilteredBtn.setLayoutData(gd);

        /*
         * Filtered UML Elements Table
         */
        gd = new GridData(SWT.LEAD, SWT.CENTER, false, false);
        gd.widthHint = TABLE_WIDTH;
        gd.heightHint = TABLE_HEIGHT;
        gd.verticalSpan = 2;
        filteredTable = new Table(filterGrp, SWT.BORDER | SWT.MULTI);
        filteredTable.setLayoutData(gd);
        setFilteredTableData();

        /*
         * Button : Available UML Elements <- Filtered UML Elements
         */
        gd = new GridData(SWT.CENTER, SWT.TOP, false, false);
        gd.heightHint = TABLE_HEIGHT / 2;
        Button toAvailBtn = new Button(filterGrp, SWT.NULL);
        toAvailBtn.setText(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
        toAvailBtn.addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                int[] idx = filteredTable.getSelectionIndices();
                if (idx.length < 1)
                    return;

                TableItem item = null;
                for (int i = idx.length - 1; i > -1; i--) {
                    item = filteredTable.getItem(idx[i]);
                    filter.unsetFilter(item.getText());
                }

                setAllTableData();
                setFilteredTableData();
            }

        });
        toAvailBtn.setLayoutData(gd);

        return filterGrp;
    }

    /**
     * 
     * void
     */
    private void setFilteredTableData() {
        filteredTable.removeAll();
        TreeSet<String> list = filter.getFilteredList();
        TableItem item = null;
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            item = new TableItem(filteredTable, SWT.NONE);
            item.setText(iterator.next());
        }
    }

    /**
     * 
     * void
     */
    private void setAllTableData() {
        allTable.removeAll();
        TreeSet<String> eleList = filter.getElementList();
        TableItem item = null;
        for (Iterator<String> iterator = eleList.iterator(); iterator.hasNext();) {
            item = new TableItem(allTable, SWT.NONE);
            item.setText(iterator.next());
        }
    }

    /**
     * 
     * 
     * @param parent
     * @return Group
     */
    private Group createFilterGroup(Composite parent) {
        Group group = new Group(parent, SWT.NULL);
        group.setText(UMLMessage.LABEL_PREFERENCE_PROJECT_FILTER);

        GridLayout layout = new GridLayout(3, false);
        group.setLayout(layout);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        group.setLayoutData(gridData);

        return group;
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        if (filter != null) {
            filter.setDefault();
            setAllTableData();
            setFilteredTableData();
        }
        super.performDefaults();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        if (filter != null) {
            filter.saveFilter();
        }
        if (viewer != null) {
            viewer.refresh();
        }
        return super.performOk();
    }

}
