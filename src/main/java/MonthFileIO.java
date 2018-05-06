/**
 * Created by je4282oi on 5/2/2018.
 */
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.*;


public class MonthFileIO {
    //Reading / writing to Excel.... May / may not happen
    //http://javahabit.com/2014/04/18/apache-poi-tutorial-reading-writing-excel-files-java/
    //Credit Morsel of Code
    //Minneapolis.edu Apache-Poi

        /*Create new workbook:
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
            }*/

        int rownum = 0;
        HSSFSheet firstSheet;
        Collection<File> files;
        HSSFWorkbook workbook = new HSSFWorkbook();
        File exactFile;

    {
        firstSheet = workbook.createSheet("First sheet");
        Row headerRow = firstSheet.createRow(rownum);
        headerRow.setHeightInPoints(40);
    }

    public static void main(String[] args) throws Exception{

            List<String> headerRow = new ArrayList<String>();
            headerRow.add("Employee No");
            headerRow.add("Employee Name");
            headerRow.add("Employee Address");

            List<String> firstRow = new ArrayList<String>();
            firstRow.add("1111");
            firstRow.add("Gautam");
            firstRow.add("India");

            List<List> recordToAdd = new ArrayList<List>();
            recordToAdd.add(headerRow);
            recordToAdd.add(firstRow);

            MonthFileIO cls = new MonthFileIO(recordToAdd);
            cls.createExcelFile();
        }

        void createExcelFile(){
            FileOutputStream fos = null;
            try {
                fos=new FileOutputStream(new File("ExcelSheet.xls"));
                HSSFCellStyle hsfstyle= workbook.createCellStyle();
                hsfstyle.setBorderBottom((short) 1);
                hsfstyle.setFillBackgroundColor((short)245);
                workbook.write(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MonthFileIO(List<List> l1) throws Exception {

            try {

                for (int j = 0; j < l1.size(); j++) {
                    Row row = firstSheet.createRow(rownum);
                    List<String> l2= l1.get(j);

                    for(int k=0; k<l2.size(); k++)
                    {
                        Cell cell = row.createCell(k);
                        cell.setCellValue(l2.get(k));
                    }
                    rownum++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }

        }
    }

        /*System.out.println("test.xlsl successfully created, somewhere..");
        //workbook1.write(inputFS);


            } catch (FileNotFoundException nfe) {
                nfe.printStackTrace();
                System.out.println("Spreadsheet not found");
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println("Spreadsheet not found");
            }
    }
}*/
