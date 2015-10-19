/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설 명 : LibraryChooseDialog</li>
 * <li>작성일 : 2011. 3. 3.</li>
 * <li>작성자 : hyun</li>
 * </ul>
 * 도구내에서 공통적으로 사용할 프로파일 선택 다이얼로그
 * 
 */
public class LibraryChooseDialog extends TitleAreaDialog {
    /** 타이틀 */
    private String title;

    /** 테이블 뷰어 */
    private TableViewer tableViewer;

    /** 선택된 Object */
    private Object selectedObject;

    /** 프로젝트 정보를 찾기 위해 필요한 URI 정보 */
    private URI uri;

    
    /** applyingProfileList */
    private List<Package> applyingLibraryList;

    /** model */
    private Model model;

    /**
     * 생성자
     * 
     * @param parentShell
     * @param title
     * @param model
     */
    public LibraryChooseDialog(Shell parentShell, String title, URI uri) {
        super(parentShell);
        this.title = title;
        this.uri = uri;
    }

    /**
     * LibraryChooseDialog
     * @param parentShell
     * @param title
     * @param model
     * @param applyingLibraryList
     */
    public LibraryChooseDialog(Shell parentShell, String title, Model model,
                               List<Package> applyingLibraryList) {
        super(parentShell);
        this.title = title;
        this.model = model;
        this.uri = model.eResource().getURI();
        this.applyingLibraryList = applyingLibraryList;
    }


    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);

        newShell.setText(title);
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Control content = super.createContents(parent);
        setTitle(title);
        setMessage(UMLMessage.MESSAGE_SELECT_LIBRARY_DESCRIPTION); // "적용할 프로파일을 선택해 주세요.");
        return content;
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);

        tableViewer = new TableViewer(container, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new LabelProvider() {
            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                if (element instanceof Package) {
                    return ((Package)element).getName();
                }

                return ""; //$NON-NLS-1$;
            }
        });
        
        //모델에 이미 적용되어 있는 라이브러리는 리스트에 추가하지 않는다. 2011/04/26
        List<Object> appliedLibraryList = getApplicableLibraryList();
        List<Object> deleteList = new ArrayList<Object>();
        for(int i = 0; i < appliedLibraryList.size(); i++){
            if(applyingLibraryList.contains(appliedLibraryList.get(i))){
                deleteList.add(appliedLibraryList.get(i));
            }
        }
        appliedLibraryList.removeAll(deleteList);
        
        tableViewer.setInput(appliedLibraryList.toArray());
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                okPressed();
            }
        });

        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = convertHeightInCharsToPixels(15);
        gd.widthHint = convertWidthInCharsToPixels(55);
        Table table = tableViewer.getTable();
        table.setLayoutData(gd);
        table.setFont(container.getFont());

        return container;
    }

    /**
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        selectedObject = selection.getFirstElement();
        super.okPressed();
    }

    /**
     * 선택된 프로파일을 반환한다.
     * 
     * 
     * @return Object
     */
    public Package getSelectedObject() {
        return (Package) selectedObject;
    }

    
    /**
     * getApplicableLibraryList
     *  
     * @return List<Object>
     */
    private List<Object> getApplicableLibraryList() {
        Resource resource = null;
        URI libraryURI = null;
        Package library = null;
        List<Object> applicableLibraryList = new ArrayList<Object>();
        
        for (String libraryName : UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES) {
            // UML 기본 라이브러리 로딩
            libraryURI = URI.createURI(UMLResource.LIBRARIES_PATHMAP + libraryName + ManagerConstant.DOT
                + UMLResource.LIBRARY_FILE_EXTENSION);
            resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(libraryURI, true);

            if (!resource.isLoaded()) {
                try {
                    resource.load(DomainUtil.getLoadOptions());
                } catch (IOException ioe) {
                    Log.error(ioe);
                }
            }

            library = DomainUtil.getUMLModelRoot(resource);
            applicableLibraryList.add(library);
        }
        
        return applicableLibraryList;
    }

    /**
     * 적용할 프로파일이 존재 하는가?
     * 
     * 
     * @return Boolean
     */
    public Boolean isExistApplicableLibrary() {
        if (getApplicableLibraryList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
