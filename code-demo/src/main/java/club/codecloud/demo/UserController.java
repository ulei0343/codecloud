package club.codecloud.demo;

import club.codecloud.base.util.base.Result;
import club.codecloud.base.util.number.RandomUtils;
import club.codecloud.demo.entity.UserDO;
import club.codecloud.demo.service.UserService;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ulei
 * @date 2018/9/4
 */
@RestController
@RequestMapping("user")
@ResponseBody
public class UserController {

    @CreateCache(cacheType = CacheType.LOCAL, localExpire = 60)
    private Cache<Integer, String> usernameCache;

    @Autowired
    private UserService userService;


    @GetMapping("/listAllUser")
    public Object listAllUser() {
        List<UserDO> userList = userService.listAllUser();
        return Result.success(userList);
    }

    /**
     * 根据ID获取信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Object selectById(@PathVariable("id") Integer id) {
        UserDO userDO = userService.selectById(id);
        return Result.success(userDO);
    }

    @GetMapping("{id}/name")
    public Object selectNameById(@PathVariable("id") Integer id) {
        String username = userService.selectNameById(id);
        return Result.success(username);
    }

    @GetMapping("{id}/cname")
    public Object selectNameByIdFromCache(@PathVariable("id") Integer id) {
        String username = usernameCache.get(id);
        if (StringUtils.isEmpty(username)) {
            username = generateRandomUsername();
            usernameCache.put(id, username);
        }
        return Result.success(username);
    }

    /**
     * 删除某个ID的信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Object delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return Result.success(null);
    }

    /**
     * 保存某个ID的信息
     *
     * @param userDO
     * @return
     */
    @PostMapping
    public Object insert(UserDO userDO) {
        userService.insert(userDO);
        return Result.success(null);
    }

    /**
     * 跟新某个ID的信息
     *
     * @param userDO
     * @return
     */
    @PutMapping("{id}")
    public Object update(UserDO userDO) {
        userService.updateNameById(userDO);
        return Result.success(null);
    }

    private String generateRandomUsername() {
        String username = RandomUtils.randomStringFixLength(8);
        System.out.println("generateRandomUsername" + username);
        return username;
    }


}
