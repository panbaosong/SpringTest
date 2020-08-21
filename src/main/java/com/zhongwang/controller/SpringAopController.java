package com.zhongwang.controller;

import com.zhongwang.service.SpringAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("aop")
public class SpringAopController {
	@Autowired
	SpringAopService springAopService;
	@RequestMapping("sayHello")
	public void sayHello() {
		springAopService.sayHello();
	}
	@RequestMapping("say")
	public String say(){
		System.out.println("执行say()方法");
		return "hello";
	}
}
