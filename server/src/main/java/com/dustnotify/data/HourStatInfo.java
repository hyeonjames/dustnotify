package com.dustnotify.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hour_stat")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class HourStatInfo extends StatisticInfo {
	
}
