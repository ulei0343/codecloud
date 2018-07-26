package club.codecloud.task.controller;

import club.codecloud.task.client.MailClient;
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
    MailClient mailClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value = "content") String content) {
        mailClient.send(content);
        System.out.println(content);
        return "success";
    }
}
