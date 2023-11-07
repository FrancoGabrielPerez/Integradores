package com.microstation.repository;

import com.microstation.model.StationMongo;

/**
 * StationMongoRepositoryCustom
 * 
 * Clase que contiene la definicion del repositorio Custom, donde se resuelven las consultas
 * que no se pueden resolver con el CRUD de MongoRepository.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 *  
 */
public interface StationMongoRepositoryCustom{
    public StationMongo findByLatitudAndLongitud(String latitud, String longitud);
}