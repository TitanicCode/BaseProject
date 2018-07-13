import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * Created by helen on 2018/6/30
 */
public class ReadExcelHSSF {

    @Test
    public void test1() throws Exception{

        FileInputStream in = new FileInputStream("d:/poi1802/商品表-1.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(in);

        for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){

            HSSFSheet sheet = workbook.getSheetAt(numSheet);

            if(sheet == null){
                continue;
            }

            String sheetName = sheet.getSheetName();
            System.out.println(sheetName);

            for(int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++){
                HSSFRow row = sheet.getRow(rowNum);

                int rowNum1 = row.getRowNum();
                System.out.print("row：" + rowNum);


                for(int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++){

                    HSSFCell cell = row.getCell(cellNum);
                    if(cell == null){
                        continue;
                    }

                    String text = cell.getStringCellValue();

                    System.out.print("\t" + text);

                    if(cellNum + 1 == row.getLastCellNum()){
                        System.out.println();
                    }
                }
            }

            System.out.println("================================");

        }
    }
    //多种数据类型读取
    @Test
    public void test2() throws Exception{

        FileInputStream in = new FileInputStream("d:/poi1802/商品表-2-多种数据类型.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(in);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(workbook);

        for(int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++){

            HSSFRow row = sheet.getRow(rowNum);

            System.out.print("row：" + rowNum);

            for(int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++){

                HSSFCell cell = row.getCell(cellNum);
                if(cell == null){
                    continue;
                }


                System.out.println(cell.toString());
                int cellType = cell.getCellType();
                //System.out.println(cellType);

                System.out.println(cell.toString());

                /*switch (cellType){
                    case Cell.CELL_TYPE_NUMERIC://0


                        if(HSSFDateUtil.isCellDateFormatted(cell)){

                            double numericCellValue = cell.getNumericCellValue();
                            Date javaDate = HSSFDateUtil.getJavaDate(numericCellValue);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dateFormat = simpleDateFormat.format(javaDate);
                            System.out.println(dateFormat);

                        }else{

                            double numericCellValue = cell.getNumericCellValue();
                            System.out.println(numericCellValue);
                        }


                        break;

                    case Cell.CELL_TYPE_STRING://1

                        String stringCellValue = cell.getStringCellValue();
                        System.out.println(stringCellValue);
                        break;

                    case Cell.CELL_TYPE_FORMULA://2


                        String cellFormula = cell.getCellFormula();
                        System.out.println(cellFormula);

                        CellValue evaluate = formulaEvaluator.evaluate(cell);
                        int evaluateCellType = evaluate.getCellType();
                        switch (evaluateCellType){

                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.println("计算的数值：" + cell.getNumericCellValue());
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.println("计算的boolean：" + cell.getBooleanCellValue());
                                break;
                        }


                        break;


                    case Cell.CELL_TYPE_BLANK://3

                        System.out.println("null---");
                        break;


                    case Cell.CELL_TYPE_BOOLEAN://4

                        boolean booleanCellValue = cell.getBooleanCellValue();
                        System.out.println("boolean=" + booleanCellValue);
                        break;

                    case Cell.CELL_TYPE_ERROR:

                        System.out.println("数据类型错误");
                        break;

                    default:
                        System.out.println(cellType + "：未知数据类型");
                        break;
                }*/


                //System.out.print("\t" + text);

                if(cellNum + 1 == row.getLastCellNum()){
                    System.out.println();
                }
            }
        }



    }
}
