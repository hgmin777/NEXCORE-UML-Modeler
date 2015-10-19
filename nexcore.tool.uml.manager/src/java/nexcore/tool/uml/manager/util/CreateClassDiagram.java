/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.UMLModelerNotationModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : CreateClassDiagram</li>
 * <li>작성일 : 2008. 06. 12</li>
 * <li>작성자 : 81228 </li>
 * </ul>
 */
public class CreateClassDiagram {

    /**
     * 생성자
     */
    public CreateClassDiagram() {
    }

    /**
     * Collaboration 내에 클래스 다이어그램을 생성하고, Interaction의 시퀀스 다이어그램을 읽어서 클래스 다이어그램을
     * 작성한다.
     * 
     * @param collabo
     * @param sequenceList
     * @param collaborationList
     * @return List<String>
     */
    public static List<String> createClassDiagram(Collaboration collabo, List<String> sequenceList,
                                                  List<Element> collaborationList) {
        if (!collaborationList.contains(collabo)) {
            collaborationList.add(collabo);
        } else {
            return null;
        }

        if (collabo != null && sequenceList != null) {
            List clbElmtList = collabo.getOwnedBehaviors();

            ClassDiagramUtil.deleteExistingClassDiagram(collabo);

            Diagram classDgm = ClassDiagramUtil.addClassDiagram(collabo);
            classDgm.setId(UUID.randomUUID().toString());

            List<Diagram> dgms = UMLModelerNotationModelHandlerUtil.getInstance().getDiagramList(collabo,
                DiagramType.CLASS_DIAGRAM);
            int cnt = -1;
            for (Diagram dgm : dgms) {
                if (dgm.getName().contains(dgms.get(0).getName())) {
                    cnt++;
                }
            }
            if (cnt != -1 && cnt != 0) {
                classDgm.setName(classDgm.getName() + ManagerConstant.UNDER_BAR + cnt);
            }

            Interaction interaction = null;
            Object clbElmtObj = null;

            sequenceDiagramElements.clear();
            sequenceDiagramMessages.clear();
            for (Iterator clbElmtItr = clbElmtList.iterator(); clbElmtItr.hasNext();) {
                clbElmtObj = (Object) clbElmtItr.next();

                // Interaction Instance이면 수행
                if (clbElmtObj != null && clbElmtObj instanceof Interaction) {
                    interaction = (Interaction) clbElmtObj;

                    // 시퀀스 다이어그램을 읽고 클래스 정보를 추출한다.
                    // addClass2Diagram(classDgm, interaction, seqElmtMap);

                    addClassDiagramElements(classDgm, interaction);
                    addClassDiagramMessages(classDgm, interaction);
                    sequenceList.add(interaction.getName());
                }
            }
            if (sequenceDiagramElements.isEmpty()) {
                return null;
            }
            createVClass(sequenceDiagramElements, interaction, classDgm);
            setLayoutDiagram(classDgm);
            createVMessages(interaction, classDgm);

            // 다이어그램을 정렬한다.
            if (classDgm != null && classDgm.getNodeList() != null && classDgm.getNodeList().size() > 0) {
                // TODO: UMLModeler.getUMLDiagramHelper().layoutNodes() 개발
                // UMLModeler.getUMLDiagramHelper().layoutNodes(classDgm.getNodeList(),
                // LayoutType.DEFAULT);
            }
        }
        return sequenceList;
    }

    /**
     * sequenceDiagramElements
     */
    private static List<Element> sequenceDiagramElements = new ArrayList<Element>();

    /**
     * sequenceDiagramMessages
     */
    private static List<Message> sequenceDiagramMessages = new ArrayList<Message>();

    /**
     * addClassDiagramElements
     *  
     * @param classDgm
     * @param interaction void
     */
    private static void addClassDiagramElements(Diagram classDgm, Interaction interaction) {

        EList<Lifeline> lifeLines = interaction.getLifelines();
        for (Lifeline lifeLine : lifeLines) {

            if (lifeLine != null && lifeLine.getRepresents() != null && lifeLine.getRepresents().getType() != null) {
                Element element = lifeLine.getRepresents().getType();

                if (element instanceof Actor) {
                    continue;
                }
                if (!sequenceDiagramElements.contains(element)) {
                    sequenceDiagramElements.add(element);
                }
            }
        }
    }

