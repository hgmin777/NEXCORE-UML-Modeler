/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.project.extension;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.extension</li>
 * <li>설  명 : UMLCommandExecutionUnitWrapper</li>
 * <li>작성일 : 2011. 6. 3.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLCommandExecutionUnitWrapper implements IUMLCommandExecutionUnitWrapper {

    /** Id */
    private String id;

    /** Label */
    private String label;

    /** 우선순위 */
    private String priority;

    /** Command 실행단위 */
    private UMLCommandExecutionUnit executionUnit;

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.IUMLCommandExecutionUnitWrapper#getId()
     */
    public String getId() {
        return id;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.IUMLCommandExecutionUnitWrapper#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.IUMLCommandExecutionUnitWrapper#getPriority()
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Command 실행단위 설정
     *  
     * @param executionUnit void
     */
    public void setExecutionUnit(UMLCommandExecutionUnit executionUnit) {
        this.executionUnit = executionUnit;
    }

    /**
     * @see nexcore.tool.uml.ui.core.project.extension.IUMLCommandExecutionUnitWrapper#getExecutionUnit()
     */
    public UMLCommandExecutionUnit getExecutionUnit() {
        return executionUnit;
    }

}
