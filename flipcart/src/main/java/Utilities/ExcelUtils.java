package Utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();

        List<Object[]> data = new ArrayList<>();

        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int cellCount = row.getLastCellNum();
            Object[] rowData = new Object[cellCount];
            for (int i = 0; i < cellCount; i++) {
                rowData[i] = row.getCell(i).toString();
            }
            data.add(rowData);
        }

        workbook.close();
        file.close();

        return data.toArray(new Object[0][0]);
    }
}
