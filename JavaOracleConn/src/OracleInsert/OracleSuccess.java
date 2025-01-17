package OracleInsert;

import java.sql.*;

public class OracleSuccess {
    public static void main(String[] args) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "c##sahmyook";
        String password = "1234";

        try {
            // 드라이버 로드
            Class.forName(driver);
            System.out.println("JDBC driver loading success.");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Oracle connection success.\n");
            Statement stmt = conn.createStatement();

            String ideptno = "50", sdname = "IT", sloc = "SEOUL";

            String checkSql = "SELECT COUNT(*) AS count FROM dept WHERE deptno = '" + ideptno + "'";
            ResultSet checkRs = stmt.executeQuery(checkSql);
            checkRs.next();
            int count = checkRs.getInt("count");

            if (count == 0) { 
                String sql = "INSERT INTO dept VALUES ('" + ideptno + "','" + sdname + "','" + sloc + "')";
                boolean b = stmt.execute(sql);
                if (!b) {
                    System.out.println("Insert success.\n");
                } else {
                    System.out.println("Insert fail.\n");
                }
            } else {
                System.out.println("Record with deptno " + ideptno + " already exists. Insert skipped.\n");
            }

            String sql2 = "SELECT deptno, dname, loc FROM dept";
            ResultSet rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                System.out.print(rs.getString("deptno") + "\t");
                System.out.print(rs.getString("dname") + "\t");
                System.out.println(rs.getString("loc") + " ");
            }

            // 연결 해제
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
