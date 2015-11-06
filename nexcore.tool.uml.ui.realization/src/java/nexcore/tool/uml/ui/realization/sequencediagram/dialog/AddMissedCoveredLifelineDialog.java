/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.dialog</li>
 * <li>설 명 : AddMissedCoveredLifelineDialog</li>
 * <li>작성일 : 2010. 6. 13.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class AddMissedCoveredLifelineDialog extends Dialog {

    /**
     * lifelineTableViwer
     */
    private CheckboxTableViewer lifelineTableViwer;
    
    /**
     * lifelineList
     */
    private List<Lifeline> lifelineList = new ArrayList<Lifeline>();
    
    /**
     * addedLifelineList
     */
    private List<Lifeline> addedLifelineList = new ArrayList<Lifeline>();
    
    /**
     * checkAllButton
     */
    private Button checkAllButton;    

    /**
     * unCheckAllButton
     */
    private Button unCheckAllButton;
    
    /**
     * isSelectAll
     */
    private boolean isSelectAll = true;
    
    /**
     * @param parentShell
     * @param type
     */
    public AddMissedCoveredLifelineDialog(Shell parentShell, List<Lifeline> lifelineList, boolean isSelectAll) {
        super(parentShell);
        this.lifelineList = lifelineList;
        this.isSelectAll = isSelectAll;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        Composite composite = (Composite) super.createDialogArea(parent);
       
        composite.getShell().setText(UMLMessage.LABEL_INSERT_COVERED_LIFELINE);

        GridLayout layout = (GridLayout) composite.getLayout();
        layout.numColumns = 1;
        
        Table table = new Table(composite, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL | SWT.V_SCROLL | SWT.HIDE_SELECTION);
        TableColumn column = new TableColumn(table, SWT.NONE);                                 
        column.setMoveable(false);        
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(100));
        table.setLayout(tableLayout);
        GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
        gd.widthHint = 300;
        gd.heightHint = 200;
        table.setLayoutData(gd);
        
        lifelineTableViwer = new CheckboxTableViewer(table);     
        lifelineTableViwer.setContentProvider(new CoveredLifelineContentProvider());
        lifelineTableViwer.setLabelProvider(new CoveredLifelineLabelProvider());
        
        lifelineTableViwer.setInput(lifelineList);
        lifelineTableViwer.setAllChecked(isSelectAll);
            
        Composite buttonComposit =  new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        buttonComposit.setLayout(gridLayout);
        buttonComposit.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        
        checkAllButton = new Button(buttonComposit, SWT.NONE);
        checkAllButton.setText(UMLMessage.LABEL_SELECT_ALL);
        checkAllButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                lifelineTableViwer.setAllChecked(true);
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        unCheckAllButton = new Button(buttonComposit, SWT.NONE);
        unCheckAllButton.setText(UMLMessage.LABEL_UNSELECT_ALL);
        unCheckAllButton.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                lifelineTableViwer.setAllChecked(false);
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        return composite;

    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        addedLifelineList.clear();
        Object[] checkedElements = lifelineTableViwer.getCheckedElements();
        for(Object object : checkedElements) {
            addedLifelineList.add((Lifeline) object);
        }
        super.okPressed();
    }

    /**
     * getAddedLifelineList
     *  
     * @return List<Lifeline>
     */
    public List<Lifeline> getAddedLifelineList() {
        return addedLifelineList;
    }
    
}
