package com.dustnotify.scheduling;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.dustnotify.http.OpenApi;

@Component
public class HourStatTimer {

	private AtomicInteger loopCounter = new AtomicInteger();

	private StopWatch watch = new StopWatch();
	
	@PostConstruct
	public void init() {
		watch.start();
		
	}
	@Autowired
	private OpenApi api;
	
	@Scheduled(fixedDelay=1000*3600)
	public void tick() throws Exception {
		watch.stop();
		System.out.println("tick start");
		api.getHourStat("PM10", 999);
		watch.start("task-" + String.valueOf(loopCounter.getAndIncrement()));
	}
	
}
