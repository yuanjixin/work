package gitTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestService {

	@SuppressWarnings("resource")
	public List<TestModel> findAll(String driverClassName, String DBURL, String db, String DBUSER, String DBPASS, String str) throws Exception {
		JDBCClass jdc = new JDBCClass(driverClassName, DBURL, db, DBUSER, DBPASS);
		Connection conn = jdc.getConnection();
		String sql = null;
		PreparedStatement pstmt = null;
		List<TestModel> list = new ArrayList<TestModel>();
		String driverName = conn.getMetaData().getDriverName();

		try {
			if (driverName.equals("PostgreSQL JDBC Driver")) {
				sql = "select A.relname,B.reltuples from pg_stat_user_tables A,pg_class B where A.relname=B.relname order by A.relname";
				pstmt = conn.prepareStatement(sql);
				if (str != null) {
					sql = "select A.relname,B.reltuples from pg_stat_user_tables A,pg_class B where A.relname=B.relname and A.relname =? order by A.relname";
					pstmt = jdc.getConnection().prepareStatement(sql);
					pstmt.setString(1, str);
				}
			} else if (driverName.equals("MySQL-AB JDBC Driver")) {
				String url = conn.getMetaData().getURL();
				String a = url.substring(url.lastIndexOf("/") + 1);
				sql = "select table_name,table_rows from information_schema.tables where table_schema= ? and table_type='base table'order by table_rows desc;";
				pstmt = jdc.getConnection().prepareStatement(sql);
				pstmt.setString(1, a);
				if (str != null) {
					sql = "SELECT t.TABLE_NAME as NAME ,t.TABLE_ROWS as rows FROM information_schema.TABLES AS t WHERE t.TABLE_SCHEMA = ? and t.TABLE_NAME = ?";
					pstmt = jdc.getConnection().prepareStatement(sql);
					pstmt.setString(1, a);
					pstmt.setString(2, str);
				}
			}

			ResultSet rs = pstmt.executeQuery();
			Integer num1 = 0;
			Integer num2 = 0;
			System.out.print("表名             ");
			System.out.println("表行数");
			while (rs.next()) {
				System.out.print(rs.getString(1) + "   ");
				System.out.println(rs.getInt(2));
				num1++;
				num2 = num2 + rs.getInt(2);
			}
			System.out.println("合计：" + num1 + "      " + num2);
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e) {
			}
		}
		return list;
	}
}