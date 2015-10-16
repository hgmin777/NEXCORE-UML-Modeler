/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : ActivityPartitionDirectEditCommand</li>
 * <li>작성일 : 2009. 12. 4.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class ActivityPartitionDirectEditCommand extends Command {
    /** oldText, newText */
    private String oldText, newText;

    /** object */
    private Object umlElement = null;

    /** notationNode */
    private ActivityPartitionFigure partitionFigure;

    /** editpart */
    private EditPart editpart;

    /** viewModel for Note or Text */
    private AbstractNode viewModel = null;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (viewModel == null) {
            oldText = partitionFigure.getName();
        } else if (viewModel instanceof ContainerNode) {

            oldText = ((ContainerNode) viewModel).getName();
        }

        oldText = oldText.replaceAll(UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET, "");
        oldText = oldText.replaceAll(UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET, "");
        redo();

    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {

        if (viewModel == null) {
            if (umlElement instanceof NamedElement) {
                ((NamedElement) umlElement).setName(newText);
            }
        } else if (viewModel instanceof ContainerNode) {
            ((ContainerNode)viewModel).setName(newText);
            partitionFigure.setName(newText);
            if (umlElement instanceof NamedElement) {
                ((NamedElement) umlElement).setName(newText);
            }
        }
    }

    /**
     * setLabelFigure
     *  
     * @param object void
     */
    public void setLabelFigure(Object object) {
        partitionFigure = (ActivityPartitionFigure) object;
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
        umlElement = umlModel;
    }

    /**
     * setEditPart
     *  
     * @param host void
     */
    public void setEditPart(EditPart host) {
        this.editpart = host;
    }

    /**
     * setViewModel
     *  
     * @param model void
     */
    public void setViewModel(AbstractNode model) {
        this.viewModel = model;
    }
}
