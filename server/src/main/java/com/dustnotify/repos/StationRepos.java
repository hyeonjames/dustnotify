package com.dustnotify.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dustnotify.data.Station;

public interface StationRepos extends JpaRepository<Station, Long>{
	Station findByStationName(String statName);
}
