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

	// ���J�I�C�w�qcontroller�U�Ҧ��{�����|�Q�]�t
	// public=�u���o���v���H�W����k *=��^���N���A(�U��) .*=���N���O .*=���N��k 3(..)���N�Ѽ�
	@Pointcut("execution (public * com.example.java_demo_test.service.impl.*.*(..))")
	public void servicePointcut() {

	}

	// �Q�n���P���J�I�A�N�}�s����k�A����}�Y�i
	@Pointcut("execution (public * com.example.java_demo_test.controller.*.*(..))")
	public void controllerPointcut() {

	}

	// ��L���I��k
	// @Before �b�ؼФ�k�ե�"�e"�q��
	// @After �b�ؼФ�k�ե�"��"�q��
	// @Around �b�ؼФ�k�ե�"�e��"�q�� => ��b��k�եΫe��۩w�q�@�Ǿާ@��k
	// @AfterReturning ���`��^�q���A�b�ؼФ�k���榨�\��"��"�եγq��
	// @AfterThrowing ���`��^�q���A�b�ؼФ�k�ߥX���`��"��"�եγq��(<= �ߥXException)
	
	
	
	//       ="�W��w�q����k�W��()"
	@Before("servicePointcut()")
	public void before() {
		logger.info("beforeService!!!!!!!");
		System.out.println("beforeService~~~~~");	
	}
	

	@Before("execution (public * com.example.java_demo_test.controller.*.*(..))"
			+ "&& args(requestObj, ..)")
	public void controllerBefore(Object requestObj) {
							//A instanceOf B�AA�O�_��B���l���O/�ۦP���O
		System.out.println(requestObj instanceof GetPersonInfoRequest); //true
		System.out.println(requestObj);
											//�j��O���F�U��.getRequest()��k�i��
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if(attributes != null) {
			String uri = attributes.getRequest().getRequestURI();
			System.out.println(uri); //�X�Ӫ����Gapi�|�h�@���׽u/api_name
		}
		
		//�t�@�ؤ�k
		String urii = Objects.requireNonNull(attributes.getRequest().getRequestURI());
		System.out.println(urii);
		
	}
	
	
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("around pjp�e");
		//�I�sproceed()�~�^������k
		Object res = pjp.proceed();
		System.out.println("around pjp��");
		
		return res;
	}
	
	
	
	//        ��ӰѼ�:       = �P�W������k()  ,          = �P�U������k()
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwableAAA")
	public void afterThrowing(Throwable throwableAAA) {
		System.out.println("���~�T���G" + throwableAAA.getMessage());
		System.out.println("===after thorwing===");
	}
	

}
