/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : UiUtil</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UiUtil {

    /**
     * Error Message box 출력
     * 
     * @param shell
     * @param title
     * @param message
     *            void
     */
    public static void errorMessageBox(Shell shell, String title, String message) {
        if (StringUtil.isNull(shell)) {
            shell = UiCorePlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
        }
        MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
        messageBox.setMessage(message);
        messageBox.setText(title);
        messageBox.open();
    }

    /**
     * Question Message box 출력
     * 
     * @param shell
     * @param title
     * @param message
     */
    public static int questionMessageBox(Shell shell, String title, String message) {
        if (StringUtil.isNull(shell)) {
            shell = UiCorePlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
        }

        MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO | SWT.CANCEL | SWT.ICON_QUESTION);
        messageBox.setMessage(message);
        messageBox.setText(title);
        int result = messageBox.open();
        return result;
    }

    /**
     * Information Message box 출력
     * 
     * @param shell
     * @param title
     * @param message
     */
    public static int informationMessageBox(Shell shell, String title, String message) {
        if (StringUtil.isNull(shell)) {
            shell = UiCorePlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
        }

        MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
        messageBox.setMessage(message);
        messageBox.setText(title);
        int result = messageBox.open();
        return result;
    }

    /**
     * 디렉토리 선택 다이얼로그를 열고, 선택한 디렉토리 경로를 리턴한다.
     * 
     * @param defalutFilePath
     * @return String
     */
    public static String directoryDialog(String message, String defalutFilePath) {
        Shell shell = UiCorePlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
        DirectoryDialog directoryDialog = new DirectoryDialog(shell);
        directoryDialog.setFilterPath(defalutFilePath);
        directoryDialog.setMessage(message);
        directoryDialog.open();
        return directoryDialog.getFilterPath();
    }

    /**
     * 문자열의 stringLength 마다 줄바꿈문자를 넣는다.
     * 
     * @param string
     * @param offset
     *            void
     */
    public static String addNewLine(String string, int stringLength) {
        if (StringUtil.isEmpty(string)) {
            return ""; //$NON-NLS-1$
        }
        StringBuffer buffer = new StringBuffer();

        while (string.length() > stringLength) {
            buffer.append(string.subSequence(0, stringLength)).append(StringUtil.getSystemLineSeparator()
                + StringUtil.getSystemLineSeparator());//$NON-NLS-1$
            string = string.substring(stringLength);
        }
        buffer.append(string);
        return buffer.toString();
    }

    /**
     * stringLength길이가 되도록, string앞에 공백을 넣는다.
     * 
     * @param string
     * @param stringLength
     * @return String
     */
    public static String insetEmptyChar(String string, int stringLength) {
        while (string.length() != stringLength) {
            string = " " + string; //$NON-NLS-1$
        }
        return string;
    }

    /**
     * getParentPathInDb(AbstractRmEntity abstractRmEntity) 를 사용하여 부모 경로를 알아오면,
     * 마지막에 > 문자가 붙는다. 이를 제거해주는 메소드
     * 
     * @param parentPathInDb
     * @return String
     */
    public static String removeParentEndCharacter(String parentPathInDb) {
        if (StringUtil.isEmpty(parentPathInDb)) {
            return ""; //$NON-NLS-1$
        }
        return parentPathInDb.substring(0, parentPathInDb.length() - 2);
    }

    /**
     * 다중선택된 Attribute의 값을 하나의 스트링으로 만들어 준다. 각 값은 ';'으로 구분한다.
     * 
     * @param String
     *            [] values
     * @return String
     */
    public static String convertMultiValueIntoString(String[] values) {
        StringBuffer value = new StringBuffer();

        for (int i = 0; i < values.length; i++) {
            value.append(values[i]).append(";"); //$NON-NLS-1$
        }

        return value.toString();
    }

    /**
     * Attribute의 값을 multi value로 만들어 준다.
     * 
     * @param String
     *            value
     * @return List<String>
     */
    public static List<String> convertStringIntoMultiValue(String value) {
        List<String> valueList = new ArrayList<String>();

        char[] characters = new char[value.length()];

        value.getChars(0, value.length(), characters, 0);

        String tempString = null;
        int beginIndex = 0;

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == ';') {
                // 구분자 ';' 중복 입력 체크
                if (beginIndex != i) {
                    tempString = value.substring(beginIndex, i);
                    valueList.add(tempString);
                }
                beginIndex = i + 1;
            } else if (i == (characters.length - 1)) {
                tempString = value.substring(beginIndex, i + 1);
                valueList.add(tempString);
            }
        }

        if (valueList.size() == 0) {
            valueList.add(value);
        }

        return valueList;
    }

    /**
     * Object값을 바이트 배열로 변환하여 리턴한다.
     * 
     * @param object
     * @return byte[]
     */
    public static synchronized byte[] convertObjectToBytes(Object object) {
        StringUtil.checkNullObject(object);

        byte[] bytes = null;
        ByteArrayOutputStream store = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            store = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(store);
            objectOutputStream.writeObject(object);

            bytes = store.toByteArray();

        } catch (Exception e) {
            Log.error(e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    Log.error(e);
                }
            }
            if (store != null) {
                try {
                    store.close();
                } catch (IOException e) {
                    Log.error(e);
                }
            }

        }

        return bytes;
    }

    /**
     * 바이트 배열 값을 특정 Object로 변환한다.
     * 
     * @param bytes
     * @return Object
     */
    public static synchronized Object convertBytesToObject(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        Object object = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);

            object = objectInputStream.readObject();
        } catch (Exception e) {
            Log.error(e);
        }

        return object;
    }

    /**
     * 프로젝트 내의 패키지 경로만을 받아 실제 경로를 만든다.
     * 
     * @param packagePath
     * @return String
     */
    public static String createResourcePath(String packagePath) {
        String path = null;

        URL url = UiCorePlugin.getDefault().getBundle().getResource(packagePath);

        try {
            URL absoluteUrl = FileLocator.toFileURL(url);
            path = absoluteUrl.getPath();
        } catch (IOException e1) {
            Log.error(e1);
        }

        return path;
    }

    /**
     * 이클립스의 configure directory내의 특정 파일을 읽어온다.
     * 
     * @return File
     */
    public static File getFileToConfigureDirectory(String diretoryPath) {
        return UiCorePlugin.getDefault().getStateLocation().append(diretoryPath).toFile();
    }

    /**
     * 
     * 
     * @param control
     *            void
     */
    public static void disposeTabInControl(final Control control) {
        control.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.TAB) {
                    control.traverse(SWT.TRAVERSE_TAB_NEXT);
                    control.getCursor().dispose();
                }
            }
        });
    }

    /**
     * eraseFeedback
     *  
     * @param editPart void
     */
    public static void eraseFeedback(EditPart editPart) {
        LayerManager.Helper.find(editPart).getLayer(LayerConstants.FEEDBACK_LAYER).getChildren().clear();
        LayerManager.Helper.find(editPart).getLayer(LayerConstants.FEEDBACK_LAYER).repaint();
    }

}
