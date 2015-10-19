/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.OperationApplicationType;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.util.MDADesignerTransformationData;
import nexcore.tool.mdd.core.util.MDADesignerTransformationUtil;
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLEditorPlugin;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.command.DeleteUMLPackageableElementCommand;
import nexcore.tool.uml.manager.util.ModelUtility;
import nexcore.tool.uml.manager.utility.SemanticModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.handler</li>
 * <li>설  명 : SemanticModelHandler</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석 </li>
 * </ul>
 */
public class SemanticModelHandler implements ISemanticModelHandler {

//    /** 모델 매니저 */
//    private ModelManager modelManager;
//
//    /** 용어 매니저 */
//    private NewGlossaryManager glossaryManager;

    /** 부모 쉘 */
    private Shell parentShell;

    /** 선택한 요소 목록 */
    private List<Element> selectedElementList;

    /** 변환규칙 집합 */
    private RuleSet ruleSet;

    /** 진행 모니터 */
    private IProgressMonitor monitor;

    /** 선택한 목적 모델 */
    private Model selectedTargetModel;

    /** 모델정보(분석모델, 설계모델)를 담는 객체 */
    private MDADesignerTransformationData data;

    /** BehaviorRule에 담긴 타겟 스테레오타입 이름들 */
    private Set<String> typeSet;
    
    /**
     * 생성자
     */
    public SemanticModelHandler() {
    }

    /**
     * 생성자
     * 
     * @param selectedElementList
     * @param monitor
     */
    public SemanticModelHandler(List<Element> selectedElementList, IProgressMonitor monitor) {
        this.selectedElementList = selectedElementList;
        this.monitor = monitor;
    }

    /**
     * ========================================================================
     * 유스케이스 추적 매트릭스 생성 코드 시작
     */

    /**
     * 유스케이스 추적 매트릭스 생성 초기화
     * 
     * void
     */
    public void initializeUseCaseTraceMatrixCreation(Model analysisModel, Model designModel) {
//        if (analysisModel != null && designModel != null) {
//            modelManager = new ModelManager(analysisModel, designModel, parentShell, monitor);
//        }
    }

    /**
     * 유스케이스 추적 매트릭스 생성
     * 
     * void
     */
    public boolean createUseCaseTraceMatrix() {
        boolean result = false;

//        if (modelManager == null) {
//            return result;
//        }
//
//        Element element = null;
//
//        for (Iterator<Element> elementIterator = selectedElementList.iterator(); elementIterator.hasNext();) {
//            element = elementIterator.next();
//
//            if (element instanceof Model) {
//                result = modelManager.getUsecaseTraceInfo((Model) element, false);
//            }
//        }

        return result;
    }

    /**
     * 유스케이스 추적 매트릭스 생성 코드 끝
     * ========================================================================
     */


