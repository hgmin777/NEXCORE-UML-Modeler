/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.ui.dialog;

import nexcore.alm.common.ui.Activator;
import nexcore.alm.common.ui.constant.ColorConstant;
import nexcore.alm.common.ui.constant.ImageConstant;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.dialog</li>
 * <li>설  명 : ContentsManagementDialog</li>
 * <li>작성일 : 2011. 11. 9.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public abstract class ContentsManagementDialog extends SelectionDialog {

    /** 다이얼로그 기본 가로 크기 */
    private static final int DIALOG_DEFAULT_WIDTH = 950;
    /** 다이얼로그 기본 세로 크기 */
    private static final int DIALOG_DEFAULT_HEIGHT = 700;
    /** 트리 기본 가로 크기 */
    protected static final int TABLE_WIDTH = 300;

    /** 왼쪽 트리 섹션 */
    protected Section sourceSection;
    /** 오른쪽 트리 섹션 */
    protected Section targetSection;
    
    /** 왼쪽 트리에 보여질 로컬 콘텐츠 트리*/
    protected ContainerCheckedTreeViewer sourceCheckTreeViewer;
    /** 오른쪽 트리에 보여질 레파지토리 콘텐츠 트리 */
    protected ContainerCheckedTreeViewer targetCheckTreeViewer;
    
    /** 로컬 콘텐츠 트리에 붙을 메뉴 바 */
    protected ToolBar sourceItemToolBar;
    /** 레파지토리 콘텐츠 트리에 붙을 메뉴 바 */
    protected ToolBar targetItemToolBar;

    /** 로컬 콘텐츠 트리에 붙을 검색 필터 */
    protected Text filterText;

    /** 레파지토리 콘텐츠 트리에 붙을 검색 필터  */
    protected Text rightFilterText;

    /** '>' 버튼 */
    protected Button addToRepositoryButton;
    /** '<' 버튼  */
//    protected Button addToLocalButton;
    
    
    /** toolkit */
    protected FormToolkit toolkit;
    
    /** treeFilter */
    protected ToolItem treeFilter;

    
    /**
     * 생성자
     * @param parentShell
     */
    protected ContentsManagementDialog(Shell parentShell) {
        super(parentShell);
        setTitle(Messages.ContentsManagementDialog_MANAGEMENT_REPOSITORY_CONTENTS);
    }
    
    
    /**
     * 다이얼로그 컴파짓 생성
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite container = (Composite) super.createDialogArea(parent);
        container.setBackground(ColorConstant.WHITE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout gl = new GridLayout();
        gl.verticalSpacing = 5;
        gl.marginWidth = 5;
        gl.marginHeight = 5;
        gl.horizontalSpacing = 0;
        container.setLayout(gl);
        
        Composite treeComposite = new Composite(container, SWT.NONE);
        treeComposite.setBackground(ColorConstant.WHITE);
        GridData treeGD = new GridData(GridData.FILL_BOTH);
        treeComposite.setLayoutData(treeGD);
        GridLayout treeGL = new GridLayout(3, false);
        treeComposite.setLayout(treeGL);
       
        // 왼쪽 로컬 섹션
        sourceSection = getFormToolkit().createSection(treeComposite, ExpandableComposite.TITLE_BAR);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.verticalSpan = 2;
        data.widthHint = TABLE_WIDTH;
        sourceSection.setLayoutData(data);
        GridLayout sectionLayout = new GridLayout(1, false);
        sourceSection.setLayout(sectionLayout);
        sourceSection.setText(Messages.ContentsManagementDialog_LOCAL_CONTENTS);//UMLMessage.LABEL_LOCAL_CONTENS); 
        
        // 검색 필터 컴파짓
        Composite filterComposite = new Composite(sourceSection, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        data = new GridData(GridData.FILL_BOTH);
        filterComposite.setLayout(layout);
        filterComposite.setLayoutData(data);
        filterComposite.setBackground(ColorConstant.WHITE);
        // 검색 필터 텍스트
        filterText = new Text(filterComposite, SWT.BORDER);
        filterText.setForeground(filterText.getDisplay().getSystemColor(SWT.COLOR_GRAY));
        filterText.setLayoutData( new GridData(GridData.FILL_HORIZONTAL) );
        filterText.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                filterText.setForeground(filterText.getDisplay().getSystemColor(SWT.COLOR_GRAY));
            }

            @Override
            public void focusGained(FocusEvent e) {
                filterText.setForeground(filterText.getDisplay().getSystemColor(SWT.COLOR_BLACK));
            }
        });
        
        // 왼쪽 로컬 트리 뷰어
        sourceCheckTreeViewer = new ContainerCheckedTreeViewer(filterComposite, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.HIDE_SELECTION);
        data = new GridData(GridData.FILL_BOTH);
        data.widthHint = TABLE_WIDTH;
        data.verticalSpan = 2;
        sourceCheckTreeViewer.getTree().setLayoutData(data);
        
        Composite buttonComposite = new Composite(treeComposite, SWT.NONE);
        buttonComposite.setBackground(ColorConstant.WHITE);
        buttonComposite.setLayout(new GridLayout());
        buttonComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true));
        
        // '>' 버튼
        addToRepositoryButton = new Button(buttonComposite, SWT.NONE);
        addToRepositoryButton.setImage(Activator.getDefault().getImage(ImageConstant.NEW_ARROW_ADD));
        new Label(buttonComposite, SWT.NONE);
        // '<' 버튼
//        addToLocalButton = new Button(buttonComposite, SWT.NONE);
//        addToLocalButton.setImage(Activator.getDefault().getImage(ImageConstant.ARROW_LEFT));

        
        // 오른쪽 레파지토리 섹션
        targetSection = getFormToolkit().createSection(treeComposite, ExpandableComposite.TITLE_BAR);
        data = new GridData(GridData.FILL_BOTH);
        data.verticalSpan = 2;
        targetSection.setLayoutData(data);
        sectionLayout = new GridLayout(1, true);
        targetSection.setLayout(sectionLayout);
        targetSection.setText(Messages.ContentsManagementDialog_REPOSITORY_CONTENTS);//UMLMessage.LABEL_NCP_METACONTENTS); 
        
     // 검색 필터 컴파짓
        Composite rightFilterComposite = new Composite(targetSection, SWT.NONE);
        layout = new GridLayout(1, false);
        data = new GridData(GridData.FILL_BOTH);
        rightFilterComposite.setLayout(layout);
        rightFilterComposite.setLayoutData(data);
        rightFilterComposite.setBackground(ColorConstant.WHITE);
        rightFilterText = new Text(rightFilterComposite, SWT.BORDER);
        rightFilterText.setForeground(filterText.getDisplay().getSystemColor(SWT.COLOR_GRAY));
        rightFilterText.setLayoutData( new GridData(GridData.FILL_HORIZONTAL) );
        rightFilterText.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                rightFilterText.setForeground(rightFilterText.getDisplay().getSystemColor(SWT.COLOR_GRAY));
            }

            @Override
            public void focusGained(FocusEvent e) {
                rightFilterText.setForeground(rightFilterText.getDisplay().getSystemColor(SWT.COLOR_BLACK));
            }
        });
        
        // 오른쪽 레파지토리 트리 뷰어
        targetCheckTreeViewer = new ContainerCheckedTreeViewer(rightFilterComposite, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.HIDE_SELECTION);
        data = new GridData(GridData.FILL_BOTH);
        data.widthHint = TABLE_WIDTH;
        data.verticalSpan = 2;
        targetCheckTreeViewer.getTree().setLayoutData(data);
        
        
        // 섹션 값 대입
        sourceSection.setClient(filterComposite);
        targetSection.setClient(rightFilterComposite);
        // 섹션 메뉴 삽입
        addSourceItemToolBar(sourceSection);
        addTargetItemToolBar(targetSection);
        
        addSelectionChangedListener();
        
      //========== 범례 
        Composite colorLabelComposite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 10;
        colorLabelComposite.setLayout(gridLayout);
        colorLabelComposite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 3, 1));

        Label colorLabel = new Label(colorLabelComposite, SWT.BOLD);
        colorLabel.setText("            "); //$NON-NLS-1$
        colorLabel.setBackground(ColorConstant.WHITE);
        Label explainLabel = new Label(colorLabelComposite, SWT.BOLD);
        explainLabel.setText(Messages.ContentsManagementDialog_COMPARE_SAME);
        
        colorLabel = new Label(colorLabelComposite, SWT.BOLD);
        colorLabel.setText("            "); //$NON-NLS-1$
        colorLabel.setBackground(ColorConstant.RED);
        explainLabel = new Label(colorLabelComposite, SWT.BOLD);
        explainLabel.setText(Messages.ContentsManagementDialog_COMPARE_DIFF);
        
        colorLabel = new Label(colorLabelComposite, SWT.BOLD);
        colorLabel.setText("            "); //$NON-NLS-1$
        colorLabel.setBackground(ColorConstant.GREEN);
        explainLabel = new Label(colorLabelComposite, SWT.BOLD);
        explainLabel.setText(Messages.ContentsManagementDialog_COMPARE_ONESIDE);
        
        initialization();
        
        return container;
    }
    
    public void addSelectionChangedListener() {
        sourceCheckTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                sourceCheckTreeViewerSelectionChanged(event);
            }
        });
        
        targetCheckTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                targetCheckTreeViewerSelectionChanged(event);
            }
        });
    }
    
    public void sourceCheckTreeViewerSelectionChanged(SelectionChangedEvent event) {
        // sourceCheckTreeViewer 의 selectionChanged 처리
    }
    
    public void targetCheckTreeViewerSelectionChanged(SelectionChangedEvent event) {
        // targetCheckTreeViewer 의 selectionChanged 처리
    }
    
    /**
     * 생성한 위젯에 액션등을 달아준다.
     * 
     * 1. 트리에 라벨프로바이더, 컨텐츠프로바이더, 소터, 필터 등...
     * 2. 트리에 setInput 값 삽입.
     * 
     * 3. 검색 필터에 액션 구현.
     * 4. '>' , '<' , 'Compare', '삭제' 버튼 액션 구현
     * 
     * 
     *   void
     */
    protected abstract void initialization();
    
    /**
     *   레파지토리 콘텐츠를 지워준다.
     *   void
     */
    protected abstract void deleteRepositoryContents();
//    /**
//     *  로컬 콘텐츠와 레파지토리 콘텐츠를 비교한다.
//     *   void
//     */
//    protected abstract void compare();
    
    /**
     *  
     *   void
     */
    protected abstract void treeFilter();

    
    /**
     * 섹션 메뉴 삽입
     *  
     * @param section void
     */
    public void addSourceItemToolBar(Section section) {
        sourceItemToolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        
        // '+' <- expandAll 메뉴 삽입
//        ToolItem expandAll = new ToolItem(sourceItemToolBar, SWT.PUSH);
//        expandAll.setToolTipText("Expand All");//UMLMessage.LABEL_EXPAND_ALL);//$NON-NLS-1$
//        expandAll.setText("[+]");
//        expandAll.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                sourceCheckTreeViewer.expandAll();
//            }
//        });
        // '-' <- collapseAll 메뉴 삽입
        ToolItem collapseAll = new ToolItem(sourceItemToolBar, SWT.PUSH);
        collapseAll.setToolTipText(Messages.ContentsManagementDialog_CALLAPSE_ALL);//UMLMessage.LABEL_CALLAPSE_ALL);
        collapseAll.setImage(Activator.getDefault().getImage(ImageConstant.NEW_COLLAPSE_ALL));
        collapseAll.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sourceCheckTreeViewer.collapseAll();
            }
        });
        // '전체선택' 메뉴 삽입
