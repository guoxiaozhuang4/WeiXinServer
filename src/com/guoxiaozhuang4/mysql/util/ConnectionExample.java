package com.guoxiaozhuang4.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class ConnectionExample {

	// 连接属性，用数组储存
	private HashMap<String, String> connMess = new HashMap<>();

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private int col;
	private int row;
	private int updateCount;

	private String sql;

	/**
	 * @author guoxiaozhuang4
	 * @Description 初始化参数
	 */
	private void initConnParam(String sql) {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/guoxiaozhuang4?useSSL=false";
		String user = "guoxiaozhuang4";
		String pass = "Qq19660425";

		initConnParam(driver, url, user, pass, sql);
	}

	private void initConnParam(String driver, String url, String user, String pass, String sql) {
		
		connMess.put("driver", driver);
		connMess.put("url", url);
		connMess.put("user", user);
		connMess.put("pass", pass);

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/guoxiaozhuang4?useSSL=false", "guoxiaozhuang4", "Qq19660425");

			preparedStatement = connection.prepareStatement(sql);

			boolean result = preparedStatement.execute();
			
			if (result) {

				resultSet = preparedStatement.getResultSet();
				
				col = resultSet.getMetaData().getColumnCount();
				
				resultSet.last();
				row = resultSet.getRow();
				resultSet.beforeFirst();
			} 

			updateCount = preparedStatement.getUpdateCount();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public ConnectionExample(String sql) {

		initConnParam(sql);
	}

	public ConnectionExample(String driver, String url, String user, String pass, String sql) {

		initConnParam(driver, url, user, pass, sql);
	}

	public HashMap<String, String> getConnMess() {
		return connMess;
	}

	public void setConnMess(HashMap<String, String> connMess) {
		this.connMess = connMess;
	}

	public Connection getConnection() {
		return connection;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public int getCol() {
		return col;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getUpdateCount() {
		return updateCount;
	}
	
	public int getRow() {
		return row;
	}
}
