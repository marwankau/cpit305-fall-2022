package lecture21.tcpip.obj;

import java.io.Serializable;

public class Employee implements Serializable {
  private int id;
  private String name;
  private double salary;

  public Employee(int id, String name, double salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  @Override
  public String toString() {
      return String.format("\nid: %d\nname: %s\nsalary: %.2f\n\n", id, name, salary);
  }
  
}
