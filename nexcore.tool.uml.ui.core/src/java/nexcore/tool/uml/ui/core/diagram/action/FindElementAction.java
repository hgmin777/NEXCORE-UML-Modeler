/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.ReceiveOperationEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : FindElementAction</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class FindElementAction extends SelectionAction {

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        Object obj = this.getSelectedObjects().get(0);
        Object viewModel = null;
        Element uml = null;

        obj = getSelectedObjects().get(0);
        viewModel = ((EditPart) obj).getModel();
        if (viewModel instanceof AbstractView) {
            uml = ((AbstractView) viewModel).getUmlModel();
        }

        if (null == obj || null == viewModel || null == uml) {
            return false;
        }

        if (null == uml || DomainUtil.isProxy(uml)) {
            return false;
        }

        if (viewModel instanceof AbstractView && !(viewModel instanceof Diagram)) {

            if (viewModel instanceof AbstractNode) {

//                AbstractNode node = (AbstractNode) viewModel;
                if (viewModel instanceof Lifeline) {
                    Lifeline lifeline = (Lifeline) viewModel;
                    ConnectableElement connectable = lifeline.getRepresents();
                    if (connectable == null) {
                        return false;
                    }
                    org.eclipse.uml2.uml.Type type = connectable.getType();
                    if (type == null) {
                        return false;
                    }
                }

                if (NodeType.NOTE.equals(((AbstractNode) viewModel).getNodeType())
                    || NodeType.TEXT.equals(((AbstractNode) viewModel).getNodeType())
                    || nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals(((AbstractNode) viewModel).getNodeType())) {
                    return false;
                }
            } else if (viewModel instanceof AbstractConnection) {
                if (nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals(((AbstractConnection) viewModel).getRelationType())) {
                    return false;
                }
            }
        }

        int count = this.getSelectedObjects().size();
        if (0 == count) {
            return false;
        }

        AbstractView abstractView;
        if (viewModel instanceof AbstractView) {
            abstractView = (AbstractView) viewModel;
            if (abstractView.getUmlModel() instanceof Element) {
                return true;
            }
        } else if (viewModel instanceof EObject) {
            return true;
        }

        return false;
    }

    /** Find Element Action ID */
    public static final String FIND_ELEMENT_ACTIONID = "FindElement";

    /**
     * @param part
     */
    public FindElementAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_FIND_ELEMENT_ACTION));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(FIND_ELEMENT_ACTIONID);
        setText(UMLMessage.LABEL_FIND_ELEMENT);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        Object object = getSelectedObjects().get(0);

        if (!(object instanceof EditPart))
            return;
        Object element = ((EditPart) object).getModel();
        if (!(element instanceof Element)) {
            return;
        }

        EObject eobject = null;
        if (element instanceof AbstractView) {
            eobject = ((AbstractView) element).getUmlModel();
        } else if (element instanceof EObject) {
            eobject = (EObject) element;
        }

        if (eobject == null) {
            return;
        }

        if (eobject instanceof Lifeline) {
            Lifeline lifeline = (Lifeline) eobject;
            ConnectableElement connectable = lifeline.getRepresents();
            if (connectable == null) {
                return;
            }
            org.eclipse.uml2.uml.Type type = connectable.getType();
            if (type == null) {
                return;
            }
            eobject = type;
        } else if (eobject instanceof Message) {
            Message message = (Message) eobject;
            MessageOccurrenceSpecification spec = (MessageOccurrenceSpecification) message.getReceiveEvent();
            if (null == spec) {
                return;
            }
            ReceiveOperationEvent event = (ReceiveOperationEvent) spec.getEvent();
            if (null == event) {
                return;
            }
            eobject = event.getOperation();
        }

        ProjectUtil.findElement(eobject);

    }

}
