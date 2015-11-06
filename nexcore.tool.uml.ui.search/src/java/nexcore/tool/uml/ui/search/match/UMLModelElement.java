/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.match;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설 명 : UMLModelElement</li>
 * <li>작성일 : 2010. 1. 13.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelElement {

    /** children을 List */
    private List<Object> children = new ArrayList<Object>();

    /** displayname */
    private String displayName;

    /** parent */
    private UMLModelElement parent;

    /** type */
    private String type;

    /** orgObject */
    private Object orgObject;

    /** eResource */
    private Resource eResource;

    /**
     * 생성자
     * 
     * @param displayName
     * @param type
     */
    public UMLModelElement(String displayName, String type) {
        this.displayName = displayName;
        this.type = type;
    }

    /**
     * 생성자
     * 
     * @param object
     */
    public UMLModelElement(Object object) {
        if (object instanceof NamedElement) {
            this.displayName = ((NamedElement) object).getName();

            if (object instanceof org.eclipse.uml2.uml.Model) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_MODEL;
            } else if (object instanceof org.eclipse.uml2.uml.Package) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_PACKAGE;
            } else if (object instanceof org.eclipse.uml2.uml.Component) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_COMPONENT;
            } else if (object instanceof org.eclipse.uml2.uml.Class) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_CLASS;
            } else if (object instanceof org.eclipse.uml2.uml.Actor) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_ACTOR;
            } else if (object instanceof org.eclipse.uml2.uml.UseCase) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_USECASE;
            } else if (object instanceof org.eclipse.uml2.uml.Interface) {
                this.type = UICoreConstant.MODELSEARCH__TYPE_INTERFACE;
            } else {
                this.type = UICoreConstant.MODELSEARCH__TYPE_NAMED_ELEMENT;
            }
        } else if (object instanceof IProject) {
            this.displayName = ((IProject) object).getName();
            this.type = UICoreConstant.MODELSEARCH__TYPE_PROJECT;
        }

        this.orgObject = object;
    }

    /**
     * @return the orgObject
     */
    public Object getOrgObject() {
        return orgObject;
    }

    /**
     * @param orgObject
     *            the orgObject to set
     */
    public void setOrgObject(Object orgObject) {
        this.orgObject = orgObject;
    }

    /**
     * addChild
     *  
     * @param child void
     */
    public void addChild(Object child) {

        if (child instanceof UMLModelElement) {
            ((UMLModelElement) child).setParent(this);
        }
        children.add(child);

    }

    /**
     * addChildren
     *  
     * @param collection void
     */
    @SuppressWarnings("unchecked")
    public void addChildren(Collection collection) {

        children.addAll(collection);
    }

    /**
     * getChildrenArray
     *  
     * @return Object[]
     */
    public Object[] getChildrenArray() {

        return children.toArray(new Object[children.size()]);
    }

    /**
     * isLeaf
     *  
     * @return boolean
     */
    public boolean isLeaf() {

        return children.size() <= 0;
    }

    /**
     * getDisplayName
     *  
     * @return String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * getIcon
     *  
     * @return Image
     */
    public Image getIcon() {
        return null;
    }

    /**
     * getParent
     *  
     * @return UMLModelElement
     */
    public UMLModelElement getParent() {
        return parent;
    }

    /**
     * getResource
     *  
     * @return IResource
     */
    public IResource getResource() {
        return null;
    }

    /**
     * hasChildren
     *  
     * @return boolean
     */
    public boolean hasChildren() {
        return children.size() > 0;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * isOpen
     *  
     * @return boolean
     */
    public boolean isOpen() {
        return false;
    }

    /**
     * removeAllChildren
     *   void
     */
    public void removeAllChildren() {

        children.clear();
    }

    /**
     * removeChildren
     *  
     * @param child void
     */
    public void removeChildren(Object child) {

        children.remove(child);

    }

    /**
     * setParent
     *  
     * @param parent void
     */
    public void setParent(UMLModelElement parent) {

        this.parent = parent;

    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.displayName;
    }

    /**
     * @return the eResource
     */
    public Resource getEResource() {

        if (eResource != null)
            return eResource;
        else if (getParent() != null && getParent().getEResource() != null) {
            return getParent().getEResource();
        }

        return eResource;
    }

    /**
     * @param resource
     *            the eResource to set
     */
    public void setEResource(Resource resource) {
        eResource = resource;
    }

    /**
     * changeDisplayName
     *  
     * @param replaceString void
     */
    public void changeDisplayName(String replaceString) {
        this.displayName = replaceString;

    }
}
