import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *   Created by zview@qq.com on 2017/10/16.
 */
public class ConditionTest {


    List list=new ArrayList();
    Lock lock=new ReentrantLock();


}

class  producer implements Runnable{

    List<Object> list;
    int max;
    Lock lock;
    Condition condition=lock.newCondition();

    public producer(List<Object> list,int max,Lock lock) {
        this.list = list;
        this.max=max;
        this.lock=lock;
        condition=lock.newCondition();
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            while (max<=list.size()){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int i=new Random().nextInt(20);
            list.add(i);
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+":add"+i);
        }
    }
}
class  customer1 implements Runnable{

    List<Object> list;
    Condition condition=lock.newCondition();

    public customer1(List<Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            while (0==list.size()){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":rem"+list.get(0));
            list.remove(0);
            condition.signalAll();
        }
    }
}
