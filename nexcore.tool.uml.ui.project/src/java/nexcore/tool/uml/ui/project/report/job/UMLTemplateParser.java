/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.job;

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
import nexcore.alm.common.word.DataModel;
import nexcore.alm.common.word.DataModelGenerator;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.job</li>
 * <li>설 명 : UMLTemplateParser</li>
 * <li>작성일 : 2011. 5. 12.</li>
 * <li>작성자 : zerotae </li>
 * </ul>
 */
public class UMLTemplateParser {
    /**
     * root
     */
    private Document root = null;
    /**
     * input
     */
    private DataModel input = null;
    /**
     * temp
     */
    private DataModel temp = new DataModel();
    /**
     * nodeCount
     */
    private int nodeCount = 0;
    /**
     * loopCount
     */
    private int loopCount = 0;
    /**
     * filename
     */
    private String filename = null;
    /**
     * metafilename
     */
    private String metafilename = null;
    /**
     * args
     */
    private Object[] args = null;
    /**
     * usedObj
     */
    private HashSet<String> usedObj = new HashSet<String>();
    /**
     * imageCount
     */
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
     * @param doc Document 변환할 워드를 Document로 변환한 instance
     * @param input DataModel 변환을 위해 사용할 데이터가 들어 있는 DataModel
     */
    public UMLTemplateParser(Document doc, DataModel input){
        this.root = doc;
        this.input = input;
    }
    

    /**
     * 
     * 워드 파일의 파일 이름과 DataModel을 입력으로 받는다.
     * 
     * @param filename String 워드 파일 이름
     * @param input DataModel 변환을 위해 사용할 데이터가 들어 있는 DataModel
     */
    public UMLTemplateParser(String filename, DataModel input){
        this.filename = filename;
        this.input = input;
    }    
    

    /**
     * 
     * 워드 파일의 파일 이름과 DataModel의 메타 파일 이름 데이터 Object를 입력으로 받는다.
     * 
     * @param filename String 워드 파일 이름
     * @param metafilename String DataModel에 대한 메타 파일 이름
     * @param args Object 데이터 Object
     */
    public UMLTemplateParser(String filename, String metafilename, Object... args){
        this.filename = filename;
        this.metafilename = metafilename;
        this.args = args;
    }      
    

    /**
     * 
     * 워드 파일에 데이터를 채워 넣어 변환한 Node를 반환 한다.
     *  
     * @return Node 변환된 노드
     * @throws Exception Node
     */
    public Node parse() throws Exception {

        if (filename != null){
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            root = builder.parse(new File(filename));           
        }
        
        if (args != null){
            DataModelGenerator generator = new DataModelGenerator(metafilename,args);
            input = generator.generate();
        }
        
        parseNode(root);
        return root;
    }
        

