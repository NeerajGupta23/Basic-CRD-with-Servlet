package in.product.neeraj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Utility {

	public static Connection getConnection(HashMap<String, String> databaseData)
			throws SQLException, ClassNotFoundException {
		
		Class.forName(databaseData.get("driverName"));
		
		return DriverManager.getConnection(databaseData.get("url"), databaseData.get("user"),
				databaseData.get("password"));
	}

	public static void closeConnection(Connection connection, Statement statement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
