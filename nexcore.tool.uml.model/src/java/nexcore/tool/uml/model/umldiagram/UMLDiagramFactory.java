/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : UMLDiagramFactory</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UMLDiagramFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    UMLDiagramFactory eINSTANCE = nexcore.tool.uml.model.umldiagram.impl.UMLDiagramFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Attachement</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Attachement</em>'.
     * @generated
     */
    Attachement createAttachement();

    /**
     * Returns a new object of class '<em>Bend Point</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Bend Point</em>'.
     * @generated
     */
    BendPoint createBendPoint();

    /**
     * Returns a new object of class '<em>Container Node</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Container Node</em>'.
     * @generated
     */
    ContainerNode createContainerNode();

    /**
     * Returns a new object of class '<em>Diagram</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Diagram</em>'.
     * @generated
     */
    Diagram createDiagram();

    /**
     * Returns a new object of class '<em>Dimension</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Dimension</em>'.
     * @generated
     */
    Dimension createDimension();

    /**
     * Returns a new object of class '<em>Map</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Map</em>'.
     * @generated
     */
    Map createMap();

    /**
     * Returns a new object of class '<em>Notation Node</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Notation Node</em>'.
     * @generated
     */
    NotationNode createNotationNode();

    /**
     * Returns a new object of class '<em>Relation</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Relation</em>'.
     * @generated
     */
    Relation createRelation();

    /**
     * Returns a new object of class '<em>Life Line Node</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Life Line Node</em>'.
     * @generated
     */
    LifeLineNode createLifeLineNode();

    /**
     * Returns a new object of class '<em>Line</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Line</em>'.
     * @generated
     */
    Line createLine();

    /**
     * Returns a new object of class '<em>Region Type</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Region Type</em>'.
     * @generated
     */
    RegionType createRegionType();

    /**
     * Returns a new object of class '<em>Label Node</em>'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return a new object of class '<em>Label Node</em>'.
     * @generated
     */
    LabelNode createLabelNode();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    UMLDiagramPackage getUMLDiagramPackage();

} // UMLDiagramFactory
