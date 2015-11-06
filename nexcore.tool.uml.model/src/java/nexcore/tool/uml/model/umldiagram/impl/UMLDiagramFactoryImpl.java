/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram.impl;

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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : UMLDiagramFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLDiagramFactoryImpl extends EFactoryImpl implements UMLDiagramFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public static UMLDiagramFactory init() {
        try {
            UMLDiagramFactory theUMLDiagramFactory = (UMLDiagramFactory)EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/uml/umldiagram"); 
            if (theUMLDiagramFactory != null) {
                return theUMLDiagramFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UMLDiagramFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public UMLDiagramFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case UMLDiagramPackage.ATTACHEMENT: return createAttachement();
            case UMLDiagramPackage.BEND_POINT: return createBendPoint();
            case UMLDiagramPackage.CONTAINER_NODE: return createContainerNode();
            case UMLDiagramPackage.DIAGRAM: return createDiagram();
            case UMLDiagramPackage.DIMENSION: return createDimension();
            case UMLDiagramPackage.MAP: return createMap();
            case UMLDiagramPackage.NOTATION_NODE: return createNotationNode();
            case UMLDiagramPackage.RELATION: return createRelation();
            case UMLDiagramPackage.LIFE_LINE_NODE: return createLifeLineNode();
            case UMLDiagramPackage.LINE: return createLine();
            case UMLDiagramPackage.REGION_TYPE: return createRegionType();
            case UMLDiagramPackage.LABEL_NODE: return createLabelNode();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case UMLDiagramPackage.LABEL_TYPE:
                return createLabelTypeFromString(eDataType, initialValue);
            case UMLDiagramPackage.DIAGRAM_TYPE:
                return createDiagramTypeFromString(eDataType, initialValue);
            case UMLDiagramPackage.NODE_TYPE:
                return createNodeTypeFromString(eDataType, initialValue);
            case UMLDiagramPackage.RELATION_TYPE:
                return createRelationTypeFromString(eDataType, initialValue);
            case UMLDiagramPackage.REGION_ENUM_TYPE:
                return createRegionEnumTypeFromString(eDataType, initialValue);
            case UMLDiagramPackage.CONNECTION_LABEL_TYPE_OBJECT:
                return createConnectionLabelTypeObjectFromString(eDataType, initialValue);
            case UMLDiagramPackage.DIAGRAM_TYPE_OBJECT:
                return createDiagramTypeObjectFromString(eDataType, initialValue);
            case UMLDiagramPackage.NODE_TYPE_OBJECT:
                return createNodeTypeObjectFromString(eDataType, initialValue);
            case UMLDiagramPackage.RELATION_TYPE_OBJECT:
                return createRelationTypeObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case UMLDiagramPackage.LABEL_TYPE:
                return convertLabelTypeToString(eDataType, instanceValue);
            case UMLDiagramPackage.DIAGRAM_TYPE:
                return convertDiagramTypeToString(eDataType, instanceValue);
            case UMLDiagramPackage.NODE_TYPE:
                return convertNodeTypeToString(eDataType, instanceValue);
            case UMLDiagramPackage.RELATION_TYPE:
                return convertRelationTypeToString(eDataType, instanceValue);
            case UMLDiagramPackage.REGION_ENUM_TYPE:
                return convertRegionEnumTypeToString(eDataType, instanceValue);
            case UMLDiagramPackage.CONNECTION_LABEL_TYPE_OBJECT:
                return convertConnectionLabelTypeObjectToString(eDataType, instanceValue);
            case UMLDiagramPackage.DIAGRAM_TYPE_OBJECT:
                return convertDiagramTypeObjectToString(eDataType, instanceValue);
            case UMLDiagramPackage.NODE_TYPE_OBJECT:
                return convertNodeTypeObjectToString(eDataType, instanceValue);
            case UMLDiagramPackage.RELATION_TYPE_OBJECT:
                return convertRelationTypeObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Attachement createAttachement() {
        AttachementImpl attachement = new AttachementImpl();
        return attachement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BendPoint createBendPoint() {
        BendPointImpl bendPoint = new BendPointImpl();
        return bendPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ContainerNode createContainerNode() {
        ContainerNodeImpl containerNode = new ContainerNodeImpl();
        return containerNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Diagram createDiagram() {
        DiagramImpl diagram = new DiagramImpl();
        return diagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension createDimension() {
        DimensionImpl dimension = new DimensionImpl();
        return dimension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Map createMap() {
        MapImpl map = new MapImpl();
        return map;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotationNode createNotationNode() {
        NotationNodeImpl notationNode = new NotationNodeImpl();
        return notationNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Relation createRelation() {
        RelationImpl relation = new RelationImpl();
        return relation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LifeLineNode createLifeLineNode() {
        LifeLineNodeImpl lifeLineNode = new LifeLineNodeImpl();
        return lifeLineNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Line createLine() {
        LineImpl line = new LineImpl();
        return line;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RegionType createRegionType() {
        RegionTypeImpl regionType = new RegionTypeImpl();
        return regionType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LabelNode createLabelNode() {
        LabelNodeImpl labelNode = new LabelNodeImpl();
        return labelNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LabelType createLabelTypeFromString(EDataType eDataType, String initialValue) {
        LabelType result = LabelType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertLabelTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DiagramType createDiagramTypeFromString(EDataType eDataType, String initialValue) {
        DiagramType result = DiagramType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertDiagramTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NodeType createNodeTypeFromString(EDataType eDataType, String initialValue) {
        NodeType result = NodeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertNodeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RelationType createRelationTypeFromString(EDataType eDataType, String initialValue) {
        RelationType result = RelationType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertRelationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RegionEnumType createRegionEnumTypeFromString(EDataType eDataType, String initialValue) {
        RegionEnumType result = RegionEnumType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertRegionEnumTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Enumerator createConnectionLabelTypeObjectFromString(EDataType eDataType, String initialValue) {
        return (Enumerator)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertConnectionLabelTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DiagramType createDiagramTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createDiagramTypeFromString(UMLDiagramPackage.Literals.DIAGRAM_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertDiagramTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertDiagramTypeToString(UMLDiagramPackage.Literals.DIAGRAM_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NodeType createNodeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createNodeTypeFromString(UMLDiagramPackage.Literals.NODE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertNodeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertNodeTypeToString(UMLDiagramPackage.Literals.NODE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RelationType createRelationTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createRelationTypeFromString(UMLDiagramPackage.Literals.RELATION_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertRelationTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertRelationTypeToString(UMLDiagramPackage.Literals.RELATION_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public UMLDiagramPackage getUMLDiagramPackage() {
        return (UMLDiagramPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static UMLDiagramPackage getPackage() {
        return UMLDiagramPackage.eINSTANCE;
    }

} // UMLDiagramFactoryImpl
