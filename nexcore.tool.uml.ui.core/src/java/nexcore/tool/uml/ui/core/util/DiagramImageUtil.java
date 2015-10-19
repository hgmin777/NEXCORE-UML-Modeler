/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.util;

import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * 문서 산출물의 다이어그램 이미지를 만든다.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : DiagramImageUtil</li>
 * <li>작성일 : 2010. 10. 14.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class DiagramImageUtil implements Runnable {
    /**
     * printUtil
     */
    private PrintUtil printUtil;

    /** Diagram */
    private Diagram diagram;

    /** Image */
    private Image img;

    /**
     * execute
     *  
     * @param util void
     */
    static public void execute(DiagramImageUtil util) {
        Display.getDefault().syncExec(util);
    }

    /**
     * DiagramImageUtil
     * @param diagram
     */
    public DiagramImageUtil(Diagram diagram) {
        this.diagram = diagram;
        if (printUtil == null)
            printUtil = new PrintUtil();
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        Image firstImage = printUtil.getDiagramImage(diagram);
        if (null == firstImage) {
            img = null;
            return;
        }
        // int width = firstImage.getBounds().width;
        // int height = firstImage.getBounds().height;
        // if (width > 630 || height > 430) {
        // double widthRatio = 630 / (double) width;
        // double heightRatio = 430 / (double) height;
        //
        // double zoom = 0;
        // if (widthRatio > heightRatio) {
        // zoom = heightRatio;
        // } else {
        // zoom = widthRatio;
        // }
        //
        // img = new Image(Display.getDefault(),
        // firstImage.getImageData().scaledTo((int) (width * zoom), (int)
        // (height * zoom)));
        //
        // }
        else {
            img = firstImage;
        }
    }

    /**
     * getDiagramImage
     *  
     * @return Image
     */
    public Image getDiagramImage() {
        return img;
    }

    /**
     * getImageWidth
     *  
     * @return int
     */
    public int getImageWidth() {
        return printUtil.getImageWidth();
    }
    
    /**
     * getImageHeight
     *  
     * @return int
     */
    public int getImageHeight() {
        return printUtil.getImageHeight();
    }
}
