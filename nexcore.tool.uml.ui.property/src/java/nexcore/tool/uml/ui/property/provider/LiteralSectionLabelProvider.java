/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.provider;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : LiteralSectionLabelProvider</li>
 * <li>작성일 : 2011. 5. 27.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class LiteralSectionLabelProvider extends ColumnLabelProvider {

    /** Adapter factory label provider. */
    private static AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /** columnNumber */
    private int columnNumber;

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;
    
    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 2;
    
    /** _SPECIFICATION_COLUMN */
    private static final int _SPECIFICATION_COLUMN = 3;

    /**
     * @param columnNumber
     */
    public LiteralSectionLabelProvider(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof EnumerationLiteral) {
            EnumerationLiteral enumerationLiteral = (EnumerationLiteral) element;
            switch (columnNumber) {
                case _NAME_COLUMN:
                    return getTextOfName(enumerationLiteral);
                case _DEFAULT_VALUE_COLUMN:
                    return getTextOfDefaultValue(enumerationLiteral);
                case _SPECIFICATION_COLUMN:
                    return getTextOfSpecification(enumerationLiteral);
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
                return adapterFactoryLabelProvider.getImage(element);
        }
        return null;
    }

    /**
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
     * 
     * @param property
     * @return String
     */
    private String getTextOfDefaultValue(final NamedElement element) {

        EnumerationLiteral literal = null;
        ValueSpecification spec = null;

        if (element instanceof EnumerationLiteral) {
            literal = (EnumerationLiteral) element;
        }

        spec = literal.getSpecification();
        if (null == spec) {
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }

        if (spec instanceof LiteralBoolean) {
            LiteralBoolean literalBoolean = (LiteralBoolean) spec;
            return String.valueOf(literalBoolean.isValue());

        } else if (spec instanceof LiteralInteger) {
            LiteralInteger literalInteger = (LiteralInteger) spec;
            return String.valueOf(literalInteger.getValue());

        } else if (spec instanceof LiteralString) {
            LiteralString literalString = (LiteralString) spec;
            return String.valueOf(literalString.getValue());

        } else if (spec instanceof LiteralUnlimitedNatural) {
            LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural) spec;
            return String.valueOf(literalUnlimitedNatural.getValue());

        } else {
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

        }
    }
    /**
     * 
     * @param property
     * @return String
     */
    private String getTextOfSpecification(final NamedElement element) {

        EnumerationLiteral literal = null;
        ValueSpecification spec = null;

        if (element instanceof EnumerationLiteral) {
            literal = (EnumerationLiteral) element;
        }

        spec = literal.getSpecification();
        if (null == spec) {
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }
        if (spec instanceof LiteralBoolean) {
            return "Boolean";

        } else if (spec instanceof LiteralInteger) {
            return "Integer";

        } else if (spec instanceof LiteralString) {
            return UICoreConstant.STRING_LITERAL;

        } else if (spec instanceof LiteralUnlimitedNatural) {
            return "UnlimitedNatural";

        } else {
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

        }
    }

}
