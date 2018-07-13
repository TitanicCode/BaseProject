import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by helen on 2018/6/30
 */
public class WriteExcelHSSF {

    @Test
    public void test() throws Exception {

        FileOutputStream out = new FileOutputStream("d:/poi1802/test-write.xls");

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        wb.setSheetName(0, "学生名单");

        for (int rownum = 0; rownum < 65536; rownum++) {

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
