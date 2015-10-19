/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.AnalysisPlugin;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityPartitionDirectEditCellEditorLocator;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityPartitionDirectEditorManager;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityDiagramComponentEditPolicy;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityPartitionDirectEditorPolicy;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityPartitionLayoutPolicy;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionLayout;
import nexcore.tool.uml.ui.analysis.activitydiagram.util.ActivityDiagramUtil;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.TitleBarBorder;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : ActivityPartitionEditPart</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityPartitionEditPart extends AbstractContainerNodeEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        
        IDialogSettings dialogSetting = ActivityDiagramUtil.getDialogSetting();
        String partitionDirection = AnalysisConstant.EMPTY_STRING;
        boolean isHorizontal = true;
        
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
        
        ContainerNode containerNode = (ContainerNode) getModel();
        ActivityPartitionFigure figure = new ActivityPartitionFigure(containerNode.getName(),
            isHorizontal,
            PositionConstants.CENTER);
        figure.setLayoutManager(new ActivityPartitionLayout(false));
        figure.setLocation(new Point(containerNode.getX(), containerNode.getY()));
        figure.setSize(new Dimension(containerNode.getWidth(), containerNode.getHeight()));

        TitleBarBorder border = figure.getTitleBarBorder();
        border.setBarVisible(true);
        border.setBarFillColor(new Color(null, getFillColor()));
        border.setLineColor(UiCorePlugin.getDefault().getColor("Black"));
        border.setLineVisible(true);
        border.setLineWidth(1);
        border.setBarWidth(30);
        border.setTextColor(new Color(null, getFontColor()));
        border.setFontName("Verdana");
        border.setFontSize(10);
        border.setTextStyle(1);

        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {

            ContainerNode containerNode = (ContainerNode) getModel();
            Rectangle bounds = new Rectangle(containerNode.getX(),
                containerNode.getY(),
                containerNode.getWidth(),
                containerNode.getHeight());

            ActivityPartitionFigure figure = (ActivityPartitionFigure) getFigure();
            figure.setName(((NamedElement) containerNode.getUmlModel()).getName());
            figure.setSize(containerNode.getWidth(), containerNode.getHeight());
            figure.setLocation(new Point(containerNode.getX(), containerNode.getY()));
            
            TitleBarBorder border = figure.getTitleBarBorder();
            border.setBarVisible(true);
            border.setBarFillColor(new Color(null, getFillColor()));
            border.setLineVisible(true);
            border.setLineWidth(1);
            border.setBarWidth(30);
            border.setTextColor(new Color(null, getFontColor()));
            border.setFontName("Verdana");
            border.setFontSize(10);
            border.setTextStyle(1);

            setLayoutConstraint(this, getFigure(), bounds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createGraphicalNodeEditPolicy()
     */
    @Override
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        // 파티션은 컨넥션이 필요없다.
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createLayoutEditPolicy()
     */
    @Override
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new ActivityPartitionLayoutPolicy();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createComponentEditPolicy()
     */
    @Override
    protected ComponentEditPolicy createComponentEditPolicy() {
        return new ActivityDiagramComponentEditPolicy();
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditorPolicy createDirectEditorPolicy() {
        return new ActivityPartitionDirectEditorPolicy();
    }

    
    /** directManager */
    private ActivityPartitionDirectEditorManager directManager = null;

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            performDirectEdit();
            return;
        }
        super.performRequest(req);
    }

    /**
     * performDirectEdit void
     */
    private void performDirectEdit() {

        IFigure figure = null;
        figure = getFigure();
        
        if (directManager == null) {
            setDirectManager(new ActivityPartitionDirectEditorManager(this,
                TextCellEditor.class,
                new ActivityPartitionDirectEditCellEditorLocator(figure , this)));
        }
        directManager.show();
    }

    /**
     * @param manager
     */
    protected void setDirectManager(ActivityPartitionDirectEditorManager manager) {
        directManager = manager;
    }
    

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#getModelChildren()
     */
    @Override
    protected List<EObject> getModelChildren() {
        List<EObject> list = new ArrayList<EObject>();
        list.addAll(((ContainerNode) getModel()).getNodeList());
        return list;
    }

    /**
     * getFillColor
     * 
     * @return RGB
     */
    protected RGB getFillColor() {
        RGB nodeColor = UiCorePlugin.getDefault().getColor("SkyBlue").getRGB();
        ContainerNode viewModel = (ContainerNode) getModel();
        if (viewModel != null) {
            if (viewModel.getFillColor() != null) {
                nodeColor = StringConverter.asRGB(viewModel.getFillColor());
            }
        }
        return nodeColor;
    }

    /**
     * getFontColor
     * 
     * @return RGB
     */
    protected RGB getFontColor() {
        RGB fontColor = UiCorePlugin.getDefault().getColor("Black").getRGB();
        ContainerNode viewModel = (ContainerNode) getModel();
        if (viewModel != null) {
            if (viewModel.getFontColor() != null) {
                fontColor = StringConverter.asRGB(viewModel.getFontColor());
            }
        }
        return fontColor;
    }
}
