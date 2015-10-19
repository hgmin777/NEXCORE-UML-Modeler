/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlglossary.impl;

import nexcore.tool.uml.model.umlglossary.Category;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage;
import nexcore.tool.uml.model.umlglossary.Word;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Word</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getSection <em>
 * Section</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getCategory <em>
 * Category</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getKoreanName
 * <em>Korean Name</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getEnglishFullName
 * <em>English Full Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getAbbreviation
 * <em>Abbreviation</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.WordImpl#getId <em>Id
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary.impl</li>
 * <li>설  명 : WordImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class WordImpl extends EObjectImpl implements Word {
    /**
     * The default value of the '{@link #getSection() <em>Section</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSection()
     * @generated
     * @ordered
     */
    protected static final String SECTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSection() <em>Section</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSection()
     * @generated
     * @ordered
     */
    protected String section = SECTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getCategory() <em>Category</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCategory()
     * @generated
     * @ordered
     */
    protected Category category;

    /**
     * The default value of the '{@link #getKoreanName() <em>Korean Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getKoreanName()
     * @generated
     * @ordered
     */
    protected static final String KOREAN_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getKoreanName() <em>Korean Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getKoreanName()
     * @generated
     * @ordered
     */
    protected String koreanName = KOREAN_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getEnglishFullName()
     * <em>English Full Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEnglishFullName()
     * @generated
     * @ordered
     */
    protected static final String ENGLISH_FULL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEnglishFullName()
     * <em>English Full Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEnglishFullName()
     * @generated
     * @ordered
     */
    protected String englishFullName = ENGLISH_FULL_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getAbbreviation()
     * <em>Abbreviation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAbbreviation()
     * @generated
     * @ordered
     */
    protected static final String ABBREVIATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAbbreviation() <em>Abbreviation</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAbbreviation()
     * @generated
     * @ordered
     */
    protected String abbreviation = ABBREVIATION_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected WordImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLGlossaryPackage.Literals.WORD;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSection() {
        return section;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSection(String newSection) {
        String oldSection = section;
        section = newSection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLGlossaryPackage.WORD__SECTION, oldSection, section));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getKoreanName() {
        return koreanName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setKoreanName(String newKoreanName) {
        String oldKoreanName = koreanName;
        koreanName = newKoreanName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UMLGlossaryPackage.WORD__KOREAN_NAME,
                oldKoreanName,
                koreanName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getEnglishFullName() {
        return englishFullName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEnglishFullName(String newEnglishFullName) {
        String oldEnglishFullName = englishFullName;
        englishFullName = newEnglishFullName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME,
                oldEnglishFullName,
                englishFullName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAbbreviation(String newAbbreviation) {
        String oldAbbreviation = abbreviation;
        abbreviation = newAbbreviation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UMLGlossaryPackage.WORD__ABBREVIATION,
                oldAbbreviation,
                abbreviation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UMLGlossaryPackage.WORD__DESCRIPTION,
                oldDescription,
                description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLGlossaryPackage.WORD__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Category getCategory() {
        if (category != null && category.eIsProxy()) {
            InternalEObject oldCategory = (InternalEObject) category;
            category = (Category) eResolveProxy(oldCategory);
            if (category != oldCategory) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this,
                        Notification.RESOLVE,
                        UMLGlossaryPackage.WORD__CATEGORY,
                        oldCategory,
                        category));
            }
        }
        return category;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Category basicGetCategory() {
        return category;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated change
     */
    public void setCategory(Category newCategory) {
        Category oldCategory = category;
        category = newCategory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UMLGlossaryPackage.WORD__CATEGORY,
                oldCategory,
                category));
        if (oldCategory != null)
            oldCategory.remove(this);
        if (category != null)
            category.add(this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLGlossaryPackage.WORD__SECTION:
                return getSection();
            case UMLGlossaryPackage.WORD__CATEGORY:
                if (resolve)
                    return getCategory();
                return basicGetCategory();
            case UMLGlossaryPackage.WORD__KOREAN_NAME:
                return getKoreanName();
            case UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME:
                return getEnglishFullName();
            case UMLGlossaryPackage.WORD__ABBREVIATION:
                return getAbbreviation();
            case UMLGlossaryPackage.WORD__DESCRIPTION:
                return getDescription();
            case UMLGlossaryPackage.WORD__ID:
                return getId();
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
            case UMLGlossaryPackage.WORD__SECTION:
                setSection((String) newValue);
                return;
            case UMLGlossaryPackage.WORD__CATEGORY:
                setCategory((Category) newValue);
                return;
            case UMLGlossaryPackage.WORD__KOREAN_NAME:
                setKoreanName((String) newValue);
                return;
            case UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME:
                setEnglishFullName((String) newValue);
                return;
            case UMLGlossaryPackage.WORD__ABBREVIATION:
                setAbbreviation((String) newValue);
                return;
            case UMLGlossaryPackage.WORD__DESCRIPTION:
                setDescription((String) newValue);
                return;
            case UMLGlossaryPackage.WORD__ID:
                setId((String) newValue);
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
            case UMLGlossaryPackage.WORD__SECTION:
                setSection(SECTION_EDEFAULT);
                return;
            case UMLGlossaryPackage.WORD__CATEGORY:
                setCategory((Category) null);
                return;
            case UMLGlossaryPackage.WORD__KOREAN_NAME:
                setKoreanName(KOREAN_NAME_EDEFAULT);
                return;
            case UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME:
                setEnglishFullName(ENGLISH_FULL_NAME_EDEFAULT);
                return;
            case UMLGlossaryPackage.WORD__ABBREVIATION:
                setAbbreviation(ABBREVIATION_EDEFAULT);
                return;
            case UMLGlossaryPackage.WORD__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UMLGlossaryPackage.WORD__ID:
                setId(ID_EDEFAULT);
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
            case UMLGlossaryPackage.WORD__SECTION:
                return SECTION_EDEFAULT == null ? section != null : !SECTION_EDEFAULT.equals(section);
            case UMLGlossaryPackage.WORD__CATEGORY:
                return category != null;
            case UMLGlossaryPackage.WORD__KOREAN_NAME:
                return KOREAN_NAME_EDEFAULT == null ? koreanName != null : !KOREAN_NAME_EDEFAULT.equals(koreanName);
            case UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME:
                return ENGLISH_FULL_NAME_EDEFAULT == null ? englishFullName != null
                : !ENGLISH_FULL_NAME_EDEFAULT.equals(englishFullName);
            case UMLGlossaryPackage.WORD__ABBREVIATION:
                return ABBREVIATION_EDEFAULT == null ? abbreviation != null
                : !ABBREVIATION_EDEFAULT.equals(abbreviation);
            case UMLGlossaryPackage.WORD__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UMLGlossaryPackage.WORD__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
        result.append(" (section: ");
        result.append(section);
        result.append(", koreanName: ");
        result.append(koreanName);
        result.append(", englishFullName: ");
        result.append(englishFullName);
        result.append(", abbreviation: ");
        result.append(abbreviation);
        result.append(", description: ");
        result.append(description);
        result.append(", id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // WordImpl
