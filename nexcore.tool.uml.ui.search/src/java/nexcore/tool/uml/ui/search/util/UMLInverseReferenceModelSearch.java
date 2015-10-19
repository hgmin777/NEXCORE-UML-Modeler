/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelElement;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelMatch;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelSearchPattern;
import nexcore.tool.uml.ui.search.ui.UMLInverseReferenceModelSearchResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.util</li>
 * <li>설 명 : UMLInverseReferenceModelSearch</li>
 * <li>작성일 : 2012. 8. 23.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelSearch {

    /** searchResult */
    private UMLInverseReferenceModelSearchResult searchResult = null;

    /** searchPattern */
    private UMLInverseReferenceModelSearchPattern searchPattern;

    Map<IProject, UMLInverseReferenceModelElement> projectMap = new HashMap<IProject, UMLInverseReferenceModelElement>();
    Map<EObject, UMLInverseReferenceModelElement> elementMap = new HashMap<EObject, UMLInverseReferenceModelElement>();
    /**
     * search
     * 
     * @param result
     * @param pattern
     * @param project
     *            void
     */
    public void search(UMLInverseReferenceModelSearchResult result, UMLInverseReferenceModelSearchPattern pattern) {
        this.searchResult = result;
        this.searchPattern = pattern;

        Job monitorUpdatedJob = new Job("uml model search job") { //$NON-NLS-1$
            /**
             * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
             */
            @Override
            protected IStatus run(IProgressMonitor arg0) {

                UMLInverseReferenceModelElement projectElement = null;
                UMLInverseReferenceModelMatch modelMatch = null;
                Set<EObject> inverseReferencesAllElement = ResourceManager.getInstance()
                    .inverseReferencesAllElement((EObject) searchPattern.getSearchElement());

                for (EObject eObject : inverseReferencesAllElement) {
                    if (!(eObject instanceof NamedElement)) {
                        if (eObject instanceof NotationNode) {
                            if (!(eObject.eContainer() instanceof Diagram)) {
                                continue;
                            }
                        } else if (eObject instanceof PackageableElement) {
                            
                        } else {
                            continue;
                        }
                    }

                    if (eObject instanceof Property) {
                        String name = ((Property) eObject).getName();
                        if (name == null || name.length() == 0) {
                            continue;
                        }
                    }
                    
                    IFile file = WorkspaceSynchronizer.getFile(eObject.eResource());
                    if(file == null || !file.exists()) {
                        continue;
                    }
                    if (projectMap.containsKey(file.getProject())) {
                        projectElement = projectMap.get(file.getProject());
                    } else {
                        projectElement = new UMLInverseReferenceModelElement(file.getProject());
                        projectMap.put(file.getProject(), projectElement);
                    }

                    List<EObject> list = new ArrayList<EObject>();
                    EList<Namespace> namespaces = null;
                    if (eObject instanceof NamedElement) {
                        namespaces = ((NamedElement) eObject).allNamespaces();

                        list.add(eObject);
                        list.addAll(namespaces);
                    } else if (eObject instanceof NotationNode && eObject.eContainer() instanceof Diagram) {
                        Diagram diagram = (Diagram) eObject.eContainer();
                        EObject parent = diagram.getParent();
                        namespaces = ((NamedElement) parent).allNamespaces();

                        // 다이어그램에서 참조하는 경우는 NotationNode에서 여러건이 참조하는 것으로 조회 되기
                        // 때문에 참조 대상을 다이어그램으로 설정한다.
                        eObject = diagram;

                        list.add(eObject);
                        list.add(parent);
                        list.addAll(namespaces);

                        if (elementMap.containsKey(eObject)) {
                            continue;
                        }
                    } else {
                        continue;
                    }

                    UMLInverseReferenceModelElement modelElement = null;
                    UMLInverseReferenceModelElement parentElement = null;

                    for (int i = list.size() - 1; i >= 0; i--) {
                        EObject ele = list.get(i);
                        if (elementMap.containsKey(ele)) {
                            modelElement = elementMap.get(ele);
                        } else {
                            modelElement = new UMLInverseReferenceModelElement(ele);
                            elementMap.put(ele, modelElement);
                        }

                        if (list.size() > 0 && (list.size() - 1) == i) {
                            projectElement.addChild(modelElement);
                        } else {
                            parentElement.addChild(modelElement);
                        }

                        parentElement = modelElement;
                        modelMatch = new UMLInverseReferenceModelMatch(modelElement);
                        searchResult.addMatch(modelMatch);
                    }
                }

                return Status.OK_STATUS;
            }
        };

        monitorUpdatedJob.schedule();
        try {
            monitorUpdatedJob.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
