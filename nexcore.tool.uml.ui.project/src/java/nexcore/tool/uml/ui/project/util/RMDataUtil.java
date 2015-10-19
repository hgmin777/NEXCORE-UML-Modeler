/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.util;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.rmdata.RMData;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.util</li>
 * <li>설  명 : RMDataUtil</li>
 * <li>작성일 : 2010. 6. 11.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class RMDataUtil {

    /**
     * 패키지 경로로부터 RMData를 찾아서 반환
     * 
     * @param rootPackage
     * @return Object
     */
    public static RMData findRMData(Package rootPackage) throws Exception {
        RMData rmData = null;

        StringBuffer strResourceURI = new StringBuffer(rootPackage.eResource().getURI().toString().substring(0,
            rootPackage.eResource().getURI().toString().lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__SLASH
                + UMLMessage.LABEL_MODEL)));
        strResourceURI.append(UICoreConstant.PROJECT_CONSTANTS__SLASH).append(UMLMessage.LABEL_RMDATA);
        strResourceURI.append(UICoreConstant.PROJECT_CONSTANTS__SLASH)
            .append(UICoreConstant.PROJECT_CONSTANTS__RMDATA_DEFAULT_MODEL_NAME);

        org.eclipse.emf.common.util.URI resourceURI = org.eclipse.emf.common.util.URI.createURI(strResourceURI.toString());
        Resource resource = DomainRegistry.getUMLDomain().getRMDataResourceSet().getResource(resourceURI, true);

        if (resource != null) {
            rmData = (RMData) resource.getContents().get(0);
        }

        return rmData;
    }

}
