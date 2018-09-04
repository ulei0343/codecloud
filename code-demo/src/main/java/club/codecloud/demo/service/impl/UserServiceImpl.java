package club.codecloud.demo.service.impl;

import club.codecloud.base.common.service.BaseServiceImpl;
import club.codecloud.demo.dao.UserDao;
import club.codecloud.demo.entity.UserDO;
import club.codecloud.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ulei
 * @date 2018/9/3
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDO, UserDao> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDO> listAllUser() {
        System.out.println("listAllUser");
        return userDao.listAllUser();
    }

    @Override
    public int updateNameById(UserDO userDO) {
        return userDao.updateById(userDO);
    }

    @Override
    public String selectNameById(Serializable id) {
        UserDO user = (UserDO) userDao.selectById(id);
        return user.getUsername();
    }


}
