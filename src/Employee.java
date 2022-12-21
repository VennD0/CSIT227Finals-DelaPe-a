public class Employee extends Person {
    private int months_worked;
    private double salary;

    public Employee(String name, int age) {
        super(name, age);
    }

    public double thirteenthmonth(){
        return salary / (12.0 / months_worked);
    }
}
