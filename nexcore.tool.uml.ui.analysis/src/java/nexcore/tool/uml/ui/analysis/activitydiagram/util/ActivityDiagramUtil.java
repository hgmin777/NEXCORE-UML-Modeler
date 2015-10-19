/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.analysis.AnalysisPlugin;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.util</li>
 * <li>설 명 : ActivityDiagramUtil</li>
 * <li>작성일 : 2010-2-24</li>
 * <li>작성자 : Duyu</li>
 * </ul>
 */
public class ActivityDiagramUtil {
    /** oldNode */
    private static ContainerNode oldNode = UMLDiagramFactory.eINSTANCE.createContainerNode();

    /**
     * list
     */
    private static List<ContainerNode> list = new ArrayList<ContainerNode>();

    /**
     * 
     * 
     * @return ContainerNode
     */
    public static ContainerNode getOldNode() {
        return oldNode;
    }

    /**
     * 
     * 
     * @param oldNode
     *            void
     */
    public static void setOldNode(ContainerNode oldNode) {
        ActivityDiagramUtil.oldNode = oldNode;
    }

    /**
     * getList
     *  
     * @return List<ContainerNode>
     */
    public static List<ContainerNode> getList() {
        return list;
    }

    /**
     * setList
     *  
     * @param node void
     */
    public static void setList(ContainerNode node) {
        if (!ActivityDiagramUtil.list.contains(node))
            ActivityDiagramUtil.list.add(node);
    }
    
    /**
     * @return
     */
    public static IDialogSettings getDialogSetting() {
        
        IDialogSettings dialogSetting = null;
        dialogSetting = AnalysisPlugin.getDefault().getDialogSettings();
        
        return dialogSetting;
    }

}
