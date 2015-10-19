/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Notation Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl#getRegions <em>Regions</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl#getCompartmentList <em>Compartment List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : NotationNodeImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class NotationNodeImpl extends AbstractNodeImpl implements NotationNode {
    /**
     * The cached value of the '{@link #getRegions() <em>Regions</em>}' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRegions()
     * @generated
     * @ordered
     */
    protected EList<RelationType> regions;

    /**
     * The cached value of the '{@link #getCompartmentList()
     * <em>Compartment List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCompartmentList()
     * @generated
     * @ordered
     */
    protected EList<AbstractNode> compartmentList;

    /**
     * The cached value of the '{@link #getLabels() <em>Labels</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLabels()
     * @generated
     * @ordered
     */
    protected EList<LabelNode> labels;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected NotationNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.NOTATION_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<RelationType> getRegions() {
        if (regions == null) {
            regions = new EDataTypeUniqueEList<RelationType>(RelationType.class, this, UMLDiagramPackage.NOTATION_NODE__REGIONS);
        }
        return regions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractNode> getCompartmentList() {
        if (compartmentList == null) {
            compartmentList = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST);
        }
        return compartmentList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelNode> getLabels() {
        if (labels == null) {
            labels = new EObjectEList<LabelNode>(LabelNode.class, this, UMLDiagramPackage.NOTATION_NODE__LABELS);
        }
        return labels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST:
                return ((InternalEList<?>)getCompartmentList()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.NOTATION_NODE__REGIONS:
                return getRegions();
            case UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST:
                return getCompartmentList();
            case UMLDiagramPackage.NOTATION_NODE__LABELS:
                return getLabels();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UMLDiagramPackage.NOTATION_NODE__REGIONS:
                getRegions().clear();
                getRegions().addAll((Collection<? extends RelationType>)newValue);
                return;
            case UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST:
                getCompartmentList().clear();
                getCompartmentList().addAll((Collection<? extends AbstractNode>)newValue);
                return;
            case UMLDiagramPackage.NOTATION_NODE__LABELS:
                getLabels().clear();
                getLabels().addAll((Collection<? extends LabelNode>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.NOTATION_NODE__REGIONS:
                getRegions().clear();
                return;
            case UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST:
                getCompartmentList().clear();
                return;
            case UMLDiagramPackage.NOTATION_NODE__LABELS:
                getLabels().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.NOTATION_NODE__REGIONS:
                return regions != null && !regions.isEmpty();
            case UMLDiagramPackage.NOTATION_NODE__COMPARTMENT_LIST:
                return compartmentList != null && !compartmentList.isEmpty();
            case UMLDiagramPackage.NOTATION_NODE__LABELS:
                return labels != null && !labels.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (Regions: ");
        result.append(regions);
        result.append(')');
        return result.toString();
    }

} // NotationNodeImpl
