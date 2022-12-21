import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App extends JFrame {
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;
    private static List<Person> persons;
    ButtonGroup bgPersons;
    public int index = 1;
    public App() {
        persons = new ArrayList<>();
        bgPersons = new ButtonGroup();
        bgPersons.add(rbCustomer);
        bgPersons.add(rbClerk);
        bgPersons.add(rbManager);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                String name = "";
                int age = 0;
                int month = 0;
                double salary = 0;

                try {
                    name = getName();
                    if(name == "") {
                        throw new InvalidNameException("Input in name cannot be empty.");
                    }
                }catch(InvalidNameException s){
                    flag = false;
                    tfName.setText("");
                    JOptionPane.showMessageDialog(pnlMain, s.getMessage());
                }

                try{
                    age = getAge();
                    if(age <= 0){
                        throw new InvalidAgeException("Age cannot be zero or negative.");
                    }
                }catch(NumberFormatException s){
                    flag = false;
                    tfAge.setText("");
                    JOptionPane.showMessageDialog(pnlMain, "Input in age must be all numbers.");
                }catch (InvalidAgeException s){
                    flag = false;
                    tfAge.setText("");
                    JOptionPane.showMessageDialog(pnlMain, s.getMessage());
                }

                if (rbCustomer.isSelected() && flag) {
                    taPersons.setText(taPersons.getText() + "\n" + index + ". " + rbCustomer.getText() + " - " + name + "(" + age + ")");
                        tfName.setText("");
                        tfAge.setText("");

                    }else if(rbManager.isSelected() || rbClerk.isSelected()){

                    try{
                        month = getMonth();
                        if(month <= 0){
                            flag = false;
                            throw new InvalidMonthException("Month cannot be less than one.");
                        }
                    }catch(NumberFormatException s){
                        flag = false;
                        tfMonths.setText("");
                        JOptionPane.showMessageDialog(pnlMain, "Input in months worked must be all numbers.");
                    }catch(InvalidMonthException s){
                        flag = false;
                        tfMonths.setText("");
                        JOptionPane.showMessageDialog(pnlMain, s.getMessage());
                    }

                    try{
                        salary = getSalary();
                    }catch(NumberFormatException s){
                        flag = false;
                        tfSalary.setText("");
                        JOptionPane.showMessageDialog(pnlMain, "Input in salary must be all numbers.");
                    }
                    if(flag) {
                        if (rbManager.isSelected()) {
                            taPersons.setText(taPersons.getText() + "\n" + index + ". " + rbManager.getText() + " - " + name + "(" + age + ")");
                            tfName.setText("");
                            tfAge.setText("");
                            tfMonths.setText("");
                            tfSalary.setText("");
                            index++;
                        }
                        if (rbClerk.isSelected()) {
                            taPersons.setText(taPersons.getText() + "\n" + index + ". " + rbClerk.getText() + " - " + name + "(" + age + ")");
                            tfName.setText("");
                            tfAge.setText("");
                            tfMonths.setText("");
                            tfSalary.setText("");
                            index++;
                        }
                    }
                    }
                    if (rbClerk.isSelected()) {
                        persons.add(new Employee.Clerk(name, age, month, salary));
                    } else if (rbCustomer.isSelected()) {
                        persons.add(new Person.Customer(name, age));
                    } else if (rbManager.isSelected()) {
                        persons.add(new Employee.Manager(name, age, month, salary));
                    }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Venn Dominic\\IdeaProjects\\CSIT227Finals-DelaPe-a\\src\\ListOfPersons", false))) {
                    bw.write(taPersons.getText());
                    } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    load();
            }
        });

        btnSayHi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sayHi();
            }
        });

        rbCustomer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                    tfSalary.setEditable(!rbCustomer.isSelected());
                    tfMonths.setEditable(!rbCustomer.isSelected());
            }
        });

        btnReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(tfLoad.getText());
                giveReward(n);
            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.setContentPane(app.pnlMain);
        app.setSize(500,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);

        Scanner scan = new Scanner(System.in);
    }

    static void giveReward(int n) {
        Person person = App.persons.get(n-1);
        try {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                JOptionPane.showMessageDialog(null, String.format("%s receives %.2f as a 13th-month pay.", employee.getName(), employee.thirteenthmonth()));
            }else{
                throw new InvalidEmployeeException("Not an Employee.Try again.");
            }
        }catch(InvalidEmployeeException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    public String getName(){
        String name = "";
        name = tfName.getText();
        return name;
    }

    public int getAge(){
        int age = 0;
        age = Integer.parseInt(tfAge.getText());

        return age;
    }

    public int getMonth(){
        int month = 0;
        month = Integer.parseInt(tfMonths.getText());

        return month;
    }

    public double getSalary(){
        double salary = 0;
        salary = Integer.parseInt(tfSalary.getText());

        return salary;
    }
    public void load(){
        int num = 0;
        Person person  = null;
        try {
             num = Integer.parseInt(tfLoad.getText());
            person = persons.get(num-1);
        }catch(NumberFormatException s){
            tfLoad.setText("");
            JOptionPane.showMessageDialog(pnlMain,"Input in load must be a number.");
        }catch(IndexOutOfBoundsException s){
            JOptionPane.showMessageDialog(pnlMain,"Load input exceeded the list. Try again.");
        }catch(NullPointerException s){
            JOptionPane.showMessageDialog(pnlMain,"Load input exceeded the list. Try again.");
        }

        tfName.setText(person.getName());
        tfAge.setText(person.getAge());
        if(person instanceof Employee.Clerk){
            Employee.Clerk clerk = (Employee.Clerk) person;
            rbClerk.setSelected(true);
            tfMonths.setText(String.valueOf(clerk.getMonth()));
            tfSalary.setText(String.valueOf(clerk.getSalary()));
        }else if(person instanceof Employee.Manager) {
            Employee.Manager manager = (Employee.Manager) person;
            rbManager.setSelected(true);
            tfMonths.setText(String.valueOf(manager.getMonth()));
            tfSalary.setText(String.valueOf(manager.getSalary()));
        }else{
            rbCustomer.setSelected(true);
            tfMonths.setText("");
            tfSalary.setText("");
        }
        }
    public void sayHi(){
        for(Person p: persons){
            taPersons.setText(taPersons.getText() + "\n"+ p.to_String());
        }
    }
    }





