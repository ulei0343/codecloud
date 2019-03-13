package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用程序实现两个线程交替打印 0~100 的奇偶数
 * <p>
 * 锁池:假设线程A已经拥有了某个对象(注意:不是类)的锁，而其它的线程想要调用这个对象的某个synchronized方法(或者synchronized块)，由于这些线程在进入对象的synchronized方法之前必须先获得该对象的锁的拥有权，
 * 但是该对象的锁目前正被线程A拥有，所以这些线程就进入了该对象的锁池中。
 * <p>
 * <p>
 * 等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁(因为wait()方法必须出现在synchronized中，这样自然在执行wait()方法之前线程A就已经拥有了该对象的锁)，同时线程A就进入到了该对象的等待池中。
 * 如果另外的一个线程调用了相同对象的notifyAll()方法，那么处于该对象的等待池中的线程就会全部进入该对象的锁池中，准备争夺锁的拥有权。如果另外的一个线程调用了相同对象的notify()方法，那么仅仅有一个处于该对象的等待池中的线程(随机)会进入该对象的锁池.
 *
 * @author: ulei
 * @date: 2019-03-12
 */
public class OddEvenPrint2 {
    private static int count = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition oddCondition = lock.newCondition();

    private static final Condition evenCondition = lock.newCondition();


    public static void main(String[] args) {
        Thread evenPrintThread = new Thread(new EvenPrintRunnable());
        evenPrintThread.start();

        Thread oddPrintThread = new Thread(new OddPrintRunnable());
        oddPrintThread.start();
    }


    /**
     * 奇数打印线程
     */
    private static class OddPrintRunnable implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                try {
                    lock.lock();
                    System.out.println(count++);
                    evenCondition.signal();
                    oddCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 奇数打印线程
     */
    private static class EvenPrintRunnable implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                try {
                    lock.lock();
                    System.out.println(count++);
                    oddCondition.signal();
                    evenCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
