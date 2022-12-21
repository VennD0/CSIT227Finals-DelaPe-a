
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

    public int getMonth(){

        return months_worked;
    }

    public double getSalary(){
        return salary;
    }
    public double thirteenthmonth(){
        return salary / (12.0 / months_worked);
    }


    public static class Clerk extends Employee {
        public Clerk(String name, int age, int months_worked, double salary) {
            super(name, age, months_worked, salary);
        }
        @Override
        public String to_String(){
            return super.to_String()+ " How may I help you?";
        }
    }

    public static class Manager extends Employee {
        public Manager (String name, int age, int months_worked, double salary) {
            super(name, age, months_worked, salary);
        }
    }
}