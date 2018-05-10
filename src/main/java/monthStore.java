import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by je4282oi on 5/2/2018.
 */
public class monthStore {

    private static LinkedList<month> monthList;

    //Initialize monthList
    monthStore () {
        monthList = new LinkedList<month>();
    }

    //Used by monthFileIO to read in from spreadsheet
    public static void add (month newMonth) {
        monthList.add(newMonth);
    }

    //Used to add month from preview textfield:
    //TODO: After this called we get everything twice
    public static int addMonthfromString (String [] lines) {
        String n = "", htS = "", gtS = "", ftS = "", ptS = "", ttS = "";

        double ht = 0.0, gt = 0.0, ft = 0.0, pt = 0.0, tt = 0.0;

        //For each of the lines in string array from previewTextArea
        for (String s : lines) {
            //Check for Name
            if (s.contains("for:")) {
                n = s.substring(s.indexOf("Spending for:") + 13);
                n = n.trim();
                for (month m : returnAllMonths()) {
                    //If the name is the same as any already in the monthstore
                    if (n.equals(m.getName())){
                        return 1;
                    }
                }
            }
            //check for homeTotal
            else if (s.contains("homeTotal:")) {
                htS = s.substring(s.indexOf("homeTotal:") + 10);
                htS = htS.trim();
                ht = Double.parseDouble(htS);
            }
            //check for groceries etc...
            else if (s.contains("groceriesTotal:")) {
                gtS = s.substring(s.indexOf("groceriesTotal:") +15);
                gtS = gtS.trim();
                gt = Double.parseDouble(gtS);
            }
            //check for foodOut
            else if (s.contains("foodOutTotal:")) {
                ftS = s.substring(s.indexOf("foodOutTotal:") + 13);
                ftS = ftS.trim();
                ft = Double.parseDouble(ftS);
            }
            //Check for personalTotal
            else if (s.contains("personalTotal:")) {
                ptS = s.substring(s.indexOf("personalTotal:") + 14);
                ptS = ptS.trim();
                pt = Double.parseDouble(ptS);
            }
            //check for foodTotal
            else if (s.contains("travelTotal:")) {
                ttS = s.substring(s.indexOf("travelTotal:") + 12);
                ttS = ttS.trim();
                tt = Double.parseDouble (ttS);
            }

        }
        //Construct new month using values read from previewTextField
        month temp = new month (n, ht, gt, ft, tt, pt);

        //Check month doesn't already exist in store:
        if (checkNotAlready(temp)) {
            //Add new month to list
            monthList.add(temp);
        }
        //If they added ok
        return 0;
    }

    public static boolean checkNotAlready(month tempMonth){
        //check the temp month not already contained in the store:
        for (month m : monthList)
        {
            if (tempMonth.equals(m)){
                return false;
            }
            if (tempMonth.getName().equals(m.getName())){
                return false;
            }
        }
        return true;
    }


    public static LinkedList<month> returnAllMonths(){
        return monthList;
    }

    public month getMonthbyName (String name) {
        for (month m : monthList){
            if (m.getName() == name) {
                return m;
            }
        }
        //If no ticket found matching that name
        return null;
    }
}


