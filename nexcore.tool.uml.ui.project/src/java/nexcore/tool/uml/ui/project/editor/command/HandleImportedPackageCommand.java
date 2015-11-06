/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.editor.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageImport;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.command</li>
 * <li>설 명 : HandleImportedPackageCommand</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleImportedPackageCommand extends RecordingCommand {

    /** 모델 요소 */
    private Model element;

    /** Import할 Package */
    private org.eclipse.uml2.uml.Package importingPackage;

    /** Comment 적용 여부 */
    private boolean importPackage;

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param importingPackage
     * @param importPackage
     */
    public HandleImportedPackageCommand(TransactionalEditingDomain domain, Model element,
    org.eclipse.uml2.uml.Package importingPackage, boolean importPackage) {
        super(domain);

        this.element = element;
        this.importingPackage = importingPackage;
        this.importPackage = importPackage;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (importPackage) {
            element.createPackageImport(importingPackage);
        } else {
            PackageImport deletingCandidate = null;

            for (org.eclipse.uml2.uml.PackageImport packageImport : element.getPackageImports()) {
                if (packageImport != null) {
                    if (packageImport.getImportedPackage().equals(importingPackage)) {
                        deletingCandidate = packageImport;
                        break;
                    }
                }
            }

            if (deletingCandidate != null) {
                element.getPackageImports().remove(deletingCandidate);
            }
        }
    }

}
