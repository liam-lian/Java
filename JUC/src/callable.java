import java.util.UUID;
import java.util.concurrent.*;

public class callable {

    public static void main(String[] args) {




        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);

        Callable<Integer> task=()->{
            TimeUnit.SECONDS.sleep(2);
            return 1;
        };

        FutureTask<Integer> future=new FutureTask<>(task);

        new Thread(future).start();

        try {
            System.out.println(future.get(1,TimeUnit.SECONDS));
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
