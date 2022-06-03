package tst.sample.utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name="getData")
    public Object[][] getData(){
        Object[][] data = testData("/src/main/resources/Credentials.xlsx",0);
        return data;
    }


    public Object[][] testData(String excelPath, int sheetIndex){
        ExcelReader er = new ExcelReader(excelPath, sheetIndex);
        int rowCount = er.getRowCount();
        int colCount = er.getColCount();

        Object[][] data = new Object[rowCount-1][colCount];
        for(int row = 1; row < rowCount; row++){
            for(int col = 0; col < colCount; col++){
               String cellData =  er.getCellData(row, col);
               data[row-1][col] = cellData;
            }
        }
        return data;
    }
}
