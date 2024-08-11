package DesignPatterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {


    String name;
    String dept;
    Double salary;

    List<Employee> reportees;


    Employee(String name, String dept, Double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.reportees = new ArrayList<>();
    }


    public void addEmployee(Employee employee) {
        reportees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        reportees.remove(employee);
    }

    public List<Employee> getReportees() {
        return this.reportees;
    }

    public String toString() {
        return "Name : " + name + " Salary: " + salary + " Department : " + dept;
    }


    public static void main(String[] args) {

        Employee CEO = new Employee("Alex Chriss", "Executive Office", 50000000.00);

        Employee marketingHead = new Employee("John Broda", "Marketing", 30000000.00);
        Employee salesHead = new Employee("Bradley james", "Sales", 30000000.00);

        CEO.addEmployee(marketingHead);
        CEO.addEmployee(salesHead);

        Employee marketingExecutive1 = new Employee("Hardy long", "Marketing", 80000.00);
        Employee marketingExecutive2 = new Employee("Beaver Musk", "Marketing", 95000.00);

        marketingHead.addEmployee(marketingExecutive1);
        marketingHead.addEmployee(marketingExecutive2);

        Employee salesExecutive1 = new Employee("Bill jones", "Sales", 100000.00);
        Employee salesExecutive2 = new Employee("Nancy james", "Sales", 150000.00);

        salesHead.addEmployee(salesExecutive1);
        salesHead.addEmployee(salesExecutive2);

        System.out.println(CEO);

        for (Employee CEOreportee : CEO.getReportees()) {

            System.out.println(CEOreportee);

            for (Employee Level2Reportee : CEOreportee.getReportees()) {
                System.out.print("Employee under " + CEOreportee.name + " is ");
                System.out.println(Level2Reportee);
            }
        }
    }
}