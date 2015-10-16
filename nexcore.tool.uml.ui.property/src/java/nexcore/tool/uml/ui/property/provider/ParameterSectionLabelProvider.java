/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.provider;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.property.type.MultiplicityType;
import nexcore.tool.uml.ui.property.type.ParameterDirectionType;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.TypedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : ParameterSectionLabelProvider</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class ParameterSectionLabelProvider extends ColumnLabelProvider {

    /** Adapter factory label provider. */
    private static AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** _SINGLE */
    private static final String _UNIQUE = String.valueOf(1);

    /** _ZERO */
    private static final String _ZERO = String.valueOf(0);

    /** SQUARE_BRAKET_LEFT */
    protected static final String SQUARE_BRAKET_LEFT = "<"; //$NON-NLS-1$

    /** SQUARE_BRAKET_RIGHT */
    protected static final String SQUARE_BRAKET_RIGHT = ">"; //$NON-NLS-1$

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /** columnNumber */
    private int columnNumber;

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _DIRECTION_COLUMN */
    private static final int _DIRECTION_COLUMN = 1;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 2;

    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 3;

    /** _TYPE_COLUMN */
    private static final int _TYPE_COLUMN = 4;

    /** _IS_ORDERED_COLUMN */
    private static final int _IS_ORDERED_COLUMN = 5;

    /** _IS_UNIQUE_COLUMN */
    private static final int _IS_UNIQUE_COLUMN = 6;

    /** _MULTIPLICITY_COLUMN */
    private static final int _MULTIPLICITY_COLUMN = 7;

    /** PARENTHESIS_LEFT */
    protected static final String PARENTHESIS_LEFT = "("; //$NON-NLS-1$

    /** PARENTHESIS_RIGHT */
    protected static final String PARENTHESIS_RIGHT = ")"; //$NON-NLS-1$

    /**
     * @param columnNumber
     */
    public ParameterSectionLabelProvider(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof Parameter) {
            switch (columnNumber) {
                case _DIRECTION_COLUMN:
                    return getTextOfDirection((Parameter) element);
                case _NAME_COLUMN:
                    return getTextOfName((Parameter) element);
                case _DEFAULT_VALUE_COLUMN:
                    return getTextOfDefaultValue((Parameter) element);
                case _TYPE_COLUMN:
                    return getTextOfType((Parameter) element);
                case _IS_ORDERED_COLUMN:
                    return getTextOfIsOrdered((Parameter) element);
                case _IS_UNIQUE_COLUMN:
                    return getTextOfIsUnique((Parameter) element);
                case _MULTIPLICITY_COLUMN:
                    return getTextOfMultiplicity((Parameter) element);
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
                return getImageOfParameter((Parameter) element);
            case _TYPE_COLUMN:
                return getImageOfType((TypedElement) element);
        }
        return null;
    }

    /**
     * 매개변수 이미지 리턴.
     * 
     * @param parameter
     * @return Image
     */
    private Image getImageOfParameter(Parameter parameter) {
        return adapterFactoryLabelProvider.getImage(parameter);
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
     * 
     * 
     * @param element
     * @return Object
     */
    private String getTextOfDirection(Parameter element) {
        if (element.getDirection().equals(ParameterDirectionKind.IN_LITERAL)) {
            return ParameterDirectionType.IN.toString();
        } else if (element.getDirection().equals(ParameterDirectionKind.INOUT_LITERAL)) {
            return ParameterDirectionType.INOUT.toString();
        } else if (element.getDirection().equals(ParameterDirectionKind.OUT_LITERAL)) {
            return ParameterDirectionType.OUT.toString();
        } else if (element.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) {
            return ParameterDirectionType.RETURN.toString();
        }
        return null;
    }

    /**
     * 고유함을 리턴.
     * 
     * @param element
     * @return String
     */
    private String getTextOfIsUnique(MultiplicityElement element) {
        if (element.isUnique()) {
            return Boolean.toString(element.isUnique());
        } else {
            return Boolean.toString(element.isUnique());
        }
    }

    /**
     * 정렬됨을 리턴.
     * 
     * @param element
     * @return String
     */
    private String getTextOfIsOrdered(MultiplicityElement element) {
        if (element.isOrdered()) {
            return Boolean.toString(element.isOrdered());
        } else {
            return Boolean.toString(element.isOrdered());
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
            }
        } else {
            return MultiplicityType.UNIQUE.toString();
        }
        return EMPTY_TEXT;
    }

    /**
     * 기본값을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getTextOfDefaultValue(Parameter parameter) {

        if (parameter.getDefault() != null)
            return parameter.getDefault();

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
     * 이미지를 리턴.
     * 
     * @param strings
     *            void
     */
    @SuppressWarnings("unused")
    private String getImage(Parameter parameter) {
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
