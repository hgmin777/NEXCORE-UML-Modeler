/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */


package nexcore.tool.uml.ui.project.explorer.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command</li>
 * <li>설 명 : CopyCommand</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class CopyCommand extends Command {

    /** EObject list to copy. */
    private List<EObject> list;

    /** UML Domain */
    // private UMLDomain umlDomain;
    private IDomainModelHandler umlDomain;

    /** 원래 설정되어 있던 클립보드의 내용 */
    private Collection<Object> oldClipboard;

    /**
     * @param objList
     */
    public CopyCommand(List<EObject> objList) {
        umlDomain = DomainRegistry.getUMLDomain();
        list = objList;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        List<String> names = new ArrayList<String>();
        if (ProjectExplorerPlugin.getDisplay().getFocusControl() instanceof Text) {
            names.add(((Text) ProjectExplorerPlugin.getDisplay().getFocusControl()).getSelectionText());

            oldClipboard = umlDomain.getClipboard();
            umlDomain.setClipboard(new ArrayList<Object>(names));
        } else {
            oldClipboard = umlDomain.getClipboard();
            umlDomain.setClipboard(new ArrayList<Object>(list));
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        umlDomain.setClipboard(oldClipboard);
    }

}
