/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.relation.impl;

import nexcore.tool.uml.model.relation.Relation;
import nexcore.tool.uml.model.relation.RelationPackage;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.relation.impl.RelationImpl#getRelationMap <em>Relation Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.relation.impl</li>
 * <li>설  명 : RelationImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RelationImpl extends EObjectImpl implements Relation {
    /**
     * The cached value of the '{@link #getRelationMap() <em>Relation Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelationMap()
     * @generated
     * @ordered
     */
    protected EMap<Element, EList<Element>> relationMap;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RelationPackage.Literals.RELATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<Element, EList<Element>> getRelationMap() {
        if (relationMap == null) {
            relationMap = new EcoreEMap<Element,EList<Element>>(RelationPackage.Literals.RELATION_MAP, RelationMapImpl.class, this, RelationPackage.RELATION__RELATION_MAP);
        }
        return relationMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RelationPackage.RELATION__RELATION_MAP:
                return ((InternalEList<?>)getRelationMap()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RelationPackage.RELATION__RELATION_MAP:
                if (coreType) return getRelationMap();
                else return getRelationMap().map();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RelationPackage.RELATION__RELATION_MAP:
                ((EStructuralFeature.Setting)getRelationMap()).set(newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case RelationPackage.RELATION__RELATION_MAP:
                getRelationMap().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RelationPackage.RELATION__RELATION_MAP:
                return relationMap != null && !relationMap.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //RelationImpl
