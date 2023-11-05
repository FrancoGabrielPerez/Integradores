package com.microstation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.microstation.model.StationMongo;

public class StationMongoRepositoryCustomImpl implements StationMongoRepositoryCustom{
    
	@Autowired
    private MongoTemplate mongoTemplate;

    public StationMongo findByLatitudAndLongitud(String latitud, String longitud){
        Query query = new Query();
        query.addCriteria(Criteria.where("latitud").is(latitud).and("longitud").is(longitud));
        return mongoTemplate.findOne(query, StationMongo.class, "station");
        // Query query = new Query(Criteria.where("field").is("value"));
        // List<DocumentType> result = mongoTemplate.find(query, DocumentType.class, "collectionName");
    };
}