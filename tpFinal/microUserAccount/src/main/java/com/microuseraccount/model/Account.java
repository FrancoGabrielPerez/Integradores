package com.microuseraccount.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microuseraccount.dto.AccountDTO;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private long accountId;
    @Column(name="fecha_alta")
    private Timestamp fechaAlta;
    @Column(name = "habilitada")
	private boolean habilitada;
	@Column(name = "id_mpago")
	private String idMPago;
    @Column(name = "saldo", nullable = false)
	private Double saldo;
	
    
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<UserAccount> usuarios;

    public Account() {
        super();
        this.usuarios = new HashSet<>();
    }

    public Account(boolean habilitada, String idMPago, Double saldo) {
        super();
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.habilitada = habilitada;
		this.idMPago = idMPago;
        this.saldo = saldo;
        this.usuarios = new HashSet<>();
    }

    public Account(AccountDTO entity) {
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.habilitada = entity.isHabilitada();
        this.usuarios = new HashSet<>();
    } 

}
