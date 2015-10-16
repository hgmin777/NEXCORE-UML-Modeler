/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.internal.impl.ElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract View</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getUmlModel <em>Uml Model</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getFontColor <em>Font Color</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getFontName <em>Font Name</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getFontStyle <em>Font Style</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getFontSize <em>Font Size</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getX <em>X</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractViewImpl#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : AbstractViewImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public abstract class AbstractViewImpl extends ElementImpl implements AbstractView {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    protected EObject parent;

    /**
     * The cached value of the '{@link #getUmlModel() <em>Uml Model</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getUmlModel()
     * @generated
     * @ordered
     */
    protected Element umlModel;

    /**
     * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    protected static final String FILL_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    protected String fillColor = FILL_COLOR_EDEFAULT;

    /**
     * This is true if the Fill Color attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean fillColorESet;

    /**
     * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLineColor()
     * @generated
     * @ordered
     */
    protected static final String LINE_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLineColor()
     * @generated
     * @ordered
     */
    protected String lineColor = LINE_COLOR_EDEFAULT;

    /**
     * This is true if the Line Color attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean lineColorESet;

    /**
     * The default value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontColor()
     * @generated
     * @ordered
     */
    protected static final String FONT_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontColor()
     * @generated
     * @ordered
     */
    protected String fontColor = FONT_COLOR_EDEFAULT;

    /**
     * This is true if the Font Color attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean fontColorESet;

    /**
     * The default value of the '{@link #getFontName() <em>Font Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontName()
     * @generated
     * @ordered
     */
    protected static final String FONT_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFontName() <em>Font Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontName()
     * @generated
     * @ordered
     */
    protected String fontName = FONT_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getFontStyle() <em>Font Style</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontStyle()
     * @generated
     * @ordered
     */
    protected static final String FONT_STYLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFontStyle() <em>Font Style</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontStyle()
     * @generated
     * @ordered
     */
    protected String fontStyle = FONT_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getFontSize() <em>Font Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected static final int FONT_SIZE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getFontSize() <em>Font Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected int fontSize = FONT_SIZE_EDEFAULT;

    /**
     * This is true if the Font Size attribute has been set.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean fontSizeESet;

    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected int x = X_EDEFAULT;

    /**
     * This is true if the X attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean xESet;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected int y = Y_EDEFAULT;

    /**
     * This is true if the Y attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean yESet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractViewImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.ABSTRACT_VIEW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EObject getParent() {
        if (parent != null && parent.eIsProxy()) {
            InternalEObject oldParent = (InternalEObject)parent;
            parent = eResolveProxy(oldParent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLDiagramPackage.ABSTRACT_VIEW__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EObject basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setParent(EObject newParent) {
        EObject oldParent = parent;
        parent = newParent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__PARENT, oldParent, parent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Element getUmlModel() {
        if (umlModel != null && umlModel.eIsProxy()) {
            InternalEObject oldUmlModel = (InternalEObject)umlModel;
            umlModel = (Element)eResolveProxy(oldUmlModel);
            if (umlModel != oldUmlModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL, oldUmlModel, umlModel));
            }
        }
        return umlModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Element basicGetUmlModel() {
        return umlModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setUmlModel(Element newUmlModel) {
        Element oldUmlModel = umlModel;
        umlModel = newUmlModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL, oldUmlModel, umlModel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getFillColor() {
        return fillColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFillColor(String newFillColor) {
        String oldFillColor = fillColor;
        fillColor = newFillColor;
        boolean oldFillColorESet = fillColorESet;
        fillColorESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR, oldFillColor, fillColor, !oldFillColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetFillColor() {
        String oldFillColor = fillColor;
        boolean oldFillColorESet = fillColorESet;
        fillColor = FILL_COLOR_EDEFAULT;
        fillColorESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR, oldFillColor, FILL_COLOR_EDEFAULT, oldFillColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetFillColor() {
        return fillColorESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getLineColor() {
        return lineColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLineColor(String newLineColor) {
        String oldLineColor = lineColor;
        lineColor = newLineColor;
        boolean oldLineColorESet = lineColorESet;
        lineColorESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR, oldLineColor, lineColor, !oldLineColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetLineColor() {
        String oldLineColor = lineColor;
        boolean oldLineColorESet = lineColorESet;
        lineColor = LINE_COLOR_EDEFAULT;
        lineColorESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR, oldLineColor, LINE_COLOR_EDEFAULT, oldLineColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetLineColor() {
        return lineColorESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getFontColor() {
        return fontColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFontColor(String newFontColor) {
        String oldFontColor = fontColor;
        fontColor = newFontColor;
        boolean oldFontColorESet = fontColorESet;
        fontColorESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR, oldFontColor, fontColor, !oldFontColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetFontColor() {
        String oldFontColor = fontColor;
        boolean oldFontColorESet = fontColorESet;
        fontColor = FONT_COLOR_EDEFAULT;
        fontColorESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR, oldFontColor, FONT_COLOR_EDEFAULT, oldFontColorESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetFontColor() {
        return fontColorESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFontName(String newFontName) {
        String oldFontName = fontName;
        fontName = newFontName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_NAME, oldFontName, fontName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getFontStyle() {
        return fontStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFontStyle(String newFontStyle) {
        String oldFontStyle = fontStyle;
        fontStyle = newFontStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_STYLE, oldFontStyle, fontStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFontSize(int newFontSize) {
        int oldFontSize = fontSize;
        fontSize = newFontSize;
        boolean oldFontSizeESet = fontSizeESet;
        fontSizeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE, oldFontSize, fontSize, !oldFontSizeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetFontSize() {
        int oldFontSize = fontSize;
        boolean oldFontSizeESet = fontSizeESet;
        fontSize = FONT_SIZE_EDEFAULT;
        fontSizeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE, oldFontSize, FONT_SIZE_EDEFAULT, oldFontSizeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetFontSize() {
        return fontSizeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setX(int newX) {
        int oldX = x;
        x = newX;
        boolean oldXESet = xESet;
        xESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__X, oldX, x, !oldXESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetX() {
        int oldX = x;
        boolean oldXESet = xESet;
        x = X_EDEFAULT;
        xESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__X, oldX, X_EDEFAULT, oldXESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetX() {
        return xESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getY() {
        return y;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setY(int newY) {
        int oldY = y;
        y = newY;
        boolean oldYESet = yESet;
        yESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_VIEW__Y, oldY, y, !oldYESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetY() {
        int oldY = y;
        boolean oldYESet = yESet;
        y = Y_EDEFAULT;
        yESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_VIEW__Y, oldY, Y_EDEFAULT, oldYESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetY() {
        return yESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_VIEW__ID:
                return getId();
            case UMLDiagramPackage.ABSTRACT_VIEW__NAME:
                return getName();
            case UMLDiagramPackage.ABSTRACT_VIEW__DESCRIPTION:
                return getDescription();
            case UMLDiagramPackage.ABSTRACT_VIEW__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL:
                if (resolve) return getUmlModel();
                return basicGetUmlModel();
            case UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR:
                return getFillColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR:
                return getLineColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR:
                return getFontColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_NAME:
                return getFontName();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_STYLE:
                return getFontStyle();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE:
                return new Integer(getFontSize());
            case UMLDiagramPackage.ABSTRACT_VIEW__X:
                return new Integer(getX());
            case UMLDiagramPackage.ABSTRACT_VIEW__Y:
                return new Integer(getY());
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_VIEW__ID:
                setId((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__NAME:
                setName((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__PARENT:
                setParent((EObject)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL:
                setUmlModel((Element)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR:
                setFillColor((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR:
                setLineColor((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR:
                setFontColor((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_NAME:
                setFontName((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_STYLE:
                setFontStyle((String)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE:
                setFontSize(((Integer)newValue).intValue());
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__X:
                setX(((Integer)newValue).intValue());
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__Y:
                setY(((Integer)newValue).intValue());
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
            case UMLDiagramPackage.ABSTRACT_VIEW__ID:
                setId(ID_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__PARENT:
                setParent((EObject)null);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL:
                setUmlModel((Element)null);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR:
                unsetFillColor();
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR:
                unsetLineColor();
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR:
                unsetFontColor();
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_NAME:
                setFontName(FONT_NAME_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_STYLE:
                setFontStyle(FONT_STYLE_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE:
                unsetFontSize();
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__X:
                unsetX();
                return;
            case UMLDiagramPackage.ABSTRACT_VIEW__Y:
                unsetY();
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
            case UMLDiagramPackage.ABSTRACT_VIEW__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UMLDiagramPackage.ABSTRACT_VIEW__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UMLDiagramPackage.ABSTRACT_VIEW__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UMLDiagramPackage.ABSTRACT_VIEW__PARENT:
                return parent != null;
            case UMLDiagramPackage.ABSTRACT_VIEW__UML_MODEL:
                return umlModel != null;
            case UMLDiagramPackage.ABSTRACT_VIEW__FILL_COLOR:
                return isSetFillColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__LINE_COLOR:
                return isSetLineColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_COLOR:
                return isSetFontColor();
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_NAME:
                return FONT_NAME_EDEFAULT == null ? fontName != null : !FONT_NAME_EDEFAULT.equals(fontName);
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_STYLE:
                return FONT_STYLE_EDEFAULT == null ? fontStyle != null : !FONT_STYLE_EDEFAULT.equals(fontStyle);
            case UMLDiagramPackage.ABSTRACT_VIEW__FONT_SIZE:
                return isSetFontSize();
            case UMLDiagramPackage.ABSTRACT_VIEW__X:
                return isSetX();
            case UMLDiagramPackage.ABSTRACT_VIEW__Y:
                return isSetY();
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
        result.append(" (id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", fillColor: ");
        if (fillColorESet) result.append(fillColor); else result.append("<unset>");
        result.append(", lineColor: ");
        if (lineColorESet) result.append(lineColor); else result.append("<unset>");
        result.append(", fontColor: ");
        if (fontColorESet) result.append(fontColor); else result.append("<unset>");
        result.append(", fontName: ");
        result.append(fontName);
        result.append(", fontStyle: ");
        result.append(fontStyle);
        result.append(", fontSize: ");
        if (fontSizeESet) result.append(fontSize); else result.append("<unset>");
        result.append(", x: ");
        if (xESet) result.append(x); else result.append("<unset>");
        result.append(", y: ");
        if (yESet) result.append(y); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // AbstractViewImpl