//        ToolItem checkItem = new ToolItem(sourceItemToolBar, SWT.PUSH);
//        checkItem.setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);//UMLMessage.LABEL_CHECK_ALL);
//        checkItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
//        checkItem.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                sourceCheckTreeViewer.setAllChecked(true);
//            }
//        });
//        // '선택해제' 메뉴 삽입
//        ToolItem unCheckItem = new ToolItem(sourceItemToolBar, SWT.PUSH);
//        unCheckItem.setToolTipText(Messages.ContentsManagementDialog_UNCHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
//        unCheckItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_UNCHECK_ALL));
//        unCheckItem.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                sourceCheckTreeViewer.setAllChecked(false);
//            }
//        });
        
        ToolItem toggleCheckItem = new ToolItem(sourceItemToolBar, SWT.CHECK);
        toggleCheckItem.setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
        toggleCheckItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
        toggleCheckItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sourceCheckTreeViewer.setAllChecked(((ToolItem) e.widget).getSelection());
                if(((ToolItem) e.widget).getSelection()) {
                    ((ToolItem) e.widget).setToolTipText(Messages.ContentsManagementDialog_UNCHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
                    ((ToolItem) e.widget).setImage(Activator.getDefault().getImage(ImageConstant.NEW_UNCHECK_ALL));
                } else {
                    ((ToolItem) e.widget).setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
                    ((ToolItem) e.widget).setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
                }
            }
        });
        
        // 'Compare' 메뉴 삽입
