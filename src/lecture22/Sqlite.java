package lecture22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sqlite {
    public static void main(String[] args)  throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/lecture22/my.db");

        Scanner keyboard = new Scanner(System.in);
        String line = "";

        System.out.println(generateEmpID(conn));

        while (true) {
            System.out.println("Welcome to CPIT305 Database Browser:");
            System.out.println("1. list all employees");
            System.out.println("2. Insert new employee");
            System.out.println("3. update an employee");
            System.out.println("4. delete an employee");
            System.out.println("5. search by name");
            System.out.println("6. create employees table");
            System.out.println("7. exit");
            System.out.print("choose: ");
            line = keyboard.nextLine();

            if (line.equals("1")) {

                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery("SELECT * FROM employees;");

                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    double salary = result.getDouble("salary");

                    System.out.printf("%10d  %15s  %10.2f\n", id, name, salary);

                }
            } else if (line.equals("2")) {
                System.out.print("name: ");
                String name = keyboard.nextLine();
                
                System.out.print("salary: ");
                double salary = Double.parseDouble(keyboard.nextLine());

                PreparedStatement ps = conn.prepareStatement("INSERT INTO employees (`id`, `name`, `salary`) VALUES (?, ?, ?);");

                ps.setInt(1, generateEmpID(conn));
                ps.setString(2, name);
                ps.setDouble(3, salary);

                int n = ps.executeUpdate();
                System.out.println(n);

            } else if (line.equals("5")) {
                System.out.print("name or part of name to search for: ");
                String name = keyboard.nextLine();

                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE name LIKE ?;");

                ps.setString(1, "%" + name + "%");

                if (ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        name = rs.getString("name");
                        double salary = rs.getDouble("salary");
    
                        System.out.printf("%10d  %15s  %10.2f\n", id, name, salary);
                    }
                } else {
                    System.out.println("\nNothing found!\n");
                }
            } else if (line.equals("6")) {
                PreparedStatement ps = conn.prepareStatement("""
CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
                """);

                int n = ps.executeUpdate();
                System.out.println(n);
            } else if (line.equals("7")) {
                break;
            }
        }

        conn.close();        
    }

    private static int generateEmpID(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT MAX(id) as 'new_id' FROM employees;");
        rs.next();

        return rs.getInt("new_id") + 1;
    }
}
