/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

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
 * <li>설  명 : AddCoveredLifelineByMessageDialog</li>
 * <li>작성일 : 2011. 5. 25.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class AddCoveredLifelineByMessageDialog extends Dialog {

    /**
     * lifelineTableViwer
     */
    private CheckboxTableViewer lifelineTableViwer;
    
    /**
     * lifelineMap
     */
    private HashMap<Lifeline, AbstractConnection> lifelineMap;
    
    /**
     * addedEntryList
     */
    private List<Entry<Lifeline, AbstractConnection>> addedEntryList = new ArrayList<Entry<Lifeline, AbstractConnection>>();
        
    /**
     * checkAllButton
     */
    private Button checkAllButton;    

    /**
     * unCheckAllButton
     */
    private Button unCheckAllButton;
    
    /**
     * diagram
     */
    private Diagram diagram;
        
    /**
     * @param parentShell
     * @param type
     */
    public AddCoveredLifelineByMessageDialog(Shell parentShell, HashMap<Lifeline, AbstractConnection> lifelineMap, Diagram diagram) {
        super(parentShell);
        this.lifelineMap = lifelineMap;
        this.diagram = diagram;
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
        lifelineTableViwer.setContentProvider(new CoveredLifelineByMessageContentProvider());        
        List<AbstractConnection> connectionList = SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());        
        lifelineTableViwer.setLabelProvider(new CoveredLifelineByMessageLabelProvider(connectionList));
        
        lifelineTableViwer.setInput(lifelineMap);
        lifelineTableViwer.setAllChecked(true);
        
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
        addedEntryList.clear();
        Object[] checkedElements = lifelineTableViwer.getCheckedElements();
        for(Object object : checkedElements) {
            addedEntryList.add((Entry) object);
        }
        super.okPressed();
    }

    /**
     * getAddedEntryList
     *  
     * @return List<Entry<Lifeline,AbstractConnection>>
     */
    public List<Entry<Lifeline, AbstractConnection>> getAddedEntryList() {
        return addedEntryList;
    }
        
}
