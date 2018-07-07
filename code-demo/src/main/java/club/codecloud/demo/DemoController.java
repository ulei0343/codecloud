package club.codecloud.demo;


import club.codecloud.base.config.encrypt.annotation.Decrypt;
import club.codecloud.base.config.encrypt.annotation.Encrypt;
import club.codecloud.base.util.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/demo")
@ResponseBody()
public class DemoController {

    @GetMapping("/user")
    @Encrypt
    public Object get() {
        return "{\"name\":\"李四\",\"age\":18}";
    }


    @GetMapping("/get")
    public Object getInfo() {
        return Result.success("成功");
    }


    @PostMapping("/save")
    @Decrypt
    public Object save(@RequestBody @Valid User user) {
        return Result.success(user.getName());
    }


}
