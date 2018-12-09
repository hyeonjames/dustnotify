package com.dustnotify.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@Table(name="daily_stat")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class DailyStatInfo extends StatisticInfo {
	
}
