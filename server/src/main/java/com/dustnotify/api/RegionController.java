package com.dustnotify.api;

import java.util.List;

import com.dustnotify.data.Region;
import com.dustnotify.repos.RegionRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired RegionRepos repos;
    @GetMapping("list")
    public List<Region> list(){
        return repos.findAll();
    }
}