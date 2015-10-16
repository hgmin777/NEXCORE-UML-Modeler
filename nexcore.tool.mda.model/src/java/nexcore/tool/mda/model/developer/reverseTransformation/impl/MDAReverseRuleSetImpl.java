/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.common.IRuleSet;
import nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ClassRule;
import nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;
import nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MDA Reverse Rule Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getSourceProjectType <em>Source Project Type</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getSourceLanguage <em>Source Language</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getSourceProjects <em>Source Projects</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getTargetModelURI <em>Target Model URI</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getMergeType <em>Merge Type</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#isReferenceModelChange <em>Reference Model Change</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getProfileLocation <em>Profile Location</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getClassRules <em>Class Rules</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getClassDiagramRule <em>Class Diagram Rule</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl#getSequenceDiagramRule <em>Sequence Diagram Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated NOT
 */
public class MDAReverseRuleSetImpl extends EObjectImpl implements MDAReverseRuleSet, IRuleSet {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * The default value of the '{@link #getSourceProjectType() <em>Source Project Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceProjectType()
     * @generated
     * @ordered
     */
    protected static final SourceProjectType SOURCE_PROJECT_TYPE_EDEFAULT = SourceProjectType.JAVA;

    /**
     * The cached value of the '{@link #getSourceProjectType() <em>Source Project Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceProjectType()
     * @generated
     * @ordered
     */
    protected SourceProjectType sourceProjectType = SOURCE_PROJECT_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceLanguage() <em>Source Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceLanguage()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceLanguage() <em>Source Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceLanguage()
     * @generated
     * @ordered
     */
    protected String sourceLanguage = SOURCE_LANGUAGE_EDEFAULT;

    /**
     * The cached value of the '{@link #getSourceProjects() <em>Source Projects</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceProjects()
     * @generated
     * @ordered
     */
    protected EList<String> sourceProjects;

    /**
     * The default value of the '{@link #getTargetModelURI() <em>Target Model URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetModelURI()
     * @generated
     * @ordered
     */
    protected static final String TARGET_MODEL_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetModelURI() <em>Target Model URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetModelURI()
     * @generated
     * @ordered
     */
    protected String targetModelURI = TARGET_MODEL_URI_EDEFAULT;

    /**
     * The default value of the '{@link #getMergeType() <em>Merge Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMergeType()
     * @generated
     * @ordered
     */
    protected static final String MERGE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMergeType() <em>Merge Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMergeType()
     * @generated
     * @ordered
     */
    protected String mergeType = MERGE_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isReferenceModelChange() <em>Reference Model Change</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isReferenceModelChange()
     * @generated
     * @ordered
     */
    protected static final boolean REFERENCE_MODEL_CHANGE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isReferenceModelChange() <em>Reference Model Change</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isReferenceModelChange()
     * @generated
     * @ordered
     */
    protected boolean referenceModelChange = REFERENCE_MODEL_CHANGE_EDEFAULT;

    /**
     * The default value of the '{@link #getProfileLocation() <em>Profile Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProfileLocation()
     * @generated
     * @ordered
     */
    protected static final String PROFILE_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProfileLocation() <em>Profile Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProfileLocation()
     * @generated
     * @ordered
     */
    protected String profileLocation = PROFILE_LOCATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getClassRules() <em>Class Rules</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassRules()
     * @generated
     * @ordered
     */
    protected EList<ClassRule> classRules;

    /**
     * The cached value of the '{@link #getClassDiagramRule() <em>Class Diagram Rule</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassDiagramRule()
     * @generated
     * @ordered
     */
    protected ClassDiagramRule classDiagramRule;

