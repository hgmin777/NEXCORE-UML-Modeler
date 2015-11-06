/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.ui;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.explorer.ExplorerElementFilter;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelElement;
import nexcore.tool.uml.ui.search.provider.UMLInverseReferenceModelLabelProvider;
import nexcore.tool.uml.ui.search.provider.UMLInverseReferenceModelTableContentProvider;
import nexcore.tool.uml.ui.search.provider.UMLInverseReferenceModelTreeContentProvider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.ui</li>
 * <li>설 명 : UMLInverseReferenceModelSearchResultPage</li>
 * <li>작성일 : 2012. 8. 24.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelSearchResultPage extends AbstractTextSearchViewPage implements ISearchResultPage {
    /**
     * tableContentProvider
     */
    @SuppressWarnings("unused")
    private UMLInverseReferenceModelTableContentProvider tableContentProvider = null;

    /**
     * treeContentProvider
     */
    private UMLInverseReferenceModelTreeContentProvider treeContentProvider;

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#setInput(org.eclipse.search.ui.ISearchResult,
     *      java.lang.Object)
     */
    @Override
    public void setInput(ISearchResult newSearch, Object viewState) {
        super.setInput(newSearch, viewState);
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#clear()
     */
    @Override
    protected void clear() {
        
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#configureTableViewer(org.eclipse.jface.viewers.TableViewer)
     */
    @Override
    protected void configureTableViewer(TableViewer viewer) {
        viewer.setUseHashlookup(true);
        UMLInverseReferenceModelLabelProvider labelProvider = new UMLInverseReferenceModelLabelProvider();
        viewer.setLabelProvider(labelProvider);
        viewer.setContentProvider(new UMLInverseReferenceModelTableContentProvider());
        
        viewer.setFilters((ViewerFilter[]) new Object[]{new ExplorerElementFilter()});
        
        this.tableContentProvider = (UMLInverseReferenceModelTableContentProvider) viewer.getContentProvider();
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#configureTreeViewer(org.eclipse.jface.viewers.TreeViewer)
     */
    @Override
    protected void configureTreeViewer(TreeViewer viewer) {
        viewer.setUseHashlookup(true);
        UMLInverseReferenceModelLabelProvider labelProvider = new UMLInverseReferenceModelLabelProvider();
        viewer.setLabelProvider(labelProvider);
        viewer.setContentProvider(new UMLInverseReferenceModelTreeContentProvider(viewer));
        this.treeContentProvider = (UMLInverseReferenceModelTreeContentProvider) viewer.getContentProvider();
        viewer.addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent event) {

                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                Object selectedElement = selection.getFirstElement();

                UMLInverseReferenceModelElement element = (UMLInverseReferenceModelElement) selectedElement;
                if (!(UICoreConstant.MODELSEARCH__TYPE_PROJECT.equals(element.getType()))) {
                    ProjectUtil.findElement((EObject) (element.getOrgObject()));
                }

            }
        });
    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#elementsChanged(java.lang.Object[])
     */
    @Override
    protected void elementsChanged(Object[] objects) {
        if (treeContentProvider != null && !getViewer().getControl().isDisposed()) {
            treeContentProvider.elementsChanged(objects);
        }
    }

    /**
     * refresh void
     */
    public void refresh() {
        if (treeContentProvider != null) {
            getViewer().refresh(true);
        }
    }

}
