/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.Map;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RegionEnumType;
import nexcore.tool.uml.model.umldiagram.RegionType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : UMLDiagramPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLDiagramPackageImpl extends EPackageImpl implements UMLDiagramPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractViewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass attachementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bendPointEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass containerNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass diagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass dimensionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass mapEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass notationNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass relationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass lifeLineNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass lineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass regionTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass labelNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum labelTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum diagramTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum nodeTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum relationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum regionEnumTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EDataType connectionLabelTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EDataType diagramTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EDataType nodeTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EDataType relationTypeObjectEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private UMLDiagramPackageImpl() {
        super(eNS_URI, UMLDiagramFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static UMLDiagramPackage init() {
        if (isInited) return (UMLDiagramPackage)EPackage.Registry.INSTANCE.getEPackage(UMLDiagramPackage.eNS_URI);

        // Obtain or create and register package
        UMLDiagramPackageImpl theUMLDiagramPackage = (UMLDiagramPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UMLDiagramPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UMLDiagramPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        UMLPackage.eINSTANCE.eClass();
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theUMLDiagramPackage.createPackageContents();

        // Initialize created meta-data
        theUMLDiagramPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUMLDiagramPackage.freeze();

        return theUMLDiagramPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractConnection() {
        return abstractConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_Source() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_SourceAnchor() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_Target() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_TargetAnchor() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_BendPointList() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractConnection_Labels() {
        return (EReference)abstractConnectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractConnection_RelationType() {
        return (EAttribute)abstractConnectionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractNode() {
        return abstractNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_Width() {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_Height() {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_IncomingConnectionList() {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_SourceAnchorMap() {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_OutgoingConnectionList() {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_TargetAnchorMap() {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_IsContainer() {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_NodeType() {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractView() {
        return abstractViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_Id() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_Name() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_Description() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractView_Parent() {
        return (EReference)abstractViewEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractView_UmlModel() {
        return (EReference)abstractViewEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_FillColor() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_LineColor() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_FontColor() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_FontName() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_FontStyle() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_FontSize() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_X() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractView_Y() {
        return (EAttribute)abstractViewEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getAttachement() {
        return attachementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getBendPoint() {
        return bendPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBendPoint_FirstPosition() {
        return (EReference)bendPointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getBendPoint_SecondPosition() {
        return (EReference)bendPointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBendPoint_Weight() {
        return (EAttribute)bendPointEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getContainerNode() {
        return containerNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainerNode_NodeList() {
        return (EReference)containerNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainerNode_ConnectionList() {
        return (EReference)containerNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDiagram() {
        return diagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagram_Type() {
        return (EAttribute)diagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagram_NodeList() {
        return (EReference)diagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagram_ConnectionList() {
        return (EReference)diagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDimension() {
        return dimensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDimension_Width() {
        return (EAttribute)dimensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDimension_Height() {
        return (EAttribute)dimensionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMap() {
        return mapEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMap_Key() {
        return (EAttribute)mapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMap_Value() {
        return (EAttribute)mapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getNotationNode() {
        return notationNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNotationNode_Regions() {
        return (EAttribute)notationNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getNotationNode_CompartmentList() {
        return (EReference)notationNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getNotationNode_Labels() {
        return (EReference)notationNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRelation() {
        return relationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getLifeLineNode() {
        return lifeLineNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getLifeLineNode_Line() {
        return (EReference)lifeLineNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getLifeLineNode_BehaviorList() {
        return (EReference)lifeLineNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getLine() {
        return lineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRegionType() {
        return regionTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRegionType_RegionKind() {
        return (EAttribute)regionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRegionType_RegionSize() {
        return (EReference)regionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelNode() {
        return labelNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelNode_Type() {
        return (EAttribute)labelNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelNode_CompartmentList() {
        return (EReference)labelNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getLabelType() {
        return labelTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getDiagramType() {
        return diagramTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getNodeType() {
        return nodeTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRelationType() {
        return relationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRegionEnumType() {
        return regionEnumTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType getConnectionLabelTypeObject() {
        return connectionLabelTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType getDiagramTypeObject() {
        return diagramTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType getNodeTypeObject() {
        return nodeTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType getRelationTypeObject() {
        return relationTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public UMLDiagramFactory getUMLDiagramFactory() {
        return (UMLDiagramFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        abstractConnectionEClass = createEClass(ABSTRACT_CONNECTION);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__SOURCE);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__SOURCE_ANCHOR);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__TARGET);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__TARGET_ANCHOR);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__BEND_POINT_LIST);
        createEReference(abstractConnectionEClass, ABSTRACT_CONNECTION__LABELS);
        createEAttribute(abstractConnectionEClass, ABSTRACT_CONNECTION__RELATION_TYPE);

        abstractNodeEClass = createEClass(ABSTRACT_NODE);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__WIDTH);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__HEIGHT);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__INCOMING_CONNECTION_LIST);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__SOURCE_ANCHOR_MAP);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__OUTGOING_CONNECTION_LIST);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__TARGET_ANCHOR_MAP);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__IS_CONTAINER);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__NODE_TYPE);

        abstractViewEClass = createEClass(ABSTRACT_VIEW);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__ID);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__NAME);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__DESCRIPTION);
        createEReference(abstractViewEClass, ABSTRACT_VIEW__PARENT);
        createEReference(abstractViewEClass, ABSTRACT_VIEW__UML_MODEL);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__FILL_COLOR);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__LINE_COLOR);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__FONT_COLOR);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__FONT_NAME);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__FONT_STYLE);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__FONT_SIZE);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__X);
        createEAttribute(abstractViewEClass, ABSTRACT_VIEW__Y);

        attachementEClass = createEClass(ATTACHEMENT);

        bendPointEClass = createEClass(BEND_POINT);
        createEReference(bendPointEClass, BEND_POINT__FIRST_POSITION);
        createEReference(bendPointEClass, BEND_POINT__SECOND_POSITION);
        createEAttribute(bendPointEClass, BEND_POINT__WEIGHT);

        containerNodeEClass = createEClass(CONTAINER_NODE);
        createEReference(containerNodeEClass, CONTAINER_NODE__NODE_LIST);
        createEReference(containerNodeEClass, CONTAINER_NODE__CONNECTION_LIST);

        diagramEClass = createEClass(DIAGRAM);
        createEAttribute(diagramEClass, DIAGRAM__TYPE);
        createEReference(diagramEClass, DIAGRAM__NODE_LIST);
        createEReference(diagramEClass, DIAGRAM__CONNECTION_LIST);

        dimensionEClass = createEClass(DIMENSION);
        createEAttribute(dimensionEClass, DIMENSION__WIDTH);
        createEAttribute(dimensionEClass, DIMENSION__HEIGHT);

        mapEClass = createEClass(MAP);
        createEAttribute(mapEClass, MAP__KEY);
        createEAttribute(mapEClass, MAP__VALUE);

        notationNodeEClass = createEClass(NOTATION_NODE);
        createEAttribute(notationNodeEClass, NOTATION_NODE__REGIONS);
        createEReference(notationNodeEClass, NOTATION_NODE__COMPARTMENT_LIST);
        createEReference(notationNodeEClass, NOTATION_NODE__LABELS);

        relationEClass = createEClass(RELATION);

        lifeLineNodeEClass = createEClass(LIFE_LINE_NODE);
        createEReference(lifeLineNodeEClass, LIFE_LINE_NODE__LINE);
        createEReference(lifeLineNodeEClass, LIFE_LINE_NODE__BEHAVIOR_LIST);

        lineEClass = createEClass(LINE);

        regionTypeEClass = createEClass(REGION_TYPE);
        createEAttribute(regionTypeEClass, REGION_TYPE__REGION_KIND);
        createEReference(regionTypeEClass, REGION_TYPE__REGION_SIZE);

        labelNodeEClass = createEClass(LABEL_NODE);
        createEAttribute(labelNodeEClass, LABEL_NODE__TYPE);
        createEReference(labelNodeEClass, LABEL_NODE__COMPARTMENT_LIST);

        // Create enums
        labelTypeEEnum = createEEnum(LABEL_TYPE);
        diagramTypeEEnum = createEEnum(DIAGRAM_TYPE);
        nodeTypeEEnum = createEEnum(NODE_TYPE);
        relationTypeEEnum = createEEnum(RELATION_TYPE);
        regionEnumTypeEEnum = createEEnum(REGION_ENUM_TYPE);

        // Create data types
        connectionLabelTypeObjectEDataType = createEDataType(CONNECTION_LABEL_TYPE_OBJECT);
        diagramTypeObjectEDataType = createEDataType(DIAGRAM_TYPE_OBJECT);
        nodeTypeObjectEDataType = createEDataType(NODE_TYPE_OBJECT);
        relationTypeObjectEDataType = createEDataType(RELATION_TYPE_OBJECT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
        UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        abstractConnectionEClass.getESuperTypes().add(this.getAbstractView());
        abstractNodeEClass.getESuperTypes().add(this.getAbstractView());
        abstractViewEClass.getESuperTypes().add(theUMLPackage.getElement());
        attachementEClass.getESuperTypes().add(this.getAbstractConnection());
        containerNodeEClass.getESuperTypes().add(this.getAbstractNode());
        diagramEClass.getESuperTypes().add(this.getAbstractNode());
        notationNodeEClass.getESuperTypes().add(this.getAbstractNode());
        relationEClass.getESuperTypes().add(this.getAbstractConnection());
        lifeLineNodeEClass.getESuperTypes().add(this.getNotationNode());
        lineEClass.getESuperTypes().add(this.getNotationNode());
        labelNodeEClass.getESuperTypes().add(this.getAbstractNode());

        // Initialize classes and features; add operations and parameters
        initEClass(abstractConnectionEClass, AbstractConnection.class, "AbstractConnection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractConnection_Source(), this.getAbstractView(), null, "source", null, 1, 1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractConnection_SourceAnchor(), this.getDimension(), null, "sourceAnchor", null, 0, 1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractConnection_Target(), this.getAbstractView(), null, "target", null, 1, 1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractConnection_TargetAnchor(), this.getDimension(), null, "targetAnchor", null, 0, 1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractConnection_BendPointList(), this.getBendPoint(), null, "bendPointList", null, 0, -1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractConnection_Labels(), this.getLabelNode(), null, "labels", null, 0, 5, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractConnection_RelationType(), this.getRelationType(), "relationType", null, 1, 1, AbstractConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractNodeEClass, AbstractNode.class, "AbstractNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractNode_Width(), theXMLTypePackage.getInt(), "width", "0", 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_Height(), theXMLTypePackage.getInt(), "height", "0", 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_IncomingConnectionList(), this.getAbstractConnection(), null, "incomingConnectionList", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_SourceAnchorMap(), this.getMap(), null, "sourceAnchorMap", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_OutgoingConnectionList(), this.getAbstractConnection(), null, "outgoingConnectionList", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_TargetAnchorMap(), this.getMap(), null, "targetAnchorMap", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_IsContainer(), theXMLTypePackage.getBoolean(), "isContainer", null, 1, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_NodeType(), this.getNodeType(), "nodeType", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractViewEClass, AbstractView.class, "AbstractView", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractView_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractView_Parent(), ecorePackage.getEObject(), null, "parent", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractView_UmlModel(), theUMLPackage.getElement(), null, "umlModel", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_FillColor(), theXMLTypePackage.getString(), "fillColor", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_LineColor(), theUMLPackage.getString(), "lineColor", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_FontColor(), theXMLTypePackage.getString(), "fontColor", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_FontName(), theXMLTypePackage.getString(), "fontName", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_FontStyle(), theXMLTypePackage.getString(), "fontStyle", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_FontSize(), theXMLTypePackage.getInt(), "fontSize", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_X(), theXMLTypePackage.getInt(), "x", "0", 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractView_Y(), theXMLTypePackage.getInt(), "y", "0", 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(attachementEClass, Attachement.class, "Attachement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(bendPointEClass, BendPoint.class, "BendPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBendPoint_FirstPosition(), this.getDimension(), null, "firstPosition", null, 1, 1, BendPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBendPoint_SecondPosition(), this.getDimension(), null, "secondPosition", null, 1, 1, BendPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBendPoint_Weight(), theXMLTypePackage.getFloat(), "weight", null, 0, 1, BendPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerNodeEClass, ContainerNode.class, "ContainerNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerNode_NodeList(), this.getAbstractNode(), null, "nodeList", null, 0, -1, ContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerNode_ConnectionList(), this.getAbstractConnection(), null, "connectionList", null, 0, -1, ContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDiagram_Type(), this.getDiagramType(), "type", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_NodeList(), this.getAbstractNode(), null, "nodeList", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_ConnectionList(), this.getAbstractConnection(), null, "connectionList", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dimensionEClass, Dimension.class, "Dimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDimension_Width(), theXMLTypePackage.getInt(), "width", null, 1, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDimension_Height(), theXMLTypePackage.getInt(), "height", null, 1, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mapEClass, Map.class, "Map", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMap_Key(), theXMLTypePackage.getString(), "key", null, 1, 1, Map.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMap_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, Map.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(notationNodeEClass, NotationNode.class, "NotationNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNotationNode_Regions(), this.getRelationType(), "Regions", null, 0, -1, NotationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNotationNode_CompartmentList(), this.getAbstractNode(), null, "compartmentList", null, 0, -1, NotationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNotationNode_Labels(), this.getLabelNode(), null, "labels", null, 0, -1, NotationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relationEClass, Relation.class, "Relation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(lifeLineNodeEClass, LifeLineNode.class, "LifeLineNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLifeLineNode_Line(), this.getLine(), null, "line", null, 1, 1, LifeLineNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLifeLineNode_BehaviorList(), this.getNotationNode(), null, "behaviorList", null, 0, -1, LifeLineNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lineEClass, Line.class, "Line", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(regionTypeEClass, RegionType.class, "RegionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRegionType_RegionKind(), this.getRegionEnumType(), "RegionKind", null, 1, 1, RegionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRegionType_RegionSize(), this.getDimension(), null, "RegionSize", null, 1, 1, RegionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelNodeEClass, LabelNode.class, "LabelNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelNode_Type(), this.getLabelType(), "type", null, 1, 1, LabelNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelNode_CompartmentList(), this.getLabelNode(), null, "compartmentList", null, 0, -1, LabelNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(labelTypeEEnum, LabelType.class, "LabelType");
        addEEnumLiteral(labelTypeEEnum, LabelType.LABEL);
        addEEnumLiteral(labelTypeEEnum, LabelType.SOURCE_ROLE);
        addEEnumLiteral(labelTypeEEnum, LabelType.TARGET_ROLE);
        addEEnumLiteral(labelTypeEEnum, LabelType.SOURCE_MULTIPLEX);
        addEEnumLiteral(labelTypeEEnum, LabelType.TARGET_MULTIPLEX);
        addEEnumLiteral(labelTypeEEnum, LabelType.STEREOTYPE);
        addEEnumLiteral(labelTypeEEnum, LabelType.COMPARTMENT);

        initEEnum(diagramTypeEEnum, DiagramType.class, "DiagramType");
        addEEnumLiteral(diagramTypeEEnum, DiagramType.USE_CASE_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.CLASS_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.SEQUENCE_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.COMPONENT_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.ACTIVITY_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.STATE_MACHINE_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.DEPLOYMENT_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.COMPOSITE_STRUCTURE_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.COMMUNICATION_DIAGRAM);
        addEEnumLiteral(diagramTypeEEnum, DiagramType.FREE_FORM_DIAGRAM);

        initEEnum(nodeTypeEEnum, NodeType.class, "NodeType");
        addEEnumLiteral(nodeTypeEEnum, NodeType.NOTE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.TEXT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACTIVITY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACTIVITY_PARTITION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.STRUCTURED_ACTIVITY_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COMPONENT_STRUCTURE_COMPARTMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ALTERNATIVE_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.BREAK_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LOOP_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.SEQUENCE_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.STRICT_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PARALLEL_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CRITICAL_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.NEGATIVE_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ASSERT_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.IGNORE_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CONSIDER_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OPTION_COMBINED_FRAGMENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COMBINED_FRAGMENT_NAME);
        addEEnumLiteral(nodeTypeEEnum, NodeType.INTERACTION_OPERAND);
        addEEnumLiteral(nodeTypeEEnum, NodeType.GUARD);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OPTION_IF);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ALTERNATIVE_SWITCH);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ALTERNATIVE_IF_ELSE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LOOP_WHILE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LOOP_FOR);
        addEEnumLiteral(nodeTypeEEnum, NodeType.REGION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.STATE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COMPOSITE_STRUCTURE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACCEPT_EVENT_ACTION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACTIVITY_FINAL_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACTIVITY_PARAMETER_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CALL_BEHAVIOR_ACTION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CALL_OPERATION_ACTION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CENTRAL_BUFFER_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DATA_STORE_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DECISION_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.EXPANSION_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.FLOW_FINAL_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.FORK_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.INITIAL_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.INPUT_PIN);
        addEEnumLiteral(nodeTypeEEnum, NodeType.JOIN_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.MERGE_NODE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OPAQUE_ACTION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OUTPUT_PIN);
        addEEnumLiteral(nodeTypeEEnum, NodeType.SEND_SIGNAL_ACTION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.VALUE_PIN);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ARTIFACT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CLASS);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COLLABORATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DATA_TYPE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ENUMERATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.INTERFACE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PACKAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.SIGNAL);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COMPONENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DESTRUCTION_EVENT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.INTERACTION_USE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LIFELINE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.MESSAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.SYNCHRONOUS_MESSAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ASYNCHRONOUS_MESSAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CREATE_MESSAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DESTROY_MESSAGE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PSEUDOSTATE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.CHOICE_POINT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.JUNCTION_POINT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.DEEP_HISTORY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.SHALLOW_HISTORY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.JOIN);
        addEEnumLiteral(nodeTypeEEnum, NodeType.FORK);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ENTRY_POINT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.EXIT_POINT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.TERMINATE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.FINAL_STATE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.STATE_INVARIANT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.TRANSITION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.COLLABORATION_USE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PROPERTY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PORT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ACTOR);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ELEMENT_IMPORT);
        addEEnumLiteral(nodeTypeEEnum, NodeType.GENERALIZATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.USE_CASE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LINE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LIFE_LINE_BEHAVIOR);
        addEEnumLiteral(nodeTypeEEnum, NodeType.LOLLY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.HALF_LOLLY);
        addEEnumLiteral(nodeTypeEEnum, NodeType.NAME);
        addEEnumLiteral(nodeTypeEEnum, NodeType.STEREOTYPE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ATTRIBUTES);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ATTRIBUTE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OPERATIONS);
        addEEnumLiteral(nodeTypeEEnum, NodeType.OPERATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PROVIDED_INTERFACES);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PROVIDED_INTERFACE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.REQUIRED_INTERFACES);
        addEEnumLiteral(nodeTypeEEnum, NodeType.REQUIRED_INTERFACE);
        addEEnumLiteral(nodeTypeEEnum, NodeType.REQUIRED_INTERFACE_OPERATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.PROVIDED_INTERFACE_OPERATION);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ENUMERATION_LITERALS);
        addEEnumLiteral(nodeTypeEEnum, NodeType.ENUMERATION_LITERAL);

        initEEnum(relationTypeEEnum, RelationType.class, "RelationType");
        addEEnumLiteral(relationTypeEEnum, RelationType.ATTACHEMENT);
        addEEnumLiteral(relationTypeEEnum, RelationType.ASSOCIATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.AGGREGATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.COMPOSITION);
        addEEnumLiteral(relationTypeEEnum, RelationType.DIRECTED_AGGREGATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.DIRECTED_ASSOCIATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.DIRECTED_COMPOSITION);
        addEEnumLiteral(relationTypeEEnum, RelationType.CONNECTOR);
        addEEnumLiteral(relationTypeEEnum, RelationType.CONTROL_FLOW);
        addEEnumLiteral(relationTypeEEnum, RelationType.DEPENDENCY);
        addEEnumLiteral(relationTypeEEnum, RelationType.ABSTRACTION);
        addEEnumLiteral(relationTypeEEnum, RelationType.EXTEND);
        addEEnumLiteral(relationTypeEEnum, RelationType.INCLUDE);
        addEEnumLiteral(relationTypeEEnum, RelationType.PACKAGE_IMPORT);
        addEEnumLiteral(relationTypeEEnum, RelationType.PACKAGE_MERGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.USAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.GENERALIZATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.OBJECT_FLOW);
        addEEnumLiteral(relationTypeEEnum, RelationType.REALIZATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.COMPONENT_REALIZATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.INTERFACE_REALIZATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.MESSAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.SYNCHRONOUS_MESSAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.ASYNCHRONOUS_MESSAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.CREATE_MESSAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.DESTROY_MESSAGE);
        addEEnumLiteral(relationTypeEEnum, RelationType.REPLY_MESSAGE);

        initEEnum(regionEnumTypeEEnum, RegionEnumType.class, "RegionEnumType");
        addEEnumLiteral(regionEnumTypeEEnum, RegionEnumType.ATTRIBUTE);
        addEEnumLiteral(regionEnumTypeEEnum, RegionEnumType.OPERATION);
        addEEnumLiteral(regionEnumTypeEEnum, RegionEnumType.REQUIRED_INTERFACE);
        addEEnumLiteral(regionEnumTypeEEnum, RegionEnumType.PROVIDED_INTERFACE);

        // Initialize data types
        initEDataType(connectionLabelTypeObjectEDataType, Enumerator.class, "ConnectionLabelTypeObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(diagramTypeObjectEDataType, DiagramType.class, "DiagramTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(nodeTypeObjectEDataType, NodeType.class, "NodeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(relationTypeObjectEDataType, RelationType.class, "RelationTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
        addAnnotation
          (connectionLabelTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "ConnectionLabelType:Object",
             "baseType", "ConnectionLabelType"
           });		
        addAnnotation
          (diagramTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "DiagramType:Object",
             "baseType", "DiagramType"
           });		
        addAnnotation
          (getDimension_Width(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "width"
           });		
        addAnnotation
          (getDimension_Height(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "height"
           });		
        addAnnotation
          (nodeTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "NodeType:Object",
             "baseType", "NodeType"
           });		
        addAnnotation
          (relationTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "RelationType:Object",
             "baseType", "RelationType"
           });
    }

} // UMLDiagramPackageImpl
