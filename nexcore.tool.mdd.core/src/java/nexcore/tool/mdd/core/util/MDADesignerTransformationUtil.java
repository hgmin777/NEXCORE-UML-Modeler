/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mdd.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.LocationKeywordType;
import nexcore.tool.mda.model.designer.transformation.LocationSegment;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.SourceType;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TransformationFactory;
import nexcore.tool.mdd.core.MDDCoreConstant;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;

/**
 * 업무 그룹명 : nexcore.tool.mda.core.infrastructure.util 서브 업무명 :
 * MDADesignerTransformationUtil.java 작성자 : 최윤석 작성일 : 2010. 9. 8. 설 명 :
 * MDADesignerTransformationUtil
 */
public class MDADesignerTransformationUtil {

    /**
     * Sender Lifeline과 Receiver Lifeline으로 상세 Behavior변환규칙 목록을 가져오는 메소드
     * 
     * @param wholeRules
     * @param senderLifeline
     * @param receiverLifeline
     * @return
     */
    public static List<BehaviorTransformationDetailData> getDetailBehaviorRules(RuleSet wholeRules,
                                                                                Lifeline senderLifeline,
                                                                                Lifeline receiverLifeline) {
        // null 값 체크
        if (wholeRules == null || senderLifeline == null || senderLifeline.getRepresents() == null
            || senderLifeline.getRepresents().getType() == null || receiverLifeline == null
            || receiverLifeline.getRepresents() == null || receiverLifeline.getRepresents().getType() == null) {
            return null;
        }

        // Sender Lifeline의 구조변환규칙 얻기
        SourceStructureTransformationData senderStructureRule = getSourceStructureRule(wholeRules, senderLifeline);
        // Receiver Lifeline의 구조변환규칙 얻기
        SourceStructureTransformationData receiverStructureRule = getSourceStructureRule(wholeRules, receiverLifeline);
        if (senderStructureRule == null || receiverStructureRule == null) {
            return null;
        }

        // Sender SourceType 명 얻기
        String senderSourceName = senderStructureRule.getSourceType().getSourceName();
        // Receiver SourceType 명 얻기
        String receiverSourceName = receiverStructureRule.getSourceType().getSourceName();
        if (senderSourceName == null || receiverSourceName == null) {
            return null;
        }

        // Sender SourceType명과 Receiver SourceType명으로 상세 Behavior변환규칙 목록 얻기
        List<BehaviorTransformationDetailData> detailBehaviorRules = getDetailBehaviorRules(wholeRules,
            senderSourceName,
            receiverSourceName);
        if (detailBehaviorRules == null) {
            return null;
        }

        return detailBehaviorRules;
    }
    
    /**
     * 행위 규칙에 맞는 Sender SourceType명과 Receiver SourceType명을 리스트에 넣어서 리턴.
     * 0번째 : Sender, 1번째 : Receiver.
     * 
     * @param wholeRules
     * @param senderLifeline
     * @param receiverLifeline
     * @return
     */
    private static List<String> getBehaviorLifelineTypeName(RuleSet wholeRules, 
    		Lifeline senderLifeline, Lifeline receiverLifeline) {
    	
    	// null 값 체크
        if (wholeRules == null || senderLifeline == null || senderLifeline.getRepresents() == null
            || senderLifeline.getRepresents().getType() == null || receiverLifeline == null
            || receiverLifeline.getRepresents() == null || receiverLifeline.getRepresents().getType() == null) {
            return null;
        }

        // Sender Lifeline의 구조변환규칙 얻기
        SourceStructureTransformationData senderStructureRule = getSourceStructureRule(wholeRules, senderLifeline);
        // Receiver Lifeline의 구조변환규칙 얻기
        SourceStructureTransformationData receiverStructureRule = getSourceStructureRule(wholeRules, receiverLifeline);
        if (senderStructureRule == null || receiverStructureRule == null) {
            return null;
        }

        // Sender SourceType 명 얻기
        String senderSourceName = senderStructureRule.getSourceType().getSourceName();
        // Receiver SourceType 명 얻기
        String receiverSourceName = receiverStructureRule.getSourceType().getSourceName();
        if (senderSourceName == null || receiverSourceName == null) {
            return null;
        }
        
        List<String> result = new ArrayList<String>();
        result.add(senderSourceName);
        result.add(receiverSourceName);
        
        return result;
    }

