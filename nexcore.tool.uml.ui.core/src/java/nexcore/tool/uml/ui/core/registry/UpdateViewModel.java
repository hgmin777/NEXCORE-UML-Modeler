/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설  명 : UpdateViewModel</li>
 * <li>작성일 : 2011. 3. 3.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class UpdateViewModel {

    /**
     * defaultFont
     */
    private static Font defaultFont = UiCorePlugin.getDefault().getFont("default10");

    /**
     * commandList
     */
    private static CompoundCommand commandList;

    /**
     * 
     * void
     * 
     * @param file
     * 
     * @param model
     */
    public static void updateViewModel(Package element, final IFile file) {
        commandList = new CompoundCommand();

        if (DomainUtil.isProxy(element)) {
            URI uri = EcoreUtil.getURI(element);
            element = (Package) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
        }
        update(element, file);

        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(commandList);
    }

    /**
     * update
     *  
     * @param element
     * @param file void
     */
    private static void update(Package element, final IFile file) {
        if (DomainUtil.isProxy(element)) {
            URI uri = EcoreUtil.getURI(element);
            element = (Package) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
        }

        for (int i = 0; i < element.getEAnnotations().size(); i++) {
            EAnnotation eAnnotation = element.getEAnnotations().get(i);
            if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(eAnnotation.getSource())) {
                for (int j = 0; j < eAnnotation.eContents().size(); j++) {
                    EObject eObj = eAnnotation.eContents().get(j);

                    if (DomainUtil.isProxy(eObj)) {
                        URI uri = EcoreUtil.getURI(eObj);
                        eObj = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                    }
                    if (eObj instanceof Diagram) {
                        UpdateViewModelCommand command = new UpdateViewModelCommand((Diagram) eObj, file);
                        commandList.add(command);
                    }
                }
            }
        }
        for (int i = 0; i < element.getPackagedElements().size(); i++) {
            PackageableElement packageableElement = element.getPackagedElements().get(i);
            for (int j = 0; j < packageableElement.getEAnnotations().size(); j++) {
                EAnnotation eAnnotation = packageableElement.getEAnnotations().get(j);
                if (eAnnotation.getSource() == null) {
                    continue;
                }
                if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(eAnnotation.getSource())) {
                    for (int k = 0; k < eAnnotation.eContents().size(); k++) {
                        EObject eObj = eAnnotation.eContents().get(k);

                        if (DomainUtil.isProxy(eObj)) {
                            URI uri = EcoreUtil.getURI(eObj);
                            eObj = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                        }

                        if (eObj instanceof Diagram) {
                            UpdateViewModelCommand command = new UpdateViewModelCommand((Diagram) eObj, file);
                            commandList.add(command);
                        }
                    }
                }
            }

            if (DomainUtil.isProxy(packageableElement)) {
                URI uri = EcoreUtil.getURI(packageableElement);
                packageableElement = (PackageableElement) DomainRegistry.getUMLDomain()
                    .getResourceSet()
                    .getEObject(uri, true);
            }

            if (packageableElement instanceof Package) {
                update((Package) packageableElement, file);
            }
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
     * <li>설 명 : UpdateViewModelCommand</li>
     * <li>작성일 : 2011. 2. 25.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public static class UpdateViewModelCommand extends Command {

        private Diagram diagram;

        private List<AbstractNode> nodeList = new ArrayList<AbstractNode>();

        private List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();

        private IFile file;

        /**
         * @param obj
         */
        public UpdateViewModelCommand(Diagram obj, IFile file) {
            this.diagram = obj;
            this.nodeList = diagram.getNodeList();
            this.connectionList = diagram.getConnectionList();
            this.file = file;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            for (int i = 0; i < connectionList.size(); i++) {
                connectionList.get(i).getLabels().clear();
            }
            List<LabelNode> removeList = new ArrayList<LabelNode>();
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i) instanceof LabelNode) {
                    removeList.add((LabelNode) nodeList.get(i));
                }
            }
            for (int i = 0; i < removeList.size(); i++) {
                nodeList.remove(removeList.get(i));
            }
            for (int i = 0; i < connectionList.size(); i++) {
                AbstractConnection connection = connectionList.get(i);
                if (connection.getUmlModel() instanceof Association) {
                    // Connection Label 생성
                    if (!(connection.getSource().getUmlModel() instanceof UseCase
                        || connection.getSource().getUmlModel() instanceof Actor
                        || connection.getTarget().getUmlModel() instanceof UseCase || connection.getTarget()
                        .getUmlModel() instanceof Actor)) {
                        setAssociationLabel(connection);
                    } else {
                        setConnectionLabel(connection);
                    }
                } else {
                    setConnectionLabel(connection);
                }
            }

            for (int i = 0; i < nodeList.size(); i++) {
                EObject eObj = nodeList.get(i);
                if (eObj instanceof NotationNode) {
                    if (DomainUtil.isProxy(eObj)) {
                        URI uri = EcoreUtil.getURI(eObj);
                        eObj = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                    }
                    setModelInfo((NotationNode) eObj);
                }
            }

        }
    }

    /** sourceProperty */
    public static Property sourceProperty;

    /** targetProperty */
    public static Property targetProperty;

    /**
     * setAssociationLabel
     *  
     * @param connection void
     */
    public static void setAssociationLabel(AbstractConnection connection) {

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
            if (LabelType.SOURCE_ROLE.equals(label.getType())) {
                hasSourceRole = true;
            } else if (LabelType.TARGET_ROLE.equals(label.getType())) {
                hasTargetRole = true;
            } else if (LabelType.SOURCE_MULTIPLEX.equals(label.getType())) {
                hasSourceMultiplex = true;
            } else if (LabelType.TARGET_MULTIPLEX.equals(label.getType())) {
                hasTargetMultiplex = true;
            } else if (LabelType.COMPARTMENT.equals(label.getType())) {
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
    public static void createConnectionNameLabel(Association association, AbstractConnection relation,
                                                 Point sourceAnchorLocation, Point targetAnchorLocation,
                                                 boolean hasNameLabel) {
        LabelNode associationLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        associationLabel.setType(LabelType.COMPARTMENT);
        associationLabel.setName(association.getName());
        associationLabel.setUmlModel(association);
        associationLabel.setX(0);
        associationLabel.setY(0);
        associationLabel.setParent(relation);
        int associationWidth = DiagramUtil.getWidthSize(association.getName(), defaultFont);
        associationLabel.setWidth(associationWidth);
        associationLabel.setHeight(40);
        if (!hasNameLabel) {
            relation.getLabels().add(associationLabel);
        }

        LabelNode node = associationLabel;
        AbstractView parent = (AbstractView) node.getParent();
        Element umlElement = parent.getUmlModel();

        // 해당 모델의 이름과 스테레오 타입을 가져와서 Empty String이 아니라면 뷰모델을 생성하여 리턴한다.
        String elementName = ((NamedElement) umlElement).getName();
        String stereotype = ProjectUtil.getStereotypeText(umlElement);

        // 이름 뷰모델을 생성
        if (elementName != null && !elementName.equals(UICoreConstant.EMPTY_STRING)) {

            if (!hasLabelType(node, LabelType.LABEL)) {
                LabelNode nameLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                nameLabel.setId(UUID.randomUUID().toString());
                nameLabel.setParent(node);
                nameLabel.setX(0);
                nameLabel.setY(0);
                nameLabel.setType(LabelType.LABEL);
                nameLabel.setUmlModel(umlElement);
                node.getCompartmentList().add(nameLabel);
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.LABEL);
        }

        // 스테레오 타입 뷰모델을 생성
        if (!UICoreConstant.EMPTY_STRING.equals(stereotype)) {

            if (!hasLabelType(node, LabelType.STEREOTYPE)) {
                LabelNode stereotypeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                stereotypeLabel.setId(UUID.randomUUID().toString());
                stereotypeLabel.setParent(node);
                stereotypeLabel.setX(0);
                stereotypeLabel.setY(0);
                stereotypeLabel.setType(LabelType.STEREOTYPE);
                stereotypeLabel.setUmlModel(umlElement);
                node.getCompartmentList().add(stereotypeLabel);
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.STEREOTYPE);
        }
    }

    /**
     * 
     * 라벨의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    public static boolean hasLabelType(LabelNode node, LabelType labelType) {
        for (LabelNode child : node.getCompartmentList()) {
            if (labelType.equals(child.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * 값이 없는 경우 뷰모델과 에디트파트를 지워준다
     * 
     * @param node
     * @param labelType
     *            void
     */
    public static void removeLabelViewAndEditPart(LabelNode node, LabelType labelType) {

        // 뷰모델을 지운다.
        LabelNode removeTarget = null;
        for (LabelNode label : node.getCompartmentList()) {
            if (labelType.equals(label.getType())) {
                removeTarget = label;
            }
        }
        if (removeTarget != null) {
            if (node.getCompartmentList().contains(removeTarget)) {
                node.getCompartmentList().remove(removeTarget);
            }
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
    public static void createTargetMultiplexLabel(AbstractConnection relation, Point targetAnchorLocation,
                                                  boolean hasTargetMultiplex) {
        LabelNode targetMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetMultiplexLabel.setType(LabelType.TARGET_MULTIPLEX);
        targetMultiplexLabel.setName(getMultiplex(sourceProperty.getUpper(), sourceProperty.getLower()));
        targetMultiplexLabel.setUmlModel(sourceProperty);
        targetMultiplexLabel.setX(-20);
        targetMultiplexLabel.setY(20);
        targetMultiplexLabel.setParent(relation);
        int targetMultiplexWidth = DiagramUtil.getWidthSize(targetMultiplexLabel.getName(), defaultFont);
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
    public static void createSourceMultiplexLabel(AbstractConnection relation, Point sourceAnchorLocation,
                                                  boolean hasSourceMultiplex) {
        LabelNode sourceMultiplexLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceMultiplexLabel.setType(LabelType.SOURCE_MULTIPLEX);
        sourceMultiplexLabel.setName(getMultiplex(targetProperty.getUpper(), targetProperty.getLower()));
        sourceMultiplexLabel.setUmlModel(targetProperty);
        sourceMultiplexLabel.setX(20);
        sourceMultiplexLabel.setY(20);
        sourceMultiplexLabel.setParent(relation);
        int sourceMultiplexWidth = DiagramUtil.getWidthSize(sourceMultiplexLabel.getName(), defaultFont);
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
    public static void createTargetRoleLabel(AbstractConnection relation, Point targetAnchorLocation,
                                             boolean hasTargetRole) {
        LabelNode targetRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        targetRoleLabel.setType(LabelType.TARGET_ROLE);
        targetRoleLabel.setName(sourceProperty.getName());
        targetRoleLabel.setUmlModel(sourceProperty);
        targetRoleLabel.setX(-20);
        targetRoleLabel.setY(-20);
        targetRoleLabel.setParent(relation);
        int targetRoleLabelWidth = DiagramUtil.getWidthSize(targetRoleLabel.getName(), defaultFont);
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
    public static void createSourceRoleLabel(AbstractConnection relation, Point sourceAnchorLocation,
                                             boolean hasSourceRole) {
        LabelNode sourceRoleLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
        sourceRoleLabel.setType(LabelType.SOURCE_ROLE);
        sourceRoleLabel.setName(targetProperty.getName());
        sourceRoleLabel.setUmlModel(targetProperty);
        sourceRoleLabel.setX(20);
        sourceRoleLabel.setY(-20);
        sourceRoleLabel.setParent(relation);
        int sourceRoleLabelWidth = DiagramUtil.getWidthSize(sourceRoleLabel.getName(), defaultFont);
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
    public static String getMultiplex(int upper, int lower) {
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
    public static void setConnectionLabel(AbstractConnection abstractConnection) {

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
            if (LabelType.LABEL.equals(label.getType())) {
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
        int nameWidth = DiagramUtil.getWidthSize(connectionName, defaultFont);
        int stereotypeWidth = DiagramUtil.getWidthSize(stereoType, defaultFont);

        connectionLabel.setWidth(nameWidth > stereotypeWidth ? nameWidth : stereotypeWidth);
        if (!hasNameLabel) {
            relation.getLabels().add(connectionLabel);
        }

        LabelNode node = connectionLabel;
        AbstractView parent = (AbstractView) node.getParent();
        Element umlElement = parent.getUmlModel();

        // 해당 모델의 이름과 스테레오 타입을 가져와서 Empty String이 아니라면 뷰모델을 생성하여 리턴한다.
        String elementName = "";
        if (umlElement instanceof NamedElement) {
            elementName = ((NamedElement) umlElement).getName();
        }
        String stereotype = ProjectUtil.getStereotypeText(umlElement);

        // 이름 뷰모델을 생성
        if (elementName != null && !elementName.equals(UICoreConstant.EMPTY_STRING)) {

            if (!hasLabelType(node, LabelType.LABEL)) {
                LabelNode nameLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                nameLabel.setId(UUID.randomUUID().toString());
                nameLabel.setParent(node);
                nameLabel.setX(0);
                nameLabel.setY(0);
                nameLabel.setType(LabelType.LABEL);
                nameLabel.setUmlModel(umlElement);
                node.getCompartmentList().add(nameLabel);
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.LABEL);
        }

        // 스테레오 타입 뷰모델을 생성
        if (!UICoreConstant.EMPTY_STRING.equals(stereotype)) {

            if (!hasLabelType(node, LabelType.STEREOTYPE)) {
                LabelNode stereotypeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                stereotypeLabel.setId(UUID.randomUUID().toString());
                stereotypeLabel.setParent(node);
                stereotypeLabel.setX(0);
                stereotypeLabel.setY(0);
                stereotypeLabel.setType(LabelType.STEREOTYPE);
                stereotypeLabel.setUmlModel(umlElement);
                node.getCompartmentList().add(stereotypeLabel);
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.STEREOTYPE);
        }
    }

    /**
     * setModelInfo
     *  
     * @param notationNode void
     */
    public static void setModelInfo(NotationNode notationNode) {
        final NotationNode viewModel = notationNode;

        Object object = viewModel.getUmlModel();
        if (!(object instanceof NamedElement)) {
            return;
        }
        NamedElement namedElement = (NamedElement) object;

        List<Object> list = new ArrayList<Object>();

        List<Object> listStereotypes = new ArrayList<Object>();
        EList<String> keywords = namedElement.getKeywords();
        if (0 < keywords.size()) {
            listStereotypes.add(keywords); // Element Keywords
        }
        EList<Stereotype> stereotypes = namedElement.getAppliedStereotypes();
        if (0 < stereotypes.size()) {
            listStereotypes.add(stereotypes); // element Stereotypes
        }

        if (namedElement instanceof Actor) {
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            }
        } else {
            if (0 < listStereotypes.size()) {
                if (hasNodeType(NodeType.STEREOTYPE, viewModel))
                    addCompartmentList(viewModel, NodeType.STEREOTYPE, namedElement);
            }
            if (hasNodeType(NodeType.NAME, viewModel))
                addCompartmentList(viewModel, NodeType.NAME, namedElement);
        }
        boolean showOperation = false;
        if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
            .equals("true")) {
            showOperation = true;
        }
        if (showOperation) {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }
        } else {
            if (namedElement instanceof org.eclipse.uml2.uml.Component) {
                removeLabelViewAndEditPart(viewModel, NodeType.OPERATIONS);
                removeLabelViewAndEditPart(viewModel, NodeType.ATTRIBUTES);
            }
        }
        if (!(namedElement instanceof org.eclipse.uml2.uml.Component)) {
            if (namedElement instanceof org.eclipse.uml2.uml.Class
                || namedElement instanceof org.eclipse.uml2.uml.Enumeration) {
                if (hasNodeType(NodeType.ATTRIBUTES, viewModel)) {
                    addCompartmentList(viewModel, NodeType.ATTRIBUTES, namedElement);
                }
            }
            if (namedElement instanceof org.eclipse.uml2.uml.Class
                || namedElement instanceof org.eclipse.uml2.uml.Interface) {
                if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                    addCompartmentList(viewModel, NodeType.OPERATIONS, namedElement);
                }
            }

        }

        if (namedElement instanceof org.eclipse.uml2.uml.Component) {
            if (hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.PROVIDED_INTERFACES, namedElement);
            if (hasNodeType(NodeType.REQUIRED_INTERFACES, viewModel))
                addCompartmentList(viewModel, NodeType.REQUIRED_INTERFACES, namedElement);
        }
        
        if (namedElement instanceof org.eclipse.uml2.uml.Enumeration) {
            if (hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                addCompartmentList(viewModel, NodeType.ENUMERATION_LITERALS, namedElement);
            }
        }

        int childrenHeight = 0;
        NotationNode childNode = null;
        for (int i = 0; i < viewModel.getCompartmentList().size(); i++) {
            AbstractNode child = viewModel.getCompartmentList().get(i);
            childrenHeight += child.getHeight();
            childNode = (NotationNode) child;
        }
        int parentHeight = 0;
        if (viewModel.getHeight() > childrenHeight) {
            parentHeight = viewModel.getHeight();
            if (childNode != null) {
                if (childNode.getHeight() != childNode.getHeight() + parentHeight - childrenHeight) {
                    childNode.setHeight(childNode.getHeight() + parentHeight - childrenHeight);
                }
            }
        } else {
            parentHeight = childrenHeight;
        }
        if (viewModel.getHeight() != parentHeight) {
            viewModel.setHeight(parentHeight);
        }
    }

    // NotationNode의 하위 요소를 검사하여 추가.
    /**
     * addCompartmentList
     *  
     * @param viewModel
     * @param nodeType
     * @param namedElement void
     */
    private static void addCompartmentList(final NotationNode viewModel, final NodeType nodeType,
                                           final NamedElement namedElement) {
        boolean showOperation = false;
        if (PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT)
            .equals("true")) {
            showOperation = true;
        }

        NotationNode childViewModel = null;
        if(nodeType != null){
            if (nodeType.equals(NodeType.NAME)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.NAME);
                childViewModel.setParent(viewModel);
                childViewModel.setHeight(15);
                
            } else if (nodeType.equals(NodeType.STEREOTYPE)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.STEREOTYPE);
                childViewModel.setParent(viewModel);
                childViewModel.setHeight(21);
                
            } else if (nodeType.equals(NodeType.ATTRIBUTES)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.ATTRIBUTES);
                childViewModel.setParent(viewModel);
                childViewModel.setHeight(40);
                
            } else if (nodeType.equals(NodeType.OPERATIONS)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.OPERATIONS);
                childViewModel.setParent(viewModel);
                childViewModel.setHeight(40);
                
            } else if (nodeType.equals(NodeType.PROVIDED_INTERFACES)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.PROVIDED_INTERFACES);
                childViewModel.setParent(viewModel);
                if (showOperation) {
                    childViewModel.setHeight(50);
                } else {
                    childViewModel.setHeight(90);
                }
                
            } else if (nodeType.equals(NodeType.REQUIRED_INTERFACES)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.REQUIRED_INTERFACES);
                childViewModel.setParent(viewModel);
                if (showOperation) {
                    childViewModel.setHeight(50);
                } else {
                    childViewModel.setHeight(90);
                }
                
            } else if (nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
                childViewModel = UMLDiagramFactory.eINSTANCE.createNotationNode();
                childViewModel.setNodeType(NodeType.ENUMERATION_LITERALS);
                childViewModel.setParent(viewModel);
                childViewModel.setHeight(40);
            }
        }
        childViewModel.setUmlModel(namedElement);

        if (childViewModel.getNodeType().equals(NodeType.STEREOTYPE)) {
            viewModel.getCompartmentList().add(0, childViewModel);
            if (viewModel.getNodeType().equals(NodeType.ACTOR)) {}
            viewModel.setHeight(viewModel.getHeight() + 15);

        } else if (childViewModel.getNodeType().equals(NodeType.NAME)) {
            if (hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                viewModel.getCompartmentList().add(0, childViewModel);
            } else {
                viewModel.getCompartmentList().add(1, childViewModel);
            }
        } else if (nodeType.equals(NodeType.ATTRIBUTES) || nodeType.equals(NodeType.ENUMERATION_LITERALS)) {
            int cnt = 0;
            if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                cnt++;
            }
            if (!hasNodeType(NodeType.NAME, viewModel)) {
                cnt++;
            }
            if (hasNodeType(NodeType.ATTRIBUTES, viewModel) || hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                viewModel.getCompartmentList().add(cnt, childViewModel);
            }
            if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                    viewModel.setHeight(viewModel.getHeight() + 40);
                }
            }
        } else if (nodeType.equals(NodeType.OPERATIONS)) {
            int cnt = 0;
            if (!hasNodeType(NodeType.STEREOTYPE, viewModel)) {
                cnt++;
            }
            if (!hasNodeType(NodeType.NAME, viewModel)) {
                cnt++;
            }
            if (!hasNodeType(NodeType.ATTRIBUTES, viewModel) || !hasNodeType(NodeType.ENUMERATION_LITERALS, viewModel)) {
                cnt++;
            }
            if (hasNodeType(NodeType.OPERATIONS, viewModel)) {
                viewModel.getCompartmentList().add(cnt, childViewModel);
            }
            if (viewModel.getNodeType().equals(NodeType.COMPONENT)) {
                if (!hasNodeType(NodeType.PROVIDED_INTERFACES, viewModel)) {
                    viewModel.setHeight(viewModel.getHeight() + 40);
                }
            }
        } else {
            viewModel.getCompartmentList().add(childViewModel);
        }
    }

    /**
     * 
     * 라벨의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    private static boolean hasNodeType(NodeType nodeType, NotationNode notationNode) {
        NotationNode node = notationNode;
        for (int i = 0; i < node.getCompartmentList().size(); i++) {
            AbstractNode child = node.getCompartmentList().get(i);
            if (child.getNodeType().equals(nodeType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * 값이 없는 경우 뷰모델과 에디트파트를 지워준다
     * 
     * @param node
     * @param labelType
     *            void
     */
    public static void removeLabelViewAndEditPart(final NotationNode node, NodeType nodeType) {

        NotationNode removeTarget = null;

        for (int i = 0; i < node.getCompartmentList().size(); i++) {
            AbstractNode label = node.getCompartmentList().get(i);
            if (label.getNodeType().equals(nodeType)) {
                removeTarget = (NotationNode) label;
                break;
            }
        }
        final NotationNode removeElement;
        if (removeTarget != null) {
            removeElement = removeTarget;
            if (node.getCompartmentList().contains(removeElement)) {
                // DomainUtil.run(new TransactionalAction() {
                // /**
                // * @see
                // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                // */
                // @Override
                // public void doExecute() {

                IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage()
                    .getEditors();
                for (int i = 0; i < editors.length; i++) {
                    IEditorPart editorPart = editors[i];
                    if (editorPart instanceof AbstractDiagramEditor
                        && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram()
                            .getType())) {
                        AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                        GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                        ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                        List<EditPart> diagramEditParts = root.getChildren();

                        EditPart targetEditPart = findTargetEditPart(diagramEditParts, removeElement);
                        if (targetEditPart != null) {
                            // System.out.println(removeEditPart(targetEditPart,
                            // diagramEditParts));
                        }
                    }
                }
                // 에디트파트를 찾아서 지운다.
                // List<EditPart> children = getChildren();
                // EditPart removeChild = null;
                // for (EditPart child : children) {
                // if (child.getModel().equals(removeElement)) {
                // removeChild = (child);
                // }
                // }
                // if(getChildren().contains(removeChild)) {
                // getFigure().remove(
                // ((GraphicalEditPart)removeChild).getFigure() );
                // getChildren().remove(removeChild);
                //                            
                node.setHeight(node.getHeight() - removeElement.getHeight());
                // }
                node.getCompartmentList().remove(removeElement);
            }
            // });
            // }
        }
    }

    /**
     * removeEditPart
     *  
     * @param targetEditPart
     * @param editParts
     * @return boolean
     */
    private static boolean removeEditPart(EditPart targetEditPart, List<EditPart> editParts) {
        EditPart removeChild = null;
        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = editParts.get(i);
            if (child.equals(targetEditPart)) {
                removeChild = child;
            }
            removeEditPart(targetEditPart, child.getChildren());
        }
        if (removeChild != null) {
            if (editParts.contains(removeChild)) {
                editParts.remove(removeChild);
                // System.out.println(removeChild);
                return true;
            }
        }
        return false;
    }

    /**
     * findTargetEditPart
     *  
     * @param editParts
     * @param removeElement
     * @return EditPart
     */
    private static EditPart findTargetEditPart(List<EditPart> editParts, NotationNode removeElement) {

        EditPart removeChild = null;

        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = editParts.get(i);
            if (child.getModel().equals(removeElement)) {
                removeChild = child;
                return child;
            } else {
                removeChild = findTargetEditPart(child.getChildren(), removeElement);
                if (removeChild != null)
                    return removeChild;
            }
        }
        return removeChild;
    }

}
