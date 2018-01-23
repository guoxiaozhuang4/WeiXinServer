package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.guoxiaozhuang4.util.publicParameterUtil;

public class parameterUtilTest {

	public static void main(String[] args) throws Exception {
		
		//publicParameterUtil.makeParameterFile("PublicParameter");
		
		//boolean isSuccess = publicParameterUtil.setParameterValue("myLove", "wangfen");
		//boolean isSuccess = publicParameterUtil.setParameterValue("myName", "guoxiaozhuang4");
		
		//System.out.println("isSuccess:" + isSuccess);

//		publicParameterUtil.setParameterValue("MysqlDriver", "com.mysql.jdbc.driver");
//		publicParameterUtil.setParameterValue("MysqlUser", "guoxiaozhuang4");
//		publicParameterUtil.setParameterValue("MysqlPassword", "Qq19660425");
//		publicParameterUtil.setParameterValue("MysqlUrl", "jdbc:mysql://localhost/guoxiaozhuang4?useUnicode=true&characterEncoding=utf-8&useSSL=false");
		
		String driver = publicParameterUtil.getParameterValue("MysqlDriver");
		String url = publicParameterUtil.getParameterValue("MysqlUrl");
		String user = publicParameterUtil.getParameterValue("MysqlUser");
		String password = publicParameterUtil.getParameterValue("MysqlPassword");
		
		Class.forName(driver);
		
		String sql = "SELECT ULI_NO FROM USER_LOGIN_INFO WHERE ULI_NAME = ? AND ULI_PASSWORD = ?";
		
		try(
				
				Connection connection = DriverManager.getConnection(url, user, password);
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				) {
			
			preparedStatement.setString(1, "guoxiaozhuang4");
			preparedStatement.setString(2, "Qq1966042");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				System.out.println(1);
			} else {
				
				System.out.println(-1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}