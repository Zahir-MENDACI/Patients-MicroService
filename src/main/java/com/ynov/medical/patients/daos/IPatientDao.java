package com.ynov.medical.patients.daos;

import java.util.List;

import com.ynov.medical.patients.models.Patient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientDao extends MongoRepository<Patient, String> {

    Patient findByName(String name);
    
}
