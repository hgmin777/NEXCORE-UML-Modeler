/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.util;

import java.util.Properties;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.rm.core</li>
 * <li>서브 업무명 : nexcore.tool.rm.core.util</li>
 * <li>설  명 : SystemUtil</li>
 * <li>작성일 : 2009. 8. 31.</li>
 * <li>작성자 : 04898</li>
 * </ul>
 */
public class SystemUtil {
    /** OS : windows 계열 */
    public final static int OS_WINDOWS = 1;

    /** OS : Mac 계열 */
    public final static int OS_MAC = 2;

    /** OS : 기타 계열 */
    public final static int OS_OTHER = 3;

    /** 독립 프로그램으로 실행 : 로컬 Embedded DB 사용 */
    public final static int RUN_TYPE_STANDALONE = 1;

    /** 협업 프로그램을 실행 : 네트워크 DB 사용 */
    public final static int RUN_TYPE_NETWORK = 2;

    public static int getOsType() {
        String osName = System.getProperty("os.name");//$NON-NLS-1$

        if (osName == null || "".equals(osName)) {//$NON-NLS-1$
            return SystemUtil.OS_OTHER;
        } else if (osName.startsWith("Windows")) { //$NON-NLS-1$
            return SystemUtil.OS_WINDOWS;
        } else if (osName.startsWith("Mac")) { //$NON-NLS-1$
            return SystemUtil.OS_MAC;
        } else {
            return SystemUtil.OS_OTHER;
        }
    }

    public static String getUserHome() {
        String userHome = null;

        userHome = System.getProperty("user.home"); //$NON-NLS-1$

        return (userHome == null) ? "c:\\" : userHome; //$NON-NLS-1$;
    }

    /**
     * OS별로 파일 구분자를 리턴한다.
     * 주의 : Eclipse 실행 중에만 사용할 수 있는 메소드임
     * 
     * <pre>
     * String fileSeparator = FileUtil.getFileSeparator();
     * </pre>
     * 
     * @return String : 파일 구분자
     */
    public synchronized static String getOsFileSeparator() {
        Properties prop = System.getProperties();

        return prop.getProperty("file.separator"); //$NON-NLS-1$
    }

    /**
     * 현재 실행되고 있는 OS에 적합한 Line separator를 리턴한다.
     * 주의 : Eclipse 실행 중에만 사용할 수 있는 메소드임 
     * @return String
     */
    public static String getOsLineSeparator() {
        return System.getProperty("line.separator"); //$NON-NLS-1$
    }

    public static int getRunType() {
        String type = System.getProperty("NEXCORE_RM_RUN_TYPE"); //$NON-NLS-1$
        if ("STANDALONE".equals(type)) { //$NON-NLS-1$
            return RUN_TYPE_STANDALONE;
        } else {
            return RUN_TYPE_NETWORK;
        }
    }
}
