/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.dialog;

import java.util.ArrayList;
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
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
 * <li>설  명 : SelectRequirementDialog</li>
 * <li>작성일 : 2010. 5. 13.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SelectRequirementDialog extends Dialog {

    /** 테이블 뷰어 객체 */
    private TableViewer tableViewer;

    /** 요구사항 목록 */
    private List<RMObject> requirementList;

    /** 이전에 선택한 요구사항 Id 목록 */
    private List<Object> alreadySelectedRequirementIdList;

    /** 선택한 요구사항 Id 목록 */
    private List<String> selectedRequirementIdList;

    /** 선택한 RM 객체 */
    private static RMObject selectedRMObject;

    /** 필터 라벨 */
    private Label filterLabel;

    /** 필터 텍스트 */
    private Text filterText;

    /** 필터 크기 */
    private int filterSize = 1;

    /**
     * 생성자
     * 
     * @param shell
     */
    public SelectRequirementDialog(Shell shell) {
        super(shell);
    }

    /**
     * 생성자
     * 
     * @param shell
     * @param requirementList
     */
    public SelectRequirementDialog(Shell shell, List<RMObject> requirementList,
    List<Object> alreadySelectedRequirementIdList) {
        this(shell);

        this.requirementList = requirementList;
        this.alreadySelectedRequirementIdList = alreadySelectedRequirementIdList;

        if (selectedRequirementIdList == null) {
            selectedRequirementIdList = new ArrayList<String>();
        }
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

        createTableViewerControl(composite, requirementList);
        initializeDialogList();

        filterText.addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                ViewerFilter[] viewerFilters = new ViewerFilter[filterSize];

                viewerFilters[0] = new ViewerFilter() {
                    /**
                     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
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
        shell.setText(UMLMessage.LABEL_SELECT_REQUIREMENT);//$NON-NLS-1$
        shell.setSize(700, 600);
    }

    /**
     * 테이블 뷰어 컨트롤 생성
     * 
     * @param parent
     * @param requirementList
     *            void
     */
    private void createTableViewerControl(Composite parent, final List<RMObject> requirementList) {
        Composite tableViewerComposite = new Composite(parent, SWT.NONE);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.MULTI | SWT.CHECK | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);
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
        tableViewer.setLabelProvider(new RequirementLabelProvider());

        // tableViewer.getTable().addSelectionListener(new SelectionListener() {
        // /**
        // * @see
        // org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
        // */
        // public void widgetSelected(SelectionEvent e) {
        // TableItem[] items = tableViewer.getTable().getItems();
        //
        // for(TableItem item : items) {
        // if(item.equals(e.item)) {
        // selectedRMObject = (RMObject) (item.getData());
        // item.setChecked(true);
        // } else {
        // item.setChecked(false);
        // }
        // }
        // }
        //
        // /**
        // * @see
        // org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
        // */
        // public void widgetDefaultSelected(SelectionEvent e) {}
        // });

        tableViewer.setInput(requirementList.toArray());
        tableViewer.getTable().getColumn(0).addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                if (tableViewer.getSorter() == null || ((NewViewerSorter) tableViewer.getSorter()).sortingKind == 1) {
                    tableViewer.setSorter(new NewViewerSorter(0));
                    reSelectItem();
                } else {
                    tableViewer.setSorter(new NewViewerSorter(1));
                    reSelectItem();
                }
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        tableViewer.getTable().getColumn(1).addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                if (tableViewer.getSorter() == null || ((NewViewerSorter) tableViewer.getSorter()).sortingKind != 2) {
                    tableViewer.setSorter(new NewViewerSorter(2));
                    reSelectItem();
                } else {
                    tableViewer.setSorter(new NewViewerSorter(3));
                    reSelectItem();
                }
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }

    /**
     * 항목 다시 선택후 체크 박스 설정 변경시키는 메소드 void
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
     * <li>업무 그룹명 : nexcore.tool.uml.ui.property_v1.0</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
     * <li>설 명 : NewViewerSorter</li>
     * <li>작성일 : 2010. 6. 14.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public class NewViewerSorter extends ViewerSorter {
        /** 정렬 종류 */
        public int sortingKind = 0;

        /**
         * 생성자
         * 
         * @param sortingKind
         */
        public NewViewerSorter(int sortingKind) {
            this.sortingKind = sortingKind;
        }

        /**
         * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
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
     * 정렬된 목록 반환
     * 
     * @param requirementList
     * @param bool
     * @return List
     */
    public List<RMObject> getSortedList(List<RMObject> requirementList, final boolean bool) {
        Comparator<RMObject> comparator = new Comparator<RMObject>() {
            public int compare(RMObject o1, RMObject o2) {
                if (bool) {
                    return o1.getRMObjectId().compareTo(o2.getRMObjectId());
                } else {
                    return o1.getRMObjectName().compareTo(o2.getRMObjectName());
                }

            }
        };

        Collections.sort(requirementList, comparator);

        return requirementList;
    }

    /**
     * 다이얼로그 목록 초기화
     * 
     * void
     */
    private void initializeDialogList() {
        TableItem[] items = tableViewer.getTable().getItems();

        String alreadySelectedRequirementId = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

        for (Object selectedRequirementIdObject : alreadySelectedRequirementIdList) {
            alreadySelectedRequirementId = (String) selectedRequirementIdObject;

            for (TableItem item : items) {
                if (alreadySelectedRequirementId.equals(((RMObject) item.getData()).getRMObjectId())) {
                    item.setChecked(true);
                    break;
                }
            }
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        // 이전에 선택된 요구사항 목록 지우기.
        if (selectedRequirementIdList.size() > 0) {
            selectedRequirementIdList.clear();
        }

        TableItem[] items = tableViewer.getTable().getItems();

        for (TableItem item : items) {
            if (!item.isDisposed()) {
                if (item.getChecked()) {
                    selectedRequirementIdList.add(((RMObject) item.getData()).getRMObjectId());
                }
            }
        }

        if (selectedRequirementIdList.size() > 0) {
            super.okPressed();
        }
    }

    /**
     * 선택한 요구사항 목록 반환
     * 
     * @return List<RMObject>
     */
    public List<String> getSelectedRequirementList() {
        return selectedRequirementIdList;
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.property_v1.0</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
     * <li>설 명 : RequirementLabelProvider</li>
     * <li>작성일 : 2010. 5. 13.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class RequirementLabelProvider implements ITableLabelProvider {
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
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) {
        }
    }
}