//        ToolItem compare = new ToolItem(sourceItemToolBar, SWT.CHECK);
//        compare.setToolTipText("Compare");
//        compare.setText("Compare"); 
//        compare.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                compare();
//            }
//        });
        
        // 'TreeFilter' 메뉴 삽입
        treeFilter = new ToolItem(sourceItemToolBar, SWT.CHECK);
        treeFilter.setToolTipText(Messages.ContentsManagementDialog_VIEW_ONLY_DIFFRENT); 
        treeFilter.setImage(Activator.getDefault().getImage(ImageConstant.NEW_VIEW_ONLY_DIFFRENT));
        treeFilter.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                treeFilter();
            }

        });
        
        section.setTextClient(sourceItemToolBar);
    }

    /**
     * 섹션 메뉴 삽입
     *  
     * @param section void
     */
    public void addTargetItemToolBar(Section section) {
        targetItemToolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        // '+' <- expandAll 메뉴 삽입
//        ToolItem expandAll = new ToolItem(targetItemToolBar, SWT.PUSH);
//        expandAll.setToolTipText("Expand All"); 
//        expandAll.setText("[+]");
//        expandAll.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                targetCheckTreeViewer.expandAll();
//            }
//        });
        // '-' <- collapseAll 메뉴 삽입
        ToolItem collapseAll = new ToolItem(targetItemToolBar, SWT.PUSH);
        collapseAll.setToolTipText(Messages.ContentsManagementDialog_CALLAPSE_ALL);  
        collapseAll.setImage(Activator.getDefault().getImage(ImageConstant.NEW_COLLAPSE_ALL));
        collapseAll.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                targetCheckTreeViewer.collapseAll();
            }
        });
        // '전체선택' 메뉴 삽입       
