/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.excel;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Excel 문서를 Import 또는 Export 하기 위한 클래스
 * 
 * @author indeday
 * 
 */
public abstract class ExcelDocumentBuilder {

    /**
     * 생성자 호출 금지.
     */
    protected ExcelDocumentBuilder() {
    }

    /**
     * 새로운 엑셀 문서를 생성한다. (Export 용)
     * 
     * @param templateFile
     *            엑셀 export용 템플릿 파일명
     * @param objects
     *            전달하고자 하는 자바 객체 목록
     * @return 생성된 엑셀 문서를 반환한다.
     */
    public abstract ExcelDocument newDocument(String templateFile, List<?> objects) throws ExcelException;

    /**
     * 새로운 엑셀 문서를 생성한다. (Export 용)
     * 
     * @param templateFile
     *            엑셀 export용 템플릿 파일명
     * @param properties
     *            전달하고자 하는 Property 목록
     * @param objects
     *            전달하고자 하는 자바 객체 목록
     * @return 생성된 엑셀 문서를 반환한다.
     */
    public abstract ExcelDocument newDocument(String templateFile, Properties properties, List<?> objects)
    throws ExcelException;

    /**
     * 
     * 새로운 엑셀 문서 객체를 생성한다. (Import 용)
     * 
     * @param url
     *            : xslt 템플릿 파일명
     * @param xlsInput
     *            : import 할 대상 엑셀 파일 스트림
     * @return
     * @throws ExcelException
     *             ExcelDocument
     */
    public abstract ExcelDocument newDocument(URL url, InputStream xlsInput) throws ExcelException;

    /**
     * 기존 작성된 엑셀 문서를 Import 하여, 각종 사용자 객체 또는 속성들을 추출한다.
     * 
     * @param templateFile
     *            엑셀 import용 템플릿 파일명
     * @param xlsInput
     *            엑셀 문서를 읽어들일 InputStream
     * @return Import 된 엑셀 문서
     */
    public abstract ExcelDocument newDocument(String templateFile, InputStream xlsInput) throws ExcelException;

    /**
     * URL 반환
     * 
     * 
     * @return Object
     */
    public abstract Object getURL();
}
