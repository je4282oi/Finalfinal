import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by je4282oi on 4/11/2018.
 */
public class budget_Manager {

    monthStore MonthStore;
    budgetGUI BudgetGUI = new budgetGUI(this);


    public static void main(String[] args) {

        budget_Manager manager = new budget_Manager();
        monthStore MonthStore = new monthStore();
        manager.start();
    }

    void start() {
        createMonthStore();
        startGUI();
    }

    protected void startGUI() {
        BudgetGUI = new budgetGUI(this);
    }


    protected void createMonthStore() {
        MonthStore = new monthStore();
    }

    protected void addMonth (month newMonth) {
        monthStore.add(newMonth);
    }


    }
