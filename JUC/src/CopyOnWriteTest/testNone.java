package CopyOnWriteTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 *   Created by zview@qq.com on 2017/10/13.
 */
public class testNone {
    public static void main(String[] args) {

        list1 list1=new list1();
        Runnable r1=()->{
            for (int i = 0; i < 10; i++) {
                list1.write("z");
                 }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2=()->{
            for (int i = 0; i < 10; i++) {
                list1.read();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(r1).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(r2).start();
        }
    }
}

class list1{

    List<String> list=new ArrayList<>();

    public void read(){
        if(list.size()>0){
            System.out.println(list.get(0));
        }
    }

    public void write(String s){
        list.add(s);
        System.out.println("write");
    }
}
