import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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
    private List<Person> persons;

    public int index = 1;
    public App() {
        persons = new ArrayList<>();
        ButtonGroup bgPersons = new ButtonGroup();
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
                        throw new InvalidNameException("Input in name cannot empty.");
                    }
                }catch(InvalidNameException s){
                    flag = false;
                    tfName.setText("");
                    JOptionPane.showMessageDialog(pnlMain, s.getMessage());
                }

                try{
                    age = getAge();
                    if(age < 0){
                        throw new InvalidAgeException("Age cannot be negative.");
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

                    }else if(rbManager.isSelected() || rbClerk.isSelected()){

                    try{
                        month = getMonth();
                        if(month <= 0){
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
                            index++;
                        }
                        if (rbClerk.isSelected()) {
                            taPersons.setText(taPersons.getText() + "\n" + index + ". " + rbClerk.getText() + " - " + name + "(" + age + ")");
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
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");

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
                if(rbCustomer.isSelected()){
                    tfSalary.setEditable(false);
                    tfMonths.setEditable(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.setContentPane(app.pnlMain);
        app.setSize(500,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    static void giveReward(int n) {

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
        try {
             num = Integer.parseInt(tfLoad.getText());
        }catch(NumberFormatException s){
            tfLoad.setText("");
            JOptionPane.showMessageDialog(pnlMain,"Input in load must be a number.");
        }
        Person person = persons.get(num-1);
        tfName.setText(person.getName());
        tfAge.setText(person.getAge());
        if(person instanceof Employee.Clerk){
            rbClerk.setSelected(true);
            tfMonths.setText(Employee.Clerk.getMonth());
            tfSalary.setText(String.valueOf(Employee.Clerk.getSalary()));
        }else if(person instanceof Employee.Manager) {
            rbManager.setSelected(true);
            tfMonths.setText(Employee.Manager.getMonth());
            tfSalary.setText(String.valueOf(Employee.Manager.getSalary()));
        }
        }
    public void sayHi(){
        for(Person p: persons){
            taPersons.setText(taPersons.getText() + "\n"+ p.to_String());
        }
    }
    }





