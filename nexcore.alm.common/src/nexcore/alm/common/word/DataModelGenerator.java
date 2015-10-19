/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.word;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import nexcore.alm.common.word.meta.ClassElt;
import nexcore.alm.common.word.meta.KeyElt;
import nexcore.alm.common.word.meta.TemplateMetaElt;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.word</li>
 * <li>설 명 : DataModelGenerator</li>
 * <li>작성일 : 2008. 12. 29</li>
 * <li>작성자 : 김명기</li>
 * </ul>
 */
public class DataModelGenerator {
    TemplateMetaElt templateMeta = null;

    Object[] model = null;

    DataModel dataModel = new DataModel();

    ArrayList tempInstance = new ArrayList();

    int loopCount = 0;

    /**
     * 데이터 모델이 저장된 파일 이름과 모델에 적용할 object 목록을 입력받는다.
     * 
     * @param filename
     *            String 데이터 모델이 저장된 파일 이름
     * @param model
     *            Object 데이터가 저장된 모델
     * @throws Exception
     */
    public DataModelGenerator(String filename, Object... model) throws Exception {
        JAXBContext context = JAXBContext.newInstance("nexcore.tool.core.word.meta");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        templateMeta = (TemplateMetaElt) ((JAXBElement) unmarshaller.unmarshal(new File(filename))).getValue();
        this.model = model;
    }

    /**
     * 데이터 모델과 Object를 이용하여 데이터 모델을 생성한다.
     * 
     * @return DataModel 생성된 데이터 모델
     */
    public DataModel generate() {
        List keyList = templateMeta.getKey();
        dataModel.clear();

        for (int i = 0; i < keyList.size(); i++) {
            KeyElt key = (KeyElt) keyList.get(i);
            String name = key.getName();
            dataModel.put(name, getValue(key.getClazz()));
        }

        return dataModel;
    }

    /**
     * 데이터 모델에 적혀 있는 클래스 이름을 가지고 Object로 부터 값을 읽어온다. Object가 지정되지 않은 경우 미리 입력된
     * 모델에서 같은 클래스를 찾아 get+필드 이름의 메소드를 호출하여 사용한다.
     * 
     * @param c
     *            ClassElt 클래스 모델
     * @return Object 호출되어 읽어진 값
     */
    private Object getValue(ClassElt c) {
        String className = c.getName();
        String fieldName = c.getField();
        Object result = null;

        try {
            Method method = Class.forName(className).getMethod("get" + upperCase(fieldName), new Class[0]);

            for (int i = 0; i < model.length; i++) {
                if (model[i].getClass().equals(Class.forName(className))) {
                    result = method.invoke(model[i], new Object[0]);
                    break;
                }
            }

            if (c.getKey().size() > 0) {
                if (result instanceof Object[]) {
                    int size = ((Object[]) result).length;

                    DataModel[] t = new DataModel[size];

                    for (int i = 0; i < size; i++) {
                        t[i] = new DataModel();

                        for (int j = 0; j < c.getKey().size(); j++) {
                            t[i].put(c.getKey().get(j).getName(), getValue(c.getKey().get(j).getClazz(),
                                ((Object[]) result)[i]));
                        }
                    }

                    result = t;
                } else {
                    DataModel t = new DataModel();

                    for (int i = 0; i < c.getKey().size(); i++) {
                        t.put(c.getKey().get(i).getName(), getValue(c.getKey().get(i).getClazz(), result));
                    }

                    result = t;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 데이터 모델에 적혀 있는 클래스 이름을 가지고 Object로 부터 값을 읽어온다. 메소드가 지정되지 않은 경우 get+필드 이름의
     * 메소드를 호출하여 사용한다.
     * 
     * @param c
     *            ClassElt 클래스 모델
     * @param object
     *            Object 메소드를 호출할 Object
     * @return Object 호출되어 읽어진 값
     */
    private Object getValue(ClassElt c, Object object) {
        String className = c.getName();
        String fieldName = c.getField();
        Object result = null;

        try {
            Method method = Class.forName(className).getMethod("get" + upperCase(fieldName), new Class[0]);

            if (object.getClass().equals(Class.forName(className))) {
                result = method.invoke(object, new Object[0]);
            }

            if (c.getKey().size() > 0) {
                if (result instanceof Object[]) {
                    int size = ((Object[]) result).length;

                    DataModel[] t = new DataModel[size];
                    for (int i = 0; i < size; i++) {
                        t[i] = new DataModel();

                        for (int j = 0; j < c.getKey().size(); j++) {
                            t[i].put(c.getKey().get(j).getName(), getValue(c.getKey().get(j).getClazz(),
                                ((Object[]) result)[i]));
                        }
                    }

                    result = t;
                } else {
                    DataModel t = new DataModel();

                    for (int i = 0; i < c.getKey().size(); i++) {
                        t.put(c.getKey().get(i).getName(), getValue(c.getKey().get(i).getClazz(), result));
                    }

                    result = t;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 스트링의 첫글자를 대문자로 변경한다.
     * 
     * @param s
     *            String 변경할 스트링
     * @return String
     */
    private String upperCase(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
    }
}
