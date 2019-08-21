package proxy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ulei
 * @date: 2019-07-03
 */
@Slf4j
public class PrintInvocationHandler implements InvocationHandler {
    private Object target;

    public PrintInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("param:[{}]", JSON.toJSONString(args));
        Object result = method.invoke(target, args);
        log.info("result:[{}]", JSON.toJSONString(result));
        return result;
    }

    public Object getProxy() {
        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        System.out.println(proxyClass);


        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
