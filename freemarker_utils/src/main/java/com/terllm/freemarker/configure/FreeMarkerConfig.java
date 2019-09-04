package com.terllm.freemarker.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreeMarkerConfig {

	@Bean
	public FreeMarkerConfigurer freeMarker() {
		FreeMarkerConfigurer  freeMarkers =new FreeMarkerConfigurer();
		freeMarkers.setTemplateLoaderPath("classpath:/static/ftl/");
		freeMarkers.setDefaultEncoding("utf-8");
		return freeMarkers;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
