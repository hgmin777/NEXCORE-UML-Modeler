/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer;

import java.util.Iterator;
import java.util.Set;

import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.builder.UMLNature;
import nexcore.tool.uml.ui.project.explorer.provider.UMLLabelProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer</li>
 * <li>설 명 : ExplorerDecorator</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ExplorerDecorator extends UMLLabelProvider implements ILightweightLabelDecorator {

    /** ExplorerDecorator ID */
    public static final String ID = UICoreConstant.PROJECT_CONSTANTS__DECORATOR_ID;

    /**
     * ExplorerDecorator
     */
    public ExplorerDecorator() {
        super();
    }

    /**
     * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
     *      org.eclipse.jface.viewers.IDecoration)
     */
    public void decorate(Object element, IDecoration decoration) {
        if (element instanceof IProject) {
            IProject project = (IProject) element;
            if (UMLNature.hasUMLNature(project)) {
                decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.LOCATION_OVER_DECORATOR));
            }
            return;
        }

        ITreeNode treeNode = null;
        if (!(element instanceof ITreeNode)) {
            return;
        } else {
            treeNode = (ITreeNode) element;
        }
        EObject eobject = treeNode.getEObject();

        Resource resource = null;
        if (eobject == null) {
            return;
        }

        if (element instanceof ITreeNode) {
            // TODO pns 속도 문제로 주석처리 개선 필요.
            // createValidationDecorator(eobject, decoration);
        }

//        if (ALMPreference.useCollaboration() && ProjectUtil.useCollaboration(eobject)) {
//            if (MetaContentUtil.hasMetaContent(eobject)) {
//                decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_NCP_DECORATOR),
//                    IDecoration.BOTTOM_RIGHT);
//            }
//        }

        if (eobject instanceof Model) {
            resource = eobject.eResource();
            // 2011-10-26 nspark
            // 프로파일이 삭제된 경우 모델에 에러 아이콘 표시
            Model model = (Model) eobject;
            for (ProfileApplication profileApplication : model.getProfileApplications()) {
                if (profileApplication.getAppliedProfile() != null) {
                    Profile newProfile = profileApplication.getAppliedProfile();
                    if (newProfile.eIsProxy()) {
                        decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_ERROR_OVER));
                        break;
                    }
                }
            }
            boolean active = treeNode.isActive();
            if (!active) {
                decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_CLOSE_MODEL));
            }
        } else if (element instanceof UMLFileTreeNode) {
            resource = eobject.eResource();

            // 2011-08-03 nspark
            // project explorer 에 모델파일에 오류가 있는지 상태 아이콘 표시
            boolean validationCheck = UiCorePlugin.getDefault()
                .getPreferenceStore()
                .getBoolean("MODEL_VALIDATION_CHECK");
            if (validationCheck) {
                if (element instanceof UMLFileTreeNode) {
                    treeNode = (UMLFileTreeNode) element;
                    boolean isValid = treeNode.isValid();
                    if (!isValid) {
                        decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_WARNING_OVER));
                    }
                }
            }
        } else if (eobject instanceof Diagram) {

            // 2011-08-09 강석찬
            // diagram에 오류(dangling)가 있는지 상태 아이콘 표시
            Diagram diagram = (Diagram) eobject;

            boolean isValid = true;
            for (Iterator<AbstractNode> iter = diagram.getNodeList().iterator(); iter.hasNext();) {
                AbstractNode abstractNode = iter.next();
                if (NodeType.NOTE.equals(abstractNode.getNodeType())) {
                    return;
                }

                EObject umlModel = abstractNode.getUmlModel();
                if (umlModel == null) {
                    isValid = false;
                } else if (DomainUtil.isProxy(umlModel)) {
                    isValid = false;
                }
            }

            if (!isValid) {
                decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_WARNING_OVER));
            }
        } else {
            return;
        }

        if (resource == null) {
            return;
        }
        
        if (resource.isModified()) {
            decoration.addSuffix(UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__STAR);
        } else {
            // crossReference 모델의 dirty 상태 체크
            if( resource.getContents() != null && resource.getContents().size() > 0 ) {
                EObject contents = resource.getContents().get(0);
                boolean dirtyOfCrossReference = ResourceManager.getInstance().isDirtyOfCrossReference(contents);
                if (dirtyOfCrossReference) {
                    decoration.addSuffix(UICoreConstant.PROJECT_CONSTANTS__BLANK + UICoreConstant.PROJECT_CONSTANTS__STAR);
                } else {
                    decoration.addSuffix(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                }
            } else {
                decoration.addSuffix(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
            }
        }
    }

    /**
     * 'Validate'에서 실행한 결과에 따라 Marker Decoration 생성.
     * 
     * @param eobject
     * @param decoration
     *            void
     */
    private void createValidationDecorator(EObject eobject, IDecoration decoration) {
        EObject rootContainer = EcoreUtil.getRootContainer(eobject);
        if (rootContainer == null)
            return;

        Resource containerResource = rootContainer.eResource();
        if (containerResource == null)
            return;

        Set<Resource> resourceList = ProjectUtil.getFragmentedFileList(containerResource);

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        for (Resource resource : resourceList) {
            IPath path = new Path(resource.getURI().toString());
            IFile file = root.getFile(path);

            if (file != null && file.exists()) {
                try {
                    IMarker[] markers = file.findMarkers(UICoreConstant.VALIDATION_MARKER_TYPE,
                        true,
                        IResource.DEPTH_INFINITE);
                    for (int i = 0; i < markers.length; i++) {
                        String location = (String) markers[i].getAttribute(IMarker.LOCATION);
                        if (null == location) {
                            return;
                        }
                        int severity = markers[i].getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);

                        // QualifiedName을 비교하여 심각도에 따라 데코레이션을 붙여준다.
                        if (eobject instanceof NamedElement) {
                            String qualifiedName = ((NamedElement) eobject).getQualifiedName();
                            if (null == qualifiedName) {
                                return;
                            } else if (location.startsWith(qualifiedName)) {
                                switch (severity) {
                                    case IMarker.SEVERITY_ERROR:
                                        decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_ERROR_OVER));
                                        break;
                                    case IMarker.SEVERITY_WARNING:
                                        decoration.addOverlay(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_WARNING_OVER));
                                        break;
                                }
                            }
                        }
                    }
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

