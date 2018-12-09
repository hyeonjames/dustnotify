package com.dustnotify.repos;

import java.util.Date;
import java.util.List;

import com.dustnotify.data.StatisticInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatInfoRepos<T extends StatisticInfo> extends JpaRepository<T, Long> {
	@Query("SELECT (CASE WHEN COUNT(u.date) > 0 THEN TRUE ELSE FALSE END) " + "FROM #{#entityName} u "
			+ "WHERE u.date = ?1")
	public boolean existByDate(Date date);

	@Query("SELECT s FROM #{#entityName} s WHERE s.date >= ?1 AND s.date <= ?2 ORDER BY s.date ASC")
	List<T> list(Date from, Date to);

}
