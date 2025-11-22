package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility implements AutoCloseable {  // ✅ Add this

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;  // ✅ Keep this
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    private boolean workbookInitialized = false;  // ✅ Add this

    public ExcelUtility(String path) {
        this.path = path;
    }

    // ✅ Add this method to initialize workbook once
    private void initializeWorkbook() throws IOException {
        if (!workbookInitialized) {
            fi = new FileInputStream(path);
            workbook = new XSSFWorkbook(fi);
            workbookInitialized = true;
        }
    }

    // ✅ Add this method to close resources
    @Override
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
        if (fi != null) {
            fi.close();
        }
        workbookInitialized = false;
    }

    public int getRowCount(String sheetName) throws IOException {
        initializeWorkbook();  // ✅ Call this instead of creating new workbook
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        // ❌ REMOVE workbook.close() and fi.close() from here
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        initializeWorkbook();  // ✅ Call this instead of creating new workbook
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        // ❌ REMOVE workbook.close() and fi.close() from here
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        initializeWorkbook();  // ✅ Call this instead of creating new workbook
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data = "";
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        // ❌ REMOVE workbook.close() and fi.close() from here
        return data;
    }

    // ❌ KEEP the existing setCellData, fillRedColor, fillGreenColor methods AS THEY ARE
    // These should still open/close their own workbooks since they modify the file
    
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        // Keep this method exactly as it is - don't change it
        File xlfile = new File(path);
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        // Keep this method exactly as it is - don't change it
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        // Keep this method exactly as it is - don't change it
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}