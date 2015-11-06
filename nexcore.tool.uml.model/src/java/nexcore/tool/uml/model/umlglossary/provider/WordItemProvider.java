/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlglossary.provider;

import java.util.Collection;
import java.util.List;

import nexcore.tool.uml.model.umlglossary.Category;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage;
import nexcore.tool.uml.model.umlglossary.Word;
import nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link nexcore.tool.uml.model.umlglossary.Word} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated changed
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary.provider</li>
 * <li>설  명 : WordItemProvider</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class WordItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
IStructuredItemContentProvider, ITableItemLabelProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource, IItemColorProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WordItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addSectionPropertyDescriptor(object);
            addCategoryPropertyDescriptor(object);
            addKoreanNamePropertyDescriptor(object);
            addEnglishFullNamePropertyDescriptor(object);
            addAbbreviationPropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addIdPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Section feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSectionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_section_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_section_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__SECTION,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the Category feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCategoryPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_category_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_category_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__CATEGORY,
            true,
            false,
            true,
            null,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the Korean Name feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addKoreanNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_koreanName_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_koreanName_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__KOREAN_NAME,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the English Full Name feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEnglishFullNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_englishFullName_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_englishFullName_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__ENGLISH_FULL_NAME,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the Abbreviation feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAbbreviationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_abbreviation_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_abbreviation_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__ABBREVIATION,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the Description feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_description_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_description_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__DESCRIPTION,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This adds a property descriptor for the Id feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIdPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Word_id_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Word_id_feature", "_UI_Word_type"),
            UMLGlossaryPackage.Literals.WORD__ID,
            true,
            false,
            false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
            null,
            null));
    }

    /**
     * This returns Word.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/changed/Word"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated changed
     */
    @Override
    public String getText(Object object) {
        String label = ((Word) object).getKoreanName();
        return label == null ? "" : label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to
     * update any cached children and by creating a viewer notification, which
     * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Word.class)) {
            case UMLGlossaryPackage.WORD__SECTION:
            case UMLGlossaryPackage.WORD__KOREAN_NAME:
            case UMLGlossaryPackage.WORD__ENGLISH_FULL_NAME:
            case UMLGlossaryPackage.WORD__ABBREVIATION:
            case UMLGlossaryPackage.WORD__DESCRIPTION:
            case UMLGlossaryPackage.WORD__ID:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return UMLGlossaryEditPlugin.INSTANCE;
    }

    /**
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object object, int columnIndex) {
        switch (columnIndex) {
            // case 0:
            // return getText(object);
            case 0: {
                // return ((Word) object).getSection();
                GlossaryImpl glossary = (GlossaryImpl) ((EObject) object).eContainer();
                Word word;
                for (int i = 0; i < glossary.getWords().size(); i++) {
                    word = glossary.getWords().get(i);
                    if (word == object) {
                        return new Integer(i + 1).toString();
                    }
                }
                return "";
            }
            case 1:
                Category category = ((Word) object).getCategory();
                if (category != null) {
                    return category.getName();
                } else {
                    return Category.NONE_CATEGORY;
                }
            case 2:
                return ((Word) object).getKoreanName();
            case 3:
                return ((Word) object).getEnglishFullName();
            case 4:
                return ((Word) object).getAbbreviation();
            case 5:
                return ((Word) object).getDescription();
        }
        return getText(object);
    }

    /**
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getColumnImage(java.lang.Object, int)
     */
    public Object getColumnImage(Object object, int columnIndex) {
        // return getColumnText(object, columnIndex) != null && columnIndex < 1
        // ? getImage(object)
        // : null;
        return columnIndex < 1 ? getImage(object) : null;
    }
}
