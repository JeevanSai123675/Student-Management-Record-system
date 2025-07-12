package StudentCourseManagement;

public class Student extends Person{
    private int id;
    public Student(int id,String name,String email) {
        super(name,email);
        this.id=id;
    }
    public int getId() {
        return id;
    }
}