    /**
     * Sender Lifeline과 Receiver Lifeline과 셀프 여부로 상세 Behavior변환규칙 목록을 가져오는 메소드
     * 
     * @param wholeRules
     * @param senderLifeline
     * @param receiverLifeline
     * @param isSelf
     * @return List<BehaviorTransformationDetailData>
     */
    public static List<BehaviorTransformationDetailData> getDetailBehaviorRules(RuleSet wholeRules,
                                                                                Lifeline senderLifeline,
                                                                                Lifeline receiverLifeline,
                                                                                boolean isSelf) {
    	List<String> lifelineNames = getBehaviorLifelineTypeName(wholeRules, senderLifeline, receiverLifeline);
    	if (lifelineNames == null || lifelineNames.size() == 0) {
    		return null;
    	}

        // Sender SourceType명과 Receiver SourceType명으로 상세 Behavior변환규칙 목록 얻기
        List<BehaviorTransformationDetailData> detailBehaviorRules = getDetailBehaviorRules(wholeRules, 
        		lifelineNames.get(0), lifelineNames.get(1), isSelf);
        if (detailBehaviorRules == null) {
            return null;
        }

        return detailBehaviorRules;
    }
    
    /**
     * 행위 변환룰의 Remark 정보 리턴.
     * 
     * @param wholeRules
     * @param senderLifeline
     * @param receiverLifeline
     * @param isSelf
     * @return
     */
    public static String getBehaviorTransformationRemark(RuleSet wholeRules,
            Lifeline senderLifeline, Lifeline receiverLifeline, boolean isSelf) {
    	
    	List<String> lifelineNames = getBehaviorLifelineTypeName(wholeRules, senderLifeline, receiverLifeline);
    	if (lifelineNames == null || lifelineNames.size() == 0) {
    		return null;
    	}

    	// Sender SourceType명과 Receiver SourceType명으로 해당 행위룰을 찾아서 Remark 정보를 가져온다.
    	String remark = getBahaviorRemark(wholeRules, lifelineNames.get(0), 
    			lifelineNames.get(1), isSelf);

        return remark;
        
    }

    /**
     * Lifeline으로 Source 구조변환규칙을 가져오는 메소드
     * 
     * @param wholeRules
     * @param lifeline
     * @return
     */
    private static SourceStructureTransformationData getSourceStructureRule(RuleSet wholeRules, Lifeline lifeline) {
        // null 값 체크
        if (wholeRules == null || lifeline == null || lifeline.getRepresents() == null
            || lifeline.getRepresents().getType() == null) {
            return null;
        }

        EList<SourceStructureTransformationData> structureRules = wholeRules.getStructureRules();
        Type lifelineType = lifeline.getRepresents().getType();

        SourceStructureTransformationData sourceStructureRule = null;

        if (structureRules == null) {
            return null;
        }

        if (lifelineType instanceof Actor) {
            sourceStructureRule = TransformationFactory.eINSTANCE.createSourceStructureTransformationData();

            SourceType sourceType = TransformationFactory.eINSTANCE.createSourceType();
            sourceType.setSourceName(MDDCoreConstant.ACTOR);
            sourceStructureRule.setSourceType(sourceType);

            return sourceStructureRule;
        } else {
            for (Iterator<SourceStructureTransformationData> iter = structureRules.iterator(); iter.hasNext();) {
                sourceStructureRule = iter.next();

                if (isAppliedStereotype(lifelineType, sourceStructureRule.getSourceType().getSourceName())) {
                    return sourceStructureRule;
                }
            }

            if (lifelineType instanceof Type) {
                sourceStructureRule = TransformationFactory.eINSTANCE.createSourceStructureTransformationData();
                SourceType sourceType = TransformationFactory.eINSTANCE.createSourceType();
                sourceType.setSourceName(MDDCoreConstant.TYPE);
                sourceStructureRule.setSourceType(sourceType);
                return sourceStructureRule;
            }
        }

        return null;
    }

