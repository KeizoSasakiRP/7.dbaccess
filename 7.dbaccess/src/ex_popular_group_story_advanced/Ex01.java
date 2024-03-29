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

        String sql1 = """
                DROP TABLE IF EXISTS colors;
                CREATE TABLE colors(
                    id  integer primary key,
                    name text
                )
                """;

        String sql2 = """
                DROP TABLE IF EXISTS members;
                CREATE TABLE members(
                    id  serial primary key,
                    name    text not null,
                    birth_day   date,
                    gender  varchar(1),
                    color_id    integer REFERENCES colors(id)
                )
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            connection.setAutoCommit(false); // 演習7
            // Javaのtry-with-resourcesを利用してrollback
            try (PreparedStatement pStatement = connection.prepareStatement(sql1);
                    PreparedStatement pStatement2 = connection.prepareStatement(sql2);) {
                pStatement.executeUpdate();
                System.out.println("実行されたSQL「" + sql1 + "」");
                pStatement2.executeUpdate();
                System.out.println("実行されたSQL「" + sql2 + "」");
                connection.commit(); // 演習7
            } catch (SQLException e) {
                if (connection != null && !connection.isClosed()) {
                    System.err.println("SQL文の実行中に例外が発生したため、ロールバックします。");
                    connection.rollback(); // 演習7
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
