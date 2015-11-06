/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.print.PrintGraphicalViewerOperation;
import nexcore.tool.uml.ui.core.print.PrintPage;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : PrintAction</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintAction extends org.eclipse.gef.ui.actions.PrintAction {

    /**
     * PrintAction
     * @param part
     */
    public PrintAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * @see org.eclipse.gef.ui.actions.PrintAction#run()
     */
    public void run() {
        GraphicalViewer viewer;
        viewer = (GraphicalViewer) getWorkbenchPart().getAdapter(GraphicalViewer.class);

        PrintDialog dialog = new PrintDialog(viewer.getControl().getShell(), SWT.NULL);       
        PrinterData printData = Printer.getDefaultPrinterData();
        
        String orientation = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION);
        if (orientation.equals(UMLMessage.LABEL_PRINT_LANDSCAPE)) {
            printData.orientation = PrinterData.LANDSCAPE;
        }
        else {            
            printData.orientation = PrinterData.PORTRAIT;            
        }
        
        dialog.setPrinterData(printData);
        PrinterData data = dialog.open();
        Printer printer = new Printer(data);

        if (data != null) {
            PrintGraphicalViewerOperation op = new PrintGraphicalViewerOperation(printer, viewer);
            Insets inset = new Insets();

            // 프린터에 여백 설정
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
            op.run(getWorkbenchPart().getTitle());
        }
    }

}
