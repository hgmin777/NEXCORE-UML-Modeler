/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.relation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.relation.RelationFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.relation</li>
 * <li>설  명 : RelationPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface RelationPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "relation";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/relation";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "relation";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    RelationPackage eINSTANCE = nexcore.tool.uml.model.relation.impl.RelationPackageImpl.init();

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.relation.impl.RelationImpl <em>Relation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.relation.impl.RelationImpl
     * @see nexcore.tool.uml.model.relation.impl.RelationPackageImpl#getRelation()
     * @generated
     */
    int RELATION = 0;

    /**
     * The feature id for the '<em><b>Relation Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__RELATION_MAP = 0;

    /**
     * The number of structural features of the '<em>Relation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.relation.impl.RelationMapImpl <em>Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.relation.impl.RelationMapImpl
     * @see nexcore.tool.uml.model.relation.impl.RelationPackageImpl#getRelationMap()
     * @generated
     */
    int RELATION_MAP = 1;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_MAP_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.relation.Relation <em>Relation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relation</em>'.
     * @see nexcore.tool.uml.model.relation.Relation
     * @generated
     */
    EClass getRelation();

    /**
     * Returns the meta object for the map '{@link nexcore.tool.uml.model.relation.Relation#getRelationMap <em>Relation Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Relation Map</em>'.
     * @see nexcore.tool.uml.model.relation.Relation#getRelationMap()
     * @see #getRelation()
     * @generated
     */
    EReference getRelation_RelationMap();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.uml2.uml.Element"
     *        valueType="org.eclipse.uml2.uml.Element" valueMany="true"
     * @generated
     */
    EClass getRelationMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getRelationMap()
     * @generated
     */
    EReference getRelationMap_Key();

    /**
     * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getRelationMap()
     * @generated
     */
    EReference getRelationMap_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RelationFactory getRelationFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.relation.impl.RelationImpl <em>Relation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.relation.impl.RelationImpl
         * @see nexcore.tool.uml.model.relation.impl.RelationPackageImpl#getRelation()
         * @generated
         */
        EClass RELATION = eINSTANCE.getRelation();

        /**
         * The meta object literal for the '<em><b>Relation Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATION__RELATION_MAP = eINSTANCE.getRelation_RelationMap();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.relation.impl.RelationMapImpl <em>Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.relation.impl.RelationMapImpl
         * @see nexcore.tool.uml.model.relation.impl.RelationPackageImpl#getRelationMap()
         * @generated
         */
        EClass RELATION_MAP = eINSTANCE.getRelationMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATION_MAP__KEY = eINSTANCE.getRelationMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATION_MAP__VALUE = eINSTANCE.getRelationMap_Value();

    }

} //RelationPackage
