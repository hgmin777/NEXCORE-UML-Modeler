/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.edit.part.AssociationEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.property.type.MultiplicityType;
import nexcore.tool.uml.ui.property.type.VisibilityType;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : AssociationGeneralSection</li>
 * <li>작성일 : 2009. 12. 08.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class AssociationGeneralSection extends AbstractPropertySection {
    /** */
    private static final String ROLE_AT = "Role at ";

    /** relation */
    private Relation relation;

    /** association */
    private Association association;

    /** sourceProperty */
    private Property sourceProperty;

    /** targetProperty */
    private Property targetProperty;

    /** mainComposite */
    private Composite mainComposite;

    /** gridLayout */
    private GridLayout gridLayout = null;

    /** sourceSignature */
    // private CLabel sourceSignature;
    /** targetSignature */
    // private CLabel targetSignature;
    /** sourceGroup */
    private Group sourceGroup;

    /** targetGroup */
    private Group targetGroup;

    /** sourceVisibilityComposite */
    private Composite sourceVisibilityComposite;

    /** targetVisibilityComposite */
    private Composite targetVisibilityComposite;

    /** sourceAggegationKindComposite */
    private Composite sourceAggegationKindComposite;

    /** targetAggegationKindComposite */
    private Composite targetAggegationKindComposite;

    /** labelTextName */
    private Text labelTextName;

    /** buttonSourceNone */
    private Button buttonSourceNone;

    /** buttonSourceShared */
    private Button buttonSourceShared;

    /** buttonSourceComposition */
    private Button buttonSourceComposition;

    /** buttonTargetNone */
    private Button buttonTargetNone;

    /** buttonTargetShared */
    private Button buttonTargetShared;

    /** buttonTargetComposition */
    private Button buttonTargetComposition;

    /** buttonSourcePublic */
    private Button buttonSourcePublic;

    /** buttonSourceProtected */
    private Button buttonSourceProtected;

    /** buttonSourcePrivate */
    private Button buttonSourcePrivate;

    /** buttonSourcePackage */
    private Button buttonSourcePackage;

    /** buttonTargetPublic */
    private Button buttonTargetPublic;

    /** buttonTargetProtected */
    private Button buttonTargetProtected;

    /** buttonTargetPrivate */
    private Button buttonTargetPrivate;

    /** buttonTargetPackage */
    private Button buttonTargetPackage;

    /** labelTextSourceRoleName */
    private Text labelTextSourceRoleName;

    /** labelTextTargetRoleName */
    private Text labelTextTargetRoleName;

    /** comboSourceMultiplicity */
    private CCombo comboSourceMultiplicity;

    /** comboTargetMultiplicity */
    private CCombo comboTargetMultiplicity;

    /** buttonSourceNavigable */
    private Button buttonSourceNavigable;

    /** buttonTargetNavigable */
    private Button buttonTargetNavigable;

    /** buttonIsLeaf */
    private Button buttonIsLeaf;

    /** textNameModifyListener */
    private TextNameKeyListener textNameKeyListener;

    /** textNameModifyListener */
    private TextNameFocusListener textNameFocusListener;

    /** visibilitySelectionListenerSource */
    private VisibilitySelectionListener visibilitySelectionListenerSource;

    /** visibilitySelectionListenerTarget */
    private VisibilitySelectionListener visibilitySelectionListenerTarget;

    /** aggregationKindSelectionListenerSource */
    private AggregationKindSelectionListener aggregationKindSelectionListenerSource;

    /** aggregationKindSelectionListenerTarget */
    private AggregationKindSelectionListener aggregationKindSelectionListenerTarget;

    /** roleModifyListenerSource */
    private RoleModifyListener roleModifyListenerSource;

    /** roleModifyListenerTarget */
    private RoleModifyListener roleModifyListenerTarget;

    /** multiplicitySelectionListenerSource */
    private MultiplicityListener multiplicitySelectionListenerSource;

    /** multiplicitySelectionListenerTarget */
    private MultiplicityListener multiplicitySelectionListenerTarget;

    /** navigableSelectionListenerSource */
    private NavigableSelectionListener navigableSelectionListenerSource;

    /** navigableSelectionListenerTarget */
    private NavigableSelectionListener navigableSelectionListenerTarget;

    /** leafSelectionListener */
    private LeafSelectionListener leafSelectionListener;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        this.initComposite(parent);
    }

    /**
     * 컴포지트 초기화
     * 
     * @param parent
     *            void
     */
    private void initComposite(Composite parent) {
        GridData gridData;
        this.mainComposite = this.getWidgetFactory().createFlatFormComposite(parent);
        this.gridLayout = new GridLayout(4, false);
        this.mainComposite.setLayout(this.gridLayout);

        // sourceSignature =
        // this.getWidgetFactory().createCLabel(this.mainComposite,
        // UICoreConstant.UMLSECTION_CONSTANTS__EMPTY_TEXT);
        // gridData = new GridData();
        // gridData.horizontalAlignment = GridData.BEGINNING;
        // gridData.horizontalSpan = 2;
        // this.sourceSignature.setLayoutData(gridData);

        // targetSignature =
        // this.getWidgetFactory().createCLabel(this.mainComposite,
        // UICoreConstant.UMLSECTION_CONSTANTS__EMPTY_TEXT);
        // gridData = new GridData();
        // gridData.horizontalAlignment = GridData.BEGINNING;
        // gridData.horizontalSpan = 2;
        // this.targetSignature.setLayoutData(gridData);

        @SuppressWarnings("unused")
        CLabel label = this.getWidgetFactory().createCLabel(this.mainComposite,
            UMLMessage.LABEL_LABEL + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.horizontalSpan = 3;
        this.labelTextName = getWidgetFactory().createText(this.mainComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        this.labelTextName.setLayoutData(gridData);

        sourceGroup = this.getWidgetFactory().createGroup(this.mainComposite, UMLMessage.LABEL_SOURCE);
        this.gridLayout = new GridLayout(2, false);
        this.sourceGroup.setLayout(gridLayout);

        gridData = new GridData();
        gridData.horizontalSpan = 2;
        this.sourceGroup.setLayoutData(gridData);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_VISIBILITY + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);

        this.sourceVisibilityComposite = this.getWidgetFactory().createComposite(this.sourceGroup);
        this.sourceVisibilityComposite.setLayout(new GridLayout(4, false));
        buttonSourcePublic = this.getWidgetFactory().createButton(this.sourceVisibilityComposite,
            VisibilityType.PUBLIC.toString(),
            SWT.RADIO);
        buttonSourceProtected = this.getWidgetFactory().createButton(this.sourceVisibilityComposite,
            VisibilityType.PROTECTED.toString(),
            SWT.RADIO);
        buttonSourcePrivate = this.getWidgetFactory().createButton(this.sourceVisibilityComposite,
            VisibilityType.PRIVATE.toString(),
            SWT.RADIO);
        buttonSourcePackage = this.getWidgetFactory().createButton(this.sourceVisibilityComposite,
            VisibilityType.PACKAGE.toString(),
            SWT.RADIO);

        targetGroup = this.getWidgetFactory().createGroup(this.mainComposite, UMLMessage.LABEL_TARGET);

        this.gridLayout = new GridLayout(2, false);
        this.targetGroup.setLayout(gridLayout);

        gridData = new GridData(GridData.FILL_VERTICAL);
        gridData.horizontalSpan = 2;
        targetGroup.setLayoutData(gridData);

        label = this.getWidgetFactory().createCLabel(this.targetGroup,
            UMLMessage.LABEL_VISIBILITY + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);

        this.targetVisibilityComposite = this.getWidgetFactory().createComposite(this.targetGroup);
        this.targetVisibilityComposite.setLayout(new GridLayout(4, false));
        buttonTargetPublic = this.getWidgetFactory().createButton(this.targetVisibilityComposite,
            VisibilityType.PUBLIC.toString(),
            SWT.RADIO);
        buttonTargetProtected = this.getWidgetFactory().createButton(this.targetVisibilityComposite,
            VisibilityType.PROTECTED.toString(),
            SWT.RADIO);
        buttonTargetPrivate = this.getWidgetFactory().createButton(this.targetVisibilityComposite,
            VisibilityType.PRIVATE.toString(),
            SWT.RADIO);
        buttonTargetPackage = this.getWidgetFactory().createButton(this.targetVisibilityComposite,
            VisibilityType.PACKAGE.toString(),
            SWT.RADIO);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_AGGREGATION + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.sourceAggegationKindComposite = this.getWidgetFactory().createComposite(this.sourceGroup);
        this.sourceAggegationKindComposite.setLayout(new GridLayout(3, false));
        buttonSourceNone = this.getWidgetFactory().createButton(this.sourceAggegationKindComposite,
            UMLMessage.LABEL_NONE,
            SWT.RADIO);
        buttonSourceShared = this.getWidgetFactory().createButton(this.sourceAggegationKindComposite,
            UMLMessage.LABEL_SHARED,
            SWT.RADIO);
        buttonSourceComposition = this.getWidgetFactory().createButton(this.sourceAggegationKindComposite,
            UMLMessage.LABEL_COMPOSITE,
            SWT.RADIO);

        label = this.getWidgetFactory().createCLabel(this.targetGroup,
            UMLMessage.LABEL_AGGREGATION + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.targetAggegationKindComposite = this.getWidgetFactory().createComposite(this.targetGroup);
        this.targetAggegationKindComposite.setLayout(new GridLayout(3, false));
        buttonTargetNone = this.getWidgetFactory().createButton(this.targetAggegationKindComposite,
            UMLMessage.LABEL_NONE,
            SWT.RADIO);
        buttonTargetShared = this.getWidgetFactory().createButton(this.targetAggegationKindComposite,
            UMLMessage.LABEL_SHARED,
            SWT.RADIO);
        buttonTargetComposition = this.getWidgetFactory().createButton(this.targetAggegationKindComposite,
            UMLMessage.LABEL_COMPOSITE,
            SWT.RADIO);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_ROLE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.labelTextSourceRoleName = getWidgetFactory().createText(this.sourceGroup,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        this.labelTextSourceRoleName.setLayoutData(gridData);

        label = this.getWidgetFactory().createCLabel(this.targetGroup,
            UMLMessage.LABEL_ROLE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.labelTextTargetRoleName = getWidgetFactory().createText(this.targetGroup,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        this.labelTextTargetRoleName.setLayoutData(gridData);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_MULTIPLICITY + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.comboSourceMultiplicity = getWidgetFactory().createCCombo(this.sourceGroup, SWT.NONE);
        this.comboSourceMultiplicity.setItems(UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES);

        label = this.getWidgetFactory().createCLabel(this.targetGroup,
            UMLMessage.LABEL_MULTIPLICITY + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        this.comboTargetMultiplicity = getWidgetFactory().createCCombo(this.targetGroup, SWT.NONE);
        this.comboTargetMultiplicity.setItems(UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_NAVIGABLE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        buttonSourceNavigable = this.getWidgetFactory().createButton(this.sourceGroup,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.CHECK);

        label = this.getWidgetFactory().createCLabel(this.targetGroup,
            UMLMessage.LABEL_NAVIGABLE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        buttonTargetNavigable = this.getWidgetFactory().createButton(this.targetGroup,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.CHECK);

        label = this.getWidgetFactory().createCLabel(this.sourceGroup,
            UMLMessage.LABEL_LEAF + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        buttonIsLeaf = this.getWidgetFactory().createButton(this.sourceGroup,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.CHECK);

        textNameKeyListener = new TextNameKeyListener();
        textNameFocusListener = new TextNameFocusListener();

        visibilitySelectionListenerSource = new VisibilitySelectionListener(this.sourceProperty);
        visibilitySelectionListenerTarget = new VisibilitySelectionListener(this.targetProperty);

        aggregationKindSelectionListenerSource = new AggregationKindSelectionListener();
        aggregationKindSelectionListenerTarget = new AggregationKindSelectionListener();

        multiplicitySelectionListenerSource = new MultiplicityListener();
        multiplicitySelectionListenerTarget = new MultiplicityListener();

        roleModifyListenerSource = new RoleModifyListener();
        roleModifyListenerTarget = new RoleModifyListener();

        navigableSelectionListenerSource = new NavigableSelectionListener();
        navigableSelectionListenerTarget = new NavigableSelectionListener();

        leafSelectionListener = new LeafSelectionListener();
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();

        if (input instanceof EditPart) {
            if (input instanceof AssociationEditPart) {
                this.relation = (Relation) ((AssociationEditPart) input).getModel();
                this.association = (Association) relation.getUmlModel();
            } else if (input instanceof LabelNodeEditPart) {
                this.relation = (Relation) ((AbstractView) (((LabelNodeEditPart) input).getModel())).getOwner();
                this.association = (Association) relation.getUmlModel();
            } else if (input instanceof CompartmentLabelNodeEditPart) {
                if (null == ((AbstractView) ((AbstractView) ((CompartmentLabelNodeEditPart) input).getModel()).getParent())) {
                    return;
                }
                this.relation = (Relation) ((AbstractView) ((AbstractView) ((CompartmentLabelNodeEditPart) input).getModel()).getParent()).getParent();
                this.association = (Association) relation.getUmlModel();
            } else {
                return;
            }
        } else if (input instanceof ITreeNode) {
            this.relation = null;
            EObject model = ((ITreeNode) input).getEObject();
            if (model instanceof Association) {
                this.association = (Association) model;
            } else {
                return;
            }
        } else {
            return;
        }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh() {
        this.unsetListener();

        if (null == this.association) {
            return;
        }

        if (this.association.getMemberEnds().size() <= 1) {
            return;
        }

        this.sourceProperty = this.association.getMemberEnds().get(0);
        this.targetProperty = this.association.getMemberEnds().get(1);
        if (DomainUtil.isProxy(sourceProperty) || DomainUtil.isProxy(targetProperty)) {
        	return;
        }

        // this.sourceSignature.setText(this.sourceProperty.getType().getName());
        // this.targetSignature.setText(this.targetProperty.getType().getName());
        // this.sourceSignature.setImage(UICoreConstant.UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER.getImage(this.sourceProperty.getType()));
        // this.targetSignature.setImage(UICoreConstant.UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER.getImage(this.targetProperty.getType()));

        this.labelTextName.setText(this.association.getName());

        if (null != this.sourceProperty.getType()) {
        	this.sourceGroup.setText(ROLE_AT + this.sourceProperty.getType().getName());
        }
        if (null != this.targetProperty.getType()) {
        	this.targetGroup.setText(ROLE_AT + this.targetProperty.getType().getName());
        }

        VisibilityKind sourceVisibilityKind = this.sourceProperty.getVisibility();
        VisibilityKind targetVisibilityKind = this.targetProperty.getVisibility();
        if (sourceVisibilityKind.equals(VisibilityKind.PUBLIC_LITERAL)) {
            this.buttonSourcePublic.setSelection(true);
        } else if (sourceVisibilityKind.equals(VisibilityKind.PROTECTED_LITERAL)) {
            this.buttonSourceProtected.setSelection(true);
        } else if (sourceVisibilityKind.equals(VisibilityKind.PRIVATE_LITERAL)) {
            this.buttonSourcePrivate.setSelection(true);
        } else {
            this.buttonSourcePackage.setSelection(true);
        }

        if (targetVisibilityKind.equals(VisibilityKind.PUBLIC_LITERAL)) {
            this.buttonTargetPublic.setSelection(true);
        } else if (targetVisibilityKind.equals(VisibilityKind.PROTECTED_LITERAL)) {
            this.buttonTargetProtected.setSelection(true);
        } else if (targetVisibilityKind.equals(VisibilityKind.PRIVATE_LITERAL)) {
            this.buttonTargetPrivate.setSelection(true);
        } else {
            this.buttonTargetPackage.setSelection(true);
        }

        // 집계
        AggregationKind sourceKind = this.targetProperty.getAggregation();
        AggregationKind targetKind = this.sourceProperty.getAggregation();

        if (sourceKind.equals(AggregationKind.NONE_LITERAL)) {
            this.buttonSourceNone.setSelection(true);
        }
        if (sourceKind.equals(AggregationKind.SHARED_LITERAL)) {
            this.buttonSourceShared.setSelection(true);
            this.buttonTargetNone.setSelection(true);
        }
        if (sourceKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
            this.buttonSourceComposition.setSelection(true);
            this.buttonTargetNone.setSelection(true);
        }

        if (targetKind.equals(AggregationKind.NONE_LITERAL)) {
            this.buttonTargetNone.setSelection(true);
        }
        if (targetKind.equals(AggregationKind.SHARED_LITERAL)) {
            this.buttonTargetShared.setSelection(true);
            this.buttonSourceNone.setSelection(true);
        }
        if (targetKind.equals(AggregationKind.COMPOSITE_LITERAL)) {
            this.buttonTargetComposition.setSelection(true);
            this.buttonSourceNone.setSelection(true);
        }

        // 역할
        this.labelTextSourceRoleName.setText(this.sourceProperty.getName());
        this.labelTextTargetRoleName.setText(this.targetProperty.getName());

        // 다중성.
        int sourceUpperValue = sourceProperty.getUpper();
        int sourceLowerValue = sourceProperty.getLower();

        int targetUpperValue = targetProperty.getUpper();
        int targetLowerValue = targetProperty.getLower();

        if (sourceLowerValue == 0 && sourceUpperValue == -1) {
            comboSourceMultiplicity.setText(MultiplicityType.SINGLE_STAR.toString());
        } else if (sourceLowerValue == 0 && sourceUpperValue == 1) {
            comboSourceMultiplicity.setText(MultiplicityType.ZERO_TO_UNIQUE.toString());
        } else if (sourceLowerValue == 1 && sourceUpperValue == 1) {
            comboSourceMultiplicity.setText(MultiplicityType.UNIQUE.toString());
        } else if (sourceLowerValue == 1 && sourceUpperValue == -1) {
            comboSourceMultiplicity.setText(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString());
        } else {
            comboSourceMultiplicity.setText(String.valueOf(sourceLowerValue));
        }

        if (targetLowerValue == 0 && targetUpperValue == -1) {
            comboTargetMultiplicity.setText(MultiplicityType.SINGLE_STAR.toString());
        } else if (targetLowerValue == 0 && targetUpperValue == 1) {
            comboTargetMultiplicity.setText(MultiplicityType.ZERO_TO_UNIQUE.toString());
        } else if (targetLowerValue == 1 && targetUpperValue == 1) {
            comboTargetMultiplicity.setText(MultiplicityType.UNIQUE.toString());
        } else if (targetLowerValue == 1 && targetUpperValue == -1) {
            comboTargetMultiplicity.setText(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString());
        } else {
            comboTargetMultiplicity.setText(String.valueOf(targetLowerValue));
        }

        // 탐색 가능 여부
        boolean sourceNavigable = this.sourceProperty.isNavigable();
        boolean targetNavigable = this.targetProperty.isNavigable();
        this.buttonSourceNavigable.setSelection(sourceNavigable);
        this.buttonTargetNavigable.setSelection(targetNavigable);

        this.buttonIsLeaf.setSelection(this.association.isLeaf());

        super.refresh();

        this.initListener();
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sheet.section</li>
     * <li>설 명 : TextNameFocusListener</li>
     * <li>작성일 : 2010. 1. 27.</li>
     * <li>작성자 : 변영민</li>
     * </ul>
     */
    class TextNameFocusListener implements FocusListener {
        /**
         * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
         */
        public void focusGained(FocusEvent e) {
        }

        /**
         * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
         */
        public void focusLost(FocusEvent e) {
            if (association.getName().equals(labelTextName.getText())) {
                return;
            }

            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    association.setName(labelTextName.getText());
                }
            });
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sheet.section</li>
     * <li>설 명 : TextNameModifyListener</li>
     * <li>작성일 : 2010. 1. 26.</li>
     * <li>작성자 : 변영민</li>
     * </ul>
     */
    class TextNameKeyListener implements KeyListener {
        /**
         * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
         */
        public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
        }

        /**
         * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
         */
        public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
            if (e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER
                || e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER_SECOND) {
                if (association.getName().equals(labelTextName.getText())) {
                    return;
                }

                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        association.setName(labelTextName.getText());
                    }
                });
            }
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.sheet</li>
     * <li>설 명 : VisibilitySelectionListener Visibility Radio버튼 설정을 지정된
     * Property로 설정하는 리스너</li>
     * <li>작성일 : 2009. 12. 8.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    class VisibilitySelectionListener implements SelectionListener {
        /** property */
        private Property property;

        /**
         * 생성자
         * 
         * @param property
         */
        public VisibilitySelectionListener(Property property) {
            this.property = property;
        }

        public void setValue(Property property) {
            this.property = property;
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(final SelectionEvent e) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    Button button = (Button) e.getSource();
                    if (button.getSelection()) {
                        String text = button.getText();
                        VisibilityKind kind = property.getVisibility();
                        if (text.equals(VisibilityType.PUBLIC.toString())) {
                            if (!kind.equals(VisibilityKind.PUBLIC_LITERAL)) {
                                property.setVisibility(VisibilityKind.PUBLIC_LITERAL);
                            }
                        } else if (text.equals(VisibilityType.PROTECTED.toString())) {
                            if (!kind.equals(VisibilityKind.PROTECTED_LITERAL)) {
                                property.setVisibility(VisibilityKind.PROTECTED_LITERAL);
                            }
                        } else if (text.equals(VisibilityType.PRIVATE.toString())) {
                            if (!kind.equals(VisibilityKind.PRIVATE_LITERAL)) {
                                property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
                            }
                        } else {
                            if (!kind.equals(VisibilityKind.PACKAGE_LITERAL)) {
                                property.setVisibility(VisibilityKind.PACKAGE_LITERAL);
                            }
                        }
                    }
                }
            });
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.sheet</li>
     * <li>설 명 : AggregationKindSelectionListener AggregationKind Radio버튼 설정을
     * 지정된 Property로 설정하는 리스너</li>
     * <li>작성일 : 2009. 12. 8.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    class AggregationKindSelectionListener implements SelectionListener {
        /** 지정 property */
        private Property property;

        /** 반대 property */
        private Property propertyAnother;

        /** buttonNone */
        private Button buttonNone;

        /** buttonShared */
        private Button buttonShared;

        /** buttonComposition */
        private Button buttonComposition;

        /**
         * 생성자
         */
        public AggregationKindSelectionListener() {
        }

        /**
         * 생성자
         * 
         * @param property
         * @param propertyAnother
         * @param buttonNone
         * @param buttonShared
         * @param buttonComposition
         */
        public AggregationKindSelectionListener(Property property, Property propertyAnother, Button buttonNone,
        Button buttonShared, Button buttonComposition) {
            this.property = property;
            this.propertyAnother = propertyAnother;
            this.buttonNone = buttonNone;
            this.buttonShared = buttonShared;
            this.buttonComposition = buttonComposition;
        }

        /**
         * 
         * 
         * @param property
         * @param propertyAnother
         * @param buttonNone
         * @param buttonShared
         * @param buttonComposition
         *            void
         */
        public void setValue(Property property, Property propertyAnother, Button buttonNone, Button buttonShared,
                             Button buttonComposition) {
            this.property = property;
            this.propertyAnother = propertyAnother;
            this.buttonNone = buttonNone;
            this.buttonShared = buttonShared;
            this.buttonComposition = buttonComposition;
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(final SelectionEvent e) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    Button button = (Button) e.getSource();
                    if (button.getSelection()) {
                        String text = button.getText();
                        AggregationKind kind = propertyAnother.getAggregation();
                        if (text.equals(UMLMessage.getMessage(UMLMessage.LABEL_NONE))) {
                            if (!kind.equals(AggregationKind.NONE_LITERAL)) {
                                propertyAnother.setAggregation(AggregationKind.NONE_LITERAL);

                                // buttonNone.setSelection(true);
                                // buttonShared.setSelection(false);
                                // buttonComposition.setSelection(false);

                                setRelationType();
                            }
                        } else if (text.equals(UMLMessage.getMessage(UMLMessage.LABEL_SHARED))) {
                            if (!kind.equals(AggregationKind.SHARED_LITERAL)) {
                                propertyAnother.setAggregation(AggregationKind.SHARED_LITERAL);
                                property.setAggregation(AggregationKind.NONE_LITERAL);

                                buttonNone.setSelection(false);
                                buttonShared.setSelection(true);
                                buttonComposition.setSelection(false);

                                if (association.getMemberEnds().get(1) == property) {
                                    buttonSourceNone.setSelection(true);
                                    buttonSourceShared.setSelection(false);
                                    buttonSourceComposition.setSelection(false);
                                } else {
                                    buttonTargetNone.setSelection(true);
                                    buttonTargetShared.setSelection(false);
                                    buttonTargetComposition.setSelection(false);
                                }

                                setRelationType();
                            }
                        } else if (text.equals(UMLMessage.getMessage(UMLMessage.LABEL_COMPOSITE))) {
                            if (!kind.equals(AggregationKind.COMPOSITE_LITERAL)) {
                                propertyAnother.setAggregation(AggregationKind.COMPOSITE_LITERAL);
                                property.setAggregation(AggregationKind.NONE_LITERAL);

                                buttonNone.setSelection(false);
                                buttonShared.setSelection(false);
                                buttonComposition.setSelection(true);

                                if (association.getMemberEnds().get(1) == property) {
                                    buttonSourceNone.setSelection(true);
                                    buttonSourceShared.setSelection(false);
                                    buttonSourceComposition.setSelection(false);
                                } else {
                                    buttonTargetNone.setSelection(true);
                                    buttonTargetShared.setSelection(false);
                                    buttonTargetComposition.setSelection(false);
                                }
                                setRelationType();
                            }
                        }
                    }
                }
            });
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.sheet</li>
     * <li>설 명 : RoleModifyListener Label리스너</li>
     * <li>작성일 : 2009. 12. 8.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    class RoleModifyListener implements ModifyListener {
        /** property */
        private Property property;

        /**
         * 생성자
         */
        public RoleModifyListener() {
        }

        /**
         * 생성자
         * 
         * @param property
         */
        public RoleModifyListener(Property property) {
            this.property = property;
        }

        public void setValue(Property property) {
            this.property = property;
        }

        /**
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(final ModifyEvent e) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    Text text = (Text) e.getSource();
                    if (text.getText().equals(property.getName())) {
                        return;
                    }
                    property.setName(text.getText());
                }
            });
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.sheet</li>
     * <li>설 명 : MultiplicitySelectionListener</li>
     * <li>작성일 : 2009. 12. 9.</li>
     * <li>작성자 : 송주경</li>
     * </ul>
     */
    class MultiplicityListener implements SelectionListener, KeyListener, FocusListener {

        public void keyPressed(KeyEvent e) {
            if (e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER
                || e.keyCode == UICoreConstant.UMLSECTION_CONSTANTS__KEYCODE_ENTER_SECOND) {
                CCombo combo = (CCombo) e.getSource();
                String text = combo.getText();

                try {
                    final int value = new Integer(text).intValue();
                    final Property property = this.property;
                    if (value > 0) {

                        DomainUtil.run(new TransactionalAction() {
                            @Override
                            public void doExecute() {
                                property.setLower(value);
                                property.setUpper(value);
                            }
                        });
                    }
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        public void focusGained(FocusEvent e) {
            // TODO Auto-generated method stub

        }

        public void focusLost(FocusEvent e) {
            CCombo combo = (CCombo) e.getSource();
            String text = combo.getText();

            try {
                final int value = new Integer(text).intValue();
                final Property property = this.property;
                if (value > 0) {

                    DomainUtil.run(new TransactionalAction() {
                        @Override
                        public void doExecute() {
                            property.setLower(value);
                            property.setUpper(value);
                        }
                    });
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }

        }

        /** property */
        private Property property;

        /**
         * 생성자
         */
        public MultiplicityListener() {
        }

        /**
         * 생성자
         * 
         * @param property
         */
        public MultiplicityListener(Property property) {
            this.property = property;
        }

        /**
         * 
         * 
         * @param property
         *            void
         */
        public void setValue(Property property) {
            this.property = property;
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(final SelectionEvent e) {
//            DomainUtil.run(new TransactionalAction() {
//                /**
//                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
//                 */
//                @Override
//                public void doExecute() {
//                    CCombo combo = (CCombo) e.getSource();
//                    String t = combo.getText();
//                    if (combo.getSelectionIndex() == -1) {
//                        property.setLower(1);
//                        property.setUpper(1);
//                        combo.setText(MultiplicityType.UNIQUE.toString());
//                    }
//                }
//            });
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(final SelectionEvent e) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    CCombo combo = (CCombo) e.getSource();
                    String comboValue = combo.getText();
                    if (comboValue.equals(MultiplicityType.SINGLE_STAR.toString())) {
                        property.setLower(0);
                        property.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                    } else if (comboValue.equals(MultiplicityType.ZERO_TO_UNIQUE.toString())) {
                        property.setLower(0);
                        property.setUpper(1);
                    } else if (comboValue.equals(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString())) {
                        property.setLower(1);
                        property.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                    } else {
                        property.setLower(1);
                        property.setUpper(1);
                    }
                }
            });
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.sheet</li>
     * <li>설 명 : NavigableSelectionListner Association Property의 방향 변경 버튼 리스너</li>
     * <li>작성일 : 2009. 12. 8.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    class NavigableSelectionListener implements SelectionListener {

        /** associaton */
        private Association association;

        /** property */
        private Property property;

        /**
         * 생성자
         */
        public NavigableSelectionListener() {
        }

        /**
         * 생성자
         * 
         * @param association
         * @param property
         */
        public NavigableSelectionListener(Association association, Property property) {
            this.association = association;
            this.property = property;
        }

        /**
         * @return the property
         */
        public Property getProperty() {
            return property;
        }

        /**
         * @param property
         *            the property to set
         */
        public void setProperty(Property property) {
            this.property = property;
        }

        /**
         * @param association
         * @param property
         */
        public void setValue(Association association, Property property) {
            this.association = association;
            this.property = property;
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(final SelectionEvent e) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    Button button = (Button) e.getSource();
                    Type type = property.getType();
                    if (button.getSelection()) {
                        if (type instanceof Actor || type instanceof UseCase) {
                            if (!association.getNavigableOwnedEnds().contains(property)) {
                                association.getNavigableOwnedEnds().add(property);
                            }
                        } else {
                            setNavigable(property, true);
                        }
                    } else {
                        if (type instanceof Actor || type instanceof UseCase) {
                            if (association.getNavigableOwnedEnds().contains(property)) {
                                association.getNavigableOwnedEnds().remove(property);
                            }
                        } else {
                            setNavigable(property, false);
                        }
                    }
                    setRelationType();
                }
            });
        }

        /**
         * 
         * 
         * @param selectedProperty
         * @param navigable
         *            void
         */
        private void setNavigable(Property selectedProperty, boolean navigable) {
            StructuredClassifier element = null;
            Interface iElement = null;
            DataType dElement = null;
            Artifact aElement = null;

            if (navigable) {
                if (association.getMemberEnds().get(0).equals(selectedProperty)) {
                    if (association.getMemberEnds().get(1).getType() instanceof Interface) {
                        iElement = (Interface) association.getMemberEnds().get(1).getType();
                    } else if (association.getMemberEnds().get(1).getType() instanceof DataType) {
                        dElement = (DataType) association.getMemberEnds().get(1).getType();
                    } else if (association.getMemberEnds().get(1).getType() instanceof Artifact) {
                        aElement = (Artifact) association.getMemberEnds().get(1).getType();
                    } else {
                        element = (StructuredClassifier) association.getMemberEnds().get(1).getType();
                    }
                } else {
                    if (association.getMemberEnds().get(0).getType() instanceof Interface) {
                        iElement = (Interface) association.getMemberEnds().get(0).getType();
                    } else if (association.getMemberEnds().get(0).getType() instanceof DataType) {
                        dElement = (DataType) association.getMemberEnds().get(0).getType();
                    } else if (association.getMemberEnds().get(0).getType() instanceof Artifact) {
                        aElement = (Artifact) association.getMemberEnds().get(0).getType();
                    } else {
                        element = (StructuredClassifier) association.getMemberEnds().get(0).getType();
                    }

                }

                if (element != null) {
                    if (!element.getOwnedAttributes().contains(selectedProperty)) {
                        element.getOwnedAttributes().add(selectedProperty);
                    }
                }
                if (iElement != null) {
                    if (!iElement.getOwnedAttributes().contains(selectedProperty)) {
                        iElement.getOwnedAttributes().add(selectedProperty);
                    }
                }
                if (dElement != null) {
                    if (!dElement.getOwnedAttributes().contains(selectedProperty)) {
                        dElement.getOwnedAttributes().add(selectedProperty);
                    }
                }
                if (aElement != null) {
                    if (!aElement.getOwnedAttributes().contains(selectedProperty)) {
                        aElement.getOwnedAttributes().add(selectedProperty);
                    }
                }
                this.association.getOwnedEnds().remove(selectedProperty);
            } else {
                if (selectedProperty.getOwner() instanceof Interface) {
                    ((Interface) selectedProperty.getOwner()).getOwnedAttributes().remove(selectedProperty);
                } else if (selectedProperty.getOwner() instanceof DataType) {
                    ((DataType) selectedProperty.getOwner()).getOwnedAttributes().remove(selectedProperty);
                } else if (selectedProperty.getOwner() instanceof Artifact) {
                    ((Artifact) selectedProperty.getOwner()).getOwnedAttributes().remove(selectedProperty);
                } else {
                    ((StructuredClassifier) selectedProperty.getOwner()).getOwnedAttributes().remove(selectedProperty);
                }

                if (!this.association.getOwnedEnds().contains(selectedProperty)) {
                    this.association.getOwnedEnds().add(selectedProperty);
                }
            }
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sheet.section</li>
     * <li>설 명 : LeafSelectionListener</li>
     * <li>작성일 : 2010. 1. 18.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    class LeafSelectionListener implements SelectionListener {
        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            if (buttonIsLeaf.getSelection() == association.isLeaf()) {
                return;
            }

            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    association.setIsLeaf(buttonIsLeaf.getSelection());
                }
            });
        }

        /**
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
        }
    }

    /**
     * 컨트롤에 이벤트 핸들러 설정 void
     */
    private void initListener() {

        this.labelTextName.addKeyListener(textNameKeyListener);
        this.labelTextName.addFocusListener(textNameFocusListener);

        visibilitySelectionListenerSource.setValue(this.sourceProperty);
        visibilitySelectionListenerTarget.setValue(this.targetProperty);

        this.buttonSourcePublic.addSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourceProtected.addSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourcePrivate.addSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourcePackage.addSelectionListener(visibilitySelectionListenerSource);

        this.buttonTargetPublic.addSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetProtected.addSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetPrivate.addSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetPackage.addSelectionListener(visibilitySelectionListenerTarget);

        aggregationKindSelectionListenerSource.setValue(this.sourceProperty,
            this.targetProperty,
            this.buttonTargetNone,
            this.buttonTargetShared,
            this.buttonTargetComposition);
        aggregationKindSelectionListenerTarget.setValue(this.targetProperty,
            this.sourceProperty,
            this.buttonSourceNone,
            this.buttonSourceShared,
            this.buttonSourceComposition);

        this.buttonSourceNone.addSelectionListener(aggregationKindSelectionListenerSource);
        this.buttonSourceShared.addSelectionListener(aggregationKindSelectionListenerSource);
        this.buttonSourceComposition.addSelectionListener(aggregationKindSelectionListenerSource);

        this.buttonTargetNone.addSelectionListener(aggregationKindSelectionListenerTarget);
        this.buttonTargetShared.addSelectionListener(aggregationKindSelectionListenerTarget);
        this.buttonTargetComposition.addSelectionListener(aggregationKindSelectionListenerTarget);

        multiplicitySelectionListenerSource.setValue(this.sourceProperty);
        multiplicitySelectionListenerTarget.setValue(this.targetProperty);

        this.comboSourceMultiplicity.addSelectionListener(multiplicitySelectionListenerSource);
        this.comboSourceMultiplicity.addKeyListener(multiplicitySelectionListenerSource);
        this.comboSourceMultiplicity.addFocusListener(multiplicitySelectionListenerSource);
        this.comboTargetMultiplicity.addSelectionListener(multiplicitySelectionListenerTarget);
        this.comboTargetMultiplicity.addKeyListener(multiplicitySelectionListenerTarget);
        this.comboTargetMultiplicity.addFocusListener(multiplicitySelectionListenerTarget);

        roleModifyListenerSource.setValue(this.sourceProperty);
        roleModifyListenerTarget.setValue(this.targetProperty);
        this.labelTextSourceRoleName.addModifyListener(roleModifyListenerSource);
        this.labelTextTargetRoleName.addModifyListener(roleModifyListenerTarget);

        navigableSelectionListenerSource.setValue(this.association, this.sourceProperty);
        navigableSelectionListenerTarget.setValue(this.association, this.targetProperty);

        this.buttonSourceNavigable.addSelectionListener(navigableSelectionListenerSource);
        this.buttonTargetNavigable.addSelectionListener(navigableSelectionListenerTarget);
        this.buttonIsLeaf.addSelectionListener(leafSelectionListener);
    }

    /**
     * 위젯에 달려였는 리스너 해제 void
     */
    private void unsetListener() {
        this.labelTextName.addKeyListener(textNameKeyListener);

        this.buttonSourcePublic.removeSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourceProtected.removeSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourcePrivate.removeSelectionListener(visibilitySelectionListenerSource);
        this.buttonSourcePackage.removeSelectionListener(visibilitySelectionListenerSource);

        this.buttonTargetPublic.removeSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetProtected.removeSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetPrivate.removeSelectionListener(visibilitySelectionListenerTarget);
        this.buttonTargetPackage.removeSelectionListener(visibilitySelectionListenerTarget);

        this.buttonSourceNone.removeSelectionListener(aggregationKindSelectionListenerSource);
        this.buttonSourceShared.removeSelectionListener(aggregationKindSelectionListenerSource);
        this.buttonSourceComposition.removeSelectionListener(aggregationKindSelectionListenerSource);

        this.buttonTargetNone.removeSelectionListener(aggregationKindSelectionListenerTarget);
        this.buttonTargetShared.removeSelectionListener(aggregationKindSelectionListenerTarget);
        this.buttonTargetComposition.removeSelectionListener(aggregationKindSelectionListenerTarget);

        this.comboSourceMultiplicity.removeSelectionListener(multiplicitySelectionListenerSource);
        this.comboSourceMultiplicity.removeKeyListener(multiplicitySelectionListenerSource);
        this.comboSourceMultiplicity.removeFocusListener(multiplicitySelectionListenerSource);
        this.comboTargetMultiplicity.removeSelectionListener(multiplicitySelectionListenerTarget);
        this.comboTargetMultiplicity.removeKeyListener(multiplicitySelectionListenerTarget);
        this.comboTargetMultiplicity.removeFocusListener(multiplicitySelectionListenerTarget);

        this.labelTextSourceRoleName.removeModifyListener(roleModifyListenerSource);
        this.labelTextTargetRoleName.removeModifyListener(roleModifyListenerTarget);

        this.buttonSourceNavigable.removeSelectionListener(navigableSelectionListenerSource);
        this.buttonTargetNavigable.removeSelectionListener(navigableSelectionListenerTarget);

        this.buttonIsLeaf.removeSelectionListener(leafSelectionListener);
    }

    /**
     * 
     * void
     */
    private void setRelationType() {
        if (this.relation == null) {
            return;
        }

        EList<Property> properties = this.association.getMemberEnds();
        Property source = properties.get(0);
        Property target = properties.get(1);
        boolean bNavigable = true;
        if (source.getType() instanceof Actor || source.getType() instanceof UseCase) {
            if (0 == this.association.getNavigableOwnedEnds().size()
                || 2 == this.association.getNavigableOwnedEnds().size()) {
                bNavigable = false;
            }
        } else {
            if ((source.getType().equals(source.getOwner()) && target.getType().equals(target.getOwner()))
                || (!source.getType().equals(source.getOwner()) && !target.getType().equals(target.getOwner()))) {
                bNavigable = false;
            }
        }
        if (source.getAggregation().equals(AggregationKind.SHARED_LITERAL)
            || target.getAggregation().equals(AggregationKind.SHARED_LITERAL)) {
            if (bNavigable) {
                this.relation.setRelationType(RelationType.DIRECTED_AGGREGATION);
            } else {
                this.relation.setRelationType(RelationType.AGGREGATION);
            }
        } else if (source.getAggregation().equals(AggregationKind.NONE_LITERAL)
            || target.getAggregation().equals(AggregationKind.NONE_LITERAL)) {
            if (bNavigable) {
                this.relation.setRelationType(RelationType.DIRECTED_ASSOCIATION);
            } else {
                this.relation.setRelationType(RelationType.ASSOCIATION);
            }
        } else if (source.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL)
            || target.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL)) {
            if (bNavigable) {
                this.relation.setRelationType(RelationType.DIRECTED_COMPOSITION);
            } else {
                this.relation.setRelationType(RelationType.COMPOSITION);
            }
        }
    }
}
