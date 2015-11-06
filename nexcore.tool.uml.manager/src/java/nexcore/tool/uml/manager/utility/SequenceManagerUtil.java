/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.manager.utility;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * 유스케이스 추적 매트릭스 생성
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.utility</li>
 * <li>설  명 : SequenceManagerUtil</li>
 * <li>작성일 : 2012. 1. 19.</li>
 * <li>작성자 : 황선림 </li>
 * </ul>
 */
public class SequenceManagerUtil {
	
	/**
	 * 해당 타입과 관련된 Interface, SuperClass 등을 합해서 이름이 같은 오퍼레이션을 리턴한다.
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public static Operation getOperationFromAll(Type type, String name) {
		
		List<Operation> operations = getTypeOperations(type);
		for (Operation operation : operations) {
			if (name.equals(operation.getName())) {
				return operation;
			}
		}
		
		return null;
	}

	/**
     * 해당 Type의 Operation을 리턴한다.
     * 
     * 
     * @param type
     * @return List<Operation>
     * @author 황선림
     */
    public static List<Operation> getTypeOperations(Type type) {

        List<Operation> result = new ArrayList<Operation>();
        if (type == null) {
            return result;

        } else if (type instanceof org.eclipse.uml2.uml.Class) {
            org.eclipse.uml2.uml.Class c = (org.eclipse.uml2.uml.Class) type;

            result.addAll(getProperOperations(c.getAllOperations()));

            // operation within interface
            EList<Interface> list = c.getAllImplementedInterfaces();
            for (int i = 0; i < list.size(); i++) {
                result.addAll(getProperOperations(list.get(i).getAllOperations()));
            }

        } else if (type instanceof Interface) {
            result = getProperOperations(((Interface) type).getAllOperations());

        } else {
            return result;

        }

        // operation within Abstract class
        EList<Dependency> dependencyList = type.getClientDependencies();
        EList<NamedElement> suppliers = null;
        for (Dependency dependency : dependencyList) {
            if (dependency instanceof Abstraction) {
                suppliers = dependency.getSuppliers();
                for (NamedElement element : suppliers) {
                    if (element instanceof Class) {
                        result.addAll(getProperOperations(((Class) element).getAllOperations()));
                    }
                }
            }
        }

        return result;

    }
    
    /**
     * visibility를 체크해서 적합한 operation들만 list로 리턴한다.
     * 
     * @param allOperations
     * @return List<Operation>
     */
    private static List<Operation> getProperOperations(EList<Operation> allOperations) {

        List<Operation> result = new ArrayList<Operation>();
        for (Operation operation : allOperations) {
            if (VisibilityKind.PRIVATE_LITERAL.equals(operation.getVisibility())) {
                continue;
            }
            result.add(operation);
        }
        return result;
    }
    
}
