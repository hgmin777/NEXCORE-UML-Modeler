/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : LifeLineDirectEditCommand</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineDirectEditCommand extends Command {
    /** oldText, newText */
    private String newText;

    /** object */
    private Object object;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @SuppressWarnings("unchecked")
    public void execute() {

        if (object instanceof NotationNode) {
            ((NotationNode) object).setName(newText);
            ((NamedElement) ((NotationNode) object).getUmlModel()).setName(newText);
        } else if (object instanceof NamedElement) {
            ((NamedElement) object).setName(newText);
        } else {
            EList<EObject> list = (EList<EObject>) ((NotationNode) object).getUmlModel();
            ((Property) list.get(0)).setName(newText);
        }
        // label.setText(newText);
    }

    /**
     * setText
     *  
     * @param text void
     */
    public void setText(String text) {
        newText = text;
    }

    /**
     * setUmlModel
     *  
     * @param umlModel void
     */
    public void setUmlModel(Object umlModel) {
        object = umlModel;
    }

}
