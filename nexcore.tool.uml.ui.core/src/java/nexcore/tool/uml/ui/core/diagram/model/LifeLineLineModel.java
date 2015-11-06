/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

//package nexcore.tool.uml.ui.core.diagram.model;
//
//import org.eclipse.emf.common.notify.Adapter;
//import org.eclipse.emf.ecore.EObject;
//
///**
// * <ul>
// * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
// * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.model</li>
// * <li>설 명 : LifeLineLineModel</li>
// * <li>작성일 : 2009. 12. 15.</li>
// * <li>작성자 : ytchoi</li>
// * </ul>
// */
//public class LifeLineLineModel implements ILifeLineLine {
//
//	public LifeLineLineModel(Object umlModel) {
//		this.umlModel = umlModel;
//	}
//
//	/** storage for uml model */
//	Object umlModel = null;
//
//	/**
//	 * @see nexcore.tool.uml.ui.core.diagram.model.IUMLCompartmentModel#getUMLInfo()
//	 */
//	public Object getUMLInfo() {
//		return this.umlModel;
//	}
//
//	/**
//	 * @see nexcore.tool.uml.ui.core.diagram.model.IUMLCompartmentModel#setUMLInfo(java.lang.Object)
//	 */
//	public void setUMLInfo(Object objectUML) {
//		this.umlModel = objectUML;
//
//	}
//
//	/**
//	 * @see nexcore.tool.uml.ui.core.diagram.model.IUMLCompartmentModel#setAdpater(org.eclipse.emf.common.notify.Adapter)
//	 */
//	public void setAdpater(Adapter adapter) {
//		((EObject) umlModel).eAdapters().add(adapter);
//	}
//
//	/**
//	 * @see nexcore.tool.uml.ui.core.diagram.model.IUMLCompartmentModel#unsetAdpater(org.eclipse.emf.common.notify.Adapter)
//	 */
//	public void unsetAdpater(Adapter adapter) {
//		((EObject) umlModel).eAdapters().remove(adapter);
//	}
//
//}
