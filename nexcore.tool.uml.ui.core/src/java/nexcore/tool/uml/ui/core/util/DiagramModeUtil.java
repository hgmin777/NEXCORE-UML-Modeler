/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.internal.runtime.IRuntimeConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : DiagramModeUtil</li>
 * <li>작성일 : 2011. 11. 11.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class DiagramModeUtil {

    /**
     * 
     * @param elements
     * @param target
     * @return String 이동되지 않은 다이어그램의 이름(Comma로 구분됨)
     */
	public static String moveDiagram(Collection<Object> elements, EObject target) {

		String notMovedDiagrams = UICoreConstant.EMPTY_STRING;
		
        final ChangeRecorder recorder = new ChangeRecorder(DomainRegistry.getUMLDomain().getResourceSet());
        try {
            List<Diagram> activityDiagramList = new ArrayList<Diagram>();
            List<Diagram> classDiagramList = new ArrayList<Diagram>();
            List<Diagram> sequenceDiagramList = new ArrayList<Diagram>();
            List<Diagram> usecaseDiagramList = new ArrayList<Diagram>();
            List<Diagram> componentDiagramList = new ArrayList<Diagram>();

            for (Iterator<Object> iterator = elements.iterator(); iterator.hasNext();) {
                Object object = iterator.next();
                if (object instanceof Diagram) {
                    Diagram diagram = (Diagram) object;
                    if (diagram.getType() == DiagramType.ACTIVITY_DIAGRAM) {
                        if (target instanceof Model || target instanceof Package || target instanceof Activity) {
                            activityDiagramList.add(diagram);
                        } else {
                        	if (UICoreConstant.EMPTY_STRING.equals(notMovedDiagrams)) {
                        		notMovedDiagrams = diagram.getName();
                        	} else {
                        		notMovedDiagrams += UICoreConstant.PROJECT_CONSTANTS__COMMA + diagram.getName();
                        	}
                        }
                    } else if (diagram.getType() == DiagramType.CLASS_DIAGRAM) {
                        classDiagramList.add(diagram);
                    } else if (diagram.getType() == DiagramType.SEQUENCE_DIAGRAM) {
                        sequenceDiagramList.add(diagram);
                    } else if (diagram.getType() == DiagramType.USE_CASE_DIAGRAM) {
                        usecaseDiagramList.add(diagram);
                    } else if (diagram.getType() == DiagramType.COMPONENT_DIAGRAM) {
                        componentDiagramList.add(diagram);
                    }
                }
            }
            if (activityDiagramList.size() > 0) {
                moveActivityDiagram(activityDiagramList, target);
            }
            if (classDiagramList.size() > 0) {
                moveClassDiagram(classDiagramList, target);
            }
            if (sequenceDiagramList.size() > 0) {
                moveSequenceDiagram(sequenceDiagramList, target);
            }
            if (usecaseDiagramList.size() > 0) {
                moveUsecaseDiagram(usecaseDiagramList, target);
            }
            if (componentDiagramList.size() > 0) {
                moveComponentDiagram(componentDiagramList, target);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ProjectUtil.rollbackResourceSet(recorder);
//            return new Status(Status.ERROR, IRuntimeConstants.PI_RUNTIME, Status.ERROR, e.getMessage(), null);
        }

        return notMovedDiagrams;
//        return Status.OK_STATUS;
    }

    /**
     * 
     * 
     * 
     * @param diagramList
     * @param target
     * @return IStatus
     */
    private static IStatus moveSequenceDiagram(final List<Diagram> diagramList, final EObject target) {
        Map<Collaboration, Map<Interaction, List<Diagram>>> scg = new HashMap<Collaboration, Map<Interaction, List<Diagram>>>();
        Map<Collaboration, Map<Interaction, List<Diagram>>> cg = new HashMap<Collaboration, Map<Interaction, List<Diagram>>>();

        for (Diagram source : diagramList) {
            EObject parent = ((Diagram) source).getParent();

            if (parent instanceof Interaction) {
                Interaction interaction = (Interaction) parent;

                EObject container = interaction.eContainer();

                if (container instanceof Collaboration) {
                    Collaboration collaboration = (Collaboration) container;

                    { // 이동할 시퀀스다이어그램의 Collaboration, Interaction 정보 map 생성
                        if (scg.containsKey(container)) {
                            Map<Interaction, List<Diagram>> interactionGroup = scg.get(container);
                            if (interactionGroup.containsKey(interaction)) {
                                interactionGroup.get(interaction).add(source);
                            } else {
                                List<Diagram> diagrams = new ArrayList<Diagram>();
                                diagrams.add(source);
                                interactionGroup.put(interaction, diagrams);
                            }
                        } else {
                            Map<Interaction, List<Diagram>> interactionGroup = new HashMap<Interaction, List<Diagram>>();
                            List<Diagram> diagrams = new ArrayList<Diagram>();
                            diagrams.add(source);
                            interactionGroup.put(interaction, diagrams);

                            scg.put(collaboration, interactionGroup);
                        }
                    }

                    { // Collaboration 에 속한 전체 Interaction 및 전체 Diagram 정보 map
                        // 생성
                        EList<Behavior> ownedBehaviors = collaboration.getOwnedBehaviors();
                        for (Behavior behavior : ownedBehaviors) {
                            if (behavior instanceof Interaction) {

                                if (cg.containsKey(container)) {
                                    Map<Interaction, List<Diagram>> interactionGroup = cg.get(container);
                                    Interaction ir = (Interaction) behavior;
                                    if (!interactionGroup.containsKey(ir)) {
                                        interactionGroup.put(ir, new ArrayList<Diagram>());
                                    }
                                } else {
                                    Map<Interaction, List<Diagram>> interactionGroup = new HashMap<Interaction, List<Diagram>>();

                                    Interaction ir = (Interaction) behavior;
                                    if (!interactionGroup.containsKey(ir)) {
                                        interactionGroup.put(ir, new ArrayList<Diagram>());
                                    }

                                    cg.put(collaboration, interactionGroup);
                                }
                            }
                        }

                        if (cg.containsKey(collaboration)) {
                            Map<Interaction, List<Diagram>> interactionGroup = cg.get(collaboration);
                            if (interactionGroup.containsKey(interaction)) {
                                interactionGroup.get(interaction).add(source);
                            } else {
                                List<Diagram> diagrams = new ArrayList<Diagram>();
                                diagrams.add(source);

                                interactionGroup.put(interaction, diagrams);
                            }
                        }
                    }
                }

            }
        }

        for (Iterator<Collaboration> it = cg.keySet().iterator(); it.hasNext();) {
            Collaboration collaboration = (Collaboration) it.next();
            EList<Behavior> ownedBehaviors = collaboration.getOwnedBehaviors();

            int interactionCnt = 0;

            for (Behavior behavior : ownedBehaviors) {
                if (behavior instanceof Interaction) {
                    interactionCnt++;
                }
            }

            Map<Interaction, List<Diagram>> sourceInteractionGroup = scg.get(collaboration);

            if (sourceInteractionGroup != null && interactionCnt == sourceInteractionGroup.size()) {
                EObject container = collaboration.eContainer();
                if (container == target) {
                    // 다이어그램을 포함하고 있는 Package가 target 일 경우 이동 못함.
                    continue;
                }
                moveInteraction(collaboration, sourceInteractionGroup, target);
            } else {
                Collaboration targetCollaboration = UMLHelper.createCollaboration();
                targetCollaboration.setName(collaboration.getName());
                moveInteraction(collaboration, targetCollaboration, sourceInteractionGroup, target);
            }

        }

        return Status.OK_STATUS;
    }

    /**
     * 
     * 
     * 
     * @param orgCollaboration
     * @param targetCollaboration
     * @param collaborationGroup
     * @param sourceInteractionGroup
     * @param target
     * @return IStatus
     */
    private static IStatus moveInteraction(Collaboration orgCollaboration, Collaboration targetCollaboration,
                                           Map<Interaction, List<Diagram>> sourceInteractionGroup, final EObject target) {
        int allDiagramCnt = 0;
        List<Diagram> diagrams = new ArrayList<Diagram>();

        EList<Behavior> ownedBehaviors = orgCollaboration.getOwnedBehaviors();
        for (Behavior behavior : ownedBehaviors) {
            if (behavior instanceof Interaction) {
                Interaction interaction = (Interaction) behavior;

                EAnnotation annotation = interaction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);

                EList<EObject> contents = annotation.getContents();

                for (EObject content : contents) {
                    if (content instanceof Diagram) {
                        allDiagramCnt++;
                    }
                }
            }
        }

        for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
            Interaction interaction = (Interaction) iterator.next();

            List<Diagram> c = sourceInteractionGroup.get(interaction);
            if (c.size() > 0) {
                diagrams.addAll(c);
            }
        }

        for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
            Interaction interaction = (Interaction) iterator.next();

            EAnnotation annotation = interaction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);

            EList<EObject> contents = annotation.getContents();
            Interaction targetInteraction = null;

            int diagramCnt = 0;

            // 1. uml:Interaction 에 ActivityDiagram 이 아닌 다른 다이어그램이 포함된 경우
            // 2. uml:Interaction 에 Diagram 이 1개 이상인 경우
            // ==> 새로운 uml:Interaction 를 생성한다.
            for (EObject content : contents) {
                if (content instanceof Diagram) {
                    diagramCnt++;
                }
            }

            List<Diagram> sourceDiagramList = sourceInteractionGroup.get(interaction);

            for (EObject content : contents) {
                if (content instanceof Diagram) {
                    Diagram diagram = (Diagram) content;
                    if (diagram.getType() != DiagramType.SEQUENCE_DIAGRAM) {
                        if (target instanceof Package) {

                            targetInteraction = UMLHelper.createInteraction();
                            targetInteraction.setName(interaction.getName());
                            targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                            ((Package) target).getPackagedElements().add(targetCollaboration);

                            for (Diagram sourceDiagram : sourceDiagramList) {
                                sourceDiagram.setParent(targetInteraction);
                            }

                        }
                        break;
                    }
                }
            }

            if (diagramCnt == sourceDiagramList.size()) {
                targetInteraction = interaction;
                if (target instanceof Package) {
                    targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                    ((Package) target).getPackagedElements().add((PackageableElement) targetCollaboration);
                    
                    moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                    continue;
                } else if (target instanceof Collaboration) {
                    ((Collaboration) target).getOwnedBehaviors().add(targetInteraction);
                    
                    moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                    continue;
                } else if (target instanceof Interaction) {
                    targetInteraction = (Interaction) target;
                    for (Diagram sourceDiagram : sourceDiagramList) {
                        sourceDiagram.setParent(targetInteraction);
                    }
                    
                    moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                }
            } else {
                if (targetInteraction == null) {
                    if (target instanceof Package) {
                        targetInteraction = UMLHelper.createInteraction();
                        targetInteraction.setName(interaction.getName());
                        targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                        ((Package) target).getPackagedElements().add(targetCollaboration);

                        for (Diagram sourceDiagram : sourceDiagramList) {
                            sourceDiagram.setParent(targetInteraction);
                        }
                    } else if (target instanceof Collaboration) {
                        targetCollaboration = (Collaboration) target;

                        targetInteraction = UMLHelper.createInteraction();
                        targetInteraction.setName(interaction.getName());
                        for (Diagram sourceDiagram : sourceDiagramList) {
                            sourceDiagram.setParent(targetInteraction);
                        }

                        targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                    } else if (target instanceof Interaction) {
                        targetInteraction = (Interaction) target;
                        for (Diagram sourceDiagram : sourceDiagramList) {
                            sourceDiagram.setParent(targetInteraction);
                        }
                    }
                    
                    moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                }
            }

            for (Diagram source : sourceDiagramList) {
                List<EObject> refList = new ArrayList<EObject>();

                Map<EObject, Collection<Setting>> findResult = EcoreUtil.UsageCrossReferencer.find(source.eContents());

                for (Iterator<EObject> ir = findResult.keySet().iterator(); ir.hasNext();) {
                    EObject eObj = (EObject) ir.next();
                    refList.add(eObj);
                }

                TreeIterator<EObject> allContents = source.eAllContents();

                while (allContents.hasNext()) {
                    EObject eObj = allContents.next();
                    if (eObj instanceof AbstractView) {
                        if (((AbstractView) eObj).getParent() == interaction) {
                            ((AbstractView) eObj).setParent(targetInteraction);
                        }
                    } else {
                        // System.err.println(eObj);
                    }

                    refList.remove(eObj);
                }
                refList.remove(source);

                EAnnotation eAnnotation = targetInteraction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                if (null == eAnnotation) {
                    eAnnotation = targetInteraction.createEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                    targetInteraction.getEAnnotations().add(eAnnotation);
                }

                eAnnotation.getContents().add(source);
            }
        }

        return Status.OK_STATUS;
    }

    /**
     * moveInteraction
     *  
     * @param targetCollaboration
     * @param sourceInteractionGroup
     * @param target
     * @return IStatus
     */
    private static IStatus moveInteraction(Collaboration targetCollaboration,
                                           Map<Interaction, List<Diagram>> sourceInteractionGroup, final EObject target) {
        int allDiagramCnt = 0;
        List<Diagram> diagrams = new ArrayList<Diagram>();

        Collaboration collaboration = targetCollaboration;

        EList<Behavior> ownedBehaviors = collaboration.getOwnedBehaviors();
        for (Behavior behavior : ownedBehaviors) {
            if (behavior instanceof Interaction) {
                Interaction interaction = (Interaction) behavior;

                EAnnotation annotation = interaction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);

                EList<EObject> contents = annotation.getContents();

                for (EObject content : contents) {
                    if (content instanceof Diagram) {
                        allDiagramCnt++;
                    }
                }
            }
        }

        for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
            Interaction interaction = (Interaction) iterator.next();

            List<Diagram> diagram = sourceInteractionGroup.get(interaction);
            if (diagram.size() > 0) {
                diagrams.addAll(diagram);
            }
        }

        if (allDiagramCnt == diagrams.size()) {
            // 1. 이동하려는 다이어그램이 Collaboration 에 포함된 전체일때
            if (target instanceof Package) {
                // 1.1 Collaboration 자체를 이동한다.
                Package targetPackage = (Package) target;
                targetPackage.getPackagedElements().add(targetCollaboration);

                // 시퀀스다이어그램에서 참조관계가 있는 모델이동.
                moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                return Status.OK_STATUS;
            } else if (target instanceof Collaboration) {
                // 1.2 target Collaboration 으로 전체 Interaction 를 이동한다.
                targetCollaboration = (Collaboration) target;

                for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
                    Interaction interaction = (Interaction) iterator.next();
                    targetCollaboration.getOwnedBehaviors().add(interaction);
                }

                moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                return Status.OK_STATUS;
            } else if (target instanceof Interaction) {
                // 1.3 target Interaction 으로 전체 Diagram 를 이동한다.
                Interaction targetInteraction = (Interaction) target;

                for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
                    Interaction interaction = (Interaction) iterator.next();

                    List<Diagram> list = sourceInteractionGroup.get(interaction);
                    for (Diagram sourceDiagram : list) {
                        sourceDiagram.setParent(targetInteraction);
                        EAnnotation eAnnotation = targetInteraction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                        if (null == eAnnotation) {
                            eAnnotation = targetInteraction.createEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                            targetInteraction.getEAnnotations().add(eAnnotation);
                        }
                        eAnnotation.getContents().add(sourceDiagram);
                    }
                }

                moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);

                return Status.OK_STATUS;
            }
        } else {
            // 2. 이동하려는 다이어그램이 Collaboration 에 포함된 전체가 아닐때
            targetCollaboration = UMLHelper.createCollaboration();
            targetCollaboration.setName(collaboration.getName());
            for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
                Interaction interaction = (Interaction) iterator.next();

                EAnnotation annotation = interaction.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);

                EList<EObject> contents = annotation.getContents();
                Interaction targetInteraction = null;

                int diagramCnt = 0;

                // 1. uml:Interaction 에 ActivityDiagram 이 아닌 다른 다이어그램이 포함된 경우
                // 2. uml:Interaction 에 Diagram 이 1개 이상인 경우
                // ==> 새로운 uml:Interaction 를 생성한다.

                for (EObject content : contents) {
                    if (content instanceof Diagram) {
                        diagramCnt++;
                    }
                }

                List<Diagram> sourceDiagramList = sourceInteractionGroup.get(interaction);

                for (EObject content : contents) {
                    if (content instanceof Diagram) {
                        Diagram diagram = (Diagram) content;
                        if (diagram.getType() != DiagramType.SEQUENCE_DIAGRAM) {
                            if (target instanceof Package) {

                                targetInteraction = UMLHelper.createInteraction();
                                targetInteraction.setName(interaction.getName());
                                targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                                ((Package) target).getPackagedElements().add(targetCollaboration);

                                for (Diagram sourceDiagram : sourceDiagramList) {
                                    sourceDiagram.setParent(targetInteraction);
                                }
                            }
                            break;
                        }
                    }
                }

                if (diagramCnt == sourceDiagramList.size()) {
                    targetInteraction = interaction;
                    if (target instanceof Package) {
                        targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                        ((Package) target).getPackagedElements().add((PackageableElement) targetCollaboration);
                    } else if (target instanceof Collaboration) {
                        ((Collaboration) target).getOwnedBehaviors().add(targetInteraction);
                    } else if (target instanceof Interaction) {
                        targetInteraction = (Interaction) target;
                        for (Diagram sourceDiagram : sourceDiagramList) {
                            sourceDiagram.setParent(targetInteraction);
                        }
                    }
                    
                    moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                } else {
                    if (targetInteraction == null) {
                        if (target instanceof Package) {
                            targetInteraction = UMLHelper.createInteraction();
                            targetInteraction.setName(interaction.getName());
                            targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                            ((Package) target).getPackagedElements().add(targetCollaboration);

                            for (Diagram sourceDiagram : sourceDiagramList) {
                                sourceDiagram.setParent(targetInteraction);
                            }
                        } else if (target instanceof Collaboration) {
                            targetCollaboration = (Collaboration) target;

                            targetInteraction = UMLHelper.createInteraction();
                            targetInteraction.setName(interaction.getName());
                            for (Diagram sourceDiagram : sourceDiagramList) {
                                sourceDiagram.setParent(targetInteraction);
                            }

                            targetCollaboration.getOwnedBehaviors().add(targetInteraction);
                        } else if (target instanceof Interaction) {
                            targetInteraction = (Interaction) target;
                            for (Diagram sourceDiagram : sourceDiagramList) {
                                sourceDiagram.setParent(targetInteraction);
                            }
                        }
                        
                        moveSequenceDiagramReferencer(targetCollaboration, sourceInteractionGroup, target);
                    }
                }
            }

        }

        return Status.OK_STATUS;
    }

    /**
     * moveSequenceDiagramReferencer
     *  
     * @param targetCollaboration
     * @param sourceInteractionGroup
     * @param target void
     */
    private static void moveSequenceDiagramReferencer(Collaboration targetCollaboration,
                                                      Map<Interaction, List<Diagram>> sourceInteractionGroup,
                                                      final EObject target) {

        Package targetPackage = null;
        Interaction targetInteraction = null;
        if (target instanceof Package) {
            targetPackage = (Package) target;
        } else if (target instanceof Collaboration) {
            if (target.eContainer() instanceof Package) {
                targetPackage = (Package) target.eContainer();
            }
        } else if (target instanceof Interaction) {
            targetInteraction = (Interaction) target;

            if (target.eContainer().eContainer() instanceof Package) {
                targetPackage = (Package) target.eContainer().eContainer();
            }
        }

        for (Iterator<Interaction> iterator = sourceInteractionGroup.keySet().iterator(); iterator.hasNext();) {
            Interaction interaction = (Interaction) iterator.next();

            List<Diagram> sourceDiagramList = sourceInteractionGroup.get(interaction);
            for (Diagram source : sourceDiagramList) {
                Map<EObject, Collection<Setting>> crossRefMap = EcoreUtil.UsageCrossReferencer.find(source.eContents());

                if (target instanceof Interaction) {
                    targetInteraction = (Interaction) target;
                } else {
                    targetInteraction = interaction;
                }
                for (Iterator<EObject> ir = crossRefMap.keySet().iterator(); ir.hasNext();) {
                    EObject eObj = (EObject) ir.next();

                    if (eObj instanceof Message) {
                        Message message = (Message) eObj;
                        targetInteraction.getMessages().add(message);
                    } else if (eObj instanceof Lifeline) {
                        Lifeline lifeline = (Lifeline) eObj;
                        targetInteraction.getLifelines().add(lifeline);

                        EList<InteractionFragment> coveredBys = lifeline.getCoveredBys();
                        for (InteractionFragment fragment : coveredBys) {
                            targetInteraction.getFragments().add(fragment);

                            if (fragment instanceof OccurrenceSpecification) {

                                OccurrenceSpecification os = (OccurrenceSpecification) fragment;
                                Event event = os.getEvent();
                                targetPackage.getPackagedElements().add(event);

                                Map<EObject, Collection<Setting>> findResult = EcoreUtil.UsageCrossReferencer.find(fragment.eContents());
                                for (Iterator<EObject> ir1 = findResult.keySet().iterator(); ir1.hasNext();) {
                                    EObject refObj = (EObject) ir1.next();

                                    if (refObj instanceof OccurrenceSpecification) {
                                        os = (OccurrenceSpecification) refObj;
                                        event = os.getEvent();
                                        targetPackage.getPackagedElements().add(event);
                                    }
                                }
                            } else if (fragment instanceof InteractionFragment) {

                                Map<EObject, Collection<Setting>> findResult = EcoreUtil.UsageCrossReferencer.find(fragment.eContents());
                                for (Iterator<EObject> ir1 = findResult.keySet().iterator(); ir1.hasNext();) {
                                    EObject refObj = (EObject) ir1.next();

                                    if (refObj instanceof OccurrenceSpecification) {
                                        OccurrenceSpecification os = (OccurrenceSpecification) refObj;
                                        Event event = os.getEvent();
                                        targetPackage.getPackagedElements().add(event);
                                    }
                                }
                            }
                        }

                        ConnectableElement represents = lifeline.getRepresents();
                        targetCollaboration.getOwnedAttributes().add((Property) represents);
                    }
                }
            }
        }
    }

    /**
     * 
     * 
     * 
     * @param diagramList
     * @param target
     * @return IStatus
     */
    private static IStatus moveComponentDiagram(final List<Diagram> diagramList, final EObject target) {
        return moveClassDiagram(diagramList, target);
    }

    /**
     * 
     * 
     * 
     * @param diagramList
     * @param target
     * @return IStatus
     */
    private static IStatus moveUsecaseDiagram(final List<Diagram> diagramList, final EObject target) {
        return moveClassDiagram(diagramList, target);
    }

    /**
     * 
     * 
     * 
     * @param diagramList
     * @param target
     * @return IStatus
     */
    private static IStatus moveClassDiagram(final List<Diagram> diagramList, final EObject target) {
        for (Diagram source : diagramList) {
            EObject parent = ((Diagram) source).getParent();
            if (!(target instanceof Diagram)) {
                source.setParent(target);
            } else {
                System.err.println(target);
                return new Status(Status.ERROR, IRuntimeConstants.PI_RUNTIME, Status.ERROR, "", null);
            }

            TreeIterator<EObject> allContents = source.eAllContents();

            while (allContents.hasNext()) {
                EObject eObj = allContents.next();
                if (eObj instanceof AbstractView) {
                    if (((AbstractView) eObj).getParent() == parent) {
                        ((AbstractView) eObj).setParent(target);
                    }
                } else {
                    // System.err.println(eObj);
                }
            }

            if (target instanceof Element) {
                Element targetElement = (Element) target;

                EAnnotation eAnnotation = targetElement.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                if (null == eAnnotation) {
                    eAnnotation = targetElement.createEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                    targetElement.getEAnnotations().add(eAnnotation);
                }

                eAnnotation.getContents().add(source);
            } else {
                // System.err.println(target);
            }
        }

        return Status.OK_STATUS;
    }

    /**
     * 
     * 
     * 
     * @param diagramList
     * @param target
     * @return IStatus
     */
    private static IStatus moveActivityDiagram(final List<Diagram> diagramList, final EObject target) {
        Map<Activity, List<Diagram>> activityGroup = new HashMap<Activity, List<Diagram>>();
        for (Diagram source : diagramList) {
            EObject parent = ((Diagram) source).getParent();

            if (parent instanceof Activity) {
                Activity activity = (Activity) parent;
                if (activityGroup.containsKey(activity)) {
                    activityGroup.get(activity).add(source);
                } else {
                    List<Diagram> dl = new ArrayList<Diagram>();
                    dl.add(source);

                    activityGroup.put(activity, dl);
                }
            }
        }

        for (Iterator<Activity> iterator = activityGroup.keySet().iterator(); iterator.hasNext();) {
            Activity activity = (Activity) iterator.next();

            EAnnotation annotation = activity.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);

            EList<EObject> contents = annotation.getContents();

            int diagramCnt = 0;

            // 1. uml:Activity 에 ActivityDiagram 이 아닌 다른 다이어그램이 포함된 경우
            // 2. uml:Activity 에 Diagram 이 1개 이상인 경우
            // ==> 새로운 uml:Activity 를 생성한다.

            for (EObject content : contents) {
                if (content instanceof Diagram) {
                    diagramCnt++;
                }
            }

            Activity targetActivity = null;

            List<Diagram> diagrams = activityGroup.get(activity);

            for (EObject content : contents) {
                if (content instanceof Diagram) {
                    Diagram diagram = (Diagram) content;
                    if (diagram.getType() != DiagramType.ACTIVITY_DIAGRAM) {
                        if (target instanceof Package) {
                            targetActivity = UMLHelper.createActivity();
                            ((Package) target).getPackagedElements().add(targetActivity);

                            for (Diagram sourceDiagram : diagrams) {
                                sourceDiagram.setParent(targetActivity);
                            }
                        }
                        break;
                    }
                }
            }

            if (diagramCnt == diagrams.size()) {
                targetActivity = activity;
                if (target instanceof Package) {
                    // uml:Activity 에 Diagram 이 1개 이상인 경우
                    // uml:Activity 를 이동 시킨다.
                    ((Package) target).getPackagedElements().add((PackageableElement) targetActivity);
                    continue;
                } else if (target instanceof Activity) {
                    targetActivity = (Activity) target;
                    for (Diagram sourceDiagram : diagrams) {
                        sourceDiagram.setParent(targetActivity);
                    }
                }
            } else {
                if (targetActivity == null) {
                    if (target instanceof Package) {
                        targetActivity = UMLHelper.createActivity();
                        ((Package) target).getPackagedElements().add(targetActivity);
                        for (Diagram sourceDiagram : diagrams) {
                            sourceDiagram.setParent(targetActivity);
                        }
                    } else if (target instanceof Activity) {
                        targetActivity = (Activity) target;
                        for (Diagram sourceDiagram : diagrams) {
                            sourceDiagram.setParent(targetActivity);
                        }
                    }
                }
            }

            for (Diagram source : diagrams) {
                List<EObject> refList = new ArrayList<EObject>();

                Map<EObject, Collection<Setting>> find4 = EcoreUtil.UsageCrossReferencer.find(source.eContents());

                for (Iterator<EObject> ir = find4.keySet().iterator(); ir.hasNext();) {
                    EObject eObj = (EObject) ir.next();

                    if (!refList.contains(eObj)) {
                        refList.add(eObj);
                    }
                }

                TreeIterator<EObject> allContents = source.eAllContents();

                while (allContents.hasNext()) {
                    EObject eObj = allContents.next();
                    if (eObj instanceof AbstractView) {
                        if (((AbstractView) eObj).getParent() == activity) {
                            ((AbstractView) eObj).setParent(targetActivity);
                        }
                    } else {
                        // System.err.println(eObj);
                    }

                    refList.remove(eObj);
                }
                refList.remove(source);

                for (EObject eObj : refList) {
                    if (eObj instanceof ControlNode) {
                        targetActivity.getNodes().add((ControlNode) eObj);
                    } else if (eObj instanceof ActivityEdge) {
                        targetActivity.getEdges().add((ActivityEdge) eObj);
                    } else if (eObj instanceof DataStoreNode) {
                        targetActivity.getNodes().add((DataStoreNode) eObj);
                    } else if (eObj instanceof OpaqueAction) {
                        targetActivity.getNodes().add((OpaqueAction) eObj);
                    } else if (eObj instanceof CentralBufferNode) {
                        targetActivity.getNodes().add((CentralBufferNode) eObj);
                    } else if (eObj instanceof ActivityGroup) {
                        targetActivity.getGroups().add((ActivityGroup) eObj);
                    } else {
                        // System.out.println(eObj);
                    }
                }

                EAnnotation eAnnotation = targetActivity.getEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                if (null == eAnnotation) {
                    eAnnotation = targetActivity.createEAnnotation(UMLHelper.DIAGRAM_SOURCE_NAME);
                    targetActivity.getEAnnotations().add(eAnnotation);
                }

                eAnnotation.getContents().add(source);
            }
        }

        return Status.OK_STATUS;
    }

}
