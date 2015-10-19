/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.action;

import java.io.File;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.ProjectItemSelectionDialog;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;
import nexcore.tool.uml.ui.project.report.job.XMLReportJob;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.action</li>
 * <li>설 명 : CommonReportAction</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 송정훈</li>
 * </ul>
 */
public abstract class CommonReportAction extends CommonActionDelegate {

    /**
     * templateFile
     */
    public IFile templateFile;
    
    /**
     * fileLocation
     */
    public String fileLocation;
    
    /** 프로젝트 이름 */
    private String projectName;

    
    /**
     * 
     *  
     * @param dialog
     * @return String
     */
    protected String getFileLocation(ProjectItemSelectionDialog dialog) {
        String classDefinitionFileURI = dialog.getFileNameToExportTo();
        classDefinitionFileURI = classDefinitionFileURI.replace('\\', '/');
        classDefinitionFileURI = classDefinitionFileURI.replace("//", UICoreConstant.PROJECT_CONSTANTS__SLASH); //$NON-NLS-1$
        templateFile = dialog.getTemplateFile();
        fileLocation = classDefinitionFileURI;
        return classDefinitionFileURI;
    }
    
    /**
     * 
     *   void
     */
    protected void createCompleteMessageDialog() {
        boolean result = MessageDialog.openConfirm(getShell(), 
            UMLMessage.MESSAGE_COMPLETE_DOCUMENT_CREATION,//"문서산출물 생성완료", 
            fileLocation + 
            UMLMessage.MESSAGE_DOCUMENT_HAS_BEEN_CREATED);//" 문서가 생성되었습니다. 생성된 문서를 보시겠습니까?");
        
        if( result ) {
            File file = new File(fileLocation);
            if( file.exists() ) {
                Program program = Program.findProgram(file.getName().substring(file.getName().lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__DOT)));
                if (null != program) {
                    String string = file.getAbsolutePath().toString();
                    program.execute(string);
                }
            }
        }
    }
    
    /**
     * 
     *  
     * @return Shell
     */
    private Shell getShell() {
        Shell shell = null;
        IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();
        for (IWorkbenchWindow workbenchWindow : workbenchWindows) {
            if (workbenchWindow.getShell() != null) {
                shell = workbenchWindow.getShell();
                break;
            }
        }
        return shell;
    }
    
    /**
     * 파일 생성 성공 메시지
     */
    protected void openSuccessDialog() {
        openSuccessDialog(UMLMessage.LABEL_SUCCESS, UMLMessage.MESSAGE_SUCCESS_EXPORT);
    }

    /**
     * 파일 생성 성공 메시지 다이얼로그
     * 
     * @param title
     * @param message
     */
    protected void openSuccessDialog(String title, String message) {
        MessageDialog.openInformation(ProjectExplorerPlugin.getShell(), title, message);
    }

    /**
     * 파일 생성 에러 메시지
     */
    protected void openErrorDialog() {
        openErrorDialog(UMLMessage.LABEL_ERROR, UMLMessage.MESSAGE_ERROR_EXPORT);
    }

    /**
     * 파일 생성 에러 메시지 다이얼로그
     * 
     * @param title
     * @param message
     */
    protected void openErrorDialog(String title, String message) {
        MessageDialog.openError(ProjectExplorerPlugin.getShell(), title, message);
    }

    /**
     * 파일 오버라이트 다이얼로그
     * 
     * @return boolean
     */
    protected boolean overwriteFileDialog() {
        return overwriteFileDialog(UMLMessage.LABEL_CONFIRM, UMLMessage.MESSAGE_CONFIRM_OVERWRITE_FILE);
    }

    /**
     * 파일 오버라이트 다이얼로그
     * 
     * @param title
     * @param message
     * @return boolean
     */
    protected boolean overwriteFileDialog(String title, String message) {
        return MessageDialog.openConfirm(ProjectExplorerPlugin.getShell(), title, message);
    }

    /**
     * 동일 파일 존재 메시지
     */
    protected void exsitFileDialog() {
        exsitFileDialog(UMLMessage.LABEL_ERROR, UMLMessage.MESSAGE_ERROR_EXIST_FILE);
    }

    /**
     * 동일파일 존재 메시지 다이얼 로그
     * 
     * @param title
     * @param message
     */
    protected void exsitFileDialog(String title, String message) {
        MessageDialog.openError(ProjectExplorerPlugin.getShell(), title, message);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        super.selectionChanged(action, selection);

        if (!(selection instanceof IStructuredSelection))
            return;

        Object object = ((IStructuredSelection) selection).getFirstElement();
        if (object instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) object;
            projectName = getProjectName(node);
        }
    }

    /**
     * getProjectName
     *  
     * @param treeNode
     * @return String
     */
    private String getProjectName(ITreeNode treeNode) {
        try {
            if (treeNode instanceof UMLFileTreeNode) {
                UMLFileTreeNode node = (UMLFileTreeNode) treeNode;
                return node.getResource().getProject().getName();
            } else if (treeNode instanceof UMLTreeNode) {
                UMLTreeNode node = (UMLTreeNode) treeNode;
                if (node.getParentNode() != null && node.getParentNode() instanceof ITreeNode) {
                    return getProjectName((ITreeNode) node.getParentNode());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return null;

    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        if (projectName == null)
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        return projectName;
    }

    /**
     * getSelectedElement
     *  
     * @return List<Element>
     */
    protected List<Element> getSelectedElement() {
        if (!(selectedEObject instanceof Package)) {
            return null;
        }

        Package rootElement = (Package) selectedEObject;
        EObject rootModel = EcoreUtil.getRootContainer(selectedEObject);

        if (null == rootModel) {
            return null;
        }
        ProjectItemSelectionDialog dialog = new ProjectItemSelectionDialog(ProjectExplorerPlugin.getShell(),
            getTitle(),
            rootElement,
            getFilter(),
            getDocType());

        // 최소경우처리
        if (Window.OK != dialog.open()) {
            return null;
        }

        // 선택항목 가져오기
        return dialog.getSelectedElementList();
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("static-access")
    @Override
    public void run(IAction action) {
        if (!(selectedEObject instanceof Package)) {
            return;
        }

        Package rootElement = (Package) selectedEObject;
        EObject rootModel = EcoreUtil.getRootContainer(selectedEObject);

        if (null == rootModel) {
            return;
        }
        ProjectItemSelectionDialog dialog = new ProjectItemSelectionDialog(ProjectExplorerPlugin.getShell(),
            getTitle(),
            rootElement,
            getFilter(),
            getDocType());

        // 최소경우처리
        if (dialog.OK != dialog.open()) {
            return;
        }

        // 선택항목 가져오기
        List<Element> selectedElementList = dialog.getSelectedElementList();

        if (null == selectedElementList) {
            return;
        }
        // 선택된 항목 없을 경우 작업취소
        if (0 == selectedElementList.size()) {
            return;
        }

        String useCaseDefinitionFileURI = UICoreConstant.EMPTY_STRING;
        // useCaseDefinitionFileURI = dialog.getFilterPath() +
        // UICoreConstant.PROJECT_CONSTANTS__SLASH
        // + dialog.getFileNameToExportTo();
        useCaseDefinitionFileURI = getFileLocation(dialog);

        XMLReportJob xmlReportJob = new XMLReportJob();
        ReportHandler reportHandler = this.getReportHandler(useCaseDefinitionFileURI,
            templateFile,
            selectedElementList,
            this.getProjectName());

        xmlReportJob.setReportHandler(reportHandler);
        addJobChangeListener(xmlReportJob);
        try {

            xmlReportJob.setUser(true);
            xmlReportJob.setPriority(Job.SHORT);
            xmlReportJob.schedule();

        } catch (Exception e1) {
            this.openErrorDialog();
            Log.error(e1);
            // e1.printStackTrace();

        } finally {
            if (xmlReportJob != null) {
                xmlReportJob = null;
            }
        }
    }

    /**
     * addJobChangeListener
     *  
     * @param xmlReportJob void
     */
    protected void addJobChangeListener(Job xmlReportJob) {
        xmlReportJob.addJobChangeListener(new DocumentJobListener());
    }
    
    public class DocumentJobListener implements IJobChangeListener {
        public void aboutToRun(IJobChangeEvent event) {
        }
        public void awake(IJobChangeEvent event) {
        }
        public void done(IJobChangeEvent event) {
            
            if (Status.OK_STATUS.equals(event.getResult())) {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        createCompleteMessageDialog();
                    }
                });
                
            } else {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        MessageDialog.openWarning(getShell(), 
                            UMLMessage.MESSAGE_DIALOG_TITLE, 
                            UMLMessage.MESSAGE_FAIL_DOCUMENT_CREATION);
                    }
                });
                
            }
        }
        public void running(IJobChangeEvent event) {
        }
        public void scheduled(IJobChangeEvent event) {
        }
        public void sleeping(IJobChangeEvent event) {
        }
    }
    
    /**
     * 
     * Action별로 재정의되는 필터
     * 
     * @return EClass
     */
    protected EClass getFilter() {
        return UMLPackage.Literals.PACKAGE;
    }

    /**
     * Action별로 재정의되는 문서 타입
     * 
     * @return String
     */
    protected String getDocType() {
        return UICoreConstant.REPORT__DOCTYPE_DEFINITION;
    }

    /**
     * Action별로 재정의되는 선택창 타이틀
     * 
     * @return String
     */
    protected String getTitle() {
        return UMLMessage.LABEL_EXPORT_USECASE_DEFINITION_TO_DOC;
    }

    /**
     * getReportHandler
     *  
     * @param reportLocation
     * @param templateFile
     * @param selectedPackage
     * @param projectName
     * @return ReportHandler
     */
    protected ReportHandler getReportHandler(String reportLocation, IFile templateFile, List<Element> selectedPackage,
                                             String projectName) {
        return null;
    }
}
