import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

/*
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 *
 * 二、线程池的体系结构：
 * 	java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 * 		|--**ExecutorService 子接口: 线程池的主要接口
 * 			|--ThreadPoolExecutor 线程池的实现类
 * 			|--ScheduledExecutorService 子接口：负责线程的调度
 * 				|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
 *
 * 三、工具类 : Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 */
    public static void main(String[] args) {
       ExecutorService pool= Executors.newFixedThreadPool(3);
        List<Future<Integer>> list=new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<Integer> future=pool.submit(()->{
                int sum=0;
                for (int j = 0; j < 10; j++) {
                    sum+=j;
                    System.out.println(Thread.currentThread().getName()+" "+sum);
                }
                return sum;
            });
            list.add(future);
        }

        pool.shutdown();
        for(Future<Integer> i:list){

            try {
                System.out.println(i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        ScheduledExecutorService spool=Executors.newScheduledThreadPool(3);
        for (int j = 0; j < 10; j++) {
            spool.schedule(()->{
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            },3,TimeUnit.SECONDS);  //这里表示的是3s之后执行，前面是数量，后面是数量的单位

        }

        System.out.println("zz");
        spool.shutdown();

    }
}


