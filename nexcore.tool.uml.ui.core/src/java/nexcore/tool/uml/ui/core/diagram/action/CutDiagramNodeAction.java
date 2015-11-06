/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : CutDiagramNodeAction</li>
 * <li>작성일 : 2011. 1. 27.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class CutDiagramNodeAction extends SelectionAction {

    /** list **/
    private List<Object> list;

    /** diagramEditPart **/
    private EditPart diagramEditPart = null;

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        int count = this.getSelectedObjects().size();
        if (0 == count) {
            return false;
        } else if (1 == count) {
            for (Object obj : this.getSelectedObjects()) {
                if (!(obj instanceof AbstractNotationNodeEditPart)) {
                    return false;
                }
            }
        }
        
        Object obj = this.getSelectedObjects().get(0);
        if(obj instanceof EditPart){
            Object model = ((EditPart)obj).getModel();
            if(model instanceof AbstractView){
                if( model instanceof AbstractNode ) {
                    if( NodeType.NOTE.equals( ((AbstractNode)model).getNodeType()) ||  NodeType.TEXT.equals( ((AbstractNode)model).getNodeType()) ||  nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals( ((AbstractNode)model).getNodeType())) {
                        return false;
                    }
                } else if( model instanceof AbstractConnection ) {
                    if(nexcore.tool.uml.model.umldiagram.RelationType.ATTACHEMENT.equals( ((AbstractConnection)model).getRelationType())) {
                        return false;
                    }
                }
                if( DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return false;
                }
            }
        }
        
        return true;
    }

    /** Cut Diagram Node Action ID */
    public static final String CUT_DIAGRAMNODE_ACTIONID = "Cut";

    /**
     * @param part
     */
    public CutDiagramNodeAction(IWorkbenchPart part) {
        super(part);
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_CUT_ACTION));
    }

    /**
     * @param part
     * @param messageFlag
     *            확인 메세지박스를 보여줄 것인지 결정하는 플래그.
     */
    public CutDiagramNodeAction(IWorkbenchPart part, boolean messageFlag) {
        this(part);
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
        setId(CUT_DIAGRAMNODE_ACTIONID);
        setText(UMLMessage.LABEL_CUT);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        EditPart editPart = null;
        DiagramCutCommand cutCommand;

        list = new ArrayList<Object>();
//        List<EditPart> viewList = new ArrayList<EditPart>();

        if (0 == getSelectedObjects().size()) {
            return;
        }

        this.list.add(CUT_DIAGRAMNODE_ACTIONID);
        for (Object selectedObj : getSelectedObjects()) {
            if (selectedObj instanceof EditPart) {
                editPart = (EditPart) selectedObj;

                if (editPart.getParent() == null) {
                    continue;
                }
                if (!(editPart.getParent() instanceof AbstractDiagramEditPart)) {
                    continue;
                }
                if (editPart.getModel() instanceof LabelNode) {
                    continue;
                }

                if (!list.contains(editPart)) {
                    list.add(editPart);
                }
            }
        }
        cutCommand = new DiagramCutCommand();
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(cutCommand);
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
     * <li>설 명 : DiagramCutCommand</li>
     * <li>작성일 : 2011. 2. 22.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public class DiagramCutCommand extends Command {

        /** UML Domain */
        private IDomainModelHandler umlDomain;

        /** 원래 설정되어 있던 클립보드의 내용 */
        private Collection<Object> oldClipboard;

        /** diagram **/
        private Diagram diagram = null;

        /** element **/
        private AbstractNode element;

        /** editPart **/
        private EditPart editPart = null;

        /**
         * @param objList
         */
        public DiagramCutCommand() {
            umlDomain = DomainRegistry.getUMLDomain();
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            oldClipboard = umlDomain.getClipboard();
            umlDomain.setClipboard(new ArrayList<Object>(list));

            for (Object child : list) {
                if (child instanceof EditPart) {
                    this.editPart = (EditPart) child;
                    this.element = (AbstractNode) editPart.getModel();

                    if (editPart != null) {
                        if (editPart.getParent() instanceof AbstractDiagramEditPart) {
                            diagramEditPart = editPart.getParent();
                            if (diagramEditPart.getModel() instanceof Diagram) {
                                diagram = (Diagram) diagramEditPart.getModel();
                            }
                        }
                    }
                    deleteNodeView();
                }
            }
        }

        /**
         * @see org.eclipse.gef.commands.Command#undo()
         */
        @Override
        public void undo() {
            umlDomain.setClipboard(oldClipboard);
        }

        public void deleteNodeView() {

            List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
            diagram.getNodeList().remove(element);
            if (element instanceof NotationNode) {
                diagram.getNodeList().removeAll(((NotationNode) element).getLabels());
            }

            AbstractConnection connection;
            for (int i = 0; i < element.getIncomingConnectionList().size(); i++) {
                connection = element.getIncomingConnectionList().get(i);
                DiagramUtil.detachSourceOfConnection(connection);
                diagram.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
            for (int i = 0; i < element.getOutgoingConnectionList().size(); i++) {
                connection = element.getOutgoingConnectionList().get(i);
                DiagramUtil.detachTargetOfConnection(connection);
                diagram.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
            UMLManager.deleteElement(element);
        }

    }
}
