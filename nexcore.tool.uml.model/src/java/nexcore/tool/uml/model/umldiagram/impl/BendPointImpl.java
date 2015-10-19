/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bend Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.BendPointImpl#getFirstPosition <em>First Position</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.BendPointImpl#getSecondPosition <em>Second Position</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.BendPointImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : BendPointImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class BendPointImpl extends EObjectImpl implements BendPoint {
    /**
     * The cached value of the '{@link #getFirstPosition() <em>First Position</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFirstPosition()
     * @generated
     * @ordered
     */
    protected Dimension firstPosition;

    /**
     * The cached value of the '{@link #getSecondPosition() <em>Second Position</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSecondPosition()
     * @generated
     * @ordered
     */
    protected Dimension secondPosition;

    /**
     * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected static final float WEIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected float weight = WEIGHT_EDEFAULT;

    /**
     * This is true if the Weight attribute has been set.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean weightESet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BendPointImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.BEND_POINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension getFirstPosition() {
        return firstPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFirstPosition(Dimension newFirstPosition, NotificationChain msgs) {
        Dimension oldFirstPosition = firstPosition;
        firstPosition = newFirstPosition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.BEND_POINT__FIRST_POSITION, oldFirstPosition, newFirstPosition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFirstPosition(Dimension newFirstPosition) {
        if (newFirstPosition != firstPosition) {
            NotificationChain msgs = null;
            if (firstPosition != null)
                msgs = ((InternalEObject)firstPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.BEND_POINT__FIRST_POSITION, null, msgs);
            if (newFirstPosition != null)
                msgs = ((InternalEObject)newFirstPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.BEND_POINT__FIRST_POSITION, null, msgs);
            msgs = basicSetFirstPosition(newFirstPosition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.BEND_POINT__FIRST_POSITION, newFirstPosition, newFirstPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension getSecondPosition() {
        return secondPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSecondPosition(Dimension newSecondPosition, NotificationChain msgs) {
        Dimension oldSecondPosition = secondPosition;
        secondPosition = newSecondPosition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.BEND_POINT__SECOND_POSITION, oldSecondPosition, newSecondPosition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSecondPosition(Dimension newSecondPosition) {
        if (newSecondPosition != secondPosition) {
            NotificationChain msgs = null;
            if (secondPosition != null)
                msgs = ((InternalEObject)secondPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.BEND_POINT__SECOND_POSITION, null, msgs);
            if (newSecondPosition != null)
                msgs = ((InternalEObject)newSecondPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.BEND_POINT__SECOND_POSITION, null, msgs);
            msgs = basicSetSecondPosition(newSecondPosition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.BEND_POINT__SECOND_POSITION, newSecondPosition, newSecondPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public float getWeight() {
        return weight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setWeight(float newWeight) {
        float oldWeight = weight;
        weight = newWeight;
        boolean oldWeightESet = weightESet;
        weightESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.BEND_POINT__WEIGHT, oldWeight, weight, !oldWeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetWeight() {
        float oldWeight = weight;
        boolean oldWeightESet = weightESet;
        weight = WEIGHT_EDEFAULT;
        weightESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.BEND_POINT__WEIGHT, oldWeight, WEIGHT_EDEFAULT, oldWeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetWeight() {
        return weightESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.BEND_POINT__FIRST_POSITION:
                return basicSetFirstPosition(null, msgs);
            case UMLDiagramPackage.BEND_POINT__SECOND_POSITION:
                return basicSetSecondPosition(null, msgs);
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
            case UMLDiagramPackage.BEND_POINT__FIRST_POSITION:
                return getFirstPosition();
            case UMLDiagramPackage.BEND_POINT__SECOND_POSITION:
                return getSecondPosition();
            case UMLDiagramPackage.BEND_POINT__WEIGHT:
                return new Float(getWeight());
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
            case UMLDiagramPackage.BEND_POINT__FIRST_POSITION:
                setFirstPosition((Dimension)newValue);
                return;
            case UMLDiagramPackage.BEND_POINT__SECOND_POSITION:
                setSecondPosition((Dimension)newValue);
                return;
            case UMLDiagramPackage.BEND_POINT__WEIGHT:
                setWeight(((Float)newValue).floatValue());
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
            case UMLDiagramPackage.BEND_POINT__FIRST_POSITION:
                setFirstPosition((Dimension)null);
                return;
            case UMLDiagramPackage.BEND_POINT__SECOND_POSITION:
                setSecondPosition((Dimension)null);
                return;
            case UMLDiagramPackage.BEND_POINT__WEIGHT:
                unsetWeight();
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
            case UMLDiagramPackage.BEND_POINT__FIRST_POSITION:
                return firstPosition != null;
            case UMLDiagramPackage.BEND_POINT__SECOND_POSITION:
                return secondPosition != null;
            case UMLDiagramPackage.BEND_POINT__WEIGHT:
                return isSetWeight();
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
        result.append(" (weight: ");
        if (weightESet) result.append(weight); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // BendPointImpl
