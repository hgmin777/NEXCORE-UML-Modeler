/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.util;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UMLPackage;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : DiagramUtil</li>
 * <li>작성일 : 2009. 11. 17.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public class DiagramUtil {

    /**
     * indexOfList
     * 
     * 리스트에서 해당 객체의 index를 리턴한다. 리스트에 해당 객체가 없으면 -1를 리턴한다.
     * 
     * @param list
     * @param object
     * @return int
     */
    @SuppressWarnings("unchecked")
    public static int getIndexOfByList(List list, Object object) {
        int index = -1;

        if (list == null || list.size() == 0 || object == null) {
            return index;
        }

        for (int i = 0; i < list.size(); i++) {
            if (object.equals(list.get(i))) {
                return i;
            }
        }

        return index;
    }

    /**
     * attachSourceOfConnection
     * 
     * 해당 컨넥션에 연결되어있던 소스노드에 해당컨넥션을 추가한다.
     * 
     * @param connection
     *            void
     */
    public static void attachSourceOfConnection(AbstractConnection connection) {
        AbstractNode source = (AbstractNode) connection.getSource();
        if (!source.getOutgoingConnectionList().contains(connection)) {
            source.getOutgoingConnectionList().add(connection);
        }
    }

    /**
     * attachTargetOfConnection
     * 
     * 해당 컨넥션에 연결되어있던 타겟노드에 해당컨넥션을 추가한다.
     * 
     * @param connection
     *            void
     */
    public static void attachTargetOfConnection(AbstractConnection connection) {
        AbstractNode target = (AbstractNode) connection.getTarget();
        if (!target.getIncomingConnectionList().contains(connection)) {
            target.getIncomingConnectionList().add(connection);
        }
    }

    /**
     * detachSourceOfConnection
     * 
     * 해당 컨넥션에 연결되어있던 소스노드에 해당컨넥션을 삭제한다.
     * 
     * @param connection
     *            void
     */
    public static void detachSourceOfConnection(AbstractConnection connection) {
        AbstractNode source = (AbstractNode) connection.getSource();
        source.getOutgoingConnectionList().remove(connection);
    }

    /**
     * detachTargetOfConnection
     * 
     * 해당 컨넥션에 연결되어있던 타겟노드에 해당컨넥션을 삭제한다.
     * 
     * @param connection
     *            void
     */
    public static void detachTargetOfConnection(AbstractConnection connection) {
        AbstractNode target = (AbstractNode) connection.getTarget();
        target.getIncomingConnectionList().remove(connection);
    }

    /**
     * cloneOfConnection
     * 
     * 해당 컨넥션을 클론한다.
     * 
     * @param connection
     *            void
     */
    public static AbstractConnection cloneOfConnection(AbstractConnection connection) {

        AbstractConnection cloneConnection;
        if (connection instanceof Relation) {
            cloneConnection = UMLDiagramFactory.eINSTANCE.createRelation();
        } else {
            cloneConnection = UMLDiagramFactory.eINSTANCE.createAttachement();
        }
        cloneConnection.setId(connection.getId());
        cloneConnection.setUmlModel(connection.getUmlModel());
        cloneConnection.setParent(connection.getParent());
        cloneConnection.setTarget(connection.getTarget());

        AbstractNode target = (AbstractNode) connection.getTarget();
        target.getIncomingConnectionList().remove(connection);

        return cloneConnection;
    }

    /**
     * 폰트로 라벨(스트링)의 넓이를 계산하는 메소드
     * 
     * @param label
     * @param fontName
     * @param fontSize
     * @param fontStyle
     * @return int
     */
    public static int getWidthSize(String label, String fontName, int fontSize, int fontStyle) {
        int width = 0;
        Shell shell = new Shell();
        GC gc = new GC(shell);
        Font font = new Font(null, new FontData(fontName, fontSize, fontStyle));
        gc.setFont(font);
        width = gc.textExtent(label).x;
        font.dispose();
        gc.dispose();
        shell.dispose();
        return width;
    }

    /**
     * 폰트로 라벨(스트링)의 넓이를 계산하는 메소드
     * 
     * @param label
     * @param fontName
     * @param fontSize
     * @param fontStyle
     * @return int
     */
    public static int getWidthSize(final String label, final Font font) {
        if (label == null || label.equals("")) {
            return 0;
        }
        int width = 0;
        RunnableWithResult<Integer> runnable = new RunnableWithResult<Integer>() {
            /**
             * width
             */
            int width = 0;
            /**
             * @see org.eclipse.emf.transaction.RunnableWithResult#getResult()
             */
            public Integer getResult() {
                return width;
            }
            
            /**
             * @see org.eclipse.emf.transaction.RunnableWithResult#setStatus(org.eclipse.core.runtime.IStatus)
             */
            public void setStatus(IStatus status) {
                
            }
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
                Shell shell = new Shell();
                GC gc = new GC(shell);
                gc.setFont(font);
                width = gc.textExtent(label).x;
                gc.dispose();
                shell.dispose();
            }

            public IStatus getStatus() {
                return null;
            }
        };
        Display.getDefault().syncExec(runnable);
        
        Integer result = runnable.getResult();

        return result == null ? width : result.intValue();
    }

    /**
     * 폰트로 라벨(스트링)의 넓이를 계산하는 메소드
     * 
     * @param label
     * @param fontName
     * @param fontSize
     * @param fontStyle
     * @return int
     */
    public static int getWidthSize(String label, FontData fontData) {
        int width = 0;
        Shell shell = new Shell();
        GC gc = new GC(shell);
        Font font = new Font(null, fontData);
        gc.setFont(font);
        width = gc.textExtent(label).x;
        font.dispose();
        gc.dispose();
        shell.dispose();
        return width;
    }
    
    /**
     * @param relationship
     * @return
     */
    public static Relation createConnection(Relationship relationship) {
        Relation connection = UMLDiagramFactory.eINSTANCE.createRelation();
        connection.setUmlModel(relationship);
        if (relationship.eClass() == UMLPackage.Literals.ASSOCIATION) {
            connection.setRelationType(RelationType.ASSOCIATION);
        } else if (relationship.eClass() == UMLPackage.Literals.INTERFACE_REALIZATION) {
            connection.setRelationType(RelationType.INTERFACE_REALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.COMPONENT_REALIZATION) {
            connection.setRelationType(RelationType.COMPONENT_REALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.REALIZATION) {
            connection.setRelationType(RelationType.REALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.ABSTRACTION) {
            connection.setRelationType(RelationType.ABSTRACTION);
        } else if (relationship.eClass() == UMLPackage.Literals.USAGE) {
            connection.setRelationType(RelationType.USAGE);
        } else if (relationship.eClass() == UMLPackage.Literals.DEPENDENCY) {
            connection.setRelationType(RelationType.DEPENDENCY);
        } else if (relationship.eClass() == UMLPackage.Literals.EXTEND) {
            connection.setRelationType(RelationType.EXTEND);
        } else if (relationship.eClass() == UMLPackage.Literals.INCLUDE) {
            connection.setRelationType(RelationType.INCLUDE);
        } else if (relationship.eClass() == UMLPackage.Literals.GENERALIZATION) {
            connection.setRelationType(RelationType.GENERALIZATION);
        } else if (relationship.eClass() == UMLPackage.Literals.INTERFACE_REALIZATION) {
            connection.setRelationType(RelationType.INTERFACE_REALIZATION);
        }
        return connection;
    }

}
