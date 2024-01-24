package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex02 {
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
                    INSERT INTO members
                    (name, birth_day, gender, color_id) VALUES
                    ('大野 智','1980-11-26','男',1),
                    ('櫻井 翔','1982-01-25','男',2),
                    ('相葉 雅紀','1982-12-24','男',3),
                    ('二宮 和也','1983-06-17','男',4),
                    ('松本 潤','1983-08-30','男',5);
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
