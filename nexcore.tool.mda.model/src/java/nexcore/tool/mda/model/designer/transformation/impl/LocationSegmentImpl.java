/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.LocationKeywordType;
import nexcore.tool.mda.model.designer.transformation.LocationSegment;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Location Segment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl#getSequence
 * <em>Sequence</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl#getLocationName
 * <em>Location Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl#getLocationKeyword
 * <em>Location Keyword</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LocationSegmentImpl extends EObjectImpl implements LocationSegment {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * The default value of the '{@link #getSequence() <em>Sequence</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSequence()
     * @generated
     * @ordered
     */
    protected static final int SEQUENCE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getSequence() <em>Sequence</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSequence()
     * @generated
     * @ordered
     */
    protected int sequence = SEQUENCE_EDEFAULT;

    /**
     * The default value of the '{@link #getLocationName()
     * <em>Location Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLocationName()
     * @generated
     * @ordered
     */
    protected static final String LOCATION_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLocationName()
     * <em>Location Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLocationName()
     * @generated
     * @ordered
     */
    protected String locationName = LOCATION_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLocationKeyword()
     * <em>Location Keyword</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLocationKeyword()
     * @generated
     * @ordered
     */
    protected static final LocationKeywordType LOCATION_KEYWORD_EDEFAULT = LocationKeywordType.NONE;

    /**
     * The cached value of the '{@link #getLocationKeyword()
     * <em>Location Keyword</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLocationKeyword()
     * @generated
     * @ordered
     */
    protected LocationKeywordType locationKeyword = LOCATION_KEYWORD_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LocationSegmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.LOCATION_SEGMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSequence(int newSequence) {
        int oldSequence = sequence;
        sequence = newSequence;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.LOCATION_SEGMENT__SEQUENCE,
                oldSequence,
                sequence));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLocationName(String newLocationName) {
        String oldLocationName = locationName;
        locationName = newLocationName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.LOCATION_SEGMENT__LOCATION_NAME,
                oldLocationName,
                locationName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationKeywordType getLocationKeyword() {
        return locationKeyword;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLocationKeyword(LocationKeywordType newLocationKeyword) {
        LocationKeywordType oldLocationKeyword = locationKeyword;
        locationKeyword = newLocationKeyword == null ? LOCATION_KEYWORD_EDEFAULT : newLocationKeyword;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.LOCATION_SEGMENT__LOCATION_KEYWORD,
                oldLocationKeyword,
                locationKeyword));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.LOCATION_SEGMENT__SEQUENCE:
                return new Integer(getSequence());
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_NAME:
                return getLocationName();
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_KEYWORD:
                return getLocationKeyword();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TransformationPackage.LOCATION_SEGMENT__SEQUENCE:
                setSequence(((Integer) newValue).intValue());
                return;
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_NAME:
                setLocationName((String) newValue);
                return;
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_KEYWORD:
                setLocationKeyword((LocationKeywordType) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TransformationPackage.LOCATION_SEGMENT__SEQUENCE:
                setSequence(SEQUENCE_EDEFAULT);
                return;
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_NAME:
                setLocationName(LOCATION_NAME_EDEFAULT);
                return;
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_KEYWORD:
                setLocationKeyword(LOCATION_KEYWORD_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TransformationPackage.LOCATION_SEGMENT__SEQUENCE:
                return sequence != SEQUENCE_EDEFAULT;
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_NAME:
                return LOCATION_NAME_EDEFAULT == null ? locationName != null
                : !LOCATION_NAME_EDEFAULT.equals(locationName);
            case TransformationPackage.LOCATION_SEGMENT__LOCATION_KEYWORD:
                return locationKeyword != LOCATION_KEYWORD_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sequence: ");
        result.append(sequence);
        result.append(", locationName: ");
        result.append(locationName);
        result.append(", locationKeyword: ");
        result.append(locationKeyword);
        result.append(')');
        return result.toString();
    }

} // LocationSegmentImpl
