/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.composite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.core.util.SystemUtil;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.ProfileChooseDialog;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.project.editor.command.HandleProfileCommand;
import nexcore.tool.uml.ui.project.editor.provider.ProfileTableViewerLabelProvider;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.composite</li>
 * <li>설 명 : ProfileComposite</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.composite</li>
 * <li>설  명 : ProfileComposite</li>
 * <li>작성일 :  2009. 12. 15.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProfileComposite extends Composite {

    /** 컴포짓을 호출한 섹션 */
    private AbstractSection callerSection;

    /** 모델 인스턴스 */
    private Model model;

    /** 적용할 프로파일 목록 */
    private List<Profile> applyingProfileList;

    /** 프로파일 테이블 뷰어 */
    private TableViewer profileTableViewer;

    /** 프로파일 테이블 */
    private Table table;

    /** 프로파일 추가 버튼 */
    private Button addProfileButton;

    /** 프로파일 삭제 버튼 */
    private Button deleteProfileButton;

    /** 프로파일 다이얼로그 */
    private FileDialog profileDialog;

    /** 마지막으로 열린 프로파일의 경로 */
    private String lastProfilePath;

    /** 새로 적용된 프로파일 */
    private Profile newProfile;

    /**
     * 생성자
     * 
     * @param parent
     * @param model
     */
    public ProfileComposite(Composite parent, Model model) {
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
    public ProfileComposite(AbstractSection callerSection, Composite parent, Model model) {
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

        profileTableViewer.setInput(applyingProfileList.toArray());
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
    private void createButtonControl(Composite composite) {
        Composite buttonComposite = new Composite(composite, SWT.FILL);
        GridLayout buttonGridLayout = new GridLayout();
        buttonComposite.setLayout(buttonGridLayout);
        buttonComposite.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
        
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
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
        gridData.widthHint = 60;
        addProfileButton.setLayoutData(gridData);

        addProfileButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {

                ProfileChooseDialog profileChooseDialog = new ProfileChooseDialog(getShell(),
                    UMLMessage.LABEL_UML_PROFILE_APPLICATION,
                    model.eResource().getURI(), applyingProfileList);

                if (profileChooseDialog.isExistApplicableProfiles()) { // 조회할
                    // 프로파일
                    // 목록이
                    // 존재하면
                    openProfileApplicationDialog(profileChooseDialog);
                } else {
                    openProfileDialog();
                    saveMemento();
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
                    
                    apply();
                    
                    callerSection.isDirty();
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
            
            apply();

            callerSection.isDirty();
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
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
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
                    
                    apply();
                    
                    callerSection.isDirty();
                }
            }
        });
    }

    /**
     * apply
     *   void
     */
    private void apply() {

//        CompoundCommand commands = new CompoundCommand();
//        
//        // root model 에 프로파일 적용-추가 및 삭제 (동기화)
//        applyProfile(commands, model);
//        DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);
        
        DomainUtil.run(new TransactionalAction() {
            
            @Override
            public void doExecute() {
                CompoundCommand commands = new CompoundCommand();
                // root model 에 프로파일 적용-추가 및 삭제 (동기화)
                applyProfile(commands, model);
                
            }
        });
    }

    private void applyProfile(CompoundCommand commands, Package pkg) {
        RecordingCommand command = null;

        List<Profile> appliedProfileList = new ArrayList<Profile>();
        List<Profile> displayedProfileList = getApplyingProfileList();
        for(ProfileApplication pApp : pkg.getAllProfileApplications()){
            appliedProfileList.add(pApp.getAppliedProfile());
        }
        //추가할 프로파일 리스트 생성
        List<Profile> addList = new ArrayList<Profile>();
        for(Profile pro : displayedProfileList){
            addList.add(pro);
        }
        //삭제할 프로파일 리스트 생성
        List<Profile> removeList = new ArrayList<Profile>();
        for(Profile pro : appliedProfileList){
            removeList.add(pro);
        }
        
        //모델편집기에는 표시되나 실제 모델에는 적용되지 않은 프로파일 리스트를 추려낸다.
        for(Profile profile : appliedProfileList){
            if( addList.contains(profile) ){
                addList.remove(profile);
            }
        }
        //프로파일들을 모델에 추가
        for (Profile profile : addList) {
            pkg.applyProfile(profile);
        }
        //모델편집기에는 표시되지 않고 실제 모델에는 적용되어 있는 프로파일 리스트를 추려낸다.
        for(Profile profile : displayedProfileList){
            if( removeList.contains(profile) ){
                removeList.remove(profile);
            }
        }
        //프로파일들을 모델에서 삭제
        for (Profile profile : removeList) {
            pkg.unapplyProfile(profile);
        }
        
        TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList(pkg);
        
        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
            Package fragmentedPackage = (Package) iterator.next();

            List<Profile> profiles = fragmentedPackage.getAppliedProfiles();
            for (Profile profile : profiles) {
                // 패지키에 적용된 프로파일이 모델에 포함되어 있지 않으면 프로파일 unApply.
                if (!displayedProfileList.contains(profile)) {
                    fragmentedPackage.unapplyProfile(profile);
                }
            }
        }
        
        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
            Package fragmentedPackage = (Package) iterator.next();
            
            List<Profile> profiles = fragmentedPackage.getAppliedProfiles();
            
            if (profiles == null || profiles.isEmpty()) {
                for (Profile profile : displayedProfileList) {
                    fragmentedPackage.applyProfile(profile);
                }
            } else {
                for (Profile profile : displayedProfileList) {
                    if (!profiles.contains(profile)) {
                        fragmentedPackage.applyProfile(profile);
                    }
                }
            }
            
        }
    }
    private void applyProfile2(CompoundCommand commands, Package pkg) {
        RecordingCommand command = null;
        
        List<Profile> appliedProfileList = new ArrayList<Profile>();
        List<Profile> displayedProfileList = getApplyingProfileList();
        for(ProfileApplication pApp : pkg.getAllProfileApplications()){
            appliedProfileList.add(pApp.getAppliedProfile());
        }
        //추가할 프로파일 리스트 생성
        List<Profile> addList = new ArrayList<Profile>();
        for(Profile pro : displayedProfileList){
            addList.add(pro);
        }
        //삭제할 프로파일 리스트 생성
        List<Profile> removeList = new ArrayList<Profile>();
        for(Profile pro : appliedProfileList){
            removeList.add(pro);
        }
        
        //모델편집기에는 표시되나 실제 모델에는 적용되지 않은 프로파일 리스트를 추려낸다.
        for(Profile profile : appliedProfileList){
            if( addList.contains(profile) ){
                addList.remove(profile);
            }
        }
        //프로파일들을 모델에 추가
        for (Profile profile : addList) {
            command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                pkg,
                profile,
                true);
            commands.append(command);
        }
        //모델편집기에는 표시되지 않고 실제 모델에는 적용되어 있는 프로파일 리스트를 추려낸다.
        for(Profile profile : displayedProfileList){
            if( removeList.contains(profile) ){
                removeList.remove(profile);
            }
        }
        //프로파일들을 모델에서 삭제
        for (Profile profile : removeList) {
            command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                pkg,
                profile,
                false);
            commands.append(command);
        }
        
        TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList(pkg);
        
        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
            Package fragmentedPackage = (Package) iterator.next();
            
            List<Profile> profiles = fragmentedPackage.getAppliedProfiles();
            for (Profile profile : profiles) {
                // 패지키에 적용된 프로파일이 모델에 포함되어 있지 않으면 프로파일 unApply.
                if (!displayedProfileList.contains(profile)) {
                    System.out.println("remove : "+fragmentedPackage.getQualifiedName() +" / "+ profile.getName() +" / "+ DomainRegistry.getEditingDomain().isReadOnly(fragmentedPackage.eResource()));
                    command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                        fragmentedPackage,
                        profile,
                        false);
                    commands.append(command);
                }
            }
        }
        
        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
            Package fragmentedPackage = (Package) iterator.next();
            
            List<Profile> profiles = fragmentedPackage.getAppliedProfiles();
            
            if (profiles == null || profiles.isEmpty()) {
                for (Profile profile : displayedProfileList) {
                    System.out.println("add : "+fragmentedPackage.getQualifiedName() +" / "+ profile.getName());
                    command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                        fragmentedPackage,
                        profile,
                        true);
                    commands.append(command);
                }
            } else {
                for (Profile profile : displayedProfileList) {
                    if (!profiles.contains(profile)) {
                        System.out.println("add : "+fragmentedPackage.getQualifiedName() +" / "+ profile.getName());
                        command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                            fragmentedPackage,
                            profile,
                            true);
                        commands.append(command);
                    }
                }
            }
            
        }
    }
    
    /**
     * 저장된 메멘토 적재
     * 
     * void
     */
    private void loadMemento() {
        FileReader reader = null;

        try {
            reader = new FileReader(UiUtil.getFileToConfigureDirectory(UICoreConstant.PROJECT_CONSTANTS__PROFILE_MEMENTO));//$NON-NLS-1$

            if (reader == null) {
                lastProfilePath = SystemUtil.getUserHome();
                Log.error("Load file failed"); //$NON-NLS-1$
                return;
            }

            loadLastProfilePath(XMLMemento.createReadRoot(reader));
        } catch (FileNotFoundException fnfe) {
            Log.error(fnfe);
        } catch (Exception e) {
            Log.error(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioe) {
                Log.error(ioe);
            }
        }
    }

    /**
     * 프로파일을 추가한 마지막 경로 가져오기
     * 
     * @param memento
     *            void
     */
    private void loadLastProfilePath(XMLMemento memento) {
        IMemento[] children = memento.getChildren(UICoreConstant.PROJECT_CONSTANTS__TAG_PROFILE);
        lastProfilePath = children[0].getString(UICoreConstant.PROJECT_CONSTANTS__TAG_LAST_PROFILE_PATH);

        if (StringUtil.isEmpty(lastProfilePath)) {
            lastProfilePath = SystemUtil.getUserHome();
        }
    }

    /**
     * 메멘토 저장
     * 
     * void
     */
    private void saveMemento() {
        XMLMemento memento = XMLMemento.createWriteRoot(UICoreConstant.PROJECT_CONSTANTS__TAG_PROFILE);
        saveLastProfilePath(memento);

        FileWriter writer = null;
        try {
            writer = new FileWriter(UiUtil.getFileToConfigureDirectory(UICoreConstant.PROJECT_CONSTANTS__PROFILE_MEMENTO));
            memento.save(writer);
        } catch (IOException ioe) {
            Log.error(ioe);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ioe) {
                Log.error(ioe);
            }
        }
    }

    /**
     * 프로파일을 추가한 마지막 경로 저장
     * 
     * @param memento
     *            void
     */
    private void saveLastProfilePath(XMLMemento memento) {
        if (profileDialog != null && profileDialog.getFilterPath() != null) {
            lastProfilePath = profileDialog.getFilterPath();
        }

        IMemento child = memento.createChild(UICoreConstant.PROJECT_CONSTANTS__TAG_PROFILE);

        child.putString(UICoreConstant.PROJECT_CONSTANTS__TAG_LAST_PROFILE_PATH, lastProfilePath);
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(Model model) {
        this.model = model;

        if (model.getAllProfileApplications().size() > 0) {
            this.applyingProfileList = new ArrayList<Profile>();

            for (ProfileApplication profileApplication : model.getProfileApplications()) {
                if (profileApplication.getAppliedProfile() != null) {
                    this.applyingProfileList.add(profileApplication.getAppliedProfile());
                }
            }
        } else {
            this.applyingProfileList = new ArrayList<Profile>();
        }
    }

    /**
     * 적용할 수 있는 프로파일 목록 초기화 더이상 사용하지 않습니다. 로직 참고를 위해 주석처리만 하였습니다. void
     */
    @Deprecated
    private void initializeApplicableProfileList() {
        // // UML Core 프로파일 존재 여부 확인
        // isExistUMLCoreProfile =
        // PrecedingInitializerRegistry.getInstance().isProjectExist(ManagerConstant.NEXCORE_TOOL_UML_UI_CORE_PRECEDING_INITIALIZER_ID);
        // // MDA 프로파일 존재 여부 확인
        // isExistMDAProfile =
        // PrecedingInitializerRegistry.getInstance().isProjectExist(ManagerConstant.NEXCORE_TOOL_MDA_CORE_PRECEDING_INITIALIZER_ID);
        //        
        // if(applicableProfileList == null) {
        // applicableProfileList = new ArrayList<Object>();
        // } else if(!applicableProfileList.isEmpty()) {
        // applicableProfileList.clear();
        // }
        //
        // // UML Core 프로파일이 존재하면 적용할수 있는 프로파일 목록에 추가
        // if(isExistUMLCoreProfile) {
        // applicableProfileList.addAll(PrecedingInitializerRegistry.getInstance().getProperInitializer(ManagerConstant.NEXCORE_TOOL_UML_UI_CORE_PRECEDING_INITIALIZER_ID).getFeedbacks());
        // }
        // // MDA 프로파일이 존재하면 적용할수 있는 프로파일 목록에 추가
        // if(isExistMDAProfile) {
        // applicableProfileList.addAll(PrecedingInitializerRegistry.getInstance().getProperInitializer(ManagerConstant.NEXCORE_TOOL_MDA_CORE_PRECEDING_INITIALIZER_ID).getFeedbacks());
        // } else {
        // loadMemento();
        // }
        //        
        // // 프로젝트 프로파일 존재 여부 확인
        // List<Object> projectProfileList =
        // EditorUtil.getProjectProfileList(model);
        // // 목록이 있을 경우 적용할 수 있는 프로파일 목록에 추가
        // if(!projectProfileList.isEmpty()) {
        // applicableProfileList.addAll(projectProfileList);
        // }
        //        
        // for(ProfileApplication appliedProfile :
        // model.getProfileApplications()) {
        // if(appliedProfile.getAppliedProfile() != null) {
        // applicableProfileList.remove(appliedProfile.getAppliedProfile());
        // }
        // }
    }

    /**
     * 적용할 프로파일 목록 반환
     * 
     * @return List<Profile>
     */
    public List<Profile> getApplyingProfileList() {
        return applyingProfileList;
    }

    /**
     * 프로파일 테이블 뷰어 반환
     * 
     * @return the profileTableViewer
     */
    public TableViewer getProfileTableViewer() {
        return profileTableViewer;
    }

}
