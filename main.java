package StudentCourseManagement;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            EnrollmentManagerDAO enrollmentDAO = new EnrollmentManagerDAO(conn);
            StudentDAO studentDAO = new StudentDAO(conn);
            CourseDAO courseDAO = new CourseDAO(conn);
            EnrollmentManager enrollmentManager = new EnrollmentManager(conn);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n===== Student Course Management Menu =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Add Course");
                System.out.println("4. View Courses");
                System.out.println("5. Enroll Student to Course");
                System.out.println("6. View Enrollments");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String sName = sc.nextLine();
                        System.out.print("Enter student email: ");
                        String sEmail = sc.nextLine();
                        int studentId = new java.util.Random().nextInt(1000);
                        Student student = new Student(studentId, sName, sEmail);
                        studentDAO.addStudent(student);
                        break;
                    case 2:
                        studentDAO.showAllStudents();
                        break;
                    case 3:
                        System.out.print("Enter course name: ");
                        String cName = sc.nextLine();
                        System.out.print("Enter course duration: ");
                        String cDuration = sc.nextLine();
                        int courseId = new java.util.Random().nextInt(1000);
                        Course course = new Course(courseId, cName, cDuration);
                        courseDAO.addCourse(course);
                        break;
                    case 4:
                        List<Course> courses = courseDAO.showAllCourses();
                        for (Course c : courses) {
                            System.out.println("ID: " + c.getId() + " | Name: " + c.getName() + " | Duration: " + c.getDuration());
                        }
                        break;
                    case 5:
                        System.out.print("Enter student ID: ");
                        int studentId1 = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter course ID: ");
                        int courseId1 = sc.nextInt();
                        sc.nextLine();
                        enrollmentDAO.enrollStudent(studentId1, courseId1);
                        enrollmentManager.enrollStudent(studentId1, courseId1);
                        break;
                    case 6:
                        enrollmentManager.showEnrollments();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        conn.close();
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
