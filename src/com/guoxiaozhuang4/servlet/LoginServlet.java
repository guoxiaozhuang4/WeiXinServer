package com.guoxiaozhuang4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guoxiaozhuang4.util.publicParameterUtil;

/**
 * @author guoxiaozhuang4
 * @explain ´¦ÀíµÇÂ½µÄservlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		boolean isLoginSucess = checkAccount(user, pass);

		PrintWriter out = response.getWriter();

		if (isLoginSucess == true) {

			out.println("login is sucess");
		} else {

			out.print("username or password is wrong");
		}
	}

	private boolean checkAccount(String userName, String pass) {

		boolean isLoginSucess = false;

		String driver = publicParameterUtil.getParameterValue("MysqlDriver");
		String url = publicParameterUtil.getParameterValue("MysqlUrl");
		String user = publicParameterUtil.getParameterValue("MysqlUser");
		String password = publicParameterUtil.getParameterValue("MysqlPassword");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT ULI_NO FROM USER_LOGIN_INFO WHERE ULI_NAME = ? AND ULI_PASSWORD = ?";

		try (

				Connection connection = DriverManager.getConnection(url, user, password);

				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				) {

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, pass);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				isLoginSucess = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return isLoginSucess;
	}
}
