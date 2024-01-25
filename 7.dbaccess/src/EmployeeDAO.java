import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String TABLE_NAME = "employees";

    /**
     * 
     * @param id
     * @return
     */
    public Employee load(int id) {
        Connection con = DBManager.createConnection();

        String sql = "SELECT id, name, age, gender, department_id FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, id);
            ResultSet result = pStatement.executeQuery();

            if (result.next()) {
                Employee employee = new Employee();
                employee.setId(result.getInt("id"));
                employee.setName(result.getString("name"));
                employee.setAge(result.getInt("age"));
                employee.setGender(result.getString("gender"));
                employee.setDepartmentId(result.getInt("department_id"));
                return employee;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("load処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    /**
     * 
     * 
     * @param departmentId
     * @return
     */
    public List<Employee> findByDepartmentId(int departmentId) {
        Connection con = DBManager.createConnection();
        String sql = "SELECT id, name, age, gender, department_id FROM " + TABLE_NAME + " WHERE department_id = ?";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, departmentId);
            ResultSet result = pStatement.executeQuery();

            List<Employee> employeeList = new ArrayList<>();

            while (result.next()) {
                Employee employee = new Employee();
                employee.setId(result.getInt("id"));
                employee.setName(result.getString("name"));
                employee.setAge(result.getInt("age"));
                employee.setGender(result.getString("gender"));
                employee.setDepartmentId(result.getInt("department_id"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("検索処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    public int insert(Employee employee) {
        Connection con = DBManager.createConnection();
        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, age, gender, department_id)"
                + " VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, employee.getId());
            pStatement.setString(2, employee.getName());
            pStatement.setInt(3, employee.getAge());
            pStatement.setString(4, employee.getGender());
            pStatement.setInt(5, employee.getDepartmentId());

            int affected = pStatement.executeUpdate();
            return affected;

        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("insert処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }
}
