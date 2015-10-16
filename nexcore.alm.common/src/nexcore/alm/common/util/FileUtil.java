/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;

import nexcore.alm.common.exception.BaseException;
import nexcore.alm.common.log.Logger;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.rm.ui</li>
 * <li>서브 업무명 : nexcore.tool.rm.ui</li>
 * <li>설 명 : FileUtil</li>
 * <li>작성일 : 2009. 7. 14.</li>
 * <li>작성자 : 이종원</li>
 * </ul>
 */

public class FileUtil {

    private static final Logger logger = new Logger(FileUtil.class);

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
     * @throws IOException
     *             void
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
     * 폴더를 복사한다.
     * 
     *  
     * @param oldPath
     * @param newPath
     * @return boolean
     */
    public static boolean copyDirectory(String oldPath, String newPath) {
        File file = new File(oldPath);

        if (file.exists()) {
            if (file.isDirectory()) {
                String newFilename = newPath + File.separator + file.getName();
                File newFile = new File(newFilename);
                newFile.mkdirs();

                File[] orgFiles = file.listFiles();

                for (File orgFile : orgFiles)
                    copyDirectory(orgFile.getAbsolutePath(), newFile.getAbsolutePath());
            } else {
                try {
                    File newFile = new File(newPath + File.separator + file.getName());
                    try {
                        newFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    copy(file.getAbsolutePath(), newFile.getAbsolutePath());
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    /**
     * 파일을 복사한다.
     * 
     * 
     * @param srcPath
     * @param desPath
     * @throws BaseException
     *             void
     */
    public static void copy(String srcPath, String desPath) throws BaseException {
        long size = 0L;

        FileChannel fcIn = null;
        FileChannel fcOut = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(desPath);

            fcIn = fis.getChannel();
            fcOut = fos.getChannel();

            size = fcIn.size();

            fcIn.transferTo(0L, size, fcOut);

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
                if (fis != null)
                    fis.close();
            } catch (Exception e) {
                throw new BaseException(e);
            }
        }
    }

    /**
     * 폴더를 복사한다.
     * 
     *  
     * @param oldPath
     * @param newPath
     * @param isSaveOriginal
     * @return boolean
     */
    public static boolean copyDirectory(String oldPath, String newPath, boolean isSaveOriginal) {
        String newDirectory = "";
        boolean ismove = true;

        String fileSeparator = File.separator;

        if (oldPath.indexOf(File.separator) != -1)
            fileSeparator = File.separator;
        else if (oldPath.indexOf("/") != -1)
            fileSeparator = "/";
        else {
            return false;
        }

        int i = 0;
        try {
            if (isExist(oldPath)) {
                i = oldPath.lastIndexOf(fileSeparator);

                newDirectory = oldPath.substring(i + 1);

                File oldFile = new File(oldPath);

                newPath = chkSeparator(newPath);
                newPath = newPath + newDirectory;

                makeDirectory(newPath);

                moveDirectory(oldFile.listFiles(), newPath, newDirectory, "cp", isSaveOriginal);
            }
        } catch (Exception localException) {
            ismove = false;
        }
        return ismove;
    }

    /**
     * 폴더를 이동한다.
     * 
     *  
     * @param oldPath
     * @param newPath
     * @param isSaveOriginal
     * @return boolean
     */
    public static boolean moveDirectory(String oldPath, String newPath, boolean isSaveOriginal) {
        String newDirectory = "";
        boolean ismove = true;

        String fileSeparator = File.separator;

        if (oldPath.indexOf(File.separator) != -1)
            fileSeparator = File.separator;
        else if (oldPath.indexOf("/") != -1)
            fileSeparator = "/";
        else {
            return false;
        }

        int i = 0;
        try {
            if (isExist(oldPath)) {
                i = oldPath.lastIndexOf(fileSeparator);

                newDirectory = oldPath.substring(i + 1);

                File oldFile = new File(oldPath);

                newPath = chkSeparator(newPath);
                newPath = newPath + newDirectory;

                makeDirectory(newPath);

                moveDirectory(oldFile.listFiles(), newPath, newDirectory, "mv", isSaveOriginal);

                deleteDirectory(oldPath);
            }
        } catch (Exception localException) {
            ismove = false;
        }
        return ismove;
    }

    /**
     * 폴더를 이동한다.
     * 
     *  
     * @param fl
     * @param target
     * @param root
     * @param cp
     * @param isSaveOriginal
     * @return boolean
     */
    private static boolean moveDirectory(File[] fl, String target, String root, String cp, boolean isSaveOriginal) {
        String currentName = "";
        String newp = "";
        String fileName = "";
        int idx = 0;

        boolean isMove = true;
        try {
            for (int j = 0; j < fl.length; ++j) {
                if (fl[j].isDirectory()) {
                    currentName = fl[j].getAbsolutePath();
                    idx = currentName.indexOf(root);
                    currentName = currentName.substring(idx + root.length() + 1);

                    target = chkSeparator(target);
                    newp = target + currentName;

                    isMove = makeDirectory(newp);

                    moveDirectory(fl[j].listFiles(), target, root, cp, isSaveOriginal);

                    if (cp.equals("mv"))
                        fl[j].delete();
                } else {
                    fileName = fl[j].getName();

                    idx = fl[j].getAbsolutePath().indexOf(root);
                    String temppath = fl[j].getAbsolutePath().substring(idx + root.length(),
                        fl[j].getAbsolutePath().length() - (fileName.length() + 1));

                    if (cp.equals("cp")) {
                        copy(fl[j], target + temppath, cp, isSaveOriginal);
                    } else {
                        moveFile(fl[j], target + temppath, fileName);
                    }
                }
            }

            isMove = true;
        } catch (Exception localException) {
            isMove = false;
        }

        return isMove;
    }

    /**
     * 새로운 파일 명으로 특정 위치에 복사한다.
     * 
     *  
     * @param file
     * @param path
     * @param cp
     * @param newId void
     */
    public static void copyRename(File file, String path, String cp, String newId) {
        try {
            path = chkSeparator(path);

            if (!(isExist(path))) {
                makeDirectory(path);
            }

            FileInputStream filestream = new FileInputStream(file);
            FileOutputStream outStream = new FileOutputStream(path + newId);

            byte[] b = new byte[1024];
            int numRead = filestream.read(b);

            while (numRead != -1) {
                outStream.write(b, 0, numRead);
                numRead = filestream.read(b);
            }

            outStream.flush();
            outStream.close();
            if (filestream != null) {
                filestream.close();
            }

            if (cp.equals("mv"))
                file.delete();
        } catch (Exception localException) {}
    }

    /**
     * 폴더를 삭제한다.
     * 
     *  
     * @param path
     * @return boolean
     */
    public static boolean deleteDirectory(String path) {
        boolean isOk = false;
        try {
            File dir = new File(path);

            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; ++i) {
                    boolean success = deleteDirectory(new File(dir, children[i]).getAbsolutePath());
                    if (!(success)) {
                        return false;
                    }
                }

            }

            return dir.delete();
        } catch (Exception localException) {
            isOk = false;
        }
        return isOk;
    }

    /**
     * 파일을 이동한다.
     * 
     *  
     * @param file
     * @param path
     * @param fileName void
     */
    public static void moveFile(File file, String path, String fileName) {
        String fileName2 = "";
        try {
            path = chkSeparator(path);

            if (!(isExist(path))) {
                makeDirectory(path);
            }

            if (isExist(path, fileName)) {
                fileName2 = copyFileWithIndex(path, fileName);

                while (isExist(path, fileName2)) {
                    fileName2 = copyFileWithIndex(path, fileName2);
                }
            } else {
                fileName2 = fileName;
            }

            File file2 = new File(path, fileName2);

            file.renameTo(file2);
        } catch (Exception localException) {}
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
            logger.error(e);
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
            logger.error(e);
            return ""; //$NON-NLS-1$
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                logger.error(e);
                return ""; //$NON-NLS-1$
            }
        }
    }

    /**
     * 스트링내의 특정문자를 원하는 문자열로 Swap 시킨다. <br>
     * 하지만 이 메소드를 사용하는 것은 추천하지 않고 정규식을 사용하는 것을 권장한다. 기존 코드와의 호환성 문제로 제거하지 않고 남겨둔
     * 
     * 
     * @param input
     *            치환시킬 입력 문자열
     * @param oldStr
     *            치환될 대상인 부분 문자열
     * @param newStr
     *            치환할 부분 문자열
     * @return String input의 oldStr이 newStr로 치환된 출력 문자열
     */
    public static String replaceAll(String input, String oldStr, String newStr) {
        try {
            int startIdx = 0;
            int idx = 0;
            int length = oldStr.length();

            StringBuffer sb = new StringBuffer((int) (input.length() * 1.2));
            while ((idx = input.indexOf(oldStr, startIdx)) >= 0) {
                sb.append(input.substring(startIdx, idx));
                sb.append(newStr);
                startIdx = idx + length;
            }
            sb.append(input.substring(startIdx));
            return sb.toString();
        } catch (Exception e) {
            ;
        }

        return oldStr;
    }

    /**
     * 입력 문자열이 null 혹은 빈 문자열인지 확인한다. 만일 해당하는 경우 대체 문자열을 반납하고, 아닌 경우에는 입력 문자열을
     * 그대로 리턴한다.
     * 
     * @param source
     *            입력 문자열
     * @param alernative
     *            입력 문자열이 null 혹은 빈 문자열일 경우 대체할 문자열
     * @return String 입력 문자열이 null 혹은 빈 문자열일 경우 대체 문자열, 아닌 경우엔 입력 문자열.
     */
    public static String nullStringCheck(String source, String alernative) {
        if (source == null || source.length() == 0) {
            return alernative;
        }
        return source;
    }

    /**
     * 파일을 새로 생성하여 content 인자의 내용을 파일에 쓴다. 기본적으로 utf-8 인코딩에 윈도 계열로 설정되어 있다.
     * 
     * <pre>
     * FileUtil.write(&quot;C:/test.xml&quot;, &quot;&lt;?xml ...&quot;);
     * </pre>
     * 
     * @param fileLocation
     *            파일을 생성할 경로. 경로가 존재하지 않으면 새로 생성한다.
     * @param content
     *            파일에 쓸 문자열
     * @throws BaseException
     *             파일이 없거나 입출력에 문제가 발생한 경우 던진다.
     */
    public synchronized static void write(String fileLocation, String content) {
        write(fileLocation, content, false);
    }

    /**
     * 파일을 새로 생성하여 content 인자의 내용을 파일에 쓴다. 기본적으로 인코딩은 utf-8을 사용한다.
     * 
     * <pre>
     * FileUtil.write(tempFileLocation, source, true);
     * </pre>
     * 
     * @param fileLocation
     *            파일을 생성할 경로. 경로가 존재하지 않으면 새로 생성한다.
     * @param content
     *            파일에 쓸 문자열
     * @param isUnixFile
     *            true이면 Unix 파일용으로 작성한다(라인구분자가 "\n"을 사용) false인 경우, 해당 시스템의
     *            라인구분자를 사용한다.
     * @throws BaseException
     *             파일이 없거나 입출력에 문제가 발생한 경우 던진다.
     */
    public synchronized static void write(String fileLocation, String content, boolean isUnixFile) {
        write(fileLocation, content, "UTF-8", isUnixFile);
    }

    /**
     * 파일을 새로 생성하여 content 인자의 내용을 파일에 쓴다. 입력의 맨 뒤에는 '\n' (Unix에선 '\n\r')이 추가된다.
     * 
     * <pre>
     * FileUtil.write(tempFileLocation, source, &quot;utf-8&quot;, true);
     * </pre>
     * 
     * @param fileLocation
     *            파일을 생성할 경로. 경로가 존재하지 않으면 새로 생성한다.
     * @param content
     *            파일에 쓸 문자열
     * @param encoding
     *            문자열 출력 인코딩 셋
     * @param isUnixFile
     *            true이면 Unix 파일용으로 작성한다(라인구분자가 "\n"을 사용) false인 경우, 해당 시스템의
     *            라인구분자를 사용한다.
     * @throws BaseException
     *             파일이 없거나 입출력에 문제가 발생한 경우 던진다.
     * 
     */
    public synchronized static void write(String fileLocation, String content, String encoding, boolean isUnixFile) {
        StringReader stringReader = new StringReader(content);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        String s = "";
        BufferedWriter bufferedWriter = null;
        try {
            // 해당 폴더가 없으면 폴더를 생성한다.
            String folderLocation = getFilePath(fileLocation);
            if (new File(folderLocation).exists() == false) {
                makeDirectory(folderLocation);
            }

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation), encoding));

            while ((s = bufferedReader.readLine()) != null) {
                bufferedWriter.write(s);
                if (isUnixFile) {
                    bufferedWriter.write('\n');
                } else {
                    bufferedWriter.newLine();
                }
            }
            stringReader.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (stringReader != null) {
                stringReader.close();
            }
            if (bufferedReader != null) {

                try {
                    bufferedReader.close();
                } catch (IOException e) {

                    logger.error(e);
                }

            }
            if (bufferedWriter != null) {

                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    logger.error(e);
                }

            }
        }
    }

    /**
     * Absolute Path에서 FileName 을 빼고 경로만 반환한다. <br>
     * Windows 와 Unix 계열에서의 디렉토리 구분자가 다르므로, 동일한 데이터에 대해 다른 결과를 반환하므로, 개발/운용시 주의
     * 하기바람. <br>
     * 구분자는 System.getProperty("file.separator") 연산의 결과를 사용한다.
     * 
     * @param fullFileName
     *            파일 경로
     * @return String 파일 이름을 제외한 절대 경로 (/를 사용함)
     */
    public static String getFilePath(String fullFileName) {
        fullFileName = StringUtil.replaceAll(fullFileName, "\\", "/");

        return getFilePath(fullFileName, "/");

    }

    /**
     * Absolute Path에서 FileName 을 빼고 경로만 반환한다. <br>
     * Windows 와 Unix 계열에서의 디렉토리 구분자가 다르므로, 동일한 데이터에 대해 다른 결과를 반환하므로, 개발/운용시 주의
     * 하기바람. <br>
     * 
     * @param fullFileName
     *            파일 경로
     * @param fileSeperator
     *            파일 경로의 디렉토리 구분자
     * @return String 파일 이름을 제외한 절대 경로
     */
    public static String getFilePath(String fullFileName, String fileSeparator) {

        return fullFileName.substring(0, fullFileName.lastIndexOf(fileSeparator));

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

        File file = new File(dirPath);

        if (!file.exists()) {
            isOk = file.mkdirs();
        }

        return isOk;
    }

    /**
     * 
     * @param file
     * @param path
     * @param cp
     * @param isSaveOriginal
     */
    public static synchronized void copy(File file, String path, String cp, boolean isSaveOriginal) {
        String fileName2 = "";
        try {
            String fileName = file.getName();

            path = chkSeparator(path);

            if (!(isExist(path))) {
                makeDirectory(path);
            }

            if (isExist(path, fileName))
                if (isSaveOriginal) {
                    fileName2 = copyFileWithIndex(path, fileName);
                    while (isExist(path, fileName2)) {
                        fileName2 = copyFileWithIndex(path, fileName2);
                    }
                } else {
                    fileName2 = fileName;
                }
            else
                fileName2 = fileName;

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
            if (filestream != null) {
                filestream.close();
            }

            if (cp.equals("mv"))
                file.delete();
        } catch (Exception localException) {}
    }

    public static synchronized String chkSeparator(String path) {
        if (path == null) {
            return null;
        }

        return path + File.separator;
    }

    public static synchronized boolean isExist(String fileName) {
        boolean isExist = true;
        try {
            File f = new File(fileName);
            isExist = f.exists();
        } catch (Exception localException) {
            isExist = false;
        }

        return isExist;
    }

    public static synchronized boolean isExist(String path, String fileName) {
        boolean i = false;
        try {
            File f = new File(path, fileName);
            i = f.exists();
        } catch (Exception localException) {
            i = false;
        }
        return i;
    }

    public static synchronized String copyFileWithIndex(String path, String fileName) {
        String newName = "";
        String tt = "000";
        int i = 0;
        int j = 0;
        try {
            i = fileName.lastIndexOf("_");
            j = fileName.lastIndexOf(".");

            if (fileName.lastIndexOf("_") < 0) {
                newName = fileName.substring(0, j) + "_002" + fileName.substring(j);

            }

            int k = Integer.parseInt(fileName.substring(i + 1, j));
            ++k;
            tt = tt + Integer.toString(k);

            tt = "_" + tt.substring(tt.length() - 3);

            newName = fileName.substring(0, i) + tt + fileName.substring(j);
        } catch (Exception localException1) {
            try {
                newName = fileName.substring(0, j) + "_002" + fileName.substring(j);
            } catch (Exception localException2) {
                newName = fileName;
            }
        }
        return newName;
    }
}
