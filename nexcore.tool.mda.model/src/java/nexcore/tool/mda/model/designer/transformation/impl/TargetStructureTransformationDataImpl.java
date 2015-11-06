/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.designer.transformation.ApplicationType;
import nexcore.tool.mda.model.designer.transformation.DataNameType;
import nexcore.tool.mda.model.designer.transformation.LocationType;
import nexcore.tool.mda.model.designer.transformation.NameType;
import nexcore.tool.mda.model.designer.transformation.ParentType;
import nexcore.tool.mda.model.designer.transformation.TargetCreationType;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TargetType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Target Structure Transformation Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getDataName
 * <em>Data Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getTargetCreationType
 * <em>Target Creation Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getTargetLocation
 * <em>Target Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getTargetApplicableStereotype
 * <em>Target Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getTargetType
 * <em>Target Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getParentType
 * <em>Parent Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getTargetName
 * <em>Target Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getApplicationType
 * <em>Application Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#isPropertyCreation
 * <em>Property Creation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getPropertyApplicableStereotype
 * <em>Property Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#isOperationCreation
 * <em>Operation Creation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl#getOperationApplicableStereotype
 * <em>Operation Applicable Stereotype</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TargetStructureTransformationDataImpl extends EObjectImpl implements TargetStructureTransformationData {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached value of the '{@link #getDataName() <em>Data Name</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDataName()
     * @generated
     * @ordered
     */
    protected DataNameType dataName;

    /**
     * The default value of the '{@link #getTargetCreationType()
     * <em>Target Creation Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetCreationType()
     * @generated
     * @ordered
     */
    protected static final TargetCreationType TARGET_CREATION_TYPE_EDEFAULT = TargetCreationType.SELF;

    /**
     * The cached value of the '{@link #getTargetCreationType()
     * <em>Target Creation Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetCreationType()
     * @generated
     * @ordered
     */
    protected TargetCreationType targetCreationType = TARGET_CREATION_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTargetLocation()
     * <em>Target Location</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetLocation()
     * @generated
     * @ordered
     */
    protected LocationType targetLocation;

    /**
     * The default value of the '{@link #getTargetApplicableStereotype()
     * <em>Target Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTargetApplicableStereotype()
     * @generated
     * @ordered
     */
    protected static final String TARGET_APPLICABLE_STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetApplicableStereotype()
     * <em>Target Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTargetApplicableStereotype()
     * @generated
     * @ordered
     */
    protected String targetApplicableStereotype = TARGET_APPLICABLE_STEREOTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetType() <em>Target Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetType()
     * @generated
     * @ordered
     */
    protected static final TargetType TARGET_TYPE_EDEFAULT = TargetType.NONE;

    /**
     * The cached value of the '{@link #getTargetType() <em>Target Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetType()
     * @generated
     * @ordered
     */
    protected TargetType targetType = TARGET_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getParentType() <em>Parent Type</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getParentType()
     * @generated
     * @ordered
     */
    protected EList<ParentType> parentType;

    /**
     * The cached value of the '{@link #getTargetName() <em>Target Name</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetName()
     * @generated
     * @ordered
     */
    protected NameType targetName;

    /**
     * The default value of the '{@link #getApplicationType()
     * <em>Application Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getApplicationType()
     * @generated
     * @ordered
     */
    protected static final ApplicationType APPLICATION_TYPE_EDEFAULT = ApplicationType.MERGING;

    /**
     * The cached value of the '{@link #getApplicationType()
     * <em>Application Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getApplicationType()
     * @generated
     * @ordered
     */
    protected ApplicationType applicationType = APPLICATION_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isPropertyCreation()
     * <em>Property Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isPropertyCreation()
     * @generated
     * @ordered
     */
    protected static final boolean PROPERTY_CREATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPropertyCreation()
     * <em>Property Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isPropertyCreation()
     * @generated
     * @ordered
     */
    protected boolean propertyCreation = PROPERTY_CREATION_EDEFAULT;

    /**
     * The default value of the '{@link #getPropertyApplicableStereotype()
     * <em>Property Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getPropertyApplicableStereotype()
     * @generated
     * @ordered
     */
    protected static final String PROPERTY_APPLICABLE_STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPropertyApplicableStereotype()
     * <em>Property Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getPropertyApplicableStereotype()
     * @generated
     * @ordered
     */
    protected String propertyApplicableStereotype = PROPERTY_APPLICABLE_STEREOTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isOperationCreation()
     * <em>Operation Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isOperationCreation()
     * @generated
     * @ordered
     */
    protected static final boolean OPERATION_CREATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOperationCreation()
     * <em>Operation Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isOperationCreation()
     * @generated
     * @ordered
     */
    protected boolean operationCreation = OPERATION_CREATION_EDEFAULT;

    /**
     * The default value of the '{@link #getOperationApplicableStereotype()
     * <em>Operation Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOperationApplicableStereotype()
     * @generated
     * @ordered
     */
    protected static final String OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOperationApplicableStereotype()
     * <em>Operation Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOperationApplicableStereotype()
     * @generated
     * @ordered
     */
    protected String operationApplicableStereotype = OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TargetStructureTransformationDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.TARGET_STRUCTURE_TRANSFORMATION_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DataNameType getDataName() {
        return dataName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDataName(DataNameType newDataName, NotificationChain msgs) {
        DataNameType oldDataName = dataName;
        dataName = newDataName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME,
                oldDataName,
                newDataName);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDataName(DataNameType newDataName) {
        if (newDataName != dataName) {
            NotificationChain msgs = null;
            if (dataName != null)
                msgs = ((InternalEObject) dataName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME, null, msgs);
            if (newDataName != null)
                msgs = ((InternalEObject) newDataName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME, null, msgs);
            msgs = basicSetDataName(newDataName, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME,
                newDataName,
                newDataName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetCreationType getTargetCreationType() {
        return targetCreationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetCreationType(TargetCreationType newTargetCreationType) {
        TargetCreationType oldTargetCreationType = targetCreationType;
        targetCreationType = newTargetCreationType == null ? TARGET_CREATION_TYPE_EDEFAULT : newTargetCreationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE,
                oldTargetCreationType,
                targetCreationType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationType getTargetLocation() {
        return targetLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTargetLocation(LocationType newTargetLocation, NotificationChain msgs) {
        LocationType oldTargetLocation = targetLocation;
        targetLocation = newTargetLocation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION,
                oldTargetLocation,
                newTargetLocation);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetLocation(LocationType newTargetLocation) {
        if (newTargetLocation != targetLocation) {
            NotificationChain msgs = null;
            if (targetLocation != null)
                msgs = ((InternalEObject) targetLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION, null, msgs);
            if (newTargetLocation != null)
                msgs = ((InternalEObject) newTargetLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION, null, msgs);
            msgs = basicSetTargetLocation(newTargetLocation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION,
                newTargetLocation,
                newTargetLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTargetApplicableStereotype() {
        return targetApplicableStereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetApplicableStereotype(String newTargetApplicableStereotype) {
        String oldTargetApplicableStereotype = targetApplicableStereotype;
        targetApplicableStereotype = newTargetApplicableStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE,
                oldTargetApplicableStereotype,
                targetApplicableStereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetType getTargetType() {
        return targetType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetType(TargetType newTargetType) {
        TargetType oldTargetType = targetType;
        targetType = newTargetType == null ? TARGET_TYPE_EDEFAULT : newTargetType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE,
                oldTargetType,
                targetType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ParentType> getParentType() {
        if (parentType == null) {
            parentType = new EObjectContainmentEList<ParentType>(ParentType.class,
                this,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE);
        }
        return parentType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameType getTargetName() {
        return targetName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTargetName(NameType newTargetName, NotificationChain msgs) {
        NameType oldTargetName = targetName;
        targetName = newTargetName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME,
                oldTargetName,
                newTargetName);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetName(NameType newTargetName) {
        if (newTargetName != targetName) {
            NotificationChain msgs = null;
            if (targetName != null)
                msgs = ((InternalEObject) targetName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME, null, msgs);
            if (newTargetName != null)
                msgs = ((InternalEObject) newTargetName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME, null, msgs);
            msgs = basicSetTargetName(newTargetName, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME,
                newTargetName,
                newTargetName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ApplicationType getApplicationType() {
        return applicationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setApplicationType(ApplicationType newApplicationType) {
        ApplicationType oldApplicationType = applicationType;
        applicationType = newApplicationType == null ? APPLICATION_TYPE_EDEFAULT : newApplicationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE,
                oldApplicationType,
                applicationType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isPropertyCreation() {
        return propertyCreation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPropertyCreation(boolean newPropertyCreation) {
        boolean oldPropertyCreation = propertyCreation;
        propertyCreation = newPropertyCreation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION,
                oldPropertyCreation,
                propertyCreation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPropertyApplicableStereotype() {
        return propertyApplicableStereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPropertyApplicableStereotype(String newPropertyApplicableStereotype) {
        String oldPropertyApplicableStereotype = propertyApplicableStereotype;
        propertyApplicableStereotype = newPropertyApplicableStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE,
                oldPropertyApplicableStereotype,
                propertyApplicableStereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isOperationCreation() {
        return operationCreation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOperationCreation(boolean newOperationCreation) {
        boolean oldOperationCreation = operationCreation;
        operationCreation = newOperationCreation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION,
                oldOperationCreation,
                operationCreation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getOperationApplicableStereotype() {
        return operationApplicableStereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOperationApplicableStereotype(String newOperationApplicableStereotype) {
        String oldOperationApplicableStereotype = operationApplicableStereotype;
        operationApplicableStereotype = newOperationApplicableStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE,
                oldOperationApplicableStereotype,
                operationApplicableStereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME:
                return basicSetDataName(null, msgs);
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION:
                return basicSetTargetLocation(null, msgs);
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE:
                return ((InternalEList<?>) getParentType()).basicRemove(otherEnd, msgs);
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME:
                return basicSetTargetName(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME:
                return getDataName();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE:
                return getTargetCreationType();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION:
                return getTargetLocation();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE:
                return getTargetApplicableStereotype();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE:
                return getTargetType();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE:
                return getParentType();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME:
                return getTargetName();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE:
                return getApplicationType();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION:
                return isPropertyCreation() ? Boolean.TRUE : Boolean.FALSE;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE:
                return getPropertyApplicableStereotype();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION:
                return isOperationCreation() ? Boolean.TRUE : Boolean.FALSE;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                return getOperationApplicableStereotype();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME:
                setDataName((DataNameType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE:
                setTargetCreationType((TargetCreationType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION:
                setTargetLocation((LocationType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE:
                setTargetApplicableStereotype((String) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE:
                setTargetType((TargetType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE:
                getParentType().clear();
                getParentType().addAll((Collection<? extends ParentType>) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME:
                setTargetName((NameType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE:
                setApplicationType((ApplicationType) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION:
                setPropertyCreation(((Boolean) newValue).booleanValue());
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE:
                setPropertyApplicableStereotype((String) newValue);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION:
                setOperationCreation(((Boolean) newValue).booleanValue());
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                setOperationApplicableStereotype((String) newValue);
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
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME:
                setDataName((DataNameType) null);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE:
                setTargetCreationType(TARGET_CREATION_TYPE_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION:
                setTargetLocation((LocationType) null);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE:
                setTargetApplicableStereotype(TARGET_APPLICABLE_STEREOTYPE_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE:
                setTargetType(TARGET_TYPE_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE:
                getParentType().clear();
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME:
                setTargetName((NameType) null);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE:
                setApplicationType(APPLICATION_TYPE_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION:
                setPropertyCreation(PROPERTY_CREATION_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE:
                setPropertyApplicableStereotype(PROPERTY_APPLICABLE_STEREOTYPE_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION:
                setOperationCreation(OPERATION_CREATION_EDEFAULT);
                return;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                setOperationApplicableStereotype(OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT);
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
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME:
                return dataName != null;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE:
                return targetCreationType != TARGET_CREATION_TYPE_EDEFAULT;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION:
                return targetLocation != null;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE:
                return TARGET_APPLICABLE_STEREOTYPE_EDEFAULT == null ? targetApplicableStereotype != null
                : !TARGET_APPLICABLE_STEREOTYPE_EDEFAULT.equals(targetApplicableStereotype);
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE:
                return targetType != TARGET_TYPE_EDEFAULT;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE:
                return parentType != null && !parentType.isEmpty();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME:
                return targetName != null;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE:
                return applicationType != APPLICATION_TYPE_EDEFAULT;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION:
                return propertyCreation != PROPERTY_CREATION_EDEFAULT;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE:
                return PROPERTY_APPLICABLE_STEREOTYPE_EDEFAULT == null ? propertyApplicableStereotype != null
                : !PROPERTY_APPLICABLE_STEREOTYPE_EDEFAULT.equals(propertyApplicableStereotype);
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION:
                return operationCreation != OPERATION_CREATION_EDEFAULT;
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                return OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT == null ? operationApplicableStereotype != null
                : !OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT.equals(operationApplicableStereotype);
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
        result.append(" (targetCreationType: ");
        result.append(targetCreationType);
        result.append(", targetApplicableStereotype: ");
        result.append(targetApplicableStereotype);
        result.append(", targetType: ");
        result.append(targetType);
        result.append(", applicationType: ");
        result.append(applicationType);
        result.append(", propertyCreation: ");
        result.append(propertyCreation);
        result.append(", propertyApplicableStereotype: ");
        result.append(propertyApplicableStereotype);
        result.append(", operationCreation: ");
        result.append(operationCreation);
        result.append(", operationApplicableStereotype: ");
        result.append(operationApplicableStereotype);
        result.append(')');
        return result.toString();
    }

} // TargetStructureTransformationDataImpl
