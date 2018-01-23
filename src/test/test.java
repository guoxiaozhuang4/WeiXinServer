package test;

import org.dom4j.DocumentException;

import com.guoxiaozhuang4.exception.FlowException;
import com.guoxiaozhuang4.jsp.util.DomForJsp;

public class test {

	public static void main(String[] args) throws FlowException, DocumentException {
		
		DomForJsp domForJsp = new DomForJsp();
		
		System.out.println("val:"+domForJsp.getSqlDataValue("123"));
		
	}
}
