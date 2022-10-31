package lecture21.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Intro {
    
  public static void main(String[] args) throws SQLException {
    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305", "root", "123456");
    Statement stmt = conn.createStatement();
    ResultSet result = stmt.executeQuery("SELECT * FROM employees;");

    while (result.next()) {
      int id = result.getInt("id");
      String name = result.getString("name");
      double salary = result.getDouble("salary");

      System.out.printf("%10d  %15s  %10.2f\n", id, name, salary);

    }

    conn.close();
  }
}
