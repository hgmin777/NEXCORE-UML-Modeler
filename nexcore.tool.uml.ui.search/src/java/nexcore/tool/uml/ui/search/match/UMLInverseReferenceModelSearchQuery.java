/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.match;

import nexcore.tool.uml.ui.search.ui.UMLInverseReferenceModelSearchResult;
import nexcore.tool.uml.ui.search.util.UMLInverseReferenceModelSearch;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설 명 : UMLInverseReferenceModelSearchQuery</li>
 * <li>작성일 : 2012. 8. 24.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelSearchQuery implements ISearchQuery {

    /**
     * result
     */
    private UMLInverseReferenceModelSearchResult result = null;

    /**
     * searcher
     */
    private UMLInverseReferenceModelSearch searcher;

    /**
     * pattern
     */
    private UMLInverseReferenceModelSearchPattern pattern;

    /**
     * UMLInverseReferenceModelSearchQuery
     * 
     * @param projects
     * @param searcher
     * @param pattern
     */
    public UMLInverseReferenceModelSearchQuery(UMLInverseReferenceModelSearch searcher,
    UMLInverseReferenceModelSearchPattern pattern) {
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
    public Element getSearchString() {
        return pattern.getSearchElement();
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#getLabel()
     */
    public String getLabel() {
        return String.format("%d referenced '%s'", result.getMatchCount() ,((NamedElement) pattern.getSearchElement()).getQualifiedName());
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#getSearchResult()
     */
    public ISearchResult getSearchResult() {
        if (result == null) {
            result = new UMLInverseReferenceModelSearchResult(this);
            return result;
        }
        return result;
    }

    /**
     * @see org.eclipse.search.ui.ISearchQuery#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus run(IProgressMonitor monitor) throws OperationCanceledException {
        UMLInverseReferenceModelSearchResult searchResult = (UMLInverseReferenceModelSearchResult) getSearchResult();
        searchResult.removeAll();
        searcher.search(searchResult, pattern);

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
