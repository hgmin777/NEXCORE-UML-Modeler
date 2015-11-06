/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : ReconnectConnectionCommand</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ReconnectConnectionCommand extends Command {

    /** 연결선 */
    private AbstractConnection connection;

    /**
     * getConnection
     *  
     * @return AbstractConnection
     */
    public AbstractConnection getConnection() {
        return connection;
    }

    /**
     * setConnection
     *  
     * @param connection void
     */
    public void setConnection(AbstractConnection connection) {
        this.connection = connection;
    }

    /** diagram */
    private Diagram diagram;

    /** new Source ViewModel */
    private AbstractNode newSource = null;

    /** new Target ViewModel */
    private AbstractNode newTarget = null;

    /** old Source ViewModel */
    private AbstractNode oldSource = null;

    /** old Target ViewModel */
    private AbstractNode oldTarget = null;

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (newSource != null) {
            handleNewSource();
        } else if (newTarget != null) {
            handleNewTarget();
        }
    }

    /**
     * 타켓의 변경 처리 void
     */
    private void handleNewTarget() {

        oldTarget = (AbstractNode) connection.getTarget();

        diagram.getConnectionList().remove(connection);
        Dimension targetAnchor = connection.getTargetAnchor();
        if (targetAnchor != null) {
            targetAnchor.setWidth(targetAnchorPoint.x);
            targetAnchor.setHeight(targetAnchorPoint.y);
        }
        reconnectTarget(newTarget);
        diagram.getConnectionList().add(connection);

        if (this.connection.getUmlModel() instanceof Relationship) {
            Relationship relationship = (Relationship) this.connection.getUmlModel();
            if (relationship != null) {
                switch (relationship.eClass().getClassifierID()) {
                    case UMLPackage.ASSOCIATION:
                        handleReconnectForAssociation((Association) relationship, this.oldTarget, this.newTarget);
                        break;
                    case UMLPackage.EXTEND:
                        handlerNewTargetForExtend((Extend) relationship);
                        break;
                    case UMLPackage.INCLUDE:
                        handlerNewTargetForInclude((Include) relationship);
                        break;
                    case UMLPackage.GENERALIZATION:
                        handlerNewTargetForGeneralization((Generalization) relationship);
                        break;
                    default:
                        if (relationship instanceof Dependency) {
                            handleNewTargetForDependency((Dependency) relationship);
                        }
                        return;
                }
            }
        }
    }

    /**
     * 
     * 
     * @param extend
     *            void
     */
    private void handlerNewTargetForExtend(Extend extend) {
        ExtensionPoint extensionPoint;
        String extensionPointName;
        UseCase supplier;

        supplier = (UseCase) this.newTarget.getUmlModel();
        extensionPointName = UMLMessage.getMessage(UMLMessage.UML_EXTENSIONPOINT);
        extensionPoint = supplier.createExtensionPoint(UMLManager.getPackagedUniqueName(supplier, extensionPointName));

        // extend.setExtendedCase(client);
        extend.getExtensionLocations().clear();
        extend.getExtensionLocations().add(extensionPoint);
        // client.getExtends().add(extend);
        supplier.getExtensionPoints().add(extensionPoint);
    }

    /**
     * 
     * 
     * @param include
     *            void
     */
    private void handlerNewTargetForInclude(Include include) {
        UseCase useCase = (UseCase) this.newTarget.getUmlModel();
        include.setAddition(useCase);
    }

    /**
     * 
     * 
     * @param include
     *            void
     */
    private void handlerNewSourceForInclude(Include include) {
        ((UseCase) this.oldSource.getUmlModel()).getIncludes().remove(include);
        ((UseCase) this.newSource.getUmlModel()).getIncludes().add(include);
    }

    /**
     * 
     * 
     * @param include
     *            void
     */
    private void handlerNewSourceForExtend(Extend extend) {
        ((UseCase) this.oldSource.getUmlModel()).getExtends().remove(extend);
        ((UseCase) this.newSource.getUmlModel()).getExtends().add(extend);
    }

    /**
     * 
     * 
     * @param generalization
     *            void
     */
    private void handlerNewTargetForGeneralization(Generalization generalization) {
        Classifier classifier = (Classifier) this.newTarget.getUmlModel();
        generalization.setGeneral(classifier);
    }

    /**
     * 
     * 
     * @param generalization
     *            void
     */
    private void handlerNewSourceForGeneralization(Generalization generalization) {
        ((Classifier) this.oldSource.getUmlModel()).getGeneralizations().remove(generalization);
        ((Classifier) this.newSource.getUmlModel()).getGeneralizations().add(generalization);
    }

    // TODO Control Flow and Object Flow처리
    /**
     * 
     * 
     * @param dependency
     *            void
     */
    private void handleNewTargetForDependency(Dependency dependency) {
        NamedElement oldElement, newElement;

        oldElement = (NamedElement) this.oldTarget.getUmlModel();
        newElement = (NamedElement) this.newTarget.getUmlModel();
        dependency.getSuppliers().remove(oldElement);
        dependency.getSuppliers().add(newElement);

        if (dependency instanceof ComponentRealization) {
            ComponentRealization componentRealization = (ComponentRealization) dependency;
            // 2011-06-30 nspark
            // 이클립스 개발환경 3.4 버전에서 3.6 으로 변경시 아래 소스 코드는 수정되어야함.
            // 컴포넌트 다이어그램에서 component realization 은 사용하지 않아 아래 코드는 주석 처리함.
            // eclipse 3.4
            // componentRealization.setRealizingClassifier((Classifier) newElement);
            // eclipse 3.6
            // componentRealization.getRealizingClassifiers().add((Classifier) newElement);

        } else if (dependency instanceof InterfaceRealization) {
            InterfaceRealization interfaceRealization = (InterfaceRealization) dependency;
            interfaceRealization.setContract((Interface) newElement);
        }

    }

    /**
     * 
     * 
     * @param dependency
     *            void
     */
    private void handleNewSourceForDependency(Dependency dependency) {
        NamedElement oldElement, newElement;

        oldElement = (NamedElement) this.oldSource.getUmlModel();
        newElement = (NamedElement) this.newSource.getUmlModel();
        dependency.getClients().remove(oldElement);
        dependency.getClients().add(dependency);
        Component oldComponent, newComponent;
        if (dependency instanceof ComponentRealization) {
            ComponentRealization componentRealization = (ComponentRealization) dependency;
            // 2011-06-30 nspark
            // 이클립스 개발환경 3.4 버전에서 3.6 으로 변경시 아래 소스 코드는 수정되어야함.
            // 컴포넌트 다이어그램에서 component realization 은 사용하지 않아 아래 코드는 주석 처리함.
            // eclipse 3.4
            // componentRealization.setRealizingClassifier((Classifier) newElement);
            // eclipse 3.6
            // componentRealization.getRealizingClassifiers().add((Classifier) newElement);
            
            oldComponent = (Component) oldElement;
            oldComponent.getRealizations().remove(componentRealization);

            newComponent = (Component) newElement;
            newComponent.getRealizations().add(componentRealization);

        } else if (dependency instanceof InterfaceRealization) {
            InterfaceRealization interfaceRealization = (InterfaceRealization) dependency;
            interfaceRealization.setContract((Interface) newElement);
            oldComponent = (Component) oldElement;
            oldComponent.getInterfaceRealizations().remove(interfaceRealization);

            newComponent = (Component) newElement;
            newComponent.getInterfaceRealizations().add(interfaceRealization);
        }
    }

    /**
     * 
     * 
     * @param association
     * @param oldNode
     * @param newNode
     *            void
     */
    private void handleReconnectForAssociation(Association association, AbstractNode oldNode, AbstractNode newNode) {
        Property property;
        Type oldType, newType;
        oldType = (Type) oldNode.getUmlModel();
        newType = (Type) newNode.getUmlModel();
        if (((Property) association.getMembers().get(0)).getType().equals(oldType)) {
            property = (Property) association.getMembers().get(0);
        } else {
            property = (Property) association.getMembers().get(1);
        }
        property.setType(newType);
        if (property.isNavigable()) {

            if (property.getOwner() instanceof StructuredClassifier) {
                ((StructuredClassifier) property.getOwner()).getOwnedAttributes().remove(property);
            }
            if (newType instanceof StructuredClassifier) {
                ((StructuredClassifier) newType).getOwnedAttributes().add(property);
            }
        }
    }

    /**
     * 소스의 변경 처리. void
     */
    private void handleNewSource() {
        oldSource = (AbstractNode) connection.getSource();

        diagram.getConnectionList().remove(connection);
        Dimension sourceAnchor = connection.getSourceAnchor();
        if (sourceAnchor != null) {
            sourceAnchor.setWidth(sourceAnchorPoint.x);
            sourceAnchor.setHeight(sourceAnchorPoint.y);
        }
        reconnectSource(newSource);
        diagram.getConnectionList().add(connection);

        if (this.connection.getUmlModel() instanceof Relationship) {
            Relationship relationship = (Relationship) this.connection.getUmlModel();
            if (relationship != null) {
                switch (relationship.eClass().getClassifierID()) {
                    case UMLPackage.ASSOCIATION:
                        handleReconnectForAssociation((Association) relationship, this.oldSource, this.newSource);
                        break;
                    case UMLPackage.EXTEND:
                    case UMLPackage.INCLUDE:
                        if (relationship instanceof Include) {
                            handlerNewSourceForInclude((Include) relationship);
                        } else {
                            handlerNewSourceForExtend((Extend) relationship);
                        }
                        break;
                    case UMLPackage.GENERALIZATION:
                        handlerNewSourceForGeneralization((Generalization) relationship);
                        break;
                    default:
                        if (relationship instanceof Dependency) {
                            handleNewSourceForDependency((Dependency) relationship);
                        }
                        return;
                }
            }
        }
    }

    /**
     * reconnectSource
     * 
     * @param source
     *            void
     */
    private void reconnectSource(AbstractNode source) {
        DiagramUtil.detachSourceOfConnection(connection);
        connection.setSource(source);
        DiagramUtil.attachSourceOfConnection(connection);
    }

    /**
     * reconnectTarget
     * 
     * @param target
     *            void
     */
    private void reconnectTarget(AbstractNode target) {
        DiagramUtil.detachTargetOfConnection(connection);
        connection.setTarget(target);
        DiagramUtil.attachTargetOfConnection(connection);
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setConnectionModel(Object model) {
        connection = (AbstractConnection) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewSource(Object model) {
        newSource = (AbstractNode) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewTarget(Object model) {
        newTarget = (AbstractNode) model;
    }

    /**
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        // if (oldSource != null) {
        // reconnectSource(oldSource);
        // }
        // if (oldTarget != null) {
        // reconnectTarget(oldTarget);
        // }
        //
        // oldSource = null;
        // oldTarget = null;
    }

    /** sourceAnchorPoint */
    private Point sourceAnchorPoint;

    /** targetAnchorPoint */
    private Point targetAnchorPoint;

    /**
     * 
     * setSourceAnchorPoint
     * 
     * @param location
     *            void
     */
    public void setSourceAnchorPoint(Point location) {
        sourceAnchorPoint = location;
    }

    /**
     * 
     * setTargetAnchorPoint
     * 
     * @param location
     *            void
     */
    public void setTargetAnchorPoint(Point location) {
        targetAnchorPoint = location;
    }

    /**
     * getNewSource
     *  
     * @return AbstractNode
     */
    public AbstractNode getNewSource() {
        return newSource;
    }

    /**
     * setNewSource
     *  
     * @param newSource void
     */
    public void setNewSource(AbstractNode newSource) {
        this.newSource = newSource;
    }

    /**
     * getNewTarget
     *  
     * @return AbstractNode
     */
    public AbstractNode getNewTarget() {
        return newTarget;
    }

    /**
     * setNewTarget
     *  
     * @param newTarget void
     */
    public void setNewTarget(AbstractNode newTarget) {
        this.newTarget = newTarget;
    }

    /**
     * getOldSource
     *  
     * @return AbstractNode
     */
    public AbstractNode getOldSource() {
        return oldSource;
    }

    /**
     * setOldSource
     *  
     * @param oldSource void
     */
    public void setOldSource(AbstractNode oldSource) {
        this.oldSource = oldSource;
    }

    /**
     * getOldTarget
     *  
     * @return AbstractNode
     */
    public AbstractNode getOldTarget() {
        return oldTarget;
    }

    /**
     * setOldTarget
     *  
     * @param oldTarget void
     */
    public void setOldTarget(AbstractNode oldTarget) {
        this.oldTarget = oldTarget;
    }
}
