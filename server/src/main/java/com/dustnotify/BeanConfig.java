package com.dustnotify;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StopWatch;

import com.dustnotify.http.HttpConn;
import com.dustnotify.http.OpenApi;

@Configuration
@EnableTransactionManagement
public class BeanConfig {
	
	@Bean
	public HttpConn httpConn() {
		return new HttpConn();
	}
	
	@Bean
	public OpenApi api() {
		return new OpenApi();
	}
	

	@Bean
	public StopWatch watch() {
		return new StopWatch();
	}
	
}
