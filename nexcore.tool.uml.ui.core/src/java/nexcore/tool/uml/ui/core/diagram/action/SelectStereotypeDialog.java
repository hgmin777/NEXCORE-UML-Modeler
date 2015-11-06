/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설  명 : SelectStereotypeDialog</li>
 * <li>작성일 : 2011. 6. 8.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class SelectStereotypeDialog extends Dialog {

    /** 테이블 뷰어 객체 */
    private TableViewer tableViewer;

    /** 적용 가능한 스테레오타입 목록 */
    private List<Stereotype> applicableStereotypeList = new ArrayList<Stereotype>();

    /** 이미 적용된 스테레오타입 목록 */
    private List<Stereotype> appliedStereotypeList = new ArrayList<Stereotype>();

    /** applicableStereotypeMap */
    private HashMap<Stereotype, Object> applicableStereotypeMap;
    /** appliedStereotypeMap */
    private HashMap<Stereotype, Object> appliedStereotypeMap;
    /** selectedElement */
    private List<Element> selectedElement;

    /**
     * 생성자
     * 
     * @param shell
     */
    public SelectStereotypeDialog(Shell shell) {
        super(shell);
    }

    /**
     * 생성자
     * 
     * @param shell
     * @param applicableStereotypeList
     * @param appliedStereotypeList
     * @param appliedStereotypeMap 
     * @param applicableStereotypeMap 
     * @param firstSelectedEObject 
     */
    public SelectStereotypeDialog(Shell shell, List<Stereotype> applicableStereotypeList,
    List<Stereotype> appliedStereotypeList, HashMap<Stereotype, Object> applicableStereotypeMap, HashMap<Stereotype, Object> appliedStereotypeMap, List<Element> firstSelectedEObject) {
        this(shell);

        this.applicableStereotypeList = applicableStereotypeList;
        this.appliedStereotypeList = appliedStereotypeList;
        this.applicableStereotypeMap = applicableStereotypeMap;
        this.appliedStereotypeMap = appliedStereotypeMap;
        this.selectedElement = firstSelectedEObject;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        GridLayout gridLayout = new GridLayout();
        composite.setLayout(gridLayout);

        createTableViewerControl(composite, applicableStereotypeList);

        initializeDialogList();

        return composite;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#initializeBounds()
     */
    @Override
    protected void initializeBounds() {
        super.initializeBounds();

        Shell shell = this.getShell();
        shell.setText(UMLMessage.LABEL_APPLY_STEREOTYPE);//$NON-NLS-1$
        shell.setSize(700, 500);
    }

    /**
     * 테이블 뷰어 컨트롤 생성
     * 
     * @param parent
     * @param applicableStereotypeList
     *            void
     */
    private void createTableViewerControl(Composite parent, List<Stereotype> applicableStereotypeList) {
        Composite tableViewerComposite = new Composite(parent, SWT.NONE);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.CHECK | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);
        Table table = tableViewer.getTable();

        GridData tableGridData = new GridData(GridData.FILL_BOTH);
        table.setLayoutData(tableGridData);

        TableColumnLayout layout = new TableColumnLayout();
        tableViewerComposite.setLayout(layout);

        TableColumn stereotypeNameColumn = new TableColumn(table, SWT.CENTER);
        stereotypeNameColumn.setText(UMLMessage.LABEL_NAME);
        layout.setColumnData(stereotypeNameColumn, new ColumnWeightData(4));

        TableColumn ownedProfileNameColumn = new TableColumn(table, SWT.CENTER);
        ownedProfileNameColumn.setText(UMLMessage.LABEL_UMLPROFILE);
        layout.setColumnData(ownedProfileNameColumn, new ColumnWeightData(6));

        TableColumn applicableTargetColumn = new TableColumn(table, SWT.CENTER);
        applicableTargetColumn.setText(UMLMessage.LABEL_APPLICABLE_ELEMENT_TYPE);
        layout.setColumnData(applicableTargetColumn, new ColumnWeightData(6));
        
        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new StereotypeLabelProvider());

        tableViewer.setInput(applicableStereotypeList.toArray());

        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }
    
    /**
     * getDialogInput
     *  
     * @return Object
     */
    public Object getDialogInput () {
        if( tableViewer != null ) {
            return tableViewer.getInput();
        }
        return null;
    }

    /**
     * 다이얼로그 목록 초기화
     * 
     * void
     */
    private void initializeDialogList() {
        TableItem[] items = tableViewer.getTable().getItems();

        for (TableItem item : items) {
            for (Stereotype stereotype : appliedStereotypeList) {
                if (stereotype.getQualifiedName().equals(((Stereotype) item.getData()).getQualifiedName())) {

                    boolean isGrayed = true;
                    for(Element element : selectedElement){
                        if(!element.getAppliedStereotypes().contains(stereotype)){
                            isGrayed = false;
                        }
                    }
                    
                    if(isGrayed){
                        item.setChecked(true);
                    } else {
                        item.setChecked(true);
                        item.setGrayed(true);
                    }
                }
            }
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        TableItem[] items = tableViewer.getTable().getItems();

        appliedStereotypeList = new ArrayList<Stereotype>();

        for (TableItem item : items) {
            if (item.getChecked()) {
                appliedStereotypeList.add((Stereotype) item.getData());
            }
        }

        super.okPressed();
    }

    /**
     * 선택한 스테레오타입 목록 반환
     * 
     * @return Object
     */
    public List<Stereotype> getSelectedStereotypeList() {
        return appliedStereotypeList;
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sheet.dialog</li>
     * <li>설 명 : StereotypeLabelProvider</li>
     * <li>작성일 : 2009. 12. 18.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class StereotypeLabelProvider implements ITableLabelProvider {
        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
         *      int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
         *      int)
         */
        public String getColumnText(Object element, int columnIndex) {
            String result = ""; //$NON-NLS-1$

            if (element != null) {
                Stereotype stereotype = (Stereotype) element;

                switch (columnIndex) {
                    case 0:
                        result = stereotype.getName();
                        break;
                    case 1:
                        result = stereotype.getProfile().getName();
                        break;
                    case 2:
                        List<EClassifier> list = (List<EClassifier>) applicableStereotypeMap.get(stereotype);
                        String str = ""; //$NON-NLS-1$
                        for(EClassifier child : list){
                            str += child.getName();
                            int index = list.indexOf(child);
                            if( index != list.size() - 1 ){
                                str += ", ";
                            }
                        }
                        result = str;
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
            // ignore
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
            // ignore
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
            // ignore
        }

    }
}
