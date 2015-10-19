/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.ui;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.search.match.UMLModelElement;
import nexcore.tool.uml.ui.search.provider.UMLModelLabelProvider;
import nexcore.tool.uml.ui.search.provider.UMLModelTableContentProvider;
import nexcore.tool.uml.ui.search.provider.UMLModelTreeContentProvider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.ui</li>
 * <li>설 명 : UMLModelSearchResultPage</li>
 * <li>작성일 : 2010. 1. 12.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelSearchResultPage extends AbstractTextSearchViewPage implements ISearchResultPage {
    /**
     * tableContentProvider
     */
    @SuppressWarnings("unused")
    private UMLModelTableContentProvider tableContentProvider = null;

    /**
     * treeContentProvider
     */
    private UMLModelTreeContentProvider treeContentProvider;

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
        UMLModelLabelProvider labelProvider = new UMLModelLabelProvider();
        viewer.setLabelProvider(labelProvider);
        viewer.setContentProvider(new UMLModelTableContentProvider());
        this.tableContentProvider = (UMLModelTableContentProvider) viewer.getContentProvider();

        viewer.addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent event) {

                // IStructuredSelection selection = (IStructuredSelection)
                // event.getSelection();
                // Object selectedElement = selection.getFirstElement();
                //                
                // IViewPart part =
                // PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.ui.navigator.ProjectExplorer");
                // DMToolsPlugin.getDefault().openEditor(selectedElement);
            }
        });

    }

    /**
     * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#configureTreeViewer(org.eclipse.jface.viewers.TreeViewer)
     */
    @Override
    protected void configureTreeViewer(TreeViewer viewer) {
        viewer.setUseHashlookup(true);
        UMLModelLabelProvider labelProvider = new UMLModelLabelProvider();
        viewer.setLabelProvider(labelProvider);
        viewer.setContentProvider(new UMLModelTreeContentProvider(viewer));
        this.treeContentProvider = (UMLModelTreeContentProvider) viewer.getContentProvider();
        viewer.addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent event) {

                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                Object selectedElement = selection.getFirstElement();

                UMLModelElement element = (UMLModelElement) selectedElement;
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
     * refresh
     *   void
     */
    public void refresh() {
        if (treeContentProvider != null) {
            getViewer().refresh(true);
        }
    }

}
