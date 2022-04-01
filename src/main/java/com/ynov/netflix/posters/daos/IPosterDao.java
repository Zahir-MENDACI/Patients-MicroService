package com.ynov.netflix.posters.daos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ynov.netflix.posters.models.Poster;

@Repository
public interface IPosterDao extends MongoRepository<Poster, String> {

    Poster findByName(String idContent);
    
}
