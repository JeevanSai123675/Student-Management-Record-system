package StudentCourseManagement;

import java.sql.*;
import java.util.*;

public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (id, name, duration) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getId());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getDuration());
            stmt.executeUpdate();
            System.out.println("✅ Course added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding course:");
            e.printStackTrace();
        }
    }

    public List<Course> showAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String duration = rs.getString("duration");
                Course c = new Course(id, name, duration);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching courses:");
            e.printStackTrace();
        }
        return list;
    }
}
