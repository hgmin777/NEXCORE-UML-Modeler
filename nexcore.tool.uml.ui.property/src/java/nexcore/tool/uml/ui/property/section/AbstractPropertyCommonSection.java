/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.property.util.PropertyModelHandler;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설 명 : AbstractPropertyCommonSection</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 변영민</li>
 * <li>
 * 변경: 2009.12.24 한승일 변경내용: refresh로직 변경, control listener해제 이후 refresh,
 * listener재설정</li>
 * </ul>
 */

public abstract class AbstractPropertyCommonSection extends AbstractPropertySectionWithAdapter {

    /** sectionComposite */
    protected Composite sectionComposite;

    /** uniqueName */
    protected String uniqueName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

    /** reentered */
    @SuppressWarnings("unused")
    private boolean reentered = false;

    /** inputEditPart */
    private Object existingInput;

    /**
     * getExistingInput
     *  
     * @return Object
     */
    public Object getExistingInput() {
        return existingInput;
    }

    /**
     * setExistingInput
     *  
     * @param existingInput void
     */
    public void setExistingInput(Object existingInput) {
        this.existingInput = existingInput;
    }

    /** sectionLayout */
    protected GridLayout sectionLayout;

    /** gridData */
    protected GridData gridData;

    /** tableViewer */
    protected TableViewer tableViewer;

    /** table */
    protected Table table;

    /** viewerColumn */
    protected TableViewerColumn viewerColumn;

    /** tableViewerCompsite */
    protected Composite tableViewerComposite;

    /** buttonComposite */
    protected Composite buttonComposite;

    /** input */
    private Object input;

    /**
     * 생성자
     */
    public AbstractPropertyCommonSection() {
        this.reentered = false;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        this.parent = parent;
        super.createControls(parent, tabbedPropertySheetPage);
        this.createMainComposite();
        this.sectionComposite.pack();
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection) 변경날짜: 2009.12.24 변경내용: 같은종류의
     *      Editpart가 선택되면 Refresh안됨
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        Assert.isTrue(selection instanceof IStructuredSelection);

        input = ((IStructuredSelection) selection).getFirstElement();

        if (input == null) {
            this.setSelectedModel(null);
            return;
        }
        // if (input.equals(this.existingInput)) {
        // this.reentered = true;
        // } else {
        this.reentered = false;

        this.existingInput = PropertyModelHandler.getInstance().getProperModel(input);
        this.setSelectedModel((Element) existingInput);
        // }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh() {
        // if (this.reentered) {
        // return;
        // }

        this.unsetListener();

        this.refreshChildren();
        this.setListener();
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * 테이블 뷰어의 각 컬럼의 폭을 조정함. void
     */
    protected void packColumns() {
    	// 2011-10-24 nspark 수정.
    	//컬럼 사이즈는 기본 값으로 설정하여 매번 resize 되지 않도록 수정
//        TableColumn[] tableColumns = tableViewer.getTable().getColumns();
//        for (TableColumn tableColumn : tableColumns) {
//            tableColumn.pack();
//        }
    }
}
