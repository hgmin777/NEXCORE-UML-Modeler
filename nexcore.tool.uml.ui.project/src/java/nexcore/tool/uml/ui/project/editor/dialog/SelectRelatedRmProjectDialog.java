/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.dialog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.rmdata.RMObject;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.dialog</li>
 * <li>설  명 : SelectRelatedRmProjectDialog</li>
 * <li>작성일 : 2010. 6. 11.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class SelectRelatedRmProjectDialog extends Dialog {

    /** 테이블 뷰어 객체 */
    private TableViewer tableViewer;

    /** 프로젝트 목록 */
    private List<RMObject> projectList;

    /** 선택한 프로젝트 ID */
    private String selectedProjectId;

    /** 선택한 프로젝트 */
    private RMObject selectedProject;

    /** 검색 필터 라벨 */
    private Label filterLabel;

    /** 검색 필터 텍스트 */
    private Text filterText;

    /** 선택된 RM 객체 */
    private static RMObject selectedRMObject;

    /**
     * 생성자
     * 
     * @param shell
     */
    public SelectRelatedRmProjectDialog(Shell shell) {
        super(shell);
    }

    /**
     * @param shell
     * @param projectList
     * @param selectedProjectId
     */
    public SelectRelatedRmProjectDialog(Shell shell, List<RMObject> projectList, String selectedProjectId) {
        this(shell);

        this.projectList = projectList;
        this.selectedProjectId = selectedProjectId;

    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        GridLayout gridLayout = new GridLayout();
        composite.setLayout(gridLayout);

        Composite filterComposite = new Composite(composite, SWT.NONE);
        gridLayout = new GridLayout(2, false);
        filterComposite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        filterComposite.setLayoutData(gridData);

        filterLabel = new Label(filterComposite, SWT.None);
        filterLabel.setText(UMLMessage.LABEL_SEARCH_FILTER);

        filterText = new Text(filterComposite, SWT.NONE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        filterText.setLayoutData(gridData);

        createTableViewerControl(composite, projectList);
        initializeDialogList();

        filterText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                ViewerFilter[] viewerFilters = new ViewerFilter[1];
                viewerFilters[0] = new ViewerFilter() {
                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        RMObject rmObject = (RMObject) element;
                        if (!rmObject.getRMObjectName().contains(filterText.getText())) {
                            return false;
                        } else {
                            return true;
                        }

                    }
                };
                tableViewer.setFilters(viewerFilters);
            }
        });

        return composite;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#initializeBounds()
     */
    @Override
    protected void initializeBounds() {
        super.initializeBounds();

        Shell shell = this.getShell();
        shell.setText(UMLMessage.LABEL_SELECT_THE_RELATED_RM_PROJECT);//$NON-NLS-1$
        shell.setSize(700, 600);
    }

    /**
     * 테이블 뷰어 컨트롤 생성
     * 
     * @param parent
     * @param projectList
     *            void
     */
    private void createTableViewerControl(Composite parent, final List<RMObject> projectList) {
        Composite tableViewerComposite = new Composite(parent, SWT.NONE);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.CHECK | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
        Table table = tableViewer.getTable();

        GridData tableGridData = new GridData(GridData.FILL_BOTH);
        table.setLayoutData(tableGridData);

        TableColumnLayout layout = new TableColumnLayout();
        tableViewerComposite.setLayout(layout);

        TableColumn requirementIdColumn = new TableColumn(table, SWT.CENTER);
        requirementIdColumn.setText(UMLMessage.LABEL_ID);
        layout.setColumnData(requirementIdColumn, new ColumnWeightData(2));

        TableColumn requirementNameColumn = new TableColumn(table, SWT.LEFT);
        requirementNameColumn.setText(UMLMessage.LABEL_NAME);
        layout.setColumnData(requirementNameColumn, new ColumnWeightData(8));

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new ProjectLabelProvider());
        tableViewer.getTable().addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = tableViewer.getTable().getItems();

                for (TableItem item : items) {
                    if (item.equals(e.item)) {
                        selectedRMObject = (RMObject) (item.getData());
                        item.setChecked(true);
                    } else {
                        item.setChecked(false);
                    }
                }
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        tableViewer.setInput(projectList.toArray());
        tableViewer.getTable().getColumn(0).addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                if (tableViewer.getSorter() == null || ((NewViewerSorter) tableViewer.getSorter()).sortingKind == 1) {
                    tableViewer.setSorter(new NewViewerSorter(0));
                    reSelectItem();
                } else {
                    tableViewer.setSorter(new NewViewerSorter(1));
                    reSelectItem();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        tableViewer.getTable().getColumn(1).addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                if (tableViewer.getSorter() == null || ((NewViewerSorter) tableViewer.getSorter()).sortingKind != 2) {
                    tableViewer.setSorter(new NewViewerSorter(2));
                    reSelectItem();
                } else {
                    tableViewer.setSorter(new NewViewerSorter(3));
                    reSelectItem();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }

    /**
     * 
     * void
     */
    private void reSelectItem() {
        TableItem[] items = tableViewer.getTable().getItems();
        if (selectedRMObject != null) {
            for (TableItem item : items) {
                if (item.getData().equals(selectedRMObject)) {
                    item.setChecked(true);
                }
            }
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project_v1.0</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.dialog</li>
     * <li>설 명 : NewViewerSorter</li>
     * <li>작성일 : 2010. 6. 11.</li>
     * <li>작성자 : 오은주</li>
     * </ul>
     */
    public class NewViewerSorter extends ViewerSorter {
        public int sortingKind = 0;

        public NewViewerSorter(int sortingKind) {
            this.sortingKind = sortingKind;
        }

        @Override
        public int compare(Viewer viewer, Object e1, Object e2) {
            switch (this.sortingKind) {
                case 0:
                    return ((RMObject) e1).getRMObjectId().compareTo(((RMObject) e2).getRMObjectId());
                case 1:
                    return ((RMObject) e2).getRMObjectId().compareTo(((RMObject) e1).getRMObjectId());
                case 2:
                    return ((RMObject) e1).getRMObjectName().compareTo(((RMObject) e2).getRMObjectName());
                case 3:
                    return ((RMObject) e2).getRMObjectName().compareTo(((RMObject) e1).getRMObjectName());
                default:
                    return 0;
            }
        }
    }

    /**
     * 정렬 기능
     * 
     * @param projectList
     * @param bool
     * @return List
     */
    public List getSortedList(List projectList, final boolean bool) {
        Comparator<RMObject> comparator = new Comparator<RMObject>() {
            public int compare(RMObject o1, RMObject o2) {
                if (bool) {
                    return o1.getRMObjectId().compareTo(o2.getRMObjectId());
                } else {
                    return o1.getRMObjectName().compareTo(o2.getRMObjectName());
                }

            }
        };
        Collections.sort(projectList, comparator);
        return projectList;
    }

    /**
     * 다이얼로그 목록 초기화
     * 
     * void
     */
    private void initializeDialogList() {
        TableItem[] items = tableViewer.getTable().getItems();

        for (TableItem item : items) {
            if (selectedProjectId.equals(((RMObject) item.getData()).getRMObjectId())) {
                item.setChecked(true);
            }
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        TableItem[] items = tableViewer.getTable().getItems();

        for (TableItem item : items) {
            if (!item.isDisposed()) {
                if (item.getChecked()) {
                    selectedProject = (RMObject) item.getData();
                }
            }
        }

        if (selectedProject != null) {
            super.okPressed();
        }
    }

    /**
     * 선택한 프로젝트 반환
     * 
     * @return Object
     */
    public RMObject getSelectedProject() {
        return selectedProject;
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project_v1.0</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.dialog</li>
     * <li>설 명 : ProjectLabelProvider</li>
     * <li>작성일 : 2010. 6. 11.</li>
     * <li>작성자 : 오은주</li>
     * </ul>
     */
    class ProjectLabelProvider implements ITableLabelProvider {
        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
         *      int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
         *      int)
         */
        public String getColumnText(Object element, int columnIndex) {
            String result = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

            if (element != null) {
                RMObject requirement = (RMObject) element;

                switch (columnIndex) {
                    case 0:
                        result = requirement.getRMObjectId();
                        break;
                    case 1:
                        result = requirement.getRMObjectName();
                        break;
                    default:
                        break;
                }
            }

            return result;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
            // TODO Auto-generated method stub

        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
            // TODO Auto-generated method stub

        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
            // TODO Auto-generated method stub

        }

    }

}
