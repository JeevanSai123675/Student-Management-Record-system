package StudentCourseManagement;

import java.sql.*;

public class EnrollmentManager {
    private Connection conn;

    public EnrollmentManager(Connection conn) {
        this.conn = conn;
    }

    public void enrollStudent(int sid, int cid) {
        try {
            String checkSql = "SELECT * FROM enrollments WHERE student_id = ? AND course_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, sid);
            checkStmt.setInt(2, cid);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("⚠️ Student is already enrolled in this course!");
                return;
            }

            String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sid);
            stmt.setInt(2, cid);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Student enrolled successfully!");
            } else {
                System.out.println("❌ Enrollment failed!");
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

    public void showEnrollments() {
        showAllEnrollments();
    }
}
