/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.property.part.factory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.property.part.factory</li>
 * <li>설  명 : SectionPartFactory</li>
 * <li>작성일 :2010. 2. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface SectionPartFactory {

    /**
     * 자신의 컴포짓을 그리기 위한 추상화 메소드 - 하위에서 컴포짓을 그리는 메소드들을 선택된 요소에 맞게 호출하여 컴포짓을 그린다
     * 
     * void
     */
    abstract void createParts(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage);

    /**
     * (공통) 일반 - 이름 컴포짓 생성 메소드
     * 
     * void
     */
    void createNamePart(Composite parent);

    /**
     * (공통) 일반 - 가시성 컴포짓 생성 메소드
     * 
     * void
     */
    void createVisibilityPart(Composite parent);

    /**
     * (엘리먼트) 일반 - 추상 컴포짓 생성 메소드
     * 
     * void
     */
    void createAbstractPart(Composite parent);

    /**
     * (엘리먼트) 일반 - 리프 컴포짓 생성 메소드
     * 
     * void
     */
    void createLeafPart(Composite parent);

    /**
     * (패키지) 일반 - 기준 컴포짓 생성 메소드
     * 
     * void
     */
    void createStandardPart(Composite parent);

    /**
     * (아티팩트) 일반 - 파일 이름 컴포짓 생성 메소드
     * 
     * void
     */
    void createFileNamePart(Composite parent);

    /**
     * (일반화) 일반 - 대체 컴포짓 생성 메소드
     * 
     * void
     */
    void createSubstitutionPart(Composite parent);

    /**
     * (상호작용) 일반 - 스펙 컴포짓 생성 메소드
     * 
     * void
     */
    void createSpecificationPart(Composite parent);

    /**
     * (라이프라인) 일반 - 표시 컴포짓 생성 메소드
     * 
     * void
     */
    void createRepresentsPart(Composite parent);

    /**
     * (공통) 일반 - 유형 컴포짓 생성 메소드
     * 
     * void
     */
    void createTypePart(Composite parent);

    /**
     * (연관) 일반 - 레이블 컴포짓 생성 메소드
     * 
     * void
     */
    void createLabelPart(Composite parent);

    /**
     * (연관) 일반 - 집계 컴포짓 생성 메소드
     * 
     * void
     */
    void createAggregationPart(Composite parent);

    /**
     * (연관) 일반 - 역할 컴포짓 생성 메소드
     * 
     * void
     */
    void createRolePart(Composite parent);

    /**
     * (연관) 일반 - 다중성 컴포짓 생성 메소드
     * 
     * void
     */
    void createMultiplicityPart(Composite parent);

    /**
     * (연관) 일반 - 방향 컴포짓 생성 메소드
     * 
     * void
     */
    void createIsNavigablePart(Composite parent);

    /**
     * (메시지) 일반 - 서명 컴포짓 생성 메소드
     * 
     * void
     */
    void createSignaturePart(Composite parent);

    /**
     * (메시지) 일반 - 유형 컴포짓 생성 메소드
     * 
     * void
     */
    void createMessageSortTypePart(Composite parent);

    /**
     * (메시지) 일반 - 인수 서명 컴포짓 생성 메소드
     * 
     * void
     */
    void createArgumentSignaturePart(Composite parent);

    /**
     * (메시지) 일반 - 인수 컴포짓 생성 메소드
     * 
     * void
     */
    void createArgumentsPart(Composite parent);

    /**
     * (스테레오타입) 일반 - 스테레오타입 컴포짓 생성 메소드
     * 
     * void
     */
    void createStereoTypePart(Composite parent);

    /**
     * (속성) 일반 - 속성 컴포짓 생성 메소드
     * 
     * void
     */
    void createPropertyPart(Composite parent);

    /**
     * (오퍼레이션) 일반 - 오퍼레이션 컴포짓 생성 메소드
     * 
     * void
     */
    void createOperationPart(Composite parent);

    /**
     * (문서) 일반 - 문서 컴포짓 생성 메소드
     * 
     * void
     */
    void createDocumentPart(Composite parent);

}
