/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.core.UMLDebug;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.SharedImages;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core</li>
 * <li>설 명 : UiCorePlugin</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class UiCorePlugin extends AbstractUIPlugin {

    // The plug-in ID
    /**
     * PLUGIN_ID
     */
    public static final String PLUGIN_ID = "nexcore.tool.uml.ui.core"; //$NON-NLS-1$

    // The shared instance
    /**
     * plugin
     */
    private static UiCorePlugin plugin;

    /** colorRegistry */
    private HashMap<String, Color> colorRegistry = new HashMap<String, Color>();

    /** fontRegistry **/
    private HashMap<String, Font> fontRegistry = new HashMap<String, Font>();

    /**
     * Adapter Factory
     */
    private ComposedAdapterFactory adapterFactory;

    /** adapterFactoryLabelProvider */
    public AdapterFactoryLabelProvider adapterFactoryLabelProvider = null;

    /**
     * The constructor
     */
    public UiCorePlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
     * )
     */
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        setColorRegistry();
        setFontRegistry();
        adapterFactory = createAdapterFactory();
        if (adapterFactoryLabelProvider == null) {
            adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        }

        // resource change listener add
        ResourceManager.getInstance();
        
        try {
            String value = Platform.getDebugOption("nexcore.tool.uml/debug");
            if (value != null && "true".equals(value)) {
                UMLDebug.DEBUG = true;
            }
        } catch (Exception e) {
        }
    }

    /**
     * getAdapterFactoryLabelProvider
     *  
     * @return AdapterFactoryLabelProvider
     */
    public AdapterFactoryLabelProvider getAdapterFactoryLabelProvider() {
        if (adapterFactoryLabelProvider == null) {
            adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        }
        return adapterFactoryLabelProvider;
    }

    /**
     * getImageForUMLElement
     *  
     * @param eobject
     * @return Image
     */
    public Image getImageForUMLElement(EObject eobject) {

        if (eobject instanceof NamedElement) {
            NamedElement namedElement = (NamedElement) eobject;
            EList<Stereotype> stereos = namedElement.getAppliedStereotypes();
            org.eclipse.uml2.uml.Image image = null;
            for (int i = 0; i < stereos.size(); i++)
                if (stereos.get(i).getIcons().size() != 0)
                    image = stereos.get(i).getIcons().get(0);

            if (image != null && image.getLocation() != null)
                return ImageDescriptor.createFromURL(getBundle().getEntry(image.getLocation())).createImage();

            // } else if( eobject instanceof EAnnotation ) {
            // String source = ((EAnnotation) eobject).getSource();
            // if( ProjectConstants.DIAGRAM.equals(source) ) {
            // return PlatformUI.getWorkbench().getSharedImages()
            // .getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OPEN_MARKER);
            // }

        } else if (eobject instanceof Diagram) {
            Diagram diagram = (Diagram) eobject;
            DiagramType type = diagram.getType();

            if (DiagramType.CLASS_DIAGRAM.equals(type)) {
                return getImageRegistry().get(IConstantImageRegistry.LOCATION_CLASS_DIAGRAM);

            } else if (DiagramType.ACTIVITY_DIAGRAM.equals(type)) {
                return getImageRegistry().get(IConstantImageRegistry.LOCATION_ACTIVITY_DIAGRAM);

            } else if (DiagramType.COMPONENT_DIAGRAM.equals(type)) {
                return getImageRegistry().get(IConstantImageRegistry.LOCATION_COMPONENT_DIAGRAM);

            } else if (DiagramType.USE_CASE_DIAGRAM.equals(type)) {
                return getImageRegistry().get(IConstantImageRegistry.LOCATION_USECASE_DIAGRAM);

            } else if (DiagramType.SEQUENCE_DIAGRAM.equals(type)) {
                return getImageRegistry().get(IConstantImageRegistry.LOCATION_SEQUENCE_DIAGRAM);
            }

            return PlatformUI.getWorkbench().getSharedImages().getImage(SharedImages.IMG_OBJS_INFO_TSK);

        }
        return adapterFactoryLabelProvider.getImage(eobject);
    }

    /**
     * 
     * 
     * @return ComposedAdapterFactory
     */
    @SuppressWarnings("unchecked")
    protected ComposedAdapterFactory createAdapterFactory() {
        List factories = new ArrayList();
        fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    /**
     * 
     * 
     * @param factories
     *            void
     */
    @SuppressWarnings("unchecked")
    protected void fillItemProviderFactories(List factories) {
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
        factories.add(new UMLItemProviderAdapterFactory());
        // factories.add(new RelativeItemProviderAdapterFactory());
    }

    /**
     * 
     * 
     * @return AdapterFactory
     */
    public AdapterFactory getItemProvidersAdapterFactory() {
        return adapterFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
     * )
     */
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        ResourceManager.getInstance().distroy();
        
        adapterFactory.dispose();
        adapterFactory = null;
        adapterFactoryLabelProvider.dispose();
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static UiCorePlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * 
     * 
     * @return Display
     */
    public static Display getDisplay() {
        return PlatformUI.getWorkbench().getDisplay();
    }

    /**
     * getProgressService
     *  
     * @return IProgressService
     */
    public IProgressService getProgressService() {
        return getWorkbench().getProgressService();
    }
    
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
     */
    protected void initializeImageRegistry(ImageRegistry reg) {

        // // image location
        // IPath packagePath = new
        // Path(IConstantImageRegistry.LOCATION_PACKAGE);
        // IPath attributePath = new
        // Path(IConstantImageRegistry.LOCATION_ATTRIBUTE);
        // IPath classPath = new Path(IConstantImageRegistry.LOCATION_CLASS);
        // IPath methodPath = new Path(IConstantImageRegistry.LOCATION_METHOD);
        // // IPath associationPath = new
        // Path(IConstantImageRegistry.LOCATION_ASSOCIATION);
        // IPath noneAssociationPath = new
        // Path(IConstantImageRegistry.LOCATION_NONE_ASSOCIATION);
        // IPath sharedAssociationPath = new
        // Path(IConstantImageRegistry.LOCATION_SHARED_ASSOCIATION);
        // IPath compositeAssociationPath = new
        // Path(IConstantImageRegistry.LOCATION_COMPOSITE_ASSOCIATION);
        // IPath generalizationPath = new
        // Path(IConstantImageRegistry.LOCATION_GENERALIZATION);
        // IPath realizationPath = new
        // Path(IConstantImageRegistry.LOCATION_REALIZATION);
        //
        // // image 를 registry 에 저장
        // imageRegister(reg, packagePath, IConstantImageRegistry.NAME_PACKAGE);
        // imageRegister(reg, attributePath,
        // IConstantImageRegistry.NAME_ATTRIBUTE);
        // imageRegister(reg, classPath, IConstantImageRegistry.NAME_CLASS);
        // imageRegister(reg, methodPath, IConstantImageRegistry.NAME_METHOD);
        // // imageRegister(reg, associationPath,
        // IConstantImageRegistry.NAME_ASSOCIATION);
        // imageRegister(reg, noneAssociationPath,
        // IConstantImageRegistry.NAME_NONE_ASSOCIATION);
        // imageRegister(reg, sharedAssociationPath,
        // IConstantImageRegistry.NAME_SHARED_ASSOCIATION);
        // imageRegister(reg, compositeAssociationPath,
        // IConstantImageRegistry.NAME_COMPOSITE_ASSOCIATION);
        // imageRegister(reg, generalizationPath,
        // IConstantImageRegistry.NAME_GENERALIZATION);
        // imageRegister(reg, realizationPath,
        // IConstantImageRegistry.NAME_REALIZATION);

        imageRegister(reg,
            new Path(IConstantImageRegistry.LOCATION_ACTIVITY_DIAGRAM),
            IConstantImageRegistry.LOCATION_ACTIVITY_DIAGRAM);
        imageRegister(reg,
            new Path(IConstantImageRegistry.LOCATION_CLASS_DIAGRAM),
            IConstantImageRegistry.LOCATION_CLASS_DIAGRAM);
        imageRegister(reg,
            new Path(IConstantImageRegistry.LOCATION_COMPONENT_DIAGRAM),
            IConstantImageRegistry.LOCATION_COMPONENT_DIAGRAM);
        imageRegister(reg,
            new Path(IConstantImageRegistry.LOCATION_SEQUENCE_DIAGRAM),
            IConstantImageRegistry.LOCATION_SEQUENCE_DIAGRAM);
        imageRegister(reg,
            new Path(IConstantImageRegistry.LOCATION_USECASE_DIAGRAM),
            IConstantImageRegistry.LOCATION_USECASE_DIAGRAM);

        imageRegister(reg,
            new Path(IConstantImageRegistry.ICONNAME_PROJECT_WIZARD),
            IConstantImageRegistry.ICONNAME_PROJECT_WIZARD);
        imageRegister(reg,
            new Path(IConstantImageRegistry.ICONNAME_MODEL_WIZARD),
            IConstantImageRegistry.ICONNAME_MODEL_WIZARD);

        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ABSTRACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACCEPTCALLACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACCEPTEVENTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIONEXECUTIONSPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIONINPUTPIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIVITY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIVITYFINALNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIVITYPARAMETERNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTIVITYPARTITION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTOR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADDSTRUCTURALFEATUREVALUEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADDVARIABLEVALUEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ANYRECEIVEEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ARTIFACT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASSOCIATIONCLASS);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASSOCIATION_COMPOSITE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASSOCIATION_NONE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASSOCIATION_SHARED);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_BEHAVIOREXECUTIONSPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_BROADCASTSIGNALACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CALLBEHAVIORACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CALLEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CALLOPERATIONACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CHANGEEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLASS);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLASSIFIERTEMPLATEPARAMETER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLAUSE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLEARASSOCIATIONACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLEARSTRUCTURALFEATUREACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLEARVARIABLEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COLLABORATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COLLABORATIONUSE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMBINEDFRAGMENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMMENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMMUNICATIONPATH);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMPONENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMPONENTREALIZATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONDITIONALNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTABLEELEMENTTEMPLATEPARAMETER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTIONPOINTREFERENCE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTOR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTOREND);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTOR_ASSEMBLY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONNECTOR_DELEGATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONSIDERIGNOREFRAGMENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONSTRAINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONTINUATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CONTROLFLOW);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATELINKACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATELINKOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATEOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATIONEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DATASTORENODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DATATYPE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DECISIONNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DEPENDENCY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DEPLOYMENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DEPLOYMENTSPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DESTROYLINKACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DESTROYOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DESTRUCTIONEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DEVICE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DURATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DURATIONCONSTRAINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DURATIONINTERVAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DURATIONOBSERVATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ELEMENTIMPORT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ENUMERATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ENUMERATIONLITERAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXCEPTIONHANDLER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXECUTIONENVIRONMENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXECUTIONEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXECUTIONOCCURRENCESPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXPANSIONNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXPANSIONREGION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXPRESSION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXTEND);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXTENSION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXTENSIONEND);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_EXTENSIONPOINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_FINALSTATE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_FLOWFINALNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_FORKNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_FUNCTIONBEHAVIOR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_GATE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_GENERALIZATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_GENERALIZATIONSET);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_GENERALORDERING);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_IMAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INCLUDE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INFORMATIONFLOW);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INFORMATIONITEM);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INITIALNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INPUTPIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INSTANCESPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INSTANCEVALUE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTIONCONSTRAINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTIONOPERAND);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTIONUSE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERFACE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERFACEREALIZATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERRUPTIBLEACTIVITYREGION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERVAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERVALCONSTRAINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_JOINNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LIFELINE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LINKENDCREATIONDATA);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LINKENDDATA);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LINKENDDESTRUCTIONDATA);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LITERALBOOLEAN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LITERALINTEGER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LITERALNULL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LITERALSTRING);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LITERALUNLIMITEDNATURAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_LOOPNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MANIFESTATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MERGENODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGEOCCURRENCESPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_ASYNCHCALL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_ASYNCHSIGNAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_CREATEMESSAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_DELETEMESSAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_REPLY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MESSAGE_SYNCHCALL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MODEL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_NODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OBJECTFLOW);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OCCURRENCESPECIFICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OPAQUEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OPAQUEBEHAVIOR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OPAQUEEXPRESSION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OPERATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OPERATIONTEMPLATEPARAMETER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_OUTPUTPIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PACKAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PACKAGEIMPORT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PACKAGEMERGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETERSET);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETER_IN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETER_INOUT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETER_OUT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARAMETER_RETURN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PARTDECOMPOSITION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PORT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PRIMITIVETYPE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROFILE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROFILEAPPLICATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROPERTY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROTOCOLCONFORMANCE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROTOCOLSTATEMACHINE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROTOCOLTRANSITION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_CHOICE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_DEEPHISTORY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_ENTRYPOINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_EXITPOINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_FORK);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_INITIAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_JOIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_JUNCTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_SHALLOWHISTORY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PSEUDOSTATE_TERMINATE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_QUALIFIERVALUE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_RAISEEXCEPTIONACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READEXTENTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READISCLASSIFIEDOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READLINKACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READLINKOBJECTENDACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READLINKOBJECTENDQUALIFIERACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READSELFACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READSTRUCTURALFEATUREACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_READVARIABLEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REALIZATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_RECEIVEOPERATIONEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_RECEIVESIGNALEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_RECEPTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_RECLASSIFYOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REDEFINABLETEMPLATESIGNATURE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REDUCEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REGION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REMOVESTRUCTURALFEATUREVALUEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REMOVEVARIABLEVALUEACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REPLYACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SENDOBJECTACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SENDOPERATIONEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SENDSIGNALACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SENDSIGNALEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SEQUENCENODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SIGNAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SIGNALEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SLOT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STARTCLASSIFIERBEHAVIORACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STATE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STATEINVARIANT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STATEMACHINE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STEREOTYPE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STRINGEXPRESSION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_STRUCTUREDACTIVITYNODE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SUBSTITUTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TEMPLATEBINDING);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TEMPLATEPARAMETER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TEMPLATEPARAMETERSUBSTITUTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TEMPLATESIGNATURE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TESTIDENTITYACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TIMECONSTRAINT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TIMEEVENT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TIMEEXPRESSION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TIMEINTERVAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TIMEOBSERVATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSITION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSITION_EXTERNAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSITION_INTERNAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSITION_LOCAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRIGGER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_UNMARSHALLACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_USAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_USECASE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_VALUEPIN);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_VALUESPECIFICATIONACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_VARIABLE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PROVIDEDINTERFACE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REQUIREDINTERFACE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ACTION);

        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ASYNCH_CALL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SYNCH_CALL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MESSAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DELETE_MESSAGE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REPLY);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_ALT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_ASSERT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_BREAK);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_CONSIDER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_CRITICAL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_IGNORE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_LOOP);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_NEG);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_OPT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_PAR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_SEQ);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_INTERACTION_STRICT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_BINARY_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DIRECTED_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SHARED_BINARY_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SHARED_DIRECTED_ASSOCIATIONASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMPOSITE_BINARY_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMPOSITE_DIRECTED_ASSOCIATION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_NOTE);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TEXT);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ATTACHMENT);

        
        //Action Icon
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADD_ATTRIBUTE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADD_COVERED_LIFELINE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADD_INTERACTION_OPERAND_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.iCONNAME_ADD_OPERATION_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_APPLY_STEREOTYPE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COMPARTMENT_VISIBILITY_ACTION);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CUT_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_DELETE_FROM_MODEL_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_FIND_ELEMENT_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SAVE_TO_IMAGE_ACTION);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_COPY_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_SAVE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_PASTE_ACTION);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REMOVE_COVERED_LIFELINE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSFORMATION_KEYWORD_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_TRANSFORMATION_LANGUAGE_ACTION);
        
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_DICTIONARY_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MDAD_ANALYSIS_STRUCTURE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MDAD_BEHAVIOR_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MDAD_DESIGN_MODEL_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MDAD_SEQUENCE_DIAGRAM_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_MDAD_STRUCTURE_ACTION);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_REPORT_COMPONENT_ARCHITECTURE_DEFINITION_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_REPORT_COMPONENT_DEFINITION_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CREATE_REPORT_INTERFACE_INTERACTION_DEFINITION_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_ADD_CREATE_REPORT_UI_DEFINITION);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_GENERATE_SOURCE_CODE_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MDA_DESIGNER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MDA_DEVELOPER);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_MERGE_CLASS_ACTION);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_WARNING_OVER);
        
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLEAR);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_CLOSE_MODEL);
        createImageRegisterMap(reg, IConstantImageRegistry.ICONNAME_REVERSE_EXEC);
    }

    /**
     * createImageRegisterMap
     *  
     * @param reg
     * @param name void
     */
    protected void createImageRegisterMap(ImageRegistry reg, String name) {
        IPath pathLarge = new Path(IConstantImageRegistry.eInstance.getLocationForLarge(name));
        imageRegister(reg, pathLarge, IConstantImageRegistry.eInstance.getLargeIconKey(name));
        IPath pathNormal = new Path(IConstantImageRegistry.eInstance.getLocationForNormal(name));
        imageRegister(reg, pathNormal, IConstantImageRegistry.eInstance.getNormalIconKey(name));
        IPath pathSmall = new Path(IConstantImageRegistry.eInstance.getLocationForSmall(name));
        imageRegister(reg, pathSmall, IConstantImageRegistry.eInstance.getSmallIconKey(name));
    }

    /**
     * 
     * 
     * @param reg
     * @param path
     * @param key
     *            void
     */
    @SuppressWarnings("deprecation")
    protected void imageRegister(ImageRegistry reg, IPath path, String key) {

        Bundle bundle = Platform.getBundle(PLUGIN_ID);
        URL url = Platform.find(bundle, path);
        ImageDescriptor desc = ImageDescriptor.createFromURL(url);

        if (desc != null) {
            // 이미지 등록
            reg.put(key, desc);
        }

    }

    /**
     * setColorRegistry
     *   void
     */
    public void setColorRegistry() {
        colorRegistry.put("AntiqueWhite", new Color(null, 250, 235, 215));
        colorRegistry.put("Bisque", new Color(null, 255, 228, 196));
        colorRegistry.put("Peach", new Color(null, 255, 218, 185));
        colorRegistry.put("LightGray", new Color(null, 211, 211, 211));
        colorRegistry.put("SlateGray", new Color(null, 112, 138, 144));
        colorRegistry.put("Gray", new Color(null, 190, 190, 190));
        colorRegistry.put("DimGray", new Color(null, 105, 105, 105));
        colorRegistry.put("MidnightBlue", new Color(null, 25, 25, 112));
        colorRegistry.put("Cornflower", new Color(null, 100, 149, 237));
        colorRegistry.put("SkyBlue", new Color(null, 135, 206, 250));
        colorRegistry.put("CadetBlue", new Color(null, 95, 158, 160));
        colorRegistry.put("GreenYellow", new Color(null, 173, 255, 47));
        colorRegistry.put("LimeGreen", new Color(null, 50, 205, 50));
        colorRegistry.put("Khaki", new Color(null, 240, 230, 140));
        colorRegistry.put("LightGoldenrod", new Color(null, 238, 221, 130));
        colorRegistry.put("Yellow", new Color(null, 255, 255, 0));
        colorRegistry.put("IndianRed", new Color(null, 205, 92, 92));
        colorRegistry.put("Firebrick", new Color(null, 178, 34, 34));
        colorRegistry.put("Brown", new Color(null, 165, 42, 42));
        colorRegistry.put("Orange", new Color(null, 255, 165, 0));
        colorRegistry.put("Coral", new Color(null, 255, 127, 80));
        colorRegistry.put("Tomato", new Color(null, 255, 69, 0));
        colorRegistry.put("OrangeRed", new Color(null, 255, 69, 0));
        colorRegistry.put("HotPink", new Color(null, 255, 105, 180));
        colorRegistry.put("Pink", new Color(null, 255, 192, 203));
        colorRegistry.put("LightPink", new Color(null, 255, 182, 193));
        colorRegistry.put("Violet", new Color(null, 238, 130, 238));
        colorRegistry.put("Thistle", new Color(null, 216, 191, 216));
        colorRegistry.put("Black", new Color(null, 0, 0, 0));
        colorRegistry.put("White", new Color(null, 255, 255, 255));
        colorRegistry.put("Red", new Color(null, 255, 0, 0));
    }

    /**
     * setFontRegistry
     *   void
     */
    private void setFontRegistry() {
        fontRegistry.put("default", new Font(null, "sampleFont", 9, SWT.BOLD));
        fontRegistry.put("default8", new Font(null, "sampleFont", 8, SWT.BOLD));
        fontRegistry.put("default7", new Font(null, "sampleFont", 7, SWT.BOLD));
        fontRegistry.put("italic", new Font(null, "sampleFont", 9, SWT.ITALIC | SWT.BOLD));
        fontRegistry.put("italic8", new Font(null, "sampleFont", 8, SWT.ITALIC | SWT.BOLD));
        fontRegistry.put("default10", new Font(null, "sampleFont", 10, SWT.NORMAL));
    }

    /**
     * getFont
     *  
     * @param fontType
     * @return Font
     */
    public Font getFont(String fontType) {
        return fontRegistry.get(fontType);
    }

    /**
     * getColor
     *  
     * @param colorName
     * @return Color
     */
    public Color getColor(String colorName) {
        return (Color) colorRegistry.get(colorName);
    }

    /**
     * getColorRegiColor
     *  
     * @return HashMap<String,Color>
     */
    public HashMap<String, Color> getColorRegiColor() {
        return colorRegistry;
    }

    /**
     * getShell
     *  
     * @return Shell
     */
    public static Shell getShell() {
        return getActiveWorkbench().getActiveWorkbenchWindow().getShell();
    }

    /**
     * getActiveWorkbench
     *  
     * @return IWorkbench
     */
    public static IWorkbench getActiveWorkbench() {
        return getDefault().getWorkbench();
    }

    /**
     * getActivePage
     *  
     * @return IWorkbenchPage
     */
    public static IWorkbenchPage getActivePage() {

        IWorkbenchWindow window = getActiveWorkbench().getActiveWorkbenchWindow();
        if (window == null)
            return null;

        return window.getActivePage();

    }

}
