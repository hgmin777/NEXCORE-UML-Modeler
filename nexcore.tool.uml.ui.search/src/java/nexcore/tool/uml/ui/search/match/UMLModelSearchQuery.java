/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.match;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.search.ui.UMLModelSearchResult;
import nexcore.tool.uml.ui.search.util.UMLModelSearch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설 명 : UMLModelSearchQuery</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelSearchQuery implements ISearchQuery {

    /**
     * result
     */
    private UMLModelSearchResult result = null;

    /**
     * projectArray
     */
    private IProject projectArray[] = null;

    /**
     * searcher
     */
    private UMLModelSearch searcher;

    /**
     * pattern
     */
    private UMLModelSearchPattern pattern;

    /**
     * UMLModelSearchQuery
     * @param projects
     * @param searcher
     * @param pattern
     */
    public UMLModelSearchQuery(IProject projects[], UMLModelSearch searcher, UMLModelSearchPattern pattern) {
        this.projectArray = projects;
        this.searcher = searcher;
        this.pattern = pattern;
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#canRerun()
     */
    public boolean canRerun() {
        return true;
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#canRunInBackground()
     */
    public boolean canRunInBackground() {
        return true;
    }

    /**
     * 
     * 
     * 
     * @return String
     */
    public String getSearchString() {

        return pattern.getSearchText();
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#getLabel()
     */
    public String getLabel() {
        return result.getMatchCount() + UMLMessage.LABEL_NAMES_MATCHING + "'" + pattern.getSearchText() + "'";
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#getSearchResult()
     */
    public ISearchResult getSearchResult() {
        if (result == null) {
            result = new UMLModelSearchResult(this);
            return result;
        }
        return result;
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus run(IProgressMonitor monitor) throws OperationCanceledException {
        UMLModelSearchResult searchResult = (UMLModelSearchResult) getSearchResult();
        searchResult.removeAll();
        IProject project = null;
        for (int i = 0; i < projectArray.length; i++) {
            project = projectArray[i];
            searcher.search(searchResult, pattern, project);
        }
        return Status.OK_STATUS;
    }

    /**
     * 
     * 
     * @return boolean
     */
    public boolean isRegexSearch() {
        return false;
    }
}