    /**
     * The cached value of the '{@link #getSequenceDiagramRule() <em>Sequence Diagram Rule</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSequenceDiagramRule()
     * @generated
     * @ordered
     */
    protected SequenceDiagramRule sequenceDiagramRule;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MDAReverseRuleSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReverseTransformationPackage.Literals.MDA_REVERSE_RULE_SET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceProjectType getSourceProjectType() {
        return sourceProjectType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceProjectType(SourceProjectType newSourceProjectType) {
        SourceProjectType oldSourceProjectType = sourceProjectType;
        sourceProjectType = newSourceProjectType == null ? SOURCE_PROJECT_TYPE_EDEFAULT : newSourceProjectType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE, oldSourceProjectType, sourceProjectType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceLanguage(String newSourceLanguage) {
        String oldSourceLanguage = sourceLanguage;
        sourceLanguage = newSourceLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE, oldSourceLanguage, sourceLanguage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getSourceProjects() {
        if (sourceProjects == null) {
            sourceProjects = new EDataTypeUniqueEList<String>(String.class, this, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECTS);
        }
        return sourceProjects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetModelURI() {
        return targetModelURI;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetModelURI(String newTargetModelURI) {
        String oldTargetModelURI = targetModelURI;
        targetModelURI = newTargetModelURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__TARGET_MODEL_URI, oldTargetModelURI, targetModelURI));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMergeType() {
        return mergeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeType(String newMergeType) {
        String oldMergeType = mergeType;
        mergeType = newMergeType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__MERGE_TYPE, oldMergeType, mergeType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isReferenceModelChange() {
        return referenceModelChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReferenceModelChange(boolean newReferenceModelChange) {
        boolean oldReferenceModelChange = referenceModelChange;
        referenceModelChange = newReferenceModelChange;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE, oldReferenceModelChange, referenceModelChange));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getProfileLocation() {
        return profileLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProfileLocation(String newProfileLocation) {
        String oldProfileLocation = profileLocation;
        profileLocation = newProfileLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__PROFILE_LOCATION, oldProfileLocation, profileLocation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ClassRule> getClassRules() {
        if (classRules == null) {
            classRules = new EObjectContainmentEList<ClassRule>(ClassRule.class, this, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES);
        }
        return classRules;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDiagramRule getClassDiagramRule() {
        return classDiagramRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetClassDiagramRule(ClassDiagramRule newClassDiagramRule, NotificationChain msgs) {
        ClassDiagramRule oldClassDiagramRule = classDiagramRule;
        classDiagramRule = newClassDiagramRule;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE, oldClassDiagramRule, newClassDiagramRule);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClassDiagramRule(ClassDiagramRule newClassDiagramRule) {
        if (newClassDiagramRule != classDiagramRule) {
            NotificationChain msgs = null;
            if (classDiagramRule != null)
                msgs = ((InternalEObject)classDiagramRule).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE, null, msgs);
            if (newClassDiagramRule != null)
                msgs = ((InternalEObject)newClassDiagramRule).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE, null, msgs);
            msgs = basicSetClassDiagramRule(newClassDiagramRule, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE, newClassDiagramRule, newClassDiagramRule));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceDiagramRule getSequenceDiagramRule() {
        return sequenceDiagramRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSequenceDiagramRule(SequenceDiagramRule newSequenceDiagramRule, NotificationChain msgs) {
        SequenceDiagramRule oldSequenceDiagramRule = sequenceDiagramRule;
        sequenceDiagramRule = newSequenceDiagramRule;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE, oldSequenceDiagramRule, newSequenceDiagramRule);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSequenceDiagramRule(SequenceDiagramRule newSequenceDiagramRule) {
        if (newSequenceDiagramRule != sequenceDiagramRule) {
            NotificationChain msgs = null;
            if (sequenceDiagramRule != null)
                msgs = ((InternalEObject)sequenceDiagramRule).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE, null, msgs);
            if (newSequenceDiagramRule != null)
                msgs = ((InternalEObject)newSequenceDiagramRule).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE, null, msgs);
            msgs = basicSetSequenceDiagramRule(newSequenceDiagramRule, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE, newSequenceDiagramRule, newSequenceDiagramRule));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES:
                return ((InternalEList<?>)getClassRules()).basicRemove(otherEnd, msgs);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE:
                return basicSetClassDiagramRule(null, msgs);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE:
                return basicSetSequenceDiagramRule(null, msgs);
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
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE:
                return getSourceProjectType();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE:
                return getSourceLanguage();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECTS:
                return getSourceProjects();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__TARGET_MODEL_URI:
                return getTargetModelURI();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__MERGE_TYPE:
                return getMergeType();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE:
                return isReferenceModelChange();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__PROFILE_LOCATION:
                return getProfileLocation();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES:
                return getClassRules();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE:
                return getClassDiagramRule();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE:
                return getSequenceDiagramRule();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE:
                setSourceProjectType((SourceProjectType)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE:
                setSourceLanguage((String)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECTS:
                getSourceProjects().clear();
                getSourceProjects().addAll((Collection<? extends String>)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__TARGET_MODEL_URI:
                setTargetModelURI((String)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__MERGE_TYPE:
                setMergeType((String)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE:
                setReferenceModelChange((Boolean)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__PROFILE_LOCATION:
                setProfileLocation((String)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES:
                getClassRules().clear();
                getClassRules().addAll((Collection<? extends ClassRule>)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE:
                setClassDiagramRule((ClassDiagramRule)newValue);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE:
                setSequenceDiagramRule((SequenceDiagramRule)newValue);
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
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE:
                setSourceProjectType(SOURCE_PROJECT_TYPE_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE:
                setSourceLanguage(SOURCE_LANGUAGE_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECTS:
                getSourceProjects().clear();
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__TARGET_MODEL_URI:
                setTargetModelURI(TARGET_MODEL_URI_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__MERGE_TYPE:
                setMergeType(MERGE_TYPE_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE:
                setReferenceModelChange(REFERENCE_MODEL_CHANGE_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__PROFILE_LOCATION:
                setProfileLocation(PROFILE_LOCATION_EDEFAULT);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES:
                getClassRules().clear();
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE:
                setClassDiagramRule((ClassDiagramRule)null);
                return;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE:
                setSequenceDiagramRule((SequenceDiagramRule)null);
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
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE:
                return sourceProjectType != SOURCE_PROJECT_TYPE_EDEFAULT;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE:
                return SOURCE_LANGUAGE_EDEFAULT == null ? sourceLanguage != null : !SOURCE_LANGUAGE_EDEFAULT.equals(sourceLanguage);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SOURCE_PROJECTS:
                return sourceProjects != null && !sourceProjects.isEmpty();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__TARGET_MODEL_URI:
                return TARGET_MODEL_URI_EDEFAULT == null ? targetModelURI != null : !TARGET_MODEL_URI_EDEFAULT.equals(targetModelURI);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__MERGE_TYPE:
                return MERGE_TYPE_EDEFAULT == null ? mergeType != null : !MERGE_TYPE_EDEFAULT.equals(mergeType);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE:
                return referenceModelChange != REFERENCE_MODEL_CHANGE_EDEFAULT;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__PROFILE_LOCATION:
                return PROFILE_LOCATION_EDEFAULT == null ? profileLocation != null : !PROFILE_LOCATION_EDEFAULT.equals(profileLocation);
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_RULES:
                return classRules != null && !classRules.isEmpty();
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE:
                return classDiagramRule != null;
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE:
                return sequenceDiagramRule != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sourceProjectType: ");
        result.append(sourceProjectType);
        result.append(", sourceLanguage: ");
        result.append(sourceLanguage);
        result.append(", sourceProjects: ");
        result.append(sourceProjects);
        result.append(", targetModelURI: ");
        result.append(targetModelURI);
        result.append(", mergeType: ");
        result.append(mergeType);
        result.append(", referenceModelChange: ");
        result.append(referenceModelChange);
        result.append(", profileLocation: ");
        result.append(profileLocation);
        result.append(')');
        return result.toString();
    }

    /** 검증 여부 플래그 */
    boolean validated = false;
    
    /**
     * @see nexcore.tool.mda.model.common.IRuleSet#setValidated(boolean)
     */
    @Override
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    /**
     * @see nexcore.tool.mda.model.common.IRuleSet#isValidated()
     */
    @Override
    public boolean isValidated() {
        return validated;
    }

} //MDAReverseRuleSetImpl
