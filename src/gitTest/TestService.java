package gitTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestService {

	JDBCClass jdc = new JDBCClass();

	public List<TestModel> findAll(String str) throws Exception {
		PreparedStatement pstmt = null;
		List<TestModel> list = new ArrayList<TestModel>();

		String sql = "SELECT relname,reltuples FROM cpm_view";
		try {
			pstmt = jdc.getConnection().prepareStatement(sql);
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
