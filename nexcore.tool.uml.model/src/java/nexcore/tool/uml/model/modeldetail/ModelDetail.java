/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.modeldetail;

import org.eclipse.emf.ecore.EAnnotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Model Detail</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.modeldetail.ModelDetail#getModelType <em>
 * Model Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.modeldetail.ModelDetailPackage#getModelDetail()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.modeldetail</li>
 * <li>설  명 : ModelDetail</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ModelDetail extends EAnnotation {
    /**
     * Returns the value of the '<em><b>Model Type</b></em>' attribute. The
     * default value is <code>"General"</code>. The literals are from the
     * enumeration {@link nexcore.tool.uml.model.modeldetail.ModelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Type</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Model Type</em>' attribute.
     * @see nexcore.tool.uml.model.modeldetail.ModelType
     * @see #setModelType(ModelType)
     * @see nexcore.tool.uml.model.modeldetail.ModelDetailPackage#getModelDetail_ModelType()
     * @model default="General" required="true"
     * @generated
     */
    ModelType getModelType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.modeldetail.ModelDetail#getModelType
     * <em>Model Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Model Type</em>' attribute.
     * @see nexcore.tool.uml.model.modeldetail.ModelType
     * @see #getModelType()
     * @generated
     */
    void setModelType(ModelType value);

} // ModelDetail