    /**
     * addClassDiagramMessages
     *  
     * @param classDgm
     * @param interaction void
     */
    private static void addClassDiagramMessages(Diagram classDgm, Interaction interaction) {

        for (Message message : interaction.getMessages()) {
            if (!sequenceDiagramMessages.contains(message)) {
                sequenceDiagramMessages.add(message);
            }
        }
    }

    /**
     * createVClass
     *  
     * @param sequenceDiagramElements
     * @param interaction
     * @param classDgm void
     */
    private static void createVClass(List sequenceDiagramElements, Interaction interaction, Diagram classDgm) {

        for (int i = 0; i < sequenceDiagramElements.size(); i++) {

            // 클래스의 경우 소유한 어트리뷰트와 오퍼레이션의 갯수를 확인하여 클래스 노드를 그릴 시 크기에 반영한다.

            NamedElement namedElement = null;
            if (sequenceDiagramElements.get(i) instanceof NamedElement) {
                namedElement = (NamedElement) sequenceDiagramElements.get(i);
            }

            Class classElement = null;
            Interface interfaceElement = null;
            if (sequenceDiagramElements.get(i) instanceof org.eclipse.uml2.uml.Class) {
                classElement = (Class) sequenceDiagramElements.get(i);
            } else if (sequenceDiagramElements.get(i) instanceof Interface) {
                interfaceElement = (Interface) sequenceDiagramElements.get(i);
            }

            int ownedAttributeCnt, ownedOperationCnt, ownedElement = 0;
            EList<Property> childProperty = null;
            EList<Operation> childOperation = null;
            if (classElement != null) {

                childProperty = classElement.getOwnedAttributes();
                childOperation = classElement.getOwnedOperations();

                ownedAttributeCnt = childProperty.size();
                ownedOperationCnt = childOperation.size();

                if (ownedAttributeCnt == 0 && ownedOperationCnt != 0) {
                    ownedElement = ownedOperationCnt;
                } else if (ownedOperationCnt == 0 && ownedAttributeCnt != 0) {
                    ownedElement = ownedAttributeCnt;
                } else {
                    ownedElement = ownedAttributeCnt + ownedOperationCnt;
                    if (ownedElement != 0) {
                        ownedElement -= 3;
                    }
                }
            }
            if (interfaceElement != null) {

                childProperty = interfaceElement.getOwnedAttributes();
                childOperation = interfaceElement.getOwnedOperations();

                ownedAttributeCnt = childProperty.size();
                ownedOperationCnt = childOperation.size();

                if (ownedAttributeCnt == 0 && ownedOperationCnt != 0) {
                    ownedElement = ownedOperationCnt;
                } else if (ownedOperationCnt == 0 && ownedAttributeCnt != 0) {
                    ownedElement = ownedAttributeCnt;
                } else {
                    ownedElement = ownedAttributeCnt + ownedOperationCnt;
                    if (ownedElement != 0) {
                        ownedElement -= 3;
                    }
                }
            }
            // 클래스의 소유한 오퍼레이션, 어트리뷰트의 이름을 조사하여
            // 클래스 노드의 기본 가로 길이 160보다 길다면 가로 사이즈를 추가해 준다.
            String elementName = ManagerConstant.EMPTY_STRING;
            int maxNameLength = 0;
            for (Property property : childProperty) {
                elementName = property.getName();
                if (elementName.length() > maxNameLength) {
                    maxNameLength = elementName.length();
                }
            }
            for (Operation operation : childOperation) {
                elementName = operation.getName();
                if (elementName.length() > maxNameLength) {
                    maxNameLength = elementName.length();
                }
            }
            if (maxNameLength != 0) {
                maxNameLength = maxNameLength - 10;
            }

            // 클래스 노드를 생성
            NotationNode node = UMLDiagramFactory.eINSTANCE.createNotationNode();
            node.setUmlModel((Element) sequenceDiagramElements.get(i));
            if((Element) sequenceDiagramElements.get(i) instanceof Class){
                node.setNodeType(NodeType.CLASS);
            } else if((Element) sequenceDiagramElements.get(i) instanceof Interface){
                node.setNodeType(NodeType.INTERFACE);
            }

            if (namedElement != null) {
                if (getWidthSize(namedElement.getName(), UMLMessage.LABEL_SAMPLEFONT, 10, SWT.ITALIC) + 50 > 160 + (maxNameLength * 5)) {
                    node.setWidth(getWidthSize(namedElement.getName(), UMLMessage.LABEL_SAMPLEFONT, 10, SWT.ITALIC) + 50);
                } else {
                    node.setWidth(160 + (maxNameLength * 5));
                }
            }
            node.setHeight(80 + (ownedElement * 20));

            classDgm.getNodeList().add(node);
        }
    }

