package com.dustnotify.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Region {
	
	@Id
	@Column(name="region_code" ,insertable=false, updatable=false, length=10)
	String regionCode;
	
	String regionKorName;
	
	String regionEngName;
	
}
