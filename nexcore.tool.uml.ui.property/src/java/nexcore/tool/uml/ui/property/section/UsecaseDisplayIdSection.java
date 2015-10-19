/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : UsecaseDisplayIdSection</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UsecaseDisplayIdSection extends TemplateTextSection {

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
        Element element = this.getData();
        EAnnotation eAnnotation = element.getEAnnotation(ManagerConstant.USECASE_DISPLAY_ID_EANNOTATION_SOURCE_NAME);
        UseCaseDisplayId displayId = null;

        if (eAnnotation != null) {
            displayId = (UseCaseDisplayId) eAnnotation;
        } else {
            return "";
//            displayId = UsecasedisplayIdFactory.eINSTANCE.createUseCaseDisplayId();
//            displayId.setSource(ManagerConstant.USECASE_DISPLAY_ID_EANNOTATION_SOURCE_NAME);
//            setElementInfo(element, displayId);
        }

        String usecaseId = displayId.getDisplayId();
        return usecaseId;
    }

    /**
     * 
     * 
     * @param element
     * @param displayId
     *            void
     */
    private void setElementInfo(final Element element, final UseCaseDisplayId displayId) {

        
        Display.getDefault().asyncExec(new Runnable() {
            
            @Override
            public void run() {
                
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        
                        element.getEAnnotations().add(0, displayId);
                    }
                });
            }
        });
        
//        DomainUtil.run(new TransactionalAction() {
//            /**
//             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
//             */
//            @Override
//            public void doExecute() {
//                element.getEAnnotations().add(displayId);
//            }
//        });
        
        
//        DomainRegistry.getEditingDomain()
//            .getCommandStack()
//            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
//                @Override
//                protected void doExecute() {
//                    element.getEAnnotations().add(displayId);
//                }
//            });
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(final String value) {
        Element element = this.getData();
        EAnnotation eAnnotation = element.getEAnnotation(ManagerConstant.USECASE_DISPLAY_ID_EANNOTATION_SOURCE_NAME);
        UseCaseDisplayId displayId = null;

        if (eAnnotation != null) {
            displayId = (UseCaseDisplayId) eAnnotation;
            displayId.setDisplayId(value);
        } else {
            displayId = UsecasedisplayIdFactory.eINSTANCE.createUseCaseDisplayId();
            displayId.setSource(ManagerConstant.USECASE_DISPLAY_ID_EANNOTATION_SOURCE_NAME);
            displayId.setDisplayId(value);
            setElementInfo(element, displayId);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_ID);
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

}
