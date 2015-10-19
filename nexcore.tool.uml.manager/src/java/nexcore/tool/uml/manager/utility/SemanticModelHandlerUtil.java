/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.ExtensionType;
import nexcore.tool.mda.model.designer.transformation.LocationKeywordType;
import nexcore.tool.mda.model.designer.transformation.LocationSegment;
import nexcore.tool.mda.model.designer.transformation.LocationType;
import nexcore.tool.mda.model.designer.transformation.ParentType;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TargetType;
import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.SemanticModelHandlerRegistry;
import nexcore.tool.mdd.core.util.MDADesignerTransformationData;
import nexcore.tool.mdd.core.util.MDADesignerTransformationUtil;
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.ModelUtility;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설 명 : SemanticModelHandlerUtil</li>
 * <li>작성일 : 2010. 11. 25.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SemanticModelHandlerUtil {

    /** UML 모델러 시맨틱 모델 핸들러 유틸 객체 */
    private static SemanticModelHandlerUtil semanticModelHandlerUtil;

    /** 시맨틱 모델 핸들러 */
    private static ISemanticModelHandler semanticModelHandler;

    /**
     * 생성자
     */
    private SemanticModelHandlerUtil() {
    }

    /**
     * UML 모델러 시맨틱 모델 핸들러 유틸 객체 반환
     * 
     * @return SemanticModelHandlerUtil
     */
    public static SemanticModelHandlerUtil getInstance() {
        if (semanticModelHandler == null) {
            if (SemanticModelHandlerRegistry.getInstance() != null) {
                if (SemanticModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER) != null) {
                    semanticModelHandler = SemanticModelHandlerRegistry.getInstance()
                        .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER);
                }
            }

            return semanticModelHandlerUtil;
        }

        return null;
    }

    /**
     * UML 모델러 시맨틱 모델 핸들러 반환
     * 
     * @return ISemanticModelHandler
     */
    public static ISemanticModelHandler getHandlerInstance() {
        if (SemanticModelHandlerRegistry.getInstance() != null) {
            if (SemanticModelHandlerRegistry.getInstance()
                .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER) != null) {
                return SemanticModelHandlerRegistry.getInstance()
                    .getHandlerInstance(ManagerConstant.NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER);
            }
        }

        return semanticModelHandler;
    }

    /**
     * ========================================================================
     * 설계 모델 구조 생성 코드 시작
     */

    /**
     * Source모델(분석 모델)의 Element로부터 Base 패키지를 찾아서 RuleSet(변환규칙)의 Target모델 URI정보로
     * 모델을 읽어온 다음 Target모델(설계 모델) Base 패키지 위치를 찾아서 구조를 복사하는 메소드
     * 
     * @param sourceElement
     * @param transformationData
     */
    public static void copyStructure(Element sourceElement, MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceElement == null || !(sourceElement instanceof NamedElement) || !isValid(transformationData)) {
            return;
        }
        
        try {
            // Source모델(분석 모델) 가져오기
            Model sourceModel = sourceElement.getModel();
            // check if the source element is from the source model
            Model dataSourceModel = null;
            if (transformationData.getSourceModelURI() != null) {
                dataSourceModel = DomainModelHandlerUtil.openModel(transformationData.getSourceModelURI());
            }

            // if(!transformationData.getSourceModel().getName().equals(sourceModel.getName()))
            // {
            if (!dataSourceModel.getName().equals(sourceModel.getName())) {
                return;
            }

            // Package targetBase = null;
            // if (sourceElement instanceof Package) { // 빈 패키지는 생성시키지 않음.
            // return;
            // } else if (sourceElement instanceof Class || sourceElement
            // instanceof Interface) { // 클래스나 인터페이스의 경우, 내부에 패키지 생성로직이 있으므로 패키지
            // 생성은 안해야 함.
            // targetBase = getBasePackage(sourceElement,
            // transformationData.getRuleSet());
            // } else if (sourceElement instanceof Property) { // 프로퍼티도 아무일 하지
            // 않음
            // return;
            // } else { // 그 외에는 패키지 구조를 생성시켜 놔야 함.
            // targetBase = getTargetBasePackage(sourceElement,
            // transformationData);
            // }

            // Target모델(설계 모델)의 Base 패키지 가져오기
            Package targetBase = getTargetBasePackage(sourceElement, transformationData);
            if (targetBase == null) {
                return;
            }

            // 2012-08-28
            // 분석모델 -> 설계모델 변환시 패지키의 키워드까지 변환되도록 한다.
            if (sourceElement instanceof Package && targetBase instanceof Package ) {
                EList<String> keywords = sourceElement.getKeywords();
                if (!keywords.isEmpty()) {
                    for (String keyword : keywords) {
                        targetBase.addKeyword(keyword);
                    }
                }
            }
            // Source요소(분석 모델)가 Collaboration일 경우
            if (sourceElement instanceof Collaboration) {
                // Target(설계 모델)이미 존재하던 Collaboration이 있다면 삭제하고,
                NamedElement targetNamedElement = targetBase.getOwnedMember(((Collaboration) sourceElement).getName());
                if (targetNamedElement instanceof Collaboration) {
                    targetNamedElement.destroy();
                }

                // 새로운 Collaboration을 추가하고,
                EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getCollaboration();
                PackageableElement targetElement = targetBase.createPackagedElement(((Collaboration) sourceElement).getName(),
                    eClass);
                
                // 키워드를 복사한다. (2012-11-08)
                ModelUtility.copyKeywords(sourceElement, targetElement);
                // 코멘트를 복사한다.
                copyComment(sourceElement, targetElement);
                // Source요소(분석 모델)가 Actor나 Component일 경우
            } else if (sourceElement instanceof Actor || sourceElement instanceof Component) {
                // 바로 복사
                copy((NamedElement) sourceElement, targetBase, transformationData);
                // Source요소(분석 모델)가 Interaction일 경우
            } else if (sourceElement instanceof Interaction) {
                // Interaction을 생성 후,
                EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getInteraction();
                Interaction sourceInteraction = (Interaction) sourceElement;
                String sourceInteractionQualifiedName = sourceInteraction.getQualifiedName();
                String targetCollaborationQualifiedName = (targetBase.getModel().getName() + sourceInteractionQualifiedName.substring(sourceInteractionQualifiedName.indexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING))); //$NON-NLS-1$
                targetCollaborationQualifiedName = targetCollaborationQualifiedName.substring(0,
                    targetCollaborationQualifiedName.lastIndexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING));

                // 부모가 맞는 Collaboration을 찾아서,
                Collaboration collaboration = getCollaboration(targetBase, targetCollaborationQualifiedName);
                // 그 하위에 Interaction을 추가
                if (collaboration != null) {
                    Behavior behavior = collaboration.createOwnedBehavior(sourceInteraction.getName(), eClass);

                    // 코멘트를 복사한다.
                    copyComment(sourceElement, behavior);
                }
                // Source요소(분석 모델)가 Class나 Interface일 경우
            } else if (sourceElement instanceof Class || sourceElement instanceof Interface) {
                // 변환규칙에 따라 Class나 Interface를 변환한다.
                transformClassOrInterface((Type) sourceElement, targetBase, transformationData);
            } else if (sourceElement instanceof Diagram) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Source모델(분석 모델) 요소 기반으로 Target모델(설계 모델)의 Base 패키지를 찾는 메소드
     * 
     * @param sourceElement
     * @param transformationData
     * @return Package
     */
    private static Package getTargetBasePackage(Element sourceElement, MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceElement == null || sourceElement.getModel() == null || !isValid(transformationData)) {
            return null;
        }

        // Source모델(분석 모델) 가져오기
        Model sourceModel = null;
        if (transformationData.getSourceModelURI() != null) {
            sourceModel = DomainModelHandlerUtil.openModel(transformationData.getSourceModelURI());
        }

        // Source모델(분석 모델) null 확인
        if (!sourceModel.getName().equals(sourceElement.getModel().getName())) {// ||
            // !(transformationData.getSourceModel().getName().equals(sourceElement.getModel().getName()))
            return null;
        }

        // Model targetModel = transformationData.getTargetModel();
        // Target모델(설계 모델) 가져오기
        Model targetModel = null;
        if (transformationData.getTargetModelURI() != null) {
            targetModel = DomainModelHandlerUtil.openModel(transformationData.getTargetModelURI());
        }

        // Target모델(설계 모델) Base 패키지 획득
        Package sourceBase = getBasePackage(sourceElement, transformationData.getRuleSet());
        String sourceModelName = sourceBase.getModel().getName();
        String sourceBaseQName = sourceBase.getQualifiedName();
        String targetBaseQName = targetModel.getName() + sourceBaseQName.substring(sourceModelName.length());

        Package targetBase = transformPackage(sourceBase, targetModel, targetBaseQName, false);
        return targetBase;
    }

    /**
     * Base 패키지를 찾는 메소드
     * 
     * @param inputElement
     * @param ruleSet
     * @return
     */
    private static Package getBasePackage(Element inputElement, RuleSet ruleSet) {
        // null 확인
        if (inputElement == null) {
            return null;
        }

        if (ruleSet == null) {
            return inputElement.getNearestPackage();
        }

        EList<SourceStructureTransformationData> sourceStructureTransformationDataList = ruleSet.getStructureRules();
        SourceStructureTransformationData sourceStructureTransformationData = null;
        Package inputPackage = null;
        LocationType locationType = null;
        EList<LocationSegment> locationSegmentList = null;
        LocationSegment locationSegment = null;
        // location segment sequence
        int sequence = -1;
        Package basePackage = null;
        Property inputProperty = null;
        Operation inputOperation = null;

        for (Iterator<SourceStructureTransformationData> iter = sourceStructureTransformationDataList.iterator(); iter.hasNext();) {
            sourceStructureTransformationData = iter.next();

            // 입력된 요소가 Package인 경우
            if (inputElement instanceof Package) {
                inputPackage = (Package) inputElement;
                locationType = sourceStructureTransformationData.getSourceLocation();
                if (locationType == null) {
                    continue;
                }

                locationSegmentList = locationType.getLocation();

                if (locationSegmentList != null) {
                    for (int segmentCount = 0; segmentCount < locationSegmentList.size(); segmentCount++) {
                        locationSegment = locationSegmentList.get(segmentCount);

                        if (inputPackage.getName().equals(locationSegment.getLocationName())) {
                            sequence = locationSegment.getSequence();
                            basePackage = inputPackage;

                            while (sequence > 0) {
                                basePackage = basePackage.getNestingPackage();

                                sequence--;
                            }

                            return basePackage;
                        }
                    }
                }
                // 입력된 요소가 Class인 경우
            } else if (inputElement instanceof Class) {
                if (isAppliedStereotype(inputElement, sourceStructureTransformationData.getSourceType().getSourceName())) {
                    locationType = sourceStructureTransformationData.getSourceLocation();
                    locationSegmentList = locationType.getLocation();

                    sequence = MDADesignerTransformationUtil.getSize(locationSegmentList);
                    basePackage = inputElement.getNearestPackage();

                    while (sequence > 1) {
                        basePackage = basePackage.getNestingPackage();

                        sequence--;
                    }

                    return basePackage;
                }
                // 입력된 요소가 Property인 경우
            } else if (inputElement instanceof Property) {
                inputProperty = (Property) inputElement;

                if (inputProperty.getClass_() != null) {
                    return getBasePackage(inputProperty.getClass_(), ruleSet);
                } else {
                    return inputProperty.getNearestPackage();
                }
                // 입력된 요소가 Operation인 경우
            } else if (inputElement instanceof Operation) {
                inputOperation = (Operation) inputElement;

                if (inputOperation.getInterface() != null) {
                    return getBasePackage(inputOperation.getInterface(), ruleSet);
                } else if (inputOperation.getClass_() != null) {
                    return getBasePackage(inputOperation.getClass_(), ruleSet);
                }
            }
        }

        return inputElement.getNearestPackage();
    }

    /**
     * 지정된 QualifiedString의 구조대로 Package를 생성.
     * 
     * @param sourceBase
     * @param model
     * @param qualifiedStr
     * @param isTruncated
     *            true인경우 기존 최하위의 Package를 삭제
     * @return
     */
    public static Package transformPackage(Package sourceBase, Model model, String qualifiedStr, boolean isTruncated) {
        // null 확인
        if (model == null || qualifiedStr == null) {
            return null;
        }

        int index = qualifiedStr.indexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING);
        // null 확인
        if (index == -1) {
            return null;
        }

        Package parentPackage = model;
        String packagedElementName = qualifiedStr.substring(0, index);
        if (!model.getName().equals(packagedElementName)) {
            return null;
        }

        qualifiedStr = qualifiedStr.substring(index + 2);
        index = qualifiedStr.indexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING); //$NON-NLS-1$

        Package newPackage = null;
        while (index != -1 && parentPackage != null) {
            packagedElementName = qualifiedStr.substring(0, index);
            newPackage = parentPackage.getNestedPackage(packagedElementName);

            if (newPackage != null && isTruncated) {
                newPackage.destroy();

                newPackage = null;
            }

            if (newPackage == null) {
                newPackage = parentPackage.createNestedPackage(packagedElementName);
            }

            parentPackage = newPackage;
            qualifiedStr = qualifiedStr.substring(index + 2);
            index = qualifiedStr.indexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING); //$NON-NLS-1$
        }

        newPackage = parentPackage.getNestedPackage(qualifiedStr);
        if (newPackage != null && isTruncated) {
            newPackage.destroy();

            newPackage = null;
        }

        if (newPackage == null) {
            newPackage = parentPackage.createNestedPackage(qualifiedStr);
            copyComment(sourceBase, newPackage);
        }

        return newPackage;
    }

    /**
     * copy comment from a source element to a target element
     * 
     * @param source
     * @param target
     */
    public static void copyComment(Element source, Element target) {
        if (source != null && target != null) {
            if (target.getOwnedComments() != null) {
                target.getOwnedComments().clear();
            }

            for (Iterator iterator = source.getOwnedComments().iterator(); iterator.hasNext();) {
                Comment sourceComment = (Comment) iterator.next();
                String body = sourceComment.getBody();
                Comment targetComment = target.createOwnedComment();
                targetComment.setBody(body);
            }
        }
    }

    /**
     * Source(분석 모델)요소를 Target(설계모델) 패키지 이하로 복사하는 메소드
     * 
     * @param sourceElement
     * @param targetPackage
     * @param transformationData
     */
    private static void copy(NamedElement sourceElement, Package targetPackage,
                             MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceElement == null || targetPackage == null) {
            return;
        }
        if (sourceElement instanceof Activity) {
            return;
        }

        if (sourceElement instanceof Collaboration || sourceElement instanceof Class
            || sourceElement instanceof Interface || sourceElement instanceof Component
            || sourceElement instanceof Actor) {
            NamedElement namedElement = targetPackage.getOwnedMember(sourceElement.getName());

            if (namedElement != null) {
                if (namedElement instanceof Collaboration && sourceElement instanceof Collaboration)
                    return;
                else if (namedElement instanceof Class && sourceElement instanceof Class)
                    return;
                else if (namedElement instanceof Interface && sourceElement instanceof Interface)
                    return;
                else if (namedElement instanceof Component && sourceElement instanceof Component)
                    return;
                else if (namedElement instanceof Actor && sourceElement instanceof Actor)
                    return;

            }

            copyElement(targetPackage, sourceElement, transformationData);
        }
    }

    /**
     * 부모 패키지 하위로 요소를 복사하는 메소드
     * 
     * @param parent
     * @param object
     */
    private static void copyElement(EObject parent, Object object, MDADesignerTransformationData data) {
        int supported = 0;

        // filter: only copy the following object
        if (object != null
            && (object instanceof Package || object instanceof Class || object instanceof Operation
                || object instanceof Property || object instanceof Collaboration || object instanceof Interaction
                || object instanceof Lifeline || object instanceof Component || object instanceof Interface || object instanceof Actor)) {
            supported = 1;

        }

        if (supported == 0) {
            return;
        }

        EObject eobject;
        EAnnotation eAnnotation;

        boolean hasDiagram = false;

        if (object instanceof EObject) {
            eobject = EcoreUtil.copy((EObject) object);
            if (eobject == parent) {
                return;
            } else if (eobject instanceof Model) {
                return;
            } else if (eobject instanceof Diagram) {
                hasDiagram = true;
                return;
            }

            if (parent.equals(((EObject) object).eContainer())) {
                if (eobject instanceof NamedElement && parent instanceof Namespace) {
                    ((NamedElement) eobject).setName(getCopiedUniqueName((NamedElement) eobject,
                        (Namespace) parent,
                        ((NamedElement) eobject).getName()));
                }
            }

            // parent에 따른 분기
            if (parent instanceof Package) {
                Package packageObject = (Package) parent;
                if (eobject instanceof Class || eobject instanceof Interface) {
                    Type source = (Type) object;
                    Type target = null;

                    if (object instanceof Class)
                        target = packageObject.createOwnedClass(((Class) object).getName(), false);
                    else if (object instanceof Interface)
                        target = packageObject.createOwnedInterface(((Interface) object).getName());
                    transformAttributes(source, target, null);
                    transformOperations(source, target, data, null);
                    EList<String> keywords = source.getKeywords();
                    if (!keywords.isEmpty()) {
                        for (String keyword : keywords) {
                            target.addKeyword(keyword);
                        }
                    }

                    return;

                } else if (eobject instanceof PackageableElement) {

                    packageObject.getPackagedElements().add((PackageableElement) eobject);

                    return;
                } else if (eobject instanceof EAnnotation) {
                    eAnnotation = packageObject.getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);

                    if (eAnnotation == null) {
                        packageObject.getEAnnotations().add((EAnnotation) eobject);
                    }

                    return;
                }
            } else if (parent instanceof Component) {
                Component component = (Component) parent;

                if (eobject instanceof Package) {
                    component.getPackagedElements().add((Package) eobject);

                    return;
                } else if (eobject instanceof Property) { // Property, Port
                    component.getOwnedAttributes().add((Property) eobject);

                    return;
                } else if (eobject instanceof Operation) {
                    component.getOwnedOperations().add((Operation) eobject);

                    return;
                } else if (eobject instanceof Class) { // Class, Component
                    component.getPackagedElements().add((Class) eobject);

                    return;
                } else if (eobject instanceof Enumeration) {
                    component.getPackagedElements().add((Enumeration) eobject);

                    return;
                } else if (eobject instanceof Interface) {
                    component.getPackagedElements().add((Interface) eobject);

                    return;
                } else if (eobject instanceof DataType) {// DataType,
                    // PrimitiveType
                    component.getPackagedElements().add((DataType) eobject);

                    return;
                } else if (eobject instanceof Activity) {
                    component.getOwnedBehaviors().add((Activity) eobject);

                    return;
                } else if (eobject instanceof Collaboration) {
                    component.getPackagedElements().add((Collaboration) eobject);

                    return;
                } else if (eobject instanceof CollaborationUse) {
                    component.getCollaborationUses().add((CollaborationUse) eobject);

                    return;
                } else if (eobject instanceof Interaction) {
                    component.getOwnedBehaviors().add((Interaction) eobject);

                    return;
                }
            } else if (parent instanceof Class) {
                Class clazz = (Class) parent;

                if (eobject instanceof Property) { // Property, Port
                    clazz.getOwnedAttributes().add((Property) eobject);

                    return;
                } else if (eobject instanceof Operation) {
                    clazz.getOwnedOperations().add((Operation) eobject);
                    return;
                } else if (eobject instanceof Reception) {
                    clazz.getOwnedReceptions().add((Reception) eobject);
                    return;
                } else if (eobject instanceof Class) {
                    clazz.getNestedClassifiers().add((Class) eobject);

                    return;
                } else if (eobject instanceof Enumeration) {
                    clazz.getNestedClassifiers().add((Enumeration) eobject);

                    return;
                } else if (eobject instanceof Interface) {
                    clazz.getNestedClassifiers().add((Interface) eobject);

                    return;
                } else if (eobject instanceof Activity) {
                    clazz.getOwnedBehaviors().add((Activity) eobject);

                    return;
                } else if (eobject instanceof CollaborationUse) {
                    clazz.getCollaborationUses().add((CollaborationUse) eobject);

                    return;
                } else if (eobject instanceof Interaction) {
                    clazz.getNestedClassifiers().add((Interaction) eobject);

                    return;
                }
            } else if (parent instanceof Activity) {
                Activity activity = (Activity) parent;

                if (eobject instanceof ActivityPartition) {
                    activity.getGroups().add((ActivityPartition) eobject);

                    return;
                } else if (eobject instanceof Action) {
                    activity.getNodes().add((Action) eobject);

                    return;
                } else if (eobject instanceof ActivityNode) {
                    activity.getNodes().add((ActivityNode) eobject);

                    return;
                }
            } else if (parent instanceof Collaboration) {
                Collaboration collaboration = (Collaboration) parent;

                if (eobject instanceof Property) {
                    return;
                } else if (eobject instanceof CollaborationUse) {
                    collaboration.getCollaborationUses().add((CollaborationUse) eobject);

                    return;
                }
            } else if (parent instanceof Artifact) {
                Artifact artifact = (Artifact) parent;

                if (eobject instanceof Property) {
                    artifact.getOwnedAttributes().add((Property) eobject);

                    return;
                } else if (eobject instanceof Operation) {
                    artifact.getOwnedOperations().add((Operation) eobject);

                    return;
                } else if (eobject instanceof Artifact) {
                    artifact.getNestedArtifacts().add((Artifact) eobject);

                    return;
                }
            } else if (parent instanceof Enumeration) {
                Enumeration enumeration = (Enumeration) parent;

                if (eobject instanceof EnumerationLiteral) {
                    enumeration.getOwnedLiterals().add((EnumerationLiteral) eobject);

                    return;
                } else if (eobject instanceof Property) {
                    enumeration.getOwnedAttributes().add((Property) eobject);

                    return;
                } else if (eobject instanceof Operation) {
                    enumeration.getOwnedOperations().add((Operation) eobject);

                    return;
                }
            } else if (parent instanceof Interface) {
                Interface interfaze = (Interface) parent;

                if (eobject instanceof Property) {
                    interfaze.getOwnedAttributes().add((Property) eobject);

                    return;
                } else if (eobject instanceof Operation) {
                    interfaze.getOwnedOperations().add((Operation) eobject);

                    return;
                } else if (eobject instanceof Reception) {
                    interfaze.getOwnedReceptions().add((Reception) eobject);

                    return;
                } else if (eobject instanceof Class) {
                    interfaze.getNestedClassifiers().add((Class) eobject);

                    return;
                } else if (eobject instanceof Enumeration) {
                    interfaze.getNestedClassifiers().add((Enumeration) eobject);

                    return;
                } else if (eobject instanceof Collaboration) {
                    interfaze.getNestedClassifiers().add((Collaboration) eobject);

                    return;
                } else if (eobject instanceof CollaborationUse) {
                    interfaze.getCollaborationUses().add((CollaborationUse) eobject);

                    return;
                }
            } else if (parent instanceof Signal) {
                Signal signal = (Signal) parent;

                if (eobject instanceof Property) {
                    signal.getOwnedAttributes().add((Property) eobject);

                    return;
                }
            }

            // 위의 어느 경우에도 해당되지 않을 때, 메세지를 보여준다.
            if (eobject instanceof NamedElement) {
                MessageDialog.openWarning(null, // UiCorePlugin.getShell(),
                    UMLMessage.LABEL_NEXCORE_UML_MODELER,
                    UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_PASTE, ((NamedElement) eobject).getName()));
            }
        }

        // 붙이려고 하는 요소 중 다이어그램이 있을 경우에 메세지를 보여준다.
        if (hasDiagram) {
            MessageDialog.openInformation(null, // UiCorePlugin.getShell(),
                UMLMessage.LABEL_NEXCORE_UML_MODELER,
                UMLMessage.MESSAGE_DIAGRAM_CANNOT_PASTE);
        }
    }

    /**
     * 특정 qualifiedName을 갖는 Collaboration 반환
     * 
     * @param eobject
     * @param qualifiedName
     * @return Collaboration
     */
    public static Collaboration getCollaboration(EObject eobject, String qualifiedName) {
        // null 확인
        if (eobject == null) {
            return null;
        }

        if (eobject instanceof Collaboration) {
            Collaboration collaboration = (Collaboration) eobject;

            if (collaboration.getQualifiedName().equals(qualifiedName)) {
                return collaboration;
            }
        } else {
            EObject eobj = null;

            for (Iterator iter = eobject.eContents().iterator(); iter.hasNext();) {
                eobj = (EObject) iter.next();
                Collaboration tmpCollaboration = getCollaboration(eobj, qualifiedName);

                if (tmpCollaboration != null) {
                    return tmpCollaboration;
                }
            }
        }

        return null;
    }
    
    /**
     * targetTypeList
     */
    List<NamedElement> targetTypeList = new ArrayList<NamedElement>();

    /**
     * 얘가 처음 불리는 거임.
     * Source요소(분석 모델)를 변환규칙 파일을 이용하여 Target(설계 모델) 타입으로 변환하는 메소드
     * 
     * @param sourceType
     * @param targetBasePackage
     * @param transformationData
     * @return List<Type>
     */
    private static List<Type> transformClassOrInterface(Type sourceType, Package targetBasePackage,
                                                        MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceType == null || targetBasePackage == null || transformationData == null
            || (!(isValid(transformationData))) || !(sourceType instanceof Class || sourceType instanceof Interface)) {
            return null;
        }

        EList<SourceStructureTransformationData> sourceStructureTransformationDataList = transformationData.getRuleSet()
            .getStructureRules();
        SourceStructureTransformationData sourceStructureTransformationData = null;
        List<Type> targetTypes = new ArrayList<Type>();
        List<Type> tempTargetTypes = null;
        int isTransformed = 0;

        // Source요소(분석 모델)에 Stereotype이 적용된 요소의 경우
        for (Iterator<SourceStructureTransformationData> iterator = sourceStructureTransformationDataList.iterator(); iterator.hasNext();) {
            sourceStructureTransformationData = iterator.next();

            // 변환규칙에서 반환 타입을 찾아 리턴
            if (isAppliedStereotype(sourceType, sourceStructureTransformationData.getSourceType().getSourceName())) {
                isTransformed = 1;

                tempTargetTypes = transformClassOrInterface(sourceType,
                    targetBasePackage,
                    transformationData,
                    sourceStructureTransformationData);

                
                if (tempTargetTypes != null && !tempTargetTypes.isEmpty()) {
                    targetTypes.addAll(tempTargetTypes);
                }
            }
        }
        
        // Source요소(분석 모델)에 Stereotype이 적용안 된 일반 요소인 경우
        if (isTransformed == 0) {
            // 바로 복사
            // System.out.println(sourceType.eClass().toString() + "           "
            // + sourceType.getName());
            copy(sourceType, targetBasePackage, transformationData);
            NamedElement namedElement = targetBasePackage.getOwnedMember(sourceType.getName());
            // System.out.println(namedElement.eClass().toString() +
            // "--------------" + namedElement.getName());
            targetTypes.add((Type) namedElement);
        }
        
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // NCP 연계 정보 생성 시작 /////////////////////////////////////////////////////////////////////////////////////
//        try {
//            RelationManager.getInstance().createRelationAnalysisClassToDesignClass(sourceType, targetTypes.toArray(new EObject[]{}));
//        } catch (Exception e) {
//            ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(e.getMessage(), e);
//        }
//            
//        boolean useProject = ProjectUtil.useProject(getProject(sourceType).getLocation());
//        if (useProject) {
//            boolean isCreateAttribute = Platform.getPreferencesService().getBoolean("nexcore.tool.uml.ui.core",
//                ManagerConstant.IS_CREATE_ATTRIBUTE,
//                true,
//                null);
//
//            IStatus status = createMultiTargetRelation(sourceType,
//                targetTypes.toArray(new Element[] {}),
//                isCreateAttribute);
//            if (status.isMultiStatus()) {
//                IStatus[] children = status.getChildren();
//                for (IStatus s : children) {
//                    if (status.getCode() != Status.OK) {
//                        ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(s.getMessage(), s.getException());
//                    }
//                }
//            }
//
//            if (status.getCode() != Status.OK) {
//                ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(status.getMessage(), status.getException());
//            }
//        }
//        // NCP 연계 정보 생성 끝!!/////////////////////////////////////////////////////////////////////////////////////
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        return targetTypes;
    }

    /**
     * 
     *  
     * @param sourceType
     * @param namedElement void
     */
