/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.util.paste;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;

/**
 * 특정 UML 요소가 주어지는 부모에게 복사가 가능한지를 체크한 후 가능하면 복사시키는 역할을
 * 하는 클래스 (다이어그램은 제외)
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util.paste</li>
 * <li>설  명 : ElementCopier</li>
 * <li>작성일 : 2012. 1. 4.</li>
 * <li>작성자 : 김기선</li>
 * </ul>
 */
public class ElementCopier {
    /** 복사 */
    private static final int COPY = 1;
    
    /** 복사 가능 여부 */
    private static final int CHECK_COPYABLE = 2;
    
    /**
     * ElementCopier
     */
    private ElementCopier() { }
    
    /**
     * eobject가 복사할 수 있는 대상인지 아닌지를 판변한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @return boolean 복사 가능 여부
     */
    public static boolean isCopyable(EObject parent, EObject copied, EObject original) {
        if ((copied == parent) || (copied instanceof Model))
            return false;
        
        return execute(parent, copied, original, CHECK_COPYABLE);
    }
    
    /**
     * UML 요소가 부모로 복사할 수 있는지 체크한 후 복사 가능하면 복사후 결과값을 반환
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @return boolean 복사 성공 여부를 반환
     */
    public static boolean copy(EObject parent, EObject copied, EObject original) {
        return execute(parent, copied, original, COPY);
    }
    
    /**
     * UML 요소가 복사할 수 있는지 체크하거나 복사 후 결과값을 반환
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 복사 성공 여부를 반환
     */
    // 패턴화 대상
    private static boolean execute(EObject parent, EObject copied, EObject original, int something) {
        if (parent instanceof Package)
            return parentIsPackageAndDo(parent, copied, original, something);
        else if (parent instanceof Operation)
            return parentIsOperationAndDo(parent, copied, original, something);
        else if (parent instanceof Interaction) // Class 보다 먼저 검사해야 함
            return parentIsInteractionAndDo(parent, copied, original, something);
        else if (parent instanceof Component)   // Class 보다 먼저 검사해야 함
            return parentIsComponentAndDo(parent, copied, original, something);
        else if (parent instanceof Activity)    // Class 보다 먼저 검사해야 함
            return parentIsActivityAndDo(parent, copied, original, something);
        else if (parent instanceof Class)
            return parentIsClassAndDo(parent, copied, original, something);
        else if (parent instanceof Collaboration)
            return parentIsCollaborationAndDo(parent, copied, original, something);
        else if (parent instanceof Artifact)
            return parentIsArtifactAndDo(parent, copied, original, something);
        else if (parent instanceof Enumeration)
            return parentIsEnumerationAndDo(parent, copied, original, something);
        else if (parent instanceof Interface)
            return parentIsInterfaceAndDo(parent, copied, original, something);
        else if (parent instanceof Signal)
            return parentIsSignalAndDo(parent, copied, original, something);
        
        return false;
    }
    
