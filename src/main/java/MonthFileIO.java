/**
 * Created by je4282oi on 5/2/2018.
 */
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.*;


public class MonthFileIO {
    //http://javahabit.com/2014/04/18/apache-poi-tutorial-reading-writing-excel-files-java/
    //Credit Morsel of Code     //Minneapolis.edu Apache-Poi
    //http://javabeginnerstutorial.com/code-base/create-excel-file-in-java-using-poi/

    public static void saveMonthsAppend (LinkedList<month> monthsToSave){
        //TODO: How to append in POI?
    }

    //TODO: Undo the extra quit program button. Just have them writing to spreadsheet once, when they're saved to list.
    public static void saveMonths(LinkedList<month> monthsToSave /*month monthToSave*/) {

        //New workbook:
        HSSFWorkbook workbook = new HSSFWorkbook();
        //New sheet:
        HSSFSheet firstSheet = workbook.createSheet("2018");

        //********************Create the data object for the excel sheet
        int rownum = 0;
        //A list to store the headings:
        List<String> headerRow = new ArrayList<>();
        headerRow.add("Month");
        headerRow.add("Home total");
        headerRow.add("Groceries");
        headerRow.add("Food out");
        headerRow.add("Personal");
        headerRow.add("Travel");

        //Add headerRow to listToWrite ArrayList
        List<List> listToWrite = new ArrayList<>();
        listToWrite.add(headerRow);

        //Using monthsToSave that gets sent as returnAllMonths
        if ((monthsToSave != null) && (monthsToSave.size() > 0)) {
            for (month m : monthsToSave) {
                //A new arrayList for each month
                List<String> newRow = new ArrayList<>();
                newRow.add(m.getName());
                //All the data is stored as text Strings in Excel
                //TODO: Is this what we want?
                newRow.add(String.valueOf(m.getHomeTotal()));
                newRow.add(String.valueOf(m.getGrocTotal()));
                newRow.add(String.valueOf(m.getFoodOutTotal()));
                newRow.add(String.valueOf(m.getPersonalTotal()));
                newRow.add(String.valueOf(m.getTravelTotal()));
                //Add each newRow to listToWRite
                listToWrite.add(newRow);
            }
        }
         else
            System.out.println("No months to save");

        //***************************Iterate over the data object, and write it to the sheet

        for (int j = 0; j < listToWrite.size(); j++) {
                //Iterates over rows
                Row row = firstSheet.createRow(rownum);
                List<String> l2 = listToWrite.get(j);
                //Iterates over columns
                for (int k = 0; k < l2.size(); k++) {
                    Cell cell = row.createCell(k);
                    cell.setCellValue(l2.get(k));
                }
                rownum++;
            }

        //*******************Need try catch blocks for saving it to sheet

        try {
            FileOutputStream outputStream = new FileOutputStream(budgetGUI.workbookName);
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not found");
        } catch (IOException ioe) {
            System.out.println("IOException");
        }
    }


    //https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
    public static void readFromFile() {
        //Called by budget_Manager during setup.
        //Create Workbook instance from excel sheet

        try {
            //Get the Excel File
            if (exists(budgetGUI.workbookName)) {
                FileInputStream file = new FileInputStream(new File(budgetGUI.workbookName));
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                //Get sheet at position 0
                HSSFSheet sheet = workbook.getSheetAt(0);

                //Increment over rows
                //Skip first row as it's headers
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

                    month nMonth = new month();
                    //First cell is name
                     nMonth.setName(sheet.getRow(i).getCell(0).getStringCellValue());
                    //Second cell is home
                    nMonth.setHomeTotal(Double.parseDouble(sheet.getRow(i).getCell(1).getStringCellValue()));
                    //Third cell is groceries
                    nMonth.setGrocTotal(Double.parseDouble(sheet.getRow(i).getCell(2).getStringCellValue()));
                    //Fourth cell is food out
                    nMonth.setFoodOutTotal(Double.parseDouble(sheet.getRow(i).getCell(3).getStringCellValue()));
                    //Fifth cell is personal
                    nMonth.setPersonalTotal(Double.parseDouble(sheet.getRow(i).getCell(4).getStringCellValue()));
                    //Sixth cell is travel
                    nMonth.setTravelTotal(Double.parseDouble(sheet.getRow(i).getCell(5).getStringCellValue()));

                    monthStore.add(nMonth);
                }
                file.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            //e.printStackTrace(); These might be what's creating npes
        } catch (IOException e) {
            System.out.println("IO Exception happening");
            //e.printStackTrace();
        }
    }

    public static boolean exists(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }

//End of class
}


