package com.zhongwang.service.impl;

import com.zhongwang.dao.SpringAopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhongwang.service.SpringAopService;
import org.springframework.stereotype.Service;

@Service("springAopService")
public class SpringAopServiceImpl implements SpringAopService {
	@Autowired
	private SpringAopDao springAopDao;
	@Override
	public void query() {
		System.out.println("my phone is 17625625839");
	}

	@Override
	public void query1(String str) {
		System.out.println("my phone is 17625625839");
	}

	@Override
	public void sayHello() {
		int a = 31415926;
		int b =  springAopDao.insert(a);
		System.out.println(b);
	}

}