    /**
     * 상위 부모가 Interaction 요소이고 자식 요소가 해당 Interaction에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsInteractionAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Interaction interaction = (Interaction)parent;
        if (copied instanceof Interaction) {
            result = false;
            
        } else if (copied instanceof Lifeline) {
            if (something == COPY) {
                Lifeline lifeline = (Lifeline)copied;
                if (parent.eContainer() instanceof Collaboration) {
                    Collaboration collaboration = (Collaboration)parent.eContainer();
                    ConnectableElement ce = lifeline.getRepresents();
                    if (ce != null)
                        collaboration.getOwnedAttributes().add((Property)ce);
                }
                interaction.getLifelines().add(lifeline);
            }
            result = true;
            
        } else if (copied instanceof InteractionFragment) {
            if (something == COPY) interaction.getFragments().add((InteractionFragment)copied);
            result = true;
            
        } else if (copied instanceof Message) {
            if (something == COPY) interaction.getMessages().add((Message)copied);
            result = true;
            
        } else if (copied instanceof Diagram && something == CHECK_COPYABLE) {
            result = true;
            
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Signal 요소이고 자식 요소가 해당 Signal에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsSignalAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Signal signal = (Signal) parent;
        if (copied instanceof Property) {
            if (something == COPY) signal.getOwnedAttributes().add((Property) copied);
            result = true;
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Interface 요소이고 자식 요소가 해당 Interface에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsInterfaceAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Interface interfaze = (Interface) parent;
        if (copied instanceof Property) {
            if (something == COPY) interfaze.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof Operation) {
            if (something == COPY) interfaze.getOwnedOperations().add((Operation) copied);
            result = true;
            
        } else if (copied instanceof Reception) {
            if (something == COPY) interfaze.getOwnedReceptions().add((Reception) copied);
            result = true;
            
        } else if (copied instanceof Class) {
            if (something == COPY) interfaze.getNestedClassifiers().add((Class) copied);
            result = true;
            
        } else if (copied instanceof Enumeration) {
            if (something == COPY) interfaze.getNestedClassifiers().add((Enumeration) copied);
            result = true;
            
        } else if (copied instanceof Collaboration) {
            if (something == COPY) interfaze.getNestedClassifiers().add((Collaboration) copied);
            result = true;
            
        } else if (copied instanceof CollaborationUse) {
            if (something == COPY) interfaze.getCollaborationUses().add((CollaborationUse) copied);
            result = true;
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Enumeration 요소이고 자식 요소가 해당 Enumeration에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsEnumerationAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Enumeration enumeration = (Enumeration) parent;
        if (copied instanceof EnumerationLiteral) {
            if (something == COPY) enumeration.getOwnedLiterals().add((EnumerationLiteral) copied);
            result = true;
            
        } else if (copied instanceof Property) {
            if (something == COPY) enumeration.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof Operation) {
            if (something == COPY) enumeration.getOwnedOperations().add((Operation) copied);
            result = true;
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Artifact 요소이고 자식 요소가 해당 Artifact에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsArtifactAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Artifact artifact = (Artifact) parent;
        if (copied instanceof Property) {
            if (something == COPY) artifact.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof Operation) {
            if (something == COPY) artifact.getOwnedOperations().add((Operation) copied);
            result = true;
            
        } else if (copied instanceof Artifact) {
            if (something == COPY) artifact.getNestedArtifacts().add((Artifact) copied);
            result = true;
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Collaboration 요소이고 자식 요소가 해당 Collaboration에
     * 속한다면 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsCollaborationAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Collaboration collaboration = (Collaboration) parent;
        if (copied instanceof Interaction) {
            if (something == COPY) collaboration.getOwnedBehaviors().add((Interaction) copied);
            result = true;
            
        } else if (copied instanceof Property) {
            if (something == COPY) collaboration.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof CollaborationUse) {
            if (something == COPY) collaboration.getCollaborationUses().add((CollaborationUse) copied);
            result = true;
            
        // 다이어그램 복사는 ElementPaster에서 함. 나중에 이 클래스로 옮길것
        } else if (copied instanceof Diagram && something == CHECK_COPYABLE) {
            result = true;
            
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Activity 요소이고 자식 요소가 해당 Activity에 속한다면
     * 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsActivityAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Activity activity = (Activity) parent;
        if (copied instanceof ActivityPartition) {
            if (something == COPY) activity.getGroups().add((ActivityPartition) copied);
            result = true;
            
        } else if (copied instanceof Action) {
            if (something == COPY) activity.getNodes().add((Action) copied);
            result = true;
            
        } else if (copied instanceof ActivityNode) {
            if (something == COPY) activity.getNodes().add((ActivityNode) copied);
            result = true;
            
        } else if (copied instanceof ActivityEdge) {
            if (something == COPY) activity.getEdges().add((ActivityEdge) copied);
            result = true;
            
        } else if (copied instanceof Diagram && something == CHECK_COPYABLE) {
            if (((Diagram)copied).getType() != DiagramType.SEQUENCE_DIAGRAM)
                result = true;
            
        }
        
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Class 요소이고 자식 요소가 해당 Class에 속한다면
     * 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsClassAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Class clazz = (Class) parent;
        if (copied instanceof Property) { // Property, Port
            if (something == COPY) clazz.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof Operation) {
            if (something == COPY) clazz.getOwnedOperations().add((Operation) copied);
            result = true;
            
        } else if (copied instanceof Reception) {
            if (something == COPY) clazz.getOwnedReceptions().add((Reception) copied);
            result = true;
            
        } else if (copied instanceof Class) {
            if (something == COPY) clazz.getNestedClassifiers().add((Class) copied);
            result = true;
            
        } else if (copied instanceof Enumeration) {
            if (something == COPY) clazz.getNestedClassifiers().add((Enumeration) copied);
            result = true;
            
        } else if (copied instanceof Interface) {
            if (something == COPY) clazz.getNestedClassifiers().add((Interface) copied);
            result = true;
            
        } else if (copied instanceof Activity) {
            if (something == COPY) clazz.getOwnedBehaviors().add((Activity) copied);
            result = true;
            
        } else if (copied instanceof CollaborationUse) {
            if (something == COPY) clazz.getCollaborationUses().add((CollaborationUse) copied);
            result = true;
            
        } else if (copied instanceof Interaction) {
            if (something == COPY) clazz.getNestedClassifiers().add((Interaction) copied);
            result = true;
        }
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Component 요소이고 자식 요소가 해당 Component에 속한다면
     * 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsComponentAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Component component = (Component) parent;
        if (copied instanceof Package) {
            if (something == COPY) component.getPackagedElements().add((Package) copied);
            result = true;
            
        } else if (copied instanceof Property) { // Property, Port
            if (something == COPY) component.getOwnedAttributes().add((Property) copied);
            result = true;
            
        } else if (copied instanceof Operation) {
            if (something == COPY) component.getOwnedOperations().add((Operation) copied);
            result = true;
            
        } else if (copied instanceof Class) { // Class, Component
            if (something == COPY) component.getPackagedElements().add((Class) copied);
            result = true;
            
        } else if (copied instanceof Enumeration) {
            if (something == COPY) component.getPackagedElements().add((Enumeration) copied);
            result = true;
            
        } else if (copied instanceof Interface) {
            if (something == COPY) component.getPackagedElements().add((Interface) copied);
            result = true;
            
        } else if (copied instanceof DataType) {// DataType, PrimitiveType
            if (something == COPY) component.getPackagedElements().add((DataType) copied);
            result = true;
            
        } else if (copied instanceof Activity) {
            if (something == COPY) component.getOwnedBehaviors().add((Activity) copied);
            result = true;
            
        } else if (copied instanceof Collaboration) {
            if (something == COPY) component.getPackagedElements().add((Collaboration) copied);
            result = true;
            
        } else if (copied instanceof CollaborationUse) {
            if (something == COPY) component.getCollaborationUses().add((CollaborationUse) copied);
            result = true;
            
        } else if (copied instanceof Interaction) {
            if (something == COPY) component.getOwnedBehaviors().add((Interaction) copied);
            result = true;
        }
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Operation 요소이고 자식 요소가 Parameter라면
     * 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsOperationAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Operation operation = (Operation) parent;
        if (copied instanceof Parameter) {
            if (something == COPY) operation.getOwnedParameters().add((Parameter) copied);
            result = true;
        }
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 상위 부모가 Package 요소이고 자식 요소가 PackageableElement라면
     * 붙이기를 수행한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     * @param something 복사할 것인지 복사 가능여부만 체크할 것인지 결정하는 플래그
     * @return boolean 부모로 복사 성공 여부
     */
    private static boolean parentIsPackageAndDo(EObject parent, EObject copied, EObject original, int something) {
        boolean result = false;
        
        Package _package = (Package)parent;
        if (copied instanceof Interaction) {
            result = false;
            
        } else if (copied instanceof PackageableElement) {
            if (something == COPY) _package.getPackagedElements().add((PackageableElement) copied);
            result = true;
            
        } else if (copied instanceof Diagram && something == CHECK_COPYABLE) {
            result = true;
            
        }
        if (result && something == COPY) applyStereotypes(parent, copied, original); 
        
        return result;
    }
    
