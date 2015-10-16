/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer;

import java.util.Iterator;
import java.util.TreeSet;

import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer</li>
 * <li>설 명 : ExplorerElementFilter</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ExplorerElementFilter extends ViewerFilter {

    /** UML 요소 전체의 리스트 */
    private TreeSet<String> allList;

    /** 필터링할 UML 요소의 리스트 */
    private TreeSet<String> filteredList;

    /** Preference 설정 정보가 저장된 곳 */
    private IPreferenceStore store;

    /**
     * constructor.
     */
    public ExplorerElementFilter() {
        allList = new TreeSet<String>();
        filteredList = new TreeSet<String>();
        store = ProjectExplorerPlugin.getDefault().getPreferenceStore();
        init();
    }

    /**
     * 
     * void
     */
    public void init() {
        allList.clear();
        filteredList.clear();
        createElementList();
        String fStr = getPreferenceString();
        String[] prefFilters = fStr.split(UICoreConstant.PROJECT_CONSTANTS__SEMICOLON);
        for (int i = 1; i < prefFilters.length; i++) {
            if (allList.contains(prefFilters[i]))
                setFilter(prefFilters[i]);
        }
    }

    /**
     * 
     * 
     * @return TreeSet<String>
     */
    public TreeSet<String> getElementList() {
        return allList;
    }

    /**
     * 
     * 
     * @return TreeSet<String>
     */
    public TreeSet<String> getFilteredList() {
        return filteredList;
    }

    /**
     * 
     * 
     * @param string
     *            void
     */
    public void setFilter(String string) {
        filteredList.add(string);
        allList.remove(string);
    }

    /**
     * 
     * 
     * @param string
     *            void
     */
    public void unsetFilter(String string) {
        allList.add(string);
        filteredList.remove(string);
    }

    /**
     * 
     * 
     * @return String
     */
    private String getPreferenceString() {
        String str = store.getString(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FILTER);
        if (str.length() == 0) {
            setDefault();
            return saveFilter();
        }
        return str;
    }

    /**
     * 
     * 
     * @return String
     */
    public String saveFilter() {
        TreeSet<String> list = getFilteredList();
        int size = list.size();
        String filterStr = UICoreConstant.PROJECT_CONSTANTS__SEMICOLON;
        if (size != 0) {

            for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
                filterStr += iterator.next() + UICoreConstant.PROJECT_CONSTANTS__SEMICOLON;
            }

            filterStr = filterStr.substring(0, filterStr.length() - 1);
        }
        store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FILTER, filterStr);
        ProjectExplorerPlugin.getDefault().savePluginPreferences();

        return filterStr;
    }

    /**
     * @return boolean 'false'이면 필터링되어 나타나지 않는다.
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        EObject parent, child;
        // 시퀀스다이어그램용 속성 필드 필터됨

		// if (element instanceof IFile) {
		// IFile file = (IFile) element;
		// if (!LicenseManager.checkLicense()) {
		// if (file.getParent() instanceof IFolder) {
		// return false;
		// }
		// }
		// }

        if( element instanceof ClosedTreeNode) {
            return true;
        } else if (element instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) element;
            
            child = node.getEObject();
            if( child == null){
                return false;
            }
            parent = child.eContainer();
            
            if (child instanceof Property) {
                if (parent instanceof Collaboration) {
                    return false;
                }
                if (null != ((Property) child).getAssociation()) {
                    return false;
                }
            }

            String name = node.getEObject().eClass().getName();
            return !filteredList.contains(name);

        } else if (element instanceof IFile) {
            IFile file = (IFile) element;

            // 2011-12-28 nspark
            // 네이처 추가/제거에 따른 필터링 처리
            try {
                if (!file.getProject().hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                    if (UICoreConstant.DESIGNER_FILE_EXTENSION.equals(file.getFileExtension())
                        || UICoreConstant.DEVELOPER_FILE_EXTENSION.equals(file.getFileExtension())
                        || UICoreConstant.DEVELOPER_REVERSE_FILE_EXTENSION.equals(file.getFileExtension())) {
                        return false;
                    }
                }
            } catch (CoreException e) {
                return false;
            }

            if (UMLLoginController.getInstance().getLoginState() == UMLNexcoreLoginEvent.LOG_IN) {
                if (UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension())
                    || UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())) {
                    if (file.getParent() instanceof IFolder) {
                        return false;
                    }
                }
            } else {
                if (UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension())
                    || UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())
                    || UICoreConstant.DESIGNER_FILE_EXTENSION.equals(file.getFileExtension())
                    || UICoreConstant.DEVELOPER_FILE_EXTENSION.equals(file.getFileExtension())
                    || UICoreConstant.DEVELOPER_REVERSE_FILE_EXTENSION.equals(file.getFileExtension())) {
                    if (file.getParent() instanceof IFolder) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * 필터링할 UML 요소들을 리스트에 지정한다.
     */
    public void setDefault() {
        createElementList();
        filteredList.clear();
        filteredList.add("Abstraction"); //$NON-NLS-1$
        filteredList.add("Association"); //$NON-NLS-1$
        filteredList.add("BehaviorExecutionSpecification"); //$NON-NLS-1$
        filteredList.add("CombinedFragment"); //$NON-NLS-1$
        filteredList.add("Comment"); //$NON-NLS-1$
        filteredList.add("Connector"); //$NON-NLS-1$
        filteredList.add("ControlFlow"); //$NON-NLS-1$
        filteredList.add("Dependency"); //$NON-NLS-1$
        filteredList.add("ExecutionEvent"); //$NON-NLS-1$
        filteredList.add("ExecutionOccurrenceSpecification"); //$NON-NLS-1$
        filteredList.add("Extend"); //$NON-NLS-1$
        filteredList.add("Generalization"); //$NON-NLS-1$
        filteredList.add("Include"); //$NON-NLS-1$
        filteredList.add("InteractionOperand"); //$NON-NLS-1$
        filteredList.add("InterfaceRealization"); //$NON-NLS-1$
        filteredList.add("Lifeline"); //$NON-NLS-1$
        filteredList.add("LiteralBoolean"); //$NON-NLS-1$
        filteredList.add("LiteralInteger"); //$NON-NLS-1$
        filteredList.add("LiteralNull"); //$NON-NLS-1$
        filteredList.add("LiteralString"); //$NON-NLS-1$
        filteredList.add("LiteralUnlimitedNatural"); //$NON-NLS-1$
        filteredList.add("Message"); //$NON-NLS-1$
        filteredList.add("MessageOccurrenceSpecification"); //$NON-NLS-1$
        filteredList.add("ObjectFlow"); //$NON-NLS-1$
        filteredList.add("Realization"); //$NON-NLS-1$
        filteredList.add("ReceiveOperationEvent"); //$NON-NLS-1$
        filteredList.add("SendOperationEvent"); //$NON-NLS-1$
        filteredList.add("Usage"); //$NON-NLS-1$
        
        // activity 다이어그램의 노드 
        filteredList.add("JoinNode"); //$NON-NLS-1$
        filteredList.add("MergeNode"); //$NON-NLS-1$
        filteredList.add("ForkNode"); //$NON-NLS-1$
        filteredList.add("DecisionNode"); //$NON-NLS-1$
        filteredList.add("ActivityFinalNode"); //$NON-NLS-1$
        filteredList.add("InitialNode"); //$NON-NLS-1$
        filteredList.add("ActivityPartition"); //$NON-NLS-1$
        filteredList.add("CentralBufferNode"); //$NON-NLS-1$
        filteredList.add("DataStoreNode"); //$NON-NLS-1$
        filteredList.add("OpaqueAction"); //$NON-NLS-1$
    }

    /**
     * 전체 UML Element List 구성. void
     */
    private void createElementList() {

        allList.add("Abstraction"); //$NON-NLS-1$
        allList.add("AcceptCallAction"); //$NON-NLS-1$
        allList.add("AcceptEventAction"); //$NON-NLS-1$
        allList.add("ActionExecutionSpecification"); //$NON-NLS-1$
        allList.add("ActionInputPin"); //$NON-NLS-1$
        allList.add("Activity"); //$NON-NLS-1$
        allList.add("ActivityFinalNode"); //$NON-NLS-1$
        allList.add("ActivityParameterNode"); //$NON-NLS-1$
        allList.add("ActivityPartition"); //$NON-NLS-1$
        allList.add("Actor"); //$NON-NLS-1$
        allList.add("AddStructuralFeatureValueAction"); //$NON-NLS-1$
        allList.add("AddVariableValueAction"); //$NON-NLS-1$
        allList.add("AnyReceiveEvent"); //$NON-NLS-1$
        allList.add("Artifact"); //$NON-NLS-1$
        allList.add("Association"); //$NON-NLS-1$
        allList.add("AssociationClass"); //$NON-NLS-1$
        allList.add("BehaviorExecutionSpecification"); //$NON-NLS-1$
        allList.add("BroadcastSignalAction"); //$NON-NLS-1$
        allList.add("CallBehaviorAction"); //$NON-NLS-1$
        allList.add("CallEvent"); //$NON-NLS-1$
        allList.add("CallOperationAction"); //$NON-NLS-1$
        allList.add("CentralBufferNode"); //$NON-NLS-1$
        allList.add("ChangeEvent"); //$NON-NLS-1$
        allList.add("Class"); //$NON-NLS-1$
        allList.add("ClassifierTemplateParameter"); //$NON-NLS-1$
        allList.add("Clause"); //$NON-NLS-1$
        allList.add("ClearAssociationAction"); //$NON-NLS-1$
        allList.add("ClearStructuralFeatureAction"); //$NON-NLS-1$
        allList.add("ClearVariableAction"); //$NON-NLS-1$
        allList.add("Collaboration"); //$NON-NLS-1$
        allList.add("CollaborationUse"); //$NON-NLS-1$
        allList.add("CombinedFragment"); //$NON-NLS-1$
        allList.add("Comment"); //$NON-NLS-1$
        allList.add("CommunicationPath"); //$NON-NLS-1$
        allList.add("Component"); //$NON-NLS-1$
        allList.add("ComponentRealization"); //$NON-NLS-1$
        allList.add("ConditionalNode"); //$NON-NLS-1$
        allList.add("ConnectableElementTemplateParameter"); //$NON-NLS-1$
        allList.add("ConnectionPointReference"); //$NON-NLS-1$
        allList.add("Connector"); //$NON-NLS-1$
        allList.add("ConnectorEnd"); //$NON-NLS-1$
        allList.add("ConsiderIgnoreFragment"); //$NON-NLS-1$
        allList.add("Constraint"); //$NON-NLS-1$
        allList.add("Continuation"); //$NON-NLS-1$
        allList.add("ControlFlow"); //$NON-NLS-1$
        allList.add("CreateLinkAction"); //$NON-NLS-1$
        allList.add("CreateLinkObjectAction"); //$NON-NLS-1$
        allList.add("CreateObjectAction"); //$NON-NLS-1$
        allList.add("CreationEvent"); //$NON-NLS-1$
        allList.add("DataStoreNode"); //$NON-NLS-1$
        allList.add("DataType"); //$NON-NLS-1$
        allList.add("DecisionNode"); //$NON-NLS-1$
        allList.add("Dependency"); //$NON-NLS-1$
        allList.add("Deployment"); //$NON-NLS-1$
        allList.add("DeploymentSpecification"); //$NON-NLS-1$
        allList.add("DestroyLinkAction"); //$NON-NLS-1$
        allList.add("DestroyObjectAction"); //$NON-NLS-1$
        allList.add("DestructionEvent"); //$NON-NLS-1$
        allList.add("Device"); //$NON-NLS-1$
        allList.add("Duration"); //$NON-NLS-1$
        allList.add("DurationConstraint"); //$NON-NLS-1$
        allList.add("DurationInterval"); //$NON-NLS-1$
        allList.add("DurationObservation"); //$NON-NLS-1$
        allList.add("ElementImport"); //$NON-NLS-1$
        allList.add("Enumeration"); //$NON-NLS-1$
        allList.add("EnumerationLiteral"); //$NON-NLS-1$
        allList.add("ExceptionHandler"); //$NON-NLS-1$
        allList.add("ExecutionEnvironment"); //$NON-NLS-1$
        allList.add("ExecutionEvent"); //$NON-NLS-1$
        allList.add("ExecutionOccurrenceSpecification"); //$NON-NLS-1$
        allList.add("ExpansionNode"); //$NON-NLS-1$
        allList.add("ExpansionRegion"); //$NON-NLS-1$
        allList.add("Expression"); //$NON-NLS-1$
        allList.add("Extend"); //$NON-NLS-1$
        allList.add("Extension"); //$NON-NLS-1$
        allList.add("ExtensionEnd"); //$NON-NLS-1$
        allList.add("ExtensionPoint"); //$NON-NLS-1$
        allList.add("FinalState"); //$NON-NLS-1$
        allList.add("FlowFinalNode"); //$NON-NLS-1$
        allList.add("ForkNode"); //$NON-NLS-1$
        allList.add("FunctionBehavior"); //$NON-NLS-1$
        allList.add("Gate"); //$NON-NLS-1$
        allList.add("Generalization"); //$NON-NLS-1$
        allList.add("GeneralizationSet"); //$NON-NLS-1$
        allList.add("GeneralOrdering"); //$NON-NLS-1$
        allList.add("Image"); //$NON-NLS-1$
        allList.add("Include"); //$NON-NLS-1$
        allList.add("InformationFlow"); //$NON-NLS-1$
        allList.add("InformationItem"); //$NON-NLS-1$
        allList.add("InitialNode"); //$NON-NLS-1$
        allList.add("InputPin"); //$NON-NLS-1$
        allList.add("InstanceSpecification"); //$NON-NLS-1$
        allList.add("InstanceValue"); //$NON-NLS-1$
        allList.add("Interaction"); //$NON-NLS-1$
        allList.add("InteractionConstraint"); //$NON-NLS-1$
        allList.add("InteractionOperand"); //$NON-NLS-1$
        allList.add("InteractionUse"); //$NON-NLS-1$
        allList.add("Interface"); //$NON-NLS-1$
        allList.add("InterfaceRealization"); //$NON-NLS-1$
        allList.add("InterruptibleActivityRegion"); //$NON-NLS-1$
        allList.add("Interval"); //$NON-NLS-1$
        allList.add("IntervalConstraint"); //$NON-NLS-1$
        allList.add("JoinNode"); //$NON-NLS-1$
        allList.add("Lifeline"); //$NON-NLS-1$
        allList.add("LinkEndCreationData"); //$NON-NLS-1$
        allList.add("LinkEndData"); //$NON-NLS-1$
        allList.add("LinkEndDestructionData"); //$NON-NLS-1$
        allList.add("LiteralBoolean"); //$NON-NLS-1$
        allList.add("LiteralInteger"); //$NON-NLS-1$
        allList.add("LiteralNull"); //$NON-NLS-1$
        allList.add("LiteralString"); //$NON-NLS-1$
        allList.add("LiteralUnlimitedNatural"); //$NON-NLS-1$
        allList.add("LoopNode"); //$NON-NLS-1$
        allList.add("Manifestation"); //$NON-NLS-1$
        allList.add("MergeNode"); //$NON-NLS-1$
        allList.add("Message"); //$NON-NLS-1$
        allList.add("MessageOccurrenceSpecification"); //$NON-NLS-1$
        allList.add("Model"); //$NON-NLS-1$
        allList.add("Node"); //$NON-NLS-1$
        allList.add("ObjectFlow"); //$NON-NLS-1$
        allList.add("OccurrenceSpecification"); //$NON-NLS-1$
        allList.add("OpaqueAction"); //$NON-NLS-1$
        allList.add("OpaqueBehavior"); //$NON-NLS-1$
        allList.add("OpaqueExpression"); //$NON-NLS-1$
        allList.add("Operation"); //$NON-NLS-1$
        allList.add("OperationTemplateParameter"); //$NON-NLS-1$
        allList.add("OutputPin"); //$NON-NLS-1$
        allList.add("Package"); //$NON-NLS-1$
        allList.add("PackageImport"); //$NON-NLS-1$
        allList.add("PackageMerge"); //$NON-NLS-1$
        allList.add("Parameter"); //$NON-NLS-1$
        allList.add("ParameterSet"); //$NON-NLS-1$
        allList.add("PartDecomposition"); //$NON-NLS-1$
        allList.add("Pin"); //$NON-NLS-1$
        allList.add("Port"); //$NON-NLS-1$
        allList.add("PrimitiveType"); //$NON-NLS-1$
        allList.add("Profile"); //$NON-NLS-1$
        allList.add("ProfileApplication"); //$NON-NLS-1$
        allList.add("Property"); //$NON-NLS-1$
        allList.add("ProtocolConformance"); //$NON-NLS-1$
        allList.add("ProtocolStateMachine"); //$NON-NLS-1$
        allList.add("ProtocolTransition"); //$NON-NLS-1$
        allList.add("Pseudostate"); //$NON-NLS-1$
        allList.add("QualifierValue"); //$NON-NLS-1$
        allList.add("RaiseExceptionAction"); //$NON-NLS-1$
        allList.add("ReadExtentAction"); //$NON-NLS-1$
        allList.add("ReadIsClassifiedObjectAction"); //$NON-NLS-1$
        allList.add("ReadLinkAction"); //$NON-NLS-1$
        allList.add("ReadLinkObjectEndAction"); //$NON-NLS-1$
        allList.add("ReadLinkObjectEndQualifierAction"); //$NON-NLS-1$
        allList.add("ReadSelfAction"); //$NON-NLS-1$
        allList.add("ReadStructuralFeatureAction"); //$NON-NLS-1$
        allList.add("ReadVariableAction"); //$NON-NLS-1$
        allList.add("Realization"); //$NON-NLS-1$
        allList.add("ReceiveOperationEvent"); //$NON-NLS-1$
        allList.add("ReceiveSignalEvent"); //$NON-NLS-1$
        allList.add("Reception"); //$NON-NLS-1$
        allList.add("ReclassifyObjectAction"); //$NON-NLS-1$
        allList.add("RedefinableTemplateSignature"); //$NON-NLS-1$
        allList.add("ReduceAction"); //$NON-NLS-1$
        allList.add("Region"); //$NON-NLS-1$
        allList.add("RemoveStructuralFeatureValueAction"); //$NON-NLS-1$
        allList.add("RemoveVariableValueAction"); //$NON-NLS-1$
        allList.add("ReplyAction"); //$NON-NLS-1$
        allList.add("SendObjectAction"); //$NON-NLS-1$
        allList.add("SendOperationEvent"); //$NON-NLS-1$
        allList.add("SendSignalAction"); //$NON-NLS-1$
        allList.add("SendSignalEvent"); //$NON-NLS-1$
        allList.add("SequenceNode"); //$NON-NLS-1$
        allList.add("Signal"); //$NON-NLS-1$
        allList.add("SignalEvent"); //$NON-NLS-1$
        allList.add("Slot"); //$NON-NLS-1$
        allList.add("StartClassifierBehaviorAction"); //$NON-NLS-1$
        allList.add("State"); //$NON-NLS-1$
        allList.add("StateInvariant"); //$NON-NLS-1$
        allList.add("StateMachine"); //$NON-NLS-1$
        allList.add("Stereotype"); //$NON-NLS-1$
        allList.add("StringExpression"); //$NON-NLS-1$
        allList.add("StructuredActivityNode"); //$NON-NLS-1$
        allList.add("Substitution"); //$NON-NLS-1$
        allList.add("TemplateBinding"); //$NON-NLS-1$
        allList.add("TemplateParameter"); //$NON-NLS-1$
        allList.add("TemplateParameterSubstitution"); //$NON-NLS-1$
        allList.add("TemplateSignature"); //$NON-NLS-1$
        allList.add("TestIdentityAction"); //$NON-NLS-1$
        allList.add("TimeConstraint"); //$NON-NLS-1$
        allList.add("TimeEvent"); //$NON-NLS-1$
        allList.add("TimeExpression"); //$NON-NLS-1$
        allList.add("TimeInterval"); //$NON-NLS-1$
        allList.add("TimeObservation"); //$NON-NLS-1$
        allList.add("Transition"); //$NON-NLS-1$
        allList.add("Trigger"); //$NON-NLS-1$
        allList.add("UnmarshallAction"); //$NON-NLS-1$
        allList.add("Usage"); //$NON-NLS-1$
        allList.add("UseCase"); //$NON-NLS-1$
        allList.add("ValuePin"); //$NON-NLS-1$
        allList.add("ValueSpecificationAction"); //$NON-NLS-1$
        allList.add("Variable"); //$NON-NLS-1$

    }
}

