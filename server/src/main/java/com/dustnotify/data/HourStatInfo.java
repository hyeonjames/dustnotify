package com.dustnotify.data;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="hour_stat")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class HourStatInfo extends StatisticInfo {
	
}
