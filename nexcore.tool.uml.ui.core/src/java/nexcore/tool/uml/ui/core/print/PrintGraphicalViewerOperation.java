/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.print;

import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramRootEditPart;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PrinterGraphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Display;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.print</li>
 * <li>설 명 : PrintGraphicalViewerOperation</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintGraphicalViewerOperation extends org.eclipse.gef.print.PrintGraphicalViewerOperation {

    /**
     * viewer
     */
    private GraphicalViewer viewer;
    /**
     * previewBounds
     */
    private Rectangle previewBounds;
    /**
     * printer
     */
    private Printer printer;
    /**
     * PrintGraphicalViewerOperation
     * @param p
     * @param g
     */
    public PrintGraphicalViewerOperation(Printer p, GraphicalViewer g) {
        super(p, g);
        viewer = g;
        printer = p;
        previewBounds = ((DiagramRootEditPart)g.getRootEditPart()).getPrintPreviewFigure().getBounds();
    }

    /**
     * 실제 프린트에서 출력될 용지 1장의 크기를 반환한다.
     * 
     * @return
     */
    public Rectangle getClipRect() {

        GC gc = new GC(getPrinter(), SWT.LEFT_TO_RIGHT);
        PrinterGraphics graphics = new PrinterGraphics(new SWTGraphics(gc), printer);
        setupGraphicsForPage(graphics);
        
        IFigure figure = getPrintSource();
        setupPrinterGraphicsFor(graphics, figure);
        Rectangle clipRect = new Rectangle();
        graphics.pushState();       
        int x = previewBounds.x, y = previewBounds.y;
        graphics.translate(-x, -y);
        graphics.getClip(clipRect);
        return clipRect;        
    }

    /**
     * 실제 프린트기에서 출력을 실행한다.
     * 
     * @return
     */
    protected void printPages() {   
        DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart)viewer.getRootEditPart();
        diagramRootEditPart.redrawPrintPreview();
        previewBounds = diagramRootEditPart.getPrintPreviewFigure().getBounds();
        
        PrinterGraphics graphics = getFreshPrinterGraphics();
        IFigure figure = getPrintSource();
        setupPrinterGraphicsFor(graphics, figure);
        
        int x = previewBounds.x, y = previewBounds.y;
        Rectangle clipRect = new Rectangle();
        while (y < previewBounds.y + previewBounds.height) {
            while (x < previewBounds.x + previewBounds.width) {
                graphics.pushState();               
                getPrinter().startPage();
                graphics.translate(-x, -y);
                graphics.getClip(clipRect);
                clipRect.setLocation(x, y);
                graphics.clipRect(clipRect);
                figure.paint(graphics);
                getPrinter().endPage();
                graphics.popState();
                x += clipRect.width;
            }
            x = previewBounds.x;
            y += clipRect.height;
        }
    }
    
    /**
     * @see org.eclipse.draw2d.PrintFigureOperation#setupPrinterGraphicsFor(org.eclipse.draw2d.Graphics, org.eclipse.draw2d.IFigure)
     */
    protected void setupPrinterGraphicsFor(Graphics graphics, IFigure figure) {
        double dpiScale = (double)getPrinter().getDPI().x / Display.getCurrent().getDPI().x;
        
        Rectangle printRegion = getPrintRegion();
        // put the print region in display coordinates
        printRegion.width /= dpiScale;
        printRegion.height /= dpiScale;
        
        
//        Rectangle bounds = figure.getBounds();
        double xScale = (double)printRegion.width / previewBounds.width;
        double yScale = (double)printRegion.height / previewBounds.height;
        switch (getPrintMode()) {
            case FIT_PAGE:
                graphics.scale(Math.min(xScale, yScale) * dpiScale);
                break;
            case FIT_WIDTH:
                graphics.scale(xScale * dpiScale);
                break;
            case FIT_HEIGHT:
                graphics.scale(yScale * dpiScale);
                break;
            default:
                graphics.scale(dpiScale);
        }
        graphics.setForegroundColor(figure.getForegroundColor());
        graphics.setBackgroundColor(figure.getBackgroundColor());
        graphics.setFont(figure.getFont());
    }
}
