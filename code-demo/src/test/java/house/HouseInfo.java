package house;

import com.google.common.collect.Sets;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author: ulei
 * @date: 2019-05-22
 */
public class HouseInfo {

    private static final Logger logger = LoggerFactory.getLogger(HouseInfo.class);

    public static void main(String[] args) throws IOException {
        Set<String> urls = Sets.newHashSet("https://bj.ke.com/ershoufang/19032819510100103514.html",
                "https://bj.ke.com/ershoufang/19042119510100141025.html");

        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet("房屋信息");
        HSSFRow firstRow = sheet.createRow(0);

        firstRow.createCell(0).setCellValue("主标题");
        firstRow.createCell(1).setCellValue("子标题");
        firstRow.createCell(2).setCellValue("总价");
        firstRow.createCell(3).setCellValue("单价");
        firstRow.createCell(4).setCellValue("房屋户型");
        firstRow.createCell(5).setCellValue("所在楼层");
        firstRow.createCell(6).setCellValue("建筑面积");
        firstRow.createCell(7).setCellValue("户型结构");
        firstRow.createCell(8).setCellValue("套内面积");
        firstRow.createCell(9).setCellValue("建筑类型");
        firstRow.createCell(10).setCellValue("房屋朝向");
        firstRow.createCell(11).setCellValue("建筑结构");
        firstRow.createCell(12).setCellValue("装修情况");
        firstRow.createCell(13).setCellValue("梯户比例");
        firstRow.createCell(14).setCellValue("供暖方式");
        firstRow.createCell(15).setCellValue("配备电梯");
        firstRow.createCell(16).setCellValue("产权年限");
        firstRow.createCell(17).setCellValue("挂牌时间");
        firstRow.createCell(18).setCellValue("交易权属");
        firstRow.createCell(19).setCellValue("上次交易");
        firstRow.createCell(20).setCellValue("房屋用途");
        firstRow.createCell(21).setCellValue("房屋年限");
        firstRow.createCell(22).setCellValue("产权所属");
        firstRow.createCell(23).setCellValue("抵押信息");
        firstRow.createCell(24).setCellValue("房本备件");

        int rowNum = 1;
        for (String url : urls) {
            Document page = Jsoup.connect(url).get();

            HSSFRow row1 = sheet.createRow(rowNum);
            String title = page.getElementsByClass("main").get(0).html();
            row1.createCell(0).setCellValue(title);

            String subTitle = page.getElementsByClass("sub").get(0).html();
            row1.createCell(1).setCellValue(subTitle);

            String total = page.getElementsByClass("total").get(0).html().concat("万");
            row1.createCell(2).setCellValue(total);

            String unitPriceValue = page.getElementsByClass("unitPriceValue").get(0).html().concat("元/平米");
            row1.createCell(3).setCellValue(unitPriceValue);

            Elements houseInfoElements = page.getElementsByClass("introContent").get(0).getElementsByTag("li");
            for (int i = 0, column = 4; i < houseInfoElements.size(); i++, column++) {
                if (i == houseInfoElements.size() - 2) {
                    row1.createCell(column).setCellValue(houseInfoElements.get(i).getElementsByTag("span").get(1).html());
                } else {
                    row1.createCell(column).setCellValue(houseInfoElements.get(i).html().split("</span>")[1]);
                }
            }
            rowNum++;
        }

        excel.write(new File("/Users/ulei/Documents/Dev/Code/codecloud/code-demo/src/test/java/house/房屋信息.xls"));
        logger.info("导出完成");
    }


}
