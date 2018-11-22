import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HospitalRegistration {
    private static final Logger logger = LoggerFactory.getLogger(HospitalRegistration.class);

    public static void main(String[] args) {
        String url = "http://www.bjguahao.gov.cn/dpt/hps/%s,0,0,0,108.htm?";

        for (int i = 1; i <= 10; i++) {
            String requestUrl = String.format(url, i);
            Document document = null;
            try {
                document = Jsoup.connect(requestUrl).get();
            } catch (IOException e) {
                break;
            }
            Elements hospitals = document.getElementsByClass("yiyuan_content_1");
            for (Element hospital : hospitals) {
                String hospitalName = hospital.getElementsByClass("yiyuan_co_titl").get(0).getElementsByTag("a").html();
                Elements departments = hospital.getElementsByClass("ksgh_listgg-bottom").get(0).getElementsByTag("a");
                for (Element department : departments) {
                    logger.info("【page:{}】医院:{},门诊:{},链接:{}",i,hospitalName,department.html(),"http://www.bjguahao.gov.cn" + department.attr("href"));
                }

            }

        }


    }
}
