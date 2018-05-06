/**
 * Created by je4282oi on 5/2/2018.
 */
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.*;


public class MonthFileIO {
    //http://javahabit.com/2014/04/18/apache-poi-tutorial-reading-writing-excel-files-java/
    //Credit Morsel of Code     //Minneapolis.edu Apache-Poi
    //http://javabeginnerstutorial.com/code-base/create-excel-file-in-java-using-poi/

    public static void main(String[] args) {

        readFromFile();
        int rownum = 0;
        HSSFSheet firstSheet;
        Collection<File> files;
        HSSFWorkbook workbook = new HSSFWorkbook();
        File exactFile;
        {
            //Name the first sheet:
            //TODO: Could have this saving as current year.
            firstSheet = workbook.createSheet("2018");
            Row headerRow = firstSheet.createRow(rownum);
            headerRow.setHeightInPoints(40);
        }

        //public static void writeToFile ()
        {
            try {

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

                LinkedList<month> monthsToSave = monthStore.returnAllMonths();
                if ((monthsToSave != null) && (monthsToSave.size() > 0)) {
                    for (month m : monthsToSave) {
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
                } else
                    System.out.println("No months to save");
                //Send listToWrite to MonthFileIo constructor
                //MonthFileIO clas = new MonthFileIO(listToWrite);
                //createExcelFile();
                try {
                    for (int j = 0; j < listToWrite.size(); j++) {
                        Row row = firstSheet.createRow(rownum);
                        List<String> l2 = listToWrite.get(j);
                        for (int k = 0; k < l2.size(); k++) {
                            Cell cell = row.createCell(k);
                            cell.setCellValue(l2.get(k));
                        }
                        rownum++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }

            } catch (Exception e) {
                System.out.println("Not working.");
            }
        }

        //void createExcelFile () {
            FileOutputStream fileos;
            try {
                fileos = new FileOutputStream(new File("BudgetExcelSheet.xls"));
                HSSFCellStyle hsfstyle = workbook.createCellStyle();
                hsfstyle.setBorderBottom((short) 1);
                hsfstyle.setFillBackgroundColor((short) 245);
                workbook.write(fileos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void readFromFile () {
            //Create Workbook instance from excel sheet
            //https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/

            try {
                //Get the Excel File
                FileInputStream file = new FileInputStream(new File("BudgetExcelSheet.xls"));
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                //Get sheet at position 0
                HSSFSheet sheet = workbook.getSheetAt(0);

                //Increment over rows
                for (Row row : sheet) {
                    //Iterate and get the cells from the row
                    Iterator cellIterator = row.cellIterator();
                    // Loop till you read all the data
                    while (cellIterator.hasNext()) {
                        Cell cell = (Cell) cellIterator.next();
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC: {
                                System.out.print(cell.getNumericCellValue() + "t");
                                break;
                            }
                            case Cell.CELL_TYPE_STRING: {
                                System.out.print(cell.getStringCellValue() + "t");
                                break;
                            }
                        }
                    }
                    System.out.println("");
                }
                file.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception happening");
                e.printStackTrace();
            }
        }
}


