public class DeleteExercise {
    public static void main(String[] args) {
        DepartmentDAO dao = new DepartmentDAO();
        dao.deleteById(1000);

        Department department = dao.load(1000);
        System.out.println(department);

    }
}
