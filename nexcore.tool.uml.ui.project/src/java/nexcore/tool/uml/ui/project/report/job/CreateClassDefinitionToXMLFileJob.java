/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.FileUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.DiagramImageUtil;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 클래스 명세서
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.job</li>
 * <li>설 명 : CreateClassDefinitionToXMLFileJob</li>
 * <li>작성일 : 2010. 1. 15.</li>
 * <li>작성자 : 송정훈</li>
 * </ul>
 */
public class CreateClassDefinitionToXMLFileJob extends CommonReportJob {

    /** 출력할 대상 Package */
    private Package rootPkg;

    /** 출력할 대상 Model */
    private Model model;

    /** 문서를 생성할 경로 */
    private String outputFilePath;

    /** 프로젝트 이름 */
    private String projectName;

    /** 선택한 객체 리스트 */
    private List<Element> selectedElementList;

    /** 사용할 템플릿 */
    private IFile templateFile;

    /**
     * 생성자
     * 
     * @param pkg
     * @param outputFilePath
     * @param projectName
     */
    public CreateClassDefinitionToXMLFileJob(Package pkg, EObject rootModel, String outputFilePath, String projectName,
    IFile templateFile) {
        super("Create Class Definition"); //$NON-NLS-1$
        this.rootPkg = pkg;
        this.model = (Model) rootModel;
        this.outputFilePath = outputFilePath;
        this.projectName = projectName;
        this.templateFile = templateFile;
    }

    /**
     * 생성자
     * 
     * @param pkg
     * @param selectedElementList
     * @param outputFilePath
     * @param projectName
     */
    public CreateClassDefinitionToXMLFileJob(Package pkg, EObject rootModel, List<Element> selectedElementList,
    String outputFilePath, String projectName, IFile templateFile) {
        this(pkg, rootModel, outputFilePath, projectName, templateFile);

        this.selectedElementList = selectedElementList;
    }

    /**
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.beginTask("Reporting...", selectedElementList.size()); //$NON-NLS-1$

        try {

            InputStream inputStream = null;

            // 템플릿 선택했을때는 그 템플릿 유형으로 나오게.
            if (templateFile != null) {
                inputStream = templateFile.getContents(true);
            } else {
                // 특별한 선택 없을때는 default로 나오게.
                URL url = this.getClass()
                    .getClassLoader()
                    .getResource(UICoreConstant.CLASS_DEFINITION_TO_XML_TEMPLATE_FILE_PATH);

                inputStream = url.openStream();
            }
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            DataModel dataModel = createRootDataModel(monitor); // rootDataModel
            UMLTemplateParser parser = new UMLTemplateParser(doc, dataModel);

            Node node = parser.parse();

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(node);
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            StreamResult streamResult = new StreamResult(fos);
            transformer.transform(source, streamResult);
            fos.close();

            File file = new File(System.getProperty("java.io.tmpdir") + UICoreConstant.REPORT__TMP_DIR); //$NON-NLS-1$
            if (file.exists()) {
                FileUtil.deleteDirectory(file.getPath());
            }

        } catch (Exception e) {
            Log.error(e);
            monitor.done();
            return new Status(IStatus.ERROR, UMLMessage.MESSAGE_DIALOG_TITLE, e.getMessage(), e);
        }
        monitor.done();
        return Status.OK_STATUS;
    }

    /**
     * 루트 데이터 모델을 생성합니다.
     * 
     * @return DataModel
     */
    private DataModel createRootDataModel(IProgressMonitor monitor) throws Exception {
        DataModel rootModel = new DataModel();

        setDataModel(rootModel, UICoreConstant.REPORT__PROJECT_NAME, projectName);
        setDataModel(rootModel, UICoreConstant.REPORT__SYSTEM_NAME, System.getProperty("user.name")); //$NON-NLS-1$
        setDataModel(rootModel, UICoreConstant.REPORT__AUTHOR, System.getProperty("user.name")); //$NON-NLS-1$
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        setDataModel(rootModel, UICoreConstant.REPORT__DATE, dateString);
        setDataModel(rootModel, UICoreConstant.REPORT__MODEL_NAME, model.getName());
        setDataModel(rootModel, UICoreConstant.REPORT__PACKAGE_LIST, createPackageDataModelList(rootPkg, monitor)); // package
        // data

        return rootModel;
    }

