package gitTest;

public class Maintest {

	public static void main(String[] args) {

		TestService t = new TestService();
		try {
			// String str = null;
			// str = "com_address";//需要查询单个表的时候输入表名
			if (args.length == 1) {
				t.findAll(args[0]);
			} else {
				t.findAll(null);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
