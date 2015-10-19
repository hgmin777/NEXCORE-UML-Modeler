/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getBendPointList <em>Bend Point List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractConnectionImpl#getRelationType <em>Relation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : AbstractConnectionImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public abstract class AbstractConnectionImpl extends AbstractViewImpl implements AbstractConnection {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected AbstractView source;

    /**
     * The cached value of the '{@link #getSourceAnchor() <em>Source Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceAnchor()
     * @generated
     * @ordered
     */
    protected Dimension sourceAnchor;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected AbstractView target;

    /**
     * The cached value of the '{@link #getTargetAnchor() <em>Target Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetAnchor()
     * @generated
     * @ordered
     */
    protected Dimension targetAnchor;

    /**
     * The cached value of the '{@link #getBendPointList()
     * <em>Bend Point List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBendPointList()
     * @generated
     * @ordered
     */
    protected EList<BendPoint> bendPointList;

    /**
     * The cached value of the '{@link #getLabels() <em>Labels</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLabels()
     * @generated
     * @ordered
     */
    protected EList<LabelNode> labels;

    /**
     * The default value of the '{@link #getRelationType() <em>Relation Type</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRelationType()
     * @generated
     * @ordered
     */
    protected static final RelationType RELATION_TYPE_EDEFAULT = RelationType.ATTACHEMENT;

    /**
     * The cached value of the '{@link #getRelationType() <em>Relation Type</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRelationType()
     * @generated
     * @ordered
     */
    protected RelationType relationType = RELATION_TYPE_EDEFAULT;

    /**
     * This is true if the Relation Type attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean relationTypeESet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.ABSTRACT_CONNECTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public AbstractView getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSource(AbstractView newSource) {
        AbstractView oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension getSourceAnchor() {
        return sourceAnchor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceAnchor(Dimension newSourceAnchor, NotificationChain msgs) {
        Dimension oldSourceAnchor = sourceAnchor;
        sourceAnchor = newSourceAnchor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR, oldSourceAnchor, newSourceAnchor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSourceAnchor(Dimension newSourceAnchor) {
        if (newSourceAnchor != sourceAnchor) {
            NotificationChain msgs = null;
            if (sourceAnchor != null)
                msgs = ((InternalEObject)sourceAnchor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR, null, msgs);
            if (newSourceAnchor != null)
                msgs = ((InternalEObject)newSourceAnchor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR, null, msgs);
            msgs = basicSetSourceAnchor(newSourceAnchor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR, newSourceAnchor, newSourceAnchor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public AbstractView getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(AbstractView newTarget) {
        AbstractView oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Dimension getTargetAnchor() {
        return targetAnchor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetAnchor(Dimension newTargetAnchor, NotificationChain msgs) {
        Dimension oldTargetAnchor = targetAnchor;
        targetAnchor = newTargetAnchor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR, oldTargetAnchor, newTargetAnchor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setTargetAnchor(Dimension newTargetAnchor) {
        if (newTargetAnchor != targetAnchor) {
            NotificationChain msgs = null;
            if (targetAnchor != null)
                msgs = ((InternalEObject)targetAnchor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR, null, msgs);
            if (newTargetAnchor != null)
                msgs = ((InternalEObject)newTargetAnchor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR, null, msgs);
            msgs = basicSetTargetAnchor(newTargetAnchor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR, newTargetAnchor, newTargetAnchor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<BendPoint> getBendPointList() {
        if (bendPointList == null) {
            bendPointList = new EObjectContainmentEList<BendPoint>(BendPoint.class, this, UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST);
        }
        return bendPointList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelNode> getLabels() {
        if (labels == null) {
            labels = new EObjectContainmentEList<LabelNode>(LabelNode.class, this, UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS);
        }
        return labels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RelationType getRelationType() {
        return relationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setRelationType(RelationType newRelationType) {
        RelationType oldRelationType = relationType;
        relationType = newRelationType == null ? RELATION_TYPE_EDEFAULT : newRelationType;
        boolean oldRelationTypeESet = relationTypeESet;
        relationTypeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE, oldRelationType, relationType, !oldRelationTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetRelationType() {
        RelationType oldRelationType = relationType;
        boolean oldRelationTypeESet = relationTypeESet;
        relationType = RELATION_TYPE_EDEFAULT;
        relationTypeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE, oldRelationType, RELATION_TYPE_EDEFAULT, oldRelationTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetRelationType() {
        return relationTypeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR:
                return basicSetSourceAnchor(null, msgs);
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR:
                return basicSetTargetAnchor(null, msgs);
            case UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST:
                return ((InternalEList<?>)getBendPointList()).basicRemove(otherEnd, msgs);
            case UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS:
                return ((InternalEList<?>)getLabels()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE:
                return getSource();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR:
                return getSourceAnchor();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET:
                return getTarget();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR:
                return getTargetAnchor();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST:
                return getBendPointList();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS:
                return getLabels();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE:
                return getRelationType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE:
                setSource((AbstractView)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR:
                setSourceAnchor((Dimension)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET:
                setTarget((AbstractView)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR:
                setTargetAnchor((Dimension)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST:
                getBendPointList().clear();
                getBendPointList().addAll((Collection<? extends BendPoint>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS:
                getLabels().clear();
                getLabels().addAll((Collection<? extends LabelNode>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE:
                setRelationType((RelationType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE:
                setSource((AbstractView)null);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR:
                setSourceAnchor((Dimension)null);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET:
                setTarget((AbstractView)null);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR:
                setTargetAnchor((Dimension)null);
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST:
                getBendPointList().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS:
                getLabels().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE:
                unsetRelationType();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE:
                return source != null;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__SOURCE_ANCHOR:
                return sourceAnchor != null;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET:
                return target != null;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__TARGET_ANCHOR:
                return targetAnchor != null;
            case UMLDiagramPackage.ABSTRACT_CONNECTION__BEND_POINT_LIST:
                return bendPointList != null && !bendPointList.isEmpty();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__LABELS:
                return labels != null && !labels.isEmpty();
            case UMLDiagramPackage.ABSTRACT_CONNECTION__RELATION_TYPE:
                return isSetRelationType();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (relationType: ");
        if (relationTypeESet) result.append(relationType); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // AbstractConnectionImpl
