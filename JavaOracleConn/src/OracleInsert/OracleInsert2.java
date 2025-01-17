package OracleInsert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleInsert2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##sahmyook";
		String password = "1234";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			// 1. select --------------------------------------
			String sql1 = "SELECT MAX(deptno) FROM dept";
			ResultSet rs1 = stmt.executeQuery(sql1);

			rs1.next();
			int iDeptno = rs1.getInt("MAX(deptno)");
			System.out.println(iDeptno);

			// 2. insert --------------------------------------

			String sdname = "IT", sloc = "SEOUL";
			String sql2 = "INSERT INTO dept VALUES ('" + (iDeptno + 10) + "','" + sdname + "','" + sloc + "')";
			System.out.println(sql2);
			boolean b = stmt.execute(sql2);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("Insert fail.\n");
			}

			// 3. select --------------------------------------
			String sql3 = "SELECT deptno, dname, loc FROM dept ORDER BY deptno";
			ResultSet rs2 = stmt.executeQuery(sql3);
			while (rs2.next()) {
				System.out.print(rs2.getString("deptno") + "\t");
				System.out.print(rs2.getString("dname") + "\t");
				System.out.println(rs2.getString("loc"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}