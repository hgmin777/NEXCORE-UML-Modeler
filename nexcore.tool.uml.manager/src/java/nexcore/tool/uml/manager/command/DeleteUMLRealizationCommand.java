/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Realization;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLRealizationCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLRealizationCommand extends DeleteUMLDirectedRelationshipCommand {

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
    public DeleteUMLRealizationCommand(Element targetElement) {
        super(targetElement);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#deleteDirectedAttributes()
     */
    @Override
    protected void deleteDirectedAttributes() {
        Realization realization = (Realization) this.targetElement;
        this.source = realization.getSources().get(0);
        this.target = realization.getTargets().get(0);
        realization.getClients().clear();
        realization.getSuppliers().clear();
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#restoreDirectedAttributes()
     */
    @Override
    protected void restoreDirectedAttributes() {
        Realization realization = (Realization) this.targetElement;
        realization.getClients().add((NamedElement) this.source);
        realization.getSuppliers().add((NamedElement) this.target);
    }

}
