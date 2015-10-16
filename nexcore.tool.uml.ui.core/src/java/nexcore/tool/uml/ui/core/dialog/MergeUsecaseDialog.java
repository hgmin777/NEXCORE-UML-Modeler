/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.dialog;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설 명 : MergeUsecaseDialog</li>
 * <li>작성일 : 2011. 8. 16.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class MergeUsecaseDialog extends ElementTreeSelectionDialog {

    /** className */
    private Text usecaseName;

    /** name */
    private String name;

    /**
     * 생성자
     * 
     * @param parent
     * @param labelProvider
     * @param contentProvider
     */
    public MergeUsecaseDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
        super(parent, labelProvider, contentProvider);

        this.setAllowMultiple(false);

        intializeDialog();
    }

    /**
     * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        GridLayout layout = (GridLayout) composite.getLayout();
        layout.numColumns = 1;

        Label operationLabel = toolkit.createLabel(composite, UMLMessage.LABEL_USECASENAME);
        operationLabel.setBackground(ColorConstants.menuBackground);
        GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        operationLabel.setLayoutData(gd);

        usecaseName = toolkit.createText(composite, UMLMessage.LABEL_MERGED_USECASE_NAME);
        usecaseName.addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                updateStatus();
            }
        });

        gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        gd.widthHint = 200;
        usecaseName.setLayoutData(gd);

        return composite;
    }

    /**
     * 초기화
     * 
     * void
     */
    private void intializeDialog() {
        setTitle(UMLMessage.LABEL_USECASES_MERGER);
        setInput(ResourcesPlugin.getWorkspace().getRoot());

        addFilter(new ViewerFilter() {
            /**
             * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof org.eclipse.uml2.uml.Package || element instanceof org.eclipse.uml2.uml.UseCase
                    || element instanceof IProject) {
                    return true;
                } else if (parentElement instanceof IProject && element instanceof IFile) {
                    return false;
                } else if (element instanceof IResource) {
                    if (((IResource) element).getName().startsWith(ManagerConstant.DOT)) {
                        return false;
                    }

                    return true;
                }

                return false;
            }
        });
    }

    /**
     * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#updateOKStatus()
     */
    @SuppressWarnings("restriction")
    @Override
    protected void updateOKStatus() {

        Object result[] = this.getResult();

        if ((result != null) && (result.length != 0) && (result[0] instanceof UseCase)) {
            usecaseName.setText(((UseCase) result[0]).getName());
        }

        updateStatus();
    }

    /**
     * updateStatus
     *   void
     */
    protected void updateStatus() {
        Status status;

        if (validate()) {
            status = new Status(IStatus.OK, PlatformUI.PLUGIN_ID, IStatus.OK, UICoreConstant.EMPTY_STRING, null);
        } else {
            status = new Status(IStatus.ERROR,
                PlatformUI.PLUGIN_ID,
                IStatus.ERROR,
                WorkbenchMessages.ElementTreeSelectionDialog_nothing_available,
                null);
        }

        updateStatus(status);
    }

    /**
     * 검증
     * 
     * @return boolean
     */
    private boolean validate() {
        setUsecaseName();
        Object result[] = this.getResult();
        if (null == result || 0 == this.name.length() || 0 == result.length) {
            return false;
        }
        if (result[0] instanceof UseCase) {
            return true;
        }
        return false;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        setUsecaseName();
        super.okPressed();
    }

    /**
     * setUsecaseName
     *   void
     */
    private void setUsecaseName() {
        name = usecaseName.getText();
    }

    /**
     * getUsecaseName
     *  
     * @return String
     */
    public String getUsecaseName() {
        return name;
    }

}
