package com.microuseraccount.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="account_id")
    private Integer accountId;
    @Column(name="fecha_alta")
    private Timestamp fechaAlta;


    
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "accountId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAccount> usuarios;

    public Account(){
        super();
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.usuarios = new HashSet<>();
    }

}
