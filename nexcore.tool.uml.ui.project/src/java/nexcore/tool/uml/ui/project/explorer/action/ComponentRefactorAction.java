/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : ComponentRefactorAction</li>
 * <li>작성일 : 2011. 9. 21.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ComponentRefactorAction extends CommonActionDelegate {


    /** 선택한 엘리먼트 */
    private Element selectedElement;
    
    /** 오퍼레이션을 복사할 대상 bizUnit의 Prefix */
    private List<String> prefixSet;
    
    /** 오퍼레이션을 복사할 컴포넌트 */
    private List<org.eclipse.uml2.uml.Class> componentList;
    
    /** «Component» */
    private static final String COMPONENT_STEREOTYPE = "«Component»";
    /**
     * ComponentRefactorAction
     */
    public ComponentRefactorAction() {
    }
    
    /* 
     * selectionChanged
     * 
     * (non-Javadoc)
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        if (!(selection instanceof IStructuredSelection)) {
            return;
        }

        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        ITreeNode treeNode = (ITreeNode) structureSelection.getFirstElement();
        if( treeNode != null && treeNode.getEObject() instanceof Element ) {
            selectedElement = (Element) treeNode.getEObject();
        }
    }

    /**
     * 
     * SelectedElement 하위에 컴포넌트가 있는지 검사.
     * 
     * @param parentElement
     * @return
     */
    private boolean hasComponentChild(Element parentElement) {
        
        for( Element child : parentElement.getOwnedElements() ) {
            if( child instanceof org.eclipse.uml2.uml.Class ) {
                if( COMPONENT_STEREOTYPE.equals( ProjectUtil.getStereotypeLabel(child).trim() ) ) {
                    return true;
                }
            }
            if (child instanceof PackageableElement) {
                if(hasComponentChild( child )){
                    return true;
                }
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    // 하위 노드에 컴포넌트가 포함되지 않는다면 액션을 실행하지 않는다.
                    if (!hasComponentChild(selectedElement)) {
                    	MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                            UMLMessage.MESSAGE_DIALOG_TITLE,
                            UMLMessage.MESSAGE_SELECTED_ELEMENT_HAS_NO_COMPONENT);// "선택한 엘리먼트의 하위에 컴포넌트가 존재하지 않습니다.");
                        return;
                    } else {
                        componentList = new ArrayList<Class>();
                        setComponentList(selectedElement);
                    }
                    
					int appliedCnt = 0;
					String prefixStr = PreferenceUtil.INSTANCE
							.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_TARGET_ELEMENT_PREFIX);
					
					if (null != prefixStr && !UICoreConstant.EMPTY_STRING.equals(prefixStr)) {
						setPrefixString(prefixStr);

						for (org.eclipse.uml2.uml.Class component : componentList) {
							Element parent = component.getOwner();
							for (Operation oper : getOperationList(parent)) {
								Operation newOper = (Operation) EcoreUtil.copy(oper);
								List<String> oldOperNameList = new ArrayList<String>();
								for (Operation oldOper : component.getOwnedOperations()) {
									oldOperNameList.add(oldOper.getName());
								}

								if (!oldOperNameList.contains(newOper.getName().trim())) {

									component.getOwnedOperations().add((Operation) newOper);

									// BizUnit 하위 오퍼레이션에 적용된 스테레오타입과
									// 스테레오타입 속성값도 함께
									// 복사해준다.
									String serviceStereoTypeName = MDDCoreConstant.MDA_PROFILES_NAMES[1]
											+ UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON
											+ MDDCoreConstant.BIZUNIT_METADATA_METHOD_STEREOTYPE_NAME;

									Stereotype stereotype = oper.getAppliedStereotype(serviceStereoTypeName);
									if (stereotype != null) {

										Stereotype newStereo = newOper
												.getApplicableStereotype(serviceStereoTypeName);
										newOper.applyStereotype(newStereo);

										String korName = UICoreConstant.EMPTY_STRING;
										String engName = UICoreConstant.EMPTY_STRING;
										Object temp = oper.getValue(stereotype,
														MDDCoreConstant.METHOD_NAME_KEY_IN_METHOD_STEREOTYPE);
										if (null != temp) {
											korName = (String) temp;
										}
										temp = oper.getValue(
														stereotype,
														MDDCoreConstant.METHOD_ENG_NAME_KEY_IN_METHOD_STEREOTYPE);
										if (null != temp) {
											engName = (String) temp;
										}

										newOper.setValue(newStereo,
												MDDCoreConstant.METHOD_NAME_KEY_IN_METHOD_STEREOTYPE,
												korName);
										newOper.setValue(newStereo,
												MDDCoreConstant.METHOD_ENG_NAME_KEY_IN_METHOD_STEREOTYPE,
												engName);
										appliedCnt++;

									}

								}
							}
						}
					}
					
					MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                            UMLMessage.MESSAGE_DIALOG_TITLE,
                            UMLMessage.getMessage(UMLMessage.MESSAGE_APPLY_CREATE_OPERATION, String.valueOf(appliedCnt)));
                }
            });
    }
    
    /**
     * 
     * 컴포넌트 상위 부모의 하위 요소들 중에 존재하는 오퍼레이션들을 검사한다.
     * 
     * @param parent
     * @return
     */
    private List<Operation> getOperationList(Element parent) {

        List<org.eclipse.uml2.uml.Package> packageList = new ArrayList<org.eclipse.uml2.uml.Package>();
        List<Operation> operations = new ArrayList<Operation>();
        
//        org.eclipse.uml2.uml.Package bizPackage = null;
//        for( Element child : parent.getOwnedElements() ) {
//            if( child instanceof org.eclipse.uml2.uml.Package ) {
//                if( "biz".equals( ((org.eclipse.uml2.uml.Package)child).getName().trim()) ) {
//                    bizPackage = (Package) child;
//                }
//            }
//        }
        getPackageList(parent, packageList);
        
        for( org.eclipse.uml2.uml.Package bizPackage : packageList ) {
            if( bizPackage == null ) {
                return new ArrayList<Operation>();
            }
            for( Element child : bizPackage.getOwnedElements() ) {
                
                if( child instanceof org.eclipse.uml2.uml.Package ) {
                    org.eclipse.uml2.uml.Package childPackage = (org.eclipse.uml2.uml.Package) child;
                    
                    for( String prefix : prefixSet ) {
                        int charCount = prefix.length();
                        String packagePrefix = childPackage.getName().substring(0, charCount);
                        if( prefix.equals(packagePrefix)){
                            
                            for( Element childElement : childPackage.getOwnedElements() ) {
                                if( childElement instanceof org.eclipse.uml2.uml.Class ) {
                                    operations.addAll( ((org.eclipse.uml2.uml.Class)childElement).getOperations() );
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return operations;
    }

    /**
     * 
     * 컴포넌트에 복사할 오퍼레이션을 가지고 있는 biz 패키지를 찾는다.
     * 
     * @param parent
     * @param packageList
     * @return
     */
    private List<org.eclipse.uml2.uml.Package> getPackageList(Element parent, List<org.eclipse.uml2.uml.Package> packageList) {
        
        for( Element child : parent.getOwnedElements() ) {
            if( child instanceof org.eclipse.uml2.uml.Package ) {
                org.eclipse.uml2.uml.Package childPackage = (org.eclipse.uml2.uml.Package) child ;
                packageList.add(childPackage);
                
                packageList = getPackageList(childPackage, packageList);
            }
        }
        return packageList;
    }

    /**
     * 
     * SelectedElement 하위에 있는 전체 컴포넌트를 리스트로 보관.
     * 
     * @param parentElement
     */
    private void setComponentList(Element parentElement) {
        for( Element child : parentElement.getOwnedElements() ) {
            if( child instanceof org.eclipse.uml2.uml.Class ) {
                if( COMPONENT_STEREOTYPE.equals( ProjectUtil.getStereotypeLabel(child).trim() ) ) {
                    
                    if( !componentList.contains(child) ) {
                        componentList.add((Class) child);
                    }
                }
            } else if ( child instanceof PackageableElement ) {
                setComponentList(child);
            }
        }
    }

    /**
     * 
     * Prefix Set을 환경설정에서 부터 읽어와 ',' 단위로 파싱하여 보관한다.
     * 
     * @param prefixStr
     */
    private void setPrefixString(String prefixStr) {
        
        prefixStr = prefixStr.trim();
        prefixSet = new ArrayList<String>();
        while (prefixStr.indexOf(MDDCoreConstant.COMMA) != -1) {
            prefixSet.add(prefixStr.substring(0, prefixStr.indexOf(',')).trim());
            prefixStr = prefixStr.substring(prefixStr.indexOf(',') + 1).trim();
        }
        prefixSet.add(prefixStr);
    }

    /**
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
