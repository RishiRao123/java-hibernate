import dao.StudentDAO;
import entity.Student;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentDAO studentDAO = new StudentDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student By ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Find Students By Course");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudentById();
                case 3 -> viewAllStudents();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> findStudentsByCourse();
                case 7 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        scanner.close();
    }

    // 1. Add Student
    private static void addStudent() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        System.out.print("Enter marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setMarks(marks);

        studentDAO.saveStudent(student);

        System.out.println("Student added successfully!");
    }

    // 2. View Student By ID
    private static void viewStudentById() {

        System.out.print("Enter student ID: ");
        Long id = scanner.nextLong();

        Student student = studentDAO.getStudentById(id);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    // 3. View All Students
    private static void viewAllStudents() {

        List<Student> students = studentDAO.getAllStudents();

        if (students != null && !students.isEmpty()) {
            students.forEach(System.out::println);
        } else {
            System.out.println("No students found.");
        }
    }

    // 4. Update Student
    private static void updateStudent() {

        System.out.print("Enter student ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Student student = studentDAO.getStudentById(id);

        if (student != null) {

            System.out.print("Enter new name: ");
            student.setName(scanner.nextLine());

            System.out.print("Enter new email: ");
            student.setEmail(scanner.nextLine());

            System.out.print("Enter new course: ");
            student.setCourse(scanner.nextLine());

            studentDAO.updateStudent(student);

            System.out.println("Student updated successfully!");

        } else {
            System.out.println("Student not found!");
        }
    }

    // 5. Delete Student
    private static void deleteStudent() {

        System.out.print("Enter student ID to delete: ");
        Long id = scanner.nextLong();

        studentDAO.deleteStudent(id);

        System.out.println("Delete operation completed.");
    }

    // 6. Find Students By Course
    private static void findStudentsByCourse() {

        System.out.print("Enter course name: ");
        String course = scanner.nextLine();

        List<Student> students = studentDAO.getStudentsByCourse(course);

        if (students != null && !students.isEmpty()) {
            students.forEach(System.out::println);
        } else {
            System.out.println("No students found for this course.");
        }
    }
}
