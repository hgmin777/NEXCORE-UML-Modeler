/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : BendpointCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class BendpointCommand extends Command {

    /** pointIndex */
    protected int pointIndex;

    /** pointLocation */
    protected Point pointLocation;

    /** firstDimension, secondDimension */
    private Dimension firstDimension, secondDimension;

    /** relation */
    protected Relation relation;

    /** polylineConnection */
    protected PolylineConnection polylineConnection;

    /**
     * 
     * 
     * @return nexcore.tool.uml.model.umldiagram.Dimension
     */
    protected nexcore.tool.uml.model.umldiagram.Dimension getFirstRelativeDimension() {
        nexcore.tool.uml.model.umldiagram.Dimension dimension = UMLDiagramFactory.eINSTANCE.createDimension();
        dimension.setWidth(firstDimension.width);
        dimension.setHeight(firstDimension.height);
        return dimension;
    }

    /**
     * 
     * 
     * @return nexcore.tool.uml.model.umldiagram.Dimension
     */
    protected nexcore.tool.uml.model.umldiagram.Dimension getSecondRelativeDimension() {
        nexcore.tool.uml.model.umldiagram.Dimension dimension = UMLDiagramFactory.eINSTANCE.createDimension();
        dimension.setWidth(secondDimension.width);
        dimension.setHeight(secondDimension.height);
        return dimension;
    }

    /**
     * getIndex
     *  
     * @return int
     */
    protected int getIndex() {
        return pointIndex;
    }

    /**
     * getLocation
     *  
     * @return Point
     */
    protected Point getLocation() {
        return pointLocation;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        execute();
    }

    /**
     * setRelativeDimensions
     *  
     * @param dim1
     * @param dim2 void
     */
    public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
        firstDimension = dim1;
        secondDimension = dim2;
    }

    /**
     * setIndex
     *  
     * @param i void
     */
    public void setIndex(int i) {
        pointIndex = i;
    }

    /**
     * setLocation
     *  
     * @param p void
     */
    public void setLocation(Point p) {
        pointLocation = p;
    }

    /**
     * setModel
     *  
     * @param object void
     */
    public void setModel(Object object) {
        this.relation = (Relation) object;
    }

    /**
     * setFigure
     *  
     * @param figure void
     */
    public void setFigure(IFigure figure) {
        this.polylineConnection = (PolylineConnection) figure;
    }
}
