package com.dustnotify;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dustnotify.http.OpenApi;
@Component
public class DataInitializer implements CommandLineRunner{

	@Value("${spring.jpa.hibernate.ddl-auto}")
	String ddlAuto;
	
	@Autowired
	OpenApi api;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(ddlAuto.equals("create") || ddlAuto.equals("create-drop")) {
			// 초기 데이터 받아옴
			//log.debug("초기 데이터 생성 시작");
			
			api.getStationData();
		}
	}

}
