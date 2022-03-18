package com.ynov.medical.patients.models;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Patient implements Serializable{
    private String id;
    private String name;
    private long age;
    private String doctor;
    // private List<Pathologie> listPaths;

    public Patient () {

    }

    public Patient (String id, String name, long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Patient (String id, String name, long age, String doctor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.doctor = doctor;
    }

    public Patient(Map<String, Object> map) {
        this.id = (String) map.get("id");
        this.name = (String) map.get("name");
        this.age = (long) map.get("age");
        this.doctor = (String) map.get("doctor");
      }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    // public List<Pathologie> getListPaths() {
    //     return listPaths;
    // }
    // public void setListPaths(List<Pathologie> listPaths) {
    //     this.listPaths = listPaths;
    // }
    
    
}