/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.action;

import java.util.List;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : RefreshDiagramAction</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class RefreshDiagramAction extends SelectionAction {

    /**
     * showMessage
     */
    protected boolean showMessage = true;

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        
        Object obj = this.getSelectedObjects().get(0);
        if(obj instanceof EditPart){
            Object model = ((EditPart)obj).getModel();
            if(model instanceof AbstractView){
                if( ((AbstractView)model).getUmlModel() != null && DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return false;
                }
            }
        }
        
        int count = this.getSelectedObjects().size();
        if (0 == count) {
            return false;
        } else if (1 == count) {
            Object object = this.getSelectedObjects().get(0);
            if (!(object instanceof EditPart)) {
                return false;
            }
        }
        return true;
    }

    /** REFRESH_DIAGRAM_ACTION */
    public static final String REFRESH_DIAGRAM_ACTION = "Refresh Diagram";

    /** REFRESH_DIAGRAM_ACTIONID */
    public static final String REFRESH_DIAGRAM_ACTIONID = "RefreshDiagram";

    /**
     * @param part
     */
    public RefreshDiagramAction(IWorkbenchPart part) {
        super(part);
        this.showMessage = true;
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
    }

    /**
     * @param part
     * @param messageFlag
     *            확인 메세지박스를 보여줄 것인지 결정하는 플래그.
     */
    public RefreshDiagramAction(IWorkbenchPart part, boolean messageFlag) {
        this(part);
        this.showMessage = messageFlag;
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
        setId(REFRESH_DIAGRAM_ACTIONID);
        setText(REFRESH_DIAGRAM_ACTION);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        /*
         * DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
         * {
         * 
         * @Override public void execute() { refreshDiagram(); } });
         */
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                public void doExecute() {
                    refreshDiagram();
                }
            });
    }

    /**
     * refreshDiagram
     *   void
     */
    private void refreshDiagram() {

        Object obj = getSelectedObjects().get(0);
        RootEditPart root = null;

        if (!(obj instanceof EditPart)) {
            return;
        }

        root = ((EditPart) obj).getRoot();
        root.refresh();

        List<EditPart> editparts = root.getChildren();
        for (EditPart editpart : editparts) {
            editpart.refresh();

            List<EditPart> nodeEditParts = editpart.getChildren();
            for (EditPart nodeEditPart : nodeEditParts) {
                if (nodeEditPart instanceof GraphicalEditPart) {

                    List<EditPart> sConnectionList = ((GraphicalEditPart) nodeEditPart).getSourceConnections();
                    List<EditPart> tConnectionList = ((GraphicalEditPart) nodeEditPart).getTargetConnections();

                    for (EditPart sConnectionEditPart : sConnectionList) {
                        sConnectionEditPart.refresh();
                    }
                    for (EditPart tConnectionEditPart : tConnectionList) {
                        tConnectionEditPart.refresh();
                    }

                    if (nodeEditPart.getModel() instanceof LifeLineNode) {
                        List<EditPart> childrenEditPart = nodeEditPart.getChildren();
                        for (EditPart child : childrenEditPart) {
                            List<EditPart> csConnectionList = ((GraphicalEditPart) child).getSourceConnections();
                            List<EditPart> ctConnectionList = ((GraphicalEditPart) child).getTargetConnections();

                            for (EditPart sConnectionEditPart : csConnectionList) {
                                sConnectionEditPart.refresh();
                            }
                            for (EditPart tConnectionEditPart : ctConnectionList) {
                                tConnectionEditPart.refresh();
                            }
                        }
                    }
                }
            }
        }
    }
}
