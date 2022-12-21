import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private List<JRadioButton> bgPersons;
    public int index = 1;
    public App() {
        persons = new ArrayList<>();
        bgPersons = new ArrayList<>();
        bgPersons.add(rbCustomer);
        bgPersons.add(rbClerk);
        bgPersons.add(rbManager);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                index++;
            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.setContentPane(app.pnlMain);
        app.setSize(500,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
        // add here how to make GUI visible
    }

    static void giveReward(int n) {

    }

    public void save(){
        Person person = null;
        for(JRadioButton rb: bgPersons){
            if(rb.isSelected()){
                taPersons.setText(taPersons.getText()+"\n"+index+". "+rb.getText() + " - " + tfName.getText() +"("+tfAge.getText()+")");
            }
            if(rb == rbClerk){
                person = new Employee.Clerk(tfName.getText(), Integer.parseInt(tfAge.getText()), Integer.parseInt(tfMonths.getText()), Integer.parseInt(tfSalary.getText()));
            }else if(rb == rbCustomer){
                person = new Customer(tfName.getText(), Integer.parseInt(tfAge.getText()));
            }else if(rb == rbManager){
                person = new Employee.Manager(tfName.getText(), Integer.parseInt(tfAge.getText()), Integer.parseInt(tfMonths.getText()), Integer.parseInt(tfSalary.getText()));
            }
            persons.add(person);
        }
        tfName.setText("");
        tfAge.setText("");
        tfMonths.setText("");
        tfSalary.setText("");
    }

}
