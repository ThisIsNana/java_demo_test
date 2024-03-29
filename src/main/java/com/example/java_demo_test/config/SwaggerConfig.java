//package com.example.java_demo_test.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration // 跟 Spring 說此 class 為配置類
//@EnableOpenApi // 通過注釋來啟用swagger
//@EnableWebMvc // 引入DelegatingWebMvcConfiguration配置類，並啟用spring MVC
//public class SwaggerConfig {
//	
//	//接口訊息，包括標題、說明、版本、聯絡人.....(還有很多其他有的沒的，有需要再用)
//	private ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//			.title("java_demo_test Restful API") //標題
//			.description("java_demo_test API") //說明  
////			.termsOfServiceUrl("urn:tos") //服務條款網址，默認urn:tos
////			.contact(new Contact("DEFAULT", "", "")) //聯絡人
////			.license("Apache 2.0")
////			.version("v3") //版本
////			.licenseUrl("http://www.apache.org/licenses/Licence-2.0.txt")
//			.build();
//	
//	@Bean
//	public Docket api() {
//		//返回文檔概要訊息
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(DEFAULT_API_INFO)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.java_demo_test.controller")) //controller的路徑
//				.paths(PathSelectors.any())
//				.build();
//	}
//	
//}
