import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *   Created by zview@qq.com on 2017/10/16.
 */
public class Wait_Notify {

    public static void main(String[] args) {

        List<Object> list=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            new  Thread(new procuder(list,10),"Threadprodecer"+i).start();
        }
        for (int i = 0; i < 3; i++) {
            new  Thread(new customer(list),"Threadcustomer"+i).start();
        }
    }
}


class procuder implements Runnable {
    List<Object> list;
    int max;

    public procuder(List<Object> list, int max) {
        this.list = list;
        this.max = max;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                while (list.size() == max) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer i = new Random().nextInt();
                list.add(i);
                System.out.println(Thread.currentThread().getName() + ":" + i);
                list.notifyAll();
            }
        }
    }
}

class customer implements Runnable{
    List<Object> list;

    public customer(List<Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                while (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + list.get(0));
                list.remove(0);
                list.notifyAll();
            }
        }
    }

}

