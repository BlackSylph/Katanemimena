package com.katanemimena.project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userTransferStatements")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.category",
                joinColumns = @JoinColumn(name = "transferStatement_id")) })
public class UserTransferStatement implements Serializable {

    @EmbeddedId
    private UserTransferStatementId pk = new UserTransferStatementId();

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private ERole userRole;

    @Column(name = "accepted")
    @Enumerated(EnumType.STRING)
    private EStatus accepted;

    public UserTransferStatement() {
    }

    public UserTransferStatementId getPk() {
        return pk;
    }

    public void setPk(UserTransferStatementId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public TransferStatement getTransferStatement() {
        return getPk().getTransferStatement();
    }

    public void setTransferStatement(TransferStatement transferStatement) {
        getPk().setTransferStatement(transferStatement);
    }

    public EStatus getAccepted() {
        return accepted;
    }

    public void setAccepted(EStatus accepted) {
        this.accepted = accepted;
    }

    public ERole getUserRole() {
        return userRole;
    }

    public void setUserRole(ERole userRole) {
        this.userRole = userRole;
    }
}
