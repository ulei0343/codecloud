package club.codecloud.demo.dao;

import club.codecloud.demo.entity.User;

import java.util.List;

/**
 * Created by ulei on 2018/7/26.
 */
public interface UserDao {

    List<User> listAllUser();
}
