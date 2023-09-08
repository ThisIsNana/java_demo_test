package com.example.java_demo_test.aspect;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.java_demo_test.vo.GetPersonInfoRequest;

@Component
@Aspect
public class LogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

	// 切入點。定義controller下所有程式都會被包含
	// public=只抓到這個權限以上的方法 *=返回任意型態(萬用) .*=任意類別 .*=任意方法 3(..)任意參數
	@Pointcut("execution (public * com.example.java_demo_test.service.impl.*.*(..))")
	public void servicePointcut() {

	}

	// 想要不同切入點，就開新的方法，更改位址即可
	@Pointcut("execution (public * com.example.java_demo_test.controller.*.*(..))")
	public void controllerPointcut() {

	}

	// 其他切點方法
	// @Before 在目標方法調用"前"通知
	// @After 在目標方法調用"後"通知
	// @Around 在目標方法調用"前後"通知 => 能在方法調用前後自定義一些操作方法
	// @AfterReturning 正常返回通知，在目標方法執行成功之"後"調用通知
	// @AfterThrowing 異常返回通知，在目標方法拋出異常之"後"調用通知(<= 拋出Exception)
	
	
	
	//       ="上方定義的方法名稱()"
	@Before("servicePointcut()")
	public void before() {
		logger.info("beforeService!!!!!!!");
		System.out.println("beforeService~~~~~");	
	}
	

	@Before("execution (public * com.example.java_demo_test.controller.*.*(..))"
			+ "&& args(requestObj, ..)")
	public void controllerBefore(Object requestObj) {
							//A instanceOf B，A是否為B的子類別/相同類別
		System.out.println(requestObj instanceof GetPersonInfoRequest); //true
		System.out.println(requestObj);
											//強轉是為了下面.getRequest()方法可用
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if(attributes != null) {
			String uri = attributes.getRequest().getRequestURI();
			System.out.println(uri); //出來的結果api會多一條斜線/api_name
		}
		
		//另一種方法
		String urii = Objects.requireNonNull(attributes.getRequest().getRequestURI());
		System.out.println(urii);
		
	}
	
	
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("around pjp前");
		//呼叫proceed()才回執行原方法
		Object res = pjp.proceed();
		System.out.println("around pjp後");
		
		return res;
	}
	
	
	
	//        兩個參數:       = 同上面的方法()  ,          = 同下面的方法()
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwableAAA")
	public void afterThrowing(Throwable throwableAAA) {
		System.out.println("錯誤訊息：" + throwableAAA.getMessage());
		System.out.println("===after thorwing===");
	}
	

}
