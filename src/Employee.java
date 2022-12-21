public class Employee extends Person {
    private int months_worked;
    private double salary;

    public Employee(String name, int age) {
        super(name, age);
    }

    public Employee(String name, int age, int months_worked, double salary) {
        super(name,age);
        this.months_worked = months_worked;
        this.salary = salary;
    }
    public double thirteenthmonth(){
        return salary / (12.0 / months_worked);
    }

    public static class Clerk extends Employee{
        public Clerk(String name, int age, int months_worked, double salary) {
            super(name, age, months_worked, salary);
        }
    }

    public static class Manager extends Employee {
        public Manager (String name, int age, int months_worked, double salary) {
            super(name, age, months_worked, salary);
        }
    }
}
