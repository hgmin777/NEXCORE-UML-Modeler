/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

/**
 * 엑셀 문서를 나타내는 클래스.
 * 
 * @author indeday
 * 
 */
public interface ExcelDocument {

    /**
     * 엑셀 Export 시 사용할 속성 또는 Import 한 결과 추출된 속성을 설정한다.
     * 
     * @param properties
     *            속성
     */
    public void setProperties(Properties properties);

    /**
     * @return 엑셀 Export 시 사용할 속성 또는 Import 한 결과 추출된 속성을 반환한다.
     */
    public Properties getProperties();

    /**
     * 엑셀 Export 시 사용할 속성 또는 Import 한 결과 추출된 사용자 객체 목록을 설정한다.
     * 
     * @param objects
     *            사용자 객체 목록
     */
    public void setUserObjects(List<?> objects);

    /**
     * @return 엑셀 Export 시 사용할 속성 또는 Import 한 결과 추출된 사용자 객체 목록을 반환한다.
     */
    public List<?> getUserObjects();

    /**
     * 엑셀 문서 내용을 주어진 파일에 출력한다.
     * 
     * @param fileName
     *            파일 이름
     * @throws IOException
     *             파일 저장 시 입출력 오류가 발생할 경우
     */
    public void writeTo(String fileName) throws IOException;

    /**
     * 엑셀 문서 내용을 주어진 파일에 출력한다. (파일 형식 지정)
     * 
     * @param fileName
     *            파일 이름
     * @param fileFormatType
     *            one of the FileFormatType values which specifies the file
     *            format.
     * @throws IOException
     *             파일 저장 시 입출력 오류가 발생할 경우
     */
    public void writeTo(String fileName, int fileFormatType) throws IOException;

    /**
     * 엑셀 문서 내용을 주어진 스트림에 출력한다.
     * 
     * @param out
     *            출력하고자 하는 스트림
     * @throws IOException
     *             스트림 출력 시 입출력 오류가 발생할 경우
     */
    public void writeTo(OutputStream out) throws IOException;

    /**
     * 엑셀 문서 내용을 주어진 스트림에 출력한다. (출력 형식 지정)
     * 
     * @param out
     *            출력하고자 하는 스트림
     * @param fileFormatType
     *            one of the FileFormatType values which specifies the file
     *            format.
     * @throws IOException
     *             스트림 출력 시 입출력 오류가 발생할 경우
     */
    public void writeTo(OutputStream out, int fileFormatType) throws IOException;

    /**
     * import한 엑셀 파일로 부터 총 행수를 반환한다.
     * 
     * 
     * @return int
     */
    public int getRowCount();

    /**
     * 
     * import 한 엑셀 Doc의 값으로 부터 objects를 생성한다.
     * 
     * @param objects
     * @return List<Person>
     */
    public List<?> generateObjects(List<?> objects);
}
