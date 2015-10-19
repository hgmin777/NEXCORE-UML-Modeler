/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.OCLExpressionUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.figure.NoteFigure;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DirectEditCommand</li>
 * <li>작성일 : 2009. 12. 4.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DirectEditCommand extends Command {
    /** oldText, newText */
    private String oldText, newText;

    /** object */
    private Object umlElement = null;

    /** notationNode */
    private Label label;

    /** editpart */
    private EditPart editpart;

    /** text */
    private TextFigure text;

    /** note */
    private NoteFigure note;

    /** EMPTY_TEXT */
    private String EMPTY_TEXT = "";

    /** viewModel for Note or Text */
    private AbstractNode viewModel = null;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        
        if (UICoreConstant.EMPTY_STRING.equals(newText)) {
            if( !(umlElement instanceof Relationship) ) {
                MessageDialog.openInformation(null, UMLMessage.LABEL_WARNING, UMLMessage.MESSAGE_NO_NAME_ELEMENT_WARNING);
                return;
            }
        }

        if (viewModel == null) {
            oldText = label.getText();
        } else if (viewModel instanceof LabelNode) {
            oldText = label.getText();
        } else {
            if (viewModel.getNodeType().equals(NodeType.NOTE)) {
                oldText = note.getName();
            } else if (viewModel.getNodeType().equals(NodeType.TEXT)) {
                oldText = text.getName();
            } else if (viewModel.getNodeType().equals(NodeType.STEREOTYPE)) {
                oldText = label.getText();
            }
        }

        oldText = oldText.replaceAll(UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET, "");
        oldText = oldText.replaceAll(UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET, "");
        redo();

        
//        try {
//            createNCPInfo();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            // TODO 에러창
//        }
    }
    
    /**
     * 
     *   void
     * @throws Exception 
     */
