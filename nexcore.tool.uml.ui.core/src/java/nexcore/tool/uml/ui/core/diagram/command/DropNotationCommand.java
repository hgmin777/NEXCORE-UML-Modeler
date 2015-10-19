/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DropNotationCommand</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DropNotationCommand extends Command {

    /** child */
    protected AbstractNode child;

    /** parent */
    protected Diagram diagram;

    /** index */
    protected int index = -1;

    /**
     * @param label
     */
    public DropNotationCommand(String label) {
        super(label);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (child instanceof NotationNode) {
            ViewModelUtil.setModelInfo((NotationNode) child);
        }

        if (index < 0)
            diagram.getNodeList().add(child);
        else
            diagram.getNodeList().add(index, child);
        
        
        
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
//        UseCase usecase = null;
//        Class childClass = null;
//        MetaContent childMeta = null;
//        MetaContent parentMeta = null;
//        
//        EObject parentElement = diagram.getParent();
//        EObject childElement = child.getUmlModel();
//        // 드랍대상이 클래스이고 다이어그램의 부모가 유스케이스 이면 연계 시작.
////        if( !(parentElement instanceof UseCase) || !(childElement instanceof Class) ) {
////            return;
////        }
//        
//        if (!(childElement instanceof Class)) {
//            return;
//        }
//        EObject relatedUsecase = null;
//        if (parentElement != null && !(parentElement instanceof UseCase)) {
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
//        String source = ProjectUtil.elementInstanceName(parentElement);
//        String target = ProjectUtil.elementInstanceName(childElement);
//        
//        boolean result = MessageDialog.openConfirm(UiCorePlugin.getShell(), UMLMessage.MESSAGE_DIALOG_TITLE, 
//                UMLMessage.getMessage(UMLMessage.MESSAGE_CREATE_NCP_RELATION, 
//                        ((NamedElement)parentElement).getName(), ((NamedElement)childElement).getName(), source, target));
//        
//        if( !result ) {
//            return;
//        }
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
//        IStatus status = MetaContentUtil.createRelation(relationMap);
//        
//        if( status.isMultiStatus() ) {
//            IStatus[] children = status.getChildren();
//            for( IStatus s : children ) {
//                if(status.getCode() != Status.OK) {
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(s.getMessage(), s.getException());
//                }
//            }
//        }
//        if(status.getCode() != Status.OK) {
//            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(status.getMessage(), status.getException());
//        }
//    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (index < 0)
            diagram.getNodeList().add(child);
        else
            diagram.getNodeList().add(index, child);
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        diagram.getNodeList().remove(child);
    }

    /**
     * @return Returns the child.
     */
    public AbstractNode getChild() {
        return child;
    }

    /**
     * @param child
     *            The child to set.
     */
    public void setChild(AbstractNode child) {
        this.child = child;
    }

    /**
     * @return Returns the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            The index to set.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return Returns the parent.
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * @param parent
     *            The parent to set.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
}
