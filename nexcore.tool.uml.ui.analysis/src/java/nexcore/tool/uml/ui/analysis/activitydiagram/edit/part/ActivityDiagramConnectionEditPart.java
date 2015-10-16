/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;

import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityDiagramConnectionEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설  명 : ActivityDiagramConnectionEditPart</li>
 * <li>작성일 : 2011. 7. 13.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ActivityDiagramConnectionEditPart extends AbstractDiagramConnectionEditPart {

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.CONNECTION_ROLE, createConnectionEditPolicy());
    }

    /**
     * 컨넥션 에디트 폴리시(삭제정책)를 생성한다. EditPolicy.CONNECTION_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ConnectionEditPolicy createConnectionEditPolicy() {
        return new ActivityDiagramConnectionEditPolicy();
    }
}
