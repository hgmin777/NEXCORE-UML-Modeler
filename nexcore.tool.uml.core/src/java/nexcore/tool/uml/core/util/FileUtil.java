/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.FileChannel;

import nexcore.alm.common.exception.BaseException;
import nexcore.alm.common.exception.BaseRuntimeException;
import nexcore.tool.uml.core.log.Log;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설  명 : FileUtil</li>
 * <li>작성일 : 2009. 7. 14.</li>
 * <li>작성자 : 이종원 </li>
 * </ul>
 */
public class FileUtil {

    /**
     * 파일을 읽어 byte배열에 저장하여 반환한다.
     * 
     * @param file
     * @return byte[]
     * @throws IOException
     */
    public static byte[] readBytesToFile(File file) throws IOException {

        int fileLength = (int) file.length();
        byte[] bytes = new byte[fileLength];

        FileInputStream fileInputStream = null;

        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);
        fileInputStream.close();

        return bytes;

    }

    /**
     * 데이터를 byte 배열을 넘겨받아 파일에 데이터를 쓴다.
     * 
     * @param bytes
     * @param file
     * @throws IOException void
     */
    public static void writeFileToByte(byte[] bytes, File file) throws IOException {

        FileOutputStream fileOutputStream = null;

        fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

    }

    /**
     * 넘겨받은 스트링(파일경로)에서 실제 파일이름을 추출한다.
     * 
     * @param string
     * @return String
     */
    public static String createFilename(String string) {
        IPath path = new Path(string);

        return path.lastSegment();
    }

    /**
     * 넘겨받은 스트링(파일경로)에서 실제 파일이름을 제외한 경로를 추출한다.
     * 
     * @param string
     * @return String
     */
    public static String createFilePath(String string) {
        IPath path = new Path(string);

        path = path.removeLastSegments(1);

        return path.toOSString();
    }

    /**
     * 넘겨받은 파일 경로와 파일 이름을 이용해 파일의 전체 경로(경로+이름)을 만든다.
     * 
     * @param filePath
     * @param filename
     * @return String
     */
    public static String createFullPath(String filePath, String filename) {
        if (filePath.length() == 2 && filePath.charAt(1) == Path.DEVICE_SEPARATOR) {
            filePath += java.io.File.separator;
        }

        IPath fullPath = new Path(filePath);

        fullPath = fullPath.append(filename);

        return fullPath.toOSString();
    }

    /**
     * 디렉토리를 만든다.
     * 
     * @param file
     * @return boolean
     */
    public static boolean createDirectory(File file) {
        return file.mkdir();
    }

    /**
     * 파일을 복사한다.
     * 
     * @param sourceFile
     * @param targetFile
     *            void
     */
    public static void copyFile(File sourceFile, File targetFile) {
        try {
            FileInputStream inputStream = new FileInputStream(sourceFile);

            if (!targetFile.isFile()) {
                File fParent = new File(targetFile.getParent());
                if (!fParent.exists()) {
                    fParent.mkdir();
                }
                targetFile.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(targetFile);
            FileChannel inFileChannel = inputStream.getChannel();
            FileChannel outFileChannel = outputStream.getChannel();

            long size = inFileChannel.size();
            inFileChannel.transferTo(0, size, outFileChannel);

            outFileChannel.close();
            inFileChannel.close();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
    }
    
    /**
     * 스트림 데이터를 받아서 파일로 저장(복사)한다.
     * 
     * <pre>
     * FileInputStream in = new FileInputStream(&quot;c:/data.txt&quot;);
     * FileUtil.copy(in, &quot;c:/backup.txt&quot;, true);
     * 
     * // 결과 : &quot;c:/data.txt&quot; 가 &quot;c:/backup.txt&quot; 으로 복사됨.
     * </pre>
     * 
     * @param in
     *            InputStream : 복사할 원본 데이터가 있는 스트림
     * @param filePath
     *            String : 복사할 대상 파일 전체 경로
     * @param isForceWrite
     *            boolean : 파일이 존재하면 덮어쓸지 여부(true/false)
     * @throws BaseException
     * @exception BaseRuntimeException
     *                : IO 과정에서 익셉션이 발생한 경우
     */
    public synchronized static void copy(InputStream in, String filePath, boolean isForceWrite) throws BaseException {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File file = null;
        BufferedInputStream bin = null;

        try {
            file = new File(filePath);

            bin = new BufferedInputStream(in);

            if (file.exists() && !isForceWrite) {
                return;
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            int c;
            while ((c = bin.read()) != -1) // 파일의 끝이 아닌 동안 반복
            {
                bos.write((byte) c);
            }

            bos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // throw new BaseRuntimeException("SKE2601", e);
        } catch (IOException e) {
            throw new BaseException(e);
            // throw new BaseRuntimeException("SKE2604", e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                throw new BaseException(e);
            }
        }
    }
    
    /**
     * 파일 경로 뒤에 파일 구분자('/' 혹은 '\')가 없으면 파일 구분자를 붙여서 리턴한다.
     * 
     * <pre>
     * String filePath = &quot;c:/test&quot;;
     * filePath = FileUtil.chkSeparator(filePath);
     * 
     * // 결과 : filePath = &quot;c:/test/&quot;
     * </pre>
     * 
     * @param path
     *            String : 파일경로
     * @return String : 수정된 파일 경로
     * @throws BaseRuntimeException
     *             : path가 null인 경우
     */
    public synchronized static String chkSeparator(String path) {
        if (path == null) {
            return null;
            // throw new BaseRuntimeException("SKE2610"); // 입력값이 null입니다.
        }

        return (path.endsWith(File.separator) || path.endsWith("/")) ? path : path + File.separator; //$NON-NLS-1$
    }

    /**
     * 파일이나 디렉토리가 존재하는지 확인한다.
     * 
     * <pre>
     * boolean isExist = FileUtil.isExist(&quot;c:/test.txt&quot;);
     * </pre>
     * 
     * @param fileName
     *            String : 존재 여부를 체크할 대상 파일명의 전체 경로
     * @return boolean : 파일 존재 여부 (true.존재 false.존재안함)
     */
    public synchronized static boolean isExist(String fileName) {
        boolean isExist = true;

        try {
            File f = new File(fileName);
            isExist = f.exists();
        } catch (Exception e) {
            isExist = false;
        }

        return isExist;
    }
    
    /**
     * 해당 경로와 파일명을 인자로 받아서 해당 경로에 파일명이 존재하는지 확인한다.
     * 
     * <pre>
     * boolean isExist = FileUtil.isExist(&quot;c:/&quot;, &quot;test.txt&quot;);
     * </pre>
     * 
     * @param path
     *            String : 체크할 대상 경로명
     * @param fileName
     *            String : 존재 여부를 체크할 대상 파일명
     * @return boolean : 파일 존재 여부 (true.존재 false.존재안함)
     */
    public synchronized static boolean isExist(String path, String fileName) {
        boolean i = false;

        try {
            File f = new File(path, fileName);
            i = f.exists();
        } catch (Exception e) {

            i = false;
        }
        return i;
    }
    
    /**
     * copy
     *  
     * @param file
     * @param path
     * @param cp
     * @param isSaveOriginal void
     */
    public synchronized static void copy(File file, String path, String cp, boolean isSaveOriginal) {
        String fileName2 = ""; //$NON-NLS-1$
        String fileName;

        try {
            // 한글 파일의 경우 아래와 같이 해야 한글이 깨지지 않는다.
            // fileName = new
            // String(di.getOldFileName().getBytes("euc-kr"),"8859_1");
            fileName = file.getName();

            // 파일패스 뒤에 separator가 있는지 확인한다.
            path = chkSeparator(path);

            // 복사할 곳에 디렉토리가 있는지 확인한다.
            if (!isExist(path)) {
                // 디렉토리가 없으면 만든다.
                makeDirectory(path);
            }

            if (isExist(path, fileName)) {
                if (isSaveOriginal) {
                    fileName2 = copyFileWithIndex(path, fileName);
                    while (isExist(path, fileName2)) {
                        // 파일 이름이 겹치면 계속해서 새 이름을 만든다.
                        fileName2 = copyFileWithIndex(path, fileName2);
                    }
                } else
                    fileName2 = fileName;
            } else {
                fileName2 = fileName;
            }

            // 복사될 파일 객체를 만든다.
            // File file2 = new File(path +fileName);

            FileInputStream filestream = new FileInputStream(file);
            FileOutputStream outStream = new FileOutputStream(path + fileName2);

            byte[] b = new byte[1024];
            int numRead = filestream.read(b);

            while (numRead != -1) {
                outStream.write(b, 0, numRead);
                numRead = filestream.read(b);
            }

            outStream.flush();
            outStream.close();
            if (filestream != null)
                filestream.close();

            // 이동일경우 기존의 파일을 삭제한다.
            if (cp.equals("mv")) { //$NON-NLS-1$
                file.delete();
            }
        } catch (Exception e) {

        } finally {}
    }
    
    /**
     * 해당 디렉토리에 같은 이름이 있을 경우 파일에 인덱스를 붙여서 저장한다. 파일명 뒤에 _002에서 _999 까지를 붙인다.
     * 
     * <pre>
     * FileUtil.copyFileWithIndex(&quot;c:/data&quot;, &quot;text.txt&quot;);
     * </pre>
     * 
     * @param path
     *            String : 이름을 변경할 파일의 전체 경로
     * @param fileName
     *            String : 이름을 변경할 파일명
     * @return String : 변경된 파일명
     */
    public synchronized static String copyFileWithIndex(String path, String fileName) {
        String newName = ""; //$NON-NLS-1$
        String tt = "000"; //$NON-NLS-1$
        int i = 0;
        int j = 0;

        try {
            i = fileName.lastIndexOf("_"); //$NON-NLS-1$
            j = fileName.lastIndexOf("."); //$NON-NLS-1$

            if (fileName.lastIndexOf("_") < 0) { //$NON-NLS-1$
                // 최초에는 001을 붙여준다.
                newName = fileName.substring(0, j) + "_002" //$NON-NLS-1$
                    + fileName.substring(j);
            } else {
                // 버젼 숫자를 잘라낸다.
                int k = Integer.parseInt(fileName.substring(i + 1, j));
                k += 1; // 버젼 숫자에 1을 더한다.
                tt = tt + Integer.toString(k);
                // 자리수를 맞춰서 버젼을 만든다.
                tt = "_" + tt.substring(tt.length() - 3); //$NON-NLS-1$

                // 새 파일 이름을 만든다.
                newName = fileName.substring(0, i) + tt + fileName.substring(j);
            }

        } catch (Exception e) {
            try {
                // 새 파일 이름을 만든다.
                newName = fileName.substring(0, j) + "_002" //$NON-NLS-1$
                    + fileName.substring(j);
            } catch (Exception e1) {

                newName = fileName;
            }
        }
        return newName;
    }
    
    /**
     * 디렉토리를 새로 생성한다.
     * 
     * <pre>
     * FileUtil.makeDirectory(&quot;c:/data/backup&quot;);
     * 
     * // 결과 : &quot;c:/data/backup&quot; 디렉토리가 새로 생성됨.
     * </pre>
     * 
     * @param dirPath
     *            새로 생성할 디렉토리의 전체 경로
     * @return boolean : 디렉토리 생성 여부 (true.생성 false.생성못함)
     */
    public synchronized static boolean makeDirectory(String dirPath) {
        boolean isOk = true;
        try {
            File file = new File(dirPath);

            if (!file.exists()) {
                isOk = file.mkdirs();
            }
        } catch (Exception e) {

            isOk = false;
        }

        return isOk;
    }
    
    /**
     * 디렉토리를 삭제한다. 주의 : 하위 디렉토리까지 모두 삭제된다.
     * 
     * <pre>
     * FileUtil.deleteDirectory(&quot;c:/test&quot;);
     * 
     * // 결과 : &quot;c:/test&quot; 가 삭제됨.
     * </pre>
     * 
     * @param path
     *            String : 삭제할 디렉토리 전체 경로
     * @return boolean : 파일 삭제 성공 여부
     */
    public synchronized static boolean deleteDirectory(String path) {
        boolean isOk = false;
        try {
            File dir = new File(path);

            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDirectory(new File(dir, children[i]).getAbsolutePath());
                    if (!success) {
                        return false;
                    }
                }
            }

            // The directory is now empty so delete it
            return dir.delete();
        } catch (Exception e) {
            isOk = false;
        }
        return isOk;
    }
    
    /**
     * copyDirectory
     *  
     * @param oldPath
     * @param newPath
     * @return boolean
     */
    public synchronized static boolean copyDirectory(String oldPath, String newPath) {

        File file = new File(oldPath);

        if (file.exists()) {
            if (file.isDirectory()) {
                String newFilename = newPath + File.separator + file.getName();
                File newFile = new File(newFilename);
                newFile.mkdirs();

                File[] orgFiles = file.listFiles();

                for (File orgFile : orgFiles) {
                    copyDirectory(orgFile.getAbsolutePath(), newFile.getAbsolutePath());
                }
            } else {
                try {
                    File newFile = new File(newPath + File.separator + file.getName());
                    try {
                        newFile.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    copy(file.getAbsolutePath(), newFile.getAbsolutePath());
                } catch (BaseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
    
    /**
     * 입력 파일을 복사한다.
     * 
     * <pre>
     * FileUtil.copy(&quot;c:/test.txt&quot;, &quot;d:/test2.txt&quot;);
     * 
     * // 결과 : &quot;c:/test.txt&quot; 가 &quot;d:/test2.txt&quot; 으로 복사됨.
     * </pre>
     * 
     * @param srcPath
     *            String : 복사할 원본 파일 전체 경로
     * @param desPath
     *            String : 복사할 대상 파일 전체 경로
     * @throws BaseException
     * @throws BaseRuntimeException
     *             : 지정한 파일이 없거나, IO 과정에서 익셉션이 발생한 경우
     */
    public synchronized static void copy(String srcPath, String desPath) throws BaseException {
        long size = 0;

        FileChannel fcIn = null;
        FileChannel fcOut = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcPath); // 원본 파일
            fos = new FileOutputStream(desPath); // 대상 파일

            fcIn = fis.getChannel();
            fcOut = fos.getChannel();

            size = fcIn.size();

            fcIn.transferTo(0, size, fcOut);

            fcOut.close();
            fcIn.close();

            fos.close();
            fis.close();
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } catch (Exception e) {
            throw new BaseException(e);
        } finally {
            try {
                if (fcOut != null) {
                    fcOut.close();
                }
                if (fcIn != null) {
                    fcIn.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                throw new BaseException(e);
                // Log.logDebugData("FileUtil", "copy()", "파일 닫기를 실패했습니다.");
            }
        }
    }
    
    /**
     * 인코딩을 UTF-8로 지정하여 파일 내용을 2048바이트씩 잘라서 읽어온 후 하나의 문자열로 조립하여 리턴한다.<br/>
     * 파일 끝에 "\r\n"이 붙는다.
     * 
     * <pre>
     * File file = new File(filePath);
     * 
     * String content = FileUtil.read(file);
     * </pre>
     * 
     * @param file
     *            파일 명
     * @return String 결과 문자열
     */
    public static synchronized String read(File file) {
        return read(file, "UTF-8");
    }

    /**
     * 인코딩을 지정하여 파일 내용을 2048바이트씩 잘라서 읽어온 후 하나의 문자열로 조립하여 리턴한다.<br/>
     * 파일 끝에 "\r\n"이 붙는다.
     * 
     * <pre>
     * File file = new File(filePath);
     * 
     * String content = FileUtil.read(file, &quot;UTF-8&quot;);
     * </pre>
     * 
     * @param file
     *            파일명
     * @param encoding
     *            인코딩 종류
     * @return String 결과 문자열
     */
    public static synchronized String read(File file, String encoding) {
        if (!file.exists())
            return ""; //$NON-NLS-1$
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            Reader reader = new BufferedReader(new InputStreamReader(stream, encoding)); //$NON-NLS-1$
            StringBuffer result = new StringBuffer(2048);
            char[] buf = new char[2048];
            while (true) {
                int count = reader.read(buf);
                if (count < 0)
                    break;
                result.append(buf, 0, count);
            }
            return result.toString();
        } catch (Exception e) {
            Log.error(e);
            return ""; //$NON-NLS-1$
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                Log.error(e);
                return ""; //$NON-NLS-1$
            }
        }
    }
}
