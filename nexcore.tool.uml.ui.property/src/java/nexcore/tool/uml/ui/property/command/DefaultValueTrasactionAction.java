/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.command;

import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.ui.property.section.DefaultValueGeneralSection;

import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.command</li>
 * <li>설  명 : DefaultValueTrasactionAction</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class DefaultValueTrasactionAction extends TransactionalAction {

    /**
     * element
     */
    private Object element;

    /**
     * value
     */
    private Object value;

    /**
     * DefaultValueTrasactionAction
     * @param element
     * @param value
     */
    public DefaultValueTrasactionAction(Object element, Object value) {
        this.element = element;
        this.value = value;
    }

    /**
     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
     */
    @Override
    public void doExecute() {

        if (element instanceof Parameter) {
            Parameter parameter = (Parameter) element;

            if (parameter.getType() != null
                && !DefaultValueGeneralSection.validate(parameter.getType().getName(), (String) value))
                return;
            parameter.setDefault((String) value);
            /*
             * if(parameter.getType() == null){ } else if(parameter.getType()
             * instanceof Element && !(parameter.getType() instanceof
             * PrimitiveType)){ } else if
             * (parameter.getType().getName().equals("String")) {
             * parameter.setStringDefaultValue((String) value); } else if
             * (parameter.getType().getName().equals("Integer")){ try{
             * parameter.setIntegerDefaultValue(Integer.parseInt((String)
             * value)); }catch(NumberFormatException e){
             * System.out.println("NumberFormatException!"); } } else if
             * (parameter.getType().getName().equals("Boolean")){ if(
             * (String)value == "true"){ parameter.setBooleanDefaultValue(true);
             * } else{ parameter.setBooleanDefaultValue(false); } } else if
             * (parameter.getType().getName().equals("UnlimitedNatural")){ try{
             * parameter
             * .setUnlimitedNaturalDefaultValue(Integer.parseInt((String)
             * value)); }catch(NumberFormatException e){
             * System.out.println("NumberFormatException!"); } } else{ }
             */
        } else if (element instanceof Property) {
            Property property = (Property) element;
            if (property.getType() != null
                && !DefaultValueGeneralSection.validate(property.getType().getName(), (String) value))
                return;
            property.setDefault((String) value);

            /*
             * 
             * if(property.getType() == null){ } else if(property.getType()
             * instanceof Element && !(property.getType() instanceof
             * PrimitiveType)){ } else if
             * (property.getType().getName().equals("String")) {
             * property.setStringDefaultValue((String) value); } else if
             * (property.getType().getName().equals("Integer")){ try{
             * property.setIntegerDefaultValue(Integer.parseInt((String)
             * value)); }catch(NumberFormatException e){
             * System.out.println("NumberFormatException!"); } } else if
             * (property.getType().getName().equals("Boolean")){ if(
             * (String)value == "true"){ property.setBooleanDefaultValue(true);
             * } else{ property.setBooleanDefaultValue(false); } } else if
             * (property.getType().getName().equals("UnlimitedNatural")){ try{
             * property
             * .setUnlimitedNaturalDefaultValue(Integer.parseInt((String)
             * value)); }catch(NumberFormatException e){
             * System.out.println("NumberFormatException!"); } } else{ }
             */
        }
    }

}
