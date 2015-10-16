/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : MessageDocumentSection</li>
 * <li>작성일 : 2010. 4. 15.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class MessageDocumentSection extends DocumentSection {

    /**
     * 입력된 모델을 Message로 리턴함.
     * 
     * @return Message
     */
    private Message getData() {
        return (Message) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {

        MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) getData().getSendEvent();
        SendOperationEvent soe = (SendOperationEvent) mos.getEvent();

        if (mos != null && soe != null && soe.getOperation() != null) {
            EList<Comment> list = soe.getOperation().getOwnedComments();
            if (!list.isEmpty()) {
                return list.get(0).getBody();
            }
        }
        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.DocumentSection#addComment(java.lang.String)
     */
    protected void addComment(final String value) {

        MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) getData().getSendEvent();
        SendOperationEvent soe = (SendOperationEvent) mos.getEvent();

        if (mos != null && soe != null) {
            if (soe.getOperation() == null) {
                MessageDialog.openWarning(UiCorePlugin.getShell(),
                    UMLMessage.UML_MODELER,
                    UMLMessage.getMessage(UMLMessage.MESSAGE_NO_ASSIGNED_OPERATION));
                return;
            } else {
                comment = UMLFactory.eINSTANCE.createComment();
                comment.setBody(value);

                soe.getOperation().getOwnedComments().clear();
                soe.getOperation().getOwnedComments().add(comment);
            }
        }

    }

}
