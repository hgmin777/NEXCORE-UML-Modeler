/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.composite.ProfileComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설 명 : DetailsProfileSection</li>
 * <li>작성일 : 2009. 12. 14.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DetailsProfileSection extends AbstractSection {

    /** 모델 인스턴스 */
    private Model model;

    /** 프로파일 컴포짓 */
    private ProfileComposite profileComposite;

    /**
     * 생성자
     * 
     * @param page
     * @param parent
     */
    public DetailsProfileSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        section.setText(UMLMessage.LABEL_UMLPROFILE_LIST);
        section.setDescription(UMLMessage.MESSAGE_PROFILE_LIST);

        Composite client = toolkit.createComposite(section);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = toolkit.getBorderStyle() != SWT.NULL ? 0 : 2;
        client.setLayout(layout);

        Composite composite = toolkit.createComposite(client);
        GridLayout compositeLayout = new GridLayout();
        compositeLayout.numColumns = 2;
        composite.setLayout(compositeLayout);

        initializeSection();

        createProfileControl(toolkit, composite);

        section.setClient(client);
    }

    /**
     * 프로파일 컨트롤러 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createProfileControl(FormToolkit toolkit, Composite composite) {
        // profileComposite = new ProfileComposite(composite, model);
        profileComposite = new ProfileComposite(this, composite, model);

        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        gridData.grabExcessVerticalSpace = true;

        profileComposite.setLayoutData(gridData);
    }

    /**
     * 섹션 초기화
     * 
     * void
     */
    private void initializeSection() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();
    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    @Override
    public void commit(boolean onSave) {
        if (onSave) {
//            CompoundCommand commands = new CompoundCommand();
//            RecordingCommand command = null;
//
//            List<Profile> appliedProfileList = new ArrayList<Profile>();
//            List<Profile> displayedProfileList = profileComposite.getApplyingProfileList();
//            for(ProfileApplication pApp : model.getAllProfileApplications()){
//                appliedProfileList.add(pApp.getAppliedProfile());
//            }
//            //추가할 프로파일 리스트 생성
//            List<Profile> addList = new ArrayList<Profile>();
//            for(Profile pro : displayedProfileList){
//                addList.add(pro);
//            }
//            //삭제할 프로파일 리스트 생성
//            List<Profile> removeList = new ArrayList<Profile>();
//            for(Profile pro : appliedProfileList){
//                removeList.add(pro);
//            }
//            //모델편집기에는 표시되나 실제 모델에는 적용되지 않은 프로파일 리스트를 추려낸다.
//            for(Profile profile : appliedProfileList){
//                if( addList.contains(profile) ){
//                    addList.remove(profile);
//                }
//            }
//            //프로파일들을 모델에 추가
//            for (Profile profile : addList) {
//                command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
//                model,
//                profile,
//                true);
//                commands.append(command);
//            }
//            //모델편집기에는 표시되지 않고 실제 모델에는 적용되어 있는 프로파일 리스트를 추려낸다.
//            for(Profile profile : displayedProfileList){
//                if( removeList.contains(profile) ){
//                    removeList.remove(profile);
//                }
//            }
//            //프로파일들을 모델에서 삭제
//            for (Profile profile : removeList) {
//                command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
//                model,
//                profile,
//                false);
//                commands.append(command);
//            }
//            
//
//            DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);
//
//            profileComposite.getProfileTableViewer().setInput(profileComposite.getApplyingProfileList().toArray());
            profileComposite.getProfileTableViewer().refresh();

            super.commit(onSave);
            // model.eResource().setModified(false);
        }
    }

}
