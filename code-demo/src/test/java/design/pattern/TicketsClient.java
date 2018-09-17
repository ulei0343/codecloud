package design.pattern;

/**
 * @author ulei
 * @date 2018/9/5
 */
public class TicketsClient {

    private TicketsClient() {
        System.out.println("init TicketsClient");
    }

    private static class SingletonHolder {
        private static final TicketsClient INSTANCE = new TicketsClient();
    }

    public static TicketsClient getInstance() {
        System.out.println("getInstance");
        return SingletonHolder.INSTANCE;
    }
}
