/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : InsertLifeLineNodeCommand</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class InsertLifeLineNodeCommand extends Command {

    /**
     * 현재의 constraint
     */
    private int newX;

    /**
     * 이전의 constraint
     */
    private int oldX;

    /** viewModel */
    private AbstractNode node;

    /** X좌표로 오름순 정렬된 lifeLineNode 리스트 */
    private List<LifeLineNode> lifeLineNodeList;

    /** margin */
    private final int margin = 10;

    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * InsertLifeLineNodeCommand
     * @param editPart
     * @param constraint
     * @param lifeLineNodeList
     * @param diagram
     */
    public InsertLifeLineNodeCommand(EditPart editPart, Rectangle constraint, List<LifeLineNode> lifeLineNodeList,
    Diagram diagram) {
        this.node = (AbstractNode) editPart.getModel();
        this.lifeLineNodeList = lifeLineNodeList;
        this.oldX = node.getX();
        this.newX = constraint.x;
        this.diagram = diagram;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        node.setX(newX);
        if (oldX > newX) {
            setLeftInsertLayout();
        } else {
            setRightInsertLayout();
        }

        if (diagram != null) {
            SequenceUtil.refreshLifeLineOrder(diagram);
        }
    }

    /**
     * 왼쪽으로 Insert 되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setLeftInsertLayout() {

        LifeLineNode lifeLineNode;
        LifeLineNode otherLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (node.equals(lifeLineNode)) {
                for (int k = 0; k < i; k++) {
                    otherLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);

                    if (node.getX() >= otherLifeLineNode.getX()
                        && node.getX() <= otherLifeLineNode.getX() + otherLifeLineNode.getWidth()) {
                        shiftLifeLine(otherLifeLineNode, k);
                        break;
                    }

                    if (node.getX() <= otherLifeLineNode.getX()
                        && node.getX() + node.getWidth() >= otherLifeLineNode.getX()) {
                        shiftOtherLifeLine(lifeLineNode, otherLifeLineNode, k);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * 오른쪽으로 Insert 되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    private void setRightInsertLayout() {

        LifeLineNode lifeLineNode;
        LifeLineNode otherLifeLineNode;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (node.equals(lifeLineNode)) {
                for (int k = i + 1; k < lifeLineNodeList.size(); k++) {
                    otherLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);

                    if (node.getX() >= otherLifeLineNode.getX()
                        && node.getX() <= otherLifeLineNode.getX() + otherLifeLineNode.getWidth()) {
                        shiftLifeLine(otherLifeLineNode, k);
                        break;
                    }

                    if (node.getX() <= otherLifeLineNode.getX()
                        && node.getX() + node.getWidth() >= otherLifeLineNode.getX()) {
                        shiftOtherLifeLine(lifeLineNode, otherLifeLineNode, k);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * shiftLifeLineByLeftInsert
     * 
     * @param otherLifeLineNode
     * @param k
     *            void
     */
    private void shiftLifeLine(LifeLineNode otherLifeLineNode, int k) {
        int shiftX;
        node.setX(otherLifeLineNode.getX() + otherLifeLineNode.getWidth() + margin);
        if (k < lifeLineNodeList.size() - 1 && lifeLineNodeList.get(k + 1) != null
            && lifeLineNodeList.get(k + 1).getX() < node.getX() + node.getWidth()) {
            shiftX = node.getWidth() + margin + margin
                - (lifeLineNodeList.get(k + 1).getX() - (otherLifeLineNode.getX() + otherLifeLineNode.getWidth()));
            rightSifht(shiftX, k);
        }
    }

    /**
     * shiftOtherLifeLineByLeftInert
     * 
     * @param lifeLineNode
     * @param otherLifeLineNode
     * @param k
     *            void
     */
    private void shiftOtherLifeLine(LifeLineNode lifeLineNode, LifeLineNode otherLifeLineNode, int k) {
        int shiftX;
        shiftX = (lifeLineNode.getX() + lifeLineNode.getWidth() + margin) - otherLifeLineNode.getX();
        otherLifeLineNode.setX(lifeLineNode.getX() + lifeLineNode.getWidth() + margin);
        rightSifht(shiftX, k);
    }

    /**
     * Insert되면서 오른쪽으로 밀리는 노드 처리
     * 
     * @param shiftX
     * @param k
     *            void
     */
    private void rightSifht(int shiftX, int k) {
        LifeLineNode shiftNode;
        for (int q = k + 1; q < lifeLineNodeList.size(); q++) {
            shiftNode = (LifeLineNode) lifeLineNodeList.get(q);
            if (node.equals(shiftNode)) {
                break;
            }
            shiftNode.setX(shiftNode.getX() + shiftX);
        }
    }

}
