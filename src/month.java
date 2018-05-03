import java.util.HashMap;

/**
 * Created by je4282oi on 4/11/2018.
 */
public class month {
    private String name; //name of month Jan, Feb etc
    private double homeTotal;
    private double grocTotal;
    private double foodOutTotal;
    private double personalTotal;
    private double travelTotal;
    //private HashMap<String, Double> catsAndTotals; //homeTotal, groceriesTotal, foodOutTotal, travelTotal ...
    private double totalSaved; //any wages earned above spending goes here

    //Constructor for basic month object with data given
    month (String name, double home, double groc, double foodOut, double travel, double personal) {
        this.name = name;
        this.homeTotal = home;
        this.foodOutTotal = foodOut;
        this.grocTotal = groc;
        this.travelTotal = travel;
        this.personalTotal = personal;
    }

    //Constructor for blank month object
    month () {
        this.name ="";
        this.homeTotal = 0.0;
        this.grocTotal = 0.0;
        this.foodOutTotal = 0.0;
        this.travelTotal =  0.0;
        this.personalTotal = 0.0;
        this.totalSaved = 0.0;
    }

    //*****************Month Methods*****************

    //SetMonth
    public void setName(String name) {        this.name = name;    }

    public void setHomeTotal (double homeTotal) { this.homeTotal = homeTotal;}

    public void setGrocTotal(double grocTotal) {        this.grocTotal = grocTotal;    }

    public void setFoodOutTotal(double foodOutTotal) {        this.foodOutTotal = foodOutTotal;    }

    public void setPersonalTotal(double personalTotal) {        this.personalTotal = personalTotal;    }

    public void setTravelTotal(double travelTotal) {        this.travelTotal = travelTotal;    }

    public void setTotalSaved(double totalSaved) {         this.totalSaved = totalSaved;    }

    //GetMonth
    public String getName() {   return this.name;     }

    //GetTotals
    public double getHomeTotal () {        return this.homeTotal;    }

    public double getGrocTotal () {       return this.grocTotal;    }

    public double getFoodOutTotal () {    return this.foodOutTotal;    }

    public double getPersonalTotal () {     return this.personalTotal;    }

    public double getTravelTotal () {        return this.travelTotal;    }


    //CalculateTotalMonth'sSpending
    public double getTotalSpent () { return (getHomeTotal() + getFoodOutTotal() + getGrocTotal() +
        getTravelTotal() +getPersonalTotal());
    }


    public double getTotalSaved () {
        double foodBudget = 400;
        double travelBudget = 200;
        double personalBudget = 100;
        double homeBudget = 100;
        double savings = 0.0;
        savings += foodBudget - (getGrocTotal()+getFoodOutTotal());
        savings += travelBudget - getTravelTotal();
        savings += personalBudget - getPersonalTotal();
        savings += homeBudget - getHomeTotal();
        return (savings); }

    //GetPercentages
    public double getHomePercent() {
        return (getHomeTotal()/getTotalSpent() *100);
    }

    //etc

    @Override
    public String toString() {
        return "Spending for: " + getName()  + "\n" +
                "homeTotal: " + getHomeTotal() + "\n" +
                "groceriesTotal: " + getGrocTotal() + "\n"+
                "foodOutTotal: " + getFoodOutTotal() + "\n" +
                "travelTotal: " + getTravelTotal() + "\n" +
                "personalTotal: " + getPersonalTotal() + "\n" +
                "totalSpent: " + getTotalSpent() + "\n" +
                "totalSaved=" + getTotalSaved();
    }
}


