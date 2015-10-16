/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.registry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.osgi.framework.Bundle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설  명 : UMLUiCorePrecedingInitializer</li>
 * <li>작성일 : 2010. 5. 17.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLUiCorePrecedingInitializer extends AbstractPrecedingInitializer {

    /** UML 코어 프로파일 목록 */
    private List<Object> umlCoreProfileList;

    /**
     * @see nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer#setInitialInformation()
     */
    @Override
    public void setInitialInformation() {
        // TransactionalEditingDomain 에서 관리되는 ResourceSet URIConverter 의 맵을
        // 가져온다.
        Map<URI, URI> uriMap = DomainRegistry.getEditingDomain().getResourceSet().getURIConverter().getURIMap();

        Bundle bundle = UiCorePlugin.getDefault().getBundle();

        Path profileRelativePath = new Path(UICoreConstant.UML_CORE_PROFILE_RELATIVE_PATH);
        URL profilesURL = FileLocator.find(bundle, profileRelativePath, null);

        String umlProfilePathMapURI = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

        try {
            umlProfilePathMapURI = FileLocator.toFileURL(profilesURL).getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // UML Core Profile Pathmap이 존재하지 않는다면 경로 맵에 추가한다.
        if (!uriMap.containsKey(ManagerConstant.NEXCORE_UML_PROFILES_PATHMAP)) {
            uriMap.put(URI.createURI(ManagerConstant.NEXCORE_UML_PROFILES_PATHMAP),
                URI.createFileURI(umlProfilePathMapURI));
        }

        loadUMLCoreProfile();
    }

    /**
     * @see nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer#getFeedback()
     */
    @Override
    public Object getFeedback() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer#getFeedbacks()
     */
    @Override
    public List<Object> getFeedbacks() {
        return umlCoreProfileList;
    }

    /**
     * UML Core 프로파일 적재하는 메소드 void
     */
    private void loadUMLCoreProfile() {
        Resource resource = null;
        URI profileURI = null;
        Profile profile = null;

        if (umlCoreProfileList == null) {
            umlCoreProfileList = new ArrayList<Object>();
        } else {
            umlCoreProfileList.clear();
        }

        // UML 프로파일 로딩
        for (int profileNameIdx = 0; profileNameIdx < ManagerConstant.UML_PROFILES_NAMES.length; profileNameIdx++) {
            profileURI = URI.createURI(ManagerConstant.NEXCORE_UML_PROFILES_PATHMAP
                + ManagerConstant.UML_PROFILES_NAMES[profileNameIdx] + ManagerConstant.DOT
                + UMLResource.PROFILE_FILE_EXTENSION);

            resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(profileURI, true);

            if (!resource.isLoaded()) {
                try {
                    resource.load(DomainUtil.getLoadOptions());
                } catch (IOException e) {}
            }

            profile = DomainUtil.getUMLProfileRoot(resource);

            umlCoreProfileList.add(profile);
        }

    }

}