    /**
     * Sender SourceType명과 Receiver SourceType명으로 상세 Behavior변환규칙 목록을 가져오는 메소드
     * 
     * @param wholeRules
     * @param senderSourceName
     * @param receiverSourceName
     * @return
     */
    private static List<BehaviorTransformationDetailData> getDetailBehaviorRules(RuleSet wholeRules,
                                                                                 String senderSourceName,
                                                                                 String receiverSourceName) {
        // null 값 체크
        if (wholeRules == null || senderSourceName == null || receiverSourceName == null) {
            return null;
        }

        List<BehaviorTransformationData> behaviorRules = wholeRules.getBehaviorRules();

        if (behaviorRules == null) {
            return null;
        }

        for (BehaviorTransformationData behaviorRule : behaviorRules) {
            if (behaviorRule.getSourceRelation().getSource().getSourceName().equals(senderSourceName)
                && behaviorRule.getSourceRelation().getTarget().getSourceName().equals(receiverSourceName)) {
                return behaviorRule.getBehaviorTransformationDetailDataSet();
            }
        }

        return null;
    }

    /**
     * Sender SourceType명과 Receiver SourceType명과 셀프 여부로 상세 Behavior변환규칙 목록을 가져오는
     * 메소드
     * 
     * @param wholeRules
     * @param senderSourceName
     * @param receiverSourceName
     * @param isSelf
     * @return List<BehaviorTransformationDetailData>
     */
    private static List<BehaviorTransformationDetailData> getDetailBehaviorRules(RuleSet wholeRules,
                                                                                 String senderSourceName,
                                                                                 String receiverSourceName,
                                                                                 boolean isSelf) {
        // null 값 체크
        if (wholeRules == null || senderSourceName == null || receiverSourceName == null) {
            return null;
        }

        List<BehaviorTransformationData> behaviorRules = wholeRules.getBehaviorRules();

        if (behaviorRules == null) {
            return null;
        }

        for (BehaviorTransformationData behaviorRule : behaviorRules) {
            if (behaviorRule.getSourceRelation().getSource().getSourceName().equals(senderSourceName)
                && behaviorRule.getSourceRelation().getTarget().getSourceName().equals(receiverSourceName)
                && behaviorRule.getSourceRelation().isSelfRelation() == isSelf) {
                return behaviorRule.getBehaviorTransformationDetailDataSet();
            }
        }

        return null;
    }
    
    /**
     * 행위 규칙에서 Remark 정보를 리턴한다.
     * 
     * @param wholeRules
     * @param senderSourceName
     * @param receiverSourceName
     * @param isSelf
     * @return
     */
    private static String getBahaviorRemark(RuleSet wholeRules, 
    		String senderSourceName, String receiverSourceName, boolean isSelf) {
    	
    	// null 값 체크
        if (wholeRules == null || senderSourceName == null || receiverSourceName == null) {
            return null;
        }

        List<BehaviorTransformationData> behaviorRules = wholeRules.getBehaviorRules();

        if (behaviorRules == null) {
            return null;
        }

        for (BehaviorTransformationData behaviorRule : behaviorRules) {
            if (behaviorRule.getSourceRelation().getSource().getSourceName().equals(senderSourceName)
                && behaviorRule.getSourceRelation().getTarget().getSourceName().equals(receiverSourceName)
                && behaviorRule.getSourceRelation().isSelfRelation() == isSelf) {
                return behaviorRule.getRemark();
            }
        }

        return null;
    	
    }

