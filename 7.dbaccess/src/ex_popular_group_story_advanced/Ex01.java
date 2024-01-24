package ex_popular_group_story_advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            sql = """
                    DROP TABLE IF EXISTS colors;
                    CREATE TABLE colors(
                        id  integer primary key,
                        name text
                    )
                    """;

            pStatement = connection.prepareStatement(sql);
            pStatement.executeUpdate();
            System.out.println("実行されたSQL「" + sql + "」");
            pStatement.close();

            sql = """
                    DROP TABLE IF EXISTS members;
                    CREATE TABLE members(
                        id  serial primary key,
                        name    text not null,
                        birth_day   date,
                        gender  varchar(1),
                        color_id    integer REFERENCES colors(id)
                    )
                    """;
            pStatement = connection.prepareStatement(sql);
            pStatement.executeUpdate();
            System.out.println("実行されたSQL「" + sql + "」");

        } catch (SQLException e) {
            System.err.println("実行されたSQL「" + sql + "」");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
