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
		for (int i = 0; i < 2000; i++) {
			springAopDao.insert(i);
			System.out.println(i);
		}
	}

}
