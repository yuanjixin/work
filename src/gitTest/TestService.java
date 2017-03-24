package gitTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestService {

	JDBCClass jdc = new JDBCClass();

	public List<TestModel> findAll(String str) throws Exception {
		Connection conn = jdc.getConnection();
		String sql = null;
		PreparedStatement pstmt = null;
		List<TestModel> list = new ArrayList<TestModel>();

		sql = "CREATE OR REPLACE VIEW cpm_view AS SELECT  *   FROM (select A.relname,B.reltuples from pg_stat_user_tables A,pg_class B where A.relname=B.relname order by A.relname) a";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		sql = "SELECT relname,reltuples FROM cpm_view";
		try {
			pstmt = conn.prepareStatement(sql);
			if (str != null) {
				sql = "SELECT relname,reltuples FROM cpm_view where relname = ?";
				pstmt = jdc.getConnection().prepareStatement(sql);
				pstmt.setString(1, str);
			}
			ResultSet rs = pstmt.executeQuery();
			Integer num1 = 0;
			Integer num2 = 0;
			System.out.print("表名                 ");
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
