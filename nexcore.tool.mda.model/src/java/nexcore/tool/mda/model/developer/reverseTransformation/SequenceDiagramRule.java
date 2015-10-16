/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Diagram Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#isCreateVOPCYN <em>Create VOPCYN</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#getMessageCallDepth <em>Message Call Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getSequenceDiagramRule()
 * @model
 * @generated
 */
public interface SequenceDiagramRule extends DiagramRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Create VOPCYN</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create VOPCYN</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Create VOPCYN</em>' attribute.
     * @see #setCreateVOPCYN(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getSequenceDiagramRule_CreateVOPCYN()
     * @model default="false" required="true"
     * @generated
     */
    boolean isCreateVOPCYN();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#isCreateVOPCYN <em>Create VOPCYN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Create VOPCYN</em>' attribute.
     * @see #isCreateVOPCYN()
     * @generated
     */
    void setCreateVOPCYN(boolean value);

    /**
     * Returns the value of the '<em><b>Message Call Depth</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Call Depth</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Call Depth</em>' attribute.
     * @see #setMessageCallDepth(int)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getSequenceDiagramRule_MessageCallDepth()
     * @model default="1" required="true"
     * @generated
     */
    int getMessageCallDepth();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#getMessageCallDepth <em>Message Call Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Call Depth</em>' attribute.
     * @see #getMessageCallDepth()
     * @generated
     */
    void setMessageCallDepth(int value);

} // SequenceDiagramRule
