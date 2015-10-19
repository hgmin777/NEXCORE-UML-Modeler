/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.util;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.util.ModelManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.util</li>
 * <li>설  명 : UMLManipulation</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class UMLManipulation {

    /**
     * ACTORS_PACKAGE
     */
    private final static String ACTORS_PACKAGE = "Actors"; //$NON-NLS-1$

    //  private final static String USECASES_PACKAGE = "Usecases"; //$NON-NLS-1$
    //
    // private final static String USECASE_REALIZATION_PACKAGE =
    // "Usecase Realization";

    /**
     * 이름이 "Actors"인 패키지를 찾아서 리턴한다.
     * 
     * @param pkg
     * @return Package
     */
    public static Package findActorPackage(Package pkg) {
        Package pack = getPackageOfTheName(pkg, ACTORS_PACKAGE);
        if (null == pack) {
            Display.getDefault().syncExec(new Runnable() {

                public void run() {
                    MessageDialog.openWarning(ProjectExplorerPlugin.getShell(),
                        UMLMessage.MESSAGE_DIALOG_TITLE,
                        UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_FIND, ACTORS_PACKAGE) 
                            + UICoreConstant.PROJECT_CONSTANTS__BLANK + UMLMessage.MESSAGE_CONTINUE);
                }

            });
        }
        return pack;
    }

    /**
     * findUsecasePackage
     *  
     * @param pkg
     * @return Package
     */
    public static Package findUsecasePackage(Package pkg) {
        Package pack = getPackageOfTheName(pkg, MDDCommonUtil.getUsecasesPackageName()); // USECASES_PACKAGE
        // 에서
        // 수정
        if (null == pack) {
            Display.getDefault().syncExec(new Runnable() {

                public void run() {
                    MessageDialog.openWarning(ProjectExplorerPlugin.getShell(),
                        UMLMessage.MESSAGE_DIALOG_TITLE,
                        UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_FIND,
                            MDDCommonUtil.getUsecasesPackageName()) 
                                + UICoreConstant.PROJECT_CONSTANTS__BLANK + UMLMessage.MESSAGE_CONTINUE); // USECASES_PACKAGE
                    // 에서 수정
                }

            });
        }
        return pack;
    }

    /**
     * findUsecaseRealizationPackage
     *  
     * @param pkg
     * @return Package
     */
    public static Package findUsecaseRealizationPackage(Package pkg) {
        Package pack = getPackageOfTheName(pkg, MDDCommonUtil.getUsecaseRealizationPackageName()); // USECASE_REALIZATION_PACKAGE
        // 에서
        // 수정
        if (null == pack) {
            Display.getDefault().syncExec(new Runnable() {

                public void run() {
                    MessageDialog.openWarning(ProjectExplorerPlugin.getShell(),
                        UMLMessage.MESSAGE_DIALOG_TITLE,
                        UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_FIND,
                            MDDCommonUtil.getUsecaseRealizationPackageName())
                                + UICoreConstant.PROJECT_CONSTANTS__BLANK + UMLMessage.MESSAGE_CONTINUE); // USECASE_REALIZATION_PACKAGE
                    // 에서
                    // 수정
                }

            });
        }
        return pack;
    }

    /**
     * getDiagramList
     *  
     * @param namespace
     * @param type
     * @return List<Diagram>
     */
    public static List<Diagram> getDiagramList(Namespace namespace, DiagramType type) {
        List<Diagram> diagramModelList = new ArrayList<Diagram>();

        if (DiagramType.SEQUENCE_DIAGRAM.equals(type)) {
            diagramModelList.addAll(ModelManager.getAllDiagramList(namespace, type));

        } else {
            EAnnotation annotation = namespace.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
            if (annotation != null) {
                Diagram diagram;
                for (EObject eobj : annotation.getContents()) {
                    if (eobj.eClass() == UMLDiagramPackage.Literals.DIAGRAM) {
                        diagram = (Diagram) eobj;

                        if (type == diagram.getType()) {
                            diagramModelList.add(diagram);
                        }
                    }
                }
            }
        }
        return ModelManager.sortDiagramList(diagramModelList);

    }

    /**
     * getChildUsecaseRealizationList
     *  
     * @param parent
     * @return List<Package>
     */
    public static List<Package> getChildUsecaseRealizationList(Package parent) {
        UMLEnumerator<Package> packageEnumerator = new UMLEnumerator<Package>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.PACKAGE);
    }

    /**
     * getChildActorList
     *  
     * @param parent
     * @return List<Actor>
     */
    public static List<Actor> getChildActorList(Package parent) {
        UMLEnumerator<Actor> packageEnumerator = new UMLEnumerator<Actor>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.ACTOR);
    }

    /**
     * getChildUseCaseList
     *  
     * @param parent
     * @return List<UseCase>
     */
    public static List<UseCase> getChildUseCaseList(Package parent) {
        UMLEnumerator<UseCase> packageEnumerator = new UMLEnumerator<UseCase>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.USE_CASE);
    }

    /**
     * getChildComponentList
     *  
     * @param parent
     * @return List<Component>
     */
    public static List<Component> getChildComponentList(Package parent) {
        UMLEnumerator<Component> packageEnumerator = new UMLEnumerator<Component>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.COMPONENT);
    }

    /**
     * getChildCollaborationList
     *  
     * @param parent
     * @return List<Collaboration>
     */
    public static List<Collaboration> getChildCollaborationList(Package parent) {
        UMLEnumerator<Collaboration> packageEnumerator = new UMLEnumerator<Collaboration>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.COLLABORATION);
    }

    /**
     * getChildPackageList
     *  
     * @param parent
     * @return List<Package>
     */
    public static List<Package> getChildPackageList(Package parent) {
        UMLEnumerator<Package> packageEnumerator = new UMLEnumerator<Package>();
        return packageEnumerator.getSortedChildren(parent, UMLPackage.Literals.PACKAGE);
    }

    /**
     * getAllChildPackageList
     *  
     * @param parent
     * @return List<Package>
     */
    public static List<Package> getAllChildPackageList(Package parent) {
        UMLEnumerator<Package> packageEnumerator = new UMLEnumerator<Package>();
        // return packageEnumerator.getSortedAllChildrenList(parent,
        // UMLPackage.Literals.PACKAGE);
        return packageEnumerator.getSortedAllPackageChildrenList(parent);
    }

    /**
     * getPackageOfTheName
     *  
     * @param parentPkg
     * @param name
     * @return Package
     */
    public static Package getPackageOfTheName(Package parentPkg, String name) {

        if (parentPkg == null) {
            return null;
        }
        List<PackageableElement> list = parentPkg.getPackagedElements();
        Package pkg;
        for (PackageableElement packageableElement : list) {
            if (packageableElement.eClass() == UMLPackage.Literals.PACKAGE) {
                pkg = (Package) packageableElement;
                if (name.toLowerCase().equals(pkg.getName().toLowerCase())) {
                    return pkg;
                }
                pkg = getPackageOfTheName(pkg, name);
                if (null != pkg) {
                    return pkg;
                }
            }
        }
        return null;

    }

    /**
     * getChildPackageOfTheName
     *  
     * @param parentPkg
     * @param name
     * @return Package
     */
    public static Package getChildPackageOfTheName(Package parentPkg, String name) {

        if (parentPkg == null) {
            return null;
        }
        List<PackageableElement> list = parentPkg.getPackagedElements();
        Package pkg;
        for (PackageableElement packageableElement : list) {
            if (packageableElement.eClass() == UMLPackage.Literals.PACKAGE) {
                pkg = (Package) packageableElement;
                if (name.toLowerCase().equals(pkg.getName().toLowerCase())) {
                    return pkg;
                }
            }
        }
        return null;
    }

    /**
     * name 이름을 가진 상위 패키지의 존재 여부를 리턴한다.
     * 
     * @param element
     * @param name
     * @return boolean
     */
    public static boolean hasParentPackageOfTheName(NamedElement namedElement, String name) {

        NamedElement element = (NamedElement) namedElement.getOwner();
        if (null != element) {
            if (element instanceof Package && name.equals(element.getName())) {
                return true;
            }
            return hasParentPackageOfTheName(element, name);
        }
        return false;
    }

    // static public List<Classifier> getClassList(Diagram diagram) {
    // List<Classifier> list = new ArrayList<Classifier>();
    // if (null != diagram) {
    // return list;
    // }
    // Element element;
    // EList<AbstractNode> nodeList = diagram.getNodeList();
    // for (AbstractNode abstractNode : nodeList) {
    // element = abstractNode.getUmlModel();
    // if (element != null) {
    // if (element instanceof Classifier) {
    // list.add((Classifier) element);
    // }
    // }
    // }
    // return list;
    // }

    /**
     * diagram에 작성된 Classifier의 리스트를 source에 담는다.
     * 
     * @param diagram
     * @param source
     */
    public static void getClassList(Diagram diagram, List<Classifier> source) {

        if (null != diagram) {
            EList<AbstractNode> nodeList = diagram.getNodeList();
            for (AbstractNode abstractNode : nodeList) {
                Element element = abstractNode.getUmlModel();
                if (element != null) {
                    if (element instanceof Classifier) {
                        if (!source.contains(element)) {
                            source.add((Classifier) element);
                        }
                    }
                }
            }
        }

    }

    /**
     * getComponentList
     *  
     * @param diagram
     * @param source void
     */
    public static void getComponentList(Diagram diagram, List<Component> source) {

        if (null != diagram) {
            EList<AbstractNode> nodeList = diagram.getNodeList();
            for (AbstractNode abstractNode : nodeList) {
                Element element = abstractNode.getUmlModel();
                if (element != null) {
                    if (element.eClass() == UMLPackage.Literals.COMPONENT) {
                        if (!source.contains(element)) {
                            source.add((Component) element);
                        }
                    }
                }
            }
        }

    }

}
