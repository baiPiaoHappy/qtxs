import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 死锁
 * @author: 小猴子
 * @date: 2019-10-15 11:31
 */
public class TextThreadDemo2 {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        deadLock();
    }

    private static void deadLock(){
        Thread T1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A){
                    try { Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread T2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("0");
                    }
                }
            }
        });
    }
}
