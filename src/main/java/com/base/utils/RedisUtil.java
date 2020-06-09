package com.base.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description: redis 工具
 * @author: 小猴子
 * @date: 2019-11-11 16:56
 */
public class RedisUtil {

    public static   JedisPool jedisPool = com.base.utils.JedisPool.getJedisPoolInstance();

    public static final int SET = 0;

    public static final int HASH = 1;

    public static final int LIST = 2;

    public static final int STRING = 3;

    //redis新增数据
    public static String addRedis(int type,String key,String filed,String value){
        Jedis jedis = jedisPool.getResource();
        String msg = null;
        switch (type) {
            case 0: // set
                msg = jedis.set(key,value);
                jedis.expire(key,36000);
                break;
            case 1: // hash
               msg = jedis.mset(key,filed,value);
               jedis.expire(key,36000);
                break;
            case 2: // list

                break;
            case  3: // string
                msg = jedis.set(key,value);
                jedis.expire(key,36000);
                break;
            default:
        }
        return msg;
    }
    //redis删除数据
    public static long delRedis(String key){
        Jedis jedis = jedisPool.getResource();
        long code = 0;
        code = jedis.del(key);
        return code;
    }
/*    //redis更新数据
    public static long updateRedis(int type,String key,String filed,String value){
        Jedis jedis = jedisPool.getResource();
        long code = 0;
        switch (type) {
            case 0:
                jedis.set(key,value);
                jedis.expire(key,36000);
                break;
            case 1:
                code = jedis.mset(key,filed,value);
                jedis.expire(key,36000);
                break;
            case 2:

                break;
            case  3:
                code = jedis.set(key,value);
                jedis.expire(key,36000);
                break;
            default:
        }
    }*/

    //redis获取数据
    public static Object getRedis(int type,String key,String filed){
        Jedis jedis = jedisPool.getResource();
        Object o = new Object();
        switch (type) {
            case 0:
                o = jedis.get(key);
                break;
            case 1:
                o = jedis.hgetAll(key);
                break;
            case 2:

                break;
            case  3:
                o = jedis.get(key);
                break;
            default:
        }
        closeRedis(jedis);
        return o;
    }

    //关闭连接
    private static void closeRedis(Jedis jedis){
        jedis.close();
    }

}
