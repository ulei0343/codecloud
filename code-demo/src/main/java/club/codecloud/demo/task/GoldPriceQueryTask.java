package club.codecloud.demo.task;

import club.codecloud.base.util.net.HttpUtils;
import club.codecloud.base.util.time.DateFormatUtils;
import club.codecloud.demo.common.mail.MailClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ulei
 * @date 2018/4/12
 */
//@Component
public class GoldPriceQueryTask {

    private static final Logger logger = LoggerFactory.getLogger(GoldPriceQueryTask.class);

    /**
     * 查询地址
     */
    private static final String QUERY_URL = "http://www.sge.com.cn/graph/quotations";

    /**
     * 黄金类型
     */
    private static final String GOLD_TYPE = "Au99.99";

    /**
     * 查询参数
     */
    private static final Map<String, String> QUERY_PARAMS = ImmutableMap.of("instid", GOLD_TYPE);

    /**
     * 报警值
     */
    private static final BigDecimal ALARM_VALUE = new BigDecimal(275.00);

    private static Cache<String, BigDecimal> cache = CacheBuilder.newBuilder().expireAfterWrite(12, TimeUnit.HOURS).build();

    @Autowired
    MailClient mailClient;

    /**
     * 周一到周五，指定时间点，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 0,1,2,9,10,11,13,14,15,20,21,22,23 ? * MON-FRI")
    public void exec() {

    }

    public static void main(String[] args) {

        String data = HttpUtils.post(QUERY_URL, QUERY_PARAMS);
        JSONObject result = JSON.parseObject(data);

        String nowTime = DateFormatUtils.formatDate(DateFormatUtils.TIME_FORMAT, new Date());
        JSONArray times = result.getJSONArray("times");
        int index = -1;
        for (int i = 0; i < times.size(); i++) {
            if (nowTime.equals(times.getString(i))) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            logger.warn("没获取到当前金价");
            return;
        }
        BigDecimal currentGoldPrice = result.getJSONArray("data").getBigDecimal(index);


        if (currentGoldPrice.compareTo(ALARM_VALUE) > 0 && cache.getIfPresent(GOLD_TYPE) != null) {
            logger.info("当前金价：{}，已超过报警值：{}", currentGoldPrice, ALARM_VALUE);


        }

    }
}
