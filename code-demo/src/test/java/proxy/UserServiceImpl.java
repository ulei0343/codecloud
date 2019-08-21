package proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ulei
 * @date: 2019-07-03
 */
@Slf4j
public class UserServiceImpl implements UserSerivce {
    @Override
    public int add(int id, String name) {
        log.info("add");
        return 1;
    }
}
