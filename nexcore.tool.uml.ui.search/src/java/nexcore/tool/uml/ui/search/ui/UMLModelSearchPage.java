/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.ui;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.search.Activator;
import nexcore.tool.uml.ui.search.match.UMLModelElement;
import nexcore.tool.uml.ui.search.match.UMLModelSearchPattern;
import nexcore.tool.uml.ui.search.match.UMLModelSearchQuery;
import nexcore.tool.uml.ui.search.util.UMLModelSearch;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.internal.ui.ISearchHelpContextIds;
import org.eclipse.search.internal.ui.SearchPlugin;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.ui</li>
 * <li>설 명 : UMLModelSearchPage</li>
 * <li>작성일 : 2010. 1. 12.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class UMLModelSearchPage extends DialogPage implements ISearchPage {

    /** 검색어란 */
    @SuppressWarnings("unused")
    private Composite cmpTextSearch = null;

    /** 오브젝트 선택란 */
    @SuppressWarnings("unused")
    private Composite cmpModelScope = null;

    /** 검색어 */
    private Combo cmbText = null;

    /** 대소문자 구별 체크박스 */
    private Button chkCaseSensitive = null;

    /** 오브젝트 체크박스 테이블 뷰어 */
    private CheckboxTableViewer checkboxViewer;

    /** container */
    private ISearchPageContainer container;

    /** previousSearchPatterns */
    @SuppressWarnings("unchecked")
    private List previousSearchPatterns = new ArrayList<UMLModelSearchPattern>(20);

    /** 대소문자 구별 여부 */
    private boolean isCaseSensitive = false;

    /** items */
    private UMLModelElement[] items = new UMLModelElement[] {
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_PACKAGE, UICoreConstant.MODELSEARCH__TYPE_PACKAGE),
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_CLASS, UICoreConstant.MODELSEARCH__TYPE_CLASS),
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_COMPONENT, UICoreConstant.MODELSEARCH__TYPE_COMPONENT),
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_ACTOR, UICoreConstant.MODELSEARCH__TYPE_ACTOR),
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_USECASE, UICoreConstant.MODELSEARCH__TYPE_USECASE),
        new UMLModelElement(UMLMessage.LABEL_MODELSEARCH_INTERFACE, UICoreConstant.MODELSEARCH__TYPE_INTERFACE) };

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.search.ui</li>
     * <li>설 명 : UMLModelSearchPageInput</li>
     * <li>작성일 : 2010. 1. 12.</li>
     * <li>작성자 : 신용일</li>
     * </ul>
     */
    class UMLModelSearchPageInput {
        /** fSearchText */
        @SuppressWarnings("unused")
        private String fSearchText;

        /** fIsCaseSensitive */
        @SuppressWarnings("unused")
        private boolean fIsCaseSensitive;

        public UMLModelSearchPageInput() {
            this.fSearchText = cmbText.getText();
            this.fIsCaseSensitive = isCaseSensitive;
        }
    }

    /**
     * search버튼을 눌렀을때 수행되는 액션
     * 
     * @see org.eclipse.search.ui.ISearchPage#performAction()
     */
    public boolean performAction() {
        // if (checkboxViewer.getCheckedElements() == null) {
        // MessageDialog.
        // }
        // view에 query결과를 보여준다.
        NewSearchUI.runQueryInBackground(newQuery());
        writeConfiguration();
        return true;
    }

    /**
     * setContainer
     * 
     * @see org.eclipse.search.ui.ISearchPage#setContainer(org.eclipse.search.ui.ISearchPageContainer)
     */
    public void setContainer(ISearchPageContainer container) {
        this.container = container;
        container.setSelectedScope(ISearchPageContainer.SELECTED_PROJECTS_SCOPE);
    }

    /**
     * Search 페이지의 컨트롤들을 생성한다.
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);
        readConfiguration();

        Composite result = new Composite(parent, SWT.None);
        result.setFont(parent.getFont());
        GridLayout layout = new GridLayout(2, false);
        result.setLayout(layout);

        createTextSearchComposite(result);
        createModelScopeComposite(result);
        setControl(result);
        Dialog.applyDialogFont(result);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(result, ISearchHelpContextIds.TEXT_SEARCH_PAGE);
    }

    /**
     * Replace버튼 눌렀을시 실행
     * 
     * @return boolean
     */
    public boolean performReplace() {
        return false;
    }

    /**
     * Search Composite을 생성한다.
     */
    private void createTextSearchComposite(Composite cmpMain) {
        Label labelText = new Label(cmpMain, SWT.LEAD);
        labelText.setText(UMLMessage.LABEL_MODELSEARCH_LABELTEXT);
        labelText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        labelText.setFont(cmpMain.getFont());

        cmbText = new Combo(cmpMain, SWT.SINGLE | SWT.BORDER);
        cmbText.setFont(cmpMain.getFont());
        GridData cmbTextGridData = new GridData(GridData.FILL, GridData.FILL, true, false, 1, 1);
        cmbText.setLayoutData(cmbTextGridData);

        chkCaseSensitive = new Button(cmpMain, SWT.CHECK);
        chkCaseSensitive.setText(UMLMessage.LABEL_MODELSEARCH_CASESENSITIVE);
        chkCaseSensitive.setSelection(isCaseSensitive);
        chkCaseSensitive.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        chkCaseSensitive.setFont(cmpMain.getFont());

        CLabel statusLabel = new CLabel(cmpMain, SWT.LEAD);
        statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        statusLabel.setText(UMLMessage.LABEL_MODELSEARCH_STATUSLABEL);
        statusLabel.setAlignment(SWT.LEFT);

        cmbText.setItems(getPreviousSearchPatterns());
        if (getPreviousSearchPatterns().length > 0) {
            cmbText.select(0);
        }
    }

    /**
     * 이전 검색패턴
     * 
     * @return String[]
     */
    private String[] getPreviousSearchPatterns() {
        int size = previousSearchPatterns.size();

        String[] patterns = new String[size];

        for (int i = 0; i < size; i++) {
            patterns[i] = ((UMLModelSearchPattern) previousSearchPatterns.get(i)).getSearchText();
        }

        return patterns;
    }

    /**
     * 모델Scope Composite을 생성
     * 
     * @param cmpMain
     *            void
     */
    private void createModelScopeComposite(Composite cmpMain) {
        Label lblModelScope = new Label(cmpMain, SWT.NONE);
        lblModelScope.setText(UMLMessage.LABEL_MODELSEARCH_SELECTOBJECT);
        lblModelScope.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        lblModelScope.setFont(cmpMain.getFont());

        checkboxViewer = CheckboxTableViewer.newCheckList(cmpMain, SWT.SINGLE | SWT.TOP | SWT.BORDER);
        checkboxViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        checkboxViewer.getTable().setFont(cmpMain.getFont());
        checkboxViewer.setLabelProvider(new LabelProvider() {
            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
             */
            @Override
            public Image getImage(Object element) {
                if (element instanceof UMLModelElement) {
                    UMLModelElement umlElement = (UMLModelElement) element;

                    if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_PACKAGE) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_PACKAGE);
                    } else if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_CLASS) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_CLASS);
                    } else if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_COMPONENT) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_COMPONENT);
                    } else if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_ACTOR) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_ACTOR);
                    } else if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_USECASE) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_USECASE);
                    } else if (umlElement.getType() == UICoreConstant.MODELSEARCH__TYPE_INTERFACE) {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_INTERFACE);
                    } else {
                        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_DEFAULT);
                    }
                }

                return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_DEFAULT);
            }

            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                return element.toString();
            }
        });

        checkboxViewer.setContentProvider(new IStructuredContentProvider() {
            @SuppressWarnings("unchecked")
            private final Comparator comparer = new Comparator() {
                private Collator collator = Collator.getInstance();

                public int compare(Object arg0, Object arg1) {
                    String s1 = ((UMLModelElement) arg0).getDisplayName();
                    String s2 = ((UMLModelElement) arg1).getDisplayName();
                    return collator.compare(s1, s2);
                }
            };

            @SuppressWarnings("unchecked")
            public Object[] getElements(Object inputElement) {
                Object[] elements = (Object[]) inputElement;
                Object[] results = new Object[elements.length];
                System.arraycopy(elements, 0, results, 0, elements.length);
                Collections.sort(Arrays.asList(results), comparer);
                return results;
            }

            public void dispose() {
                // TODO Auto-generated method stub

            }

            public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
                // TODO Auto-generated method stub

            }
        });

        checkboxViewer.setInput(items);

        Composite cmpSelectButtons = new Composite(cmpMain, SWT.NONE);
        cmpSelectButtons.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));
        cmpSelectButtons.setLayout(new GridLayout(1, true));

        Button btnSelectAll = new Button(cmpSelectButtons, SWT.None);
        btnSelectAll.setText("Select All"); //$NON-NLS-1$
        btnSelectAll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

        Button btnDeselectAll = new Button(cmpSelectButtons, SWT.NONE);
        btnDeselectAll.setText("Deselect All"); //$NON-NLS-1$
        btnDeselectAll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        btnSelectAll.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent arg0) {
                checkboxViewer.setAllChecked(true);

            }
        });

        btnDeselectAll.addSelectionListener(new SelectionListener() {
            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent arg0) {

            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent arg0) {
                checkboxViewer.setAllChecked(false);

            }
        });

        checkboxViewer.setAllChecked(true);
    }

    /**
     * 설정 정보를 로드
     * 
     * void
     */
    @SuppressWarnings("unchecked")
    private void readConfiguration() {
        IDialogSettings s = getDialogSettings();
        // STORE_CASE_SENSITIVE = CASE_SENSITIVE
        isCaseSensitive = s.getBoolean(UICoreConstant.MODELSEARCH__STORE_CASE_SENSITIVE);
        // isRegExSearch= s.getBoolean(STORE_IS_REG_EX_SEARCH);

        try {
            int historySize = s.getInt(UICoreConstant.MODELSEARCH__STORE_HISTORY_SIZE);

            for (int i = 0; i < historySize; i++) {
                IDialogSettings histSettings = s.getSection(UICoreConstant.MODELSEARCH__STORE_HISTORY + i);

                if (histSettings != null) {
                    UMLModelSearchPattern data = UMLModelSearchPattern.create(histSettings);

                    if (data != null) {
                        previousSearchPatterns.add(data);
                    }
                }
            }
        } catch (NumberFormatException e) {}
    }

    /**
     * 설정 정보를 저장
     * 
     * void
     */
    private void writeConfiguration() {
        IDialogSettings s = getDialogSettings();
        s.put(UICoreConstant.MODELSEARCH__STORE_CASE_SENSITIVE, isCaseSensitive);
        // s.put(STORE_IS_REG_EX_SEARCH, isRegExSearch);

        int historySize = Math.min(previousSearchPatterns.size(), UICoreConstant.MODELSEARCH__HISTORY_SIZE);
        s.put(UICoreConstant.MODELSEARCH__STORE_HISTORY_SIZE, historySize);

        for (int i = 0; i < historySize; i++) {
            IDialogSettings histSettings = s.addNewSection(UICoreConstant.MODELSEARCH__STORE_HISTORY + i);

            UMLModelSearchPattern data = ((UMLModelSearchPattern) previousSearchPatterns.get(i));

            data.store(histSettings);
        }
    }

    /**
     * getDialogSettings
     * 
     * @return IDialogSettings
     */
    private IDialogSettings getDialogSettings() {
        return SearchPlugin.getDefault().getDialogSettingsSection(UICoreConstant.MODELSEARCH__PAGE_NAME);
    }

    /**
     * newQuery
     * 
     * @return ISearchQuery
     */
    private ISearchQuery newQuery() {
        String projectNames[] = container.getSelectedProjectNames();

        UMLModelSearch searcher = new UMLModelSearch();
        IProject projectArray[] = searcher.findUMLDesignProjects(projectNames);

        UMLModelSearchPattern pattern = getPatternData();
        pattern.setObjectScope(checkboxViewer.getCheckedElements());

        ISearchQuery query = new UMLModelSearchQuery(projectArray, searcher, pattern);

        return query;
    }

    /**
     * 
     * 
     * @return UMLModelSearchPattern
     */
    @SuppressWarnings("unchecked")
    private UMLModelSearchPattern getPatternData() {
        UMLModelSearchPattern match = null;

        if (cmbText.getText().indexOf(UICoreConstant.PROJECT_CONSTANTS__STAR) < 0 && cmbText.getText().length() != 0) {
            match = findInPrevious(cmbText.getText() + UICoreConstant.PROJECT_CONSTANTS__STAR);
        } else {
            match = findInPrevious(cmbText.getText());
        }

        if (match != null) {
            previousSearchPatterns.remove(match);
        }

        if (cmbText.getText().indexOf(UICoreConstant.PROJECT_CONSTANTS__STAR) < 0 && cmbText.getText().length() != 0) {
            match = new UMLModelSearchPattern(cmbText.getText() + UICoreConstant.PROJECT_CONSTANTS__STAR,
                chkCaseSensitive.getSelection());
        } else {
            match = new UMLModelSearchPattern(cmbText.getText(), chkCaseSensitive.getSelection());
        }

        previousSearchPatterns.add(0, match);

        return match;
    }

    /**
     * 앞의 검색에서 찾기
     * 
     * @param pattern
     * @return UMLModelSearchPattern
     */
    @SuppressWarnings("unchecked")
    private UMLModelSearchPattern findInPrevious(String pattern) {
        for (Iterator iter = previousSearchPatterns.iterator(); iter.hasNext();) {
            UMLModelSearchPattern element = (UMLModelSearchPattern) iter.next();

            if (pattern.equals(element.getSearchText())) {
                return element;
            }
        }

        return null;
    }
}
