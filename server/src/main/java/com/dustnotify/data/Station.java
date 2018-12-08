package com.dustnotify.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	Long statNo;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="region_code")
	Region region;

	@Column(nullable=false, unique=true)
	String stationName;
	
	String addr;
	int year;
	String oper;
	@JsonIgnore
	String photo;
	@JsonIgnore
	String map;
	@JsonIgnore
	String mangName;
	@JsonIgnore
	String item;
	double dmX;
	double dmY;
	
	@JsonIgnore
	Date createdAt;
}
