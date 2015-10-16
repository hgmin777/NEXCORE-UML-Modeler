/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.CommonViewer;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설  명 : UMLBuilder</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLBuilder extends IncrementalProjectBuilder {

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.newproject</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
     * <li>설 명 : SampleDeltaVisitor</li>
     * <li>작성일 : 2009. 11. 30.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class SampleDeltaVisitor implements IResourceDeltaVisitor {
        /**
         * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
         */
        public boolean visit(IResourceDelta delta) throws CoreException {

            IResource source = delta.getResource();
            switch (source.getType()) {
                case IResource.ROOT:
                    return true;
                case IResource.PROJECT:
                    IProject project = (IProject) source;
                    break;
            }

            IResource resource = delta.getResource();
            switch (delta.getKind()) {
                case IResourceDelta.ADDED:
                    // handle added resource
                    checkXML(resource);
                    break;
                case IResourceDelta.REMOVED:
                    // handle removed resource
                    // 삭제된 경우 상위 폴더 refresh
                    if (resource instanceof IFile) {
                        final IFile file = (IFile) resource;
                        
                        if ((UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension()) || UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension()))) {
                            Display.getDefault().asyncExec(new Runnable() {
                                /**
                                 * @see java.lang.Runnable#run()
                                 */
                                public void run() {
                                    try {
                                        CommonViewer commonViewer = ViewerRegistry.getViewer();
                                        ISelection selection = commonViewer.getSelection();
                                        if (commonViewer.getControl().isDisposed()) {
                                            return;
                                        }
                                        TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

                                        if(file != null && file.getParent() != null ) {
                                        	commonViewer.refresh(file.getParent());
                                        }
                                        TreeItemUtil.expandTreePath(expanedTreePaths, selection);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
                            List<Object> removeTarget = new ArrayList<Object>();
                            for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
                                String type = (String) iterator.next();
                                URI uri = URI.createURI(file.getFullPath().toString());
                                if (type.indexOf(uri.toString()) > -1) {
                                    removeTarget.add(type);
                                }
                            }

                            // ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes()
                            // 에서 관련된 키 삭제
                            for (Object o : removeTarget) {
                                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
                            }
                        }
                    }
                    break;
                case IResourceDelta.CHANGED:
                    // handle changed resource
                    checkXML(resource);
                    break;
                case IResourceDelta.OPEN:
                    // TODO 닫혀있는 프로젝트를 열었을때 리소스 load 처리( 로그인 했을때와 동일)
                    break;
            }
            // return true to continue visiting children.
            return true;
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.newproject</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
     * <li>설 명 : SampleResourceVisitor</li>
     * <li>작성일 : 2009. 11. 30.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class SampleResourceVisitor implements IResourceVisitor {
        /**
         * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
         */
        public boolean visit(IResource resource) {
            checkXML(resource);
            // return true to continue visiting children.

            IResource source = resource;
            switch (source.getType()) {
                case IResource.ROOT:
                    return true;
                case IResource.PROJECT:
                    IProject project = (IProject) source;
                    break;
            }

            return true;
        }
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.newproject</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
     * <li>설 명 : XMLErrorHandler</li>
     * <li>작성일 : 2009. 11. 30.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class XMLErrorHandler extends DefaultHandler {

        /** */
        private IFile file;

        /**
         * @param file
         */
        public XMLErrorHandler(IFile file) {
            this.file = file;
        }

        /**
         * 
         * 
         * @param e
         * @param severity
         *            void
         */
        private void addMarker(SAXParseException e, int severity) {
            UMLBuilder.this.addMarker(file, e.getMessage(), e.getLineNumber(), severity);
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
         */
        public void error(SAXParseException exception) throws SAXException {
            addMarker(exception, IMarker.SEVERITY_ERROR);
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
         */
        public void fatalError(SAXParseException exception) throws SAXException {
            addMarker(exception, IMarker.SEVERITY_ERROR);
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
         */
        public void warning(SAXParseException exception) throws SAXException {
            addMarker(exception, IMarker.SEVERITY_WARNING);
        }
    }

    /** */
    public static final String BUILDER_ID = "nexcore.tool.uml.ui.project.builder.UMLBuilder";

    /** */
    private static final String MARKER_TYPE = "nexcore.tool.uml.ui.project.umxProblem";

    /** */
    private SAXParserFactory parserFactory;

    /**
     * 
     * 
     * @param file
     * @param message
     * @param lineNumber
     * @param severity
     *            void
     */
    private void addMarker(IFile file, String message, int lineNumber, int severity) {
        try {
            IMarker marker = file.createMarker(MARKER_TYPE);
            marker.setAttribute(IMarker.MESSAGE, message);
            marker.setAttribute(IMarker.SEVERITY, severity);
            if (lineNumber == -1) {
                lineNumber = 1;
            }
            marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
        } catch (CoreException e) {}
    }

    /**
     * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int,
     *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings("unchecked")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
        if (kind == FULL_BUILD) {
            fullBuild(monitor);
        } else {
            IResourceDelta delta = getDelta(getProject());
            if (delta == null) {
                fullBuild(monitor);
            } else {
                incrementalBuild(delta, monitor);
            }
        }
        return null;
    }

    /**
     * 
     * 
     * @param resource
     *            void
     */
    void checkXML(IResource resource) {
        if (resource instanceof IFile
            && (resource.getName().endsWith(UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION) || resource.getName()
                .endsWith(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION))) {
            IFile file = (IFile) resource;
            deleteMarkers(file);
            XMLErrorHandler reporter = new XMLErrorHandler(file);
            try {
                getParser().parse(file.getContents(), reporter);
            } catch (Exception e1) {}
        }
    }

    /**
     * 
     * 
     * @param file
     *            void
     */
    private void deleteMarkers(IFile file) {
        try {
            file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
        } catch (CoreException ce) {}
    }

    /**
     * 
     * 
     * @param monitor
     * @throws CoreException void
     */
    protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
        try {
            getProject().accept(new SampleResourceVisitor());
        } catch (CoreException e) {}
    }

    /**
     * 
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     *             SAXParser
     */
    private SAXParser getParser() throws ParserConfigurationException, SAXException {
        if (parserFactory == null) {
            parserFactory = SAXParserFactory.newInstance();
        }
        return parserFactory.newSAXParser();
    }

    /**
     * 
     * 
     * @param delta
     * @param monitor
     * @throws CoreException void
     */
    protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
        // the visitor does the work.
        delta.accept(new SampleDeltaVisitor());
    }
}
