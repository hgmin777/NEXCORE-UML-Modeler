/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.composite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.LibraryChooseDialog;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.command.HandleImportedPackageCommand;
import nexcore.tool.uml.ui.project.editor.provider.LibraryTableViewerLabelProvider;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.composite</li>
 * <li>설 명 : LibraryComposite</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class LibraryComposite extends Composite {

    /** 컴포짓을 호출한 섹션 */
    private AbstractSection callerSection;

    /** 모델 인스턴스 */
    private Model model;

    /** 적용할 모델 라이브러리 목록 */
    private List<Package> applyingLibraryList;

    /** 모델 라이브러리 테이블 뷰어 */
    private TableViewer libraryTableViewer;

    /** 모델 라이브러리 테이블 */
    private Table table;

    /** 모델 라이브러리 추가 버튼 */
    private Button addLibraryButton;

    /** 모델 라이브러리 삭제 버튼 */
    private Button deleteLibraryButton;

    /** 적용할 수 있는 라이브러리 목록 */
    private List<Package> applicableLibraryList;

    /**
     * 생성자
     * 
     * @param composite
     * @param model
     */
    public LibraryComposite(Composite parent, Model model) {
        super(parent, SWT.NULL);

        setModel(model);

        initializeApplicableLibraryList();

        createComposite(this);
    }

    /**
     * 생성자
     * 
     * @param callerSection
     * @param parent
     * @param model
     */
    public LibraryComposite(AbstractSection callerSection, Composite parent, Model model) {
        this(parent, model);

        this.callerSection = callerSection;
    }

    /**
     * 컴포짓
     * 
     * @param libraryComposite
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
     * 테이블 컨트롤 생성
     * 
     * @param composite
     *            void
     */
    private void createTableControl(Composite composite) {
        Composite tablePartComposite = new Composite(composite, SWT.FILL);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.heightHint = 150;

        tablePartComposite.setLayoutData(gridData);

        TableColumnLayout tableColumnLayout = new TableColumnLayout();
        tablePartComposite.setLayout(tableColumnLayout);

        libraryTableViewer = new TableViewer(tablePartComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);

        table = libraryTableViewer.getTable();

        TableColumn libraryNameTableColumn = new TableColumn(table, SWT.LEFT);
        libraryNameTableColumn.setText(UMLMessage.LABEL_LIBRARY_NAME);

        tableColumnLayout.setColumnData(libraryNameTableColumn, new ColumnWeightData(4));

        TableColumn libraryPathTableColumn = new TableColumn(table, SWT.LEFT);
        libraryPathTableColumn.setText(UMLMessage.LABEL_LIBRARY_PATH);

        tableColumnLayout.setColumnData(libraryPathTableColumn, new ColumnWeightData(6));

        libraryTableViewer.setContentProvider(new ArrayContentProvider());
        libraryTableViewer.setLabelProvider(new LibraryTableViewerLabelProvider());

        libraryTableViewer.setInput(applyingLibraryList.toArray());
        libraryTableViewer.refresh();

        table.setHeaderVisible(true);
        table.setLinesVisible(false);

        table.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                deleteLibraryButton.setEnabled(true);
            }
        });
    }

    /**
     * 버튼 컨트롤 생성
     * 
     * @param composite
     *            void
     */
    private void createButtonControl(Composite composite) {
        Composite buttonComposite = new Composite(composite, SWT.FILL);
        GridLayout buttonGridLayout = new GridLayout();
        buttonComposite.setLayout(buttonGridLayout);
        buttonComposite.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
        
        createAddLibraryButton(buttonComposite);
        createDeleteLibraryButton(buttonComposite);
    }

    /**
     * 모델 라이브러리 추가버튼 컨트롤 생성
     * 
     * @param buttonComposite
     *            void
     */
    private void createAddLibraryButton(final Composite buttonComposite) {
        addLibraryButton = new Button(buttonComposite, SWT.PUSH);
        addLibraryButton.setText(UMLMessage.LABEL_ADD_UMLPROFILE);
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
        gridData.widthHint = 60;
        addLibraryButton.setLayoutData(gridData);
        
        addLibraryButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {

                LibraryChooseDialog libraryChooseDialog = new LibraryChooseDialog(getShell(),
                    UMLMessage.LABEL_LIBRARY_APPLICATION,
                    model, applyingLibraryList);

//                if (libraryChooseDialog.isExistApplicableLibrary()) {
                    openProfileApplicationDialog(libraryChooseDialog);
//                }
            }
        });
    }
    
    /**
     * 프로파일 적용하는 다이얼로그 여는 메소드
     * 
     * void
     */
    protected void openProfileApplicationDialog(LibraryChooseDialog libraryChooseDialog) {

        if (libraryChooseDialog.open() == Window.OK) {
            Package library = libraryChooseDialog.getSelectedObject();

            if (!applyingLibraryList.contains(library)) {
                if(null != library){
                    applyingLibraryList.add(library);

                    libraryTableViewer.setInput(applyingLibraryList.toArray());
                    libraryTableViewer.refresh();
                    
                    apply();
                    
                    callerSection.isDirty();
                }
            }

        }
    }

    /**
     * 라이브러리 적용하는 다이얼로그 여는 메소드 void
     */
    protected void openLibraryApplicationDialog() {
        ListDialog libraryApplicationDialog = new ListDialog(getShell());

        libraryApplicationDialog.setAddCancelButton(true);
        libraryApplicationDialog.setContentProvider(new ArrayContentProvider());
        libraryApplicationDialog.setLabelProvider(new LabelProvider() {
            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                if (element instanceof Package) {
                    return ((Package) element).getName();
                }

                return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            }
        });

        initializeApplicableLibraryList();

        libraryApplicationDialog.setInput(applicableLibraryList.toArray());
        libraryApplicationDialog.setInitialSelections(applicableLibraryList.toArray());
        libraryApplicationDialog.setTitle(UMLMessage.LABEL_LIBRARY_APPLICATION);

        if (libraryApplicationDialog.open() == Window.OK) {
            Object[] libraries = libraryApplicationDialog.getResult();

            Package newLibrary = (Package) libraries[0];

            applyingLibraryList.add(newLibrary);

            // RecordingCommand command = new
            // HandleImportedPackageCommand(DomainRegistry.getEditingDomain(),
            // model, newLibrary, true);
            // DomainUtil.executeCommand(command);

            libraryTableViewer.setInput(applyingLibraryList.toArray());
            libraryTableViewer.refresh();

            apply();
            
            callerSection.isDirty();
        }
    }
    
    /**
     * apply
     *   void
     */
    private void apply() {
        CompoundCommand commands = new CompoundCommand();
        RecordingCommand command = null;

        for (org.eclipse.uml2.uml.Package importedPackage : model.getImportedPackages()) {
            command = new HandleImportedPackageCommand(DomainRegistry.getEditingDomain(), model, importedPackage, false);

            commands.append(command);
        }

        for (org.eclipse.uml2.uml.Package importingPackage : getApplyingLibraryList()) {
            command = new HandleImportedPackageCommand(DomainRegistry.getEditingDomain(), model, importingPackage, true);

            commands.append(command);
        }

        DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);
    }

    /**
     * 모델 라이브러리 삭제버튼 컨트롤 생성
     * 
     * @param buttonComposite
     *            void
     */
    private void createDeleteLibraryButton(Composite buttonComposite) {
        deleteLibraryButton = new Button(buttonComposite, SWT.PUSH);
        deleteLibraryButton.setText(UMLMessage.LABEL_REMOVE);
        deleteLibraryButton.setEnabled(false);
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
        gridData.widthHint = 60;
        deleteLibraryButton.setLayoutData(gridData);
        
        deleteLibraryButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (event != null) {
                    for (Iterator<Package> iterator = ((IStructuredSelection) libraryTableViewer.getSelection()).iterator(); iterator.hasNext();) {
                        Package selectedImportedPackage = (Package) iterator.next();

                        applyingLibraryList.remove(selectedImportedPackage);

                        // RecordingCommand command = new
                        // HandleImportedPackageCommand(DomainRegistry.getEditingDomain(),
                        // model, selectedImportedPackage, false);
                        // DomainUtil.executeCommand(command);

                        libraryTableViewer.setInput(applyingLibraryList.toArray());
                        libraryTableViewer.refresh();

                    }
                    apply();
                    
                    callerSection.isDirty();
                }
            }
        });
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(Model model) {
        this.model = model;

        if (model.getImportedPackages().size() > 0) {
            // this.modelLibraryList = model.getImportedPackages();

            this.applyingLibraryList = new ArrayList<Package>();

            for (Package importedPackage : model.getImportedPackages()) {
                if (importedPackage != null) {
                    this.applyingLibraryList.add(importedPackage);
                }
            }
        } else {
            this.applyingLibraryList = new ArrayList<Package>();
        }
    }

    /**
     * 적용할 수 있는 라이브러리 목록 초기화 void
     */
    private void initializeApplicableLibraryList() {
        Resource resource = null;
        URI libraryURI = null;
        Package library = null;

        if (applicableLibraryList == null) {
            applicableLibraryList = new ArrayList<Package>();
        } else if (!applicableLibraryList.isEmpty()) {
            applicableLibraryList.clear();
        }

        for (String libraryName : UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES) {
            // UML 기본 라이브러리 로딩
            libraryURI = URI.createURI(UMLResource.LIBRARIES_PATHMAP + libraryName + ManagerConstant.DOT
                + UMLResource.LIBRARY_FILE_EXTENSION);
            resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(libraryURI, true);

            if (!resource.isLoaded()) {
                try {
                    resource.load(DomainUtil.getLoadOptions());
                } catch (IOException ioe) {
                    Log.error(ioe);
                }
            }

            library = DomainUtil.getUMLModelRoot(resource);

            applicableLibraryList.add(library);
        }

        for (Package importedPackage : model.getImportedPackages()) {
            applicableLibraryList.remove(importedPackage);
        }
    }

    /**
     * 적용할 모델 라이브러리 목록 반환
     * 
     * @return the applyingLibraryList
     */
    public List<Package> getApplyingLibraryList() {
        return applyingLibraryList;
    }

    /**
     * 모델 라이브러리 테이블 뷰어 반환
     * 
     * @return the libraryTableViewer
     */
    public TableViewer getLibraryTableViewer() {
        return libraryTableViewer;
    }

}
