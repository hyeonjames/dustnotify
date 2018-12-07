package com.dustnotify.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long statNo;
	
	@Column(unique=true, nullable=false)
	String stationName;
	String addr;
	int year;
	String oper;
	String photo;
	String map;
	String mangName;
	String item;
	double dmX;
	double dmY;
	
	Date createdAt;
}
