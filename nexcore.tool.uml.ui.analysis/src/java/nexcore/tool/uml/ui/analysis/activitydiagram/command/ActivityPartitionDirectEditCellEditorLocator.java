/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityDiagramEditPart.ActivityDiagramLayout;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : ActivityPartitionDirectEditCellEditorLocator</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class ActivityPartitionDirectEditCellEditorLocator implements CellEditorLocator {

    /** LOCATOR_HEIGHT_MARGIN */
    private static final int LOCATOR_HEIGHT_MARGIN = 25;
    /** LOCATOR_WIDTH_MARGIN */
    private static final int LOCATOR_WIDTH_MARGIN = 5;
    /** LOCATOR_MARGIN */
    private static final int LOCATOR_MARGIN = 2;
    /** figure */
    private IFigure figure;
    /** activityPartitionEditPart */
    private ActivityPartitionEditPart activityPartitionEditPart;

    /**
     * ActivityPartitionDirectEditCellEditorLocator
     * @param f
     * @param activityPartitionEditPart
     */
    public ActivityPartitionDirectEditCellEditorLocator(IFigure f, ActivityPartitionEditPart activityPartitionEditPart) {
        this.figure = f;
        this.activityPartitionEditPart = activityPartitionEditPart;
    }

    /**
     * @see org.eclipse.gef.tools.CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
     */
    public void relocate(CellEditor celleditor) {
        Text text = (Text) celleditor.getControl();

        if ( figure instanceof ActivityPartitionFigure ) {

            ActivityPartitionFigure partition = (ActivityPartitionFigure) figure;
            
            ScalableFreeformRootEditPart rootEditPart;
            rootEditPart = (ScalableFreeformRootEditPart) activityPartitionEditPart.getRoot();
            GraphicalViewer viewer = (GraphicalViewer) rootEditPart.getViewer();
            FigureCanvas canvas = (FigureCanvas) viewer.getControl();
            Viewport viewport = canvas.getViewport();
            
            IFigure parentFigure = partition.getParent();
            ActivityDiagramLayout partitionLayout = (ActivityDiagramLayout) parentFigure.getLayoutManager();
            Rectangle cellLocation = new Rectangle( partition.getBounds().x + LOCATOR_MARGIN - viewport.getHorizontalRangeModel().getValue(),
                partition.getBounds().y + LOCATOR_MARGIN - viewport.getVerticalRangeModel().getValue(),
                partition.getBounds().width - LOCATOR_WIDTH_MARGIN,
                LOCATOR_HEIGHT_MARGIN );

            if(!text.isDisposed()){
                text.setBounds(cellLocation.x, cellLocation.y, cellLocation.width, cellLocation.height);
            }
        }
    }
    
    /**
     * getFigure
     *  
     * @return IFigure
     */
    public IFigure getFigure() {
        return figure;
    }
}
