/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager</li>
 * <li>설 명 : UMLManager</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class UMLManager {

    /** */
    private static final String UML_MODEL_ATTRIBUTE_NAME = "umlModel";

    /** NAME_INDEX */
    public static int NAME_INDEX = 1;

    /**
     * getNodeTypeFromUML
     *  
     * @param classType
     * @return NodeType
     */
    @SuppressWarnings("unchecked")
    public static NodeType getNodeTypeFromUML(Class classType) {
        if (classType.equals(org.eclipse.uml2.uml.ActivityFinalNode.class)) {
            return NodeType.ACTIVITY_FINAL_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.CentralBufferNode.class)) {
            return NodeType.CENTRAL_BUFFER_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.DataStoreNode.class)) {
            return NodeType.DATA_STORE_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.DecisionNode.class)) {
            return NodeType.DECISION_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.ForkNode.class)) {
            return NodeType.FORK_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.InitialNode.class)) {
            return NodeType.INITIAL_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.JoinNode.class)) {
            return NodeType.JOIN_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.MergeNode.class)) {
            return NodeType.MERGE_NODE;
        }
        if (classType.equals(org.eclipse.uml2.uml.OpaqueAction.class)) {
            return NodeType.OPAQUE_ACTION;
        }
        if (classType.equals(org.eclipse.uml2.uml.Artifact.class)) {
            return NodeType.ARTIFACT;
        }
        if (classType.equals(org.eclipse.uml2.uml.Class.class)) {
            return NodeType.CLASS;
        }
        if (classType.equals(org.eclipse.uml2.uml.Collaboration.class)) {
            return NodeType.COLLABORATION;
        }
        if (classType.equals(org.eclipse.uml2.uml.DataType.class)) {
            return NodeType.DATA_TYPE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Enumeration.class)) {
            return NodeType.ENUMERATION;
        }
        if (classType.equals(org.eclipse.uml2.uml.Interface.class)) {
            return NodeType.INTERFACE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Package.class)) {
            return NodeType.PACKAGE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Component.class)) {
            return NodeType.COMPONENT;
        }
        if (classType.equals(org.eclipse.uml2.uml.DestructionEvent.class)) {
            return NodeType.DESTRUCTION_EVENT;
        }
        if (classType.equals(org.eclipse.uml2.uml.InteractionUse.class)) {
            return NodeType.INTERACTION_USE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Lifeline.class)) {
            return NodeType.LIFELINE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Message.class)) {
            return NodeType.MESSAGE;
        }
        if (classType.equals(org.eclipse.uml2.uml.StateInvariant.class)) {
            return NodeType.STATE_INVARIANT;
        }
        if (classType.equals(org.eclipse.uml2.uml.Transition.class)) {
            return NodeType.TRANSITION;
        }
        if (classType.equals(org.eclipse.uml2.uml.CollaborationUse.class)) {
            return NodeType.COLLABORATION_USE;
        }
        if (classType.equals(org.eclipse.uml2.uml.Property.class)) {
            return NodeType.PROPERTY;
        }
        if (classType.equals(org.eclipse.uml2.uml.Actor.class)) {
            return NodeType.ACTOR;
        }
        if (classType.equals(org.eclipse.uml2.uml.UseCase.class)) {
            return NodeType.USE_CASE;
        }
        if (classType.equals(org.eclipse.uml2.uml.FinalState.class)) {
            return NodeType.FINAL_STATE;
        }
        return NodeType.NOTE;

    }

    // public static String getPackagedUniqueName(org.eclipse.uml2.uml.Package
    // parentPackage, String name) {
    // int index = 1;
    // String newName;
    // while (true) {
    // newName = name + Integer.toString(index++);
    // if (null == parentPackage.getMember(newName)) {
    // return newName;
    // }
    // if (10000 <= index) {
    // return name + Integer.toString(NAME_INDEX++);
    // }
    // }
    //
    // }

    /**
     * getPackagedUniqueName
     *  
     * @param parentPackage
     * @param name
     * @return String
     */
    public static String getPackagedUniqueName(org.eclipse.uml2.uml.Namespace parentPackage, String name) {
        int index = 1;
        String newName;
        while (true) {
            newName = name + Integer.toString(index++);
            if (null == parentPackage.getOwnedMember(newName)) {
                return newName;
            }
            if (10000 <= index) {
                return name + Integer.toString(NAME_INDEX++);
            }
        }

    }

    /**
     * 
     * @param diagramName
     * @param parent
     * @return String
     */
    public static String getDiagramUniqueName(String diagramName, Element parent) {
        int uniqueIndex = 0;
        uniqueIndex = getUniqueIndex(uniqueIndex, diagramName, parent);
        if (uniqueIndex == 0) {
            return diagramName;
        } else {
            return diagramName + uniqueIndex;
        }
    }

    /**
     * 
     * 
     * @param uniqueIndex
     * @param diagramName
     * @param parent
     * @return int
     */
    private static int getUniqueIndex(int uniqueIndex, String diagramName, Element parent) {

        EAnnotation diagramAnnotation = parent.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);
        if (diagramAnnotation == null) {
            return 0;
        }
        for (EObject child : diagramAnnotation.getContents()) {
            if (child instanceof Diagram) {

                Diagram diagram = (Diagram) child;
                if (uniqueIndex == 0) {
                    if (diagram.getName().equals(diagramName)) {
                        uniqueIndex++;
                        uniqueIndex = getUniqueIndex(uniqueIndex, diagramName, parent);
                    }
                } else {
                    if (diagram.getName().equals(diagramName + uniqueIndex)) {
                        uniqueIndex++;
                        uniqueIndex = getUniqueIndex(uniqueIndex, diagramName, parent);
                    }
                }
            }
        }
        return uniqueIndex;
    }
    
    /**
     * 
     * 
     * @param element
     * @return org.eclipse.uml2.uml.Package
     */
    public static org.eclipse.uml2.uml.Package getParentPackage(PackageableElement element) {
        return element.getNearestPackage();
    }

    /**
     * View model의 부모 반환
     * 
     * @param element
     * @return org.eclipse.uml2.uml.PackageableElement
     */
    public static org.eclipse.uml2.uml.PackageableElement getParentPackage(AbstractView element) {
        org.eclipse.uml2.uml.PackageableElement packageableElement = getParent(element);
        if (null == packageableElement) {
            return null;
        }

        if (packageableElement instanceof org.eclipse.uml2.uml.Package
            || packageableElement instanceof org.eclipse.uml2.uml.Component) {
            return packageableElement;
        } else {
            return (org.eclipse.uml2.uml.PackageableElement) getParentPackage(packageableElement);
        }
    }

    /**
     * getParent
     *  
     * @param eAnnotation
     * @return org.eclipse.uml2.uml.PackageableElement
     */
    public static org.eclipse.uml2.uml.PackageableElement getParent(EAnnotation eAnnotation) {
        if (null == eAnnotation) {
            return null;
        }
        EObject parent = eAnnotation.eContainer();
        if (parent instanceof PackageableElement) {
            return (org.eclipse.uml2.uml.PackageableElement) parent;
        }
        return null;
    }

    /**
     * 
     * 
     * @param element
     * @return org.eclipse.uml2.uml.PackageableElement
     */
    public static org.eclipse.uml2.uml.PackageableElement getParent(Element element) {
        if (null == element) {
            return null;
        }
        Element parentElement = element.getOwner();
        if (null != parentElement) {
            return (org.eclipse.uml2.uml.PackageableElement) parentElement;
        }

        EAnnotation eAnnotation = null;
        if (element.eContainer() instanceof EAnnotation) {
            eAnnotation = (EAnnotation) element.eContainer();
        }

        if (eAnnotation != null) {
            EObject eObject = eAnnotation.eContainer();
            if (eObject instanceof org.eclipse.uml2.uml.PackageableElement) {
                return (org.eclipse.uml2.uml.PackageableElement) eObject;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 모델요소 삭제 UMLelement사용
     * 
     * @param child
     *            void
     */
    public static void deleteChild(Element child) {
        Element parentElement = child.getOwner();

        if (null != parentElement) {
            parentElement.getOwnedElements().remove(child);
        } else {
            EAnnotation eAnnotation = (EAnnotation) child.eContainer();
            eAnnotation.getContents().remove(child);
        }
    }

    /**
     * 모델요소 삭제 UMLelement사용
     * 
     * @param childrun
     *            void
     */
    public static void deleteChildren(Element[] childrun) {
        for (Element element : childrun) {
            deleteChild(element);
        }
    }

    /**
     * 
     * 
     * @param element
     *            void
     */
    public static void deleteElement(EObject element) {
        deleteElement((Element) element);
    }

    /**
     * 모델요소 삭제 ECoreUtil사용
     * 
     * @param element
     *            void
     */
    public static void deleteElement(Element element) {
        if (null == element) {
            return;
        }
        UMLManager.clearStereotype(element);
        EcoreUtil.remove(element);
    }

    /**
     * 
     * 
     * @param elements
     *            void
     */
    public static void deleteElements(EObject[] elements) {
        deleteElements((Element[]) elements);
    }

    /**
     * 모델요소 삭제 ECoreUtil사용
     * 
     * @param elements
     *            void
     */
    public static void deleteElements(Element[] elements) {
        for (Element element : elements) {
            deleteElement(element);
        }
    }

    /**
     * dependency 설정
     * 
     * @param dependency
     * @param supplier
     * @param client
     *            void
     */
    public static void setDependency(org.eclipse.uml2.uml.Dependency dependency, NamedElement supplier,
                                     NamedElement client) {
        dependency.getSuppliers().add(supplier);
        dependency.getClients().add(client);
        client.getClientDependencies().add(dependency);
        org.eclipse.uml2.uml.Package packageElement = UMLManager.getParentPackage((PackageableElement) client);
        packageElement.getPackagedElements().add(dependency);

        if (client instanceof Component && supplier instanceof Interface) {
            ((Component) client).getClientDependencies().add(dependency);
        }
    }

    /**
     * dependency 설정
     * 
     * @param dependency
     * @param supplier
     * @param client
     *            void
     */
    public static void setExtendDependency(org.eclipse.uml2.uml.Extend extend, org.eclipse.uml2.uml.UseCase supplier,
                                           org.eclipse.uml2.uml.UseCase client, ExtensionPoint newExtensionPoint) {
        ExtensionPoint extensionPoint;
        String extensionPointName;
        if (null == newExtensionPoint) {
            extensionPointName = UMLMessage.getMessage(UMLMessage.UML_EXTENSIONPOINT);

            extensionPoint = supplier.createExtensionPoint(getPackagedUniqueName(supplier, extensionPointName));
        } else {
            extensionPoint = newExtensionPoint;
        }

        extend.setExtendedCase(client);
        extend.getExtensionLocations().add(extensionPoint);
        client.getExtends().add(extend);
        supplier.getExtensionPoints().add(extensionPoint);

    }

    /**
     * dependency 설정
     * 
     * @param dependency
     * @param supplier
     * @param client
     *            void
     */
    public static void unsetExtendDependency(org.eclipse.uml2.uml.Extend extend) {
        for (ExtensionPoint extensionPoint : extend.getExtensionLocations()) {
            deleteElement(extensionPoint);
        }
        deleteElement(extend);

    }

    /**
     * dependency 설정
     * 
     * @param dependency
     * @param supplier
     * @param client
     *            void
     */
    public static void setIncludeDependency(org.eclipse.uml2.uml.Include include,
                                            org.eclipse.uml2.uml.UseCase supplier, org.eclipse.uml2.uml.UseCase client) {
        include.setAddition(supplier);
        client.getIncludes().add(include);
    }

    /**
     * unsetIncludeDependency
     *  
     * @param include void
     */
    public static void unsetIncludeDependency(org.eclipse.uml2.uml.Include include) {
        deleteElement(include);
    }

    /**
     * dependency 설정지움
     * 
     * @param dependency
     *            void
     */
    public static void unsetDependency(org.eclipse.uml2.uml.Dependency dependency) {
        NamedElement client;
        client = dependency.getClients().get(0);
        client.getClientDependencies().remove(dependency);
        org.eclipse.uml2.uml.Package packageElement = client.getNearestPackage();
        packageElement.getPackagedElements().remove(dependency);
        deleteElement(dependency);
    }

    /**
     * Association설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setAssociationForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                             boolean isNevigableSupplier,
                                                             AggregationKind aggregationKindSupplier,
                                                             NamedElement client, boolean isNevigableClient,
                                                             AggregationKind aggregationKindClient) {
        Property supplierProperty = UMLHelper.createProperty();
        Property clientProperty = UMLHelper.createProperty();
        supplierProperty.setType((Type) client);
        clientProperty.setType((Type) supplier);

        clientProperty.setName(supplier.getName().toLowerCase());
        supplierProperty.setName(client.getName().toLowerCase());

        supplierProperty.setAggregation(aggregationKindSupplier);
        clientProperty.setAggregation(aggregationKindClient);

        supplierProperty.setAssociation(umlModel);
        clientProperty.setAssociation(umlModel);

        if (isNevigableSupplier) {
            setProperty(supplier, supplierProperty);
        } else {
            umlModel.getOwnedEnds().add(supplierProperty);
        }
        if (isNevigableClient) {
            setProperty(client, clientProperty);
        } else {
            umlModel.getOwnedEnds().add(clientProperty);
        }

        org.eclipse.uml2.uml.Package packageElement = client.getNearestPackage();
        if (client instanceof Component) {
            ((Component) client).getPackagedElements().add(umlModel);
        } else {
            packageElement.getPackagedElements().add(umlModel);
        }

    }

    /**
     * setProperty
     *  
     * @param parent
     * @param property void
     */
    private static void setProperty(NamedElement parent, Property property) {
        if (parent instanceof StructuredClassifier) {
            ((StructuredClassifier) parent).getOwnedAttributes().add(property);
        } else if (parent instanceof Interface) {
            ((Interface) parent).getOwnedAttributes().add(property);
        } else if (parent instanceof Artifact) {
            ((Artifact) parent).getOwnedAttributes().add(property);
        } else if (parent instanceof DataType) {
            ((DataType) parent).getOwnedAttributes().add(property);
        } else if (parent instanceof Signal) {
            ((Signal) parent).getOwnedAttributes().add(property);
        }
    }

    /**
     * Association설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setAssociationForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * Association설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setDirectedAssociationForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            false,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * Aggregation설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setAggregationForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.SHARED_LITERAL);

    }

    /**
     * Aggregation설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setDirectedAggregationForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            false,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.SHARED_LITERAL);

    }

    /**
     * Composition설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setCompositionForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.COMPOSITE_LITERAL);
    }

    /**
     * Composition설정
     * 
     * @param relationType
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param client
     * @param isNevigableClient
     *            void
     */
    public static void setDirectedCompositionForStructuredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForStructuredClassifier(umlModel,
            supplier,
            false,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.COMPOSITE_LITERAL);
    }

    /**
     * Actor와 UseCase Association
     * 
     * @param umlModel
     * @param supplier
     * @param isNavigableSupplier
     * @param aggregationKindSupplier
     * @param client
     * @param isNavigableClient
     * @param aggregationKindClient
     *            void
     */
    public static void setAssociationForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                             boolean isNavigableSupplier,
                                                             AggregationKind aggregationKindSupplier,
                                                             NamedElement client, boolean isNavigableClient,
                                                             AggregationKind aggregationKindClient) {
        Property supplierProperty = UMLHelper.createProperty();
        Property clientProperty = UMLHelper.createProperty();

        supplierProperty.setType((Type) supplier);
        clientProperty.setType((Type) client);

        supplierProperty.setAggregation(aggregationKindSupplier);
        clientProperty.setAggregation(aggregationKindClient);

        supplierProperty.setAssociation(umlModel);
        clientProperty.setAssociation(umlModel);

        umlModel.getOwnedEnds().add(supplierProperty);
        umlModel.getOwnedEnds().add(clientProperty);
        if (isNavigableSupplier) {
            umlModel.getNavigableOwnedEnds().add(supplierProperty);
        }
        if (isNavigableClient) {
            umlModel.getNavigableOwnedEnds().add(clientProperty);
        }

        if (client instanceof Actor || client instanceof UseCase || supplier instanceof Actor
            || supplier instanceof UseCase) {
            org.eclipse.uml2.uml.Package packageElement = supplier.getNearestPackage();
            packageElement.getPackagedElements().add(umlModel);

        } else {
            org.eclipse.uml2.uml.Package packageElement = client.getNearestPackage();
            packageElement.getPackagedElements().add(umlModel);
        }
    }

    /**
     * Actor와 UseCase Association
     * 
     * @param umlModel
     * @param supplier
     * @param isNevigableSupplier
     * @param aggregationKindSupplier
     * @param client
     * @param isNevigableClient
     * @param aggregationKindClient
     *            void
     */
    public static void setAssociationForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.NONE_LITERAL,
            client,
            true,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * setAggregationForBehavioredClassifier
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setAggregationForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.SHARED_LITERAL,
            client,
            true,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * setCompositionForBehavioredClassifier
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setCompositionForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                             NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.COMPOSITE_LITERAL,
            client,
            true,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * setDirectedAssociationForBehavioredClassifier
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setDirectedAssociationForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.NONE_LITERAL,
            client,
            false,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * setDirectedAggregationForBehavioredClassifier
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setDirectedAggregationForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.SHARED_LITERAL,
            client,
            false,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * setDirectedCompositionForBehavioredClassifier
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setDirectedCompositionForBehavioredClassifier(Association umlModel, NamedElement supplier,
                                                                     NamedElement client) {
        setAssociationForBehavioredClassifier(umlModel,
            supplier,
            true,
            AggregationKind.COMPOSITE_LITERAL,
            client,
            false,
            AggregationKind.NONE_LITERAL);
    }

    /**
     * association설정지움
     * 
     * @param umlModel
     *            void
     */
    public static void unsetAssociation(Association umlModel) {
        umlModel.getNearestPackage().getPackagedElements().remove(umlModel);
        EList<Property> propertyList = umlModel.getMemberEnds();
        Property property;
        Element element;
        for (Iterator<Property> iter = propertyList.iterator(); iter.hasNext();) {
            property = iter.next();
            element = property.getOwner();
            if (!umlModel.equals(element)) {
                ((StructuredClassifier) element).getOwnedAttributes().remove(property);
                deleteElement(property);
            }
        }
        umlModel.getMemberEnds().clear();
        deleteElement(umlModel);

    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void setGeneralization(Generalization umlModel, Classifier supplier, Classifier client) {
        umlModel.setGeneral(supplier);
        client.getGeneralizations().add(umlModel);
    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void unsetGeneralization(Generalization umlModel, Classifier client) {
        client.getGeneralizations().remove(umlModel);
        deleteElement(umlModel);
    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void setRealization(Realization umlModel, NamedElement supplier, NamedElement client) {
        umlModel.getSuppliers().add(supplier);
        umlModel.getClients().add(client);
        if (umlModel instanceof ComponentRealization) {
            ComponentRealization componentRealization = (ComponentRealization) umlModel;
            // 2011-06-30 nspark
            // 이클립스 개발환경 3.4 버전에서 3.6 으로 변경시 아래 소스 코드는 수정되어야함.
            // 컴포넌트 다이어그램에서 component realization 은 사용하지 않아 아래 코드는 주석 처리함.
            // eclipse 3.4
            // componentRealization.setRealizingClassifier((Classifier) supplier);
            // eclipse 3.6
            // componentRealization.getRealizingClassifiers().add((Classifier) supplier);       
            
            Component component = (Component) client;
            component.getRealizations().add(componentRealization);
        } else if (umlModel instanceof InterfaceRealization) {
            InterfaceRealization interfaceRealization = (InterfaceRealization) umlModel;
            if (supplier instanceof Interface) {
                interfaceRealization.setContract((Interface) supplier);
            }
            BehavioredClassifier behavioredClassifier = (BehavioredClassifier) client;
            behavioredClassifier.getInterfaceRealizations().add(interfaceRealization);
        } else {
            client.getNearestPackage().getPackagedElements().add(umlModel);
        }

    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void unsetRealization(Realization umlModel, NamedElement supplier, NamedElement client) {
        ((org.eclipse.uml2.uml.Class) client).getClientDependencies().remove(umlModel);

        if (umlModel instanceof ComponentRealization) {
            ComponentRealization componentRealization = (ComponentRealization) umlModel;
            Component component = (Component) client;
            component.getRealizations().remove(componentRealization);
        } else if (umlModel instanceof InterfaceRealization) {
            InterfaceRealization interfaceRealization = (InterfaceRealization) umlModel;
            Component component = (Component) client;
            component.getInterfaceRealizations().remove(interfaceRealization);
        }
        deleteElement(umlModel);
    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void setMessage(Message umlModel, NamedElement supplier, NamedElement client) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void unsetMessage(Message umlModel, NamedElement supplier, NamedElement client) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void setControlFlow(ControlFlow umlModel, NamedElement supplier, NamedElement client) {
        Activity activity = (Activity) supplier.getOwner();
        activity.getEdges().add(umlModel);
        ((ActivityNode) supplier).getIncomings().add(umlModel);
        ((ActivityNode) client).getOutgoings().add(umlModel);
    }

    /**
     * 
     * 
     * @param umlModel
     * @param supplier
     * @param client
     *            void
     */
    public static void unsetControlFlow(Activity activity, ControlFlow umlModel, NamedElement supplier,
                                        NamedElement client) {
        EcoreUtil.remove(umlModel);
    }

    /**
     * setObjectFlow
     *  
     * @param umlModel
     * @param supplier
     * @param client void
     */
    public static void setObjectFlow(ObjectFlow umlModel, NamedElement supplier, NamedElement client) {
        Activity activity = (Activity) supplier.getOwner();
        activity.getEdges().add(umlModel);
        ((ActivityNode) supplier).getIncomings().add(umlModel);
        ((ActivityNode) client).getOutgoings().add(umlModel);
    }

    /**
     * 
     * 관계된 ViewModel찾기
     * 
     * @param sourceElement
     * @return List<Element>
     */
    @SuppressWarnings("unchecked")
    public static List<AbstractView> getRelatedViewModel(Element sourceElement) {
        Collection settings = DomainRegistry.getUMLDomain()
            .getECrossReferenceAdapter()
            .getInverseReferences(sourceElement);

        // Collection settings =
        // EcoreUtil.UsageCrossReferencer.find(sourceElement,
        // DomainRegistry.getUMLDomain()
        // .getResourceSet());
        List<AbstractView> viewModelList = new ArrayList<AbstractView>();
        EStructuralFeature.Setting setting;
        for (Iterator iter = settings.iterator(); iter.hasNext();) {
            setting = (EStructuralFeature.Setting) iter.next();
            if (setting.getEStructuralFeature().getName().equals(UML_MODEL_ATTRIBUTE_NAME)) {
                viewModelList.add((AbstractView) setting.getEObject());
            }

        }
        return viewModelList;
    }

    /**
     * 
     * 관계된 UMLModel찾기
     * 
     * @param sourceElement
     * @return List<Element>
     */
    @SuppressWarnings("unchecked")
    public static List<Element> getRelatedUMLModel(Element sourceElement) {
        Collection settingsInverse = DomainRegistry.getUMLDomain()
            .getECrossReferenceAdapter()
            .getInverseReferences(sourceElement);

        // Collection settings =
        // EcoreUtil.UsageCrossReferencer.find(sourceElement,
        // DomainRegistry.getUMLDomain()
        // .getResourceSet());
        List<Element> modelList = new ArrayList<Element>();
        EStructuralFeature.Setting setting;
        EObject eObject;
        for (Iterator iter = settingsInverse.iterator(); iter.hasNext();) {
            setting = (EStructuralFeature.Setting) iter.next();
            eObject = setting.getEObject();
            if (eObject instanceof Element
                && (!(setting.getEStructuralFeature().getName().equals(UML_MODEL_ATTRIBUTE_NAME)))) {
                if (!modelList.contains(eObject)) {
                    modelList.add((Element) eObject);
                }
            }
        }
        return modelList;
    }

    /**
     * getRelatedElement
     *  
     * @param sourceElement
     * @return List<Element>
     */
    public static List<Element> getRelatedElement(Element sourceElement) {
        Collection settingsInverse = DomainRegistry.getUMLDomain()
            .getECrossReferenceAdapter()
            .getInverseReferences(sourceElement);

        // Collection settings =
        // EcoreUtil.UsageCrossReferencer.find(sourceElement,
        // DomainRegistry.getUMLDomain()
        // .getResourceSet());
        List<Element> modelList = new ArrayList<Element>();
        EStructuralFeature.Setting setting;
        EObject eObject;
        for (Iterator iter = settingsInverse.iterator(); iter.hasNext();) {
            setting = (EStructuralFeature.Setting) iter.next();
            eObject = setting.getEObject();
            if (eObject instanceof Element) {
                if (!modelList.contains(eObject)) {
                    modelList.add((Element) eObject);
                }
            }
        }
        
        
        return modelList;
    }

    /**
     * 
     * 관계된 AssociationModel찾기
     * 
     * @param sourceElement
     * @return List<Element>
     */
    @SuppressWarnings("unchecked")
    public static List<Relationship> getRelatedRelationshipModel(Element sourceElement) {

        Collection settings = DomainRegistry.getUMLDomain()
            .getECrossReferenceAdapter()
            .getInverseReferences(sourceElement);

        List<Relationship> modelList = new ArrayList<Relationship>();
        for (Relationship relation : sourceElement.getRelationships()) {
            modelList.add(relation);
        }

        EStructuralFeature.Setting setting;
        Object object;

        EList eList;

        for (Iterator iter = settings.iterator(); iter.hasNext();) {
            setting = (EStructuralFeature.Setting) iter.next();
            if (setting instanceof EList) {
                eList = (EList) setting;
                for (int index = 0; index < eList.size(); index++) {
                    object = eList.get(index);
                    if (object instanceof EObject) {
                        addRelationShip(sourceElement, modelList, (EObject) object);
                    }
                }
            } else {
                addRelationShip(sourceElement, modelList, setting.getEObject());
            }

        }
        return modelList;
    }

    /**
     * addRelationShip
     *  
     * @param sourceElement
     * @param modelList
     * @param reference void
     */
    private static void addRelationShip(Element sourceElement, List<Relationship> modelList, EObject reference) {
        Property property;
        Association association;
        if (reference instanceof Relationship) {
            if (checkRelatedRelationship(sourceElement, (Relationship) reference)) {
                if (!modelList.contains(reference)) {
                    modelList.add((Relationship) reference);
                }
            }
        } else if (reference instanceof Property) {
            property = (Property) reference;
            association = property.getAssociation();
            if (null != association) {
                if (checkRelatedRelationship(sourceElement, association)) {
                    if (!modelList.contains(association)) {
                        modelList.add(association);
                    }
                }
            }
        }
    }

    /**
     * checkRelatedRelationship
     *  
     * @param sourceElement
     * @param relationship
     * @return boolean
     */
    private static boolean checkRelatedRelationship(Element sourceElement, Relationship relationship) {
        DirectedRelationship directedRelationship;
        Association association;
        Extend extend;
        Element memberEndFirst, memberEndSecond;
        if (null == sourceElement) {
            return false;
        }
        if (relationship instanceof Extend) {
            extend = (Extend) relationship;
            if (extend.getExtendedCase().equals(sourceElement)
                || extend.getExtensionLocations().get(0).getOwner().equals(sourceElement)) {
                return true;
            }
        } else if (relationship instanceof DirectedRelationship) {
            directedRelationship = (DirectedRelationship) relationship;
            if (directedRelationship.getTargets().contains(sourceElement)
                || directedRelationship.getSources().contains(sourceElement)) {
                return true;
            }
        } else {
            association = (Association) relationship;
            if (1 <= association.getMemberEnds().size()) {
                if (null == association.getMemberEnds().get(0).getType()) {
                    memberEndFirst = association.getMemberEnds().get(0).getOwner();
                } else {
                    memberEndFirst = association.getMemberEnds().get(0).getType();
                }
            } else {
                memberEndFirst = null;
            }
            if (2 <= association.getMemberEnds().size()) {
                if (null == association.getMemberEnds().get(1).getType()) {
                    memberEndSecond = association.getMemberEnds().get(1).getOwner();
                } else {
                    memberEndSecond = association.getMemberEnds().get(1).getType();
                }
            } else {
                memberEndSecond = null;
            }

            if (sourceElement.equals(memberEndFirst) || sourceElement.equals(memberEndSecond)) {
                return true;
            }
        }
        return false;
    }

    /**
     * clearStereotype
     *  
     * @param element void
     */
    public static void clearStereotype(Element element) {

        for (Stereotype stereotype : element.getAppliedStereotypes()) {
            element.unapplyStereotype(stereotype);
            // 스테레오 타입이 삭제되면 도구가 다시 실행되기 전까지 항목이 안보임
            // UMLManager.deleteElement(stereotype);
        }
    }

    /**
     * getRelatedActors
     *  
     * @param usecase
     * @return List<Actor>
     */
    public static List<Actor> getRelatedActors(UseCase usecase) {
        List<Actor> list = new ArrayList<Actor>();
        if (null == usecase) {
            return list;
        }
        Property property;
        Association association;
        Type type;
        List<Element> relatedUMLModel = getRelatedUMLModel(usecase);
        for (Element obj : relatedUMLModel) {
            if (obj instanceof Property) {
                property = (Property) obj;
                association = property.getAssociation();
                if (null == association) {
                    continue;
                }
                for (Property propertyEnd : association.getMemberEnds()) {
                    type = propertyEnd.getType();
                    if (type instanceof Actor) {
                        if (!list.contains(type) && !DomainUtil.isProxy(type)) {
                            list.add((Actor) type);
                        }
                    }
                }
            }
        }

        return list;
    }
}
