/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Anhong
 * 
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : ActivityReconnectConnectionCommand</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ActivityReconnectConnectionCommand extends Command {

    /** connection */
    private AbstractConnection connection;

    /** diagram */
    private Diagram diagram;

    /** new Source ViewModel */
    private AbstractNode newSource = null;

    /** new Target ViewModel */
    private AbstractNode newTarget = null;

    /** old Source ViewModel */
    private AbstractNode oldSource = null;

    /** old Target ViewModel */
    private AbstractNode oldTarget = null;

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (newSource != null) {
            handleNewSource();
        } else if (newTarget != null) {
            handleNewTarget();
        }
        ((AbstractDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
            .getEditDomain()
            .setDefaultTool(new NewSelectionTool());

    }

    /**
     * handleNewTarget
     */
    private void handleNewTarget() {

        oldTarget = (AbstractNode) connection.getTarget();

        diagram.getConnectionList().remove(connection);
        reconnectTarget(newTarget);
        diagram.getConnectionList().add(connection);
        ActivityEdge activity = (ActivityEdge) this.connection.getUmlModel();
        if (activity != null) {
            switch (activity.eClass().getClassifierID()) {
                case UMLPackage.CONTROL_FLOW:
                    handleNewTargetForControlFlow((ControlFlow) activity);
                    break;
                case UMLPackage.OBJECT_FLOW:
                    handlerNewTargetForObjectFlow((ObjectFlow) activity);
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * ì†ŒìŠ¤ì�˜ ë³€ê²½ ì²˜ë¦¬. void
     */
    private void handleNewSource() {
        oldSource = (AbstractNode) connection.getSource();

        diagram.getConnectionList().remove(connection);
        reconnectSource(newSource);
        diagram.getConnectionList().add(connection);

        ActivityEdge activity = (ActivityEdge) this.connection.getUmlModel();
        if (activity != null) {
            switch (activity.eClass().getClassifierID()) {
                case UMLPackage.CONTROL_FLOW:
                    handleNewSourceForControlFlow((ControlFlow) activity);
                    break;
                case UMLPackage.OBJECT_FLOW:
                    handlerNewSourceForObjectFlow((ObjectFlow) activity);
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * 
     * 
     * @param controlflow
     *            void
     */
    private void handleNewTargetForControlFlow(ControlFlow controlFlow) {
        ((ActivityNode) this.oldTarget.getUmlModel()).getIncomings().remove(controlFlow);
        ((ActivityNode) this.newTarget.getUmlModel()).getIncomings().add(controlFlow);
    }

    /**
     * 
     * 
     * @param objectflow
     *            void
     */
    private void handlerNewTargetForObjectFlow(ObjectFlow objectFlow) {
        ((ActivityNode) this.oldTarget.getUmlModel()).getIncomings().remove(objectFlow);
        ((ActivityNode) this.newTarget.getUmlModel()).getIncomings().add(objectFlow);
    }

    /**
     * 
     * 
     * @param controlflow
     *            void
     */
    private void handleNewSourceForControlFlow(ControlFlow controlFlow) {
        ((ActivityNode) this.oldSource.getUmlModel()).getOutgoings().remove(controlFlow);
        ((ActivityNode) this.newSource.getUmlModel()).getOutgoings().add(controlFlow);
    }

    /**
     * 
     * 
     * @param objectflow
     *            void
     */
    private void handlerNewSourceForObjectFlow(ObjectFlow objectFlow) {
        ((ActivityNode) this.oldSource.getUmlModel()).getOutgoings().remove(objectFlow);
        ((ActivityNode) this.newSource.getUmlModel()).getOutgoings().add(objectFlow);
    }

    /**
     * reconnectTarget
     * 
     * @param target
     *            void
     */
    private void reconnectTarget(AbstractNode target) {
        DiagramUtil.detachTargetOfConnection(connection);
        connection.setTarget(target);
        DiagramUtil.attachTargetOfConnection(connection);
    }

    /**
     * reconnectSource
     * 
     * @param source
     *            void
     */
    private void reconnectSource(AbstractNode source) {
        DiagramUtil.detachSourceOfConnection(connection);
        connection.setSource(source);
        DiagramUtil.attachSourceOfConnection(connection);
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setConnectionModel(Object model) {
        connection = (AbstractConnection) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewSource(Object model) {
        newSource = (AbstractNode) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewTarget(Object model) {
        newTarget = (AbstractNode) model;
    }

    /**
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        // if (oldSource != null) {
        // reconnectSource(oldSource);
        // }
        // if (oldTarget != null) {
        // reconnectTarget(oldTarget);
        // }
        //
        // oldSource = null;
        // oldTarget = null;
    }

    /** sourceAnchorPoint */
    @SuppressWarnings("unused")
    private Point sourceAnchorPoint;

    /** targetAnchorPoint */
    @SuppressWarnings("unused")
    private Point targetAnchorPoint;

    /**
     * 
     * setSourceAnchorPoint
     * 
     * @param location
     *            void
     */
    public void setSourceAnchorPoint(Point location) {
        sourceAnchorPoint = location;
    }

    /**
     * 
     * setTargetAnchorPoint
     * 
     * @param location
     *            void
     */
    public void setTargetAnchorPoint(Point location) {
        targetAnchorPoint = location;
    }

}
