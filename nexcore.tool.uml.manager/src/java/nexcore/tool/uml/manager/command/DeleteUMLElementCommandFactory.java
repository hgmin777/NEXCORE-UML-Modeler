/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLElementCommandFactory</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLElementCommandFactory {

    // public static Command getCommand(Element selectedElement){
    //        
    // return getCommand(selectedElement,true);
    // }
    /**
     * getCommand
     *  
     * @param selectedElement
     * @return Command
     */
    public static Command getCommand(Element selectedElement) {

        if (null == selectedElement.eContainer()) {
            return null;
        }
        Command deleteCommand = null;
        if (selectedElement instanceof Diagram) {
            deleteCommand = new DeleteUMLDiagramCommand((Diagram) selectedElement);
        } else if (selectedElement instanceof Association) {
            deleteCommand = new DeleteUMLAssociationCommand((Association) selectedElement);
        } else if (selectedElement instanceof Dependency) {
            deleteCommand = new DeleteUMLDependencyCommand(selectedElement);
        } else if (selectedElement instanceof Include) {
            deleteCommand = new DeleteUMLIncludeCommand(selectedElement);
        } else if (selectedElement instanceof Extend) {
            deleteCommand = new DeleteUMLExtendCommand(selectedElement);
        } else if (selectedElement instanceof Generalization) {
            deleteCommand = new DeleteUMLGeneralizationCommand(selectedElement);
        } else if (selectedElement instanceof InterfaceRealization) {
            deleteCommand = new DeleteUMLInterfaceRealizationCommand(selectedElement);
        } else if (selectedElement instanceof Realization) {
            deleteCommand = new DeleteUMLRealizationCommand(selectedElement);
        } else if (selectedElement instanceof Property) {
            deleteCommand = new DeleteUMLPropertyCommand((Property) selectedElement);
        } else if (selectedElement instanceof ActivityEdge) {
            deleteCommand = new DeleteUMLActivityEdgeCommand((ActivityEdge) selectedElement);
        } else if (selectedElement instanceof ActivityNode) {
            deleteCommand = new DeleteUMLActivityNodeCommand((ActivityNode) selectedElement);
        } else if ((selectedElement instanceof Lifeline) || (selectedElement instanceof Message)) {
            deleteCommand = new DeleteUMLSequenceElementCommand((NamedElement)selectedElement);
        } else if (selectedElement instanceof NamedElement) {
            deleteCommand = new DeleteUMLPackageableElementCommand((NamedElement) selectedElement);
        }//
        return deleteCommand;
    }

}
