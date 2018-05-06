import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by je4282oi on 4/11/2018.
 */
public class budget_Manager {

    monthStore MonthStore;
    budgetGUI BudgetGUI;
    MonthFileIO monthFileIO;
    //monthStore MonthStore;


    public static void main(String[] args) {
        //Declare instances of classes
        budget_Manager budgetmanager = new budget_Manager();
        //monthStore MonthStore = new monthStore();
        budgetmanager.start();
    }

    void start() {
        createMonthStore();
        startGUI();
        //monthFileIO = new MonthFileIO();
        monthFileIO.readFromFile();
    }

    protected void startGUI() {
        BudgetGUI = new budgetGUI(this);
    }


    protected void createMonthStore() {
        MonthStore = new monthStore();
    }

    /*protected void addMonth (month newMonth) {
        monthStore.add(newMonth);
    }*/

}
