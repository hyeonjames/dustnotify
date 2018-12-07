package com.dustnotify.http;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dustnotify.data.DailyStatInfo;
import com.dustnotify.data.HourStatInfo;
import com.dustnotify.data.Station;
import com.dustnotify.etc.QueryMap;
import com.dustnotify.repos.DailyStatInfoRepos;
import com.dustnotify.repos.HourStatInfoRepos;
import com.dustnotify.repos.StationRepos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenApi{
	@Autowired
	HttpConn con;

	@Value("${project.openapi.service.key}")
	String serviceKey;

	@Autowired
	DailyStatInfoRepos dRepos;
	
	@Autowired
	StationRepos stationRepos;
	public void getStationData() throws IOException {
		final String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList";
		QueryMap q = new QueryMap();
		q.set("numOfRows", 999)
		.set("pageNo", 1)
		.set("serviceKey", serviceKey)
		.set("_returnType", "json");
		ObjectMapper mapper = new ObjectMapper();
		List<Station> stat = getData(url, q, (d)->{
			Station st;
			
			try {
				String stationName = d.get("stationName").asText();
				st = stationRepos.findByStationName(stationName);
				if(st == null) {
					st = mapper.treeToValue(d, Station.class);
				} else {
					return st;
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			st.setCreatedAt(new Date());
			try {
				
				stationRepos.save(st);
			} catch(Exception e) {
				
			}
			return st;
		});
	}
	
	public <T> List<T> getData(String url, QueryMap q, JNodeConvertFunc<T> func) throws IOException {
		ArrayList<T> list = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = con.get(url, q);
		JsonNode nd = mapper.readTree(json);
		JsonNode l = nd.get("list");
		for(JsonNode o : l) {
			try {
				list.add(func.method(o));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	@Autowired
	HourStatInfoRepos hRepos;
	public void getHourStat(String itemCode, int numRows) throws Exception {
		String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst";
		QueryMap q = new QueryMap().set("serviceKey", serviceKey)
		.set("numOfRows", numRows)
		.set("itemCode", itemCode)
		.set("dataGubun", "HOUR")
		.set("searchCondition", "MONTH")
		.set("_returnType", "json");
		ObjectMapper mapper = new ObjectMapper();
		List<HourStatInfo> list = getData(url, q, (nd)->{
			HourStatInfo di;
			try {
				di = mapper.treeToValue(nd, HourStatInfo.class);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dt = df.parse(nd.get("dataTime").asText());
			di.setDate(dt);
			di.setCreatedAt(new Date());
			if(!hRepos.existByDate(dt)) {
				hRepos.save(di);
			}
			return di;
		});
	}
	
	public void getDailyStat(String itemCode, int numRows) throws Exception {
		//http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst
		String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst";
		QueryMap q = new QueryMap().set("serviceKey", serviceKey)
		.set("numOfRows", numRows)
		.set("itemCode", itemCode)
		.set("dataGubun", "DAILY")
		.set("searchCondition", "MONTH")
		.set("_returnType", "json");
		ObjectMapper mapper = new ObjectMapper();
		List<DailyStatInfo> list = getData(url, q, (nd)->{
			DailyStatInfo di;
			try {
				di = mapper.treeToValue(nd, DailyStatInfo.class);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = df.parse(nd.get("dataTime").asText());
			di.setDate(dt);
			
			di.setCreatedAt(new Date());
			if(!dRepos.existByDate(dt)) {
				dRepos.save(di);
			}
			return di;
		});

	}
}
