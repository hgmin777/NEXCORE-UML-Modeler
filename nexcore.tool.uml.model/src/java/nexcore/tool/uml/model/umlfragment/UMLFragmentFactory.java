/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlfragment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.umlfragment.UMLFragmentPackage
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment</li>
 * <li>설  명 : UMLFragmentFactory</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UMLFragmentFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    UMLFragmentFactory eINSTANCE = nexcore.tool.uml.model.umlfragment.impl.UMLFragmentFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Diagram Grabber</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Diagram Grabber</em>'.
     * @generated
     */
    DiagramGrabber createDiagramGrabber();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    UMLFragmentPackage getUMLFragmentPackage();

} // UMLFragmentFactory
