/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */
package nexcore.tool.mdd.core.util;

import nexcore.tool.mda.model.designer.transformation.RuleSet;

import org.eclipse.emf.common.util.URI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mda.core</li>
 * <li>서브 업무명 : nexcore.tool.mda.core.infrastructure.util</li>
 * <li>설 명 : MDADesignerTransformationData</li>
 * <li>작성일 : 2010. 10. 7.</li>
 * <li>작성자 : 진해용</li>
 * </ul>
 */
public class MDADesignerTransformationData {

    /** 변환규칙 집합 */
    private RuleSet ruleSet;

    /** 소스 모델 */
    // private Model sourceModel;
    /** 타겟 모델 */
    // private Model targetModel;
    /** 소스 모델 URI */
    private URI sourceModelURI;

    /** 타겟 모델 URI */
    private URI targetModelURI;

    /**
     * 생성자
     * 
     * @param ruleSet
     */
    public MDADesignerTransformationData(RuleSet ruleSet) {
        this.ruleSet = ruleSet;

        // this.sourceModel = MDDCoreUtil.getSourceModel(ruleSet);
        // this.targetModel = MDDCoreUtil.getTargetModel(ruleSet);
        if (ruleSet.getSourceModelLocation() != null) {
            this.setSourceModelURI(URI.createURI(ruleSet.getSourceModelLocation()));
        }
        if (ruleSet.getTargetModelLocation() != null) {
            this.setTargetModelURI(URI.createURI(ruleSet.getTargetModelLocation()));
        }
    }

    /**
     * 변환규칙 집합 설정
     * 
     * @param ruleSet
     *            void
     */
    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    /**
     * 변환규칙 집합 반환
     * 
     * @return RuleSet
     */
    public RuleSet getRuleSet() {
        return ruleSet;
    }

    /**
     * 소스 모델 설정
     * 
     * @param sourceModel
     *            void
     */
    /*
     * public void setSourceModel(Model sourceModel) { this.sourceModel =
     * sourceModel; }
     */

    /**
     * 소스 모델 반환
     * 
     * @return Model
     */
    /*
     * public Model getSourceModel() { return sourceModel; }
     */

    /**
     * 타겟 모델 설정
     * 
     * @param targetModel
     *            void
     */
    /*
     * public void setTargetModel(Model targetModel) { this.targetModel =
     * targetModel; }
     */

    /**
     * 타겟 모델 반환
     * 
     * @return Model
     */
    /*
     * public Model getTargetModel() { return targetModel; }
     */

    /**
     * 소스 모델 URI 설정
     * 
     * @param sourceModelURI
     *            void
     */
    public void setSourceModelURI(URI sourceModelURI) {
        this.sourceModelURI = sourceModelURI;
    }

    /**
     * 소스 모델 URI 반환
     * 
     * @return URI
     */
    public URI getSourceModelURI() {
        return sourceModelURI;
    }

    /**
     * 타겟 모델 URI 설정
     * 
     * @param targetModelURI
     *            void
     */
    public void setTargetModelURI(URI targetModelURI) {
        this.targetModelURI = targetModelURI;
    }

    /**
     * 타겟 모델 URI 반환
     * 
     * @return URI
     */
    public URI getTargetModelURI() {
        return targetModelURI;
    }

}
