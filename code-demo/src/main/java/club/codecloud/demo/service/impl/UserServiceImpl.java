package club.codecloud.demo.service.impl;

import club.codecloud.demo.dao.UserDao;
import club.codecloud.demo.entity.UserDO;
import club.codecloud.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ulei
 * @date 2018/9/3
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDO> listAllUser() {
        System.out.println("listAllUser");
        return userDao.listAllUser();
    }
}
