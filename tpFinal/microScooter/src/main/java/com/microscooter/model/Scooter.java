package com.microscooter.model;

import com.microscooter.dto.ScooterDTO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

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
		this.estado = "Libre";
		this.kilometros = 0;
		this.tiempoDeUso = 0;
		this.tiempoEnpausa = 0;
		this.latitud = "0";
		this.longitud = "0";
    }

//    public Scooter(String latitud,String longitud){
//        super();
//        this.latitud = latitud;
//        this.longitud = longitud;
//        this.estado = "Libre";
//        this.kilometros = 0;
//        this.tiempoDeUso = 0;
//        this.tiempoEnpausa = 0;
//    }

    public Scooter(@NonNull ScooterDTO dto){ //dejamos el notnull?
		super();
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		this.estado = dto.getEstado();
		this.kilometros = dto.getKilometros();
		this.tiempoDeUso = dto.getTiempoDeUso();
		this.tiempoEnpausa = dto.getTiempoEnpausa();
    }

	public void setFromDTO(@NonNull ScooterDTO dto){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		this.estado = dto.getEstado();
		this.kilometros = dto.getKilometros();
		this.tiempoDeUso = dto.getTiempoDeUso();
		this.tiempoEnpausa = dto.getTiempoEnpausa();
	}
}