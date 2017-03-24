package gitTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCClass {

	private Connection conn = null;

	public JDBCClass(String driverClassName, String DBURL, String db, String DBUSER, String DBPASS) {

		switch (driverClassName) {
		case "mysql":
			driverClassName = "com.mysql.jdbc.Driver";
			DBURL = "jdbc:mysql://" + DBURL + ":3306/" + db;
			break;

		case "psql":
			driverClassName = "org.postgresql.Driver";
			DBURL = "jdbc:postgresql://" + DBURL + ":5432/" + db;
			break;

		default:
			System.out.println("不支持此数据库");
			break;
		}

		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {

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

				e.printStackTrace();
			}
		}
	}
}