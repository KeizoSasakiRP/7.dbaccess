import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet result = null;
        String sql = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            // sql = "SELECT id, name, age FROM employees order by age;";
            sql = """
                    Slect
                    id,
                    name,
                    age
                    from
                    employees
                    order by age;
                    """;
            pStatement = con.prepareStatement(sql);
            result = pStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");

                System.out.print("id= " + id);
                System.out.print(" name=" + name);
                System.out.print(" age=" + age);
                System.out.println();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (pStatement != null) {
                pStatement.close();
            }
            if (result != null) {
                result.close();
            }
        }

    }
}
