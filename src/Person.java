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

    public void setName(String name){
        this.name = name;
    }
    public String to_String(Person person){
        if(person instanceof Clerk){
                return "How may I help you?";
        }
        return "Hello, my name is " + name;
    }

    public static class Customer extends Person{
        public Customer(String name, int age) {
            super(name, age);
        }
    }

}