//    private void createNCPInfo() throws Throwable {
//        
//        UseCase usecase = null;
//        Class childClass = null;
//        MetaContent childMeta = null;
//        MetaContent parentMeta = null;
//        
//        Object parentElement = ((Diagram)ProjectUtil.getDiagramEditPart().getModel()).getParent();
//        Object childElement = umlElement;
//        
//        
//        if (!(childElement instanceof Class)) {
//            return;
//        }
//        EObject relatedUsecase = null;
//        if (!(parentElement instanceof UseCase)) {
//            // 선택된 모델의 부모요소가 유스케이스모델에서 생성된 Collaboration, Interaction, SequenceDiagram 인경우인지를
//            // 체크하는 로직.
//            relatedUsecase = RelationManager.getInstance().findRelatedElement((EObject) parentElement);
//        }
//        if( relatedUsecase != null) {
//            parentElement = relatedUsecase;
//        } else {
//            if (!(parentElement instanceof UseCase) || !(childElement instanceof Class)) {
//                return;
//            }
//        }
//
////        ClassNameDialog classNameDialog = new ClassNameDialog(null);
////        classNameDialog.setClassName( ((NamedElement)childElement).getName() );
////        classNameDialog.open();
////        String className = classNameDialog.getClassName();
//        
////        String message = String.format(UMLMessage.MESSAGE_CREATE_RELATION, ((NamedElement)parentElement).getName(), ((NamedElement)childElement).getName());
////        boolean result = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), UMLMessage.LABEL_CREATE_RELATION, message);
//        
//        String source = ProjectUtil.elementInstanceName(parentElement);
//        String target = ProjectUtil.elementInstanceName(childElement);
//        
//        boolean result = MessageDialog.openConfirm(UiCorePlugin.getShell(), UMLMessage.MESSAGE_DIALOG_TITLE, 
//        		UMLMessage.getMessage(UMLMessage.MESSAGE_CREATE_NCP_RELATION, 
//        				((NamedElement)parentElement).getName(), ((NamedElement)childElement).getName(), source, target));
//        
//        if( !result ) {
//            // 다이얼로그에서 작성된 이름으로 클래스 이름 변경
//            ((NamedElement)childElement).setName(((NamedElement)childElement).getName());
//            return;
//        }
//        
//        // 드랍대상이 클래스이고 다이어그램의 부모가 유스케이스 이면 연계 시작.
//        
//        
//        // 드랍하는 클래스의 메타 정보 얻기
//        if( childElement instanceof Class ) {
//            childClass = (Class) childElement;
//        }
//        if( null != childClass ) {
//            List<MetaContent> metaContentList = MetaContentUtil.getMetaContent( EcoreUtil.getURI(childClass).fragment() );
//            if( null == metaContentList || metaContentList.isEmpty() ) {
//                boolean isCreateAttribute = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.IS_CREATE_ATTRIBUTE);
//                childMeta = MetaContentUtil.createMetaContent(childClass, IUMLIdentifier.RESOURCECODE_UML_ANALYSIS_CLASS, isCreateAttribute);
//            } else {
//                childMeta = metaContentList.get(0);
//            }
//        }
//        
//        // 드랍당할 유스케이스의 메타 정보 얻기
//        usecase = (UseCase) parentElement;
//        List<MetaContent> metaContentList = MetaContentUtil.getMetaContent( EcoreUtil.getURI(usecase).fragment() );
//        if( null == metaContentList || metaContentList.isEmpty() ) {
//            parentMeta = MetaContentUtil.createMetaContent(usecase, IUMLIdentifier.RESOURCECODE_UML_USECASE, false);
//        } else {
//            parentMeta = metaContentList.get(0);
//        }
//        
////        Map<MetaContent, MetaContent> relationMap = new HashMap<MetaContent, MetaContent>();
////        relationMap.put(childMeta, parentMeta);
//        Map<MetaContent, List<MetaContent>> relationMap = new HashMap<MetaContent, List<MetaContent>>();
//        relationMap.put(childMeta, Arrays.asList(parentMeta));
//        
//        IStatus status = MetaContentUtil.createRelation(relationMap);
//        
//        if( status.isMultiStatus() ) {
//            IStatus[] children = status.getChildren();
//            for( IStatus s : children ) {
//                if(status.getCode() != Status.OK) {
//                    ALMLogger.getLog("nexcore.tool.uml.ui.core").error(s.getMessage(), s.getException());
//                }
//            }
//        }
//        if(status.getCode() != Status.OK) {
//            ALMLogger.getLog("nexcore.tool.uml.ui.core").error(status.getMessage(), status.getException());
//        }
//        
//        // 다이얼로그에서 작성된 이름으로 클래스 이름 변경
//        ((NamedElement)childElement).setName(((NamedElement)childElement).getName());
//    }
    
    

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {

        // if(viewModel.getNodeType().equals(NodeType.ATTRIBUTE) ||
        // viewModel.getNodeType().equals(NodeType.OPERATION)){
        // setElementInfo();
        // }

        if (viewModel == null) {
            if (umlElement instanceof NamedElement) {
                if(umlElement instanceof InteractionConstraint) {
                    InteractionConstraint constraint = (InteractionConstraint) umlElement;
                    OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
                    expression.getBodies().set(0, newText);
                } else {
                    ((NamedElement) umlElement).setName(newText);
                    label.setText(newText);
                }
            }
        } else {
            if (viewModel instanceof LabelNode) {
                if (((LabelNode) viewModel).getType().equals(LabelType.STEREOTYPE)) {
                    setKeyword((Element) umlElement, newText);
                    label.setText(ProjectUtil.getStereotypeLabel((Element) umlElement));
                } else {
                    ((NamedElement) umlElement).setName(newText);
                    viewModel.setName(newText);
                    label.setText(newText);
                }
            } else if (viewModel instanceof NotationNode) {
                if (((NotationNode) viewModel).getNodeType().equals(NodeType.STEREOTYPE)) {
                    setKeyword((Element) umlElement, newText);
                    label.setText(ProjectUtil.getStereotypeLabel((Element) umlElement));
                } else if (((NotationNode) viewModel).getNodeType().equals(NodeType.NOTE)) {
                    ((NotationNode) viewModel).setName(newText);
                    note.setName(newText);
                    viewModel.setName(newText);
                } else if (((NotationNode) viewModel).getNodeType().equals(NodeType.TEXT)) {
                    ((NotationNode) viewModel).setName(newText);
                    text.setName(newText);
                    viewModel.setName(newText);
                } 
            }
        }
    }

    /**
     * setElementInfo
     *   void
     */
    private void setElementInfo() {

        if (viewModel.getNodeType().equals(NodeType.ATTRIBUTE)) {
            Property property = (Property) viewModel.getUmlModel();
            if (newText.contains("-")) {
                property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
            } else if (newText.contains("+")) {
                property.setVisibility(VisibilityKind.PUBLIC_LITERAL);
            } else if (newText.contains("~")) {
                property.setVisibility(VisibilityKind.PACKAGE_LITERAL);
            } else if (newText.contains("#")) {
                property.setVisibility(VisibilityKind.PROTECTED_LITERAL);
            }

        } else if (viewModel.getNodeType().equals(NodeType.ATTRIBUTE)) {

        }
    }

    /**
     * 
     * getKeyword
     * 
     * @return String
     */
    protected String getKeyword(Element element) {

        String keywordString;
        if (element.getKeywords().isEmpty()) {
            return keywordString = EMPTY_TEXT;
        }
        EList<String> list = element.getKeywords();
        if (0 == list.size()) {
            return keywordString = EMPTY_TEXT;
        }
        List<String> keywordList = new ArrayList<String>();
        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            keywordList.add(iter.next());
        }
        keywordString = OCLExpressionUtil.parseKeywordListToString(keywordList);
        return keywordString;
    }

    /**
     * setKeyword
     *  
     * @param element
     * @param value void
     */
    protected void setKeyword(Element element, String value) {
        if (this.oldText.equals(value))
            return;

        List<String> keywordList = OCLExpressionUtil.parseStringToKeywordList(this.oldText);

        for (String keyword : keywordList) {
            element.removeKeyword(keyword.trim());
        }

        if (null == value) {
            this.oldText = EMPTY_TEXT;
            return;
        } else {
            this.oldText = value;
        }
        keywordList = OCLExpressionUtil.parseStringToKeywordList(this.oldText);

        for (final String keyword : keywordList) {
            element.addKeyword(keyword.trim());
        }
    }

    /**
     * setLabelFigure
     *  
     * @param object void
     */
    public void setLabelFigure(Object object) {
        label = (Label) object;
    }

    /**
     * setText
     *  
     * @param text void
     */
    public void setText(String text) {
        newText = text;
    }

    /**
     * setUmlModel
     *  
     * @param umlModel void
     */
    public void setUmlModel(Object umlModel) {
        umlElement = umlModel;
    }

    /**
     * setEditPart
     *  
     * @param host void
     */
    public void setEditPart(EditPart host) {
        this.editpart = host;
    }

    /**
     * setNoteFigure
     *  
     * @param hostFigure void
     */
    public void setNoteFigure(IFigure hostFigure) {
        this.note = (NoteFigure) hostFigure;
    }

    /**
     * setTextFigure
     *  
     * @param hostFigure void
     */
    public void setTextFigure(IFigure hostFigure) {
        this.text = (TextFigure) hostFigure;
    }

    /**
     * setViewModel
     *  
     * @param model void
     */
    public void setViewModel(AbstractNode model) {
        this.viewModel = model;
    }
}

