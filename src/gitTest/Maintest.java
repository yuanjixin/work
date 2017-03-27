package gitTest;

public class Maintest {

	public static void main(String[] args) {

		TestService t = new TestService();
		try {
			if (args.length == 6) {
				t.findAll(args[0], args[1], args[2], args[3], args[4], args[5]);
			} else if (args.length == 5) {
				t.findAll(args[0], args[1], args[2], args[3], args[4], null);
			} else {
				System.out.println("请输入参数");
				System.out.println("参数格式: 数据库类型(mysql/psql)   链接IP 数据库名称    用户名   密码 ");
				System.out.println("如果需要查看单表，在上述参数后面加上表名");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}