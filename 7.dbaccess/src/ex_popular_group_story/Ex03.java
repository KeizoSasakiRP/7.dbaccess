package ex_popular_group_story;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex03 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = """
                    SELECT
                    *
                    FROM
                    members;
                      """;
            pre = con.prepareStatement(sql);
            result = pre.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                Date birthDay = result.getDate("birth_day");
                String gender = result.getString("gender");
                int colorId = result.getInt("color_id");
                System.out.print("name=" + name);
                System.out.print(" birth_day=" + birthDay);
                System.out.print(" gender=" + gender);
                System.out.print(" color_id=" + colorId);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("実行されたSQL「" + sql + "」");
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
