package com.microstation.repository;

import com.microstation.model.StationMongo;

public interface StationMongoRepositoryCustom{
    public StationMongo findByLatitudAndLongitud(String latitud, String longitud);
}