/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.CompartmentLabelFigure;
import nexcore.tool.uml.ui.core.diagram.figure.HeaderFooterFigure;
import nexcore.tool.uml.ui.core.diagram.figure.PrintPreviewFigure;
import nexcore.tool.uml.ui.core.print.PrintGraphicalViewerOperation;
import nexcore.tool.uml.ui.core.print.PrintPage;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : DiagramRootEditPart</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class DiagramRootEditPart extends ScalableFreeformRootEditPart {

    /**
     * DiagramRootEditPart
     */
    public DiagramRootEditPart() {
        super();
        printPreviewFigure = new PrintPreviewFigure();
        printPreviewFigure.setVisible(false);
        getLayer(PRINT_PREVIEW_LAYER).add(printPreviewFigure);

        headerFooterFigureList = new ArrayList<HeaderFooterFigure>();
    }

    /**
     * PRINT_PREVIEW_LAYER
     */
    final public static String PRINT_PREVIEW_LAYER = "Print Preview Layer"; //$NON-NLS-1$

    /**
     * printPreviewFigure
     */
    private PrintPreviewFigure printPreviewFigure;

    /**
     * headerFooterFigureList
     */
    private List<HeaderFooterFigure> headerFooterFigureList;

    /**
     * getPrintPreviewFigure
     *  
     * @return PrintPreviewFigure
     */
    public PrintPreviewFigure getPrintPreviewFigure() {
        return printPreviewFigure;
    }

    /**
     * @see org.eclipse.gef.editparts.ScalableFreeformRootEditPart#createScaledLayers()
     */
    protected ScalableFreeformLayeredPane createScaledLayers() {

        ScalableFreeformLayeredPane layers = super.createScaledLayers();
        layers.add(new FreeformLayer(), PRINT_PREVIEW_LAYER, 0);
        return layers;
    }

    /**
     * setHeaderFooterFigureVisibility
     *  
     * @param bool void
     */
    private void setHeaderFooterFigureVisibility(boolean bool) {
        for (Iterator<HeaderFooterFigure> it = headerFooterFigureList.iterator(); it.hasNext();) {
            ((HeaderFooterFigure) it.next()).setVisible(bool);
        }
    }

    /**
     * removeHeaderFooterFigures
     *   void
     */
    private void removeHeaderFooterFigures() {
        for (Iterator<HeaderFooterFigure> it = headerFooterFigureList.iterator(); it.hasNext();) {
            getLayer(LayerConstants.PRINTABLE_LAYERS).remove((HeaderFooterFigure) it.next());
        }

        headerFooterFigureList.clear();
    }

    /**
     * 프린트 미리보기 화면을 토글(toggle)한다.
     * 
     * @return
     */
    public void togglePreviewFigure() {
        if (!printPreviewFigure.isVisible()) {
            showPrintPreview();
        } else {
            printPreviewFigure.setVisible(false);
            setHeaderFooterFigureVisibility(false);
        }
    }

    /**
     * 프린트 미리보기 화면을 생성한다.
     * 
     * @return
     */
    public void redrawPrintPreview() {
        // r - 출력해야할 화면의 사이즈
        Rectangle r = getPrintRegionSize();

        // 기본 프린터로 설정된 프린터의 설정정보를 가져옴
        PrinterData printData = Printer.getDefaultPrinterData();
        Printer printer = new Printer(printData);

        GraphicalViewer viewer;

        viewer = (GraphicalViewer) UiCorePlugin.getActivePage().getActivePart().getAdapter(GraphicalViewer.class);
        PrintGraphicalViewerOperation op = new PrintGraphicalViewerOperation(printer, viewer);

        Insets inset = new Insets();

        boolean useMm = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM);
        if (useMm) {
            // inset - mm 여백
            inset.top = (int) PrintPage.mmToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP)));
            inset.left = (int) PrintPage.mmToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT)));
            inset.right = (int) PrintPage.mmToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT)));
            inset.bottom = (int) PrintPage.mmToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM)));
        } else {
            // inset - inches 여백
            inset.top = (int) PrintPage.inchesToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP)));
            inset.left = (int) PrintPage.inchesToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT)));
            inset.right = (int) PrintPage.inchesToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT)));
            inset.bottom = (int) PrintPage.inchesToPixel(Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM)));
        }

        op.setPrintMargin(inset);

        Rectangle clipRect = new Rectangle();
        
        // 현재 프린터에 설정된 출력용지 1장의 크기를 가져옴
        clipRect = op.getClipRect();

        String value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION);
        if (value.equals(UMLMessage.LABEL_PRINT_LANDSCAPE)) {
            int tmp = clipRect.width;
            clipRect.width = clipRect.height;
            clipRect.height = tmp;
            printData.orientation = PrinterData.LANDSCAPE;
        }
        else {            
            printData.orientation = PrinterData.PORTRAIT;            
        }
            

        int pageColCount = (r.getSize().width - r.x) / clipRect.width + 1;
        int pageRowCount = (r.getSize().height - r.y) / clipRect.height + 1;
        printPreviewFigure.setPageCount(pageRowCount, pageColCount);
        printPreviewFigure.setSize(pageColCount * clipRect.width, pageRowCount * clipRect.height);
        printPreviewFigure.setLocation(r.getLocation());

        // 화면에 머리글/꼬리글 출력
        drawHeaderFooterFigures(pageColCount, pageRowCount, clipRect);
    }
    
    /**
     * showPrintPreview
     *   void
     */
    public void showPrintPreview() {
        redrawPrintPreview();
        printPreviewFigure.setVisible(true);
        setHeaderFooterFigureVisibility(true);
    }

    /**
     * 머리글, 꼬리글 (Header, Footer) Figure를 생성한다.
     * 
     * @param pageColCount
     * @param pageRowCount
     * @param clipRect
     * @return
     */
    private void drawHeaderFooterFigures(int pageColCount, int pageRowCount, Rectangle clipRect) {
        removeHeaderFooterFigures();

        Rectangle r = printPreviewFigure.getBounds();
        Point location = printPreviewFigure.getLocation();
        int colSize = (int) Math.floor(r.width / pageColCount);
        int rowSize = (int) Math.floor(r.height / pageRowCount);

        Diagram diagram = (Diagram) ProjectUtil.getDiagramEditPart().getModel();
        String headerText = UICoreConstant.EMPTY_STRING;

        boolean bool = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM);
        if (bool) {
            // 다이어그램명 표시가 체크된경우 머리글에 표시할 다이어그램 이름(Qualified Name) 생성
            headerText = (String) umlDiagramPropertyTitleSwitch.doSwitch(diagram);
        }

        String value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT);

        // Preference에서 자유텍스트가 설정된 경우 머리글에 추가
        if (!value.equals(UICoreConstant.EMPTY_STRING)) {
            if (!headerText.equals(UICoreConstant.EMPTY_STRING)) {
                headerText = headerText + UICoreConstant.EMPTY_STRING + UICoreConstant.PROJECT_CONSTANTS__COLON
                    + UICoreConstant.EMPTY_STRING;
            }
            headerText = headerText + value;
        }

        // 머리글은 용지 가로폭의 절반을 넘을수 없음
        int halfPageSize = (int) Math.floor(colSize / 2);
        for (int row = 1; row < pageRowCount + 1; row++) {
            for (int col = 1; col < pageColCount + 1; col++) {
                int colsOffset = location.x + (colSize * col);
                int rowOffset = location.y + (rowSize * row);
                int topCenter = colsOffset - halfPageSize;
                //                String pageNumber = row + " - " + col; //$NON-NLS-1$

                value = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER);

                if ((value.equals(UMLMessage.LABEL_PRINT_HEADER)) || (value.equals(UMLMessage.LABEL_PRINT_ALL))) {
                    // 머리글 출력
                    HeaderFooterFigure headerFooterFigure = new HeaderFooterFigure();

                    headerFooterFigure.setMaxWidth(colSize);

                    headerFooterFigure.setOptions(colsOffset, rowOffset, topCenter, rowSize);
                    headerFooterFigure.setHeaderText(headerText);

                    headerFooterFigure.setLocation(r.getLocation());
                    headerFooterFigure.setSize(clipRect.width, clipRect.height);
                    headerFooterFigureList.add(headerFooterFigure);

                    // 머리글/꼬리글은 PrintPreviewFigure와는 다르게 출력 가능한 레이어(PRINTABLE_LAYERS)에 표시
                    getLayer(LayerConstants.PRINTABLE_LAYERS).add(headerFooterFigure);
                }

                if ((value.equals(UMLMessage.LABEL_PRINT_FOOTER)) || (value.equals(UMLMessage.LABEL_PRINT_ALL))) {
                    // 꼬리글 출력
                    HeaderFooterFigure headerFooterFigure = new HeaderFooterFigure();
                    String pageNumber = String.valueOf((row - 1) * (pageColCount) + col); //$NON-NLS-1$
                    String footerText = pageNumber;

                    headerFooterFigure.setFooterText(footerText);
                    headerFooterFigure.setOptions(colsOffset, rowOffset, topCenter, rowSize);

                    headerFooterFigure.setLocation(r.getLocation());
                    headerFooterFigure.setSize(clipRect.width, clipRect.height);
                    headerFooterFigureList.add(headerFooterFigure);
                    
                    // 머리글/꼬리글은 PrintPreviewFigure와는 다르게 출력 가능한 레이어(PRINTABLE_LAYERS)에 표시
                    getLayer(LayerConstants.PRINTABLE_LAYERS).add(headerFooterFigure);
                }
            }
        }

    }

    /**
     * Diagram상에서 프린트로 출력되어야할 영역의 크기를 계산한다.
     * 
     * @return Rectangle
     */
    private Rectangle getPrintRegionSize() {
        Rectangle pageSize = null;
        int pageWidth = Integer.MIN_VALUE;
        int pageHeight = Integer.MIN_VALUE;

        IFigure fig = ((AbstractDiagramEditPart) getChildren().get(0)).getContentPane();

        // Diagram의 모든 요소들을 포함하는 최소Bound를 계산한다
        Iterator it = fig.getChildren().iterator();
        while (it.hasNext()) {
            Figure childFigure = (Figure) it.next();
            if ((!(childFigure instanceof Label)) && (!(childFigure instanceof PrintPreviewFigure))
                && (!(childFigure instanceof CompartmentLabelFigure))) {
                Rectangle bound = childFigure.getBounds();
                if (pageWidth < bound.x + bound.width)
                    pageWidth = bound.x + bound.width;
                if (pageHeight < bound.y + bound.height)
                    pageHeight = bound.y + bound.height;
            }
        }
        int x = fig.getBounds().x;
        int y = fig.getBounds().y;
        pageSize = new Rectangle(x, y, pageWidth, pageHeight);
        return pageSize;
    }
    
    /**
     * 머리글에 사용될 TEXT를 생성한다.
     * 
     * @return Rectangle
     */
    private static UMLDiagramSwitch<Object> umlDiagramPropertyTitleSwitch = new UMLDiagramSwitch<Object>() {
        StringBuilder propertyTitleStringBuilder = new StringBuilder();

        /**
         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseDiagram(nexcore.tool.uml.model.umldiagram.Diagram)
         */
        @Override
        public Object caseDiagram(Diagram object) {
            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
            propertyTitleStringBuilder.append(object.getType().getName());
            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
            propertyTitleStringBuilder.append(((NamedElement) object.getParent()).getQualifiedName());
            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON);
            propertyTitleStringBuilder.append(object.getName());

            return propertyTitleStringBuilder.toString();
        }

    };
}
