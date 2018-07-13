import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by helen on 2018/6/30
 */
public class WriteExcelSXSSF2 {

    @Test
    public void test() throws Exception{

        FileOutputStream out = new FileOutputStream("d:/poi1802/test-write-22.xls");


        SXSSFWorkbook wb = new SXSSFWorkbook(100);


        Sheet sheet = wb.createSheet();
        wb.setSheetName(0, "学生名单");

        for (int rownum = 0; rownum < 65537; rownum++) {

            Row row = sheet.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                cell.setCellValue("学生" + cellnum);
            }
        }

        wb.write(out);
        out.close();
    }
}
