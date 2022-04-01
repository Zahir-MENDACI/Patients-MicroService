package com.ynov.netflix.posters.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Document(collection = "poster")
public class Poster implements Serializable{
    private String idPoster;
    private String idContent;
  //  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  //  @Column(name = "time")
    private String urlMorning;
    private String urlNight;
    
    
    

   
	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	public Poster () {

    }

    public Poster (String idPoster, String idContent,String urlMorning, String urlNight) {
        this.idPoster = idPoster;
        this.idContent = idContent;
        this.urlMorning = urlMorning;
        this.urlNight = urlNight;
    }
    

//    public Patient(Map<String, Object> map) {
//        this.id = (String) map.get("id");
//        this.name = (String) map.get("name");
//        this.age = (long) map.get("age");
//        this.doctor = (String) map.get("doctor");
//      }
    
    public String getIdPoster() {
        return idPoster;
    }
    public void setIdPoster(String idPoster) {
        this.idPoster = idPoster;
    }
    public String getIdContent() {
        return idContent;
    }
    public void setName(String idContent) {
        this.idContent = idContent;
    }
    public String getUrlMorning() {
        return urlMorning;
    }
    public void setUrlMorning(String urlMorning) {
        this.urlMorning = urlMorning;
    }
    
    public String getUrlNight() {
        return urlNight;
    }
    public void setUrlNight(String urlNight) {
        this.urlNight = urlNight;
    }

	
    
    
    
    
    
}