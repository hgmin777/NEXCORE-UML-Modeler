/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.registry;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.handler.UMLResourceSetManager;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.editor.command.HandleProjectRecordingCommand;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : ModelUpdate</li>
 * <li>작성일 : 2011. 7. 12.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ModelUpdater {

    /**
     * ENCORDING
     */
    private static final String ENCORDING = "UTF-8";

    /**
     * REGEX
     */
    private static final String REGEX = "href=\".*.um[x|f].*.\"";

    /**
     * HREF_FORMAT
     */
    private static final String HREF_FORMAT = "href=\"%s#%s\"";

    /**
     * PLATFORM_RESOURCE_FORMAT
     */
    private static final String PLATFORM_RESOURCE_FORMAT = "platform:/resource";
    
    /**
     * ERROR_MESSAGE_FORMAT
     */
    private static final String ERROR_MESSAGE_FORMAT = "%s -> '%s' does not exist.";

    /**
     * EMPTY_URL_STRING
     */
    private static final String EMPTY_URL_STRING = "%20";
    
    /**
     * BLANK_STRING
     */
    private static final String BLANK_STRING = " ";
    
    /**
     * EMPTY_STRING
     */
    private static final String EMPTY_STRING = "";

    /**
     * resourceSetKey
     */
    private static final String resourceSetKey = "workspace";
    
    /**
     * XMI_REGEX
     */
    private static final String XMI_REGEX = "xmi:id=\".{23}";

    /** alreadyCheck */
    private static boolean alreadyCheck;

    /**
     * resourceSet
     */
    private ResourceSet resourceSet = null;

    /**
     * errorList
     */
    private List<String> errorList = null;
    
    /**
     * refMap
     */
    private Map<String, String> refMap = Collections.EMPTY_MAP;

    /**
     * ModelUpdate
     */
    public ModelUpdater() {
        resourceSet = UMLResourceSetManager.getInstance().getResourceSet(resourceSetKey);
        refMap = new HashMap<String, String>();
        errorList = new ArrayList<String>();
    }

    /**
     * scanAllMembers
     *  
     * @param modelFile void
     */
    public void scanAllMembers(IResource modelFile) {
        try {
            if (modelFile instanceof IFolder) {
                for (IResource member : ((IFolder) modelFile).members()) {
                    scanAllMembers(member);
                }
            } else if (modelFile instanceof IFile) {

                IFile resourceFile = (IFile) modelFile;
                if (!ProjectUtil.isModelFile(resourceFile)) {
                    return;
                }

                StringBuffer sb = readFile(resourceFile);

                Pattern p = Pattern.compile(XMI_REGEX);
                Matcher m = p.matcher(sb.toString());

                while (m.find()) {
                    String id = m.group().substring(8, m.group().length());
                    refMap.put(id, resourceFile.getFullPath().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * readFile
     *  
     * @param resourceFile
     * @return StringBuffer
     */
    private StringBuffer readFile(IFile resourceFile) {
        StringBuffer sb = new StringBuffer();
        InputStream contents = null;
        char[] b = null;
        Reader reader = null;
        try {
            b = new char[1024];
            contents = resourceFile.getContents();
            reader = new BufferedReader(new InputStreamReader(contents, ENCORDING));
            for (int n; (n = reader.read(b)) != -1;) {
                sb.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
            error(e);
        } catch (CoreException e) {
            e.printStackTrace();
            error(e);
        } finally {
            if (contents != null) {
                try {
                    contents.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            b = null;
        }
        return sb;
    }
    
    /**
     * isValid
     *  
     * @param resourceFile
     * @return IStatus
     */
    public static IStatus isValid(IFile resourceFile) {

        StringBuffer sb = new StringBuffer();
        InputStream contents = null;
        char[] b = null;
        Reader reader = null;
        try {
            b = new char[1024];
            contents = resourceFile.getContents();
            reader = new BufferedReader(new InputStreamReader(contents, ENCORDING));
            for (int n; (n = reader.read(b)) != -1;) {
                sb.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CoreException e) {
            e.printStackTrace();
        } finally {
            if (contents != null) {
                try {
                    contents.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            b = null;
        }

        Pattern p = Pattern.compile(REGEX);

        Matcher m = p.matcher(sb.toString());

        MultiStatus resultStatus = new MultiStatus(UiCorePlugin.PLUGIN_ID, IStatus.ERROR, null, null);

        while (m.find()) {
            int s = 6;
            if( m.group().indexOf("platform:/resource") > -1){
                s += PLATFORM_RESOURCE_FORMAT.length();
            }
            String href = m.group().substring(s, m.group().length() - 1);
            
            String[] split = href.split("#");
            if (split.length == 2) {
                String refAddr = split[0];
                String xmiId = split[1];

                // %20 형태로 참조주소에 공백문자가 설정된 경우 에러..
                if (refAddr.indexOf(EMPTY_URL_STRING) > -1) {
                    resultStatus.add(new Status(IStatus.ERROR, UiCorePlugin.PLUGIN_ID, UMLMessage.MESSAGE_ERROR_REF_ADDR_BLANK));
                }

                // platform:/resource 로 시작하는 참조 주소의 경우 에러
                if (refAddr.indexOf(EMPTY_URL_STRING) > -1) {
                    resultStatus.add(new Status(IStatus.ERROR, UiCorePlugin.PLUGIN_ID, UMLMessage.MESSAGE_ERROR_REF_ADDR_PLATFORM_RES));
                }

                try {
                    IFile file2 = resourceFile.getWorkspace().getRoot().getFile(new Path(refAddr).makeAbsolute());
                    if (!file2.exists()) {
                        resultStatus.add(new Status(IStatus.ERROR, UiCorePlugin.PLUGIN_ID, file2.getFullPath().toString() +UMLMessage.MESSAGE_ERROR_FILE_NOT_EXIST));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultStatus.getChildren().length > 0) {
            return resultStatus;
        }

        return Status.OK_STATUS;
    }

    /**
     * updateChildReferenceAddress
     *  
     * @param modelFile void
     */
    public void updateChildReferenceAddress(IResource modelFile) {
        try {
            if (modelFile instanceof IFolder) {
                for (IResource member : ((IFolder) modelFile).members()) {
                    updateChildReferenceAddress(member);
                }
            } else if (modelFile instanceof IFile) {

                IFile resourceFile = (IFile) modelFile;
                if (!ProjectUtil.isModelFile(resourceFile)) {
                    return;
                }

                StringBuffer sb = readFile(resourceFile);

                Pattern p = Pattern.compile(REGEX);
                // Pattern p = Pattern.compile("href=\"[^/].*.um[x|f].*.\"");

                Matcher m = p.matcher(sb.toString());

                StringBuffer sb2 = new StringBuffer();

                boolean isSave = false;
                while (m.find()) {
                    String href = m.group().substring(6, m.group().length() - 1);
                    String[] split = href.split("#");
                    if (split.length == 2) {
                        String refAddr = split[0];
                        String xmiId = split[1];

                        refAddr = refAddr.replaceAll(EMPTY_URL_STRING, BLANK_STRING);
                        if (href.startsWith(PLATFORM_RESOURCE_FORMAT)) {
                            refAddr = refAddr.replaceAll(PLATFORM_RESOURCE_FORMAT, EMPTY_STRING);
                        }

                        if (refMap.containsKey(xmiId)) {
                            String replaceRef = String.format(HREF_FORMAT, refMap.get(xmiId), xmiId);
                            if (!m.group().toString().equals(replaceRef)) {
                                m.appendReplacement(sb2, replaceRef);

                                isSave = true;
                            }
                        } else {
                            IFile file2 = resourceFile.getWorkspace()
                                .getRoot()
                                .getFile(new Path(refAddr).makeAbsolute());
                            if (!file2.exists()) {
                                error(String.format(ERROR_MESSAGE_FORMAT, resourceFile.getFullPath()
                                    .toString(), file2.getFullPath().toString()));
                            }
                            String replaceRef = String.format(HREF_FORMAT, file2.getFullPath().toString(), xmiId);
                            if (!m.group().toString().equals(replaceRef)) {
                                m.appendReplacement(sb2, replaceRef);

                                isSave = true;
                            }
                            refMap.put(xmiId, file2.getFullPath().toString());
                        }

                    }
                }
                m.appendTail(sb2);

                if (isSave) {
                    resourceFile.setContents(new ByteArrayInputStream(sb2.toString().getBytes(ENCORDING)),
                        true,
                        false,
                        null);
                }
            }
        } catch (Exception e) {
            error(e);
        }
    }

    /**
     * updateReferenceAddress
     *  
     * @param project void
     */
    public void updateReferenceAddress(IProject project) {
        try {
            for (IResource resource : project.members()) {
                scanAllMembers(resource);
            }

            for (IResource resource : project.members()) {
                updateChildReferenceAddress(resource);
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * error
     *  
     * @param message void
     */
    private void error(String message) {
        if (!errorList.contains(message)) {
            UiCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, UiCorePlugin.PLUGIN_ID, message));
            errorList.add(message);
        }
    }

    /**
     * error
     *  
     * @param modelPath
     * @param e void
     */
    private void error(String modelPath, Exception e) {
        UiCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            UiCorePlugin.PLUGIN_ID,
            IStatus.ERROR,
            String.format("%s -> %s", modelPath, e.getMessage()),
            e));
    }

    /**
     * error
     *  
     * @param e void
     */
    private void error(Exception e) {
        UiCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            UiCorePlugin.PLUGIN_ID,
            IStatus.ERROR,
            e.getMessage(),
            e));
    }

    /**
     * 
     * 
     * @param project
     *            void
     */
    public void modelUpdate(IProject project, IProgressMonitor monitor) {
        try {
            alreadyCheck = false;
//            updateReferenceAddress(project);

            for (IResource resource : project.members()) {
                updateChildResource(resource, monitor);
            }
        } catch (CoreException e) {
            e.printStackTrace();
        } finally {
            resourceSet.getResources().clear();
            refMap.clear();
        }

    }
    
    /**
     * dispose
     *   void
     */
    public void dispose() {
        resourceSet = null;
        refMap = null;
        UMLResourceSetManager.getInstance().dispose(resourceSetKey);
    }

    /**
     * updateChildResource
     *  
     * @param resourceFile
     * @param monitor void
     */
    public void updateChildResource(IResource resourceFile, IProgressMonitor monitor) {
        try {
            if (resourceFile instanceof IFolder) {
                for (IResource member : ((IFolder) resourceFile).members()) {
                    updateChildResource(member, monitor);
                }
            } else if (resourceFile instanceof IFile) {
                try {
                    boolean isSavable = false;
                    if (ProjectUtil.isModelFile((IFile) resourceFile)) {
                        monitor.worked(1);
                        monitor.subTask(((IFile) resourceFile).getFullPath().toOSString());
                        URI uri = URI.createURI(((IFile) resourceFile).getFullPath().toString());
                        Resource res = DomainRegistry.getUMLDomain().getResource(uri, true);
                        if(res == null) {
                        	return;
                        }
                        for (int j = 0; j < res.getContents().size(); j++) {
                            EObject obj = res.getContents().get(j);
                            if (obj instanceof org.eclipse.uml2.uml.Package) {
                                obj = ProjectUtil.resolveResource(obj);
                                if (!checkViewModelVersion((org.eclipse.uml2.uml.Package) obj, (IFile) resourceFile)) {

                                    if (!alreadyCheck) {
                                        alreadyCheck = true;
                                        isSavable = true;
                                    }
                                    UpdateViewModel.updateViewModel((org.eclipse.uml2.uml.Package) obj,
                                        (IFile) resourceFile);
                                    obj = ProjectUtil.resolveResource((Package) obj);
                                    addUMLModelVersion((org.eclipse.uml2.uml.Package) obj, (IFile) resourceFile);
                                    obj = ProjectUtil.resolveResource(obj);
                                } else if (!alreadyCheck) {
                                    alreadyCheck = true;
                                    isSavable = false;
                                }
                            }
                        }
                        try {
                            if (isSavable) {
                                DomainModelHandlerUtil.save(res, true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // ignore
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 
     * @param file
     * @param obj
     *            void
     */
    public boolean checkViewModelVersion(org.eclipse.uml2.uml.Package package1, IFile file) {
        ProjectElement projectInfo = null;
        for (EAnnotation eAnnotation : package1.getEAnnotations()) {
            if (eAnnotation instanceof ProjectElement) {
                projectInfo = (ProjectElement) eAnnotation;
            }
        }

        if (projectInfo == null) {
            return false;
        } else if (!UICoreConstant.PROJECT_CONSTANTS__MODEL_VERSION.equals(projectInfo.getModelVersion())) {
            return false;
        } else {
            return true;
        }
    } 

    /**
     * 
     * 
     * @param package1
     * @param file
     *            void
     */
    public void addUMLModelVersion(org.eclipse.uml2.uml.Package package1, IFile file) {
        package1 = (Package) ProjectUtil.resolveResource(package1);
        ProjectElement newProjectInfo = ProjectUtil.createProjectInfo();
        RecordingCommand command = new HandleProjectRecordingCommand(DomainRegistry.getEditingDomain(),
            package1,
            newProjectInfo,
            true);
        DomainUtil.executeCommand(command);
    }

}
