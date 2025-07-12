package StudentCourseManagement;

public class Course {
    private int id;
    private String name;
    private String duration;
    public Course(int id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDuration() { return duration; }
}
