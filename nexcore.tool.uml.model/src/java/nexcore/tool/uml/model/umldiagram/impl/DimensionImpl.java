/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dimension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.DimensionImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.DimensionImpl#getHeight <em>Height</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : DimensionImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class DimensionImpl extends EObjectImpl implements Dimension {
    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * This is true if the Width attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean widthESet;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * This is true if the Height attribute has been set.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean heightESet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected DimensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.DIMENSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        boolean oldWidthESet = widthESet;
        widthESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.DIMENSION__WIDTH, oldWidth, width, !oldWidthESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetWidth() {
        int oldWidth = width;
        boolean oldWidthESet = widthESet;
        width = WIDTH_EDEFAULT;
        widthESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.DIMENSION__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetWidth() {
        return widthESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        boolean oldHeightESet = heightESet;
        heightESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.DIMENSION__HEIGHT, oldHeight, height, !oldHeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetHeight() {
        int oldHeight = height;
        boolean oldHeightESet = heightESet;
        height = HEIGHT_EDEFAULT;
        heightESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.DIMENSION__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetHeight() {
        return heightESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.DIMENSION__WIDTH:
                return new Integer(getWidth());
            case UMLDiagramPackage.DIMENSION__HEIGHT:
                return new Integer(getHeight());
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
            case UMLDiagramPackage.DIMENSION__WIDTH:
                setWidth(((Integer)newValue).intValue());
                return;
            case UMLDiagramPackage.DIMENSION__HEIGHT:
                setHeight(((Integer)newValue).intValue());
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
            case UMLDiagramPackage.DIMENSION__WIDTH:
                unsetWidth();
                return;
            case UMLDiagramPackage.DIMENSION__HEIGHT:
                unsetHeight();
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
            case UMLDiagramPackage.DIMENSION__WIDTH:
                return isSetWidth();
            case UMLDiagramPackage.DIMENSION__HEIGHT:
                return isSetHeight();
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
        result.append(" (width: ");
        if (widthESet) result.append(width); else result.append("<unset>");
        result.append(", height: ");
        if (heightESet) result.append(height); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // DimensionImpl
