/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.model;

import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
 * <li>설 명 : ModelCreatorOfStateDiagram</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ModelCreatorOfStateDiagram implements CreationFactory {

    /**
     * State타입이 세부 기능 연결 기능 또는 State 내부의 Region수
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
     * <li>설 명 : StateType</li>
     * <li>작성일 : 2009. 12. 1.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    public enum StateType {
        STATE, COMPOSITE, ORTHOGONOL, SUBMACHINE
    }

    /** 타겟 클래스 */
    @SuppressWarnings("unchecked")
    private Class targetClass;

    /** 수도 스테이트 구분 */
    private PseudostateKind pseudostateKind = PseudostateKind.ENTRY_POINT_LITERAL;

    /** 스테이트 유형 구분자 */
    private StateType stateType = StateType.STATE;

    /**
     * 생성하고자 하는 클래스타입만으로 생성할 수 있는 경우
     * 
     * @param targetClass
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfStateDiagram(Class targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * ModelCreatorOfStateDiagram
     * @param targetClass
     * @param pseudostateKind
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfStateDiagram(Class targetClass, PseudostateKind pseudostateKind) {
        this(targetClass);
        this.pseudostateKind = pseudostateKind;
    }

    /**
     * ModelCreatorOfStateDiagram
     * @param targetClass
     * @param stateType
     */
    @SuppressWarnings("unchecked")
    public ModelCreatorOfStateDiagram(Class targetClass, StateType stateType) {
        this(targetClass);
        this.stateType = stateType;
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        Element newObject = null;
        @SuppressWarnings("unused")
        NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        if (this.targetClass.equals(org.eclipse.uml2.uml.State.class)) {
            newObject = UMLHelper.createState();
            State state = (State) newObject;
            switch (this.stateType) {
                case COMPOSITE:
                    state.createRegion("Region1");
                    break;
                case ORTHOGONOL:
                    state.createRegion("Region1");
                    state.createRegion("Region2");
                    break;
                case SUBMACHINE:
                    state.setSubmachine(null);// 무의미한 작업 내용을 구분하기 위해 남겨놓음.커맨드에서.
                    // StateMachine을 작성하거나 기존의 내용을 할당
                    break;
                default:
                    break;
            }
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Pseudostate.class)) {
            newObject = UMLHelper.createPseudostate();
            Pseudostate pseudostate = (Pseudostate) newObject;
            pseudostate.setKind(this.pseudostateKind);
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Region.class)) {
            newObject = UMLHelper.createRegion();
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.StateInvariant.class)) {
            newObject = UMLHelper.createStateInvariant();
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.Transition.class)) {
            newObject = UMLHelper.createTransition();
        } else if (this.targetClass.equals(org.eclipse.uml2.uml.FinalState.class)) {
            newObject = UMLHelper.createFinalState();
        }

        return newObject;
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        if (this.stateType.equals(StateType.STATE)) {
            return targetClass;
        } else {
            return this.stateType;
        }
    }

}
