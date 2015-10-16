/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.extension;

import org.eclipse.gef.EditPartFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.extension</li>
 * <li>설  명 : UMLDiagramApplication</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public class UMLDiagramApplication implements IUMLDiagramApplication {
    /**
     * factory
     */
    EditPartFactory factory;

    /**
     * @return the factory
     */
    public EditPartFactory getFactory() {
        return factory;
    }

    /**
     * @param factory
     *            the factory to set
     */
    public void setFactory(EditPartFactory factory) {
        this.factory = factory;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * id
     */
    String id;

    /**
     * name
     */
    String name;

}
