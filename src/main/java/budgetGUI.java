import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

/**
 * Created by je4282oi on 4/18/2018.
 */
public class budgetGUI extends JFrame{
    //Components to display
    private JPanel mainPanel;
    private JPanel displayPanel;
    private JTextArea previewMonthTextArea;
    private JList recentMonthsList;

    //Components to enter data
    private JPanel dataEntryPanel;
    private JLabel programTitle;
    private JButton addMonthButton;
    private JTextField whichMonthTextField;
    private JComboBox<String> purchaseTypeComboBox;
    private JTextField purchaseAmountTextField;
    private JButton previewMonthButton;

    //Components to write to file
    private JButton saveToFileButton;
    private JButton addPurchaseAmountButton;
    private JLabel comboBoxStatusDescriptionLabel;

    budget_Manager manager;

    public static String typeHome = "Home related purchase";
    public static String typeGroc = "Groceries purchase";
    public static String typeFoodOut = "Food out";
    public static String typeTravel = "Travel purchase";
    public static String typePersonal = "Personal purchase";

    month newMonth = new month();

    //constructor:
    budgetGUI (budget_Manager manager) {

        this.manager = manager;

        //Populates comboBox with static types of purchase
        setComboBox();
        actionHandling();
        setJList();

        setContentPane(mainPanel);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        previewMonthTextArea.setText("Generated preview here");
    }

    //Reset after new month created
    public void resetFields() {
        whichMonthTextField.setText("");
        purchaseAmountTextField.setText("");

    }

    //At the beginning and after new month added
    public void setJList() {

        DefaultListModel<month> newList = new DefaultListModel<>();

        if (monthStore.returnAllMonths()!=null) {
            if (monthStore.returnAllMonths().size() > 0) {
                for (month m : monthStore.returnAllMonths()) {
                    newList.addElement(m);
                }
            }
        }

        recentMonthsList.setModel(newList);

    }

    public void setComboBox() {
        purchaseTypeComboBox.addItem("Choose Purchase Category:");
        purchaseTypeComboBox.addItem(typeHome);
        purchaseTypeComboBox.addItem(typeGroc);
        purchaseTypeComboBox.addItem(typeFoodOut);
        purchaseTypeComboBox.addItem(typeTravel);
        purchaseTypeComboBox.addItem(typePersonal);
    }

    public void actionHandling() {

        //TODO:?? Set so that dataEntryPanel only displays when addMonth pushed
        //Use Custom Create?

        purchaseTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (purchaseTypeComboBox.getSelectedItem() == typeHome)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for home:");
            }
        });

        //Set newMonth's name
        whichMonthTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                if ((whichMonthTextField.getText() != null) && (!whichMonthTextField.getText().isEmpty())) {
                    name = whichMonthTextField.getText();
                    newMonth.setName(name);
                }
                else
                    JOptionPane.showMessageDialog(budgetGUI.this, "Enter a month!");
            }  });

        //Set newMonth's hashmap reading info from boxes, each time pushed
        addPurchaseAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setTotals();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(budgetGUI.this, "Amount must be number");
                }
                setName(whichMonthTextField.getText());
            } });

        //Preview newMonth's data
        previewMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setContentPane(displayPanel);
                previewMonthTextArea.setText(newMonth.toString());
                resetFields();
            } });
        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthFileIO monthFileIO = new MonthFileIO();
                monthFileIO.writeToFile();
                monthFileIO.createExcelFile();
            }
        });

        //Click to add the month data to monthStore
        // then... TODO: write information to file
        saveToFileButton.addActionListener(new ActionListener() {
            String [] lines = new String [8];
            @Override
            public void actionPerformed(ActionEvent e) {
                //Too easy?! monthStore.add(newMonth);
                //month monthToSave = new month();
                lines = previewMonthTextArea.getText().split("\\n");
                //monthToSave = readMonth(lines);
                monthStore.addMonthfromString(lines);
                //monthStore.add(monthToSave);
                setJList();
            }  });


    }


    public void setTotals() {
        //Set amount entered into correct total in catsAndTotals
        if (purchaseTypeComboBox.getSelectedItem() == typeHome) {
            newMonth.setHomeTotal(Double.parseDouble(purchaseAmountTextField.getText()));
            System.out.println(newMonth.getHomeTotal());
        }
        if (purchaseTypeComboBox.getSelectedItem() == typeFoodOut){
            newMonth.setFoodOutTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        if (purchaseTypeComboBox.getSelectedItem() == typeGroc){
            newMonth.setGrocTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        if (purchaseTypeComboBox.getSelectedItem() == typePersonal){
            newMonth.setPersonalTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        if (purchaseTypeComboBox.getSelectedItem() == typeTravel){
            newMonth.setTravelTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }

    }

    /*private void createUIComponents() {
        // TODO: place custom component creation code here
    }*/
}
