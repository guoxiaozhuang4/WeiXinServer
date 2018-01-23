/**
 * 	@date: 2018年1月23日下午9:30:47
 */
package com.guoxiaozhuang4.util.domhandle;

import java.io.File;
import java.util.ArrayList;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.guoxiaozhuang4.exception.FlowException;

/**
 * @author guoxiaozhuang4
 * @Decription TODO
 * @date 下午9:30:47
 */
public class DomExample {

	private Document document;
	private Element root;
	private Element location;
	
	private static String SETTINGS_PATH = "G:\\workspace\\WeiXinServer\\WebContent\\settings.xml";
	
	
	//location增加子节点
	public boolean addSonElement(String elementName) throws FlowException {
		
		boolean isSuccess = false;
		
		location.addElement(elementName);
		
		try {
			
			XMLWriter xmlWriter = new XMLWriter();
			
			xmlWriter.write(document);
			xmlWriter.close();
			
			isSuccess = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("add element be failed");
		}
		
		return isSuccess;
	}
	
	//获取locaton所有属性
	public ArrayList<Attribute> getLocationAllAttr() throws FlowException {
		
		return getAllAttrFromElement(location);
	}
	
	//获取location指定属性的值
	public String getLocationAttr(String attrName) throws FlowException {
		
		return getAttrFromElement(location, attrName);
	}
	
	//获取节点所有属性
	public ArrayList<Attribute> getAllAttrFromElement(Element element) throws FlowException {
		
		ArrayList<Attribute> allAttr;
		
		try {
			
			allAttr = (ArrayList<Attribute>) element.selectNodes("./@*");
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("attrList init failed");
		}
		
		return allAttr;
	}
	
	//获取节点的指定属性
	public String getAttrFromElement(Element element, String attrName) throws FlowException {
		
		String attrVal = "";
		
		try {
			
			attrVal = (String) ((Attribute) element.selectNodes("./@"+attrName).get(0)).getData();
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("tmpList for attrVal init failed");
		}
		
		return attrVal;
	}
	
	//获取location子元素集合
	public ArrayList<Element> getLocationSonElements(String... elementNames) throws FlowException {
		
		return getSonElementsByName(location, elementNames);
	}
	
	//获取location后代元素集合
	public ArrayList<Element> getLocationGenerElements(String... elementNames) throws FlowException {
		
		return getGenerElementsByName(location, elementNames);
	}
	
	//获取root子元素集合
	public ArrayList<Element> getRootSonElements(String... elementNames) throws FlowException {
		
		return getSonElementsByName(root, elementNames);
	}
	
	//获取root后代元素集合
	public ArrayList<Element> getRootGenerElements(String... elementNames) throws FlowException {
		
		return getGenerElementsByName(root, elementNames);
	}
	
	//根据节点名获取子元素集合
	public ArrayList<Element> getSonElementsByName(Element parentElement, String... elementNames) throws FlowException {
		
		String regexStr = "./";

		return getElementsByName(regexStr, parentElement, elementNames);
	}

	//根据节点名获取后代元素集合
	public ArrayList<Element> getGenerElementsByName(Element parentElement, String... elementNames) throws FlowException {
		
		String regexStr = ".//";

		return getElementsByName(regexStr, parentElement, elementNames);
	}
	
	//根据节点名获取所有元素集合
	public ArrayList<Element> getElementsByName(Element parentElement, String... elementNames) throws FlowException {
		
		return getElementsByName("", parentElement, elementNames);
	}
	
	//根据节点名和正则获取元素集合
	public ArrayList<Element> getElementsByName(String regexStr, Element parentElement, String... elementNames) throws FlowException {

		ArrayList<Element> data = new ArrayList<>();
		
		String params = "";
		
		for(String param:elementNames) {
			
			params += param + "|";
		}
		
		params = params.equals("")?"*":params.substring(0, params.length()-1);
		
		if(parentElement != null) {
			
			data = (ArrayList<Element>)parentElement.selectNodes(regexStr + params);
		} else {
			
			throw new FlowException("this isn't find the parentElement: "+parentElement.getName());
		}
		
		return data;
	}
	
	public DomExample(String path, String xPath) throws FlowException {
		
		initDomExample(path, xPath);
	}
	
	public DomExample(String xPath) throws FlowException {
		
		initDomExample(SETTINGS_PATH, xPath);
	}
	
	public DomExample() throws FlowException {
		
		initDomExample(SETTINGS_PATH, "");
	}
	
	private void initDomExample(String path, String xPath) throws FlowException {
		
		SAXReader saxReader = new SAXReader();
		
		try {
			
			document = saxReader.read(new File(path));
			
			root = document.getRootElement();
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("init fail, the document can't read from: "+path);
		}
		
		try {

			location = xPath.equals("")? root : (Element) document.selectNodes(xPath).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("xPath is be parsed");
		}
	}

	public Document getDocument() {
		
		return document;
	}

	public Element getRoot() {
		
		return root;
	}

	public Element getLocation() {
		
		return location;
	}

	public void setLocation(String xPath) throws FlowException {
		
		try {
			
			location = (Element)location.selectNodes(xPath).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("xPath is wrong, please check it");
		}
	}
}