    /**
     * getWidthSize
     *  
     * @param label
     * @param fontName
     * @param fontSize
     * @param fontStyle
     * @return int
     */
    private static int getWidthSize(String label, String fontName, int fontSize, int fontStyle) {
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
     * setLayoutDiagram
     *  
     * @param classDgm void
     */
    private static void setLayoutDiagram(Diagram classDgm) {

        int x = 10;
        int y = 10;
        for (int i = 0; i < classDgm.getNodeList().size(); i++) {
            AbstractNode node = classDgm.getNodeList().get(i);
            node.setX(x);
            node.setY(y);
            x += 300;
            if ((i + 1) % 3 == 0) {
                y += 200;// 150;
                x = 10;
            }
        }
    }

    /**
     * sourceTargetList
     */
    private static HashMap<AbstractNode, List<AbstractNode>> sourceTargetList = new HashMap<AbstractNode, List<AbstractNode>>();

    /**
     * createVMessages
     *  
     * @param interaction
     * @param classDgm void
     */
    private static void createVMessages(Interaction interaction, Diagram classDgm) {

        List<Message> messages = sequenceDiagramMessages;
        AbstractNode source = null;
        AbstractNode target = null;
        for (Message message : messages) {
            try {
                // if(!interaction.getMessages().contains(message)){
                // continue;
                // }
                if (message.getMessageSort().equals(MessageSort.REPLY_LITERAL)) {
                    continue;
                }
                NamedElement sourceElement = ((MessageOccurrenceSpecification) message.getSendEvent()).getCovereds()
                    .get(0)
                    .getRepresents()
                    .getType();
                NamedElement targetElement = ((MessageOccurrenceSpecification) message.getReceiveEvent()).getCovereds()
                    .get(0)
                    .getRepresents()
                    .getType();
                if (sourceElement == null || targetElement == null) {
                    continue;
                }

                if (sourceElement == targetElement) {
                    continue;
                }

                if (sourceElement instanceof org.eclipse.uml2.uml.Actor
                    || targetElement instanceof org.eclipse.uml2.uml.Actor) {
                    continue;
                }

                for (AbstractView node : classDgm.getNodeList()) {
                    if (node.getUmlModel().equals(sourceElement)) {
                        source = (AbstractNode) node;
                    }
                    if (node.getUmlModel().equals(targetElement)) {
                        target = (AbstractNode) node;
                    }
                }
                if (sourceTargetList.get(source) == null) {
                    List<AbstractNode> targetList = new ArrayList<AbstractNode>();
                    targetList.add(target);
                    sourceTargetList.put(source, targetList);
                } else {
                    List<AbstractNode> targetList = sourceTargetList.get(source);
                    if (targetList.contains(target)) {
                        continue;
                    }
                }

                Dependency dependency = UMLFactory.eINSTANCE.createDependency();
                UMLManager.setDependency(dependency, targetElement, sourceElement);
                // InterfaceRealization interfaceRealization =
                // UMLFactory.eINSTANCE.createInterfaceRealization();
                // UMLManager.setRealization(interfaceRealization,
                // targetElement, sourceElement);

                boolean bool = true;
                for (AbstractConnection connection : classDgm.getConnectionList()) {
                    if (connection.getUmlModel() instanceof Dependency) {
                        if (connection.getSource().getUmlModel().equals(sourceElement)
                            && connection.getTarget().getUmlModel().equals(targetElement)) {
                            bool = false;
                        }
                    }
                }

                if (bool) {
                    AbstractConnection connection = UMLDiagramFactory.eINSTANCE.createRelation();
                    connection.setUmlModel(dependency);
                    connection.setRelationType(RelationType.DEPENDENCY);
                    connection.setSource((AbstractView) source);
                    connection.setTarget((AbstractView) target);

                    // RectangleFigure sourceNodeBound = new RectangleFigure();
                    // sourceNodeBound.setSize(source.getWidth(),
                    // source.getHeight());
                    // sourceNodeBound.setLocation(new Point(source.getX(),
                    // source.getY()));
                    // ChopboxAnchor sourceAnchor = new ChopboxAnchor((IFigure)
                    // sourceNodeBound);
                    // RectangleFigure targetNodeBound = new RectangleFigure();
                    // targetNodeBound.setSize(target.getWidth(),
                    // target.getHeight());
                    // targetNodeBound.setLocation(new Point(target.getX(),
                    // target.getY()));
                    // ChopboxAnchor targetAnchor = new ChopboxAnchor((IFigure)
                    // targetNodeBound);
                    //				
                    // Dimension sourceAnchorDimension =
                    // UMLDiagramFactory.eINSTANCE.createDimension();
                    // sourceAnchorDimension.setWidth(sourceAnchor.getReferencePoint().x);
                    // sourceAnchorDimension.setHeight(sourceAnchor.getReferencePoint().y);
                    // Dimension targetAnchorDimension =
                    // UMLDiagramFactory.eINSTANCE.createDimension();
                    // targetAnchorDimension.setWidth(targetAnchor.getReferencePoint().x);
                    // targetAnchorDimension.setHeight(targetAnchor.getReferencePoint().y);
                    //	            
                    // connection.setSourceAnchor(sourceAnchorDimension);
                    // connection.setTargetAnchor(targetAnchorDimension);
                    classDgm.getConnectionList().add(connection);
                    DiagramUtil.attachSourceOfConnection(connection);
                    DiagramUtil.attachTargetOfConnection(connection);
                }
            } catch (Exception error) {
                Log.error(error);
            }
        }
        // UMLManager.setDependency((Dependency) connection.getUmlModel(),
        // supplier, client);
    }

    /**
     * 시퀀스 다이어그램을 읽어서 클래스 정보를 추출 (복수개의 시퀀스 다이어그램이 존재할 수 있으므로 seqElmtMap에 엘리먼트명
     * 인덱스를 만들어 중복생성을 방지한다.)
     * 
     * @param classDgm
     * @param interaction
     * @param seqElmtMap
     *            void
     */
    /*
     * private static void addClass2Diagram(Diagram classDgm, Interaction
     * interaction, HashMap seqElmtMap) {
     * 
     * HashMap voClassMap = new HashMap(); HashMap controlClassMap = new
     * HashMap();
     * 
     * // 1. 클래스 다이어그램에서 클래스를 생성한다. boolean isAddVoClass = false; int voCmrIdx =
     * -1;
     * 
     * // 설정파일에서 ClassDiagram VOClass의 isMandatory가 true 일 경우 클래스 다이어그램에 VO 클래스를
     * 추가한다.
     * if(ModelPropertyManager.getInstance().getClassDiagram().getVoClass()
     * .isMandatory()) { isAddVoClass = true; voCmrIdx = getVoCmrIndex(); }
     * 
     * // 2. 기본정보 설정 getBasicInfo(seqElmtMap, classDgm, interaction,
     * controlClassMap, voClassMap, isAddVoClass, voCmrIdx);
     * //getBasicInfo(HashMap seqElmtMap, Diagram classDgm, Interaction
     * interaction, HashMap controlClassMap, HashMap voClassMap, boolean
     * isAddVoClass, int voCmrIdx){
     * 
     * // 3. 시퀀스 다디어그램 정보를 읽어온다. Diagram seqDgm = null; List seqDgmList =
     * UMLModelerNotationModelHandlerUtil
     * .getInstance().getDiagramList(interaction, DiagramType.SEQUENCE_DIAGRAM);
     * 
     * // interaction에는 실제로 한 개의 시퀀스 다이어그램만 존재 if (seqDgmList.size() > 0) {
     * seqDgm = (Diagram) seqDgmList.get(0); }
     * 
     * EList edges =
     * SequenceUtil.getOnlyMessageList(seqDgm.getConnectionList());
     * 
     * // edge가 읽히지 않을 경우 시퀀스 다이어그램을 강제로 연다. if(seqDgm != null && edges.size()
     * == 0) { //TODO : UMLModeler.getUMLDiagramHelper().renderToSWTImage() 개발
     * //UMLModeler.getUMLDiagramHelper().renderToSWTImage(seqDgm); }
     * 
     * // 4. 클래스와 클래스에 관계를 연결한다. ClassDiagramUtil.connectClassToClass(edges,
     * seqElmtMap);
     * 
     * // 5. VO 클래스와 Control 클래스를 연결한다. if(isAddVoClass && voCmrIdx != -1) {
     * ClassDiagramUtil.connectControlToVO( seqDgm, controlClassMap, voClassMap,
     * seqElmtMap); } }
     * 
     * // 기본정보 읽기 private static void getBasicInfo(HashMap seqElmtMap, Diagram
     * classDgm, Interaction interaction, HashMap controlClassMap, HashMap
     * voClassMap, boolean isAddVoClass, int voCmrIdx){ Model model = null;
     * ClassMappingRule cmr = null; ConnectableElement conElmt = null; Type
     * elmtType = null; Lifeline lifeLine = null; String elmtName = null; String
     * anlElmtName = null; EList stList = null; Stereotype stereotype = null;
     * String voName = null; org.eclipse.uml2.uml.Class voClass = null; int i=0;
     * 
     * EList lifeLines = interaction.getLifelines(); model =
     * interaction.getModel();
     * 
     * for (Iterator iterator = lifeLines.iterator(); iterator.hasNext();) {
     * lifeLine = (Lifeline) iterator.next();
     * 
     * conElmt = lifeLine.getRepresents(); if(conElmt != null) { elmtType =
     * conElmt.getType(); } else { elmtType = null; }
     * 
     * if(elmtType != null) { // 다이어그램에 클래스 추가 elmtName = elmtType.getName();
     * 
     * // if(!seqElmtMap.containsKey(elmtName.toLowerCase())) { // 액터는 제거 if
     * (elmtName != null && !seqElmtMap.containsKey(elmtName.toLowerCase()) &&
     * !(elmtType instanceof Actor)) { //TODO:
     * UMLModeler.getUMLDiagramHelper().createNode() 개발
     * //seqElmtMap.put(elmtName.toLowerCase(),
     * UMLModeler.getUMLDiagramHelper().createNode(classDgm, elmtType));
     * 
     * if(isAddVoClass && voCmrIdx != -1) {
     * 
     * stList = elmtType.getAppliedStereotypes();
     * 
     * for(int j=0; j<stList.size(); j++) { stereotype =
     * (Stereotype)stList.get(j);
     * 
     * // 스테레오 타입이 Entity인 경우 클래스 다이어그램에 VO클래스 추가
     * if(stereotype.getName().equals(
     * ModelPropertyManager.getInstance().getDesignEntityStereotype())) { cmr =
     * ModelPropertyManager.getInstance().getEntity().getClassMappingRule1();
     * anlElmtName = elmtName;
     * 
     * // VO 클래스 명을 구하기 위해서 설계모델의 Entity클래스 명에서 분석모델의 Entity클래스 명을 구한다.
     * if(!cmr.getPrefix().equals(ManagerConstant.EMPTY_STRING)) { anlElmtName =
     * anlElmtName.replaceAll(cmr.getPrefix(), ManagerConstant.EMPTY_STRING); }
     * if(!cmr.getPostfix().equals(ManagerConstant.EMPTY_STRING)) { anlElmtName
     * = anlElmtName.replaceAll(cmr.getPostfix(), ManagerConstant.EMPTY_STRING);
     * }
     * 
     * voName =
     * ModelPropertyManager.getInstance().getEntity().getClassNameByMappingRule
     * (anlElmtName, voCmrIdx); voClass = ModelUtility.getClassByName(model,
     * voName);
     * 
     * if(voClass != null) { //TODO :
     * UMLModeler.getUMLDiagramHelper().createNode() 개발
     * //seqElmtMap.put(voClass.getName().toLowerCase(),
     * UMLModeler.getUMLDiagramHelper().createNode(classDgm, voClass));
     * voClassMap.put(voClass.getName().toLowerCase(), voClass); } }
     * 
     * if(stereotype.getName().equals(ModelPropertyManager.getInstance().
     * getDesignControlStereotype())) {
     * controlClassMap.put(elmtName.toLowerCase(), elmtType); } } } } } i++; } }
     * 
     * 
     * private static int getVoCmrIndex(){ int result = -1;
     * if(ModelPropertyManager
     * .getInstance().getClassDiagram().getVoClass().getMappingRule
     * ().getRuleName
     * ().equals(ModelPropertyManager.getInstance().getEntity().getClassMappingRule3
     * ().getRuleName())) { result = 3; } else
     * if(ModelPropertyManager.getInstance
     * ().getClassDiagram().getVoClass().getMappingRule
     * ().getRuleName().equals(ModelPropertyManager
     * .getInstance().getEntity().getClassMappingRule2().getRuleName())) {
     * result = 2; } else
     * if(ModelPropertyManager.getInstance().getClassDiagram()
     * .getVoClass().getMappingRule
     * ().getRuleName().equals(ModelPropertyManager.getInstance
     * ().getEntity().getClassMappingRule1().getRuleName())) { result = 1; }
     * return result; }
     */
}
