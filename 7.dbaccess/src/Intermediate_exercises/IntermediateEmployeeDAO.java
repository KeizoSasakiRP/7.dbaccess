package Intermediate_exercises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class IntermediateEmployeeDAO {
    private static final String TABLE_NAME1 = "employees";
    private static final String TABLE_NAME2 = "departments";

    public List<Employee> load(int id){
        Connection con = DBManagerInterMediate.createConnection();
        String sql = "SELECT * FROM " + TABLE_NAME1 + " WHERE id = ?";

        List<Department> departmentList = new ArrayList<>();
        try {
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, id);
            
            
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
