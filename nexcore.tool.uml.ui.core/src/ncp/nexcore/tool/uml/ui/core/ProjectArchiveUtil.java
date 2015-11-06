/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.core.util.DateUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.internal.wizards.datatransfer.IFileExporter;
import org.eclipse.ui.internal.wizards.datatransfer.ZipFileExporter;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core</li>
 * <li>설  명 : ProjectArchiveUtil</li>
 * <li>작성일 : 2012. 9. 14.</li>
 * <li>작성자 : nspark </li>
 * </ul>
 */
public class ProjectArchiveUtil {
    private IFileExporter exporter;

    IResource[] projects;

    /**
     * ProjectBackup
     */
    public ProjectArchiveUtil(IResource[] projects) {
        this.projects = projects;
    }

    public void run() {
        String pathStr = String.format("%s%suml%s", System.getProperty("user.home"), File.separator, File.separator);
        try {
            File path = new File(pathStr);
            if (!path.exists()) {
                path.mkdirs();
            }

            File[] listFiles = path.listFiles();

            // 백업 파일 삭제
            deleteHistoryFile(listFiles);

            long time = System.currentTimeMillis();
            String fileName = null;
            for (IResource project : projects) {
                try {
                    if (!ProjectUtil.isActiveUMLProject((IProject) project)) {
                        continue;
                    }
                    fileName = String.format("%s_%d.zip", project.getName(), time);
                    File saveFile = new File(path, fileName);

                    exporter = new ZipFileExporter(saveFile.toString(), true);

                    exportResource(project, 1);

                    exporter.finished();
                } catch (Exception e) {
                    e.printStackTrace();
                    ALMLogger.getLog("nexcore.tool.uml.ui.core").error("[backup error -> ]" + fileName, e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * 24 * 3 시간이 지난 백업 파일은 각시간대별 최근 파일 1개만 남기고 삭제.
     * 10일이 지난 백업 파일은 모두 삭제
     * 
     * @param listFiles
     *            void
     */
    private void deleteHistoryFile(File[] listFiles) {
        long s = System.currentTimeMillis();
        try {
            TreeMap<Long, File> fileMap = new TreeMap<Long, File>();
            for (File f : listFiles) {
                if (f.getName().endsWith(".zip")) {
                    fileMap.put(f.lastModified(), f);
                }
            }

            Set<String> deleteFileMap = new HashSet<String>();

            File file = null;
            for (Iterator<Long> iterator = fileMap.descendingMap().keySet().iterator(); iterator.hasNext();) {
                Long f = (Long) iterator.next();
                file = fileMap.get(f);
                String key = file.getName().substring(0, file.getName().length() - 18) + "_" + genKey(f);
                if (deleteFileMap.contains(key)) {
                    if ((s - file.lastModified()) > 1000 * 60 * 60 * 24 * 3) {
                        file.delete();
                    }
                } else {
                    deleteFileMap.add(key);
                }
            }

            // 생성된지 10일이 지난 파일은 삭제
            for (File f : listFiles) {
                if ((s - f.lastModified()) > 1000 * 60 * 60 * 24 * 10) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String genKey(long time) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(time);
        gc.getTime();

        String tempPattern = "yyyy_MM_dd_HH"; //$NON-NLS-1$
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tempPattern);
        String stringTimeStamp = simpleDateFormat.format(gc.getTime());

        return stringTimeStamp;
    }

    public static void main(String[] args) {
        String convertLongTimeStampToString = DateUtil.convertLongTimeStampToString(1348460524284L);

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(1348460524284L);
        gc.getTime();

        String tempPattern = "yyyy_MM_dd_HH"; //$NON-NLS-1$
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tempPattern);
        String stringTimeStamp = simpleDateFormat.format(gc.getTime());

        System.out.println(stringTimeStamp);
    }

    public void exportResource(IResource exportResource, int leadupDepth) throws InterruptedException {
        if (!exportResource.isAccessible()) {
            return;
        }

        if (exportResource.getType() == IResource.FILE) {
            String destinationName;
            IPath fullPath = exportResource.getFullPath();
            if (true) {
                destinationName = fullPath.makeRelative().toString();
            } else {
                destinationName = fullPath.removeFirstSegments(fullPath.segmentCount() - leadupDepth).toString();
            }

            try {
                exporter.write((IFile) exportResource, destinationName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CoreException e) {
                e.printStackTrace();
            }

        } else {
            IResource[] children = null;

            try {
                children = ((IContainer) exportResource).members(IContainer.INCLUDE_HIDDEN);
            } catch (CoreException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < children.length; i++) {
                exportResource(children[i], leadupDepth + 1);
            }

        }
    }
}
