/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlglossary;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Model Element</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getModelElement()
 * @model abstract="true"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary</li>
 * <li>설  명 : ModelElement</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ModelElement extends ENamedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model ordered="false"
     * @generated
     */
    EAnnotation addEAnnotation(String source);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void addEAnnotationDetail(EAnnotation eAnnotation, String key, String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String getEAnnotationDetail(EAnnotation eAnnotation, String key);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void setAnnotationDetail(EAnnotation eAnnotation, String key, String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void removeEAnnotationDetail(EAnnotation eAnnotation, String key);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    EAnnotation getEAnnotation(EStructuralFeature source);

} // ModelElement
