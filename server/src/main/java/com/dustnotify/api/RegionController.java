package com.dustnotify.api;

import java.util.List;

import com.dustnotify.data.Region;
import com.dustnotify.data.Station;
import com.dustnotify.repos.RegionRepos;
import com.dustnotify.repos.StationRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired RegionRepos repos;
    @Autowired StationRepos stationRepos;
    @GetMapping("list")
    public List<Region> list(){
        return repos.findAll();
    }

    @GetMapping("station/{regionCode}")
    public List<Station> stationList(@PathVariable("regionCode")String code) {
        return stationRepos.findByRegion(code);
    }
}