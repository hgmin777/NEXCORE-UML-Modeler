/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mdd.core.extension;

import java.util.HashMap;
import java.util.List;

import nexcore.tool.mda.model.designer.transformation.RuleSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.handler</li>
 * <li>설 명 : ISemanticModelHandler</li>
 * <li>작성일 : 2010. 11. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface ISemanticModelHandler {

//    /**
//     * 유스케이스 생성(From NCP Meta Content) 초기화
//     * 
//     * @param projectCode
//     * @param selectedContents void
//     */
//    public void initializeUseCaseCreationFromNCPMetaContent(String projectCode, List<?> selectedContents);
//
//    /**
//     * 유스케이스 생성(From NCP Meta Content)
//     * 
//     *   void
//     */
//    public void createUseCaseFromNCPMetaContent();

    /**
     * 분석 모델 구조 생성
     * 
     * void
     */
    public void createAnalysisModelStructure();

    /**
     * 설계 모델 구조 생성
     * 
     * void
     */
    public void createStructure();

    /**
     * 설계 모델 행위 생성
     * 
     * @throws Exception void
     */
    public void createBehavior() throws Exception;

    /**
     * 유스케이스 추적 매트릭스 생성에 필요한 초기화 메소드
     * 
     * @param selectedAnalysisModel
     * @param selectedDesignModel
     *            void
     */
    public void initializeUseCaseTraceMatrixCreation(Model selectedAnalysisModel, Model selectedDesignModel);

    /**
     * 유스케이스 추적 매트릭스 생성
     * 
     * @return boolean
     */
    public boolean createUseCaseTraceMatrix();

    /**
     * 키워드의 스테레오타입 변환
     * 
     * void
     */
    public void convertKeywordToStereotype();

    /**
     * 클래스 병합에 필요한 초기화 메소드
     * 
     * @param firstResult
     * @param className
     * @param clear
     *            void
     */
    public void initializeClassesMerger(Object firstResult, String className, boolean clear);

    /**
     * 클래스 병합
     * 
     * void
     */
    public void mergeClassToClass();

    /**
     * 용어 관리에 필요한 초기화 메소드
     * 
     * void
     */
    public void initializeGlossaryManagement();

    /**
     * 용어 사전 생성
     * 
     * @return boolean
     */
    public boolean createGlossaryDictionary();

    /**
     * 용어의 영문 변환
     *  
     * @return boolean
     */
    public boolean translateGlossaryIntoEnglish();

    /**
     * 선택한 요소 목록 설정
     * 
     * @param selectedElementList
     *            void
     */
    public void setElementList(List<Element> selectedElementList);

    /**
     * 부모 쉘 설정
     * 
     * @param parentShell
     *            void
     */
    public void setParentShell(Shell parentShell);

    /**
     * 진행율 모니터 설정
     * 
     * @param monitor
     *            void
     */
    public void setProgressMonitor(IProgressMonitor monitor);

    /**
     * 선택한 목적 모델 설정
     * 
     * @param selectedTargetModel
     *            void
     */
    public void setSelectedTargetModel(Model selectedTargetModel);

    /**
     * 변환 규칙 파일 설정
     * 
     * @param ruleSet
     *            void
     */
    public void setRuleSet(RuleSet ruleSet);

    /**
     * 공유 객체 설정
     *  
     * @param sharingObject void
     */
    public void setSharingObject(Object sharingObject);
    

    /**
     * 용어사전에 생성할 UML 목록 설정
     *  
     * @param list void
     */
    public void setGenerateList(HashMap<String, Boolean> list);

}
