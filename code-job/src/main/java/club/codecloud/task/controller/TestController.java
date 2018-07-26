package club.codecloud.task.controller;

import club.codecloud.task.client.MailFeign;
import club.codecloud.task.client.SmsFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ulei
 * @date 2018/4/12
 */
@RestController
public class TestController {

    @Autowired
    private MailFeign mailFeign;

    @Autowired
    private SmsFeign smsFeign;



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value = "content") String content) {
        mailFeign.send(content);
        System.out.println(content);
        return "success";
    }

    @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
    public String sendSms(@RequestParam(value = "content") String content) {
        smsFeign.send(content);
        System.out.println(content);
        return "success";
    }
}