    /**
     * Package 데이터 모델 리스트를 생성합니다.
     * 
     * @param parentPkg
     * @return List<DataModel>
     */
    private List<DataModel> createPackageDataModelList(Package parentPkg, IProgressMonitor monitor) throws Exception {
        List<DataModel> packageModelList = new ArrayList<DataModel>();
        List<DataModel> diagramList = createDiagramDataModelList(parentPkg);
        List<DataModel> classList = createClassDataModelList(parentPkg);
        if (diagramList.size() > 0 || classList.size() > 0) {
            DataModel packageModel = new DataModel();
            setDataModel(packageModel, UICoreConstant.REPORT__PACKAGE_NAME, parentPkg.getName());
            setDataModel(packageModel, UICoreConstant.REPORT__PACKAGE_NAMESPACE, parentPkg.getQualifiedName());
            setDataModel(packageModel,
                UICoreConstant.REPORT__PACKAGE_DOCUMENTATION,
                getCommentToString(parentPkg.getOwnedComments()));
            setDataModel(packageModel, UICoreConstant.REPORT__DIAGRAM_LIST, diagramList);
            setDataModel(packageModel, UICoreConstant.REPORT__CLASS_LIST, classList);
            packageModelList.add(packageModel);
        }

        Package pkg;
        for (PackageableElement element : parentPkg.getPackagedElements()) {
            if (element.eClass() == UMLPackage.Literals.PACKAGE) {
                pkg = (Package) element;

                if (selectedElementList.contains(pkg)) {
                    if (monitor.isCanceled()) {
                        monitor.done();
                        return null;
                    }
                    monitor.subTask(pkg.getQualifiedName());

                    packageModelList.addAll(createPackageDataModelList((Package) element, monitor));
                    monitor.worked(1);
                }

            }
        }

        return packageModelList;
    }

    /**
     * Diagram 데이터 모델 리스트를 생성합니다.
     * 
     * @param pkg
     * @return List<DataModel>
     */
    private List<DataModel> createDiagramDataModelList(Package pack) throws Exception {
        List<DataModel> diagramModelList = new ArrayList<DataModel>();
        EAnnotation annotation = null;
        Diagram diagram;
        for (PackageableElement element : pack.getPackagedElements()) {
            if (element.eClass() == UMLPackage.Literals.COLLABORATION) {
                annotation = element.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                if (annotation != null) {
                    for (EObject eobj : annotation.getContents()) {
                        if (eobj.eClass() == UMLDiagramPackage.Literals.DIAGRAM) {
                            diagram = (Diagram) eobj;
                            if (diagram.getType() == DiagramType.CLASS_DIAGRAM) {
                                diagramModelList.add(createDiagramModel(diagram));
                            }
                        }
                    }
                }
            }
        }

        // if (eObject.eClass() == UMLPackage.Literals.PACKAGE
        // || eObject.eClass() == UMLPackage.Literals.MODEL) {
        // Package pkg = (Package) eObject;
        // for (PackageableElement element : pkg.getPackagedElements()) {
        // diagramModelList.addAll(createDiagramDataModelList(element));
        // }
        // }

        return diagramModelList;
    }

    /**
     * 
     * 
     * @param diagram
     * @return DataModel
     * @throws IOException
     */
    private DataModel createDiagramModel(Diagram diagram) throws IOException {

        DataModel diagramModel = new DataModel();
        setDataModel(diagramModel, UICoreConstant.REPORT__DIAGRAM_NAME, diagram.getName());
        DiagramImageUtil diagramUtil = new DiagramImageUtil(diagram);
        super.showSyncResults(diagramUtil);

        Image img = diagramUtil.getDiagramImage();
        ImageLoader loader = new ImageLoader();

        if (img == null) {
            img = new Image(null, 1, 1);
        }
        setDataModel(diagramModel, UICoreConstant.REPORT__DIAGRAM_WIDTH, Integer.toString(diagramUtil.getImageWidth()));
        setDataModel(diagramModel,
            UICoreConstant.REPORT__DIAGRAM_HEIGHT,
            Integer.toString(diagramUtil.getImageHeight()));
        loader.data = new ImageData[] { img.getImageData() };

        File file = new File(System.getProperty("java.io.tmpdir") + UICoreConstant.REPORT__TMP_DIR); //$NON-NLS-1$
        if (!file.exists()) {
            file.mkdir();
        }

        loader.save(file.getPath() + "\\" + img.toString() + UICoreConstant.PROJECT_CONSTANTS__DOT
            + UICoreConstant.REPORT__IMG_JPG, SWT.IMAGE_JPEG); //$NON-NLS-1$
        setDataModel(diagramModel, UICoreConstant.REPORT__DIAGRAM_IMG, file.getPath()
            + "\\" + img.toString() + UICoreConstant.PROJECT_CONSTANTS__DOT + UICoreConstant.REPORT__IMG_JPG); //$NON-NLS-1$

        img = null;
        loader = null;
        diagramUtil = null;

        return diagramModel;

    }

