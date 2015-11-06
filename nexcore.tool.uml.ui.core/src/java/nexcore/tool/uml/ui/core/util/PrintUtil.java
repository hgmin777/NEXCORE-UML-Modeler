/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.extension.IUMLDiagramApplication;
import nexcore.tool.uml.ui.core.diagram.extension.UMLDiagramApplicationRegistry;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * 문서 산출물에 들어갈 다이어그램 이미지를 만든다.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : PrintUtil</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class PrintUtil {
    /** figure 목록 */
    List<IFigure> figureList;

    /** 경계최소값 */
    int minX;

    /** 경계최소값 */
    int minY;

    /** 경계최대값 */
    int maxX;

    /** 경계최소대값 */
    int maxY;
    
    /**
     * x
     */
    /**
     * y
     */
    int x, y;
    /**
     * @return the figureList
     */
    public List<IFigure> getFigureList() {
        return figureList;
    }

    /**
     * 
     */
    public PrintUtil() {
        figureList = new ArrayList<IFigure>();
        minX = minY = 999;
        maxX = maxY = 0;
    }

    /**
     * @param eobject
     *            Diagram
     */
    public Image getDiagramImage(Diagram diagram) {

        String editorID;
        switch (diagram.getType().getValue()) {
            case DiagramType.CLASS_DIAGRAM_VALUE:
                editorID = UICoreConstant.PROJECT_CONSTANTS__ClassEditorID;
                break;
            case DiagramType.ACTIVITY_DIAGRAM_VALUE:
                editorID = UICoreConstant.PROJECT_CONSTANTS__ActivityEditorID;
                break;
            case DiagramType.USE_CASE_DIAGRAM_VALUE:
                editorID = UICoreConstant.PROJECT_CONSTANTS__UsecaseEditorID;
                break;
            case DiagramType.SEQUENCE_DIAGRAM_VALUE:
                editorID = UICoreConstant.PROJECT_CONSTANTS__SequenceEditorID;
                break;
            case DiagramType.COMPONENT_DIAGRAM_VALUE:
                editorID = UICoreConstant.PROJECT_CONSTANTS__ComponentEditorID;
                break;
            default:
                return null;
        }
        try {
            UiCorePlugin.getDefault().getImageRegistry();
            // proxy resolving
            if (DomainUtil.isProxy(diagram)) {
                diagram = (Diagram) EcoreUtil.resolve(diagram, diagram.eResource().getResourceSet());
            }

            IUMLDiagramApplication diagramApplication = UMLDiagramApplicationRegistry.getInstance()
                .getApplication(editorID);
            EditPartFactory editPartFactory = diagramApplication.getFactory();

            GraphicalViewer viewer = new ScrollingGraphicalViewer();
            viewer.setEditPartFactory(editPartFactory);
            viewer.setRootEditPart(new ScalableFreeformRootEditPart());

            viewer.setContents(diagram);
            Object editpart = viewer.getRootEditPart().getChildren().get(0);
            ((EditPart) editpart).refresh();
            ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) viewer.getEditPartRegistry().get(LayerManager.ID);

            Font font;
            font = UiCorePlugin.getDefault().getFont("default");
//            if (diagram.getType().equals(DiagramType.CLASS_DIAGRAM)) {
//                font = UiCorePlugin.getDefault().getFont("default8");
//            } else {
//                font = UiCorePlugin.getDefault().getFont("default");
//            }
            
            IFigure rootFigure = ((LayerManager) rootEditPart).getLayer(LayerConstants.PRINTABLE_LAYERS);
            rootFigure.setFont(font);
            Object editPart = rootEditPart.getChildren().get(0);
            if (!(editPart instanceof AbstractDiagramEditPart)) {
                return null;
            }
            IFigure notationFigure = ((AbstractDiagramEditPart) editPart).getFigure();
            ConnectionLayer connectionLayer = (ConnectionLayer) rootFigure.getChildren().get(1);

            figureList.clear();
            notationFigure.validate();
            connectionLayer.validate();
            minX = minY = 999;
            maxX = maxY = 0;
            getFigureList(rootFigure);
            Image img = null;
          
            x = this.maxX + 6 - this.minX;
            y = this.maxY + 6 - this.minY;
//            x = x < 630 ? 630 : x;
//            y = y < 430 ? 430 : y;
            if(x<0) {
                x = 0;
            }
            if(y<0) {
                y=0;
            }
            img = new Image(Display.getDefault(), x + 10, y + 10);
            //img = new Image(Display.getDefault(), figure.getSize().width, figure.getSize().height);

            GC gc = new GC(img);
            //gc.setClidpping(0, 0, this.maxX, this.maxY);
            gc.setClipping(0, 0, x, y);
            gc.setAntialias(SWT.ON);
            // gc.setAdvanced(true);
            SWTGraphics graphics = new SWTGraphics(gc);
            graphics.translate(3 - this.minX, 3 - this.minY);
            //graphics.translate(- this.minY, - this.minY);
            rootEditPart.refresh();
            notationFigure.setBorder(null);
            notationFigure.paint(graphics);
            connectionLayer.paint(graphics);
            gc.dispose();
            graphics.dispose();
            return img;
        } catch (Exception err) {
            err.printStackTrace();
            Log.error(diagram.getName());
            Log.error(err);
            return null;
        }
    }

    /**
     * 
     * 
     * @param rootFigure
     * @return List<IFigure>
     */
    @SuppressWarnings("unchecked")
    public void getFigureList(IFigure rootFigure) {
        List children = rootFigure.getChildren();
        IFigure figure;
        Point bottomRight;
        Rectangle rectangle;
        for (Object element : children) {
            if (element instanceof IFigure) {
                figure = (IFigure) element;
                rectangle = figure.getBounds();
                if (0 == rectangle.height && 0 == rectangle.width) {
                    continue;
                }
                if (!(figure.getParent() instanceof FreeformLayer || figure.getParent() instanceof ConnectionLayer)) {
                    continue;
                }
                if (this.minX > rectangle.x) {
                    this.minX = rectangle.x;
                }
                if (this.minY > rectangle.y) {
                    this.minY = rectangle.y;
                }
                bottomRight = rectangle.getBottomRight();
                if (this.maxX < bottomRight.x) {
                    this.maxX = bottomRight.x;
                }
                if (this.maxY < bottomRight.y) {
                    this.maxY = bottomRight.y;
                }
                figureList.add(figure);
            }
        }
        for (Object element : children) {
            if (element instanceof IFigure) {
                getFigureList((IFigure) element);
            }
        }
    }
    
    /**
     * getImageWidth
     *  
     * @return int
     */
    public int getImageWidth() {
        return x;
    }
    
    /**
     * getImageHeight
     *  
     * @return int
     */
    public int getImageHeight() {
        return y;
    }

}
