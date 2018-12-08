package com.dustnotify.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.dustnotify.data.Measure;
import com.dustnotify.data.Station;
import com.dustnotify.http.OpenApi;
import com.dustnotify.repos.MeasureRepos;
import com.dustnotify.repos.StationRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("measure")
public class MeasureController {
    @Autowired MeasureRepos measureRepos;
    @Autowired OpenApi api;
    @Autowired StationRepos stationRepos;
    /*
    @RequestMapping("list/{stationName}")
    public List<Measure> list(@PathVariable("stationName")String stationName){
        System.out.println("MEASURE " + stationName);
        Station station = stationRepos.findByStationName(stationName);
        if(station == null) {
            return new ArrayList<Measure>();
        }
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -1);
        if(!measureRepos.existDate(station, cal.getTime())) {
            try {
                api.getMeasure(station);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return measureRepos.findByStation(station);
    }
    */

    
}