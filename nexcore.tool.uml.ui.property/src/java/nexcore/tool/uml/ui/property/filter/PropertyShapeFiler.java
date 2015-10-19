/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.filter;

import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributesEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NotationNameEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationsEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.StereotypeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.CombinedFragmentEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.GuardEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.InteractionOperandEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineBehaviorEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameHeaderEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LineEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.filter</li>
 * <li>설  명 : PropertyShapeFiler</li>
 * <li>작성일 : 2011. 2. 17.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class PropertyShapeFiler implements IFilter {

    /**
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object toTest) {
        
        if(toTest instanceof EditPart){
            Object model = ((EditPart)toTest).getModel();
            if(model instanceof AbstractView){
                if( null != ((AbstractView)model).getUmlModel() && DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return false;
                }
            }
        }
        
        if (toTest instanceof EditPart) {
            if (toTest instanceof AbstractDiagramEditPart) {
                return false;
            } else if (toTest instanceof TextEditPart) {
                return false;
            } else if (toTest instanceof NoteEditPart) {
                return false;
            } else if (toTest instanceof CompartmentLabelNodeEditPart) {
                return false;
            } else if (toTest instanceof StereotypeEditPart) {
                return false;
            } else if (toTest instanceof LabelNodeEditPart) {
                return false;
            } else if (toTest instanceof LifeLineBehaviorEditPart) {
                return false;
            } else if (toTest instanceof LifeLineNameHeaderEditPart) {
                return false;
            } else if (toTest instanceof LifeLineNameEditPart) {
                return false;
            } else if (toTest instanceof LineEditPart) {
                return false;
            } else if (toTest instanceof NotationNameEditPart) {
                return false;
            } else if (toTest instanceof OperationsEditPart) {
                return false;
            } else if (toTest instanceof AttributesEditPart) {
                return false;
            } else if (toTest instanceof OperationEditPart) {
                return false;
            } else if (toTest instanceof AttributeEditPart) {
                return false;
            } else if (toTest instanceof CombinedFragmentEditPart) {
                return false;
            } else if (toTest instanceof InteractionOperandEditPart) {
                return false;
            } else if (toTest instanceof GuardEditPart) {
                return false;
            }
            return true;
        }
        return false;
    }

}
