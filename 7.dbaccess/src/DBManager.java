import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBの接続と切断を管理するクラス
 * 
 * @author keizosasaki
 * 
 */
public class DBManager {
    private final static String URL = "jdbc:postgresql://localhost:5432/student";
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "postgres";

    /**
     * DBに接続
     * 
     * @return 接続情報
     */
    public static Connection createConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DBの接続に失敗しました", e);
        }
    }

    /**
     * DBから切断
     * 
     * @param con 接続オブジェクト
     * 
     */
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
