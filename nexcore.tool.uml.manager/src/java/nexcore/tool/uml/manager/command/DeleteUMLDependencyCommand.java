/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLDependencyCommand</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLDependencyCommand extends DeleteUMLDirectedRelationshipCommand {

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#undoDiretedRelationshipElement()
     */
    @Override
    protected void undoDiretedRelationshipElement() {
        ((org.eclipse.uml2.uml.Package) this.parentElement).getPackagedElements()
            .add((PackageableElement) this.targetElement);
    }

    /**
     * @param targetElement
     */
    public DeleteUMLDependencyCommand(Element targetElement) {
        super(targetElement);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#deleteDirectedAttributes()
     */
    @Override
    protected void deleteDirectedAttributes() {
        Dependency dependency = (Dependency) this.targetElement;
        // this.source = dependency.getSources().get(0);
        // this.target = dependency.getTargets().get(0);
        dependency.getClients().clear();
        dependency.getSuppliers().clear();
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#restoreDirectedAttributes()
     */
    @Override
    protected void restoreDirectedAttributes() {
        // Dependency dependency = (Dependency) this.targetElement;
        // dependency.getClients().add((NamedElement) this.source);
        // dependency.getSuppliers().add((NamedElement) this.target);
    }

}
