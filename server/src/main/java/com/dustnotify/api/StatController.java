package com.dustnotify.api;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dustnotify.data.DailyStatInfo;
import com.dustnotify.data.HourStatInfo;
import com.dustnotify.data.StatisticInfo;
import com.dustnotify.http.OpenApi;
import com.dustnotify.repos.DailyStatInfoRepos;
import com.dustnotify.repos.HourStatInfoRepos;

@RestController
@RequestMapping("stat")
public class StatController {
	
	@Autowired OpenApi api;
	
	@Autowired DailyStatInfoRepos dRepos;
	@Autowired HourStatInfoRepos hRepos;
	
	@GetMapping("get/daily")
	public List<DailyStatInfo> getDaily(){
		Date to = new Date();
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		return dRepos.list(c.getTime(), to);
	}
	
	@GetMapping("get/hour")
	public List<HourStatInfo> getHour(){
		Date to = new Date();
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		List<HourStatInfo> list =  hRepos.list(c.getTime(),to);
		
		return list;
	}
}
