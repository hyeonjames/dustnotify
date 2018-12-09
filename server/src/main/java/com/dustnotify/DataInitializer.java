package com.dustnotify;

import com.dustnotify.http.OpenApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	String ddlAuto;

	@Autowired
	OpenApi api;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		api.getStationData();
	}

}
