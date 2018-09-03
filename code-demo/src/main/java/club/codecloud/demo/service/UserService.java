package club.codecloud.demo.service;

import club.codecloud.demo.entity.UserDO;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

import java.util.List;

/**
 * @author ulei
 */
public interface UserService {

    @Cached(name = "userCache", cacheType = CacheType.REMOTE, expire = 100)
    List<UserDO> listAllUser();
}
