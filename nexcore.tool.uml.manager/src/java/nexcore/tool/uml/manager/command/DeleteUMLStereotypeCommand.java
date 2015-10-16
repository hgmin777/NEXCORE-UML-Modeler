/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLStereotypeCommand</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLStereotypeCommand extends Command {

    /**
     * DeleteUMLStereotypeCommand
     * @param targetElement
     */
    public DeleteUMLStereotypeCommand(Element targetElement) {
        this.targetElement = targetElement;

    }

    /** applied stereotype to delete */
    private EList<Stereotype> appliedStereotypeList;

    /** Element to remove stereotype from */
    protected Element targetElement;

    /** Parent of Element to remove stereotype from */
    protected PackageableElement parentElement;

    /** recursive delete commands */
    protected List<DeleteUMLStereotypeCommand> compoundCommand;

    // /** related relationviewmodel to delete */
    // protected List<Relation> relatedRelationViewModel;
    //
    // /** related notationviewmodel to delete */
    // protected List<NotationNode> relatedNotationNodeViewModel;
    //
    /** viewModelContainer */
    // protected HashMap<AbstractView, Diagram> viewModelContainer;
    /**
     * 
     * 
     * @return Element
     */
    public Element getTargetElement() {
        return targetElement;
    }

    /**
     * 커맨드초기화 및 종속커맨드생성 void
     */
    protected void init() {

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        this.redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        this.parentElement = (org.eclipse.uml2.uml.PackageableElement) this.targetElement.getOwner();
        this.appliedStereotypeList = this.targetElement.getAppliedStereotypes();
        this.compoundCommand = new ArrayList<DeleteUMLStereotypeCommand>();
        this.init();
        for (Iterator<DeleteUMLStereotypeCommand> iter = this.compoundCommand.iterator(); iter.hasNext();) {
            iter.next().redo();
        }
        Stereotype stereotype;
        for (Iterator<Stereotype> iter = appliedStereotypeList.iterator(); iter.hasNext();) {
            stereotype = iter.next();
            this.targetElement.unapplyStereotype(stereotype);
            this.delete(stereotype);
        }
        this.redoOwnElement();
    }

    /**
     * 스테레오 타입 삭제와 다른 항목삭제 처리를 위한 함수 void
     */
    protected void redoOwnElement() {
        this.delete(this.targetElement);
    }

    /**
     * 스테레오 타입 삭제와 다른 항목삭제 처리를 위한 함수 void
     */
    // protected void afterRedo(){
    //        
    // }
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Stereotype stereotype;
        for (Iterator<Stereotype> iter = appliedStereotypeList.iterator(); iter.hasNext();) {
            stereotype = iter.next();
            this.targetElement.applyStereotype(stereotype);
        }
        this.undoOwnElement();
        for (int i = this.compoundCommand.size() - 1; i >= 0; i--) {
            this.compoundCommand.get(i).undo();
        }
        this.compoundCommand.clear();
        this.releaseResource();
    }

    /**
     * 스테레오타입 undo와 항목 undo를 위한 함수 void
     */
    // protected void beforeUndo(){
    // this.parentElement.getOwnedElements().add(this.targetElement);
    // }
    /**
     * 스테레오타입 undo와 항목 undo를 위한 함수 void
     */
    protected void undoOwnElement() {
        ((org.eclipse.uml2.uml.Package) this.parentElement).getPackagedElements()
            .add((PackageableElement) this.targetElement);
    }

    /**
     * 
     * 
     * @param command
     *            void
     */
    protected void add(DeleteUMLStereotypeCommand command) {
        if (null == command) {
            return;
        }
        this.compoundCommand.add(command);
    }

    /**
     * 
     * 
     * @param command
     *            void
     */
    protected void remove(DeleteUMLStereotypeCommand command) {
        if (null == command) {
            return;
        }
        this.compoundCommand.remove(command);
    }

    /**
     * 
     * void
     */
    protected void clearCompoundCommand() {
        this.compoundCommand.clear();
    }

    /**
     * 
     * 
     * @param element
     *            void
     */
    protected void delete(Element element) {
        EcoreUtil.remove(element);
    }

    /**
     * 삭제처리를 위해 확보했던 자원 해제 undo처리가 실행될 때 처리됨 void
     */
    protected void releaseResource() {

    }

}
