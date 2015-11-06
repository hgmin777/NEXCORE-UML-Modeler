/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.manager.UMLManager;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairDanglingObjectCommand</li>
 * <li>작성일 : 2010. 3. 29.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class RepairDanglingObjectCommand extends Command {

    /**
     * RepairDanglingObjectCommand
     * @param eObject
     */
    public RepairDanglingObjectCommand(EObject eObject) {
        this.eObject = eObject;
    }

    /**
     * eObject
     */
    private EObject eObject;

    /**
     * @param behaviorExecutionSpecification
     *            the behaviorExecutionSpecification to set
     */
    public void setEObject(EObject eObject) {
        this.eObject = eObject;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (this.eObject instanceof BehaviorExecutionSpecification) {
            this.clearInteraction((BehaviorExecutionSpecification) this.eObject);
        }

    }

    /**
     * clearInteraction
     *  
     * @param behaviorExecutionSpecification void
     */
    private void clearInteraction(BehaviorExecutionSpecification behaviorExecutionSpecification) {
        Interaction interaction = (Interaction) behaviorExecutionSpecification.eContainer();
        if (null != interaction) {
            interaction.getFragments().remove(this.eObject);
            for (Lifeline lifeline : interaction.getLifelines()) {
                lifeline.getCoveredBys().remove(behaviorExecutionSpecification);
            }
            UMLManager.deleteElement(this.eObject);
        }
    }

}
