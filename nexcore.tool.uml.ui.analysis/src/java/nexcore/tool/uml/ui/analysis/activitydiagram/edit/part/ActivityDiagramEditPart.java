/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityDiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;
import nexcore.tool.uml.ui.analysis.activitydiagram.util.ActivityDiagramUtil;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.jface.dialogs.IDialogSettings;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : ActivityDiagramEditPart</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityDiagramEditPart extends AbstractDiagramEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        Figure fiugre = new FreeformLayer();
        fiugre.setBorder(new MarginBorder(10));
        fiugre.setLayoutManager(new FreeformLayout());
        fiugre.setOpaque(false);

        LayoutManager layout = new ActivityDiagramLayout();
        fiugre.setLayoutManager(layout);

        return fiugre;
    }

    public class ActivityDiagramLayout extends FreeformLayout {

        /** PARTITION_LOCATION */
        private static final int PARTITION_LOCATION = 20;

        /**
         * @see org.eclipse.draw2d.XYLayout#layout(org.eclipse.draw2d.IFigure)
         */
        @Override
        public void layout(IFigure parent) {
            Iterator children = parent.getChildren().iterator();
            List<ActivityPartitionFigure> partitions = new ArrayList<ActivityPartitionFigure>();
            IFigure f;
            while (children.hasNext()) {
                f = (IFigure) children.next();
                Rectangle bounds = (Rectangle) getConstraint(f);
                if (bounds == null)
                    continue;
                if (f instanceof ActivityPartitionFigure) {
                    partitions.add((ActivityPartitionFigure) f);
                }
            }
            
            for( ActivityPartitionFigure partition : partitions ) {
                if(0 == partitions.indexOf(partition)) {
                    partition.setLocation( new org.eclipse.draw2d.geometry.Point(PARTITION_LOCATION, PARTITION_LOCATION) );
                    continue;
                }
                
                int index = partitions.indexOf(partition) - 1;
                ActivityPartitionFigure prePartition = partitions.get(index);

                IDialogSettings dialogSetting = ActivityDiagramUtil.getDialogSetting();
                String partitionDirection = AnalysisConstant.EMPTY_STRING;
                boolean isHorizontal = true;
                
                partitionDirection = dialogSetting.get(AnalysisConstant.DIRECTION);
                partitionDirection = dialogSetting.get(AnalysisConstant.DIRECTION);
                if( null == partitionDirection ) {
                    isHorizontal = false;
                    dialogSetting.put(AnalysisConstant.DIRECTION, AnalysisConstant.VERTICAL);
                }
                if( AnalysisConstant.HORIZONTAL.equals(partitionDirection) ) {
                    isHorizontal = true;
                } else if( AnalysisConstant.VERTICAL.equals(partitionDirection) ) {
                    isHorizontal = false;
                }

                if( isHorizontal ) {
                    partition.setLocation( new org.eclipse.draw2d.geometry.Point(PARTITION_LOCATION , prePartition.getLocation().y + prePartition.getSize().height) );
                } else {
                    partition.setLocation( new org.eclipse.draw2d.geometry.Point(prePartition.getLocation().x + prePartition.getSize().width , PARTITION_LOCATION) );
                }
            }
        }
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#createLayoutEditPolicy()
     */
    @Override
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new ActivityDiagramLayoutEditPolicy();
    }

}
