package design.pattern;

/**
 * @author ulei
 * @date 2018/10/9
 */
public class OrderEventMessage {
    private Integer orderId;

    public OrderEventMessage(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
