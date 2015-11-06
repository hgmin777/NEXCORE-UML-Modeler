/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.print;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.draw2d.geometry.Point;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.print</li>
 * <li>설 명 : PrintPage</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintPage {

    /**
     * name
     */
    private String name;

    /**
     * size
     */
    private Point size;

    /**
     * A3
     */
    public static PrintPage A3 = new PrintPage(UMLMessage.LABEL_PRINT_A3,
        new Point(297, 420));

    /**
     * A4
     */
    public static PrintPage A4 = new PrintPage(UMLMessage.LABEL_PRINT_A4,
        new Point(210, 297));

    /**
     * B4
     */
    public static PrintPage B4 = new PrintPage(UMLMessage.LABEL_PRINT_B4,
        new Point(250, 353));

    /**
     * B5
     */
    public static PrintPage B5 = new PrintPage(UMLMessage.LABEL_PRINT_B5,
        new Point(176, 250));

    /**
     * getPrintPage
     *  
     * @param size
     * @return PrintPage
     */
    public static PrintPage getPrintPage(String size) {
        if (size.equals(UMLMessage.LABEL_PRINT_A3))
            return PrintPage.A3;
        if (size.equals(UMLMessage.LABEL_PRINT_B4))
            return PrintPage.B4;
        if (size.equals(UMLMessage.LABEL_PRINT_B5))
            return PrintPage.B5;
        if (size.equals(UMLMessage.LABEL_PRINT_A4))
            return PrintPage.A4;

        return PrintPage.A4;
    }

    /**
     * PrintPage
     * @param name
     * @param point
     */
    private PrintPage(String name, Point point) {
        this.name = name;
        this.size = point;
    }

    /**
     * getName
     *  
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * getSize
     *  
     * @return Point
     */
    public Point getSize() {
        return size;
    }
    
    /**
     * pages
     */
    public static PrintPage[] pages = { A3, A4, B4, B5 };

    /**
     * mmToPixel
     *  
     * @param mm
     * @return long
     */
    public static long mmToPixel(double mm) {
        return Math.round(mm / 25.4 * 72.0);
    }
    
    /**
     * inchesToPixel
     *  
     * @param inches
     * @return long
     */
    public static long inchesToPixel(double inches) {
        return Math.round(inches * 72.0);
    }
}
