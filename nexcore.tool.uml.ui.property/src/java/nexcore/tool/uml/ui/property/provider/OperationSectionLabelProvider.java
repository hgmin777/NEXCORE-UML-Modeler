/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.provider;

import java.util.Iterator;

import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : OperationSectionLabelProvider</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class OperationSectionLabelProvider extends ColumnLabelProvider {

    /** Adapter factory label provider. */
    private static AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;

    /** _VISIBILITY_COLUMN */
    private static final int _VISIBILITY_COLUMN = 2;

    /** _STATIC_COLUMN */
    private static final int _STATIC_COLUMN = 3;

    /** _ABSTRACT_COLUMN */
    private static final int _ABSTRACT_COLUMN = 4;

    /** _OWNED_PARAMETER_COLUMN */
    private static final int _OWNED_PARAMETER_COLUMN = 5;

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /** columnNumber */
    private int columnNumber;

    /**
     * @param columnNumber
     */
    public OperationSectionLabelProvider(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof Operation) {
            switch (columnNumber) {
                case _NAME_COLUMN:
                    return getName((Operation) element);
                case _VISIBILITY_COLUMN:
                    return getVisibility((Operation) element);
                case _STATIC_COLUMN:
                    return getIsStatic((Operation) element);
                case _ABSTRACT_COLUMN:
                    return getIsAbstract((Operation) element);
                case _OWNED_PARAMETER_COLUMN:
                    return getOwnedParameter((Operation) element);
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
                return getImageOfOperation((Operation) element);
        }
        return null;
    }

    /**
     * 오퍼레이션 이미지 리턴.
     * 
     * @param operation
     * @return Image
     */
    private Image getImageOfOperation(Operation operation) {
        return adapterFactoryLabelProvider.getImage(operation);
    }

    /**
     * 
     * 
     * @param operation
     * @return String
     */
    private String getOwnedParameter(org.eclipse.uml2.uml.Operation operation) {
        StringBuffer sb = new StringBuffer();
        EList list = operation.getOwnedParameters();
        if (list != null) {
            for (Iterator iter = list.iterator(); iter.hasNext();) {
                Parameter p = (Parameter) iter.next();
                
                if( p == null || p.getName() == null) {
                    continue;
                }
                
                if (sb.length() > 0)
                    sb.append(",");
                sb.append(p.getName());
            }
        }
        return sb.toString();
    }

    /**
     * 추상 여부를 리턴.
     * 
     * @param operation
     * @return String
     */
    private String getIsAbstract(org.eclipse.uml2.uml.Operation operation) {
        if (operation.isAbstract())
            return Boolean.toString(operation.isAbstract());
        else
            return Boolean.toString(operation.isAbstract());
    }

    /**
     * 정적 여부를 리턴.
     * 
     * @param property
     * @return String
     */
    private String getIsStatic(org.eclipse.uml2.uml.Operation operation) {
        if (operation.isStatic())
            return Boolean.toString(operation.isStatic());
        else
            return Boolean.toString(operation.isStatic());
    }

    /**
     * 가시성을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getVisibility(org.eclipse.uml2.uml.Operation operation) {
        if (operation.getVisibility() != null)
            return operation.getVisibility().toString();
        else
            return ""; //$NON-NLS-1$
    }

    /**
     * 이름을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getName(org.eclipse.uml2.uml.Operation operation) {
        if (operation.getName() != null)
            return operation.getName().toString();
        else
            return ""; //$NON-NLS-1$
    }
}