    /**
     * 복사하려는 요소와 자식 요소들에 적용된 스테레오타입을 적용한다.
     * @param parent UML 요소를 붙여 넣을 부모
     * @param copied 복사된 UML 요소
     * @param original 원본 UML 요소
     */
    private static void applyStereotypes(EObject parent, EObject copied, EObject original) {
        ProjectUtil.copyStereotype(parent, copied, original);
        
        // 자식 요소는 이름있는 요소만 스테레오타입을 적용시킨다. (기존 코드 참조)
        if (!(copied instanceof Element) || !(original instanceof Element)) return;
        
        Element originalElm = (Element)original;
        Element copiedElm = (Element)copied;
        Set<NamedElement> collected = new HashSet<NamedElement>();
        
        for (Element elm : originalElm.allOwnedElements()) {
            if ((elm instanceof NamedElement) && (((NamedElement)elm).getName() != null)
                    && (elm.getAppliedStereotypes().size() > 0))
                collected.add((NamedElement)elm);
        }
        
        for (Element targetElement : copiedElm.allOwnedElements()) {
            if (collected.size() <= 0) break;
            
            Element applied = null;
            Iterator<NamedElement> iter = collected.iterator();
            while (iter.hasNext()) {
                NamedElement sourceElement = iter.next();
                if (targetElement.getClass().equals(sourceElement.getClass())) {
                    NamedElement ne = (NamedElement)targetElement;
                    if (sourceElement.getName().equals(ne.getName())) {
                        applied = sourceElement;
                        ProjectUtil.copyStereotype(copied, targetElement, sourceElement);
                    }
                }
            }
            
            collected.remove(applied);
        }
    }
}