//    private static void createRelation(Element source, Element target) {
//        Element sourceElement = source;
//        Element targetElement = target;
//        String sourceUUID = EcoreUtil.getURI(sourceElement).fragment();
//        String targetUUID = EcoreUtil.getURI(targetElement).fragment();
//        
//        MetaContent sourceMetaContent = null;
//        MetaContent targetMetaContent = null;
//        
//        List<MetaContent> metaContents;
//        try {
//            metaContents = NCPMetaContentServiceUtil.getMetaContent(sourceUUID);
//            if( null == metaContents || metaContents.isEmpty() ) {
//                sourceMetaContent = NCPMetaContentServiceUtil.createMetaContent(sourceElement, IUMLIdentifier.RESOURCECODE_UML_ANALYSIS_CLASS);
//            } else {
//                sourceMetaContent = metaContents.get(0);
//            }
//            metaContents = new ArrayList<MetaContent>();
//            metaContents = NCPMetaContentServiceUtil.getMetaContent(targetUUID);
//            if( null == metaContents || metaContents.isEmpty() ) {
//                targetMetaContent = NCPMetaContentServiceUtil.createMetaContent(targetElement, IUMLIdentifier.RESOURCECODE_UML_DESIGN_CLASS);
//            } else {
//                targetMetaContent = metaContents.get(0);
//            }
//
//            
//            Map<MetaContent, MetaContent> metaContentMap = new HashMap<MetaContent, MetaContent>();
//            metaContentMap.put(sourceMetaContent, targetMetaContent);
//            
//            NCPMetaContentServiceUtil.createRelation(metaContentMap);
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    private static IStatus createMultiTargetRelation(Element source, Element[] targets, boolean isCreateAttribute) {
//        Element sourceElement = source;
//        String sourceUUID = EcoreUtil.getURI(sourceElement).fragment();
//        MetaContent sourceMetaContent = null;
//        List<MetaContent> metaContents;
//        
//        IStatus status = Status.OK_STATUS;
//
//        try {
//            metaContents = MetaContentUtil.getMetaContent(sourceUUID);
//            if (null == metaContents || metaContents.isEmpty()) {
//                sourceMetaContent = MetaContentUtil.createMetaContent(sourceElement,
//                    IUMLIdentifier.RESOURCECODE_UML_ANALYSIS_CLASS, true);
//            } else {
//                sourceMetaContent = metaContents.get(0);
//            }
//        } catch (Throwable e) {
//            return new MultiStatus(UMLEditorPlugin.PLUGIN_ID, Status.ERROR , e.getMessage(), e);
//        }
//        
//        Map<MetaContent, List<MetaContent>> metaContentMap = new HashMap<MetaContent, List<MetaContent>>();
//
//        for (Element target : targets) {
//            Element targetElement = target;
//            String targetUUID = EcoreUtil.getURI(targetElement).fragment();
//
//            MetaContent targetMetaContent = null;
//
//            try {
//                metaContents = new ArrayList<MetaContent>();
//                metaContents = MetaContentUtil.getMetaContent(targetUUID);
//                if (null == metaContents || metaContents.isEmpty()) {
//                    targetMetaContent = MetaContentUtil.createMetaContent(targetElement, IUMLIdentifier.RESOURCECODE_UML_DESIGN_CLASS, isCreateAttribute);
//                } else {
//                    targetMetaContent = metaContents.get(0);
//                }
//                
//                if(metaContentMap.containsKey(sourceMetaContent)) {
//                    List<MetaContent> metaList = metaContentMap.get(sourceMetaContent);
//                    metaList.add(targetMetaContent);
//                    metaContentMap.put(sourceMetaContent, metaList);
//                } else {
//                    List<MetaContent> metaList = new ArrayList<MetaContent>();
//                    metaList.add(targetMetaContent);
//                    metaContentMap.put(sourceMetaContent, metaList);
//                }
//
//            } catch (Throwable e) {
//                e.printStackTrace();
//                ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(e.getMessage(), e);
//            }
//        }
//        try {
//            return MetaContentUtil.createRelation(metaContentMap);
//        } catch (Exception e) {
//            ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(e.getMessage(), e);
//        }
//        
//        return status;
//    }

    /**
     * source(분석 모델) class/interface가 target(설계 모델) Base 위치를 기준으로 변환규칙에 따라 변환
     * 
     * @param sourceType
     * @param targetBasePackage
     * @param transformationData
     * @param sourceStructureTransformationData
     * @return List<Type>
     */
    private static List<Type> transformClassOrInterface(Type sourceType,
                                                        Package targetBasePackage,
                                                        MDADesignerTransformationData transformationData,
                                                        SourceStructureTransformationData sourceStructureTransformationData) {
        // null 확인
        if (targetBasePackage == null || sourceStructureTransformationData == null || sourceType == null
            || !(sourceType instanceof Class || sourceType instanceof Interface)) {
            return null;
        }

        EList<TargetStructureTransformationData> targetStructureTransformationDataList = 
        	sourceStructureTransformationData.getTargetStructureTransformationDataSet();
        TargetStructureTransformationData targetStructureTransformationData = null;
        LocationType locationType = null;
        Package targetParentPackage = null;
        EList<LocationSegment> locationSegments = null;
        List<LocationSegment> sortedLocationSegments = null;
        // LocationSegment locationSegment = null;
        Type targetType = null;
        boolean isNewlyCreated = false;
        List<Type> result = new ArrayList<Type>();

        // Source(분석 모델) 변환규칙에 해당하는 Target(설계 모델) 변환규칙 갯수만큼 반복
        for (Iterator<TargetStructureTransformationData> iter = targetStructureTransformationDataList.iterator(); iter.hasNext();) {
            targetStructureTransformationData = iter.next();

            locationType = targetStructureTransformationData.getTargetLocation();
            targetParentPackage = targetBasePackage;
            locationSegments = locationType.getLocation();
            sortedLocationSegments = MDADesignerTransformationUtil.sort(locationSegments);

            if (sortedLocationSegments != null && sortedLocationSegments.size() > 1) {
                boolean isBeforeBase = true; // [base] 키워드 앞에 있는 segment 인가?
                int madePackageCount = 0; // 만들어진 패키지 개수
                for (LocationSegment segment : sortedLocationSegments) {
                    if (isBeforeBase) { // [base] 키워드를 아직 지나지 않았으면, root Model
                        // 밑으로 패키지를 만들어 붙인다.
                        if (LocationKeywordType.BASE.equals(segment.getLocationKeyword())) { // [base] 키워드이면.
                            if (madePackageCount == 0) { // [base]가 시작패키지 이면, 기존
                                // targetParentPackage 밑으로 생성되야 하기 때문에 아무것도 하지 않음.

                            } else { // [base] 앞에 패키지들이 생성되었으므로, 그 밑으로.
                                // targetBasePackage 구조를 만들어 줘야 한다.
                                targetParentPackage = createBasePackageStructure(targetParentPackage, targetBasePackage);
                            }
                            isBeforeBase = false;
                            madePackageCount++;
                        } else { // [base] 키워드가 아니면.
                            if (!StringUtil.isEmpty(segment.getLocationName())) {
                                if (madePackageCount > 0) { // 이미 root Package 가.
                                    // 만들어 졌으면 그 아래로 패키지를 붙임.
                                    targetParentPackage = createPackage(targetParentPackage, segment.getLocationName());
                                    madePackageCount++;
                                } else { // 첫번째라면 모델 밑으로 root Package 를 만든다.
                                    targetParentPackage = createPackage(targetParentPackage.getModel(),
                                        segment.getLocationName());
                                    madePackageCount++;
                                }

                            }
                        }
                    } else { // [base] 키워드를 지나갔으면, [base] 밑으로 패키지를 만들어 붙인다.
                        targetParentPackage = createPackage(targetParentPackage, segment.getLocationName());
                    }
                }
            }

            // if(sortedLocationSegments != null &&
            // sortedLocationSegments.size() > 1) {
            // for(int segmentCount = 1; segmentCount < sortedLocationSegments
            // .size(); segmentCount++) {
            // locationSegment = sortedLocationSegments.get(segmentCount);
            //
            // // Target(설계 모델) 부모 패키지 생성
            // if(locationSegment.getLocationName() != null &&
            // !ManagerConstant.EMPTY_STRING.equals(locationSegment.getLocationName().trim()))
            // {
            // targetParentPackage = createPackage(targetParentPackage,
            // locationSegment.getLocationName());
            // }
            // }
            // }

            // Prefix가 있는 경우 설정
            String targetTypeName = ManagerConstant.EMPTY_STRING;
            if (targetStructureTransformationData.getTargetName().getPrefix() != null) {
                targetTypeName += targetStructureTransformationData.getTargetName().getPrefix();
            }
            // Postfix가 있는 경우 설정
            targetTypeName += sourceType.getName();
            if (targetStructureTransformationData.getTargetName().getPostfix() != null) {
                targetTypeName += targetStructureTransformationData.getTargetName().getPostfix();
            }

            NamedElement namedElement = targetParentPackage.getOwnedMember(targetTypeName);
            
            // Creation Type이 Class인 경우
            if (TargetType.CLASS_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (namedElement != null && namedElement instanceof Class) {
                    targetType = (Class) namedElement;
                } else {
                    targetType = targetParentPackage.createOwnedClass(targetTypeName, false);

                    isNewlyCreated = true;
                }
                // Creation Type이 Interface인 경우
            } else if (TargetType.INTERFACE_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (namedElement != null && namedElement instanceof Interface) {
                    targetType = (Interface) namedElement;
                } else {
                    targetType = targetParentPackage.createOwnedInterface(targetTypeName);

                    isNewlyCreated = true;
                }
                // Creation Type이 Component인 경우
            } else if (TargetType.COMPONENT_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (namedElement != null && namedElement instanceof Component) {
                    targetType = (Component) namedElement;
                } else {
                    targetType = UMLHelper.createComponent(targetTypeName);
                    targetParentPackage.getPackagedElements().add(targetType);

                    isNewlyCreated = true;
                }
            }

            if (targetType != null) {
                result.add(targetType);
            }

            // 변환규칙에 적용해야 될 Stereotype이 있는 경우
            if (targetStructureTransformationData.getTargetApplicableStereotype() != null) {
                // 변환규칙의 Stereotype의 이름을 가져와서 Stereotype을 적용
                applyStereotype(sourceType, targetType, targetStructureTransformationData.getTargetApplicableStereotype());
            }

            // 신규로 생성된 경우
            if (isNewlyCreated) {
                copyComment(sourceType, targetType);
            }

            // Property 변환여부가 true인 경우
            if (targetStructureTransformationData.isPropertyCreation()) {
                transformAttributes(sourceType, targetType, targetStructureTransformationData);
            }

            // Operation 변환여부가 true인 경우
            if (targetStructureTransformationData.isOperationCreation()) {
                transformOperations(sourceType, targetType, transformationData, targetStructureTransformationData);
            }
            
            // Extend/Implements 처리
            EList<ParentType> parentTypes = targetStructureTransformationData.getParentType();
            for (ParentType parentType : parentTypes) {
    			if (ExtensionType.EXTENDS.equals(parentType.getExtensionType())) {
    				String extendsName = parentType.getTypeName().get(0);
    				if (ManagerConstant.EMPTY_STRING.equals(extendsName)) {
    					continue;
    				}
    				// extend에 작성된 요소 찾기
    				Classifier supplier = (Classifier) ModelUtility.getElementByFullPackageName(
    						targetParentPackage.getModel(), 
    						UMLPackage.eINSTANCE.getClassifier(), extendsName);
    				// 위에서 찾은 요소와 targetType 사이에 Generalization 관계 생성
    				UMLManager.setGeneralization(UMLFactory.eINSTANCE.createGeneralization(), 
    						supplier, (Classifier) targetType);
    				
    			} else if (ExtensionType.IMPLEMENTS.equals(parentType.getExtensionType())) {
    				EList<String> implTypes = parentType.getTypeName();
    				for (String implType : implTypes) {
    					if (ManagerConstant.EMPTY_STRING.equals(implType)) {
    						continue;
    					}
						// implements에 작성된 요소 찾기
    					Classifier supplier = (Classifier) ModelUtility.getElementByFullPackageName(
    							targetParentPackage.getModel(), 
    							UMLPackage.eINSTANCE.getClassifier(), implType);
    					// 위에서 찾은 요소와 targetType 사이에 InterfaceRealization 관계 생성
    					UMLManager.setRealization(UMLFactory.eINSTANCE.createInterfaceRealization(),
    							supplier, (Classifier) targetType);
					}
    				
    			}
    		}
//            System.out.println(((NamedElement)targetType).getQualifiedName());
        }
        
        return result;
    }

    /**
     * targetBasePackage 의 구조 그대로 parentPackage 밑으로 패키지들을 생성한다.
     * 
     * 
     * @param targetBasePackage
     * @return Package
     */
    private static Package createBasePackageStructure(Package parentPackage, Package targetBasePackage) {

        Package tmpPackage = null;
        Package tmpParentPackage = null;

        List<String> packageNames = new ArrayList<String>();

        tmpPackage = targetBasePackage;

        while (!(tmpPackage instanceof Model)) {
            packageNames.add(tmpPackage.getName());
            tmpPackage = (Package) tmpPackage.getOwner();
            if (tmpPackage == null || tmpPackage instanceof Model) {
                break;
            }
        }
        tmpParentPackage = parentPackage;
        for (int i = packageNames.size() - 1; i >= 0; i--) {
            tmpParentPackage = createPackage(tmpParentPackage, packageNames.get(i));
        }

        return tmpParentPackage;
    }

    /**
     * parent 패키지에 특정 name의 패키지 생성
     * 
     * @param parent
     * @param name
     * @return
     */
    public static Package createPackage(Package parent, String name) {
        // null 확인
        if (parent == null || name == null) {
            return null;
        }

        NamedElement namedElement = parent.getOwnedMember(name);

        if (namedElement != null && namedElement instanceof Package) {
            return (Package) namedElement;
        } else {
            Package createdPackage = parent.createNestedPackage(name);

            return createdPackage;
        }
    }

    /**
     * transform attributes for target class or interface
     * 
     * @param sourceType
     * @param targetType
     * @param targetStructureTransformationData
     */
    private static void transformAttributes(Type sourceType, Type targetType,
                                            TargetStructureTransformationData targetStructureTransformationData) {
        EList<Property> sourceProperties = null;
        Property sourceProperty = null;
        Property targetProperty = null;
        boolean isNewlyCreated = false;

        // Source(분석 모델)요소가 Class인 경우
        if (sourceType instanceof Class) {
            sourceProperties = ((Class) sourceType).getOwnedAttributes();
            // Source(분석 모델)요소가 Interface인 경우
        } else if (sourceType instanceof Interface) {
            sourceProperties = ((Interface) sourceType).getOwnedAttributes();
        }

        for (Iterator<Property> propertyIterator = sourceProperties.iterator(); propertyIterator.hasNext();) {
            sourceProperty = propertyIterator.next();

            if (targetType instanceof Interface) {
                targetProperty = ((Interface) targetType).getOwnedAttribute(sourceProperty.getName(), null);

                if (targetProperty == null) {
                    /**
                     * 2011.03.09 조승현 원래는 모든 property 의 Type을 다 복사해야 맞지만,
                     * Primitive 타입이 아닐 경우에는 아직 만들어 지지 않은 Type(Class) 일 수도 있어서,
                     * 현재는 Primitive 타입일 경우에만 Type을 복사 함. 전개 순서를 패키지 -> Type ->
                     * Attribute -> Operation... 으로 바꾸는 작업이 선행 되어야 함.
                     */
                    if (sourceProperty.getType() instanceof PrimitiveType) {
                        targetProperty = ((Class) targetType).createOwnedAttribute(sourceProperty.getName(),
                            sourceProperty.getType());
                    } else {
                        targetProperty = ((Class) targetType).createOwnedAttribute(sourceProperty.getName(), null);
                    }

                    isNewlyCreated = true;
                }
            } else if (targetType instanceof Class) {
                targetProperty = ((Class) targetType).getOwnedAttribute(sourceProperty.getName(), null);
                /**
                 * 2011.03.09 조승현 원래는 모든 property 의 Type을 다 복사해야 맞지만, Primitive
                 * 타입이 아닐 경우에는 아직 만들어 지지 않은 Type(Class) 일 수도 있어서, 현재는 Primitive
                 * 타입일 경우에만 Type을 복사 함. 전개 순서를 패키지 -> Type -> Attribute ->
                 * Operation... 으로 바꾸는 작업이 선행 되어야 함.
                 */
                if (targetProperty == null) {
                    if (sourceProperty.getType() instanceof PrimitiveType) {
                        targetProperty = ((Class) targetType).createOwnedAttribute(sourceProperty.getName(),
                            sourceProperty.getType());
                    } else {
                        targetProperty = ((Class) targetType).createOwnedAttribute(sourceProperty.getName(), null);
                    }

                    isNewlyCreated = true;
                }
            }

            if (targetStructureTransformationData != null
                && targetStructureTransformationData.getPropertyApplicableStereotype() != null
                && targetProperty != null) {
                applyStereotype(sourceProperty, targetProperty, targetStructureTransformationData.getPropertyApplicableStereotype());
            }

            // 신규 생성인 경우
            if (isNewlyCreated) {
                // 코멘트를 복사한다
                copyComment(sourceProperty, targetProperty);
            }

            targetProperty.setVisibility(sourceProperty.getVisibility());
        }
    }

    /**
     * transform operations for target class or interface
     * 
     * @param sourceType
     * @param targetType
     * @param transformationData
     * @param targetStructureTransformationData
     */
    private static void transformOperations(Type sourceType, Type targetType,
                                            MDADesignerTransformationData transformationData,
                                            TargetStructureTransformationData targetStructureTransformationData) {
        // null 확인
        if (sourceType == null || targetType == null
            || !(sourceType instanceof Class || sourceType instanceof Interface)
            || !(targetType instanceof Class || targetType instanceof Interface)) {
            return;
        }

        EList<Operation> sourceOperations = null;
        Operation sourceOperation = null;
        Operation targetOperation = null;
        
        // Source(분석 모델)요소가 Class인 경우
        if (sourceType instanceof Class) {
            sourceOperations = ((Class) sourceType).getOwnedOperations();
            // Source(분석 모델)요소가 Interface인 경우
        } else if (sourceType instanceof Interface) {
            sourceOperations = ((Interface) sourceType).getOwnedOperations();
        }

        for (Iterator<Operation> oiter = sourceOperations.iterator(); oiter.hasNext();) {
            sourceOperation = oiter.next();

            if (targetType instanceof Interface || targetType instanceof Class) {
                if (targetStructureTransformationData == null) {
                    targetOperation = createTargetOperation(sourceOperation, targetType, transformationData, 
                        null);
                } else {
                    targetOperation = createTargetOperation(sourceOperation, targetType, transformationData, 
                        targetStructureTransformationData.getParentType());
                }
            }

            if (targetOperation != null) {
                targetOperation.setVisibility(sourceOperation.getVisibility());
            }

            if (targetStructureTransformationData != null
                && targetStructureTransformationData.getOperationApplicableStereotype() != null
                && targetOperation != null) {
                applyStereotype(sourceOperation, targetOperation, targetStructureTransformationData.getOperationApplicableStereotype());
            }
        }
    }

    /**
     * create operation for target class/interface using source operation
     * 
     * @param sourceOperation
     * @param targetType
     *            target class or interface
     * @param transformationData
     * @param parentTypes 
     * @return
     */
    private static Operation createTargetOperation(Operation sourceOperation, Type targetType,
                                                   MDADesignerTransformationData transformationData, EList<ParentType> parentTypes) {
        // null 확인
        if (sourceOperation == null || targetType == null) {
            return null;
        }

        Operation targetOperation = null;
        boolean isNewlyCreated = false;
        
        if (targetType instanceof Class) {
//        	targetOperation = SequenceManagerUtil.getOperationFromAll(targetType, sourceOperation.getName());
            targetOperation = ((Class) targetType).getOwnedOperation(sourceOperation.getName(), null, null);

            if (targetOperation == null) {
                targetOperation = ((Class) targetType).createOwnedOperation(sourceOperation.getName(), null, null);

                isNewlyCreated = true;
            }
        } else if (targetType instanceof Interface) {
            targetOperation = ((Interface) targetType).getOwnedOperation(sourceOperation.getName(), null, null);

            if (targetOperation == null) {
                targetOperation = ((Interface) targetType).createOwnedOperation(sourceOperation.getName(), null, null);

                isNewlyCreated = true;
            }
        }

        // 신규 생성인 경우
        if (isNewlyCreated) {
            // 코멘트를 복사한다
            copyComment(sourceOperation, targetOperation);
        }

        // Parameter 변환 처리
        transformParameters(sourceOperation, targetOperation, transformationData);

        return targetOperation;
    }

    /**
     * transform parameters of source operation using the transformation data
     * 
     * @param sourceOperation
     * @param targetOperation
     * @param transformationData
     */
    private static void transformParameters(Operation sourceOperation, Operation targetOperation,
                                            MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceOperation == null || targetOperation == null || !isValid(transformationData)) {
            return;
        }

        if (targetOperation.getOwnedParameters() != null) {
            targetOperation.getOwnedParameters().clear();
        }

        EList<Parameter> sourceParameters = sourceOperation.getOwnedParameters();

        if (sourceParameters != null) {
            for (Iterator<Parameter> iter = sourceParameters.iterator(); iter.hasNext();) {
                Parameter sourceParameter = iter.next();

                if (sourceParameter.getType() != null) {
                    Type sourceParameterType = sourceParameter.getType();
                    Type targetParameterType = null;

                    if ((sourceParameterType instanceof Class || sourceParameterType instanceof Interface)
                        && sourceParameterType.getModel() != null
                        && sourceParameterType.getModel().getName().equals(sourceOperation.getModel().getName())) {
                        targetParameterType = getTargetType(sourceParameterType, transformationData);
                    }

                    if (targetParameterType == null
                        && (sourceParameterType.getModel() == null || !sourceParameterType.getModel()
                            .getName()
                            .equals(sourceOperation.getModel().getName()))) {
                        targetParameterType = sourceParameterType;
                    }

                    Parameter targetParameter = (Parameter) EcoreUtil.copy(sourceParameter);
                    targetParameter.setType(targetParameterType);
                    targetOperation.getOwnedParameters().add(targetParameter);
                    
//                    System.out.println(((NamedElement)targetParameterType).getQualifiedName());
                }
            }
        }
    }

    /**
     * get target type for source type
     * 
     * @param sourceType
     * @param transformationData
     * @return
     */
    public static Type getTargetType(Type sourceType, MDADesignerTransformationData transformationData) {
        // null 확인
        if (sourceType == null || !(sourceType instanceof Class || sourceType instanceof Interface)
            || !isValid(transformationData)) {
            return null;
        }

        Package targetBase = getTargetBasePackage(sourceType, transformationData);
        if (targetBase == null) {
            return null;
        }

        EList<SourceStructureTransformationData> sourceStructureTransformationDataList = transformationData.getRuleSet()
            .getStructureRules();
        SourceStructureTransformationData structureTransformationData = null;
        List<Type> targetTypes = new ArrayList<Type>();
        List<Type> tempTargetTypes = null;
        int isTransformed = 0;

        // Source요소(분석 모델)에 Stereotype이 적용된 요소의 경우
        for (Iterator<SourceStructureTransformationData> iter = sourceStructureTransformationDataList.iterator(); iter.hasNext();) {
            structureTransformationData = iter.next();

            if (isAppliedStereotype(sourceType, structureTransformationData.getSourceType().getSourceName())) {
                isTransformed = 1;
                tempTargetTypes = getTargetTypes(sourceType, targetBase, structureTransformationData);

                if (tempTargetTypes != null) {
                    targetTypes.addAll(tempTargetTypes);
                }
            }
        }

        // Source요소(분석 모델)에 Stereotype이 적용안 된 일반 요소인 경우
        if (isTransformed == 0) {
            Type copiedType = copyType(sourceType, targetBase);

            if (copiedType != null) {
                targetTypes.add(copiedType);
            }
        }

        if (targetTypes != null) {
            return targetTypes.get(0);
        }

        return null;
    }

    /**
     * get target types for source type using transformation data
     * 
     * @param source
     * @param targetBasePackage
     * @param structuredTransformationData
     * @return
     */
    private static List<Type> getTargetTypes(Type source, Package targetBasePackage,
                                             SourceStructureTransformationData structuredTransformationData) {
        // null 확인
        if (targetBasePackage == null || structuredTransformationData == null || source == null
            || !(source instanceof Class || source instanceof Interface)) {
            return null;
        }

        EList<TargetStructureTransformationData> tList = structuredTransformationData.getTargetStructureTransformationDataSet();
        TargetStructureTransformationData targetStructureTransformationData = null;
        LocationType locationType = null;
        Package targetParentPackage = null;
        EList<LocationSegment> locationSegments = null;
        List<LocationSegment> sortedLocationSegments = null;
        LocationSegment locationSegment = null;
        Type targetType = null;

        List<Type> targetTypes = new ArrayList<Type>();

        // Source(분석 모델) 변환규칙에 해당하는 Target(설계 모델) 변환규칙 갯수만큼 반복
        for (Iterator<TargetStructureTransformationData> iter = tList.iterator(); iter.hasNext();) {
            targetStructureTransformationData = iter.next();

            locationType = targetStructureTransformationData.getTargetLocation();
            targetParentPackage = targetBasePackage;
            locationSegments = locationType.getLocation();
            sortedLocationSegments = MDADesignerTransformationUtil.sort(locationSegments);

            if (sortedLocationSegments != null && sortedLocationSegments.size() > 1) {
                for (int segmentCount = 0; segmentCount < sortedLocationSegments.size(); segmentCount++) {
                    locationSegment = sortedLocationSegments.get(segmentCount);

                    // Target(설계 모델) 부모 패키지 생성
                    if (locationSegment.getLocationName() != null
                        && !ManagerConstant.EMPTY_STRING.equals(locationSegment.getLocationName().trim())) {
                        targetParentPackage = createPackage(targetParentPackage, locationSegment.getLocationName());
                    }
                }
            }

            // Prefix가 있는 경우 설정
            String targetName = ManagerConstant.EMPTY_STRING;
            if (targetStructureTransformationData.getTargetName().getPrefix() != null) {
                targetName += targetStructureTransformationData.getTargetName().getPrefix();
            }
            // Postfix가 있는 경우 설정
            targetName += source.getName();
            if (targetStructureTransformationData.getTargetName().getPostfix() != null) {
                targetName += targetStructureTransformationData.getTargetName().getPostfix();
            }

            NamedElement ne = targetParentPackage.getOwnedMember(targetName);

            // Creation Type이 Class인 경우
            if (TargetType.CLASS_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (ne != null && ne instanceof Class) {
                    targetType = (Class) ne;
                } else {
                    targetType = targetParentPackage.createOwnedClass(targetName, false);
                }
                // Creation Type이 Interface인 경우
            } else if (TargetType.INTERFACE_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (ne != null && ne instanceof Interface) {
                    targetType = (Interface) ne;
                } else {
                    targetType = targetParentPackage.createOwnedInterface(targetName);
                }
                // Creation Type이 Component인 경우
            } else if (TargetType.COMPONENT_VALUE == targetStructureTransformationData.getTargetType().getValue()) {
                if (ne != null && ne instanceof Component) {
                    targetType = (Component) ne;
                } else {
                    targetType = UMLHelper.createComponent();
                    targetParentPackage.getPackagedElements().add(targetType);
                }
            }

//            if (targetType != null
//                && targetStructureTransformationData.getTargetCreationType().getValue() == TargetCreationType.SELF_VALUE) {
                targetTypes.add(targetType);
//            }

            if (targetStructureTransformationData.getTargetApplicableStereotype() != null) {
                applyStereotype(source, targetType, targetStructureTransformationData.getTargetApplicableStereotype());
            }
            
        }

        return targetTypes;
    }

    /**
     * copy a source type to target package
     * 
     * @param sourceType
     * @param targetPackage
     * @return
     */
    private static Type copyType(Type sourceType, Package targetPackage) {
        // null 확인
        if (sourceType == null || targetPackage == null) {
            return null;
        }

        EObject targetEobject = null;
        NamedElement targetNamedElement = targetPackage.getOwnedMember(sourceType.getName());

        if (targetNamedElement != null) {
            if (targetNamedElement instanceof Class && sourceType instanceof Class) {
                return (Type) targetNamedElement;
            } else if (targetNamedElement instanceof Interface && sourceType instanceof Interface) {
                return (Type) targetNamedElement;
            }
        }

        targetEobject = EcoreUtil.copy((EObject) sourceType);

        if (targetEobject instanceof Type) {
            targetPackage.getPackagedElements().add((Type) targetEobject);

            return (Type) targetEobject;
        }

        return null;
    }

    /**
     * 요소에 스테레오타입 이름에 해당하는 스테레오타입을 찾아 적용하는 메소드
     * 
     * @param source
     * @param target
     * @param stereotypeName
     * @return
     */
    private static Stereotype applyStereotype(Element source, Element target, String stereotypeName) {
        // null 확인
        if (source == null || target == null || stereotypeName == null) {
            return null;
        }

        Stereotype stereotype = target.getAppliedStereotype(stereotypeName);
        if (stereotype == null) {
            stereotype = getApplicableStereotype(target, stereotypeName);

            if (stereotype != null) {
                if (!isAppliedStereotype(target, stereotypeName)) {
                    target.applyStereotype(stereotype);
                    
                    // 소스와 타겟에 같은 스테레오타입이 적용되어 있을 때는 스테레오타입의 특성값도 함께 적용해준다.
                    Stereotype sType = source.getAppliedStereotype(stereotype.getQualifiedName());
                    if( null != sType) {
                        EList<Property> attributes = sType.getAllAttributes();
                        for (Property property : attributes) {
                            String propertyName = property.getName();
                            Object obj = source.getValue(sType, propertyName);
                            if( null != obj && !propertyName.startsWith(Extension.METACLASS_ROLE_PREFIX)) {
                                target.setValue(stereotype, propertyName, obj);
                            }
                        }
                    }
                }
            }
        }

        return stereotype;
    }

    /**
     * 설계 모델 구조 생성 코드 끝
     * ========================================================================
     */

    /**
     * ========================================================================
     * 설계 모델 행위 생성 코드 시작
     */

    /**
     * find target interaction with source interaction and target model uri
     * 
     * @param targetModelUri
     * @param sourceInteraction
     * @return Interaction
     */
    public static Interaction findTargetInteraction(URI targetModelUri, Interaction sourceInteraction) {
        // null 확인
        if (sourceInteraction == null || targetModelUri == null) {
            return null;
        }

        Model targetModel = DomainModelHandlerUtil.openModel(targetModelUri);
        if (targetModel == null) {
            return null;
        }

        return findTargetInteraction(targetModel, sourceInteraction);
    }

    /**
     * find target interaction with source interaction and target model
     * 
     * @param targetModel
     * @param sourceInteraction
     * @return
     */
    public static Interaction findTargetInteraction(Model targetModel, Interaction sourceInteraction) {
        // null 확인
        if (sourceInteraction == null || targetModel == null) {
            return null;
        }

        String sourceInteractionQualifiedName = sourceInteraction.getQualifiedName();
        String targetInteractionQualifiedName = targetModel.getName()
            + sourceInteractionQualifiedName.substring(sourceInteractionQualifiedName.indexOf(ManagerConstant.QUALIFIED_SEGMENTATION_STRING));

        return getInteraction(targetModel, targetInteractionQualifiedName);
    }

    /**
     * find interaction through scanning eobject
     * 
     * @param eobject
     * @param qualifiedName
     * @return
     */
    private static Interaction getInteraction(EObject eobject, String qualifiedName) {
        // null 확인
        if (eobject == null) {
            return null;
        }

        if (eobject instanceof Interaction) {
            Interaction interaction = (Interaction) eobject;

            if (interaction.getQualifiedName().equals(qualifiedName)) {
                return interaction;
            }
        } else {
            EObject eobj = null;

            for (Iterator iter = eobject.eContents().iterator(); iter.hasNext();) {
                eobj = (EObject) iter.next();
                Interaction tmpInteraction = getInteraction(eobj, qualifiedName);

                if (tmpInteraction != null) {
                    return tmpInteraction;
                }
            }
        }

        return null;
    }

    /**
     * Interaction 안의 라이프라인 목록 가져오기
     * 
     * @param interaction
     *            parent interaction
     * @return
     */
    public static List<Lifeline> getLifelines(Interaction interaction) {
        // null 확인
        if (interaction == null) {
            return null;
        }

        return interaction.getLifelines();
    }

    /**
     * find target transformed lifelines' types of the source lifeline
     * 
     * @param sourceResource 분석모델의 리소스
     * @param sourceLifeline
     * @param transformationData
     * @return
     */
    public static List<Type> getTargetLifeLineTypeList(Resource sourceResource, Lifeline sourceLifeline,
                                                       MDADesignerTransformationData transformationData) {
        // null 확인
        if (transformationData == null || sourceLifeline == null) {
            return null;
        }

        if (sourceLifeline.getRepresents() != null) {
            Type type = sourceLifeline.getRepresents().getType();

            if (type != null) {
                List<Type> result = new ArrayList<Type>();

                if (type instanceof Actor) {
                    result.add(type);
                } else if (type instanceof Class || type instanceof Interface) {
                    Package targetBasePackage = getTargetBasePackage(type, transformationData);

                    if (targetBasePackage == null) {
                        // 소스모델과 라이프라인의 리소스가 다를 경우 targetLifeLineTypeList를 수동으로 만들어준다.
                        if( !type.eResource().equals(sourceResource)) {
                            result.add(type);
                            return result;
                        } else {
                            return null;
                        }
                    }

                    return transformClassOrInterface(type, targetBasePackage, transformationData);
                } else {
                    result.add(type);
                }

                return result;
            }
        }

        return null;
    }

    /**
     * lifeline 추가
     * 
     * @param interaction
     * @param lifelineName
     * @param type
     * @return
     */
    public static Lifeline addLifeline(Interaction interaction, String lifelineName, Type type) {
        // null 확인
        if (interaction == null || lifelineName == null) {
            return null;
        }

        int exists = 0;
        Lifeline lifeline = null;
        Lifeline existingLifeline = null;
        Type existingLifelineType = null;

        EList<Lifeline> lifelines = interaction.getLifelines();
        for (Iterator<Lifeline> lifeLineIterator = lifelines.iterator(); lifeLineIterator.hasNext();) {
            existingLifeline = lifeLineIterator.next();

            if (existingLifeline.getName().equals(lifelineName)) {
                if (existingLifeline.getRepresents() != null) {
                    existingLifelineType = existingLifeline.getRepresents().getType();

                    if (type.equals(existingLifelineType)) {
                        lifeline = existingLifeline;
                        exists = 1;

                        break;
                    }
                }
            }
        }

        if (exists == 0) {
            lifeline = interaction.createLifeline(lifelineName);

            StructuredClassifier node = (StructuredClassifier) interaction.getOwner();
            Property property = node.createOwnedAttribute(UMLManager.getPackagedUniqueName(node,
                UMLMessage.getMessage(UMLMessage.LABEL_ATTRIBUTE)), null);
            lifeline.setRepresents(property);

            if (type != null && lifeline.getRepresents() != null) {
                lifeline.getRepresents().setType(type);
            }
        }

        return lifeline;
    }

    /**
     * 메세지 목록 가져오기
     * 
     * @param interaction
     * @return
     */
    public static List<Message> getMessages(Interaction interaction) {
        if (interaction == null) {
            return null;
        }

        return interaction.getMessages();
    }

    /**
     * find the sender of message
     * 
     * @param message
     * @return
     */
    public static Lifeline getSenderLifeline(Message message) {
        if (message == null) {
            return null;
        }

        MessageEnd sendEvent = message.getSendEvent();
        MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) sendEvent;
        EList<Lifeline> covereds = messageOccurrenceSpecification.getCovereds();

        if (covereds != null && covereds.size() > 0) {
            return covereds.get(0);
        }

        return null;
    }

    /**
     * find the receiver for a message
     * 
     * @param message
     * @return
     */
    public static Lifeline getReceiverLifeline(Message message) {
        if (message == null) {
            return null;
        }

        MessageEnd receiveEvent = message.getReceiveEvent();
        MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) receiveEvent;
        EList<Lifeline> covereds = mos.getCovereds();

        if (covereds != null && covereds.size() > 0) {
            return covereds.get(0);
        }

        return null;
    }

    /**
     * find target sender
     * 
     * @param sourceInteraction
     * @param targetInteraction
     * @param senderDataName
     * @param source
     * @param transformationData
     * @return
     */
    public static Lifeline getTargetSenderLifeline(Interaction sourceInteraction, Interaction targetInteraction, String senderDataName,
                                                   Lifeline source, MDADesignerTransformationData transformationData) {
        if (!isValid(transformationData) || source == null) {
            return null;
        }

        List<Type> targetTypeList = getTargetLifeLineTypeList(sourceInteraction.eResource(), source, transformationData);
        List<BehaviorTransformationDetailData> behaviorTransformationDetailDataList = findSenderTransformationRule(source,
            transformationData.getRuleSet());
        for (Iterator<BehaviorTransformationDetailData> iter = behaviorTransformationDetailDataList.iterator(); iter.hasNext();) {
            BehaviorTransformationDetailData behaviorTransformationDetailData = iter.next();
            String dataName = behaviorTransformationDetailData.getTargetRelation().getSource().getDataName();
            if (dataName.equals(senderDataName)) {
                return getLifelineByDataNameAndTypeList(targetInteraction,
                    dataName,
                    transformationData.getRuleSet(),
                    targetTypeList);
            }
        }

        return null;
    }

    /**
     * find target receiver
     * 
     * @param sourceInteraction 
     * @param targetInteraction
     * @param receiverDataName
     * @param source
     * @param transformationData
     * @return
     */
    public static Lifeline getTargetReceiverLifeline(Interaction sourceInteraction, Interaction targetInteraction, String receiverDataName,
                                                     Lifeline source, MDADesignerTransformationData transformationData) {
        if (transformationData == null || source == null) {
            return null;
        }
        List<Type> targetTypeList = getTargetLifeLineTypeList(sourceInteraction.eResource(), source, transformationData);

        List<BehaviorTransformationDetailData> behaviorTransformationDetailDataList = findReceiverTransformationRule(source,
            transformationData.getRuleSet());
        for (Iterator<BehaviorTransformationDetailData> iter = behaviorTransformationDetailDataList.iterator(); iter.hasNext();) {
            BehaviorTransformationDetailData behaviorTransformationDetailData = iter.next();
            String dataName = behaviorTransformationDetailData.getTargetRelation().getTarget().getDataName();
            if (dataName.equals(receiverDataName)) {
                return getLifelineByDataNameAndTypeList(targetInteraction,
                    dataName,
                    transformationData.getRuleSet(),
                    targetTypeList);
            }
        }

        return null;
    }

    /**
     * 메세지 추가
     * 
     * @param interaction
     *            target interaction
     * @param sender
     *            source lifeline
     * @param receiver
     *            target lifeline
     * @param msgName
     *            message name
     * @param msgSort
     *            message sort
     * @param operation
     *            operation
     * @param remark description
     * @param stereotype stereotype to apply to message
     */
    public static void addMessage(Interaction interaction, Lifeline sender, Lifeline receiver, String msgName,
                                  MessageSort msgSort, Operation operation, String remark, String stereotype) {
        if (interaction == null || sender == null || receiver == null) {
            return;
        }

        Message createdMessage = interaction.createMessage(msgName);
        createdMessage.setMessageSort(msgSort);

        Package parentPackage = interaction.getNearestPackage();

        SendOperationEvent sendOperationEvent = (SendOperationEvent) parentPackage.createPackagedElement(null,
            UMLPackage.Literals.SEND_OPERATION_EVENT);

        // sendEventOccur.setEvent(sendOpEvent);
        ReceiveOperationEvent receiveOperationEvent = (ReceiveOperationEvent) parentPackage.createPackagedElement(null,
            UMLPackage.Literals.RECEIVE_OPERATION_EVENT);

//        ExecutionEvent executionEvent = (ExecutionEvent) parentPackage.createPackagedElement(null,
//            UMLPackage.Literals.EXECUTION_EVENT);

        if (operation != null) {
            sendOperationEvent.setOperation(operation);
            receiveOperationEvent.setOperation(operation);
        }

        MessageOccurrenceSpecification sendEventOccur = (MessageOccurrenceSpecification) interaction.createFragment(null,
            UMLPackage.Literals.MESSAGE_OCCURRENCE_SPECIFICATION);
        sendEventOccur.setEvent(sendOperationEvent);

        sender.getCoveredBys().add(sendEventOccur);

        sendEventOccur.setMessage(createdMessage);

        MessageOccurrenceSpecification recvEventOccur = (MessageOccurrenceSpecification) interaction.createFragment(null,
            UMLPackage.Literals.MESSAGE_OCCURRENCE_SPECIFICATION);
        // recvEventOccur.getCovereds().add(lifelineRecv);
        receiver.getCoveredBys().add(recvEventOccur);
        recvEventOccur.setMessage(createdMessage);
        recvEventOccur.setEvent(receiveOperationEvent);

//        MessageOccurrenceSpecification exeEventOccur = (MessageOccurrenceSpecification) interaction.createFragment(null,
//            UMLPackage.Literals.MESSAGE_OCCURRENCE_SPECIFICATION);
//        // exeEventOccur.getCovereds().add(lifelineRecv);
//        receiver.getCoveredBys().add(exeEventOccur);
//        exeEventOccur.setEvent(executionEvent);

        createdMessage.setSendEvent(sendEventOccur);
        createdMessage.setReceiveEvent(recvEventOccur);

        BehaviorExecutionSpecification recvExecutionSpec = (BehaviorExecutionSpecification) interaction.createFragment(null,
            UMLPackage.Literals.BEHAVIOR_EXECUTION_SPECIFICATION);
        recvExecutionSpec.getCovereds().add(receiver);

        if (recvExecutionSpec.getStart() == null) {
            recvExecutionSpec.setStart(recvEventOccur);
        }

        recvExecutionSpec.setFinish(recvEventOccur); //
        
        // remark 내용을 받아서 문서 내용에 저장
		if (remark != null) {
			Comment comment = UMLFactory.eINSTANCE.createComment();
			comment.setBody(remark);
			((NamedElement) createdMessage).getOwnedComments().clear();
			((NamedElement) createdMessage).getOwnedComments().add(comment);
		}
        
		// 메시지에 스테레오타입 적용(오퍼레이션이 아님!). 스테레오타입으로 적용할 수 없으면 키워드에 적용함.
		if (stereotype != null && !ManagerConstant.EMPTY_STRING.equals(stereotype)) {
			if (!isAppliedStereotype((Element) createdMessage, stereotype) || 
					!createdMessage.getKeywords().contains(stereotype)) {
				Stereotype stereo = getApplicableStereotype(createdMessage, stereotype);
				if (stereo != null) {
					createdMessage.applyStereotype(stereo);
				} else {
					createdMessage.addKeyword(stereotype);
				}
			}
			
		}
    }
    
    /**
     * super type을 확인하고, super에 같은 이름의 오퍼레이션이 없을 경우에는
     * 새 오퍼레이션을 추가하여 리턴한다.
     * 
     * @param receiver
     * @param operationName
     * @return
     */
    public static Operation getOperation(Lifeline receiver, String operationName) {
        if (receiver == null || operationName == null) {
            return null;
        }

        Operation operation = null;
        if (receiver.getRepresents() != null && receiver.getRepresents().getType() != null) {
            Type type = receiver.getRepresents().getType();
            
            List<Operation> operations = SequenceManagerUtil.getTypeOperations(type);
            for (Operation superOperation : operations) {
				if (operationName.equals(superOperation.getName())) {
					return superOperation;
				}
			}

            if (type instanceof Class) {
                Class c = (Class) type;
                operation = c.getOperation(operationName, null, null);
                if (operation == null) {
                    operation = c.createOwnedOperation(operationName, null, null);
                }
            } else if (type instanceof Interface) {
                Interface c = (Interface) type;
                operation = c.getOperation(operationName, null, null);
                if (operation == null) {
                    operation = c.createOwnedOperation(operationName, null, null);
                }
            }
        }

        return operation;
    }

    /**
     * 설계 모델 행위 생성 코드 끝
     * ========================================================================
     */

    /**
     * find a list of behavior transformationDetail when lifeline is a receiver
     * 
     * @param source
     * @param ruleSet
     * @return
     */

    public static List<BehaviorTransformationDetailData> findReceiverTransformationRule(Lifeline source, RuleSet ruleSet) {

        if (source == null || ruleSet == null)
            return null;
        List<BehaviorTransformationDetailData> result = new ArrayList<BehaviorTransformationDetailData>();
        EList<BehaviorTransformationData> behaviorTransformationDataList = ruleSet.getBehaviorRules();

        BehaviorTransformationData behaviorTransformationData = null;
        Type sourceType = null;
        if (behaviorTransformationDataList != null)

            for (Iterator<BehaviorTransformationData> iter = behaviorTransformationDataList.iterator(); iter.hasNext();) {
                behaviorTransformationData = iter.next();

                String sourceName = behaviorTransformationData.getSourceRelation().getTarget().getSourceName();
                sourceType = source.getRepresents().getType();
                if (source.getRepresents() != null && sourceType != null) {
                    if (ManagerConstant.UML_ACTOR_LITERAL.equals(sourceName) && sourceType instanceof Actor) {
                        result.addAll(behaviorTransformationData.getBehaviorTransformationDetailDataSet());

                    } else if (isAppliedStereotype(sourceType, sourceName)) {
                        result.addAll(behaviorTransformationData.getBehaviorTransformationDetailDataSet());

                    } else if (MDDCoreConstant.TYPE.equals(sourceName) && sourceType instanceof Type
                        && sourceType.getAppliedStereotypes().size() == 0) {
                        result.addAll(behaviorTransformationData.getBehaviorTransformationDetailDataSet());
                    }
                }

            }
        return result;
    }

    /**
     * find a list of behavior transformationDetail when lifeline is a sender
     * 
     * @param sourceLifeline
     * @param ruleSet
     * @return
     */
    public static List<BehaviorTransformationDetailData> findSenderTransformationRule(Lifeline sourceLifeline,
                                                                                      RuleSet ruleSet) {
        if (sourceLifeline == null || ruleSet == null)
            return null;
        List<BehaviorTransformationDetailData> result = new ArrayList<BehaviorTransformationDetailData>();
        EList<BehaviorTransformationData> behaviorTransformationDataList = ruleSet.getBehaviorRules();

        BehaviorTransformationData behaviorTransformationData = null;
        if (behaviorTransformationDataList != null)

            for (Iterator<BehaviorTransformationData> iter = behaviorTransformationDataList.iterator(); iter.hasNext();) {
                behaviorTransformationData = iter.next();

                String sourceName = behaviorTransformationData.getSourceRelation().getSource().getSourceName();

                if (sourceName.equals(ManagerConstant.UML_ACTOR_LITERAL)) {
                    if (sourceLifeline.getRepresents() != null && sourceLifeline.getRepresents().getType() != null) {
                        if (sourceLifeline.getRepresents().getType() instanceof Actor)
                            result.addAll(behaviorTransformationData.getBehaviorTransformationDetailDataSet());
                    }

                } else if (sourceLifeline.getRepresents() != null && sourceLifeline.getRepresents().getType() != null) {
                    if (isAppliedStereotype(sourceLifeline.getRepresents().getType(), sourceName))
                        result.addAll(behaviorTransformationData.getBehaviorTransformationDetailDataSet());
                }
            }
        return result;
    }

    /**
     * find target interaction with source interaction and target model uri
     * 
     * @param targetModelUri
     * @param sourceInteraction
     * @return
     */
    public static Interaction findTargetInteraction(String targetModelUri, Interaction sourceInteraction) {
        if (sourceInteraction == null) {
            return null;
        }

        URI uri = URI.createURI(targetModelUri);

        Model targetModel = DomainModelHandlerUtil.openModel(uri);
        if (targetModel == null) {
            return null;
        }

        return findTargetInteraction(targetModel, sourceInteraction);

    }

    /**
     * 스테레오타입 이름으로 요소에 적용 가능한 스테레오타입 목록에서 스테레오타입 찾아오는 메소드
     * 
     * @param elememt
     * @param stereotypeName
     * @return
     */
    private static Stereotype getApplicableStereotype(Element elememt, String stereotypeName) {
        if (elememt == null || stereotypeName == null) {
            return null;
        }

        EList<Stereotype> applicableStereotypes = elememt.getApplicableStereotypes();
        Stereotype stereotype = null;

        if (applicableStereotypes != null) {
            for (Iterator<Stereotype> stereoTypeIterator = applicableStereotypes.iterator(); stereoTypeIterator.hasNext();) {
                stereotype = stereoTypeIterator.next();

                if (stereotype.getName().equals(stereotypeName)) {
                    return stereotype;
                }
            }
        }

        return null;
    }

    /**
     * find applicable stereotypes for meta class
     * 
     * @param profile
     * @param metaClassName
     * @return
     */

    public static List<Stereotype> getApplicableStereotypes(Profile profile, String metaClassName) {

        if (profile == null || metaClassName == null)
            return null;

        List<Stereotype> applicableStereotypes = new ArrayList<Stereotype>();

        for (Iterator<Stereotype> iter = profile.getOwnedStereotypes().iterator(); iter.hasNext();) {
            Stereotype stereotype = (Stereotype) iter.next();
            EList<Class> extendedMetaClasses = stereotype.getExtendedMetaclasses();
            for (int clazzCount = 0; clazzCount < extendedMetaClasses.size(); clazzCount++) {
                Class clazz = extendedMetaClasses.get(clazzCount);
                if (clazz.getName().equals(metaClassName))
                    applicableStereotypes.add(stereotype);
            }

        }

        return applicableStereotypes;

    }

    /**
     * 요소 이름이 중복될 경우 유일한 이름을 가져오는 메소드
     * 
     * @param element
     * @param parent
     * @param name
     * @return
     */
    private static String getCopiedUniqueName(NamedElement element, Namespace parent, String name) {
        int index = 1;
        String newName = null;

        while (true) {
            newName = UMLMessage.getMessage(UMLMessage.LABEL_COPYOF) + Integer.toString(index++)
                + ManagerConstant.UNDER_BAR + name;
            if (null == parent.getOwnedMember(newName)) {
                return newName;
            }

            if (10000 <= index) {
                return name + Integer.toString(UMLManager.NAME_INDEX++);
            }
        }
    }

    /**
     * find interaction through scanning eobject
     * 
     * @param eobject
     * @param result
     */
    private static void getInteractions(EObject eobject, List<Interaction> result) {
        if (eobject instanceof Interaction) {
            result.add((Interaction) eobject);
        } else {
            EObject eobj = null;
            for (Iterator iter = eobject.eContents().iterator(); iter.hasNext();) {
                eobj = (EObject) iter.next();
                getInteractions(eobj, result);
            }
        }

    }

    /**
     * find the corresponding lifeline using data name and type list
     * 
     * @param targetInteraction
     * @param dataName
     * @param ruleSet
     * @param typeList
     * @return
     */
    public static Lifeline getLifelineByDataNameAndTypeList(Interaction targetInteraction, String dataName,
                                                            RuleSet ruleSet, List<Type> typeList) {
        if (targetInteraction == null || dataName == null || ruleSet == null || typeList == null)
            return null;
        String stereotypeName = getRelatedStereotypeNameFromDataName(dataName, ruleSet);

        EList<Lifeline> lifelines = targetInteraction.getLifelines();
        Lifeline result = null;
        for (Iterator<Lifeline> lifelineIterator = lifelines.iterator(); lifelineIterator.hasNext();) {
            result = lifelineIterator.next();
            if (result.getRepresents() != null) {
                Type type = result.getRepresents().getType();
                Type candidateType = null;
                for (Iterator<Type> typeIterator = typeList.iterator(); typeIterator.hasNext();) {
                    candidateType = typeIterator.next();
                    if (candidateType.getQualifiedName() != null
                        && candidateType.getQualifiedName().equals(type.getQualifiedName())) {
                        if (stereotypeName == null && (type instanceof Actor || type instanceof Type)) {
                            return result;
                        }

                        if (stereotypeName != null && isAppliedStereotype(type, stereotypeName))
                            return result;
                    }
                }

            }
        }
        for (Iterator<Lifeline> lifelineIterator = lifelines.iterator(); lifelineIterator.hasNext();) {
            result = lifelineIterator.next();
            Type candidateType = null;
            if (result.getRepresents() != null) {
                candidateType = result.getRepresents().getType();

                if (stereotypeName == null && (candidateType instanceof Actor || candidateType instanceof Type))
                    return result;

                if (stereotypeName != null && isAppliedStereotype(candidateType, stereotypeName))
                    return result;

            }
        }

        return null;

    }

    /**
     * operations 가져오기
     * 
     * @param recLifeline
     *            lifeline receiver
     * @return
     */
    public static List<Operation> getOperations(Lifeline recLifeline) {
        if (recLifeline != null && recLifeline.getRepresents() != null && recLifeline.getRepresents().getType() != null) {
            Type type = recLifeline.getRepresents().getType();
            if (type instanceof Class) {
                Class clazz = (Class) type;
                return clazz.getOwnedOperations();
            }
        }
        return null;
    }

    /**
     * figure out the project of an eobject
     * 
     * @param eobj
     * @return
     */
    public static IProject getProject(EObject eobj) {

        if (eobj == null)
            return null;

        String eobjPath = eobj.eResource().getURI().toPlatformString(true);
        if (eobjPath == null)
            eobjPath = eobj.eResource().getURI().toString();
        if (eobjPath != null && eobjPath.startsWith(ManagerConstant.PLATFORM_RESOURCE_PREFIX))
            eobjPath = eobjPath.replace(ManagerConstant.PLATFORM_RESOURCE_PREFIX, ManagerConstant.EMPTY_STRING);
        String projectPath = null;
        int startPos = eobjPath.indexOf(ManagerConstant.SLASH);
        int endPos = eobjPath.indexOf(ManagerConstant.SLASH, startPos + 1);
        if (startPos != -1 && endPos != -1) {
            projectPath = eobjPath.substring(0, endPos);
        }

        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectPath);

        return project;
    }

    /**
     * find the corresponding stereotype name from rule set using data name
     * 
     * @param dataName
     * @param ruleSet
     * @return
     */
    public static String getRelatedStereotypeNameFromDataName(String dataName, RuleSet ruleSet) {
        if (dataName == null)
            return null;
        EList<SourceStructureTransformationData> sourceStructureTransformationDataList = ruleSet.getStructureRules();
        SourceStructureTransformationData structureTransformationData = null;
        TargetStructureTransformationData targetStructureTransformationData = null;
        if (sourceStructureTransformationDataList != null)
            for (Iterator<SourceStructureTransformationData> sourceIterator = sourceStructureTransformationDataList.iterator(); sourceIterator.hasNext();) {
                structureTransformationData = sourceIterator.next();
                EList<TargetStructureTransformationData> targetStructureTransformationDataList = structureTransformationData.getTargetStructureTransformationDataSet();
                if (targetStructureTransformationDataList != null)
                    for (Iterator<TargetStructureTransformationData> targetIterator = targetStructureTransformationDataList.iterator(); targetIterator.hasNext();) {
                        targetStructureTransformationData = targetIterator.next();
                        if (dataName.equals(targetStructureTransformationData.getDataName().getDataName()))
                            return targetStructureTransformationData.getTargetApplicableStereotype();
                    }
            }
        return null;
    }

    /**
     * get rule files from the project that contains the eobject
     * 
     * @param eobject
     * @return
     */
    public static List<IFile> getRuleFiles(EObject eobject) {
        IProject project = getProject(eobject);
        if (project == null)
            return null;
        List<IFile> ruleFileList = new ArrayList<IFile>();
        getRuleFiles(project, ruleFileList);
        return ruleFileList;

    }

    /**
     * scan and add rule files to the rule file list
     * 
     * @param resource
     * @param ruleFileList
     */
    private static void getRuleFiles(IResource resource, List<IFile> ruleFileList) {

        if (resource == null)
            return;
        try {
            if (resource instanceof IFile
                && ((IFile) resource).getName().endsWith(MDDCoreConstant.MDA_DESIGNER_RULE_FILE_EXTENTION)) {
                ruleFileList.add((IFile) resource);
            } else if (resource instanceof IFolder || resource instanceof IProject) {
                IResource[] members = null;
                if (resource instanceof IFolder)
                    members = ((IFolder) resource).members();
                else
                    members = ((IProject) resource).members();
                if (members != null)
                    for (IResource member : members) {
                        getRuleFiles(member, ruleFileList);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * find a list of related SourceStructureTransformationData for lifeline
     * 
     * @param sourceLifeline
     * @param ruleSet
     * @return
     */
    public static List<SourceStructureTransformationData> getSourceStructureTransformationDataList(
                                                                                                   Lifeline sourceLifeline,
                                                                                                   RuleSet ruleSet) {
        if (sourceLifeline == null || sourceLifeline.getRepresents() == null
            || sourceLifeline.getRepresents().getType() == null) {
            return null;
        }

        Type type = sourceLifeline.getRepresents().getType();
        EList<SourceStructureTransformationData> sourceStructureTransformationDataList = ruleSet.getStructureRules();
        SourceStructureTransformationData sourceStructureTransformationData = null;
        List<SourceStructureTransformationData> result = new ArrayList<SourceStructureTransformationData>();

        for (Iterator<SourceStructureTransformationData> iter = sourceStructureTransformationDataList.iterator(); iter.hasNext();) {
            sourceStructureTransformationData = iter.next();

            if (isAppliedStereotype(type, sourceStructureTransformationData.getSourceType().getSourceName())) {
                result.add(sourceStructureTransformationData);
            }
        }

        return result;
    }

    /**
     * check if the element is applied with the stereotype
     * 
     * @param element
     * @param stereotypeName
     * @return
     */
    public static boolean isAppliedStereotype(Element element, String stereotypeName) {
        if (element == null || stereotypeName == null)
            return false;
        EList<Stereotype> stList = element.getAppliedStereotypes();
        if (stList != null)
            for (Iterator<Stereotype> iter = stList.iterator(); iter.hasNext();) {
                Stereotype st = iter.next();
                if (st.getName().equals(stereotypeName))
                    return true;
            }

        return false;
    }

    /**
     * check if the transformation data is valid
     * 
     * @param transformationData
     * @return
     */
    public static boolean isValid(MDADesignerTransformationData transformationData) {
        Model sourceModel = null;
        if (transformationData.getSourceModelURI() != null) {
            sourceModel = DomainModelHandlerUtil.openModel(transformationData.getSourceModelURI());
        }
        Model targetModel = null;
        if (transformationData.getTargetModelURI() != null) {
            targetModel = DomainModelHandlerUtil.openModel(transformationData.getTargetModelURI());
        }

        if (transformationData == null || transformationData.getRuleSet() == null
        // || transformationData.getSourceModel() == null ||
            // transformationData.getTargetModel() == null) {
            || sourceModel == null || targetModel == null) {
            return false;
        }

        return true;
    }

    /**
     * interaction 가져오기
     * 
     * @param selectedObject
     *            selected element
     * @return
     */
    public static List<Interaction> getInteractions(EObject selectedObject) {
        if (selectedObject == null)
            return null;
        List<Interaction> result = new ArrayList<Interaction>();
        getInteractions(selectedObject, result);
        return result;

    }

    /**
     * load library with provided path
     * 
     * 
     * @param libraryPath
     * @return Package
     */
    public static Package loadLibrary(String libraryPath) {

        ResourceSet resourceSet = new ResourceSetImpl();

        Resource inputResource = resourceSet.createResource(URI.createFileURI(libraryPath));
        try {
            inputResource.load(null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Package library = DomainUtil.getUMLModelRoot(inputResource);
        return library;
    }

    /**
     * 모델파일 Path 반환
     * 
     * @param uriStr
     * @return String
     */
    public static String getModelFilePath(String uriStr) {
        String[] parseArray = uriStr.split(ManagerConstant.SLASH);
        String projectName = parseArray[2];

        IProject[] projectList = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (int i = 0; i < projectList.length; i++) {
            if (projectName.equals(projectList[i].getName())) {
                String path = projectList[i].getLocation().toString();
                for (int j = 3; j < parseArray.length; j++) {
                    if (parseArray[j].endsWith(ManagerConstant.DOT
                        + ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION)) {
                        return path;
                    } else {
                        path = path + ManagerConstant.SLASH + parseArray[j];
                    }
                }
            }
        }

        return null;
    }

    /**
     * 모델을 포함하는 프로젝트 찾는 메소드
     * 
     * @param model
     * @return IProject
     */
    public static String findProjectIncludingModel(Model model) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String projectName = getProjectNameFromModel(model);

        for (IProject project : root.getProjects()) {
            if (projectName.length() > 0) {
                if (project.getName().equals(projectName)) {
                    return project.getLocation().toString();
                }
            }
        }

        return null;
    }

    /**
     * 모델로부터 프로젝트 이름 가져오는 메소드
     * 
     * @param model
     * @return String
     */
    private static String getProjectNameFromModel(Model model) {
        /**
         * 일감번호 #6195 - 모델 편집기 상세 탭 오류 Modified by yschoi. 20100714
         * 
         * 결함 : 모델 디렉토리로부터의 상대적인 위치로 프로젝트 정보를 찾아 오게 되어 있어서 기본 위치(프로젝트/모델)를 벗어난
         * 모델의 경우엔 모델 편집기의 상세 탭에서 프로젝트 정보를 제대로 가져 오지 못해 상세 탭이 열리지 않는 문제 발생 해결 방안
         * : 모델의 getURI() 로 얻어오게 되는 세그먼트[1]는 무조건 프로젝트 정보이므로 세그먼트[1]를 프로젝트명으로
         * 가져오는 방식으로 변경
         */
        if (!model.eResource().getURI().isEmpty()) {
            return model.eResource().getURI().segment(1);
        }

        return null;
    }

    /**
     * 모델파일 복사
     * 
     * @param sourceFile
     * @param targetFile
     * @return boolean
     */
    public static boolean copyModelFile(String sourceFile, String targetFile) {
        try {
            if ((new File(sourceFile)).exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF8")); //$NON-NLS-1$
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF8")); //$NON-NLS-1$

                String inStr;
                while ((inStr = br.readLine()) != null) {
                    bw.write(inStr + "\n"); //$NON-NLS-1$
                }

                bw.close();
                br.close();
            } else {
                Log.debug(UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_ORIGINAL_DESIGN_MODEL_FILE_DOES_NOT_EXIST_FOR_COPYING,
                    sourceFile));
                return false;
            }
        } catch (IOException ioe) {
            Log.error("copyModelFile Error : " + ioe.getMessage()); //$NON-NLS-1$
            return false;
        }

        return true;
    }

    /**
     * 설계모델 전개 후 아무것도 없는 빈 패키지를 제거한다. Usecase Realization 패키지는 제거하지 않는다.
     * 
     * @param targetModel
     *            void
     */
    public static void removeEmptyPackages(MDADesignerTransformationData data) {

        Model targetModel = DomainModelHandlerUtil.openModel(data.getTargetModelURI());

        for (Package pack : targetModel.getNestedPackages()) {
            if (MDDCommonUtil.getUsecaseRealizationPackageName().equals(pack.getName())) { // Usecase
                // Realization
                // 패키지는
                // 제거하지
                // 않는다.
                continue;
            }
            if (getContainedValidElementCount(pack, 0) == 0) { // 패키지 내에 클래스,
                // 인터페이스가 하나도 없으면.
                // 삭제함.
                pack.destroy();
            }
        }
    }

    /**
     * 입력된 패키지의 하위패키지 중 의미있는 엘리먼트를 가지고 있는지 카운트를 반환한다. 몇개가 있는지가 중요한것이 아니라, 있는지
     * 없는지만 알면 되므로 cnt 가 0을 넘으면 더 이상 수행하지 않고 그냥 cnt 를 반환한다. 이 메소드는 전개 후 불필요하게
     * 생성된 빈 패키지를 없애기 위해 수행된다. 재귀 메소드 임. 의미있는 엘리먼트 : 더 필요하면 추가 할 것 - 클래스, 인터페이스,
     * Collaboration, Actor, Component, Interaction
     * 
     * @param targetPackage
     * @param cnt
     * @return int
     */
    public static int getContainedValidElementCount(Package targetPackage, int cnt) {
        if (cnt > 0)
            return cnt;

        for (Package p : targetPackage.getNestedPackages()) {
            for (Element element : p.getOwnedElements()) {
                if (element instanceof Class || element instanceof Interface || element instanceof Collaboration
                    || element instanceof Actor || element instanceof Component || element instanceof Interaction) {
                    cnt++;
                }
            }

            cnt = getContainedValidElementCount(p, cnt);
        }

        return cnt;
    }

}

