/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlfragment;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Grabber</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.umlfragment.DiagramGrabber#getGrabbedDiagrams
 * <em>Grabbed Diagrams</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.umlfragment.UMLFragmentPackage#getDiagramGrabber()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment</li>
 * <li>설  명 : DiagramGrabber</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface DiagramGrabber extends EAnnotation {
    /**
     * Returns the value of the '<em><b>Grabbed Diagrams</b></em>' reference
     * list. The list contents are of type {@link org.eclipse.emf.ecore.EObject}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grabbed Diagrams</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Grabbed Diagrams</em>' reference list.
     * @see nexcore.tool.uml.model.umlfragment.UMLFragmentPackage#getDiagramGrabber_GrabbedDiagrams()
     * @model
     * @generated
     */
    EList<EObject> getGrabbedDiagrams();

} // DiagramGrabber
