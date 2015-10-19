/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.editor.palette;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.model.INote;
import nexcore.tool.uml.ui.core.diagram.model.IText;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreationFactory;
import nexcore.tool.uml.ui.core.diagram.model.ModelCreatorOfSequenceDiagram;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.editor.SequenceSelectionTool;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.MessageSort;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor.palette</li>
 * <li>설 명 : SequenceDiagramPaletteFactory</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramPaletteFactory {

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
        PaletteToolbar toolbar = new PaletteToolbar(UMLMessage.LABEL_TOOLS);

        // Add a selection tool to the group
        ToolEntry tool = new PanningSelectionToolEntry() {
            @Override
            public Tool createTool() {
                return new SequenceSelectionTool();
            }
        };
        toolbar.add(tool);
        // palette.setDefaultEntry(tool);

        // Add a marquee tool to the group
        tool = new MarqueeToolEntry();
        tool.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
            MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED);
        toolbar.add(tool);

        tool = new CreationToolEntry(UMLMessage.LABEL_NOTE,
            UMLMessage.LABEL_CREATE_A_NOTE,
            new ModelCreationFactory(INote.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_NOTE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_NOTE)));
        toolbar.add(tool);

        tool = new CreationToolEntry(UMLMessage.LABEL_TEXT,
            UMLMessage.LABEL_CREATE_A_TEXT,
            new ModelCreationFactory(IText.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_TEXT)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_TEXT)));
        toolbar.add(tool);

        tool = new ConnectionCreationToolEntry(UMLMessage.LABEL_NOTE_ATTACHEMENT,
            UMLMessage.LABEL_CREATE_A_NOTE_ATTACHMENT,
            new ModelCreationFactory(Attachement.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ATTACHMENT)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ATTACHMENT)));
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
        PaletteDrawer componentsDrawer = new PaletteDrawer(UMLMessage.LABEL_NOTE);

        CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry("Note",
            UMLMessage.LABEL_CREATE_A_NOTE,
            INote.class,
            new ModelCreationFactory(INote.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)));
        componentsDrawer.add(component);

        component = new CombinedTemplateCreationEntry(UMLMessage.LABEL_TEXT,
            UMLMessage.LABEL_CREATE_A_TEXT,
            IText.class,
            new ModelCreationFactory(IText.class),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)),
            ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE)));
        componentsDrawer.add(component);

        component = new CombinedTemplateCreationEntry(UMLMessage.LABEL_NOTE_ATTACHEMENT,
            UMLMessage.LABEL_CREATE_A_NOTE_ATTACHMENT,
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
            case DiagramType.SEQUENCE_DIAGRAM_VALUE:
                return sequenceDiagramPaletteDrawer();
            default:
                return null;
        }
    }

    /**
     * 
     * SequenceDiagramPaletteDrawer
     * 
     * @return PaletteRoot
     */
    private static PaletteRoot sequenceDiagramPaletteDrawer() {

        PaletteRoot palette = new PaletteRoot();

        PaletteDrawer componentsDrawer = new PaletteDrawer(UMLMessage.LABEL_SEQUENCE_DIAGRAM);

        componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_LIFELINE,
            UMLMessage.MESSAGE_PALETTE_LIFELINE,
            org.eclipse.uml2.uml.Lifeline.class,
            org.eclipse.uml2.uml.Lifeline.class,
            IConstantImageRegistry.ICONNAME_LIFELINE,
            null,
            null,
            null));
        // componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_INTERACTIONUSE,
        // UMLMessage.MESSAGE_PALETTE_INTERACTIONUSE,
        // org.eclipse.uml2.uml.InteractionUse.class,
        // org.eclipse.uml2.uml.InteractionUse.class,
        // IConstantImageRegistry.ICONNAME_INTERACTIONUSE,
        // null,
        // null));
        // componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_DESTRUCTIONEVENT,
        // UMLMessage.MESSAGE_PALETTE_DESTRUCTIONEVENT,
        // org.eclipse.uml2.uml.DestructionEvent.class,
        // org.eclipse.uml2.uml.DestructionEvent.class,
        // IConstantImageRegistry.ICONNAME_DESTRUCTIONEVENT,
        // null,
        // null));
        //        

        componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_SYNCH_CALL,
            UMLMessage.MESSAGE_PALETTE_SYNCH_CALL,
            null,
            org.eclipse.uml2.uml.Message.class,
            IConstantImageRegistry.ICONNAME_MESSAGE_SYNCHCALL,
            MessageSort.SYNCH_CALL_LITERAL,
            null,
            null));
        componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_ASYNCH_CALL,
            UMLMessage.MESSAGE_PALETTE_ASYNCH_CALL,
            null,
            org.eclipse.uml2.uml.Message.class,
            IConstantImageRegistry.ICONNAME_MESSAGE_ASYNCHCALL,
            MessageSort.ASYNCH_CALL_LITERAL,
            null,
            null));
        // componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_CREATE_MESSAGE,
        // UMLMessage.MESSAGE_PALETTE_CREATE_MESSAGE,
        // null,
        // org.eclipse.uml2.uml.Message.class,
        // IConstantImageRegistry.ICONNAME_CREATE_MESSAGE,
        // MessageSort.CREATE_MESSAGE_LITERAL,
        // null));
        // componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_DELETE_MESSAGE,
        // UMLMessage.MESSAGE_PALETTE_DELETE_MESSAGE,
        // null,
        // org.eclipse.uml2.uml.Message.class,
        // IConstantImageRegistry.ICONNAME_DELETE_MESSAGE,
        // MessageSort.DELETE_MESSAGE_LITERAL,
        // null));
        componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_REPLY,
            UMLMessage.MESSAGE_PALETTE_REPLY,
            null,
            org.eclipse.uml2.uml.Message.class,
            IConstantImageRegistry.ICONNAME_MESSAGE_REPLY,
            MessageSort.REPLY_LITERAL,
            null,
            null));

         componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_OPTION_IF,
         UMLMessage.MESSAGE_PALETTE_OPTION_IF,
         org.eclipse.uml2.uml.CombinedFragment.class,
         org.eclipse.uml2.uml.CombinedFragment.class,
         IConstantImageRegistry.ICONNAME_INTERACTION_OPT, null,
         InteractionOperatorKind.OPT_LITERAL, UICoreConstant.OPTION_IF));
                   
         componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_ALTERNATIVE_IFELSE,
         UMLMessage.MESSAGE_PALETTE_ALTERNATIVE_IFELSE,
         org.eclipse.uml2.uml.CombinedFragment.class,
         org.eclipse.uml2.uml.CombinedFragment.class,
         IConstantImageRegistry.ICONNAME_INTERACTION_ALT, null,
         InteractionOperatorKind.ALT_LITERAL,
         UICoreConstant.ALTERNATIVE_IFELSE));
                   
         componentsDrawer.add(createToolEntryForSequenceDiagram(UMLMessage.UML_LOOP_WHILE,
         UMLMessage.MESSAGE_PALETTE_LOOT_WHILE,
         org.eclipse.uml2.uml.CombinedFragment.class,
         org.eclipse.uml2.uml.CombinedFragment.class,
         IConstantImageRegistry.ICONNAME_INTERACTION_LOOP, null,
         InteractionOperatorKind.LOOP_LITERAL, UICoreConstant.LOOP_WHILE));

        palette.add(componentsDrawer);
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
     * @param messageSort
     * @param interactionOperatorKind
     * @return CreationToolEntry
     */
    @SuppressWarnings("unchecked")
    private static CreationToolEntry createToolEntryForSequenceDiagram(String nameKey, String messageKey,
                                                                       Class objectTemlateType, Class objectType,
                                                                       String imageName, MessageSort messageSort,
                                                                       InteractionOperatorKind interactionOperatorKind,
                                                                       String operandType) {
        ImageDescriptor largeImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getLargeIcon(imageName));
        ImageDescriptor smallImage = ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(imageName));
        CreationToolEntry toolEntry;
        ModelCreatorOfSequenceDiagram creationFactory;

        if (messageSort == null) {
            if (interactionOperatorKind == null) {
                creationFactory = new ModelCreatorOfSequenceDiagram(objectType);
            } else {
                creationFactory = new ModelCreatorOfSequenceDiagram(objectType, interactionOperatorKind, operandType);
            }
        } else {
            creationFactory = new ModelCreatorOfSequenceDiagram(objectType, messageSort);
        }

        if (null == objectTemlateType) {
            toolEntry = new ConnectionCreationToolEntry(nameKey, messageKey, creationFactory, smallImage, largeImage);
            toolEntry.setToolClass(MessageCreationToolForSequenceDiagramRelationship.class);
        } else if (org.eclipse.uml2.uml.Lifeline.class == objectTemlateType) {
            toolEntry = new CombinedTemplateCreationEntry(nameKey,
                messageKey,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
            toolEntry.setToolClass(LifeLineCreationToolWithAdditionalInformation.class);
        } else {
            toolEntry = new CombinedTemplateCreationEntry(nameKey,
                messageKey,
                objectTemlateType,
                creationFactory,
                smallImage,
                largeImage);
        }
        return toolEntry;
    }
}
