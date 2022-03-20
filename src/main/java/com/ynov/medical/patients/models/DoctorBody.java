package com.ynov.medical.patients.models;

import java.io.Serializable;
import java.util.Map;


public class DoctorBody implements Serializable{
    private String patient;
    private String doctor;

    public DoctorBody () {

    }

    public DoctorBody (String patient, String doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }


}
