/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.command;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLGeneralizationCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLGeneralizationCommand extends DeleteUMLDirectedRelationshipCommand {

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#undoDiretedRelationshipElement()
     */
    @Override
    protected void undoDiretedRelationshipElement() {
        ((org.eclipse.uml2.uml.Classifier) this.parentElement).getGeneralizations()
            .add((Generalization) this.targetElement);
    }

    /**
     * @param targetElement
     */
    public DeleteUMLGeneralizationCommand(Element targetElement) {
        super(targetElement);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#deleteDirectedAttributes()
     */
    @Override
    protected void deleteDirectedAttributes() {
        Generalization generalization = (Generalization) this.targetElement;
        this.source = generalization.getSources().get(0);
        ((Classifier) this.source).getGeneralizations().remove(generalization);
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#restoreDirectedAttributes()
     */
    @Override
    protected void restoreDirectedAttributes() {
        Generalization generalization = (Generalization) this.targetElement;
        this.source = generalization.getSources().get(0);
        ((Classifier) this.source).getGeneralizations().add(generalization);
    }

}
