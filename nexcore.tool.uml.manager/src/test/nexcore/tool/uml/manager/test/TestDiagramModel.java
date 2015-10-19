/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */


package nexcore.tool.uml.manager.test;

import junit.framework.TestCase;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.test</li>
 * <li>설 명 : TestDiagramModel</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class TestDiagramModel extends TestCase {

    /**
     * 라이프 라인 생성 테스트 void
     */
    public void testCreateLifeLine() {
        UMLDomainTest umlDomain = new UMLDomainTest();

        Resource resource = umlDomain.createUMLModelRoot(URI.createURI("file:/c:/umlModel.umx")); //$NON-NLS-1$
        Model model = DomainUtil.getUMLModelRoot(resource);
        Diagram diagram = UMLHelper.createSequenceDiagram(model, "Sequence Diagram");
        // Diagram diagram = umlDomain.createDiagram((Element)
        // resource.getContents().get(0),
        // DiagramType.SEQUENCE_DIAGRAM,
        //            "Sequence Diagram"); //$NON-NLS-1$
        assertNotNull(diagram);

        // Lifeline umlLifeLine = UMLHelper.createLifeline();
        // Collaboration collaboration = (org.eclipse.uml2.uml.Collaboration)
        // EcoreUtil.getObjectByType(resource.getContents(),
        // UMLPackage.Literals.COLLABORATION);
        //
        // Interaction interaction = (org.eclipse.uml2.uml.Interaction)
        // EcoreUtil.getObjectByType(resource.getContents(),
        // UMLPackage.Literals.INTERACTION);
        // interaction.createLifeline("LifeLine");
        // ((Interaction)collaboration.getOwnedBehavior("상호작용")).getCovereds().add(umlLifeLine);

        // collaboration.getOwnedAttributes().add((Property) umlLifeLine);
        LifeLineNode viewLifeLine = UMLDiagramFactory.eINSTANCE.createLifeLineNode();
        // viewLifeLine.setUmlModel(umlLifeLine);
        Line viewLine = UMLDiagramFactory.eINSTANCE.createLine();
        viewLifeLine.setLine(viewLine);

        diagram.getNodeList().add(viewLifeLine);

        umlDomain.save((EObject) diagram);
    }
}
