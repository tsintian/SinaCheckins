package daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBManager {
	static Logger logger = LogManager.getLogger(DBManager.class);
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/weibodb";
			conn = DriverManager.getConnection(url, "weibo", "weibo");
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("exceptions when connect the database", e);
		}
		return null;
	}
}
