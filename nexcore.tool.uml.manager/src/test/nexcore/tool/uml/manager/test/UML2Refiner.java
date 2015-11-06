/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */


package nexcore.tool.uml.manager.test;

import java.io.IOException;

import nexcore.tool.uml.manager.UMLHelper;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.internal.resource.UMLResourceImpl;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.test</li>
 * <li>설 명 : UML2Refiner</li>
 * <li>작성일 : 2010. 2. 24.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class UML2Refiner {

    /**
     * 
     * 
     * @param args
     *            void
     */
    public static void main(String[] args) {
        Model model = UMLFactory.eINSTANCE.createModel();
        model.setName("UML Model");
        org.eclipse.uml2.uml.Package pack = (org.eclipse.uml2.uml.Package) model.createNestedPackage("View");
        UseCase usecase = UMLHelper.createUseCase();
        usecase.setName("handle uc");
        pack.getPackagedElements().add(usecase);
        URI uri = URI.createURI("file://d://original.umx");
        EcoreFactory factory = EcoreFactory.eINSTANCE;
        EAttribute eAttribute = factory.createEAttribute();
        eAttribute.setName("UC_IC");
        eAttribute.setEType(EcorePackage.eINSTANCE.getEString());
        usecase.eClass().getEStructuralFeatures().add(eAttribute);
        // UMLPackage.eINSTANCE.getEClassifiers().add(eAttri);

        usecase.eSet(eAttribute, "333");

        Resource resource = new UMLResourceImpl(uri);
        resource.getContents().add(model);
        try {
            resource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
