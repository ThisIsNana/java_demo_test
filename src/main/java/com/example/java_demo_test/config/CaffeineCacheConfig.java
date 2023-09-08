package com.example.java_demo_test.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching	// Spring frame���Ψ��X�ʽw�s������
				// �e�����ܤ֭n���@��CacheManager��Bean
@Configuration	// �t�m���]�w�ҡA�å��SpringBoot�U��
public class CaffeineCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder()
				//�]�w�L���ɶ��A1 �̫�@���g�J �� 2.�X�ݫ�L�T�w�@�q�ɶ�
				.expireAfterAccess(600, TimeUnit.SECONDS)
				//��l���w�s�Ŷ��j�p
				.initialCapacity(100)
				//�w�s���̤j����
				.maximumSize(500));
		
		return cacheManager;
	}
	
	
}
