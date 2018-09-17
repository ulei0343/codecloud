package club.codecloud.demo;

import club.codecloud.base.util.base.Result;
import club.codecloud.base.util.number.RandomUtils;
import club.codecloud.demo.entity.UserDO;
import club.codecloud.demo.entity.UserRequest;
import club.codecloud.demo.entity.UserResponse;
import club.codecloud.demo.service.UserService;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    @CreateCache(cacheType = CacheType.LOCAL, expire = 5)
    private Cache<Integer, String> usernameCache;

    @Autowired
    private UserService userService;


    @GetMapping("/listAllUser")
    public Result listAllUser() {
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
    public Result selectById(@PathVariable("id") Integer id) {
        UserDO userDO = userService.selectById(id);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userDO, userResponse);
        return Result.success(userResponse);
    }

    @GetMapping("{id}/name")
    public Result selectNameById(@PathVariable("id") Integer id) {
        String username = userService.selectNameById(id);
        return Result.success(username);
    }

    @GetMapping("{id}/cname")
    public Result selectNameByIdFromCache(@PathVariable("id") Integer id) {
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
    public Result delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return Result.success(null);
    }

    /**
     * 保存某个ID的信息
     *
     * @param userRequest
     * @return
     */
    @PostMapping
    public Result insert(UserRequest userRequest) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userRequest, userDO);
        userService.insert(userDO);
        return Result.success(userDO.getId());
    }

    /**
     * 跟新某个ID的信息
     *
     * @param userRequest
     * @return
     */
    @PutMapping("{id}")
    public Result update(UserRequest userRequest) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userRequest, userDO);
        userService.updateNameById(userDO);
        return Result.success("ok");
    }

    private String generateRandomUsername() {
        String username = RandomUtils.randomStringFixLength(8);
        System.out.println("generateRandomUsername:" + username);
        return username;
    }


}
