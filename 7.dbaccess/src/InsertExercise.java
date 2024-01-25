public class InsertExercise {
    public static void main(String[] args) {
        DepartmentDAO dao = new DepartmentDAO();
        Department department = new Department();

        department.setId(1000);
        department.setName("システム情報部");

        dao.insert(department);

        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());
    }
}
