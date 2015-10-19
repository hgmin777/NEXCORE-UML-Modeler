/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.relation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.relation.Relation#getRelationMap <em>Relation Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.relation.RelationPackage#getRelation()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.relation</li>
 * <li>설  명 : Relation</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Relation extends EObject {
    /**
     * Returns the value of the '<em><b>Relation Map</b></em>' map.
     * The key is of type {@link org.eclipse.uml2.uml.Element},
     * and the value is of type list of {@link org.eclipse.uml2.uml.Element},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relation Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relation Map</em>' map.
     * @see nexcore.tool.uml.model.relation.RelationPackage#getRelation_RelationMap()
     * @model mapType="nexcore.tool.uml.model.relation.RelationMap<org.eclipse.uml2.uml.Element, org.eclipse.uml2.uml.Element>"
     * @generated
     */
    EMap<Element, EList<Element>> getRelationMap();

} // Relation
