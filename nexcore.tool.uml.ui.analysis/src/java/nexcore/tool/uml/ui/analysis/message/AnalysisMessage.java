/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.resource</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.resource.message</li>
 * <li>설 명 : AnalysisMessage</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class AnalysisMessage {

    /********************************** LABEL *******************************************/

    /** add */
    public static final String LABEL_ADD = Messages.getString("LABEL.ADD");//$NON-NLS-1$

    /** all **/
    public static final String LABEL_ALL = Messages.getString("LABEL.ALL"); //$NON-NLS-1$

    /** attribute */
    public static final String LABEL_ATTRIBUTE = Messages.getString("LABEL.ATTRIBUTE"); //$NON-NLS-1$

    /**
     * LABLE_SAVETOIMAGE
     */
    public static final String LABLE_SAVETOIMAGE = Messages.getString("LABEL.SAVETOIMAGE"); //$NON-NLS-1$

    /********************************** MESSAGE *******************************************/

    /** Do you want to delete? : ${0} */
    public static final String MESSAGE_DELETE = Messages.getString("MESSAGE.DELETE"); //$NON-NLS-1$

    /**
     * 메시지를 조합하여 리턴한다. 파라메터로는 여러 개의 String을 넘길 수 있다. 첫 번째부터 ${0}, ${1}, ${2},
     * ... , ${n} 의 형태로 문자열 치환이 일어난다.
     * 
     * @param baseMessage
     * @param replaces
     * @return String
     */
    public static String getMessage(String baseMessage, String... replaces) {
        StringBuilder message = new StringBuilder(baseMessage);
        String indexString = ""; //$NON-NLS-1$
        int start, end;

        for (int i = 0; i < replaces.length; i++) {
            indexString = "${" + i + "}"; //$NON-NLS-1$ //$NON-NLS-2$
            while ((start = message.indexOf(indexString)) != -1) {
                end = start + indexString.length();
                message.replace(start, end, replaces[i]);
            }
        }

        return message.toString();
    }
}

