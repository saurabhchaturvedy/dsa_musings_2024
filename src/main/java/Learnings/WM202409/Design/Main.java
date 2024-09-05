package Learnings.WM202409.Design;

import java.util.*;

class Employee implements Comparable<Employee> {
    private final String id;
    private final String name;
    private final int age;

    public Employee(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(final Employee employee) {
        return this.name.compareTo(employee.name);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d", id, name, age);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("3", "John", 32));
        employees.add(new Employee("1", "James", 27));
        employees.add(new Employee("2", "Jackson", 22));

        Collections.sort(employees);

        employees.forEach(employee -> System.out.println(employee.toString()));
    }
}