/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.internal.util.StringMatcher;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.search.match.UMLModelElement;
import nexcore.tool.uml.ui.search.match.UMLModelMatch;
import nexcore.tool.uml.ui.search.match.UMLModelSearchPattern;
import nexcore.tool.uml.ui.search.ui.UMLModelSearchResult;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.util</li>
 * <li>설 명 : UMLModelSearch</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelSearch {

    /** searchResult */
    private UMLModelSearchResult searchResult = null;

    /** searchPattern */
    private UMLModelSearchPattern searchPattern;

    /** _project */
    private IProject _project;

    /**
     * 프로젝트를 리스트에 담아 반환
     * 
     * @param projectNames
     * @return IProject[]
     */
    public IProject[] findUMLDesignProjects(String projectNames[]) {
        List<IProject> returnProjectList = new ArrayList<IProject>();

        if (projectNames == null) {
            IProject projects[] = ResourcesPlugin.getWorkspace().getRoot().getProjects();

            for (int i = 0; i < projects.length; i++) {
                returnProjectList.add(projects[i]);
            }

            return returnProjectList.toArray(new IProject[0]);
        }

        for (int i = 0; i < projectNames.length; i++) {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectNames[i]);

            if (project != null) {
                returnProjectList.add(project);
            }
        }

        return returnProjectList.toArray(new IProject[0]);
    }

    /**
     * search
     *  
     * @param result
     * @param pattern
     * @param project void
     */
    public void search(UMLModelSearchResult result, UMLModelSearchPattern pattern, IProject project) {
        this.searchResult = result;
        this.searchPattern = pattern;
        this._project = project;

        Job monitorUpdatedJob = new Job("uml model search job") { //$NON-NLS-1$
            /**
             * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
             */
            @SuppressWarnings("unchecked")
            @Override
            protected IStatus run(IProgressMonitor arg0) {
//                String scheme = null;
                String uriSegment = null;
                String projectName = null;
                UMLModelElement projectElement = new UMLModelElement(_project);
                UMLModelElement parentElement = null;

                EList<Resource> list = DomainRegistry.getEditingDomain().getResourceSet().getResources();
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    Resource resource = (Resource) iterator.next();
                    URI uri = resource.getURI();
//                    scheme = uri.scheme();
                    if (ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(uri.fileExtension())) {
//                    if (UICoreConstant.MODELSEARCH__SCHEME.equals(scheme)) {
                        uriSegment = uri.segment(0);
                        projectName = _project.getName();
                        if (projectName.equals(uriSegment)) {
                            EList<EObject> objList = resource.getContents();
                            for (EObject obj : objList) {
                                if (obj instanceof NamedElement) {
                                    parentElement = new UMLModelElement(obj);
                                    projectElement.addChild(parentElement);
                                    searchMatch(parentElement, searchPattern);
                                    searchChildren(obj, parentElement);
                                }
                            }
                        }
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

    /**
     * searchMatch
     *  
     * @param element
     * @param pattern void
     */
    private void searchMatch(UMLModelElement element, UMLModelSearchPattern pattern) {
        StringMatcher matcher = new StringMatcher(pattern.getSearchText(), !pattern.isCaseSensitive(), false);
        UMLModelMatch modelMatch = null;

        String modelName = element.getDisplayName();

        if (modelName != null) {
            if (matcher.match(modelName)) {
                modelMatch = new UMLModelMatch(element);
                modelMatch.setOffset(modelName.indexOf(pattern.getSearchText()));
                modelMatch.setLength(pattern.getSearchText().length());

                Object obj = element.getOrgObject();
                NamedElement ne = null;
                if (obj instanceof NamedElement)
                    ne = (NamedElement) obj;
                int flag = 0;
                if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_MODEL)
                    && ne.eClass() == UMLPackage.Literals.MODEL) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_PACKAGE)
                    && ne.eClass() == UMLPackage.Literals.PACKAGE) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_CLASS)
                    && ne.eClass() == UMLPackage.Literals.CLASS) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_COMPONENT)
                    && ne.eClass() == UMLPackage.Literals.COMPONENT) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_ACTOR)
                    && ne.eClass() == UMLPackage.Literals.ACTOR) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_USECASE)
                    && ne.eClass() == UMLPackage.Literals.USE_CASE) {
                    flag = 1;

                } else if (ne != null && searchPattern.containScope(UICoreConstant.MODELSEARCH__TYPE_INTERFACE)
                    && ne.eClass() == UMLPackage.Literals.INTERFACE) {
                    flag = 1;
                }
                if (flag == 1)
                    searchResult.addMatch(modelMatch);

            }
        }

    }

    /**
     * searchChildren
     *  
     * @param eModel
     * @param parentElement void
     */
    public void searchChildren(EObject eModel, UMLModelElement parentElement) {
        NamedElement nameElement = (NamedElement) eModel;
        EList<Element> eleList = nameElement.getOwnedElements();
        UMLModelElement element = null;

        for (Element ele : eleList) {
            if (ele instanceof NamedElement) {
                element = new UMLModelElement(ele);
                parentElement.addChild(element);

                try {
                    // switch(ele.eClass().getClassifierID()){
                    // case UMLPackage.MODEL:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.PACKAGE:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.CLASS:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.COMPONENT:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.ACTOR:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.USE_CASE:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    // case UMLPackage.INTERFACE:
                    // searchMatch(element, searchPattern);
                    // searchChildren(ele, element);
                    // break;
                    //                            
                    // default : ;
                    // }
                    searchMatch(element, searchPattern);
                    searchChildren(ele, element);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
