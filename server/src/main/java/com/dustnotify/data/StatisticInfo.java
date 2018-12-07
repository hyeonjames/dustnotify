package com.dustnotify.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class StatisticInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	Long infoNo;
	
	@Column(name="date",  nullable=false, unique=true, length=30)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date date;
	
	@JsonIgnore
	Date createdAt;
	
	int seoul;
	int busan;
	int daegu;
	int incheon;
	int gwangju;
	int daejeon;
	int ulsan;
	int gyeonggi;
	int gangwon;
	int chungbuk;
	int chungnam;
	int jeonbuk;
	int jeonnam;
	int gyeongbuk;
	int gyeongnam;
	int jeju;
	int sejong;
}
