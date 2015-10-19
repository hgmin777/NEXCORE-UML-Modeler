/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.util.DiagramImageUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : SaveToImageAction</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SaveToImageAction extends SelectionAction {

    /** SAVE_TO_IMAGE_ACTIONID */
    public static final String SAVE_TO_IMAGE_ACTIONID = "SaveToImageAction";

    /**
     * SaveToImageAction
     * @param part
     */
    public SaveToImageAction(IWorkbenchPart part) {
        super(part);
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_SAVE_TO_IMAGE_ACTION));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(SAVE_TO_IMAGE_ACTIONID);
        setText(UMLMessage.LABEL_SAVE_TO_IMAGE);
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {

        if (getSelectedObjects().size() < 1)
            return;

        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;

        ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) ((EditPart) obj).getRoot();
        ;
        ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) root.getViewer();

        FileDialog dlg = new FileDialog(viewer.getControl().getShell(), SWT.SAVE);

        dlg.setFilterNames(new String[] { "JPEG Files (*.jpg)", "PNG Files (*.png)", "Bitmap Files (*.bmp)" });
        dlg.setFilterExtensions(new String[] { "*.jpg", "*.png", "*.bmp" });

        String fileName = dlg.open();

        if (fileName == null)
            return;

        LayerManager lm = (LayerManager) viewer.getEditPartRegistry().get(LayerManager.ID);
        IFigure figure = lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
//        IFigure backgroundfigure = lm.getLayer(LayerConstants.FEEDBACK_LAYER);

//        Display display = getWorkbenchPart().getSite().getWorkbenchWindow().getWorkbench().getDisplay();

//        Image img = new Image(display, figure.getSize().width, figure.getSize().height);
//        GC gc = new GC(img);
//        SWTGraphics graphics = new SWTGraphics(gc);
//
//        figure.setLocation(new Point(0, 0));
//        backgroundfigure.setLocation(new Point(0, 0));
//        try {
//            root.getZoomManager().setZoom(0.001);
//            figure.paint(graphics);
//            backgroundfigure.paint(graphics);
//        } finally {
//            root.getZoomManager().setZoom(1);
//            figure.paint(graphics);
//            gc.dispose();
//            graphics.dispose();
//        }

        Diagram diagram = (Diagram) ((EditPart) obj).getModel();
        DiagramImageUtil diagramUtil = new DiagramImageUtil(diagram);
        Display.getDefault().syncExec(diagramUtil);
        Image img = diagramUtil.getDiagramImage();
        
        ImageLoader loader = new ImageLoader();
        if (img == null) {
            img = new Image(null, figure.getSize().width, figure.getSize().height);
        }
        loader.data = new ImageData[] { img.getImageData() };
        
        if (fileName.toLowerCase().endsWith("jpg")) {
            loader.save(fileName, SWT.IMAGE_JPEG);
        } else if (fileName.toLowerCase().endsWith("png")) {
            loader.save(fileName, SWT.IMAGE_PNG);
        } else {
            loader.save(fileName, SWT.IMAGE_BMP);
        }
    }

}
