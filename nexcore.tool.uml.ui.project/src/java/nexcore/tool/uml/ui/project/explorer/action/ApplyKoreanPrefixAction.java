/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : ApplyKoreanPrefixAction</li>
 * <li>작성일 : 2011. 12. 02.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class ApplyKoreanPrefixAction extends CommonActionDelegate {

    /** 선택한 객체들 */
    List<Element> selectedElementList;

    /** 선택한 객체 */
    private EObject firstSelectedEObject;

    /** 변환 규칙 */
    private Map<String, String> unitPrefixMap;

    /**
     * methodPrefixMap
     */
    private Map<String, String> methodPrefixMap;

    /**
     * componentPrefixMap
     */
    private Map<String, String> componentPrefixMap;

    /**
     * 
     */
    public ApplyKoreanPrefixAction() {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        Package pack = (Package) firstSelectedEObject;

        ApplyKoreanPrefixCommand cmd = new ApplyKoreanPrefixCommand(pack);

        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(cmd);
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void selectionChanged(IAction action, ISelection selection) {
        if (!(selection instanceof IStructuredSelection)) {
            return;
        }

        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        Object pickedUpObject;
        ITreeNode node;

        selectedElementList = new ArrayList<Element>();

        for (Iterator<Object> structureSelectionIterator = structureSelection.iterator();

        structureSelectionIterator.hasNext();) {
            pickedUpObject = structureSelectionIterator.next();

            if (pickedUpObject instanceof ITreeNode) {
                node = (ITreeNode) pickedUpObject;

                selectedElementList.add((Element) node.getEObject());

                if (selectedElementList.size() == 1) {
                    firstSelectedEObject = (Element) node.getEObject();
                }
            }
        }

    }

    public class ApplyKoreanPrefixCommand extends Command {

        private Package pack;

        public ApplyKoreanPrefixCommand(Package pack) {
            this.pack = pack;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            unitPrefixMap = new HashMap<String, String>();
            methodPrefixMap = new HashMap<String, String>();
            componentPrefixMap = new HashMap<String, String>();

            unitPrefixMap.put("P", "PU");
            unitPrefixMap.put("F", "FU");
            unitPrefixMap.put("D", "DU");

            methodPrefixMap.put("p", "PM");
            methodPrefixMap.put("f", "FM");
            methodPrefixMap.put("d", "DM");

            componentPrefixMap.put("B", "BC");
            componentPrefixMap.put("S", "SC");

            EObjectCondition condition = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getClass_());
            SELECT statement = new SELECT(new FROM(pack), new WHERE(condition));
            IQueryResult classList = statement.execute();

            condition = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getOperation());
            statement = new SELECT(new FROM(pack), new WHERE(condition));
            IQueryResult operationList = statement.execute();

            int successNum = 0;

            for (EObject obj : classList) {
                Class clazz = (Class) obj;
                if (changePrefix(clazz, unitPrefixMap)) {
                    successNum++;
                }
            }

            for (EObject obj : operationList) {
                Operation operation = (Operation) obj;
                if (changePrefix(operation, methodPrefixMap)) {
                    successNum++;
                }
            }

            // 한글명 prefix 적용 완료
            MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_APPLY_KOREAN_PREFIX,
                successNum + UMLMessage.MESSAGE_APPLY_KOREAN_PREFIX_SUCCESS);
        }

        // Prefix 변경 - target의 prefix가 변경되면 true를 반환
        private boolean changePrefix(NamedElement target, Map<String, String> prefixMap) {
            Stereotype stereotype = null;
            String korPropertyName = UICoreConstant.EMPTY_STRING;
            String korName = UICoreConstant.EMPTY_STRING;
            String engName = UICoreConstant.EMPTY_STRING;
            String profilePrefix = MDDCoreConstant.MDA_PROFILES_NAMES[1]
                + MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING;

            // 유닛 class, 메소드 operation, 컴포넌트 class
            // BizUnit, service, Component
            if (target instanceof Class) {
                stereotype = ((Class) target).getAppliedStereotype(profilePrefix
                    + MDDCoreConstant.BIZUNIT_METADATA_BIZUNIT_STEREOTYPE_NAME);

                if (stereotype == null) {
                    return false;
                }

                korPropertyName = MDDCoreConstant.BIZUNIT_NAME_KEY_IN_BIZUNIT_STEREOTYPE;
                korName = (String) target.getValue(stereotype, korPropertyName);
                engName = (String) target.getValue(stereotype,
                    MDDCoreConstant.BIZUNIT_ENG_NAME_KEY_IN_BIZUNIT_STEREOTYPE);

            } else if (target instanceof Operation) {
                stereotype = ((Operation) target).getAppliedStereotype(profilePrefix
                    + MDDCoreConstant.BIZUNIT_METADATA_METHOD_STEREOTYPE_NAME);

                if (stereotype == null) {
                    return false;
                }
                
                korPropertyName = MDDCoreConstant.METHOD_NAME_KEY_IN_METHOD_STEREOTYPE;
                korName = (String) target.getValue(stereotype, korPropertyName);
                engName = (String) target.getValue(stereotype, MDDCoreConstant.METHOD_ENG_NAME_KEY_IN_METHOD_STEREOTYPE);
            } 

            if (korName == null || engName == null) {
                return false;
            }

            for (String initial : prefixMap.keySet()) {
                if (engName.startsWith(initial)) {
                    String prefix = prefixMap.get(initial);
                    String fullPrefix = UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT + prefix
                        + UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT;
                    
                    if (korName.startsWith(fullPrefix)) {
                        // 1. 이미 [PM]로 시작하면 그냥 놔둠
                    } else if (korName.startsWith(prefix)) {
                        // 2. PM로 시작하면 PM를 [PM]로 변경
                        korName = korName.replaceFirst(prefix, fullPrefix);
                    } else {
                        // 3. 아니면 앞에 [PM]를 붙임
                        korName = fullPrefix + korName;
                    }
                    
                    target.setValue(stereotype, korPropertyName, korName);
                    return true;
                }
            }

            return false;
        }
    }
}
