package com.microstation.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.microstation.dto.StationDTO;

import lombok.Data;

import org.springframework.data.annotation.Id;



@Document(collection = "station")
@Data
public class StationMongo {
    @Id
    private String id; // El campo "_id" de MongoDB
    private String name;
    private String latitud;
    private String longitud;

    public StationMongo(){
        super();
    }

    public StationMongo(String name, String latitud, String longitud) {
		super();
        this.name = name;
		this.latitud = latitud;
		this.longitud = longitud;
	}

    public StationMongo(StationDTO dto){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
        this.name = dto.getName();
	}

    // Getters y setters
}