package design.pattern;

/**
 * @author ulei
 * @date 2018/9/5
 */
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
//        TicketsClient.getInstance();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                TicketsClient.getInstance().test1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TicketsClient.getInstance().test2();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                TicketsClient.getInstance().test3();
            }
        }, "t3");

        t1.start();
//        t2.start();
        t3.start();

        Thread.sleep(3000);
    }


}
