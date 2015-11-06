/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.report.job;

import java.util.List;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Comment;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.job</li>
 * <li>설 명 : CommonReportJob</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 송정훈</li>
 * </ul>
 */
public abstract class CommonReportJob extends Job {

    /**
     * @param name
     */
    public CommonReportJob(String name) {
        super(name);
    }

    /**
     * Async show result
     * 
     * @param action
     */
    protected void showResults(Runnable action) {
        Display.getDefault().asyncExec(action);
    }

    /**
     * Sync show result
     * 
     * @param action
     */
    protected void showSyncResults(Runnable action) {
        Display.getDefault().syncExec(action);
    }

    /**
     * DataModel에 값을 셋팅합니다.
     * 
     * @param dataModel
     * @param key
     * @param value
     */
    protected void setDataModel(DataModel dataModel, String key, Object value) {
        if (dataModel == null) {
            dataModel = new DataModel();
        }

        if (key == null) {
            key = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }

        if (value == null) {
            value = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }

        dataModel.put(key, value);
    }

    /**
     * Comment 를 String 형으로 반환합니다.
     * 
     * @param commentList
     * @return String
     */
    protected String getCommentToString(List<Comment> commentList) {
        StringBuffer sb = new StringBuffer();
        Comment comment;
//        for (int i = 0; i < commentList.size(); i++) {
//            comment = commentList.get(i);
//            sb.append(comment.getBody());
//            if (i < commentList.size() - 1) {
//                sb.append("\n");
//            }
//        }
        
        if (commentList.size() > 0) {
            comment = commentList.get(0);
            sb.append(comment.getBody());
            sb.append("\n");
        }
        
        return sb.toString();
    }

}
