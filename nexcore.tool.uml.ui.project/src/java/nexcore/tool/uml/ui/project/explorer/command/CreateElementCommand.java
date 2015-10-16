/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.command;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.BroadcastSignalAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ClearAssociationAction;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ConditionalNode;
import org.eclipse.uml2.uml.CreateLinkAction;
import org.eclipse.uml2.uml.CreateLinkObjectAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.DestroyLinkAction;
import org.eclipse.uml2.uml.DestroyObjectAction;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.LoopNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReadExtentAction;
import org.eclipse.uml2.uml.ReadIsClassifiedObjectAction;
import org.eclipse.uml2.uml.ReadLinkAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction;
import org.eclipse.uml2.uml.ReadSelfAction;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.ReclassifyObjectAction;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TestIdentityAction;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command</li>
 * <li>설 명 : CreateElementCommand</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class CreateElementCommand extends Command {

    /** 생성을 지시한 부모 객체 */
    private EObject parent;

    /** 이 커맨드를 통해 생성한 객체 */
    private EObject newElement;

    /** 액션 ID */
    private final String actionId;

    /**
     * 생성자
     * 
     * @param label
     */
    public CreateElementCommand(EObject parent, String actionId) {
        this.parent = parent;
        this.actionId = actionId;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        // Model component
        if ("nexcore.tool.uml.ui.project.createPackage".equals(actionId)) { //$NON-NLS-1$
            Package pack = UMLHelper.createPackage();
            newElement = pack;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(pack);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(pack);
            }
            
            // Model Class component
        } else if ("nexcore.tool.uml.ui.project.createActivity".equals(actionId)) { //$NON-NLS-1$
            Activity activity = UMLHelper.createActivity();
            newElement = activity;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(activity);
            } else if (parent instanceof Class) { // Class, Component
                ((Class) parent).getOwnedBehaviors().add(activity);
            }
            // Model component interface
        } else if ("nexcore.tool.uml.ui.project.createCollaboration".equals(actionId)) { //$NON-NLS-1$
            Collaboration collaboration = UMLHelper.createCollaboration();
            newElement = collaboration;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(collaboration);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(collaboration);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getNestedClassifiers().add(collaboration);
            }
            // Model Class component interface
        } else if ("nexcore.tool.uml.ui.project.createClass".equals(actionId)) { //$NON-NLS-1$
            org.eclipse.uml2.uml.Class clazz = UMLHelper.createClass();
            newElement = clazz;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(clazz);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(clazz);
            } else if (parent instanceof Class) {
                ((Class) parent).getNestedClassifiers().add(clazz);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getNestedClassifiers().add(clazz);
            }
            
            // 유스케이스와 매핑 되어 있는 경우 해당 클래스를 자동으로 유스케이스와의 연계 생성한다.
            // TODO PNS 자동 연계 생성 (확인 창 필요)
            // createRelation(parent, clazz);
            // Model Class component
        } else if ("nexcore.tool.uml.ui.project.createInterface".equals(actionId)) { //$NON-NLS-1$
            Interface interfaze = UMLHelper.createInterface();
            newElement = interfaze;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(interfaze);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(interfaze);
            } else if (parent instanceof Class) {
                ((Class) parent).getNestedClassifiers().add(interfaze);
            }
            // Model
        } else if ("nexcore.tool.uml.ui.project.createActor".equals(actionId)) { //$NON-NLS-1$
            Actor actor = UMLHelper.createActor();
            newElement = actor;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(actor);
            }
            // Model
        } else if ("nexcore.tool.uml.ui.project.createUsecase".equals(actionId)) { //$NON-NLS-1$
            UseCase useCase = UMLHelper.createUseCase();
            newElement = useCase;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(useCase);
            }
            // Model artifact
        } else if ("nexcore.tool.uml.ui.project.createArtifact".equals(actionId)) { //$NON-NLS-1$
            Artifact artifact = UMLHelper.createArtifact();
            newElement = artifact;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(artifact);
            } else if (parent instanceof Artifact) {
                ((Artifact) parent).getNestedArtifacts().add(artifact);
            }
            // Model component
        } else if ("nexcore.tool.uml.ui.project.createComponent".equals(actionId)) { //$NON-NLS-1$
            org.eclipse.uml2.uml.Component component = UMLHelper.createComponent();
            newElement = component;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(component);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(component);
            }
            // Model Class component interface
        } else if ("nexcore.tool.uml.ui.project.createEnum".equals(actionId)) { //$NON-NLS-1$
            Enumeration enumeration = UMLHelper.createEnumeration();
            newElement = enumeration;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(enumeration);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(enumeration);
            } else if (parent instanceof Class) {
                ((Class) parent).getNestedClassifiers().add(enumeration);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getNestedClassifiers().add(enumeration);
            }
            // Model component
        } else if ("nexcore.tool.uml.ui.project.createPrimitiveType".equals(actionId)) { //$NON-NLS-1$
            PrimitiveType primitiveType = UMLHelper.createPrimitiveType();
            newElement = primitiveType;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(primitiveType);
            } else if (parent instanceof Component) {
                ((Component) parent).getPackagedElements().add(primitiveType);
            }
            // Model
        } else if ("nexcore.tool.uml.ui.project.createSignal".equals(actionId)) { //$NON-NLS-1$
            Signal signal = UMLHelper.createSignal();
            newElement = signal;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(signal);
            }

            // Class collaboration artifact component enumeration interface
            // signal
        } else if ("nexcore.tool.uml.ui.project.createAttribute".equals(actionId)) { //$NON-NLS-1$

            Property property = UMLHelper.createProperty();
            property.setName(UMLMessage.UML_PROPERTY);
            newElement = property;
            if (parent instanceof Class) { // Class, Component
                ((Class) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Collaboration) {
                ((Collaboration) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Artifact) {
                ((Artifact) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Enumeration) {
                ((Enumeration) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Signal) {
                ((Signal) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof DataType) {
                ((DataType) parent).getOwnedAttributes().add(property);
            }
            // Class artifact component enumeration interface
        } else if ("nexcore.tool.uml.ui.project.createOperation".equals(actionId)) { //$NON-NLS-1$
            Operation operation = UMLHelper.createOperation();
            newElement = operation;
            if (parent instanceof Class) { // Class, Component
                ((Class) parent).getOwnedOperations().add(operation);
            } else if (parent instanceof Artifact) {
                ((Artifact) parent).getOwnedOperations().add(operation);
            } else if (parent instanceof Enumeration) {
                ((Enumeration) parent).getOwnedOperations().add(operation);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getOwnedOperations().add(operation);
            } else if (parent instanceof DataType) {
                ((DataType) parent).getOwnedOperations().add(operation);
            }
            // Class collaboration artifact component interface
        } else if ("nexcore.tool.uml.ui.project.createPart".equals(actionId)) { //$NON-NLS-1$
            Property property = UMLHelper.createProperty();
            property.setName(UMLMessage.UML_PART);
            property.setAggregation(AggregationKind.COMPOSITE_LITERAL);
            property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
            newElement = property;
            if (parent instanceof Class) { // Class, Component
                ((Class) parent).getOwnedAttributes().add(property);
            } else if (parent instanceof Collaboration) {
                ((Collaboration) parent).getOwnedAttributes().add(property);
            }
            // Class component
        } else if ("nexcore.tool.uml.ui.project.createPort".equals(actionId)) { //$NON-NLS-1$
            Port port = UMLHelper.createPort();
            newElement = port;
            if (parent instanceof Class) { // Class, Component
                ((Class) parent).getOwnedAttributes().add(port);
            }
            // Class interface
        } else if ("nexcore.tool.uml.ui.project.createReception".equals(actionId)) { //$NON-NLS-1$
            Reception reception = UMLHelper.createReception();
            newElement = reception;
            // TODO 부모 클래스와 같은 레벨에 신호를 하나 만들어두거나 다른 신호를 지정하게 해서 연결해야 함.
            if (parent instanceof Class) {
                ((Class) parent).getOwnedReceptions().add(reception);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getOwnedReceptions().add(reception);
            }

            // Class collaboration component interface
        } else if ("nexcore.tool.uml.ui.project.createCollaborationUse".equals(actionId)) { //$NON-NLS-1$

            CollaborationUse collaborationUse = UMLHelper.createCollaborationUse();
            newElement = collaborationUse;
            if (parent instanceof Class) { // Class, Component
                ((Class) parent).getCollaborationUses().add(collaborationUse);
            } else if (parent instanceof Collaboration) {
                ((Collaboration) parent).getCollaborationUses().add(collaborationUse);
            } else if (parent instanceof Interface) {
                ((Interface) parent).getCollaborationUses().add(collaborationUse);
            }
            // Model Class component
        } else if ("nexcore.tool.uml.ui.project.createInteraction".equals(actionId)) { //$NON-NLS-1$
            Interaction interaction = UMLHelper.createInteraction();
            newElement = interaction;
            if (parent instanceof Package) { // Package, Model
                ((Package) parent).getPackagedElements().add(interaction);
            } else if (parent instanceof Component) {
                ((Component) parent).getOwnedBehaviors().add(interaction);
            } else if (parent instanceof Class) {
                ((Class) parent).getNestedClassifiers().add(interaction);
            } else if (parent instanceof Collaboration) {
                ((Collaboration) parent).getOwnedBehaviors().add(interaction);
            }
            // collaboration
        } else if ("nexcore.tool.uml.ui.project.createRole".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Collaboration) {
                Property property = UMLHelper.createProperty();
                property.setName(UMLMessage.UML_ROLE);
                property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
                newElement = property;
                ((Collaboration) parent).getOwnedAttributes().add(property);
            }
            // component
        } else if ("nexcore.tool.uml.ui.project.createDataType".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Component) {
                DataType dataType = UMLHelper.createDataType();
                newElement = dataType;
                ((Component) parent).getPackagedElements().add(dataType);
            }
            // enumeration
        } else if ("nexcore.tool.uml.ui.project.createEnumLiteral".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Enumeration) {
                EnumerationLiteral enumerationLiteral = UMLHelper.createEnumerationLiteral();
                newElement = enumerationLiteral;
                ((Enumeration) parent).getOwnedLiterals().add(enumerationLiteral);
            }

            /*************************************/
            /************ Activity *************/
            /*************************************/
        } else if ("nexcore.tool.uml.ui.project.createPartition".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ActivityPartition partition = UMLHelper.createActivityPartition();
                newElement = partition;
                ((Activity) parent).getGroups().add(partition);
            }
        } else if ("nexcore.tool.uml.ui.project.createAction".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                OpaqueAction opaqueAction = UMLHelper.createOpaqueAction();
                newElement = opaqueAction;
                ((Activity) parent).getNodes().add(opaqueAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createActivityFinalNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ActivityFinalNode activityFinalNode = UMLHelper.createActivityFinalNode();
                newElement = activityFinalNode;
                ((Activity) parent).getNodes().add(activityFinalNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createDecisionNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                DecisionNode decisionNode = UMLHelper.createDecisionNode();
                newElement = decisionNode;
                ((Activity) parent).getNodes().add(decisionNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createFlowFinalNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                FlowFinalNode flowFinalNode = UMLHelper.createFlowFinalNode();
                newElement = flowFinalNode;
                ((Activity) parent).getNodes().add(flowFinalNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createBranchNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ForkNode forkNode = UMLHelper.createForkNode();
                newElement = forkNode;
                ((Activity) parent).getNodes().add(forkNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createInitialNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                InitialNode initialNode = UMLHelper.createInitialNode();
                newElement = initialNode;
                ((Activity) parent).getNodes().add(initialNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createJointNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                JoinNode joinNode = UMLHelper.createJoinNode();
                newElement = joinNode;
                ((Activity) parent).getNodes().add(joinNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createMergeNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                MergeNode mergeNode = UMLHelper.createMergeNode();
                newElement = mergeNode;
                ((Activity) parent).getNodes().add(mergeNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createActivityParameterNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ActivityParameterNode activityParameterNode = UMLHelper.createActivityParameterNode();
                newElement = activityParameterNode;
                ((Activity) parent).getNodes().add(activityParameterNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createCentralBufferNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CentralBufferNode centralBufferNode = UMLHelper.createCentralBufferNode();
                newElement = centralBufferNode;
                ((Activity) parent).getNodes().add(centralBufferNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createDataStoreNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                DataStoreNode dataStoreNode = UMLHelper.createDataStoreNode();
                newElement = dataStoreNode;
                ((Activity) parent).getNodes().add(dataStoreNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createStructuredActivitiesNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                StructuredActivityNode structuredActivityNode = UMLHelper.createStructuredActivityNode();
                newElement = structuredActivityNode;
                ((Activity) parent).getNodes().add(structuredActivityNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createLoopNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                LoopNode loopNode = UMLHelper.createLoopNode();
                newElement = loopNode;
                ((Activity) parent).getNodes().add(loopNode);
            }
        } else if ("nexcore.tool.uml.ui.project.createAddVariableValueMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                AddVariableValueAction addVariableValueAction = UMLHelper.createAddVariableValueAction();
                newElement = addVariableValueAction;
                ((Activity) parent).getNodes().add(addVariableValueAction);
            }
        } else if ("nexcore.tool.uml.nexcore.tool.uml.ui.project.createDeleteVariableMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ClearVariableAction clearVariableAction = UMLHelper.createClearVariableAction();
                newElement = clearVariableAction;
                ((Activity) parent).getNodes().add(clearVariableAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadVariableMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadVariableAction readVariableAction = UMLHelper.createReadVariableAction();
                newElement = readVariableAction;
                ((Activity) parent).getNodes().add(readVariableAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createRemoveVariableMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                RemoveVariableValueAction removeVariableValueAction = UMLHelper.createRemoveVariableValueAction();
                newElement = removeVariableValueAction;
                ((Activity) parent).getNodes().add(removeVariableValueAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createAddStructuralFeatureMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                AddStructuralFeatureValueAction addStructuralFeatureValueAction = UMLHelper.createAddStructuralFeatureValueAction();
                newElement = addStructuralFeatureValueAction;
                ((Activity) parent).getNodes().add(addStructuralFeatureValueAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createClearStructuralFeatureMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ClearStructuralFeatureAction clearStructuralFeatureAction = UMLHelper.createClearStructuralFeatureAction();
                newElement = clearStructuralFeatureAction;
                ((Activity) parent).getNodes().add(clearStructuralFeatureAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadStructuralFeatureMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadStructuralFeatureAction readStructuralFeatureAction = UMLHelper.createReadStructuralFeatureAction();
                newElement = readStructuralFeatureAction;
                ((Activity) parent).getNodes().add(readStructuralFeatureAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createRemoveStructuralFeatureMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                RemoveStructuralFeatureValueAction removeStructuralFeatureValueAction = UMLHelper.createRemoveStructuralFeatureValueAction();
                newElement = removeStructuralFeatureValueAction;
                ((Activity) parent).getNodes().add(removeStructuralFeatureValueAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createObjectMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CreateObjectAction createObjectAction = UMLHelper.createCreateObjectAction();
                newElement = createObjectAction;
                ((Activity) parent).getNodes().add(createObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createRemoveObjectMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                DestroyObjectAction destroyObjectAction = UMLHelper.createDestroyObjectAction();
                newElement = destroyObjectAction;
                ((Activity) parent).getNodes().add(destroyObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadRangeMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadExtentAction readExtentAction = UMLHelper.createReadExtentAction();
                newElement = readExtentAction;
                ((Activity) parent).getNodes().add(readExtentAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadClassifiedObjectMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadIsClassifiedObjectAction readIsClassifiedObjectAction = UMLHelper.createReadIsClassifiedObjectAction();
                newElement = readIsClassifiedObjectAction;
                ((Activity) parent).getNodes().add(readIsClassifiedObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadItselfMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadSelfAction readSelfAction = UMLHelper.createReadSelfAction();
                newElement = readSelfAction;
                ((Activity) parent).getNodes().add(readSelfAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createObjectReclassificationMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReclassifyObjectAction reclassifyObjectAction = UMLHelper.createReclassifyObjectAction();
                newElement = reclassifyObjectAction;
                ((Activity) parent).getNodes().add(reclassifyObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createStartClassBehaviorMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                StartClassifierBehaviorAction startClassifierBehaviorAction = UMLHelper.createStartClassifierBehaviorAction();
                newElement = startClassifierBehaviorAction;
                ((Activity) parent).getNodes().add(startClassifierBehaviorAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createIDTestMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                TestIdentityAction testIdentityAction = UMLHelper.createTestIdentityAction();
                newElement = testIdentityAction;
                ((Activity) parent).getNodes().add(testIdentityAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createClearAssociationMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ClearAssociationAction clearAssociationAction = UMLHelper.createClearAssociationAction();
                newElement = clearAssociationAction;
                ((Activity) parent).getNodes().add(clearAssociationAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createLinkMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CreateLinkAction createLinkAction = UMLHelper.createCreateLinkAction();
                newElement = createLinkAction;
                ((Activity) parent).getNodes().add(createLinkAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createLinkObjectMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CreateLinkObjectAction createLinkObjectAction = UMLHelper.createCreateLinkObjectAction();
                newElement = createLinkObjectAction;
                ((Activity) parent).getNodes().add(createLinkObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createRemoveLinkMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                DestroyLinkAction destroyLinkAction = UMLHelper.createDestroyLinkAction();
                newElement = destroyLinkAction;
                ((Activity) parent).getNodes().add(destroyLinkAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReadLinkMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadLinkAction readLinkAction = UMLHelper.createReadLinkAction();
                newElement = readLinkAction;
                ((Activity) parent).getNodes().add(readLinkAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createLinkObjectResultReadMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadLinkObjectEndAction readLinkObjectEndAction = UMLHelper.createReadLinkObjectEndAction();
                newElement = readLinkObjectEndAction;
                ((Activity) parent).getNodes().add(readLinkObjectEndAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createLinkObjectResultRegulatorReadMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReadLinkObjectEndQualifierAction readLinkObjectEndQualifierAction = UMLHelper.createReadLinkObjectEndQualifierAction();
                newElement = readLinkObjectEndQualifierAction;
                ((Activity) parent).getNodes().add(readLinkObjectEndQualifierAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createBroadcastSignalMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                BroadcastSignalAction broadcastSignalAction = UMLHelper.createBroadcastSignalAction();
                newElement = broadcastSignalAction;
                ((Activity) parent).getNodes().add(broadcastSignalAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createBehaviorCallMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CallBehaviorAction callBehaviorAction = UMLHelper.createCallBehaviorAction();
                newElement = callBehaviorAction;
                ((Activity) parent).getNodes().add(callBehaviorAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createOperationCallMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                CallOperationAction callOperationAction = UMLHelper.createCallOperationAction();
                newElement = callOperationAction;
                ((Activity) parent).getNodes().add(callOperationAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createObjectTransmissionMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                SendObjectAction sendObjectAction = UMLHelper.createSendObjectAction();
                newElement = sendObjectAction;
                ((Activity) parent).getNodes().add(sendObjectAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createSignalTransmissionMeasure".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                SendSignalAction sendSignalAction = UMLHelper.createSendSignalAction();
                newElement = sendSignalAction;
                ((Activity) parent).getNodes().add(sendSignalAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createCallApprovedMeasures".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                AcceptCallAction acceptCallAction = UMLHelper.createAcceptCallAction();
                newElement = acceptCallAction;
                ((Activity) parent).getNodes().add(acceptCallAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createEventMeasures".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                AcceptEventAction acceptEventAction = UMLHelper.createAcceptEventAction();
                newElement = acceptEventAction;
                ((Activity) parent).getNodes().add(acceptEventAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createReplyMeasures".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ReplyAction replyAction = UMLHelper.createReplyAction();
                newElement = replyAction;
                ((Activity) parent).getNodes().add(replyAction);
            }
        } else if ("nexcore.tool.uml.ui.project.createConditionalNode".equals(actionId)) { //$NON-NLS-1$
            if (parent instanceof Activity) {
                ConditionalNode conditionalNode = UMLHelper.creatConditionalNode();
                newElement = conditionalNode;
                ((Activity) parent).getNodes().add(conditionalNode);
            }
        }

        if (!(newElement instanceof NamedElement))
            return;
        NamedElement namedElement = (NamedElement) newElement;
        namedElement.setName(UMLManager.getPackagedUniqueName((Namespace) parent, namedElement.getName()));
    }
    
}
