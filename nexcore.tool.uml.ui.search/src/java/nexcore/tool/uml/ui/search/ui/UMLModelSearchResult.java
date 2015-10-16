/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.ui;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.ui</li>
 * <li>설 명 : UMLModelSearchResult</li>
 * <li>작성일 : 2010. 1. 12.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */

public class UMLModelSearchResult extends AbstractTextSearchResult implements ISearchResult, IEditorMatchAdapter {

    /**
     * query
     */
    private ISearchQuery query = null;

    /**
     * UMLModelSearchResult
     * @param searchQuery
     */
    public UMLModelSearchResult(ISearchQuery searchQuery) {
        this.query = searchQuery;
    }

    /**
     * UMLModelSearchResult
     */
    public UMLModelSearchResult() {
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchResult#addListener(org.eclipse.search.ui.ISearchResultListener)
     */
    @Override
    public void addListener(ISearchResultListener listener) {
        super.addListener(listener);
    }

    /**
     * @see org.eclipse.search.ui.ISearchResult#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /**
     * @see org.eclipse.search.ui.ISearchResult#getLabel()
     */
    public String getLabel() {
        String searchResult = null;
        searchResult = UMLMessage.LABEL_MODELSEARCH_SEARCHRESULT + UICoreConstant.PROJECT_CONSTANTS__COLON
            + query.getLabel();
        return searchResult;
    }

    /**
     * @see org.eclipse.search.ui.ISearchResult#getQuery()
     */
    public ISearchQuery getQuery() {
        return query;
    }

    /**
     * @see org.eclipse.search.ui.ISearchResult#getTooltip()
     */
    public String getTooltip() {
        String searchResult = null;
        searchResult = "SEARCH_RESULT";
        return searchResult;
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchResult#removeListener(org.eclipse.search.ui.ISearchResultListener)
     */
    @Override
    public void removeListener(ISearchResultListener arg0) {
        super.removeListener(arg0);

    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchResult#getEditorMatchAdapter()
     */
    @Override
    public IEditorMatchAdapter getEditorMatchAdapter() {
        return this;
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchResult#getFileMatchAdapter()
     */
    @Override
    public IFileMatchAdapter getFileMatchAdapter() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.search.ui.text.IEditorMatchAdapter#computeContainedMatches(org.eclipse.search.ui.text.AbstractTextSearchResult,
     *      org.eclipse.ui.IEditorPart)
     */
    public Match[] computeContainedMatches(AbstractTextSearchResult result, IEditorPart editor) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.search.ui.text.IEditorMatchAdapter#isShownInEditor(org.eclipse.search.ui.text.Match,
     *      org.eclipse.ui.IEditorPart)
     */
    public boolean isShownInEditor(Match match, IEditorPart editor) {
        // TODO Auto-generated method stub
        return false;
    }

}
