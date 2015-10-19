/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설  명 : OrphanChildCommand</li>
 * <li>작성일 : 2009. 11. 17.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public class OrphanChildCommand extends Command {

    /** oldLocation */
    private Point oldLocation;

    /** parent */
    private ContainerNode parent;

    /** child */
    private NotationNode child;

    /** index */
    private int index;

    /**
     * @param label
     */
    public OrphanChildCommand(String label) {
        super(label);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.index = DiagramUtil.getIndexOfByList(parent.getNodeList(), child);
        this.oldLocation = new Point(child.getX(), child.getY());
        parent.getNodeList().remove(child);
    }
    

    /**
     * @return Returns the child.
     */
    public NotationNode getChild() {
        return child;
    }

    /**
     * @param child
     *            The child to set.
     */
    public void setChild(NotationNode child) {
        this.child = child;
    }

    /**
     * @return Returns the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            The index to set.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return Returns the oldLocation.
     */
    public Point getOldLocation() {
        return oldLocation;
    }

    /**
     * @param oldLocation
     *            The oldLocation to set.
     */
    public void setOldLocation(Point oldLocation) {
        this.oldLocation = oldLocation;
    }

    /**
     * @return Returns the parent.
     */
    public ContainerNode getParent() {
        return parent;
    }

    /**
     * @param parent
     *            The parent to set.
     */
    public void setParent(ContainerNode parent) {
        this.parent = parent;
    }
}
