package gitTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCClass {
	private static final String driverClassName = "org.postgresql.Driver";
	private static final String DBURL = "jdbc:postgresql://192.168.1.250:5432/cpm_db";
	private static final String DBUSER = "cpm";
	private static final String DBPASS = "cpm";

	// public static final String driverClassName = "com.mysql.jdbc.Driver";
	// public static final String DBURL = "jdbc:mysql://localhost:3306/giit";
	// public static final String DBUSER = "root";
	// public static final String DBPASS = "1";
	private Connection conn = null;

	public JDBCClass() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
