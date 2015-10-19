/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.util</li>
 * <li>설 명 : ZipUtil</li>
 * <li>작성일 : 2009. 10. 28.</li>
 * <li>작성자 : 05690</li>
 * </ul>
 */
public class ZipUtil {

    private static final int BUFFER = 2048;

    /**
     * ZipUtil.compress("dummy-1.txt");
     * 
     * @param source
     *            void
     */
    public static void compress(String source) {
        try {
            if (source == null) {
                return;
            }

            File sourceFile = new File(source);
            if (!sourceFile.exists()) {
                return;
            }

            String target = source + ".zip";
            if (sourceFile.isFile()) {
                target = source.substring(0, source.lastIndexOf('.')) + ".zip"; // 파일
                                                                                // 확장자
                                                                                // 삭제
            } else {
                if (source.lastIndexOf('/') == source.length() - 1) {
                    target = source.substring(0, source.lastIndexOf('/')) + ".zip"; // 파일
                                                                                    // 확장자
                                                                                    // 삭제
                } else if (source.lastIndexOf('\\') == source.length() - 1) {
                    target = source.substring(0, source.lastIndexOf('\\')) + ".zip"; // 파일
                                                                                     // 확장자
                                                                                     // 삭제
                }
            }

            File targetFile = new File(target);
            if (targetFile.exists()) {
                return;
            }

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));

            if (sourceFile.isDirectory()) {
                zipDirectory(out, sourceFile);
            } else {
                zipFile(out, sourceFile);
            }

            // Finish zip process
            out.close();
            System.out.println("File Compressed... : " + target);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ZipUtil.compress("dummy-1.txt", "dummy-1.zip");
     * 
     * @param source
     * @param target
     *            void
     */
    public static void compress(String source, String target) {
        try {
            if (source == null || target == null) {
                return;
            }

            File sourceFile = new File(source);
            if (!sourceFile.exists()) {
                return;
            }

            File targetFile = new File(target);
            if (targetFile.exists()) {
                return;
            }

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));

            if (sourceFile.isDirectory()) {
                zipDirectory(out, sourceFile);
            } else {
                zipFile(out, sourceFile);
            }

            // Finish zip process
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipFile(ZipOutputStream out, File sourceFile) throws IOException {
        System.out.println("Adding File: " + sourceFile.getAbsolutePath());
        FileInputStream fi = new FileInputStream(sourceFile);
        BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
        String entryName = sourceFile.getAbsolutePath().substring(sourceFile.getAbsolutePath().indexOf(':') + 2,
            sourceFile.getAbsolutePath().length());
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = origin.read(data, 0, BUFFER)) != -1) {
            out.write(data, 0, count);
        }
        origin.close();
    }

    private static void zipDirectory(ZipOutputStream out, File sourceFile) throws IOException {
        System.out.println("Adding Directory: " + sourceFile.getPath());
        String[] files = sourceFile.list();
        File file = null;
        for (int i = 0; i < files.length; i++) {
            file = new File(sourceFile, files[i]);
            if (file.isDirectory()) {
                zipDirectory(out, file);
            } else {
                zipFile(out, file);
            }
        }
    }

    /**
     * 압축 파일 해제 메서드
     * 
     * @param zipFile
     * @param targetPath
     *            void
     */
    public static void decompress(String zipFile, String targetPath) {
        int BUFFER = 2048;
        ZipFile zipfile = null;
        File root = null;
        try {
            BufferedOutputStream dest = null;
            BufferedInputStream is = null;
            ZipEntry entry;
            zipfile = new ZipFile(zipFile);
            Enumeration e = zipfile.entries();

            root = new File(targetPath);
            File file = null;
            while (e.hasMoreElements()) {
                entry = (ZipEntry) e.nextElement();
                String fileName = entry.getName();// .replaceAll("\\\\", "/");

                file = new File(root, fileName);
                if (fileName.endsWith("/") || fileName.endsWith("\\")) {
                    if (!file.exists())
                        file.mkdirs();
                    file = null;
                    // continue;
                } else {
                    System.out.println("Decompress File :" + file.getAbsolutePath());
                    is = new BufferedInputStream(zipfile.getInputStream(entry));
                    int count;
                    byte data[] = new byte[BUFFER];

                    File p = new File(file.getParent());
                    if (!p.exists())
                        p.mkdirs();
                    p = null;

                    FileOutputStream fos = new FileOutputStream(file);
                    dest = new BufferedOutputStream(fos, BUFFER);
                    while ((count = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                    if (fos != null)
                        fos.close();
                    is.close();
                }

            }
        } catch (Exception e) {} finally {
            root = null;
            try {
                if (zipfile != null)
                    zipfile.close();
            } catch (Exception xe) {}
        }
    }

}
