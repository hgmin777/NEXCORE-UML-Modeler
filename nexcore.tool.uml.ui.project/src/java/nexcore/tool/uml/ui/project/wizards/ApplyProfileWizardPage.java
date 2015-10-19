/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.wizards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.ProfileChooseDialog;
import nexcore.tool.uml.ui.project.editor.provider.ProfileTableViewerLabelProvider;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설  명 : ApplyProfileWizardPage</li>
 * <li>작성일 : 2011. 10. 20.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ApplyProfileWizardPage extends WizardPage {

    


    /**
     * ApplyProfileWizardPage
     * @param pageName
     */
    protected ApplyProfileWizardPage(String pageName) {
        super(pageName);
        this.uri = uri;
        setTitle(UMLMessage.LABEL_APPLY_PROFILE);
        applyingProfileList = new ArrayList<Profile>();
    }
    
    /**
     * uri
     */
    private URI uri;
    /**
     * composite
     */
    private Composite composite;
    /**
     * profileTableViewer
     */
    private TableViewer profileTableViewer;
    /**
     * table
     */
    private Table table;
    /**
     * applyingProfileList
     */
    private List<Profile> applyingProfileList;
    /**
     * addProfileButton
     */
    private Button addProfileButton;
    /**
     * deleteProfileButton
     */
    private Button deleteProfileButton;
    /**
     * profileDialog
     */
    private FileDialog profileDialog;
    /**
     * newProfile
     */
    private Profile newProfile;
    /**
     * lastProfilePath
     */
    private String lastProfilePath;
    
    
    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        composite.setLayout(layout);
        GridData data = new GridData();
        composite.setLayoutData(data);
        
        createTable();
        createButton();
    }



    /**
     * createTable
     *   void
     */
    private void createTable() {
        Composite tablePartComposite = new Composite(composite, SWT.FILL);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.heightHint = 240;

        tablePartComposite.setLayoutData(gridData);

        TableColumnLayout tableColumnLayout = new TableColumnLayout();
        tablePartComposite.setLayout(tableColumnLayout);

        profileTableViewer = new TableViewer(tablePartComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL
            | SWT.BORDER);

        table = profileTableViewer.getTable();

        TableColumn profileNameTableColumn = new TableColumn(table, SWT.LEFT);
        profileNameTableColumn.setText(UMLMessage.LABEL_UMLPROFILE_NAME);

        tableColumnLayout.setColumnData(profileNameTableColumn, new ColumnWeightData(4));

        TableColumn profilePathTableColumn = new TableColumn(table, SWT.LEFT);
        profilePathTableColumn.setText(UMLMessage.LABEL_UMLPROFILE_PATH);

        tableColumnLayout.setColumnData(profilePathTableColumn, new ColumnWeightData(6));

        profileTableViewer.setContentProvider(new ArrayContentProvider());
        profileTableViewer.setLabelProvider(new ProfileTableViewerLabelProvider());

//        profileTableViewer.setInput(applyingProfileList.toArray());
        profileTableViewer.refresh();

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
                deleteProfileButton.setEnabled(true);
            }
        });
    }
    
    /**
     * 버튼 컨트롤 생성
     * 
     * @param composite
     *            void
     */
    private void createButton() {
        
        Composite buttonComposite = new Composite(composite, SWT.NONE);
        GridLayout buttonGridLayout = new GridLayout(2, false);
        buttonComposite.setLayout(buttonGridLayout);

        createAddProfileButton(buttonComposite);
        createDeleteProfileButton(buttonComposite);
    }
    
    /**
     * 프로파일 추가버튼 컨트롤 생성
     * 
     * @param buttonComposite
     *            void
     */
    private void createAddProfileButton(Composite buttonComposite) {
        addProfileButton = new Button(buttonComposite, SWT.PUSH);
        addProfileButton.setText(UMLMessage.LABEL_ADD_UMLPROFILE);
        GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, true);
        gridData.widthHint = 60;
        addProfileButton.setLayoutData(gridData);
        
        addProfileButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                
                ProfileChooseDialog profileChooseDialog = new ProfileChooseDialog(getShell(),
                    UMLMessage.LABEL_UML_PROFILE_APPLICATION,
                    uri, applyingProfileList);
                
                if (profileChooseDialog.isExistApplicableProfiles()) { // 조회할
                    // 프로파일
                    // 목록이
                    // 존재하면
                    openProfileApplicationDialog(profileChooseDialog);
                } else {
                    openProfileDialog();
                }
            }
        });
    }
    
    /**
     * 프로파일 적용하는 다이얼로그 여는 메소드
     * 
     * void
     */
    protected void openProfileApplicationDialog(ProfileChooseDialog profileChooseDialog) {

        if (profileChooseDialog.open() == Window.OK) {
            newProfile = profileChooseDialog.getSelectedObject();

            if (!applyingProfileList.contains(newProfile)) {
                if(null != newProfile){
                    applyingProfileList.add(newProfile);
                    
                    profileTableViewer.setInput(applyingProfileList.toArray());
                    profileTableViewer.refresh();
                }
            }
        }
    }

    /**
     * 프로파일 적용하는 다이얼로그 열기
     * 
     * void
     */
    protected void openProfileDialog() {
        profileDialog = new FileDialog(getShell(), SWT.MULTI);
        profileDialog.setFilterPath(lastProfilePath);
        profileDialog.setFilterExtensions(UICoreConstant.PROJECT_CONSTANTS__UML_PROFILE_FILE_EXTENSIONS);

        Resource profileResource = null;
        // Profile newProfile = null;
        // RecordingCommand command = null;

        if (profileDialog.open() != null) {
            String[] filenames = profileDialog.getFileNames();
            String filePath = profileDialog.getFilterPath();
            StringBuffer uri = new StringBuffer();

            for (int i = 0; i < filenames.length; i++) {
                if (applyingProfileList != null) {
                    for (Profile profile : applyingProfileList) {
                        if (!filenames[i].equals(profile.getName())) {
                            if (uri.length() > 0) {
                                uri.delete(0, uri.length());
                            }
                            uri.append(filePath).append(UICoreConstant.PROJECT_CONSTANTS__SLASH).append(filenames[i]);

                            profileResource = DomainRegistry.getUMLDomain()
                                .getResourceSet()
                                .getResource(URI.createFileURI(uri.toString()), true);
                            newProfile = (org.eclipse.uml2.uml.Profile) EcoreUtil.getObjectByType(profileResource.getContents(),
                                UMLPackage.Literals.PROFILE);

                            applyingProfileList.add(newProfile);

                            // command = new
                            // HandleProfileCommand(DomainRegistry.getEditingDomain(),
                            // model, newProfile, true);
                            // DomainUtil.executeCommand(command);
                        }
                    }
                } else {
                    if (uri.length() > 0) {
                        uri.delete(0, uri.length());
                    }
                    uri.append(filePath).append(UICoreConstant.PROJECT_CONSTANTS__SLASH).append(filenames[i]);

                    profileResource = DomainRegistry.getUMLDomain()
                        .getResourceSet()
                        .getResource(URI.createFileURI(uri.toString()), true);
                    newProfile = (org.eclipse.uml2.uml.Profile) EcoreUtil.getObjectByType(profileResource.getContents(),
                        UMLPackage.Literals.PROFILE);

                    applyingProfileList.add(newProfile);

                    // command = new
                    // HandleProfileCommand(DomainRegistry.getEditingDomain(),
                    // model, newProfile, true);
                    // DomainUtil.executeCommand(command);
                }
            }

            profileTableViewer.setInput(applyingProfileList.toArray());
            profileTableViewer.refresh();
        }
    }
    
    /**
     * 프로파일 삭제버튼 컨트롤 생성
     * 
     * @param buttonComposite
     *            void
     */
    private void createDeleteProfileButton(Composite buttonComposite) {
        deleteProfileButton = new Button(buttonComposite, SWT.PUSH);
        deleteProfileButton.setText(UMLMessage.LABEL_REMOVE);
        deleteProfileButton.setEnabled(false);
        GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, true);
        gridData.widthHint = 60;
        deleteProfileButton.setLayoutData(gridData);
        
        deleteProfileButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent event) {
                if (event != null) {

                    for (Iterator<Profile> iterator = ((IStructuredSelection) profileTableViewer.getSelection()).iterator(); iterator.hasNext();) {
                        Profile selectedProfile = (Profile) iterator.next();

                        applyingProfileList.remove(selectedProfile);
                    }
                    profileTableViewer.setInput(applyingProfileList.toArray());
                    profileTableViewer.refresh();
                }
            }
        });
    }
    
    /**
     * getProfileList
     *  
     * @return List<Profile>
     */
    public List<Profile> getProfileList() {
        return applyingProfileList;
    }
    
    /**
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        getControl().setVisible(visible);
    }
    
    /**
     * @see org.eclipse.jface.dialogs.DialogPage#getControl()
     */
    @Override
    public Control getControl() {
        return composite;
    }



    /**
     * setUri
     *  
     * @param uri void
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

}
