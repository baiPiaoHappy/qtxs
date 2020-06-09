import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: redis 连接池
 * @author: 小猴子
 * @date: 2019-10-18 13:29
 */
public class JedisPoolUtil {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6379;
    private static int getNum = 0;

    private static volatile JedisPool jedisPool =null;

    private JedisPoolUtil(){ }

    /**
     *  单例模式获取JedisPool实例
     * @return
     */
    public static JedisPool getJedisPoolInstance(){
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class){
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(100); //最大连接数
                jedisPoolConfig.setMaxIdle(5);  //最大空闲连接数
                jedisPoolConfig.setMaxWaitMillis(3000); //最大等待时间
                jedisPoolConfig.setTestOnBorrow(true); //检测连接是否可用

                jedisPool = new JedisPool(jedisPoolConfig,HOST,PORT);
            }
        }
        return jedisPool;
    }

    /**
     * 从连接池获取jedis实例连接
     * @return
     */
    public static Jedis getJedisInstance(){
        Jedis jedis = getJedisPoolInstance().getResource();
        System.out.println("获取第"+getNum+"条redis连接");
        getNum++;
        return getJedisPoolInstance().getResource();
    }

    /**
     * 释放
     * @param jedis
     * @param jedisPool
     */
    public static void release(Jedis jedis,JedisPool jedisPool){
        if (jedis !=null) {
            jedisPool.close();
            System.out.println("释放一条连接1");
        }
    }

}
