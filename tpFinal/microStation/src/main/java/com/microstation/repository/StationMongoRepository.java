package com.microstation.repository;

import org.springframework.stereotype.Repository;

import com.microstation.model.StationMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface StationMongoRepository extends StationMongoRepositoryCustom, MongoRepository<StationMongo, String>{}