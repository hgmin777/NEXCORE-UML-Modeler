/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.RegionEnumType;
import nexcore.tool.uml.model.umldiagram.RegionType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Region Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl#getRegionKind <em>Region Kind</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl#getRegionSize <em>Region Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : RegionTypeImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RegionTypeImpl extends EObjectImpl implements RegionType {
    /**
     * The default value of the '{@link #getRegionKind() <em>Region Kind</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRegionKind()
     * @generated
     * @ordered
     */
    protected static final RegionEnumType REGION_KIND_EDEFAULT = RegionEnumType.ATTRIBUTE;

    /**
     * The cached value of the '{@link #getRegionKind() <em>Region Kind</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRegionKind()
     * @generated
     * @ordered
     */
    protected RegionEnumType regionKind = REGION_KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getRegionSize() <em>Region Size</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRegionSize()
     * @generated
     * @ordered
     */
    protected Dimension regionSize;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RegionTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.REGION_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RegionEnumType getRegionKind() {
        return regionKind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setRegionKind(RegionEnumType newRegionKind) {
        RegionEnumType oldRegionKind = regionKind;
        regionKind = newRegionKind == null ? REGION_KIND_EDEFAULT : newRegionKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.REGION_TYPE__REGION_KIND, oldRegionKind, regionKind));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension getRegionSize() {
        if (regionSize != null && regionSize.eIsProxy()) {
            InternalEObject oldRegionSize = (InternalEObject)regionSize;
            regionSize = (Dimension)eResolveProxy(oldRegionSize);
            if (regionSize != oldRegionSize) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLDiagramPackage.REGION_TYPE__REGION_SIZE, oldRegionSize, regionSize));
            }
        }
        return regionSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension basicGetRegionSize() {
        return regionSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setRegionSize(Dimension newRegionSize) {
        Dimension oldRegionSize = regionSize;
        regionSize = newRegionSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.REGION_TYPE__REGION_SIZE, oldRegionSize, regionSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.REGION_TYPE__REGION_KIND:
                return getRegionKind();
            case UMLDiagramPackage.REGION_TYPE__REGION_SIZE:
                if (resolve) return getRegionSize();
                return basicGetRegionSize();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UMLDiagramPackage.REGION_TYPE__REGION_KIND:
                setRegionKind((RegionEnumType)newValue);
                return;
            case UMLDiagramPackage.REGION_TYPE__REGION_SIZE:
                setRegionSize((Dimension)newValue);
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
            case UMLDiagramPackage.REGION_TYPE__REGION_KIND:
                setRegionKind(REGION_KIND_EDEFAULT);
                return;
            case UMLDiagramPackage.REGION_TYPE__REGION_SIZE:
                setRegionSize((Dimension)null);
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
            case UMLDiagramPackage.REGION_TYPE__REGION_KIND:
                return regionKind != REGION_KIND_EDEFAULT;
            case UMLDiagramPackage.REGION_TYPE__REGION_SIZE:
                return regionSize != null;
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
        result.append(" (RegionKind: ");
        result.append(regionKind);
        result.append(')');
        return result.toString();
    }

} // RegionTypeImpl