//        ToolItem checkItem = new ToolItem(targetItemToolBar, SWT.PUSH);
//        checkItem.setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);  
//        checkItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
//        checkItem.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                targetCheckTreeViewer.setAllChecked(true);
//            }
//        });
//        // '선택해제' 메뉴 삽입
//        ToolItem unCheckItem = new ToolItem(targetItemToolBar, SWT.PUSH);
//        unCheckItem.setToolTipText(Messages.ContentsManagementDialog_UNCHECK_ALL);   
//        unCheckItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_UNCHECK_ALL));
//        unCheckItem.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                targetCheckTreeViewer.setAllChecked(false);
//            }
//        });
        
        ToolItem toggleCheckItem = new ToolItem(targetItemToolBar, SWT.CHECK);
        toggleCheckItem.setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
        toggleCheckItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
        toggleCheckItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                targetCheckTreeViewer.setAllChecked(((ToolItem) e.widget).getSelection());
                if(((ToolItem) e.widget).getSelection()) {
                    ((ToolItem) e.widget).setToolTipText(Messages.ContentsManagementDialog_UNCHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
                    ((ToolItem) e.widget).setImage(Activator.getDefault().getImage(ImageConstant.NEW_UNCHECK_ALL));
                } else {
                    ((ToolItem) e.widget).setToolTipText(Messages.ContentsManagementDialog_CHECK_ALL);//UMLMessage.LABEL_UNCHECK_ALL); 
                    ((ToolItem) e.widget).setImage(Activator.getDefault().getImage(ImageConstant.NEW_CHECK_ALL));
                }
            }
        });
        
        // '삭제' 메뉴 삽입
        ToolItem deleteItem = new ToolItem(targetItemToolBar, SWT.PUSH);
        deleteItem.setToolTipText(Messages.ContentsManagementDialog_DELETE);//UMLMessage.LABEL_DELETE_METACONTENT);  
        deleteItem.setImage(Activator.getDefault().getImage(ImageConstant.NEW_DELETE));
        deleteItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 콘텐츠 삭제 실행.
                deleteRepositoryContents();
            }

            
        });
        section.setTextClient(targetItemToolBar);
    }
    
    /**
     * 다이얼로그 창을 닫을 'Close' 버튼 생성.
     * 
     * @see org.eclipse.ui.dialogs.SelectionDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL,
            false);
    }
    
    /**
     * 다이얼로그 기본 사이즈와 최소 사이즈 지정
     * 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     */
    @Override
    protected Point getInitialSize() {
        getShell().setMinimumSize(DIALOG_DEFAULT_WIDTH, DIALOG_DEFAULT_HEIGHT);
        
        return new Point(DIALOG_DEFAULT_WIDTH, DIALOG_DEFAULT_HEIGHT);
    }

    /**
     * @see org.eclipse.jface.dialogs.TrayDialog#createHelpControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createHelpControl(Composite parent) {
//        return super.createHelpControl(parent);
        return parent;
    }
    
    /**
     * getFormToolkit
     *  
     * @return FormToolkit
     */
    private FormToolkit getFormToolkit() {
        toolkit = new FormToolkit(getShell().getDisplay());
        return toolkit;
    }
}
