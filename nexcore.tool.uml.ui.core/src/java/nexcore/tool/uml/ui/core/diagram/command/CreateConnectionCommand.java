/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
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
 * <li>설 명 : CreateConnectionCommand</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateConnectionCommand extends Command {

    /** parent */
    private AbstractNode parent;

    /**
     * ` 연결점 시작 노드
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

    /** UseCase간 연결시 Extend연결에 사용되는 ExtensionPoint null시 새로 생성 */
    private ExtensionPoint extensionPoint;

    /**
     * @return the extensionPoint
     */
    public ExtensionPoint getExtensionPoint() {
        return extensionPoint;
    }

    /**
     * @param extensionPoint
     *            the extensionPoint to set
     */
    public void setExtensionPoint(ExtensionPoint extensionPoint) {
        this.extensionPoint = extensionPoint;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {

        // 1.소스나 타겟이null의 경우는 실행 불가
        if (sourceNode == null && targetNode == null)
            return false;

        return true;
    }

    /**
     * Connection 반환
     * 
     * @return AbstractView
     */
    public AbstractConnection getConnection() {
        return connection;
    }

    /**
     * set Connection
     * 
     * @param model
     *            void
     */
    public void setConnection(AbstractConnection model) {
        connection = (AbstractConnection) model;
    }

    /**
     * Source Node 반환
     * 
     * @return AbstractView
     */
    public AbstractView getSource() {
        return sourceNode;
    }

    /**
     * set Source
     * 
     * @param model
     *            void
     */
    public void setSource(AbstractNode model) {
        sourceNode = model;
    }

    /**
     * Target Node 반환
     * 
     * @return AbstractView
     */
    public AbstractView getTarget() {
        return targetNode;
    }

    /**
     * set Target
     * 
     * @param model
     *            void
     */
    public void setTarget(AbstractNode model) {
        targetNode = model;
    }

    /**
     * @param traceDiagram
     *            the traceDiagram to set
     */
    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        try {

            NamedElement client = (NamedElement) sourceNode.getUmlModel();
            NamedElement supplier = (NamedElement) targetNode.getUmlModel();
            RelationType relationType = this.connection.getRelationType();

            if (RelationType.ABSTRACTION.equals(relationType) || RelationType.USAGE.equals(relationType)
                || RelationType.DEPENDENCY.equals(relationType)) {
                UMLManager.setDependency((Dependency) connection.getUmlModel(), supplier, client);
            } else if (RelationType.EXTEND.equals(relationType)) {
                UMLManager.setExtendDependency((Extend) connection.getUmlModel(),
                    (UseCase) supplier,
                    (UseCase) client,
                    this.extensionPoint);
            } else if (RelationType.INCLUDE.equals(relationType)) {
                UMLManager.setIncludeDependency((Include) connection.getUmlModel(),
                    (UseCase) supplier,
                    (UseCase) client);
            } else if (RelationType.ASSOCIATION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase || supplier instanceof Actor
                    || supplier instanceof UseCase) {
                    UMLManager.setAssociationForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setAssociationForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }

            } else if (RelationType.AGGREGATION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase || supplier instanceof Actor
                    || supplier instanceof UseCase) {
                    UMLManager.setAggregationForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setAggregationForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }
            } else if (RelationType.COMPOSITION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase || supplier instanceof Actor
                    || supplier instanceof UseCase) {
                    UMLManager.setCompositionForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setCompositionForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }

            } else if (RelationType.DIRECTED_ASSOCIATION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase) {
                    UMLManager.setDirectedAssociationForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setDirectedAssociationForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }
            } else if (RelationType.DIRECTED_AGGREGATION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase) {
                    UMLManager.setDirectedAggregationForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setDirectedAggregationForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }
            } else if (RelationType.DIRECTED_COMPOSITION.equals(relationType)) {
                if (client instanceof Actor || client instanceof UseCase) {
                    UMLManager.setDirectedCompositionForBehavioredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                } else {
                    UMLManager.setDirectedCompositionForStructuredClassifier((Association) connection.getUmlModel(),
                        supplier,
                        client);
                }

            } else if (RelationType.GENERALIZATION.equals(relationType)) {
                UMLManager.setGeneralization((Generalization) connection.getUmlModel(),
                    (Classifier) supplier,
                    (Classifier) client);
            } else if (RelationType.REALIZATION.equals(relationType)
                || RelationType.INTERFACE_REALIZATION.equals(relationType)
                || RelationType.COMPONENT_REALIZATION.equals(relationType)) {
                UMLManager.setRealization((Realization) connection.getUmlModel(), supplier, client);
            } else if (RelationType.CONTROL_FLOW.equals(relationType)) {
                UMLManager.setControlFlow((ControlFlow) connection.getUmlModel(), supplier, client);
            } else if (RelationType.OBJECT_FLOW.equals(relationType)) {
                UMLManager.setObjectFlow((ObjectFlow) connection.getUmlModel(), supplier, client);
            }

            connection.setSource(sourceNode);
            connection.setTarget(targetNode);

            if (connection.getUmlModel() instanceof Association) {
                // Connection Label 생성
                if (!(connection.getSource().getUmlModel() instanceof UseCase
                    || connection.getSource().getUmlModel() instanceof Actor
                    || connection.getTarget().getUmlModel() instanceof UseCase || connection.getTarget().getUmlModel() instanceof Actor)) {
                    setAssociationLabel(connection);
                } else {
                    setConnectionLabel(connection);
                }
                // Self Connection의 BendPoint 생성
                if (connection.getSource().equals(connection.getTarget())) {
                    createBendPointSelfConnection();
                }
            } else if (!((connection).getUmlModel() instanceof Generalization
                || (connection).getUmlModel() instanceof Message || connection instanceof Attachement)) {
                setConnectionLabel(connection);
            }  

            if (connection.getUmlModel() instanceof ControlFlow
                ||connection.getUmlModel() instanceof ObjectFlow) {
                // Self Connection의 BendPoint 생성
                if (connection.getSource().equals(connection.getTarget())) {
                    createBendPointSelfConnection();
                }
            }

            if (connection.getSourceAnchor() == null) {
                // sourceAnchor, targetAnchor 생성
                Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
                sourceAnchorDimension.setWidth(sourceAnchorPoint.x - sourceNode.getX());
                sourceAnchorDimension.setHeight(sourceAnchorPoint.y - sourceNode.getY());
                // sourceAnchorDimension.setWidth(sourceAnchorPoint.x);
                // sourceAnchorDimension.setHeight(sourceAnchorPoint.y);
                connection.setSourceAnchor(sourceAnchorDimension);
            }
            if (connection.getTargetAnchor() == null) {
                Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
                targetAnchorDimension.setWidth(targetAnchorPoint.x - targetNode.getX());
                targetAnchorDimension.setHeight(targetAnchorPoint.y - targetNode.getY());
                // targetAnchorDimension.setWidth(targetAnchorPoint.x);
                // targetAnchorDimension.setHeight(targetAnchorPoint.y);
                connection.setTargetAnchor(targetAnchorDimension);
            }

            if (parent instanceof Diagram) {
                ((Diagram) parent).getConnectionList().add(connection);
            } else if (parent instanceof ContainerNode) {
                if( ((ContainerNode) parent).eContainer() instanceof Diagram ) {
                    Diagram diagram = (Diagram) ((ContainerNode) parent).eContainer();
                    diagram.getConnectionList().add( connection );
                }
            }

            DiagramUtil.attachSourceOfConnection(connection);
            DiagramUtil.attachTargetOfConnection(connection);

            ((AbstractDiagramEditor) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getEditDomain().setDefaultTool(new NewSelectionTool());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * createBendPointSelfConnection
     *   void
     */
    private void createBendPointSelfConnection() {
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
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {

        this.execute();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        NamedElement client = (NamedElement) sourceNode.getUmlModel();
        NamedElement supplier = (NamedElement) targetNode.getUmlModel();
        RelationType relationType = this.connection.getRelationType();

        if (RelationType.ABSTRACTION.equals(relationType) || RelationType.USAGE.equals(relationType)
            || RelationType.DEPENDENCY.equals(relationType)) {
            UMLManager.unsetDependency((Dependency) connection.getUmlModel());
        } else if (RelationType.EXTEND.equals(relationType)) {
            UMLManager.unsetExtendDependency((Extend) connection.getUmlModel());
        } else if (RelationType.INCLUDE.equals(relationType)) {
            UMLManager.unsetIncludeDependency((Include) connection.getUmlModel());
        } else if (RelationType.ASSOCIATION.equals(relationType) || RelationType.AGGREGATION.equals(relationType)
            || RelationType.COMPOSITION.equals(relationType) || RelationType.DIRECTED_AGGREGATION.equals(relationType)
            || RelationType.DIRECTED_ASSOCIATION.equals(relationType)
            || RelationType.DIRECTED_COMPOSITION.equals(relationType)) {
            UMLManager.unsetAssociation((Association) connection.getUmlModel());
        } else if (RelationType.GENERALIZATION.equals(relationType)) {
            UMLManager.unsetGeneralization((Generalization) connection.getUmlModel(), (Classifier) client);
        } else if (RelationType.REALIZATION.equals(relationType)
            || RelationType.INTERFACE_REALIZATION.equals(relationType)
            || RelationType.COMPONENT_REALIZATION.equals(relationType)) {
            UMLManager.unsetRealization((Realization) connection.getUmlModel(), supplier, client);
        } else if (RelationType.CONTROL_FLOW.equals(relationType)) {
            // UMLManager.setControlFlow((ControlFlow)connection.getUmlModel(),supplier,
            // client);
        } else if (RelationType.OBJECT_FLOW.equals(relationType)) {
            // UMLManager.setObjectFlow((ObjectFlow)connection.getUmlModel(),supplier,
            // client);
        }

        DiagramUtil.detachSourceOfConnection(connection);
        DiagramUtil.detachTargetOfConnection(connection);
        if (parent instanceof Diagram) {
            ((Diagram) parent).getConnectionList().remove(connection);
        } else if (parent instanceof ContainerNode) {
            ((ContainerNode) parent).getConnectionList().remove(connection);
        }
    }

    /** sourceAnchorPoint */
    private Point sourceAnchorPoint;

    /** targetAnchorPoint */
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

    /**
     * 
     * 
     * @return boolean
     */
    public boolean isExpensible() {
        // TODO Auto-generated method stub
        return true;
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
        LabelNode targetMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetMultiplexLabel.setType(LabelType.TARGET_MULTIPLEX);
        targetMultiplexLabel.setName(getMultiplex(sourceProperty.getUpper(), sourceProperty.getLower()));
        targetMultiplexLabel.setUmlModel(sourceProperty);
        targetMultiplexLabel.setX(-20);
        targetMultiplexLabel.setY(20);
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
        LabelNode sourceMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceMultiplexLabel.setType(LabelType.SOURCE_MULTIPLEX);
        sourceMultiplexLabel.setName(getMultiplex(targetProperty.getUpper(), targetProperty.getLower()));
        sourceMultiplexLabel.setUmlModel(targetProperty);
        sourceMultiplexLabel.setX(20);
        sourceMultiplexLabel.setY(20);
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
        LabelNode targetRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetRoleLabel.setType(LabelType.TARGET_ROLE);
        targetRoleLabel.setName(sourceProperty.getName());
        targetRoleLabel.setUmlModel(sourceProperty);
        targetRoleLabel.setX(-20);
        targetRoleLabel.setY(-20);
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
        LabelNode sourceRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceRoleLabel.setType(LabelType.SOURCE_ROLE);
        sourceRoleLabel.setName(targetProperty.getName());
        sourceRoleLabel.setUmlModel(targetProperty);
        sourceRoleLabel.setX(20);
        sourceRoleLabel.setY(-20);
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
                string = " 0 .. 1 ";
            }
        } else {
            if (lower == 1) {
                string = " 1 .. * ";
            } else {
                string = " * ";
            }
        }
        return string;
    }

    /**
     * 
     * 
     * @param abstractConnection
     *            void
     */
    private void setConnectionLabel(AbstractConnection abstractConnection) {

        Relationship connection = null;
        ActivityEdge activityEdge = null;
        String connectionName = "defaultName";

        if (abstractConnection.getUmlModel() instanceof Realization) {
            connection = (Realization) abstractConnection.getUmlModel();
            connectionName = ((Realization) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Extend) {
            connection = (Extend) abstractConnection.getUmlModel();
            connectionName = ((Extend) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Include) {
            connection = (Include) abstractConnection.getUmlModel();
            connectionName = ((Include) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Usage) {
            connection = (Usage) abstractConnection.getUmlModel();
            connectionName = ((Usage) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Dependency) {
            connection = (Dependency) abstractConnection.getUmlModel();
            connectionName = ((Dependency) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof Association) {
            connection = (Association) abstractConnection.getUmlModel();
            connectionName = ((Association) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof ControlFlow) {
            activityEdge = (ControlFlow) abstractConnection.getUmlModel();
            connectionName = ((ControlFlow) abstractConnection.getUmlModel()).getName();
        } else if (abstractConnection.getUmlModel() instanceof ObjectFlow) {
            activityEdge = (ObjectFlow) abstractConnection.getUmlModel();
            connectionName = ((ObjectFlow) abstractConnection.getUmlModel()).getName();
        }
        AbstractConnection relation = (AbstractConnection) abstractConnection;

        boolean hasNameLabel = false;
        for (LabelNode label : relation.getLabels()) {
            if (label.getType().equals(LabelType.LABEL)) {
                hasNameLabel = true;
            }
        }

        LabelNode connectionLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        connectionLabel.setType(LabelType.COMPARTMENT);
        connectionLabel.setParent(relation);

        if (connection != null && activityEdge == null) {
            connectionLabel.setUmlModel(connection);
        } else if (connection == null & activityEdge != null) {
            connectionLabel.setUmlModel(activityEdge);
        }

        connectionLabel.setX(0);
        connectionLabel.setY(0);

        if (connectionName == null)
            connectionName = "";
        String stereoType = ProjectUtil.getStereotypeLabel(connection);
        int nameWidth = DiagramUtil.getWidthSize(connectionName, UiCorePlugin.getDefault().getFont("default"));
        int stereotypeWidth = DiagramUtil.getWidthSize(stereoType, UiCorePlugin.getDefault().getFont("default"));

        connectionLabel.setWidth(nameWidth > stereotypeWidth ? nameWidth : stereotypeWidth);
        if (!hasNameLabel) {
            relation.getLabels().add(connectionLabel);
        }
    }

}
