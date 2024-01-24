package ex_popular_group_story_advanced;

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

        String sql = """
                select
                m.name,
                m.birth_day,
                m.gender,
                col.name as color_name
                from
                members as m
                join
                colors as col
                on m.color_id = col.id;
                      """;

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pre = con.prepareStatement(sql);
                ResultSet result = pre.executeQuery();) {

            while (result.next()) {
                String name = result.getString("name");
                Date birthDay = result.getDate("birth_day");
                String gender = result.getString("gender");
                String colorName = result.getString("color_name");
                System.out.print("name=" + name);
                System.out.print(" birth_day=" + birthDay);
                System.out.print(" gender=" + gender);
                System.out.print(" color_name=" + colorName);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("実行されたSQL「" + sql + "」");
            e.printStackTrace();
        }
    }
}
