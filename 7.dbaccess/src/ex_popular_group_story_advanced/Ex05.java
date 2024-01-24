package ex_popular_group_story_advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex05 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        String sql = """
                DELETE
                FROM
                members
                WHERE
                name IN ('佐々木 慶蔵','櫻井 翔')
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            connection.setAutoCommit(false);
            try (PreparedStatement pStatement = connection.prepareStatement(sql)) {

                int numOfUpdate = pStatement.executeUpdate();

                System.out.println(numOfUpdate + "件データを操作しました");
                connection.commit();
            } catch (SQLException e) {
                if (connection != null && connection.isClosed()) {
                    System.err.println("SQL文の実行中に例外が発生したため、ロールバックします。");
                    connection.rollback();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
