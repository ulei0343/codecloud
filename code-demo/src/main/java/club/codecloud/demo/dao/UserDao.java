package club.codecloud.demo.dao;

import club.codecloud.base.common.dao.BaseDao;
import club.codecloud.demo.entity.UserDO;

import java.util.List;

/**
 * Created by ulei on 2018/7/26.
 */
public interface UserDao extends BaseDao {

    List<UserDO> listAllUser();
}
