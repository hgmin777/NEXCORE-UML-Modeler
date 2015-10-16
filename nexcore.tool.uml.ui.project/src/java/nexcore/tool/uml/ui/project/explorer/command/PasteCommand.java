/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.command;

import java.util.Collection;
import java.util.Iterator;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.paste.ElementPaster;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command</li>
 * <li>설 명 : PasteCommand</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class PasteCommand extends Command {

    /** 클립보드에 복사된 객체들을 붙일 부모 객체 */
    private EObject parent;

    /** UML Domain */
    // private UMLDomain umlDomain;
    private IDomainModelHandler umlDomain;

    /**
     * @param eobject
     */
    public PasteCommand(EObject eobject) {
        parent = eobject;
        umlDomain = DomainRegistry.getUMLDomain();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        Collection<Object> clipboard = umlDomain.getClipboard();
        String name = null;
        if (ProjectExplorerPlugin.getDisplay().getFocusControl() != null
            && ProjectExplorerPlugin.getDisplay().getFocusControl() instanceof Text) {

            Text text = (Text) ProjectExplorerPlugin.getDisplay().getFocusControl();
            for (Iterator<Object> iterator = clipboard.iterator(); iterator.hasNext();) {
                name = (String) iterator.next();

                if (text.getSelectionText().equals(text.getText())) {
                    text.setText(name);
                } else if (text.getSelectionText().equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                    text.setText(text.getText() + name);
                } else {
                    text.setText(text.getText().replaceFirst(text.getSelectionText(), name));
                }
            }

        } else {
            // 2012-01-09 다이어그램 복사 기능 추가때문에 변경함.
//            ProjectUtil.pasteElements(parent, clipboard);
            new ElementPaster().paste(parent, clipboard);
        }
    }

}
