/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.provider;

import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>ResourceSet의 변화에 따라, Project Explorer와 에디터를 refresh해준다.(모델 노드에 dirty 표시
 * 달아주는 용도)</li>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : ProjectResourceSetListener</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ProjectResourceSetListener extends ResourceSetListenerImpl /*implements ResourceSetController*/ {

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        List<Notification> list = event.getNotifications();
        Notification notification = null;
        EObject eObject = null;
        Object newValue = null;

        int type = -1;

        for (Iterator<Notification> it = list.iterator(); it.hasNext();) {
            try {
                newValue = null;
                notification = it.next();
                if (notification.isTouch()) {
                    continue;
                }
                /*
                 * 리소스 셋의 변경이 이루졌을 때 이벤트 리스너가 실행되는 경우는 모델 파일 자체 변경과 모델요소 변경 이벤트
                 * 두가지 유형이 동시에 발생됨. 이벤트는 NotifyingList의 변경시에도 발생됨으로
                 * notification의 유형으로 구분하여 Resource의 경우만 처리하여야함.
                 */
                if (notification instanceof NotifyingList) {
                    continue;
                }
                type = notification.getEventType();
                
                if (notification.getNotifier() instanceof Resource) {
                    Resource resource = (Resource) notification.getNotifier();
                    if (resource == null || resource.getContents() == null || resource.getContents().isEmpty()) {
                        continue;
                    }
                    
                    String uriStr = resource.getURI().toString();
                    if (uriStr.endsWith(UICoreConstant.PROJECT_CONSTANTS__RELATION)) {
                        continue;
                    }

                    switch (type) {
                        /*
                         * modified 속성이 변경될 때 발생. 저장 될 경우 또는 Dirty 상태가 되었을 때 발생,
                         * 모델이 새롭게 생성되었을 경우도 발생됨. 단편화 시. 병합 시. svn update 시.
                         */
                        case Notification.SET:
                            if (resource.getContents().size() == 0) {
                                return;
                            }
                            eObject = resource.getContents().get(0);
                            if (null != eObject) {
                                final EObject eObj = eObject;
                                if (!DomainUtil.isProxy(eObj)) {
                                    
                                    
	                                Display.getDefault().syncExec(new Runnable() {
	                                    /**
	                                     * @see java.lang.Runnable#run()
	                                     */
	                                    public void run() {
	                                        try {
	                                            URI rootURI = EcoreUtil.getRootContainer(eObj).eResource().getURI();
	                                            boolean active = ResourceManager.getInstance().isActive(rootURI);
	                                            if( active ) {
	                                                ProjectUtil.updateExplorer(eObj, true);
	                                            } else {
	                                                List<Resource> inverseReferences = ResourceManager.getInstance().inverseReferences(eObj);
	                                                for(Resource r : inverseReferences) {
	                                                    ProjectUtil.updateExplorer(r.getContents().get(0), true);
	                                                }
	                                            }
                                            } catch (Exception e) {
                                            }
	                                    }
	                                });
                                }
                            }
                            break;
                        /*
                         * 리소스가 외부(편집기 에서 소스 수정)에서 수정된 경우 또는, 완전히 새롭게 로딩된 경우 기존의
                         * 리소스가 제외되어 이벤트 발생 => 결국 리소스가 변경되었을 때 리소스의 부모 객체를 업데이트
                         * 하도록 처리하고 있음. 추후, 리소스 자체 변경되어 refresh가 필요한 경우 처리가 필요함
                         */
                        case Notification.REMOVE:
                            break;

                        case Notification.REMOVE_MANY:
                            break;

                        default:
                            break;
                    }
                } else if (notification.getNotifier() instanceof EObject) {
                    eObject = (EObject) notification.getNotifier();

                    switch (type) {
                        /*
                         * 모델요소가 추가된후 이름변경 처리로 인한 이벤트 발생, 속성 변경이 된 경우 발생되며, 변경
                         * 객체가 전달됨. 키워드가 등록된 후 undo처리할 경우 eAnnotation의 부모가
                         * ChangeDescription으로 전달됨으로 별도 처리 필요함.<= 후속 이벤트가 키워드의
                         * 부모객체에 Set이벤트가 발생함으로 현재는 eobject의 null처리만 처리함.
                         */
                        case Notification.SET:
                            if (eObject instanceof EAnnotation) {
                                eObject = UMLManager.getParent((EAnnotation) eObject);

                            } else if (eObject instanceof Diagram) {
                                eObject = UMLManager.getParent((EAnnotation) eObject.eContainer());

                            } else if (eObject instanceof DynamicEObjectImpl) {
                                // 스테레오타입 해제 시 처리
                                final Object object = notification.getOldValue();
                                if (object instanceof EObject) {
                                    Display.getDefault().syncExec(new Runnable() {
                                        /**
                                         * @see java.lang.Runnable#run()
                                         */
                                        public void run() {
                                            ProjectUtil.updateExplorer((EObject) object, false);
                                        }
                                    });
                                    break;
                                }

                            } else {
                                if (!(eObject instanceof Model)) {
                                    eObject = eObject.eContainer();
                                }
                            }
                            if (null != eObject) {
                                final EObject eObj = eObject;
                                Display.getDefault().syncExec(new Runnable() {
                                    /**
                                     * @see java.lang.Runnable#run()
                                     */
                                    public void run() {
                                        ProjectUtil.refreshNodeInExplorer(eObj);
                                        // 유형설정 등의 기능에서 프로젝트 내의 UML 요소들을 검색하는 시간을 단축하기 위해 요소들을 리스트에 동적으로 관리.
                                    }
                                });
                            }

                            break;
                        /*
                         * 모델 요소가 추가된 경우 이벤트 발생 getNewValue()와 getOldValue()가
                         * 유효함 다이어그램이 또는, 다이어그램(ViewModel)요소가 추가된 경우 처리가 필요. 추가된
                         * 요소가 전달됨 stereotype 인 경우 전달 객체가 DynamicEobjectImpl이므로
                         * 할당된 클래스를 계산하여 처리해야 함. <= Refresh에서 처리하고 있음.
                         */
                        case Notification.ADD:
                            
                            if (eObject instanceof EAnnotation) {
                                eObject = UMLManager.getParent((EAnnotation) eObject);
                            }
                            if (null != eObject) {
                                final EObject eObj = (EObject) eObject;
                                final Object object = notification.getNewValue();
                                Display.getDefault().syncExec(new Runnable() {
                                    /**
                                     * @see java.lang.Runnable#run()
                                     */
                                    public void run() {
                                        ProjectUtil.refreshNodeInExplorer(eObj);
                                        // 유형설정 등의 기능에서 프로젝트 내의 UML 요소들을 검색하는 시간을 단축하기 위해 요소들을 리스트에 동적으로 관리.
                                    }
                                });

                                ProjectUtil.expandTree(eObject);
                                newValue = notification.getNewValue();
                                if (!(newValue instanceof EAnnotation)) {

                                    final EObject value = (EObject) newValue;
                                    ProjectUtil.setSelectonNodeInExplorer(value);
                                    
                                    Display.getDefault().syncExec(new Runnable() {
                                        /**
                                         * @see java.lang.Runnable#run()
                                         */
                                        public void run() {
                                            ProjectUtil.setSelectonNodeInExplorer(value);
                                        }
                                    });
                                }
                            }

                            break;
                        case Notification.ADD_MANY:
                            break;
                        /*
                         * 삭제된 객체가 전달되어 전달된 객체의 부모 객체를 확인하여 처리해야함.
                         */
                        case Notification.REMOVE:
                            if (eObject instanceof EAnnotation) {
                                eObject = UMLManager.getParent((EAnnotation) eObject);
                            }
                            if (null != eObject) {
                                final EObject eObj = eObject;

                                Display.getDefault().syncExec(new Runnable() {
                                    /**
                                     * @see java.lang.Runnable#run()
                                     */
                                    public void run() {
                                        ProjectUtil.refreshNodeInExplorer(eObj);
                                    }
                                });
                            }

                            break;
                        case Notification.REMOVE_MANY:
                            break;

                        default:
                            break;
                    }

                }
            } catch (Exception error) {
                Log.error(error);
            }
        }

    }
}

