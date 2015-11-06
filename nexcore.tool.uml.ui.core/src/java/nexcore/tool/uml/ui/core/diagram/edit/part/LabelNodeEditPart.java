/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.policy.LabelNodeFeedbackEditPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.CompartmentLabelFigure;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : LabelNodeEditPart</li>
 * <li>작성일 : 2011. 2. 14.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class LabelNodeEditPart extends AbstractNotationNodeEditPart {

    /** currentLocation */
    private Point currentLocation = new Point();

    /** labelVisibility */
    private boolean labelVisibility = true;

    /** sourceAnchorPoint */
    private Point sourceAnchorPoint;

    /** targetAnchorPoint */
    private Point targetAnchorPoint;

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());

        if (getModel() != null)
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, createSizeableEditPolicy());
    }

    /**
     * createSizeableEditPolicy
     * 
     * @return EditPolicy
     */
    private EditPolicy createSizeableEditPolicy() {
        return new LabelNodeFeedbackEditPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        LabelNode labelNode = null;
        if (getModel() instanceof LabelNode) {
            labelNode = (LabelNode) getModel();
        }

        if (labelNode != null) {
            setLabelVisibility(labelNode);

            if (labelNode.getType().equals(LabelType.COMPARTMENT)) {
                return createCompartmentFigure(labelNode);
            } else {
                return createNoneComparmentFigure(labelNode);
            }
        }

        return null;
    }

    /**
     * refreshParentVisuals
     *  
     * @param refreshParent void
     */
    public void refreshParentVisuals(boolean refreshParent) {

        LabelNode nodeLabel = (LabelNode) getModel();

        if (nodeLabel.getParent() == null) {
            return;
        }
        AbstractView parent = (AbstractView) nodeLabel.getParent();
        Element umlElement = parent.getUmlModel();
        try {

            if (nodeLabel.getType().equals(LabelType.COMPARTMENT)) {

                // 스테레오 타입과 이름의 텍스트 길이를 비교해서 더 긴쪽의 길이로 라벨을 생성하여 한쪽이 짤리지 않도록
                // 한다.
                int nameLength = DiagramUtil.getWidthSize(((NamedElement) umlElement).getName(),
                    UiCorePlugin.getDefault().getFont("default"));
                int stereotypeLength = DiagramUtil.getWidthSize(ProjectUtil.getStereotypeText(umlElement),
                    UiCorePlugin.getDefault().getFont("default")) + 20;
                getFigure().setSize((nameLength > stereotypeLength ? nameLength : stereotypeLength) + 5,
                    nodeLabel.getCompartmentList().size() * 17);

                if (parent instanceof AbstractNode) {
                    // 라벨 뷰모델에 저장되어 있는 위치 값은 부모 노드의 위치와의 차이값이다. 라벨 자체의 위치 값이 저장된
                    // 것이 아님.
                    getFigure().setLocation(new Point(parent.getX() + nodeLabel.getX(), parent.getY()
                        + nodeLabel.getY()));

                } else {
                    setPreference(nodeLabel);

                    Point centerPoint = getCenterPoint(nodeLabel);
                    figure.setLocation(new Point(centerPoint.x + nodeLabel.getX(), centerPoint.y + nodeLabel.getY()));

                }

                // Empty String을 갖는 경우 없는 것과 마찬가지이므로 라벨을 삭제한다.
                List<Figure> children = getFigure().getChildren();
                List<EditPart> childEditParts = getChildren();
                EList<LabelNode> childModel = ((LabelNode) getModel()).getCompartmentList();

                List<EditPart> removeList = new ArrayList<EditPart>();
                List<IFigure> removeFigureList = new ArrayList<IFigure>();
                 

                for (EditPart childA : childEditParts) {
                    Object model = childA.getModel();
                    for( EditPart childB : childEditParts ) {
                        if( childA.equals(childB) || removeList.contains(childA) ) {
                            continue;
                        }
                        if( model.equals(childB.getModel()) ) {
                            removeList.add(childB);
                            removeFigureList.add(((GraphicalEditPart) childB).getFigure());
                        }
                    }
                }
                childEditParts.removeAll(removeList);
                children.removeAll(removeFigureList);
                
                
                

                for (Figure child : children) {
                    if (((Label) child).getText().equals(UICoreConstant.EMPTY_STRING)) {
                        getFigure().getChildren().remove(child);
                    }
                }

            } else {
                AbstractConnection abstractConnection = (AbstractConnection) nodeLabel.getOwner();

                if (nodeLabel.getType().equals(LabelType.SOURCE_ROLE)
                    || nodeLabel.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
                    Label label = (Label) getFigure();
                    if (nodeLabel.getType().equals(LabelType.SOURCE_ROLE)) {
                        VisibilityKind visibilityKind = ((Property) nodeLabel.getUmlModel()).getVisibility();
                        if (visibilityKind == VisibilityKind.PUBLIC_LITERAL) {
                            label.setText("+" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PRIVATE_LITERAL) {
                            label.setText("-" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PROTECTED_LITERAL) {
                            label.setText("#" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PACKAGE_LITERAL) {
                            label.setText("~" + ((Property) nodeLabel.getUmlModel()).getName());
                        }
                    } else if (nodeLabel.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
                        int upper, lower;
                        upper = ((Property) nodeLabel.getUmlModel()).getUpper();
                        lower = ((Property) nodeLabel.getUmlModel()).getLower();
                        label.setText(getMultiplex(upper, lower));
                    }

                    int connectionWidthSize = DiagramUtil.getWidthSize(label.getText(), UiCorePlugin.getDefault()
                        .getFont("default10"));
                    label.setSize(connectionWidthSize, nodeLabel.getHeight());
                    Point point;
                    if (sourceAnchorPoint != null) {
                        point = new Point(sourceAnchorPoint);
                    } else {
                        point = new Point(abstractConnection.getSourceAnchor().getWidth(),
                            abstractConnection.getSourceAnchor().getHeight());
                    }
                    label.setLocation(new Point(point.x + nodeLabel.getX(), point.y + nodeLabel.getY()));
                    currentLocation = new Point(point.x + nodeLabel.getX(), point.y + nodeLabel.getY());

                    label.setVisible(labelVisibility);
                    setPreference(nodeLabel);
                } else if (nodeLabel.getType().equals(LabelType.TARGET_ROLE)
                    || nodeLabel.getType().equals(LabelType.TARGET_MULTIPLEX)) {
                    Label label = (Label) getFigure();

                    if (nodeLabel.getType().equals(LabelType.TARGET_ROLE)) {
                        VisibilityKind visibilityKind = ((Property) nodeLabel.getUmlModel()).getVisibility();
                        if (visibilityKind == VisibilityKind.PUBLIC_LITERAL) {
                            label.setText("+" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PRIVATE_LITERAL) {
                            label.setText("-" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PROTECTED_LITERAL) {
                            label.setText("#" + ((Property) nodeLabel.getUmlModel()).getName());
                        } else if (visibilityKind == VisibilityKind.PACKAGE_LITERAL) {
                            label.setText("~" + ((Property) nodeLabel.getUmlModel()).getName());
                        }
                    } else if (nodeLabel.getType().equals(LabelType.TARGET_MULTIPLEX)) {
                        int upper, lower;
                        upper = ((Property) nodeLabel.getUmlModel()).getUpper();
                        lower = ((Property) nodeLabel.getUmlModel()).getLower();
                        label.setText(getMultiplex(upper, lower));
                    }

                    int connectionWidthSize = DiagramUtil.getWidthSize(label.getText(), UiCorePlugin.getDefault()
                        .getFont("default10"));
                    label.setSize(connectionWidthSize, nodeLabel.getHeight());
                    Point point;
                    if (targetAnchorPoint != null) {
                        point = new Point(targetAnchorPoint);
                    } else {
                        point = new Point(abstractConnection.getTargetAnchor().getWidth(),
                            abstractConnection.getTargetAnchor().getHeight());
                    }
                    label.setLocation(new Point(point.x + nodeLabel.getX(), point.y + nodeLabel.getY()));
                    currentLocation = new Point(point.x + nodeLabel.getX(), point.y + nodeLabel.getY());

                    label.setVisible(labelVisibility);
                    setPreference(nodeLabel);

                }
            }
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_NOTATION_LABEL_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
        if (refreshParent) {
            List<EditPart> children = getChildren();
            for (EditPart child : children) {
                child.refresh();
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        refreshParentVisuals(true);
    }

    /**
     * getCenterPoint
     *  
     * @param nodeLabel
     * @return Point
     */
    private Point getCenterPoint(LabelNode nodeLabel) {
        AbstractConnection abstractConnection = (AbstractConnection) nodeLabel.getParent();

        AbstractNode sourceNode = (AbstractNode) abstractConnection.getSource();
        AbstractNode targetNode = (AbstractNode) abstractConnection.getTarget();

        RectangleFigure sourceFigure = new RectangleFigure();
        if (sourceNode != null) {
            sourceFigure.setSize(sourceNode.getWidth(), sourceNode.getHeight());
            sourceFigure.setLocation(new Point(sourceNode.getX(), sourceNode.getY()));
        }
        RectangleFigure targetFigure = new RectangleFigure();
        if (targetNode != null) {
            targetFigure.setSize(targetNode.getWidth(), targetNode.getHeight());
            targetFigure.setLocation(new Point(targetNode.getX(), targetNode.getY()));
        }

        ChopboxAnchor sourceAnchor = new ChopboxAnchor(sourceFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(targetFigure);

        Point source = new Point(sourceAnchor.getReferencePoint().x, sourceAnchor.getReferencePoint().y);
        Point target = new Point(targetAnchor.getReferencePoint().x, targetAnchor.getReferencePoint().y);

        Point centerPoint = new Point((source.x + target.x) / 2, (source.y + target.y) / 2);
        return centerPoint;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getModelChildren()
     */
    @Override
    protected List<LabelNode> getModelChildren() {

        if (!((LabelNode) getModel()).getType().equals(LabelType.COMPARTMENT)) {
            return new ArrayList<LabelNode>();
        }
        List<LabelNode> children = new ArrayList<LabelNode>();

        final LabelNode node = (LabelNode) getModel();
        AbstractView parent = (AbstractView) node.getParent();
        Element umlElement = parent.getUmlModel();

        if(umlElement == null) {
            return new ArrayList<LabelNode>();
        }
        // 해당 모델의 이름과 스테레오 타입을 가져와서 Empty String이 아니라면 뷰모델을 생성하여 리턴한다.
        String elementName = ((NamedElement) umlElement).getName();
        String stereotype = ProjectUtil.getStereotypeText(umlElement);

        // 이름 뷰모델을 생성
        if (elementName != null && !elementName.equals(UICoreConstant.EMPTY_STRING)) {

            if (!hasLabelType(LabelType.LABEL)) {
                final LabelNode nameLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                nameLabel.setId(UUID.randomUUID().toString());
                nameLabel.setParent(node);
                nameLabel.setX(0);
                nameLabel.setY(0);
                nameLabel.setType(LabelType.LABEL);
                nameLabel.setUmlModel(umlElement);
                DomainRegistry.getEditingDomain()
                    .getCommandStack()
                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                        @Override
                        protected void doExecute() {
                            node.getCompartmentList().add(nameLabel);
                        }
                    });
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.LABEL);
        }

        // 스테레오 타입 뷰모델을 생성
        if (!stereotype.equals(UICoreConstant.EMPTY_STRING)) {

            if (!hasLabelType(LabelType.STEREOTYPE)) {
                final LabelNode stereotypeLabel = UMLDiagramFactory.eINSTANCE.createLabelNode();
                stereotypeLabel.setId(UUID.randomUUID().toString());
                stereotypeLabel.setParent(node);
                stereotypeLabel.setX(0);
                stereotypeLabel.setY(0);
                stereotypeLabel.setType(LabelType.STEREOTYPE);
                stereotypeLabel.setUmlModel(umlElement);
                DomainRegistry.getEditingDomain()
                    .getCommandStack()
                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                        @Override
                        protected void doExecute() {
                            node.getCompartmentList().add(stereotypeLabel);
                        }
                    });
            }
        } else {
            // 없는 경우 지워준다.
            removeLabelViewAndEditPart(node, LabelType.STEREOTYPE);
        }
        children.addAll(node.getCompartmentList());
        return children;
    }

    /**
     * 
     * 라벨의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    private boolean hasLabelType(LabelType labelType) {
        LabelNode node = (LabelNode) getModel();
        for (LabelNode child : node.getCompartmentList()) {
            if (child.getType().equals(labelType)) {
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
    private void removeLabelViewAndEditPart(LabelNode node, LabelType labelType) {

        // 뷰모델을 지운다.
        LabelNode removeTarget = null;
        for (LabelNode label : node.getCompartmentList()) {
            if (label.getType().equals(labelType)) {
                removeTarget = label;
            }
        }
        if (removeTarget != null) {
            if (node.getCompartmentList().contains(removeTarget)) {
                node.getCompartmentList().remove(removeTarget);
            }
        }

        // 에디트파트를 찾아서 지운다.
        List<EditPart> children = getChildren();
        EditPart removeTargetEditPart = null;
        for (EditPart child : children) {
            if (child.getModel().equals(removeTarget)) {
                removeTargetEditPart = child;
            }
        }
        if (removeTargetEditPart != null) {
            if (getChildren().contains(removeTargetEditPart)) {
                getChildren().remove(removeTargetEditPart);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();

        // 현재 뷰모델에 어댑터 등록
        LabelNode node = (LabelNode) getModel();

        if (node.getParent() != null) {

            node.getParent().eAdapters().add(this);

            // 현재 뷰모델의 UML 모델에도 어댑터 등록
            Element umlModel = ((AbstractView) node.getParent()).getUmlModel();
            if (umlModel != null) {
                umlModel.eAdapters().add(this);

                // 현재 UML 모델의 Stereotyp과 annotation에 어댑터 등록
                // annotation 안에 keyword가 존재한다.
                for (Stereotype stereotype : ((Element) umlModel).getAppliedStereotypes()) {
                    stereotype.eAdapters().add(this);
                }
                for (EAnnotation annotation : ((EModelElement) umlModel).getEAnnotations()) {
                    annotation.eAdapters().add(this);
                }
            }
        }
    }

    /**
     * activate에서 등록한 어댑터를 삭제한다.
     * 
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        LabelNode node = (LabelNode) getModel();

        if (node.getParent() != null) {
            node.getParent().eAdapters().remove(this);

            Element umlModel = ((AbstractView) node.getParent()).getUmlModel();
            if (umlModel != null) {
                umlModel.eAdapters().remove(this);

                for (Stereotype stereotype : ((Element) umlModel).getAppliedStereotypes()) {
                    stereotype.eAdapters().remove(this);
                }

                for (EAnnotation annotation : ((EModelElement) umlModel).getEAnnotations()) {
                    annotation.eAdapters().remove(this);
                }
            }
        }
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refresh();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#addChildVisual(org.eclipse.gef.EditPart,
     *      int)
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {

        super.addChildVisual(childEditPart, index);

        // if( childEditPart.getModel() instanceof LabelNode ) {
        // LabelNode labelNode = (LabelNode) childEditPart.getModel();
        // LabelType labelType = labelNode.getType();
        //
        // EditPart parentEditPart = childEditPart.getParent();
        // List<EditPart> children = parentEditPart.getChildren();
        // for( EditPart child : children ) {
        //
        // if( child.equals(childEditPart) ) {
        // continue;
        // }
        // if( child.getModel() instanceof LabelNode ) {
        // LabelNode childNode = (LabelNode) child.getModel();
        // LabelType childNodeType = childNode.getType();
        //
        // if( labelType.equals(childNodeType) ) {
        //
        // IFigure parentFigure = ((GraphicalEditPart)
        // parentEditPart).getFigure();
        // parentFigure.getChildren().remove(((GraphicalEditPart)
        // child).getFigure());
        // return;
        // }
        // }
        // }
        // }
    }

    /**
     * 
     * 이름,스테레오타입을 위한 컴파트먼트 피겨를 생성
     * 
     * @param labelNode
     * @return IFigure
     */
    private IFigure createCompartmentFigure(LabelNode labelNode) {
        AbstractView parent = (AbstractView) labelNode.getParent();
        CompartmentLabelFigure figure = new CompartmentLabelFigure();

        if (parent instanceof AbstractNode) {
            // 라벨 뷰모델에 저장되어 있는 위치 값은 부모 노드의 위치와의 차이값이다. 라벨 자체의 위치 값이 저장된 것이 아님.
            figure.setLocation(new Point(parent.getX() + labelNode.getX(), parent.getY() + labelNode.getY()));
            figure.setSize(DiagramUtil.getWidthSize(((NamedElement) parent.getUmlModel()).getName(),
                UiCorePlugin.getDefault().getFont("default")), 0);
            figure.setOpaque(false);

            GridLayout gridLayout = new GridLayout();
            gridLayout.verticalSpacing = 0;
            gridLayout.numColumns = 1;
            figure.setLayoutManager(gridLayout);

            return figure;

        } else {
            Point centerPoint = getCenterPoint(labelNode);
            figure.setLocation(new Point(centerPoint.x + labelNode.getX(), centerPoint.y + labelNode.getY()));

            return figure;
        }
    }

    /**
     * 
     * 이름 라벨을 제외한 나머지(역할, 다중성) 라벨을 위한 피겨를 생성
     * 
     * @param labelNode
     * @return IFigure
     */
    private IFigure createNoneComparmentFigure(LabelNode labelNode) {
        AbstractConnection abstractConnection = (AbstractConnection) labelNode.getParent();

        Label label = new Label();
        label.setSize(labelNode.getWidth(), labelNode.getHeight());
        label.setText(labelNode.getName());
        Point point;
        if (sourceAnchorPoint != null) {
            point = new Point(sourceAnchorPoint);
        } else {
            point = new Point(abstractConnection.getSourceAnchor().getWidth(), abstractConnection.getSourceAnchor()
                .getHeight());
        }
        label.setLocation(new Point(point.x + labelNode.getX(), point.y + labelNode.getY()));
        currentLocation = new Point(point.x + labelNode.getX(), point.y + labelNode.getY());

        label.setVisible(labelVisibility);
        return label;
    }

    /**
     * 
     * 
     * @param labelNode
     *            void
     */
    private void setLabelVisibility(LabelNode labelNode) {
        if (labelNode.getUmlModel() instanceof Property) {
            if (((Property) labelNode.getUmlModel()).isNavigable()) {
                labelVisibility = true;
            } else {
                labelVisibility = false;
            }
        } else if (labelNode.getUmlModel() instanceof ControlFlow) {
            labelVisibility = true;
        } else if (labelNode.getUmlModel() instanceof ObjectFlow) {
            labelVisibility = true;
        } else {
            labelVisibility = false;
        }
    }

    /**
     * setPreference
     *  
     * @param LabelNode void
     */
    private void setPreference(LabelNode LabelNode) {
        if (LabelNode.getType().equals(LabelType.COMPARTMENT)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                getFigure().setVisible(true);
            } else {
                getFigure().setVisible(false);
            }
        } else if (LabelNode.getType().equals(LabelType.SOURCE_ROLE)
            || LabelNode.getType().equals(LabelType.TARGET_ROLE)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                if (labelVisibility) {
                    getFigure().setVisible(true);
                }
            } else {
                getFigure().setVisible(false);
            }
        } else if (LabelNode.getType().equals(LabelType.SOURCE_MULTIPLEX)
            || LabelNode.getType().equals(LabelType.TARGET_MULTIPLEX)) {
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            String showLabel = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL);
            if (showLabel.equals(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN[0])
                || showLabel.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                if (labelVisibility) {
                    getFigure().setVisible(true);
                }
            } else {
                getFigure().setVisible(false);
            }
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
     * @param sourceAnchorPoint
     * @param targetAnchorPoint
     *            void
     */
    public void setConnectionAnchorPoints(Point sourceAnchorPoint, Point targetAnchorPoint) {
        this.sourceAnchorPoint = sourceAnchorPoint;
        this.targetAnchorPoint = targetAnchorPoint;

        refreshVisuals();
    }

    /**
     * 
     * 
     * @return Point
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * 
     * 
     * @return boolean
     */
    public boolean isLabelVisibility() {
        return labelVisibility;
    }

    /**
     * 
     * 
     * @param labelVisibility
     *            void
     */
    public void setLabelVisibility(boolean labelVisibility) {
        this.labelVisibility = labelVisibility;
    }
}
