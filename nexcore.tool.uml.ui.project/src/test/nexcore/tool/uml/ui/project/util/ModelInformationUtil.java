/* 
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C.
 * You shall not disclose such confidential information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with SK C&C.
 */

package nexcore.tool.uml.ui.project.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.builder.UMLNature;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * 모델별 패키지 정보 - 이름, 개수
 * 모델별 단편 정보 - 이름, 개수
 * 
 * 모델/패키지 - 다이어그램 정보 - 종류, 이름 모드수?
 * 
 * 전체 resourceSet 의 파일 사이즈 크기 
 * - 모델별 크기
 * - 단편별 크기 
 * - 전체 합
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.info</li>
 * <li>설 명 : ProjectInformation</li>
 * <li>작성일 : 2012. 8. 20.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ModelInformationUtil {

    /**
     * 리소스에 포함된 패키지 개수
     */
    public static int packageCountOfResource(Resource resource) {
        /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        if (model == null)
            return 0;

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
        }*/
        
        TreeMap<String, Package> packageList = selectQuery(resource);
        
        return packageList.size();
    }

    public static TreeMap<String, Package> getFragmentPackages(final Model model) {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                continue;
            }
        }

        return packageList;
    }

    /**
     * 프로젝트에 포함된 패지키 개수 packageOfResourceSet void
     */
    public static int packageCountOfProject(IProject project) {
        TreeMap<String, Package> packageList = new TreeMap<String, Package>();
        
        try {
            if (!UMLNature.hasUMLNature(project)) {
                return 0;
            }

            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();


            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);

                IFile file = WorkspaceSynchronizer.getFile(resource);

                if (file.getProject() != project) {
                    continue;
                }

                /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }
                    packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                }*/
                
                TreeMap<String, Package> selectQuery = selectQuery(resource);
                
                if(!selectQuery.isEmpty()) {
                    packageList.putAll(selectQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return packageList.size();
    }

    /**
     * 리소스셋에 포함된 패지키 개수 packageOfResourceSet void
     */
    public static int packageCountOfResourceSet() {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        try {
            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();

            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);
                /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }
                    packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                }*/
                
                TreeMap<String, Package> selectQuery = selectQuery(resource);
                
                if(!selectQuery.isEmpty()) {
                    packageList.putAll(selectQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageList.size();
    }

    /**
     * 리소스에 포함된 패키지 정보
     */
    public static TreeMap<String, Package> packageInfomationOfResource(Resource resource) {

        /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        if (model == null)
            return packageList;


        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
        }*/
        
        TreeMap<String, Package> packageList = selectQuery(resource);
        
        return packageList;
    }

    /**
     * 프로젝트에 포함된 패지키 정보 packageOfResourceSet void
     */
    public static TreeMap<String, Package> packageInfomationOfProject(IProject project) {
        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        try {
            if (!UMLNature.hasUMLNature(project)) {
                return packageList;
            }

            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();


            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);

                IFile file = WorkspaceSynchronizer.getFile(resource);

                if (file.getProject() != project) {
                    continue;
                }

                /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }
                    packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                }*/
                
                TreeMap<String, Package> selectQuery = selectQuery(resource);
                
                if(!selectQuery.isEmpty()) {
                    packageList.putAll(selectQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageList;
    }

    /**
     * 리소스셋에 포함된 패지키 정보 packageOfResourceSet void
     */
    public static TreeMap<String, Package> packageInfomationOfResourceSet() {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        try {
            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();

            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);
                /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }
                    packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                }*/
                
                TreeMap<String, Package> selectQuery = selectQuery(resource);
                
                if(!selectQuery.isEmpty()) {
                    packageList.putAll(selectQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageList;
    }

    /**
     * 
     * 리소스에 포함된 단편 개수 void
     */
    public static TreeMap<String, Package> packageFragmentOfResource(Resource resource) {
        
        Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        if (model == null)
            return packageList;

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
            }
        }

        return packageList;
    }

    /**
     * 
     * 프로젝트에 포함된 단편 개수 void
     */
    public static TreeMap<String, Package> packageFragmentOfProject(IProject project) {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        try {
            if (!UMLNature.hasUMLNature(project)) {
                return packageList;
            }

            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();


            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);

                IFile file = WorkspaceSynchronizer.getFile(resource);

                if (file.getProject() != project) {
                    continue;
                }

                Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }
                    
                    if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                        packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageList;
    }

    /**
     * 
     * 리소스셋에 포함된 단편 개수 void
     */
    public static TreeMap<String, Package> packageFragmentOfResourceSet() {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        try {
            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();

            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);
                /*Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Package pkg = (Package) ir.next();

                    if (pkg == null || pkg.getQualifiedName() == null) {
                        continue;
                    }

                    if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                        packageList.put(pkg.getQualifiedName() + "_" + EcoreUtil.getURI(pkg).fragment(), pkg);
                    }
                }*/
                
                TreeMap<String, Package> selectQuery = selectQuery(resource);
                if (selectQuery.isEmpty()) {
                    packageList.putAll(selectQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageList;

    }
    
    private static TreeMap<String, Package> selectQuery(Resource resource) {
        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        if (model == null)
            return packageList;

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }

            packageList.put(String.format("%s_%s", pkg.getQualifiedName(), EcoreUtil.getURI(pkg).fragment()), pkg);
        }

        return packageList;
    }
    

    /**
     * 
     * 리소스에 포함된 다이어그램 정보 void
     */
    public static TreeMap<String, Diagram> diagramInfomationOfResource(Resource resource) {
        Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
            UMLPackage.Literals.MODEL);

        TreeMap<String, Diagram> diagramList = new TreeMap<String, Diagram>();

        if (model == null)
            return diagramList;

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLDiagramPackage.eINSTANCE.getDiagram())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Diagram diagram = (Diagram) ir.next();
            String key = genKey(diagram);

            diagramList.put(key, diagram);
        }

        return diagramList;
    }

    private static String genKey(Diagram diagram) {
        StringBuffer sb = new StringBuffer();
        sb.append(((NamedElement) diagram.getParent()).getQualifiedName());
        sb.append(UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON);
        sb.append(diagram.getName());
        sb.append("_");
        sb.append(EcoreUtil.getURI(diagram).fragment());
        return sb.toString();
    }

    /**
     * 
     * 프로젝트에 포함된 다이어그램 정보 void
     */
    public static TreeMap<String, Diagram> diagramInfomationOfProject(IProject project) {
        TreeMap<String, Diagram> diagramList = new TreeMap<String, Diagram>();
        
        try {
            if (!UMLNature.hasUMLNature(project)) {
                return diagramList;
            }

            final ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();

            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);

                IFile file = WorkspaceSynchronizer.getFile(resource);

                if (file.getProject() != project) {
                    continue;
                }

                Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLDiagramPackage.eINSTANCE.getDiagram())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Diagram diagram = (Diagram) ir.next();
                    String key = genKey(diagram);

                    diagramList.put(key, diagram);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return diagramList;
    }

    /**
     * 
     * 리소스셋에 포함된 다이어그램 정보 void
     */
    public static TreeMap<String, Diagram> diagramInfomationOfResourceSet() {

        TreeMap<String, Diagram> diagramList = new TreeMap<String, Diagram>();
        try {
            ResourceSet resourceSet = DomainRegistry.getEditingDomain().getResourceSet();
            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();


            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                URI uri = (URI) iterator.next();

                if (!uri.toString().endsWith("umx")) {
                    continue;
                }

                Resource resource = uriResourceMap.get(uri);
                Model model = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resource.getContents(),
                    UMLPackage.Literals.MODEL);

                if (model == null)
                    continue;

                SELECT statement = new SELECT(new FROM(model),
                    new WHERE(new EObjectTypeRelationCondition(UMLDiagramPackage.eINSTANCE.getDiagram())));
                IQueryResult result = statement.execute();

                for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                    Diagram diagram = (Diagram) ir.next();
                    String key = genKey(diagram);

                    diagramList.put(key, diagram);
                }
            }

            System.out.println("ResourceSet : " + diagramList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return diagramList;
    }
}
