package com.zhongwang.Aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
//声明一个切面
@Component
@Aspect
public class MyAspect {
	//声明一个切入点
	//连接点 是 com.zhongwang.service.impl包下的任意接口或类的任意方法
	@Pointcut("execution(* com.zhongwang.service.*.*(..))")
	public void anyService() {
		
	}
	//匹配运行时传递的参数类型为指定类型 的、且参数个数和顺序匹配
	@Pointcut("args(java.lang.String)")
	public void argsService() {
		
	}
	//去掉有一个参数的
	@Pointcut("execution(* com.zhongwang.service.*.*(..))&&!argsService()")
	public void anyService1() {
		
	}
	@Pointcut("this(com.zhongwang.service.impl.SpringAopServiceImpl)")
	public void thisService() {
		
	}
	@Before("thisService()")
	public void thisAdvice() {
		System.out.println("***********************");
	}
	
	//通知  方法的执行时机  
//	@Before("anyService()")
//	public void advice() {
//		System.out.println("please say your telephone?");
//	}
//	//一个参数的为String的
//	@Before("argsService()")
//	public void Argsadvice() {
//		System.out.println("HH!!!!");
//	}
	
	//一个参数的为String的
//	@Before("anyService1()")
//	public void Argsadvice1() {
//		System.out.println("HH!!!!");
//	}
}
