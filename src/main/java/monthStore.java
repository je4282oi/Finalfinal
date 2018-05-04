import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by je4282oi on 5/2/2018.
 */
public class monthStore {

    private static LinkedList<month> monthList;

    monthStore () {
        monthList = new LinkedList<month>();
    }

    public static void add (month newMonth) {
        monthList.add(newMonth);
    }
    public static void addMonthfromString (String [] lines) {
        String n = "", htS = "", gtS = "", ftS = "", ptS = "", ttS = "";
        //month temp = new month();
        double ht = 0.0, gt = 0.0, ft = 0.0, pt = 0.0, tt = 0.0;

        for (int i=0; i<lines.length; i++) {
            //Check for Name
            if (lines[i].contains("for:")) {
                n = lines[i].substring(lines[i].indexOf("Spending for:") + 13);
                n = n.trim();
                //temp.setName(n);
            }
            //check for homeTotal
            if (lines[i].contains("homeTotal:")) {
                htS = lines[i].substring(lines[i].indexOf("homeTotal:") + 10);
                htS = htS.trim();
                ht = Double.parseDouble(htS);
            }
            //check for groceries etc...
            if (lines[i].contains("groceriesTotal:")) {
                gtS = lines[i].substring(lines[i].indexOf("groceriesTotal:") +15);
                gtS = gtS.trim();
                gt = Double.parseDouble(gtS);
            }

            //check for foodOut
            if (lines[i].contains("foodOutTotal:")) {
                ftS = lines[i].substring(lines[i].indexOf("foodOutTotal:") + 13);
                ftS = ftS.trim();
                ft = Double.parseDouble(ftS);
            }

            //Check for personalTotal
            if (lines[i].contains("personalTotal:")) {
                ptS = lines[i].substring(lines[i].indexOf("personalTotal:") + 14);
                ptS = ptS.trim();
                pt = Double.parseDouble(ptS);
            }
            if (lines[i].contains("travelTotal:")) {
                ttS = lines[i].substring(lines[i].indexOf("travelTotal:") + 12);
                ttS = ttS.trim();
                tt = Double.parseDouble (ttS);
            }

        }
            month temp = new month (n, ht, gt, ft, tt, pt);
            monthList.add(temp);
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


