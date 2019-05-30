package club.codecloud.demo.service.impl;

import club.codecloud.demo.DemoApplication;
import club.codecloud.demo.entity.UserDO;
import club.codecloud.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ulei
 * @date: 2019-05-29
 */
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void selectById() {
        UserDO userDO = userService.selectById(1);
        System.out.println(userDO.getStatus());
    }
}