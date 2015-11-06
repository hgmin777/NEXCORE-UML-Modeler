/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.util.paste;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Property;

/**
 * 관계있는 요소들을 수집한다.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util.paste</li>
 * <li>설  명 : ElementCollector</li>
 * <li>작성일 : 2012. 1. 4.</li>
 * <li>작성자 : 김기선</li>
 * </ul>
 */
public class ElementCollector {
    /** 분석할 UML 요소들 */
    private Object[] elements;
    
    /**
     * ElementCollector
     * @param elements
     */
    public ElementCollector(Object[] elements) {
        this.elements = elements;
    }
    
    /**
     * 붙여넣을 려는 요소를 분석하여 필요한 UML 요소를 더 수집한다.
     * @param elements 분석할 UML 요소
     * @return Set<EObject> 더 수집된 UML 요소들
     */
    public Set<EObject> collect() {
        Set<EObject> collected = collect(elements, true);
        doCoherence(collected);
        
        return collected;
    }
    
    /**
     * 복사된 요소가 중복으로 복사되지 않도록 일관성(정합성?)을 맞춘다.
     * @param collected 복사하려는 수집된 요소
     */
    private void doCoherence(Set<EObject> collected) {
        Set<EObject> setExtra = new HashSet<EObject>(collected);
        Set<EObject> setUnnecessary = new HashSet<EObject>(collected);
        
        EObject[] extra = setExtra.toArray(new EObject[0]);
        EObject[] unnecessary = setUnnecessary.toArray(new EObject[0]);
        
        for (int i=0; i<unnecessary.length; i++) {
            boolean found = false;
            for (int j=0; j<extra.length && !found; j++) {
                if (extra[j] instanceof Diagram) continue;
                Iterator<EObject> iter = extra[j].eAllContents();
                while (iter.hasNext()) {
                    EObject eObject = iter.next();
                    if (unnecessary[i].equals(eObject)) {
                        found = true;
                        break;
                    }
                }
            }
            
            setExtra.remove(unnecessary[i]);
            if (!found) setUnnecessary.remove(unnecessary[i]);
        }
        
        collected.removeAll(setUnnecessary);
    }

    /**
     * 붙여넣을 려는 요소를 분석하여 필요한 UML 요소를 더 수집한다.
     * @param elements 분석할 UML 요소
     * @param addChildren 하위 자식 요소 추가 여부
     * @return Set<EObject> 더 수집된 UML 요소들
     */
    private Set<EObject> collect(Object[] elements, boolean addChildren) {
        Set<EObject> set = new HashSet<EObject>();

        // 우선 붙여넣으려는 UML 요소를 모아본다.
        // 범위는 Package, Collaboration, Interaction, Activity 안에 있는 것 까지만
        for (int i = 0; i < elements.length; i++) {
            if ((elements[i] instanceof EAnnotation) || (elements[i] instanceof Collaboration)
                || (elements[i] instanceof Interaction) || (elements[i] instanceof Activity)) {
                set.addAll(collect(((EObject) elements[i]).eContents().toArray(), false));
                if (addChildren)
                    set.add((EObject) elements[i]);

            } else if (elements[i] instanceof Diagram) {
                Iterator<EObject> iter = ((EObject) elements[i]).eAllContents();
                while (iter.hasNext()) {
                    EObject eObj = iter.next();
                    if ((eObj instanceof AbstractView)) {
                        Element umlModel = ((AbstractView) eObj).getUmlModel();
                        set.addAll(collectExtraElement(umlModel));
                    }
                }
                if (addChildren)
                    set.add((EObject) elements[i]);

            } else if (addChildren && (elements[i] instanceof Element)) {
                set.add((EObject) elements[i]);
            }
        }

        return set;
    }
    
    /**
     * 해당 UML 요소와 관련되어 있는 추가적인 요소들을 더 수집한다.
     * @param element 찾으려는 UML 요소
     * @return Set<EObject> 수집된 UML 요소
     */
    private Set<EObject> collectExtraElement(Element element) {
        Set<EObject> set = new HashSet<EObject>();
        
        if (!(element instanceof Property)) set.add(element);
        if (element instanceof Lifeline) {
            Lifeline lifeline = (Lifeline)element;
            if (lifeline.getRepresents() != null) {
                set.add(lifeline.getRepresents());
                if (lifeline.getRepresents().getType() != null)
                    set.add(lifeline.getRepresents().getType());
            }
        }
        
        return set;
    }
}
