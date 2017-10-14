import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReasWriteLockTest {

    public static void main(String[] args) {
        testReadWriteLock t = new testReadWriteLock();
        new Thread(()->t.write(12),"write1").start();
        new Thread(()->t.write(120),"write2").start();
        for (int i = 0; i < 10; i++) {
            new Thread(()->t.read(),"read"+i).start();
        }
    }

}

class testReadWriteLock {


    int val;
    ReadWriteLock lock = new ReentrantReadWriteLock();

    void write(int val) {

        lock.writeLock().lock();

        try {
            this.val = val;
            System.out.println(Thread.currentThread().getName());

        } finally {
            lock.writeLock().unlock();
        }

    }

    void read() {

        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + val);
        } finally {
            lock.readLock().unlock();
        }

    }
}

