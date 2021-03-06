/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlfragment.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the
 * package. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.umlfragment.util.UMLFragmentResourceImpl
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment.util</li>
 * <li>설  명 : UMLFragmentResourceFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLFragmentResourceFactoryImpl extends ResourceFactoryImpl {
    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public UMLFragmentResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Resource createResource(URI uri) {
        Resource result = new UMLFragmentResourceImpl(uri);
        return result;
    }

    /**
     * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useUUIDs()
     */
    protected boolean useUUIDs() {
        return true;
    }
} // UMLFragmentResourceFactoryImpl
