package com.dustnotify.repos;

import com.dustnotify.data.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepos extends JpaRepository<Region,Long> {
    
    @Query("SELECT r FROM Region r WHERE ?1 LIKE CONCAT(r.regionKorName,'%')")
    public Region getByAddr(String addr);
}