    /**
     * ========================================================================
     * 분석 모델 구조 생성 코드 시작
     */

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#createAnalysisModelStructure()
     */
    public void createAnalysisModelStructure() {

        // 분석모델의(Target 으로 설정된 모델의) Usecases Realization 패키지 확인 - 있으면 해당 패키지를
        // 없으면 null을 받아온다.
        Package analysisUseCaseRealizationPackage = getUseCaseRealizationPackage(selectedTargetModel);

        // 타겟(분석)모델에 Usecases Realization 패키지가 없으면 해당이름으로 패키지를 생성한다.
        if (analysisUseCaseRealizationPackage == null) {
            // 분석 모델에 Usecases Realization 패키지 생성
            analysisUseCaseRealizationPackage = selectedTargetModel.createNestedPackage(MDDCommonUtil.getUsecaseRealizationPackageName()); // ManagerConstant.USECASE_REALIZATION_PACKAGE_NAME
            // 에서
            // 수정함.
        }
        /*
         * if(selectedElementList.get(0) instanceof Model) { // 유즈케이스의 Usecases
         * 패키지 반환 Package ucPkg = getUsecasesPackage(selectedElementList);
         * 
         * // Usecases Realization 패키지 생성(하위까지 생성)
         * createRealizationPackage(ucPkg, ucrPkg); } else
         * if(selectedElementList.get(0) instanceof Package) { Package selPkg =
         * (Package)selectedElementList.get(0);
         * 
         * // 분석 모델내에서 상위 패키지 생성 Package ucrSelPkg = createUpperPackage(ucrPkg,
         * selPkg); ModelUtility.copyDocumentation(ucrSelPkg, selPkg);
         * 
         * // Usecases Realization 패키지 생성( 하위까지 생성 )
         * createRealizationPackage(selPkg, ucrSelPkg); }
         */
        Package selectedPackage = null;
        Package parentPackage = null;

        for (Element selectedElement : selectedElementList) {
            if (selectedElement instanceof Package) {
                // 모델 바로 아래의 패키지는 생성 대상에서 제외
                if (selectedElement.getOwner() instanceof Model) {} else {
                    monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_STRUCTURE_CREATION)
                        + ((NamedElement) selectedElement).getName());

                    selectedPackage = (Package) selectedElement;

                    // 분석 모델내에서 상위 패키지 생성
                    parentPackage = createUpperPackage(analysisUseCaseRealizationPackage, selectedPackage);
                    // 선택한 패키지의 문서 복사
                    ModelUtility.copyDocumentation(parentPackage, selectedPackage);

                    // Usecases Realization 패키지 생성( 하위까지 생성 )
                    createRealizationPackage(selectedPackage, parentPackage);
                    
                    // 2012-08-28
                    // 유스케이스모델 -> 분석모델 변환시 패지키의 키워드까지 변환되도록 한다.
                    EList<String> keywords = selectedPackage.getKeywords();
                    if( !keywords.isEmpty()) {
                        for (final String keyword : keywords) {
                            parentPackage.addKeyword(keyword.trim());
                        }
                    }
                    monitor.worked(1);
                }
            }
        }
    }

    /**
     * 모델에 속한 Usecase Realization 패키지를 반환
     * 
     * @param model
     * @return Package
     */
    private Package getUseCaseRealizationPackage(Model model) {
        List<Element> elementList = model.getOwnedElements();

        Package packageElement = null;
        Element element = null;

        for (Iterator<Element> elementIterator = elementList.iterator(); elementIterator.hasNext();) {
            element = elementIterator.next();

            if (element != null && element instanceof Package) {
                packageElement = (Package) element;

                if (packageElement.getName().equals(MDDCommonUtil.getUsecaseRealizationPackageName())) { // UMLMessage.LABEL_USECASE_REALIZATION_PACKAGE
                    // 에서
                    // 수정함.
                    return packageElement;
                }
            }
        }

        return null;
    }

    /**
     * 유스케이스 모델내에서 선택한 패키지를 포함하는 부모 패키지 구조를 분석 모델 아래의 Usecase Realization 패키지
     * 아래에 생성한다.
     * 
     * @param analysisUseCaseRealizationPackage
     * @param selectedPackage
     * @return Package
     */
    private Package createUpperPackage(Package analysisUseCaseRealizationPackage, Package selectedPackage) {
        Package nestingPackage = selectedPackage;

        StringBuffer wholeParentPackages = new StringBuffer();
        String delimiter = ManagerConstant.SLASH;

        // 유스케이스 모델의 선택한 패키지에서부터 Usecases 패키지까지의 구조를 먼저 파악한다.
        for (int packageDepth = 0; packageDepth < 30; packageDepth++) {
            if (nestingPackage.getName().toLowerCase().equals(nestingPackage.getModel().getName().toLowerCase())) {
                // 패키지명과 모델명이 같으면 중단
                break;
            } else {
                // 유스케이스 모델의 Usecases 패키지를 만나면 중단
                if (nestingPackage.getName()
                    .replaceAll(ManagerConstant.BLANK_STRING, ManagerConstant.EMPTY_STRING)
                    .toUpperCase()
                    .equals(MDDCommonUtil.getUsecasesPackageName()
                        .toUpperCase()
                        .replaceAll(ManagerConstant.BLANK_STRING, ManagerConstant.EMPTY_STRING))) { // UMLMessage.LABEL_USECASE_PACKAGE
                    // 에서
                    // 수정함.
                    break;
                } else {
                    if (wholeParentPackages.length() > 0) {
                        wholeParentPackages.insert(0, delimiter);
                    }

                    wholeParentPackages.insert(0, nestingPackage.getName());
                }

                nestingPackage = nestingPackage.getNestingPackage();
            }
        }

        // 
        if (wholeParentPackages.length() == 0) {
            return analysisUseCaseRealizationPackage;
        }

        String upperPackage = wholeParentPackages.toString();
        String[] packageArray = upperPackage.split(delimiter);

        Package analysisPackage = null;
        Package parentPackage = null;

        if (packageArray.length == 0) {
            return analysisUseCaseRealizationPackage;
        }

        for (int packageArrayIndex = 0; packageArrayIndex < packageArray.length; packageArrayIndex++) {
            if (packageArrayIndex == 0) {
                parentPackage = analysisUseCaseRealizationPackage.getNestedPackage(packageArray[packageArrayIndex]);

                if (parentPackage == null) {
                    analysisPackage = analysisUseCaseRealizationPackage.createNestedPackage(packageArray[packageArrayIndex]);
                    parentPackage = analysisPackage;
                }
            } else {
                analysisPackage = parentPackage.getNestedPackage(packageArray[packageArrayIndex]);

                if (analysisPackage == null) {
                    analysisPackage = parentPackage.createNestedPackage(packageArray[packageArrayIndex]);
                }
                parentPackage = analysisPackage;
            }
        }

        return parentPackage;
    }

    /**
     * Usecases Realization 패키지 생성(하위까지 생성)
     * 
     * @param usecasesPkg
     * @param realizePkg
     *            void
     */
    private void createRealizationPackage(Package usecasesPkg, Package realizePkg) {
        if (usecasesPkg != null && realizePkg != null) {
            Package ucPkg = null;
            Package rzPkg = null;
            Collaboration collabo = null;
            Interaction intact = null;
            UseCase useCase = null;
            Object object = null;
            EList ucList = usecasesPkg.getOwnedElements();

            for (Iterator itr = ucList.iterator(); itr.hasNext();) {
                object = itr.next();

                // 패키지가 발견되면 하위 패키지를 탐색한다.
                if (object != null && object instanceof Package) {
                    ucPkg = (Package) object;

                    rzPkg = realizePkg.getNestedPackage(ucPkg.getName());
                    if (rzPkg == null) {
                        // Usecases Realization에 패키지를 추가한다.
                        rzPkg = realizePkg.createNestedPackage(ucPkg.getName());
                        ModelUtility.copyDocumentation(rzPkg, ucPkg);
                    } else {
                        ModelUtility.copyDocumentation(rzPkg, ucPkg);
                    }
                    
                    // 2012-08-28
                    // 유스케이스모델 -> 분석모델 변환시 패지키의 키워드까지 변환되도록 한다.
                    EList<String> keywords = ucPkg.getKeywords();
                    if( !keywords.isEmpty()) {
                        for (final String keyword : keywords) {
                            rzPkg.addKeyword(keyword.trim());
                        }
                    }
                    
                    // 재귀 호출
                    // createRealizationPackage(ucPkg, rzPkg);
                } else if (object != null && object instanceof UseCase) {
                    useCase = (UseCase) object;

                    // Collaboration 생성
                    collabo = createCollaboration(realizePkg, useCase);

                    // Interaction instacne 생성
                    intact = createInteraction(collabo, useCase.getName());

                    // 시퀀스 다이어그램 생성
//                    Diagram diagram = createSequenceDiagram(intact, useCase.getName());

//                    try {
//                        RelationManager.getInstance().createRelationUsecaseToAnalysisClass(useCase, new EObject[] { collabo, intact, diagram });
//                    } catch (Exception e) {
//                        ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(e.getMessage(), e);
//                    }
                }
            }
        }
    }

    /**
     * Collaboration 생성
     * 
     * @param realizePkg
     * @param useCase
     * @return Collaboration
     */
    private Collaboration createCollaboration(Package realizePkg, UseCase useCase) {
        Package ucPackage = realizePkg.getNestedPackage(useCase.getName());
        if (ucPackage == null) {
            ucPackage = realizePkg.createNestedPackage(useCase.getName());
        }
        PackageableElement packagedElement = ucPackage.getPackagedElement(useCase.getName());
        Collaboration collabo = null;
        if (packagedElement instanceof Collaboration) {
            collabo = (Collaboration) packagedElement;
        } else {
            EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getCollaboration();
            collabo = (Collaboration) ucPackage.createPackagedElement(useCase.getName(), eClass);
        }
        ModelUtility.copyDocumentation(collabo, useCase);
        ModelUtility.copyKeywords(useCase, collabo);

        return collabo;
    }

    /**
     * Interaction 생성
     * 
     * @param collabo
     * @param name
     * @return Interaction
     */
    private Interaction createInteraction(Collaboration collabo, String name) {
        EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getInteraction();

        Behavior ownedBehavior = collabo.getOwnedBehavior(name);
        if (ownedBehavior != null && ownedBehavior instanceof Interaction) {
            return (Interaction) ownedBehavior;
        } else {
            return (Interaction) collabo.createOwnedBehavior(name, eClass);
        }
    }

    /**
     * 시퀀스 다이어그램 생성
     * 
     * @param intact
     * @param name
     *            void
     */
    private Diagram createSequenceDiagram(Interaction intact, String name) {
        EAnnotation eAnnotation = intact.getEAnnotation("Diagram");
        if (eAnnotation != null) {
            EList<EObject> contents = eAnnotation.getContents();
            for (EObject eObj : contents) {
                if (eObj instanceof Diagram) {
                    Diagram diagram = (Diagram) eObj;
                    if (diagram.getType() == DiagramType.SEQUENCE_DIAGRAM) {
                        return diagram;
                    }
                }
            }
        }
        INotationModelHandler modelHandler = new UMLModelerNotationModelHandler();

        return modelHandler.createDiagram(intact, name, DiagramType.SEQUENCE_DIAGRAM);
    }

    /**
     * 분석 모델 구조 생성 코드 끝
     * ========================================================================
     */

    /**
     * ========================================================================
     * 설계 모델 구조 생성 코드 시작
     */

    /**
     * Structure 생성 처리
     */
    public void createStructure() {

        // Source 모델에서 선택한 항목이 하나라도 있을 경우
        if (!selectedElementList.isEmpty()) {
            // 선택한 항목 갯수만큼 반복
            for (Element element : selectedElementList) {
                monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_STRUCTURE_CREATION)
                    + ((NamedElement) element).getName());

                SemanticModelHandlerUtil.copyStructure(element, data);

                monitor.worked(1);
            }

            /**
             * 구조가 생성된 후 찌꺼기 패키지(빈 패키지)를 삭제한다. 만약, 빈 패키지 구조가 의미가 있다면 삭제하면 안되고 아래
             * 메소드 호출을 주석 처리 할것.
             */
            SemanticModelHandlerUtil.removeEmptyPackages(data);
        }
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
     * Behavior 생성 처리
     */
    public void createBehavior() throws Exception {
        // Source 모델에서 선택한 항목이 하나라도 있을 경우
        if (!selectedElementList.isEmpty()) {
            typeSet = getTypesInBehaviorTransformationDetailRule();
            // 선택한 항목 갯수만큼 반복
            for (Element element : selectedElementList) {
                // Lifeline 생성
                if (element instanceof Interaction) {
                    monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_LIFELINE_CREATION)
                        + ((NamedElement) element).getName());

                    createLifeline((Interaction) element, data);

                    monitor.worked(1);
                }
            }

            for (Element element : selectedElementList) {
                // Message 생성
                if (element instanceof Interaction) {
                    monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_MESSAGE_CREATION)
                        + ((NamedElement) element).getName());

                    createMessage((Interaction) element, data);

                    monitor.worked(1);
                }
            }
        }

    }

    /**
     * Lifeline 생성
     * 
     * @param sourceInteraction
     * @param ruleSet
     */
    private void createLifeline(Interaction sourceInteraction, MDADesignerTransformationData data) throws Exception {
        // 선택한 Source(분석모델)요소와 RuleSet이 존재하지 않는다면
        if (sourceInteraction == null || !SemanticModelHandlerUtil.isValid(data)) {
            return;
        }

        // String targetModelURI = ruleSet.getTargetModelLocation();
        Interaction targetInteraction = null;
        String lifelineName = null;
        List<Type> targetLifeLineTypeList = null;

        // Target(설계모델) 인터랙션 위치를 찾고
        // targetInteraction =
        // SemanticModelHandlerUtil.findTargetInteraction(data.getTargetModel(),
        // sourceInteraction);
        targetInteraction = SemanticModelHandlerUtil.findTargetInteraction(data.getTargetModelURI(), sourceInteraction);
        if (targetInteraction == null) {
            return;
        }

        for (Lifeline sourceLifeline : SemanticModelHandlerUtil.getLifelines(sourceInteraction)) {
            // null 확인
            if (sourceLifeline == null || sourceLifeline.getRepresents() == null) {
                return;
            }

            // Source Lifeline이 Target 으로 전개된 Type들을 가져와서
            targetLifeLineTypeList = SemanticModelHandlerUtil.getTargetLifeLineTypeList(sourceInteraction.eResource(), 
                    sourceLifeline, data);

            // Type들이 존재한다면.
            if (targetLifeLineTypeList != null && targetLifeLineTypeList.size() > 0) {
                // 생성된 Target Type의 갯수만큼
                for (Type lifelineType : targetLifeLineTypeList) {
                    lifelineName = lifelineType.getName();
                    // BehaviorRule에 있는 경우에 대해서만 라이프라인 생성.
                    if (existInTypeSet(typeSet, lifelineType) && targetInteraction != null && lifelineName != null
                        && lifelineType != null) {
                        // Target 인터랙션에 Lifeline을 추가
                        SemanticModelHandlerUtil.addLifeline(targetInteraction, lifelineName, lifelineType);
                    }
                }
            }
        }
    }

    /**
     * 라이프라인의 타입이 BehaviorRule에 해당 유무 반환.
     * 
     * @param typeSet
     * @param lifelineType
     * @return boolean
     */
    private boolean existInTypeSet(Set<String> typeSet, Type lifelineType) {
        if (null == typeSet || typeSet.size() == 0) {
            return false;
        }
        if (typeSet.contains(ManagerConstant.UML_TYPE_LITERAL)) {
            if (null != lifelineType) {
                return true;
            }
        }
        if (typeSet.contains(ManagerConstant.UML_ACTOR_LITERAL)) {
            if (lifelineType instanceof Actor) {
                return true;
            }
        }

        EList<Stereotype> stereotypes = lifelineType.getAppliedStereotypes();
        for (Stereotype stereotype : stereotypes) {
            if (typeSet.contains(stereotype.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 행위 변환 규칙에 있는 Sender와 Receiver 타입의 이름들을 Set으로 반환한다.
     * 
     * @return List<String>
     */
    private Set<String> getTypesInBehaviorTransformationDetailRule() {
        Set<String> result = new HashSet<String>();
        EList<BehaviorTransformationData> behaviorRules = data.getRuleSet().getBehaviorRules();
        EList<BehaviorTransformationDetailData> detailRules;
        for (BehaviorTransformationData behaviorTransformationData : behaviorRules) {
            detailRules = behaviorTransformationData.getBehaviorTransformationDetailDataSet();
            for (BehaviorTransformationDetailData detailRule : detailRules) {
                try {
                    result.add(detailRule.getTargetRelation().getSource().getDataName());
                    result.add(detailRule.getTargetRelation().getTarget().getDataName());
                } catch (Exception e) {
                    // DetailRule이 없을 때는 무시하고 넘어감.
                }
            }
        }
        result = getTypeNamesInStructureRule(result);
        return result;
    }

    /**
     * 행위 변환 규칙에 있는 RuleName에 대응하는 스테레오타입명을 Set으로 반환한다.
     * 
     * @param result
     * @return Set<String>
     */
    private Set<String> getTypeNamesInStructureRule(Set<String> set) {
        Set<String> result = new HashSet<String>();
        if (set.contains(ManagerConstant.UML_ACTOR_LITERAL)) {
            result.add(ManagerConstant.UML_ACTOR_LITERAL);
        }
        if (set.contains(ManagerConstant.UML_TYPE_LITERAL)) {
            result.add(ManagerConstant.UML_TYPE_LITERAL);
        }
        EList<SourceStructureTransformationData> sourceStructureRules = data.getRuleSet().getStructureRules();
        EList<TargetStructureTransformationData> targetStructureRules;
        for (SourceStructureTransformationData srcData : sourceStructureRules) {
            targetStructureRules = srcData.getTargetStructureTransformationDataSet();
            for (TargetStructureTransformationData targetData : targetStructureRules) {
                if (set.contains(targetData.getDataName().getDataName())) {
                    result.add(targetData.getTargetApplicableStereotype());
                }
            }
        }
        return result;
    }

    /**
     * Message 생성
     * 
     * @param sourceInteraction
     * @param ruleSet
     */
    private void createMessage(Interaction sourceInteraction, MDADesignerTransformationData data) throws Exception {
        // 선택한 요소와 RuleSet이 존재하지 않는다면
        if (sourceInteraction == null || !SemanticModelHandlerUtil.isValid(data)) {
            return;
        }

        // String targetModelURI = ruleSet.getTargetModelLocation();
        Interaction targetInteraction = null;
        Lifeline sourceSenderLifeline = null;
        Lifeline sourceReceiverLifeline = null;
        boolean isSelf = false;
        Lifeline targetSenderLifeline = null;
        Lifeline targetReceiverLifeline = null;
        String targetSenderDataName = null;
        String targetReceiverDataName = null;
        List<BehaviorTransformationDetailData> detailBehaviorRules = null;
        Message sourceMessage = null;
        List<Operation> sourceOperationList = null;
        Operation sourceOperation = null;
        String sourceMessageName = null;
        Operation targetOperation = null;

        // Target 인터랙션 위치를 찾고
        // targetInteraction =
        // SemanticModelHandlerUtil.findTargetInteraction(data.getTargetModel(),
        // sourceInteraction);
        targetInteraction = SemanticModelHandlerUtil.findTargetInteraction(data.getTargetModelURI(), sourceInteraction);
        if (targetInteraction == null) {
            return;
        }

        // Source 인터랙션이 가지는 Message들 가져와서
        List<Message> sourceMessageList = SemanticModelHandlerUtil.getMessages(sourceInteraction);

        if (sourceMessageList != null) {
            for (Iterator<Message> iter = sourceMessageList.iterator(); iter.hasNext();) {
                sourceMessage = iter.next();

                // 2012.12.12 Reply 메시지 변환이 제대로 되지 않아서 제외 처리.
                if (MessageSort.REPLY_LITERAL.equals(sourceMessage.getMessageSort())) {
                    continue;
                }
                
                sourceOperation = (Operation) sourceMessage.getSignature();
                sourceMessageName = null;

                isSelf = false;

                if (sourceOperation != null) {
                    sourceMessageName = sourceOperation.getName();
                } else {
                    sourceMessageName = sourceMessage.getName();
                }

                // 가져온 Message에서 Sender Lifeline, Receiver Lifeline 을 가져와서
                sourceSenderLifeline = SemanticModelHandlerUtil.getSenderLifeline(sourceMessage);
                sourceReceiverLifeline = SemanticModelHandlerUtil.getReceiverLifeline(sourceMessage);

                if (sourceSenderLifeline == null || sourceReceiverLifeline == null) {
                    continue;
                }

                if (sourceSenderLifeline.equals(sourceReceiverLifeline)) {
                    isSelf = true;
                }


                // 상세 Behavior변환규칙 목록을 찾아오고,
                detailBehaviorRules = MDADesignerTransformationUtil.getDetailBehaviorRules(ruleSet,
                    sourceSenderLifeline,
                    sourceReceiverLifeline,
                    isSelf);
                // Remark 정보 가져오기
                String remark = MDADesignerTransformationUtil.getBehaviorTransformationRemark(ruleSet,
                        sourceSenderLifeline, sourceReceiverLifeline, isSelf);
                
                if (detailBehaviorRules == null) {
                    continue;
                }

                for (BehaviorTransformationDetailData detailBehaviorRule : detailBehaviorRules) {
                    // Target Sender Data명, Target Receiver Data명을 가져와서
                    targetSenderDataName = detailBehaviorRule.getTargetRelation().getSource().getDataName();
                    targetReceiverDataName = detailBehaviorRule.getTargetRelation().getTarget().getDataName();

                    if (targetSenderDataName == null || targetReceiverDataName == null) {
                        continue;
                    }

                    // Target Sender Lifeline, Target Receiver Lifeline 을 가져온다.
                    targetSenderLifeline = SemanticModelHandlerUtil.getTargetSenderLifeline(sourceInteraction, targetInteraction,
                        targetSenderDataName,
                        sourceSenderLifeline,
                        data);
                    targetReceiverLifeline = SemanticModelHandlerUtil.getTargetReceiverLifeline(sourceInteraction, targetInteraction,
                        targetReceiverDataName,
                        sourceReceiverLifeline,
                        data);

                    if (targetInteraction == null || targetSenderLifeline == null || targetReceiverLifeline == null) {
                        continue;
                    }

                    // MDAUtil.addMessage(targetInteraction,
                    // targetSenderLifeline, targetReceiverLifeline,
                    // sourceMessage.getName(), MessageSort.SYNCH_CALL_LITERAL,
                    // op);
                    /**
                     * Operation 적용 타입이 'same_type' 일 경우,
                     * Prefix[operation]Postfix 를 지원해야 함. same type 이라는 의미는
                     * operation의 이름이 아니라, 변수와 반환type 을 말하는 것. (ex-
                     * excute(String a, intb):void 이런식)
                     */
                    if (detailBehaviorRule.getOperation()
                        .getOperationApplicationType()
                        .equals(OperationApplicationType.SAME_TYPE)) {

                        // opearation 이름: preFix 와 postFix를 적용
                        String operationName = ManagerConstant.EMPTY_STRING;
                        if (detailBehaviorRule.getOperation().getUserDefinedOperationName() == null) {
                            operationName = sourceMessageName;
                        } else {
                            operationName = MDDCommonUtil.getKeywordAppliedString(MDDCoreConstant.OPERATION_QULIFIER_B,
                                detailBehaviorRule.getOperation().getUserDefinedOperationName(),
                                sourceMessageName);
                        }

                        targetOperation = SemanticModelHandlerUtil.getOperation(targetReceiverLifeline, operationName);

                        String messageName = ManagerConstant.EMPTY_STRING; // 메세지명.
                        if (!StringUtil.isEmpty(detailBehaviorRule.getMessage())) { // 입력한  메시지가 있다면,
                            // preFix 와 postFix를 적용
                            messageName = MDDCommonUtil.getKeywordAppliedString(MDDCoreConstant.OPERATION_QULIFIER_B,
                                detailBehaviorRule.getMessage(),
                                sourceMessageName);
                        } else { // 입력한 메시지명이 없으면 operation 명 사용
                            messageName = targetOperation.getName();
                        }

                        if (targetOperation != null) {
                            SemanticModelHandlerUtil.addMessage(targetInteraction,
                                targetSenderLifeline,
                                targetReceiverLifeline,
                                messageName,
                                sourceMessage.getMessageSort(),
                                targetOperation, remark, detailBehaviorRule.getOperationApplicableStereotype());
                        }
                        /**
                         * Operation 적용 타입이 'user_defined' 일 경우, 변수와 반환type 을
                         * 사용자가 수정 할 수 있어야 함. 현재 지원하지 않음.
                         */
                    } else if (detailBehaviorRule.getOperation()
                        .getOperationApplicationType()
                        .equals(OperationApplicationType.USER_DEFINED)) {

                        // if
                        // (!StringUtil.isEmpty(detailBehaviorRule.getOperation().getUserDefinedOperationName()))
                        // {
                        // // preFix 와 postFix를 적용
                        // String operationName =
                        // MDDCommonUtil.getKeywordAppliedString(MDDCoreConstant.OPERATION_QULIFIER_B,
                        // detailBehaviorRule.getOperation().getUserDefinedOperationName(),
                        // sourceMessageName);
                        // targetOperation =
                        // SemanticModelHandlerUtil.addOperation(targetReceiverLifeline,
                        // operationName);
                        // } else { // 오퍼레이션 명을 입력하지 않았다면 오퍼레이션을 생성하지 않는다.
                        // // 이 경우, Message 는 오퍼레이션 없이 LifeLine 에 달라붙음
                        // targetOperation = null;
                        // }
                        // String messageName = ""; // 메세지 명.
                        // if
                        // (!StringUtil.isEmpty(detailBehaviorRule.getMessage()))
                        // { // 입력한 메시지가 있다면,
                        // // preFix 와 postFix를 적용
                        // messageName =
                        // MDDCommonUtil.getKeywordAppliedString(MDDCoreConstant.OPERATION_QULIFIER_B,
                        // detailBehaviorRule.getMessage(), sourceMessageName);
                        // }
                        //                        
                        // SemanticModelHandlerUtil.addMessage(targetInteraction,
                        // targetSenderLifeline, targetReceiverLifeline,
                        // messageName, sourceMessage.getMessageSort(),
                        // targetOperation);
                        /**
                         * Operation 적용 타입이 'none' 일 경우, Operation 은 생성하지 않고,
                         * Message 만 생성한다.
                         */
                    } else if (detailBehaviorRule.getOperation()
                        .getOperationApplicationType()
                        .equals(OperationApplicationType.NONE)) {

                        targetOperation = null; // operation 은 생성하지 않는다.
                        // 메세지명 : preFix 와 postFix를 적용
                        String messageName = MDDCommonUtil.getKeywordAppliedString(MDDCoreConstant.OPERATION_QULIFIER_B,
                            detailBehaviorRule.getMessage(),
                            sourceMessageName);
                        // operation 없이 Message 만 생성한다.
                        SemanticModelHandlerUtil.addMessage(targetInteraction,
                            targetSenderLifeline,
                            targetReceiverLifeline,
                            messageName,
                            sourceMessage.getMessageSort(),
                            targetOperation, remark, detailBehaviorRule.getOperationApplicableStereotype());
                    }
                }
            }
        }
    }

    /**
     * 설계 모델 행위 생성 코드 끝
     * ========================================================================
     */

    /**
     * UseCase Model의 Usecases의 패키지 반환
     * 
     * @param elements
     * @return Package
     */
    private Package getUsecasesPackage(List elements) {
        // 선택된 UC 최상위 모델 패키지.
        Model model = null;
        if (elements.get(0) instanceof Model) {
            model = (Model) elements.get(0);
        } else if (elements.get(0) instanceof Package) {
            model = ((Package) elements.get(0)).getModel();
        }

        EList elmtList = model.allOwnedElements();
        Package pkg = null;
        Object object = null;

        for (Iterator itr = elmtList.iterator(); itr.hasNext();) {
            object = itr.next();
            if (object != null && object instanceof Package) {
                pkg = (Package) object;

                if (pkg.getName()
                    .toUpperCase()
                    .replaceAll(ManagerConstant.BLANK_STRING, ManagerConstant.EMPTY_STRING)
                    .equals(MDDCommonUtil.getUsecasesPackageName().toUpperCase())) { // UMLMessage.LABEL_USECASE_PACKAGE
                    // 에서
                    // 수정함.
                    return pkg;
                }
            }
        }

        return null;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#convertKeywordToStereotype()
     */
    public void convertKeywordToStereotype() {
        List<Element> enumeratedElementList = generateEnumeratedElementList();
        Stereotype stereotype;

        if (enumeratedElementList != null && !enumeratedElementList.isEmpty()) {
            for (Element element : enumeratedElementList) {
                stereotype = findStereotype(element);

                if (stereotype != null) {
                    monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_APPLY_STEREOTYPE)
                        + ((NamedElement) element).getName());

                    if (!element.getAppliedStereotypes().contains(stereotype)) {
                        element.applyStereotype(stereotype);
                        removeStereotype(element);
                        if (element instanceof NamedElement) {
                            String name = ((NamedElement) element).getName();
                            ((NamedElement) element).setName(name);
                        }
                    }

                    monitor.worked(1);
                }
            }
        }
    }

    /**
     * generateEnumeratedElementList
     *  
     * @return List<Element>
     */
    private List<Element> generateEnumeratedElementList() {
        List<Element> enumeratedElementList;
        EObject pickedUpEObject;

        if (selectedElementList != null && !selectedElementList.isEmpty()) {
            enumeratedElementList = new ArrayList<Element>();

            for (Element pickedUpElement : selectedElementList) {
                if (!enumeratedElementList.contains(pickedUpElement)) {
                    enumeratedElementList.add(pickedUpElement);
                }

                for (TreeIterator<EObject> eObjectIterator = pickedUpElement.eAllContents(); eObjectIterator.hasNext();) {
                    pickedUpEObject = eObjectIterator.next();

                    if (!enumeratedElementList.contains(pickedUpEObject)) {
                        if (pickedUpEObject instanceof Element) {
                            enumeratedElementList.add((Element) pickedUpEObject);
                        }
                    }
                }
            }

            return enumeratedElementList;
        }

        return null;
    }

    /**
     * '키워드의 스테레오타입 변환' 기능에서 사용.
     * 
     * @param element
     * @return Stereotype
     */
    private Stereotype findStereotype(Element element) {
        for (String keyword : element.getKeywords()) {
            for (Stereotype stereotype : element.getApplicableStereotypes()) {
                if (stereotype.getName().toLowerCase().equals(keyword.toLowerCase())) {
                    return stereotype;
                }
            }
        }

        return null;
    }

    /**
     * '키워드의 스테레오타입 변환' 기능에서 사용.
     * 
     * @param element
     *            void
     */
    private void removeStereotype(Element element) {
        for (String keyword : element.getKeywords()) {
            for (Stereotype stereotype : element.getApplicableStereotypes()) {
                if (stereotype.getName().toLowerCase().equals(keyword.toLowerCase())) {
                    element.removeKeyword(keyword);
                }
            }
        }
    }

    /**
     * targetPackage
     */
    private Package targetPackage;

    /**
     * targetClassName
     */
    private String targetClassName;

    /**
     * clearClass
     */
    private boolean clearClass;


    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#initializeClassesMerger(java.lang.Object,
     *      java.lang.String, boolean)
     */
    public void initializeClassesMerger(Object firstResult, String className, boolean clear) {
        targetPackage = (Package) firstResult;
        targetClassName = className;
        clearClass = clear;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#mergeClassToClass()
     */
    public void mergeClassToClass() {
        Class targetClass;
        Class firstClass;
        Stereotype newStereotype;
        Class clazz;
        EObject copyObject;

        List<AbstractView> viewElementList = new ArrayList<AbstractView>();
        List<AbstractView> tempViewElementList;
        List<Element> elementList;
        List<Operation> operationList = new ArrayList<Operation>();

        DeleteUMLPackageableElementCommand deleteCommand;

        targetClass = UMLHelper.createClass();
        targetClass.setName(targetClassName);
        targetPackage.getPackagedElements().add(targetClass);

        firstClass = (Class) selectedElementList.get(0);

        try {
            for (Stereotype stereotype : firstClass.getAppliedStereotypes()) {
                newStereotype = targetClass.getApplicableStereotype(stereotype.getQualifiedName());

                if (newStereotype != null) {
                    targetClass.applyStereotype(newStereotype);
                }
            }
        } catch (Exception error) {
            Log.error(error);
        }

        for (EObject eObject : selectedElementList) {
            if (!(eObject instanceof Class)) {
                continue;
            }

            clazz = (Class) eObject;

            for (Property attribute : clazz.getOwnedAttributes()) {

                if (attribute.getAssociation() == null) {
                    copyObject = EcoreUtil.copy(attribute);
                    targetClass.getOwnedAttributes().add((Property) copyObject);
                }
            }
        }

        for (EObject eObject : selectedElementList) {
            tempViewElementList = UMLManager.getRelatedViewModel((Element) eObject);

            if (tempViewElementList != null && tempViewElementList.size() > 0) {
                viewElementList.addAll(tempViewElementList);
            }

            elementList = UMLManager.getRelatedUMLModel((Element) eObject);

            if (eObject instanceof Class) {
                operationList.addAll(((Class) eObject).getOperations());

                replaceRelatedModel(elementList, (Class) eObject, targetClass);
            }
        }

        replaceEventAction(operationList, targetClass);
        replaceUMLModel(viewElementList, targetClass);

        if (this.clearClass) {
            for (EObject eObject : selectedElementList) {
                deleteCommand = new DeleteUMLPackageableElementCommand((NamedElement) eObject);

                deleteCommand.execute();
            }
        }
    }

    /**
     * 추가적인 연결정보 처리 필요. 종속성과 파라미터에 대한 정보 변경만 되고 있음.
     * 
     * @param elementList
     * @param before
     * @param targetClass
     *            void
     */
    private void replaceRelatedModel(List<Element> elementList, Class before, Class targetClass) {
        if (elementList == null) {
            return;
        }

        for (Element element : elementList) {
            if (element instanceof Property) {
                if (before.equals(((Property) element).getType())) {
                    ((Property) element).setType(targetClass);
                }

            } else if (element instanceof Dependency) {
                if (((Dependency) element).getClients().contains(before)) {
                    before.getClientDependencies().remove(element);
                    ((Dependency) element).getClients().remove(before);
                    ((Dependency) element).getClients().add(targetClass);
                } else if (((Dependency) element).getSuppliers().contains(before)) {
                    ((Dependency) element).getSuppliers().remove(before);
                    ((Dependency) element).getSuppliers().add(targetClass);
                }
                if (element.eContainer().equals(before)) {
                    before.getClientDependencies().remove(element);
                    targetClass.getClientDependencies().add((Dependency) element);
                }
            } else if (element instanceof Parameter) {
                ((Parameter) element).setType(targetClass);
            }
        }
    }

    /**
     * replaceEventAction
     *  
     * @param operationList
     * @param targetClass void
     */
    private void replaceEventAction(List<Operation> operationList, org.eclipse.uml2.uml.Class targetClass) {
        if (operationList == null) {
            return;
        }

        List<Element> relatedList;
        HashMap<String, Operation> targetOperatonList = new HashMap<String, Operation>();
        Operation mergedOperation;
        String operationName;
        Operation newOperation;

        for (Operation operation : operationList) {
            operationName = operation.getName();

            if (!targetOperatonList.containsKey(operationName)) {
                mergedOperation = (Operation) EcoreUtil.copy(operation);
                targetOperatonList.put(operationName, mergedOperation);
                targetClass.getOwnedOperations().add(mergedOperation);
            }
        }

        for (Operation operaton : operationList) {
            relatedList = UMLManager.getRelatedUMLModel(operaton);
            newOperation = targetOperatonList.get(operaton.getName());

            if (newOperation == null) {
                continue;
            }

            for (Element element : relatedList) {
                if (element instanceof SendOperationEvent) {
                    ((SendOperationEvent) element).setOperation(newOperation);
                } else if (element instanceof ReceiveOperationEvent) {
                    ((ReceiveOperationEvent) element).setOperation(newOperation);
                }
            }
        }
    }

    /**
     * replaceUMLModel
     *  
     * @param viewElementList
     * @param targetClass void
     */
    private void replaceUMLModel(List<AbstractView> viewElementList, org.eclipse.uml2.uml.Class targetClass) {
        if (viewElementList == null) {
            return;
        }

        for (AbstractView abstractView : viewElementList) {
            abstractView.setUmlModel(targetClass);
        }
    }

    /** 공유 객체 */
    private Object sharingObject;

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#initializeGlossaryManagement()
     */
    public void initializeGlossaryManagement() {
//        if (selectedElementList != null && !selectedElementList.isEmpty() && parentShell != null && monitor != null) {
//            glossaryManager = new NewGlossaryManager(parentShell, monitor);
//            
//            // 전달된 공유 객체가 있다면 실제 수행될 비즈니스 로직 클래스에 설정
//            // (여기서 전달된 공유 객체는 '엑셀 워크북')
//            if(sharingObject != null) {
//                glossaryManager.setExcelWorkBook(sharingObject);
//            }
//        }
    }
    
    // 생성할 UML 리스트
    /**
     * list
     */
    private HashMap<String, Boolean> list;
    
    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setGenerateList(java.util.HashMap)
     */
    public void setGenerateList(HashMap<String, Boolean> list) {
        this.list = list;        
    }
    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#createGlossaryDictionary()
     */
    public boolean createGlossaryDictionary() {
        boolean result = false;

//        if (glossaryManager != null) {
//            String fileName = (String) sharingObject;
//            File file = new File(fileName);
//            if (file.exists()) {
//                // 메시지 출력
//                boolean overwrite = MessageDialog.openConfirm(parentShell,
//                    UMLMessage.LABEL_NOTIFICATION,
//                    UMLMessage.MESSAGE_GLOSSARY_DICTIONARY_FILE_ALREADY_EXISTS +"\r\n"+ "덮어쓰시겠습니까?");
//
//                if (overwrite) {
//                    result = glossaryManager.generateGlossary(selectedElementList.get(0), (String) sharingObject, list);
//                } else {
//                    result = false;
//                }
//            } else {
//                result = glossaryManager.generateGlossary(selectedElementList.get(0), (String) sharingObject, list);
//            }
//        }

        return result;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#translateGlossaryIntoEnglish()
     */
    public boolean translateGlossaryIntoEnglish() {
        boolean result = false;

//        if(glossaryManager != null) {
//            result = glossaryManager.translateElement(selectedElementList.get(0));
//        }

        return result;
    }
    

//    // 용어의 변환 횟수를 반환함
//    /**
//     * getTranslationNumber
//     *  
//     * @return int
//     */
//    public int getTranslationNumber() {
//        return glossaryManager.getTranslationNumber();
//    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setSharingObject(java.lang.Object)
     */
    public void setSharingObject(Object sharingObject) {
        if(sharingObject != null) {
            this.sharingObject = sharingObject;
        }
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setElementList(java.util.List)
     */
    public void setElementList(List<Element> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setParentShell(org.eclipse.swt.widgets.Shell)
     */
    public void setParentShell(Shell parentShell) {
        this.parentShell = parentShell;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void setProgressMonitor(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setRuleSet(nexcore.tool.mda.model.designer.transformation.RuleSet)
     */
    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;

        this.data = new MDADesignerTransformationData(ruleSet);
    }

    /**
     * @see nexcore.tool.mdd.core.extension.ISemanticModelHandler#setSelectedTargetModel(org.eclipse.uml2.uml.Model)
     */
    public void setSelectedTargetModel(Model selectedTargetModel) {
        this.selectedTargetModel = selectedTargetModel;
    }

}
