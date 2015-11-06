/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedetail;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail</li>
 * <li>설  명 : UseCaseDetailFactory</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UseCaseDetailFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    UseCaseDetailFactory eINSTANCE = nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Use Case Detail</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Use Case Detail</em>'.
     * @generated
     */
    UseCaseDetail createUseCaseDetail();

    /**
     * Returns a new object of class '<em>Flow Object</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Flow Object</em>'.
     * @generated
     */
    FlowObject createFlowObject();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    UseCaseDetailPackage getUseCaseDetailPackage();

} // UseCaseDetailFactory