    /**
     * Target Lifeline의 상세 구조변환규칙 목록을 가져오는 메소드
     * 
     * @param wholeRules
     * @param detailBehaviorRules
     * @return
     */
    public static List<TargetStructureTransformationData> getDetailStructureRulesOfTargetLifelines(
                                                                                                   RuleSet wholeRules,
                                                                                                   List<BehaviorTransformationDetailData> detailBehaviorRules) {
        // null 값 체크
        if (wholeRules == null || detailBehaviorRules == null || detailBehaviorRules.isEmpty()) {
            return null;
        }

        // 전체 상세 구조변환규칙 목록 얻기
        List<TargetStructureTransformationData> wholeDetailStructureRules = getDetailStructureRules(wholeRules);
        List<TargetStructureTransformationData> detailStructureRulesOfTargetLifelines = null;

        // null 값 체크
        if (detailBehaviorRules == null || wholeDetailStructureRules == null || wholeDetailStructureRules.isEmpty()) {
            return null;
        }

        for (BehaviorTransformationDetailData behaviorDetailRule : detailBehaviorRules) {
            for (TargetStructureTransformationData detailStructureRule : wholeDetailStructureRules) {
                if (behaviorDetailRule.getTargetRelation()
                    .getSource()
                    .getDataName()
                    .equals(detailStructureRule.getDataName())
                    || behaviorDetailRule.getTargetRelation()
                        .getTarget()
                        .getDataName()
                        .equals(detailStructureRule.getDataName())) {
                    if (detailStructureRulesOfTargetLifelines == null) {
                        detailStructureRulesOfTargetLifelines = new ArrayList<TargetStructureTransformationData>();
                    }

                    if (!detailStructureRulesOfTargetLifelines.contains(detailStructureRule)) {
                        detailStructureRulesOfTargetLifelines.add(detailStructureRule);
                    }
                }
            }
        }

        return detailStructureRulesOfTargetLifelines;
    }

    /**
     * 전체 변환규칙에서 상세 구조변환규칙 목록을 가져오는 메소드
     * 
     * @param wholeRules
     * @return
     */
    private static List<TargetStructureTransformationData> getDetailStructureRules(RuleSet wholeRules) {
        if (wholeRules.getStructureRules().isEmpty()) {
            return null;
        }

        List<TargetStructureTransformationData> detailStructureRules = null;

        for (SourceStructureTransformationData structureRule : wholeRules.getStructureRules()) {
            if (!structureRule.getTargetStructureTransformationDataSet().isEmpty()) {
                if (detailStructureRules == null) {
                    detailStructureRules = new ArrayList<TargetStructureTransformationData>();
                }

                detailStructureRules.addAll(structureRule.getTargetStructureTransformationDataSet());
            }
        }

        return detailStructureRules;
    }

