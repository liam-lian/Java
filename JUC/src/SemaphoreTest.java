import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 *   Created by zview@qq.com on 2017/10/16.
 */
public class SemaphoreTest {


    public static void main(String[] args) {

        ExecutorService executorService= Executors.newFixedThreadPool(20);
        Semaphore semaphore=new Semaphore(5);

        for (int i = 0; i < 100; i++) {
            Runnable r=()->{
                try {
                    semaphore.acquire();
                    System.out.println(semaphore.availablePermits()+":"+semaphore.getQueueLength());
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.submit(r);
        }
        executorService.shutdown();
    }
}
