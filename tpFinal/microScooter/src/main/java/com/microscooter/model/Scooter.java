package com.microscooter.model;

import com.microscooter.dto.ScooterDTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "station")
public class Scooter{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scooter_id")
	private long scooterId;

	@Column(name = "latitud")
	private String latitud;

	@Column(name = "longitud")
	private String longitud;

    @Column(name = "estado")
    private String estado;

    @Column(name = "kilometros")
    private double kilometros;

    @Column(name = "tiempoDeUso")
    private int tiempoDeUso;

    @Column(name = "tiempoEnPausa")
    private int tiempoEnpausa;

    public Scooter(){
        super();
    }

    public Scooter(long QR,String latitud,String longitud){
        super();
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = "Libre";
        this.kilometros = 0;
        this.tiempoDeUso = 0;
        this.tiempoEnpausa = 0;
    }

    public Scooter(ScooterDTO dto){
        this.latitud = dto.getLatitud();
        this.longitud = dto.getLongitud();
    }
}