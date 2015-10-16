/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.extension;

import java.util.List;

import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.handler</li>
 * <li>설 명 : INotationModelHandler</li>
 * <li>작성일 : 2010. 11. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface INotationModelHandler {

    /**
     * 설계 모델 시퀀스 다이어그램 생성 void
     */
    public void handleSequenceDiagram();

    /**
     * 시퀀스 다이어그램 생성 메소드
     * 
     * @param intact
     * @param name
     * @param sequence_diagram
     * @return Diagram
     */
    public Diagram createDiagram(Interaction intact, String name, DiagramType sequence_diagram);

    /**
     * VOPC 다이어그램 생성
     * 
     * void
     */
    public void handleClassDiagram();

    /**
     * 선택한 요소 목록 설정
     * 
     * @param selectedElementList
     *            void
     */
    public void setElementList(List<Element> selectedElementList);

    /**
     * 진행율 모니터 설정
     * 
     * @param monitor
     *            void
     */
    public void setProgressMonitor(IProgressMonitor monitor);

    /**
     * 변환 규칙 파일 설정
     * 
     * @param ruleSet
     *            void
     */
    public void setRuleSet(RuleSet ruleSet);
}
