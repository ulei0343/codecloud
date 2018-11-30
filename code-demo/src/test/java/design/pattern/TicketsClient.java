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


    public synchronized void test1() {
        System.out.println("test1--" + Thread.currentThread().getName() + "--" + +System.currentTimeMillis() + this);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() {
        synchronized (this) {
            System.out.println("test2--" + Thread.currentThread().getName() + "--" + +System.currentTimeMillis());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test3() {
        synchronized (this.getClass()) {
            System.out.println("test3--" + Thread.currentThread().getName() + "--" + +System.currentTimeMillis() + this.getClass());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
