/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getBasePackage <em>Base Package</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getPackageDepth <em>Package Depth</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#isCreateYN <em>Create YN</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getDiagramRule()
 * @model
 * @generated
 */
public interface DiagramRule extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Base Package</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Base Package</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Base Package</em>' attribute.
     * @see #setBasePackage(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getDiagramRule_BasePackage()
     * @model default="" required="true"
     * @generated
     */
    String getBasePackage();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getBasePackage <em>Base Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Base Package</em>' attribute.
     * @see #getBasePackage()
     * @generated
     */
    void setBasePackage(String value);

    /**
     * Returns the value of the '<em><b>Package Depth</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Package Depth</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Package Depth</em>' attribute.
     * @see #setPackageDepth(int)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getDiagramRule_PackageDepth()
     * @model default="1" required="true"
     * @generated
     */
    int getPackageDepth();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getPackageDepth <em>Package Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Package Depth</em>' attribute.
     * @see #getPackageDepth()
     * @generated
     */
    void setPackageDepth(int value);

    /**
     * Returns the value of the '<em><b>Create YN</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create YN</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Create YN</em>' attribute.
     * @see #setCreateYN(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getDiagramRule_CreateYN()
     * @model default="false" required="true"
     * @generated
     */
    boolean isCreateYN();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#isCreateYN <em>Create YN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Create YN</em>' attribute.
     * @see #isCreateYN()
     * @generated
     */
    void setCreateYN(boolean value);

} // DiagramRule
