/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.composite;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.editor.provider.FragmentTableViewerLabelProvider;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.composite</li>
 * <li>설  명 : FragmentComposite</li>
 * <li>작성일 : 2012. 1. 30.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class FragmentComposite extends Composite {

    /** 컴포짓을 호출한 섹션 */
    private AbstractSection callerSection;

    /** 모델 인스턴스 */
    private Model model;

    /** 프래그먼트 테이블 뷰어 */
    private TableViewer fragmentTableViewer;

    /** 프래그먼트 테이블 */
    private Table table;

    /**
     * fragmentResources
     */
    private List<Resource> fragmentResources;

    /**
     * 생성자
     * 
     * @param parent
     * @param model
     */
    public FragmentComposite(Composite parent, Model model) {
        super(parent, SWT.NULL);

        setModel(model);
        createComposite(this);
    }

    /**
     * 생성자
     * 
     * @param callerSection
     * @param parent
     * @param model
     */
    public FragmentComposite(AbstractSection callerSection, Composite parent, Model model) {
        this(parent, model);

        this.callerSection = callerSection;
    }

    /**
     * 컴포짓 생성
     * 
     * @param composite
     *            void
     */
    private void createComposite(Composite composite) {
        GridLayout gridLayout = new GridLayout();
        composite.setLayout(gridLayout);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        composite.setLayoutData(gridData);

        createTableControl(composite);
    }

    /**
     * 테이블 컨트롤 생성
     * 
     * @param composite
     *            void
     */
    private void createTableControl(Composite composite) {
        
        Composite tablePartComposite = new Composite(composite, SWT.FILL);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.heightHint = 300;
        tablePartComposite.setLayoutData(gridData);

        TableColumnLayout tableColumnLayout = new TableColumnLayout();
        tablePartComposite.setLayout(tableColumnLayout);

        fragmentTableViewer = new TableViewer(tablePartComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);

        table = fragmentTableViewer.getTable();

        TableColumn fragmentNameTableColumn = new TableColumn(table, SWT.LEFT);
        fragmentNameTableColumn.setText(UMLMessage.LABEL_FRAGMENTED_ELEMENT_NAME);//"단편화된 엘리먼트명");

        tableColumnLayout.setColumnData(fragmentNameTableColumn, new ColumnWeightData(4));

        TableColumn fragmentFilePathTableColumn = new TableColumn(table, SWT.LEFT);
        fragmentFilePathTableColumn.setText(UMLMessage.LABEL_FRAGMENTED_ELEMENT_FILE_PATH);//"단편화 파일 경로");

        tableColumnLayout.setColumnData(fragmentFilePathTableColumn, new ColumnWeightData(6));

        fragmentTableViewer.setContentProvider(new ArrayContentProvider());
        fragmentTableViewer.setLabelProvider(new FragmentTableViewerLabelProvider());

        if( fragmentResources != null ) {
            fragmentTableViewer.setInput(fragmentResources);
        }
        fragmentTableViewer.refresh();

        table.setHeaderVisible(true);
        table.setLinesVisible(false);

    }


    /**
     * @param model
     *            the model to set
     */
    public void setModel(Model model) {
        this.model = model;
        
        List<Resource> resources = new ArrayList<Resource>();
        for( Resource res : ProjectUtil.getFragmentedFileList(model.eResource()) ) {
            if( UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(res.getURI().fileExtension()) ) {
                resources.add(res);
            }
        }
        fragmentResources = resources;
    }


    /**
     * 프래그먼트 테이블 뷰어 반환
     *  
     * @return TableViewer
     */
    public TableViewer getFragmentTableViewer() {
        return fragmentTableViewer;
    }

}
