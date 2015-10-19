/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract View</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getDescription <em>Description</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getParent <em>Parent</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getUmlModel <em>Uml Model</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor <em>Font Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontName <em>Font Name</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontStyle <em>Font Style</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize <em>Font Size</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getX <em>X</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractView#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView()
 * @model abstract="true"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : AbstractView</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface AbstractView extends Element {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_Description()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(EObject)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_Parent()
     * @model
     * @generated
     */
    EObject getParent();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractView#getParent
     * <em>Parent</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(EObject value);

    /**
     * Returns the value of the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uml Model</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Uml Model</em>' reference.
     * @see #setUmlModel(Element)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_UmlModel()
     * @model
     * @generated
     */
    Element getUmlModel();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractView#getUmlModel
     * <em>Uml Model</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Uml Model</em>' reference.
     * @see #getUmlModel()
     * @generated
     */
    void setUmlModel(Element value);

    /**
     * Returns the value of the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fill Color</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Fill Color</em>' attribute.
     * @see #isSetFillColor()
     * @see #unsetFillColor()
     * @see #setFillColor(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_FillColor()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getFillColor();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Fill Color</em>' attribute.
     * @see #isSetFillColor()
     * @see #unsetFillColor()
     * @see #getFillColor()
     * @generated
     */
    void setFillColor(String value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isSetFillColor()
     * @see #getFillColor()
     * @see #setFillColor(String)
     * @generated
     */
    void unsetFillColor();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor <em>Fill Color</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Fill Color</em>' attribute is set.
     * @see #unsetFillColor()
     * @see #getFillColor()
     * @see #setFillColor(String)
     * @generated
     */
    boolean isSetFillColor();

    /**
     * Returns the value of the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Color</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Line Color</em>' attribute.
     * @see #isSetLineColor()
     * @see #unsetLineColor()
     * @see #setLineColor(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_LineColor()
     * @model unsettable="true" dataType="org.eclipse.uml2.uml.String"
     * @generated
     */
    String getLineColor();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Line Color</em>' attribute.
     * @see #isSetLineColor()
     * @see #unsetLineColor()
     * @see #getLineColor()
     * @generated
     */
    void setLineColor(String value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isSetLineColor()
     * @see #getLineColor()
     * @see #setLineColor(String)
     * @generated
     */
    void unsetLineColor();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor <em>Line Color</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Line Color</em>' attribute is set.
     * @see #unsetLineColor()
     * @see #getLineColor()
     * @see #setLineColor(String)
     * @generated
     */
    boolean isSetLineColor();

    /**
     * Returns the value of the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Color</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Font Color</em>' attribute.
     * @see #isSetFontColor()
     * @see #unsetFontColor()
     * @see #setFontColor(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_FontColor()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getFontColor();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor <em>Font Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Font Color</em>' attribute.
     * @see #isSetFontColor()
     * @see #unsetFontColor()
     * @see #getFontColor()
     * @generated
     */
    void setFontColor(String value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor <em>Font Color</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isSetFontColor()
     * @see #getFontColor()
     * @see #setFontColor(String)
     * @generated
     */
    void unsetFontColor();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor <em>Font Color</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Font Color</em>' attribute is set.
     * @see #unsetFontColor()
     * @see #getFontColor()
     * @see #setFontColor(String)
     * @generated
     */
    boolean isSetFontColor();

    /**
     * Returns the value of the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Name</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Font Name</em>' attribute.
     * @see #setFontName(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_FontName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getFontName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontName
     * <em>Font Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Font Name</em>' attribute.
     * @see #getFontName()
     * @generated
     */
    void setFontName(String value);

    /**
     * Returns the value of the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Style</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Font Style</em>' attribute.
     * @see #setFontStyle(String)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_FontStyle()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getFontStyle();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontStyle <em>Font Style</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Font Style</em>' attribute.
     * @see #getFontStyle()
     * @generated
     */
    void setFontStyle(String value);

    /**
     * Returns the value of the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Size</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Font Size</em>' attribute.
     * @see #isSetFontSize()
     * @see #unsetFontSize()
     * @see #setFontSize(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_FontSize()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getFontSize();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize
     * <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Font Size</em>' attribute.
     * @see #isSetFontSize()
     * @see #unsetFontSize()
     * @see #getFontSize()
     * @generated
     */
    void setFontSize(int value);

    /**
     * Unsets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize
     * <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #isSetFontSize()
     * @see #getFontSize()
     * @see #setFontSize(int)
     * @generated
     */
    void unsetFontSize();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize <em>Font Size</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Font Size</em>' attribute is set.
     * @see #unsetFontSize()
     * @see #getFontSize()
     * @see #setFontSize(int)
     * @generated
     */
    boolean isSetFontSize();

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #setX(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_X()
     * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSetX()
     * @see #getX()
     * @see #setX(int)
     * @generated
     */
    void unsetX();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getX <em>X</em>}' attribute is set.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return whether the value of the '<em>X</em>' attribute is set.
     * @see #unsetX()
     * @see #getX()
     * @see #setX(int)
     * @generated
     */
    boolean isSetX();

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #setY(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractView_Y()
     * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #getY()
     * @generated
     */
    void setY(int value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSetY()
     * @see #getY()
     * @see #setY(int)
     * @generated
     */
    void unsetY();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getY <em>Y</em>}' attribute is set.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return whether the value of the '<em>Y</em>' attribute is set.
     * @see #unsetY()
     * @see #getY()
     * @see #setY(int)
     * @generated
     */
    boolean isSetY();

} // AbstractView
