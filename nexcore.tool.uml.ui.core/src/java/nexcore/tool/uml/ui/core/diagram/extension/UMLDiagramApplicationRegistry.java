/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPartFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.extension</li>
 * <li>설  명 : UMLDiagramApplicationRegistry</li>
 * <li>작성일 :2010. 1. 14.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public class UMLDiagramApplicationRegistry {
    /** */
    private static final String EXTENSION_EDIT_PART_FACTORY = "editPartFactory";

    /** */
    private static final String NEXCORE_TOOL_UML_UI_CORE_EDITOR_EXTENSION = "nexcore.tool.uml.ui.core.editor.extension";

    /**
     * instance
     */
    static UMLDiagramApplicationRegistry instance;

    /**
     * UMLDiagramApplicationRegistry
     */
    private UMLDiagramApplicationRegistry() {

    }

    /**
     * getInstance
     *  
     * @return UMLDiagramApplicationRegistry
     */
    public static UMLDiagramApplicationRegistry getInstance() {
        if (instance == null) {
            instance = new UMLDiagramApplicationRegistry();
            instance.init();
        }

        return instance;
    }

    /**
     * list
     */
    ArrayList<IUMLDiagramApplication> list;

    /**
     * @return the list
     */
    public ArrayList<IUMLDiagramApplication> getList() {
        return list;
    }

    /**
     * getApplication
     *  
     * @param id
     * @return IUMLDiagramApplication
     */
    public IUMLDiagramApplication getApplication(String id) {
        if (id == null)
            return null;
        List<IUMLDiagramApplication> list = getList();
        for (IUMLDiagramApplication app : list) {
            if (app.getId().equals(id))
                return app;
        }
        return null;
    }

    /**
     * init
     *   void
     */
    private void init() {
        list = new ArrayList<IUMLDiagramApplication>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor(NEXCORE_TOOL_UML_UI_CORE_EDITOR_EXTENSION);
        if (config != null) {
            for (IConfigurationElement element : config) {
                UMLDiagramApplication editor = new UMLDiagramApplication();
                try {
                    editor.setFactory((EditPartFactory) element.createExecutableExtension(EXTENSION_EDIT_PART_FACTORY));
                    editor.setName(element.getAttribute("name"));
                    editor.setId(element.getAttribute("id"));
                    list.add(editor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