    /**
     * LocationType의 LocationSegment들을 Sequence에 맞게 정렬
     * 
     * @param input
     * @return
     */
    public static List<LocationSegment> sort(EList<LocationSegment> input) {
        if (input == null) {
            return null;
        }

        List<LocationSegment> result = new LinkedList<LocationSegment>();
        LocationSegment ls = null;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                ls = (LocationSegment) input.get(j);

                if (ls.getSequence() == i) {
                    result.add(ls);
                }
            }
        }

        return result;
    }

    /**
     * Location정보를 가진 Segment의 갯수를 반환하는 메소드
     * 
     * @param location
     * @return
     */
    public static int getSize(EList<LocationSegment> location) {
        if (location == null) {
            return 0;
        }

        int flag = 0;
        LocationSegment locationSegment = null;
        String locationName = null;
        LocationKeywordType locationKeyword = null;
        int result = 0;

        for (Iterator<LocationSegment> locationSegmentIterator = location.iterator(); locationSegmentIterator.hasNext();) {
            flag = 0;
            locationSegment = locationSegmentIterator.next();
            locationName = locationSegment.getLocationName();
            locationKeyword = locationSegment.getLocationKeyword();

            if (locationKeyword.getValue() == LocationKeywordType.BASE_VALUE) {
                flag = 1;
            }

            if (flag == 0 && locationKeyword.getValue() == LocationKeywordType.NONE_VALUE && locationName != null
                && !MDDCoreConstant.EMPTY_STRING.equals(locationName.trim())) {
                flag = 1;
            }

            if (flag == 1) {
                result++;
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
     * 스테레오타입과 특정타입명을 입력받아 타입에 해당하는 스테레오타입 목록을 반환한다. 타입명 : base_Class,
     * base_Operation, base_Property
     * 
     * @param stereotypeList
     * @param typeName
     * @return EList<Stereotype>
     */
    public static List<Stereotype> getStereotypeListByTypeName(List<Stereotype> stereotypeList, String typeName) {
        List<Stereotype> stereotypes = new ArrayList<Stereotype>();

        if (typeName == null || MDDCoreConstant.EMPTY_STRING.equals(typeName)) {
            return new ArrayList<Stereotype>(0);
        }

        for (Stereotype stereotype : stereotypeList) {
            // 받아온 타입명과 base_Class 타입명이 같으면 add한다.
            for (Property property : stereotype.getAttributes()) {
                // property name 중 타입명과 일치하는 property가 있으면 add.
                if (typeName.equals(property.getName())) {
                    stereotypes.add(stereotype);
                    break;
                }
            }
        }

        return stereotypes;

    }

    /**
     * 프로파일과 특정타입명을 입력받아 타입에 해당하는 스테레오타입 목록을 반환한다. 타입명 : Class, Operation,
     * Property
     * 
     * @param stereotypeList
     * @param typeName
     * @return EList<Stereotype>
     */
    public static List<Stereotype> getStereotypeListByTypeName(Profile profile, String typeName) {
        if (profile.getOwnedStereotypes() == null) {
            return new ArrayList<Stereotype>(0);
        }
        return getStereotypeListByTypeName(profile.getOwnedStereotypes(), typeName);

    }

    /**
     * 스테레오타입이 적용될 수 있는 엘리먼트들의 이름을 나열해 반환한다. Class, Operation, Property 만 적용될 수
     * 있다고 간주한다. 각각 base_Class, base_Operation, base_Property 임. 이 로직은 프로퍼티 파일
     * 구조를 분석해 나온 로직으로서 정확한지 확인필요(알맞은 메소드가 존재하는지 여부) 명쾌한 해답이 없어요..
     * 
     * @param stereotype
     * @return String
     */
    public static String getApplicableElementName(Stereotype stereotype) {
        if( null == stereotype) {
            return MDDCoreConstant.EMPTY_STRING;
        }
        StringBuffer name = new StringBuffer();

        for (Property property : stereotype.getOwnedAttributes()) {
            for (String typeName : MDDCoreConstant.APPLICABLE_TYPE_NAMES) {
                if (typeName.equals(property.getName())) {
                    name.append(MDDCoreConstant.COMMA);
                    name.append(property.getName().substring(5)); // "base_" 를.
                    // 빼면 엘리먼트 명이
                    // 된다.
                    break;
                }
            }
        }

        if (name.length() > 0) {
            return name.substring(1); // 맨앞에 "," 제거
        } else {
            return MDDCoreConstant.EMPTY_STRING;
        }

    }
    
    /**
     * Class/Component/Interface 등 라이프라인의 타입이 될 수 있는 데이터의 리스트를 반환한다.
     * @param ruleSet
     * @param profile
     * @return
     */
    public static List<SourceStructureTransformationData> getValidLifelineTypes(RuleSet ruleSet, Profile profile) {
        
        List<SourceStructureTransformationData> elementList = new ArrayList<SourceStructureTransformationData>();
        String stereotypeName = null;
        for (SourceStructureTransformationData data : ruleSet.getStructureRules()) {
            stereotypeName = data.getSourceType().getSourceName();
            Stereotype stereotype = (Stereotype) profile.getPackagedElement(stereotypeName);
            String[] baseNames = MDADesignerTransformationUtil.getApplicableElementName(stereotype).split(MDDCoreConstant.COMMA);
//            String[] baseNames = StringUtil.split(MDADesignerTransformationUtil.getApplicableElementName(stereotype), 
//                MDDCoreConstant.COMMA);
            for (String base : baseNames) {
                if( MDDCoreConstant.UML_CLASS_LITERAL.equals(base) || MDDCoreConstant.UML_INTERFACE_LITERAL.equals(base) 
                    || MDDCoreConstant.UML_COMPONENT_LITERAL.equals(base)) {
                    elementList.add(data);
                }
            }
        }
        return elementList;
    }

}
