/**
 * Created by je4282oi on 5/2/2018.
 */
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class MonthFileIO {
    //Reading / writing to Excel.... May / may not happen
    //http://javahabit.com/2014/04/18/apache-poi-tutorial-reading-writing-excel-files-java/
    //Credit Morsel of Code
    //Minneapolis.edu Apache-Poi

    {
                    //Create new workbook:
            HSSFWorkbook workbook = new HSSFWorkbook();

            System.out.println(workbook.getNumberOfSheets());
            //Create new sheet
            HSSFSheet sheet = workbook.createSheet("First Sheet");

            //Create the data for the excel sheet
            Map<String, Object[]> data = new TreeMap<>();
            data.put("1", new Object[]{"ID", "FIRSTNAME", "LASTNAME"});
            data.put("2", new Object[]{1, "Randy", "Maven"});
            data.put("3", new Object[]{2, "Raymond", "Smith"});

            //Iterate over data and write it to the sheet
            Set keyset = data.keySet();
            int rownum = 0;
            for (Object key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj); //Treat object as string
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj); //Treat object as an integer
                }
            }

            try {
                FileOutputStream out = new FileOutputStream(new File("c:/Users/Kate/JavaPOIExcel.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("c:/Kate/JavaPOIExcel.*xlsx successfully created, somewhere...");
            } catch (FileNotFoundException nfe) {
                nfe.printStackTrace();
                System.out.println("Spreadsheet not found");
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println("Spreadsheet not found");
            }
    }
}