    /**
     * 
     * 한개의 노드를 변환한다.
     * 
     * @param node Node 변환할 노드
     */
    private void parseNode(Node node){

        nodeCount++;
        
        switch (node.getNodeType()){
            case Node.DOCUMENT_NODE:
            case Node.ELEMENT_NODE:

                if (node.getLocalName() != null && "wordDocument".equals(node.getLocalName()) && node.getNamespaceURI() != null && node.getNamespaceURI().equals(UMLTemplateParser.WORD_URI)){
                    
                    NodeList nodeList = node.getChildNodes();
                    
                    for (int i=0; i < nodeList.getLength(); i++){
                        Node child = nodeList.item(i);   
                        parseNode(child);
                    }                 
                }
                else if (node.getNamespaceURI() != null && node.getNamespaceURI().equals(UMLTemplateParser.TEMPLATE_URI)){

                    if ("print".equals(node.getLocalName()))
                        parsePrint(node);
                    else if ("foreach".equals(node.getLocalName()))
                        parseForEach(node);
                    else if ("image".equals(node.getLocalName()))
                        parseImage(node);                    

                }
                else if (node.hasChildNodes()){
                    NodeList nodeList = node.getChildNodes();
                    
                    for (int i=0; i < nodeList.getLength(); i++){
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
     * @param oChild Node 현재 노드
     * @return Node next sibling 노드
     */
    private static Node findNext(Node oChild){
        Node parentNode = oChild.getParentNode();
        Node nextChild = null;
        
        if (parentNode != null){
            NodeList childList = parentNode.getChildNodes();
            
            for (int i=0; i < childList.getLength(); i++){
                if (childList.item(i).equals(oChild)){
                    if (i < childList.getLength()-1){
                        nextChild = childList.item(i+1);
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
     * @param param String '/'로 구분된 위치 정보
     * @param store DataModel 데이터가 저장된 저장소
     * @return Object 검색된 값.
     */
    private Object getHashValueFromStore(String param, DataModel store){
        Object result = "";
        
        if (param != null){
            if (param.indexOf("/") < 0){
                if (store.containsKey(param)){
                    result = store.get(param);
                }
            }
            else {
                try {
                    String[] index = param.split("/");
                    DataModel curr = store;
                    
                    for (int i=0; i < index.length-1; i++){
                        if (curr.containsKey(index[i])){
                            curr = (DataModel)curr.get(index[i]);
                        }
                    }
                    
                    if (curr.containsKey(index[index.length-1])){
                        result = curr.get(index[index.length-1]);
                    }
                }
                catch (Exception e){
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
     * @param param String '/'로 구분된 파라미터
     * @return Object 읽혀진 값
     */
    private Object getHashValue(String param){
        Object result = "";
        DataModel store = null;
        
        if (loopCount > 0)
            store = temp;
        else 
            store = input;
        
        result = getHashValueFromStore(param, store);
        
        if (loopCount <= 0 && result == null){
            result = getHashValueFromStore(param, input);
        }
        
        return result;
    }
    
    /**
     * 
     * print 노드에 값을 채워 넣는다.
     *  
     * @param node void
     */
    private void parseImage(Node node){
        
        Node parentNode = node.getParentNode();
        
        if (node.getAttributes().getNamedItem("var") == null)
            return;

        boolean isBinary = false;
        String width = "auto";
        String height = "auto";
        String varName = null;
        String type = "jpg";
        String firstTag = "";
        String useFixSize = "";
        
        String title = "";
        
        if (node.getAttributes().getNamedItem("type") != null){            
            type = node.getAttributes().getNamedItem("type").getNodeValue();
        }        
        
        if (node.getAttributes().getNamedItem("binary") != null){            
            try {
                isBinary = Boolean.valueOf(node.getAttributes().getNamedItem("binary").getNodeValue());
            }
            catch (Exception e){
                e.printStackTrace();
                isBinary = false;
            }            
        }
        
        varName = node.getAttributes().getNamedItem("var").getNodeValue();
        String[] tags = varName.split("/");
        if(tags != null && tags.length > 1) {
            firstTag = tags[0] + "/";            
        }        
        
        if(node.getAttributes().getNamedItem("useFixSize") != null) {
            useFixSize = node.getAttributes().getNamedItem("useFixSize").getNodeValue();
        }
        
        if (node.getAttributes().getNamedItem("width") != null){
            int maxWidth = Integer.parseInt(node.getAttributes().getNamedItem("width").getNodeValue());
            if("true".equals(useFixSize)) {
                width = Integer.toString(maxWidth);
            } else {
                width = (String) getHashValue(firstTag+UICoreConstant.REPORT__DIAGRAM_WIDTH);
                try {
                    int parseInt = Integer.parseInt(width);
                    if(parseInt>maxWidth) {
                        width = Integer.toString(maxWidth);
                    }
                }
                catch (Exception e){
                    width = "auto";
                }            
            }
        }

        if (node.getAttributes().getNamedItem("height") != null){
            int maxHeight = Integer.parseInt(node.getAttributes().getNamedItem("height").getNodeValue());
            if("true".equals(useFixSize)) {
                height = Integer.toString(maxHeight);
            } else {
                height = (String) getHashValue(firstTag+UICoreConstant.REPORT__DIAGRAM_HEIGHT);
                try {
                    int parseInt = Integer.parseInt(height);
                    if(parseInt>maxHeight) {
                        height = Integer.toString(maxHeight);
                    }
                }
                catch (Exception e){
                    height = "auto";
                }             
            }
        }

        if (node.getAttributes().getNamedItem("title") != null){
            title = node.getAttributes().getNamedItem("title").getNodeValue();         
        }
        
        Object value = getHashValue(varName);
        String filename = varName + "." + type;

        if (filename.indexOf("/") > 0){
            filename = filename.replace("/", "");
        }
        

        if (node.hasChildNodes()){
            NodeList nodeList = node.getChildNodes();
            for (int i=0; i < nodeList.getLength(); i++){
                Node child = nodeList.item(i);
                
                if (child.getNodeName().equals("w:p")){
                    Node newNode = child.cloneNode(true);
                    parentNode.insertBefore(newNode, UMLTemplateParser.findNext(node)); 

                    Node rNode = moveNode(newNode, "w:r");
                    
                    NodeList childList = rNode.getChildNodes();
                    int itemSize = childList.getLength();
                    
                    for (int j=0; j < itemSize; j++){
                        rNode.removeChild(childList.item(0));
                    }
                    
                    createPicNode(rNode, varName, filename, isBinary, value, title, width, height);
                    break;
                }
                else if (child.getNodeName().equals("w:r")){
                    Node rNode = child.cloneNode(true);
                    parentNode.insertBefore(rNode, UMLTemplateParser.findNext(node));
                    
                    NodeList childList = rNode.getChildNodes();
                    int itemSize = childList.getLength();
                    
                    for (int j=0; j < itemSize; j++){
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
     * @param node void
     */
    private void parsePrint(Node node){
        
        Node parentNode = node.getParentNode();
        
        if (node.getAttributes().getNamedItem("var") == null)
        	return;
        
        String param = node.getAttributes().getNamedItem("var").getNodeValue();
        String value = String.valueOf(getHashValue(param));

//        if ("".equals(value)){
//            if (node.getNextSibling() != null) {
//                parentNode.removeChild(node.getNextSibling());
//            }
//            
//            parentNode.removeChild(node);
//            
//            if (node.getPreviousSibling() != null) {
//                parentNode.removeChild(node.getPreviousSibling());
//            }
//            
//            return;
//        } 
        
        boolean isChanged = false;
        
        List<String> stringList = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new StringReader(value));
        String line = null;
        try {
			while( (line = br.readLine()) != null){
				stringList.add(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        if (node.hasChildNodes()){
            NodeList nodeList = node.getChildNodes();
            for (int i=0; i < nodeList.getLength(); i++){
                Node child = nodeList.item(i);
                
                if ("w:r".equals(child.getNodeName()) || "w:p".equals(child.getNodeName()) || "w:tc".equals(child.getNodeName())){
                    Node newNode = child.cloneNode(true);
                    parentNode.insertBefore(newNode, UMLTemplateParser.findNext(node)); 
                    
                    Node txtNode = moveNode(newNode, "w:t");
                    
                    if (txtNode == null){
                    	txtNode = createTextNode(newNode, "");
                    }
                    
                    Node rNode = moveNode(newNode, "w:r");

                    Node newTxtNode = null;
                    
                    for (int j=0; j < stringList.size(); j++){
                    	newTxtNode = txtNode.cloneNode(true);
                    	txtNode.getParentNode().insertBefore(newTxtNode, findNext(txtNode));
                    	setNodeText(newTxtNode,stringList.get(stringList.size()-j-1));
                    	
                    	if (j < stringList.size() -1){
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
    private Node createTextNode(Node node, String value){
    	Node txtNode = null;
        if (node != null){
            Node rNode = root.createElement("w:r");
            Node tNode = root.createElement("w:t");
            txtNode = root.createTextNode(value);
            tNode.appendChild(txtNode);
            rNode.appendChild(tNode);
            node.appendChild(rNode);
        }
        
        return txtNode;
    }
    
    /**
     * fillBinData
     *  
     * @param node
     * @param data
     * @return Node
     */
    private Node fillBinData(Node node, byte[] data){
        
        StringBuffer sb = new StringBuffer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        
        try {
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));
            
            int readSize = -1;
            while ( (readSize = bis.read(buff)) >= 0 ){
                out.write(buff,0,readSize);
            }

            Base64 encoder = new Base64();
            node.setTextContent(new String(encoder.encode(out.toByteArray())));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return node;
    }
    
    /**
     * fillBinData
     *  
     * @param node
     * @param filename
     * @return Node
     */
    private Node fillBinData(Node node, String filename){
        
        StringBuffer sb = new StringBuffer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        File file = new File(filename);
        byte[] buff = new byte[4096];

        try {
            if (file != null && file.exists()){

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                
                int readSize = -1;
                while ( (readSize = bis.read(buff)) >= 0 ){
                    out.write(buff,0,readSize);
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
    
    /**
     * createPicNode
     *  
     * @param rNode
     * @param varName
     * @param filename
     * @param isBinary
     * @param data
     * @param title
     * @param width
     * @param height
     * @return Node
     */
    private Node createPicNode(Node rNode, String varName, String filename, boolean isBinary, Object data, String title, String width, String height){

        if (rNode != null){
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
            
            boolean imageOutlineFlag = false;
            String valueOfImageOutline = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_IMAGEOUTLINE);
            if("true".equals(valueOfImageOutline)) {
                imageOutlineFlag = true;
            } else {
                imageOutlineFlag = false;
            }
            
            if (imageOutlineFlag) {
                String[] borderColorAttrName = new String[] { "o:bordertopcolor", "o:borderleftcolor",
                    "o:borderbottomcolor", "o:borderrightcolor" };
                for (int i = 0; i < borderColorAttrName.length; i++) {
                    Attr borderColorAttr = root.createAttribute(borderColorAttrName[i]);
                    borderColorAttr.setNodeValue("this");
                    shapeNode.getAttributes().setNamedItem(borderColorAttr);
                }

                String[] border = new String[] { "w10:bordertop", "w10:borderleft", "w10:borderbottom",
                    "w10:borderright" };
                for (int i = 0; i < 4; i++) {
                    Node borderNode = root.createElement(border[i]);
                    Attr typeAttr = root.createAttribute("type");
                    typeAttr.setNodeValue("single");// 선 스타일
                    borderNode.getAttributes().setNamedItem(typeAttr);

                    Attr widthAttr = root.createAttribute("width");
                    widthAttr.setNodeValue("8");// 선 굵기값
                    borderNode.getAttributes().setNamedItem(widthAttr);

                    shapeNode.appendChild(borderNode);
                }
            }
            
            Node binNode = root.createElement("w:binData");
            
//            if (!usedObj.contains(varName)){
                Attr nameAttr = root.createAttribute("w:name");
                nameAttr.setNodeValue("wordml://" + filename + imageCount);
                
                imageCount++;
                
                binNode.getAttributes().setNamedItem(nameAttr);
                
                if (isBinary){
                    fillBinData(binNode, (byte[])data);
                }
                else{
                    fillBinData(binNode, (String)data);
                }
                
                picNode.appendChild(binNode);                
//            }
            
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
    private Node createCrNode(){
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
    private Node setNodeText(Node node, String value){
        
        if (node.hasChildNodes()){
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
    private Node moveNode(Node node, String targetNodeName){
        Node targetNode = null;
        
        if (node.getNodeName().equals(targetNodeName)){
            return node;
        }
        
        if (node.hasChildNodes()){
            NodeList nodeList = node.getChildNodes();
            for (int i=0; i < nodeList.getLength(); i++){
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
     * @param node void
     */
    private void parseForEach(Node node){
        Node parentNode = node.getParentNode();
        String target = node.getAttributes().getNamedItem("targetval").getNodeValue();
        String array = node.getAttributes().getNamedItem("arrayval").getNodeValue();

        Object[] param = null;
        
        Object t = getHashValue(array);
        
        if (t instanceof Object[]){
            param = (Object[])t;
        }
        else if (t instanceof List){
        	param = ((List)t).toArray();
        }
        
        loopCount++;
        Node lastNode = UMLTemplateParser.findNext(node);
        
        if (param == null){
            parentNode.removeChild(node);
            loopCount--;
            return;
        }
//            param = new String[]{""};
        
        for (int i=0; i < param.length; i++){

            // Add by Zhang Nan
            temp.put(target, param[i]);
            
            if (node.hasChildNodes()){
                NodeList nodeList = node.getChildNodes();
                for (int j=0; j < nodeList.getLength(); j++){
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
