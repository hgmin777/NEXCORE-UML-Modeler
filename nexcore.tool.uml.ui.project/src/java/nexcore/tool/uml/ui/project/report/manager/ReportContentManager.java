/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.DiagramImageUtil;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager</li>
 * <li>설  명 : ReportContentManager</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public abstract class ReportContentManager {

    /**
     * createReportContent
     *  
     * @param parentDataModel
     * @param pkg void
     */
    abstract public void createReportContent(DataModel parentDataModel, Package pkg);

    /**
     * createRootModel
     *  
     * @param projectName
     * @param modelName
     * @return DataModel
     */
    public static DataModel createRootModel(String projectName, String modelName) {
        DataModel rootModel = new DataModel();

        setDataModel(rootModel, UICoreConstant.REPORT__PROJECT_NAME, projectName);
        setDataModel(rootModel, UICoreConstant.REPORT__SYSTEM_NAME, System.getProperty("user.name")); //$NON-NLS-1$
        setDataModel(rootModel, UICoreConstant.REPORT__AUTHOR, System.getProperty("user.name")); //$NON-NLS-1$
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        setDataModel(rootModel, UICoreConstant.REPORT__DATE, dateString);
        setDataModel(rootModel, UICoreConstant.REPORT__MODEL_NAME, modelName);
        return rootModel;
    }

    /**
     * setDataModel
     *  
     * @param dataModel
     * @param key
     * @param value void
     */
    public static void setDataModel(DataModel dataModel, String key, Object value) {
        if (dataModel == null) {
            dataModel = new DataModel();
        }

        if (key == null) {
            key = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }

        if (value == null) {
            value = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }

        dataModel.put(key, value);
    }

    /**
     * getDataModel
     *  
     * @param dataModel
     * @param key
     * @return Object
     */
    public static Object getDataModel(DataModel dataModel, String key) {
        if (dataModel == null || key == null) {
            return null;
        }

        return dataModel.get(key);
    }

    /**
     * getCommentToString
     *  
     * @param commentList
     * @return String
     */
    public static String getCommentToString(List<Comment> commentList) {
        StringBuffer sb = new StringBuffer();
        Comment comment;
//        for (int i = 0; i < commentList.size(); i++) {
//            comment = commentList.get(i);
//            sb.append(comment.getBody());
//            if (i < commentList.size() - 1) {
//                sb.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
//            }
//        }
        if (commentList.size() > 0) {
            comment = commentList.get(0);
            sb.append(comment.getBody());
            sb.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * 패키지 내부에 있는 다이어그램 데이터모델의 리스트를 반환한다.
     * 
     * @param pkg
     * @param type
     * @return List<DataModel>
     */
    protected List<DataModel> createDiagramDataModelList(Package pkg, DiagramType type) {
        List<DataModel> diagramModelList = new ArrayList<DataModel>();
        DataModel diagramModel;
        EAnnotation annotation = pkg.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
        if (annotation != null) {
            Diagram diagram;
            for (EObject eobj : annotation.getContents()) {
                if (eobj.eClass() == UMLDiagramPackage.Literals.DIAGRAM) {
                    diagram = (Diagram) eobj;

                    if (diagram.getType() == type) {
                        diagramModel = createDiagramModel(diagram);
                        diagramModelList.add(diagramModel);
                    }
                }
            }
        }

        return diagramModelList;
    }

    /**
     * createDiagramModel
     *  
     * @param diagram
     * @return DataModel
     */
    protected static DataModel createDiagramModel(Diagram diagram) {

        DataModel diagramModel;
        diagramModel = new DataModel();
        setDataModel(diagramModel, UICoreConstant.REPORT__DIAGRAM_NAME, diagram.getName());
        setDataModel(diagramModel,
            UICoreConstant.REPORT__DIAGRAM_DOCUMENTATION,
            getCommentToString(diagram.getOwnedComments()));
        DiagramImageUtil diagramUtil = new DiagramImageUtil(diagram);
        DiagramImageUtil.execute(diagramUtil);

        Image img = diagramUtil.getDiagramImage();
        if (null == img) {
            return diagramModel;
        }
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

        img.dispose();
        loader = null;

        return diagramModel;
    }

    /**
     * 환경설정에서 "해당사항 없음"을 출력하는 지 여부를 체크하여 적절한 문자열을 리턴.
     * 
     * @param documentation
     * @return String
     */
    public static String applyPreference(String documentation) {

        String result = documentation;
        if (null == result) {
            result = UICoreConstant.EMPTY_STRING;
        }

        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        if (UICoreConstant.EMPTY_STRING.equals(result)
            && store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS)) {
            result = UMLMessage.LABEL_NO_APPLICABLE;
        }
        return result;
    }

    /**
     * 환경설정에서 "N/A"를 출력하는 지 여부를 체크하여 적절한 문자열을 리턴.
     * 
     * @param documentation
     * @return String
     */
    public static String applyPreferenceNA(String documentation) {

        String result = documentation;
        if (null == result) {
            result = UICoreConstant.EMPTY_STRING;
        }

        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        if (UICoreConstant.EMPTY_STRING.equals(result)
            && store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_DEFAULT_VALUE)) {
            result = UMLMessage.LABEL_NO_APPLICABLE_SHORT;
        }
        return result;
    }

    /**
     * 환경설정에서 유형을 경로 전체를 보여줄 지, 이름만 보여줄 지 설정 정보를 체크하여 적절한 문자열을 리턴.
     * 
     * @param typeName
     * @return String
     */
    public static String applyTypePreference(Type type) {

        String result;
        if (null == type) {
            return null;
        }

        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        if (store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL)) {
            result = type.getQualifiedName();
        } else {
            result = type.getName();
        }
        return result;
    }
}
