/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.provider;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.property.type.MultiplicityType;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : AttributeSectionLabelProvider</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class AttributeSectionLabelProvider extends ColumnLabelProvider {

    /** Adapter factory label provider. */
    private static AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** _SINGLE */
    private static final String _UNIQUE = String.valueOf(1);

    /** _ZERO */
    private static final String _ZERO = String.valueOf(0);

    /** PARENTHESIS_LEFT */
    protected static final String PARENTHESIS_LEFT = "("; //$NON-NLS-1$

    /** PARENTHESIS_RIGHT */
    protected static final String PARENTHESIS_RIGHT = ")"; //$NON-NLS-1$

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /** columnNumber */
    private int columnNumber;

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;

    /** _TYPE_COLUMN */
    private static final int _TYPE_COLUMN = 2;

    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 3;

    /** _VISIBILITY_COLUMN */
    private static final int _VISIBILITY_COLUMN = 4;

    /** _STATIC_COLUMN */
    private static final int _STATIC_COLUMN = 5;

    /** _MULTIPLICITY_COLUMN */
    private static final int _MULTIPLICITY_COLUMN = 6;

    /**
     * @param columnNumber
     */
    public AttributeSectionLabelProvider(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof Property) {
            Property property = (Property) element;
            switch (columnNumber) {
                case _NAME_COLUMN:
                    return getTextOfName(property);
                case _TYPE_COLUMN:
                    return getTextOfType(property);
                case _DEFAULT_VALUE_COLUMN:
                    return getTextOfDefaultValue(property);
                case _VISIBILITY_COLUMN:
                    return getTextOfVisibility(property);
                case _STATIC_COLUMN:
                    return getTextOfIsStatic(property);
                case _MULTIPLICITY_COLUMN:
                    return getTextOfMultiplicity(property);
                default:
                    return EMPTY_TEXT;
            }
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        switch (columnNumber) {
            case _IMAGE_COLUMN:
                return getImageOfProperty((Property) element);
            case _TYPE_COLUMN:
                return getImageOfType((TypedElement) element);
        }
        return null;
    }

    /**
     * 속성 이미지 리턴.
     * 
     * @param property
     * @return Image
     */
    private Image getImageOfProperty(Property property) {
        return adapterFactoryLabelProvider.getImage(property);
    }

    /**
     * 유형 이미지 리턴.
     * 
     * @param element
     * @return Image
     */
    private Image getImageOfType(TypedElement element) {
        if (element.getType() != null) {
            return adapterFactoryLabelProvider.getImage(element.getType());
        } else {
            return null;
        }
    }

    /**
     * 다중성 유형을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfMultiplicity(MultiplicityElement multiplicityElement) {
        if (multiplicityElement.getUpperValue() != null && multiplicityElement.getLowerValue() != null) {
            if (isSingleStar(multiplicityElement)) {
                return MultiplicityType.SINGLE_STAR.toString();
            } else if (isZeroToUnique(multiplicityElement)) {
                return MultiplicityType.ZERO_TO_UNIQUE.toString();
            } else if (isUnique(multiplicityElement)) {
                return MultiplicityType.UNIQUE.toString();
            } else if (isUniqueToSingleStar(multiplicityElement)) {
                return MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString();
            } else {
                // 숫자를 직접 입력한 경우
                return multiplicityElement.getUpperValue().stringValue();
            }
        } else {
            return MultiplicityType.UNIQUE.toString();
        }
    }

    /**
     * 정적 여부를 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfIsStatic(Feature element) {
        if (element.isStatic())
            return Boolean.toString(element.isStatic());
        else
            return Boolean.toString(element.isStatic());
    }

    /**
     * 가시성을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfVisibility(NamedElement element) {
        if (element.getVisibility() != null)
            return element.getVisibility().toString();
        else
            return EMPTY_TEXT;
    }

    /**
     * 기본값을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfDefaultValue(Property property) {
        if (property.getDefault() != null)
            return property.getDefault();
        else
            return EMPTY_TEXT;
    }

    /**
     * 유형을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfType(TypedElement element) {
        if (element.getType() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(element.getType().getName());
            stringBuffer.append(PARENTHESIS_LEFT);
            stringBuffer.append(element.getType().getQualifiedName());
            stringBuffer.append(PARENTHESIS_RIGHT);
            return stringBuffer.toString();
        } else {
            return EMPTY_TEXT;
        }
    }

    /**
     * 이름을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfName(NamedElement element) {
        if (element.getName() != null)
            return element.getName().toString();
        else
            return EMPTY_TEXT;
    }

    /**
     * 다중성이 "1.. *" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isUniqueToSingleStar(MultiplicityElement element) {
        if (_UNIQUE.equals(element.getLowerValue().stringValue())) {
            if (MultiplicityType.SINGLE_STAR.toString().equals(element.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "1" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isUnique(MultiplicityElement element) {
        if (_UNIQUE.equals(element.getLowerValue().stringValue())) {
            if (_UNIQUE.equals(element.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "0.. 1" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isZeroToUnique(MultiplicityElement element) {
        if (_ZERO.equals(element.getLowerValue().stringValue())) {
            if (_UNIQUE.equals(element.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 다중성이 "*" 인지 체크.
     * 
     * @param property
     * @return boolean
     */
    private boolean isSingleStar(MultiplicityElement element) {
        if (_ZERO.equals(element.getLowerValue().stringValue())) {
            if (MultiplicityType.SINGLE_STAR.toString().equals(element.getUpperValue().stringValue())) {
                return true;
            }
        }
        return false;
    }
}
