package com.microstation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.microstation.model.StationMongo;

/**
 * StationMongoRepositoryCustomImpl
 * 
 * Clase que contiene la implementacion del repositorio Custom, donde se resuelven las consultas
 * que no se pueden resolver con el CRUD de MongoRepository.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 *  
 */
public class StationMongoRepositoryCustomImpl implements StationMongoRepositoryCustom{
    
	@Autowired
    private MongoTemplate mongoTemplate;

    public StationMongo findByLatitudAndLongitud(String latitud, String longitud){
        Query query = new Query();
        query.addCriteria(Criteria.where("latitud").is(latitud).and("longitud").is(longitud));
        return mongoTemplate.findOne(query, StationMongo.class, "stations");
    };
}