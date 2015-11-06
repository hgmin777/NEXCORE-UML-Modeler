/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.util</li>
 * <li>설 명 : MessageDigestUtil</li>
 * <li>작성일 : 2009. 10. 28.</li>
 * <li>작성자 : 05690</li>
 * </ul>
 */
public class MessageDigestUtil {

    final static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    final static int szBuf = 1024; // buffer size used in reading file

    /**
     * 파일의 해쉬코드 값을 구한다. ex) String hash = MessageDigestUtil.hash("MD5",
     * file.getAbsolutePath());
     * 
     * @param algo
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     *             String
     */
    public static String hash(String algo, String file) throws NoSuchAlgorithmException, IOException {
        int num = szBuf;
        byte[] buf = new byte[szBuf];

        MessageDigest digester = MessageDigest.getInstance(algo);
        FileInputStream fis = new FileInputStream(file);
        DigestInputStream dis = new DigestInputStream(fis, digester);
        // read the stream to calculate the hash.
        while (num == szBuf) {
            num = dis.read(buf, 0, szBuf);
        }

        byte[] digest = digester.digest();

        char[] msg = new char[digest.length << 1];
        for (int i = 0; i < digest.length; i++) {
            msg[i << 1] = hexChar[(digest[i] & 0xf0) >>> 4];
            msg[(i << 1) + 1] = hexChar[digest[i] & 0x0f];
        }
        return new String(msg);
    }
}
