/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Relation</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : RelationImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RelationImpl extends AbstractConnectionImpl implements Relation {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RelationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.RELATION;
    }

} // RelationImpl
