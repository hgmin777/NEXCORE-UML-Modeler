/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;
import nexcore.tool.uml.ui.core.registry.ResourceManager;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.layout.GridData;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : NameOfNotationGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class NameOfNotationGeneralSection extends TemplateTextSection {

    /**
     * 입력된 모델을 NamedElement 로 리턴함.
     * 
     * @return NamedElement
     */
    private NamedElement getData() {
        return (NamedElement) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        return this.getData().getName();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_NAME);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
        text = getWidgetFactory().createText(sectionComposite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        text.setLayoutData(gridData);
    }
    
    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(final String value) {
        try {
            
            if( !value.equals(getData().getName()) ) {
                setElemetInfo(getData(), value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * setElemetInfo
     *  
     * @param childElement
     * @param value void
     */
    private void setElemetInfo(NamedElement childElement, String value) {

        value = value.trim();
        if (UICoreConstant.EMPTY_STRING.equals(value)) {
            if( !(childElement instanceof Relationship || childElement instanceof ActivityNode) ) {
                MessageDialog.openInformation(null, UMLMessage.LABEL_NEXCORE_UML_MODELER, UMLMessage.MESSAGE_CANNOT_BE_EMPTY_NAME);
                return;
            }
        } else {
            
            // -, + , ~, #을 검사하여 visibility 설정
            // - : privite, + : public , ~ : package , # : protected
            if (MINUS_CHAR == value.charAt(0) ) {
                childElement.setVisibility(VisibilityKind.PRIVATE_LITERAL);
                value = value.substring(1);
            } else if (PLUS_CHAR == value.charAt(0) ) {
                childElement.setVisibility(VisibilityKind.PUBLIC_LITERAL);
                value = value.substring(1);
            } else if (WAVE_CHAR == value.charAt(0) ) {
                childElement.setVisibility(VisibilityKind.PACKAGE_LITERAL);
                value = value.substring(1);
            } else if (SHOP_CHAR == value.charAt(0) ) {
                childElement.setVisibility(VisibilityKind.PROTECTED_LITERAL);
                value = value.substring(1);
            }
            value = value.trim();
        }
        
        // <<, >> 를 검사하여 스테레오타입이 있는지 확인.
        if(value.contains(LEFT_BRACKET) && value.contains(RIGHT_BRACKET)){   
            String keyword = value.substring( value.indexOf(LEFT_BRACKET) , value.indexOf(RIGHT_BRACKET) );
            keyword = keyword.substring(2);
            childElement.addKeyword(keyword);
            value = value.substring( value.indexOf(RIGHT_BRACKET));
            value = value.replaceAll(RIGHT_BRACKET, EMPTY_TEXT);
            value = value.trim();
        }

        
        if ( childElement instanceof Operation ) {
            if(value.contains(SMALL_LEFT_BRACKET) && value.contains(SMALL_RIGHT_BRACKET)){
                String elementName = value.substring(0, value.indexOf(SMALL_LEFT_BRACKET) );
                String parameters = value.substring( value.indexOf(SMALL_LEFT_BRACKET) , value.indexOf(SMALL_RIGHT_BRACKET) );
                parameters = parameters.substring(1);
                
                while(true){
                    if (parameters.contains(",")) {
                        String parameterStr = parameters.substring(0, parameters.indexOf(","));
                        parameterStr.trim();
                        parameters = parameters.substring(parameters.indexOf(",") + 1);
                        parameters = parameters.trim();
                        Parameter newParameter = UMLFactory.eINSTANCE.createParameter();
                        ((Operation)childElement).getOwnedParameters().add(newParameter);
                        setElemetInfo(newParameter, parameterStr);
                    } else {
                        if(!EMPTY_TEXT.equals( parameters )){
                            Parameter newParameter = UMLFactory.eINSTANCE.createParameter();
                            ((Operation)childElement).getOwnedParameters().add(newParameter);
                            setElemetInfo(newParameter, parameters);
                        }
                        break;
                    }
                }
                value = value.substring( value.indexOf(SMALL_RIGHT_BRACKET));
                if(value.indexOf(SMALL_RIGHT_BRACKET) == 0){
                    value = value.substring(1);
                } else {
                    value = value.replaceAll(SMALL_RIGHT_BRACKET, EMPTY_TEXT);
                }
                value = value.trim();
                StringBuffer sb = new StringBuffer(elementName);
                sb.append(value);
                value = sb.toString();
            }
        }
        findColon(childElement, value);
    }
    
    // : 을 검사하여 Type 설정
    /**
     * findColon
     *  
     * @param childElement
     * @param value void
     */
    private void findColon(NamedElement childElement, String value) {
        if(value.contains(COLON)){
            String type = value.substring( value.indexOf(COLON_CHAR) + 1 );
            type = type.trim();
            boolean setType = false;
            ArrayList<Type> applicableTypeList = new ArrayList<Type>();
            
            if( childElement instanceof Parameter ) {
                Parameter parameter = (Parameter) childElement;
                List<Element> typeList = createTypeList(TypeSelectDialogType.PROPERTY);
                for(Element child : typeList){
                    if(child instanceof NamedElement){
                        if( null == ((NamedElement)child).getName()){
                            continue;
                        }
                        if ( ((NamedElement)child).getName().toUpperCase().equals(type.toUpperCase()) ){
                            applicableTypeList.add((Type) child);
//                            parameter.setType((Type) child);
                            setType = true;
                        }
                    }
                }
                if(applicableTypeList.size() == 1){
                    parameter.setType(applicableTypeList.get(0));
                } else if(applicableTypeList.size() > 1){
                    createSelectTypeDialog(applicableTypeList);
                    if(selectedElement != null){
                        parameter.setType((Type) selectedElement);
                    }
                }
            } else if(childElement instanceof Property){
                Property property = (Property) childElement;
                List<Element> typeList = createTypeList(TypeSelectDialogType.PROPERTY);
                for(Element child : typeList){
                    if(child instanceof NamedElement){
                        if( null == ((NamedElement)child).getName()){
                            continue;
                        }
                        
//                        System.out.println(((NamedElement) child).getName());
                        if ( ((NamedElement)child).getName().toUpperCase().equals(type.toUpperCase()) ){
//                            property.setType((Type) child);
                            applicableTypeList.add((Type) child);
                            setType = true;
                        }
                    }
                }
                if(applicableTypeList.size() == 1){
                    property.setType(applicableTypeList.get(0));
                } else if(applicableTypeList.size() > 1){
                    createSelectTypeDialog(applicableTypeList);
                    if(selectedElement != null){
                        property.setType((Type) selectedElement);
                    }
                }
            } else if( childElement instanceof Operation ) {
                Operation operation = (Operation) childElement;
                List<Element> typeList = createTypeList(TypeSelectDialogType.RETURN_TYPE);
                for(Element child : typeList){
                    if(child instanceof NamedElement){
                        if( null == ((NamedElement)child).getName()){
                            continue;
                        }
                        if ( ((NamedElement)child).getName().toUpperCase().equals(type.toUpperCase()) ){
                            for (Parameter para : operation.returnResult()) {
                                para.destroy();
                            }
                            operation.createReturnResult(RETURN_RESULT, (Type) child);
                            applicableTypeList.add((Type) child);
                            setType = true;
                        }
                    }
                }
                if(applicableTypeList.size() == 1){
                    operation.setType(applicableTypeList.get(0));
                } else if(applicableTypeList.size() > 1){
                    createSelectTypeDialog(applicableTypeList);
                    if(selectedElement != null){
                        operation.setType((Type) selectedElement);
                    }
                }
            }
            // 해당 Type이 있을 경우 setType
            if(setType){
                childElement.setName(value.substring(0, value.indexOf(':')).trim());
                // 해당 Type이 없으면 이름에다가 모두 설정
            } else {
                childElement.setName(value.trim());
            }
        } else {
            childElement.setName(value.trim());
        }
    }
    /**
     * createSelectTypeDialog
     *  
     * @param applicableTypeList void
     */
    private void createSelectTypeDialog(ArrayList<Type> applicableTypeList) {
        TypeSelectDialog dialog = new TypeSelectDialog(null, TypeSelectDialogType.RETURN_TYPE, getData());
        dialog.setElements(applicableTypeList.toArray());
        int returnCode = dialog.open();
        switch (returnCode) {
            case Window.OK:
                selectedElement = (Element) dialog.getFirstResult();
            case Window.CANCEL:
                return;
            case IDialogConstants.FINISH_ID:
                return;
            default:
                return;
        }
    }
    
    // 프로젝트 내 전체 Type 읽어옴.
    /**
     * createTypeList
     *  
     * @param type
     * @return List<Element>
     */
    private List<Element> createTypeList(TypeSelectDialogType type) {
        List<Element> elementList = new ArrayList<Element>();
        Iterator<Element> iterator = ResourceManager.getAllElements().iterator();
        while( iterator.hasNext() ) {
            Element element = iterator.next();
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
        return elementList;
    }
    
    /**
     * 라이프라인 검색리스트에 나올 모델 리스트 생성
     * 
     * @param elementList
     * @param umlObject
     *            void
     */
    private void filteredLifelineListMaker(List<Element> elementList, Element umlObject) {
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
    private void filteredPropertyListMaker(List<Element> elementList, Element umlObject) {
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
    private void filteredReturnTypeListMaker(List<Element> elementList, Element umlObject) {
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
    private void filteredRaisedExceptionListMaker(List<Element> elementList, Element umlObject) {
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
    private void filteredDefaultValueListMaker(List<Element> elementList, Element umlObject) {
        if (umlObject instanceof NamedElement) {
            elementList.add(umlObject);
        }
    }
    
    /**
     * RETURN_RESULT
     */
    private static final String RETURN_RESULT = "returnResult";//$NON-NLS-1$

    /**
     * SMALL_RIGHT_BRACKET
     */
    private static final String SMALL_RIGHT_BRACKET = ")";//$NON-NLS-1$

    /**
     * SMALL_LEFT_BRACKET
     */
    private static final String SMALL_LEFT_BRACKET = "(";//$NON-NLS-1$

    /**
     * COLON_CHAR
     */
    private static final char COLON_CHAR = ':';//$NON-NLS-1$

    /**
     * SHOP_CHAR
     */
    private static final char SHOP_CHAR = '#';//$NON-NLS-1$

    /**
     * WAVE_CHAR
     */
    private static final char WAVE_CHAR = '~';//$NON-NLS-1$

    /**
     * PLUS_CHAR
     */
    private static final char PLUS_CHAR = '+';//$NON-NLS-1$

    /**
     * MINUS_CHAR
     */
    private static final char MINUS_CHAR = '-';//$NON-NLS-1$

    /**
     * COLON
     */
    private static final String COLON = ":";//$NON-NLS-1$
    
    /**
     * RIGHT_BRACKET
     */
    private static final String RIGHT_BRACKET = ">>";//$NON-NLS-1$

    /**
     * LEFT_BRACKET
     */
    private static final String LEFT_BRACKET = "<<";//$NON-NLS-1$
    
    /**
     * selectedElement
     */
    private Element selectedElement;
    
    /** EMPTY_TEXT */
    private String EMPTY_TEXT = "";
    
}
