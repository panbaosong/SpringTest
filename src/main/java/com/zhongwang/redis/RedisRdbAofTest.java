package com.zhongwang.redis;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/18 11:28
 * @Description:Redis中的两种持久化方式
 * 什么是Redis持久化 Redis有哪种持久化机制，有什么优缺点
 * 持久化就是把内存的数据写到磁盘中去，防止服务器宕机了内存数据丢失
 * 两种持久化方式  AOF RDB
 *  RDB 周期性的把内存中的数据写入到磁盘中去 ，生成.RDB文件 ，如果服务器意外宕机 ，容易造成数据丢失
 *  AOF 只要执行dml操作（新增修改删除）登操作，实时会把操作信息写入到日志记录中，如果服务器宕机，重启后直接从日志中提取数据
 */
public class RedisRdbAofTest {


}
