/**
 * 	@date: 2018年1月23日下午11:20:52
 */
package com.guoxiaozhuang4.jsp.util;

import java.util.List;

import org.dom4j.Element;

import com.guoxiaozhuang4.exception.FlowException;
import com.guoxiaozhuang4.util.domhandle.DomExample;

/**
 * @author guoxiaozhuang4
 * @Decription TODO
 * @date 下午11:20:52
 */
public class DomForJsp {

	private DomExample domExample;
	
	
	//获取sqlDataSettings
	public String getSqlDataValue(String name) throws FlowException {
		
		return getSettingsDataValue("sqlData", name);
	}
	
	//获取settings值
	public String getSettingsDataValue(String id, String name) throws FlowException {
		
		String value = "";
		
		try {
			
			domExample.setLocation("./class[@id=\""+id+"\"]");
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("init location param be failed");
		}
		
		List<Element> datas = domExample.getLocationSonElements("data");
		
		for(Element data:datas) {
			
			if(data.attributeValue("name").equals(name)) {
				
				value = data.attributeValue("value");
			}
			
			break;
		}
		
		return value;
	}
	
	private void initDomExam(String path, String xPath) throws FlowException {
		
		try {
			
			domExample = new DomExample(path, xPath);
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("init domExample be failed");
		}
	}

	public DomForJsp(String path, String xPath) throws FlowException {

		initDomExam(path, xPath);
	}
	
	public DomForJsp(String xPath) throws FlowException {

		String path = "G:\\workspace\\WeiXinServer\\WebContent\\settings.xml";
		
		initDomExam(path, xPath);
	}
	
	public DomForJsp() throws FlowException {

		String path = "G:\\workspace\\WeiXinServer\\WebContent\\settings.xml";
		
		initDomExam(path, "");
	}

	public DomExample getDomExample() {
		
		return domExample;
	}

	public void setDomExample(String path, String xPath) throws FlowException {
		
		initDomExam(path, xPath);
	}
	
	public void setDomExample(String xPath) throws FlowException {
		
		String path = "G:\\workspace\\WeiXinServer\\WebContent\\settings.xml";
		
		initDomExam(path, xPath);
	}
	
	public void setDomExample() throws FlowException {
		
		String path = "G:\\workspace\\WeiXinServer\\WebContent\\settings.xml";
		
		initDomExam(path, "");
	}
}
