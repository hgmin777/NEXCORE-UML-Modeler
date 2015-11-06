/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram.util;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.Map;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RegionType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.util</li>
 * <li>설  명 : UMLDiagramAdapterFactory</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLDiagramAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static UMLDiagramPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public UMLDiagramAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = UMLDiagramPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected UMLDiagramSwitch<Adapter> modelSwitch = new UMLDiagramSwitch<Adapter>() {
            @Override
            public Adapter caseAbstractConnection(AbstractConnection object) {
                return createAbstractConnectionAdapter();
            }
            @Override
            public Adapter caseAbstractNode(AbstractNode object) {
                return createAbstractNodeAdapter();
            }
            @Override
            public Adapter caseAbstractView(AbstractView object) {
                return createAbstractViewAdapter();
            }
            @Override
            public Adapter caseAttachement(Attachement object) {
                return createAttachementAdapter();
            }
            @Override
            public Adapter caseBendPoint(BendPoint object) {
                return createBendPointAdapter();
            }
            @Override
            public Adapter caseContainerNode(ContainerNode object) {
                return createContainerNodeAdapter();
            }
            @Override
            public Adapter caseDiagram(Diagram object) {
                return createDiagramAdapter();
            }
            @Override
            public Adapter caseDimension(Dimension object) {
                return createDimensionAdapter();
            }
            @Override
            public Adapter caseMap(Map object) {
                return createMapAdapter();
            }
            @Override
            public Adapter caseNotationNode(NotationNode object) {
                return createNotationNodeAdapter();
            }
            @Override
            public Adapter caseRelation(Relation object) {
                return createRelationAdapter();
            }
            @Override
            public Adapter caseLifeLineNode(LifeLineNode object) {
                return createLifeLineNodeAdapter();
            }
            @Override
            public Adapter caseLine(Line object) {
                return createLineAdapter();
            }
            @Override
            public Adapter caseRegionType(RegionType object) {
                return createRegionTypeAdapter();
            }
            @Override
            public Adapter caseLabelNode(LabelNode object) {
                return createLabelNodeAdapter();
            }
            @Override
            public Adapter caseEModelElement(EModelElement object) {
                return createEModelElementAdapter();
            }
            @Override
            public Adapter caseElement(Element object) {
                return createElementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection <em>Abstract Connection</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.AbstractConnection
     * @generated
     */
    public Adapter createAbstractConnectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.AbstractNode <em>Abstract Node</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.AbstractNode
     * @generated
     */
    public Adapter createAbstractNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.AbstractView <em>Abstract View</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.AbstractView
     * @generated
     */
    public Adapter createAbstractViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.Attachement <em>Attachement</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Attachement
     * @generated
     */
    public Adapter createAttachementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.BendPoint <em>Bend Point</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.BendPoint
     * @generated
     */
    public Adapter createBendPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.ContainerNode <em>Container Node</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.ContainerNode
     * @generated
     */
    public Adapter createContainerNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.uml.model.umldiagram.Diagram <em>Diagram</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Diagram
     * @generated
     */
    public Adapter createDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.Dimension <em>Dimension</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Dimension
     * @generated
     */
    public Adapter createDimensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.uml.model.umldiagram.Map <em>Map</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Map
     * @generated
     */
    public Adapter createMapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.NotationNode <em>Notation Node</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.NotationNode
     * @generated
     */
    public Adapter createNotationNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.Relation <em>Relation</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Relation
     * @generated
     */
    public Adapter createRelationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.LifeLineNode <em>Life Line Node</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.LifeLineNode
     * @generated
     */
    public Adapter createLifeLineNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.uml.model.umldiagram.Line <em>Line</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.Line
     * @generated
     */
    public Adapter createLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.RegionType <em>Region Type</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.RegionType
     * @generated
     */
    public Adapter createRegionTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.uml.model.umldiagram.LabelNode <em>Label Node</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.uml.model.umldiagram.LabelNode
     * @generated
     */
    public Adapter createLabelNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.EModelElement
     * @generated
     */
    public Adapter createEModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.uml2.uml.Element <em>Element</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.uml2.uml.Element
     * @generated
     */
    public Adapter createElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // UMLDiagramAdapterFactory
