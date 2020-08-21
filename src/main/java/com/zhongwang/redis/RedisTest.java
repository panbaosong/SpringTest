package com.zhongwang.redis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;
/**
 * @Auther: liulonganys
 * @Date: 2020/8/18 09:47
 * @Description:Redis常见面试题
 *  1.Redis支持的数据类型？
 *    String Hash List Set Zset
 *    String  字符串
 *    格式: set key value
 *    string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
 *    string类型是Redis最基本的数据类型，一个键最大能存储512MB。
 *    Hash
 *    格式: hmset name  key1 value1 key2 value2
 *    Redis hash 是一个键值(key=>value)对集合。
 *    Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。
 *    List（列表）
 *    Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）
 *    格式: lpush  name  value
 *    在 key 对应 list 的头部添加字符串元素
 *    格式: rpush  name  value
 *    在 key 对应 list 的尾部添加字符串元素
 *    格式: lrem name  index
 *    key 对应 list 中删除 count 个和 value 相同的元素
 *    格式: llen name
 *    返回 key 对应 list 的长度
 *    Set（集合）
 *    格式: sadd  name  value
 *    Redis的Set是string类型的无序集合。
 *    集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
 *    zset(sorted set：有序集合)
 *    格式: zadd  name score value
 *    Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。
 *    不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
 *    zset的成员是唯一的,但分数(score)却可以重复。
 */
