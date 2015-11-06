/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설  명 : TypeSelectDialog</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : 송주경</li>
 * </ul>
 */
public class TypeSelectDialog extends ElementListSelectionDialog/* ElementTreeSelectionDialog */{

    /** Adapter factory label provider. */
    private static AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** type */
    private TypeSelectDialogType type;

    /**
     * workspaceButton
     */
    private Button workspaceButton;

    /**
     * projectButton
     */
    private Button projectButton;

    /**
     * modelButton
     */
    private Button modelButton;

    /**
     * element
     */
    private Element element;

    /**
     * 생성자
     * 
     * @param parent
     * @param renderer
     */
    public TypeSelectDialog(Shell parent, ILabelProvider renderer) {
        super(parent, renderer);
    }
    
    /**
     * @see org.eclipse.ui.dialogs.AbstractElementListSelectionDialog#create()
     */
    @Override
    public void create() {
        initData();
        super.create();
        
    }
    
    /**
     * 생성자
     * 
     * @param parent
     * @param type
     */
    public TypeSelectDialog(Shell parent, TypeSelectDialogType type, Element element) {
        super(parent, new LabelProvider() {
            public Image getImage(Object element) {
                if (element instanceof NamedElement) {
                    return adapterFactoryLabelProvider.getImage(element);
                }
                return null;
            }

            public String getText(Object element) {
                if (element instanceof NamedElement) {
                    NamedElement umlElement = (NamedElement) element;
                    StringBuffer stringBuffer = new StringBuffer();
//                    stringBuffer.append(ProjectUtil.getStereotypeLabel(umlElement));
                    stringBuffer.append(umlElement.getName() == null || umlElement.getName().length() == 0 ? "Unknown" : umlElement.getName());
                    stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT);
                    stringBuffer.append(umlElement.getQualifiedName() == null ? " - " : umlElement.getQualifiedName());
                    stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT);
                    return stringBuffer.toString();
                }
                return null;
            }

            /**
             * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
             *      java.lang.String)
             */
            public boolean isLabelProperty(Object element, String property) {
                if (element instanceof Class) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        this.type = type;
        this.setTitle(UMLMessage.LABEL_TYPESEARCH);
        this.setMessage(UMLMessage.LABEL_SEARCH_CONTENTS);
        this.element = element;
    }

    /**
     * initData
     *   void
     */
    private void initData() {
        final ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    dialog.run(true, false, new IRunnableWithProgress() {
                        @Override
                        public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException {
                            List<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
                            monitor.beginTask("", resources.size());
                            Set<Element> elementList = new HashSet<Element>();

                            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) DomainRegistry.getEditingDomain()
                                .getResourceSet()).getURIResourceMap();
                            Set<Resource> resourceHashSet = new HashSet<Resource>();

                            for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
                                URI uri = (URI) iterator.next();
                                Resource resource = uriResourceMap.get(uri);
                                resourceHashSet.add(resource);
                            }
                            for (Iterator<Resource> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
                                monitor.worked(1);
                                Resource resource = iterator.next();

                                Model model = (Model) EcoreUtil.getObjectByType(resource.getContents(),
                                    UMLPackage.Literals.MODEL);
                                if (model != null) {
                                    for (Element element : model.allOwnedElements()) {
                                        if (null == element.getOwner()) {
                                            continue;
                                        }
                                        if (type == TypeSelectDialogType.LIFELINE) {
                                            filteredLifelineListMaker(elementList, element);
                                        } else if (type == TypeSelectDialogType.PROPERTY) {
                                            filteredPropertyListMaker(elementList, element);
                                        } else if (type == TypeSelectDialogType.RETURN_TYPE) {
                                            filteredReturnTypeListMaker(elementList, element);
                                        } else if (type == TypeSelectDialogType.RAISED_EXCEPTION) {
                                            filteredRaisedExceptionListMaker(elementList, element);
                                        } else if (type == TypeSelectDialogType.DEFAULT_VALUE) {
                                            filteredDefaultValueListMaker(elementList, element);
                                        }
                                    }
                                }
                            }
                            elements = elementList;
                            setElements(elementList.toArray());
                        }
                    });
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    

    /**
     * @see org.eclipse.ui.dialogs.SelectionDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, IDialogConstants.FINISH_ID, UMLMessage.LABEL_TYPE_CANCEL, true);

        super.createButtonsForButtonBar(parent);
    }
    
    /**
     * @see org.eclipse.ui.dialogs.AbstractElementListSelectionDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Label createMessageArea(Composite composite) {
        this.createSearchRangeComboBox(composite.getParent());
        return super.createMessageArea(composite);
    }
    
    /**
     * @see org.eclipse.jface.dialogs.TrayDialog#createHelpControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createHelpControl(Composite parent) {
        return null;
    }

    /**
     * 
     *  
     * @param parent void
     */
    private void createSearchRangeComboBox(Composite parent) {
        
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        container.setLayout(layout);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        container.setLayoutData(data);
        
        Group composite = new Group(container, SWT.NONE);
        layout = new GridLayout(3, false);
        composite.setLayout(layout);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 3;
//        data.horizontalAlignment = GridData.END;
        composite.setLayoutData(data);
        composite.setText(UMLMessage.LABEL_SEARCH_SCOPE);//"Search Scope");
        
        workspaceButton = new Button(composite, SWT.RADIO);
        workspaceButton.setText(UMLMessage.LABEL_WORKSPACE);//"Workspace");
        workspaceButton.setSelection(true);

        projectButton = new Button(composite, SWT.RADIO);
        projectButton.setText(UMLMessage.LABEL_PROJECT);//"Project");

        modelButton = new Button(composite, SWT.RADIO);
        modelButton.setText(UMLMessage.LABEL_MODEL);//"Model");

        workspaceButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                workspaceButton.setSelection(true);
                projectButton.setSelection(false);
                modelButton.setSelection(false);
                Set<Element> inputElements = getElementList();
                setListElements(inputElements.toArray());
            }
        });
        projectButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                workspaceButton.setSelection(false);
                projectButton.setSelection(true);
                modelButton.setSelection(false);

                Set<Element> inputElements = getElementList();
                List<Element> filterElements = new ArrayList<Element>();
                for( Element child : inputElements ) {
                    IFile file = WorkspaceSynchronizer.getFile(child.eResource());
                    if( null != file ) {
                        IFile selectedFile = WorkspaceSynchronizer.getFile(element.eResource());
                        if( selectedFile.getProject().equals(file.getProject()) ) {
                            filterElements.add(child);
                        }
                    } else {
                        filterElements.add(child);
                    }
                }
                setListElements(filterElements.toArray());
            }
        });
        modelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                setModelElement();
            }
        });
    }
    
    
    /**
     * 
     *   void
     */
    private void setModelElement() {
        workspaceButton.setSelection(false);
        projectButton.setSelection(false);
        modelButton.setSelection(true);
        
        Set<Element> inputElements = getElementList();
        List<Element> filterElements = new ArrayList<Element>();
        
        Model model = null;
        if( element.eResource().getContents().get(0) instanceof Model ) {
            model = (Model) element.eResource().getContents().get(0);
        }
        for( Element child : model.allOwnedElements() ) {
            if( !filterElements.contains(child) && inputElements.contains(child) ) {
                filterElements.add(child);
            }
        }
        List<Element> modelElements = new ArrayList<Element>();
        modelElements.addAll(model.allOwnedElements());
        for( Element packageImport : model.getImportedPackages() ) {
            for( Element library : packageImport.allOwnedElements()) {
                if( !filterElements.contains(library) && inputElements.contains(library) ) {
                    filterElements.add(library);
                }
            }
        }
         
        
        setListElements(filterElements.toArray());
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.FINISH_ID) {
            typeCancelPressed(buttonId);
        } else {
            super.buttonPressed(buttonId);
        }

    }

    /**
     * 유형 타입 해제 버튼을 눌렀을 경우 실행되는 메소드 void
     */
    protected void typeCancelPressed(int buttonId) {
        this.setReturnCode(IDialogConstants.FINISH_ID);
        this.close();
    }
    
    /**
     * elements
     */
    Set<Element> elements = Collections.emptySet();
    
    /**
     * 타입에 따른 목록 생성
     * 
     * @return List<Element>
     */
    private Set<Element> getElementList() {
        return elements;
    }

    /**
     * 라이프라인 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredLifelineListMaker(Set<Element> elementList, Element umlObject) {
        if (umlObject.eClass().getInstanceTypeName().equals(Class.class.getName())) {
            elementList.add(umlObject);
        } else if (umlObject.eClass().getInstanceTypeName().equals(Interface.class.getName())) {
            elementList.add(umlObject);
        } else if (umlObject.eClass().getInstanceTypeName().equals(Actor.class.getName())) {
            elementList.add(umlObject);
        }
    }

    /**
     * 프로퍼티 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredPropertyListMaker(Set<Element> elementList, Element umlObject) {
        if (umlObject instanceof Type) {
            if (umlObject instanceof Relationship) {} else {
                elementList.add(umlObject);
            }
        }
    }

    /**
     * 리턴 유형 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredReturnTypeListMaker(Set<Element> elementList, Element umlObject) {
        // if (umlObject instanceof NamedElement) {
        if (umlObject instanceof Type) {
            if (umlObject instanceof Relationship) {} else {
                elementList.add(umlObject);
            }
        }
    }

    /**
     * 발생된 예외 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredRaisedExceptionListMaker(Set<Element> elementList, Element umlObject) {
        if (umlObject instanceof NamedElement) {
            elementList.add(umlObject);
        }
    }

    /**
     * 기본값 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredDefaultValueListMaker(Set<Element> elementList, Element umlObject) {
        if (umlObject instanceof NamedElement) {
            elementList.add(umlObject);
        }
    }

}
