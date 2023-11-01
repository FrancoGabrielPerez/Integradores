package com.microuseraccount.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
public class UserAccountID implements Serializable {

    @JoinColumn(name="user_id")    
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)	
    private User user;
    
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)	
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