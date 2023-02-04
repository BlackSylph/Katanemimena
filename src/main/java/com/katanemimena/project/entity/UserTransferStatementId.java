package com.katanemimena.project.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserTransferStatementId implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private TransferStatement transferStatement;

    public UserTransferStatementId() {
    }

    public UserTransferStatementId(User user, TransferStatement transferStatement) {
        this.user = user;
        this.transferStatement = transferStatement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransferStatement getTransferStatement() {
        return transferStatement;
    }

    public void setTransferStatement(TransferStatement transferStatement) {
        this.transferStatement = transferStatement;
    }
}
