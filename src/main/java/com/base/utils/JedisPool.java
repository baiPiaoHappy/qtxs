package com.base.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: redis 连接池
 * @author: 小猴子
 * @date: 2019-11-12 16:02
 */
public class JedisPool {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6379;
    private static int getNum = 0;

    private static volatile redis.clients.jedis.JedisPool jedisPool =null;

    private JedisPool(){ }

    /**
     *  单例模式获取JedisPool实例
     * @return
     */
    public static redis.clients.jedis.JedisPool getJedisPoolInstance(){
        if (jedisPool == null) {
            synchronized (JedisPool.class){
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(100); //最大连接数
                jedisPoolConfig.setMaxIdle(5);  //最大空闲连接数
                jedisPoolConfig.setMaxWaitMillis(3000); //最大等待时间
                jedisPoolConfig.setTestOnBorrow(true); //检测连接是否可用
                jedisPool = new redis.clients.jedis.JedisPool(jedisPoolConfig,HOST,PORT);
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
        getNum++;
        return getJedisPoolInstance().getResource();
    }

    /**
     * 释放
     * @param jedis
     * @param jedisPool
     */
    public static void release(Jedis jedis, redis.clients.jedis.JedisPool jedisPool){
        if (jedis !=null) {
            jedisPool.close();
        }
    }
}
