/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : UMLDiagramPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UMLDiagramPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "umldiagram";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/umldiagram";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "umd";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    UMLDiagramPackage eINSTANCE = nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl
     * <em>Abstract View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractView()
     * @generated
     */
    int ABSTRACT_VIEW = 2;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__EANNOTATIONS = UMLPackage.ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__OWNED_ELEMENT = UMLPackage.ELEMENT__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__OWNER = UMLPackage.ELEMENT__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__OWNED_COMMENT = UMLPackage.ELEMENT__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__ID = UMLPackage.ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__NAME = UMLPackage.ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__DESCRIPTION = UMLPackage.ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__PARENT = UMLPackage.ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__UML_MODEL = UMLPackage.ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__FILL_COLOR = UMLPackage.ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__LINE_COLOR = UMLPackage.ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__FONT_COLOR = UMLPackage.ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__FONT_NAME = UMLPackage.ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__FONT_STYLE = UMLPackage.ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__FONT_SIZE = UMLPackage.ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__X = UMLPackage.ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW__Y = UMLPackage.ELEMENT_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '<em>Abstract View</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VIEW_FEATURE_COUNT = UMLPackage.ELEMENT_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl <em>Abstract Connection</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractConnection()
     * @generated
     */
    int ABSTRACT_CONNECTION = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__EANNOTATIONS = ABSTRACT_VIEW__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__OWNED_ELEMENT = ABSTRACT_VIEW__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__OWNER = ABSTRACT_VIEW__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__OWNED_COMMENT = ABSTRACT_VIEW__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__ID = ABSTRACT_VIEW__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__NAME = ABSTRACT_VIEW__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__DESCRIPTION = ABSTRACT_VIEW__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__PARENT = ABSTRACT_VIEW__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__UML_MODEL = ABSTRACT_VIEW__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__FILL_COLOR = ABSTRACT_VIEW__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__LINE_COLOR = ABSTRACT_VIEW__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__FONT_COLOR = ABSTRACT_VIEW__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__FONT_NAME = ABSTRACT_VIEW__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__FONT_STYLE = ABSTRACT_VIEW__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__FONT_SIZE = ABSTRACT_VIEW__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__X = ABSTRACT_VIEW__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__Y = ABSTRACT_VIEW__Y;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__SOURCE = ABSTRACT_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__SOURCE_ANCHOR = ABSTRACT_VIEW_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__TARGET = ABSTRACT_VIEW_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__TARGET_ANCHOR = ABSTRACT_VIEW_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Bend Point List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__BEND_POINT_LIST = ABSTRACT_VIEW_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__LABELS = ABSTRACT_VIEW_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Relation Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION__RELATION_TYPE = ABSTRACT_VIEW_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Abstract Connection</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONNECTION_FEATURE_COUNT = ABSTRACT_VIEW_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl
     * <em>Abstract Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractNode()
     * @generated
     */
    int ABSTRACT_NODE = 1;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__EANNOTATIONS = ABSTRACT_VIEW__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__OWNED_ELEMENT = ABSTRACT_VIEW__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__OWNER = ABSTRACT_VIEW__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__OWNED_COMMENT = ABSTRACT_VIEW__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__ID = ABSTRACT_VIEW__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__NAME = ABSTRACT_VIEW__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__DESCRIPTION = ABSTRACT_VIEW__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__PARENT = ABSTRACT_VIEW__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__UML_MODEL = ABSTRACT_VIEW__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__FILL_COLOR = ABSTRACT_VIEW__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__LINE_COLOR = ABSTRACT_VIEW__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__FONT_COLOR = ABSTRACT_VIEW__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__FONT_NAME = ABSTRACT_VIEW__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__FONT_STYLE = ABSTRACT_VIEW__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__FONT_SIZE = ABSTRACT_VIEW__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__X = ABSTRACT_VIEW__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__Y = ABSTRACT_VIEW__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__WIDTH = ABSTRACT_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__HEIGHT = ABSTRACT_VIEW_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__INCOMING_CONNECTION_LIST = ABSTRACT_VIEW_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__SOURCE_ANCHOR_MAP = ABSTRACT_VIEW_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__OUTGOING_CONNECTION_LIST = ABSTRACT_VIEW_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__TARGET_ANCHOR_MAP = ABSTRACT_VIEW_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__IS_CONTAINER = ABSTRACT_VIEW_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__NODE_TYPE = ABSTRACT_VIEW_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Abstract Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_FEATURE_COUNT = ABSTRACT_VIEW_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.AttachementImpl
     * <em>Attachement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.AttachementImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAttachement()
     * @generated
     */
    int ATTACHEMENT = 3;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__EANNOTATIONS = ABSTRACT_CONNECTION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__OWNED_ELEMENT = ABSTRACT_CONNECTION__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__OWNER = ABSTRACT_CONNECTION__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__OWNED_COMMENT = ABSTRACT_CONNECTION__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__ID = ABSTRACT_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__NAME = ABSTRACT_CONNECTION__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__DESCRIPTION = ABSTRACT_CONNECTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__PARENT = ABSTRACT_CONNECTION__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__UML_MODEL = ABSTRACT_CONNECTION__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__FILL_COLOR = ABSTRACT_CONNECTION__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__LINE_COLOR = ABSTRACT_CONNECTION__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__FONT_COLOR = ABSTRACT_CONNECTION__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__FONT_NAME = ABSTRACT_CONNECTION__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__FONT_STYLE = ABSTRACT_CONNECTION__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__FONT_SIZE = ABSTRACT_CONNECTION__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__X = ABSTRACT_CONNECTION__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__Y = ABSTRACT_CONNECTION__Y;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__SOURCE = ABSTRACT_CONNECTION__SOURCE;

    /**
     * The feature id for the '<em><b>Source Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__SOURCE_ANCHOR = ABSTRACT_CONNECTION__SOURCE_ANCHOR;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__TARGET = ABSTRACT_CONNECTION__TARGET;

    /**
     * The feature id for the '<em><b>Target Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__TARGET_ANCHOR = ABSTRACT_CONNECTION__TARGET_ANCHOR;

    /**
     * The feature id for the '<em><b>Bend Point List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__BEND_POINT_LIST = ABSTRACT_CONNECTION__BEND_POINT_LIST;

    /**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT__LABELS = ABSTRACT_CONNECTION__LABELS;

    /**
     * The feature id for the '<em><b>Relation Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ATTACHEMENT__RELATION_TYPE = ABSTRACT_CONNECTION__RELATION_TYPE;

    /**
     * The number of structural features of the '<em>Attachement</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTACHEMENT_FEATURE_COUNT = ABSTRACT_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.BendPointImpl
     * <em>Bend Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.BendPointImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getBendPoint()
     * @generated
     */
    int BEND_POINT = 4;

    /**
     * The feature id for the '<em><b>First Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEND_POINT__FIRST_POSITION = 0;

    /**
     * The feature id for the '<em><b>Second Position</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEND_POINT__SECOND_POSITION = 1;

    /**
     * The feature id for the '<em><b>Weight</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEND_POINT__WEIGHT = 2;

    /**
     * The number of structural features of the '<em>Bend Point</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BEND_POINT_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl <em>Container Node</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getContainerNode()
     * @generated
     */
    int CONTAINER_NODE = 5;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__EANNOTATIONS = ABSTRACT_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__OWNED_ELEMENT = ABSTRACT_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__OWNER = ABSTRACT_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__OWNED_COMMENT = ABSTRACT_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__NAME = ABSTRACT_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__DESCRIPTION = ABSTRACT_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__PARENT = ABSTRACT_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__UML_MODEL = ABSTRACT_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__FILL_COLOR = ABSTRACT_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__LINE_COLOR = ABSTRACT_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__FONT_COLOR = ABSTRACT_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__FONT_NAME = ABSTRACT_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__FONT_STYLE = ABSTRACT_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__FONT_SIZE = ABSTRACT_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__X = ABSTRACT_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__Y = ABSTRACT_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__WIDTH = ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__HEIGHT = ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__INCOMING_CONNECTION_LIST = ABSTRACT_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__SOURCE_ANCHOR_MAP = ABSTRACT_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__OUTGOING_CONNECTION_LIST = ABSTRACT_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__TARGET_ANCHOR_MAP = ABSTRACT_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__IS_CONTAINER = ABSTRACT_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__NODE_TYPE = ABSTRACT_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Node List</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__NODE_LIST = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE__CONNECTION_LIST = ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Container Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.DiagramImpl <em>Diagram</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.DiagramImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagram()
     * @generated
     */
    int DIAGRAM = 6;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__EANNOTATIONS = ABSTRACT_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__OWNED_ELEMENT = ABSTRACT_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__OWNER = ABSTRACT_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__OWNED_COMMENT = ABSTRACT_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__NAME = ABSTRACT_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__DESCRIPTION = ABSTRACT_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__PARENT = ABSTRACT_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__UML_MODEL = ABSTRACT_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__FILL_COLOR = ABSTRACT_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__LINE_COLOR = ABSTRACT_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__FONT_COLOR = ABSTRACT_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__FONT_NAME = ABSTRACT_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__FONT_STYLE = ABSTRACT_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__FONT_SIZE = ABSTRACT_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__X = ABSTRACT_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__Y = ABSTRACT_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__WIDTH = ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__HEIGHT = ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__INCOMING_CONNECTION_LIST = ABSTRACT_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__SOURCE_ANCHOR_MAP = ABSTRACT_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__OUTGOING_CONNECTION_LIST = ABSTRACT_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__TARGET_ANCHOR_MAP = ABSTRACT_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__IS_CONTAINER = ABSTRACT_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__NODE_TYPE = ABSTRACT_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM__TYPE = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Node List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__NODE_LIST = ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Connection List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__CONNECTION_LIST = ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Diagram</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.DimensionImpl <em>Dimension</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.DimensionImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDimension()
     * @generated
     */
    int DIMENSION = 7;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIMENSION__WIDTH = 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIMENSION__HEIGHT = 1;

    /**
     * The number of structural features of the '<em>Dimension</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIMENSION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.MapImpl <em>Map</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.MapImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getMap()
     * @generated
     */
    int MAP = 8;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Map</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl
     * <em>Notation Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNotationNode()
     * @generated
     */
    int NOTATION_NODE = 9;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__EANNOTATIONS = ABSTRACT_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__OWNED_ELEMENT = ABSTRACT_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__OWNER = ABSTRACT_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__OWNED_COMMENT = ABSTRACT_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__NAME = ABSTRACT_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__DESCRIPTION = ABSTRACT_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__PARENT = ABSTRACT_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__UML_MODEL = ABSTRACT_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__FILL_COLOR = ABSTRACT_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__LINE_COLOR = ABSTRACT_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__FONT_COLOR = ABSTRACT_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__FONT_NAME = ABSTRACT_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__FONT_STYLE = ABSTRACT_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__FONT_SIZE = ABSTRACT_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__X = ABSTRACT_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__Y = ABSTRACT_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__WIDTH = ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__HEIGHT = ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__INCOMING_CONNECTION_LIST = ABSTRACT_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__SOURCE_ANCHOR_MAP = ABSTRACT_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__OUTGOING_CONNECTION_LIST = ABSTRACT_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__TARGET_ANCHOR_MAP = ABSTRACT_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__IS_CONTAINER = ABSTRACT_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__NODE_TYPE = ABSTRACT_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Regions</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__REGIONS = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Compartment List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE__COMPARTMENT_LIST = ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Labels</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTATION_NODE__LABELS = ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Notation Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.RelationImpl <em>Relation</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.RelationImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelation()
     * @generated
     */
    int RELATION = 10;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__EANNOTATIONS = ABSTRACT_CONNECTION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__OWNED_ELEMENT = ABSTRACT_CONNECTION__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__OWNER = ABSTRACT_CONNECTION__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__OWNED_COMMENT = ABSTRACT_CONNECTION__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__ID = ABSTRACT_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__NAME = ABSTRACT_CONNECTION__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__DESCRIPTION = ABSTRACT_CONNECTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__PARENT = ABSTRACT_CONNECTION__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__UML_MODEL = ABSTRACT_CONNECTION__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__FILL_COLOR = ABSTRACT_CONNECTION__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__LINE_COLOR = ABSTRACT_CONNECTION__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__FONT_COLOR = ABSTRACT_CONNECTION__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__FONT_NAME = ABSTRACT_CONNECTION__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__FONT_STYLE = ABSTRACT_CONNECTION__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__FONT_SIZE = ABSTRACT_CONNECTION__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__X = ABSTRACT_CONNECTION__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__Y = ABSTRACT_CONNECTION__Y;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__SOURCE = ABSTRACT_CONNECTION__SOURCE;

    /**
     * The feature id for the '<em><b>Source Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__SOURCE_ANCHOR = ABSTRACT_CONNECTION__SOURCE_ANCHOR;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__TARGET = ABSTRACT_CONNECTION__TARGET;

    /**
     * The feature id for the '<em><b>Target Anchor</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__TARGET_ANCHOR = ABSTRACT_CONNECTION__TARGET_ANCHOR;

    /**
     * The feature id for the '<em><b>Bend Point List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__BEND_POINT_LIST = ABSTRACT_CONNECTION__BEND_POINT_LIST;

    /**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__LABELS = ABSTRACT_CONNECTION__LABELS;

    /**
     * The feature id for the '<em><b>Relation Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION__RELATION_TYPE = ABSTRACT_CONNECTION__RELATION_TYPE;

    /**
     * The number of structural features of the '<em>Relation</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RELATION_FEATURE_COUNT = ABSTRACT_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl <em>Life Line Node</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLifeLineNode()
     * @generated
     */
    int LIFE_LINE_NODE = 11;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__EANNOTATIONS = NOTATION_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__OWNED_ELEMENT = NOTATION_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__OWNER = NOTATION_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__OWNED_COMMENT = NOTATION_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__ID = NOTATION_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__NAME = NOTATION_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__DESCRIPTION = NOTATION_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__PARENT = NOTATION_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__UML_MODEL = NOTATION_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__FILL_COLOR = NOTATION_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__LINE_COLOR = NOTATION_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__FONT_COLOR = NOTATION_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__FONT_NAME = NOTATION_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__FONT_STYLE = NOTATION_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__FONT_SIZE = NOTATION_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__X = NOTATION_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__Y = NOTATION_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__WIDTH = NOTATION_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__HEIGHT = NOTATION_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__INCOMING_CONNECTION_LIST = NOTATION_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__SOURCE_ANCHOR_MAP = NOTATION_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__OUTGOING_CONNECTION_LIST = NOTATION_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__TARGET_ANCHOR_MAP = NOTATION_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__IS_CONTAINER = NOTATION_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__NODE_TYPE = NOTATION_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Regions</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__REGIONS = NOTATION_NODE__REGIONS;

    /**
     * The feature id for the '<em><b>Compartment List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__COMPARTMENT_LIST = NOTATION_NODE__COMPARTMENT_LIST;

    /**
     * The feature id for the '<em><b>Labels</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__LABELS = NOTATION_NODE__LABELS;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__LINE = NOTATION_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Behavior List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE__BEHAVIOR_LIST = NOTATION_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Life Line Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFE_LINE_NODE_FEATURE_COUNT = NOTATION_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.impl.LineImpl <em>Line</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.impl.LineImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLine()
     * @generated
     */
    int LINE = 12;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__EANNOTATIONS = NOTATION_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__OWNED_ELEMENT = NOTATION_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__OWNER = NOTATION_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__OWNED_COMMENT = NOTATION_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__ID = NOTATION_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__NAME = NOTATION_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__DESCRIPTION = NOTATION_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__PARENT = NOTATION_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__UML_MODEL = NOTATION_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__FILL_COLOR = NOTATION_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__LINE_COLOR = NOTATION_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__FONT_COLOR = NOTATION_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__FONT_NAME = NOTATION_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__FONT_STYLE = NOTATION_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__FONT_SIZE = NOTATION_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__X = NOTATION_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__Y = NOTATION_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__WIDTH = NOTATION_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__HEIGHT = NOTATION_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__INCOMING_CONNECTION_LIST = NOTATION_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__SOURCE_ANCHOR_MAP = NOTATION_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__OUTGOING_CONNECTION_LIST = NOTATION_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__TARGET_ANCHOR_MAP = NOTATION_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__IS_CONTAINER = NOTATION_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__NODE_TYPE = NOTATION_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Regions</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__REGIONS = NOTATION_NODE__REGIONS;

    /**
     * The feature id for the '<em><b>Compartment List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE__COMPARTMENT_LIST = NOTATION_NODE__COMPARTMENT_LIST;

    /**
     * The feature id for the '<em><b>Labels</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE__LABELS = NOTATION_NODE__LABELS;

    /**
     * The number of structural features of the '<em>Line</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_FEATURE_COUNT = NOTATION_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl
     * <em>Region Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRegionType()
     * @generated
     */
    int REGION_TYPE = 13;

    /**
     * The feature id for the '<em><b>Region Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REGION_TYPE__REGION_KIND = 0;

    /**
     * The feature id for the '<em><b>Region Size</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REGION_TYPE__REGION_SIZE = 1;

    /**
     * The number of structural features of the '<em>Region Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGION_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.impl.LabelNodeImpl
     * <em>Label Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.impl.LabelNodeImpl
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLabelNode()
     * @generated
     */
    int LABEL_NODE = 14;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__EANNOTATIONS = ABSTRACT_NODE__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__OWNED_ELEMENT = ABSTRACT_NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__OWNER = ABSTRACT_NODE__OWNER;

    /**
     * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__OWNED_COMMENT = ABSTRACT_NODE__OWNED_COMMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__NAME = ABSTRACT_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__DESCRIPTION = ABSTRACT_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__PARENT = ABSTRACT_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Uml Model</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__UML_MODEL = ABSTRACT_NODE__UML_MODEL;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__FILL_COLOR = ABSTRACT_NODE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__LINE_COLOR = ABSTRACT_NODE__LINE_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__FONT_COLOR = ABSTRACT_NODE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Font Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__FONT_NAME = ABSTRACT_NODE__FONT_NAME;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__FONT_STYLE = ABSTRACT_NODE__FONT_STYLE;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__FONT_SIZE = ABSTRACT_NODE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__X = ABSTRACT_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__Y = ABSTRACT_NODE__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__WIDTH = ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__HEIGHT = ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Incoming Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__INCOMING_CONNECTION_LIST = ABSTRACT_NODE__INCOMING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__SOURCE_ANCHOR_MAP = ABSTRACT_NODE__SOURCE_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Outgoing Connection List</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__OUTGOING_CONNECTION_LIST = ABSTRACT_NODE__OUTGOING_CONNECTION_LIST;

    /**
     * The feature id for the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__TARGET_ANCHOR_MAP = ABSTRACT_NODE__TARGET_ANCHOR_MAP;

    /**
     * The feature id for the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__IS_CONTAINER = ABSTRACT_NODE__IS_CONTAINER;

    /**
     * The feature id for the '<em><b>Node Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__NODE_TYPE = ABSTRACT_NODE__NODE_TYPE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_NODE__TYPE = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Compartment List</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE__COMPARTMENT_LIST = ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Label Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.LabelType <em>Label Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.LabelType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLabelType()
     * @generated
     */
    int LABEL_TYPE = 15;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.DiagramType
     * <em>Diagram Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagramType()
     * @generated
     */
    int DIAGRAM_TYPE = 16;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.NodeType <em>Node Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNodeType()
     * @generated
     */
    int NODE_TYPE = 17;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umldiagram.RelationType
     * <em>Relation Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelationType()
     * @generated
     */
    int RELATION_TYPE = 18;

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.umldiagram.RegionEnumType <em>Region Enum Type</em>}' enum.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.RegionEnumType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRegionEnumType()
     * @generated
     */
    int REGION_ENUM_TYPE = 19;

    /**
     * The meta object id for the '<em>Connection Label Type Object</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.Enumerator
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getConnectionLabelTypeObject()
     * @generated
     */
    int CONNECTION_LABEL_TYPE_OBJECT = 20;

    /**
     * The meta object id for the '<em>Diagram Type Object</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagramTypeObject()
     * @generated
     */
    int DIAGRAM_TYPE_OBJECT = 21;

    /**
     * The meta object id for the '<em>Node Type Object</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNodeTypeObject()
     * @generated
     */
    int NODE_TYPE_OBJECT = 22;

    /**
     * The meta object id for the '<em>Relation Type Object</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelationTypeObject()
     * @generated
     */
    int RELATION_TYPE_OBJECT = 23;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection
     * <em>Abstract Connection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Abstract Connection</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection
     * @generated
     */
    EClass getAbstractConnection();

    /**
     * Returns the meta object for the reference '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getSource()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_Source();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSourceAnchor <em>Source Anchor</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Anchor</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getSourceAnchor()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_SourceAnchor();

    /**
     * Returns the meta object for the reference '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getTarget()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_Target();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTargetAnchor <em>Target Anchor</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Anchor</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getTargetAnchor()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_TargetAnchor();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getBendPointList <em>Bend Point List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Bend Point List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getBendPointList()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_BendPointList();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Labels</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getLabels()
     * @see #getAbstractConnection()
     * @generated
     */
    EReference getAbstractConnection_Labels();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType <em>Relation Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relation Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType()
     * @see #getAbstractConnection()
     * @generated
     */
    EAttribute getAbstractConnection_RelationType();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.AbstractNode <em>Abstract Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Node</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode
     * @generated
     */
    EClass getAbstractNode();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Width();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Height();

    /**
     * Returns the meta object for the reference list '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getIncomingConnectionList <em>Incoming Connection List</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Connection List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getIncomingConnectionList()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_IncomingConnectionList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getSourceAnchorMap
     * <em>Source Anchor Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Source Anchor Map</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getSourceAnchorMap()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_SourceAnchorMap();

    /**
     * Returns the meta object for the reference list '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getOutgoingConnectionList <em>Outgoing Connection List</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing Connection List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getOutgoingConnectionList()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_OutgoingConnectionList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getTargetAnchorMap
     * <em>Target Anchor Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Target Anchor Map</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getTargetAnchorMap()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_TargetAnchorMap();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#isIsContainer <em>Is Container</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Container</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#isIsContainer()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_IsContainer();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Node Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_NodeType();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.AbstractView <em>Abstract View</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract View</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView
     * @generated
     */
    EClass getAbstractView();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getId()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_Id();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getName()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_Name();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getDescription()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_Description();

    /**
     * Returns the meta object for the reference '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parent</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getParent()
     * @see #getAbstractView()
     * @generated
     */
    EReference getAbstractView_Parent();

    /**
     * Returns the meta object for the reference '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getUmlModel <em>Uml Model</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Uml Model</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getUmlModel()
     * @see #getAbstractView()
     * @generated
     */
    EReference getAbstractView_UmlModel();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor <em>Fill Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fill Color</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getFillColor()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_FillColor();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor <em>Line Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Color</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getLineColor()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_LineColor();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor <em>Font Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Color</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getFontColor()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_FontColor();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontName <em>Font Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Name</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getFontName()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_FontName();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontStyle <em>Font Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Style</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getFontStyle()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_FontStyle();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize <em>Font Size</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Size</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getFontSize()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_FontSize();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getX <em>X</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getX()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_X();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.AbstractView#getY <em>Y</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView#getY()
     * @see #getAbstractView()
     * @generated
     */
    EAttribute getAbstractView_Y();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.Attachement <em>Attachement</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Attachement</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Attachement
     * @generated
     */
    EClass getAttachement();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.BendPoint <em>Bend Point</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Bend Point</em>'.
     * @see nexcore.tool.uml.model.umldiagram.BendPoint
     * @generated
     */
    EClass getBendPoint();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getFirstPosition <em>First Position</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>First Position</em>'.
     * @see nexcore.tool.uml.model.umldiagram.BendPoint#getFirstPosition()
     * @see #getBendPoint()
     * @generated
     */
    EReference getBendPoint_FirstPosition();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getSecondPosition <em>Second Position</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Second Position</em>'.
     * @see nexcore.tool.uml.model.umldiagram.BendPoint#getSecondPosition()
     * @see #getBendPoint()
     * @generated
     */
    EReference getBendPoint_SecondPosition();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getWeight <em>Weight</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Weight</em>'.
     * @see nexcore.tool.uml.model.umldiagram.BendPoint#getWeight()
     * @see #getBendPoint()
     * @generated
     */
    EAttribute getBendPoint_Weight();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.ContainerNode <em>Container Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Container Node</em>'.
     * @see nexcore.tool.uml.model.umldiagram.ContainerNode
     * @generated
     */
    EClass getContainerNode();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.ContainerNode#getNodeList <em>Node List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.ContainerNode#getNodeList()
     * @see #getContainerNode()
     * @generated
     */
    EReference getContainerNode_NodeList();

    /**
     * Returns the meta object for the reference list '{@link nexcore.tool.uml.model.umldiagram.ContainerNode#getConnectionList <em>Connection List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Connection List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.ContainerNode#getConnectionList()
     * @see #getContainerNode()
     * @generated
     */
    EReference getContainerNode_ConnectionList();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umldiagram.Diagram <em>Diagram</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Diagram
     * @generated
     */
    EClass getDiagram();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.Diagram#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Diagram#getType()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Type();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.Diagram#getNodeList <em>Node List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Diagram#getNodeList()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_NodeList();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.Diagram#getConnectionList <em>Connection List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connection List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Diagram#getConnectionList()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_ConnectionList();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.Dimension <em>Dimension</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Dimension</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Dimension
     * @generated
     */
    EClass getDimension();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.Dimension#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Dimension#getWidth()
     * @see #getDimension()
     * @generated
     */
    EAttribute getDimension_Width();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.Dimension#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Dimension#getHeight()
     * @see #getDimension()
     * @generated
     */
    EAttribute getDimension_Height();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umldiagram.Map <em>Map</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Map</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Map
     * @generated
     */
    EClass getMap();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umldiagram.Map#getKey <em>Key</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Map#getKey()
     * @see #getMap()
     * @generated
     */
    EAttribute getMap_Key();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.Map#getValue <em>Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Map#getValue()
     * @see #getMap()
     * @generated
     */
    EAttribute getMap_Value();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.NotationNode <em>Notation Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Notation Node</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NotationNode
     * @generated
     */
    EClass getNotationNode();

    /**
     * Returns the meta object for the attribute list '{@link nexcore.tool.uml.model.umldiagram.NotationNode#getRegions <em>Regions</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Regions</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NotationNode#getRegions()
     * @see #getNotationNode()
     * @generated
     */
    EAttribute getNotationNode_Regions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umldiagram.NotationNode#getCompartmentList
     * <em>Compartment List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Compartment List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NotationNode#getCompartmentList()
     * @see #getNotationNode()
     * @generated
     */
    EReference getNotationNode_CompartmentList();

    /**
     * Returns the meta object for the reference list '{@link nexcore.tool.uml.model.umldiagram.NotationNode#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Labels</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NotationNode#getLabels()
     * @see #getNotationNode()
     * @generated
     */
    EReference getNotationNode_Labels();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.Relation <em>Relation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Relation</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Relation
     * @generated
     */
    EClass getRelation();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.LifeLineNode <em>Life Line Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Life Line Node</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LifeLineNode
     * @generated
     */
    EClass getLifeLineNode();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.uml.model.umldiagram.LifeLineNode#getLine <em>Line</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LifeLineNode#getLine()
     * @see #getLifeLineNode()
     * @generated
     */
    EReference getLifeLineNode_Line();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.uml.model.umldiagram.LifeLineNode#getBehaviorList <em>Behavior List</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Behavior List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LifeLineNode#getBehaviorList()
     * @see #getLifeLineNode()
     * @generated
     */
    EReference getLifeLineNode_BehaviorList();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umldiagram.Line <em>Line</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Line</em>'.
     * @see nexcore.tool.uml.model.umldiagram.Line
     * @generated
     */
    EClass getLine();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.RegionType <em>Region Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Region Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RegionType
     * @generated
     */
    EClass getRegionType();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionKind <em>Region Kind</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Region Kind</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RegionType#getRegionKind()
     * @see #getRegionType()
     * @generated
     */
    EAttribute getRegionType_RegionKind();

    /**
     * Returns the meta object for the reference '{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionSize <em>Region Size</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Region Size</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RegionType#getRegionSize()
     * @see #getRegionType()
     * @generated
     */
    EReference getRegionType_RegionSize();

    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.umldiagram.LabelNode <em>Label Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Node</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LabelNode
     * @generated
     */
    EClass getLabelNode();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.umldiagram.LabelNode#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LabelNode#getType()
     * @see #getLabelNode()
     * @generated
     */
    EAttribute getLabelNode_Type();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umldiagram.LabelNode#getCompartmentList
     * <em>Compartment List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Compartment List</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LabelNode#getCompartmentList()
     * @see #getLabelNode()
     * @generated
     */
    EReference getLabelNode_CompartmentList();

    /**
     * Returns the meta object for enum '{@link nexcore.tool.uml.model.umldiagram.LabelType <em>Label Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Label Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.LabelType
     * @generated
     */
    EEnum getLabelType();

    /**
     * Returns the meta object for enum '{@link nexcore.tool.uml.model.umldiagram.DiagramType <em>Diagram Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Diagram Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @generated
     */
    EEnum getDiagramType();

    /**
     * Returns the meta object for enum '{@link nexcore.tool.uml.model.umldiagram.NodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Node Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @generated
     */
    EEnum getNodeType();

    /**
     * Returns the meta object for enum '{@link nexcore.tool.uml.model.umldiagram.RelationType <em>Relation Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Relation Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @generated
     */
    EEnum getRelationType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.uml.model.umldiagram.RegionEnumType
     * <em>Region Enum Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Region Enum Type</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RegionEnumType
     * @generated
     */
    EEnum getRegionEnumType();

    /**
     * Returns the meta object for data type '{@link org.eclipse.emf.common.util.Enumerator <em>Connection Label Type Object</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for data type '<em>Connection Label Type Object</em>'.
     * @see org.eclipse.emf.common.util.Enumerator
     * @model instanceClass="org.eclipse.emf.common.util.Enumerator"
     *        extendedMetaData="name='ConnectionLabelType:Object' baseType='ConnectionLabelType'"
     * @generated
     */
    EDataType getConnectionLabelTypeObject();

    /**
     * Returns the meta object for data type '
     * {@link nexcore.tool.uml.model.umldiagram.DiagramType
     * <em>Diagram Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for data type '<em>Diagram Type Object</em>'.
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @model instanceClass="nexcore.tool.uml.model.umldiagram.DiagramType"
     *        extendedMetaData
     *        ="name='DiagramType:Object' baseType='DiagramType'"
     * @generated
     */
    EDataType getDiagramTypeObject();

    /**
     * Returns the meta object for data type '
     * {@link nexcore.tool.uml.model.umldiagram.NodeType
     * <em>Node Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for data type '<em>Node Type Object</em>'.
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @model instanceClass="nexcore.tool.uml.model.umldiagram.NodeType"
     *        extendedMetaData="name='NodeType:Object' baseType='NodeType'"
     * @generated
     */
    EDataType getNodeTypeObject();

    /**
     * Returns the meta object for data type '{@link nexcore.tool.uml.model.umldiagram.RelationType <em>Relation Type Object</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for data type '<em>Relation Type Object</em>'.
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @model instanceClass="nexcore.tool.uml.model.umldiagram.RelationType"
     *        extendedMetaData="name='RelationType:Object' baseType='RelationType'"
     * @generated
     */
    EDataType getRelationTypeObject();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UMLDiagramFactory getUMLDiagramFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl <em>Abstract Connection</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractConnection()
         * @generated
         */
        EClass ABSTRACT_CONNECTION = eINSTANCE.getAbstractConnection();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__SOURCE = eINSTANCE.getAbstractConnection_Source();

        /**
         * The meta object literal for the '<em><b>Source Anchor</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__SOURCE_ANCHOR = eINSTANCE.getAbstractConnection_SourceAnchor();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__TARGET = eINSTANCE.getAbstractConnection_Target();

        /**
         * The meta object literal for the '<em><b>Target Anchor</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__TARGET_ANCHOR = eINSTANCE.getAbstractConnection_TargetAnchor();

        /**
         * The meta object literal for the '<em><b>Bend Point List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__BEND_POINT_LIST = eINSTANCE.getAbstractConnection_BendPointList();

        /**
         * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_CONNECTION__LABELS = eINSTANCE.getAbstractConnection_Labels();

        /**
         * The meta object literal for the '<em><b>Relation Type</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_CONNECTION__RELATION_TYPE = eINSTANCE.getAbstractConnection_RelationType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl <em>Abstract Node</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractNode()
         * @generated
         */
        EClass ABSTRACT_NODE = eINSTANCE.getAbstractNode();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__WIDTH = eINSTANCE.getAbstractNode_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__HEIGHT = eINSTANCE.getAbstractNode_Height();

        /**
         * The meta object literal for the '<em><b>Incoming Connection List</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__INCOMING_CONNECTION_LIST = eINSTANCE.getAbstractNode_IncomingConnectionList();

        /**
         * The meta object literal for the '<em><b>Source Anchor Map</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__SOURCE_ANCHOR_MAP = eINSTANCE.getAbstractNode_SourceAnchorMap();

        /**
         * The meta object literal for the '<em><b>Outgoing Connection List</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__OUTGOING_CONNECTION_LIST = eINSTANCE.getAbstractNode_OutgoingConnectionList();

        /**
         * The meta object literal for the '<em><b>Target Anchor Map</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__TARGET_ANCHOR_MAP = eINSTANCE.getAbstractNode_TargetAnchorMap();

        /**
         * The meta object literal for the '<em><b>Is Container</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__IS_CONTAINER = eINSTANCE.getAbstractNode_IsContainer();

        /**
         * The meta object literal for the '<em><b>Node Type</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__NODE_TYPE = eINSTANCE.getAbstractNode_NodeType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl <em>Abstract View</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAbstractView()
         * @generated
         */
        EClass ABSTRACT_VIEW = eINSTANCE.getAbstractView();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__ID = eINSTANCE.getAbstractView_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__NAME = eINSTANCE.getAbstractView_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__DESCRIPTION = eINSTANCE.getAbstractView_Description();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_VIEW__PARENT = eINSTANCE.getAbstractView_Parent();

        /**
         * The meta object literal for the '<em><b>Uml Model</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_VIEW__UML_MODEL = eINSTANCE.getAbstractView_UmlModel();

        /**
         * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__FILL_COLOR = eINSTANCE.getAbstractView_FillColor();

        /**
         * The meta object literal for the '<em><b>Line Color</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__LINE_COLOR = eINSTANCE.getAbstractView_LineColor();

        /**
         * The meta object literal for the '<em><b>Font Color</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__FONT_COLOR = eINSTANCE.getAbstractView_FontColor();

        /**
         * The meta object literal for the '<em><b>Font Name</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__FONT_NAME = eINSTANCE.getAbstractView_FontName();

        /**
         * The meta object literal for the '<em><b>Font Style</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__FONT_STYLE = eINSTANCE.getAbstractView_FontStyle();

        /**
         * The meta object literal for the '<em><b>Font Size</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__FONT_SIZE = eINSTANCE.getAbstractView_FontSize();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__X = eINSTANCE.getAbstractView_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_VIEW__Y = eINSTANCE.getAbstractView_Y();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.AttachementImpl <em>Attachement</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.AttachementImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getAttachement()
         * @generated
         */
        EClass ATTACHEMENT = eINSTANCE.getAttachement();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.BendPointImpl <em>Bend Point</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.BendPointImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getBendPoint()
         * @generated
         */
        EClass BEND_POINT = eINSTANCE.getBendPoint();

        /**
         * The meta object literal for the '<em><b>First Position</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference BEND_POINT__FIRST_POSITION = eINSTANCE.getBendPoint_FirstPosition();

        /**
         * The meta object literal for the '<em><b>Second Position</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference BEND_POINT__SECOND_POSITION = eINSTANCE.getBendPoint_SecondPosition();

        /**
         * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute BEND_POINT__WEIGHT = eINSTANCE.getBendPoint_Weight();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl <em>Container Node</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getContainerNode()
         * @generated
         */
        EClass CONTAINER_NODE = eINSTANCE.getContainerNode();

        /**
         * The meta object literal for the '<em><b>Node List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_NODE__NODE_LIST = eINSTANCE.getContainerNode_NodeList();

        /**
         * The meta object literal for the '<em><b>Connection List</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_NODE__CONNECTION_LIST = eINSTANCE.getContainerNode_ConnectionList();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umldiagram.impl.DiagramImpl
         * <em>Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umldiagram.impl.DiagramImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagram()
         * @generated
         */
        EClass DIAGRAM = eINSTANCE.getDiagram();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__TYPE = eINSTANCE.getDiagram_Type();

        /**
         * The meta object literal for the '<em><b>Node List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__NODE_LIST = eINSTANCE.getDiagram_NodeList();

        /**
         * The meta object literal for the '<em><b>Connection List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__CONNECTION_LIST = eINSTANCE.getDiagram_ConnectionList();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umldiagram.impl.DimensionImpl
         * <em>Dimension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umldiagram.impl.DimensionImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDimension()
         * @generated
         */
        EClass DIMENSION = eINSTANCE.getDimension();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIMENSION__WIDTH = eINSTANCE.getDimension_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIMENSION__HEIGHT = eINSTANCE.getDimension_Height();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.MapImpl <em>Map</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.MapImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getMap()
         * @generated
         */
        EClass MAP = eINSTANCE.getMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute MAP__KEY = eINSTANCE.getMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute MAP__VALUE = eINSTANCE.getMap_Value();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl <em>Notation Node</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.NotationNodeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNotationNode()
         * @generated
         */
        EClass NOTATION_NODE = eINSTANCE.getNotationNode();

        /**
         * The meta object literal for the '<em><b>Regions</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTATION_NODE__REGIONS = eINSTANCE.getNotationNode_Regions();

        /**
         * The meta object literal for the '<em><b>Compartment List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference NOTATION_NODE__COMPARTMENT_LIST = eINSTANCE.getNotationNode_CompartmentList();

        /**
         * The meta object literal for the '<em><b>Labels</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference NOTATION_NODE__LABELS = eINSTANCE.getNotationNode_Labels();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umldiagram.impl.RelationImpl
         * <em>Relation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umldiagram.impl.RelationImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelation()
         * @generated
         */
        EClass RELATION = eINSTANCE.getRelation();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl <em>Life Line Node</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLifeLineNode()
         * @generated
         */
        EClass LIFE_LINE_NODE = eINSTANCE.getLifeLineNode();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference LIFE_LINE_NODE__LINE = eINSTANCE.getLifeLineNode_Line();

        /**
         * The meta object literal for the '<em><b>Behavior List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference LIFE_LINE_NODE__BEHAVIOR_LIST = eINSTANCE.getLifeLineNode_BehaviorList();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.LineImpl <em>Line</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.LineImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLine()
         * @generated
         */
        EClass LINE = eINSTANCE.getLine();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl <em>Region Type</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.RegionTypeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRegionType()
         * @generated
         */
        EClass REGION_TYPE = eINSTANCE.getRegionType();

        /**
         * The meta object literal for the '<em><b>Region Kind</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute REGION_TYPE__REGION_KIND = eINSTANCE.getRegionType_RegionKind();

        /**
         * The meta object literal for the '<em><b>Region Size</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference REGION_TYPE__REGION_SIZE = eINSTANCE.getRegionType_RegionSize();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.impl.LabelNodeImpl <em>Label Node</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.impl.LabelNodeImpl
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLabelNode()
         * @generated
         */
        EClass LABEL_NODE = eINSTANCE.getLabelNode();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_NODE__TYPE = eINSTANCE.getLabelNode_Type();

        /**
         * The meta object literal for the '<em><b>Compartment List</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference LABEL_NODE__COMPARTMENT_LIST = eINSTANCE.getLabelNode_CompartmentList();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umldiagram.LabelType
         * <em>Label Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umldiagram.LabelType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getLabelType()
         * @generated
         */
        EEnum LABEL_TYPE = eINSTANCE.getLabelType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.DiagramType <em>Diagram Type</em>}' enum.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.DiagramType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagramType()
         * @generated
         */
        EEnum DIAGRAM_TYPE = eINSTANCE.getDiagramType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.NodeType <em>Node Type</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.NodeType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNodeType()
         * @generated
         */
        EEnum NODE_TYPE = eINSTANCE.getNodeType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.RelationType <em>Relation Type</em>}' enum.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.RelationType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelationType()
         * @generated
         */
        EEnum RELATION_TYPE = eINSTANCE.getRelationType();

        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.umldiagram.RegionEnumType <em>Region Enum Type</em>}' enum.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.RegionEnumType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRegionEnumType()
         * @generated
         */
        EEnum REGION_ENUM_TYPE = eINSTANCE.getRegionEnumType();

        /**
         * The meta object literal for the '<em>Connection Label Type Object</em>' data type.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @see org.eclipse.emf.common.util.Enumerator
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getConnectionLabelTypeObject()
         * @generated
         */
        EDataType CONNECTION_LABEL_TYPE_OBJECT = eINSTANCE.getConnectionLabelTypeObject();

        /**
         * The meta object literal for the '<em>Diagram Type Object</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.DiagramType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getDiagramTypeObject()
         * @generated
         */
        EDataType DIAGRAM_TYPE_OBJECT = eINSTANCE.getDiagramTypeObject();

        /**
         * The meta object literal for the '<em>Node Type Object</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.NodeType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getNodeTypeObject()
         * @generated
         */
        EDataType NODE_TYPE_OBJECT = eINSTANCE.getNodeTypeObject();

        /**
         * The meta object literal for the '<em>Relation Type Object</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.umldiagram.RelationType
         * @see nexcore.tool.uml.model.umldiagram.impl.UMLDiagramPackageImpl#getRelationTypeObject()
         * @generated
         */
        EDataType RELATION_TYPE_OBJECT = eINSTANCE.getRelationTypeObject();

    }

} // UMLDiagramPackage
