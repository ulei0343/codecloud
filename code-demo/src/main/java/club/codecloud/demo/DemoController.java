package club.codecloud.demo;


import club.codecloud.base.config.encrypt.annotation.Decrypt;
import club.codecloud.base.config.encrypt.annotation.Encrypt;
import club.codecloud.base.util.base.Result;
import club.codecloud.base.util.cache.RedisUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/demo")
@ResponseBody
public class DemoController {

    @GetMapping("/user")
    @Encrypt
    public Object get() {
        return "{\"name\":\"李四\",\"age\":18}";
    }


    @GetMapping("/get")
    public Object getInfo() throws InterruptedException {
        Thread.sleep(2000);
        return Result.success("成功");
    }


    @PostMapping(value = "/sendSms")
    public Object sendSms(HttpServletRequest request) throws InterruptedException {
        log.info(request.getParameter("content"));
        Thread.sleep(3000);
        return "success:ok";
    }


    @PostMapping("/save")
    @Decrypt
    public Object save(@RequestBody @Valid User user) {
        return Result.success(user.getName());
    }


    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/redis")
    public String redis() {
        String key = "test";
        redisUtils.set(key, "test");
        System.out.println(redisUtils.get(key));
        return "ok";
    }


}
