/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */


package nexcore.tool.uml.ui.core.editor.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core_v1.0</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.util</li>
 * <li>설 명 : EditorUtil</li>
 * <li>작성일 : 2010. 7. 7.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class EditorUtil {

    /**
     * 프로젝트에서 추가한 프로파일 목록 반환하는 메소드
     * 
     * @param model
     * @return List<Object>
     */
    public static List<Object> getProjectProfileList(Model model) {

        IProject project = findProjectIncludingModel(model);
        IResource profileFolder = getProfileFolderInProject(project);

        return getProjectProfileListByProjectfolder(profileFolder);
    }

    /**
     * 
     * URI 를 이용하여 프로젝트 프로파일 목록 반환
     * 
     * @param uri
     * @return List<Object>
     */
    public static List<Object> getProjectProfileList(URI uri) {

        int seg = 0;
        if( uri.isPlatform() ){
            seg = 1;
        }
        IProject project = findProjectByProjectName(uri.segment(seg));
        IResource profileFolder = getProfileFolderInProject(project);

        return getProjectProfileListByProjectfolder(profileFolder);
    }

    /**
     * 
     * URI 를 이용하여 프로젝트 Root의 프로파일 목록 반환
     * 
     * @param uri
     * @return List<Object>
     */
    public static List<Object> getProjectRootProfileList(URI uri) {
        int seg = 0;
        if( uri.isPlatform() ){
            seg = 1;
        }
        
        IProject project = findProjectByProjectName(uri.segment(seg));

        List<Object> projectProfileList = new ArrayList<Object>();

        URI profileURI = null;
        Resource resource = null;
        Profile profile = null;

        try {
            for (IResource profileResource : project.members()) {
                if (profileResource instanceof IFile
                    && profileResource.getFileExtension().equals(UMLResource.FILE_EXTENSION)) {
                    profileURI = URI.createURI(profileResource.getFullPath().toString());
                    resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(profileURI, true);

                    if (!resource.isLoaded()) {
                        try {
                            resource.load(DomainUtil.getLoadOptions());
                        } catch (IOException e) {}
                    }

                    profile = DomainUtil.getUMLProfileRoot(resource);
                    projectProfileList.add(profile);
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }

        return projectProfileList;
    }

    /**
     * 프로파일 폴더를 이용하여 폴더내의 프로파일 목록을 반환
     * 
     * 
     * @param profileFolder
     * @return List<Object>
     */
    public static List<Object> getProjectProfileListByProjectfolder(IResource profileFolder) {

        List<Object> projectProfileList = new ArrayList<Object>();

        URI profileURI = null;
        Resource resource = null;
        Profile profile = null;

        if (profileFolder instanceof IFolder) {
            try {
                for (IResource profileResource : ((IFolder) profileFolder).members()) {
                    if (profileResource instanceof IFile
                        && profileResource.getFileExtension().equals(UMLResource.FILE_EXTENSION)) {
                        profileURI = URI.createURI(profileResource.getFullPath().toString());
                        resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(profileURI, true);

                        if (!resource.isLoaded()) {
                            try {
                                resource.load(DomainUtil.getLoadOptions());
                            } catch (IOException e) {}
                        }

                        profile = DomainUtil.getUMLProfileRoot(resource);
                        projectProfileList.add(profile);
                    }
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }

        return projectProfileList;
    }

    /**
     * 모델을 포함하는 프로젝트 찾는 메소드
     * 
     * @param model
     * @return IProject
     */
    private static IProject findProjectIncludingModel(Model model) {
        String projectName = getProjectNameFromModel(model);

        return findProjectByProjectName(projectName);
    }

    /**
     * 프로젝트 명을 이용하여 프로젝트를 찾는 메소드
     * 
     * 
     * @param projectName
     * @return IProject
     */
    private static IProject findProjectByProjectName(String projectName) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        for (IProject project : root.getProjects()) {
            if (projectName.length() > 0) {
                if (project.getName().equals(URI.decode(projectName))) {
                    return project;
                }
            } else {
                return project;
            }
        }

        return null;
    }

    /**
     * 모델로부터 프로젝트 이름 가져오는 메소드
     * 
     * @param model
     * @return String
     */
    private static String getProjectNameFromModel(Model model) {
        /**
         * 일감번호 #6195 - 모델 편집기 상세 탭 오류 Modified by yschoi. 20100714
         * 
         * 결함 : 모델 디렉토리로부터의 상대적인 위치로 프로젝트 정보를 찾아 오게 되어 있어서 기본 위치(프로젝트/모델)를 벗어난
         * 모델의 경우엔 모델 편집기의 상세 탭에서 프로젝트 정보를 제대로 가져 오지 못해 상세 탭이 열리지 않는 문제 발생 해결 방안
         * : 모델의 getURI() 로 얻어오게 되는 세그먼트[1]는 무조건 프로젝트 정보이므로 세그먼트[1]를 프로젝트명으로
         * 가져오는 방식으로 변경
         */
        if (!model.eResource().getURI().isEmpty()) {
            return model.eResource().getURI().segment(1);
        }

        return null;
    }

    /**
     * 프로젝트의 프로파일 디렉토리 반환하는 메소드
     * 
     * @param project
     * @return IResource
     */
    private static IResource getProfileFolderInProject(IProject project) {
        try {
            if (project != null) {
                for (IResource resource : project.members()) {
                    if (resource.getName().equals(UMLMessage.LABEL_PROFILE)) {
                        return resource;
                    }
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * getAppliedProfileList
     *  
     * @param model
     * @return List<Object>
     */
    public static List<Object> getAppliedProfileList(Model model) {
        if (null == model) {
            return new ArrayList<Object>();
        }
        List<Object> appliedProfileList = new ArrayList<Object>();
        for (ProfileApplication pApp : model.getProfileApplications()) {
            appliedProfileList.add(pApp.getAppliedProfile());
        }
        return appliedProfileList;
    }

}