    /**
     * Class 데이터 모델 리스트를 생성합니다.
     * 
     * @param pkg
     * @return List<DataModel>
     */
    private List<DataModel> createClassDataModelList(Package pkg) {
        List<DataModel> classModelList = new ArrayList<DataModel>();

        DataModel classModel;

        List<PackageableElement> list = pkg.getPackagedElements();
        List<Class> classes = new ArrayList<Class>();
        for (PackageableElement packageableElement : list) {
            if (packageableElement.eClass() == UMLPackage.Literals.CLASS) {
                classes.add((Class) packageableElement);
            }
        }

        ProjectUtil.getSortedList(classes);
        if (!classes.isEmpty()) {
            for (Class clazz : classes) {

                classModel = new DataModel();

                setDataModel(classModel, UICoreConstant.REPORT__CLASS_NAME, clazz.getName());
                setDataModel(classModel, UICoreConstant.REPORT__CLASS_STEREOTYPE, ProjectUtil.getStereotypeLabel(clazz));
                setDataModel(classModel,
                    UICoreConstant.REPORT__CLASS_DOCUMENTATION,
                    getCommentToString(clazz.getOwnedComments()));
                setDataModel(classModel, UICoreConstant.REPORT__ATTRIBUTE_LIST, createAttributeDataModelList(clazz));
                setDataModel(classModel, UICoreConstant.REPORT__OPERATION_LIST, createOperationDataModelList(clazz));

                classModelList.add(classModel);
            }

        }

        return classModelList;

    }

    /**
     * Attribute 데이터 모델 리스트를 생성합니다.
     * 
     * @param cls
     * @return List<DataModel>
     */
    private List<DataModel> createAttributeDataModelList(Class cls) {
        List<DataModel> attributeModelList = new ArrayList<DataModel>();
        DataModel attributeModel;
        String qualifiedTypeName = UICoreConstant.EMPTY_STRING;

        if (!cls.getAttributes().isEmpty()) {
            for (Property property : cls.getAttributes()) {
                attributeModel = new DataModel();

                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_NAME, property.getName());
                setDataModel(attributeModel,
                    UICoreConstant.REPORT__ATTRIBUTE_DOCUMENTATION,
                    getCommentToString(property.getOwnedComments()));
                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_VISIBILITY, property.getVisibility()
                    .toString());

                if (property.getType() != null) {
                    StringBuffer stringBuffer = new StringBuffer();

                    boolean isPrimitives = false;
                    qualifiedTypeName = property.getType().getQualifiedName();
                    for (int i = 0; i < UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES.length; i++) {
                        if (qualifiedTypeName.startsWith(UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES[i])) {
                            isPrimitives = true;
                            stringBuffer.append(property.getType().getName());
                            break;
                        }
                    }
                    if (!isPrimitives) {
                        stringBuffer.append(MDDCommonUtil.getMappedName(qualifiedTypeName));
                    }
                    setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_TYPE, stringBuffer.toString());
                } else {
                    setDataModel(attributeModel,
                        UICoreConstant.REPORT__ATTRIBUTE_TYPE,
                        UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                }

                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_DEFAULT_VALUE, property.getDefault());

