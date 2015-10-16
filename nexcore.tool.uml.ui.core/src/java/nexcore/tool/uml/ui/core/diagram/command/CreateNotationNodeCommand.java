/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : CreateNotationNodeCommand</li>
 * <li>작성일 : 2009. 11. 13.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateNotationNodeCommand extends Command {

    /** WIDTH_MINIMUM_SIZE */
    protected static final int WIDTH_MINIMUM_SIZE = 50;

    /** HEIGHT_MINIMUM_SIZE */
    protected static final int HEIGHT_MINIMUM_SIZE = 50;

    /** request */
    protected CreateRequest request;

    /** parentNodeModel */
    protected AbstractNode parentNodeModel;

    /** notationNode */
    protected NotationNode notationNode;

    /** location */
    protected Point location;

    /** editPart */
    protected EditPart editPart;

    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     */
    public CreateNotationNodeCommand(EditDomain editDomain, EditPart editPart, CreateRequest request,
    Point location) {
        // this.diagramEditDomain = (DiagramEditDomain) editDomain;
        this.editPart = editPart;
        this.parentNodeModel = (AbstractNode) editPart.getModel();
        this.request = request;
        this.location = location;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        UiUtil.eraseFeedback(editPart);
        
        notationNode = (NotationNode) request.getNewObject();

        setLocationAndSize();
        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;

        if (umlModel instanceof NamedElement) {
            if (umlModel instanceof Lifeline) {
                parentElement = UMLManager.getParent(this.parentNodeModel);

            } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
                && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
                parentElement = UMLManager.getParentPackage(this.parentNodeModel);
                ((NamedElement) umlModel).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
                    ((NamedElement) umlModel).getName()));
            }

            notationNode.setName(((NamedElement) umlModel).getName());
        }

        ViewModelUtil.setModelInfo(notationNode);
        redo();

        ((AbstractDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
            .getEditDomain()
            .setDefaultTool(new NewSelectionTool());
        
        
        
//        try {
//            createNCPInfo();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            ALMLogger.getLog("nexcore.tool.uml.ui.core").error(e.getMessage(), e);
//        } 
    }
    
    /**
     * 
     *   void
     * @throws Exception 
     */
//    private void createNCPInfo() throws Throwable{
//        
//        UseCase usecase = null;
//        Class childClass = null;
//        MetaContent childMeta = null;
//        MetaContent parentMeta = null;
//        
//        EObject parentElement = parentNodeModel.getParent();
//        EObject childElement = notationNode.getUmlModel();
//        
//        
//        if (!(childElement instanceof Class)) {
//            return;
//        }
//        EObject relatedUsecase = null;
//        if (!(parentElement instanceof UseCase)) {
//            // 선택된 모델의 부모요소가 유스케이스모델에서 생성된 Collaboration, Interaction, SequenceDiagram 인경우인지를
//            // 체크하는 로직.
//            relatedUsecase = RelationManager.getInstance().findRelatedElement(parentElement);
//        }
//        if( relatedUsecase != null) {
//            parentElement = relatedUsecase;
//        } else {
//            if (!(parentElement instanceof UseCase) || !(childElement instanceof Class)) {
//                return;
//            }
//        }
//
//        ClassNameDialog classNameDialog = new ClassNameDialog(null);
//        classNameDialog.setClassName( ((NamedElement)childElement).getName() );
//        classNameDialog.open();
//        String className = classNameDialog.getClassName();
//        
////        String message = String.format(UMLMessage.MESSAGE_CREATE_RELATION, ((NamedElement)parentElement).getName(), className);
////        boolean result = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), UMLMessage.LABEL_CREATE_RELATION, message); //$NON-NLS-1$
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
//            ((NamedElement)childElement).setName(className);
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
////            List<MetaContent> metaContentList = MetaContentUtil.getMetaContent( EcoreUtil.getURI(childClass).fragment() );
////            if( null == metaContentList || metaContentList.isEmpty() ) {
////                boolean isCreateAttribute = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.IS_CREATE_ATTRIBUTE);
////                childMeta = MetaContentUtil.createMetaContent(childClass, IUMLIdentifier.RESOURCECODE_UML_ANALYSIS_CLASS, isCreateAttribute);
////            } else {
////                childMeta = metaContentList.get(0);
////            }
//            
//            // 다이얼로그에서 작성된 이름으로 클래스 이름 변경
//            ((NamedElement) childElement).setName(className);
//        }
//        
//        // 드랍당할 유스케이스의 메타 정보 얻기
//        
//        if( parentElement instanceof UseCase) {
//            usecase = (UseCase) parentElement;
////            List<MetaContent> metaContentList = MetaContentUtil.getMetaContent( EcoreUtil.getURI(usecase).fragment() );
////            if( null == metaContentList || metaContentList.isEmpty() ) {
////                parentMeta = MetaContentUtil.createMetaContent(usecase, IUMLIdentifier.RESOURCECODE_UML_USECASE, false);
////            } else {
////                parentMeta = metaContentList.get(0);
////            }
//        }
//        
//        if (parentMeta != null) {
////            Map<MetaContent, MetaContent> relationMap = new HashMap<MetaContent, MetaContent>();
////            relationMap.put(childMeta, parentMeta);
//            
////            Map<MetaContent, List<MetaContent>> relationMap = new HashMap<MetaContent, List<MetaContent>>();
////            relationMap.put(childMeta, Arrays.asList(parentMeta));
////            IStatus status = MetaContentUtil.createRelation(relationMap);
////
////            if (status.isMultiStatus()) {
////                IStatus[] children = status.getChildren();
////                for (IStatus s : children) {
////                    if (status.getCode() != Status.OK) {
////                        ALMLogger.getLog("nexcore.tool.uml.ui.core").error(s.getMessage(), s.getException());
////                    }
////                }
////            }
////            if (status.getCode() != Status.OK) {
////                ALMLogger.getLog("nexcore.tool.uml.ui.core").error(status.getMessage(), status.getException());
////            }
//        }
//    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;

        if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            parentElement = UMLManager.getParentPackage(this.parentNodeModel);
            if (parentElement instanceof org.eclipse.uml2.uml.Component) {
                ((org.eclipse.uml2.uml.Component) parentElement).getPackagedElements()
                    .add((PackageableElement) umlModel);
            } else {
                ((org.eclipse.uml2.uml.Package) parentElement).getPackagedElements().add((PackageableElement) umlModel);
            }
        }
        if( parentElement != null ) {
            notationNode.setParent(parentElement);
        }
        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Element umlModel = notationNode.getUmlModel();

        if (umlModel instanceof Lifeline) {
            Interaction interaction = (Interaction) notationNode.getParent();
            interaction.getLifelines().remove((Lifeline) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            org.eclipse.uml2.uml.PackageableElement parentElement = (org.eclipse.uml2.uml.PackageableElement) notationNode.getParent();
            if (parentElement instanceof org.eclipse.uml2.uml.Component) {
                ((org.eclipse.uml2.uml.Component) parentElement).getPackagedElements().remove(umlModel);
            } else {
                ((org.eclipse.uml2.uml.Package) parentElement).getPackagedElements().remove(umlModel);
            }
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().remove(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().remove(notationNode);
        }
    }

    /**
     * 노드의 위치와 사이즈 설정 void
     */
    private void setLocationAndSize() {
        notationNode.setX(location.x);
        notationNode.setY(location.y);
        if (NodeType.LIFELINE.equals(notationNode.getNodeType())) {
            notationNode.setY(10);
        }
        Dimension size = request.getSize();

        if (size != null) {
            if (size.width > WIDTH_MINIMUM_SIZE) {
                notationNode.setWidth(size.width);
            } else {
                notationNode.setWidth(WIDTH_MINIMUM_SIZE);
            }
            if (size.height > HEIGHT_MINIMUM_SIZE) {
                notationNode.setHeight(size.height);
            } else {
                notationNode.setHeight(HEIGHT_MINIMUM_SIZE);
            }
        } else {
            Dimension dimension = FigureConstant.getFigureDimension(notationNode.getNodeType());
            notationNode.setWidth(dimension.width);
            notationNode.setHeight(dimension.height);
        }
    }

}
