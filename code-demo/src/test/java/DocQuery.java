import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author: ulei
 * @date: 2019-07-18
 */
public class DocQuery {

    private static final String REQUEST_URL = "http://bjgy.chinacourt.gov.cn/article/index/id/MzAwMjAwNjAwMSACAAA/page/%s.shtml";
//    private static final String REQUEST_URL = "http://bjgy.chinacourt.gov.cn/article/index/id/MzAwMjAwBiPCAAA/page/%s.shtml";

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 30; i++) {
            System.out.println("第" + i + "页查询中");
            String url = String.format(REQUEST_URL, i);
            Document document = Jsoup.connect(url).get();
            document.getElementsByClass("left").forEach(element ->
            {

                Element ele = element.getElementsByTag("a").get(0);
                String name = ele.html();
                if (name.contains("双惠")) {
                    System.out.println(name + "-----" + ele.attr("href"));
                }
            });

        }

    }
}
