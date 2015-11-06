/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.command;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLExtendCommand</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLExtendCommand extends DeleteUMLDirectedRelationshipCommand {

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#undoDiretedRelationshipElement()
     */
    @Override
    protected void undoDiretedRelationshipElement() {
        ((org.eclipse.uml2.uml.UseCase) this.parentElement).getExtends().add((Extend) this.targetElement);
    }

    /**
     * @param targetElement
     */
    public DeleteUMLExtendCommand(Element targetElement) {
        super(targetElement);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#deleteDirectedAttributes()
     */
    @Override
    protected void deleteDirectedAttributes() {
        Extend extend = (Extend) this.targetElement;
        this.source = extend.getExtendedCase();

    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#restoreDirectedAttributes()
     */
    @Override
    protected void restoreDirectedAttributes() {
        Extend extend = (Extend) this.targetElement;
        extend.setExtendedCase((UseCase) this.source);
    }

}
