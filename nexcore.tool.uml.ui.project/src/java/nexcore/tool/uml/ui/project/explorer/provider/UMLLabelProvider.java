/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : UMLLabelProvider</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLLabelProvider implements ICommonLabelProvider, IStyledLabelProvider, IColorProvider {

    // private AdapterFactoryLabelProvider adapterFactoryLabelProvider = new
    // AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
    // .getItemProvidersAdapterFactory());

    /**
     * 
     * @see org.eclipse.ui.navigator.ICommonLabelProvider#init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
     */
    public void init(ICommonContentExtensionSite config) {
        // redmine(6711) Commit 후, 노드명의 꺽쇠표시가 없어져야 하는데 refresh가 되지 않는 문제 해결
        final CommonViewer commonViewer = ((CommonViewer) ViewerRegistry.getViewer());
        commonViewer.getLabelProvider().addListener(new ILabelProviderListener() {
            public synchronized void labelProviderChanged(LabelProviderChangedEvent event) {
                if (!ProjectUtil.isUMLPerspective()) {
                    return;
                }

                Map<String, IFile> umlModelFile = new HashMap<String, IFile>();
                Object[] elements = event.getElements();
                if (elements == null || elements.length == 0) {
                    return;
                }

                for (final Object element : elements) {
                    if (element instanceof IFile) {
                        IFile file = (IFile) element;
                        if (ProjectUtil.isModelFile(file)) {
                            umlModelFile.put(file.getFullPath().toString(), file);
                        } else {
                            continue;
                        }
                    }
                }

                CommonViewer commonViewer = ViewerRegistry.getViewer();
                TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

                if (umlModelFile.size() > 0) {
                    TreePath[] treePaths = null;
                    try {
                        treePaths = TreeItemUtil.getUMLModelTreePaths(commonViewer.getTree()).clone();
                    } catch (Exception e) {
                        // do nothing
                    } finally {
                        if (treePaths == null) {
                            return;
                        }
                    }
                    for (TreePath t : treePaths) {
                        if (t == null)
                            continue;

                        Object node = t.getLastSegment();
                        if (node instanceof UMLFileTreeNode) {
                            try {
                                ((UMLFileTreeNode) node).refreshNode();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            try {
                                if (((UMLFileTreeNode) node).getResource() != null) {
                                    String filePath = ((UMLFileTreeNode) node).getResource().getFullPath().toString();

                                    if (umlModelFile.containsKey(filePath)) {
                                        ProjectUtil.refreshNodeInExplorer(((UMLFileTreeNode) node).getEObject());
                                    }
                                    try {
                                        commonViewer.refresh(node);
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (node instanceof IFolder) {
                            // redmine 13244 : 모델 파일 삭제 후 Undo 시 Refresh
                            for (Iterator<IFile> iterator = umlModelFile.values().iterator(); iterator.hasNext();) {
                                IFile f = iterator.next();

                                IContainer parent = f.getParent();
                                if (((IFolder) node).getFullPath().equals((parent).getFullPath())) {
                                    commonViewer.refresh(node);
                                    try {
                                        parent.refreshLocal(IResource.DEPTH_INFINITE, null);
                                    } catch (CoreException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                TreeItemUtil.expandTreePath(expanedTreePaths, null);
            }

        });
    }

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object element) {

        if( element instanceof ClosedTreeNode) {
            return UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_CLOSE_MODEL).createImage();
        } else if (element instanceof ITreeNode) {
            ITreeNode treeNode = (ITreeNode) element;
            
            EObject eobject = treeNode.getEObject();
            return UiCorePlugin.getDefault().getImageForUMLElement(eobject);

        } else if (element instanceof IFile) {
            IFile file = (IFile) element;
            if (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())
                || UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension())) {
                URI uri = URI.createURI(file.getFullPath().toString());
                
                boolean active = ResourceManager.getInstance().isActive(uri);
                if(!active) {
                    return null;
                }
                
                Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
				if (resource != null) {
					EObject eobject = resource.getContents().get(0);
					return UiCorePlugin.getDefault().getImageForUMLElement(eobject);
				}
            }
        }
        return null;
    }

    /**
     * replaceNullString
     *  
     * @param text
     * @return String
     */
    public String replaceNullString(String text) {
        return text == null || UICoreConstant.EMPTY_STRING.equals(text) || "null".equals(text) ? UICoreConstant.PROJECT_CONSTANTS__BLANK : text;
    }

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object element) {
        // don't change the order!
        if (element instanceof ClosedTreeNode) {
            ClosedTreeNode treeNode = (ClosedTreeNode) element;
            IResource file = treeNode.getResource();
            return file.getName().substring(0, file.getName().length() - 4);
        } else if (element instanceof ITreeNode) {
            ITreeNode treeNode = (ITreeNode) element;
            EObject eobject = treeNode.getEObject();

            if (eobject instanceof NamedElement) {
                NamedElement namedElement = (NamedElement) eobject;
                return replaceNullString(ProjectUtil.getStereotypeLabel(namedElement) + namedElement.getName());

            } else if (eobject instanceof Diagram) {
                return replaceNullString(((Diagram) eobject).getName());

            } else if (eobject instanceof EAnnotation) {
                String source = ((EAnnotation) eobject).getSource();
                if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(source)) {
                    return replaceNullString(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                }
            } else if (eobject instanceof ProfileApplication) {
                ProfileApplication profileApplication = (ProfileApplication) eobject;

                    Profile profile = profileApplication.getAppliedProfile();
                    if (profile.eIsProxy()) {
                        String uriString = EcoreUtil.getURI(profile).toString();
                        String[] split = uriString.split("#");
                        return split !=null && split.length == 2 ? split[0] : uriString;
                    }
            }

            String label = UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().getText(eobject);
            int index = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET);
            if (index < 0) {
                return replaceNullString(label.substring(label.indexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET) + 1));
            } else {
                int first = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET);
                int last = label.lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
                String removed = label.substring(first + 2, last + 1);
                return replaceNullString(label.replaceFirst(removed, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING));
            }

        } else if (element instanceof IFile) {
            IFile file = (IFile) element;
            if (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())
                || UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension())) {
                URI uri = URI.createURI(file.getFullPath().toString());
                
                boolean active = ResourceManager.getInstance().isActive(uri);
                if(!active) {
                    int index = file.getFullPath().segmentCount();
                    return file.getFullPath().segment(index-1);
                }
                
                Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                if (resource == null) {
					return replaceNullString(file.getName());
				}
                NamedElement namedElement = (NamedElement) resource.getContents().get(0);
                return replaceNullString(ProjectUtil.getStereotypeLabel(namedElement) + namedElement.getName());
            } else {
                return replaceNullString(file.getName());
            }

        }

        return UICoreConstant.PROJECT_CONSTANTS__BLANK;
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener) {
        UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().addListener(listener);
        // RND:refresh()
        // if( ViewerRegistry.getViewer() != null ) {
        // ViewerRegistry.getViewer().addPostSelectionChangedListener(new
        // ISelectionChangedListener() {
        //
        // /**
        // * @see
        // org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
        // */
        // public void selectionChanged(SelectionChangedEvent event) {
        // IStructuredSelection selection = (IStructuredSelection)
        // event.getSelection();
        // Object obj = selection.getFirstElement();
        // if( obj instanceof ITreeNode) {
        // FocusRegistry.setFocused( ((ITreeNode) obj).getEObject() );
        // }
        // }
        //
        // });
        // }
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().dispose();
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property) {
        return UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().isLabelProperty(element, property);
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener) {
        UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().removeListener(listener);
    }

    /**
     * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
     */
    public void restoreState(IMemento memento) {

    }

    /**
     * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {

    }

    /**
     * @see org.eclipse.ui.navigator.IDescriptionProvider#getDescription(java.lang.Object)
     */
    public String getDescription(Object anElement) {
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider#getStyledText(java.lang.Object)
     */
    public StyledString getStyledText(Object element) {
        if (element instanceof ClosedTreeNode) {
            return new StyledString(getText(element), StyledString.createColorRegistryStyler("QUALIFIER_COLOR", null));// gray color
        } else if (element instanceof ITreeNode) {
            EObject eobject = ((ITreeNode) element).getEObject();
            if (eobject instanceof ProfileApplication) {
                ProfileApplication profileApplication = (ProfileApplication) eobject;

                Profile profile = profileApplication.getAppliedProfile();
                if (profile.eIsProxy()) {
                    String text = String.format("'%s' does not exist.", getText(element));
                    return new StyledString(text, StyledString.createColorRegistryStyler("ERROR_COLOR", null));// red color
                }
            }
        }

        return new StyledString(getText(element));
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(Object element) {
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(Object element) {
        return null;
    }
}
