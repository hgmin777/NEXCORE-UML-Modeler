/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlglossary.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umlglossary.Category;
import nexcore.tool.uml.model.umlglossary.Glossary;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage;
import nexcore.tool.uml.model.umlglossary.Word;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Glossary</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl#getWords <em>
 * Words</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl#getCategories
 * <em>Categories</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary.impl</li>
 * <li>설  명 : GlossaryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class GlossaryImpl extends ModelElementImpl implements Glossary {
    /**
     * The cached value of the '{@link #getWords() <em>Words</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWords()
     * @generated
     * @ordered
     */
    protected EList<Word> words;

    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category> categories;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GlossaryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLGlossaryPackage.Literals.GLOSSARY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Word> getWords() {
        if (words == null) {
            words = new EObjectContainmentEList<Word>(Word.class, this, UMLGlossaryPackage.GLOSSARY__WORDS);
        }
        return words;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Category> getCategories() {
        if (categories == null) {
            categories = new EObjectContainmentEList<Category>(Category.class,
                this,
                UMLGlossaryPackage.GLOSSARY__CATEGORIES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void add(Word data) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void remove(Word data) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void add(Category data) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void remove(Category data) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLGlossaryPackage.GLOSSARY__WORDS:
                return ((InternalEList<?>) getWords()).basicRemove(otherEnd, msgs);
            case UMLGlossaryPackage.GLOSSARY__CATEGORIES:
                return ((InternalEList<?>) getCategories()).basicRemove(otherEnd, msgs);
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
            case UMLGlossaryPackage.GLOSSARY__WORDS:
                return getWords();
            case UMLGlossaryPackage.GLOSSARY__CATEGORIES:
                return getCategories();
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
            case UMLGlossaryPackage.GLOSSARY__WORDS:
                getWords().clear();
                getWords().addAll((Collection<? extends Word>) newValue);
                return;
            case UMLGlossaryPackage.GLOSSARY__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>) newValue);
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
            case UMLGlossaryPackage.GLOSSARY__WORDS:
                getWords().clear();
                return;
            case UMLGlossaryPackage.GLOSSARY__CATEGORIES:
                getCategories().clear();
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
            case UMLGlossaryPackage.GLOSSARY__WORDS:
                return words != null && !words.isEmpty();
            case UMLGlossaryPackage.GLOSSARY__CATEGORIES:
                return categories != null && !categories.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // GlossaryImpl
