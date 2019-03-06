package com.utils;

import com.constant.Constant;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	//�������ӳ�
	private static final JedisPoolConfig config;
	private static final JedisPool pool;
	
	static{
		config=new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(2);
		
		pool=new JedisPool(config, Constant.REDIS_HOST, Constant.REDIS_PORT);
	}
	
	
	//��ȡ���ӵķ���
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	//�ͷ�����
	public static void closeJedis(Jedis j){
		if(j!=null){
			j.close();
		}
	}
}
