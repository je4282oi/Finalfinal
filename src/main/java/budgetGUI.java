import javax.swing.*;
import java.awt.*;
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
    private JButton quitProgramButton;

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
        //Resets everything to zero. Activated on 'saveTofile' button
        whichMonthTextField.setText("");
        purchaseAmountTextField.setText("");
    }

    //At the beginning and after new month added
    public void setJList() {

        DefaultListModel<month> newList = new DefaultListModel<>();

        if (monthStore.returnAllMonths()!=null) {
            if (monthStore.returnAllMonths().size() > 0) {
                for (month m : monthStore.returnAllMonths()) {
                    newList.addElement(m); } }
        }

        recentMonthsList.setModel(newList);
    }

    //Set Combination box, called on Startup.
    public void setComboBox() {
        purchaseTypeComboBox.addItem("Choose Purchase Category:");
        purchaseTypeComboBox.addItem(typeHome);
        purchaseTypeComboBox.addItem(typeGroc);
        purchaseTypeComboBox.addItem(typeFoodOut);
        purchaseTypeComboBox.addItem(typeTravel);
        purchaseTypeComboBox.addItem(typePersonal);
    }

    //Handles actions for buttons on GUI
    public void actionHandling() {

        //TODO: What does this DO!?
        purchaseTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (purchaseTypeComboBox.getSelectedItem() == typeHome)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for home:"); }  });

        //Set newMonth's name, display error if no month entered
        whichMonthTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                if ((whichMonthTextField.getText() != null) && (!whichMonthTextField.getText().isEmpty())) {
                    name = whichMonthTextField.getText();
                    newMonth.setName(name);
                }
                else
                    JOptionPane.showMessageDialog(budgetGUI.this, "Enter a month!"); }  });

        //Set newMonth's hashmap reading info from boxes, each time pushed. Error if NaN entered
        addPurchaseAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setTotals();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(budgetGUI.this, "Amount must be number");
                }
                setName(whichMonthTextField.getText()); } });

        //Preview newMonth's data and set to PreviewArea
        previewMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setContentPane(displayPanel);
                //Color defaultColor = mainPanel.getBackground();
                //mainPanel.setBackground(defaultColor);
                System.out.println(newMonth.toString());
                //previewMonthTextArea.setOpaque(true);
                previewMonthTextArea.setText(newMonth.toString());
                //After saving the data for previous entries, the global new month's fields should be set to 0.
                resetNewMonth();
                //This resets purchaseAmount, monthName and previewMonth text fields;
                resetFields(); } });

        //Click to add the month data to monthStore
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
                previewMonthTextArea.setText("");
                setJList();
            }  });

        //Save to SpreadSheet when pushed. TODO: Also quit
        quitProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthFileIO.saveMonths(monthStore.returnAllMonths());   }  });


    }

    //Reset all global newMonth fields to 0 after adding to store
    public void resetNewMonth() {
        newMonth.setName("");
        newMonth.setFoodOutTotal(0.0);
        newMonth.setGrocTotal(0.0);
        newMonth.setHomeTotal(0.0);
        newMonth.setPersonalTotal(0.0);
        newMonth.setTravelTotal(0.0);
        newMonth.setTotalSaved(0.0);
    }

    //Set amount entered into correct total within object
    public void setTotals() {
        //Set home total
        if (purchaseTypeComboBox.getSelectedItem() == typeHome) {
            newMonth.setHomeTotal(Double.parseDouble(purchaseAmountTextField.getText()));
            System.out.println(newMonth.getHomeTotal());
        }
        //Set food out total
        if (purchaseTypeComboBox.getSelectedItem() == typeFoodOut){
            newMonth.setFoodOutTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        //Set groceries total
        if (purchaseTypeComboBox.getSelectedItem() == typeGroc){
            newMonth.setGrocTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        //set personal total
        if (purchaseTypeComboBox.getSelectedItem() == typePersonal){
            newMonth.setPersonalTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
        //set travel total
        if (purchaseTypeComboBox.getSelectedItem() == typeTravel){
            newMonth.setTravelTotal(Double.parseDouble(purchaseAmountTextField.getText()));
        }
    }

    //End of class
}
