/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.ui.core.diagram.edit.policy.LabelNodeFeedbackEditPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.MarginBorders;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : CompartmentLabelNodeEditPart</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class CompartmentLabelNodeEditPart extends AbstractNotationNodeEditPart {

    /** label figure */
    private Label label = new Label();

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        LabelNode labelNode = (LabelNode) getModel();

        label = new Label();

        // compartment figure 안에 들어갈 stereotype, name 두 개의 라벨을 생성한다.
        // 따로 사이즈를 저장하지 않고 compartment figure의 사이즈에 맞게 생성하기 위해 grab 옵션과
        // alignment = Fill 을 사용했다.
        setFigureLayout();

        // 뷰모델의 라벨 타입에 따라서 스테레오타입을 생성하지 이름을 생성할지 결정한다.
        if (labelNode.getType().equals(LabelType.STEREOTYPE)) {
            label.setText(ProjectUtil.getStereotypeLabel(labelNode.getUmlModel()));
            label.setTextAlignment(PositionConstants.CENTER);
        } else if (labelNode.getType().equals(LabelType.LABEL)) {
            label.setText(((NamedElement) labelNode.getUmlModel()).getName());
            label.setTextAlignment(PositionConstants.LEFT);

        }
        label.setForegroundColor(ColorConstants.black);
        label.setBorder(new MarginBorders(0, 5, 0, 0));
        label.setToolTip(new Label(label.getText()));

        return label;
    }

    /**
     * setFigureLayout
     *   void
     */
    private void setFigureLayout() {
        LayoutManager layoutParent = ((AbstractGraphicalEditPart) this.getParent()).getFigure().getLayoutManager();
        GridData gridData;
        GridLayout gridLayout;
        if (layoutParent instanceof GridLayout) {
            gridLayout = (GridLayout) layoutParent;
            gridData = new GridData();
            gridData.grabExcessHorizontalSpace = true;
            gridData.grabExcessVerticalSpace = true;
            gridData.verticalAlignment = GridData.FILL;
            gridData.horizontalAlignment = GridData.FILL;
            gridLayout.setConstraint(label, gridData);
            gridLayout.marginHeight = gridLayout.marginWidth = 0;
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {

        try {
            super.refreshVisuals();
            
            LabelNode nodeLabel = (LabelNode) getModel();

            if (nodeLabel.getType().equals(LabelType.STEREOTYPE)) {
                label.setText(ProjectUtil.getStereotypeLabel(nodeLabel.getUmlModel()));
                label.setTextAlignment(PositionConstants.CENTER);
            } else if (nodeLabel.getType().equals(LabelType.LABEL)) {
                label.setText(((NamedElement) nodeLabel.getUmlModel()).getName());
                label.setTextAlignment(PositionConstants.CENTER);
            }
            label.setForegroundColor(new Color(null, getFontColor()));
            label.setToolTip(new Label(label.getText()));

            if (getParent() instanceof LabelNodeEditPart) {
                // ((LabelNodeEditPart)getParent()).refreshParentVisuals(false);
            }

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_NOTATION_LABEL_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

    /**
     * getFontColor
     * 
     * @return RGB
     */
    protected RGB getFontColor() {
        RGB fontColor = PreferenceUtil.INSTANCE.getNodeFontColor();
        AbstractView abstractView = (AbstractView) ((AbstractView) ((AbstractView) getModel()).getParent()).getParent();
        if (abstractView != null) {
            if (abstractView.getFontColor() != null) {
                fontColor = StringConverter.asRGB(abstractView.getFontColor());
            }
        }
        return fontColor;
    }

    /**
     * 
     * setName 메소드
     * 
     * @param name
     *            void
     */
    public void setName(String name) {
        this.label.setText(name);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DelegatingDragEditPartsTracker(this, getParent());
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();

        LabelNode node = (LabelNode) getModel();

        if (node.getParent() != null) {
            node.getParent().eAdapters().add(this);
            if (null == ((AbstractView) node.getParent()).getParent()) {
                return;
            }
            ((AbstractView) node.getParent()).getParent().eAdapters().add(this);

            ((AbstractView) node.getParent()).getUmlModel().eAdapters().add(this);

            for (Stereotype stereotype : ((Element) ((AbstractView) node.getParent()).getUmlModel()).getAppliedStereotypes()) {
                stereotype.eAdapters().add(this);
            }

            for (EAnnotation annotation : ((EModelElement) ((AbstractView) node.getParent()).getUmlModel()).getEAnnotations()) {
                annotation.eAdapters().add(this);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();

        LabelNode node = (LabelNode) getModel();

        if (node.getParent() != null) {
            node.getParent().eAdapters().remove(this);
            ((AbstractView) node.getParent()).getParent().eAdapters().remove(this);

            ((AbstractView) node.getParent()).getUmlModel().eAdapters().remove(this);

            for (Stereotype stereotype : ((Element) ((AbstractView) node.getParent()).getUmlModel()).getAppliedStereotypes()) {
                stereotype.eAdapters().remove(this);
            }

            for (EAnnotation annotation : ((EModelElement) ((AbstractView) node.getParent()).getUmlModel()).getEAnnotations()) {
                annotation.eAdapters().remove(this);
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        // refresh();
    }

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
}
