package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Table.Cell;

public class DataProviders {

 
    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\login_data.xlsx";
        
        // ✅ SINGLE workbook opening with proper resource management
        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = WorkbookFactory.create(fis)) {
            
            Sheet sheet = workbook.getSheet("Sheet1");
            DataFormatter formatter = new DataFormatter();
            
            // Get dimensions
            int totalrows = sheet.getLastRowNum() + 1; // 1-based row count
            int totalcols = sheet.getRow(0) != null ? sheet.getRow(0).getLastCellNum() : 0;
            
            String logindata[][] = new String[totalrows - 1][totalcols]; // Exclude header if exists

            // ✅ SINGLE PASS through all data - no repeated workbook openings
            for (int i = 1; i < totalrows; i++) { // Start from row 1 (skip header)
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < totalcols; j++) {
                        org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
                        logindata[i-1][j] = (cell != null) ? formatter.formatCellValue(cell) : "";
                    }
                }
            }
            return logindata;
        }
    }
    //DataProvider 2
}
