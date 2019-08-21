package proxy;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author: ulei
 * @date: 2019-07-03
 */
@Slf4j
public class ProxyTest {


    @Test
    public void add() {
        // 如果需要产生动态代理类，需添加参数，即可生成
        // -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
        UserSerivce userSerivce = new UserServiceImpl();

        PrintInvocationHandler handler = new PrintInvocationHandler(userSerivce);

        UserSerivce userServiceProxy = (UserSerivce) handler.getProxy();

        int result = userServiceProxy.add(1, "张三");
        log.info("result:[{}]", result);

    }

    @Test
    public void remove1() {
        List<String> names = Lists.newArrayList("zhangsan", "lisi", "wangwu", "zhaoliu");
        for (String name : names) {
            if (name.equals("wangwu")) {
                names.remove(name);
                continue;
            }
            System.out.println(name);
        }
        System.out.println(names);
    }

    @Test
    public void remove2() {
        List<String> names = Lists.newArrayList("zhangsan", "lisi", "wangwu", "zhaoliu");
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals("wangwu")) {
                names.remove(i);
                continue;
            }
            System.out.println(names.get(i));
        }
        System.out.println(names);
    }

    @Test
    public void remove3() {
        List<String> names = Lists.newArrayList("zhangsan", "lisi", "wangwu", "zhaoliu");
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals("wangwu")) {
                names.remove("wangwu");
                continue;
            }
            System.out.println(name);
        }

        System.out.println(names);
    }

}
