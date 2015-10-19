/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
 * <li>설  명 : RemoveCoveredLifelineDialog</li>
 * <li>작성일 : 2011. 5. 24.</li>
 * <li>작성자 : SKCCADMIN</li>
 * </ul>
 */
public class RemoveCoveredLifelineDialog extends Dialog {

    /**
     * lifelineTableViwer
     */
    private CheckboxTableViewer lifelineTableViwer;
    
    /**
     * lifelineList
     */
    private List<Lifeline> lifelineList = new ArrayList<Lifeline>();
    
    /**
     * removeLifelineList
     */
    private List<Lifeline> removeLifelineList = new ArrayList<Lifeline>();
    
    /**
     * unCheckAllButton
     */
    private Button unCheckAllButton;
    
    /**
     * @param parentShell
     * @param type
     */
    public RemoveCoveredLifelineDialog(Shell parentShell, List<Lifeline> lifelineList) {

        super(parentShell);
        this.lifelineList = lifelineList;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        Composite composite = (Composite) super.createDialogArea(parent);
       
        composite.getShell().setText(UMLMessage.LABEL_REMOVE_COVERED_LIFELINE);

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
        lifelineTableViwer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                if(lifelineList.size() == lifelineTableViwer.getCheckedElements().length) {
                    getButton(IDialogConstants.OK_ID).setEnabled(false);
                } else {
                    getButton(IDialogConstants.OK_ID).setEnabled(true);                    
                }                
            }            
        });
        
        Composite buttonComposit =  new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        buttonComposit.setLayout(gridLayout);
        buttonComposit.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
       
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
        removeLifelineList.clear();
        Object[] checkedElements = lifelineTableViwer.getCheckedElements();
        for(Object object : checkedElements) {
            removeLifelineList.add((Lifeline) object);
        }
        super.okPressed();
    }

    /**
     * getRemoveLifelineList
     *  
     * @return List<Lifeline>
     */
    public List<Lifeline> getRemoveLifelineList() {
        return removeLifelineList;
    }
    
}
