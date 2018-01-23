package com.guoxiaozhuang4.jsp.util;

import com.guoxiaozhuang4.exception.FlowException;

public class jspAction {

	private ConnForJsp connForJsp;
	private DomForJsp domForJsp;

	//init sql
	public jspAction initConnExam(String sql) {

		connForJsp = new ConnForJsp(sql);
		
		return this;
	}
	
	//get connForJsp
	public ConnForJsp getConnForJsp() {
		return connForJsp;
	}
	
	//init dom
	public jspAction initDomExam() throws FlowException {
		
		try {
			
			domForJsp = new DomForJsp();
		} catch (Exception e) {
			// TODO: handle exception
			throw new FlowException("init domExam be failed");
		}
		
		return this;
	}
	
	//get domForJsp
	public DomForJsp getDomForJsp() {
		
		return domForJsp;
	}
}
