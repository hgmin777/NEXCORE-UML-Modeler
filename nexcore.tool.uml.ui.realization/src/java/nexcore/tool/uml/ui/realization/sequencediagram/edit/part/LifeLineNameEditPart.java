/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.command.LifeLineDirectEditCellEditorLocator;
import nexcore.tool.uml.ui.realization.sequencediagram.command.LifeLineDirectEditorManager;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDirectEditorPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.CollapsedLabel;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.LifeLineNameFigure;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : LifeLineNameEditPart</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineNameEditPart extends AbstractLifeLineCompartmentEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        LifeLineNameFigure figure = null;
        if (getParent().getModel() instanceof NotationNode) {
            NotationNode notationNode = (NotationNode) getParent().getModel();

            if (notationNode.getUmlModel() instanceof Lifeline) {
                Lifeline lifeline = (Lifeline) notationNode.getUmlModel();
                figure = new LifeLineNameFigure(lifeline.getName());
                figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
                figure.setSize(notationNode.getWidth(), FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
            } else if(notationNode.getUmlModel() instanceof CombinedFragment){
                System.out.println(notationNode.getUmlModel());
            }
        }
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            String strLifelineName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            NotationNode notationNode = (NotationNode) getParent().getModel();
            Lifeline lifeline = (Lifeline) notationNode.getUmlModel();
            LifeLineNameFigure figure = (LifeLineNameFigure) getFigure();

            List<Figure> figures = figure.getChildren();
            for (Figure childFigure : figures) {
                if (childFigure instanceof CollapsedLabel) {
                    ((CollapsedLabel) childFigure).setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                    ((CollapsedLabel) childFigure).repaint();
                    
                } else if (childFigure instanceof Label) {
                    ((Label) childFigure).setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                }
            }

            figure.setName(lifeline.getName());
            figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
            figure.setSize(notationNode.getWidth(), FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
            figure.getLabel()
                .setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_LIFELINE));
            figure.getLabel().setBackgroundColor(new Color(null, getFontColor()));
            figure.getLabel().setForegroundColor(new Color(null, getFontColor()));
            figure.getNameLabel2().setBackgroundColor(new Color(null, getFontColor()));
            figure.getNameLabel2().setForegroundColor(new Color(null, getFontColor()));
            figure.getStereoTypeLabel().setBackgroundColor(new Color(null, getFontColor()));
            figure.getStereoTypeLabel().setForegroundColor(new Color(null, getFontColor()));

            if (lifeline.getRepresents() != null) {
                Type type = lifeline.getRepresents().getType();

                if (type != null) {
                    String stereoTypeName = ProjectUtil.getStereotypeLabel(type);
                    String typeName = type.getName();
                    if (type instanceof org.eclipse.uml2.uml.Component) {
                        figure.getLabel()
                            .setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_COMPONENT));
                    } else if (type instanceof org.eclipse.uml2.uml.Class) {
                        figure.getLabel()
                            .setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CLASS));
                    } else if (type instanceof org.eclipse.uml2.uml.Actor) {
                        figure.getLabel()
                            .setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ACTOR));
                    } else if (type instanceof org.eclipse.uml2.uml.Interface) {
                        figure.getLabel()
                            .setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_INTERFACE));
                    }

                    if (SequenceUtil.checkLifelineVisibility()) {
                        strLifelineName = figure.getName();
                    } else {
                        strLifelineName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
                    if (stereoTypeName != null && !UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(stereoTypeName)) {                 
                        figure.setName(strLifelineName + UICoreConstant.PROJECT_CONSTANTS__COLON + typeName);
                        figure.setStereotype(stereoTypeName);
                    } else {
                        figure.setName(strLifelineName + UICoreConstant.PROJECT_CONSTANTS__COLON + typeName);     
                        figure.setStereotype(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                    }
                }
            }

            getFigure().setBackgroundColor(new Color(null, getFillColor()));

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_LIFELINE_NAME_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }

    }
    
    /**
     * isSelected
     */
    private boolean isSelected = false;

    /**
     * isSelected
     *  
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * setSelected
     *  
     * @param isSelected void
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        if (isSelected) {
            return true;
        }
        if (getParent().getSelected() == 2 || getSameLevelSelected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSameLevelSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getSameLevelSelected() {
        List<EditPart> editParts = getParent().getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }


    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        if (super.getSelected() == 2) {
            isSelected = true;
        } else if (super.getSelected() == 0) {
            isSelected = false;
        }
        return super.getSelected();
    }
    
    /**
     * getFillColor
     * 
     * @return RGB
     */
    protected RGB getFillColor() {
        RGB nodeColor = PreferenceUtil.INSTANCE.getNodeFillColor();
        AbstractView viewModel = (AbstractView) getParent().getModel();
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
        RGB fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();
        AbstractView viewModel = (AbstractView) getParent().getModel();
        if (viewModel != null) {
            if (viewModel.getFontColor() != null) {
                fontColor = StringConverter.asRGB(viewModel.getFontColor());
            }
        }
        return fontColor;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditPolicy createDirectEditorPolicy() {
        return new SequenceDirectEditorPolicy();
    }

    /**
     * performDirectEdit void
     */
    @Override
    protected void performDirectEdit(Request req) {
        if (directManager == null) {
            directManager = new LifeLineDirectEditorManager(this,
                TextCellEditor.class,
                new LifeLineDirectEditCellEditorLocator(getFigure()));
        }
        directManager.show();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        if (request instanceof SelectionRequest && ((SelectionRequest) request).getLastButtonPressed() == 3)
            return null;
        return new SequenceDelegatingDragEditPartsTracker(this, getParent());
    }

    /**
     * getSourceConnectionAnchor
     *  
     * @param connection
     * @return ConnectionAnchor
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * getSourceConnectionAnchor
     *  
     * @param request
     * @return ConnectionAnchor
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());

    }

    /**
     * getTargetConnectionAnchor
     *  
     * @param connection
     * @return ConnectionAnchor
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * getTargetConnectionAnchor
     *  
     * @param request
     * @return ConnectionAnchor
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

}
