package com.microuseraccount.model;

import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class UserAccountPK implements Serializable {
    @ManyToOne
    private User userId;
    @ManyToOne
    private Account accountId;

    public UserAccountPK(User user, Account account) {
        this.userId = user;
        this.accountId = account;
    }

    public UserAccountPK() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        UserAccountPK pk = (UserAccountPK) o;
        return Objects.equals( userId, pk.userId ) &&
                Objects.equals( accountId, pk.accountId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( userId, accountId );
    }
}
