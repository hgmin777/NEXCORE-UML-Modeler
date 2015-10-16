/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.request;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.gef.Request;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.request</li>
 * <li>설 명 : ActivityDiagramRequest</li>
 * <li>작성일 : 2009-12-28</li>
 * <li>작성자 : Bojun</li>
 * </ul>
 */
public class ActivityDiagramRequest extends Request {
    /** ADD_ATTRIBUTES */
    public static final String CHANGE_SHAPE = UMLMessage.LABEL_CHANGE_SHAPE;

    /** object */
    private Object object;

    /**
     * ActivityDiagramRequest
     * @param object
     */
    public ActivityDiagramRequest(Object object) {
        super();
        this.object = object;
        setType(CHANGE_SHAPE);
    }

    /**
     * getObject
     * 
     * @return Object
     */
    public Object getObject() {
        return object;
    }

    /**
     * setObject
     * 
     * @param object
     *            void
     */
    public void setObject(Object object) {
        this.object = object;
    }
}
