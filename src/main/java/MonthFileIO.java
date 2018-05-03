/**
 * Created by je4282oi on 5/2/2018.
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MonthFileIO {
    //Reading / writing to Excel.... May / may not happen

    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("Data");

        //Map<String, object[]=""> data =

                //Probably this would work if I had Apache downloaded
        //www.apache.org would be good start.
    }
}
