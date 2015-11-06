/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.command.HandleCommentCommand;
import nexcore.tool.uml.ui.project.editor.page.OverviewPage;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설 명 : OverviewDocumentSection</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class OverviewDocumentSection extends AbstractSection {

    /** 모델 인스턴스 */
    private Model model;

    /** documentText */
    private Text documentText;

    /**
     * 생성자
     * 
     * @param overviewPage
     * @param parent
     */
    public OverviewDocumentSection(OverviewPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        section.setText(UMLMessage.LABEL_DOCUMENT_INFORMATION);
        section.setDescription(UMLMessage.MESSAGE_DOCUMENT_OVERVIEW);

        TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
        td.grabHorizontal = true;

        Composite client = toolkit.createComposite(section);
        client.setLayout(new GridLayout(1, false));

        createTextControl(toolkit, client);

        initializeSection();

        section.setClient(client);
    }

    /**
     * 문서 텍스트 컨트롤 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createTextControl(FormToolkit toolkit, Composite composite) {
        documentText = toolkit.createText(composite, UICoreConstant.EMPTY_STRING, SWT.BORDER | SWT.V_SCROLL);
        GridData documentTextGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        documentTextGridData.heightHint = 200;
        documentText.setLayoutData(documentTextGridData);

        documentText.addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                
                String oldText = null;
                for (Comment comment : model.getOwnedComments()) {
                    oldText = comment.getBody();
                }
                
                String newText = documentText.getText();
                
                if (newText.equals(oldText)) {
                    refresh();
                    getPage().getManagedForm().dirtyStateChanged();
                    return;
                }
                
                RecordingCommand command = new HandleCommentCommand(DomainRegistry.getEditingDomain(),
                    model,
                    documentText.getText(),
                    true);

                DomainUtil.executeCommand(command);
            }
        });
    }

    /**
     * 섹션 초기화
     * 
     * void
     */
    private void initializeSection() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();

        for (Comment comment : model.getOwnedComments()) {
            documentText.setText(comment.getBody());
        }
    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    public void commit(boolean onSave) {
        if (onSave) {
//            RecordingCommand command = new HandleCommentCommand(DomainRegistry.getEditingDomain(),
//                model,
//                documentText.getText(),
//                true);
//
//            DomainUtil.executeCommand(command);

            super.commit(onSave);
        }
    }

}
