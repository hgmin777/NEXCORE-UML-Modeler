/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemLabelProvider;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemTreeContentProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설 명 : ProjectItemSelectionDialog</li>
 * <li>작성일 : 2010. 3. 17.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class ProjectItemSelectionDialog extends Dialog {

    /**
     * TEMPLATE_FILE_ABSOLUTE_PATH
     */
    private static final String TEMPLATE_FILE_ABSOLUTE_PATH = "templateFileAbsolutePath";

    /**
     * PACKAGE_LIST_FILTER_PATH
     */
    private static final String PACKAGE_LIST_FILTER_PATH = "packageListFilterPath";

    /**
     * TEMPLATE_FILE_PATH
     */
    private static final String TEMPLATE_FILE_PATH = "templateFilePath";

    /** 다이얼로그 제목 */
    private String dialogTitle;

    /** 최상위 요소 */
    private Element rootElement;

    /** 필터링 타입 */
    private EClass filterType;

    /** 생성할 doc 타입 */
    private String docType;

    /** 선택한 요소 목록 */
    private List<Element> selectedElementList;

    /** 가져오기 파일 경로 */
    private String packageListFilterPath;

    /** 가져오기 파일 이름 */
    private String packageListFileName;

    /** 레이아웃 */
    private GridLayout gridLayout;

    /** 그리드 데이터 */
    private GridData gridData;

    /** 트리 뷰어 컴포짓 */
    private Composite treeViewerComposite;

    /** 트리 뷰어 라벨 */
    private Label tableViewerLabel;

    /** 트리 뷰어 */
    private CheckboxTreeViewer treeViewer;

    /** 파일명 컴포짓 */
    private Composite fileNameComposite;

    /** 파일명 라벨 */
    private Label fileNameLabel;

    /** 파일명 텍스트 */
    private Text fileNameText;

    /** 파일 경로 찾기 버튼 */
    private Button findPathButton;

    /** 실행한 액션에 따라 테이블 뷰어의 라벨 텍스트를 변경하기 위한 변수 */
    private String tableViewerLabelText;

    /** 실행한 액션에 따라 사용할 템플릿의 텍스트를 변경하기 위한 변수 */
    private String templateText;

    /** 파일 다이얼로그의 기본 파일명 */
    private String fileDialogDefaultFileName;

    /** 파일 다이얼로그의 기본 확장자 */
    private String fileDialogFileExtension;

    /** 사용할 템플릿명 라벨 */
    private Label templateNameLabel;

    /** 사용할 템플릿명 텍스트 */
    private Text templateNameText;

    /** 사용할 템플릿 경로 버튼 */
    private Button templatePathButton;

    /** 템플릿 파일 이름 */
    private IFile templateFile;

    /** actionTitle */
    private String actionTitle;

    /** settings */
    private IDialogSettings settings = null;

    // /** newSettings */
    // private DialogSettings newSettings;

    /**
     * 생성자
     * 
     * @param parentShell
     * @param dialogTitle
     */
    protected ProjectItemSelectionDialog(Shell parentShell, String dialogTitle) {
        super(parentShell);

        this.dialogTitle = dialogTitle;
        this.selectedElementList = new ArrayList<Element>();
    }

    /**
     * 생성자
     * 
     * @param parentShell
     * @param title
     * @param rootElement
     * @param filterType
     * @param docType
     * @param string
     */
    public ProjectItemSelectionDialog(Shell parentShell, String title, Element rootElement, EClass filterType,
    String docType) {
        this(parentShell, title);

        this.rootElement = rootElement;
        this.filterType = filterType;
        this.docType = docType;

        this.actionTitle = title;
        // newSettings = new DialogSettings(actionTitle);
        // 액션에 따른 분기
        if (UMLMessage.LABEL_EXPORT_CLASS_DEFINITION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_CLASS_DEFINITION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_CLASS_LIST_TO_XLS.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_CLASS_LIST_TO_XLS);
        } else if (UMLMessage.LABEL_EXPORT_PACKAGE_LIST_TO_XLS.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_PACKAGE_LIST_TO_XLS);
        } else if (UMLMessage.LABEL_EXPORT_RM_USECASE_TRACE_MATRIX_TO_XLS.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_RM_USECASE_TRACE_MATRIX_TO_XLS);
        } else if (UMLMessage.LABEL_EXPORT_USECASE_ANALYSIS_SPECIFICATION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_USECASE_ANALYSIS_SPECIFICATION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_USECASE_ARCHITECTURE_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_USECASE_ARCHITECTURE_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_USECASE_DEFINITION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_USECASE_DEFINITION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_USECASE_LIST_TO_XLS.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_USECASE_LIST_TO_XLS);
        } else if (UMLMessage.LABEL_EXPORT_COMPONENT_DEFINITION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_COMPONENT_DEFINITION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_INTERFACE_INTERACTION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_INTERFACE_INTERACTION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_DEFINITION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_DEFINITION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_UI_DEFINITION_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_UI_DEFINITION_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_UI_ARCHITECTURE_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_UI_ARCHITECTURE_TO_DOC);
        } else if (UMLMessage.LABEL_EXPORT_TRANSACTION_ARCHITECTURE_TO_DOC.equals(this.actionTitle)) {
            settings = getDialogSettings().getSection(UMLMessage.LABEL_EXPORT_TRANSACTION_ARCHITECTURE_TO_DOC);
        }

        if (null == settings) {
            settings = new DialogSettings(this.actionTitle);
            getDialogSettings().addSection(settings);
        }
    }

    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText(dialogTitle);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     */
    @Override
    protected Point getInitialSize() {
        return getShell().computeSize(600, 600, true);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) (super.createDialogArea(parent));

        init();
        createMainComposite(composite);

        return composite;
    }

    /**
     * 다이얼로그 초기화 void
     */
    private void init() {
        configurePreference();
    }

    /**
     * 실행한 액션의 인자에 따른 환경 변수 설정
     * 
     * void
     */
    private void configurePreference() {
        // 트리 뷰어의 라벨 텍스트와 파일 다이얼로그의 기본 파일명, 파일 확장자 설정
        if (filterType.equals(UMLPackage.Literals.PACKAGE)) {
            tableViewerLabelText = UMLMessage.getMessage(UMLMessage.LABEL_SELECT_TO_EXPORT_TO, UMLMessage.UML_PACKAGE);

            if (docType.equals(UICoreConstant.REPORT__DOCTYPE_LIST)) {
                fileDialogDefaultFileName = UICoreConstant.REPORT__PACKAGE_LIST_TO_XLS_DEFAULT_FILE_NAME;
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_LIST_FILE_EXTENSION;
                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;

            } else if (docType.equals(UICoreConstant.REPORT__DOCTYPE_DEFINITION)) {
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_DEFINITION_FILE_EXTENSION;
                if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_USECASE_ARCHITECTURE_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__USECASE_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_USECASE_DEFINITION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__USECASE_DEFINITION_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_USECASE_ANALYSIS_SPECIFICATION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__USECASE_ANALYSIS_SPECIFICATION_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_COMPONENT_DEFINITION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__COMPONENT_DEFINITION_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_INTERFACE_INTERACTION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__INTERFACE_INTERACTION_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_DEFINITION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__COMPONENT_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_UI_DEFINITION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__USER_INTERFACE_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_UI_ARCHITECTURE_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__UI_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_COMPONENT_ARCHITECTURE_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__COMPONENT_ARCHITECTURE1_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_TRANSACTION_ARCHITECTURE_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.REPORT__TRANSACTION_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME
                        + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT
                        + ((NamedElement) rootElement).getName()
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                } else if (dialogTitle.equals(UMLMessage.LABEL_EXPORT_CLASS_DEFINITION_TO_DOC)) {
                    fileDialogDefaultFileName = UICoreConstant.TEMPLATE_XML_CLASS_DEFINITION_XML;
                    fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_DEFINITION_FILE_EXTENSION;
                    templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
                }

            }
        } else if (filterType.equals(UMLPackage.Literals.CLASS)) {
            tableViewerLabelText = UMLMessage.getMessage(UMLMessage.LABEL_SELECT_TO_EXPORT_TO, UMLMessage.UML_CLASS);

            if (docType.equals(UICoreConstant.REPORT__DOCTYPE_LIST)) {
                fileDialogDefaultFileName = UICoreConstant.REPORT__CLASS_LIST_TO_XLS_DEFAULT_FILE_NAME;
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_LIST_FILE_EXTENSION;
                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
//            } else if (docType.equals(UICoreConstant.REPORT__DOCTYPE_DEFINITION)) {
//                fileDialogDefaultFileName = UICoreConstant.TEMPLATE_XML_CLASS_DEFINITION_XML;
//                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_DEFINITION_FILE_EXTENSION;
//                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
            }
        } else if (filterType.equals(UMLPackage.Literals.USE_CASE)) {
            tableViewerLabelText = UMLMessage.getMessage(UMLMessage.LABEL_SELECT_TO_EXPORT_TO, UMLMessage.UML_USECASE);

            if (docType.equals(UICoreConstant.REPORT__DOCTYPE_LIST)) {
                fileDialogDefaultFileName = UICoreConstant.REPORT__USECASE_LIST_TO_XLS_DEFAULT_FILE_NAME;
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_LIST_FILE_EXTENSION;
                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
            } else if (docType.equals(UICoreConstant.REPORT__DOCTYPE_DEFINITION)) {
                fileDialogDefaultFileName = UICoreConstant.REPORT__USECASE_DEFINITION_TO_XML_DEFAULT_FILE_NAME;
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_DEFINITION_FILE_EXTENSION;
                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
            } else if (docType.equals(UICoreConstant.REPORT__DOCTYPE_TRACE_MATRIX)) {
                fileDialogDefaultFileName = UICoreConstant.REPORT__RM_USECASE_TRACE_MATRIX_TO_XLS_DEFAULT_FILE_NAME;
                fileDialogFileExtension = UICoreConstant.REPORT__DOCTYPE_TRACE_MATRIX_FILE_EXTENSION;
                templateText = UMLMessage.LABEL_DEFAULT_TEMPLATE;
            }

        }

    }

    /**
     * 화면 구성
     * 
     * @param composite
     *            void
     */
    private void createMainComposite(Composite composite) {
        tableViewerLabel = new Label(composite, SWT.NONE);
        tableViewerLabel.setText(tableViewerLabelText + UICoreConstant.PROJECT_CONSTANTS__COLON);
        gridData = new GridData(GridData.BEGINNING);
        tableViewerLabel.setLayoutData(gridData);

        treeViewerComposite = new Composite(composite, SWT.FILL);
        gridLayout = new GridLayout(1, false);
        treeViewerComposite.setLayout(gridLayout);

        gridData = new GridData(GridData.FILL_BOTH);
        treeViewerComposite.setLayoutData(gridData);

        treeViewer = createTreeViewer(treeViewerComposite);
        gridData = new GridData(GridData.FILL_BOTH);
        treeViewer.getControl().setLayoutData(gridData);

        configureTreeViewer(treeViewer);

        fileNameComposite = new Composite(composite, SWT.NONE);
        gridLayout = new GridLayout(3, false);
        fileNameComposite.setLayout(gridLayout);

        templateNameLabel = new Label(fileNameComposite, SWT.NONE);
        templateNameLabel.setText(UMLMessage.LABEL_USE_TEMPLATE);
        gridData = new GridData(GridData.BEGINNING);
        // gridData.widthHint =
        // UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHARACTER;
        templateNameLabel.setLayoutData(gridData);

        templateNameText = new Text(fileNameComposite, SWT.BORDER | SWT.READ_ONLY);
        gridData = new GridData();
        gridData.widthHint = 400;
        templateNameText.setLayoutData(gridData);

        if (null != settings) {
            IDialogSettings dialogSetting = getDialogSettings().getSection(actionTitle);
            if (null != settings) {
                if (null != dialogSetting.get(TEMPLATE_FILE_PATH)
                    && null != dialogSetting.get(TEMPLATE_FILE_ABSOLUTE_PATH)) {
                    String templatePath = dialogSetting.get(TEMPLATE_FILE_PATH);
                    IFile tempFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(templatePath));
                    if (tempFile.exists()) {
                        templateNameText.setText(templatePath);
                        templateFile = tempFile;
                    }
                }
            }
        }
        if (UICoreConstant.EMPTY_STRING.equals(templateNameText.getText())) {
            templateNameText.setText(templateText);
        }

        templatePathButton = new Button(fileNameComposite, SWT.PUSH);
        templatePathButton.setText(UMLMessage.LABEL_FIND_PATH);
        gridData = new GridData();
        templatePathButton.setLayoutData(gridData);
        templatePathButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                UMLWorkspaceResourceDialog dialog = new UMLWorkspaceResourceDialog(getShell(),
                    new WorkbenchLabelProvider(),
                    new UmlWorkbenchContentProvider());

                dialog.setTitle(UMLMessage.LABEL_FIND_TEMPLATE);

                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

                dialog.setInput(root);
                dialog.setAllowMultiple(false);

                if (Window.OK == dialog.open()) {
                    Object obj = dialog.getFirstResult();
                    if (obj instanceof IFile) {
                        String templateFileURI = ((IFile) obj).getFullPath().toString();
                        String templateFileAbsolutePath = ((IFile) obj).getLocation().toOSString();
                        templateNameText.setText(templateFileURI);
                        templateFile = ((IFile) obj);

                        // templateInfo.xml
                        IDialogSettings dialogSetting = getDialogSettings().getSection(actionTitle);
                        dialogSetting.put(TEMPLATE_FILE_PATH, templateNameText.getText());
                        dialogSetting.put(TEMPLATE_FILE_ABSOLUTE_PATH, templateFileAbsolutePath);
                        getDialogSettings().addSection(dialogSetting);
                    }
                }
            }
        });

        fileNameLabel = new Label(fileNameComposite, SWT.NONE);
        fileNameLabel.setText(UMLMessage.LABEL_FILENAME_TO_EXPORT_TO);
        gridData = new GridData();

        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHARACTER;
        fileNameLabel.setLayoutData(gridData);

        fileNameText = new Text(fileNameComposite, SWT.BORDER | SWT.READ_ONLY);
        gridData = new GridData();
        gridData.widthHint = 400;
        fileNameText.setLayoutData(gridData);

        if (null != settings) {
            IDialogSettings dialogSetting = getDialogSettings().getSection(actionTitle);
            if (null != settings) {
                if (null != dialogSetting.get(PACKAGE_LIST_FILTER_PATH)) {
                    fileNameText.setText(dialogSetting.get(PACKAGE_LIST_FILTER_PATH));
                    packageListFileName = dialogSetting.get(PACKAGE_LIST_FILTER_PATH);
                }
            }
        }

        findPathButton = new Button(fileNameComposite, SWT.PUSH);
        findPathButton.setText(UMLMessage.LABEL_FIND_PATH);
        gridData = new GridData(GridData.END);
        findPathButton.setLayoutData(gridData);
        findPathButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                FileDialog fileDialog = new FileDialog(fileNameComposite.getShell(), SWT.SAVE);

                fileDialog.setText(dialogTitle);
                if (null != packageListFileName && !UICoreConstant.EMPTY_STRING.equals(packageListFileName)) {
                    fileDialog.setFileName(packageListFileName);
                } else {
                    fileDialog.setFileName(fileDialogDefaultFileName);
                }
                // fileDialog.setFileName(fileDialogDefaultFileName);
                fileDialog.setFilterExtensions(new String[] { fileDialogFileExtension });
                // fileDialog.setFilterPath("C:/");

                if (fileDialog.open() != null) {
                    packageListFilterPath = fileDialog.getFilterPath();
                    StringBuffer sBuffer = new StringBuffer(packageListFilterPath);
                    sBuffer.append("\\");
                    sBuffer.append(fileDialog.getFileName());

                    packageListFileName = sBuffer.toString();
                    fileNameText.setText(packageListFileName);

                    IDialogSettings dialogSetting = getDialogSettings().getSection(actionTitle);
                    dialogSetting.put(PACKAGE_LIST_FILTER_PATH, sBuffer.toString());
                    getDialogSettings().addSection(dialogSetting);
                }
            }
        });
    }

    /**
     * 트리 뷰어 생성
     * 
     * @param treeViewerComposite
     * @return TreeViewer
     */
    protected CheckboxTreeViewer createTreeViewer(Composite treeViewerComposite) {
        return new CheckboxTreeViewer(treeViewerComposite, SWT.SELECTED | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.BORDER);
    }

    /**
     * 트리 뷰어 설정
     * 
     * @param viewer
     *            void
     */
    protected void configureTreeViewer(CheckboxTreeViewer viewer) {
        viewer.setUseHashlookup(true);
        viewer.addCheckStateListener(new ICheckStateListener() {
            /**
             * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
             */
            public void checkStateChanged(CheckStateChangedEvent event) {
                treeViewer.setSubtreeChecked(event.getElement(), event.getChecked());
            }
        });

        viewer.setContentProvider(new ProjectItemTreeContentProvider(2));
        viewer.setLabelProvider(new ProjectItemLabelProvider());

        // 트리 뷰어의 경우 탐색기의 최상위 요소인 Model을 입력으로 설정하면
        // Model의 자식 요소부터 트리에 보여 주므로
        // 트리에 최상위 요소인 Model을 보여 주기 위해서는
        // Model을 자식으로 가지는 새로운 요소에다 Model을 설정해서
        // 그 요소를 트리의 입력으로 설정해 줘야 한다.
        ContainerElement container = new ContainerElement();
        container.addChild(rootElement);

        viewer.setInput(container);

        ViewerFilter viewerFilter = new ElementViewerFilter(filterType);
        viewer.addFilter(viewerFilter);
        viewer.setSorter(new NameSorter());
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        setSelectedElementList();
        // 트리에서 한 개 이상 체크 되었는지 확인하는 로직
        if (getSelectedElementList() != null && getSelectedElementList().size() > 0) {
            // 파일명이 입력되었는지 확인하는 로직
            String fileName = fileNameText.getText();

            if (fileName.length() <= 0) {
                MessageDialog.openError(this.getShell(),
                    UMLMessage.LABEL_ERROR,
                    UMLMessage.MESSAGE_ERROR_EMPTY_FILENAME);
            } else {
                super.okPressed();
            }
        } else {
            MessageDialog.openError(this.getShell(), UMLMessage.MESSAGE_DIALOG_TITLE, UMLMessage.MESSAGE_ERROR_CHECKED_ITEM);
        }
    }

    /**
     * 트리 뷰어에서 선택된 요소를 반환 객체에 설정 void
     */
    protected void setSelectedElementList() {
        Element element = null;

        selectedElementList.clear();

        for (Object object : treeViewer.getCheckedElements()) {
            if (object instanceof Element) {
                element = (Element) object;
                if (element.eClass() == filterType) {
                    selectedElementList.add((Element) object);
                }
            }
        }
    }

    /**
     * 선택한 요소 목록 반환
     * 
     * @return List<Element>
     */
    public List<Element> getSelectedElementList() {
        return this.selectedElementList;
    }

    /**
     * 내보낼 파일 경로 반환
     * 
     * @return String
     */
    public String getFilterPath() {
        return this.packageListFilterPath;
    }

    /**
     * 내보낼 파일명 반환
     * 
     * @return String
     */
    public String getFileNameToExportTo() {
        return this.packageListFileName;
    }

    /** 내보낼 템플릿 반환 */
    public IFile getDocumentTemplateToExportTo() {
        return this.templateFile;
    }

    /**
     * 템플릿 파일명 반환
     * 
     * @return the templateFileName
     */
    public IFile getTemplateFile() {
        return templateFile;
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.action</li>
     * <li>설 명 : ElementViewerFilter</li>
     * <li>작성일 : 2010. 3. 19.</li>
     * <li>작성자 : 오은주</li>
     * </ul>
     */
    class ElementViewerFilter extends ViewerFilter {

        /** 필터 타입 */
        private EClass filterType = null;

        /**
         * 생성자
         */
        public ElementViewerFilter(EClass filterType) {
            this.filterType = filterType;
        }

        /**
         * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            return checkChildren(parentElement, element);
        }

        /**
         * 자식 검사
         * 
         * @param parentElement
         * @param element
         * @return boolean
         */
        private boolean checkChildren(Object parentElement, Object element) {
            boolean bool = false;

            if (parentElement instanceof ContainerElement) {
                return true;
            }

            if (((Element) element).eClass().equals(filterType)) {
                return true;
            } else if (element instanceof org.eclipse.uml2.uml.Actor) {
                return true;
            }

            List<Element> elementChildren = ((Element) element).getOwnedElements();

            if (elementChildren.size() != 0) {
                for (Element elementChild : elementChildren) {
                    if (!(elementChild instanceof Element)) {
                        return false;
                    }
                    if (((Element) elementChild).eClass().equals(filterType)) {
                        return true;
                    } else if (elementChild instanceof org.eclipse.uml2.uml.Package
                        || elementChild instanceof org.eclipse.uml2.uml.Actor) {
                        bool = checkChildren(element, elementChild);

                        if (bool) {
                            return true;
                        }
                    }
                }
            }

            return bool;
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.action</li>
     * <li>설 명 : ContainerElement</li>
     * <li>작성일 : 2010. 4. 07.</li>
     * <li>작성자 : 오은주</li>
     * </ul>
     */
    public class ContainerElement {
        /** 자식이 담길 목록 변수 */
        List<Element> list;

        /**
         * 생성자
         */
        public ContainerElement() {
            if (list == null) {
                list = new ArrayList<Element>();
            }
        }

        /**
         * 자식 추가
         * 
         * @param child
         */
        public void addChild(Element child) {
            list.add(child);
        }

        /**
         * 자식 반환
         * 
         * @return
         */
        public List<Element> getChildren() {
            return list;
        }
    }

    public class UMLWorkspaceResourceDialog extends WorkspaceResourceDialog {

        public UMLWorkspaceResourceDialog(Shell parent, ILabelProvider labelProvider,
        ITreeContentProvider contentProvider) {
            super(parent, labelProvider, contentProvider);
        }

        @Override
        protected TreeViewer createTreeViewer(Composite parent) {
            TreeViewer treeViewer = super.createTreeViewer(parent);
            treeViewer.expandAll();
            return treeViewer;
        }
    }

    /**
     * 
     * 
     * @param settingName
     * @return String
     */
    protected String getDialogSetting(String settingName) {
        return getDialogSettings().get(settingName); //$NON-NLS-1$
    }

    /**
     * 
     * 
     * @return IDialogSettings
     */
    private IDialogSettings getDialogSettings() {
        IDialogSettings settings = UiCorePlugin.getDefault().getDialogSettings();
        return settings;
    }
}
