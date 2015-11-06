/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.util;

import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributesEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NotationNameEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationsEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.StereotypeEditPart;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.property.util.ModelHandler;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameEditPart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.util</li>
 * <li>설 명 : PropertyModelHandler</li>
 * <li>작성일 : 2010. 3. 10.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class PropertyModelHandler extends ModelHandler {

    static private PropertyModelHandler instance;
    
    /**
     * PropertyModelHandler
     */
    private PropertyModelHandler(){
    }

    public static PropertyModelHandler getInstance() {
        if(instance == null) {
            instance = new PropertyModelHandler();
        }
        return instance;
    }
    
    /**
     * input 객체에 따라 적절한 인스턴스 클래스를 반환하는 메소드
     * 
     * @param input
     * @return Class
     */
    @SuppressWarnings("unchecked")
    public Class getProperInstanceClass(Object input) {
        
        if(input instanceof EditPart){
            Object model = ((EditPart)input).getModel();
            if(model instanceof AbstractView){
                if( null != ((AbstractView)model).getUmlModel() && DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return null;
                }
            }
        }
        
        if (input instanceof ITreeNode) {
            return takeOutPropertyModelFromTreeNode(input) != null ? takeOutPropertyModelFromTreeNode(input).eClass()
                .getInstanceClass() : null;
        } else if (input instanceof EditPart) {
            return takeOutPropertyModelFromEditPart(input) != null ? takeOutPropertyModelFromEditPart(input).eClass()
                .getInstanceClass() : null;
        }

        return null;
    }

    /**
     * input 객체에 따라 적절한 모델을 반환하는 메소드
     * 
     * @param input
     * @return Class
     */
    public Object getProperModel(Object input) {
        if (input instanceof ITreeNode) {
            return takeOutPropertyModelFromTreeNode(input);
        } else if (input instanceof EditPart) {
            return takeOutPropertyModelFromEditPart(input);
        } else if (input instanceof EObject) {
            return takeOutPropertyModelFromEObject(input);
        }

        return null;
    }

    /**
     * 트리 노드에서 선택한 객체의 프로퍼티 모델을 꺼내는 메소드
     * 
     * @param object
     * @return Class
     */
//    private EObject takeOutPropertyModelFromTreeNode(Object object) {
//        EObject selectedObject = ((ITreeNode) object).getEObject();
//
//        EObject invokedClassObject = null;
//
//        // 이전 뷰 모델(umldiagram)의 다이어그램 요소가 선택된 경우
//        if (selectedObject instanceof nexcore.tool.uml.model.umldiagram.Diagram) {
//            invokedClassObject = umlDiagramSwitch.doSwitch(selectedObject);
//            // 새 뷰 모델(notation)의 다이어그램 요소가 선택된 경우
//            // } else if(selectedObject instanceof
//            // nexcore.tool.uml.model.notation.Diagram) {
//            // invokedClassObject = notationSwitch.doSwitch(selectedObject);
//        } else {
//            invokedClassObject = umlSwitch.doSwitch(selectedObject);
//        }
//
//        if (invokedClassObject != null) {
//            return invokedClassObject;
//        } else {
//            return null;
//        }
//    }
    
//    private EObject takeOutPropertyModelFromEObject(Object object) {
//        EObject selectedObject = (EObject) object;
//        
//        EObject invokedClassObject = null;
//        
//        // 이전 뷰 모델(umldiagram)의 다이어그램 요소가 선택된 경우
//        if (selectedObject instanceof nexcore.tool.uml.model.umldiagram.Diagram) {
//            invokedClassObject = umlDiagramSwitch.doSwitch(selectedObject);
//            // 새 뷰 모델(notation)의 다이어그램 요소가 선택된 경우
//            // } else if(selectedObject instanceof
//            // nexcore.tool.uml.model.notation.Diagram) {
//            // invokedClassObject = notationSwitch.doSwitch(selectedObject);
//        } else {
//            invokedClassObject = umlSwitch.doSwitch(selectedObject);
//        }
//        
//        if (invokedClassObject != null) {
//            return invokedClassObject;
//        } else {
//            return null;
//        }
//    }

    /**
     * 에딧 파트에서 선택한 객체의 프로퍼티 모델을 꺼내는 메소드
     * 
     * @param object
     * @return Class
     */
    private EObject takeOutPropertyModelFromEditPart(Object object) {
        EObject invokedClassObject = null;

        if (object instanceof NotationNameEditPart || object instanceof AttributesEditPart
            || object instanceof OperationsEditPart || object instanceof StereotypeEditPart
            || object instanceof LifeLineNameEditPart) {
            object = ((EditPart) object).getParent();
        }

        if (object != null) {
        	Object model = ((EditPart) object).getModel();
            if (model != null) {
                if (model instanceof AbstractView) {
                    invokedClassObject = umlDiagramSwitch.doSwitch((EObject) model);
                } else if (model instanceof Element) {
                    invokedClassObject = umlSwitch.doSwitch((EObject) model);
                }
            }
        }

        if (invokedClassObject != null) {
            return invokedClassObject;
        } else {
            return null;
        }
    }

    /**
     * UML 모델에서 특정 요소의 반환값을 변경할 때 사용하는 메소드
     */
//    private UMLSwitch<EObject> umlSwitch = new UMLSwitch<EObject>() {
//        /**
//         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
//         */
//        @Override
//        public EObject defaultCase(EObject object) {
//            return object;
//        }
//    };

    /**
     * 이전 뷰 모델(umldiagram)에서 특정 요소의 반환값을 변경할 때 사용하는 메소드
     */
//    private UMLDiagramSwitch<EObject> umlDiagramSwitch = new UMLDiagramSwitch<EObject>() {
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseDiagram(nexcore.tool.uml.model.umldiagram.Diagram)
//         */
//        @Override
//        public EObject caseDiagram(Diagram object) {
//            return object;
//        }
//
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseAttachement(nexcore.tool.uml.model.umldiagram.Attachement)
//         */
//        @Override
//        public EObject caseAttachement(Attachement object) {
//            return object;
//        }
//
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
//         */
//        @Override
//        public EObject defaultCase(EObject object) {
//            return ((AbstractView) object).getUmlModel();
//        }
//    };

    // /**
    // * 새 뷰 모델(notation)에서 특정 요소의 반환값을 변경할 때 사용하는 메소드
    // */
    // private static NotationSwitch<EObject> notationSwitch = new
    // NotationSwitch<EObject>() {
    // /**
    // * @see
    // nexcore.tool.uml.model.notation.util.NotationSwitch#caseDiagram(nexcore.tool.uml.model.notation.Diagram)
    // */
    // @Override
    // public EObject caseDiagram(nexcore.tool.uml.model.notation.Diagram
    // object) {
    // return object;
    // }
    // };

    /**
     * element 객체에 따라 적절한 제목을 반환하는 메소드
     * 
     * @param input
     * @return Class
     */
    public String getProperTitle(Element element) {
        String propertyTitle = null;

        if (element != null) {
            if (element instanceof AbstractView) {
                propertyTitle = (String) umlDiagramPropertyTitleSwitch.doSwitch(element);
            } else if (element instanceof Element) {
                propertyTitle = (String) umlPropertyTitleSwitch.doSwitch(element);
            }
        }

        if (propertyTitle != null) {
            return propertyTitle;
        } else {
            return null;
        }
    }

    /**
     * UML 모델에서 프로퍼티 제목에 사용할 특정 요소의 이름을 반환할 때 사용하는 메소드
     */
//    private UMLSwitch<Object> umlPropertyTitleSwitch = new UMLSwitch<Object>() {
//        StringBuilder propertyTitleStringBuilder = new StringBuilder();
//
//        /**
//         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
//         */
//        @Override
//        public Object caseComment(Comment object) {
//            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                .toString()));
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRAKET_LEFT_IN_DOUBLE);
//            propertyTitleStringBuilder.append(UMLMessage.getMessage(UMLMessage.LABEL_DOCUMENT_INFORMATION));
//            propertyTitleStringBuilder.append(UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRAKET_RIGHT_IN_DOUBLE);
//
//            return propertyTitleStringBuilder.toString();
//        }
//
//        /**
//         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseRelationship(org.eclipse.uml2.uml.Relationship)
//         */
//        @Override
//        public Object caseRelationship(Relationship object) {
//            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
//            if (!object.getSourceDirectedRelationships().isEmpty()
//                && !object.getTargetDirectedRelationships().isEmpty()) {
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                    .toString()));
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(object.getTargetDirectedRelationships().get(0).toString());
//            } else if (!object.getSourceDirectedRelationships().isEmpty()) {
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                    .toString()));
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(object.getSourceDirectedRelationships().get(0).toString());
//            } else if (!object.getTargetDirectedRelationships().isEmpty()) {
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                    .toString()));
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(object.getTargetDirectedRelationships().get(0).toString());
//            } else {
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//                propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                    .toString()));
//                propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//            }
//
//            return propertyTitleStringBuilder.toString();
//        }
//
//        /**
//         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
//         */
//        @Override
//        public Object defaultCase(EObject object) {
//            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(StringUMLNameUtil.getUMLNotationNameFromClass(object.getClass()
//                .toString()));
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(((NamedElement) object).getQualifiedName() != null
//            ? ((NamedElement) object).getQualifiedName() : UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
//
//            return propertyTitleStringBuilder.toString();
//        }
//    };

//    /**
//     * 이전 뷰 모델(umldiagram)에서 프로퍼티 제목에 사용할 특정 요소의 이름을 반환할 때 사용하는 메소드
//     */
//    private UMLDiagramSwitch<Object> umlDiagramPropertyTitleSwitch = new UMLDiagramSwitch<Object>() {
//        StringBuilder propertyTitleStringBuilder = new StringBuilder();
//
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseAttachement(nexcore.tool.uml.model.umldiagram.Attachement)
//         */
//        @Override
//        public Object caseAttachement(Attachement object) {
//            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append("NoteAttachment");
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//
//            return propertyTitleStringBuilder.toString();
//        }
//
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseDiagram(nexcore.tool.uml.model.umldiagram.Diagram)
//         */
//        @Override
//        public Object caseDiagram(Diagram object) {
//            propertyTitleStringBuilder.delete(0, propertyTitleStringBuilder.length());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(object.getType().getName());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
//            propertyTitleStringBuilder.append(((NamedElement) object.getParent()).getQualifiedName());
//            propertyTitleStringBuilder.append(UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON);
//            propertyTitleStringBuilder.append(object.getName());
//
//            return propertyTitleStringBuilder.toString();
//        }
//
//    };

    /**
     * element 객체에 따라 적절한 이름을 반환하는 메소드
     * 
     * @param input
     * @return Class
     */
    public String getProperName(Element element) {
        String propertyName = null;

        if (element != null) {
            if (element instanceof AbstractView) {
                propertyName = (String) umlDiagramNameSwitch.doSwitch(element);
            } else if (element instanceof Element) {
                propertyName = (String) umlNameSwitch.doSwitch(element);
            }
        }

        if (propertyName != null) {
            return propertyName;
        } else {
            return null;
        }
    }

//    /**
//     * UML 모델에서 프로퍼티 제목에 사용할 특정 요소의 이름을 반환할 때 사용하는 메소드
//     */
//    private UMLSwitch<Object> umlNameSwitch = new UMLSwitch<Object>() {
//        /**
//         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
//         */
//        @Override
//        public Object defaultCase(EObject object) {
//            return ((NamedElement) object).getName();
//        }
//    };
//
//    /**
//     * 이전 뷰 모델(umldiagram)에서 프로퍼티 제목에 사용할 특정 요소의 이름을 반환할 때 사용하는 메소드
//     */
//    private UMLDiagramSwitch<Object> umlDiagramNameSwitch = new UMLDiagramSwitch<Object>() {
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseAttachement(nexcore.tool.uml.model.umldiagram.Attachement)
//         */
//        @Override
//        public Object caseAttachement(Attachement object) {
//            return object.getName();
//        }
//
//        /**
//         * @see nexcore.tool.uml.model.umldiagram.util.UMLDiagramSwitch#caseDiagram(nexcore.tool.uml.model.umldiagram.Diagram)
//         */
//        @Override
//        public Object caseDiagram(Diagram object) {
//            return object.getName();
//        }
//
//    };
}
