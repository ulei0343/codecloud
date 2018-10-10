package design.pattern;

import com.google.common.eventbus.Subscribe;

/**
 * @author ulei
 * @date 2018/10/9
 */
public class OrderPayEventListener {
    @Subscribe
    public void success(OrderEventMessage event) {
        System.out.println(event.getOrderId() + "支付成功");
    }


    @Subscribe
    public void error(OrderEventMessage event) {
        System.out.println(event.getOrderId() + "支付失败");
    }
}
