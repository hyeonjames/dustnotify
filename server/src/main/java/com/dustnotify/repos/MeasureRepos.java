package com.dustnotify.repos;

import java.util.Date;
import java.util.List;

import com.dustnotify.data.Measure;
import com.dustnotify.data.Station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasureRepos extends JpaRepository<Measure,Long> {
    public Measure findByStationAndDate(Station st, Date dt);

    public List<Measure> findByStation(Station st);
    
    //@Query("SELECT (CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END) FROM Measure d WHERE d.station = ?1 AND d.date >= ?2")
    //public boolean existDate(Station st, Date dt);
    public boolean existsByStationAndDateGreaterThanEqual(Station station, Date date);
}
