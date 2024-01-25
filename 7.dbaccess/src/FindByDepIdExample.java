import java.util.List;

public class FindByDepIdExample {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employeeList = dao.findByDepartmentId(2);

        for(Employee employee : employeeList){
            System.out.println("name = " + employee.getName());
        }
    }
}
