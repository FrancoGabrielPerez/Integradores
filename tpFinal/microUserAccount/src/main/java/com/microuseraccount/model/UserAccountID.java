package com.microuseraccount.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserAccountID
 * 
 * Clase que contiene los atributos para crear una la clave primaria compuesta de una cuenta de usuario.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Data
public class UserAccountID implements Serializable {

    @JoinColumn(name="user_id")    
	@ManyToOne(cascade = CascadeType.PERSIST)	
    private User user;
    
	@ManyToOne(cascade = CascadeType.PERSIST)	
    @JoinColumn(name="account_id")
    private Account account;

    public UserAccountID(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public UserAccountID() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        UserAccountID pk = (UserAccountID) o;
        return Objects.equals( user, pk.user ) &&
                Objects.equals( account, pk.account );
    }

    @Override
    public int hashCode() {
        return Objects.hash( user, account );
    }
}
