/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.command;

import java.util.List;

import nexcore.tool.uml.manager.UMLManager;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLPropertyCommand</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLPropertyCommand extends DeleteUMLAssociationCommand {

    /**
     * property
     */
    private Property property;

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLAssociationCommand#redo()
     */
    @Override
    public void redo() {
        List<Element> elementList = UMLManager.getRelatedElement(property);
        for (Element element : elementList) {
            if (element instanceof Lifeline) {
                ((Lifeline) element).setRepresents(null);
            }
        }
        if (null != this.association) {
            super.redo();
        } else {
            UMLManager.deleteElement(this.property);
        }
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLAssociationCommand#undo()
     */
    @Override
    public void undo() {
        if (null != this.association) {
            super.undo();
        } else {
            ((org.eclipse.uml2.uml.Package) this.parentElement).getPackagedElements()
                .add((PackageableElement) this.property);
        }
    }

    /**
     * @param association
     */
    protected DeleteUMLPropertyCommand(Association association) {
        super(association);
    }

    /**
     * DeleteUMLPropertyCommand
     * @param property
     */
    public DeleteUMLPropertyCommand(Property property) {

        super(property.getAssociation());

        this.property = property;
        if (null == property.getAssociation()) {
            this.parentElement = (org.eclipse.uml2.uml.PackageableElement) this.property.getOwner();
        }

    }

}
