public class UpdateExercise {
    public static void main(String[] args) {
        DepartmentDAO dao = new DepartmentDAO();
        Department department = dao.load(1000);

        System.out.println("---更新前---");
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());

        department.setName("IT事業部");
        dao.update(department);

        department = dao.load(1000);
        System.out.println("---更新後---");
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());

    }
}
