package club.codecloud.demo.service;

import club.codecloud.base.common.service.BaseService;
import club.codecloud.demo.entity.UserDO;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ulei
 */
public interface UserService extends BaseService<UserDO> {

//    @Cached(name = "userCache", expire = 60)
    List<UserDO> listAllUser();

//    @CacheInvalidate(name = "user:selectNameById:", key = "#userDO.getId()")
//    @CacheUpdate(name = "user:selectNameById:", key = "#userDO.getId()", value = "#userDO.getUsername()")
    int updateNameById(UserDO userDO);

//    @Cached(name = "user:selectNameById:", key = "#id", expire = 1, timeUnit = TimeUnit.HOURS)
    String selectNameById(Serializable id);
}
