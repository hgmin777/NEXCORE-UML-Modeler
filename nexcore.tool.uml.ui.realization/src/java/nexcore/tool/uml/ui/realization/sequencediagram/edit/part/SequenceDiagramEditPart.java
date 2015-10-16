/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.model.LifeLineNameHeader;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.ObjectFlow;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : SequenceDiagramEditPart</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramEditPart extends AbstractDiagramEditPart {

    /** nameHeaderList */
    private List<LifeLineNameHeader> nameHeaderList = new ArrayList<LifeLineNameHeader>();
    
    /** combinedfragementList */
    private List<AbstractNode> combinedfragementList = new ArrayList<AbstractNode>();

    /** nameHeader */
    private LifeLineNameHeader nameHeader;;

    /** selectionAdapter */
    private PropertyChangeListener listener = new PropertyChangeListener() {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
            if ("viewLocation".equals(evt.getPropertyName())) { //$NON-NLS-1$                
                refreshNameHeaders();
            }
        }
    };


    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class key) {
        // if (key == AutoexposeHelper.class)
        // return new ViewportAutoexposeHelper(this);
        return super.getAdapter(key);
    }

    /**
     * refreshNameHeaders
     * 
     * @param viewport
     *            void
     */
    @SuppressWarnings("unchecked")
    public void refreshNameHeaders() {
        try {
            Viewport viewport = ((FigureCanvas) getViewer().getControl()).getViewport();
            int verticalValue = viewport.getVerticalRangeModel().getValue();
            if (verticalValue > 30) {
                for (int i = 0; i < nameHeaderList.size(); i++) {
                    nameHeader = nameHeaderList.get(i);
                    nameHeader.setVisible(true);
                }
            } else {
                for (int i = 0; i < nameHeaderList.size(); i++) {
                    nameHeader = nameHeaderList.get(i);
                    nameHeader.setVisible(false);
                }
            }
            List<EditPart> ediPartList = getChildren();
            for (int i = 0; i < ediPartList.size(); i++) {
                if (ediPartList.get(i) instanceof LifeLineNameHeaderEditPart) {
                    ((LifeLineNameHeaderEditPart) ediPartList.get(i)).refreshVisuals();
                }
            }
        } catch (Exception e) {
            Log.error(UMLMessage.LABEL_SEQUENCE_DIAGRAM_EDIT_PART_REFRESH_NAME_HEADERS_ERROR + e);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#createLayoutEditPolicy()
     */
    @Override
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new SequenceDiagramLayoutEditPolicy();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        ((FigureCanvas) getViewer().getControl()).getViewport().addPropertyChangeListener(listener);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        ((FigureCanvas) getViewer().getControl()).getViewport().removePropertyChangeListener(listener);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getModelChildren() {
        List list = new ArrayList();
        list.addAll(getDiagramModel().getNodeList());

        Object nodeArray[] = new Object[list.size()];
        nodeArray = list.toArray();
        combinedfragementList.clear();
        for(int i = 0; i <nodeArray.length; i++) {
            AbstractNode abstractNode = (AbstractNode) nodeArray[i];
            if(NodeType.OPTION_IF.equals(abstractNode.getNodeType())
                || NodeType.ALTERNATIVE_IF_ELSE.equals(abstractNode.getNodeType())
                || NodeType.ALTERNATIVE_SWITCH.equals(abstractNode.getNodeType()) 
                || NodeType.LOOP_WHILE.equals(abstractNode.getNodeType()) 
                || NodeType.LOOP_FOR.equals(abstractNode.getNodeType()) ) {
                combinedfragementList.add(abstractNode);
                list.remove(abstractNode);
            }
        }
                
        List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
        connectionList = ((Diagram) getModel()).getConnectionList();

        if (connectionList.size() != 0) {
            for (AbstractConnection connection : connectionList) {
                if ((connection).getUmlModel() instanceof Association) {
                    list.addAll(connection.getLabels());
                } else if ((connection).getUmlModel() instanceof Generalization
                    || (connection).getUmlModel() instanceof Message
                    || (connection).getUmlModel() instanceof ControlFlow
                    || (connection).getUmlModel() instanceof ObjectFlow || connection instanceof Attachement) {
                    continue;
                } else
                    list.addAll(connection.getLabels());
            }
        }

        nameHeaderList.clear();
        LifeLineNameHeader header;
        for (int i = 0; i < getDiagramModel().getNodeList().size(); i++) {
            if (getDiagramModel().getNodeList().get(i) instanceof LifeLineNode) {
                header = new LifeLineNameHeader((LifeLineNode) getDiagramModel().getNodeList().get(i));
                nameHeaderList.add(header);
            }
        }


        for(int i = 0; i < combinedfragementList.size(); i++) {
            list.add(i, combinedfragementList.get(i));
        }
        list.addAll(nameHeaderList);

        return list;
    }
}
