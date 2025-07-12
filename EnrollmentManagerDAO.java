package StudentCourseManagement;

import java.sql.*;

public class EnrollmentManagerDAO {
    private Connection conn;

    public EnrollmentManagerDAO(Connection conn) {
        this.conn = conn;
    }

    public void enrollStudent(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Student enrolled successfully!");
            } else {
                System.out.println("❌ Enrollment failed! No rows inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllEnrollments() {
        String sql = "SELECT s.name AS student_name, s.email, c.name AS course_name, c.duration " +
                     "FROM enrollments e " +
                     "JOIN students s ON e.student_id = s.id " +
                     "JOIN courses c ON e.course_id = c.id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📋 Enrollments:");
            while (rs.next()) {
                String studentName = rs.getString("student_name");
                String email = rs.getString("email");
                String courseName = rs.getString("course_name");
                String duration = rs.getString("duration");
                System.out.println("Student: " + studentName + " | Email: " + email +
                                   " | Course: " + courseName + " | Duration: " + duration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
