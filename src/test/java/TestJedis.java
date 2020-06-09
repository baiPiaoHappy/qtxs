import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @description: redis 测试
 * @author: 小猴子
 * @date: 2019-10-17 13:47
 */
public class TestJedis {

    private static final  int SIZE = 100;

    private static JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();


    public static void main(String[] args) throws InterruptedException {
        //StreesTesting();
        set();
    }

    @Test
    public static void StreesTesting() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < SIZE; i++) {
                    Jedis jedis = jedisPool.getResource();
                    System.out.println("我是第"+(i+1)+"号，我取到了"+jedis.get("bl"));
                }
            }
        });
        thread.start();

        long time = System.currentTimeMillis() - start;
        System.out.println("time:"+time);

    }



    public static void set(){
        Jedis jedis = jedisPool.getResource();
        //哈希
        for (int i = 0;i < SIZE; i++) {
            String uuid = UUID.randomUUID().toString().replace("-","");
            Integer sex = i%2;
            Integer high = i/10+170;
            jedis.hset("param:"+uuid,"sex", new Random(1)+"");
            jedis.hset("param:"+uuid,"age",sex+"");
            jedis.hset("param:"+uuid,"high",high+"");
            jedis.expire("param:"+uuid,360);
            System.out.println( "id:"+uuid+",性别:"+(sex == 0 ? "男":"女")+",身高:"+high+"cm" );
        }
    }




    public T updateT(T t){

        Jedis jedis =jedisPool.getResource();

        //jedis.hmset();

        return t;
    }








}
