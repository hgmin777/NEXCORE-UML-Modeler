/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InterfaceRealization;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLInterfaceRealizationCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLInterfaceRealizationCommand extends DeleteUMLDirectedRelationshipCommand {

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#undoDiretedRelationshipElement()
     */
    @Override
    protected void undoDiretedRelationshipElement() {
        ((org.eclipse.uml2.uml.Component) this.parentElement).getInterfaceRealizations()
            .add((InterfaceRealization) this.targetElement);
    }

    /**
     * @param targetElement
     */
    public DeleteUMLInterfaceRealizationCommand(Element targetElement) {
        super(targetElement);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#deleteDirectedAttributes()
     */
    @Override
    protected void deleteDirectedAttributes() {
        InterfaceRealization interfaceRealization = (InterfaceRealization) this.targetElement;
        this.source = interfaceRealization.getSources().get(0);
        ((Component) this.source).getInterfaceRealizations().remove(interfaceRealization);
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLDirectedRelationshipCommand#restoreDirectedAttributes()
     */
    @Override
    protected void restoreDirectedAttributes() {
        InterfaceRealization interfaceRealization = (InterfaceRealization) this.targetElement;
        this.source = interfaceRealization.getSources().get(0);
        ((Component) this.source).getInterfaceRealizations().add(interfaceRealization);
    }

}
