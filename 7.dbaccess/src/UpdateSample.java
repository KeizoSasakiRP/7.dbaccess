import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSample {
    public static void main(String[] args) {
        // 接続情報
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        // 使用する変数の宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            // (1)データベースに接続
            con = DriverManager.getConnection(url, user, password);

            // (2)SQL文を作成
            sql = """
                    INSERT INTO employees
                    (name, age) VALUES('テスト太郎',19);
                    """;

            // (3)SQL実行準備
            pstmt = con.prepareStatement(sql);

            // (4)SQL実行
            int numOfUpdate = pstmt.executeUpdate();

            // (5)結果の出力
            System.out.println(numOfUpdate + "件のデータを操作しました。");
            
        } catch (SQLException e) {
            System.err.println("SQL関連の例外が発生しました");
            System.err.println("発行したSQL文は「" + sql + "」");
            e.printStackTrace();
        } finally {
            // (6)メモリ(リソース)の解放(切断処理)
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
