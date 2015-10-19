/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : ChangeForkAndJoinCommand</li>
 * <li>작성일 : 2009-12-28</li>
 * <li>작성자 : Bojun</li>
 * </ul>
 */
public class ChangeForkAndJoinCommand extends Command {

    /**
     * newHeight
     */
    int newHeight = 0;

    /**
     * newWidth
     */
    int newWidth = 0;

    /**
     * oldHeight
     */
    int oldHeight = 0;

    /**
     * oldWidth
     */
    int oldWidth = 0;

    /**
     * newObj
     */
    Object newObj;

    /**
     * figure
     */
    IFigure figure;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        // if (!(obj instanceof EditPart))
        // return;
        // if(obj instanceof ForkNodeEditPart) {
        NotationNode node = (NotationNode) newObj;
        oldHeight = node.getHeight();
        oldWidth = node.getWidth();
        node.setHeight(oldWidth);
        node.setWidth(oldHeight);
        newObj = node;

        newHeight = node.getHeight();
        newWidth = node.getWidth();
        return;
        // }else if(obj instanceof JoinNodeEditPart) {
        // NotationNode node = (NotationNode) ((JoinNodeEditPart)
        // obj).getModel();
        // int height=node.getHeight();
        // node.setHeight(node.getWidth());
        // node.setWidth(height);
        //            
        // return;
        // }
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        figure.setSize(oldWidth, oldHeight);
        super.undo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        figure.setSize(newWidth, newHeight);
        super.redo();
    }

    /**
     * setFigure
     *  
     * @param figure void
     */
    public void setFigure(IFigure figure) {
        this.figure = figure;
    }

    /**
     * getObj
     *  
     * @return Object
     */
    public Object getObj() {
        return newObj;
    }

    /**
     * setObj
     *  
     * @param obj void
     */
    public void setObj(Object obj) {
        this.newObj = obj;
    }

}
