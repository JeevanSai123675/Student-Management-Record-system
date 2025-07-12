package StudentCourseManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id,name, email) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, student.getId());
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getEmail());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("✅ Student added successfully!");
    }

    public void showAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("\n--- List of Students ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                               " | Name: " + rs.getString("name") +
                               " | Email: " + rs.getString("email"));
        }
        stmt.close();
    }
}
