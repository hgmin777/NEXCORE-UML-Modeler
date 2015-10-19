/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action.registry;

import nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action.registry</li>
 * <li>설 명 : UMLExplorerExtendedActionWrapper</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLExplorerExtendedActionWrapper implements IUMLExplorerExtendedActionWrapper {

    /** 액션 */
    private AbstractUMLExplorerExtendedAction action;

    /** 아이디 */
    private String id;

    /** 라벨 */
    private String label;

    /**
     * 액션 설정
     * 
     * @param action
     *            void
     */
    public void setAction(AbstractUMLExplorerExtendedAction action) {
        this.action = action;
    }

    /**
     * 액션 반환
     * 
     * @see nexcore.tool.uml.ui.project.explorer.action.registry.IUMLExplorerExtendedActionWrapper#getAction()
     */
    public AbstractUMLExplorerExtendedAction getAction() {
        return this.action;
    }

    /**
     * 아이디 설정
     * 
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 아이디 반환
     * 
     * @see nexcore.tool.uml.ui.project.explorer.action.registry.IUMLExplorerExtendedActionWrapper#getId()
     */
    public String getId() {
        return this.id;
    }

    /**
     * 라벨 설정
     * 
     * @param label
     *            void
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 라벨 반환
     * 
     * @see nexcore.tool.uml.ui.project.explorer.action.registry.IUMLExplorerExtendedActionWrapper#getLabel()
     */
    public String getLabel() {
        return this.label;
    }

}
