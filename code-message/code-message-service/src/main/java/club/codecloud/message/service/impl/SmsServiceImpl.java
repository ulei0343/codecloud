package club.codecloud.message.service.impl;

import club.codecloud.message.api.service.SmsService;
import club.codecloud.message.service.dao.SmsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsServiceImpl implements SmsService {
    private final static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsDao smsDao;

    @Override
    public void send(String content) {
        logger.info("短信已接收：" + content);
        smsDao.insert();
    }
}
