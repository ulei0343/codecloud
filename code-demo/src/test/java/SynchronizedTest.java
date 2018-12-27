public class SynchronizedTest {

    /**
     * 锁住class对象
     */
    public void synchronizedClass() {
        synchronized (SynchronizedTest.class) {
            System.out.println("synchronizedClass" + System.currentTimeMillis() + SynchronizedTest.class);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * 锁住class对象
     */
    public void synchronizedClass2() {
        synchronized (this.getClass()) {
            System.out.println("synchronizedClass" + System.currentTimeMillis() + SynchronizedTest.class);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * 锁住方法，lock标记打在该实力上
     */
    public synchronized void synchronizedMethod() {
        System.out.println("synchronizedMethod" + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 锁住静态方法，lock标记打在该实力上
     */
    public static synchronized void synchronizedStaticMethod() {
        System.out.println("synchronizedStaticMethod" + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 不会有影响，正常调用
     */
    public void synchronizedMethod2WithNosynchronized() {
        System.out.println("synchronizedMethod2WithNosynchronized  no" + System.currentTimeMillis());
    }

    /**
     * synchronizedMethod 已经锁住实例， 再加锁不成功
     */
    public void synchronizedThis1() {
        synchronized (SynchronizedTest.this) {
            System.out.println("synchronizedThis1" + System.currentTimeMillis() + SynchronizedTest.this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * synchronizedMethod 已经锁住实例， 再加锁不成功
     */
    public void synchronizedThis2() {
        synchronized (this) {
            System.out.println("synchronizedThis2" + System.currentTimeMillis() + this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        final SynchronizedTest t = new SynchronizedTest();
        final SynchronizedTest t1 = new SynchronizedTest();
        //调用代码看下面
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                t.synchronizedThis1();
            }
        });
        th.start();
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                t.synchronizedThis2();
            }
        });
        th.start();
//        t.synchronizedMethod2WithNosynchronized();


    }

}
