/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.editor.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.command</li>
 * <li>설 명 : HandleCommentCommand</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleCommentCommand extends RecordingCommand {

    /** 모델 요소 */
    private Model element;

    /** 삭제할 Comment */
    private Comment deletingComment;

    /** Comment 문자열 */
    private String strComment;

    /** Comment 적용 여부 */
    private boolean applyComment;

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param strComment
     * @param applyComment
     */
    public HandleCommentCommand(TransactionalEditingDomain domain, Model element, String strComment,
    boolean applyComment) {
        super(domain);

        this.element = element;
        this.strComment = strComment;
        this.applyComment = applyComment;
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param element
     * @param deletingComment
     * @param applyComment
     */
    public HandleCommentCommand(TransactionalEditingDomain domain, Model element, Comment deletingComment,
    boolean applyComment) {
        super(domain);

        this.element = element;
        this.deletingComment = deletingComment;
        this.applyComment = applyComment;
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (applyComment) {
            element.getOwnedComments().clear();

            Comment comment = UMLFactory.eINSTANCE.createComment();
            comment.setBody(strComment);

            element.getOwnedComments().add(comment);
        } else {
            for (Comment comment : element.getOwnedComments()) {
                if (comment.equals(deletingComment)) {
                    element.getOwnedComments().remove(comment);
                }
            }
        }
    }

}
