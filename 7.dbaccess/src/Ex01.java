import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet result = null;
        String sql = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            sql = """
                    SELECT
                        id,
                        name
                    FROM
                        departments
                    ORDER BY id;
                    """;
            pStatement = connection.prepareStatement(sql);
            result = pStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");

                System.out.print("id=" + id);
                System.out.print(" name=" + name);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("例外が発生しました");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (pStatement != null) {
                    pStatement.close();
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
