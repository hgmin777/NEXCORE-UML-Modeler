/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.composite.LibraryComposite;
import nexcore.tool.uml.ui.project.editor.page.DetailsPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설 명 : DetailsModelLibrarySection</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class DetailsModelLibrarySection extends AbstractSection {

    /** 모델 인스턴스 */
    private Model model;

    /** 모델 라이브러리 컴포짓 */
    private LibraryComposite libraryComposite;

    /**
     * 생성자
     * 
     * @param detailsPage
     * @param parent
     */
    public DetailsModelLibrarySection(DetailsPage detailsPage, Composite parent) {
        super(detailsPage, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        section.setText(UMLMessage.LABEL_MODEL_LIBRARY);
        section.setDescription(UMLMessage.MESSAGE_MODEL_LIBRARY_OVERVIEW);

        TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
        td.grabHorizontal = true;

        Composite client = toolkit.createComposite(section);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = toolkit.getBorderStyle() != SWT.NULL ? 0 : 2;
        client.setLayout(layout);

        Composite composite = toolkit.createComposite(client);
        GridLayout compositeLayout = new GridLayout();
        compositeLayout.numColumns = 2;
        composite.setLayout(compositeLayout);

        initializeSection();

        createLibraryControl(toolkit, composite);

        section.setClient(client);
    }

    /**
     * 모델 라이브러리 컨트롤러 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createLibraryControl(FormToolkit toolkit, Composite composite) {
        // libraryComposite = new LibraryComposite(composite, model);
        libraryComposite = new LibraryComposite(this, composite, model);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;

        libraryComposite.setLayoutData(gridData);
    }

    /**
     * 섹션 초기화 void
     */
    private void initializeSection() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();
    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    @Override
    public void commit(boolean onSave) {
        if (onSave) {
//            CompoundCommand commands = new CompoundCommand();
//            RecordingCommand command = null;
//
//            for (org.eclipse.uml2.uml.Package importedPackage : model.getImportedPackages()) {
//                command = new HandleImportedPackageCommand(DomainRegistry.getEditingDomain(),
//                    model,
//                    importedPackage,
//                    false);
//
//                commands.append(command);
//            }
//
//            for (org.eclipse.uml2.uml.Package importingPackage : libraryComposite.getApplyingLibraryList()) {
//                command = new HandleImportedPackageCommand(DomainRegistry.getEditingDomain(),
//                    model,
//                    importingPackage,
//                    true);
//
//                commands.append(command);
//            }
//
//            DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);

//            libraryComposite.getLibraryTableViewer().setInput(libraryComposite.getApplyingLibraryList().toArray());
            libraryComposite.getLibraryTableViewer().refresh();

            super.commit(onSave);

//            model.eResource().setModified(false);
        }
    }

}
