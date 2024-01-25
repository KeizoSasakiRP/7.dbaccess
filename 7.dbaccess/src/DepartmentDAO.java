import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {
    private static final String TABLE_NAME = "departments";

    /**
     * departmentsデーブルの主キーをもとにDepartmentオブジェクトをロードする
     * 
     * @param id Departmentsテーブルの主キーであるidの値
     * @return 主キーに対応するテーブルの業の情報をもつDepartmentオブジェクト
     */
    public Department load(int id) {
        Connection con = DBManager.createConnection();
        String sql = "SELECT id, name FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, id);
            ResultSet result = pStatement.executeQuery();

            if (result.next()) {
                Department department = new Department();
                department.setId(result.getInt("id"));
                department.setName(result.getString("name"));
                return department;
            }
            return null;
        } catch (Exception e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("Loadの処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }

    }

    /**
     * 
     * @param department
     * @return
     */
    public int insert(Department department) {
        Connection con = DBManager.createConnection();
        String sql = "INSERT INTO " + TABLE_NAME + "(id, name) VALUES (?, ?)";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, department.getId());
            pStatement.setString(2, department.getName());

            int num = pStatement.executeUpdate();
            return num;
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("Insert処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    /**
     * 
     * @param department
     * @return
     */
    public int update(Department department) {
        Connection con = DBManager.createConnection();
        String sql = "UPDATE " + TABLE_NAME + " SET name = ? WHERE id = ?";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, department.getName());

            pStatement.setInt(2, department.getId());

            int num = pStatement.executeUpdate();
            return num;
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("Update処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    /**
     * 
     * 
     * @param id
     * @return
     */
    public int deleteById(int id) {
        Connection con = DBManager.createConnection();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, id);
            int num = pStatement.executeUpdate();

            return num;
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("Delete処理が失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }
}
