package lecture21.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BestPractice {
    
  public static void main(String[] args) throws SQLException {
    Scanner scan = new Scanner(System.in);

System.out.print("Enter id of emp: ");
    int id = scan.nextInt();

    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305", "root", "123456");
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?;");
    // ResultSet result = stmt.exc

    // while (result.next()) {
    //   int id = result.getInt("id");
    //   String name = result.getString("name");
    //   double salary = result.getDouble("salary");

    //   System.out.printf("%10d  %15s  %10.2f\n", id, name, salary);

    // }

    conn.close();
  }
}
