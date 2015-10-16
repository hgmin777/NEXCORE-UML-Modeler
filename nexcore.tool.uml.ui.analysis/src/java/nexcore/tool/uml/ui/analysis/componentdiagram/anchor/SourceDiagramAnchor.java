/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.componentdiagram.anchor;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.draw2d.AbstractConnectionAnchor;
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
 * <li>설 명 : SourceDiagramAnchor</li>
 * <li>작성일 : 2010-2-5</li>
 * <li>작성자 : Duyu</li>
 * </ul>
 */
public class SourceDiagramAnchor extends AbstractConnectionAnchor {

    /** node */
    private AbstractNode target;

    /**
     * @param node
     */
    public SourceDiagramAnchor(IFigure figure, ConnectionEditPart editpart) {
        super(figure);

        target = (AbstractNode) editpart.getTarget().getModel();
    }

    /**
     * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    public Point getLocation(Point reference) {
        Point targetCenterPoint = getCenterPoint(target);
        Point sourceCenterPoint = getOwner().getBounds().getCenter();
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

        Rectangle targetFigure = new Rectangle(new Point(target.getX(), target.getY()),
            new Dimension(target.getWidth(), target.getHeight()));
        Rectangle sourceFigure = new Rectangle(getOwner().getBounds());

        if (targetFigure.getCenter().x <= sourceFigure.getBottomLeft().x
            && targetFigure.getCenter().y >= sourceFigure.getBottomLeft().y) // bottomleft
            return new Point(sourceFigure.getBottomLeft());
        else if (targetFigure.getCenter().x >= sourceFigure.getBottomRight().x
            && targetFigure.getCenter().y >= sourceFigure.getBottomRight().y) // bottomright
            return new Point(sourceFigure.getBottomRight());
        else if (targetFigure.getCenter().x <= sourceFigure.getTopLeft().x
            && targetFigure.getCenter().y <= sourceFigure.getTopLeft().y) // topleft
            return new Point(sourceFigure.getTopLeft());
        else if (targetFigure.getCenter().x >= sourceFigure.getTopRight().x
            && targetFigure.getCenter().y <= sourceFigure.getTopRight().y) // topright
            return new Point(sourceFigure.getTopRight());
        else if (targetFigure.getCenter().x < sourceFigure.getLeft().x) // left
            return new Point(rf.getBounds().getTopRight().x - sourceFigure.width / 2, rf.getBounds().getTopRight().y);
        else if (targetFigure.getCenter().x > sourceFigure.getRight().x) // right
            return new Point(rf.getBounds().getTopRight().x + sourceFigure.width / 2, rf.getBounds().getTopRight().y);
        else if (targetFigure.getCenter().y < sourceFigure.getTop().y) // top
            return new Point(rf.getBounds().getTopLeft().x, rf.getBounds().getBottomLeft().y - sourceFigure.height / 2);
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
            return new Point(rf.getBounds().getBottomLeft().x, rf.getBounds().getTopLeft().y + sourceFigure.height / 2);
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
