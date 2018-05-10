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

    static String workbookName = "BudgetExcelSheet11.xls";

    month newMonth = new month();

    //constructor:
    budgetGUI (budget_Manager manager) {

        this.manager = manager;

        MonthFileIO.readFromFile();

        //Populates comboBox with static types of purchase
        setComboBox();

        //Populate the Jlist
        setJList();
        //BEFORE doing the actionHAndling! And then not again afterwards, because
        //SetJList is called again when you add a new month.
        actionHandling();

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

        //Reset the JList to blank, to avoid repetition
        recentMonthsList.setModel(newList);

        //Then readd to months from monthStore
        if (monthStore.returnAllMonths()!=null) {
            if (monthStore.returnAllMonths().size() > 0) {
                for (month m : monthStore.returnAllMonths()) {
                    newList.addElement(m); }
            }
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

        //Sets entering label based on comboBox. Extra check
        purchaseTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (purchaseTypeComboBox.getSelectedItem() == typeHome)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for home:");
                else if (purchaseTypeComboBox.getSelectedItem() == typeFoodOut)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for food out: ");
                else if (purchaseTypeComboBox.getSelectedItem() == typeGroc)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for groceries:");
                else if (purchaseTypeComboBox.getSelectedItem() == typePersonal)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for personal");
                else if (purchaseTypeComboBox.getSelectedItem() == typeTravel)
                    comboBoxStatusDescriptionLabel.setText("Entering purchase data for travel:");

            }  });

        //Set newMonth's hashmap reading info from boxes, each time pushed. Error if NaN entered
        addPurchaseAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setTotals();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(budgetGUI.this, "Amount must be number");
                }
                //Reset purchase amount button each time it's pressed.
                purchaseAmountTextField.setText("");
                //setName(whichMonthTextField.getText());
                } });

        //Preview newMonth's data and set to PreviewArea
        previewMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setContentPane(displayPanel);
                //Color defaultColor = mainPanel.getBackground();
                //mainPanel.setBackground(defaultColor);
                //System.out.println(newMonth.toString());
                //previewMonthTextArea.setOpaque(true);
                previewMonthTextArea.setText(newMonth.toString());
                //After saving the data for previous entries, the global new month's fields should be set to 0.

                //This resets purchaseAmount, monthName and previewMonth text fields;
                resetFields(); } });

        //Click to add the month data to monthStore
        //TODO: Somewhere after this we get everything twice in the monthstore
        saveToFileButton.addActionListener(new ActionListener() {
            String [] lines = new String [8];
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(monthStore.returnAllMonths());
                //*****************Too easy?! *************TRy this to prevent double writing:
                //monthStore.add(newMonth);


                //month monthToSave = new month();


                //Lines only contains one month's data.
                lines = previewMonthTextArea.getText().split("\\n");
                for (String s : lines){
                    System.out.println(s);
                }
                //monthToSave = readMonth(lines);
                monthStore.addMonthfromString(lines);
                //WRite month to file each time it's created to avoid doublewriting.
                //month tempMonth = monthStore.returnAllMonths().getLast();*/
                //MonthFileIO.saveMonths(tempMonth);
                //monthStore.add(monthToSave);
                previewMonthTextArea.setText("");
                resetNewMonth();
                //System.out.println(monthStore.returnAllMonths());
                //setJList();
            }  });

        //Save to SpreadSheet when pushed. TODO: Also quit
        quitProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (MonthFileIO.exists(workbookName)) {
                  //  MonthFileIO.saveMonthsAppend(monthStore.returnAllMonths());
                //}
                //else //Create new workbook:
                MonthFileIO.saveMonths(monthStore.returnAllMonths());
                // Close the GUI
                // this.dispose();
            }  });


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
