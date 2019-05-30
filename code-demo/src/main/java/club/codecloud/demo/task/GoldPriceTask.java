package club.codecloud.demo.task;

import club.codecloud.base.util.time.DateFormatUtils;

import club.codecloud.demo.service.component.MailClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ulei
 * @date 2018/10/12
 */
//@Component
public class GoldPriceTask {

    private static final Logger logger = LoggerFactory.getLogger(GoldPriceTask.class);
    private static final String GOLD_QUERY_URL = "http://www.dyhjw.com/hjtd/";
    private static final BigDecimal ALERT_GOLD_PRICE = new BigDecimal("273");

    private static final String[] EMAIL_TO = {"hull@codecloud.club", "zhouss@codecloud.club"};

    @Autowired
    MailClient mailClient;

    @Scheduled(cron = "0/30 * * * * ? ")
    public void exec() {
        Document document = null;
        try {
            document = Jsoup.connect(GOLD_QUERY_URL).get();
        } catch (IOException e) {
            logger.error("query error", e);
            throw new RuntimeException();
        }
        Elements elements = document.getElementsByClass("nom last red");
        for (Element element : elements) {
            BigDecimal currentPrice = new BigDecimal(element.html());
            if (currentPrice.compareTo(ALERT_GOLD_PRICE) > 0) {
                String log = DateFormatUtils.formatDate("HH:mm:ss", new Date()) + "金价：" + currentPrice;
                logger.warn("||||||||" + log);
                mailClient.sendSimpleMail(EMAIL_TO, log, log);
            } else {
                logger.info(DateFormatUtils.formatDate("HH:mm:ss", new Date()) + "金价：" + currentPrice);
            }
        }
    }
}
