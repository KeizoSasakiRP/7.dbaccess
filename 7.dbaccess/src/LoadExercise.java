public class LoadExercise {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        Department department = departmentDAO.load(1);

        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());
    }
}
