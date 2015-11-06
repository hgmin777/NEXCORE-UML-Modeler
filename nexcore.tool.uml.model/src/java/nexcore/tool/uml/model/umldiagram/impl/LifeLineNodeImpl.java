/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NotationNode;
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
 * <em><b>Life Line Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl#getLine <em>Line</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.LifeLineNodeImpl#getBehaviorList <em>Behavior List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : LifeLineNodeImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class LifeLineNodeImpl extends NotationNodeImpl implements LifeLineNode {
    /**
     * The cached value of the '{@link #getLine() <em>Line</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLine()
     * @generated
     * @ordered
     */
    protected Line line;

    /**
     * The cached value of the '{@link #getBehaviorList() <em>Behavior List</em>}' containment reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getBehaviorList()
     * @generated
     * @ordered
     */
    protected EList<NotationNode> behaviorList;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected LifeLineNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.LIFE_LINE_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Line getLine() {
        return line;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLine(Line newLine, NotificationChain msgs) {
        Line oldLine = line;
        line = newLine;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.LIFE_LINE_NODE__LINE, oldLine, newLine);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLine(Line newLine) {
        if (newLine != line) {
            NotificationChain msgs = null;
            if (line != null)
                msgs = ((InternalEObject)line).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.LIFE_LINE_NODE__LINE, null, msgs);
            if (newLine != null)
                msgs = ((InternalEObject)newLine).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UMLDiagramPackage.LIFE_LINE_NODE__LINE, null, msgs);
            msgs = basicSetLine(newLine, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.LIFE_LINE_NODE__LINE, newLine, newLine));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<NotationNode> getBehaviorList() {
        if (behaviorList == null) {
            behaviorList = new EObjectContainmentEList<NotationNode>(NotationNode.class, this, UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST);
        }
        return behaviorList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.LIFE_LINE_NODE__LINE:
                return basicSetLine(null, msgs);
            case UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST:
                return ((InternalEList<?>)getBehaviorList()).basicRemove(otherEnd, msgs);
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
            case UMLDiagramPackage.LIFE_LINE_NODE__LINE:
                return getLine();
            case UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST:
                return getBehaviorList();
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
            case UMLDiagramPackage.LIFE_LINE_NODE__LINE:
                setLine((Line)newValue);
                return;
            case UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST:
                getBehaviorList().clear();
                getBehaviorList().addAll((Collection<? extends NotationNode>)newValue);
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
            case UMLDiagramPackage.LIFE_LINE_NODE__LINE:
                setLine((Line)null);
                return;
            case UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST:
                getBehaviorList().clear();
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
            case UMLDiagramPackage.LIFE_LINE_NODE__LINE:
                return line != null;
            case UMLDiagramPackage.LIFE_LINE_NODE__BEHAVIOR_LIST:
                return behaviorList != null && !behaviorList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // LifeLineNodeImpl
