/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.dialog;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemLabelProvider;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemTreeContentProvider;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설  명 : SelectModelDialog</li>
 * <li>작성일 : 2010. 6. 15.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class SelectModelDialog extends Dialog {

    /** 선택된 분석 모델 */
    private Model selectedAnalysisModel = null;

    /** 선택된 설계 모델 */
    private Model selectedDesignModel = null;

    /** 분석 모델 텍스트 */
    private Text analysisText;

    /** 설계 모델 텍스트 */
    private Text designText;

    /**
     * 생성자
     * 
     * @param parentShell
     */
    public SelectModelDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        getShell().setText(UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION);

        Composite childComposite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, false);
        childComposite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        childComposite.setLayoutData(gridData);

        Group analysisGroup = new Group(childComposite, SWT.NONE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        analysisGroup.setLayoutData(gridData);
        gridLayout = new GridLayout(3, false);
        analysisGroup.setLayout(gridLayout);

        Label analysisLabel = new Label(analysisGroup, SWT.NONE);
        analysisLabel.setText(UMLMessage.MESSAGE_SELECT_ANALYSISMODEL);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        analysisLabel.setLayoutData(gridData);
        analysisText = new Text(analysisGroup, SWT.READ_ONLY);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
        analysisText.setLayoutData(gridData);
        analysisText.setBackground(org.eclipse.draw2d.ColorConstants.white);
        analysisText.setEditable(false);
        analysisText.setEnabled(false);
        Button analysisButton = new Button(analysisGroup, SWT.NONE);
        gridData = new GridData();
        analysisButton.setLayoutData(gridData);
        analysisButton.setText(UMLMessage.MESSAGE_SEARCH);
        analysisButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                openElementTreeSelectionDialog(true);
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        Group designGroup = new Group(childComposite, SWT.NONE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        designGroup.setLayoutData(gridData);
        gridLayout = new GridLayout(3, false);
        designGroup.setLayout(gridLayout);

        Label designLabel = new Label(designGroup, SWT.NONE);
        designLabel.setText(UMLMessage.MESSAGE_SELECT_DESIGNMODEL);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        designLabel.setLayoutData(gridData);
        designText = new Text(designGroup, SWT.READ_ONLY);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
        designText.setLayoutData(gridData);
        designText.setBackground(org.eclipse.draw2d.ColorConstants.white);
        designText.setEditable(false);
        designText.setEnabled(false);
        Button designButton = new Button(designGroup, SWT.NONE);
        gridData = new GridData();
        designButton.setLayoutData(gridData);
        designButton.setText(UMLMessage.MESSAGE_SEARCH);
        designButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                openElementTreeSelectionDialog(false);
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        return super.createDialogArea(parent);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#initializeBounds()
     */
    @Override
    protected void initializeBounds() {
        getShell().setSize(400, 300);
        super.initializeBounds();
    }

    /**
     * 트리에서 요소 선택하는 다이얼로그 열기
     * 
     * @param isSelectAnalysisModel
     *            void
     */
    private void openElementTreeSelectionDialog(boolean isSelectAnalysisModel) {
        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
            new ProjectItemLabelProvider(),
            new ProjectItemTreeContentProvider(1));

        dialog.setTitle(UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION);
        if (isSelectAnalysisModel) {
            dialog.setMessage(UMLMessage.MESSAGE_SELECT_ANALYSIS_MODEL_FOR_CREATE_USECASE_TRACE_MATRIX);
        } else {
            dialog.setMessage(UMLMessage.MESSAGE_SELECT_DESIGN_MODEL_FOR_CREATE_USECASE_TRACE_MATRIX);
        }
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        dialog.addFilter(new ViewerFilter() {
            /**
             * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IProject) {
                    return true;
                } else if (parentElement instanceof IFolder && element instanceof Model) {
                    return true;
                } else if (element instanceof IResource) {
                    if (((IResource) element).getName().startsWith(UICoreConstant.PROJECT_CONSTANTS__DOT)) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        });

        if (dialog.open() != Window.OK) {
            return;
        }

        if (!(dialog.getFirstResult() instanceof Model)) {
            return;
        }

        if (isSelectAnalysisModel) {
            if (getSelectedDesignModel() != null) {
                if (getSelectedDesignModel().equals(dialog.getFirstResult())) {
                    MessageDialog.openWarning(getShell(),
                        UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION,
                        UMLMessage.MESSAGE_WARNING_DO_NOT_SELECT_SAME_MODEL);
                    return;
                }
            }
        } else {
            if (getSelectedAnalysisModel() != null) {
                if (getSelectedAnalysisModel().equals(dialog.getFirstResult())) {
                    MessageDialog.openWarning(getShell(),
                        UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION,
                        UMLMessage.MESSAGE_WARNING_DO_NOT_SELECT_SAME_MODEL);
                    return;
                }
            }
        }

        if (isSelectAnalysisModel) {
            setSelectedAnalysisModel((Model) dialog.getFirstResult());
            analysisText.setText(getSelectedAnalysisModel().getName());
        } else {
            setSelectedDesignModel((Model) dialog.getFirstResult());
            designText.setText(getSelectedDesignModel().getQualifiedName());
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        if (getSelectedAnalysisModel() == null) {
            MessageDialog.openInformation(getShell(),
                UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION,
                UMLMessage.MESSAGE_DID_NOT_SELECT_ANALYSISMODEL);
        } else if (getSelectedDesignModel() == null) {
            MessageDialog.openWarning(getShell(),
                UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION,
                UMLMessage.MESSAGE_DID_NOT_SELECT_DESIGNMODEL);
        } else {
            super.okPressed();
        }
    }

    /**
     * 분석 모델 반환
     * 
     * @return Model
     */
    public Model getSelectedAnalysisModel() {
        return selectedAnalysisModel;
    }

    /**
     * 분석 모델 설정
     * 
     * @param selectedAnalysisModel
     *            void
     */
    public void setSelectedAnalysisModel(Model selectedAnalysisModel) {
        this.selectedAnalysisModel = selectedAnalysisModel;
    }

    /**
     * 설계 모델 반환
     * 
     * @return Model
     */
    public Model getSelectedDesignModel() {
        return selectedDesignModel;
    }

    /**
     * 설계 모델 설정
     * 
     * @param selectedDesignModel
     *            void
     */
    public void setSelectedDesignModel(Model selectedDesignModel) {
        this.selectedDesignModel = selectedDesignModel;
    }

}
