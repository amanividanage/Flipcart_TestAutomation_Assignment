import Utilities.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDataDriven {

    @DataProvider(name = "excelDataProvider")
    public Object[][] excelDataProvider() throws IOException {
        String filePath = "src/test/resources/test.xlsx";
        String sheetName = "Sheet1"; // Name of the sheet to read
        return ExcelUtils.getExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "excelDataProvider")
    public void testWithExcelData(String input1, String input2, String expectedOutput) {
        System.out.println("Input1: " + input1);
        System.out.println("Input2: " + input2);
        System.out.println("Expected Output: " + expectedOutput);


    }
}
