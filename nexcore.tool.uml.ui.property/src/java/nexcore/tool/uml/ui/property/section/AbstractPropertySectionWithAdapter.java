/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : AbstractPropertySectionWithAdapter</li>
 * <li>작성일 : 2010. 1. 25.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public abstract class AbstractPropertySectionWithAdapter extends AbstractPropertySection implements Adapter {

    /** parent Composite */
    protected Composite parent;

    /** sectionComposite */
    protected Composite sectionComposite;

    /** Selected Model */
    private Element selectedModel;

    /**
     * @return the selectedModel
     */
    public Element getSelectedModel() {
        return selectedModel;
    }

    /**
     * @param selectedModel
     *            the selectedModel to set
     */
    public void setSelectedModel(Element selectedModel) {
        this.selectedModel = selectedModel;
        this.activate();
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return null;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return false;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {

    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {

        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.SET:
            case Notification.UNSET:
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Display.getDefault().syncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        refresh();
                    }
                });
                break;
            default:
                return;
        }
    }

    /**
     * 모델에 어답터를 붙임 void
     */
    protected void activate() {
        if (null == this.selectedModel) {
            return;
        }
        if (!this.selectedModel.eAdapters().contains(this)) {
            this.selectedModel.eAdapters().add(this);
        }
        for (Stereotype stereotype : ((Element) selectedModel).getAppliedStereotypes()) {
            stereotype.eAdapters().add(this);
        }
        for (EAnnotation annotation : ((EModelElement) selectedModel).getEAnnotations()) {
            annotation.eAdapters().add(this);
            for (EObject eobject : annotation.getReferences()) {
                eobject.eAdapters().add(this);
            }
            if (annotation instanceof UseCaseDetail) {
                for (FlowObject flowObject : ((UseCaseDetail) annotation).getBasicFlowList()) {
                    flowObject.eAdapters().add(this);
                }
                for (FlowObject flowObject : ((UseCaseDetail) annotation).getSubFlowList()) {
                    flowObject.eAdapters().add(this);
                }
                for (FlowObject flowObject : ((UseCaseDetail) annotation).getExceptionFlowList()) {
                    flowObject.eAdapters().add(this);
                }
            }
        }

        if (selectedModel instanceof Message) {
            for (Element element : ((Message) selectedModel).getOwner().allOwnedElements()) {
                element.eAdapters().add(this);

            }
        }
        if (selectedModel instanceof Operation) {
            for (Parameter para : ((Operation) selectedModel).getOwnedParameters()) {
                para.eAdapters().add(this);
            }

        }

        if (selectedModel instanceof Class) {
            for (Property property : ((Class) selectedModel).getAllAttributes()) {
                property.eAdapters().add(this);
            }
            for (Operation operation : ((Class) selectedModel).getOwnedOperations()) {
                operation.eAdapters().add(this);
            }
        }

        if (selectedModel instanceof Interface) {
            for (Property property : ((Interface) selectedModel).getAllAttributes()) {
                property.eAdapters().add(this);
            }
            for (Operation operation : ((Interface) selectedModel).getOwnedOperations()) {
                operation.eAdapters().add(this);
            }
        }
        
        if (selectedModel instanceof InteractionConstraint) {
            InteractionConstraint constraint = (InteractionConstraint) selectedModel;
            OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
            expression.eAdapters().add(this);
        }
    }

    /**
     * 모델에서 어답터를 제거함 void
     */
    protected void deactivate() {
        if (null == this.selectedModel) {
            return;
        }
        if (this.selectedModel.eAdapters().contains(this)) {
            this.selectedModel.eAdapters().remove(this);
        }
        for (Stereotype stereotype : ((Element) selectedModel).getAppliedStereotypes()) {
            stereotype.eAdapters().remove(this);
        }
        for (EAnnotation annotation : ((EModelElement) selectedModel).getEAnnotations()) {
            annotation.eAdapters().remove(this);
        }
        if (selectedModel instanceof InteractionConstraint) {
            InteractionConstraint constraint = (InteractionConstraint) selectedModel;
            OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
            expression.eAdapters().remove(this);
        }
    }

    /**
     * 자식컨트롤만의 컨트롤 설정 void
     */
    protected abstract void createMainComposite();

    /**
     * 위젯에 달린 리스너를 제거함 void
     */
    protected abstract void unsetListener();

    /**
     * 위젯에 값을 세팅함 void
     */
    protected abstract void refreshChildren();

    /**
     * 위젯에 리스너를 붙임 void
     */
    protected abstract void setListener();
}
