public abstract class Person {
    // TODO implement Person and its subclasses in other Java files
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }
    public String getAge(){
        return String.valueOf(age);
    }
    public void setName(String name){
        this.name = name;
    }

    public String to_String(){
        return "Hello, my name is " + getName() +".";
    }



    public static class Customer extends Person{
        public Customer(String name, int age) {
            super(name, age);
        }
    }
}
