package src;

    import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:student.db";

        try (Connection conn = DriverManager.getConnection(url);
             Scanner input = new Scanner(System.in)) {

            StudentDAO dao = new StudentDAO(conn);
            dao.createTable();

            while (true) {

                System.out.println("\n===== STUDENT SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Delete Student");
                System.out.println("4. Update Student");
                System.out.println("5. Search Student");
                System.out.println("6. Exit");
                System.out.print("Choose: ");

                int choice = input.nextInt();

                if (choice == 1) {

                    input.nextLine();

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Age: ");
                    int age = input.nextInt();

                    System.out.print("Grade: ");
                    String grade = input.next();

                    dao.addStudent(new Student(name, age, grade));
                    System.out.println("Student added!");

                } else if (choice == 2) {

                    dao.getAllStudents();

                } else if (choice == 3) {

                    System.out.print("ID: ");
                    int id = input.nextInt();

                    dao.deleteStudent(id);
                    System.out.println("Deleted!");

                } else if (choice == 4) {

                    System.out.print("ID: ");
                    int id = input.nextInt();

                    input.nextLine();

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Age: ");
                    int age = input.nextInt();

                    System.out.print("Grade: ");
                    String grade = input.next();

                    dao.updateStudent(id, new Student(name, age, grade));
                    System.out.println("Updated!");

                } else if (choice == 5) {

                    input.nextLine();

                    System.out.print("Search: ");
                    String keyword = input.nextLine();

                    dao.searchStudent(keyword);

                } else if (choice == 6) {

                    System.out.println("Goodbye!");
                    break;

                } else {
                    System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

