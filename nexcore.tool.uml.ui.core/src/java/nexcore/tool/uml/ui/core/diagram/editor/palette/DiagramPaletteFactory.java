/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.editor.palette;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.model.INote;
import nexcore.tool.uml.ui.core.diagram.model.IText;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreationFactory;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreatorOfActivityDiagram;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreatorOfClassDiagram;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreatorOfComponentDiagram;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreatorOfUseCaseDiagram;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.uml2.uml.AggregationKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor.palette</li>
 * <li>설 명 : DiagramPaletteFactory</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DiagramPaletteFactory {

    /** imageRegistry */
    public static ImageRegistry imageRegistry = UiCorePlugin.getDefault().getImageRegistry();

    /** INVALID_TYPE */
    @SuppressWarnings("unused")
    private final static int INVALID_TYPE = -1;

    /**
     * createPalette
     * 
     * @return PaletteRoot
     */
    public static PaletteRoot createPalette(DiagramType diagramType) {

        PaletteRoot palette = new PaletteRoot();
        palette = createShapesDrawer(diagramType);
        palette.add(0, createToolsGroup(diagramType));
        // palette.add(createShapesDrawer(diagramType));

        return palette;
    }

    /**
     * createToolsGroup
     * 
     * @param palette
     * @return PaletteEntry
     */
    private static PaletteEntry createToolsGroup(DiagramType diagramType) {
        PaletteToolbar toolbar = new PaletteToolbar("Tools");

        // Add a selection tool to the group
        ToolEntry tool = new PanningSelectionToolEntry() {
            @Override
            public Tool createTool() {
                return new NewSelectionTool();
            }
        };

        toolbar.add(tool);
        // palette.setDefaultEntry(tool);

        // Add a marquee tool to the group
        toolbar.add(new MarqueeToolEntry());

        tool = new CreationToolEntry("Note",
            "Create a note",
            new ModelCreationFactory(INote.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_NOTE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_NOTE)));
        toolbar.add(tool);

        tool = new CreationToolEntry("Text",
            "Create a text",
            new ModelCreationFactory(IText.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_TEXT)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_TEXT)));
        toolbar.add(tool);

        tool = new ConnectionCreationToolEntry("Note Attachment",
            "Create a note attachment",
            new ModelCreationFactory(Attachement.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ATTACHMENT)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ATTACHMENT)));
        tool.setToolClass(ConnectionCreationToolWithAdditionalInformation.class);
        toolbar.add(tool);

        return toolbar;
    }

    /**
     * createNoteDrawer
     * 
     * @return PaletteEntry
     */
    @SuppressWarnings("unused")
    private static PaletteEntry createNoteDrawer() {
        PaletteDrawer componentsDrawer = new PaletteDrawer("Note");

        CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry("Note",
            "Create a note",
            INote.class,
            new ModelCreationFactory(INote.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)));
        componentsDrawer.add(component);

        component = new CombinedTemplateCreationEntry("Text",
            "Create a text",
            IText.class,
            new ModelCreationFactory(IText.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)));
        componentsDrawer.add(component);

        component = new CombinedTemplateCreationEntry("Note Attachement",
            "Create a note attachment",
            Attachement.class,
            new ModelCreationFactory(Attachement.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ASSOCIATION_NONE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ASSOCIATION_NONE)));
        componentsDrawer.add(component);

        return componentsDrawer;
    }

    /**
     * createShapesDrawer
     * 
     * @return PaletteContainer
     */
    private static PaletteRoot createShapesDrawer(DiagramType diagramType) {

        switch (diagramType.getValue()) {
            case DiagramType.CLASS_DIAGRAM_VALUE:
                return classDiagramPaletteDrawer();

            case DiagramType.USE_CASE_DIAGRAM_VALUE:
                return useCaseDiagramPaletteDrawer();

            case DiagramType.ACTIVITY_DIAGRAM_VALUE:
                return activityDiagramPaletteDrawer();

            case DiagramType.COMPONENT_DIAGRAM_VALUE:
                return componentDiagramPaletteDrawer();

            default:
                return null;
        }
    }

    /**
     * 
     * classDiagramPaletteDrawer
     * 
     * @return PaletteRoot
     */
    private static PaletteRoot classDiagramPaletteDrawer() {

        PaletteRoot palette = new PaletteRoot();

        PaletteDrawer componentsDrawer = new PaletteDrawer("Class Diagram");
        componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_PACKAGE,
            UMLMessage.MESSAGE_PALETTE_PACKAGE,
            org.eclipse.uml2.uml.Package.class,
            org.eclipse.uml2.uml.Package.class,
            IConstantImageRegistry.ICONNAME_PACKAGE,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_CLASS,
            UMLMessage.MESSAGE_PALETTE_CLASS,
            org.eclipse.uml2.uml.Class.class,
            org.eclipse.uml2.uml.Class.class,
            IConstantImageRegistry.ICONNAME_CLASS,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        componentsDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_ACTOR,
            UMLMessage.MESSAGE_PALETTE_ACTOR,
            org.eclipse.uml2.uml.Actor.class,
            org.eclipse.uml2.uml.Actor.class,
            IConstantImageRegistry.ICONNAME_ACTOR,
            UMLHelper.DirectedType.NONE));
        componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_COLLABORATION,
            UMLMessage.MESSAGE_PALETTE_COLLABORATION,
            org.eclipse.uml2.uml.Collaboration.class,
            org.eclipse.uml2.uml.Collaboration.class,
            IConstantImageRegistry.ICONNAME_COLLABORATION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        PaletteStack classPaletteStack = new PaletteStack("class", "", null);
        classPaletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_ARTIFACT,
            UMLMessage.MESSAGE_PALETTE_ARTIFACT,
            org.eclipse.uml2.uml.Artifact.class,
            org.eclipse.uml2.uml.Artifact.class,
            IConstantImageRegistry.ICONNAME_ARTIFACT,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        classPaletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_DATATYPE,
            UMLMessage.MESSAGE_PALETTE_DATATYPE,
            org.eclipse.uml2.uml.DataType.class,
            org.eclipse.uml2.uml.DataType.class,
            IConstantImageRegistry.ICONNAME_DATATYPE,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        classPaletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_ENUMERATION,
            UMLMessage.MESSAGE_PALETTE_ENUMERATION,
            org.eclipse.uml2.uml.Enumeration.class,
            org.eclipse.uml2.uml.Enumeration.class,
            IConstantImageRegistry.ICONNAME_ENUMERATION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_INTERFACE,
            UMLMessage.MESSAGE_PALETTE_INTERFACE,
            org.eclipse.uml2.uml.Interface.class,
            org.eclipse.uml2.uml.Interface.class,
            IConstantImageRegistry.ICONNAME_INTERFACE,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));

        componentsDrawer.add(classPaletteStack);
        PaletteStack dependency = new PaletteStack("class", "", null);
        dependency.add(createToolEntryForClassDiagram(UMLMessage.UML_DEPENDENCY,
            UMLMessage.MESSAGE_PALETTE_DEPENDENCY,
            null,
            org.eclipse.uml2.uml.Dependency.class,
            IConstantImageRegistry.ICONNAME_DEPENDENCY,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        dependency.add(createToolEntryForClassDiagram(UMLMessage.UML_ABSTRACTION,
            UMLMessage.MESSAGE_PALETTE_ABSTRACTION,
            null,
            org.eclipse.uml2.uml.Abstraction.class,
            IConstantImageRegistry.ICONNAME_ABSTRACTION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        dependency.add(createToolEntryForClassDiagram(UMLMessage.UML_USAGE,
            UMLMessage.MESSAGE_PALETTE_USAGE,
            null,
            org.eclipse.uml2.uml.Usage.class,
            IConstantImageRegistry.ICONNAME_USAGE,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));
        componentsDrawer.add(dependency);

        componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_GENERALIZATION,
            UMLMessage.MESSAGE_PALETTE_GENERALIZATION,
            null,
            org.eclipse.uml2.uml.Generalization.class,
            IConstantImageRegistry.ICONNAME_GENERALIZATION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.NONE));

        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_REALIZATION,
        // UMLMessage.MESSAGE_PALETTE_REALIZATION,
        // null,
        // org.eclipse.uml2.uml.Realization.class,
        // IConstantImageRegistry.ICONNAME_REALIZATION,
        // AggregationKind.NONE_LITERAL,
        // UMLHelper.DirectedType.NONE));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_INTERFACEREALIZATION,
            UMLMessage.MESSAGE_PALETTE_INTERFACEREALIZATION,
            null,
            org.eclipse.uml2.uml.InterfaceRealization.class,
            IConstantImageRegistry.ICONNAME_INTERFACEREALIZATION));
        // ////////////////////////////////////////////////////////////////////////////////////////////////////
        PaletteStack paletteStack = new PaletteStack("Association", "", null);
        // ////////////////////////////////////////////////////////////////////////////////////////////////////

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_BINARY_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_BINARY_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_BINARY_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_BINARY_ASSOCIATION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.BINARY));

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_DIRECTED_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_DIRECTED_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_DIRECTED_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_DIRECTED_ASSOCIATION,
            AggregationKind.NONE_LITERAL,
            UMLHelper.DirectedType.DIRECTED));

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_SHARED_BINARY_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_SHARED_BINARY_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_SHARED_BINARY_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_SHARED_BINARY_ASSOCIATION,
            AggregationKind.SHARED_LITERAL,
            UMLHelper.DirectedType.BINARY));

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_SHARED_DIRECTED_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_SHARED_DIRECTED_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_SHARED_DIRECTED_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_SHARED_DIRECTED_ASSOCIATIONASSOCIATION,
            AggregationKind.SHARED_LITERAL,
            UMLHelper.DirectedType.DIRECTED));

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_COMPOSITE_BINARY_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_COMPOSITE_BINARY_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_COMPOSITE_BINARY_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_COMPOSITE_BINARY_ASSOCIATION,
            AggregationKind.COMPOSITE_LITERAL,
            UMLHelper.DirectedType.BINARY));

        paletteStack.add(createToolEntryForClassDiagram(UMLMessage.UML_COMPOSITE_DIRECTED_ASSOCIATION,
        // componentsDrawer.add(createToolEntryForClassDiagram(UMLMessage.UML_COMPOSITE_DIRECTED_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_COMPOSITE_DIRECTED_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_COMPOSITE_DIRECTED_ASSOCIATION,
            AggregationKind.COMPOSITE_LITERAL,
            UMLHelper.DirectedType.DIRECTED));

        componentsDrawer.add(paletteStack);
        palette.add(componentsDrawer);
        return palette;
    }

    /**
     * 
     * ComponentDiagramPaletteDrawer
     * 
     * @return PaletteRoot
     */
    private static PaletteRoot componentDiagramPaletteDrawer() {

        PaletteRoot palette = new PaletteRoot();

        PaletteDrawer componentsDrawer = new PaletteDrawer("Component");

        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_COMPONENT,
            UMLMessage.MESSAGE_PALETTE_COMPONENT,
            org.eclipse.uml2.uml.Component.class,
            org.eclipse.uml2.uml.Component.class,
            IConstantImageRegistry.ICONNAME_COMPONENT));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_PACKAGE,
            UMLMessage.MESSAGE_PALETTE_PACKAGE,
            org.eclipse.uml2.uml.Package.class,
            org.eclipse.uml2.uml.Package.class,
            IConstantImageRegistry.ICONNAME_PACKAGE));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_INTERFACE,
            UMLMessage.MESSAGE_PALETTE_INTERFACE,
            org.eclipse.uml2.uml.Interface.class,
            org.eclipse.uml2.uml.Interface.class,
            IConstantImageRegistry.ICONNAME_INTERFACE));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_ARTIFACT,
            UMLMessage.MESSAGE_PALETTE_ARTIFACT,
            org.eclipse.uml2.uml.Artifact.class,
            org.eclipse.uml2.uml.Artifact.class,
            IConstantImageRegistry.ICONNAME_ARTIFACT));
        // componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_USAGE,
        // UMLMessage.MESSAGE_PALETTE_USAGE,
        // org.eclipse.uml2.uml.Usage.class,
        // org.eclipse.uml2.uml.Usage.class,
        // IConstantImageRegistry.ICONNAME_USAGE));
        // componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_COMPONENTREALIZATION,
        // UMLMessage.MESSAGE_PALETTE_COMPONENTREALIZATION, null,
        // org.eclipse.uml2.uml.ComponentRealization.class,
        // IConstantImageRegistry.ICONNAME_COMPONENTREALIZATION));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_INTERFACEREALIZATION,
            UMLMessage.MESSAGE_PALETTE_INTERFACEREALIZATION,
            null,
            org.eclipse.uml2.uml.InterfaceRealization.class,
            IConstantImageRegistry.ICONNAME_INTERFACEREALIZATION));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_DEPENDENCY,
            UMLMessage.MESSAGE_PALETTE_DEPENDENCY,
            null,
            org.eclipse.uml2.uml.Dependency.class,
            IConstantImageRegistry.ICONNAME_DEPENDENCY));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_USAGE,
            UMLMessage.MESSAGE_PALETTE_USAGE,
            null,
            org.eclipse.uml2.uml.Usage.class,
            IConstantImageRegistry.ICONNAME_USAGE));
        componentsDrawer.add(createToolEntryForComponentDiagram(UMLMessage.UML_ABSTRACTION,
            UMLMessage.MESSAGE_PALETTE_ABSTRACTION,
            null,
            org.eclipse.uml2.uml.Abstraction.class,
            IConstantImageRegistry.ICONNAME_ABSTRACTION));

        palette.add(componentsDrawer);
        return palette;
    }

    /**
     * 
     * ActivityDiagramPaletteDrawer
     * 
     * @return PaletteRoot
     */
    private static PaletteRoot activityDiagramPaletteDrawer() {

        PaletteRoot palette = new PaletteRoot();

        PaletteDrawer activityDiagramDrawer = new PaletteDrawer("Activity Diagram");

         activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_ACTIVITYPARTITION,
         UMLMessage.MESSAGE_PALETTE_ACTIVITYPARTITION,
         org.eclipse.uml2.uml.ActivityPartition.class,
         org.eclipse.uml2.uml.ActivityPartition.class,
         IConstantImageRegistry.ICONNAME_ACTIVITYPARTITION));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_INITIALNODE,
            UMLMessage.MESSAGE_PALETTE_INITIALNODE,
            org.eclipse.uml2.uml.InitialNode.class,
            org.eclipse.uml2.uml.InitialNode.class,
            IConstantImageRegistry.ICONNAME_INITIALNODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_ACTIVITYFINALNODE,
            UMLMessage.MESSAGE_PALETTE_ACTIVITYFINALNODE,
            org.eclipse.uml2.uml.ActivityFinalNode.class,
            org.eclipse.uml2.uml.ActivityFinalNode.class,
            IConstantImageRegistry.ICONNAME_ACTIVITYFINALNODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_OPAQUEACTION,
            UMLMessage.MESSAGE_PALETTE_OPAQUEACTION,
            org.eclipse.uml2.uml.OpaqueAction.class,
            org.eclipse.uml2.uml.OpaqueAction.class,
            IConstantImageRegistry.ICONNAME_OPAQUEACTION));

        // PaletteStack controlNodePaletteStack = new
        // PaletteStack("ControlNode", "", null);
        // controlNodePaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_DECISIONNODE,
        // UMLMessage.MESSAGE_PALETTE_DECISIONNODE,
        // org.eclipse.uml2.uml.DecisionNode.class,
        // org.eclipse.uml2.uml.DecisionNode.class,
        // IConstantImageRegistry.ICONNAME_DECISIONNODE));
        // controlNodePaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_MERGENODE,
        // UMLMessage.MESSAGE_PALETTE_MERGENODE,
        // org.eclipse.uml2.uml.MergeNode.class,
        // org.eclipse.uml2.uml.MergeNode.class,
        // IConstantImageRegistry.ICONNAME_MERGENODE));
        // controlNodePaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_FORKNODE,
        // UMLMessage.MESSAGE_PALETTE_FORKNODE,
        // org.eclipse.uml2.uml.ForkNode.class,
        // org.eclipse.uml2.uml.ForkNode.class,
        // IConstantImageRegistry.ICONNAME_FORKNODE));
        // controlNodePaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_JOINNODE,
        // UMLMessage.MESSAGE_PALETTE_JOINNODE,
        // org.eclipse.uml2.uml.JoinNode.class,
        // org.eclipse.uml2.uml.JoinNode.class,
        // IConstantImageRegistry.ICONNAME_JOINNODE));
        // activityDiagramDrawer.add(controlNodePaletteStack);
        //
        // PaletteStack objectPaletteStack = new PaletteStack("Object", "",
        // null);
        // objectPaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_DATASTORENODE,
        // UMLMessage.MESSAGE_PALETTE_DATASTORENODE,
        // org.eclipse.uml2.uml.DataStoreNode.class,
        // org.eclipse.uml2.uml.DataStoreNode.class,
        // IConstantImageRegistry.ICONNAME_DATASTORENODE));
        // objectPaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_CENTRALBUFFERNODE,
        // UMLMessage.MESSAGE_PALETTE_CENTRALBUFFERNODE,
        // org.eclipse.uml2.uml.CentralBufferNode.class,
        // org.eclipse.uml2.uml.CentralBufferNode.class,
        // IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE));
        // activityDiagramDrawer.add(objectPaletteStack);
        //        
        // PaletteStack flowPaletteStack = new PaletteStack("Flow", "", null);
        // flowPaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_CONTROLFLOW,
        // UMLMessage.MESSAGE_PALETTE_CONTROLFLOW,
        // null,
        // org.eclipse.uml2.uml.ControlFlow.class,
        // IConstantImageRegistry.ICONNAME_CONTROLFLOW));
        // flowPaletteStack.add(createToolEntryForActivityDiagram(UMLMessage.UML_OBJECTFLOW,
        // UMLMessage.MESSAGE_PALETTE_OBJECTFLOW,
        // null,
        // org.eclipse.uml2.uml.ObjectFlow.class,
        // IConstantImageRegistry.ICONNAME_OBJECTFLOW));
        // activityDiagramDrawer.add(flowPaletteStack);

        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_DECISIONNODE,
            UMLMessage.MESSAGE_PALETTE_DECISIONNODE,
            org.eclipse.uml2.uml.DecisionNode.class,
            org.eclipse.uml2.uml.DecisionNode.class,
            IConstantImageRegistry.ICONNAME_DECISIONNODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_MERGENODE,
            UMLMessage.MESSAGE_PALETTE_MERGENODE,
            org.eclipse.uml2.uml.MergeNode.class,
            org.eclipse.uml2.uml.MergeNode.class,
            IConstantImageRegistry.ICONNAME_MERGENODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_FORKNODE,
            UMLMessage.MESSAGE_PALETTE_FORKNODE,
            org.eclipse.uml2.uml.ForkNode.class,
            org.eclipse.uml2.uml.ForkNode.class,
            IConstantImageRegistry.ICONNAME_FORKNODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_JOINNODE,
            UMLMessage.MESSAGE_PALETTE_JOINNODE,
            org.eclipse.uml2.uml.JoinNode.class,
            org.eclipse.uml2.uml.JoinNode.class,
            IConstantImageRegistry.ICONNAME_JOINNODE));

        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_DATASTORENODE,
            UMLMessage.MESSAGE_PALETTE_DATASTORENODE,
            org.eclipse.uml2.uml.DataStoreNode.class,
            org.eclipse.uml2.uml.DataStoreNode.class,
            IConstantImageRegistry.ICONNAME_DATASTORENODE));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_CENTRALBUFFERNODE,
            UMLMessage.MESSAGE_PALETTE_CENTRALBUFFERNODE,
            org.eclipse.uml2.uml.CentralBufferNode.class,
            org.eclipse.uml2.uml.CentralBufferNode.class,
            IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE));

        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_CONTROLFLOW,
            UMLMessage.MESSAGE_PALETTE_CONTROLFLOW,
            null,
            org.eclipse.uml2.uml.ControlFlow.class,
            IConstantImageRegistry.ICONNAME_CONTROLFLOW));
        activityDiagramDrawer.add(createToolEntryForActivityDiagram(UMLMessage.UML_OBJECTFLOW,
            UMLMessage.MESSAGE_PALETTE_OBJECTFLOW,
            null,
            org.eclipse.uml2.uml.ObjectFlow.class,
            IConstantImageRegistry.ICONNAME_OBJECTFLOW));

        palette.add(activityDiagramDrawer);

        return palette;
    }

    /**
     * 
     * UseCaseDiagramPaletteDrawer
     * 
     * @return PaletteRoot
     */
    private static PaletteRoot useCaseDiagramPaletteDrawer() {

        PaletteRoot palette = new PaletteRoot();

        PaletteDrawer useCaseDrawer = new PaletteDrawer("UseCase");
        useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_PACKAGE,
            UMLMessage.MESSAGE_PALETTE_PACKAGE,
            org.eclipse.uml2.uml.Package.class,
            org.eclipse.uml2.uml.Package.class,
            IConstantImageRegistry.ICONNAME_PACKAGE,
            UMLHelper.DirectedType.NONE));
        useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_USECASE,
            UMLMessage.MESSAGE_PALETTE_USECASE,
            org.eclipse.uml2.uml.UseCase.class,
            org.eclipse.uml2.uml.UseCase.class,
            IConstantImageRegistry.ICONNAME_USECASE,
            UMLHelper.DirectedType.NONE));
        useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_ACTOR,
            UMLMessage.MESSAGE_PALETTE_ACTOR,
            org.eclipse.uml2.uml.Actor.class,
            org.eclipse.uml2.uml.Actor.class,
            IConstantImageRegistry.ICONNAME_ACTOR,
            UMLHelper.DirectedType.NONE));
        // useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_ELEMENTIMPORT,
        // UMLMessage.MESSAGE_PALETTE_ACTOR,
        // org.eclipse.uml2.uml.Actor.class,org.eclipse.uml2.uml.Actor.class,
        // IConstantImageRegistry.ICONNAME_ACTOR, UMLHelper.DirectedType.NONE));
        // useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_PACKAGEIMPORT,
        // UMLMessage.MESSAGE_PALETTE_PACKAGEIMPORT,
        // org.eclipse.uml2.uml.PackageImport.class,org.eclipse.uml2.uml.PackageImport.class,
        // IConstantImageRegistry.ICONNAME_PACKAGEIMPORT,
        // UMLHelper.DirectedType.NONE));
        // useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_PACKAGEMERGE,
        // UMLMessage.MESSAGE_PALETTE_PACKAGEMERGE,
        // org.eclipse.uml2.uml.PackageMerge.class,org.eclipse.uml2.uml.PackageMerge.class,
        // IConstantImageRegistry.ICONNAME_PACKAGEMERGE,
        // UMLHelper.DirectedType.NONE));
        //
        // PaletteDrawer useCaseRelationshipsDrawer = new
        // PaletteDrawer("UseCase Relationships");

        PaletteStack dependencyPaletteStack = new PaletteStack("Dependency", "", null);

        dependencyPaletteStack.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_DEPENDENCY,
            UMLMessage.MESSAGE_PALETTE_DEPENDENCY,
            null,
            org.eclipse.uml2.uml.Dependency.class,
            IConstantImageRegistry.ICONNAME_DEPENDENCY,
            UMLHelper.DirectedType.NONE));
        dependencyPaletteStack.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_INCLUDE,
            UMLMessage.MESSAGE_PALETTE_INCLUDE,
            null,
            org.eclipse.uml2.uml.Include.class,
            IConstantImageRegistry.ICONNAME_INCLUDE,
            UMLHelper.DirectedType.NONE));
        dependencyPaletteStack.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_EXTEND,
            UMLMessage.MESSAGE_PALETTE_EXTEND,
            null,
            org.eclipse.uml2.uml.Extend.class,
            IConstantImageRegistry.ICONNAME_EXTEND,
            UMLHelper.DirectedType.NONE));

        useCaseDrawer.add(dependencyPaletteStack);

        useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_GENERALIZATION,
            UMLMessage.MESSAGE_PALETTE_GENERALIZATION,
            null,
            org.eclipse.uml2.uml.Generalization.class,
            IConstantImageRegistry.ICONNAME_GENERALIZATION,
            UMLHelper.DirectedType.NONE));
        useCaseDrawer.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_REALIZATION,
            UMLMessage.MESSAGE_PALETTE_REALIZATION,
            null,
            org.eclipse.uml2.uml.Realization.class,
            IConstantImageRegistry.ICONNAME_REALIZATION,
            UMLHelper.DirectedType.NONE));
        //  
        // PaletteDrawer useCaseAssociationsDrawer = new
        // PaletteDrawer("UseCase Associations");

        PaletteStack associationPaletteStack = new PaletteStack("Association", "", null);

        associationPaletteStack.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_BINARY_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_BINARY_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_BINARY_ASSOCIATION,
            UMLHelper.DirectedType.BINARY));
        associationPaletteStack.add(createToolEntryForUseCaseDiagram(UMLMessage.UML_DIRECTED_ASSOCIATION,
            UMLMessage.MESSAGE_PALETTE_DIRECTED_ASSOCIATION,
            null,
            org.eclipse.uml2.uml.Association.class,
            IConstantImageRegistry.ICONNAME_DIRECTED_ASSOCIATION,
            UMLHelper.DirectedType.DIRECTED));

        useCaseDrawer.add(associationPaletteStack);

        palette.add(useCaseDrawer);
        // palette.add(useCaseRelationshipsDrawer);
        // palette.add(useCaseAssociationsDrawer);

        return palette;
    }

    /**
     * 
     * 
     * @param nameKey
     * @param messageKey
     * @param objectTemlateType
     * @param objectType
     * @param imageName
     * @return CreationToolEntry
     */
    @SuppressWarnings("unchecked")
    private static CreationToolEntry createToolEntryForActivityDiagram(String nameKey, String messageKey,
                                                                       Class objectTemlateType, Class objectType,
                                                                       String imageName) {
        ImageDescriptor largeImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getLargeIcon(imageName));
        ImageDescriptor smallImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(imageName));
        String name = UMLMessage.getMessage(nameKey);
        String message = UMLMessage.getMessage(messageKey);
        CreationToolEntry toolEntry;
        ModelCreatorOfActivityDiagram creationFactory;

        creationFactory = new ModelCreatorOfActivityDiagram(objectType);

        if (null == objectTemlateType) {
            toolEntry = new ConnectionCreationToolEntry(name, message, creationFactory, smallImage, largeImage);
            toolEntry.setToolClass(ConnectionCreationToolWithAdditionalInformation.class);
        } else {
            toolEntry = new CombinedTemplateCreationEntry(name,
                message,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
        }
        return toolEntry;
    }

    /**
     * 
     * 
     * @param nameKey
     * @param messageKey
     * @param objectTemlateType
     * @param objectType
     * @param imageName
     * @return CreationToolEntry
     */
    @SuppressWarnings("unchecked")
    private static CreationToolEntry createToolEntryForComponentDiagram(String nameKey, String messageKey,
                                                                        Class objectTemlateType, Class objectType,
                                                                        String imageName) {
        ImageDescriptor largeImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getLargeIcon(imageName));
        ImageDescriptor smallImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(imageName));
        String name = UMLMessage.getMessage(nameKey);
        String message = UMLMessage.getMessage(messageKey);
        CreationToolEntry toolEntry;
        ModelCreatorOfComponentDiagram creationFactory;

        creationFactory = new ModelCreatorOfComponentDiagram(objectType);

        if (null == objectTemlateType) {
            toolEntry = new ConnectionCreationToolEntry(name, message, creationFactory, smallImage, largeImage);
            toolEntry.setToolClass(ConnectionCreationToolWithAdditionalInformation.class);
        } else {
            toolEntry = new CombinedTemplateCreationEntry(name,
                message,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
        }
        return toolEntry;
    }

    /**
     * 
     * 
     * @param nameKey
     * @param messageKey
     * @param objectTemlateType
     * @param objectType
     * @param imageName
     * @param directedType
     * @return CreationToolEntry
     */
    @SuppressWarnings("unchecked")
    private static CreationToolEntry createToolEntryForUseCaseDiagram(String nameKey, String messageKey,
                                                                      Class objectTemlateType, Class objectType,
                                                                      String imageName,
                                                                      UMLHelper.DirectedType directedType) {
        ImageDescriptor largeImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getLargeIcon(imageName));
        ImageDescriptor smallImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(imageName));
        String name = UMLMessage.getMessage(nameKey);
        String message = UMLMessage.getMessage(messageKey);
        CreationToolEntry toolEntry;
        ModelCreatorOfUseCaseDiagram creationFactory;
        if (directedType.equals(UMLHelper.DirectedType.NONE)) {
            creationFactory = new ModelCreatorOfUseCaseDiagram(objectType);
        } else {
            creationFactory = new ModelCreatorOfUseCaseDiagram(objectType, directedType);
        }

        if (null == objectTemlateType) {
            toolEntry = new ConnectionCreationToolEntry(name, message, creationFactory, smallImage, largeImage);
            toolEntry.setToolClass(ConnectionCreationToolForUsecaseDiagramRelationship.class);
        } else {
            toolEntry = new CombinedTemplateCreationEntry(name,
                message,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
        }
        return toolEntry;
    }

    /**
     * 
     * 
     * @param nameKey
     * @param messageKey
     * @param objectTemlateType
     * @param objectType
     * @param imageName
     * @param aggregationKind
     * @param directedType
     * @return CreationToolEntry
     */
    @SuppressWarnings("unchecked")
    private static CreationToolEntry createToolEntryForClassDiagram(String nameKey, String messageKey,
                                                                    Class objectTemlateType, Class objectType,
                                                                    String imageName, AggregationKind aggregationKind,
                                                                    UMLHelper.DirectedType directedType) {
        ImageDescriptor largeImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getLargeIcon(imageName));
        ImageDescriptor smallImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(imageName));
        String name = UMLMessage.getMessage(nameKey);
        String message = UMLMessage.getMessage(messageKey);
        CreationToolEntry toolEntry;
        ModelCreatorOfClassDiagram creationFactory;

        if (!aggregationKind.equals(AggregationKind.NONE_LITERAL) && directedType.equals(UMLHelper.DirectedType.NONE)) {
            creationFactory = new ModelCreatorOfClassDiagram(objectType, aggregationKind);
        } else if (aggregationKind.equals(AggregationKind.NONE_LITERAL)
            && !directedType.equals(UMLHelper.DirectedType.NONE)) {
            creationFactory = new ModelCreatorOfClassDiagram(objectType, directedType);
        } else if (!aggregationKind.equals(AggregationKind.NONE_LITERAL)
            && !directedType.equals(UMLHelper.DirectedType.NONE)) {
            creationFactory = new ModelCreatorOfClassDiagram(objectType, directedType, aggregationKind);
        } else {
            creationFactory = new ModelCreatorOfClassDiagram(objectType);
        }

        if (null == objectTemlateType) {
            toolEntry = new ConnectionCreationToolEntry(name, message, creationFactory, smallImage, largeImage);
            toolEntry.setToolClass(ConnectionCreationToolWithAdditionalInformation.class);
        } else {
            toolEntry = new CombinedTemplateCreationEntry(name,
                message,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
        }
        return toolEntry;
    }
}
