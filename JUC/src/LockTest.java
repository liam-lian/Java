import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {

        Ticket t=new Ticket();
        new Thread(t,"Thread1").start();
        new Thread(t,"Thread2").start();
    }
}


class  Ticket  implements Runnable{

    int rest=50;
    Lock lock=new ReentrantLock();

    @Override
    public void run() {

        lock.lock();
        try {
            while (rest > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + rest--);
            }
        }finally {
            lock.unlock();
        }
    }
}