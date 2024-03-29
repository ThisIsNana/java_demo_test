package com.example.java_demo_test.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching	// Spring frame中用來驅動緩存的註解
				// 容器內至少要有一個CacheManager的Bean
@Configuration	// 配置成設定黨，並交由SpringBoot託管
public class CaffeineCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder()
				//設定過期時間，1 最後一次寫入 或 2.訪問後過固定一段時間
				.expireAfterAccess(600, TimeUnit.SECONDS)
				//初始的緩存空間大小
				.initialCapacity(100)
				//緩存的最大筆數
				.maximumSize(500));
		
		return cacheManager;
	}
	
	
}
