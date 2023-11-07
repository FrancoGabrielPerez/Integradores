package com.microstation.repository;

import org.springframework.stereotype.Repository;

import com.microstation.model.StationMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * StationMongoRepository
 * 
 * Clase que contiene los metodos de acceso a la base de datos Mongo.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 *  
 */
@Repository
public interface StationMongoRepository extends StationMongoRepositoryCustom, MongoRepository<StationMongo, String>{}