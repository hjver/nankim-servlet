package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public Connection getConnection() {
		
		Connection con = null;
		
		try {
			String db_driver = "com.mysql.cj.jdbc.Driver";
			String db_url = "jdbc:mysql://localhost:3306/mrp";
			String db_user = "project";
			String db_passwd = "a123456";
			
			Class.forName(db_driver);
			con = DriverManager.getConnection(db_url, db_user, db_passwd);
		}
		catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패: " + e.getMessage());
		}
		
		return con;
	}
}
