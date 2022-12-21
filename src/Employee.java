public class Employee extends Person {
    private static int months_worked;
    private static double salary;

    public Employee(String name, int age) {
        super(name, age);
    }

    public Employee(String name, int age, int months_worked, double salary) {
        super(name,age);
        Employee.months_worked = months_worked;
        Employee.salary = salary;
    }

    public static String getMonth(){

        return String.valueOf(months_worked);
    }

    public static double getSalary(){
        return salary;
    }
    public double thirteenthmonth(){
        return salary / (12.0 / months_worked);
    }


    public static class Clerk extends Employee{
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
