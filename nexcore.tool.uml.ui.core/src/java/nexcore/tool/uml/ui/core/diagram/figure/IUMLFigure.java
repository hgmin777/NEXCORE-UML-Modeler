/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : IUMLFigure</li>
 * <li>작성일 : 2012. 8. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public interface IUMLFigure {

    public void refresh();

    public boolean isDangling();

    public void setDangling(boolean isDangling);
    
}
