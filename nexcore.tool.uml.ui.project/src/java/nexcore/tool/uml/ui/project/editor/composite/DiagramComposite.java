/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.composite;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.editor.provider.DiagramTableViewerLabelProvider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.composite</li>
 * <li>설  명 : DiagramComposite</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class DiagramComposite extends Composite {

    /** 컴포짓을 호출한 섹션 */
    private AbstractSection callerSection;

    /** 모델 인스턴스 */
    private Model model;

    /** 다이어그램 테이블 뷰어 */
    private TableViewer diagramTableViewer;

    /** 다이어그램 테이블 */
    private Table table;

    /**
     * diagramResources
     */
    private List<Diagram> diagramResources;

    /**
     * 생성자
     * 
     * @param parent
     * @param model
     */
    public DiagramComposite(Composite parent, Model model) {
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
    public DiagramComposite(AbstractSection callerSection, Composite parent, Model model) {
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

        gridLayout.numColumns = 2;
        composite.setLayout(gridLayout);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        composite.setLayoutData(gridData);

        createTableControl(composite);
        createButtonControl(composite);
    }

    /**
     * 
     *  
     * @param composite void
     */
    private void createButtonControl(Composite composite) {
        
        Composite buttonComposite = new Composite(composite, SWT.TOP);
        GridLayout buttonGridLayout = new GridLayout();
        buttonGridLayout.verticalSpacing = 5;
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, false, true);
        buttonComposite.setLayoutData(gridData);
        buttonComposite.setLayout(buttonGridLayout);
        
        createOpenButton(buttonComposite);
    }

    /**
     * 
     *  
     * @param buttonComposite void
     */
    private void createOpenButton(Composite buttonComposite) {
        
        Button openButton = new Button(buttonComposite, SWT.PUSH);
        openButton.setText(UMLMessage.LABEL_OPEN);//"열기");
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
        gridData.widthHint = 60;
        openButton.setLayoutData(gridData);
        
        openButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                
                ISelection selection = diagramTableViewer.getSelection();
                StructuredSelection structuredSelection = null;
                if( selection instanceof StructuredSelection ) {
                    structuredSelection = (StructuredSelection) selection;
                }
                
                if( null != structuredSelection ) {
                    ProjectUtil.openDiagram((EObject) structuredSelection.getFirstElement());
                }
            }
        });
        
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
        gridData.heightHint = 600;

        tablePartComposite.setLayoutData(gridData);

        TableColumnLayout tableColumnLayout = new TableColumnLayout();
        tablePartComposite.setLayout(tableColumnLayout);

        diagramTableViewer = new TableViewer(tablePartComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);

        table = diagramTableViewer.getTable();

//        TableColumn diagramTypeTableColumn = new TableColumn(table, SWT.LEFT);
//        diagramTypeTableColumn.setText(UMLMessage.LABEL_DIAGRAM_TYPE);//"다이어그램 타입");
//        tableColumnLayout.setColumnData(diagramTypeTableColumn, new ColumnWeightData(3));

        TableColumn diagramNameTableColumn = new TableColumn(table, SWT.LEFT);
        diagramNameTableColumn.setText(UMLMessage.LABEL_DIAGRAM_NAME);//"다이어그램명");
        tableColumnLayout.setColumnData(diagramNameTableColumn, new ColumnWeightData(3));
        

        TableColumn diagramPathTableColumn = new TableColumn(table, SWT.LEFT);
        diagramPathTableColumn.setText(UMLMessage.LABEL_DIAGRAM_FILE_PATH);//"다이어그램 경로");
        tableColumnLayout.setColumnData(diagramPathTableColumn, new ColumnWeightData(6));

        diagramTableViewer.setContentProvider(new ArrayContentProvider());
        diagramTableViewer.setLabelProvider(new DiagramTableViewerLabelProvider());
        
        diagramTableViewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                
                ISelection selection = event.getSelection();
                StructuredSelection structuredSelection = null;
                if( selection instanceof StructuredSelection ) {
                    structuredSelection = (StructuredSelection) selection;
                }
                
                if( null != structuredSelection ) {
                    ProjectUtil.openDiagram((EObject) structuredSelection.getFirstElement());
                }
                
            }
        });
        

        if( diagramResources != null ) {
            diagramTableViewer.setInput(diagramResources);
        }
        diagramTableViewer.refresh();

        table.setHeaderVisible(true);
        table.setLinesVisible(false);

    }


    /**
     * @param model
     *            the model to set
     */
    public void setModel(Model model) {
        this.model = model;
        
        List<Diagram> diagrams = new ArrayList<Diagram>();
        diagrams.addAll( ProjectUtil.getDiagrams(model, DiagramType.ACTIVITY_DIAGRAM) );
        diagrams.addAll( ProjectUtil.getDiagrams(model, DiagramType.CLASS_DIAGRAM) );
        diagrams.addAll( ProjectUtil.getDiagrams(model, DiagramType.COMPONENT_DIAGRAM) );
        diagrams.addAll( ProjectUtil.getDiagrams(model, DiagramType.SEQUENCE_DIAGRAM) );
        diagrams.addAll( ProjectUtil.getDiagrams(model, DiagramType.USE_CASE_DIAGRAM) );
        
        List<Resource> resources = new ArrayList<Resource>();
        for( Element element : model.allOwnedElements() ) {
            diagrams.addAll( ProjectUtil.getDiagrams(element, DiagramType.ACTIVITY_DIAGRAM) );
            diagrams.addAll( ProjectUtil.getDiagrams(element, DiagramType.CLASS_DIAGRAM) );
            diagrams.addAll( ProjectUtil.getDiagrams(element, DiagramType.COMPONENT_DIAGRAM) );
            diagrams.addAll( ProjectUtil.getDiagrams(element, DiagramType.SEQUENCE_DIAGRAM) );
            diagrams.addAll( ProjectUtil.getDiagrams(element, DiagramType.USE_CASE_DIAGRAM) );
        }
        
        diagramResources = diagrams;
    }


    /**
     * 다이어그램 테이블 뷰어 반환
     * 
     * @return the diagramTableViewer
     */
    public TableViewer getDiagramTableViewer() {
        return diagramTableViewer;
    }

}
