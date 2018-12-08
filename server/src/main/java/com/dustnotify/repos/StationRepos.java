package com.dustnotify.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.dustnotify.data.Station;

public interface StationRepos extends JpaRepository<Station, Long>{
	Station findByStationName(String statName);

	@Query("SELECT d FROM Station d WHERE d.region.regionCode = ?1")
	List<Station> findByRegion(String regionCode);

	@Query("SELECT COUNT(d) FROM Station d WHERE d.region.regionCode = ?1")
	int countByRegion(String regionCode);
}
