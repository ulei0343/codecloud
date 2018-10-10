package design.pattern;

import com.google.common.eventbus.EventBus;

/**
 * @author ulei
 * @date 2018/10/9
 */
public class OrderPayCallback {
    public static void main(String[] args) {
        Integer orderId = 123;
        EventBus orderEvent = new EventBus("orderEvent");
        orderEvent.register(new OrderPayEventListener());
        orderEvent.post(new OrderEventMessage(orderId));
    }
}
