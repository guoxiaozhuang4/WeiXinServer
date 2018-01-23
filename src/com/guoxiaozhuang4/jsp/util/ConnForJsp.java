/**
 * 	@date: 2018年1月23日上午12:17:57
 */
package com.guoxiaozhuang4.jsp.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.guoxiaozhuang4.exception.FlowException;
import com.guoxiaozhuang4.mysql.util.ConnectionExample;

/**
 * @author guoxiaozhuang4
 * @Decription TODO
 * @date 上午12:17:57
 */
public class ConnForJsp {

	private ConnectionExample connectionExample;
	
	public ConnForJsp(String sql) {
		
		initConnExam(sql);
	}
	
	//init sql
	public void initConnExam(String sql) {

		connectionExample = new ConnectionExample(sql);
	}

	//get resultSet
	public ResultSet getResultSet() throws FlowException {

		if (connectionExample != null) {

			return connectionExample.getResultSet();
		} else {

			throw new FlowException("connExam hasn't init");
		}
	}

	//get resultSet col
	public int getCol() throws FlowException {

		if (connectionExample != null) {

			return connectionExample.getCol();
		} else {

			throw new FlowException("connExam hasn't init");
		}
	}

	//get resultSet row
	public int getRow() throws FlowException {

		if (connectionExample != null) {

			return connectionExample.getRow();
		} else {

			throw new FlowException("connExam hasn't init");
		}
	}
	
	//get updateCount
	public int getUpdateCount() throws FlowException {

		if (connectionExample != null) {

			int updateCount = connectionExample.getUpdateCount();
			
			return updateCount>0?updateCount:0;
		} else {

			throw new FlowException("connExam hasn't init");
		}
	}
	
	//return update success or not
	public boolean updateData() throws FlowException {
		
		if(getUpdateCount()>0) {

			return true;
		} else {
			
			throw new FlowException("please check your sql");
		}
	}
	
	//get query data 
	public HashMap<Integer, ArrayList<String>> queryData() throws FlowException {
		
		HashMap<Integer, ArrayList<String>> data = new HashMap<>();
		
		ResultSet resultSet = getResultSet();
		int col = getCol();
		
		int index = 0;
		
		try {
			
			while(resultSet.next()) {
				
				ArrayList<String> tmp = new ArrayList<>();
				
				for(int i=0;i<col;i++) {
					
					tmp.add(resultSet.getString(i+1));
				}
				
				data.put(index++, tmp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return data;
	}
	
	//get query data one row
	public ArrayList<String> queryDataOnce() throws FlowException {
		
		if(connectionExample.getRow()==1) {
			
			ArrayList<String> data = new ArrayList<>();
			
			return queryData().get(0);
		} else {
			
			throw new FlowException("more than one row");
		}
	}
	
	//get query data one col
	public String queryDataOnce(boolean oneCol) throws FlowException {
		
		if(oneCol) {
			
			try {
				
				return queryDataOnce().get(0);
			} catch (Exception e) {
				
				throw new FlowException(e.getMessage());
			}
		} else {
			
			throw new FlowException("parameter error");
		}
	}
}
