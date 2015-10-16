/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.alm.common.word.DataModel;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.FileUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.report.job.UMLTemplateParser;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.control</li>
 * <li>설  명 : ReportHandler</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public abstract class ReportHandler {

    /**
     * rootModel
     */
    protected DataModel rootModel;

    // 진행상태 처리기.
    /**
     * jobMonitor
     */
    protected IJobMonitor jobMonitor = null;

    /**
     * @param jobMonitor
     *            the jobMonitor to set
     */
    public void setJobMonitor(IJobMonitor jobMonitor) {
        this.jobMonitor = jobMonitor;
    }

    /** 생성할 보고서 위치 및 파일명 */
    private String reportLocation = UICoreConstant.EMPTY_STRING;

    /** 사용될 템플릿 객체 */
    private IFile templateFile = null;

    /**
     * projectName
     */
    protected String projectName = UICoreConstant.EMPTY_STRING;

    /** 보고서 대상 목록 */
    protected List<Element> selectedPackageList = null;

    /**
     * doc
     */
    private Document doc;

    /**
     * ReportHandler
     * @param reportLocation
     * @param templateFile
     * @param selectedPackageList
     * @param projectName
     */
    public ReportHandler(String reportLocation, IFile templateFile, List<Element> selectedPackageList,
    String projectName) {
        this.reportLocation = reportLocation;
        this.templateFile = templateFile;
        this.selectedPackageList = selectedPackageList;
        this.projectName = projectName;
    }

    /**
     * createReportData
     *  
     * @param model void
     */
    abstract protected void createReportData(Model model);

    /**
     * createReport
     *   void
     */
    public void createReport() {
        Model model = this.getModel();
        rootModel = ReportContentManager.createRootModel(this.projectName, this.getModelName());
        this.createReportData(model);
        if (!this.jobMonitor.isCanceled()) {
            this.parse();
            this.clearTempData();
        }
        rootModel.clear();
        selectedPackageList.clear();

    }

    /**
     * isSelected
     *  
     * @param element
     * @return boolean
     */
    protected boolean isSelected(Element element) {
        if (null == this.selectedPackageList) {
            return false;
        }
        return this.selectedPackageList.contains(element);
    }

    /**
     * navigatePackage
     *  
     * @param reportContentManager
     * @param targetModel
     * @param current
     * @return boolean
     */
    protected boolean navigatePackage(ReportContentManager reportContentManager, DataModel targetModel, Package current) {
        boolean isVisible = false;
        List<Package> list = UMLManipulation.getChildPackageList(current);

        DataModel childPackageModel = null;
        List<DataModel> childPackageList = new ArrayList<DataModel>();
        if (this.jobMonitor.isCanceled()) {
            return false;
        }

        for (Package pkg : list) {
            if (this.jobMonitor.isCanceled()) {
                return false;
            }
            childPackageModel = new DataModel();
            if (true == navigatePackage(reportContentManager, childPackageModel, pkg)) {
                isVisible = true;
                childPackageList.add(childPackageModel);
            }

        }

        this.jobMonitor.setMonitor(current.getQualifiedName() + UICoreConstant.PROJECT_CONSTANTS__BLANK
            + UMLMessage.LABEL_PROCESSING);

        if (isSelected(current)) {
            reportContentManager.createReportContent(targetModel, current);
            ReportContentManager.setDataModel(targetModel, UICoreConstant.REPORT__PKG_INDEX_NAME, current.getName());
            isVisible = true;
        } else if (true == isVisible) {
            ReportContentManager.setDataModel(targetModel, UICoreConstant.REPORT__PKG_INDEX_NAME, current.getName());
        }
        ReportContentManager.setDataModel(targetModel, UICoreConstant.REPORT__PACKAGE_DOCUMENTATION, 
            ReportContentManager.applyPreference(ReportContentManager.getCommentToString(current.getOwnedComments())));
        
        if (0 == childPackageList.size()) {
            return isVisible;
        }
        ReportContentManager.setDataModel(targetModel, UICoreConstant.REPORT__PKG_INDEX_LIST, childPackageList);
        return isVisible;
    }

    /**
     * getDefaultTemplateFileName
     *  
     * @return String
     */
    abstract protected String getDefaultTemplateFileName();

    /**
     * parse
     *  
     * @return boolean
     */
    protected boolean parse() {

        InputStream inputStream = null;

        // 템플릿 선택했을때는 그 템플릿 유형으로 나오게.
        if (templateFile != null) {
            try {
                inputStream = templateFile.getContents(true);
            } catch (CoreException e) {
                e.printStackTrace();
                ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
                return false;
            }
        } else {
            // 특별한 선택 없을때는 default로 나오게.
            URL url = this.getClass().getClassLoader().getResource(UICoreConstant.XML_TEMPLATE_FILE_ROOT_PATH
                + this.getDefaultTemplateFileName());

            try {
                inputStream = url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
                ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
                return false;
            }
        }

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        }
        UMLTemplateParser parser = new UMLTemplateParser(doc, this.rootModel);
        Node node;
        try {
            node = parser.parse();
        } catch (Exception e1) {
            e1.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e1.getMessage(), e1);
            return false;
        }

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tFactory.newTransformer();
        } catch (TransformerConfigurationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }

        DOMSource source = new DOMSource(node);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(this.reportLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        }
        StreamResult streamResult = new StreamResult(fos);
        try {
            transformer.transform(source, streamResult);
            fos.close();
        } catch (TransformerException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return false;
        }

        return true;

    }

    /**
     * save
     *   void
     */
    protected void save() {

    }

    /**
     * clearTempData
     *   void
     */
    protected void clearTempData() {
//        String pathStr = String.format("%s%sumlTemp%s", System.getProperty("user.home"), File.separator, File.separator);
        File file = new File(System.getProperty("java.io.tmpdir") + UICoreConstant.REPORT__TMP_DIR); //$NON-NLS-1$
        if (file.exists()) {
            FileUtil.deleteDirectory(file.getPath());
        }
    }

    /**
     * getModelName
     *  
     * @return String
     */
    protected String getModelName() {
        if (0 == this.selectedPackageList.size()) {
            return UICoreConstant.EMPTY_STRING;
        }
        return this.selectedPackageList.get(0).getModel().getName();
    }

    /**
     * getModel
     *  
     * @return Model
     */
    protected Model getModel() {
        if (0 == this.selectedPackageList.size()) {
            return null;
        }
        return this.selectedPackageList.get(0).getModel();
    }

}
