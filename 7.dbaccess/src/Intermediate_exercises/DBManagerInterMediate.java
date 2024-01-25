package Intermediate_exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManagerInterMediate {
    private static final String URL = "jdbc:postgresql://localhost:5432/student";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection createConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBの接続に失敗しました", e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBの切断に失敗しました", e);
        }
    }
}
