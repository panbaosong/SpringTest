package com.zhongwang.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhongwang.service.SpringAopService;
import com.zhongwang.service.impl.SpringAopServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext-mvc.xml"})
public class SpringAopTest{
	@Autowired
	ApplicationContext ctx;
	@Test
	public void test() {
		SpringAopService sa = (SpringAopService) ctx.getBean("c");
		System.out.println("query");
		sa.query();
		System.out.println("query1");
		sa.query1("1");
	}
}
