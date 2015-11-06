/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : RelationAutoGenerationCommand</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class RelationAutoGenerationCommand extends Command {

    /**
     * 생성자
     */
    public RelationAutoGenerationCommand(AbstractNode parent, AbstractNode sourceNode, AbstractNode targetNode,
    AbstractConnection connection) {
        this.parent = parent;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.connection = connection;
    }

    /** parent */
    private AbstractNode parent;

    /**
     * 연결점 시작 노드
     */
    private AbstractNode sourceNode;

    /**
     * 연결선 끝 노드
     */
    private AbstractNode targetNode;

    /**
     * 연결선
     */
    private AbstractConnection connection;

    /**
     * @param label
     */
    public RelationAutoGenerationCommand(String label) {
        super(label);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        connection.setSource(sourceNode);
        connection.setTarget(targetNode);

        RectangleFigure sourceFigure = new RectangleFigure();
        sourceFigure.setLocation(new Point(sourceNode.getX(), sourceNode.getY()));
        sourceFigure.setSize(sourceNode.getWidth(), sourceNode.getHeight());
        ChopboxAnchor sourceAnchor = new ChopboxAnchor((IFigure) sourceFigure);
        RectangleFigure targetFigure = new RectangleFigure();
        targetFigure.setLocation(new Point(targetNode.getX(), targetNode.getY()));
        targetFigure.setSize(targetNode.getWidth(), targetNode.getHeight());
        ChopboxAnchor targetAnchor = new ChopboxAnchor((IFigure) targetFigure);

        Dimension sourceDimension = UMLDiagramFactory.eINSTANCE.createDimension();
        sourceDimension.setWidth(sourceAnchor.getLocation(targetAnchor.getReferencePoint()).x - sourceNode.getX());
        sourceDimension.setHeight(sourceAnchor.getLocation(targetAnchor.getReferencePoint()).y - sourceNode.getY());
        connection.setSourceAnchor(sourceDimension);
        Dimension targetDimension = UMLDiagramFactory.eINSTANCE.createDimension();
        targetDimension.setWidth(targetAnchor.getLocation(sourceAnchor.getReferencePoint()).x - targetNode.getX());
        targetDimension.setHeight(targetAnchor.getLocation(sourceAnchor.getReferencePoint()).y - targetNode.getY());
        connection.setTargetAnchor(targetDimension);

        if (connection.getUmlModel() instanceof Association) {
            if (!(connection.getSource().getUmlModel() instanceof UseCase
                || connection.getSource().getUmlModel() instanceof Actor
                || connection.getTarget().getUmlModel() instanceof UseCase || connection.getTarget().getUmlModel() instanceof Actor)) {
                setAssociationLabel(connection);
            } else {
                setLabelNode(connection);
            }
            setSelfConnection();
        }

        else if (!((connection).getUmlModel() instanceof Generalization
            || (connection).getUmlModel() instanceof Message || (connection).getUmlModel() instanceof ControlFlow
            || (connection).getUmlModel() instanceof ObjectFlow || connection instanceof Attachement)) {
            setLabelNode(connection);
        }

        if (parent instanceof Diagram) {
            ((Diagram) parent).getConnectionList().add(connection);
        } else if (parent instanceof ContainerNode) {
            ((ContainerNode) parent).getConnectionList().add(connection);
        }

        DiagramUtil.attachSourceOfConnection(connection);
        DiagramUtil.attachTargetOfConnection(connection);

    }

    /**
     * setSelfConnection
     *   void
     */
    protected void setSelfConnection() {
        if (connection.getSource().equals(connection.getTarget())) {
            // Self Connection을 위한 Anchor Point 생성
            Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            sourceAnchorDimension.setWidth(sourceNode.getX() + sourceNode.getWidth());
            sourceAnchorDimension.setHeight(sourceNode.getY() + (int) (sourceNode.getHeight() / 2));
            connection.setSourceAnchor(sourceAnchorDimension);
            Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            targetAnchorDimension.setWidth(targetNode.getX() + (int) (targetNode.getWidth() / 2));
            targetAnchorDimension.setHeight(targetNode.getY() + targetNode.getHeight());
            connection.setTargetAnchor(targetAnchorDimension);

            Point sourcePoint = new Point(connection.getSourceAnchor().getWidth(), connection.getSourceAnchor()
                .getHeight());
            Point targetPoint = new Point(connection.getTargetAnchor().getWidth(), connection.getTargetAnchor()
                .getHeight());

            Point p1 = new Point(sourceAnchorDimension.getWidth() + 50, sourceAnchorDimension.getHeight());
            BendPoint firstBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension dimension1 = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension dimension2 = UMLDiagramFactory.eINSTANCE.createDimension();
            dimension1.setWidth(p1.getDifference(sourcePoint).width);
            dimension1.setHeight(p1.getDifference(sourcePoint).height);
            firstBendPoint.setFirstPosition(dimension1);
            dimension2.setWidth(p1.getDifference(targetPoint).width);
            dimension2.setHeight(p1.getDifference(targetPoint).height);
            firstBendPoint.setSecondPosition(dimension2);
            connection.getBendPointList().add(0, firstBendPoint);

            Point p2 = new Point(targetAnchorDimension.getWidth(), targetAnchorDimension.getHeight() + 50);
            BendPoint secondBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension dimension3 = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension dimension4 = UMLDiagramFactory.eINSTANCE.createDimension();
            dimension3.setWidth(p2.getDifference(sourcePoint).width);
            dimension3.setHeight(p2.getDifference(sourcePoint).height);
            secondBendPoint.setFirstPosition(dimension3);
            dimension4.setWidth(p2.getDifference(targetPoint).width);
            dimension4.setHeight(p2.getDifference(targetPoint).height);
            secondBendPoint.setSecondPosition(dimension4);
            connection.getBendPointList().add(1, secondBendPoint);

            Point p3 = new Point(p1.x, p2.y);
            BendPoint thirdBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension dimension5 = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension dimension6 = UMLDiagramFactory.eINSTANCE.createDimension();
            dimension5.setWidth(p3.getDifference(sourcePoint).width);
            dimension5.setHeight(p3.getDifference(sourcePoint).height);
            thirdBendPoint.setFirstPosition(dimension5);
            dimension6.setWidth(p3.getDifference(targetPoint).width);
            dimension6.setHeight(p3.getDifference(targetPoint).height);
            thirdBendPoint.setSecondPosition(dimension6);
            connection.getBendPointList().add(1, thirdBendPoint);
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        this.execute();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        DiagramUtil.detachSourceOfConnection(connection);
        DiagramUtil.detachTargetOfConnection(connection);

        if (parent instanceof Diagram) {
            ((Diagram) parent).getConnectionList().remove(connection);
        } else if (parent instanceof ContainerNode) {
            ((ContainerNode) parent).getConnectionList().remove(connection);
        }
    }

    /** sourceProperty */
    private Property sourceProperty;

    /** targetProperty */
    private Property targetProperty;

    /**
     * setAssociationLabel
     *  
     * @param connection void
     */
    private void setAssociationLabel(AbstractConnection connection) {

        Association association = (Association) connection.getUmlModel();
        AbstractConnection relation = (AbstractConnection) connection;
        NotationNode source = (NotationNode) relation.getSource();
        NotationNode target = (NotationNode) relation.getTarget();

        if (((NotationNode) relation.getSource()).getUmlModel().equals(association.getMemberEnds().get(0).getType())) {
            if (((NotationNode) relation.getSource()).getUmlModel() instanceof Actor) {
                sourceProperty = association.getMemberEnds().get(0);
                targetProperty = association.getMemberEnds().get(1);
            } else {
                sourceProperty = association.getMemberEnds().get(1);
                targetProperty = association.getMemberEnds().get(0);
            }
        } else if (((NotationNode) relation.getSource()).getUmlModel().equals(association.getMemberEnds()
            .get(1)
            .getType())) {
            if (((NotationNode) relation.getSource()).getUmlModel() instanceof Actor) {
                sourceProperty = association.getMemberEnds().get(1);
                targetProperty = association.getMemberEnds().get(0);
            } else {
                sourceProperty = association.getMemberEnds().get(0);
                targetProperty = association.getMemberEnds().get(1);
            }
        }

        RectangleFigure sourceFigure = new RectangleFigure();
        sourceFigure.setLocation(new Point(source.getX(), source.getY()));
        sourceFigure.setSize(source.getWidth(), source.getHeight());

        RectangleFigure targetFigure = new RectangleFigure();
        targetFigure.setLocation(new Point(target.getX(), target.getY()));
        targetFigure.setSize(target.getWidth(), target.getHeight());

        ChopboxAnchor sourceAnchor = new ChopboxAnchor(targetFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(sourceFigure);

        Point sourceAnchorLocation = new Point();
        Point targetAnchorLocation = new Point();

        sourceAnchorLocation = targetAnchor.getLocation(sourceAnchor.getReferencePoint());
        targetAnchorLocation = sourceAnchor.getLocation(targetAnchor.getReferencePoint());

        boolean hasSourceRole = false;
        boolean hasTargetRole = false;
        boolean hasSourceMultiplex = false;
        boolean hasTargetMultiplex = false;
        boolean hasNameLabel = false;

        for (LabelNode label : relation.getLabels()) {
            if (label.getType().equals(LabelType.SOURCE_ROLE)) {
                hasSourceRole = true;
            } else if (label.getType().equals(LabelType.TARGET_ROLE)) {
                hasTargetRole = true;
            } else if (label.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
                hasSourceMultiplex = true;
            } else if (label.getType().equals(LabelType.TARGET_MULTIPLEX)) {
                hasTargetMultiplex = true;
            } else if (label.getType().equals(LabelType.LABEL)) {
                hasNameLabel = true;
            }
        }

        createSourceRoleLabel(relation, sourceAnchorLocation, hasSourceRole);
        createTargetRoleLabel(relation, targetAnchorLocation, hasTargetRole);
        createSourceMultiplexLabel(relation, sourceAnchorLocation, hasSourceMultiplex);
        createTargetMultiplexLabel(relation, targetAnchorLocation, hasTargetMultiplex);
        createConnectionNameLabel(association, relation, sourceAnchorLocation, targetAnchorLocation, hasNameLabel);
    }

    /**
     * 
     * 
     * @param association
     * @param relation
     * @param sourceAnchorLocation
     * @param targetAnchorLocation
     * @param hasNameLabel
     *            void
     */
    private void createConnectionNameLabel(Association association, AbstractConnection relation,
                                           Point sourceAnchorLocation, Point targetAnchorLocation, boolean hasNameLabel) {
        LabelNode associationLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        associationLabel.setType(LabelType.COMPARTMENT);
        associationLabel.setName(association.getName());
        associationLabel.setUmlModel(association);
        associationLabel.setX(0);
        associationLabel.setY(0);
        associationLabel.setParent(relation);
        int associationWidth = DiagramUtil.getWidthSize(association.getName(), UiCorePlugin.getDefault()
            .getFont("default"));
        associationLabel.setWidth(associationWidth);
        associationLabel.setHeight(40);
        if (!hasNameLabel) {
            relation.getLabels().add(associationLabel);
        }
    }

    /**
     * 
     * 
     * @param relation
     * @param targetAnchorLocation
     * @param hasTargetMultiplex
     *            void
     */
    private void createTargetMultiplexLabel(AbstractConnection relation, Point targetAnchorLocation,
                                            boolean hasTargetMultiplex) {
        nexcore.tool.uml.model.umldiagram.LabelNode targetMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetMultiplexLabel.setType(LabelType.TARGET_MULTIPLEX);
        targetMultiplexLabel.setName(getMultiplex(sourceProperty.getUpper(), sourceProperty.getLower()));
        targetMultiplexLabel.setUmlModel(sourceProperty);
        targetMultiplexLabel.setX(-20);// targetAnchorLocation.x);
        targetMultiplexLabel.setY(20);// targetAnchorLocation.y);
        targetMultiplexLabel.setParent(relation);
        int targetMultiplexWidth = DiagramUtil.getWidthSize(targetMultiplexLabel.getName(), UiCorePlugin.getDefault()
            .getFont("default"));
        targetMultiplexLabel.setWidth(targetMultiplexWidth);
        targetMultiplexLabel.setHeight(20);
        if (!hasTargetMultiplex) {
            relation.getLabels().add(targetMultiplexLabel);
        }
    }

    /**
     * 
     * 
     * @param relation
     * @param sourceAnchorLocation
     * @param hasSourceMultiplex
     *            void
     */
    private void createSourceMultiplexLabel(AbstractConnection relation, Point sourceAnchorLocation,
                                            boolean hasSourceMultiplex) {
        nexcore.tool.uml.model.umldiagram.LabelNode sourceMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceMultiplexLabel.setType(LabelType.SOURCE_MULTIPLEX);
        sourceMultiplexLabel.setName(getMultiplex(targetProperty.getUpper(), targetProperty.getLower()));
        sourceMultiplexLabel.setUmlModel(targetProperty);
        sourceMultiplexLabel.setX(20);// sourceAnchorLocation.x);
        sourceMultiplexLabel.setY(20);// sourceAnchorLocation.y);
        sourceMultiplexLabel.setParent(relation);
        int sourceMultiplexWidth = DiagramUtil.getWidthSize(sourceMultiplexLabel.getName(), UiCorePlugin.getDefault()
            .getFont("default"));
        sourceMultiplexLabel.setWidth(sourceMultiplexWidth);
        sourceMultiplexLabel.setHeight(20);
        if (!hasSourceMultiplex) {
            relation.getLabels().add(sourceMultiplexLabel);
        }
    }

    /**
     * 
     * 
     * @param relation
     * @param targetAnchorLocation
     * @param hasTargetRole
     *            void
     */
    private void createTargetRoleLabel(AbstractConnection relation, Point targetAnchorLocation, boolean hasTargetRole) {
        nexcore.tool.uml.model.umldiagram.LabelNode targetRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetRoleLabel.setType(LabelType.TARGET_ROLE);
        targetRoleLabel.setName(sourceProperty.getName());
        targetRoleLabel.setUmlModel(sourceProperty);
        targetRoleLabel.setX(-20);// targetAnchorLocation.x);
        targetRoleLabel.setY(-20);// targetAnchorLocation.y);
        targetRoleLabel.setParent(relation);
        int targetRoleLabelWidth = DiagramUtil.getWidthSize(targetRoleLabel.getName(), UiCorePlugin.getDefault()
            .getFont("default"));
        targetRoleLabel.setWidth(targetRoleLabelWidth);
        targetRoleLabel.setHeight(20);
        if (!hasTargetRole) {
            relation.getLabels().add(targetRoleLabel);
        }
    }

    /**
     * 
     * 
     * @param relation
     * @param sourceAnchorLocation
     * @param hasSourceRole
     *            void
     */
    private void createSourceRoleLabel(AbstractConnection relation, Point sourceAnchorLocation, boolean hasSourceRole) {
        nexcore.tool.uml.model.umldiagram.LabelNode sourceRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceRoleLabel.setType(LabelType.SOURCE_ROLE);
        sourceRoleLabel.setName(targetProperty.getName());
        sourceRoleLabel.setUmlModel(targetProperty);
        sourceRoleLabel.setX(20);// sourceAnchorLocation.x);
        sourceRoleLabel.setY(-20);// sourceAnchorLocation.y);
        sourceRoleLabel.setParent(relation);
        int sourceRoleLabelWidth = DiagramUtil.getWidthSize(sourceRoleLabel.getName(), UiCorePlugin.getDefault()
            .getFont("default"));
        sourceRoleLabel.setWidth(sourceRoleLabelWidth);
        sourceRoleLabel.setHeight(20);
        if (!hasSourceRole) {
            relation.getLabels().add(sourceRoleLabel);
        }
    }

    /**
     * 
     * 
     * @param upper
     * @param lower
     * @return String
     */
    private String getMultiplex(int upper, int lower) {
        String string;
        if (upper == 1) {
            if (lower == 1) {
                string = " 1 ";
            } else {
                string = "0 ... 1";
            }
        } else {
            if (lower == 1) {
                string = "1 ... *";
            } else {
                string = " * ";
            }
        }
        return string;
    }

    /**
     * setLabelNode
     *  
     * @param abstractConnection void
     */
    private void setLabelNode(AbstractConnection abstractConnection) {

        Relationship connection = null;
        String ConnectionName = "defaultName";

        if (abstractConnection.getUmlModel() instanceof Realization) {
            connection = (Realization) abstractConnection.getUmlModel();
            ConnectionName = ((Realization) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Extend) {
            connection = (Extend) abstractConnection.getUmlModel();
            ConnectionName = ((Extend) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Include) {
            connection = (Include) abstractConnection.getUmlModel();
            ConnectionName = ((Include) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Usage) {
            connection = (Usage) abstractConnection.getUmlModel();
            ConnectionName = ((Usage) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Dependency) {
            connection = (Dependency) abstractConnection.getUmlModel();
            ConnectionName = ((Dependency) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Association) {
            connection = (Association) abstractConnection.getUmlModel();
            ConnectionName = ((Association) abstractConnection.getUmlModel()).getName();
        }

        AbstractConnection relation = (AbstractConnection) abstractConnection;

        boolean hasNameLabel = false;
        for (LabelNode label : relation.getLabels()) {
            if (label.getType().equals(LabelType.LABEL)) {
                hasNameLabel = true;
            }
        }

        nexcore.tool.uml.model.umldiagram.LabelNode LabelNode = UMLDiagramFactory.eINSTANCE.createLabelNode();
        LabelNode.setType(LabelType.LABEL);
        LabelNode.setUmlModel(connection);
        LabelNode.setX(0);
        LabelNode.setY(0);

        if (ConnectionName == null)
            ConnectionName = "";
        String stereoType = ProjectUtil.getStereotypeLabel(connection);
        int nameWidth = DiagramUtil.getWidthSize(ConnectionName, UiCorePlugin.getDefault().getFont("default"));
        int stereotypeWidth = DiagramUtil.getWidthSize(stereoType, UiCorePlugin.getDefault().getFont("default")) + 30;
        if (abstractConnection.getUmlModel() instanceof Extend || abstractConnection.getUmlModel() instanceof Include
            || abstractConnection.getUmlModel() instanceof Usage
            || abstractConnection.getUmlModel() instanceof Dependency) {
            stereotypeWidth += 30;
        }

        LabelNode.setWidth(nameWidth > stereotypeWidth ? nameWidth : stereotypeWidth);
        LabelNode.setHeight(40);
        if (!hasNameLabel) {
            relation.getLabels().add(LabelNode);
        }
    }
}
