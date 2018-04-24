package club.codecloud.task.finance.task;

import club.codecloud.base.util.HttpUtils;
import club.codecloud.message.api.service.MailMessageService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Observable;

/**
 * @author ulei
 * @date 2018/4/12
 */
@Component
public class GoldPriceQueryTask {

    private static final Logger logger = LoggerFactory.getLogger(GoldPriceQueryTask.class);

    /**
     * 查询地址
     */
    private static final String QUERY_URL = "http://www.sge.com.cn/graph/quotations";

    /**
     * 查询参数
     */
    private static final Map<String, String> PARAMS = ImmutableMap.of("instid", "Au99.99");

    /**
     * 报警值
     */
    private static final BigDecimal ALARM_VALUE = new BigDecimal(275.00);

    @Autowired
    MailMessageService mailMessageService;

    /**
     * 周一到周五，指定时间点，每分钟执行一次
     */
//    @Scheduled(cron = "0 0/1 0,1,2,9,10,11,13,14,15,20,21,22,23 ? * MON-FRI")
    public void exec() {
        String data = HttpUtils.post(QUERY_URL, PARAMS);
        JSONObject result = JSON.parseObject(data);
        JSONArray timesArray = result.getJSONArray("times");
        JSONArray priceArray = result.getJSONArray("data");
        // 金价初始值
        BigDecimal initValue = priceArray.getBigDecimal(0);
        // 当前金价
        BigDecimal currentGoldPrice = null;

        for (int i = priceArray.size() - 1; i > 0; i--) {
            if (priceArray.getBigDecimal(i).compareTo(initValue) > 0) {
                currentGoldPrice = priceArray.getBigDecimal(i);
                logger.info("[{}]金价：{}", timesArray.get(i), currentGoldPrice.doubleValue());
                break;
            }
        }
        if (currentGoldPrice == null) {
            logger.warn("没获取到当前金价");
            return;
        }

        // 触发报警
        if (currentGoldPrice.compareTo(ALARM_VALUE) > 0) {
            logger.info("当前金价：{}，已超过报警值：{}", currentGoldPrice, ALARM_VALUE);

            //放置一个被观察者
            Observable observable = new Observable();
            observable.addObserver(new GoldPriceObserver());
            observable.hasChanged();
            observable.notifyObservers(currentGoldPrice);
        }
//        mailMessageService.send("mail");
    }
}
