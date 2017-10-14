import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/*
 *   Created by zview@qq.com on 2017/10/13.
 */
public class ForkandJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool p=new ForkJoinPool();
        Future future=p.submit(new calBigSum(0,1000000000));
        System.out.println(future.get());


    }
}

class calBigSum extends RecursiveTask<Long>{


    long limit=1000;
    long start,end;

    public calBigSum(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end-start<10000){
            long sum=0;
            for (long i =start ; i <=end ; i++) {
                sum+=i;
            }
            return sum;
        }
        else {
            long mid=(start+end)/2;
            calBigSum left=new calBigSum(start,mid);
            calBigSum right=new calBigSum(mid+1,end);

            //fork就是去执行下面这个任务，其实就是执行compute这个函数
            left.fork();
            right.fork();

            //join等待fork的结果并返回
            return left.join()+right.join();
        }
    }
}
