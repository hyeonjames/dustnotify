package com.dustnotify.scheduling;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.dustnotify.http.OpenApi;

@Component
public class DailyStatTimer {
	
	private AtomicInteger loopCounter = new AtomicInteger();
	
	private StopWatch watch = new StopWatch();
	
	@PostConstruct
	public void init() {
		watch.start();
		
	}
	@Autowired
	private OpenApi api;
	
	@Scheduled(fixedDelay=1000*3600*12)
	public void tick() throws Exception {
		watch.stop();
		System.out.println("tick start");
		api.getDailyStat("PM10", 30);
		watch.start("task-" + String.valueOf(loopCounter.getAndIncrement()));
	}
	
}