public class RedisTest {
        //1.单独连接1台Redis服务器
        private static Jedis jedis;//单独的一个实例对象
        //2.主从、哨兵使用shard
        private static ShardedJedis shard;//分片 用于提高系统性能
        //3.连接池(10 jedis)
        private static ShardedJedisPool pool;//;连接池,里面一堆Redis实例
        @BeforeClass
        public static void setUpBeforeClass(){
            //单个节点
            jedis = new Jedis("127.0.0.1",6379);
            //分片
            List<JedisShardInfo>shards = Arrays.asList(new JedisShardInfo("127.0.0.1",6379));
            shard = new ShardedJedis(shards);

            GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
            goConfig.setMaxTotal(100);
            goConfig.setMaxIdle(20);
            goConfig.setMaxWaitMillis(-1);
            goConfig.setTestOnBorrow(true);
            pool = new ShardedJedisPool(goConfig,shards);
        }
        @AfterClass
        public static void tearDownAfterClass(){
            jedis.disconnect();
            shard.disconnect();
            pool.destroy();
        }
        /**
         * jedis操作String
         */
        @Test
        public void testString(){
            //添加数据
            jedis.set("name", "pan");//向key-->name中放入了value-->pan
            System.out.println(jedis.get("name"));//执行结果pan

            jedis.append("name", " is my lover");//拼接
            System.out.println(jedis.get("name"));

            jedis.del("name");//删除某个键
            System.out.println(jedis.get("name"));

            //设置多个键值对
            jedis.mset("name","pan","age","26","qq","957645348");
            jedis.incr("age");//进行加1操作
            System.out.println(jedis.get("name") +"-"+ jedis.get("age") +"-"+ jedis.get("qq"));
        }
        /**
         * jedis操作Hash
         */
        @Test
        public void testMap(){
            //添加数据
            Map<String,String>map = new HashMap<String,String>();
            map.put("name", "cengceng");
            map.put("age", "28");
            map.put("qq", "1532940527");
            jedis.hmset("user",map);
            //取出user中的name 执行结果cengceng
            //第一个参数是存入redis中map对象的key 后面跟的是放入map中对象的key,后面的key可跟多个是可变参数
            List<String>rsmap  = jedis.hmget("user", "name","age","qq");
            System.out.println(rsmap);
            //删除map中某个键值
            jedis.hdel("user", "qq");
            System.out.println(jedis.hmget("user", "qq"));//因为删除了，所有返回的是null
            System.out.println(jedis.hlen("user"));//返回key为user的键中存放的值的个数
            System.out.println(jedis.exists("user"));//是否存在key为user的记录
            System.out.println(jedis.hkeys("user"));//返回map对象中所有的key
            System.out.println(jedis.hvals("user"));//返回map对象中所有的value

            //遍历所有的key value
            Iterator<String>iter = jedis.hkeys("user").iterator();
            while(iter.hasNext()){
                String key = iter.next();
                System.out.println(key+":"+jedis.hmget("user", key));
            }
        }
        /**
         * jedis操作List
         */
        @Test
        public void testList(){
            //开始前先移除所有内容
            jedis.del("java framework");
            System.out.println(jedis.lrange("java framework", 0, -1));

            //先向key java framework 存放三条数据
            //lpush 从头部加入 栈 先进后出
            jedis.lpush("java framework", "string");
            jedis.lpush("java framework", "struts");
            jedis.lpush("java framework", "hibernate");
            //再取出所有数据jedis.lrange是按范围输出
            //第一个是key 第二个是起始位置 第三个是结束位置 jedis.llen获取长度为-1 表示获得所有
            System.out.println(jedis.lrange("java framework", 0, -1));

            //从尾部添加 先进先出 消息队列
            jedis.del("java framework");
            jedis.rpush("java framework", "string");
            jedis.rpush("java framework", "struts");
            jedis.rpush("java framework", "hibernate");
            System.out.println(jedis.lrange("java framework", 0, -1));
        }
        /**
         * jedis操作Set
         */
        @Test
        public void testSet(){
            //添加
            jedis.sadd("user", "liuling");
            jedis.sadd("user", "xinxin");
            jedis.sadd("user", "ling");
            jedis.sadd("user", "zhangxinxin");
            jedis.sadd("user", "who");
            //异常noname
            jedis.srem("user", "who");
            System.out.println(jedis.smembers("user"));//获取所有加入的value
            System.out.println(jedis.sismember("user", "who"));//判断who是否为 user集合中的元素
            System.out.println(jedis.srandmember("user"));//随机返回一个元素
            System.out.println(jedis.scard("user"));//返回集合的元素的个数
        }
        @Test
        public void testRLpush(){
            //jedis 排序
            //注意 此处的rpush和lpush是List的操作 是一个双向链表
            jedis.del("a");//先清除数据 再加入数据进行测试
            jedis.rpush("a", "1");
            jedis.lpush("a", "6");
            jedis.lpush("a", "3");
            jedis.lpush("a", "9");
            System.out.println(jedis.lrange("a", 0, -1));//[9,3,6,1]
            System.out.println(jedis.sort("a"));//[1,3,6,9] 输入后排序效果
            System.out.println(jedis.lrange("a", 0, -1));
        }
        /**
         * jedis操作事物
         */
        @Test
        public void testTrans(){
            long start = System.currentTimeMillis();
            Transaction tx = jedis.multi();
            for(int i = 0; i < 10000; i++){
                tx.set("t" + i, "t" + i);
            }
//		System.out.println(tx.get("t1000").get());
            List<Object>result = tx.exec();
            long end = System.currentTimeMillis();
            System.out.println("Transaction SET: " + ((end-start)/1000.0) + " seconds");
        }
        /**
         * jedis操作事物
         */
        @Test
        public void testPipelineTrans(){
            long start = System.currentTimeMillis();
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();
            for(int i = 0; i < 10000; i++){
                pipeline.set("t" + i, "t" + i);
            }
            pipeline.exec();
            List<Object>result = pipeline.syncAndReturnAll();
            long end = System.currentTimeMillis();
            System.out.println("Pipeline Transaction SET: " + ((end-start)/1000.0) + " seconds");
        }
        /**
         * 分片
         */
        @Test
        public void testShard(){
            long start = System.currentTimeMillis();
            for(int i = 0; i < 100000; i++){
                String result = shard.set("shard" + i, "n" + i);
            }
            long end = System.currentTimeMillis();
            System.out.println("Shard SET: " + ((end-start)/1000.0) + " seconds");
        }
        /**
         * 分片事物
         */
        @Test
        public void testShardpipelined(){
            ShardedJedisPipeline pipeline = shard.pipelined();
            long start = System.currentTimeMillis();
            for(int i = 0; i < 100000; i++){
                pipeline.set("sp" + i, "p" + i);
            }
            List<Object>results = pipeline.syncAndReturnAll();
            long end = System.currentTimeMillis();
            System.out.println("Shardpipelined SET: " + ((end-start)/1000.0) + " seconds");
        }

        /**
         * 分片连接池
         */
        @Test
        public void testShardpool(){
            ShardedJedis sj = pool.getResource();
            long start = System.currentTimeMillis();
            for(int i = 0; i < 100000; i++){
                String result = sj.set("spn" + i, "n" + i);
            }
            long end = System.currentTimeMillis();
            pool.returnResource(shard);
            System.out.println("shardPool SET: " + ((end-start)/1000.0) + " seconds");
        }
    }


