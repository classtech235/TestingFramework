package tst.sample.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {
    static String projPath = System.getProperty("user.dir");
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelReader(String excelPath, int sheetIdx){
        try {
            workbook = new XSSFWorkbook(projPath+excelPath);
            sheet = workbook.getSheetAt(sheetIdx);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int getRowCount(){
        int rowCount=Integer.MIN_VALUE;
        try{
            rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("no:of rows: "+rowCount);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return rowCount;
    }

    public static int getColCount(){
        int colCount = Integer.MIN_VALUE;
        try {
            colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println("No:of cols: " + colCount);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return colCount;
    }

    public static String getCellData(int rowNum, int colNum){
        String cellData = "";
        try{
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }


}
