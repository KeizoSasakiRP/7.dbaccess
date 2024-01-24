package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex05 {
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
                    DELETE
                    FROM
                    members
                    WHERE
                    name IN ('佐々木 慶蔵','櫻井 翔')
                    """;

            pStatement = connection.prepareStatement(sql);
            int numOfUpdate = pStatement.executeUpdate();

            System.out.println(numOfUpdate + "件データを操作しました");

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
