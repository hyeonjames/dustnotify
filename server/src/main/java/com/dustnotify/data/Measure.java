package com.dustnotify.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"date", "stat_no"})})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Measure {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    Long measureNo;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Setter
    Date date;
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
    @JoinColumn(name="stat_no")
    @JsonIgnore
    @Setter
    Station station;

    @JsonProperty
    int pm10Value;
    @JsonProperty
    int pm10Value24;
    @JsonProperty
    int pm25Value;
    @JsonProperty
    int pm25Value24;

    @JsonProperty("stationName")
    public String stationName(){
        if(station == null) return "";
        return station.getStationName();
    }
}