package Encapsulation;

import java.util.ArrayList;

public class EmployeePojo {
    private int emp_id;
    private String name;
    private int age;
    private String salary;
    private ArrayList <String> tech_stack = new ArrayList<>();
    EmployeePojo() {}
    EmployeePojo(int emp_id, String name, int age, String salary, ArrayList <String> stack) {
        this.emp_id = emp_id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.tech_stack = stack;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public ArrayList<String> getTech_stack() {
        return tech_stack;
    }

    public void setTech_stack(ArrayList<String> tech_stack) {
        this.tech_stack = tech_stack;
    }

    @Override
    public String toString() {
        return "EmployeePojo{" +
                "emp_id=" + emp_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary='" + salary + '\'' +
                ", tech_stack=" + tech_stack +
                '}';
    }
}
