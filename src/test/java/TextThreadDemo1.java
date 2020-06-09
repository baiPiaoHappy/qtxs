import org.junit.Test;

/**
 * @description:
 * @author: 小猴子
 * @date: 2019-10-13 17:18
 */

public class TextThreadDemo1 {

    public static final  long count=1000001;

    public static void main(String[] args) throws InterruptedException {
        //并行
        concurrency();
        //串行
        serial();
    }

    private static void concurrency () throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for(int i = 0; i < count; i++){
                    //a += 5;
                    System.out.println("任豚了");
                    return ;
                }
            }
        });
        thread.start();
        int b=0;
        for(int i = 0; i < count; i++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis()-start;
        System.out.print("concurrencu :"+time+"ms,b="+b);

    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(" serial :"+time+"ms,b="+b+",a="+a);

    }

}
