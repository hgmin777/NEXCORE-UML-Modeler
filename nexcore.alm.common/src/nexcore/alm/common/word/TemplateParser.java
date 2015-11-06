/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.word;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nexcore.alm.common.util.Base64;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.word</li>
 * <li>설 명 : TemplateParser</li>
 * <li>작성일 : 2008. 12. 29</li>
 * <li>작성자 : 김명기</li>
 * </ul>
 */
public class TemplateParser {

    private Document root = null;

    private DataModel input = null;

    private DataModel temp = new DataModel();

    private int nodeCount = 0;

    private int loopCount = 0;

    private String filename = null;

    private String metafilename = null;

    private Object[] args = null;

    private HashSet<String> usedObj = new HashSet<String>();

    private int imageCount = 0;

    /**
     * 템플릿 URI
     */
    public final static String TEMPLATE_URI = "http://skcc.com/2003/wordtemplate_v1.1";

    /**
     * 워드 포맷 URI
     */
    public final static String WORD_URI = "http://schemas.microsoft.com/office/word/2003/wordml";

    /**
     * 
     * Document와 DataModel을 입력으로 받는다.
     * 
     * @param doc
     *            Document 변환할 워드를 Document로 변환한 instance
     * @param input
     *            DataModel 변환을 위해 사용할 데이터가 들어 있는 DataModel
     */
    public TemplateParser(Document doc, DataModel input) {
        this.root = doc;
        this.input = input;
    }

    /**
     * 
     * 워드 파일의 파일 이름과 DataModel을 입력으로 받는다.
     * 
     * @param filename
     *            String 워드 파일 이름
     * @param input
     *            DataModel 변환을 위해 사용할 데이터가 들어 있는 DataModel
     */
    public TemplateParser(String filename, DataModel input) {
        this.filename = filename;
        this.input = input;
    }

    /**
     * 
     * 워드 파일의 파일 이름과 DataModel의 메타 파일 이름 데이터 Object를 입력으로 받는다.
     * 
     * @param filename
     *            String 워드 파일 이름
     * @param metafilename
     *            String DataModel에 대한 메타 파일 이름
     * @param args
     *            Object 데이터 Object
     */
    public TemplateParser(String filename, String metafilename, Object... args) {
        this.filename = filename;
        this.metafilename = metafilename;
        this.args = args;
    }

    /**
     * 
     * 워드 파일에 데이터를 채워 넣어 변환한 Node를 반환 한다.
     * 
     * @return Node 변환된 노드
     * @throws Exception
     *             Node
     */
    public Node parse() throws Exception {

        if (filename != null) {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            root = builder.parse(new File(filename));
        }

        if (args != null) {
            DataModelGenerator generator = new DataModelGenerator(metafilename, args);
            input = generator.generate();
        }

        parseNode(root);
        return root;
    }

    /**
     * 
     * 한개의 노드를 변환한다.
     * 
     * @param node
     *            Node 변환할 노드
     */
    private void parseNode(Node node) {

        nodeCount++;

        switch (node.getNodeType()) {
            case Node.DOCUMENT_NODE:
            case Node.ELEMENT_NODE:

                if (node.getLocalName() != null && node.getLocalName().equals("wordDocument")
                    && node.getNamespaceURI() != null && node.getNamespaceURI().equals(TemplateParser.WORD_URI)) {

                    NodeList nodeList = node.getChildNodes();

                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node child = nodeList.item(i);
                        parseNode(child);
                    }
                } else if (node.getNamespaceURI() != null && node.getNamespaceURI().equals(TemplateParser.TEMPLATE_URI)) {

                    if (node.getLocalName().equals("print"))
                        parsePrint(node);
                    else if (node.getLocalName().equals("foreach"))
                        parseForEach(node);
                    else if (node.getLocalName().equals("image"))
                        parseImage(node);

                } else if (node.hasChildNodes()) {
                    NodeList nodeList = node.getChildNodes();

                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node child = nodeList.item(i);
                        parseNode(child);
                    }
                }

