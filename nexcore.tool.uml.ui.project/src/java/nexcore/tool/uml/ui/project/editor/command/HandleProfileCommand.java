/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.command</li>
 * <li>설 명 : HandleProfileCommand</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleProfileCommand extends RecordingCommand {

    /** UML 패키지 요소 */
    private Package packageElement;

    /** 적용 혹은 적용 해제할 프로파일 요소 */
    private Profile profile;

    /** 프로파일 적용 여부 */
    private boolean applyProfile = false;

    /**
     * 생성자
     * 
     * @param domain
     * @param packageElement
     * @param profile
     * @param applyProfile
     */
    public HandleProfileCommand(TransactionalEditingDomain domain, Package packageElement, Profile profile, boolean applyProfile) {
        super(domain);

        this.packageElement = packageElement;
        this.profile = profile;

        this.applyProfile = applyProfile;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if(applyProfile) {
            packageElement.applyProfile(profile);
        } else {
            packageElement.unapplyProfile(profile);
        }
    }

}
