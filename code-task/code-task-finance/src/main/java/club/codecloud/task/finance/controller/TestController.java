package club.codecloud.task.finance.controller;

import club.codecloud.message.api.service.EmailMessageService;
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
    EmailMessageService emailMessageClient;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloConsumer3(@RequestParam(value = "content") String content) {
        emailMessageClient.send(content);
        System.out.println(content);
        return "success";
    }
}
