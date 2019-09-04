package com.terllm.freemarker.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * 配置swagger
 * 
 * @author Administrator
 *
 */
@Configuration
public class SwaggerConfigers {

	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.terllm.freemarker.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springboot利用swagger构建api文档")
				.description("简单优雅的restfun风格,http://127.0.0.1:8080/swagger-ui.html#/")
				.termsOfServiceUrl("http://127.0.0.1:8080/wcapps/swagger-ui.html#/")
				.contact("terllm")
				.version("1.0")
				.build();
	}
	
	
	
	
	
}
