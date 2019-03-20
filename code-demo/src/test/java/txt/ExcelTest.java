package txt;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * @author: ulei
 * @date: 2019-03-20
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("基础数据");
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("班级");

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("张三");
        row1.createCell(1).setCellValue("二班");

        HSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("张三2");
        row2.createCell(1).setCellValue("三班");

        wb.write(new File("/Users/ulei/Documents/Dev/Code/codecloud/code-demo/src/test/java/txt/data.xls"));

        System.out.println("ok");
    }
}