                break;
        }
    }

    /**
     * 
     * 현재 노드의 다음 노드 위치(Next Sibling)를 넘겨준다.
     * 
     * @param oChild
     *            Node 현재 노드
     * @return Node next sibling 노드
     */
    private static Node findNext(Node oChild) {
        Node parentNode = oChild.getParentNode();
        Node nextChild = null;

        if (parentNode != null) {
            NodeList childList = parentNode.getChildNodes();

            for (int i = 0; i < childList.getLength(); i++) {
                if (childList.item(i).equals(oChild)) {
                    if (i < childList.getLength() - 1) {
                        nextChild = childList.item(i + 1);
                    }
                }
            }
        }

        return nextChild;
    }

    /**
     * 
     * '/'로 구분된 값 위치 정보를 이용하여 DataModel 저장소에서 특정 값을 가져온다.
     * 
     * @param param
     *            String '/'로 구분된 위치 정보
     * @param store
     *            DataModel 데이터가 저장된 저장소
     * @return Object 검색된 값.
     */
    private Object getHashValueFromStore(String param, DataModel store) {
        Object result = "";

        if (param != null) {
            if (param.indexOf("/") < 0) {
                if (store.containsKey(param)) {
                    result = store.get(param);
                }
            } else {
                try {
                    String[] index = param.split("/");
                    DataModel curr = store;

                    for (int i = 0; i < index.length - 1; i++) {
                        if (curr.containsKey(index[i])) {
                            curr = (DataModel) curr.get(index[i]);
                        }
                    }

                    if (curr.containsKey(index[index.length - 1])) {
                        result = curr.get(index[index.length - 1]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 
     * 데이터 모델 저장소에서 '/'로 구분된 파라미터에 해당하는 값을 읽어 온다.
     * 
     * @param param
     *            String '/'로 구분된 파라미터
     * @return Object 읽혀진 값
     */
    private Object getHashValue(String param) {
        Object result = "";
        DataModel store = null;

        if (loopCount > 0)
            store = temp;
        else
            store = input;

        result = getHashValueFromStore(param, store);

        if (loopCount <= 0 && result == null) {
            result = getHashValueFromStore(param, input);
        }

        return result;
    }

    /**
     * 
     * print 노드에 값을 채워 넣는다.
     * 
     * @param node
     *            void
     */
    private void parseImage(Node node) {

        Node parentNode = node.getParentNode();

        if (node.getAttributes().getNamedItem("var") == null)
            return;

        boolean isBinary = false;
        String width = "auto";
        String height = "auto";
        String varName = null;
        String type = "jpg";

        String title = "";

        if (node.getAttributes().getNamedItem("type") != null) {
            type = node.getAttributes().getNamedItem("type").getNodeValue();
        }

        if (node.getAttributes().getNamedItem("binary") != null) {
            try {
                isBinary = Boolean.valueOf(node.getAttributes().getNamedItem("binary").getNodeValue());
            } catch (Exception e) {
                e.printStackTrace();
                isBinary = false;
            }
        }

        if (node.getAttributes().getNamedItem("width") != null) {
            width = node.getAttributes().getNamedItem("width").getNodeValue();

            try {
                Integer.parseInt(width);
            } catch (Exception e) {
                width = "auto";
            }
        }

        if (node.getAttributes().getNamedItem("height") != null) {
            height = node.getAttributes().getNamedItem("height").getNodeValue();

            try {
                Integer.parseInt(height);
            } catch (Exception e) {
                height = "auto";
            }
        }

        if (node.getAttributes().getNamedItem("title") != null) {
            title = node.getAttributes().getNamedItem("title").getNodeValue();
        }

        varName = node.getAttributes().getNamedItem("var").getNodeValue();

        Object value = getHashValue(varName);
        String filename = varName + "." + type;

        if (filename.indexOf("/") > 0) {
            filename = filename.replace("/", "");
        }

        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);

                if (child.getNodeName().equals("w:p")) {
                    Node newNode = child.cloneNode(true);
                    parentNode.insertBefore(newNode, TemplateParser.findNext(node));

                    Node rNode = moveNode(newNode, "w:r");

                    NodeList childList = rNode.getChildNodes();
                    int itemSize = childList.getLength();

                    for (int j = 0; j < itemSize; j++) {
                        rNode.removeChild(childList.item(0));
                    }

                    createPicNode(rNode, varName, filename, isBinary, value, title, width, height);
                    break;
                } else if (child.getNodeName().equals("w:r")) {
                    Node rNode = child.cloneNode(true);
                    parentNode.insertBefore(rNode, TemplateParser.findNext(node));

                    NodeList childList = rNode.getChildNodes();
                    int itemSize = childList.getLength();

                    for (int j = 0; j < itemSize; j++) {
                        rNode.removeChild(childList.item(0));
                    }

                    createPicNode(rNode, varName, filename, isBinary, value, title, width, height);
                    break;
                }
            }
        }

        parentNode.removeChild(node);
    }

    /**
     * 
     * print 노드에 값을 채워 넣는다.
     * 
     * @param node
     *            void
     */
    private void parsePrint(Node node) {

        Node parentNode = node.getParentNode();

        if (node.getAttributes().getNamedItem("var") == null)
            return;

        String param = node.getAttributes().getNamedItem("var").getNodeValue();
        String value = String.valueOf(getHashValue(param));

        // if ("".equals(value)){
        // if (node.getNextSibling() != null) {
        // parentNode.removeChild(node.getNextSibling());
        // }
        //            
        // parentNode.removeChild(node);
        //            
        // if (node.getPreviousSibling() != null) {
        // parentNode.removeChild(node.getPreviousSibling());
        // }
        //            
        // return;
        // }

        boolean isChanged = false;

        List<String> stringList = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new StringReader(value));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);

                if (child.getNodeName().equals("w:r") || child.getNodeName().equals("w:p")
                    || child.getNodeName().equals("w:tc")) {
                    Node newNode = child.cloneNode(true);
                    parentNode.insertBefore(newNode, TemplateParser.findNext(node));

                    Node txtNode = moveNode(newNode, "w:t");

                    if (txtNode == null) {
                        txtNode = createTextNode(newNode, "");
                    }

                    Node rNode = moveNode(newNode, "w:r");

                    Node newTxtNode = null;

                    for (int j = 0; j < stringList.size(); j++) {
                        newTxtNode = txtNode.cloneNode(true);
                        txtNode.getParentNode().insertBefore(newTxtNode, findNext(txtNode));
                        setNodeText(newTxtNode, stringList.get(stringList.size() - j - 1));

                        if (j < stringList.size() - 1) {
                            txtNode.getParentNode().insertBefore(createCrNode(), findNext(txtNode));
                        }
                    }

                    rNode.removeChild(txtNode);

                    break;
                }
            }
        }

        parentNode.removeChild(node);
    }

    /**
     * 
     * Text 노드를 삽입한다.
     * 
     * @param node
     * @param value
     * @return Node
     */
    private Node createTextNode(Node node, String value) {
        Node txtNode = null;
        if (node != null) {
            Node rNode = root.createElement("w:r");
            Node tNode = root.createElement("w:t");
            txtNode = root.createTextNode(value);
            tNode.appendChild(txtNode);
            rNode.appendChild(tNode);
            node.appendChild(rNode);
        }

        return txtNode;
    }

    private Node fillBinData(Node node, byte[] data) {

        StringBuffer sb = new StringBuffer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];

        try {
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));

            int readSize = -1;
            while ((readSize = bis.read(buff)) >= 0) {
                out.write(buff, 0, readSize);
            }

            Base64 encoder = new Base64();
            node.setTextContent(new String(encoder.encode(out.toByteArray())));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return node;
    }

    private Node fillBinData(Node node, String filename) {

        StringBuffer sb = new StringBuffer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        File file = new File(filename);
        byte[] buff = new byte[4096];

        try {
            if (file != null && file.exists()) {

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

                int readSize = -1;
                while ((readSize = bis.read(buff)) >= 0) {
                    out.write(buff, 0, readSize);
                }

                Base64 encoder = new Base64();
                node.setTextContent(new String(encoder.encode(out.toByteArray())));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return node;
    }

    private Node createPicNode(Node rNode, String varName, String filename, boolean isBinary, Object data,
                               String title, String width, String height) {

        if (rNode != null) {
            Node picNode = root.createElement("w:pict");
            Node shapeNode = root.createElement("v:shape");
            Node imageDataNode = root.createElement("v:imagedata");

            Attr idAttr = root.createAttribute("id");
            idAttr.setNodeValue(varName);

            Attr styleAttr = root.createAttribute("style");
            styleAttr.setNodeValue("width:" + width + ";height:" + height);

            shapeNode.getAttributes().setNamedItem(idAttr);
            shapeNode.getAttributes().setNamedItem(styleAttr);

            Attr srcAttr = root.createAttribute("src");
            srcAttr.setNodeValue("wordml://" + filename + imageCount);

            Attr titleAttr = root.createAttribute("o:title");
            titleAttr.setNodeValue(title);

            imageDataNode.getAttributes().setNamedItem(srcAttr);
            imageDataNode.getAttributes().setNamedItem(titleAttr);

            shapeNode.appendChild(imageDataNode);

            Node binNode = root.createElement("w:binData");

            // if (!usedObj.contains(varName)){
            Attr nameAttr = root.createAttribute("w:name");
            nameAttr.setNodeValue("wordml://" + filename + imageCount);

            imageCount++;

            binNode.getAttributes().setNamedItem(nameAttr);

            if (isBinary) {
                fillBinData(binNode, (byte[]) data);
            } else {
                fillBinData(binNode, (String) data);
            }

            picNode.appendChild(binNode);
            // }

            picNode.appendChild(shapeNode);

            rNode.appendChild(picNode);
        }

        usedObj.add(varName);

        return rNode;
    }

    /**
     * 
     * 빈줄 노드를 하나 생성한다.
     * 
     * @return Node
     */
    private Node createCrNode() {
        Node crNode = root.createElement("w:br");
        return crNode;
    }

    /**
     * 
     * 텍스트 노드에 값을 세팅한다.
     * 
     * @param node
     * @param value
     * @return Node
     */
    private Node setNodeText(Node node, String value) {

        if (node.hasChildNodes()) {
            node.getFirstChild().setNodeValue(value);
        }

        return node;
    }

    /**
     * 
     * 현재 노드에서 다음 타겟 노드로 이동한다.
     * 
     * @param node
     * @param targetNodeName
     * @return Node
     */
    private Node moveNode(Node node, String targetNodeName) {
        Node targetNode = null;

        if (node.getNodeName().equals(targetNodeName)) {
            return node;
        }

        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);

                targetNode = moveNode(child, targetNodeName);

                if (targetNode != null)
                    break;
            }
        }

        return targetNode;
    }

    /**
     * 
     * forEach 태그를 분석하여 값을 채운다.
     * 
     * @param node
     *            void
     */
    private void parseForEach(Node node) {
        Node parentNode = node.getParentNode();
        String target = node.getAttributes().getNamedItem("targetval").getNodeValue();
        String array = node.getAttributes().getNamedItem("arrayval").getNodeValue();

        Object[] param = null;

        Object t = getHashValue(array);

        if (t instanceof Object[]) {
            param = (Object[]) t;
        } else if (t instanceof List) {
            param = ((List) t).toArray();
        }

        loopCount++;
        Node lastNode = TemplateParser.findNext(node);

        if (param == null) {
            parentNode.removeChild(node);
            loopCount--;
            return;
        }
        // param = new String[]{""};

        for (int i = 0; i < param.length; i++) {

            // Add by Zhang Nan
            temp.put(target, param[i]);

            if (node.hasChildNodes()) {
                NodeList nodeList = node.getChildNodes();
                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node child = nodeList.item(j);
                    Node newNode = child.cloneNode(true);
                    parentNode.insertBefore(newNode, lastNode);
                    parseNode(newNode);
                }
            }
        }

        parentNode.removeChild(node);
        temp.remove(target);
        loopCount--;
    }
}