                attributeModelList.add(attributeModel);
            }
        } else {
            attributeModel = new DataModel();
            // setDataModel(attributeModel,
            // UICoreConstant.REPORT__ATTRIBUTE_NAME,
            // UMLMessage.LABEL_NO_APPLICABLE);

            attributeModelList.add(attributeModel);

        }

        return attributeModelList;
    }

    /**
     * Operation 데이터 모델 리스트를 생성합니다.
     * 
     * @param cls
     * @return List<DataModel>
     */
    private List<DataModel> createOperationDataModelList(Class cls) {
        List<DataModel> operationModelList = new ArrayList<DataModel>();
        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        DataModel operationModel;

        if (!cls.getOperations().isEmpty()) {
            for (Operation operation : cls.getOperations()) {
                operationModel = new DataModel();

                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_NAME, operation.getName());
                setDataModel(operationModel,
                    UICoreConstant.REPORT__OPERATION_DOCUMENTATION,
                    getCommentToString(operation.getOwnedComments()));
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_VISIBILITY, operation.getVisibility()
                    .toString());

                // 리턴 유형
                Parameter returnType = operation.getReturnResult();
                String returnTypeName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                if (returnType != null) {
                    if (returnType.getType() != null) {
                        if (returnType.getType().getName() != null) {
                            returnTypeName = returnType.getType().getName();
                        }
                    }
                }
                if (UICoreConstant.EMPTY_STRING.equals(returnTypeName)
                    && store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE)) {
                    returnTypeName = UMLMessage.LABEL_NO_APPLICABLE_SHORT;
                }
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_RETURNTYPE, returnTypeName);

                StringBuffer paramDocument = new StringBuffer();
                StringBuffer sb = new StringBuffer();
                String typeName = UICoreConstant.EMPTY_STRING;
                for (int i = 0; i < operation.getOwnedParameters().size(); i++) {
                    Parameter parameter = operation.getOwnedParameters().get(i);
                    if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
                        typeName = UICoreConstant.EMPTY_STRING;

                        // 번호
                        int tempCnt = i + 1;
                        paramDocument.append(tempCnt);
                        paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__DOT);
                        paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);

                        sb.append(tempCnt);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__DOT);

                        // 변수명.
                        sb.append(UMLMessage.LABEL_NAME);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        if (parameter.getName() != null) {
                            sb.append(parameter.getName());
                        }
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COMMA);

                        // 방향
                        sb.append(UMLMessage.LABEL_DIRECTION);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        if (parameter.getDirection() != null) {
                            sb.append(getParameterName(parameter.getDirection()));
                        }
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COMMA);

                        // 타입
                        sb.append(UMLMessage.LABEL_TYPE);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        if (parameter.getType() != null) {
                            if (parameter.getType().getName() != null) {
                                typeName = parameter.getType().getName();
                                sb.append(typeName);
                            }
                        }
                        // sb.append(UICoreConstant.PROJECT_CONSTANTS__COMMA);
                        //
                        // sb.append(UMLMessage.LABEL_ORDERED);
                        // sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        // sb.append(parameter.isOrdered());
                        // sb.append(UICoreConstant.PROJECT_CONSTANTS__COMMA);
                        // sb.append(UMLMessage.LABEL_IS_UNIQUE);
                        // sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        // sb.append(parameter.isUnique());
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
                    }
                }
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_PARAMETER, sb.toString());
                operationModelList.add(operationModel);

            }
        } else {
            operationModel = new DataModel();
            // setDataModel(operationModel,
            // UICoreConstant.REPORT__OPERATION_NAME,
            // UMLMessage.LABEL_NO_APPLICABLE);

            operationModelList.add(operationModel);

        }

        return operationModelList;
    }

    /**
     * 
     * 
     * @param direction
     * @return String
     */
    private String getParameterName(ParameterDirectionKind direction) {
        if (ParameterDirectionKind.IN_LITERAL.equals(direction)) {
            return UMLMessage.LABEL_IN;
        } else if (ParameterDirectionKind.INOUT_LITERAL.equals(direction)) {
            return UMLMessage.LABEL_IN_OUT;
        } else if (ParameterDirectionKind.OUT_LITERAL.equals(direction)) {
            return UMLMessage.LABEL_OUT;
        } else if (ParameterDirectionKind.RETURN_LITERAL.equals(direction)) {
            return UMLMessage.LABEL_RETURN;
        }
        return null;
    }

}
