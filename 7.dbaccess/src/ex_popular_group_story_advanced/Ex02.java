package ex_popular_group_story_advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex02 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        String sql1 = """
                INSERT INTO colors (id, name) VALUES
                (1, 'blue'),
                (2, 'red'),
                (3, 'green'),
                (4, 'yellow'),
                (5, 'purple'),
                (6, 'orange');
                """;

        String sql2 = """
                INSERT INTO members
                (name, birth_day, gender, color_id) VALUES
                ('大野 智','1980-11-26','男',1),
                ('櫻井 翔','1982-01-25','男',2),
                ('相葉 雅紀','1982-12-24','男',3),
                ('二宮 和也','1983-06-17','男',4),
                ('松本 潤','1983-08-30','男',5);
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            connection.setAutoCommit(false);
            try (PreparedStatement pStatement = connection.prepareStatement(sql1);
                    PreparedStatement pStatement2 = connection.prepareStatement(sql2)) {
                pStatement.executeUpdate();
                System.out.println("実行されたSQL「" + sql1 + "」");

                pStatement2.executeUpdate();
                System.out.println("実行されたSQL「" + sql2 + "」");
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
