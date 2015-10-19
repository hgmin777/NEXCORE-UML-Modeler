/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.componentdiagram.anchor;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.componentdiagram.anchor</li>
 * <li>설 명 : TargetDiagramAnchor</li>
 * <li>작성일 : 2010-2-5</li>
 * <li>작성자 : Duyu</li>
 * </ul>
 */
public class TargetDiagramAnchor extends ChopboxAnchor {
    /** node */
    private AbstractNode source;

    /**
     * @param node
     */
    public TargetDiagramAnchor(IFigure figure, ConnectionEditPart editpart) {
        super(figure);

        source = (AbstractNode) editpart.getSource().getModel();
    }

    /**
     * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    public Point getLocation(Point reference) {
        Point sourceCenterPoint = getCenterPoint(source);
        Point targetCenterPoint = getOwner().getBounds().getCenter();
        AbstractNode node = UMLDiagramFactory.eINSTANCE.createNotationNode();
        node.setX(targetCenterPoint.x);
        node.setY(targetCenterPoint.y);
        int x;
        int y;
        int w = sourceCenterPoint.x - targetCenterPoint.x;
        int h = sourceCenterPoint.y - targetCenterPoint.y;
        node.setWidth(w);
        node.setHeight(h);
        RectangleFigure rf = createRectangleFigure(node);

        Rectangle targetFigure = new Rectangle(getOwner().getBounds());
        Rectangle sourceFigure = new Rectangle(new Point(source.getX(), source.getY()),
            new Dimension(source.getWidth(), source.getHeight()));

        if (targetFigure.getCenter().x <= sourceFigure.getBottomLeft().x
            && targetFigure.getCenter().y >= sourceFigure.getBottomLeft().y) // bottomleft
            return super.getLocation(reference);
        else if (targetFigure.getCenter().x >= sourceFigure.getBottomRight().x
            && targetFigure.getCenter().y >= sourceFigure.getBottomRight().y) // bottomright
            return super.getLocation(reference);
        else if (targetFigure.getCenter().x <= sourceFigure.getTopLeft().x
            && targetFigure.getCenter().y <= sourceFigure.getTopLeft().y) // topleft
            return super.getLocation(reference);
        else if (targetFigure.getCenter().x >= sourceFigure.getTopRight().x
            && targetFigure.getCenter().y <= sourceFigure.getTopRight().y) // topright
            return super.getLocation(reference);
        else if (targetFigure.getCenter().x < sourceFigure.getLeft().x) // left
            return new Point(rf.getBounds().getTopLeft().x + targetFigure.width / 2, rf.getBounds().getTopLeft().y);
        else if (targetFigure.getCenter().x > sourceFigure.getRight().x) // right
            return new Point(rf.getBounds().getTopLeft().x - targetFigure.width / 2, rf.getBounds().getTopLeft().y);
        else if (targetFigure.getCenter().y < sourceFigure.getTop().y) // top
            return new Point(rf.getBounds().getTopLeft().x, rf.getBounds().getTopLeft().y + targetFigure.height / 2);
        else if (targetFigure.getCenter().y > sourceFigure.getBottom().y) { // bottom
            x = targetCenterPoint.x;
            y = sourceCenterPoint.y;
            node.setX(x);
            node.setY(y);
            w = sourceCenterPoint.x - targetCenterPoint.x;
            h = targetCenterPoint.y - sourceCenterPoint.y;
            node.setWidth(w);
            node.setHeight(h);
            rf = createRectangleFigure(node);
            return new Point(rf.getBounds().getBottomLeft().x, rf.getBounds().getBottomLeft().y - targetFigure.height
                / 2);
        }

        return null;
    }

    /**
     * getCenterPoint
     * 
     * @param model
     * @return Point
     */
    public static Point getCenterPoint(AbstractNode model) {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(model.getWidth(), model.getHeight());
        rectangle.setLocation(model.getX(), model.getY());

        return rectangle.getCenter();
    }

    /**
     * createRectangleFigure
     * 
     * @param model
     * @return RectangleFigure
     */
    public static RectangleFigure createRectangleFigure(AbstractNode model) {
        RectangleFigure figure = new RectangleFigure();
        figure.setLocation(new Point(model.getX(), model.getY()));
        figure.setSize(new Dimension(model.getWidth(), model.getHeight()));

        return figure;
    }
}
