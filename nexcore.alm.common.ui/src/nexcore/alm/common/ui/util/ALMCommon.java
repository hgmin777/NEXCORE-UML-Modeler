/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.ui.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.util</li>
 * <li>설 명 : ALMCommon</li>
 * <li>작성일 : 2012. 3. 21.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ALMCommon {

    public static boolean isAliveAgent() {
        try {

            String agentSessionCheckURL = "http://localhost:18003/ncs/fd/agent/sessionCheck";
            URL url = new URL(agentSessionCheckURL);
            URLConnection con = url.openConnection();

            return checkInputStream(con.getInputStream());
        } catch (Exception e) {
            // ALMLogger.getLog(Activator.PLUGIN_ID).warn("Agent is not running.", e);
        }

        return false;
    }

    /**
     * agent로 부터 응답이 있는지 확인한다.
     * 
     * @param is
     *            void
     */
    private static boolean checkInputStream(InputStream is) {
        boolean alive = false;
        byte[] buf = new byte[1024];
        int len = -1;

        try {
            while ((len = is.read(buf, 0, buf.length)) != -1) {
                alive = true;
            }
        } catch (IOException e) {
            // ALMLogger.getLog(Activator.PLUGIN_ID).warn("Agent is not running.", e);
        }

        return alive;
    }

    /**
     * 라이선스가 등록 되었는지 체크 한다. 라이선스 등록 되었으면 Status.OK_STATUS 를 반환한다.
     * 
     * 
     * @param productCode
     *            ex) "/ncs/alm/uml"
     * @param version
     *            ex) "2.0.0"
     * @return
     * @throws FileNotFoundException
     *             라이선스가 등록되지 않은 경우
     * @throws Exception
     *             IStatus 라이선스에 만료되거나 문제가 있는 경우에 대한 예외
     */
    public static IStatus checkLicense(String productCode, String version) {
//        SystemConstants.NCS_PRODUCT_CODE = productCode;
//        SystemConstants.NCS_VERSION = version;
//
//        try {
//            NCPLicenseValidator.getInstance(productCode, version);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
//        }

        return Status.OK_STATUS;
    }

}
