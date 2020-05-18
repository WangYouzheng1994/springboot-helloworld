package com.wyz.springboothelloworld.util;

public class JedisUtil {/*
    private static JedisPool pool = null;

    private static ThreadLocal<JedisPool> poolThreadLocal = new ThreadLocal<JedisPool>();

    public static JedisPool getPool() {
        *//*if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(Integer.valueOf(PropertyUtil.getRedisValue("redis.pool.maxActive")));
            config.setMaxIdle(Integer.valueOf(PropertyUtil.getRedisValue("redis.pool.maxIdle")));
            config.setMaxWait(Long.valueOf(PropertyUtil.getRedisValue("redis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(PropertyUtil.getRedisValue("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(PropertyUtil.getRedisValue("redis.pool.testOnReturn")));
            // 测试环境
            // pool = new JedisPool(config, bundle.getString("redis.ip"),
            // Integer.valueOf(bundle.getString("redis.port")));
            // 线上环境
            pool =
                    new JedisPool(config, PropertyUtil.getRedisValue("redis.ip"), Integer.valueOf(PropertyUtil
                            .getRedisValue("redis.port")), 100000, PropertyUtil.getRedisValue("redis.pool.password"));
        }*//*
        return pool;
    }

    public void setObject(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null) {
            return;
        }

        if (value instanceof String) {
            // String strKey = value;
        }
    }*/
}